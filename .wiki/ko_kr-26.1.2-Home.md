# Durability Multiplier — Minecraft 26.1.2 문서 허브

**Minecraft 26.1.2** (`1.1.21+26.1.2`) 버전 **Durability Multiplier** 공식 기술 문서에 오신 것을 환영합니다.

> 📌 **소스코드 관련 안내**: 본 위키 문서는 CurseForge나 Modrinth에 공개된 빌드보다 앞선 최신 커밋 및 개발 중인 기능을 포함할 수 있는 **저장소의 현재 소스코드 상태**를 반영합니다.

---

## 📋 기술 스냅샷 (26.1.2)

| 매개변수 | 값 | 설명 |
| :--- | :--- | :--- |
| **모드 식별자** | `durability-multiplier` | Fabric Loader 내 모드 ID |
| **모드 버전** | `1.1.21+26.1.2` | SemVer 릴리스 태그 |
| **대상 마인크래프트** | `26.1.2` (`*`) | 네이티브 버전 앵커 |
| **자바 릴리스** | Java 25 | `release = 25`로 컴파일됨 |
| **Fabric Loader** | `>=0.16.9` | 최소 로더 요구 사양 |
| **Fabric API** | `0.145.4+26.1.2` | 런타임 Fabric API 요구 사양 |
| **DasikLibrary** | `1.8.28` | 공유 아키텍처 코어 |
| **등록된 게임 규칙** | **정적 규칙 73개** + 동적 모드 규칙 | 퍼센트 24개, 무한 24개, 1회용 24개, 툴팁 1개 |
| **Mixin 주입 지점** | 3개 대상 클래스 | `ItemStack`, `GameRules` |
| **제작자 및 라이선스** | **Dasik (Rifaditya)** / GPL-3.0-or-later | 오픈소스 모드 |

---

## 🧭 탐색 매트릭스 (26.1.2)

### 🎮 플레이어 및 게임플레이 가이드
* [[내구도 배율 및 범주|ko_kr-26.1.2-Durability-Multipliers]] — 24개 세부 범주별 퍼센트 시스템 및 우선순위 체계.
* [[신 모드 및 무한화|ko_kr-26.1.2-God-Mode-and-Infinity]] — 24개 범주에 걸친 무적 파괴 불가 설정.
* [[피해 감소 수학 공식 및 확률|ko_kr-26.1.2-Damage-Reduction-and-Probability-Math]] — 수학적 계산식 및 확률적 반올림 메커니즘.
* [[아이템 분류 및 모드 호환성|ko_kr-26.1.2-Item-Classification-and-Mod-Compatibility]] — 바닐라 및 모드 아이템 분류 방식.
* [[동적 모드 아이템 탐색 및 등록|ko_kr-26.1.2-Dynamic-Modded-Item-Registration]] — 범용 3단계 탐색 스캐너 및 자동 입력.
* [[툴팁 표시 및 HUD|ko_kr-26.1.2-Tooltip-Indicators-and-HUD]] — 클라이언트 측 툴팁 렌더링.
* [[게임 규칙 참조 표|ko_kr-26.1.2-GameRules]] — 73개 정적 게임 규칙 전체 참조 표.
* [[명령어 및 게임 내 관리|ko_kr-26.1.2-Commands-and-Administration]] — `/gamerule`을 통한 게임 내 설정 관리.
* [[발전 과제 및 도전 과제|ko_kr-26.1.2-Advancements]] — 미사용 정책 및 바닐라 연동.
* [[설정 GUI 및 월드 기본값|ko_kr-26.1.2-Configuration]] — ModMenu 및 Cloth Config 연동.

* [[문제 해결 및 자주 묻는 질문|ko_kr-26.1.2-Troubleshooting-and-FAQ]] — 진단 절차 및 FAQ.

### 💻 개발자 및 기술 참조
* [[아키텍처 및 Mixin 명세|ko_kr-26.1.2-Architecture-and-Mixins]] — 패키지 계층, 주입 후크, 재진입 안전성.
* [[네트워크 동기화 및 페이로드 프로토콜|ko_kr-26.1.2-Network-Sync-and-Payload-Protocol]] — S2C 동기화 프로토콜 (`DurabilityPayload`).
* [[개발 환경 설정 및 빌드|ko_kr-26.1.2-Developer-Setup-and-Building]] — Gradle 명령어, Loom 툴체인, JDK 설정.
* [[API 및 애드온 연동|ko_kr-26.1.2-API-and-Addon-Integration]] — 모드 확장, `DurabilityHelper`, 커스텀 규칙.
