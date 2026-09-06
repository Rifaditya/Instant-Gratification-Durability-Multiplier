# 版本相容性與生命週期矩陣

| 技术規格 | Minecraft 26.2 锚點 | Minecraft 26.1.2 锚點 |
| :--- | :--- | :--- |
| **目標 Minecraft 版本** | `26.2` (`>=26.2-`) | `26.1.2` (`*`) |
| **模組版本 (SemVer)** | `1.2.14+26.2` | `1.1.21+26.1.2` |
| **Java 工具鏈** | Java 25 (`release = 25`) | Java 25 (`release = 25`) |
| **Fabric Loader** | `>=0.16.9` (構建于 `0.19.1`) | `>=0.16.9` (構建于 `0.19.1`) |
| **Fabric API** | `0.150.1+26.2` | `0.145.4+26.1.2` |
| **Fabric Loom** | `1.15.2` | `1.15.2` |
| **DasikLibrary 依赖** | `1.8.28` | `1.8.28` |
| **Cloth Config 依赖** | `26.1.154` (可选) | `26.1.154` (可选) |
| **ModMenu 依赖** | `18.0.0-beta.1` (可选) | `18.0.0-beta.1` (可选) |
| **ModVersionGuard 防護** | ✅ 生效 (`EntityTypes` 類检查) | 標准运行時 |
| **专屬维基中心** | [[👉 打開 MC 26.2 中心\|zh_tw-26.2-Home]] | [[👉 打開 MC 26.1.2 中心\|zh_tw-26.1.2-Home]] |

---

## 🏛️ 時代架構與「一罐一版本」鐵律

Durability Multiplier 严格遵循**一罐一版本 (1 Jar 1 Version)** 的架構規范：
1. 每个 Minecraft 大版本均拥有专屬的獨立子項目目錄 (`Durability Multiplier v26.1/`, `Durability Multiplier v26.2/`)。
2. 發布产物分别獨立编译為带版本標籤的 JAR 文件 (`durability-multiplier-1.1.21+26.1.2.jar`, `durability-multiplier-1.2.14+26.2.jar`)，并在 `Archive Jar of all versions/` 中集中归檔。
3. MC 26.2 在 `onInitialize()` 中引入了零依赖的 `ModVersionGuard` 运行時版本校驗，在不兼容的运行環境中能安全中止运行，守護玩家世界存檔免遭損壞。

---

> 📌 **仓庫源碼聲明**：本 Wiki 中的文檔反映了**仓庫中的当前源代碼状態**，可能封包含领先于 CurseForge 和 Modrinth 上公開發布版本的最新未發布提交或開發中功能。
