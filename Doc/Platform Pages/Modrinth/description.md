<p align="center">
  <a href="https://modrinth.com/mod/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
  <a href="https://modrinth.com/mod/dasik-library"><img src="https://img.shields.io/badge/Requires-Dasik_Library-8A2BE2?style=for-the-badge" alt="Requires Dasik Library"></a>
  <img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&logo=java" alt="Java 25">
  <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License GPLv3">
  <img src="https://img.shields.io/badge/Minecraft-26.1.2+-brightgreen?style=for-the-badge" alt="Minecraft 26.1.2+">
</p>

# ⚒️ Durability Multiplier

> **"Stop babysitting your tools. Focus on the adventure."**

> [!NOTE]
> **1 Jar 1 Version Policy:** I build **1 dedicated JAR for each Minecraft version** (e.g. MC 26.1.2, MC 26.2, MC 26.3). Please download the exact build that matches your Minecraft installation.
> <br><br>
> **Dependency Requirement:** For modern Minecraft 26.x releases (26.1.2, 26.2, 26.3+), this mod requires both **Fabric API** and **Dasik Library** (`v1.7.4+` / `v1.8.15+`).

Tired of having your favorite diamond pickaxes, maces, and enchanted netherite armor shatter in the middle of a dangerous Nether fortress raid or deep cave expedition? Babysitting tool durability constantly interrupts your building flow, strips away valuable XP for repairs, and turns high-stakes mining into an endless cycle of maintenance.

**Durability Multiplier** gives you complete mathematical authority over item longevity. Whether you want double durability, 10x durability, complete invincibility (God Mode), or even high-stakes 1-hit break mode (Glass Cannon), it is all dynamically controlled via in-game GameRules and configuration screens with zero NBT tampering!

Part of the **Instant Gratification Collection** — mods that respect the player's time.

---

## ✨ Features

<p align="center">
  <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/4x%20tools.png" alt="4x Durability Tooltip Preview" width="85%">
  <br><br>
  <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/Unbreak.png" alt="Unbreakable God Mode Tooltip Preview" width="85%">
  <br>
  <em>Real-time in-game tooltips displaying active multipliers and unbreakable status</em>
</p>

### 📈 Stochastic Durability Percentage Scaling
Make items last 2x, 5x, 10x, or custom fractions without modifying underlying item NBT or save data:
- **Integer & Percentage Scaling**: Values represent exact durability percentages (`100` = vanilla 1.0x, `200` = 2.0x double durability, `500` = 5.0x, `50` = 0.5x half durability).
- **Stochastic Rounding**: Fractional damage uses probabilistic mathematical rounding, guaranteeing fair wear-and-tear across thousands of uses without statistical bias or premature item breaks.
- **64-Bit Arithmetic**: Built with 64-bit safe arithmetic to prevent integer wrap-around — high durability items cleanly clamp at maximum durability without breaking unexpectedly.

### 🛡️ God Mode (Infinity Mode)
Toggle complete invincibility for individual item categories or all items in the world:
- **Per-Category Control**: Make armor unbreakable while keeping weapon durability normal, or give Elytra infinite flight time without mending.
- **Zero Damage Taken**: Items take strictly 0 damage units while God Mode is active.

### 💥 Glass Cannon (Single-Use Mode)
Looking for a hardcore survival challenge or intense PvP minigame?
- **1-Hit Break Mechanic**: Setting `ig:dm_single_use_*` to `true` or setting an item percentage to `-1` causes tools and armor to shatter after a single use.
- Perfect for custom adventure maps, gauntlets, and ultra-hardcore challenge worlds.

### 🌲 3-Tier Priority Hierarchy
Durability Multiplier resolves item damage through an intuitive priority order:
1. **Dynamic Item Override**: Individual modded or vanilla item rule (e.g. `ig:percent_minecraft_diamond_pickaxe`).
2. **Specific Tag/Category Rule**: Target specific types (e.g. `ig:dm_percent_swords`, `ig:dm_percent_maces`, `ig:dm_percent_elytra`).
3. **Parent Category Fallback**: General group rule (`ig:dm_percent_tools`, `ig:dm_percent_weapons`, `ig:dm_percent_armor`).
4. **Global Rule Fallback**: Universal world rule (`ig:dm_percent_global`).

