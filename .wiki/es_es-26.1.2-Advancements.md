# Logros y avances (26.1.2)

| Parámetro del sistema | Estado |
| :--- | :--- |
| **JSONs de logros personalizados** | **Ninguno** (Omitido intencionalmente por diseño) |
| **Alcance** | Modificador puro de jugabilidad / Utilidad de Gratificación Instantánea |
| **Logros de Vanilla** | 100% compatible con todos los logros vanilla de Historia y Agricultura |

---

## 📌 Declaración de política de ausencia

En estricto cumplimiento con la filosofía **Instant Gratification (IG)** del proyecto y su arquitectura de impacto mínimo:
* Durability Multiplier contiene **cero árboles JSON de logros personalizados**.
* El mod **no** registra disparadores personalizados, criterios ni notificaciones emergentes.

---

## 🌾 Compatibilidad con avances vanilla

Todos los logros vanilla continúan activándose naturalmente:
* Minar mineral de diamante activa **"¡Diamantes!"** (`minecraft:story/mine_diamond`).
* Volar con élitros activa **"El cielo es el límite"** (`minecraft:end/elytra`).
* Bloquear daño con un escudo activa **"Hoy no, gracias"** (`minecraft:story/deflect_arrow`).

Debido a que la reducción de durabilidad del objeto ocurre de manera transparente dentro de `ItemStack.hurtAndBreak`, los logros que rastrean el uso de herramientas, bajas con armas o daño de armadura se evalúan sin interferencias.
