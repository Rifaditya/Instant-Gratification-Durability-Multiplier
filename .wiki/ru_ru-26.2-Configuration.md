# Конфигурация и графический интерфейс (26.2)

| Системный параметр | Значение |
| :--- | :--- |
| **Путь к файлу конфигурации** | `config/durability-multiplier.json` |
| **Версия конфигурации** | `2` (автоматическая миграция с v1) |
| **Провайдеры GUI** | Cloth Config (`me.shedaniel.cloth:cloth-config-fabric`) и ModMenu |
| **Класс конфигурации** | `net.instantgratification.durabilitymultiplier.config.DurabilityConfig` |
| **Помощник GUI** | `ClothConfigScreenHelper` и `ModMenuIntegration` |
| **Закон приоритета** | Файл конфигурации задает **ТОЛЬКО ЗНАЧЕНИЯ ПО УМОЛЧАНИЮ ДЛЯ НОВЫХ МИРОВ**; активные миры используют GameRules |

---

## ⚙️ Структура файла конфигурации (`config/durability-multiplier.json`)

Файл конфигурации определяет базовые параметры и значения по умолчанию для всех вновь создаваемых одиночных миров и серверов. Он поддерживает проценты прочности, Режим бога (бесконечность), Одноразовый стеклянный режим, настраиваемое форматирование подсказок и переопределения для модовых предметов.

```json
{
  "configVersion": 2,
  
  "percentGlobal": 200,
  "percentWeapons": 0,
  "percentSwords": 0,
  "percentSpears": 0,
  "percentTridents": 0,
  "percentMaces": 0,
  "percentBows": 0,
  "percentCrossbows": 0,
  "percentTools": 0,
  "percentPickaxes": 0,
  "percentAxes": 0,
  "percentShovels": 0,
  "percentHoes": 0,
  "percentShears": 0,
  "percentFishingRods": 0,
  "percentBrushes": 0,
  "percentFlintAndSteel": 0,
  "percentArmor": 0,
  "percentHelmets": 0,
  "percentChestplates": 0,
  "percentLeggings": 0,
  "percentBoots": 0,
  "percentElytra": 0,
  "percentShields": 0,
  
  "infinityGlobal": false,
  "infinityWeapons": false,
  "infinitySwords": false,
  "infinitySpears": false,
  "infinityTridents": false,
  "infinityMaces": false,
  "infinityBows": false,
  "infinityCrossbows": false,
  "infinityTools": false,
  "infinityPickaxes": false,
  "infinityAxes": false,
  "infinityShovels": false,
  "infinityHoes": false,
  "infinityShears": false,
  "infinityFishingRods": false,
  "infinityBrushes": false,
  "infinityFlintAndSteel": false,
  "infinityArmor": false,
  "infinityHelmets": false,
  "infinityChestplates": false,
  "infinityLeggings": false,
  "infinityBoots": false,
  "infinityElytra": false,
  "infinityShields": false,
  
  "singleUseGlobal": false,
  "singleUseWeapons": false,
  "singleUseSwords": false,
  "singleUseSpears": false,
  "singleUseTridents": false,
  "singleUseMaces": false,
  "singleUseBows": false,
  "singleUseCrossbows": false,
  "singleUseTools": false,
  "singleUsePickaxes": false,
  "singleUseAxes": false,
  "singleUseShovels": false,
  "singleUseHoes": false,
  "singleUseShears": false,
  "singleUseFishingRods": false,
  "singleUseBrushes": false,
  "singleUseFlintAndSteel": false,
  "singleUseArmor": false,
  "singleUseHelmets": false,
  "singleUseChestplates": false,
  "singleUseLeggings": false,
  "singleUseBoots": false,
  "singleUseElytra": false,
  "singleUseShields": false,
  
  "showTooltip": true,
  "tooltipFormat": "ADAPTIVE",
  
  "forcedItems": [],
  "forcedPercentages": {},
  "forcedInfinities": {},
  "forcedSingleUses": {}
}
```

---

## 🔄 Система автоматического заполнения

Durability Multiplier включает автономный **Универсальный 3-уровневый сканер обнаружения**, который автоматически каталогизирует предметы из модов без ручного ввода данных:

