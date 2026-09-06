<p align="center">
    <a href="https://modrinth.com/mod/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
    <a href="https://modrinth.com/mod/dasik-library"><img src="https://img.shields.io/badge/Requires-Dasik_Library-blue?style=for-the-badge&logo=modrinth" alt="Modrinth: Dasik Library"></a>
    <img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&logo=java" alt="Java 25">
    <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License">
    <img src="https://img.shields.io/badge/Minecraft-26.2+-brightgreen?style=for-the-badge" alt="Minecraft 26.2+">
</p>

# ⚒️ Durability Multiplier

> **"Stop babysitting your tools. Focus on the adventure."**

Tired of having your favorite diamond pickaxes, maces, and enchanted netherite armor shatter in the middle of a dangerous Nether fortress raid or deep mining expedition? **Durability Multiplier** gives you complete control over item longevity. Whether you want double durability, 10x durability, or complete invincibility (God Mode), it's just a GameRule away.

Part of the **Instant Gratification Collection** — mods that respect the player's time.

---

## ✨ Features

### 🔧 Granular Multiplier System
Make items last 2x, 10x, or even 2,147,483,647x longer without altering underlying item NBT or save data.

- **Global Multiplier**: One central rule (`dm_multiplier_global`) to scale all damageable items in the world.
- **Granular Category Overrides**: Independent multipliers for Swords, Spears, Tridents, Maces, Bows, Crossbows, Tools, Armor, and Elytra.
- **64-Bit Overflow Protection**: Built with 64-bit `long` arithmetic to prevent integer wrap-around — items cleanly cap at maximum durability instead of breaking unexpectedly.

> [!TIP]
> **💡 Pro-Tip:** Multipliers dynamically stack with vanilla Unbreaking enchantments, giving high-tier enchanted gear extreme longevity!

### 🛡️ God Mode (Infinity Mode)
Toggle complete invincibility for individual item categories or all items simultaneously.

- **Per-Category Control**: Make only your armor unbreakable while keeping weapon durability normal, or make everything invincible.
- **Priority Hierarchy**: Category-specific settings override global rules, and Infinity mode always takes precedence over numerical multipliers.

### 💬 Real-Time Tooltip Display
Hover over any item in your inventory to see its durability status updated in real time:

<p align="center">
  <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/Unbreak.png" alt="Unbreakable Tooltip" width="85%">
  <br><br>
  <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/4x%20tools.png" alt="4x Durability Tooltip" width="85%">
</p>

- **`⟨4x Durability⟩`** — Subtle gray indicator displaying the active category multiplier.
- **`✦ UNBREAKABLE`** — Bold golden indicator when God Mode is active.
- Can be toggled on or off anytime via `dm_show_tooltip`.

### 🔍 Compatibility & HUD Integration
- **ModMenu & Cloth Config / YACL Integration**: Configure default multipliers and toggles directly in singleplayer using the graphical configuration screen.
- **Server-Side Dedicated Support**: 100% functional on dedicated servers — vanilla clients can connect seamlessly without needing the mod installed on their client!
- **Zero NBT Modification**: Uses non-destructive event interception powered by **DasikLibrary**, guaranteeing your world saves remain 100% vanilla safe.

---

## 📊 Quick Reference & Mechanics Matrix

| Category | Multiplier Rule | God Mode (Infinity) Rule | Default Value |
| :--- | :--- | :--- | :--- |
| **Global** | `dm_multiplier_global` | `dm_infinity_global` | Multiplier: `2` (2x) / Infinity: `false` |
| **Weapons** | `dm_multiplier_weapons` | `dm_infinity_weapons` | Multiplier: `0` (Inherit Global) |
| **Swords** | `dm_multiplier_swords` | `dm_infinity_swords` | Multiplier: `0` (Inherit Global) |
| **Spears** | `dm_multiplier_spears` | `dm_infinity_spears` | Multiplier: `0` (Inherit Global) |
| **Tridents** | `dm_multiplier_tridents` | `dm_infinity_tridents` | Multiplier: `0` (Inherit Global) |
| **Maces** | `dm_multiplier_maces` | `dm_infinity_maces` | Multiplier: `0` (Inherit Global) |
| **Bows & Crossbows** | `dm_multiplier_bows` / `crossbows` | `dm_infinity_bows` / `crossbows` | Multiplier: `0` (Inherit Global) |
| **Tools (Pick/Axe/Shovel/Hoe)** | `dm_multiplier_tools` | `dm_infinity_tools` | Multiplier: `0` (Inherit Global) |
| **Armor (All Pieces)** | `dm_multiplier_armor` | `dm_infinity_armor` | Multiplier: `0` (Inherit Global) |
| **Elytra** | `dm_multiplier_elytra` | `dm_infinity_elytra` | Multiplier: `0` (Inherit Global) |

