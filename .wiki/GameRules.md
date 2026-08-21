# GameRules Reference (26.1.2)

All 25 GameRules are registered under the custom category **`durability-multiplier:durability_multiplier`** (`"Durability Multiplier"`).

---

## 📊 Complete 25 GameRules Reference Table

| # | GameRule Identifier | Type | Default | Min | Description & Behavior |
| :-: | :--- | :---: | :---: | :---: | :--- |
| 1 | `ig:dm_multiplier_global` | `Integer` | `2` | `1` | Base multiplier for all damageable items. `2` = double durability. Set to `1` to disable. |
| 2 | `ig:dm_multiplier_weapons` | `Integer` | `0` | `0` | Multiplier for all weapon items. Overrides Global if `> 0`. |
| 3 | `ig:dm_multiplier_swords` | `Integer` | `0` | `0` | Specific multiplier for Swords (`#minecraft:swords`). Overrides Global if `> 0`. |
| 4 | `ig:dm_multiplier_spears` | `Integer` | `0` | `0` | Specific multiplier for Spears (`#minecraft:spears`). Overrides Global if `> 0`. |
| 5 | `ig:dm_multiplier_tridents` | `Integer` | `0` | `0` | Specific multiplier for Tridents (`TridentItem`). Overrides Global if `> 0`. |
| 6 | `ig:dm_multiplier_maces` | `Integer` | `0` | `0` | Specific multiplier for Maces (`MaceItem`). Overrides Global if `> 0`. |
| 7 | `ig:dm_multiplier_bows` | `Integer` | `0` | `0` | Specific multiplier for Bows (`BowItem`). Overrides Global if `> 0`. |
| 8 | `ig:dm_multiplier_crossbows` | `Integer` | `0` | `0` | Specific multiplier for Crossbows (`CrossbowItem`). Overrides Global if `> 0`. |
| 9 | `ig:dm_multiplier_shields` | `Integer` | `0` | `0` | Specific multiplier for Shields (`ShieldItem`). Overrides Global if `> 0`. |
| 10 | `ig:dm_multiplier_tools` | `Integer` | `0` | `0` | Multiplier for Tools: Pickaxes, Axes, Shovels, Hoes, Shears, Brushes, etc. |
| 11 | `ig:dm_multiplier_armor` | `Integer` | `0` | `0` | Multiplier for all Armor items (Head, Chest, Legs, Feet). Overrides Global if `> 0`. |
| 12 | `ig:dm_multiplier_elytra` | `Integer` | `0` | `0` | Specific multiplier for Elytra wings (`Items.ELYTRA`, `DataComponents.GLIDER`). |
| 13 | `ig:dm_infinity_global` | `Boolean` | `false` | — | If `true`, ALL damageable items become completely unbreakable (God Mode). |
| 14 | `ig:dm_infinity_weapons` | `Boolean` | `false` | — | If `true`, all weapons become unbreakable. |
| 15 | `ig:dm_infinity_swords` | `Boolean` | `false` | — | If `true`, Swords become unbreakable. Overrides Global. |
| 16 | `ig:dm_infinity_spears` | `Boolean` | `false` | — | If `true`, Spears become unbreakable. |
| 17 | `ig:dm_infinity_tridents` | `Boolean` | `false` | — | If `true`, Tridents become unbreakable. |
| 18 | `ig:dm_infinity_maces` | `Boolean` | `false` | — | If `true`, Maces become unbreakable. |
| 19 | `ig:dm_infinity_bows` | `Boolean` | `false` | — | If `true`, Bows become unbreakable. |
| 20 | `ig:dm_infinity_crossbows` | `Boolean` | `false` | — | If `true`, Crossbows become unbreakable. |
| 21 | `ig:dm_infinity_shields` | `Boolean` | `false` | — | If `true`, Shields become unbreakable. |
| 22 | `ig:dm_infinity_tools` | `Boolean` | `false` | — | If `true`, Tools (Pickaxes, Axes, Shovels, Hoes, Shears, etc.) become unbreakable. |
| 23 | `ig:dm_infinity_armor` | `Boolean` | `false` | — | If `true`, all Armor pieces become unbreakable. |
| 24 | `ig:dm_infinity_elytra` | `Boolean` | `false` | — | If `true`, Elytra wings become unbreakable. |
| 25 | `ig:dm_show_tooltip` | `Boolean` | `true` | — | If `true`, renders durability multiplier status line on item tooltips. |

---

## ⚡ In-Game Adjustment Commands

```mcfunction
# Query current global multiplier
/gamerule ig:dm_multiplier_global

# Set diamond/netherite tools to 5x durability
/gamerule ig:dm_multiplier_tools 5

# Make Elytra wings unbreakable
/gamerule ig:dm_infinity_elytra true

# Disable all multipliers (vanilla 1x baseline)
/gamerule ig:dm_multiplier_global 1
```
