# Wiki oficial de Durability Multiplier

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

Bienvenido a la documentación técnica y de juego oficial de **Durability Multiplier** (Colección Instant Gratification), desarrollada por **Dasik (Rifaditya)**.

> 📌 **Aviso de código fuente**: La documentación en esta Wiki refleja el **estado actual del código en el repositorio**, que puede incluir confirmaciones recientes no publicadas o funciones en desarrollo previas a los lanzamientos públicos en CurseForge y Modrinth.

---

## 🧭 Portal de versiones múltiples

Durability Multiplier está diseñado para versiones ancla de Minecraft. Selecciona tu versión de Minecraft a continuación para entrar a su centro de documentación dedicado:

| Versión de Minecraft | Era de lanzamiento | Compilación admitida | Nivel de Java | Herramientas Loom | Entrada en Wiki |
| :--- | :--- | :--- | :---: | :---: | :--- |
| **Minecraft 26.2** | Era Modern Sovereign | `1.2.14+26.2` | Java 25 | Loom 1.15.2 | [[👉 Entrar a la Wiki de MC 26.2\|es_es-26.2-Home]] |
| **Minecraft 26.1.2** | Era Modern Sovereign | `1.1.21+26.1.2` | Java 25 | Loom 1.15.2 | [[👉 Entrar a la Wiki de MC 26.1.2\|es_es-26.1.2-Home]] |

---

## ⚡ Filosofía central y arquitectura

Durability Multiplier pertenece a la línea de diseño **Instant Gratification (IG)**. Su único propósito es eliminar la **fricción de mantenimiento** en la supervivencia de Minecraft:

* **Respeto por el tiempo del jugador**: Elimina los tediosos bucles de reparación, las pausas al minar y las roturas accidentales de equipo.
* **Reducción de daño puramente matemática**: La extensión de durabilidad se calcula mediante división entera y redondeo probabilístico del daño entrante, asegurando precisión matemática en millones de golpes sin sobrescribir atributos vanilla.
* **Control granular**: Configura 24 categorías individuales de objetos (espadas, lanzas, tridentes, mazas, arcos, ballestas, escudos, herramientas, picos, hachas, palas, azadas, tijeras, cañas de pescar, pinceles, mecheros, armadura, cascos, petos, pantalones, botas, élitros, armas, global) de forma independiente en 73 GameRules estáticas.
* **Modo Dios (Infinito)**: Haz que cualquier categoría sea 100% irrompible con una sola GameRule booleana.
* **Detección automática de objetos de mods**: Descubre objetos con durabilidad de mods al congelar el registro y expone GameRules dinámicas y controles en la interfaz.
* **Cero desincronización entre lados**: Las GameRules del servidor se sincronizan con los clientes mediante red personalizada de Fabric (`durability-multiplier:sync_rules`) para descripciones emergentes en vivo.

---

## 📚 Navegación global y recursos

* [[Matriz de compatibilidad de versiones|es_es-Version-Compatibility]]
* [[Centro de documentación MC 26.2|es_es-26.2-Home]]
* [[Centro de documentación MC 26.1.2|es_es-26.1.2-Home]]
* [Página de la plataforma CurseForge](https://curseforge.com/minecraft/mc-mods/durability-multiplier)
* [Página de la plataforma Modrinth](https://modrinth.com/mod/durability-multiplier)
* [Repositorio de código fuente en GitHub](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier)
