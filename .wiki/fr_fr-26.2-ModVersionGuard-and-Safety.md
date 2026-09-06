# ModVersionGuard & Sécurité à l'exécution (26.2)

| Paramètre | Valeur |
| :--- | :--- |
| **Classe de protection** | `net.instantgratification.durabilitymultiplier.util.ModVersionGuard` |
| **Invocation** | `DurabilityMultiplierFabric.onInitialize()` |
| **Classe vérifiée** | `net.minecraft.world.entity.EntityTypes` (Indicateur MC 26.2+) |
| **ClassLoader** | `Thread.currentThread().getContextClassLoader()` (Knot ClassLoader) |
| **Objectif** | Éviter la corruption du monde en cas d'environnement incompatible |

---

## 🛡️ Comment ModVersionGuard protège les sauvegardes

Les versions de Minecraft de l'ère Modern Sovereign connaissent des évolutions rapides d'API. Si un mod compilé pour MC 26.2 tourne sur un environnement incompatible, des erreurs de classloader peuvent corrompre les mondes.

`ModVersionGuard` exécute une pré-vérification sans dépendance lors de `onInitialize()` avant que les GameRules, mixins ou configurations ne soient initialisés :

```java
ModVersionGuard.checkClass("Durability Multiplier", "net.minecraft.world.entity.EntityTypes");
```

Si la classe requise est absente du Knot ClassLoader, le jeu s'arrête immédiatement avec un message d'erreur explicatif :

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
