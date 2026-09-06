# ModVersionGuard 및 런타임 안전성 (26.2)

| 매개변수 | 값 |
| :--- | :--- |
| **가드 클래스** | `net.instantgratification.durabilitymultiplier.util.ModVersionGuard` |
| **호출 지점** | `DurabilityMultiplierFabric.onInitialize()` |
| **검사 대상 클래스** | `net.minecraft.world.entity.EntityTypes` (MC 26.2+ 식별자) |
| **클래스로더** | `Thread.currentThread().getContextClassLoader()` (Knot ClassLoader) |
| **목적** | 비호환 환경 실행 시 월드 손상 방지 |

---

## 🛡️ ModVersionGuard가 저장 데이터를 보호하는 방법

Modern Sovereign 시대의 마인크래프트는 빠른 속도로 API가 변경됩니다. MC 26.2용으로 빌드된 모드가 호환되지 않는 런타임에서 실행될 경우, 클래스로딩 오류로 인해 월드가 손상될 위험이 있습니다.

`ModVersionGuard`는 규칙, Mixin, 설정 로드에 앞서 `onInitialize()`에서 의존성 없는 사전 검사를 실행합니다:

```java
ModVersionGuard.checkClass("Durability Multiplier", "net.minecraft.world.entity.EntityTypes");
```

필요한 클래스가 Knot 클래스로더에 없으면 친절한 오류 배너를 띄우며 게임을 안전하게 즉시 중단합니다:

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
