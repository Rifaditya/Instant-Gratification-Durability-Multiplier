# ModVersionGuard и безопасность во время выполнения (26.2)

| Параметр | Значение |
| :--- | :--- |
| **Класс защиты** | `net.instantgratification.durabilitymultiplier.util.ModVersionGuard` |
| **Точка вызова** | `DurabilityMultiplierFabric.onInitialize()` |
| **Проверяемый класс** | `net.minecraft.world.entity.EntityTypes` (индикатор MC 26.2+) |
| **Загрузчик классов** | `Thread.currentThread().getContextClassLoader()` (Knot ClassLoader) |
| **Назначение** | Предотвращение повреждения мира при запуске в несовместимой среде |

---

## 🛡️ Как ModVersionGuard защищает сохранения

Обновления Minecraft в эпоху Modern Sovereign сопровождаются быстрой эволюцией API. Если мод, скомпилированный для MC 26.2, будет запущен в несовместимой среде, ошибки загрузки классов могут привести к повреждению предметов или мира.

`ModVersionGuard` выполняет предварительную проверку без зависимостей во время `onInitialize()` до инициализации GameRules, миксинов и конфигов:

```java
ModVersionGuard.checkClass("Durability Multiplier", "net.minecraft.world.entity.EntityTypes");
```

Если требуемый класс отсутствует в загрузчике Knot ClassLoader, игра немедленно останавливается с информативным баннером сбоя:

```
=====================================================================
 [PRE-RELEASE / VERSION GUARD WARNING] Durability Multiplier
---------------------------------------------------------------------
 CRITICAL: Incompatible Minecraft Game Runtime or Missing Class!
 Required Class : net.minecraft.world.entity.EntityTypes
 Status         : UNRESOLVED AT RUNTIME

 Safety Protection:
 Execution halted to prevent unreleased/incompatible build deployment
 or broken world state save corruption.

 Troubleshooting Steps:
 1. Verify target Minecraft version (26.2+ release drop).
 2. Ensure all required dependencies (Fabric API, DasikLibrary) are loaded.
 3. Build/Download a verified matching release JAR from Modrinth/CurseForge.
=====================================================================
```
