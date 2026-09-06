# API 与附属模组集成 (26.1.2)

| 集成外观层 (Facade) | 包路径 / 位置 |
| :--- | :--- |
| **核心工具类** | `net.instantgratification.durabilitymultiplier.DurabilityHelper` |
| **游戏规则注册表** | `net.instantgratification.durabilitymultiplier.registry.DurabilityRules` |
| **客户端状态缓存** | `net.instantgratification.durabilitymultiplier.network.DurabilityClientState` |
| **动态规则管理器** | `net.dasik.social.api.gamerule.DynamicGameRuleManager` |

---

## 🔌 在其他模组中使用 DurabilityHelper

其他 Fabric 模组可以查询任何物品堆叠当前生效的耐久度修饰符：

```java
// Server-Side: Query effective durability percentage (e.g. 200 = 2x, 50 = 0.5x, -1 = single-use)
int percent = DurabilityHelper.getEffectivePercent(serverLevel, itemStack);

// Server-Side: Check if an item is currently unbreakable (God Mode)
boolean isGodMode = DurabilityHelper.isInfinite(serverLevel, itemStack);

// Server-Side: Check if an item is currently in Single-Use Glass Mode
boolean isGlassMode = DurabilityHelper.isSingleUse(serverLevel, itemStack);

// Server-Side: Reduce incoming durability damage amount using active rules
int finalDamage = DurabilityHelper.reduceDamage(originalDamage, serverLevel, itemStack);

// Client-Side: Query synced client state for tooltips / HUD
int clientPercent = DurabilityHelper.getEffectivePercentClient(itemStack);
boolean clientGodMode = DurabilityHelper.isInfiniteClient(itemStack);
boolean clientGlassMode = DurabilityHelper.isSingleUseClient(itemStack);
```

---

## 📦 为附属模组注册自定义游戏规则

附属模组可以使用 `DynamicGameRuleManager` 向 `DURABILITY_MULTIPLIER` 分类注册自定义游戏规则：

```java
GameRule<Integer> customRule = DynamicGameRuleManager.integerRule(
    "ig:custom_wand_percent", 
    DurabilityRules.DURABILITY_MULTIPLIER, 
    200
).name("Custom Wand Percent").min(-1).register();
```
