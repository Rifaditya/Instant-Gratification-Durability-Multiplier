# 명령어 및 관리 (26.1.2)

| 관리 시스템 | 상세 내용 |
| :--- | :--- |
| **명령어 엔진** | 바닐라 마인크래프트 `/gamerule` Brigadier 명령어 시스템 |
| **네임스페이스** | 모든 규칙에 `ig:` 접두사 적용 |
| **권한 레벨** | 레벨 2 (OP 권한 / 싱글플레이 치트 활성화) |
| **GUI 관리** | 게임 규칙 설정 화면 및 ModMenu 설정 지원 |
| **미사용 정책** | 설계상 **커스텀 Brigadier 명령어 서브트리를 일체 등록하지 않음** |

---

## ⚡ 게임 내 관리 워크플로

Durability Multiplier는 전적으로 바닐라 `/gamerule` 명령어에 의존합니다. 커스텀 명령어(`/durability set`, `/durability reload` 등)를 추가하지 않으므로 커맨드 블록, 함수, 권한 시스템, 데이터팩과 100% 네이티브 호환성을 보장합니다.

### 일반적인 관리 작업

#### 1. 표준 서바이벌 버프 구성
```mcfunction
# Double all items globally (200% durability - Default)
/gamerule ig:dm_percent_global 200

# Give mining tools a 5x (500%) lifespan
/gamerule ig:dm_percent_tools 500

# Give weapons a 3x (300%) lifespan
/gamerule ig:dm_percent_weapons 300
```

#### 2. 전투 및 PvP 서버 설정 구성
```mcfunction
# Keep armor at vanilla durability (100%) to prevent overly tanky players
/gamerule ig:dm_percent_armor 100

# Give weapons 2x (200%) durability
/gamerule ig:dm_percent_swords 200
/gamerule ig:dm_percent_bows 200
```

#### 3. 크리에이티브 스타일 서바이벌 활성화(파괴 불가 겉날개 및 도구)
```mcfunction
# Unbreakable Elytra for limitless exploration
/gamerule ig:dm_infinity_elytra true

# Unbreakable Tools for building mega-projects
/gamerule ig:dm_infinity_tools true
```

#### 4. 동적 모드 아이템 구성
```mcfunction
# Give a custom modded drill 400% durability
/gamerule ig:percent_techmod_titanium_drill 400

# Make a modded staff unbreakable
/gamerule ig:infinity_magicmod_staff_of_fire true

# Set a modded dagger to Single-Use using the -1 sentinel
/gamerule ig:percent_customweapons_glass_dagger -1
```

#### 5. 툴팁 텍스트 숨기기
```mcfunction
# Hide durability multiplier info from item tooltips
/gamerule ig:dm_show_tooltip false
```

