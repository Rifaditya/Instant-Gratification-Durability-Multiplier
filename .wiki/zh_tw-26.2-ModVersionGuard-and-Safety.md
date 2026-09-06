# ModVersionGuard 與執行時期安全 (26.2)

| 參數 | 取值 |
| :--- | :--- |
| **防護類** | `net.instantgratification.durabilitymultiplier.util.ModVersionGuard` |
| **調用時機** | `DurabilityMultiplierFabric.onInitialize()` |
| **检查的目標類** | `net.minecraft.world.entity.EntityTypes` (MC 26.2+ 關键標識類) |
| **類加載器** | `Thread.currentThread().getContextClassLoader()` (Knot ClassLoader) |
| **設計目的** | 防止在不兼容環境中加載导致世界存檔損壞 |

---

## 🛡️ ModVersionGuard 如何保護存檔

在现代 Sovereign 紀元中，Minecraft 的更新換代伴隨着快速的 API 演進。如果将专為 MC 26.2 编译的模組错误地运行在旧版本或不兼容的環境中，靜默類加載错误可能会导致物品數據損壞或世界存檔受損。

`ModVersionGuard` 在 `onInitialize()` 阶段于任何游戏規則、Mixin 或配置初始化之前執行零依赖預检：

```java
ModVersionGuard.checkClass("Durability Multiplier", "net.minecraft.world.entity.EntityTypes");
```

若 Knot 類加載器中缺少所需目標類，游戏将立即安全终止并顯示詳尽的崩溃提示横幅：

```
=====================================================================
 [PRE-RELEASE / VERSION GUARD WARNING] Durability Multiplier
---------------------------------------------------------------------
 CRITICAL: Incompatible Minecraft Game Runtime or Missing Class!
 Required Class : net.minecraft.world.entity.EntityTypes
 Status         : UNRESOLVED AT RUNTIME

 Safety Protection:
 Execution halted to prevent unreleased/incompatible build deployment
 or broken world state save corruption.

 Troubleshooting Steps:
 1. Verify target Minecraft version (26.2+ release drop).
 2. Ensure all required dependencies (Fabric API, DasikLibrary) are loaded.
 3. Build/Download a verified matching release JAR from Modrinth/CurseForge.
=====================================================================
```
