# Referencia de GameRules (26.1.2)

Todas las reglas de Durability Multiplier están registradas en la categoría personalizada **`durability-multiplier:durability_multiplier`** (`"Durability Multiplier"`).

---

## 📊 Tablas de referencia completas de GameRules

### 1. GameRules de porcentaje de durabilidad
Las reglas de porcentaje controlan el escalado de durabilidad de los objetos.
* `200` = 200% (durabilidad 2x)
* `100` = 100% (Base vanilla 1x)
* `50` = 50% (Mitad de durabilidad / desgaste 2x)
* `0` = Hereda de la categoría padre o valor global por defecto
* `-1` = Centinela de **Un solo uso (Modo Cristal)** (se rompe en 1 golpe)

| # | Identificador de GameRule | Tipo | Predeterminado | Mín | Descripción y comportamiento |
| :-: | :--- | :---: | :---: | :---: | :--- |
| 1 | `ig:dm_percent_global` | `Integer` | `200` | `-1` | Porcentaje base global para todos los objetos con durabilidad. |
| 2 | `ig:dm_percent_weapons` | `Integer` | `0` | `-1` | Modificador global para todas las armas (espadas, lanzas, tridentes, mazas, arcos, ballestas). |
| 3 | `ig:dm_percent_swords` | `Integer` | `0` | `-1` | Porcentaje específico para espadas (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `Integer` | `0` | `-1` | Porcentaje específico para lanzas (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `Integer` | `0` | `-1` | Porcentaje específico para tridentes (`TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `Integer` | `0` | `-1` | Porcentaje específico para mazas (`MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `Integer` | `0` | `-1` | Porcentaje específico para arcos (`BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `Integer` | `0` | `-1` | Porcentaje específico para ballestas (`CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `Integer` | `0` | `-1` | Porcentaje específico para escudos (`ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `Integer` | `0` | `-1` | Porcentaje de categoría principal para todas las herramientas. |
| 11 | `ig:dm_percent_pickaxes` | `Integer` | `0` | `-1` | Porcentaje específico para picos (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `Integer` | `0` | `-1` | Porcentaje específico para hachas (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `Integer` | `0` | `-1` | Porcentaje específico para palas (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `Integer` | `0` | `-1` | Porcentaje específico para azadas (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `Integer` | `0` | `-1` | Porcentaje específico para tijeras (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `Integer` | `0` | `-1` | Porcentaje específico para cañas de pescar (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `Integer` | `0` | `-1` | Porcentaje específico para pinceles (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `Integer` | `0` | `-1` | Porcentaje específico para mecheros (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `Integer` | `0` | `-1` | Porcentaje de categoría principal para todas las piezas de armadura. |
| 20 | `ig:dm_percent_helmets` | `Integer` | `0` | `-1` | Porcentaje específico para cascos (`#c:helmets`, ranura de cabeza). |
| 21 | `ig:dm_percent_chestplates` | `Integer` | `0` | `-1` | Porcentaje específico para petos (`#c:chestplates`, ranura de pecho). |
| 22 | `ig:dm_percent_leggings` | `Integer` | `0` | `-1` | Porcentaje específico para pantalones (`#c:leggings`, ranura de piernas). |
| 23 | `ig:dm_percent_boots` | `Integer` | `0` | `-1` | Porcentaje específico para botas (`#c:boots`, ranura de pies). |
| 24 | `ig:dm_percent_elytra` | `Integer` | `0` | `-1` | Porcentaje específico para élitros (`Items.ELYTRA`, `GLIDER`). |

---

### 2. GameRules de Modo Dios (Infinidad)
Cuando se activa (`true`), los objetos de esa categoría reciben $0$ de daño y nunca se rompen.

| # | Identificador de GameRule | Tipo | Predeterminado | Descripción |
| :-: | :--- | :---: | :---: | :--- |
| 25 | `ig:dm_infinity_global` | `Boolean` | `false` | Modo Dios global para todos los objetos con durabilidad del juego. |
| 26 | `ig:dm_infinity_weapons` | `Boolean` | `false` | Modo Dios para todas las armas. |
| 27 | `ig:dm_infinity_swords` | `Boolean` | `false` | Modo Dios para espadas. |
| 28 | `ig:dm_infinity_spears` | `Boolean` | `false` | Modo Dios para lanzas. |
| 29 | `ig:dm_infinity_tridents` | `Boolean` | `false` | Modo Dios para tridentes. |
| 30 | `ig:dm_infinity_maces` | `Boolean` | `false` | Modo Dios para mazas. |
| 31 | `ig:dm_infinity_bows` | `Boolean` | `false` | Modo Dios para arcos. |
| 32 | `ig:dm_infinity_crossbows` | `Boolean` | `false` | Modo Dios para ballestas. |
| 33 | `ig:dm_infinity_shields` | `Boolean` | `false` | Modo Dios para escudos. |
| 34 | `ig:dm_infinity_tools` | `Boolean` | `false` | Modo Dios para todas las herramientas. |
| 35 | `ig:dm_infinity_pickaxes` | `Boolean` | `false` | Modo Dios para picos. |
| 36 | `ig:dm_infinity_axes` | `Boolean` | `false` | Modo Dios para hachas. |
| 37 | `ig:dm_infinity_shovels` | `Boolean` | `false` | Modo Dios para palas. |
| 38 | `ig:dm_infinity_hoes` | `Boolean` | `false` | Modo Dios para azadas. |
| 39 | `ig:dm_infinity_shears` | `Boolean` | `false` | Modo Dios para tijeras. |
| 40 | `ig:dm_infinity_fishing_rods` | `Boolean` | `false` | Modo Dios para cañas de pescar. |
| 41 | `ig:dm_infinity_brushes` | `Boolean` | `false` | Modo Dios para pinceles. |
| 42 | `ig:dm_infinity_flint_and_steel` | `Boolean` | `false` | Modo Dios para mecheros. |
| 43 | `ig:dm_infinity_armor` | `Boolean` | `false` | Modo Dios para toda la armadura. |
| 44 | `ig:dm_infinity_helmets` | `Boolean` | `false` | Modo Dios para cascos. |
| 45 | `ig:dm_infinity_chestplates` | `Boolean` | `false` | Modo Dios para petos. |
| 46 | `ig:dm_infinity_leggings` | `Boolean` | `false` | Modo Dios para pantalones. |
| 47 | `ig:dm_infinity_boots` | `Boolean` | `false` | Modo Dios para botas. |
| 48 | `ig:dm_infinity_elytra` | `Boolean` | `false` | Modo Dios para élitros. |

---

### 3. GameRules de un solo uso (Modo de cristal)
Cuando se activa (`true`), los objetos de esa categoría se rompen tras un solo golpe.

| # | Identificador de GameRule | Tipo | Predeterminado | Descripción |
| :-: | :--- | :---: | :---: | :--- |
| 49 | `ig:dm_single_use_global` | `Boolean` | `false` | Modo de cristal global (un solo uso) para todos los objetos. |
| 50 | `ig:dm_single_use_weapons` | `Boolean` | `false` | Un solo uso para todas las armas. |
| 51 | `ig:dm_single_use_swords` | `Boolean` | `false` | Un solo uso para espadas. |
| 52 | `ig:dm_single_use_spears` | `Boolean` | `false` | Un solo uso para lanzas. |
| 53 | `ig:dm_single_use_tridents` | `Boolean` | `false` | Un solo uso para tridentes. |
| 54 | `ig:dm_single_use_maces` | `Boolean` | `false` | Un solo uso para mazas. |
| 55 | `ig:dm_single_use_bows` | `Boolean` | `false` | Un solo uso para arcos. |
| 56 | `ig:dm_single_use_crossbows` | `Boolean` | `false` | Un solo uso para ballestas. |
| 57 | `ig:dm_single_use_shields` | `Boolean` | `false` | Un solo uso para escudos. |
| 58 | `ig:dm_single_use_tools` | `Boolean` | `false` | Un solo uso para todas las herramientas. |
| 59 | `ig:dm_single_use_pickaxes` | `Boolean` | `false` | Un solo uso para picos. |
| 60 | `ig:dm_single_use_axes` | `Boolean` | `false` | Un solo uso para hachas. |
| 61 | `ig:dm_single_use_shovels` | `Boolean` | `false` | Un solo uso para palas. |
| 62 | `ig:dm_single_use_hoes` | `Boolean` | `false` | Un solo uso para azadas. |
| 63 | `ig:dm_single_use_shears` | `Boolean` | `false` | Un solo uso para tijeras. |
| 64 | `ig:dm_single_use_fishing_rods` | `Boolean` | `false` | Un solo uso para cañas de pescar. |
| 65 | `ig:dm_single_use_brushes` | `Boolean` | `false` | Un solo uso para pinceles. |
| 66 | `ig:dm_single_use_flint_and_steel` | `Boolean` | `false` | Un solo uso para mecheros. |
| 67 | `ig:dm_single_use_armor` | `Boolean` | `false` | Un solo uso para toda la armadura. |
| 68 | `ig:dm_single_use_helmets` | `Boolean` | `false` | Un solo uso para cascos. |
| 69 | `ig:dm_single_use_chestplates` | `Boolean` | `false` | Un solo uso para petos. |
| 70 | `ig:dm_single_use_leggings` | `Boolean` | `false` | Un solo uso para pantalones. |
| 71 | `ig:dm_single_use_boots` | `Boolean` | `false` | Un solo uso para botas. |
| 72 | `ig:dm_single_use_elytra` | `Boolean` | `false` | Un solo uso para élitros. |

---

### 4. GameRules de visualización y mods dinámicos

| Identificador de GameRule | Tipo | Predeterminado | Descripción |
| :--- | :---: | :---: | :--- |
| `ig:dm_show_tooltip` | `Boolean` | `true` | Muestra la línea de bono de durabilidad en descripciones emergentes. |
| `ig:percent_<mod>_<item>` | `Integer` | `0` | Anulación de porcentaje dinámico para objeto de mod específico (Mín `-1`). |
| `ig:infinity_<mod>_<item>` | `Boolean` | `false` | Anulación de Modo Dios dinámico para objeto de mod específico. |
| `ig:single_use_<mod>_<item>` | `Boolean` | `false` | Anulación de Un solo uso dinámico para objeto de mod específico. |

---

## ⚡ Comandos de ajuste dentro del juego

```mcfunction
# Query current global percentage
/gamerule ig:dm_percent_global

# Set diamond/netherite pickaxes to 500% (5x) durability
/gamerule ig:dm_percent_pickaxes 500

# Make Elytra wings unbreakable
/gamerule ig:dm_infinity_elytra true

# Set a modded weapon to Single-Use using the -1 sentinel
/gamerule ig:percent_techmod_plasma_cutter -1

# Disable all multipliers (vanilla 100% baseline)
/gamerule ig:dm_percent_global 100
```

