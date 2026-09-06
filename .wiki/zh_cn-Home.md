# Durability Multiplier 官方维基

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

欢迎查阅由 **Dasik (Rifaditya)** 打造的 **Durability Multiplier**（即时满足系列）官方技术与游戏玩法维基文档。

> 📌 **仓库源码声明**：本 Wiki 中的文档反映了**仓库中的当前源代码状态**，可能包含领先于 CurseForge 和 Modrinth 上公开发布版本的最新未发布提交或开发中功能。

---

## 🧭 多版本切换总门户

Durability Multiplier 跨越多个专享的 Minecraft 发布锚点精心打造。请在下方选择你的目标 Minecraft 版本，以进入其专属、独立的文档中心：

| Minecraft 版本 | 发布纪元 | 支持的构建版本 | Java 级别 | Loom 工具链 | 维基入口 |
| :--- | :--- | :--- | :---: | :---: | :--- |
| **Minecraft 26.2** | 现代 Sovereign 纪元 | `1.2.14+26.2` | Java 25 | Loom 1.15.2 | [[👉 进入 MC 26.2 维基\|zh_cn-26.2-Home]] |
| **Minecraft 26.1.2** | 现代 Sovereign 纪元 | `1.1.21+26.1.2` | Java 25 | Loom 1.15.2 | [[👉 进入 MC 26.1.2 维基\|zh_cn-26.1.2-Home]] |

---

## ⚡ 核心理念与架构

Durability Multiplier 属于**即时满足 (Instant Gratification, IG)** 设计路线。其核心使命是消除 Minecraft 原版生存中的**“装备维护内耗”**：

* **尊重玩家的时间**: 消除繁琐的工具修理循环、挖矿中断和意外的装备碎裂。
* **纯粹数学伤害减免**: 耐久度延长通过对传入损耗进行整数除法和概率舍入计算，确保数百万次命中中的数学精度，绝不覆盖原版物品属性。
* **细粒度控制**: 跨 73 项静态游戏规则独立配置 24 个独立物品类别（剑类、长矛、三叉戟、重锤、弓、弩、盾牌、工具、镐类、斧类、锹类、锄类、剪刀、钓鱼竿、刷子、打火石、护甲、头盔、胸甲、护腿、靴子、鞘翅、武器、全局）。
* **上帝模式（无限耐久）**: 仅需一条布尔游戏规则即可使任何或全部类别获得 100% 无法破坏效果。
* **自动模组物品检测**: 在注册表冻结期间发现可损耗的模组物品，并动态公开专属的游戏规则与图形界面控件。
* **零端侧不同步**: 服务端游戏规则通过自定义 Fabric 网络信道 (`durability-multiplier:sync_rules`) 实时同步给连接的客户端，提供即时的提示框指示器。

---

## 📚 全局导航与资源

* [[版本兼容性与生命周期矩阵|zh_cn-Version-Compatibility]]
* [[MC 26.2 技术文档中心|zh_cn-26.2-Home]]
* [[MC 26.1.2 技术文档中心|zh_cn-26.1.2-Home]]
* [CurseForge 平台页面](https://curseforge.com/minecraft/mc-mods/durability-multiplier)
* [Modrinth 平台页面](https://modrinth.com/mod/durability-multiplier)
* [GitHub 源码仓库](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier)
