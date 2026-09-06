# 문제 해결 및 자주 묻는 질문(FAQ) (26.1.2)

| 시스템 주제 | 요약 |
| :--- | :--- |
| **우선순위 동작** | 활성 월드에서는 GameRules가 우선하며, 설정 파일은 새 월드 기본값을 설정 |
| **계산 엔진** | 확률적 차단 (NBT 변조 없음, 세이브 불일치 없음) |
| **예외 상황 내성** | 모드 삭제, 레지스트리 해제, 컴포넌트 누락 시에도 100% 충돌 없음 |

---

## ❓ 자주 묻는 질문 (FAQ)

### Q1: ModMenu의 구성 변경 사항이 활성 싱글플레이어 월드에 영향을 주지 않는 이유는 무엇인가요?
**답변**: **우선순위의 법칙**에 따라 `durability-multiplier.json` 또는 ModMenu GUI에서의 변경 사항은 **새 월드에만** 기본값으로 적용됩니다. 현재 플레이 중인 월드의 설정을 변경하려면 게임 내 `/gamerule` 명령어(예: `/gamerule ig:dm_percent_tools 500`)나 게임 규칙 편집 화면을 사용하세요.

### Q2: 아이템 툴팁에 비율이나 배율 텍스트가 표시되지 않는 이유는 무엇인가요?
**답변**:
1. 아이템이 내구도 게이지를 가진 아이템(`DataComponents.MAX_DAMAGE > 0`)인지 확인하세요.
2. `ig:dm_show_tooltip`이 `true`로 설정되어 있는지 확인하세요.
3. 활성 설정이 `100` (바닐라 100% 내구도)인 경우 툴팁을 깔끔하게 유지하기 위해 추가 표시줄이 렌더링되지 않습니다.

### Q3: 내 500%(5배) 도구가 2번만 사용했는데 내구도가 닳은 이유는 무엇인가요?
**답변**: Durability Multiplier는 **세이브 파일의 완벽한 안전**을 위해 **확률적 피해 차단** (바닐라 마인크래프트의 *내구성(Unbreaking)* 마법부여와 동일한 메커니즘)을 사용합니다. 500% (내구도 5배)의 경우, 블록을 캘 때마다 독립적인 **20% 확률 (5번에 1번)**로 1의 피해를 입고 **80% 확률**로 피해를 흡수합니다. 매 타격마다 독립적으로 확률이 적용되므로 2번 만에 닳을 수도, 8번 동안 안 닳을 수도 있지만, 도구의 전체 수명 동안에는 정확히 5배 더 오래 사용할 수 있습니다 (다이아몬드 곡괭이 기준 약 7,805개 블록 채굴 가능).

### Q4: 게임 규칙에 0.5나 1.5 같은 소수를 입력해야 하나요?
**답변**: **아닙니다**. 마인크래프트 GameRules는 정수(`int`)만 지원합니다. 항상 정수 퍼센트 숫자를 입력하세요:
* `50` = 50% (내구도 절반 / 소모율 2배)
* `100` = 100% (바닐라 표준 1배)
* `150` = 150% (내구도 1.5배)
* `200` = 200% (내구도 2배)
* `-1` = 1회용 (유리 모드 / 1타 만에 파괴)

### Q5: Durability Multiplier가 내구성(Unbreaking) 마법부여와 함께 작동하나요?
**답변**: 네! Durability Multiplier는 마법부여 처리 **전**에 피해를 스케일링합니다. 200% (2배) 설정에 내구성 III이 적용된 곡괭이는 마법부여가 없는 바닐라 곡괭이보다 약 $4 \times 2 = 8$배 더 오래 지속됩니다.

### Q6: 아이템에 1회 타격 유리 모드(1회용)를 활성화하려면 어떻게 해야 하나요?
**답변**: 다음 두 가지 방법 중 하나를 사용할 수 있습니다:
1. 1회용 게임 규칙을 true로 설정: `/gamerule ig:dm_single_use_swords true` (또는 `/gamerule ig:single_use_<mod>_<item> true`).
2. **파워 유저용 `-1` 센티널** 사용: 퍼센트 규칙을 `-1`로 설정 (예: `/gamerule ig:dm_percent_swords -1` 또는 `/gamerule ig:percent_<mod>_<item> -1`).

---

## 🔍 심층 엣지 케이스 및 수명 주기 동작

