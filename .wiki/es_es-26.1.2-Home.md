# Durability Multiplier — Centro de documentación de Minecraft 26.1.2

Bienvenido al centro de documentación dedicado para **Durability Multiplier** en **Minecraft 26.1.2** (`1.1.21+26.1.2`).

> 📌 **Aviso de código fuente**: La documentación en esta Wiki refleja el **estado actual del código en el repositorio**, que puede incluir confirmaciones recientes no publicadas o funciones en desarrollo previas a los lanzamientos públicos en CurseForge y Modrinth.

---

## 📋 Resumen técnico (26.1.2)

| Parámetro | Valor | Descripción |
| :--- | :--- | :--- |
| **Identificador de mod** | `durability-multiplier` | ID del mod en Fabric Loader |
| **Versión del mod** | `1.1.21+26.1.2` | Etiqueta de versión SemVer |
| **Minecraft objetivo** | `26.1.2` (`*`) | Anclaje nativo de versión |
| **Lanzamiento de Java** | Java 25 | Compilado con `release = 25` |
| **Fabric Loader** | `>=0.16.9` | Requisito mínimo de cargador |
| **Fabric API** | `0.145.4+26.1.2` | Requisito de Fabric API |
| **DasikLibrary** | `1.8.28` | Núcleo de arquitectura compartida |
| **GameRules registradas** | **73 reglas estáticas** + reglas dinámicas | 24 porcentajes, 24 infinitos, 24 un solo uso, 1 descripción |
| **Puntos de inyección Mixin** | 3 clases objetivo | `ItemStack`, `GameRules` |
| **Autor y licencia** | **Dasik (Rifaditya)** / GPL-3.0-or-later | Modificación de código abierto |

---

## 🧭 Matriz de navegación (26.1.2)

### 🎮 Guías de juego para jugadores
* [[Multiplicadores de durabilidad y categorías|es_es-26.1.2-Durability-Multipliers]] — Sistema granular de porcentajes de 24 categorías y jerarquía de anulación.
* [[Modo Dios e Infinito|es_es-26.1.2-God-Mode-and-Infinity]] — Alternancias de invencibilidad de daño cero en 24 categorías.
* [[Matemáticas de reducción de daño y probabilidades|es_es-26.1.2-Damage-Reduction-and-Probability-Math]] — Fórmulas matemáticas y redondeo probabilístico.
* [[Clasificación de objetos y compatibilidad con mods|es_es-26.1.2-Item-Classification-and-Mod-Compatibility]] — Cómo se clasifican los objetos de vanilla y mods.
* [[Registro dinámico de objetos de mods|es_es-26.1.2-Dynamic-Modded-Item-Registration]] — Escáner universal de descubrimiento de 3 niveles y autopoblado.
* [[Indicadores de descripciones emergentes y HUD|es_es-26.1.2-Tooltip-Indicators-and-HUD]] — Renderizado de descripciones emergentes del lado del cliente.
* [[Tabla de referencia de GameRules|es_es-26.1.2-GameRules]] — Tabla exhaustiva de referencia para las 73 GameRules estáticas.
* [[Comandos y administración en el juego|es_es-26.1.2-Commands-and-Administration]] — Gestión de configuraciones en el juego mediante `/gamerule`.
* [[Logros y progresos|es_es-26.1.2-Advancements]] — Política de ausencia e integración con vanilla.
* [[Configuración e interfaz GUI|es_es-26.1.2-Configuration]] — Integración con ModMenu y Cloth Config.

* [[Resolución de problemas y preguntas frecuentes|es_es-26.1.2-Troubleshooting-and-FAQ]] — Procedimientos de diagnóstico y preguntas comunes.

### 💻 Referencia técnica para desarrolladores
* [[Arquitectura y descriptores de Mixin|es_es-26.1.2-Architecture-and-Mixins]] — Jerarquía de paquetes, ganchos de inyección y seguridad de reentrada.
* [[Sincronización de red y protocolo de carga|es_es-26.1.2-Network-Sync-and-Payload-Protocol]] — Protocolo de sincronización S2C (`DurabilityPayload`).
* [[Configuración de desarrollador y compilación|es_es-26.1.2-Developer-Setup-and-Building]] — Comandos Gradle, herramientas Loom y configuración de JDK.
* [[Integración de API y complementos|es_es-26.1.2-API-and-Addon-Integration]] — Ampliación del mod, `DurabilityHelper` y reglas personalizadas.