---

## 📋 Quick Start Commands

```
/gamerule dm_multiplier_global 4      → 4x durability for all items
/gamerule dm_infinity_swords true     → Swords never break
/gamerule dm_multiplier_armor 10      → 10x armor durability
/gamerule dm_infinity_global true     → Everything is unbreakable
/gamerule dm_show_tooltip false       → Hide tooltip indicator
```

---

## ⚙️ Configuration (Native Game Rules)

> [!IMPORTANT]
> **💡 Config vs. In-Game GameRules:** The global configuration file only defines default values for newly created worlds. In existing worlds, change settings in-game via the **Edit Game Rules** UI screen or the `/gamerule` command.

All settings live directly inside the **"Durability Multiplier"** GameRules category with zero external config files to manage.

<p align="center">
  <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/2026-02-15_11.24.45.png" alt="Edit Game Rules UI" width="85%">
</p>

---

## 📖 How-To & Operational Playbook

Here is how **Durability Multiplier** operates and how to configure it to your playstyle:

1. **First Launch & Auto-Population**:
   - Place `durability-multiplier-*.jar` into your `mods/` directory alongside Fabric API.
   - On the very first launch, the mod **automatically generates and populates** its default configuration file (`config/durability-multiplier.json`) and registers all `dm_*` GameRules into Minecraft's native registry with default **2x durability**.
2. **How to Configure**:
   - **In-Game (Live / Instant Update)**: Press `Esc` ➔ **Edit Game Rules** ➔ scroll down to the **"Durability Multiplier"** section (or run `/gamerule dm_multiplier_global 4`). Changes take effect immediately in your active world with **no restart required**!
   - **Config File (Global Defaults)**: If you manually edit `config/durability-multiplier.json` with a text editor to set new world defaults, **restart Minecraft** (or your server) for the new configuration file to load.
3. **How It Works In-Game**:
   - The mod intercepts item durability damage events in real time. It uses standard vanilla item tags (`#minecraft:swords`, `#minecraft:axes`, `#minecraft:chest_armor`) to identify items dynamically, scaling durability loss using safe 64-bit integer arithmetic without modifying underlying item NBT or save data.
4. **Instant In-Game Verification**:
   - Hover over any tool, weapon, or armor piece in your inventory to see the live durability status (`⟨4x Durability⟩` or `✦ UNBREAKABLE`) in the item tooltip.

### 💎 Why Using Durability Multiplier is Better (Mod vs. Vanilla Workarounds)
*You might wonder: "Can't I just use vanilla `/give` commands or custom datapacks?"* Here is why **Durability Multiplier** is the vastly superior, headache-free solution for your world or server:

| Feature & Advantage | Vanilla Commands / Datapacks | ⚒️ Durability Multiplier |
| :--- | :--- | :--- |
| **Universal Coverage** | ❌ Only affects specific `/give` items; newly crafted tools or loot chest gear are untouched. | ✅ **100% Universal** — Instantly applies to every item crafted, traded, looted, or spawned. |
| **World Save Safety** | ⚠️ Modifies item NBT tags; risk of permanent item corruption if tags break. | ✅ **100% Non-Destructive** — Pure in-memory damage interception; saves stay 100% vanilla safe. |
| **Live Real-Time Tuning** | ❌ Requires writing JSON, reloading datapacks (`/reload`), or re-issuing items. | ✅ **Live GameRules** — Change rules on the fly (`/gamerule dm_multiplier_global 4`) with zero downtime. |
| **Granular Categories** | ❌ Manual tag management across hundreds of separate item IDs. | ✅ **12+ Built-In Categories** (Swords, Armor, Elytra, Tools, Maces) with dedicated sliders. |
| **In-Game Tooltip Feedback** | ❌ No visual indicator unless you manually write custom item lore. | ✅ **Native HUD Tooltips** (`⟨4x Durability⟩` & `✦ UNBREAKABLE`) updating in real time. |
| **Dedicated Server Friendly** | ❌ Requires complex server scripts or custom permission plugins. | ✅ **Server-Side Native** — Vanilla clients connect seamlessly with zero required downloads. |

