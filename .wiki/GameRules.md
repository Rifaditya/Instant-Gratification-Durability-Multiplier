# GameRules Reference (26.1.2)

All Durability Multiplier GameRules are registered under the custom category **`durability-multiplier:durability_multiplier`** (`"Durability Multiplier"`).

---

## 📊 Complete GameRules Reference Tables

### 1. Durability Percentage GameRules
Percentage rules control item durability scaling.
* `200` = 200% (2x durability)
* `100` = 100% (Vanilla 1x baseline)
* `50` = 50% (Half durability / 2x wear rate)
* `0` = Inherit from parent category or global default
* `-1` = **Single-Use (Glass Mode)** sentinel (breaks in 1 hit)

| # | GameRule Identifier | Type | Default | Min | Description & Behavior |
| :-: | :--- | :---: | :---: | :---: | :--- |
| 1 | `ig:dm_percent_global` | `Integer` | `200` | `-1` | Global base percentage for all damageable items. |
| 2 | `ig:dm_percent_weapons` | `Integer` | `0` | `-1` | Global override for all weapons (Swords, Spears, Tridents, Maces, Bows, Crossbows). |
| 3 | `ig:dm_percent_swords` | `Integer` | `0` | `-1` | Specific percentage for Swords (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `Integer` | `0` | `-1` | Specific percentage for Spears (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `Integer` | `0` | `-1` | Specific percentage for Tridents (`TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `Integer` | `0` | `-1` | Specific percentage for Maces (`MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `Integer` | `0` | `-1` | Specific percentage for Bows (`BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `Integer` | `0` | `-1` | Specific percentage for Crossbows (`CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `Integer` | `0` | `-1` | Specific percentage for Shields (`ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `Integer` | `0` | `-1` | Parent category percentage for all Tools. |
| 11 | `ig:dm_percent_pickaxes` | `Integer` | `0` | `-1` | Specific percentage for Pickaxes (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `Integer` | `0` | `-1` | Specific percentage for Axes (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `Integer` | `0` | `-1` | Specific percentage for Shovels (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `Integer` | `0` | `-1` | Specific percentage for Hoes (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `Integer` | `0` | `-1` | Specific percentage for Shears (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `Integer` | `0` | `-1` | Specific percentage for Fishing Rods (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `Integer` | `0` | `-1` | Specific percentage for Brushes (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `Integer` | `0` | `-1` | Specific percentage for Flint and Steel (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `Integer` | `0` | `-1` | Parent category percentage for all Armor pieces. |
| 20 | `ig:dm_percent_helmets` | `Integer` | `0` | `-1` | Specific percentage for Helmets (`#c:helmets`, Head slot). |
| 21 | `ig:dm_percent_chestplates` | `Integer` | `0` | `-1` | Specific percentage for Chestplates (`#c:chestplates`, Chest slot). |
| 22 | `ig:dm_percent_leggings` | `Integer` | `0` | `-1` | Specific percentage for Leggings (`#c:leggings`, Legs slot). |
| 23 | `ig:dm_percent_boots` | `Integer` | `0` | `-1` | Specific percentage for Boots (`#c:boots`, Feet slot). |
| 24 | `ig:dm_percent_elytra` | `Integer` | `0` | `-1` | Specific percentage for Elytra wings (`Items.ELYTRA`, `GLIDER`). |

---

### 2. God Mode (Infinity) GameRules
When enabled (`true`), items in that category take $0$ damage and never break.

| # | GameRule Identifier | Type | Default | Description |
| :-: | :--- | :---: | :---: | :--- |
| 25 | `ig:dm_infinity_global` | `Boolean` | `false` | Global God Mode for all damageable items in the game. |
| 26 | `ig:dm_infinity_weapons` | `Boolean` | `false` | God Mode for all weapons. |
| 27 | `ig:dm_infinity_swords` | `Boolean` | `false` | God Mode for Swords. |
| 28 | `ig:dm_infinity_spears` | `Boolean` | `false` | God Mode for Spears. |
| 29 | `ig:dm_infinity_tridents` | `Boolean` | `false` | God Mode for Tridents. |
| 30 | `ig:dm_infinity_maces` | `Boolean` | `false` | God Mode for Maces. |
| 31 | `ig:dm_infinity_bows` | `Boolean` | `false` | God Mode for Bows. |
| 32 | `ig:dm_infinity_crossbows` | `Boolean` | `false` | God Mode for Crossbows. |
| 33 | `ig:dm_infinity_shields` | `Boolean` | `false` | God Mode for Shields. |
| 34 | `ig:dm_infinity_tools` | `Boolean` | `false` | God Mode for all Tools. |
| 35 | `ig:dm_infinity_pickaxes` | `Boolean` | `false` | God Mode for Pickaxes. |
| 36 | `ig:dm_infinity_axes` | `Boolean` | `false` | God Mode for Axes. |
| 37 | `ig:dm_infinity_shovels` | `Boolean` | `false` | God Mode for Shovels. |
| 38 | `ig:dm_infinity_hoes` | `Boolean` | `false` | God Mode for Hoes. |
| 39 | `ig:dm_infinity_shears` | `Boolean` | `false` | God Mode for Shears. |
| 40 | `ig:dm_infinity_fishing_rods` | `Boolean` | `false` | God Mode for Fishing Rods. |
| 41 | `ig:dm_infinity_brushes` | `Boolean` | `false` | God Mode for Brushes. |
| 42 | `ig:dm_infinity_flint_and_steel` | `Boolean` | `false` | God Mode for Flint and Steel. |
| 43 | `ig:dm_infinity_armor` | `Boolean` | `false` | God Mode for all Armor. |
| 44 | `ig:dm_infinity_helmets` | `Boolean` | `false` | God Mode for Helmets. |
| 45 | `ig:dm_infinity_chestplates` | `Boolean` | `false` | God Mode for Chestplates. |
| 46 | `ig:dm_infinity_leggings` | `Boolean` | `false` | God Mode for Leggings. |
| 47 | `ig:dm_infinity_boots` | `Boolean` | `false` | God Mode for Boots. |
| 48 | `ig:dm_infinity_elytra` | `Boolean` | `false` | God Mode for Elytra wings. |

---

### 3. Single-Use (Glass Mode) GameRules
When enabled (`true`), items in that category shatter after a single hit.

| # | GameRule Identifier | Type | Default | Description |
| :-: | :--- | :---: | :---: | :--- |
| 49 | `ig:dm_single_use_global` | `Boolean` | `false` | Global Glass Mode for all items. |
| 50 | `ig:dm_single_use_weapons` | `Boolean` | `false` | Single-Use for all weapons. |
| 51 | `ig:dm_single_use_swords` | `Boolean` | `false` | Single-Use for Swords. |
| 52 | `ig:dm_single_use_spears` | `Boolean` | `false` | Single-Use for Spears. |
| 53 | `ig:dm_single_use_tridents` | `Boolean` | `false` | Single-Use for Tridents. |
| 54 | `ig:dm_single_use_maces` | `Boolean` | `false` | Single-Use for Maces. |
| 55 | `ig:dm_single_use_bows` | `Boolean` | `false` | Single-Use for Bows. |
| 56 | `ig:dm_single_use_crossbows` | `Boolean` | `false` | Single-Use for Crossbows. |
| 57 | `ig:dm_single_use_shields` | `Boolean` | `false` | Single-Use for Shields. |
| 58 | `ig:dm_single_use_tools` | `Boolean` | `false` | Single-Use for all Tools. |
| 59 | `ig:dm_single_use_pickaxes` | `Boolean` | `false` | Single-Use for Pickaxes. |
| 60 | `ig:dm_single_use_axes` | `Boolean` | `false` | Single-Use for Axes. |
| 61 | `ig:dm_single_use_shovels` | `Boolean` | `false` | Single-Use for Shovels. |
| 62 | `ig:dm_single_use_hoes` | `Boolean` | `false` | Single-Use for Hoes. |
| 63 | `ig:dm_single_use_shears` | `Boolean` | `false` | Single-Use for Shears. |
| 64 | `ig:dm_single_use_fishing_rods` | `Boolean` | `false` | Single-Use for Fishing Rods. |
| 65 | `ig:dm_single_use_brushes` | `Boolean` | `false` | Single-Use for Brushes. |
| 66 | `ig:dm_single_use_flint_and_steel` | `Boolean` | `false` | Single-Use for Flint and Steel. |
| 67 | `ig:dm_single_use_armor` | `Boolean` | `false` | Single-Use for all Armor. |
| 68 | `ig:dm_single_use_helmets` | `Boolean` | `false` | Single-Use for Helmets. |
| 69 | `ig:dm_single_use_chestplates` | `Boolean` | `false` | Single-Use for Chestplates. |
| 70 | `ig:dm_single_use_leggings` | `Boolean` | `false` | Single-Use for Leggings. |
| 71 | `ig:dm_single_use_boots` | `Boolean` | `false` | Single-Use for Boots. |
| 72 | `ig:dm_single_use_elytra` | `Boolean` | `false` | Single-Use for Elytra wings. |

---

### 4. Display & Dynamic Modded GameRules

| GameRule Identifier | Type | Default | Description |
| :--- | :---: | :---: | :--- |
| `ig:dm_show_tooltip` | `Boolean` | `true` | Renders durability bonus line on item tooltips. |
| `ig:percent_<mod>_<item>` | `Integer` | `0` | Dynamic percentage override for specific modded item (Min `-1`). |
| `ig:infinity_<mod>_<item>` | `Boolean` | `false` | Dynamic God Mode override for specific modded item. |
| `ig:single_use_<mod>_<item>` | `Boolean` | `false` | Dynamic Single-Use override for specific modded item. |

---

## ⚡ In-Game Adjustment Commands

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

