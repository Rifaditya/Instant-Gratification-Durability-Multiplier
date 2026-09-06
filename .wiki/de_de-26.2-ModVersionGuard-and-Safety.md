# ModVersionGuard & Laufzeitsicherheit (26.2)

| Parameter | Wert |
| :--- | :--- |
| **Schutzklasse** | `net.instantgratification.durabilitymultiplier.util.ModVersionGuard` |
| **Aufruf** | `DurabilityMultiplierFabric.onInitialize()` |
| **Geprüfte Klasse** | `net.minecraft.world.entity.EntityTypes` (MC 26.2+ Indikator) |
| **ClassLoader** | `Thread.currentThread().getContextClassLoader()` (Knot ClassLoader) |
| **Zweck** | Schutz vor Weltbeschädigung in inkompatiblen Laufzeitumgebungen |

---

## 🛡️ Wie ModVersionGuard Spielstände schützt

Minecraft-Versionen in der Modern Sovereign-Ära entwickeln sich rasant. Wenn eine für MC 26.2 kompilierte Mod in einer inkompatiblen Umgebung gestartet wird, können Classloading-Fehler Welten beschädigen.

`ModVersionGuard` führt eine abhängigkeitsfreie Vorabprüfung in `onInitialize()` durch, bevor GameRules, Mixins oder Konfigurationen geladen werden:

```java
ModVersionGuard.checkClass("Durability Multiplier", "net.minecraft.world.entity.EntityTypes");
```

Fehlt die erforderliche Klasse im Knot ClassLoader, stoppt das Spiel sofort mit einem aussagekräftigen Absturz-Banner:

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
