# Durability Multiplier — Minecraft 26.1.2 Documentation Hub

Welcome to the dedicated documentation suite for **Durability Multiplier** on **Minecraft 26.1.2** (`1.1.21+26.1.2`).

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📋 Technical Snapshot (26.1.2)

| Parameter | Value | Description |
| :--- | :--- | :--- |
| **Mod Identifier** | `durability-multiplier` | Namespaced mod ID in Fabric Loader |
| **Mod Version** | `1.1.21+26.1.2` | SemVer release tag |
| **Target Minecraft** | `26.1.2` (`*`) | Native version anchor |
| **Java Release** | Java 25 | Compiled with `release = 25` |
| **Fabric Loader** | `>=0.16.9` | Minimum loader requirement |
| **Fabric API** | `0.145.4+26.1.2` | Fabric API runtime requirement |
| **DasikLibrary** | `1.8.28` | Shared architecture core |
| **Registered GameRules** | **73 Static Rules** + Dynamic Mod Rules | 24 Percentages, 24 Infinities, 24 Single-Uses, 1 Tooltip |
| **Mixin Injection Points** | 3 Target Classes | `ItemStack`, `GameRules` |
| **Author & License** | **Dasik (Rifaditya)** / GPL-3.0-or-later | Open-source modification |

---

## 🧭 Navigation Matrix (26.1.2)

### 🎮 Player & Gameplay Guides
* [[Durability Multipliers & Categories|Durability-Multipliers]] — Granular 24-category percentage system and override hierarchy.
* [[God Mode & Infinity|God-Mode-and-Infinity]] — Zero-damage invincibility toggles across 24 categories.
* [[Damage Reduction Math & Probabilities|Damage-Reduction-and-Probability-Math]] — Mathematical formulas and probabilistic rounding.
* [[Item Classification & Modded Compatibility|Item-Classification-and-Mod-Compatibility]] — How vanilla and modded items are classified.
* [[Dynamic Modded Item Scanning|Dynamic-Modded-Item-Registration]] — Universal 3-tier discovery scanner and auto-population.
* [[Tooltip Indicators & HUD|Tooltip-Indicators-and-HUD]] — Sided item tooltip rendering.
* [[GameRules Reference Table|GameRules]] — Exhaustive reference table for all 73 static GameRules.
* [[Commands & In-Game Administration|Commands-and-Administration]] — Managing settings in-game via `/gamerule`.
* [[Advancements & Achievements|Advancements]] — Absence policy and vanilla integration.
* [[Configuration GUI & World Defaults|Configuration]] — ModMenu & Cloth Config integration.

* [[Troubleshooting & FAQ|Troubleshooting-and-FAQ]] — Diagnostic procedures and common questions.

### 💻 Developer & Technical Reference
* [[Architecture & Mixin Descriptors|Architecture-and-Mixins]] — Package hierarchy, injection hooks, and re-entry safety.
* [[Network Sync & Payload Protocol|Network-Sync-and-Payload-Protocol]] — S2C synchronization protocol (`DurabilityPayload`).
* [[Developer Setup & Building|Developer-Setup-and-Building]] — Gradle commands, Loom toolchain, and JDK setup.
* [[API & Addon Integration|API-and-Addon-Integration]] — Extending the mod, `DurabilityHelper`, and custom rules.
