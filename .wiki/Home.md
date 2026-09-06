# Durability Multiplier Wiki

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

Welcome to the official technical and gameplay documentation for **Durability Multiplier** (Instant Gratification Collection), engineered by **Dasik (Rifaditya)**.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🧭 Multi-Version Switchboard Portal

Durability Multiplier is engineered across dedicated Minecraft release anchors. Select your targeted Minecraft version below to enter its dedicated, isolated documentation suite:

| Minecraft Version | Release Era | Supported Build | Java Level | Loom Toolchain | Wiki Entry |
| :--- | :--- | :--- | :---: | :---: | :--- |
| **Minecraft 26.2** | Modern Sovereign Era | `1.2.14+26.2` | Java 25 | Loom 1.15.2 | [[👉 Enter MC 26.2 Wiki\|26.2-Home]] |
| **Minecraft 26.1.2** | Modern Sovereign Era | `1.1.21+26.1.2` | Java 25 | Loom 1.15.2 | [[👉 Enter MC 26.1.2 Wiki\|26.1.2-Home]] |

---

## ⚡ Core Philosophy & Architecture

Durability Multiplier belongs to the **Instant Gratification (IG)** design track. Its sole mandate is to eliminate **"Maintenance Friction"** in Minecraft survival:

* **Respect the Player's Time**: Eliminate tedious tool repair loops, mining stops, and accidental gear breakage.
* **Pure Mathematical Damage Reduction**: Durability extension is calculated via integer division and probabilistic rounding on incoming damage, ensuring mathematical precision over millions of hits without overriding vanilla item attributes.
* **Granular Control**: Configure 24 individual item categories (Swords, Spears, Tridents, Maces, Bows, Crossbows, Shields, Tools, Pickaxes, Axes, Shovels, Hoes, Shears, Fishing Rods, Brushes, Flint and Steel, Armor, Helmets, Chestplates, Leggings, Boots, Elytra, Weapons, Global) independently across 73 static GameRules.
* **God Mode (Infinity)**: Make any or all categories 100% unbreakable with a single boolean GameRule.
* **Automatic Modded Item Detection**: Discovers modded damageable items during registry freeze and dynamically exposes dedicated GameRules and GUI controls.
* **Zero Sided De-synchronization**: Server GameRules are synced to connecting clients via custom Fabric networking (`durability-multiplier:sync_rules`) for instant, live tooltip indicators.

---

## 📚 Global Navigation & Resources

* [[Version Compatibility Matrix|Version-Compatibility]]
* [[MC 26.2 Documentation Hub|26.2-Home]]
* [[MC 26.1.2 Documentation Hub|26.1.2-Home]]
* [CurseForge Platform Page](https://curseforge.com/minecraft/mc-mods/durability-multiplier)
* [Modrinth Platform Page](https://modrinth.com/mod/durability-multiplier)
* [GitHub Source Repository](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier)
