# ModVersionGuard 与运行时安全 (26.2)

| 参数 | 取值 |
| :--- | :--- |
| **防护类** | `net.instantgratification.durabilitymultiplier.util.ModVersionGuard` |
| **调用时机** | `DurabilityMultiplierFabric.onInitialize()` |
| **检查的目标类** | `net.minecraft.world.entity.EntityTypes` (MC 26.2+ 关键标识类) |
| **类加载器** | `Thread.currentThread().getContextClassLoader()` (Knot ClassLoader) |
| **设计目的** | 防止在不兼容环境中加载导致世界存档损坏 |

---

## 🛡️ ModVersionGuard 如何保护存档

在现代 Sovereign 纪元中，Minecraft 的更新换代伴随着快速的 API 演进。如果将专为 MC 26.2 编译的模组错误地运行在旧版本或不兼容的环境中，静默类加载错误可能会导致物品数据损坏或世界存档受损。

`ModVersionGuard` 在 `onInitialize()` 阶段于任何游戏规则、Mixin 或配置初始化之前执行零依赖预检：

```java
ModVersionGuard.checkClass("Durability Multiplier", "net.minecraft.world.entity.EntityTypes");
```

若 Knot 类加载器中缺少所需目标类，游戏将立即安全终止并显示详尽的崩溃提示横幅：

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
