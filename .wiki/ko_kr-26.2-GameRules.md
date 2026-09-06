# 게임 규칙(GameRules) 참조 (26.2)

모든 Durability Multiplier 게임 규칙은 커스텀 범주 **`durability-multiplier:durability_multiplier`** 아래에 등록됩니다.

---

## 📊 완전한 게임 규칙 참조 표

### 1. 내구도 비율 게임 규칙
퍼센트 규칙은 아이템의 내구도 배율을 제어합니다.
* `200` = 200% (내구도 2배)
* `100` = 100% (바닐라 1배 기준값)
* `50` = 50% (내구도 절반 / 소모 속도 2배)
* `0` = 상위 범주 또는 전체 기본값 상속
* `-1` = **1회용 (유리 모드)** 센티널 값 (1회 타격 시 파괴)

| # | 게임 규칙 식별자 | 유형 | 기본값 | 최소값 | 설명 및 동작 |
| :-: | :--- | :---: | :---: | :---: | :--- |
| 1 | `ig:dm_percent_global` | `Integer` | `200` | `-1` | 모든 내구도 아이템에 대한 전역 기본 내구도 비율. |
| 2 | `ig:dm_percent_weapons` | `Integer` | `0` | `-1` | 모든 무기에 대한 전역 재정의(검, 창, 삼지창, 메이스, 활, 쇠뇌). |
| 3 | `ig:dm_percent_swords` | `Integer` | `0` | `-1` | 검 전용 내구도 비율(`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `Integer` | `0` | `-1` | 창 전용 내구도 비율(`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `Integer` | `0` | `-1` | 삼지창 전용 내구도 비율(`TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `Integer` | `0` | `-1` | 메이스 전용 내구도 비율(`MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `Integer` | `0` | `-1` | 활 전용 내구도 비율(`BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `Integer` | `0` | `-1` | 쇠뇌 전용 내구도 비율(`CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `Integer` | `0` | `-1` | 방패 전용 내구도 비율(`ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `Integer` | `0` | `-1` | 모든 도구에 대한 상위 카테고리 비율. |
| 11 | `ig:dm_percent_pickaxes` | `Integer` | `0` | `-1` | 곡괭이 전용 내구도 비율(`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `Integer` | `0` | `-1` | 도끼 전용 내구도 비율(`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `Integer` | `0` | `-1` | 삽 전용 내구도 비율(`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `Integer` | `0` | `-1` | 괭이 전용 내구도 비율(`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `Integer` | `0` | `-1` | 가위 전용 내구도 비율(`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `Integer` | `0` | `-1` | 낚싯대 전용 내구도 비율(`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `Integer` | `0` | `-1` | 솔 전용 내구도 비율(`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `Integer` | `0` | `-1` | 부싯돌과 부시 전용 내구도 비율(`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `Integer` | `0` | `-1` | 모든 갑옷 부위에 대한 상위 카테고리 비율. |
| 20 | `ig:dm_percent_helmets` | `Integer` | `0` | `-1` | 투구 전용 내구도 비율(`#c:helmets`, 머리 슬롯). |
| 21 | `ig:dm_percent_chestplates` | `Integer` | `0` | `-1` | 흉갑 전용 내구도 비율(`#c:chestplates`, 몸통 슬롯). |
| 22 | `ig:dm_percent_leggings` | `Integer` | `0` | `-1` | 레깅스 전용 내구도 비율(`#c:leggings`, 다리 슬롯). |
| 23 | `ig:dm_percent_boots` | `Integer` | `0` | `-1` | 부츠 전용 내구도 비율(`#c:boots`, 발 슬롯). |
| 24 | `ig:dm_percent_elytra` | `Integer` | `0` | `-1` | 겉날개 전용 내구도 비율(`Items.ELYTRA`, `GLIDER`). |

---

### 2. 갓 모드(무한 내구도) 게임 규칙
활성화(`true`) 시 해당 범주의 아이템은 $0$ 피해를 받으며 절대 파괴되지 않습니다.

| # | 게임 규칙 식별자 | 유형 | 기본값 | 설명 |
| :-: | :--- | :---: | :---: | :--- |
| 25 | `ig:dm_infinity_global` | `Boolean` | `false` | 게임 내 모든 내구도 아이템에 대한 전역 갓 모드. |
| 26 | `ig:dm_infinity_weapons` | `Boolean` | `false` | 모든 무기에 대한 갓 모드. |
| 27 | `ig:dm_infinity_swords` | `Boolean` | `false` | 검에 대한 갓 모드. |
| 28 | `ig:dm_infinity_spears` | `Boolean` | `false` | 창에 대한 갓 모드. |
| 29 | `ig:dm_infinity_tridents` | `Boolean` | `false` | 삼지창에 대한 갓 모드. |
| 30 | `ig:dm_infinity_maces` | `Boolean` | `false` | 메이스에 대한 갓 모드. |
| 31 | `ig:dm_infinity_bows` | `Boolean` | `false` | 활에 대한 갓 모드. |
| 32 | `ig:dm_infinity_crossbows` | `Boolean` | `false` | 쇠뇌에 대한 갓 모드. |
| 33 | `ig:dm_infinity_shields` | `Boolean` | `false` | 방패에 대한 갓 모드. |
| 34 | `ig:dm_infinity_tools` | `Boolean` | `false` | 모든 도구에 대한 갓 모드. |
| 35 | `ig:dm_infinity_pickaxes` | `Boolean` | `false` | 곡괭이에 대한 갓 모드. |
| 36 | `ig:dm_infinity_axes` | `Boolean` | `false` | 도끼에 대한 갓 모드. |
| 37 | `ig:dm_infinity_shovels` | `Boolean` | `false` | 삽에 대한 갓 모드. |
| 38 | `ig:dm_infinity_hoes` | `Boolean` | `false` | 괭이에 대한 갓 모드. |
| 39 | `ig:dm_infinity_shears` | `Boolean` | `false` | 가위에 대한 갓 모드. |
| 40 | `ig:dm_infinity_fishing_rods` | `Boolean` | `false` | 낚싯대에 대한 갓 모드. |
| 41 | `ig:dm_infinity_brushes` | `Boolean` | `false` | 솔에 대한 갓 모드. |
| 42 | `ig:dm_infinity_flint_and_steel` | `Boolean` | `false` | 부싯돌과 부시에 대한 갓 모드. |
| 43 | `ig:dm_infinity_armor` | `Boolean` | `false` | 모든 갑옷에 대한 갓 모드. |
| 44 | `ig:dm_infinity_helmets` | `Boolean` | `false` | 투구에 대한 갓 모드. |
| 45 | `ig:dm_infinity_chestplates` | `Boolean` | `false` | 흉갑에 대한 갓 모드. |
| 46 | `ig:dm_infinity_leggings` | `Boolean` | `false` | 레깅스에 대한 갓 모드. |
| 47 | `ig:dm_infinity_boots` | `Boolean` | `false` | 부츠에 대한 갓 모드. |
| 48 | `ig:dm_infinity_elytra` | `Boolean` | `false` | 겉날개에 대한 갓 모드. |

---

### 3. 1회용(유리 모드) 게임 규칙
활성화(`true`) 시 해당 범주의 아이템은 1회 사용 후 산산조각 납니다.

| # | 게임 규칙 식별자 | 유형 | 기본값 | 설명 |
| :-: | :--- | :---: | :---: | :--- |
| 49 | `ig:dm_single_use_global` | `Boolean` | `false` | 모든 아이템에 대한 전역 유리 모드(1회용). |
| 50 | `ig:dm_single_use_weapons` | `Boolean` | `false` | 모든 무기에 대한 1회용 모드. |
| 51 | `ig:dm_single_use_swords` | `Boolean` | `false` | 검에 대한 1회용 모드. |
| 52 | `ig:dm_single_use_spears` | `Boolean` | `false` | 창에 대한 1회용 모드. |
| 53 | `ig:dm_single_use_tridents` | `Boolean` | `false` | 삼지창에 대한 1회용 모드. |
| 54 | `ig:dm_single_use_maces` | `Boolean` | `false` | 메이스에 대한 1회용 모드. |
| 55 | `ig:dm_single_use_bows` | `Boolean` | `false` | 활에 대한 1회용 모드. |
| 56 | `ig:dm_single_use_crossbows` | `Boolean` | `false` | 쇠뇌에 대한 1회용 모드. |
| 57 | `ig:dm_single_use_shields` | `Boolean` | `false` | 방패에 대한 1회용 모드. |
| 58 | `ig:dm_single_use_tools` | `Boolean` | `false` | 모든 도구에 대한 1회용 모드. |
| 59 | `ig:dm_single_use_pickaxes` | `Boolean` | `false` | 곡괭이에 대한 1회용 모드. |
| 60 | `ig:dm_single_use_axes` | `Boolean` | `false` | 도끼에 대한 1회용 모드. |
| 61 | `ig:dm_single_use_shovels` | `Boolean` | `false` | 삽에 대한 1회용 모드. |
| 62 | `ig:dm_single_use_hoes` | `Boolean` | `false` | 괭이에 대한 1회용 모드. |
| 63 | `ig:dm_single_use_shears` | `Boolean` | `false` | 가위에 대한 1회용 모드. |
| 64 | `ig:dm_single_use_fishing_rods` | `Boolean` | `false` | 낚싯대에 대한 1회용 모드. |
| 65 | `ig:dm_single_use_brushes` | `Boolean` | `false` | 솔에 대한 1회용 모드. |
| 66 | `ig:dm_single_use_flint_and_steel` | `Boolean` | `false` | 부싯돌과 부시에 대한 1회용 모드. |
| 67 | `ig:dm_single_use_armor` | `Boolean` | `false` | 모든 갑옷에 대한 1회용 모드. |
| 68 | `ig:dm_single_use_helmets` | `Boolean` | `false` | 투구에 대한 1회용 모드. |
| 69 | `ig:dm_single_use_chestplates` | `Boolean` | `false` | 흉갑에 대한 1회용 모드. |
| 70 | `ig:dm_single_use_leggings` | `Boolean` | `false` | 레깅스에 대한 1회용 모드. |
| 71 | `ig:dm_single_use_boots` | `Boolean` | `false` | 부츠에 대한 1회용 모드. |
| 72 | `ig:dm_single_use_elytra` | `Boolean` | `false` | 겉날개에 대한 1회용 모드. |

---

### 4. 표시 및 동적 모드 게임 규칙

| 게임 규칙 식별자 | 유형 | 기본값 | 설명 |
| :--- | :---: | :---: | :--- |
| `ig:dm_show_tooltip` | `Boolean` | `true` | 아이템 툴팁에 내구도 배율 표시줄을 렌더링합니다. |
| `ig:percent_<mod>_<item>` | `Integer` | `0` | 특정 모드 아이템의 동적 퍼센트 재정의 (최소 `-1`). |
| `ig:infinity_<mod>_<item>` | `Boolean` | `false` | 특정 모드 아이템의 동적 신 모드 재정의. |
| `ig:single_use_<mod>_<item>` | `Boolean` | `false` | 특정 모드 아이템의 동적 1회용 재정의. |

---

## ⚡ 게임 내 조정 명령어

```mcfunction
# Query current global percentage
/gamerule ig:dm_percent_global

# Set diamond/netherite pickaxes to 500% (5x) durability
/gamerule ig:dm_percent_pickaxes 500

# Make Elytra wings unbreakable
/gamerule ig:dm_infinity_elytra true

# Set a modded weapon to Single-Use using the -1 sentinel
/gamerule ig:percent_techmod_plasma_cutter -1

# Disable all multipliers (vanilla 100% baseline)
/gamerule ig:dm_percent_global 100
```

