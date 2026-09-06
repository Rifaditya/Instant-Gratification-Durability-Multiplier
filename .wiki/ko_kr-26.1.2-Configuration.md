# 구성 및 GUI 통합 (26.1.2)

| 시스템 매개변수 | 설정값 |
| :--- | :--- |
| **설정 파일 경로** | `config/durability-multiplier.json` |
| **설정 버전** | `2` (v1에서 자동 마이그레이션) |
| **GUI 제공자** | Cloth Config (`me.shedaniel.cloth:cloth-config-fabric`) & ModMenu |
| **설정 클래스** | `net.instantgratification.durabilitymultiplier.config.DurabilityConfig` |
| **GUI 헬퍼** | `ClothConfigScreenHelper` & `ModMenuIntegration` |
| **우선순위의 법칙** | 설정 파일은 **새 월드의 기본값만 정의**하며, 기존 월드는 GameRules 사용 |

---

## ⚙️ 구성 파일 구조 (`config/durability-multiplier.json`)

설정 파일은 새로 생성되는 모든 싱글플레이 월드 및 멀티플레이 서버의 기본값을 정의합니다. 내구도 배율, 신 모드 (무한), 1회용 (유리 모드), 툴팁 서식, 동적 모드 아이템 재정의를 지원합니다.

```json
{
  "configVersion": 2,
  
  "percentGlobal": 200,
  "percentWeapons": 0,
  "percentSwords": 0,
  "percentSpears": 0,
  "percentTridents": 0,
  "percentMaces": 0,
  "percentBows": 0,
  "percentCrossbows": 0,
  "percentTools": 0,
  "percentPickaxes": 0,
  "percentAxes": 0,
  "percentShovels": 0,
  "percentHoes": 0,
  "percentShears": 0,
  "percentFishingRods": 0,
  "percentBrushes": 0,
  "percentFlintAndSteel": 0,
  "percentArmor": 0,
  "percentHelmets": 0,
  "percentChestplates": 0,
  "percentLeggings": 0,
  "percentBoots": 0,
  "percentElytra": 0,
  "percentShields": 0,
  
  "infinityGlobal": false,
  "infinityWeapons": false,
  "infinitySwords": false,
  "infinitySpears": false,
  "infinityTridents": false,
  "infinityMaces": false,
  "infinityBows": false,
  "infinityCrossbows": false,
  "infinityTools": false,
  "infinityPickaxes": false,
  "infinityAxes": false,
  "infinityShovels": false,
  "infinityHoes": false,
  "infinityShears": false,
  "infinityFishingRods": false,
  "infinityBrushes": false,
  "infinityFlintAndSteel": false,
  "infinityArmor": false,
  "infinityHelmets": false,
  "infinityChestplates": false,
  "infinityLeggings": false,
  "infinityBoots": false,
  "infinityElytra": false,
  "infinityShields": false,
  
  "singleUseGlobal": false,
  "singleUseWeapons": false,
  "singleUseSwords": false,
  "singleUseSpears": false,
  "singleUseTridents": false,
  "singleUseMaces": false,
  "singleUseBows": false,
  "singleUseCrossbows": false,
  "singleUseTools": false,
  "singleUsePickaxes": false,
  "singleUseAxes": false,
  "singleUseShovels": false,
  "singleUseHoes": false,
  "singleUseShears": false,
  "singleUseFishingRods": false,
  "singleUseBrushes": false,
  "singleUseFlintAndSteel": false,
  "singleUseArmor": false,
  "singleUseHelmets": false,
  "singleUseChestplates": false,
  "singleUseLeggings": false,
  "singleUseBoots": false,
  "singleUseElytra": false,
  "singleUseShields": false,
  
  "showTooltip": true,
  "tooltipFormat": "ADAPTIVE",
  
  "forcedItems": [],
  "forcedPercentages": {},
  "forcedInfinities": {},
  "forcedSingleUses": {}
}
```

---

## 🔄 자동 입력 시스템

Durability Multiplier는 수동 입력 없이 모드 아이템을 자동 분류하는 자율형 **범용 3단계 탐색 스캐너**를 갖추고 있습니다:

1. **시작 시 스캔**: 클라이언트/서버 구동 시 엔진이 `BuiltInRegistries.ITEM`을 스캔합니다.
2. **내구도 필터**: 외부 모드 네임스페이스(`minecraft` 및 관례 태그 `c` 제외) 아이템이 `DataComponents.MAX_DAMAGE > 0`을 가지는지 검사합니다.
3. **자동 입력**: 탐색된 내구도 아이템이 다음 항목에 자동으로 추가됩니다:
   * `"forcedItems"`: Added to the active item registry list.
   * `"forcedPercentages"`: Added with default value `0` (indicating the item dynamically inherits its category or global multiplier).