### 🔍 Automatic Modded Item Discovery
- Automatically sweeps registry registries on startup and whenever new modded items are registered.
- Dynamically creates independent GameRules for every detected damageable item (e.g. Tech Reborn tools, Create wrenches).
- Easily force custom items into the durability system via `config/durability-multiplier.json`.

### 💬 Real-Time Tooltip Display
Hover over any item in your inventory to see its durability status updated in real time:
- **`⟨4x Durability⟩`** — Subtle gray indicator displaying the active category multiplier.
- **`✦ UNBREAKABLE`** — Bold golden indicator when God Mode is active.
- **`⚠ SINGLE-USE`** — Distinct warning indicator when Glass Cannon mode is active.
- Fully toggleable via the `ig:dm_show_tooltip` GameRule.

### ⚔️ Mending & Unbreaking Compatibility
- **Unbreaking Enchantment Synergy**: Operates non-destructively on Minecraft's damage calculation (`ItemStackDurabilityMixin`). Unbreaking's probability reduction rolls naturally on top of multiplied durability!
- **Mending Parity**: Mending XP repairs items at full vanilla efficiency.
- **Zero NBT Tampering**: Items remain 100% vanilla. If you remove the mod, your tools and armor retain their exact vanilla durability and states without save corruption.

### 🧩 Compatibility & HUD Integration
- **Server-Side Native**: Works on dedicated servers without requiring clients to install the mod.
- **ModMenu & YACL Integration**: Optional graphical configuration screen in the main menu to customize new world defaults.

---

## 📊 Quick Reference & Mechanics Matrix

| Item Category | Multiplier GameRule | God Mode (Infinity) Rule | Single-Use Rule | Default Setting |
| :--- | :--- | :--- | :--- | :---: |
| **Global Fallback** | `ig:dm_percent_global` | `ig:dm_infinity_global` | `ig:dm_single_use_global` | `100` (Vanilla) / `false` |
| **Weapons Parent** | `ig:dm_percent_weapons` | `ig:dm_infinity_weapons` | `ig:dm_single_use_weapons` | `0` (Inherit) / `false` |
| **Swords** | `ig:dm_percent_swords` | `ig:dm_infinity_swords` | `ig:dm_single_use_swords` | `0` (Inherit) / `false` |
| **Maces** | `ig:dm_percent_maces` | `ig:dm_infinity_maces` | `ig:dm_single_use_maces` | `0` (Inherit) / `false` |
| **Tridents** | `ig:dm_percent_tridents` | `ig:dm_infinity_tridents` | `ig:dm_single_use_tridents` | `0` (Inherit) / `false` |
| **Bows & Crossbows** | `ig:dm_percent_bows` | `ig:dm_infinity_bows` | `ig:dm_single_use_bows` | `0` (Inherit) / `false` |
| **Tools Parent** | `ig:dm_percent_tools` | `ig:dm_infinity_tools` | `ig:dm_single_use_tools` | `0` (Inherit) / `false` |
| **Pickaxes** | `ig:dm_percent_pickaxes` | `ig:dm_infinity_pickaxes` | `ig:dm_single_use_pickaxes` | `0` (Inherit) / `false` |
| **Axes & Shovels** | `ig:dm_percent_axes` | `ig:dm_infinity_axes` | `ig:dm_single_use_axes` | `0` (Inherit) / `false` |
| **Armor Parent** | `ig:dm_percent_armor` | `ig:dm_infinity_armor` | `ig:dm_single_use_armor` | `0` (Inherit) / `false` |
| **Helmets / Chestplates** | `ig:dm_percent_helmets` | `ig:dm_infinity_helmets` | `ig:dm_single_use_helmets` | `0` (Inherit) / `false` |
| **Leggings / Boots** | `ig:dm_percent_leggings` | `ig:dm_infinity_leggings` | `ig:dm_single_use_leggings` | `0` (Inherit) / `false` |
| **Elytra** | `ig:dm_percent_elytra` | `ig:dm_infinity_elytra` | `ig:dm_single_use_elytra` | `0` (Inherit) / `false` |
| **Shields** | `ig:dm_percent_shields` | `ig:dm_infinity_shields` | `ig:dm_single_use_shields` | `0` (Inherit) / `false` |

