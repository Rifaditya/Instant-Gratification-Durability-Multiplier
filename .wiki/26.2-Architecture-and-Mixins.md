# Architecture & Mixin Descriptors (26.2)

| Architectural Property | Value |
| :--- | :--- |
| **Root Package** | `net.instantgratification.durabilitymultiplier` |
| **Compatibility Level** | `JAVA_25` |
| **Mixins Config** | `durability-multiplier.mixins.json` |
| **Default Injector Require** | `1` |
| **Re-entry Guard** | `ThreadLocal<Boolean> dm$processing` |

---

## 🌳 ASCII Package Hierarchy

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

## 💉 Complete Mixin Target Breakdown

### 1. `ItemStackDurabilityMixin`
* **Target Class**: `net.minecraft.world.item.ItemStack`
* **Target Method**: `hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V`
* **Injection Point**: `@At("HEAD")`, `cancellable = true`
* **Handler Method**: `dm$hurtAndBreak(int amount, ServerLevel level, @Nullable ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci)`
* **Design Rationale**: Single funnel point for all item damage in Minecraft. Re-entry guarded with `ThreadLocal<Boolean> dm$processing` to prevent infinite loops when re-invoking `hurtAndBreak` with reduced damage.

### 2. `ItemStackTooltipMixin`
* **Target Class**: `net.minecraft.world.item.ItemStack`
* **Target Method**: `addDetailsToTooltip(Item.TooltipContext, TooltipDisplay, Player, TooltipFlag, Consumer<Component>)V`
* **Injection Point**: `@At("TAIL")`
* **Handler Method**: `dm$addDurabilityTooltip(...)`
* **Design Rationale**: Appends gold bold `✦ UNBREAKABLE`, gray `⟨SINGLE-USE⟩`, or gray `⟨Nx Category Durability⟩` / `⟨P% Category Durability⟩` to item hover tooltips.

### 3. `GameRulesMixin`
* **Target Class**: `net.minecraft.world.level.gamerules.GameRules`
* **Target Method**: `set(GameRule<T>, T, MinecraftServer)V`
* **Injection Point**: `@At("TAIL")`
* **Handler Method**: `onSet(GameRule<T> key, T value, @Nullable MinecraftServer server, CallbackInfo ci)`
* **Design Rationale**: When a rule in `DURABILITY_MULTIPLIER` category changes, triggers `DurabilityNetworking.syncToAll(server)` to push updated values to all clients in real time.

---

## 🔄 Dynamic Modded Item Registry Scanning

Dynamic item registration is powered by **`DasikLibrary`**'s `DynamicRegistryScanner`:
* **Hook Method**: `DynamicRegistryScanner.subscribe(BuiltInRegistries.ITEM, DurabilityRules::isItemDamageable, (id, item) -> { ... })`
* **Lifecycle**: Universal 3-tier discovery scanner (Startup sweep, live entry callbacks during external mod loading, and server-start safety sweep).
* **Zero Custom Registry Mixins**: Replaces manual registry mixins with standard zero-crash classloader-safe event callbacks.
