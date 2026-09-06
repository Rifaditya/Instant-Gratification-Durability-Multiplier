# APIとアドオン連携 (26.2)

| 統合インターフェース | パッケージ / クラスパス |
| :--- | :--- |
| **コアヘルパー** | `net.instantgratification.durabilitymultiplier.DurabilityHelper` |
| **ゲームルール登録** | `net.instantgratification.durabilitymultiplier.registry.DurabilityRules` |
| **クライアント状態** | `net.instantgratification.durabilitymultiplier.network.DurabilityClientState` |
| **動的ルール** | `net.dasik.social.api.gamerule.DynamicGameRuleManager` |

---

## 🔌 他のModでDurabilityHelperを使用する

他のFabric Modは、任意のアイテムスタックに適用されている耐久度倍率を検査できます：

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

## 📦 カスタムアドオン用ゲームルールの登録

アドオンModは、`DynamicGameRuleManager`を使用して`DURABILITY_MULTIPLIER`カテゴリにカスタムルールを登録できます：

```java
GameRule<Integer> customRule = DynamicGameRuleManager.integerRule(
    "ig:custom_wand_percent", 
    DurabilityRules.DURABILITY_MULTIPLIER, 
    200
).name("Custom Wand Percent").min(-1).register();
```
