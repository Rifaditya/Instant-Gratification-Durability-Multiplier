# Множители прочности и проценты (26.2)

Durability Multiplier заменяет ванильную механику фиксированного износа динамическим **движком масштабирования процентов**, который поддерживает как увеличение прочности (например, 200% = 2x, 500% = 5x), так и штрафы за износ (например, 50% = 0.5x, 25% = 0.25x).

---

## ⚙️ Основные процентные игровые правила

| # | Идентификатор GameRule | По умолчанию | Целевая категория / Описание |
| :-: | :--- | :---: | :--- |
| 1 | `ig:dm_percent_global` | `200` | Глобальный процент, применяемый ко всем повреждаемым предметам. |
| 2 | `ig:dm_percent_weapons` | `0` | Родительское переопределение для всего оружия (мечи, копья, трезубцы, булавы, луки, арбалеты). |
| 3 | `ig:dm_percent_swords` | `0` | Особый процент прочности для мечей (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `0` | Особый процент прочности для копий (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `0` | Особый процент прочности для трезубцев (`Items.TRIDENT`, `TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `0` | Особый процент прочности для булав (`Items.MACE`, `MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `0` | Особый процент прочности для луков (`Items.BOW`, `BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `0` | Особый процент прочности для арбалетов (`Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `0` | Особый процент прочности для щитов (`Items.SHIELD`, `ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `0` | Процент родительской категории для всех инструментов. |
| 11 | `ig:dm_percent_pickaxes` | `0` | Особый процент прочности для кирок (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `0` | Особый процент прочности для топоров (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `0` | Особый процент прочности для лопат (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `0` | Особый процент прочности для мотыг (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `0` | Особый процент прочности для ножниц (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `0` | Особый процент прочности для удочек (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `0` | Особый процент прочности для кистей (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `0` | Особый процент прочности для огнива (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `0` | Процент родительской категории для всех элементов брони. |
| 20 | `ig:dm_percent_helmets` | `0` | Особый процент прочности для шлемов (`#minecraft:head_armor`, `#c:helmets`). |
| 21 | `ig:dm_percent_chestplates` | `0` | Особый процент прочности для нагрудников (`#minecraft:chest_armor`, `#c:chestplates`). |
| 22 | `ig:dm_percent_leggings` | `0` | Особый процент прочности для поножей (`#minecraft:leg_armor`, `#c:leggings`). |
| 23 | `ig:dm_percent_boots` | `0` | Особый процент прочности для ботинок (`#minecraft:foot_armor`, `#c:boots`). |
| 24 | `ig:dm_percent_elytra` | `0` | Особый процент прочности для элитр (`Items.ELYTRA`, `DataComponents.GLIDER`). |

> [!NOTE]
> Правила переопределения, установленные в `0`, автоматически откатываются к родительской категории или глобальному значению. Установка `-1` активирует **Стеклянный режим (одноразовое использование)**.

---

## 🔒 100% безопасность сохранений мира
Durability Multiplier **не** изменяет NBT предметов или `DataComponents.MAX_DAMAGE` в вашем сохранении. Все масштабирование выполняется динамически при расчете урона, гарантируя нулевое повреждение мира и отсутствие остаточных данных при удалении мода.
