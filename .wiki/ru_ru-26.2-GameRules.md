# Справочник игровых правил GameRules (26.2)

Все правила Durability Multiplier зарегистрированы в пользовательской категории **`durability-multiplier:durability_multiplier`** (`"Durability Multiplier"`).

---

## 📊 Полные справочные таблицы игровых правил

### 1. Процентные игровые правила прочности
Процентные правила управляют масштабированием прочности предметов.
* `200` = 200% (прочность 2x)
* `100` = 100% (базовый ванильный уровень 1x)
* `50` = 50% (половина прочности / износ в 2 раза быстрее)
* `0` = Наследование от родительской категории или глобального значения
* `-1` = Метка **Стеклянного режима (одноразовость)** (ломается с 1 удара)

| # | Идентификатор GameRule | Тип | По умолчанию | Мин | Описание и поведение |
| :-: | :--- | :---: | :---: | :---: | :--- |
| 1 | `ig:dm_percent_global` | `Integer` | `200` | `-1` | Базовый глобальный процент прочности для всех повреждаемых предметов. |
| 2 | `ig:dm_percent_weapons` | `Integer` | `0` | `-1` | Глобальное переопределение для всего оружия (мечи, копья, трезубцы, булавы, луки, арбалеты). |
| 3 | `ig:dm_percent_swords` | `Integer` | `0` | `-1` | Особый процент прочности для мечей (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `Integer` | `0` | `-1` | Особый процент прочности для копий (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `Integer` | `0` | `-1` | Особый процент прочности для трезубцев (`TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `Integer` | `0` | `-1` | Особый процент прочности для булав (`MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `Integer` | `0` | `-1` | Особый процент прочности для луков (`BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `Integer` | `0` | `-1` | Особый процент прочности для арбалетов (`CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `Integer` | `0` | `-1` | Особый процент прочности для щитов (`ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `Integer` | `0` | `-1` | Процент родительской категории для всех инструментов. |
| 11 | `ig:dm_percent_pickaxes` | `Integer` | `0` | `-1` | Особый процент прочности для кирок (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `Integer` | `0` | `-1` | Особый процент прочности для топоров (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `Integer` | `0` | `-1` | Особый процент прочности для лопат (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `Integer` | `0` | `-1` | Особый процент прочности для мотыг (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `Integer` | `0` | `-1` | Особый процент прочности для ножниц (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `Integer` | `0` | `-1` | Особый процент прочности для удочек (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `Integer` | `0` | `-1` | Особый процент прочности для кистей (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `Integer` | `0` | `-1` | Особый процент прочности для огнива (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `Integer` | `0` | `-1` | Процент родительской категории для всех элементов брони. |
| 20 | `ig:dm_percent_helmets` | `Integer` | `0` | `-1` | Особый процент прочности для шлемов (`#c:helmets`, слот головы). |
| 21 | `ig:dm_percent_chestplates` | `Integer` | `0` | `-1` | Особый процент прочности для нагрудников (`#c:chestplates`, слот нагрудника). |
| 22 | `ig:dm_percent_leggings` | `Integer` | `0` | `-1` | Особый процент прочности для поножей (`#c:leggings`, слот поножей). |
| 23 | `ig:dm_percent_boots` | `Integer` | `0` | `-1` | Особый процент прочности для ботинок (`#c:boots`, слот ботинок). |
| 24 | `ig:dm_percent_elytra` | `Integer` | `0` | `-1` | Особый процент прочности для элитр (`Items.ELYTRA`, `GLIDER`). |

---

### 2. Игровые правила режима бога (бесконечность)
При включении (`true`) предметы этой категории получают $0$ урона и никогда не ломаются.

| # | Идентификатор GameRule | Тип | По умолчанию | Описание |
| :-: | :--- | :---: | :---: | :--- |
| 25 | `ig:dm_infinity_global` | `Boolean` | `false` | Глобальный режим бога для всех повреждаемых предметов в игре. |
| 26 | `ig:dm_infinity_weapons` | `Boolean` | `false` | Режим бога для всего оружия. |
| 27 | `ig:dm_infinity_swords` | `Boolean` | `false` | Режим бога для мечей. |
| 28 | `ig:dm_infinity_spears` | `Boolean` | `false` | Режим бога для копий. |
| 29 | `ig:dm_infinity_tridents` | `Boolean` | `false` | Режим бога для трезубцев. |
| 30 | `ig:dm_infinity_maces` | `Boolean` | `false` | Режим бога для булав. |
| 31 | `ig:dm_infinity_bows` | `Boolean` | `false` | Режим бога для луков. |
| 32 | `ig:dm_infinity_crossbows` | `Boolean` | `false` | Режим бога для арбалетов. |
| 33 | `ig:dm_infinity_shields` | `Boolean` | `false` | Режим бога для щитов. |
| 34 | `ig:dm_infinity_tools` | `Boolean` | `false` | Режим бога для всех инструментов. |
| 35 | `ig:dm_infinity_pickaxes` | `Boolean` | `false` | Режим бога для кирок. |
| 36 | `ig:dm_infinity_axes` | `Boolean` | `false` | Режим бога для топоров. |
| 37 | `ig:dm_infinity_shovels` | `Boolean` | `false` | Режим бога для лопат. |
| 38 | `ig:dm_infinity_hoes` | `Boolean` | `false` | Режим бога для мотыг. |
| 39 | `ig:dm_infinity_shears` | `Boolean` | `false` | Режим бога для ножниц. |
| 40 | `ig:dm_infinity_fishing_rods` | `Boolean` | `false` | Режим бога для удочек. |
| 41 | `ig:dm_infinity_brushes` | `Boolean` | `false` | Режим бога для кистей. |
| 42 | `ig:dm_infinity_flint_and_steel` | `Boolean` | `false` | Режим бога для огнива. |
| 43 | `ig:dm_infinity_armor` | `Boolean` | `false` | Режим бога для всей брони. |
| 44 | `ig:dm_infinity_helmets` | `Boolean` | `false` | Режим бога для шлемов. |
| 45 | `ig:dm_infinity_chestplates` | `Boolean` | `false` | Режим бога для нагрудников. |
| 46 | `ig:dm_infinity_leggings` | `Boolean` | `false` | Режим бога для поножей. |
| 47 | `ig:dm_infinity_boots` | `Boolean` | `false` | Режим бога для ботинок. |
| 48 | `ig:dm_infinity_elytra` | `Boolean` | `false` | Режим бога для элитр. |

---

### 3. Игровые правила стеклянного режима (одноразовое использование)
При включении (`true`) предметы этой категории разрушаются после одного удара.

| # | Идентификатор GameRule | Тип | По умолчанию | Описание |
| :-: | :--- | :---: | :---: | :--- |
| 49 | `ig:dm_single_use_global` | `Boolean` | `false` | Глобальный стеклянный режим (одноразовое использование) для всех предметов. |
| 50 | `ig:dm_single_use_weapons` | `Boolean` | `false` | Одноразовое использование для всего оружия. |
| 51 | `ig:dm_single_use_swords` | `Boolean` | `false` | Одноразовое использование для мечей. |
| 52 | `ig:dm_single_use_spears` | `Boolean` | `false` | Одноразовое использование для копий. |
| 53 | `ig:dm_single_use_tridents` | `Boolean` | `false` | Одноразовое использование для трезубцев. |
| 54 | `ig:dm_single_use_maces` | `Boolean` | `false` | Одноразовое использование для булав. |
| 55 | `ig:dm_single_use_bows` | `Boolean` | `false` | Одноразовое использование для луков. |
| 56 | `ig:dm_single_use_crossbows` | `Boolean` | `false` | Одноразовое использование для арбалетов. |
| 57 | `ig:dm_single_use_shields` | `Boolean` | `false` | Одноразовое использование для щитов. |
| 58 | `ig:dm_single_use_tools` | `Boolean` | `false` | Одноразовое использование для всех инструментов. |
| 59 | `ig:dm_single_use_pickaxes` | `Boolean` | `false` | Одноразовое использование для кирок. |
| 60 | `ig:dm_single_use_axes` | `Boolean` | `false` | Одноразовое использование для топоров. |
| 61 | `ig:dm_single_use_shovels` | `Boolean` | `false` | Одноразовое использование для лопат. |
| 62 | `ig:dm_single_use_hoes` | `Boolean` | `false` | Одноразовое использование для мотыг. |
| 63 | `ig:dm_single_use_shears` | `Boolean` | `false` | Одноразовое использование для ножниц. |
| 64 | `ig:dm_single_use_fishing_rods` | `Boolean` | `false` | Одноразовое использование для удочек. |
| 65 | `ig:dm_single_use_brushes` | `Boolean` | `false` | Одноразовое использование для кистей. |
| 66 | `ig:dm_single_use_flint_and_steel` | `Boolean` | `false` | Одноразовое использование для огнива. |
| 67 | `ig:dm_single_use_armor` | `Boolean` | `false` | Одноразовое использование для всей брони. |
| 68 | `ig:dm_single_use_helmets` | `Boolean` | `false` | Одноразовое использование для шлемов. |
| 69 | `ig:dm_single_use_chestplates` | `Boolean` | `false` | Одноразовое использование для нагрудников. |
| 70 | `ig:dm_single_use_leggings` | `Boolean` | `false` | Одноразовое использование для поножей. |
| 71 | `ig:dm_single_use_boots` | `Boolean` | `false` | Одноразовое использование для ботинок. |
| 72 | `ig:dm_single_use_elytra` | `Boolean` | `false` | Одноразовое использование для элитр. |

---

### 4. Игровые правила отображения и динамических предметов

| Идентификатор GameRule | Тип | По умолчанию | Описание |
| :--- | :---: | :---: | :--- |
| `ig:dm_show_tooltip` | `Boolean` | `true` | Отображает строку бонуса прочности во всплывающей подсказке. |
| `ig:percent_<mod>_<item>` | `Integer` | `0` | Динамическое переопределение процента для предмета из мода (мин. `-1`). |
| `ig:infinity_<mod>_<item>` | `Boolean` | `false` | Динамическое переопределение Режима бога для предмета из мода. |
| `ig:single_use_<mod>_<item>` | `Boolean` | `false` | Динамическое переопределение стеклянного режима для предмета из мода. |

---

## ⚡ Команды настройки в игре

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

