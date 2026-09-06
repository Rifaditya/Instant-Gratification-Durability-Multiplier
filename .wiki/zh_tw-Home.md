# Durability Multiplier 官方維基

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

欢迎查阅由 **Dasik (Rifaditya)** 打造的 **Durability Multiplier**（即時满足系列）官方技术与游戏玩法维基文檔。

> 📌 **仓庫源碼聲明**：本 Wiki 中的文檔反映了**仓庫中的当前源代碼状態**，可能封包含领先于 CurseForge 和 Modrinth 上公開發布版本的最新未發布提交或開發中功能。

---

## 🧭 多版本切換總門戶

Durability Multiplier 跨越多个专享的 Minecraft 發布锚點精心打造。请在下方选择你的目標 Minecraft 版本，以進入其专屬、獨立的文檔中心：

| Minecraft 版本 | 發布紀元 | 支持的構建版本 | Java 級别 | Loom 工具鏈 | 维基入口 |
| :--- | :--- | :--- | :---: | :---: | :--- |
| **Minecraft 26.2** | 现代 Sovereign 紀元 | `1.2.14+26.2` | Java 25 | Loom 1.15.2 | [[👉 進入 MC 26.2 维基\|zh_tw-26.2-Home]] |
| **Minecraft 26.1.2** | 现代 Sovereign 紀元 | `1.1.21+26.1.2` | Java 25 | Loom 1.15.2 | [[👉 進入 MC 26.1.2 维基\|zh_tw-26.1.2-Home]] |

---

## ⚡ 核心理念與架構

Durability Multiplier 屬于**即時满足 (Instant Gratification, IG)** 設計路线。其核心使命是消除 Minecraft 原版生存中的**“裝備维護内耗”**：

* **尊重玩家的時间**: 消除繁琐的工具修理循環、挖矿中断和意外的裝備碎裂。
* **纯粹數学伤害减免**: 耐久度延长通过对传入損耗進行整數除法和概率舍入計算，確保數百万次命中中的數学精度，绝不覆蓋原版物品屬性。
* **細粒度控制**: 跨 73 項靜態游戏規則獨立配置 24 个獨立物品類别（劍類、长矛、三叉戟、重鎚、弓、弩、盾牌、工具、鎬類、斧類、鍬類、鋤類、剪刀、釣鱼竿、刷子、打火石、護甲、頭盔、胸甲、護腿、靴子、鞘翅、武器、全局）。
* **上帝模式（無限耐久）**: 仅需一條布尔游戏規則即可使任何或全部類别获得 100% 無法破壞效果。
* **自動模組物品检測**: 在注册表冻結期间發现可損耗的模組物品，并動態公開专屬的游戏規則与图形界面控件。
* **零端侧不同步**: 服务端游戏規則通过自定义 Fabric 網絡信道 (`durability-multiplier:sync_rules`) 實時同步给連接的客戶端，提供即時的提示框指示器。

---

## 📚 全局導航與資源

* [[版本兼容性与生命周期矩陣|zh_tw-Version-Compatibility]]
* [[MC 26.2 技术文檔中心|zh_tw-26.2-Home]]
* [[MC 26.1.2 技术文檔中心|zh_tw-26.1.2-Home]]
* [CurseForge 平台页面](https://curseforge.com/minecraft/mc-mods/durability-multiplier)
* [Modrinth 平台页面](https://modrinth.com/mod/durability-multiplier)
* [GitHub 源碼仓庫](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier)
