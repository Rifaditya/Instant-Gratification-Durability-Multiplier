# Commands & Administration (26.2)

| Administrative System | Detail |
| :--- | :--- |
| **Command Engine** | Vanilla Minecraft `/gamerule` Brigadier Command System |
| **Namespace** | `ig:` prefix for all rules |
| **Permission Level** | Level 2 (OP / Singleplayer Cheats Enabled) |
| **GUI Administration** | Supported via GameRules screen & ModMenu config |
| **Absence Policy** | **Zero custom Brigadier command subtrees** by design |

---

## ⚡ In-Game Administration Workflow

Durability Multiplier relies entirely on vanilla `/gamerule` commands. No custom commands (such as `/durability set` or `/durability reload`) are added, ensuring 100% native compatibility with vanilla command blocks, functions, permissions, and datapacks.

### Common Administrative Tasks

#### 1. Configure Standard Survival Buffs
```mcfunction
# Double all items globally (Default)
/gamerule ig:dm_multiplier_global 2

# Give mining tools a 4x lifespan
/gamerule ig:dm_multiplier_tools 4

# Give weapons a 3x lifespan
/gamerule ig:dm_multiplier_weapons 3
```

#### 2. Configure Combat & PvP Server Settings
```mcfunction
# Keep armor at vanilla durability (1x) to prevent overly tanky players
/gamerule ig:dm_multiplier_armor 1

# Give weapons 2x durability
/gamerule ig:dm_multiplier_swords 2
/gamerule ig:dm_multiplier_bows 2
```

#### 3. Enable Creative-Style Survival (Unbreakable Elytra & Tools)
```mcfunction
# Unbreakable Elytra for limitless exploration
/gamerule ig:dm_infinity_elytra true

# Unbreakable Tools for building mega-projects
/gamerule ig:dm_infinity_tools true
```

#### 4. Hide Tooltip Text
```mcfunction
# Hide multiplier info from item tooltips
/gamerule ig:dm_show_tooltip false
```
