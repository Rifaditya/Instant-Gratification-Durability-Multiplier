# GameRules Reference

Complete reference for all GameRules registered by Durability Multiplier.

> [!IMPORTANT]
> **Use Integer Percentages, Not Decimals**:
> Minecraft GameRules only accept whole integer values (`int`).
> - To set **50% durability (0.5x / half durability)**, enter **`50`** (do **NOT** enter `0.5`).
> - To set **150% durability (1.5x)**, enter **`150`** (do **NOT** enter `1.5`).
> - To set **200% durability (2x / double durability)**, enter **`200`** (do **NOT** enter `2.0`).
> - To set **100% durability (vanilla 1x)**, enter **`100`**.

## Durability Percentage Rules

| Rule | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `ig:dm_percent_global` | `int` | `200` | Base durability percentage for all damageable items. `100` = vanilla (1x), `200` = 2x double durability, `50` = 0.5x half durability. |
| `ig:dm_percent_weapons` | `int` | `0` | Durability percentage for all weapons. Overrides Global if `> 0`. `0` = inherit. |
| `ig:dm_percent_swords` | `int` | `0` | Specific percentage for Swords. Overrides Global/Weapons if `> 0`. `0` = inherit. |
| `ig:dm_percent_spears` | `int` | `0` | Specific percentage for Spears. Overrides Global/Weapons if `> 0`. `0` = inherit. |
| `ig:dm_percent_tridents` | `int` | `0` | Specific percentage for Tridents. Overrides Global/Weapons if `> 0`. `0` = inherit. |
| `ig:dm_percent_maces` | `int` | `0` | Specific percentage for Maces. Overrides Global/Weapons if `> 0`. `0` = inherit. |
| `ig:dm_percent_bows` | `int` | `0` | Specific percentage for Bows. Overrides Global/Weapons if `> 0`. `0` = inherit. |
| `ig:dm_percent_crossbows` | `int` | `0` | Specific percentage for Crossbows. Overrides Global/Weapons if `> 0`. `0` = inherit. |
| `ig:dm_percent_shields` | `int` | `0` | Specific percentage for Shields. Overrides Global if `> 0`. `0` = inherit. |
| `ig:dm_percent_tools` | `int` | `0` | Specific percentage for Tools (Pickaxes, Axes, Shovels, Hoes, Shears, Brushes). Overrides Global if `> 0`. `0` = inherit. |
| `ig:dm_percent_armor` | `int` | `0` | Specific percentage for Armor (Head, Chest, Leg, Foot). Overrides Global if `> 0`. `0` = inherit. |
| `ig:dm_percent_elytra` | `int` | `0` | Specific percentage for Elytra. Overrides Global if `> 0`. `0` = inherit. |

## Infinity Rules (God Mode)

If `true`, items in category **never take damage**. Takes priority over percentages.

| Rule | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `ig:dm_infinity_global` | `bool` | `false` | Make ALL damageable items unbreakable. |
| `ig:dm_infinity_weapons` | `bool` | `false` | Make Weapons unbreakable. |
| `ig:dm_infinity_swords` | `bool` | `false` | Make Swords unbreakable. Overrides Global. |
| `ig:dm_infinity_spears` | `bool` | `false` | Make Spears unbreakable. Overrides Global. |
| `ig:dm_infinity_tridents` | `bool` | `false` | Make Tridents unbreakable. Overrides Global. |
| `ig:dm_infinity_maces` | `bool` | `false` | Make Maces unbreakable. Overrides Global. |
| `ig:dm_infinity_bows` | `bool` | `false` | Make Bows unbreakable. Overrides Global. |
| `ig:dm_infinity_crossbows` | `bool` | `false` | Make Crossbows unbreakable. Overrides Global. |
| `ig:dm_infinity_shields` | `bool` | `false` | Make Shields unbreakable. Overrides Global. |
| `ig:dm_infinity_tools` | `bool` | `false` | Make Tools unbreakable. Overrides Global. |
| `ig:dm_infinity_armor` | `bool` | `false` | Make Armor unbreakable. Overrides Global. |
| `ig:dm_infinity_elytra` | `bool` | `false` | Make Elytra unbreakable. Overrides Global. |

## Display

| Rule | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `ig:dm_show_tooltip` | `bool` | `true` | Show durability status on item tooltips (`2x`, `50%`, or `✦ UNBREAKABLE`). |

## Override Hierarchy

```
Tag-Specific Infinity → Global Infinity → Dynamic/Category Percentage → Weapons Global → Global Percentage
```

**Example**: If `dm_infinity_swords = true` and `dm_infinity_global = false`, only swords are unbreakable.
**Example**: If `dm_percent_swords = 50` and `dm_percent_global = 200`, swords have 50% half durability (2x wear) while all other items have 200% double durability.

