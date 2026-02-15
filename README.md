# Durability Multiplier

> "Stop babysitting your tools. Focus on the adventure."

**Durability Multiplier** gives you complete control over item longevity. Whether you want double durability or complete invincibility (God Mode), it's just a GameRule away.

**Part of the Instant Gratification Collection** — Respect the Player's Time, Not the Game's Rules.

## Features

- **Multiplier System**: Scale durability by any factor (2x, 10x, 1000x...)
- **God Mode**: Make items completely unbreakable per category
- **Category Control**: Separate rules for Swords, Tools, Armor, and Elytra
- **Hierarchy Override**: Category-specific settings override global defaults
- **Overflow Safe**: Long math prevents integer overflow on extreme multipliers
- **Tooltip Indicator**: Visible "✦ UNBREAKABLE" or "⟨Nx⟩" on item hover
- **Native Mod Compat**: Uses vanilla tags — works with modded items automatically

## Configuration

All settings are `GameRules` — changeable via `/gamerule` or the Edit Game Rules screen.

| Rule | Type | Default | Description |
|---|---|---|---|
| `dm_multiplier_global` | int | 2 | Base multiplier for all items |
| `dm_multiplier_swords` | int | 0 | Override for Swords (0 = use global) |
| `dm_multiplier_tools` | int | 0 | Override for Tools |
| `dm_multiplier_armor` | int | 0 | Override for Armor |
| `dm_multiplier_elytra` | int | 0 | Override for Elytra |
| `dm_infinity_global` | bool | false | God Mode: all items |
| `dm_infinity_swords` | bool | false | God Mode: Swords |
| `dm_infinity_tools` | bool | false | God Mode: Tools |
| `dm_infinity_armor` | bool | false | God Mode: Armor |
| `dm_infinity_elytra` | bool | false | God Mode: Elytra |
| `dm_show_tooltip` | bool | true | Show tooltip indicator |

## Building

```bash
./gradlew build --no-daemon
```

Output JAR: `build/libs/durability-multiplier-<version>.jar`

## Requirements

- **Minecraft**: 26.1+
- **Fabric Loader**: 0.16.9+
- **Java**: 25+

## License

GPL-3.0-or-later
