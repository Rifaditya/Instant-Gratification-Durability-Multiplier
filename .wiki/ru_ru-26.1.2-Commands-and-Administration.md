# Команды и администрирование (26.1.2)

| Административная система | Детали |
| :--- | :--- |
| **Командный движок** | Ванильная система команд Brigadier `/gamerule` Minecraft |
| **Пространство имен** | Префикс `ig:` для всех правил |
| **Уровень разрешений** | Уровень 2 (OP / включенные читы в одиночной игре) |
| **Управление через GUI** | Поддерживается через экран GameRules и конфигурацию ModMenu |
| **Политика отсутствия** | **Ноль пользовательских поддеревьев команд Brigadier** по проекту |

---

## ⚡ Рабочий процесс внутриигрового администрирования

Durability Multiplier полностью опирается на ванильные команды `/gamerule`. Никаких пользовательских команд (таких как `/durability set` или `/durability reload`) не добавляется, что гарантирует 100% нативную совместимость с командными блоками, функциями, правами доступа и датапаками.

### Распространенные административные задачи

#### 1. Настройка стандартных усилений выживания
```mcfunction
# Double all items globally (200% durability - Default)
/gamerule ig:dm_percent_global 200

# Give mining tools a 5x (500%) lifespan
/gamerule ig:dm_percent_tools 500

# Give weapons a 3x (300%) lifespan
/gamerule ig:dm_percent_weapons 300
```

#### 2. Настройка параметров боя и PvP на сервере
```mcfunction
# Keep armor at vanilla durability (100%) to prevent overly tanky players
/gamerule ig:dm_percent_armor 100

# Give weapons 2x (200%) durability
/gamerule ig:dm_percent_swords 200
/gamerule ig:dm_percent_bows 200
```

#### 3. Включение режима «Творческое выживание» (неломаемые элитры и инструменты)
```mcfunction
# Unbreakable Elytra for limitless exploration
/gamerule ig:dm_infinity_elytra true

# Unbreakable Tools for building mega-projects
/gamerule ig:dm_infinity_tools true
```

#### 4. Настройка динамических предметов из модов
```mcfunction
# Give a custom modded drill 400% durability
/gamerule ig:percent_techmod_titanium_drill 400

# Make a modded staff unbreakable
/gamerule ig:infinity_magicmod_staff_of_fire true

# Set a modded dagger to Single-Use using the -1 sentinel
/gamerule ig:percent_customweapons_glass_dagger -1
```

#### 5. Скрытие текста всплывающих подсказок
```mcfunction
# Hide durability multiplier info from item tooltips
/gamerule ig:dm_show_tooltip false
```

