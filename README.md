<div align="center">

# 💎 Durability Multiplier

**"Stop babysitting your tools. Focus on the adventure."**

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Minecraft: 26.1+](https://img.shields.io/badge/Minecraft-26.1+-brightgreen.svg)](https://www.minecraft.net/en-us/article/minecraft-snapshot-26w11a)
[![Fabric: 0.16.10+](https://img.shields.io/badge/Fabric-0.16.10+-lightgrey.svg)](https://fabricmc.net/)

</div>

---

**Durability Multiplier** gives you surgical control over item longevity. Whether you want double durability, fine-tuned fractional wear penalties, or complete invincibility (God Mode), it's just a GameRule away. Part of the **Instant Gratification Collection** — Respect the Player's Time, Not the Game's Rules.

## ✨ Features

- **🛡️ Custom Durability Scaling**: Scale durability by any percentage (200% = 2x, 50% = 0.5x, 150% = 1.5x, 1000% = 10x...).
- **🔒 100% World-Save Safe**: Probabilistic damage interception (like vanilla *Unbreaking*) never mutates item save components. Install, tweak, or remove safely anytime.
- **✦ God Mode**: Make items completely unbreakable per category.
- **🎯 Granular Control**: 12+ categories including **Spears**, **Shields**, **Maces**, and more.
- **⚖️ Hierarchy Override**: Specific rules override global defaults (e.g., God Mode swords with 200% tools).
- **👁️ Tooltip Indicators**: Dynamic tooltips show "✦ UNBREAKABLE" or "⟨2x⟩" / "⟨50%⟩" indicators with customizable display formats.
- **📦 Native Compatibility**: Works with all modded items using vanilla tags and components.

## ⚙️ Configuration

All settings are **Server-Side GameRules**. Change them via `/gamerule ig:` or the Edit Game Rules screen.

> [!NOTE]
> **Use Whole Percentages, Not Decimals**: Enter `50` for 50% half durability (2x wear), `150` for 1.5x durability, and `200` for 2x double durability. Do **not** enter decimals like `0.5` or `1.5` because Minecraft GameRules only accept integer values.

### 📈 Durability Percentages (Integer)
| Rule | Default | Description |
| :--- | :---: | :--- |
| `ig:dm_percent_global` | 200 | Base percentage for all damageable items (100 = 1x, 200 = 2x, 50 = 0.5x). |
| `ig:dm_percent_weapons` | 0 | Percentage for all weapons. Overrides Global if > 0. |
| `ig:dm_percent_swords` | 0 | Specific percentage for Swords. |
| `ig:dm_percent_spears` | 0 | Specific percentage for Spears. |
| `ig:dm_percent_shields` | 0 | Specific percentage for Shields. |
| `ig:dm_percent_tools` | 0 | Specific percentage for Pickaxes, Axes, Shears, etc. |
| `ig:dm_percent_armor` | 0 | Specific percentage for all Armor pieces. |
| `ig:dm_percent_elytra` | 0 | Specific percentage for Elytra. |

### ♾️ God Mode (Boolean)
| Rule | Default | Description |
| :--- | :---: | :--- |
| `ig:dm_infinity_global` | false | If true, ALL damageable items become unbreakable. |
| `ig:dm_infinity_weapons` | false | If true, all Weapons become unbreakable. |
| `ig:dm_infinity_shields` | false | If true, Shields become unbreakable. |
| `ig:dm_infinity_tools` | false | If true, Tools (including Shears, Brushes) become unbreakable. |

## 🧩 Compatibility

- **Minecraft**: 26.1 Snapshot 11+
- **Fabric Loader**: 0.16.10+
- **DasikLibrary**: Required for GameRule management.
- **Combat Penalty Remover**: Fully compatible. Both can be used together for maximum protection.

## 💖 Support the Project

Keeping this mod open-source, up-to-date, and completely free takes a massive amount of time and dedication. If you like the mod, please support me! Even if you build and use the latest code straight from this repository, downloading the mod on Modrinth or CurseForge generates crucial support that keeps this project alive.

You can also donate directly to help cover hosting and development costs. Every single download, share, and donation really helps me keep this mod open-source and active!

*   **Download on Modrinth**: [Modrinth Page](https://modrinth.com/mod/instant-gratification-durability-multiplier)
*   **Download on CurseForge**: [CurseForge Page](https://www.curseforge.com/minecraft/mc-mods/instant-gratification-durability-multiplier)

---

<div align="center">

**Made with ❤️ by Dasik (Rifaditya)**
*"Because gaming shouldn't feel like a job."*

</div>
