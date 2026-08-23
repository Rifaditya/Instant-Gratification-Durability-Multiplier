<div align="center">

<!-- Banner placeholder — replace URL when banner is uploaded -->
<!-- ![Durability Multiplier Banner](https://example.com/banner.jpg) -->

</div>
<p align="center">
    <a href="https://modrinth.com/mod/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
    <a href="https://modrinth.com/mod/dasik-library"><img src="https://img.shields.io/badge/Requires-Dasik_Library-blue?style=for-the-badge&logo=modrinth" alt="Modrinth: Dasik Library"></a>
    <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java" alt="Java">
    <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License">
    <img src="https://img.shields.io/badge/Minecraft-26.1+-brightgreen?style=for-the-badge" alt="Minecraft 26.1+">
</p>

# ⚒️ Durability Multiplier

**Active Version Policy:** I build **1 JAR for 1 Version**. I only update and maintain the latest active Minecraft version (e.g. when 26.3 is released, 26.2 is retired). No backports or legacy version maintenance. Please do not ask.

> **Stop babysitting your tools. Focus on the adventure.**

**Durability Multiplier** gives you complete control over item longevity. Whether you want double durability, fine-tuned fractional wear penalties, or complete invincibility (God Mode), it's just a GameRule away. 

Part of the **Instant Gratification Collection** — mods that respect the player's time.

---

## ✨ Features
 
### 🔧 Durability Scaling & Multipliers
Scale durability by any percentage (200% = 2x, 50% = 0.5x, 150% = 1.5x, 1000% = 10x...).
- **Global Durability**: One rule to affect all damageable items (`ig:dm_percent_global`).
- **12+ Granular Categories**: Separate percentages for Swords, Spears, Tridents, Maces, Bows, Crossbows, Tools, Armor, Elytra, and Shields.
- **Reductions & Boosts**: Fully supports wear penalties (e.g. 50% durability) as well as extensions (200% double durability).
- **🛡️ 100% World-Save Safe**: Uses probabilistic damage interception (just like vanilla *Unbreaking*). It **never** mutates item NBT or save files, meaning you can safely install, change settings, or remove the mod at any time without corrupting your items or world.

> [!NOTE]
> **Use Whole Percentages, Not Decimals**: Enter `50` for 50% (half durability), `150` for 1.5x, and `200` for 2x double durability. Do **not** enter decimals like `0.5` or `1.5` because Minecraft GameRules only accept integers.
 
### 🛡️ God Mode (Infinity)
Toggle complete invincibility for item categories.
- **Per-Category Control**: Make only your armor unbreakable, or make everything invincible.
- **Hierarchy**: Category-specific settings override Global settings. Infinity always takes precedence.
 
### 📦 Dynamic Modded Item Support
Automatically scans items on startup to dynamically register percentages and infinity rules for custom modded items that don't fit into standard categories.
- **Dynamic Config GUI**: Customize modded items directly from the in-game configuration screen.
 
### 💬 Tooltip Display
Hover over any item to see its durability status directly in its tooltip:
 
<table align="center" style="border: none; background: none; width: 100%;">
  <tr style="border: none; background: none;">
    <td align="center" style="border: none; background: none; width: 50%;">
      <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/Unbreak.png" width="100%">
    </td>
    <td align="center" style="border: none; background: none; width: 50%;">
      <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/4x%20tools.png" width="100%">
    </td>
  </tr>
</table>
 
- **`⟨2x Tools Durability⟩`** or **`⟨50% Swords Durability⟩`** — showing active durability modifier with selectable display formats (`ADAPTIVE`, `PERCENTAGE`, `MULTIPLIER`).
- **`✦ UNBREAKABLE`** — gold bold text when God Mode is active.
- Fully togglable via GameRule.
 
---
 
## ⚙️ Configuration (Native Game Rules & Config GUI)
 
Configure everything in-game using the **Edit Game Rules** screen, via standard commands, or through the optional configuration GUI (requires **ModMenu** + **Cloth Config**).

> [!WARNING]
> **GameRule & Config Separation:** Changing values in the configuration file or ModMenu configuration screen only defines default settings for **newly generated worlds**. 
> To modify settings in an **existing world**, you must change them in-game using `/gamerule` commands or through the standard GameRules edit screen.
 
<p align="center">
  <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/2026-02-15_11.24.45.png" alt="Native GameRules UI">
</p>
 
```sql
/gamerule ig:dm_percent_global 200       → 200% (2x) double durability for everything
/gamerule ig:dm_percent_global 50        → 50% (0.5x) half durability (2x wear)
/gamerule ig:dm_percent_swords 150       → 150% (1.5x) durability for swords
/gamerule ig:dm_infinity_swords true     → Swords never break (God Mode)
/gamerule ig:dm_show_tooltip false       → Hide tooltip indicator
/gamerule ig:percent_mymod_cool_item 300 → 300% (3x) durability for a specific modded item
```

---

## ☕ Support

If you enjoy the **Instant Gratification** collection, consider fueling the next update!

[![Ko-fi](https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&logo=ko-fi&logoColor=white)](https://ko-fi.com/dasikigaijin/tip)
[![SocioBuzz](https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge)](https://sociabuzz.com/dasikigaijin/tribe)
[![Saweria](https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge)](https://saweria.co/DasikIgaijinn)

> [!NOTE]
> **Indonesian Users:** SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!

---

## 📜 Credits

| Role | Author |
| :--- | :--- |
| **Creator** | **Rifaditya** (Dasik) |
| **Collection** | Instant Gratification |
| **License** | GPLv3 |

---

> [!IMPORTANT]
> **📦 Modpack Permissions & Distribution:** You are free to include this mod in any modpack on any platform. However, the mod itself must be downloaded from its official distribution pages on **Modrinth** or **CurseForge**. Re-uploading or redistributing the mod jar file to third-party sites is strictly prohibited unless explicitly permitted by the creator.
> 
> **License & Forks:** Since the source code is licensed under **GNU GPLv3**, you are fully permitted to fork the repository, make modifications, build your own versions, and distribute them under the terms of the GPLv3. The prohibition on third-party redistribution applies exclusively to the official compiled releases/jars published by the original creator (Dasik/Rifaditya). Forks must be published as distinct projects, not direct re-uploads of official builds.

---

<div align="center">

**Made with ❤️ for the Minecraft community**

*Part of the Instant Gratification Collection*

</div>