1. **Сканирование при запуске**: При запуске клиента/сервера движок сканирует `BuiltInRegistries.ITEM`.
2. **Фильтр повреждаемости**: Предметы из сторонних модов (кроме `minecraft` и тегов `c`) проверяются на наличие `DataComponents.MAX_DAMAGE > 0`.
3. **Автозаполнение**: Обнаруженные предметы с прочностью автоматически добавляются в:
   * `"forcedItems"`: Added to the active item registry list.
   * `"forcedPercentages"`: Added with default value `0` (indicating the item dynamically inherits its category or global multiplier).
4. **Сохранение конфигурации**: Обновленные списки сохраняются в `config/durability-multiplier.json`, делая все модовые предметы доступными для редактирования в GUI Cloth Config / ModMenu и внутриигровых GameRules.

---

## 🛠️ Руководство по ручной настройке предметов

Авторы сборок, администраторы серверов и игроки могут вручную объявлять пользовательские правила в `config/durability-multiplier.json`:

### 1. `forcedItems` (Регистрация предметов)
Объявляет список идентификаторов предметов, распознаваемых модом.
```json
"forcedItems": [
  "techmod:plasma_cutter",
  "magicmod:staff_of_fire",
  "customweapons:obsidian_blade"
]
```

### 2. `forcedPercentages` (Проценты прочности отдельных предметов)
Назначает точные множители процентов прочности для конкретных предметов:
* `0`: Наследование от родительской категории или глобального множителя.
* `100`: Ванильный базовый уровень 100% (прочность 1x).
* `200`: Прочность 200% (увеличение срока службы в 2 раза).
* `50`: Прочность 50% (половина срока службы / износ в 2 раза быстрее).
* `-1`: Одноразовый стеклянный режим (ломается с первого удара).
```json
"forcedPercentages": {
  "techmod:plasma_cutter": 300,
  "customweapons:obsidian_blade": -1,
  "magicmod:training_wand": 50
}
```

### 3. `forcedInfinities` (Режим бога для отдельных предметов)
Предоставляет статус постоянной неразрушимости определенным предметам:
```json
"forcedInfinities": {
  "magicmod:creative_staff": true,
  "adminmod:ban_hammer": true
}
```

### 4. `forcedSingleUses` (Стеклянный режим для отдельных предметов)
Заставляет определенные предметы разрушаться после единичной потери прочности:
```json
"forcedSingleUses": {
  "techmod:disposable_cutter": true,
  "survivalplus:glass_dagger": true
}
```

---

## ⚡ Специальное значение `-1` (стеклянный режим) для опытных пользователей

Durability Multiplier включает специальное значение **`-1`** для процентов прочности:
* Установка любого процентного правила или поля конфигурации в `-1` (или любое отрицательное число) автоматически включает **Стеклянный режим** для этого предмета или категории.
* При активации предмет получает урон `maxDamage - damageValue` при первом ударе, снижая прочность до 0 и разрушаясь ровно за 1 использование.
* Это позволяет администраторам серверов и авторам сборок применять механику поломки с 1 удара через ползунки процентов или команды `/gamerule` без переключения отдельных логических правил.

---

## 🎨 Форматирование отображения подсказок

Опция `tooltipFormat` настраивает отображение бонусов прочности во всплывающих подсказках:

| Параметр формата | Пример вывода (200% / 2x) | Пример вывода (150% / 1.5x) | Описание |
| :--- | :--- | :--- | :--- |
| `"ADAPTIVE"` *(по умолчанию)* | `⟨2x Прочность мечей⟩` | `⟨150% Прочность мечей⟩` | Отображает целые множители для сотен процентов; в противном случае отображает проценты. |
| `"PERCENTAGE"` | `⟨200% Прочность мечей⟩` | `⟨150% Прочность мечей⟩` | Всегда отображает точное процентное значение. |
| `"MULTIPLIER"` | `⟨2x Прочность мечей⟩` | `⟨1.5x Прочность мечей⟩` | Всегда отображает форматированную строку коэффициента множителя. |

Установите `"showTooltip": false`, чтобы полностью скрыть индикаторы прочности.

---

## ⚠️ Важное предупреждение о приоритете конфигурации

> ⚠️ **Внимание**: Изменения, внесенные в `durability-multiplier.json` или в интерфейсе ModMenu, **определяют только базовые значения по умолчанию для вновь создаваемых миров**.
> 
> Для существующих активных миров каждый мир сохраняет собственное независимое состояние GameRule внутри данных мира (`level.dat`). Чтобы изменить настройки в активном мире, используйте команду `/gamerule` в игре или ванильный экран редактирования GameRules.

