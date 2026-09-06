# ModVersionGuardと実行時の安全性 (26.2)

| パラメータ | 設定値 |
| :--- | :--- |
| **ガードクラス** | `net.instantgratification.durabilitymultiplier.util.ModVersionGuard` |
| **呼び出し箇所** | `DurabilityMultiplierFabric.onInitialize()` |
| **検査対象クラス** | `net.minecraft.world.entity.EntityTypes` (MC 26.2+ 指標) |
| **クラスローダー** | `Thread.currentThread().getContextClassLoader()` (Knot ClassLoader) |
| **目的** | 非互換環境での起動によるワールド破損を防止 |

---

## 🛡️ ModVersionGuardがセーブデータを保護する仕組み

Modern Sovereign時代のMinecraftは急速なAPI更新が行われます。MC 26.2向けにビルドされたModが非互換環境で実行された場合、クラスローディングの失敗によりセーブデータが破損するリスクがあります。

`ModVersionGuard`は、ルールやMixin、設定の読み込みに先立って`onInitialize()`で依存関係なしの事前検査を実行します：

```java
ModVersionGuard.checkClass("Durability Multiplier", "net.minecraft.world.entity.EntityTypes");
```

必要なクラスがKnotクラスローダーに存在しない場合、親切なエラーバナーを表示してゲームを安全に即時停止させます：

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