4. **설정 영속화**: 업데이트된 목록이 `config/durability-multiplier.json`에 저장되어 Cloth Config / ModMenu GUI 및 게임 내 게임 규칙에서 즉시 확인 및 편집할 수 있습니다.

---

## 🛠️ 수동 아이템 구성 가이드

모드팩 제작자, 서버 관리자 및 플레이어는 `config/durability-multiplier.json`에서 특정 아이템에 대한 규칙을 선언할 수 있습니다:

### 1. `forcedItems` (아이템 등록)
모드에서 인식하는 아이템 식별자 목록을 선언합니다.
```json
"forcedItems": [
  "techmod:plasma_cutter",
  "magicmod:staff_of_fire",
  "customweapons:obsidian_blade"
]
```

### 2. `forcedPercentages` (아이템별 내구도 비율)
특정 아이템에 명시적인 내구도 퍼센트 배율을 지정합니다:
* `0`: 상위 범주 또는 전체 배율 상속.
* `100`: 바닐라 100% 기준 (내구도 1배).
* `200`: 내구도 200% (수명 2배).
* `50`: 내구도 50% (수명 절반 / 소모율 2배).
* `-1`: 1회용 (유리 모드 - 첫 타격 시 파괴).
```json
"forcedPercentages": {
  "techmod:plasma_cutter": 300,
  "customweapons:obsidian_blade": -1,
  "magicmod:training_wand": 50
}
```

### 3. `forcedInfinities` (아이템별 갓 모드)
특정 아이템에 영구적인 파괴 불가 (무한) 상태를 부여합니다:
```json
"forcedInfinities": {
  "magicmod:creative_staff": true,
  "adminmod:ban_hammer": true
}
```

### 4. `forcedSingleUses` (아이템별 유리 모드)
특정 아이템이 단 한 번의 내구도 감소 후 즉시 파괴되도록 강제합니다:
```json
"forcedSingleUses": {
  "techmod:disposable_cutter": true,
  "survivalplus:glass_dagger": true
}
```

---

## ⚡ 고급 사용자를 위한 `-1` 유리 모드 센티널 값

Durability Multiplier에는 내구도 퍼센트에 대한 **`-1` 센티널 값**이 포함되어 있습니다:
* 퍼센트 규칙 또는 설정 항목을 `-1` (또는 음의 정수)로 설정하면 해당 아이템 또는 범주에 **1회용 (유리 모드)**가 자동 트리거됩니다.
* 활성화 시, 아이템은 첫 타격에서 `maxDamage - damageValue` 피해를 입어 내구도가 0이 되며 정확히 1회 사용 후 파괴됩니다.
* 관리자나 제작자는 불리언 규칙을 일일이 토글하지 않고도 슬라이더나 `/gamerule`을 통해 1타 파괴 메커니즘을 적용할 수 있습니다.

---

## 🎨 툴팁 표시 서식

`tooltipFormat` 옵션은 아이템 툴팁에 내구도 보너스가 표시되는 방식을 설정합니다:

| 서식 설정 | 표시 예시 (200% / 2x) | 표시 예시 (150% / 1.5x) | 설명 |
| :--- | :--- | :--- | :--- |
| `"ADAPTIVE"` *(기본값)* | `⟨2x 검 내구도⟩` | `⟨150% 검 내구도⟩` | 100 단위는 정수 배율로, 그 외는 퍼센트로 표시합니다. |
| `"PERCENTAGE"` | `⟨200% 검 내구도⟩` | `⟨150% 검 내구도⟩` | 항상 정확한 퍼센트 값을 표시합니다. |
| `"MULTIPLIER"` | `⟨2x 검 내구도⟩` | `⟨1.5x 검 내구도⟩` | 항상 서식화된 배율 문자열을 표시합니다. |

`"showTooltip": false`로 설정하면 툴팁 내구도 표시가 완전히 숨겨집니다.

---

## ⚠️ 중요 구성 우선순위 경고

> ⚠️ **알림**: `durability-multiplier.json` 또는 ModMenu 화면에서의 변경 사항은 **새로 생성되는 월드의 기본값만 정의**합니다.
> 
> 기존 월드의 경우, 각 월드의 데이터(`level.dat`) 내에 독립된 게임 규칙 상태가 보존됩니다. 진행 중인 월드의 설정을 변경하려면 게임 내 `/gamerule` 명령어 또는 일시정지 메뉴의 게임 규칙 편집 화면을 사용하세요.

