<p align="center">
  <a href="https://discord.gg/EV99bgAFqb"><img src="https://img.shields.io/badge/Discord-Join_Community-5865F2?style=for-the-badge&logo=discord&logoColor=white" alt="Join Discord"></a>
  <a href="https://modrinth.com/mod/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
  <img src="https://img.shields.io/badge/Environment-Server_&_Client-success?style=for-the-badge" alt="Server & Client">
  <img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&logo=java" alt="Java 25">
  <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License GPLv3">
  <img src="https://img.shields.io/badge/Minecraft-26.2+-brightgreen?style=for-the-badge" alt="Minecraft 26.2+">
</p>

# 🛡️ Durability Multiplier

> **"Gear That Endures. Granular, Mathematical Durability Scaling for Every Playstyle."**

---

## 📖 Introduction

Crafting top-tier Diamond or Netherite tools, enchanting them with Mending and Unbreaking, and socketing Armor Trims represents hundreds of hours of survival dedication. Yet in vanilla Minecraft, mining out a single underground perimeter, clearing a quarry, or engaging in intense raid combat depletes durability bars at an alarming rate. You spend more time pausing to mend tools at XP farms or managing repair costs than actually enjoying your survival world.

**Durability Multiplier** eliminates equipment burnout under the **Instant Gratification** design philosophy. Rather than altering item NBT data or breaking save compatibility, it introduces server-wide mathematical durability scaling. Whether you want tools to last $2\times$, $5\times$, or $100\times$ longer—or want completely unbreakable gear—Durability Multiplier provides granular per-category control, Unbreaking enchantment synergy, anvil repair cost stabilization, and zero-save-modification safety.

> [!NOTE]
> **1 Jar 1 Version Policy:** I build **1 dedicated JAR for each Minecraft version** (e.g. MC 26.2, MC 26.3). Please download the exact build that matches your Minecraft installation.
> 
> **Survival World Safe:** Safe to add or remove at any time! Does NOT permanently corrupt item NBT or save files; gear scales dynamically at runtime.

Part of the **Instant Gratification Collection** — mods that respect the player's time.

---

## ✨ Features

### 📐 Dual Mathematical Scaling Modes
- **Proportional Wear Reduction (Default):** Hooks directly into `ItemStack.hurtAndBreak` to scale damage consumption probabilistically. For instance, with a $5\times$ multiplier, each tool use has an exact $20\%$ chance of inflicting $1$ point of wear, smoothly extending lifespan by fivefold while preserving standard vanilla durability bars.
- **True Capacity Inflation Mode:** Scales the effective max-damage ceiling of items directly, allowing tools to visually withstand thousands of uses before entering the red damage zone.

### 🏷️ Fine-Grained Category Scaling
Customize multipliers independently across different gear classifications:
- ⚔️ **Weapons:** Swords, Axes, Maces, Bows, Crossbows, and Tridents (`durability_multiplier:weapon_multiplier`).
- ⛏️ **Mining Tools:** Pickaxes, Shovels, and Hoes (`durability_multiplier:tool_multiplier`).
- 🛡️ **Armor Sets:** Helmets, Chestplates, Leggings, and Boots (`durability_multiplier:armor_multiplier`).
- 🪽 **Elytra Aeronautics:** Dedicated flight durability multiplier to enable long-distance exploration without mid-air wing snaps (`durability_multiplier:elytra_multiplier`).
- 🎣 **Utility & Fishing:** Fishing rods, Shears, Flint & Steel, and Brushes (`durability_multiplier:utility_multiplier`).

### 🔮 Unbreaking Enchantment Synergy
- **True Multiplicative Stacking:** Works seamlessly alongside vanilla Unbreaking enchantments. A $3\times$ mod multiplier combined with Unbreaking III ($4\times$ effective durability) yields a true $12\times$ lifespan boost.
- **Custom Wear Roll Thresholds:** Configurable formula curve ensures that low-durability items like Gold or Flint & Steel remain balanced while high-tier gear excels.

### 🔨 Anvil Repair Cost Normalization
- In vanilla Minecraft, repeated anvil repairs rapidly accumulate the dreaded "Too Expensive!" lockout.
- Durability Multiplier integrates with anvil mechanics to keep repair costs linear and predictable, ensuring prized heirloom tools never become unrepairable.

---

## 📊 Durability Scaling Benchmark Matrix

| Tool / Item Tier | Vanilla Durability | With $3\times$ Multiplier | With $5\times$ Multiplier | With $10\times$ Multiplier |
| :--- | :---: | :---: | :---: | :---: |
| **Golden Pickaxe** | 32 uses | **96 uses** | **160 uses** | **320 uses** |
| **Iron Pickaxe** | 250 uses | **750 uses** | **1,250 uses** | **2,500 uses** |
| **Diamond Pickaxe** | 1,561 uses | **4,683 uses** | **7,805 uses** | **15,610 uses** |
| **Netherite Pickaxe** | 2,031 uses | **6,093 uses** | **10,155 uses** | **20,310 uses** |
| **Elytra (Flight Time)** | ~7.2 minutes | **~21.6 minutes** | **~36.0 minutes** | **~72.0 minutes** |
| **Shield (Blocks)** | 336 hits | **1,008 hits** | **1,680 hits** | **3,360 hits** |

