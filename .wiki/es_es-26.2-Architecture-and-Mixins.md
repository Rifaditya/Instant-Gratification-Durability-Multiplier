# Arquitectura y descriptores de Mixins (26.2)

| Propiedad arquitectónica | Valor |
| :--- | :--- |
| **Paquete raíz** | `net.instantgratification.durabilitymultiplier` |
| **Nivel de compatibilidad** | `JAVA_25` |
| **Configuración de Mixins** | `durability-multiplier.mixins.json` |
| **Requisito de inyector predeterminado** | `1` |
| **Protección contra reentrada** | `ThreadLocal<Boolean> dm$processing` |

---

## 🌳 Jerarquía de paquetes en ASCII

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

## 💉 Desglose completo de objetivos de Mixin

### 1. `ItemStackDurabilityMixin`
* **Clase objetivo**: `net.minecraft.world.item.ItemStack`
* **Método objetivo**: `hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V`
* **Punto de inyección**: `@At("HEAD")`, `cancellable = true`
* **Método de manejo**: `dm$hurtAndBreak(int amount, ServerLevel level, @Nullable ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci)`
* **Justificación de diseño**: Punto de embudo único para todo el daño de objetos en Minecraft. Protegido contra reentradas con `ThreadLocal<Boolean> dm$processing` para evitar bucles infinitos al invocar `hurtAndBreak` con daño reducido.

### 2. `ItemStackTooltipMixin`
* **Clase objetivo**: `net.minecraft.world.item.ItemStack`
* **Método objetivo**: `addDetailsToTooltip(Item.TooltipContext, TooltipDisplay, Player, TooltipFlag, Consumer<Component>)V`
* **Punto de inyección**: `@At("TAIL")`
* **Método de manejo**: `dm$addDurabilityTooltip(...)`
* **Justificación de diseño**: Añade `✦ UNBREAKABLE` dorado en negrita, `⟨SINGLE-USE⟩` gris o `⟨Nx Durabilidad de categoría⟩` / `⟨P% Durabilidad de categoría⟩` gris a las descripciones emergentes.

### 3. `GameRulesMixin`
* **Clase objetivo**: `net.minecraft.world.level.gamerules.GameRules`
* **Método objetivo**: `set(GameRule<T>, T, MinecraftServer)V`
* **Punto de inyección**: `@At("TAIL")`
* **Método de manejo**: `onSet(GameRule<T> key, T value, @Nullable MinecraftServer server, CallbackInfo ci)`
* **Justificación de diseño**: Cuando cambia una regla en la categoría `DURABILITY_MULTIPLIER`, activa `DurabilityNetworking.syncToAll(server)` para enviar valores actualizados a todos los clientes en tiempo real.

---

## 🔄 Escaneo dinámico del registro de objetos de mods

El registro dinámico de objetos funciona mediante el `DynamicRegistryScanner` de **`DasikLibrary`**:
* **Método de captura**: `DynamicRegistryScanner.subscribe(BuiltInRegistries.ITEM, DurabilityRules::isItemDamageable, (id, item) -> { ... })`
* **Ciclo de vida**: Escáner universal de descubrimiento de 3 niveles (barrido de inicio, callbacks en tiempo real durante la carga de mods y barrido de seguridad al iniciar el servidor).
* **Cero mixins de registro personalizados**: Reemplaza mixins manuales de registro con callbacks de eventos estándar, a prueba de fallos y seguros para el classloader.
