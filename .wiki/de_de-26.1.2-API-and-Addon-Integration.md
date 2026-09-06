# API- & Addon-Integration (26.1.2)

| Integrationsfassade | Paket / Pfad |
| :--- | :--- |
| **Kern-Helfer** | `net.instantgratification.durabilitymultiplier.DurabilityHelper` |
| **GameRule-Registrierung** | `net.instantgratification.durabilitymultiplier.registry.DurabilityRules` |
| **Client-Status** | `net.instantgratification.durabilitymultiplier.network.DurabilityClientState` |
| **Dynamische Regeln** | `net.dasik.social.api.gamerule.DynamicGameRuleManager` |

---

## 🔌 Verwendung von DurabilityHelper in anderen Mods

Andere Fabric-Mods können den aktiven Haltbarkeitsmodifikator für jeden Gegenstandsstapel abfragen:

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

## 📦 Registrierung benutzerdefinierter Addon-GameRules

Addon-Mods können benutzerdefinierte Regeln in der Kategorie `DURABILITY_MULTIPLIER` über `DynamicGameRuleManager` registrieren:

```java
GameRule<Integer> customRule = DynamicGameRuleManager.integerRule(
    "ig:custom_wand_percent", 
    DurabilityRules.DURABILITY_MULTIPLIER, 
    200
).name("Custom Wand Percent").min(-1).register();
```
