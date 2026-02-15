# GameRules Reference

Complete reference for all 11 GameRules registered by Durability Multiplier.

## Multiplier Rules

| Rule | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `dm_multiplier_global` | `int` | `2` | Base multiplier for all damageable items. `2` = double durability. Set to `1` to disable. |
| `dm_multiplier_swords` | `int` | `0` | Override for Swords. Overrides Global if `> 0`. |
| `dm_multiplier_tools` | `int` | `0` | Override for Tools (Pickaxes, Axes, Shovels, Hoes, Spears). Overrides Global if `> 0`. |
| `dm_multiplier_armor` | `int` | `0` | Override for Armor (Head, Chest, Leg, Foot). Overrides Global if `> 0`. |
| `dm_multiplier_elytra` | `int` | `0` | Override for Elytra. Overrides Global if `> 0`. |

## Infinity Rules (God Mode)

If `true`, items in category **never take damage**. Takes priority over multipliers.

| Rule | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `dm_infinity_global` | `bool` | `false` | Make ALL damageable items unbreakable. |
| `dm_infinity_swords` | `bool` | `false` | Make Swords unbreakable. Overrides Global. |
| `dm_infinity_tools` | `bool` | `false` | Make Tools unbreakable. Overrides Global. |
| `dm_infinity_armor` | `bool` | `false` | Make Armor unbreakable. Overrides Global. |
| `dm_infinity_elytra` | `bool` | `false` | Make Elytra unbreakable. Overrides Global. |

## Display

| Rule | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `dm_show_tooltip` | `bool` | `true` | Show durability status on item tooltips (`Nx` multiplier or `✦ UNBREAKABLE`). |

## Override Hierarchy

```
Tag-Specific Infinity → Global Infinity → Tag-Specific Multiplier → Global Multiplier
```

**Example**: If `dm_infinity_swords = true` and `dm_infinity_global = false`, only swords are unbreakable.
**Example**: If `dm_multiplier_swords = 10` and `dm_multiplier_global = 2`, swords get 10x but everything else gets 2x.
