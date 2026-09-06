# Устранение неполадок и частые вопросы (26.2)

| Тема системы | Сводка |
| :--- | :--- |
| **Приоритет** | GameRules имеют приоритет над конфигом в активных мирах; конфиг задает базу для новых миров |
| **Движок расчета** | Вероятностный перехват (ноль изменений NBT, ноль рассинхронизаций сохранений) |
| **Устойчивость к сбоям** | 100% защита от вылетов при удалении модов, отсутствии компонентов и изменении реестра |

---

## ❓ Часто задаваемые вопросы (FAQ)

### Q1: Почему изменения в ModMenu не влияют на мой текущий одиночный мир?
**Ответ**: В соответствии с **Законом приоритета**, изменения в `durability-multiplier.json` или ModMenu задают базовые значения **ТОЛЬКО ДЛЯ НОВЫХ МИРОВ**. Для изменения настроек в текущем мире используйте команду `/gamerule` (например, `/gamerule ig:dm_percent_tools 500`) или экран редактирования GameRules.

### Q2: Почему во всплывающей подсказке предмета не отображается процент или множитель?
**Ответ**:
1. Убедитесь, что предмет расходуемый и имеет шкалу прочности (`DataComponents.MAX_DAMAGE > 0`).
2. Проверьте, установлено ли `ig:dm_show_tooltip` в значение `true`.
3. Если установлено значение `100` (100% ванильная прочность), дополнительная строка не отображается ради чистоты интерфейса.

### Q3: Почему мой инструмент на 500% (5x) получил урон прочности всего через 2 удара?
**Ответ**: Durability Multiplier использует **вероятностный перехват урона** (тот же механизм, что и ванильные чары *Прочность*), гарантируя **100% безопасность мира**. При 500% (прочность 5x) каждая поломка блока имеет независимый **шанс 20% (1 из 5)** получить 1 урон и **80% шанс** поглотить его. Так как броски независимы, инструмент может получить урон через 2 или 8 ударов, но в целом прослужит ровно в 5 раз дольше (~7 805 сломанных блоков для алмазной кирки).

### Q4: Следует ли вводить десятичные дроби (например, 0.5 или 1.5) в GameRules?
**Ответ**: **Нет**. Minecraft GameRules принимают только целые числа (`int`). Всегда вводите целые проценты:
* `50` для 50% (половина прочности / износ в 2 раза быстрее)
* `100` для 100% (стандартная ванильная прочность 1x)
* `150` для 150% (увеличение прочности в 1.5 раза)
* `200` для 200% (удвоенная прочность 2x)
* `-1` для Одноразового режима (стеклянный режим / ломается за 1 удар)

### Q5: Работает ли Durability Multiplier с чарами Прочность (Unbreaking)?
**Ответ**: Да! Durability Multiplier масштабирует входящий урон **до** обработки ванильных чар. Кирка с чарами Прочность III при значении 200% (2x) прослужит примерно в $4 \times 2 = 8$ раз дольше ванильной кирки без чар.

### Q6: Как включить стеклянный режим (одноразовое использование) для предмета?
**Ответ**: Вы можете либо:
1. Установить правило одноразовости в true: `/gamerule ig:dm_single_use_swords true` (или `/gamerule ig:single_use_<mod>_<item> true`).
2. Использовать специальное значение **`-1`**: установите правило процента в `-1`, например `/gamerule ig:dm_percent_swords -1` или `/gamerule ig:percent_<mod>_<item> -1`.

---

## 🔍 Подробный разбор крайних случаев и жизненного цикла

