<div align="center">

# 💎 Durability Multiplier

**"Stop babysitting your tools. Focus on the adventure."**

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Minecraft: 26.1+](https://img.shields.io/badge/Minecraft-26.1+-brightgreen.svg)](https://www.minecraft.net/en-us/article/minecraft-snapshot-26w11a)
[![Fabric: 0.16.10+](https://img.shields.io/badge/Fabric-0.16.10+-lightgrey.svg)](https://fabricmc.net/)

</div>

---

**Durability Multiplier** gives you surgical control over item longevity. Whether you want double durability or complete invincibility (God Mode), it's just a GameRule away. Part of the **Instant Gratification Collection** — Respect the Player's Time, Not the Game's Rules.

## ✨ Features

- **🛡️ Custom Multipliers**: Scale durability by any factor (2x, 10x, 1000x...).
- **✦ God Mode**: Make items completely unbreakable per category.
- **🎯 Granular Control**: 12+ categories including **Spears**, **Shields**, **Maces**, and more.
- **⚖️ Hierarchy Override**: Specific rules override global defaults (e.g., God Mode swords with 2x tools).
- **👁️ Tooltip Indicators**: Dynamic tooltips show "✦ UNBREAKABLE" or "⟨Nx⟩" indicators.
- **📦 Native Compatibility**: Works with all modded items using vanilla tags.

## ⚙️ Configuration

All settings are **Server-Side GameRules**. Change them via `/gamerule ig:` or the Edit Game Rules screen.

### 📈 Multipliers (Integer)
| Rule | Default | Description |
| :--- | :---: | :--- |
| `ig:dm_multiplier_global` | 2 | Base multiplier for all damageable items. |
| `ig:dm_multiplier_weapons` | 0 | Multiplier for all weapons. Overrides Global if > 0. |
| `ig:dm_multiplier_swords` | 0 | Specific multiplier for Swords. |
| `ig:dm_multiplier_spears` | 0 | Specific multiplier for Spears (Snapshot 11+). |
| `ig:dm_multiplier_shields` | 0 | Specific multiplier for Shields. |
| `ig:dm_multiplier_tools` | 0 | Specific multiplier for Pickaxes, Axes, Shears, etc. |
| `ig:dm_multiplier_armor` | 0 | Specific multiplier for all Armor pieces. |
| `ig:dm_multiplier_elytra` | 0 | Specific multiplier for Elytra. |

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
