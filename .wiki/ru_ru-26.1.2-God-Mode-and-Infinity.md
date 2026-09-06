# Режим бога и бесконечность (26.1.2)

| Системный параметр | Значение |
| :--- | :--- |
| **Глобальное правило бесконечности** | `ig:dm_infinity_global` |
| **Состояние по умолчанию** | `false` (отключено) |
| **Перехват урона** | Входящий урон отменяется в `HEAD` (наносится $0$ урона) |
| **Стиль всплывающей подсказки** | `✦ UNBREAKABLE` (золотой, жирный) |
| **Приоритет** | Абсолютный (проверяется перед любым множителем) |

---

## ⚡ Обзор и механики

**Режим бога (бесконечность)** предоставляет полную неуязвимость предметам в выбранных категориях. При активности Режима бога любое событие потери прочности полностью перехватывается и отменяется в `ItemStackDurabilityMixin`, предотвращая получение урона или поломку.

### Отличие от ванильного компонента Unbreakable
* Ванильный компонент `Unbreakable` должен применяться к отдельным стакам предметов через команды (`/give @p diamond_sword[unbreakable={}]`).
* Режим бога в Durability Multiplier действует **на уровне всего мира и категорий**: каждый инструмент, оружие или броня в мире ведет себя как неразрушимый предмет без изменения NBT или компонентов.

---

## 🛡️ 24 правила режима бога

| # | Ключ GameRule | Имя категории | Целевые предметы | По умолчанию |
| :-: | :--- | :--- | :--- | :-: |
| 1 | `ig:dm_infinity_global` | **Global God Mode** | All damageable items in the game | `false` |
| 2 | `ig:dm_infinity_weapons` | **Weapons God Mode** | All weapons (swords, spears, tridents, maces, bows, crossbows) | `false` |
| 3 | `ig:dm_infinity_swords` | **Swords God Mode** | `#minecraft:swords`, `#c:swords` | `false` |
| 4 | `ig:dm_infinity_spears` | **Spears God Mode** | `#minecraft:spears`, `#c:spears` | `false` |
| 5 | `ig:dm_infinity_tridents` | **Tridents God Mode** | `Items.TRIDENT`, `TridentItem`, `#c:tridents` | `false` |
| 6 | `ig:dm_infinity_maces` | **Maces God Mode** | `Items.MACE`, `MaceItem`, `#c:maces` | `false` |
| 7 | `ig:dm_infinity_bows` | **Bows God Mode** | `Items.BOW`, `BowItem`, `#c:bows` | `false` |
| 8 | `ig:dm_infinity_crossbows` | **Crossbows God Mode** | `Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows` | `false` |
| 9 | `ig:dm_infinity_shields` | **Shields God Mode** | `Items.SHIELD`, `ShieldItem`, `#c:shields` | `false` |
| 10 | `ig:dm_infinity_tools` | **Tools God Mode** | Parent category for all Tools | `false` |
| 11 | `ig:dm_infinity_pickaxes` | **Pickaxes God Mode** | `PickaxeItem`, `#c:pickaxes` | `false` |
| 12 | `ig:dm_infinity_axes` | **Axes God Mode** | `AxeItem`, `#c:axes` | `false` |
| 13 | `ig:dm_infinity_shovels` | **Shovels God Mode** | `ShovelItem`, `#c:shovels` | `false` |
| 14 | `ig:dm_infinity_hoes` | **Hoes God Mode** | `HoeItem`, `#c:hoes` | `false` |
| 15 | `ig:dm_infinity_shears` | **Shears God Mode** | `ShearsItem`, `#c:shears` | `false` |
| 16 | `ig:dm_infinity_fishing_rods` | **Fishing Rods God Mode** | `FishingRodItem` | `false` |
| 17 | `ig:dm_infinity_brushes` | **Brushes God Mode** | `BrushItem` | `false` |
| 18 | `ig:dm_infinity_flint_and_steel` | **Flint and Steel God Mode** | `FlintAndSteelItem` | `false` |
| 19 | `ig:dm_infinity_armor` | **Armor God Mode** | Parent category for all Armor pieces | `false` |
| 20 | `ig:dm_infinity_helmets` | **Helmets God Mode** | `#minecraft:head_armor`, `#c:helmets` | `false` |
| 21 | `ig:dm_infinity_chestplates` | **Chestplates God Mode** | `#minecraft:chest_armor`, `#c:chestplates` | `false` |
| 22 | `ig:dm_infinity_leggings` | **Leggings God Mode** | `#minecraft:leg_armor`, `#c:leggings` | `false` |
| 23 | `ig:dm_infinity_boots` | **Boots God Mode** | `#minecraft:foot_armor`, `#c:boots` | `false` |
| 24 | `ig:dm_infinity_elytra` | **Elytra God Mode** | `Items.ELYTRA`, `DataComponents.GLIDER` | `false` |

---

## 👑 Порядок разрешения режима бога

`DurabilityHelper.isInfinite(ServerLevel, ItemStack)` проверяет:

```
[1. Per-Item Dynamic Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[2. Specific Category Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[3. Weapons/Tools/Armor Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[4. Global Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[Proceed to Single-Use / Multiplier Calculation]
```

Режим бога имеет **абсолютный приоритет** над любыми множителями или одноразовым режимом. Если `ig:dm_infinity_tools = true`, инструменты никогда не получат урон независимо от значения `ig:dm_percent_tools`.

