# API 與附屬模組整合 (26.2)

| 集成外观层 (Facade) | 封包路径 / 位置 |
| :--- | :--- |
| **核心工具類** | `net.instantgratification.durabilitymultiplier.DurabilityHelper` |
| **游戏規則注册表** | `net.instantgratification.durabilitymultiplier.registry.DurabilityRules` |
| **客戶端状態缓存** | `net.instantgratification.durabilitymultiplier.network.DurabilityClientState` |
| **動態規則管理器** | `net.dasik.social.api.gamerule.DynamicGameRuleManager` |

---

## 🔌 在其他模組中使用 DurabilityHelper

其他 Fabric 模組可以查詢任何物品堆疊当前生效的耐久度修饰符：

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

## 📦 為附屬模組註冊自訂遊戲規則

附屬模組可以使用 `DynamicGameRuleManager` 向 `DURABILITY_MULTIPLIER` 分類注册自定义游戏規則：

```java
GameRule<Integer> customRule = DynamicGameRuleManager.integerRule(
    "ig:custom_wand_percent", 
    DurabilityRules.DURABILITY_MULTIPLIER, 
    200
).name("Custom Wand Percent").min(-1).register();
```
