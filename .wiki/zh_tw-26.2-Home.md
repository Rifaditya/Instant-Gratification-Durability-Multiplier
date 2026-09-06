# Durability Multiplier — Minecraft 26.2 文件中心

欢迎查阅 **Minecraft 26.2** (`1.2.14+26.2`) 平台的 **Durability Multiplier** 专屬技术文檔中心。

> 📌 **仓庫源碼聲明**：本 Wiki 中的文檔反映了**仓庫中的当前源代碼状態**，可能封包含领先于 CurseForge 和 Modrinth 上公開發布版本的最新未發布提交或開發中功能。

---

## 📋 技術規格快照 (26.2)

| 參數屬性 | 規格取值 | 詳細說明 |
| :--- | :--- | :--- |
| **模組標識符** | `durability-multiplier` | Fabric Loader 中的命名空间模組 ID |
| **模組版本** | `1.2.14+26.2` | SemVer 規范發布版本標籤 |
| **目標 Minecraft** | `26.2` (`>=26.2-`) | 原生针对版本锚點 |
| **Java 版本** | Java 25 | 使用 `release = 25` 编译構建 |
| **Fabric Loader** | `>=0.16.9` | 最低加載器版本需求 |
| **Fabric API** | `0.150.1+26.2` | Fabric API 运行時依赖版本 |
| **DasikLibrary** | `1.8.28` | 共享基础架構核心庫 |
| **已注册游戏規則** | **73 項靜態規則** + 動態模組規則 | 24 个百分比、24 个上帝模式、24 个單次使用、1 个提示框 |
| **Mixin 注入目標** | 3 个目標類 | `ItemStack`, `GameRules` |
| **作者与開源協议** | **Dasik (Rifaditya)** / GPL-3.0-or-later | 開源自由軟件许可 |

---

## 🧭 導航矩陣 (26.2)

### 🎮 玩家與遊戲玩法指南
* [[耐久度倍率与百分比|zh_tw-26.2-Durability-Multipliers]] — 細粒度 24 分類百分比體系与覆蓋层級。
* [[上帝模式与無限耐久|zh_tw-26.2-God-Mode-and-Infinity]] — 覆蓋 24 个分類的零伤害無敌開關。
* [[伤害减免与概率數学|zh_tw-26.2-Damage-Reduction-and-Probability-Math]] — 數学公式与概率舍入機制。
* [[物品分類与模組兼容性|zh_tw-26.2-Item-Classification-and-Mod-Compatibility]] — 原版与模組物品的分類匹配准則。
* [[動態模組物品注册|zh_tw-26.2-Dynamic-Modded-Item-Registration]] — 通用 3 級發现扫描器与自動填充系统。
* [[提示框指示器与 HUD|zh_tw-26.2-Tooltip-Indicators-and-HUD]] — 客戶端物品提示框渲染。
* [[游戏規則 GameRules 參考|zh_tw-26.2-GameRules]] — 封包含全部 73 項靜態游戏規則的詳尽參考表。
* [[指令与管理|zh_tw-26.2-Commands-and-Administration]] — 在游戏内通过 `/gamerule` 管理配置。
* [[進度与成就|zh_tw-26.2-Advancements]] — 缺省政策与原版進度集成。
* [[配置与 GUI 界面集成|zh_tw-26.2-Configuration]] — ModMenu 与 Cloth Config 界面集成。
* [[ModVersionGuard 与运行時安全|zh_tw-26.2-ModVersionGuard-and-Safety]] — 零依赖版本保護機制。
* [[常見問題与故障排除|zh_tw-26.2-Troubleshooting-and-FAQ]] — 诊断流程与常見問題解答。

### 💻 開發者與技術參考
* [[架構与 Mixin 描述符|zh_tw-26.2-Architecture-and-Mixins]] — 封包层次結構、注入挂钩与重入安全性。
* [[網絡同步与負載協议|zh_tw-26.2-Network-Sync-and-Payload-Protocol]] — 服务端至客戶端同步協议 (`DurabilityPayload`)。
* [[開發者環境搭建与構建|zh_tw-26.2-Developer-Setup-and-Building]] — Gradle 指令、Loom 工具鏈及 JDK 環境。
* [[API 与附屬模組集成|zh_tw-26.2-API-and-Addon-Integration]] — 拓展模組功能、`DurabilityHelper` 与自定义規則。