### Крайний случай 1: Удаление мода и исчезновение предметов
Когда игрок удаляет мод, предметы которого были зарегистрированы в Durability Multiplier:
1. **Безопасность файла конфигурации**: Идентификаторы удаленных предметов безопасно сохраняются в `forcedItems` и `forcedPercentages` в `config/durability-multiplier.json`.
2. **Спящее состояние мира**: Все динамические правила (`ig:percent_<mod>_<item>`, `ig:infinity_<mod>_<item>`) в `level.dat` остаются в неактивном спящем состоянии.
3. **Ноль вылетов и повреждений**: Так как поиск предметов проходит через `BuiltInRegistries.ITEM.getKey(stack.getItem())`, игра никогда не обратится к отсутствующим классам. Ошибки `NullPointerException` или повреждение чанков исключены.
4. **Автоматическое восстановление**: Если мод будет установлен снова, все прежние настройки процентов, Режима бога и одноразовости **мгновенно привяжутся обратно** без перенастройки!
5. **Ручная очистка конфигурации (по желанию)**: Если вы хотите удалить записи о деинсталлированных модах:
   * Open `config/durability-multiplier.json` in a text editor.
   * Remove the deleted item ID string from the `"forcedItems"` list.
   * Remove the corresponding keys from `"forcedPercentages"`, `"forcedInfinities"`, and `"forcedSingleUses"`.
   * Save the file.

---

### Крайний случай 2: Строгая фильтрация прочности (`MAX_DAMAGE > 0`)
Почему мебель из модов (например, шкафы/стулья Macaw's Furniture), строительные блоки, еда или материалы не отображаются в GameRules или `durability-multiplier.json`?
* Durability Multiplier строго проверяет `DataComponents.MAX_DAMAGE > 0` перед регистрацией любого предмета.
* Предметы без компонентов прочности (блоки, еда, слитки, семена) отсеиваются за $0.0001\mu\text{s}$ во время запуска.
* Это предотвращает загрязнение пространства имен и сохраняет автодополнение GameRules быстрым и чистым.

---

### Крайний случай 3: Полная иерархия вычисления и приоритета
Когда предмет получает урон прочности, результат определяется следующей строгой иерархией вычислений:

$$\text{God Mode (Infinity)} \longrightarrow \text{Single-Use (Glass Mode)} \longrightarrow \text{Per-Item Override} \longrightarrow \text{Subcategory} \longrightarrow \text{Parent Category} \longrightarrow \text{Global} \longrightarrow \text{100\% Vanilla}$$

1. **Проверка Режима бога (неразрушимости)**:
   * Per-Item God Mode (`ig:infinity_<mod>_<item>` / `forcedInfinities`)
   * Subcategory God Mode (`ig:dm_infinity_pickaxes`, `ig:dm_infinity_swords`, etc.)
   * Parent Category God Mode (`ig:dm_infinity_tools`, `ig:dm_infinity_weapons`, `ig:dm_infinity_armor`)
   * Global God Mode (`ig:dm_infinity_global`)
   * *If any is `true` $\rightarrow$ Damage = $0$ (Unbreakable).*
2. **Проверка стеклянного режима (одноразовости)**:
   * Effective percentage $\le -1$ (`-1` Glass Mode Sentinel)
   * Per-Item Single-Use (`ig:single_use_<mod>_<item>` / `forcedSingleUses`)
   * Subcategory Single-Use (`ig:dm_single_use_pickaxes`, `ig:dm_single_use_swords`, etc.)
   * Parent Category Single-Use (`ig:dm_single_use_tools`, `ig:dm_single_use_weapons`, `ig:dm_single_use_armor`)
   * Global Single-Use (`ig:dm_single_use_global`)
   * *If active $\rightarrow$ Item loses all remaining durability in 1 hit.*
3. **Расчет процентного масштабирования**:
   * Per-Item Override (`ig:percent_<mod>_<item>` / `forcedPercentages`) if $\neq 0$
   * Subcategory Percentage (`ig:dm_percent_pickaxes`, `ig:dm_percent_swords`, etc.) if $\neq 0$
   * Parent Category Percentage (`ig:dm_percent_tools`, `ig:dm_percent_weapons`, `ig:dm_percent_armor`) if $\neq 0$
   * Global Percentage (`ig:dm_percent_global`) if $\neq 0$
   * Vanilla Fallback: $100\%$ baseline.

