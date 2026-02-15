<div align="center">

<!-- Banner placeholder — replace URL when banner is uploaded -->
<!-- ![Durability Multiplier Banner](https://media.forgecdn.net/attachments/XXXX/XXXX/banner.jpg) -->

</div>
<p align="center">
    <a href="https://www.curseforge.com/minecraft/mc-mods/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
    <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java" alt="Java">
    <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License">
    <img src="https://img.shields.io/badge/Minecraft-26.1+-brightgreen?style=for-the-badge" alt="Minecraft 26.1+">
</p>

# ⚒️ Durability Multiplier

**No Backports:** This mod targets **Minecraft 26.1+** (Snapshot 4). Older versions are unsupported.

> **Stop babysitting your tools. Focus on the adventure.**

**Durability Multiplier** gives you complete control over item longevity. Whether you want double durability or complete invincibility (God Mode), it's just a GameRule away. Part of the **Instant Gratification Collection** — mods that respect the player's time.

---

## ✨ Features

### 🔧 Multiplier System

Make items last 2x, 10x, or even 2,147,483,647x longer.

- **Global Multiplier**: One rule to affect all damageable items.
- **Category Overrides**: Separate multipliers for Swords, Tools, Armor, and Elytra.
- **Overflow Protection**: Uses `long` math internally to prevent integer overflow — items cap at max durability instead of breaking instantly.

### 🛡️ God Mode (Infinity)

Toggle complete invincibility for item categories.

- **Per-Category Control**: Make only your armor unbreakable, or make everything invincible.
- **Priority System**: Infinity always takes precedence over multipliers.
- **Hierarchy**: Tag-specific settings override Global settings.

### 🏷️ Native Mod Compatibility

Built on the **Vanilla Tag System**. Works **automatically** with items from other mods (Better Nether, Mythic Metals, etc.) as long as they use standard tags like `#minecraft:swords` or `#minecraft:chest_armor`.

- **Zero Config**: Install a new mod, and its items inherit your multipliers instantly.
- **Tag Categories**: Swords, Tools (Pickaxes, Axes, Shovels, Hoes, Spears), Armor (all pieces), Elytra.

### 💬 Tooltip Display

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

Hover over any item to see its durability status:

- **`⟨4x Durability⟩`** — gray text showing active multiplier
- **`✦ UNBREAKABLE`** — gold bold text when God Mode is active
- Can be toggled with `dm_show_tooltip`.

### ⚙️ Pure GameRules

<p align="center">
  <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/2026-02-15_11.24.45.png">
</p>

No config files to manage. Everything lives in the **Edit Game Rules** screen or `/gamerule` commands.

---

## 📋 Quick Start

```
/gamerule dm_multiplier_global 4      → 4x durability for everything
/gamerule dm_infinity_swords true     → Swords never break
/gamerule dm_multiplier_armor 10      → 10x armor durability
/gamerule dm_infinity_global true     → Everything is unbreakable
/gamerule dm_show_tooltip false       → Hide tooltip indicator
```

---

## 📦 Install

1. Install **[Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)**.
2. Download `durability-multiplier-1.0.0.jar` and place it in your `mods` folder.
3. Launch the game — default settings give **2x durability** to all items.

---

## ☕ Support

If you enjoy the **Instant Gratification** collection, consider fueling the next update!

[![Ko-fi](https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&logo=ko-fi&logoColor=white)](https://ko-fi.com/dasikigaijin/tip)
[![SocioBuzz](https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge)](https://sociabuzz.com/dasikigaijin/tribe)

> [!NOTE]
> **Indonesian Users:** SocioBuzz supports local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!

---

## 📜 Credits

| Role | Author |
| :--- | :--- |
| **Architect** | **Rifaditya** (Dasik) |
| **Collection** | Instant Gratification |
| **License** | GPLv3 |

---

> [!IMPORTANT]
> **Modpack Permissions:** You are free to include this mod in modpacks, **provided the modpack is hosted on the same platform** (e.g. CurseForge).
>
> **Cross-platform distribution is not permitted.** If you download this mod from CurseForge, your modpack must also be published on CurseForge.

---

<div align="center">

**Made with ❤️ for the Minecraft community**

*Part of the Instant Gratification Collection*

</div>
