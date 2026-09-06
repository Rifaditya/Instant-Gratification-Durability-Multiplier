# Multiplicadores de durabilidad y porcentajes (26.2)

Durability Multiplier reemplaza la mecánica de desgaste fija de vanilla con un **Motor dinámico de escalado porcentual** que admite tanto aumentos de durabilidad (ej. 200% = 2x, 500% = 5x) como penalizaciones por desgaste (ej. 50% = 0.5x, 25% = 0.25x).

---

## ⚙️ Reglas de juego (GameRules) de porcentaje principales

| # | Identificador de GameRule | Predeterminado | Categoría objetivo / Descripción |
| :-: | :--- | :---: | :--- |
| 1 | `ig:dm_percent_global` | `200` | Porcentaje global aplicado a todos los objetos con durabilidad. |
| 2 | `ig:dm_percent_weapons` | `0` | Modificador principal para todas las armas (espadas, lanzas, tridentes, mazas, arcos, ballestas). |
| 3 | `ig:dm_percent_swords` | `0` | Porcentaje específico para espadas (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `0` | Porcentaje específico para lanzas (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `0` | Porcentaje específico para tridentes (`Items.TRIDENT`, `TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `0` | Porcentaje específico para mazas (`Items.MACE`, `MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `0` | Porcentaje específico para arcos (`Items.BOW`, `BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `0` | Porcentaje específico para ballestas (`Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `0` | Porcentaje específico para escudos (`Items.SHIELD`, `ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `0` | Porcentaje de categoría principal para todas las herramientas. |
| 11 | `ig:dm_percent_pickaxes` | `0` | Porcentaje específico para picos (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `0` | Porcentaje específico para hachas (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `0` | Porcentaje específico para palas (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `0` | Porcentaje específico para azadas (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `0` | Porcentaje específico para tijeras (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `0` | Porcentaje específico para cañas de pescar (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `0` | Porcentaje específico para pinceles (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `0` | Porcentaje específico para mecheros (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `0` | Porcentaje de categoría principal para todas las piezas de armadura. |
| 20 | `ig:dm_percent_helmets` | `0` | Porcentaje específico para cascos (`#minecraft:head_armor`, `#c:helmets`). |
| 21 | `ig:dm_percent_chestplates` | `0` | Porcentaje específico para petos (`#minecraft:chest_armor`, `#c:chestplates`). |
| 22 | `ig:dm_percent_leggings` | `0` | Porcentaje específico para pantalones (`#minecraft:leg_armor`, `#c:leggings`). |
| 23 | `ig:dm_percent_boots` | `0` | Porcentaje específico para botas (`#minecraft:foot_armor`, `#c:boots`). |
| 24 | `ig:dm_percent_elytra` | `0` | Porcentaje específico para élitros (`Items.ELYTRA`, `DataComponents.GLIDER`). |

> [!NOTE]
> Las reglas de anulación establecidas en `0` vuelven automáticamente a su categoría padre o al valor Global. Establecer `-1` activa **Un solo uso (Modo Cristal)**.

---

## 🔒 100% seguridad para los mundos guardados
Durability Multiplier **no** modifica el NBT de los objetos ni `DataComponents.MAX_DAMAGE` en los archivos guardados. Todo el escalado se realiza dinámicamente durante el cálculo de daño, garantizando cero corrupción del mundo si el mod se retira.
