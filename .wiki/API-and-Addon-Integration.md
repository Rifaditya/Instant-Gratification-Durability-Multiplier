# API & Addon Integration (26.1.2)

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
// Server-Side: Query effective multiplier for an ItemStack
int multiplier = DurabilityHelper.getEffectiveMultiplier(serverLevel, itemStack);

// Server-Side: Check if an item is currently unbreakable (God Mode)
boolean isGodMode = DurabilityHelper.isInfinite(serverLevel, itemStack);

// Client-Side: Query synced client state
int clientMultiplier = DurabilityHelper.getEffectiveMultiplierClient(itemStack);
boolean clientGodMode = DurabilityHelper.isInfiniteClient(itemStack);
```

---

## 📦 Registering Custom Addon GameRules

Addon mods can register custom rules into the `DURABILITY_MULTIPLIER` category using `DynamicGameRuleManager`:

```java
GameRule<Integer> customRule = DynamicGameRuleManager.integerRule(
    "ig:custom_wand_multiplier", 
    DurabilityRules.DURABILITY_MULTIPLIER, 
    2
).name("Custom Wand Multiplier").min(1).register();
```
