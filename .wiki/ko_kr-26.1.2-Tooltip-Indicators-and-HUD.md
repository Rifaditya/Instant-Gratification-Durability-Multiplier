# 툴팁 표시기 및 HUD (26.1.2)

| 시스템 매개변수 | 설정값 |
| :--- | :--- |
| **표시 토글 게임 규칙** | `ig:dm_show_tooltip` |
| **기본 상태** | `true` (활성화) |
| **Mixin 대상** | `ItemStack.addDetailsToTooltip` (`ItemStackTooltipMixin`) |
| **주입 지점** | `@At("TAIL")` |
| **신 모드 툴팁 서식** | `✦ UNBREAKABLE` (금색, 볼드체 — `ChatFormatting.GOLD`, `BOLD`) |
| **배율 툴팁 서식** | `⟨Nx 범주 내구도⟩` (회색 — `ChatFormatting.GRAY`) |

---

## ⚡ 개요 및 시각적 표현

Durability Multiplier는 아이템의 수명이 변경될 때마다 툴팁에 명확하고 직관적인 시각적 피드백을 제공합니다.

### 툴팁 시각적 스타일

| 상태 | 렌더링 텍스트 | 시각적 표시 | 색상 코드 |
| :--- | :--- | :--- | :--- |
| **신 모드 활성화** | `✦ UNBREAKABLE` | **✦ UNBREAKABLE** | 금색, 볼드체 (`ChatFormatting.GOLD`, `BOLD`) |
| **1회용 (유리 모드)** | `⟨SINGLE-USE⟩` | ⟨SINGLE-USE⟩ | 회색 (`ChatFormatting.GRAY`) |
| **200% / 2배 내구도** | `⟨2x 검 내구도⟩` | ⟨2x 검 내구도⟩ | 회색 (`ChatFormatting.GRAY`) |
| **150% 내구도** | `⟨150% 흉갑 내구도⟩` | ⟨150% 흉갑 내구도⟩ | 회색 (`ChatFormatting.GRAY`) |
| **50% 내구도 (절반)** | `⟨50% 검 내구도⟩` | ⟨50% 검 내구도⟩ | 회색 (`ChatFormatting.GRAY`) |
| **500% / 5배 내구도** | `⟨5x 곡괭이 내구도⟩` | ⟨5x 곡괭이 내구도⟩ | 회색 (`ChatFormatting.GRAY`) |
| **모드 아이템 재정의** | `⟨300% 플라스마 커터 내구도⟩` | ⟨300% 플라스마 커터 내구도⟩ | 회색 (`ChatFormatting.GRAY`) |
| **바닐라 기준 (100%)** | *(없음)* | *(추가 툴팁 줄 렌더링되지 않음)* | — |

---

## 🎨 툴팁 서식 모드 (`tooltipFormat`)

설정 파일 및 ModMenu GUI를 통해 3가지 표시 서식을 지원합니다:
1. **`ADAPTIVE` (기본값)**: 100 단위는 깔끔한 정수 배율(`2x`, `5x`)로, 그 외는 퍼센트(`50%`, `150%`)로 자동 표시합니다.
2. **`PERCENTAGE`**: 항상 명시적인 퍼센트로 표시합니다 (예: `200% 검 내구도`).
3. **`MULTIPLIER`**: 항상 소수점 배율로 표시합니다 (예: `2x 검 내구도`, `0.5x 검 내구도`).

---

## 🖥️ 클라이언트 및 서버 측 실행

```
                       [Item Tooltip Render]
                                 │
                                 ▼
                     [Is Player on Integrated Server?]
                     ├── YES ──► Read GameRules from ServerLevel
                     │           (DurabilityHelper.getTooltipLabel)
                     │
                     └── NO (Remote Server) ──► Read Synced Client Cache
                                                (DurabilityClientState)
```

1. **통합 서버 (싱글플레이 / LAN 호스트)**: 툴팁이 실시간으로 `ServerLevel`의 활성 규칙을 직접 조회합니다.
2. **전용 클라이언트 (멀티플레이 접속 시)**: 규칙 변경 시 전송되는 `DurabilityPayload` 패킷으로 갱신되는 `DurabilityClientState`를 읽습니다.
