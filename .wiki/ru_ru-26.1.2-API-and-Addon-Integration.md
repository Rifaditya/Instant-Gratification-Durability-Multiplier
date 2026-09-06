# Интеграция API и аддонов (26.1.2)

| Фасад интеграции | Пакет / Расположение |
| :--- | :--- |
| **Основной помощник** | `net.instantgratification.durabilitymultiplier.DurabilityHelper` |
| **Реестр GameRules** | `net.instantgratification.durabilitymultiplier.registry.DurabilityRules` |
| **Состояние клиента** | `net.instantgratification.durabilitymultiplier.network.DurabilityClientState` |
| **Динамические правила** | `net.dasik.social.api.gamerule.DynamicGameRuleManager` |

---

## 🔌 Использование DurabilityHelper в других модах

Другие моды для Fabric могут проверять активный модификатор прочности для любого стака предметов:

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

## 📦 Регистрация пользовательских игровых правил для аддонов

Моды-аддоны могут регистрировать пользовательские правила в категории `DURABILITY_MULTIPLIER` с помощью `DynamicGameRuleManager`:

```java
GameRule<Integer> customRule = DynamicGameRuleManager.integerRule(
    "ig:custom_wand_percent", 
    DurabilityRules.DURABILITY_MULTIPLIER, 
    200
).name("Custom Wand Percent").min(-1).register();
```
