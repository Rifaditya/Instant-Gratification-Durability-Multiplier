# ModVersionGuard & Keamanan Runtime (26.2)

| Parameter | Nilai |
| :--- | :--- |
| **Kelas Penjaga** | `net.instantgratification.durabilitymultiplier.util.ModVersionGuard` |
| **Pemanggilan** | `DurabilityMultiplierFabric.onInitialize()` |
| **Kelas yang Diperiksa** | `net.minecraft.world.entity.EntityTypes` (Indikator MC 26.2+) |
| **ClassLoader** | `Thread.currentThread().getContextClassLoader()` (Knot ClassLoader) |
| **Tujuan** | Mencegah kerusakan dunia jika dimuat di runtime yang tidak kompatibel |

---

## 🛡️ Cara ModVersionGuard Melindungi Simpanan

Rilis Minecraft di era Modern Sovereign mengalami evolusi API yang sangat cepat. Jika mod yang dikompilasi untuk MC 26.2 keliru dijalankan di lingkungan yang tidak kompatibel, kesalahan classloading dapat merusak data item atau simpanan dunia.

`ModVersionGuard` menjalankan pra-pemeriksaan tanpa ketergantungan selama `onInitialize()` sebelum GameRules, mixin, atau konfigurasi apa pun diinisialisasi:

```java
ModVersionGuard.checkClass("Durability Multiplier", "net.minecraft.world.entity.EntityTypes");
```

Jika kelas yang diperlukan tidak ada di Knot ClassLoader, game akan segera dihentikan dengan spanduk kesalahan yang jelas:

```
=====================================================================
 [PRE-RELEASE / VERSION GUARD WARNING] Durability Multiplier
---------------------------------------------------------------------
 CRITICAL: Incompatible Minecraft Game Runtime or Missing Class!
 Required Class : net.minecraft.world.entity.EntityTypes
 Status         : UNRESOLVED AT RUNTIME

 Safety Protection:
 Execution halted to prevent unreleased/incompatible build deployment
 or broken world state save corruption.

 Troubleshooting Steps:
 1. Verify target Minecraft version (26.2+ release drop).
 2. Ensure all required dependencies (Fabric API, DasikLibrary) are loaded.
 3. Build/Download a verified matching release JAR from Modrinth/CurseForge.
=====================================================================
```
