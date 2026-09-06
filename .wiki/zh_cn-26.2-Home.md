# Durability Multiplier — Minecraft 26.2 文档中心

欢迎查阅 **Minecraft 26.2** (`1.2.14+26.2`) 平台的 **Durability Multiplier** 专属技术文档中心。

> 📌 **仓库源码声明**：本 Wiki 中的文档反映了**仓库中的当前源代码状态**，可能包含领先于 CurseForge 和 Modrinth 上公开发布版本的最新未发布提交或开发中功能。

---

## 📋 技术规格快照 (26.2)

| 参数属性 | 规格取值 | 详细说明 |
| :--- | :--- | :--- |
| **模组标识符** | `durability-multiplier` | Fabric Loader 中的命名空间模组 ID |
| **模组版本** | `1.2.14+26.2` | SemVer 规范发布版本标签 |
| **目标 Minecraft** | `26.2` (`>=26.2-`) | 原生针对版本锚点 |
| **Java 版本** | Java 25 | 使用 `release = 25` 编译构建 |
| **Fabric Loader** | `>=0.16.9` | 最低加载器版本需求 |
| **Fabric API** | `0.150.1+26.2` | Fabric API 运行时依赖版本 |
| **DasikLibrary** | `1.8.28` | 共享基础架构核心库 |
| **已注册游戏规则** | **73 项静态规则** + 动态模组规则 | 24 个百分比、24 个上帝模式、24 个单次使用、1 个提示框 |
| **Mixin 注入目标** | 3 个目标类 | `ItemStack`, `GameRules` |
| **作者与开源协议** | **Dasik (Rifaditya)** / GPL-3.0-or-later | 开源自由软件许可 |

---

## 🧭 导航矩阵 (26.2)

### 🎮 玩家与游戏玩法指南
* [[耐久度倍率与百分比|zh_cn-26.2-Durability-Multipliers]] — 细粒度 24 分类百分比体系与覆盖层级。
* [[上帝模式与无限耐久|zh_cn-26.2-God-Mode-and-Infinity]] — 覆盖 24 个分类的零伤害无敌开关。
* [[伤害减免与概率数学|zh_cn-26.2-Damage-Reduction-and-Probability-Math]] — 数学公式与概率舍入机制。
* [[物品分类与模组兼容性|zh_cn-26.2-Item-Classification-and-Mod-Compatibility]] — 原版与模组物品的分类匹配准则。
* [[动态模组物品注册|zh_cn-26.2-Dynamic-Modded-Item-Registration]] — 通用 3 级发现扫描器与自动填充系统。
* [[提示框指示器与 HUD|zh_cn-26.2-Tooltip-Indicators-and-HUD]] — 客户端物品提示框渲染。
* [[游戏规则 GameRules 参考|zh_cn-26.2-GameRules]] — 包含全部 73 项静态游戏规则的详尽参考表。
* [[指令与管理|zh_cn-26.2-Commands-and-Administration]] — 在游戏内通过 `/gamerule` 管理配置。
* [[进度与成就|zh_cn-26.2-Advancements]] — 缺省政策与原版进度集成。
* [[配置与 GUI 界面集成|zh_cn-26.2-Configuration]] — ModMenu 与 Cloth Config 界面集成。
* [[ModVersionGuard 与运行时安全|zh_cn-26.2-ModVersionGuard-and-Safety]] — 零依赖版本保护机制。
* [[常见问题与故障排除|zh_cn-26.2-Troubleshooting-and-FAQ]] — 诊断流程与常见问题解答。

### 💻 开发者与技术参考
* [[架构与 Mixin 描述符|zh_cn-26.2-Architecture-and-Mixins]] — 包层次结构、注入挂钩与重入安全性。
* [[网络同步与负载协议|zh_cn-26.2-Network-Sync-and-Payload-Protocol]] — 服务端至客户端同步协议 (`DurabilityPayload`)。
* [[开发者环境搭建与构建|zh_cn-26.2-Developer-Setup-and-Building]] — Gradle 指令、Loom 工具链及 JDK 环境。
* [[API 与附属模组集成|zh_cn-26.2-API-and-Addon-Integration]] — 拓展模组功能、`DurabilityHelper` 与自定义规则。