---

## 🚀 In-Game Commands & Quick Start

Durability Multiplier parameters are managed via native Minecraft `/gamerule` commands with tab completion:

```text
/gamerule ig:dm_percent_global <percentage>     → Set global durability percentage (e.g. 200 for 2x, 500 for 5x)
/gamerule ig:dm_percent_tools <percentage>      → Set durability percentage for all tools
/gamerule ig:dm_percent_pickaxes <percentage>   → Set durability percentage specifically for pickaxes
/gamerule ig:dm_infinity_global <true|false>    → Toggle God Mode (invincibility) globally
/gamerule ig:dm_infinity_elytra <true|false>    → Make Elytra completely unbreakable
/gamerule ig:dm_single_use_global <true|false>  → Toggle 1-hit break Glass Cannon mode globally
/gamerule ig:dm_show_tooltip <true|false>       → Toggle in-game hover tooltip durability indicator
```

---

## ⚙️ Configuration (Native GameRules)

> [!IMPORTANT]
> **💡 Config vs. In-Game GameRules:** The global configuration file (`config/durability-multiplier.json`) only defines default values for newly created worlds. In existing worlds, change settings in-game via the **Edit Game Rules** UI screen or the `/gamerule` command.

| GameRule Name | Type | Default | Valid Range | Description |
| :--- | :---: | :---: | :---: | :--- |
| `ig:dm_percent_global` | `Integer` | `100` | `-1` to `2147483647` | Global durability percentage (`100` = 1.0x vanilla, `200` = 2.0x, `-1` = single-use). |
| `ig:dm_percent_<category>` | `Integer` | `0` | `-1` to `2147483647` | Specific category percentage (`0` inherits from parent or global fallback). |
| `ig:dm_infinity_global` | `Boolean` | `false` | `true` / `false` | Enable God Mode (zero damage taken) for all damageable items. |
| `ig:dm_infinity_<category>` | `Boolean` | `false` | `true` / `false` | Enable God Mode specifically for the specified item category. |
| `ig:dm_single_use_global` | `Boolean` | `false` | `true` / `false` | Enable Glass Cannon mode (1-hit break) for all damageable items. |
| `ig:dm_single_use_<category>` | `Boolean` | `false` | `true` / `false` | Enable Glass Cannon mode specifically for the specified item category. |
| `ig:dm_show_tooltip` | `Boolean` | `true` | `true` / `false` | Display real-time durability multiplier and status tags on item tooltips. |

---

## 📖 In-Depth How-To & Operational Playbook

### 1. Drop-In Setup & Baseline Initialization
1. Place `durability-multiplier-*.jar` along with **Fabric API** and **Dasik Library** into your `mods` directory.
2. On first launch, the mod auto-generates `config/durability-multiplier.json` defining baseline templates for newly created worlds.

### 2. Live In-Game Tuning vs. Global Template
- **For New Worlds**: Configure global baseline defaults in the ModMenu / YACL GUI before creating a world, or edit `config/durability-multiplier.json`.
- **For Existing Worlds**: Open your world and type `/gamerule ig:dm_percent_global 300` (for 3x durability). Changes take effect instantly across all active player inventories with zero restart!

