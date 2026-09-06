# API 및 애드온 통합 (26.1.2)

| 연동 인터페이스 | 패키지 / 클래스 경로 |
| :--- | :--- |
| **핵심 헬퍼** | `net.instantgratification.durabilitymultiplier.DurabilityHelper` |
| **게임 규칙 레지스트리** | `net.instantgratification.durabilitymultiplier.registry.DurabilityRules` |
| **클라이언트 상태** | `net.instantgratification.durabilitymultiplier.network.DurabilityClientState` |
| **동적 규칙** | `net.dasik.social.api.gamerule.DynamicGameRuleManager` |

---

## 🔌 다른 모드에서 DurabilityHelper 사용

다른 Fabric 모드는 모든 아이템 스택에 적용된 활성 내구도 배율을 조회할 수 있습니다:

```java
// Server-Side: Query effective durability percentage (e.g. 200 = 2x, 50 = 0.5x, -1 = single-use)
int percent = DurabilityHelper.getEffectivePercent(serverLevel, itemStack);

// Server-Side: Check if an item is currently unbreakable (God Mode)
boolean isGodMode = DurabilityHelper.isInfinite(serverLevel, itemStack);

// Server-Side: Check if an item is currently in Single-Use Glass Mode
boolean isGlassMode = DurabilityHelper.isSingleUse(serverLevel, itemStack);

// Server-Side: Reduce incoming durability damage amount using active rules
int finalDamage = DurabilityHelper.reduceDamage(originalDamage, serverLevel, itemStack);

// Client-Side: Query synced client state for tooltips / HUD
int clientPercent = DurabilityHelper.getEffectivePercentClient(itemStack);
boolean clientGodMode = DurabilityHelper.isInfiniteClient(itemStack);
boolean clientGlassMode = DurabilityHelper.isSingleUseClient(itemStack);
```

---

## 📦 커스텀 애드온 게임 규칙 등록

애드온 모드는 `DynamicGameRuleManager`를 사용하여 `DURABILITY_MULTIPLIER` 범주에 커스텀 규칙을 등록할 수 있습니다:

```java
GameRule<Integer> customRule = DynamicGameRuleManager.integerRule(
    "ig:custom_wand_percent", 
    DurabilityRules.DURABILITY_MULTIPLIER, 
    200
).name("Custom Wand Percent").min(-1).register();
```
