# 版本兼容性与生命周期矩阵

| 技术规格 | Minecraft 26.2 锚点 | Minecraft 26.1.2 锚点 |
| :--- | :--- | :--- |
| **目标 Minecraft 版本** | `26.2` (`>=26.2-`) | `26.1.2` (`*`) |
| **模组版本 (SemVer)** | `1.2.14+26.2` | `1.1.21+26.1.2` |
| **Java 工具链** | Java 25 (`release = 25`) | Java 25 (`release = 25`) |
| **Fabric Loader** | `>=0.16.9` (构建于 `0.19.1`) | `>=0.16.9` (构建于 `0.19.1`) |
| **Fabric API** | `0.150.1+26.2` | `0.145.4+26.1.2` |
| **Fabric Loom** | `1.15.2` | `1.15.2` |
| **DasikLibrary 依赖** | `1.8.28` | `1.8.28` |
| **Cloth Config 依赖** | `26.1.154` (可选) | `26.1.154` (可选) |
| **ModMenu 依赖** | `18.0.0-beta.1` (可选) | `18.0.0-beta.1` (可选) |
| **ModVersionGuard 防护** | ✅ 生效 (`EntityTypes` 类检查) | 标准运行时 |
| **专属维基中心** | [[👉 打开 MC 26.2 中心\|zh_cn-26.2-Home]] | [[👉 打开 MC 26.1.2 中心\|zh_cn-26.1.2-Home]] |

---

## 🏛️ 时代架构与“一罐一版本”铁律

Durability Multiplier 严格遵循**一罐一版本 (1 Jar 1 Version)** 的架构规范：
1. 每个 Minecraft 大版本均拥有专属的独立子项目目录 (`Durability Multiplier v26.1/`, `Durability Multiplier v26.2/`)。
2. 发布产物分别独立编译为带版本标签的 JAR 文件 (`durability-multiplier-1.1.21+26.1.2.jar`, `durability-multiplier-1.2.14+26.2.jar`)，并在 `Archive Jar of all versions/` 中集中归档。
3. MC 26.2 在 `onInitialize()` 中引入了零依赖的 `ModVersionGuard` 运行时版本校验，在不兼容的运行环境中能安全中止运行，守护玩家世界存档免遭损坏。

---

> 📌 **仓库源码声明**：本 Wiki 中的文档反映了**仓库中的当前源代码状态**，可能包含领先于 CurseForge 和 Modrinth 上公开发布版本的最新未发布提交或开发中功能。
