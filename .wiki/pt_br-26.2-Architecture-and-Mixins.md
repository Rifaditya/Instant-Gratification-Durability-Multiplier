# Arquitetura e descritores de Mixins (26.2)

| Propriedade Arquitetônica | Valor |
| :--- | :--- |
| **Pacote Raiz** | `net.instantgratification.durabilitymultiplier` |
| **Nível de Compatibilidade** | `JAVA_25` |
| **Configuração de Mixins** | `durability-multiplier.mixins.json` |
| **Requisito Padrão do Injetor** | `1` |
| **Proteção Contra Reentrada** | `ThreadLocal<Boolean> dm$processing` |

---

## 🌳 Hierarquia de pacotes em ASCII

```
net.instantgratification.durabilitymultiplier/
├── DurabilityHelper.java               # Stateless damage reduction & classification engine
├── DurabilityMultiplier.java           # Common mod initializer & logger
├── DurabilityMultiplierFabric.java     # Fabric main entrypoint
├── DurabilityMultiplierFabricClient.java # Client entrypoint & network receiver
├── config/
│   ├── ClothConfigScreenHelper.java    # Optional Cloth Config GUI builder
│   ├── DurabilityConfig.java           # JSON configuration loader & save handler
│   └── ModMenuIntegration.java         # ModMenu entrypoint hook
├── mixin/
│   ├── GameRulesMixin.java             # Intercepts GameRules.set() for network sync
│   ├── ItemStackDurabilityMixin.java   # Intercepts hurtAndBreak() for damage reduction
│   └── ItemStackTooltipMixin.java      # Intercepts addDetailsToTooltip() for status lines
├── network/
│   ├── DurabilityClientState.java      # Client-side cache of 73 GameRules & dynamic mod rules
│   ├── DurabilityNetworking.java       # S2C packet registration & player sync logic
│   └── DurabilityPayload.java          # CustomPacketPayload record definition
└── registry/
    └── DurabilityRules.java            # GameRuleCategory & 73 GameRules definitions
```

---

## 💉 Detalhamento completo dos alvos de Mixin

### 1. `ItemStackDurabilityMixin`
* **Classe Alvo**: `net.minecraft.world.item.ItemStack`
* **Método Alvo**: `hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V`
* **Ponto de Injeção**: `@At("HEAD")`, `cancellable = true`
* **Método de Tratamento**: `dm$hurtAndBreak(int amount, ServerLevel level, @Nullable ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci)`
* **Justificativa de Design**: Ponto único de afunilamento para todo dano de itens no Minecraft. Protegido com `ThreadLocal<Boolean> dm$processing` para evitar loops infinitos ao reinvocar `hurtAndBreak` com dano reduzido.

### 2. `ItemStackTooltipMixin`
* **Classe Alvo**: `net.minecraft.world.item.ItemStack`
* **Método Alvo**: `addDetailsToTooltip(Item.TooltipContext, TooltipDisplay, Player, TooltipFlag, Consumer<Component>)V`
* **Ponto de Injeção**: `@At("TAIL")`
* **Método de Tratamento**: `dm$addDurabilityTooltip(...)`
* **Justificativa de Design**: Adiciona `✦ UNBREAKABLE` dourado e negrito, `⟨SINGLE-USE⟩` cinza ou `⟨Nx Durabilidade da Categoria⟩` / `⟨P% Durabilidade da Categoria⟩` cinza às dicas de itens.

### 3. `GameRulesMixin`
* **Classe Alvo**: `net.minecraft.world.level.gamerules.GameRules`
* **Método Alvo**: `set(GameRule<T>, T, MinecraftServer)V`
* **Ponto de Injeção**: `@At("TAIL")`
* **Método de Tratamento**: `onSet(GameRule<T> key, T value, @Nullable MinecraftServer server, CallbackInfo ci)`
* **Justificativa de Design**: Quando uma regra na categoria `DURABILITY_MULTIPLIER` muda, aciona `DurabilityNetworking.syncToAll(server)` para enviar valores atualizados a todos os clientes em tempo real.

---

## 🔄 Verificação dinâmica do registro de itens de mods

O registro dinâmico de itens é alimentado pelo `DynamicRegistryScanner` da **`DasikLibrary`**:
* **Método de Gancho**: `DynamicRegistryScanner.subscribe(BuiltInRegistries.ITEM, DurabilityRules::isItemDamageable, (id, item) -> { ... })`
* **Ciclo de Vida**: Scanner de descoberta universal de 3 níveis (varredura inicial, callbacks em tempo real durante o carregamento de mods e varredura de segurança ao iniciar o servidor).
* **Zero Mixins de Registro Personalizados**: Substitui mixins manuais por callbacks de eventos padrão, seguros contra falhas e seguros para o classloader.
