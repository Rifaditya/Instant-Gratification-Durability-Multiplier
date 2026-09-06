# ModVersionGuard y seguridad en tiempo de ejecución (26.2)

| Parámetro | Valor |
| :--- | :--- |
| **Clase de protección** | `net.instantgratification.durabilitymultiplier.util.ModVersionGuard` |
| **Invocación** | `DurabilityMultiplierFabric.onInitialize()` |
| **Clase comprobada** | `net.minecraft.world.entity.EntityTypes` (Indicador MC 26.2+) |
| **Cargador de clases** | `Thread.currentThread().getContextClassLoader()` (Knot ClassLoader) |
| **Propósito** | Prevenir la corrupción del mundo si se carga en un entorno incompatible |

---

## 🛡️ Cómo protege los mundos ModVersionGuard

Las versiones de Minecraft en la era Modern Sovereign experimentan rápidas evoluciones de API. Si un mod compilado para MC 26.2 se ejecuta erróneamente en un entorno incompatible, los errores de carga de clases pueden corromper los mundos.

`ModVersionGuard` ejecuta una comprobación previa sin dependencias durante `onInitialize()` antes de inicializar GameRules, mixins o configuraciones:

```java
ModVersionGuard.checkClass("Durability Multiplier", "net.minecraft.world.entity.EntityTypes");
```

Si la clase requerida falta en el cargador Knot ClassLoader, el juego se detiene de inmediato con un mensaje informativo:

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
