# Integración de API y addons (26.2)

| Fachada de integración | Paquete / Ubicación |
| :--- | :--- |
| **Ayudante principal** | `net.instantgratification.durabilitymultiplier.DurabilityHelper` |
| **Registro de GameRules** | `net.instantgratification.durabilitymultiplier.registry.DurabilityRules` |
| **Estado del cliente** | `net.instantgratification.durabilitymultiplier.network.DurabilityClientState` |
| **Reglas dinámicas** | `net.dasik.social.api.gamerule.DynamicGameRuleManager` |

---

## 🔌 Uso de DurabilityHelper en otros mods

Otros mods de Fabric pueden inspeccionar el modificador de durabilidad activo para cualquier stack de objetos:

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

## 📦 Registro de GameRules personalizadas para addons

Los mods addons pueden registrar reglas personalizadas en la categoría `DURABILITY_MULTIPLIER` utilizando `DynamicGameRuleManager`:

```java
GameRule<Integer> customRule = DynamicGameRuleManager.integerRule(
    "ig:custom_wand_percent", 
    DurabilityRules.DURABILITY_MULTIPLIER, 
    200
).name("Custom Wand Percent").min(-1).register();
```