---

## ⚙️ Native GameRules & Server Configuration

Configure durability dynamically in-game without server restarts:

| GameRule Key | Type | Default | Valid Range | Description |
| :--- | :---: | :---: | :---: | :--- |
| `durability_multiplier:global_multiplier` | `Double` | `3.0` | `1.0 – 100.0` | Global fallback multiplier applied to all damageable items. |
| `durability_multiplier:weapon_multiplier` | `Double` | `3.0` | `1.0 – 100.0` | Dedicated multiplier for swords, bows, maces, and tridents. |
| `durability_multiplier:tool_multiplier` | `Double` | `4.0` | `1.0 – 100.0` | Dedicated multiplier for pickaxes, shovels, and hoes. |
| `durability_multiplier:armor_multiplier` | `Double` | `2.5` | `1.0 – 100.0` | Dedicated multiplier for all worn armor pieces. |
| `durability_multiplier:elytra_multiplier` | `Double` | `5.0` | `1.0 – 100.0` | Dedicated multiplier for Elytra flight durability consumption. |
| `durability_multiplier:prevent_breaking` | `Boolean` | `false` | `true / false` | When enabled, tools refuse to break at 1 HP (safeguard mode). |

---

## 📖 In-Depth How-To & Gameplay Playbook

### Step 1: Server & Singleplayer Setup
1. Install **Fabric Loader** and **Fabric API** for Minecraft 26.2+ / 26.3+.
2. Drop `durability-multiplier-x.y.z+<version>.jar` into your `mods/` folder.
3. Launch your game or server. All tools immediately gain the active durability multiplier!

### Step 2: Customizing for Modpacks & Survival Balance
- For a balanced vanilla+ survival experience, we recommend:
  - Tools: `/gamerule durability_multiplier:tool_multiplier 3.0` (smooth quarrying without constant trips).
  - Elytra: `/gamerule durability_multiplier:elytra_multiplier 5.0` (long-range exploration).
  - Armor: `/gamerule durability_multiplier:armor_multiplier 2.0` (fair combat wear).
- For an RPG or builder pack, enable `/gamerule durability_multiplier:prevent_breaking true` so precious gear is never accidentally lost.

---

## ☕ Support & Creator Community

I am an independent solo developer creating lightweight, vanilla-enhancing mods that respect your time and game performance. If Durability Multiplier saves your gear, consider supporting future development:

<p align="center">
  <a href="https://ko-fi.com/rifaditya"><img src="https://img.shields.io/badge/Ko--fi-Support_on_Ko--fi-F16061?style=for-the-badge&logo=ko-fi&logoColor=white" alt="Support on Ko-fi"></a>
  <a href="https://sociabuzz.com/rifaditya"><img src="https://img.shields.io/badge/SocioBuzz-Support_Creator-00A651?style=for-the-badge" alt="Support on SocioBuzz"></a>
  <a href="https://saweria.co/rifaditya"><img src="https://img.shields.io/badge/Saweria-Support_Local-FFA500?style=for-the-badge" alt="Support on Saweria"></a>
</p>

> [!TIP]
> **🇮🇩 Indonesian Local Payment Note:** Indonesian supporters can also support my development work directly using local payment options (**GoPay, OVO, Dana, QRIS, LinkAja**) via **Saweria** or **SocioBuzz**!

Join our official Discord community for live development updates, early test builds, and friendly support:
- 💬 **Discord Community:** [https://discord.gg/EV99bgAFqb](https://discord.gg/EV99bgAFqb)

---

## 📜 Metadata & Permissions

| Property | Value |
| :--- | :--- |
| **Mod Name** | Durability Multiplier |
| **Namespace / Mod ID** | `durability_multiplier` |
| **License** | GNU General Public License v3.0 (GPLv3) |
| **Side Safety** | Server & Client (Server-Authoritative) |
| **Source Code** | [GitHub Repository](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier) |
| **Issue Tracker** | [GitHub Issues](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier/issues) |

> [!IMPORTANT]
> **📦 Modpack Permissions & Distribution:**<br>
> You are fully welcome to include this mod in any modpack on any platform! However, the mod file must be downloaded directly through official distribution channels (**Modrinth** or **CurseForge**). Re-uploading, mirroring, or redistributing the original mod JAR to third-party mirror sites, scraper portals, or unauthorized launchers is strictly prohibited.
> <br><br>
> **⚖️ License & Fork Guidelines (No Zero-Change Re-uploads):**<br>
> This project is open-source under the **GNU GPLv3**. You are fully encouraged to inspect the code, learn from it, and fork the repository to create genuine modifications, substantial feature expansions, or community ports—provided your project remains open-source under GPLv3 with proper attribution.<br>
> **However, straight 1:1 re-uploads, clone forks with no meaningful functional changes, or re-publishing identical builds under different project names (e.g. to farm downloads or rewards) are strictly forbidden.**

---

<div align="center">

**Made with ❤️ for the Minecraft community**

*Part of the Instant Gratification Collection*

</div>
