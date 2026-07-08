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

**No Backports:** This mod targets **Minecraft 26.1+**. Older versions are unsupported.

> **Stop babysitting your tools. Focus on the adventure.**

**Durability Multiplier** gives you complete control over item longevity. Whether you want double durability or complete invincibility (God Mode), it's just a GameRule away. 

Part of the **Instant Gratification Collection** — mods that respect the player's time.

---

## ✨ Features
 
### 🔧 Multiplier System
Make items last 2x, 10x, or even 2,147,483,647x longer.
- **Global Multiplier**: One rule to affect all damageable items.
- **12+ Granular Categories**: Separate multipliers for Swords, Spears, Tridents, Maces, Bows, Crossbows, Tools, Armor, Elytra, and Shields.
- **Overflow Protection**: Uses `long` math internally to prevent integer overflow — items cap at max durability instead of breaking instantly.
 
### 🛡️ God Mode (Infinity)
Toggle complete invincibility for item categories.
- **Per-Category Control**: Make only your armor unbreakable, or make everything invincible.
- **Hierarchy**: Category-specific settings override Global settings. Infinity always takes precedence.
 
### 📦 Dynamic Modded Item Support
Automatically scans items on startup to dynamically register multipliers and infinity rules for custom modded items that don't fit into standard categories.
- **Dynamic Config GUI**: Customize modded items directly from the in-game configuration screen.
 
### 💬 Tooltip Display
Hover over any item to see its durability status directly in its tooltip:
 
<table align="center" style="border: none; background: none;">
  <tr style="border: none; background: none;">
    <td align="center" style="border: none; background: none;">
      <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/Unbreak.png" height="100">
    </td>
    <td align="center" style="border: none; background: none;">
      <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/4x%20tools.png" height="100">
    </td>
  </tr>
</table>
 
- **`⟨Nx Category/Item Durability⟩`** — showing the active multiplier, dynamically rendering localized modded item names (e.g., `⟨4x Blood Katana Durability⟩`).
- **`✦ UNBREAKABLE`** — gold bold text when God Mode is active.
- Fully togglable via GameRule.
 
---
 
## ⚙️ Configuration (Native Game Rules & Config GUI)
 
Configure everything in-game using the **Edit Game Rules** screen, via standard commands, or through the optional configuration GUI (requires **ModMenu** + **Cloth Config**).
 
<p align="center">
  <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/2026-02-15_11.24.45.png" alt="Native GameRules UI">
</p>
 
```sql
/gamerule ig:dm_multiplier_global 4      → 4x durability for everything
/gamerule ig:dm_infinity_swords true     → Swords never break
/gamerule ig:dm_multiplier_armor 10      → 10x armor durability
/gamerule ig:dm_infinity_global true     → Everything is unbreakable
/gamerule ig:dm_show_tooltip false       → Hide tooltip indicator
/gamerule ig:multiplier_mymod_cool_item 5 → 5x durability for a specific modded item
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
> **Modpack Permissions:** You are free to include this mod in modpacks, **provided the modpack is hosted on the same platform** (e.g. Modrinth).
>
> **Cross-platform distribution is not permitted.** If you download this mod from Modrinth, your modpack must also be published on Modrinth.

---

<div align="center">

**Made with ❤️ for the Minecraft community**

*Part of the Instant Gratification Collection*

</div>
