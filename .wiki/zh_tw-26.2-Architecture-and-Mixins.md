# 架構與 Mixin 描述符 (26.2)

| 架構屬性 | 取值 |
| :--- | :--- |
| **根封包名** | `net.instantgratification.durabilitymultiplier` |
| **兼容級别** | `JAVA_25` |
| **Mixin 配置文件** | `durability-multiplier.mixins.json` |
| **默认注入器要求** | `1` |
| **重入防護** | `ThreadLocal<Boolean> dm$processing` |

---

## 🌳 ASCII 套件層級架構

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

## 💉 完整 Mixin 目標與注入點解析

### 1. `ItemStackDurabilityMixin`
* **目標類**: `net.minecraft.world.item.ItemStack`
* **目標方法**: `hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V`
* **注入點**: `@At("HEAD")`, `cancellable = true`
* **处理方法**: `dm$hurtAndBreak(int amount, ServerLevel level, @Nullable ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci)`
* **設計原理**: 原版所有物品損耗的唯一樣板入口。采用 `ThreadLocal<Boolean> dm$processing` 重入保護，防止递归調用重度缩放后的 `hurtAndBreak` 時發生無限死循環。

### 2. `ItemStackTooltipMixin`
* **目標類**: `net.minecraft.world.item.ItemStack`
* **目標方法**: `addDetailsToTooltip(Item.TooltipContext, TooltipDisplay, Player, TooltipFlag, Consumer<Component>)V`
* **注入點**: `@At("TAIL")`
* **处理方法**: `dm$addDurabilityTooltip(...)`
* **設計原理**: 在物品悬浮提示框中追加金色粗體 `✦ UNBREAKABLE`、灰色 `⟨SINGLE-USE⟩` 或灰色 `⟨Nx 分类耐久度⟩` / `⟨P% 分类耐久度⟩`。

### 3. `GameRulesMixin`
* **目標類**: `net.minecraft.world.level.gamerules.GameRules`
* **目標方法**: `set(GameRule<T>, T, MinecraftServer)V`
* **注入點**: `@At("TAIL")`
* **处理方法**: `onSet(GameRule<T> key, T value, @Nullable MinecraftServer server, CallbackInfo ci)`
* **設計原理**: 当 `DURABILITY_MULTIPLIER` 分類中的規則變動時，触發 `DurabilityNetworking.syncToAll(server)` 實時向所有客戶端广播更新。

---

## 🔄 動態模組物品註冊表掃描

動態物品注册由 **`DasikLibrary`** 的 `DynamicRegistryScanner` 核心驱動：
* **挂钩方法**: `DynamicRegistryScanner.subscribe(BuiltInRegistries.ITEM, DurabilityRules::isItemDamageable, (id, item) -> { ... })`
* **生命周期**: 通用 3 級發现扫描器（啟動扫描、外部模組加載期间的動態回調，以及服务端啟動兜底扫描）。
* **零自定义注册表 Mixin**: 采用標准零崩溃且類加載器安全的事件回調，彻底取代手動注册表 Mixin。