### 3. Setting Up Unbreakable Gear (God Mode)
- Want your armor to never break during intense wither fights? Run `/gamerule ig:dm_infinity_armor true`.
- Want unbreakable Elytra for infinite exploration? Run `/gamerule ig:dm_infinity_elytra true`.
- When God Mode is active, items display the gold `✦ UNBREAKABLE` indicator on hover.

### 4. Setting Up Glass Cannon Challenge Mode
- To enable extreme 1-hit break survival, set `/gamerule ig:dm_single_use_global true`.
- Every tool, weapon, and piece of armor will shatter upon dealing or taking its first damage instance.

### 5. Modded Item Overrides & Dynamic Tuning
- When companion mods add custom tools, Durability Multiplier automatically registers dynamic rules named `ig:percent_<namespace>_<item>` and `ig:infinity_<namespace>_<item>`.
- You can tune them independently via `/gamerule` or let them automatically inherit parent category rates.

---

## 🧩 Recommended Sister Mods

If you enjoy **Durability Multiplier**, these companion mods from the **Instant Gratification Collection** plug in seamlessly:

* ⛏️ [**Ore Amplifier**](https://modrinth.com/mod/instant-gratification-ore-amplifier): Supercharge ore generation density so your enhanced tools have abundant riches to mine.
* 🚀 [**Max Elytra Fly Speed**](https://modrinth.com/mod/ig-max-elytra-fly-speed): Remove elytra speed limits and break the sound barrier with unbreakable gliders.
* 🧲 [**Magnet (Let Me Get That!)**](https://modrinth.com/mod/instant-gratification-magnet,-let-me-get-that!): Vacuum all mined blocks and mob drops directly into your inventory.

> 🌟 *Explore the full [**Instant Gratification Collection**](https://modrinth.com/collection/instant-gratification) for more high-convenience enhancements.*

---

## ☕ Support

If you enjoy the **Instant Gratification Collection**, consider fueling future development!

<p align="center">
  <a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&logo=ko-fi&logoColor=white" alt="Ko-fi"></a>
  <a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
  <a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>

> [!NOTE]
> **🇮🇩 Indonesian Users:** SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!

> [!TIP]
> **Dedicated Server Hosting Partner:**
> Looking for a reliable server to play with friends? Check out **BisectHosting** for 1-click modpack installations, automated backups, and 24/7 dedicated customer support.

---

## 📜 Credits & Modpack Permissions

| Property | Information |
| :--- | :--- |
| **Creator / Author** | **Dasik** (Rifaditya) |
| **Collection** | Instant Gratification Collection |
| **License** | [GNU General Public License v3.0 (GPLv3)](https://www.gnu.org/licenses/gpl-3.0.html) |
| **Source Code** | [GitHub - Rifaditya/Instant-Gratification-Durability-Multiplier](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier) |
| **Issue Tracker** | [GitHub Issues](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier/issues) |
| **Documentation / Wiki** | [GitHub Wiki](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier/wiki) |

> [!IMPORTANT]
> **📦 Modpack Permissions & Distribution:**<br>
> You are fully welcome to include this mod in any modpack on any platform! However, the mod file must be downloaded directly through official distribution channels (**Modrinth** or **CurseForge**). Re-uploading, mirroring, or redistributing the original mod JAR to third-party mirror sites, scraper portals, or unauthorized launchers is strictly prohibited.
> <br><br>
> **⚖️ License & Fork Guidelines (No Zero-Change Re-uploads):**<br>
> This project is open-source under the **GNU GPLv3**. You are fully encouraged to inspect the code, learn from it, and fork the repository to create genuine modifications, substantial feature expansions, or community ports—provided your project remains open-source under GPLv3 with proper attribution.<br>
> **However, straight 1:1 re-uploads, clone forks with no meaningful functional changes, or re-publishing identical builds under different project names (e.g. to farm downloads or rewards) are strictly forbidden.**

---

<p align="center">
  <strong>Made with ❤️ for the Minecraft community</strong><br>
  <em>Part of the Instant Gratification Collection</em>
</p>