### 엣지 케이스 1: 모드 제거 및 아이템 삭제
Durability Multiplier에 등록된 아이템을 가진 모드를 삭제하는 경우:
1. **설정 파일 보존**: 삭제된 아이템 ID는 `config/durability-multiplier.json` 내의 `forcedItems` 및 `forcedPercentages`에 안전하게 남습니다.
2. **월드 데이터 휴면**: 월드의 `level.dat`에 저장된 동적 게임 규칙은 메모리 내에서 완전히 비활성(휴면) 상태로 유지됩니다.
3. **충돌 및 손상 제로**: 아이템 조회가 `BuiltInRegistries.ITEM.getKey(stack.getItem())`를 통해 보호되므로 존재하지 않는 클래스나 미매핑 ID를 검색하지 않으며, `NullPointerException`이나 청크 손상이 일체 발생하지 않습니다.
4. **재설치 시 자동 복원**: 향후 해당 모드를 다시 설치하면 기존의 내구도 배율, 신 모드, 1회용 설정이 별도의 재설정 없이 **즉시 다시 연결**됩니다!
5. **수동 설정 정리 (선택 사항)**: 설정 파일에서 삭제된 모드 항목을 제거하고 싶은 경우:
   * Open `config/durability-multiplier.json` in a text editor.
   * Remove the deleted item ID string from the `"forcedItems"` list.
   * Remove the corresponding keys from `"forcedPercentages"`, `"forcedInfinities"`, and `"forcedSingleUses"`.
   * Save the file.

---

### 엣지 케이스 2: 엄격한 내구도 필터링 (`MAX_DAMAGE > 0`)
가구 모드(Macaw's Furniture의 의자나 옷장 등), 건축 블록, 음식, 제작 재료가 게임 규칙이나 설정 파일에 나타나지 않는 이유는 무엇인가요?
* Durability Multiplier는 아이템을 등록하기 전 `DataComponents.MAX_DAMAGE > 0`을 엄격히 검증합니다.
* 내구도 컴포넌트가 없는 아이템 (블록, 설치물, 음식, 주괴, 씨앗)은 시작 스캔 시 $0.0001\mu\text{s}$ 만에 거부됩니다.
* 이를 통해 네임스페이스 오염을 방지하고 Tab 자동완성을 쾌적하고 깔끔하게 유지합니다.

---

### 엣지 케이스 3: 완전한 평가 및 우선순위 계층
아이템이 내구도 피해를 입을 때, 결과는 다음의 엄격한 우선순위 계층에 따라 결정됩니다:

$$\text{God Mode (Infinity)} \longrightarrow \text{Single-Use (Glass Mode)} \longrightarrow \text{Per-Item Override} \longrightarrow \text{Subcategory} \longrightarrow \text{Parent Category} \longrightarrow \text{Global} \longrightarrow \text{100\% Vanilla}$$

1. **신 모드 확인 (무적)**:
   * Per-Item God Mode (`ig:infinity_<mod>_<item>` / `forcedInfinities`)
   * Subcategory God Mode (`ig:dm_infinity_pickaxes`, `ig:dm_infinity_swords`, etc.)
   * Parent Category God Mode (`ig:dm_infinity_tools`, `ig:dm_infinity_weapons`, `ig:dm_infinity_armor`)
   * Global God Mode (`ig:dm_infinity_global`)
   * *If any is `true` $\rightarrow$ Damage = $0$ (Unbreakable).*
2. **1회용 확인 (유리 모드)**:
   * Effective percentage $\le -1$ (`-1` Glass Mode Sentinel)
   * Per-Item Single-Use (`ig:single_use_<mod>_<item>` / `forcedSingleUses`)
   * Subcategory Single-Use (`ig:dm_single_use_pickaxes`, `ig:dm_single_use_swords`, etc.)
   * Parent Category Single-Use (`ig:dm_single_use_tools`, `ig:dm_single_use_weapons`, `ig:dm_single_use_armor`)
   * Global Single-Use (`ig:dm_single_use_global`)
   * *If active $\rightarrow$ Item loses all remaining durability in 1 hit.*
3. **퍼센트 스케일링 확인**:
   * Per-Item Override (`ig:percent_<mod>_<item>` / `forcedPercentages`) if $\neq 0$
   * Subcategory Percentage (`ig:dm_percent_pickaxes`, `ig:dm_percent_swords`, etc.) if $\neq 0$
   * Parent Category Percentage (`ig:dm_percent_tools`, `ig:dm_percent_weapons`, `ig:dm_percent_armor`) if $\neq 0$
   * Global Percentage (`ig:dm_percent_global`) if $\neq 0$
   * Vanilla Fallback: $100\%$ baseline.

