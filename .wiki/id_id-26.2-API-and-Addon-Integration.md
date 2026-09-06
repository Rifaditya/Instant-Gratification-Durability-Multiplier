# Integrasi API & Pengaya (26.2)

| Fasad Integrasi | Paket / Lokasi |
| :--- | :--- |
| **Helper Inti** | `net.instantgratification.durabilitymultiplier.DurabilityHelper` |
| **Registri GameRule** | `net.instantgratification.durabilitymultiplier.registry.DurabilityRules` |
| **Status Klien** | `net.instantgratification.durabilitymultiplier.network.DurabilityClientState` |
| **Aturan Dinamis** | `net.dasik.social.api.gamerule.DynamicGameRuleManager` |

---

## 🔌 Menggunakan DurabilityHelper di Mod Lain

Mod Fabric lain dapat memeriksa pengali ketahanan aktif untuk tumpukan item apa pun:

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

## 📦 Mendaftarkan GameRules Kustom untuk Pengaya

Mod addon dapat mendaftarkan aturan kustom ke dalam kategori `DURABILITY_MULTIPLIER` menggunakan `DynamicGameRuleManager`:

```java
GameRule<Integer> customRule = DynamicGameRuleManager.integerRule(
    "ig:custom_wand_percent", 
    DurabilityRules.DURABILITY_MULTIPLIER, 
    200
).name("Custom Wand Percent").min(-1).register();
```
