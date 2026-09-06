# Compatibilidad de versiones y matriz de ciclo de vida

| Especificación | Anclaje Minecraft 26.2 | Anclaje Minecraft 26.1.2 |
| :--- | :--- | :--- |
| **Versión objetivo de Minecraft** | `26.2` (`>=26.2-`) | `26.1.2` (`*`) |
| **Versión del mod (SemVer)** | `1.2.14+26.2` | `1.1.21+26.1.2` |
| **Herramientas de Java** | Java 25 (`release = 25`) | Java 25 (`release = 25`) |
| **Fabric Loader** | `>=0.16.9` (Compilado en `0.19.1`) | `>=0.16.9` (Compilado en `0.19.1`) |
| **Fabric API** | `0.150.1+26.2` | `0.145.4+26.1.2` |
| **Fabric Loom** | `1.15.2` | `1.15.2` |
| **Dependencia DasikLibrary** | `1.8.28` | `1.8.28` |
| **Dependencia Cloth Config** | `26.1.154` (Opcional) | `26.1.154` (Opcional) |
| **Dependencia ModMenu** | `18.0.0-beta.1` (Opcional) | `18.0.0-beta.1` (Opcional) |
| **Protección ModVersionGuard** | ✅ Activa (Comprobación de clase `EntityTypes`) | Entorno estándar |
| **Centro de Wiki dedicado** | [[👉 Abrir centro MC 26.2\|es_es-26.2-Home]] | [[👉 Abrir centro MC 26.1.2\|es_es-26.1.2-Home]] |

---

## 🏛️ Arquitectura de eras y ley "1 Jar 1 Version"

Durability Multiplier sigue el mandato de diseño **1 Jar 1 Version**:
1. Cada versión principal de Minecraft tiene su propio directorio de subproyecto dedicado (`Durability Multiplier v26.1/`, `Durability Multiplier v26.2/`).
2. Los artefactos se compilan de forma independiente en JAR etiquetados (`durability-multiplier-1.1.21+26.1.2.jar`, `durability-multiplier-1.2.14+26.2.jar`) y se archivan centralizadamente.
3. MC 26.2 incorpora la verificación en tiempo de ejecución sin dependencias `ModVersionGuard` en `onInitialize()` para detener la ejecución de forma segura si se carga en un entorno incompatible, protegiendo los mundos contra la corrupción.

---

> 📌 **Aviso de código fuente**: La documentación en esta Wiki refleja el **estado actual del código en el repositorio**, que puede incluir confirmaciones recientes no publicadas o funciones en desarrollo previas a los lanzamientos públicos en CurseForge y Modrinth.
