# 아키텍처 및 믹스인 디스크립터 (26.1.2)

| 아키텍처 속성 | 값 |
| :--- | :--- |
| **루트 패키지** | `net.instantgratification.durabilitymultiplier` |
| **호환성 레벨** | `JAVA_25` |
| **Mixin 설정** | `durability-multiplier.mixins.json` |
| **기본 인젝터 필수 수** | `1` |
| **재진입 방지 가드** | `ThreadLocal<Boolean> dm$processing` |

---

## 🌳 ASCII 패키지 계층 구조

```
net.instantgratification.durabilitymultiplier/
├── DurabilityHelper.java               # Stateless damage reduction & classification engine
├── DurabilityMultiplier.java           # Common mod initializer & logger
├── DurabilityMultiplierFabric.java     # Fabric main entrypoint
├── DurabilityMultiplierFabricClient.java # Client entrypoint & network receiver
├── config/
│   ├── ClothConfigScreenHelper.java    # Optional Cloth Config GUI builder
│   ├── DurabilityConfig.java           # JSON configuration loader & save handler
│   └── ModMenuIntegration.java         # ModMenu entrypoint hook
├── mixin/
│   ├── GameRulesMixin.java             # Intercepts GameRules.set() for network sync
│   ├── ItemStackDurabilityMixin.java   # Intercepts hurtAndBreak() for damage reduction
│   └── ItemStackTooltipMixin.java      # Intercepts addDetailsToTooltip() for status lines
├── network/
│   ├── DurabilityClientState.java      # Client-side cache of 73 GameRules & dynamic mod rules
│   ├── DurabilityNetworking.java       # S2C packet registration & player sync logic
│   └── DurabilityPayload.java          # CustomPacketPayload record definition
└── registry/
    └── DurabilityRules.java            # GameRuleCategory & 73 GameRules definitions
```

---

## 💉 완전한 믹스인 대상 분석

### 1. `ItemStackDurabilityMixin`
* **대상 클래스**: `net.minecraft.world.item.ItemStack`
* **대상 메서드**: `hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V`
* **주입 지점**: `@At("HEAD")`, `cancellable = true`
* **핸들러 메서드**: `dm$hurtAndBreak(int amount, ServerLevel level, @Nullable ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci)`
* **설계 의도**: 모든 아이템 피해의 단일 집약점. 줄어든 피해로 `hurtAndBreak`를 재호출할 때의 무한 루프를 방지하기 위해 `ThreadLocal<Boolean> dm$processing`으로 재진입을 방지합니다.

### 2. `ItemStackTooltipMixin`
* **대상 클래스**: `net.minecraft.world.item.ItemStack`
* **대상 메서드**: `addDetailsToTooltip(Item.TooltipContext, TooltipDisplay, Player, TooltipFlag, Consumer<Component>)V`
* **주입 지점**: `@At("TAIL")`
* **핸들러 메서드**: `dm$addDurabilityTooltip(...)`
* **설계 의도**: 마우스 호버 툴팁에 금색 볼드체 `✦ UNBREAKABLE`, 회색 `⟨SINGLE-USE⟩` 또는 회색 `⟨Nx 범주 내구도⟩` / `⟨P% 범주 내구도⟩`를 추가합니다.

### 3. `GameRulesMixin`
* **대상 클래스**: `net.minecraft.world.level.gamerules.GameRules`
* **대상 메서드**: `set(GameRule<T>, T, MinecraftServer)V`
* **주입 지점**: `@At("TAIL")`
* **핸들러 메서드**: `onSet(GameRule<T> key, T value, @Nullable MinecraftServer server, CallbackInfo ci)`
* **설계 의도**: `DURABILITY_MULTIPLIER` 범주의 규칙이 변경되면 `DurabilityNetworking.syncToAll(server)`를 호출하여 업데이트된 값을 실시간 동기화합니다.

---

## 🔄 동적 모드 아이템 레지스트리 검색

동적 아이템 등록은 **`DasikLibrary`**의 `DynamicRegistryScanner`로 구현됩니다:
* **후크 메서드**: `DynamicRegistryScanner.subscribe(BuiltInRegistries.ITEM, DurabilityRules::isItemDamageable, (id, item) -> { ... })`
* **수명 주기**: 범용 3단계 탐색 스캐너 (시작 시 스캔, 외부 모드 로딩 중 실시간 콜백, 서버 시작 시 안전 스캔).
* **커스텀 레지스트리 Mixin 배제**: 수동 레지스트리 Mixin 대신 충돌 없는 안전한 표준 이벤트 콜백을 사용합니다.
