# 내구도 감소 및 확률 수학 (26.1.2)

| 수학적 속성 | 값 |
| :--- | :--- |
| **총 피해 단위** | $\text{amount} \times 100$ |
| **기본 정수 나눗셈** | $\lfloor (\text{amount} \times 100) / \text{percent} \rfloor$ |
| **나머지** | $(\text{amount} \times 100) \pmod{\text{percent}}$ |
| **확률 판정** | `random.nextInt(percent) < remainder` |
| **정수 범위** | $\ge 0$ (전체 기본값: 200, 재정의: 0 = 상속) |
| **피해 0 보장** | 무한 모드 또는 확률적 흡수 성공 시 $0$ 피해 보장 |

---

## 🛡️ 왜 내구도 손상 인터셉트 방식인가 (100% 저장 데이터 안전성)?

Durability Multiplier is engineered to **never mutate item NBT or `DataComponents`** stored in world save files. 

### 왜 `DataComponents.MAX_DAMAGE`를 직접 수정하지 않나요?
1. **월드 오염 없음**: 최대 내구도를 직접 수정하면 인벤토리, 상자, 세이브 파일에 변조된 값이 영구 저장됩니다. 모드를 삭제하거나 규칙을 바꿀 때 아이템이 망가진 상태로 남을 수 있습니다.
2. **즉각적인 반영**: `/gamerule ig:dm_percent_global`을 변경하면 인벤토리 스캔이나 아이템 재생성 없이 월드 내 모든 아이템에 즉시 적용됩니다.
3. **모루 및 수선 밸런스 유지**: 모루 수리 비용과 수선(Mending) 경험치 흡수는 바닐라 표준 내구도를 기준으로 계산되어 오버플로우나 불이익이 없습니다.

대신, 이 모드는 런타임에 `ItemStackDurabilityMixin`을 통해 피해 이벤트를 가로채고 **확률적 피해 스케일링** (바닐라 마인크래프트의 **내구성(Unbreaking)** 마법부여와 동일한 아키텍처)을 적용합니다.

---

## 📐 정밀한 스케일링 알고리즘

아이템 사용 시 (블록 파괴 및 도구 공격 시 일반적으로 1의 `originalAmount` 피해 발생):

```java
public static int calculateScaledDamage(int originalAmount, int percent, RandomSource random) {
    if (originalAmount <= 0)
        return 0;
    if (percent <= 0 || percent == 100)
        return originalAmount;

    int totalDamageUnits = originalAmount * 100;
    int baseDamage = totalDamageUnits / percent;
    int remainder = totalDamageUnits % percent;
    if (remainder > 0 && random.nextInt(percent) < remainder) {
        baseDamage++;
    }
    return baseDamage;
}
```

---

## 🎲 확률 분포 매트릭스

### 1 내구도 손상 이벤트의 경우 (`originalAmount = 1`)

| 퍼센트 | 실효 배율 | 기본값 (`100 / P`) | 나머지 (`100 % P`) | 1회당 피해 확률 | 1회당 기대 피해 | 상대 내구도 |
| :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| **25%** | $0.25\times$ | `4` | `0` | $100\%$ (4 피해) | $4.00$ | $0.25\times$ (소모 4배) |
| **50%** | $0.50\times$ | `2` | `0` | $100\%$ (2 피해) | $2.00$ | $0.50\times$ (소모 2배) |
| **75%** | $0.75\times$ | `1` | `25` | $100\%$ (1) + $33.3\%$ (+1) | $1.33$ | $0.75\times$ (소모 1.33배) |
| **100%** | $1.00\times$ (바닐라) | `1` | `0` | $100\%$ (1 피해) | $1.00$ | $1.00\times$ (표준) |
| **150%** | $1.50\times$ | `0` | `100` | $\frac{100}{150} \approx 66.67\%$ (1 피해) | $0.67$ | $1.50\times$ |
| **200%** | $2.00\times$ | `0` | `100` | $\frac{100}{200} = 50.00\%$ (1 피해) | $0.50$ | $2.00\times$ |
| **300%** | $3.00\times$ | `0` | `100` | $\frac{100}{300} \approx 33.33\%$ (1 피해) | $0.33$ | $3.00\times$ |
| **500%** | $5.00\times$ | `0` | `100` | $\frac{100}{500} = 20.00\%$ (1 피해) | $0.20$ | $5.00\times$ |
| **1000%** | $10.00\times$ | `0` | `100` | $\frac{100}{1000} = 10.00\%$ (1 피해) | $0.10$ | $10.00\times$ |

---

## 📈 독립 무작위 판정 및 대수의 법칙

피해 흡수는 타격마다 독립적으로 계산되기 때문에 (바닐라 내구성과 동일):
* **500% (5배)** 시, 각 타격은 독립적으로 $20\%$ 확률로 1 피해, $80\%$ 확률로 0 피해가 됩니다.
* 짧은 테스트에서는 2회 만에 내구도가 줄 수도, 8회 동안 줄지 않을 수도 있습니다.
* 도구의 전체 수명(다이아몬드 도끼 기준 1,561회) 전체를 고려하면 파괴 가능한 블록 수는 수학적으로 **$\approx 7,805$회** (정확히 5배)로 수렴합니다.

아이템의 바닐라 내구도를 $N$, 설정 퍼센트를 $P$ ($P \ge 100$)라고 할 때, 파괴될 때까지의 사용 횟수 $U$는 다음 기댓값을 갖는 음이항 분포를 따릅니다:

$$\mathbb{E}[U] = N \times \frac{P}{100}$$

수천 번의 사용을 거치면서 큰 수의 법칙에 의해 총 내구도는 **정확히 바닐라 내구도의 $\frac{P}{100}$배**에 수렴합니다.
