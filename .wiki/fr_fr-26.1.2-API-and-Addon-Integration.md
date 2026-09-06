# Intégration d'API et d'addons (26.1.2)

| Façade d'intégration | Paquet / Emplacement |
| :--- | :--- |
| **Assistant principal** | `net.instantgratification.durabilitymultiplier.DurabilityHelper` |
| **Registre des GameRules** | `net.instantgratification.durabilitymultiplier.registry.DurabilityRules` |
| **État client** | `net.instantgratification.durabilitymultiplier.network.DurabilityClientState` |
| **Règles dynamiques** | `net.dasik.social.api.gamerule.DynamicGameRuleManager` |

---

## 🔌 Utilisation de DurabilityHelper dans d'autres mods

D'autres mods Fabric peuvent inspecter le modificateur de durabilité actif pour n'importe quelle pile d'objets :

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

## 📦 Enregistrement de GameRules personnalisées pour les addons

Les mods addons peuvent enregistrer des règles personnalisées dans la catégorie `DURABILITY_MULTIPLIER` via `DynamicGameRuleManager` :

```java
GameRule<Integer> customRule = DynamicGameRuleManager.integerRule(
    "ig:custom_wand_percent", 
    DurabilityRules.DURABILITY_MULTIPLIER, 
    200
).name("Custom Wand Percent").min(-1).register();
```
