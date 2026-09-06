# API & Addon Integration (26.2)

| Integration Facade | Package / Location |
| :--- | :--- |
| **Core Helper** | `net.instantgratification.durabilitymultiplier.DurabilityHelper` |
| **GameRule Registry** | `net.instantgratification.durabilitymultiplier.registry.DurabilityRules` |
| **Client State** | `net.instantgratification.durabilitymultiplier.network.DurabilityClientState` |
| **Dynamic Rules** | `net.dasik.social.api.gamerule.DynamicGameRuleManager` |

---

## 🔌 Using DurabilityHelper in Other Mods

Other Fabric mods can inspect the active durability modifier for any item stack:

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

## 📦 Registering Custom Addon GameRules

Addon mods can register custom rules into the `DURABILITY_MULTIPLIER` category using `DynamicGameRuleManager`:

```java
GameRule<Integer> customRule = DynamicGameRuleManager.integerRule(
    "ig:custom_wand_percent", 
    DurabilityRules.DURABILITY_MULTIPLIER, 
    200
).name("Custom Wand Percent").min(-1).register();
```
