# 架构与 Mixin 描述符 (26.1.2)

| 架构属性 | 取值 |
| :--- | :--- |
| **根包名** | `net.instantgratification.durabilitymultiplier` |
| **兼容级别** | `JAVA_25` |
| **Mixin 配置文件** | `durability-multiplier.mixins.json` |
| **默认注入器要求** | `1` |
| **重入防护** | `ThreadLocal<Boolean> dm$processing` |

---

## 🌳 ASCII 包层级结构

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

## 💉 完整 Mixin 目标与注入点解析

### 1. `ItemStackDurabilityMixin`
* **目标类**: `net.minecraft.world.item.ItemStack`
* **目标方法**: `hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V`
* **注入点**: `@At("HEAD")`, `cancellable = true`
* **处理方法**: `dm$hurtAndBreak(int amount, ServerLevel level, @Nullable ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci)`
* **设计原理**: 原版所有物品损耗的唯一样板入口。采用 `ThreadLocal<Boolean> dm$processing` 重入保护，防止递归调用重度缩放后的 `hurtAndBreak` 时发生无限死循环。

### 2. `ItemStackTooltipMixin`
* **目标类**: `net.minecraft.world.item.ItemStack`
* **目标方法**: `addDetailsToTooltip(Item.TooltipContext, TooltipDisplay, Player, TooltipFlag, Consumer<Component>)V`
* **注入点**: `@At("TAIL")`
* **处理方法**: `dm$addDurabilityTooltip(...)`
* **设计原理**: 在物品悬浮提示框中追加金色粗体 `✦ UNBREAKABLE`、灰色 `⟨SINGLE-USE⟩` 或灰色 `⟨Nx 分类耐久度⟩` / `⟨P% 分类耐久度⟩`。

### 3. `GameRulesMixin`
* **目标类**: `net.minecraft.world.level.gamerules.GameRules`
* **目标方法**: `set(GameRule<T>, T, MinecraftServer)V`
* **注入点**: `@At("TAIL")`
* **处理方法**: `onSet(GameRule<T> key, T value, @Nullable MinecraftServer server, CallbackInfo ci)`
* **设计原理**: 当 `DURABILITY_MULTIPLIER` 分类中的规则变动时，触发 `DurabilityNetworking.syncToAll(server)` 实时向所有客户端广播更新。

---

## 🔄 动态模组物品注册表扫描

动态物品注册由 **`DasikLibrary`** 的 `DynamicRegistryScanner` 核心驱动：
* **挂钩方法**: `DynamicRegistryScanner.subscribe(BuiltInRegistries.ITEM, DurabilityRules::isItemDamageable, (id, item) -> { ... })`
* **生命周期**: 通用 3 级发现扫描器（启动扫描、外部模组加载期间的动态回调，以及服务端启动兜底扫描）。
* **零自定义注册表 Mixin**: 采用标准零崩溃且类加载器安全的事件回调，彻底取代手动注册表 Mixin。
