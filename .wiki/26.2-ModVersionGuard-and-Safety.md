# ModVersionGuard & Safety (26.2)

| Parameter | Value |
| :--- | :--- |
| **Guard Class** | `net.instantgratification.durabilitymultiplier.util.ModVersionGuard` |
| **Invocation** | `DurabilityMultiplierFabric.onInitialize()` |
| **Checked Class** | `net.minecraft.world.entity.EntityTypes` (MC 26.2+ indicator) |
| **ClassLoader** | `Thread.currentThread().getContextClassLoader()` (Knot ClassLoader) |
| **Purpose** | Prevent world corruption if loaded into incompatible runtime |

---

## 🛡️ How ModVersionGuard Protects Saves

Minecraft drops in the Modern Sovereign era undergo rapid API evolutions. If a mod compiled for MC 26.2 is mistakenly run on an older or incompatible runtime, silent classloading errors can result in broken item data or corrupted world saves.

`ModVersionGuard` executes a zero-dependency pre-check during `onInitialize()` before any GameRules, mixins, or configs are initialized:

```java
ModVersionGuard.checkClass("Durability Multiplier", "net.minecraft.world.entity.EntityTypes");
```

If the required class is missing from the Knot ClassLoader, the game immediately halts with an informative crash banner:

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