### 🛡️ How to Make Items Unbreakable (God Mode)
Want complete invincibility so you never have to repair or replace your gear ever again? You have **3 easy ways**:

- **Way 1: Via In-Game GameRules (Instant & World-Wide — Recommended)**
  - Make **everything** unbreakable:
    ```
    /gamerule dm_infinity_global true
    ```
  - Or make only a **specific category** unbreakable (e.g. Armor or Swords):
    ```
    /gamerule dm_infinity_armor true
    /gamerule dm_infinity_swords true
    ```
  - *Takes effect immediately across all players in your active world with zero restarts or item re-crafting!*
- **Way 2: Via Native Game Rules Screen**
  - Press `Esc` ➔ click **Edit Game Rules** ➔ scroll down to the **"Durability Multiplier"** category ➔ toggle **"Infinity Global"** (or individual category switches) to `ON`.
- **Way 3: Via Vanilla Item Data Components (Per-Item)**
  - Give a specific player a permanently unbreakable item using Minecraft's native data component:
    ```
    /give @p netherite_chestplate[unbreakable={}] 1
    ```

> [!TIP]
> **💡 Why Way 1 (The Mod) is Better:** Unlike vanilla `/give` which only fixes *one specific item*, toggling the mod's GameRule instantly protects **all existing and future items** in your world without touching commands or player inventories!

### 💥 How to Make Items Single-Use (2 Ways)
Want to create high-stakes "Glass Cannon" weapons or one-time consumable tools that break after a single use? There are **2 ways** to do it:

- **Way 1: Via Item Data Components (Per-Item)**
  - Give yourself an item with `max_damage=1`:
    ```
    /give @p diamond_sword[max_damage=1] 1
    ```
    This sets the item's total durability to exactly 1. The moment you strike a mob or mine a block, it shatters in a single hit!
- **Way 2: Via GameRules & Multipliers (Category-Wide — Recommended)**
  - Ensure God Mode is disabled: `/gamerule dm_infinity_swords false`.
  - Set the category multiplier to `1`: `/gamerule dm_multiplier_swords 1`.
  - At a `1x` multiplier, items take unmitigated 1:1 damage, allowing any low-durability or 1-HP item (from custom datapacks or mods) to break immediately on use without multiplier extension.

> [!TIP]
> **💡 Why Way 2 (The Mod) is Better:** You can fine-tune durability scaling globally or by category with a single command, ensuring custom survival challenges and balanced minigames work effortlessly across entire multiplayer servers.

---

## ☕ Support

If you enjoy the **Instant Gratification** collection, consider fueling future updates!

<p align="center">
  <a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&logo=ko-fi&logoColor=white" alt="Ko-fi"></a>
  <a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
  <a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>

> [!NOTE]
> **🇮🇩 Indonesian Users:** SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!

---

## 📜 Credits

| Role | Author |
| :--- | :--- |
| **Creator** | **Dasik** (Rifaditya) |
| **Collection** | Instant Gratification |
| **License** | GPLv3 |

> [!IMPORTANT]
> **📦 Modpack Permissions & Distribution:**<br>
> You are fully welcome to include this mod in any modpack on any platform! However, the mod file must be downloaded directly through official distribution channels (**Modrinth** or **CurseForge**). Re-uploading, mirroring, or redistributing the original mod JAR to third-party mirror sites, scraper portals, or unauthorized launchers is strictly prohibited.
> <br><br>
> **⚖️ License & Fork Guidelines (No Zero-Change Re-uploads):**<br>
> This project is open-source under the **GNU GPLv3**. You are fully encouraged to inspect the code, learn from it, and fork the repository to create genuine modifications, substantial feature expansions, or community ports—provided your project remains open-source under GPLv3 with proper attribution.<br>
> **However, straight 1:1 re-uploads, clone forks with no meaningful functional changes, or re-publishing identical builds under different project names (e.g. to farm downloads or rewards) are strictly forbidden.**

---

<div align="center">
  <p><strong>Made with ❤️ for the Minecraft community</strong></p>
  <p><em>Part of the Instant Gratification Collection</em></p>
</div>
