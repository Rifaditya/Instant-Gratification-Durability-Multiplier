# Architecture et descripteurs de Mixins (26.1.2)

| Propriété architecturale | Valeur |
| :--- | :--- |
| **Paquet racine** | `net.instantgratification.durabilitymultiplier` |
| **Niveau de compatibilité** | `JAVA_25` |
| **Configuration Mixins** | `durability-multiplier.mixins.json` |
| **Exigence d'injecteur par défaut** | `1` |
| **Protection contre la réentrance** | `ThreadLocal<Boolean> dm$processing` |

---

## 🌳 Hiérarchie des packages en ASCII

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

## 💉 Analyse complète des cibles Mixin

### 1. `ItemStackDurabilityMixin`
* **Classe cible** : `net.minecraft.world.item.ItemStack`
* **Méthode cible** : `hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V`
* **Point d'injection** : `@At("HEAD")`, `cancellable = true`
* **Méthode de gestion** : `dm$hurtAndBreak(int amount, ServerLevel level, @Nullable ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci)`
* **Justification de conception** : Point d'entonnoir unique pour tous les dégâts d'objets dans Minecraft. Protégé par `ThreadLocal<Boolean> dm$processing` pour éviter les boucles infinies lors de la réinvocation de `hurtAndBreak` avec des dégâts réduits.

### 2. `ItemStackTooltipMixin`
* **Classe cible** : `net.minecraft.world.item.ItemStack`
* **Méthode cible** : `addDetailsToTooltip(Item.TooltipContext, TooltipDisplay, Player, TooltipFlag, Consumer<Component>)V`
* **Point d'injection** : `@At("TAIL")`
* **Méthode de gestion** : `dm$addDurabilityTooltip(...)`
* **Justification de conception** : Ajoute `✦ UNBREAKABLE` en or et gras, `⟨SINGLE-USE⟩` en gris ou `⟨Nx Durabilité de catégorie⟩` / `⟨P% Durabilité de catégorie⟩` en gris aux infobulles.

### 3. `GameRulesMixin`
* **Classe cible** : `net.minecraft.world.level.gamerules.GameRules`
* **Méthode cible** : `set(GameRule<T>, T, MinecraftServer)V`
* **Point d'injection** : `@At("TAIL")`
* **Méthode de gestion** : `onSet(GameRule<T> key, T value, @Nullable MinecraftServer server, CallbackInfo ci)`
* **Justification de conception** : Lorsqu'une règle de la catégorie `DURABILITY_MULTIPLIER` change, déclenche `DurabilityNetworking.syncToAll(server)` pour synchroniser les valeurs en temps réel.

---

## 🔄 Analyse dynamique du registre d'objets de mods

L'enregistrement dynamique des objets est alimenté par le `DynamicRegistryScanner` de **`DasikLibrary`** :
* **Méthode de crochet** : `DynamicRegistryScanner.subscribe(BuiltInRegistries.ITEM, DurabilityRules::isItemDamageable, (id, item) -> { ... })`
* **Cycle de vie** : Scanner de découverte universel à 3 niveaux (analyse de démarrage, rappels en direct pendant le chargement de mods et vérification de sécurité au démarrage du serveur).
* **Zéro mixin de registre personnalisé** : Remplace les mixins manuels par des rappels d'événements standard et sécurisés pour le classloader.
