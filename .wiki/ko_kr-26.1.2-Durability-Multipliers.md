# 내구도 배율 및 비율 (26.1.2)

Durability Multiplier는 바닐라의 고정 소모 메커니즘을 내구도 증가(예: 200% = 2배, 500% = 5배) 및 내구도 패널티(예: 50% = 0.5배, 25% = 0.25배)를 모두 지원하는 동적 **퍼센트 스케일링 엔진**으로 대체합니다.

---

## ⚙️ 핵심 비율 게임 규칙

| # | 게임 규칙 식별자 | 기본값 | 대상 범주 / 설명 |
| :-: | :--- | :---: | :--- |
| 1 | `ig:dm_percent_global` | `200` | 모든 내구도 아이템에 적용되는 전역 비율. |
| 2 | `ig:dm_percent_weapons` | `0` | 모든 무기에 대한 상위 재정의(검, 창, 삼지창, 메이스, 활, 쇠뇌). |
| 3 | `ig:dm_percent_swords` | `0` | 검 전용 내구도 비율(`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `0` | 창 전용 내구도 비율(`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `0` | 삼지창 전용 내구도 비율(`Items.TRIDENT`, `TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `0` | 메이스 전용 내구도 비율(`Items.MACE`, `MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `0` | 활 전용 내구도 비율(`Items.BOW`, `BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `0` | 쇠뇌 전용 내구도 비율(`Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `0` | 방패 전용 내구도 비율(`Items.SHIELD`, `ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `0` | 모든 도구에 대한 상위 카테고리 비율. |
| 11 | `ig:dm_percent_pickaxes` | `0` | 곡괭이 전용 내구도 비율(`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `0` | 도끼 전용 내구도 비율(`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `0` | 삽 전용 내구도 비율(`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `0` | 괭이 전용 내구도 비율(`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `0` | 가위 전용 내구도 비율(`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `0` | 낚싯대 전용 내구도 비율(`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `0` | 솔 전용 내구도 비율(`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `0` | 부싯돌과 부시 전용 내구도 비율(`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `0` | 모든 갑옷 부위에 대한 상위 카테고리 비율. |
| 20 | `ig:dm_percent_helmets` | `0` | 투구 전용 내구도 비율(`#minecraft:head_armor`, `#c:helmets`). |
| 21 | `ig:dm_percent_chestplates` | `0` | 흉갑 전용 내구도 비율(`#minecraft:chest_armor`, `#c:chestplates`). |
| 22 | `ig:dm_percent_leggings` | `0` | 레깅스 전용 내구도 비율(`#minecraft:leg_armor`, `#c:leggings`). |
| 23 | `ig:dm_percent_boots` | `0` | 부츠 전용 내구도 비율(`#minecraft:foot_armor`, `#c:boots`). |
| 24 | `ig:dm_percent_elytra` | `0` | 겉날개 전용 내구도 비율(`Items.ELYTRA`, `DataComponents.GLIDER`). |

> [!NOTE]
> `0`으로 설정된 재정의 규칙은 자동으로 상위 범주 또는 전체 기본값으로 폴백합니다. `-1`로 설정하면 **1회용 (유리 모드)**가 활성화됩니다.

---

## 🔒 100% 월드 저장 데이터 안전성
Durability Multiplier는 세이브 파일의 NBT나 `DataComponents.MAX_DAMAGE`를 **일체 수정하지 않습니다**. 모든 내구도 조정은 피해 계산 시 동적으로 수행되므로 모드를 삭제해도 월드 손상이나 변조 데이터가 전혀 남지 않습니다.
