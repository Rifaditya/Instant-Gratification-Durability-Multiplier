# Durability Multiplier 공식 위키

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

**Dasik (Rifaditya)**가 개발한 **Durability Multiplier** (Instant Gratification Collection) 공식 기술 및 게임플레이 위키에 오신 것을 환영합니다.

> 📌 **소스코드 관련 안내**: 본 위키 문서는 CurseForge나 Modrinth에 공개된 빌드보다 앞선 최신 커밋 및 개발 중인 기능을 포함할 수 있는 **저장소의 현재 소스코드 상태**를 반영합니다.

---

## 🧭 다중 버전 전환 포털

Durability Multiplier는 전용 마인크래프트 버전별로 최적화되어 있습니다. 아래에서 사용 중인 버전을 선택하여 전용 문서로 이동하세요:

| 마인크래프트 버전 | 릴리스 세대 | 지원 빌드 | Java 버전 | Loom 툴체인 | 위키 진입 |
| :--- | :--- | :--- | :---: | :---: | :--- |
| **Minecraft 26.2** | Modern Sovereign Era | `1.2.14+26.2` | Java 25 | Loom 1.15.2 | [[👉 MC 26.2 위키 열기\|ko_kr-26.2-Home]] |
| **Minecraft 26.1.2** | Modern Sovereign Era | `1.1.21+26.1.2` | Java 25 | Loom 1.15.2 | [[👉 MC 26.1.2 위키 열기\|ko_kr-26.1.2-Home]] |

---

## ⚡ 핵심 철학 및 아키텍처

Durability Multiplier는 **Instant Gratification (IG)** 디자인 철학을 따릅니다. 그 유일한 목표는 서바이벌에서의 **"장비 유지보수 스트레스"**를 없애는 것입니다:

* **플레이어의 시간 절약**: 번거로운 수리 반복, 채굴 중단, 실수로 인한 장비 파괴를 방지합니다.
* **순수 수학적 피해 감소**: 정수 나눗셈과 확률적 반올림으로 계산되어 바닐라 속성을 훼손하지 않으면서 수백만 번의 사용에도 수학적 정밀함을 유지합니다.
* **세밀한 제어**: 24개 개별 아이템 범주(검, 창, 삼지창, 메이스, 활, 쇠뇌, 방패, 도구, 곡괭이, 도끼, 삽, 괭이, 가위, 낚싯대, 솔, 라이터, 방어구, 투구, 흉갑, 레깅스, 부츠, 겉날개, 무기, 전체)를 73개 정적 게임 규칙으로 개별 설정.
* **신 모드 (무한)**: 단 하나의 불리언 게임 규칙으로 원하는 범주를 100% 파괴 불가로 설정.
* **모드 아이템 자동 감지**: 레지스트리 동결 시 모드의 내구도 아이템을 자동 탐색하여 전용 게임 규칙과 GUI 설정을 제공.
* **동기화 불일치 제로**: 전용 Fabric 네트워크(`durability-multiplier:sync_rules`)를 통해 서버 규칙을 클라이언트에 동기화하여 실시간 툴팁을 제공합니다.

---

## 📚 전역 탐색 및 리소스

* [[버전 호환성 매트릭스|ko_kr-Version-Compatibility]]
* [[MC 26.2 문서 허브|ko_kr-26.2-Home]]
* [[MC 26.1.2 문서 허브|ko_kr-26.1.2-Home]]
* [CurseForge 배포 페이지](https://curseforge.com/minecraft/mc-mods/durability-multiplier)
* [Modrinth 배포 페이지](https://modrinth.com/mod/durability-multiplier)
* [GitHub 소스 저장소](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier)
