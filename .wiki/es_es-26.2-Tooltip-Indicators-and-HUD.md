# Indicadores en descripción emergente y HUD (26.2)

| Parámetro del sistema | Valor |
| :--- | :--- |
| **GameRule de alternancia** | `ig:dm_show_tooltip` |
| **Estado predeterminado** | `true` (Activado) |
| **Objetivo de Mixin** | `ItemStack.addDetailsToTooltip` (`ItemStackTooltipMixin`) |
| **Punto de inyección** | `@At("TAIL")` |
| **Estilo de Modo Dios** | `✦ UNBREAKABLE` (Dorado, Negrita — `ChatFormatting.GOLD`, `ChatFormatting.BOLD`) |
| **Estilo de multiplicador** | `⟨Nx Durabilidad de categoría⟩` (Gris — `ChatFormatting.GRAY`) |

---

## ⚡ Descripción general y presentación visual

Durability Multiplier proporciona información inmediata y clara directamente en las descripciones emergentes siempre que se modifique la vida útil de un objeto.

### Estilos visuales de descripciones emergentes

| Estado | Texto renderizado | Apariencia visual | Código de color |
| :--- | :--- | :--- | :--- |
| **Modo Dios activo** | `✦ UNBREAKABLE` | **✦ UNBREAKABLE** | Dorado, Negrita (`ChatFormatting.GOLD`, `BOLD`) |
| **Un solo uso (Modo Cristal)** | `⟨SINGLE-USE⟩` | ⟨SINGLE-USE⟩ | Gris (`ChatFormatting.GRAY`) |
| **Multiplicador 200% / 2x** | `⟨2x Durabilidad de espadas⟩` | ⟨2x Durabilidad de espadas⟩ | Gris (`ChatFormatting.GRAY`) |
| **Durabilidad 150%** | `⟨150% Durabilidad de petos⟩` | ⟨150% Durabilidad de petos⟩ | Gris (`ChatFormatting.GRAY`) |
| **50% (Mitad de durabilidad)** | `⟨50% Durabilidad de espadas⟩` | ⟨50% Durabilidad de espadas⟩ | Gris (`ChatFormatting.GRAY`) |
| **Multiplicador 500% / 5x** | `⟨5x Durabilidad de picos⟩` | ⟨5x Durabilidad de picos⟩ | Gris (`ChatFormatting.GRAY`) |
| **Anulación de objeto de mod** | `⟨300% Durabilidad de Plasma Cutter⟩` | ⟨300% Durabilidad de Plasma Cutter⟩ | Gris (`ChatFormatting.GRAY`) |
| **Base vanilla (100%)** | *(Ninguno)* | *(No se muestra ninguna línea adicional)* | — |

---

## 🎨 Modos de formato de descripción emergente (`tooltipFormat`)

El mod admite 3 formatos de visualización configurables mediante `config/durability-multiplier.json` y la GUI de ModMenu:
1. **`ADAPTIVE` (Predeterminado)**: Muestra automáticamente multiplicadores limpios (`2x`, `5x`) para centenas exactas, y porcentajes (`50%`, `150%`) en caso contrario.
2. **`PERCENTAGE`**: Siempre muestra porcentajes explícitos (ej. `200% Durabilidad de espadas`, `50% Durabilidad de picos`).
3. **`MULTIPLIER`**: Siempre muestra multiplicadores decimales (ej. `2x Durabilidad de espadas`, `0.5x Durabilidad de espadas`, `1.5x Durabilidad de petos`).

---

## 🖥️ Ejecución del lado cliente y servidor

```
                       [Item Tooltip Render]
                                 │
                                 ▼
                     [Is Player on Integrated Server?]
                     ├── YES ──► Read GameRules from ServerLevel
                     │           (DurabilityHelper.getTooltipLabel)
                     │
                     └── NO (Remote Server) ──► Read Synced Client Cache
                                                (DurabilityClientState)
```

1. **Servidor integrado (Un jugador / LAN)**: Las descripciones consultan las GameRules activas de `ServerLevel` en tiempo real.
2. **Cliente dedicado (Multijugador)**: Las descripciones se leen de `DurabilityClientState`, que se actualiza mediante paquetes `DurabilityPayload` cuando cambian las reglas del servidor.
