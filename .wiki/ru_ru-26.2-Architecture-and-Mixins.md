# Архитектура и дескрипторы миксинов (26.2)

| Архитектурное свойство | Значение |
| :--- | :--- |
| **Корневой пакет** | `net.instantgratification.durabilitymultiplier` |
| **Уровень совместимости** | `JAVA_25` |
| **Конфиг миксинов** | `durability-multiplier.mixins.json` |
| **Требование инжектора по умолчанию** | `1` |
| **Защита от повторного входа** | `ThreadLocal<Boolean> dm$processing` |

---

## 🌳 ASCII-иерархия пакетов

```
net.instantgratification.durabilitymultiplier/
├── DurabilityHelper.java               # Stateless damage reduction & classification engine
├── DurabilityMultiplier.java           # Common mod initializer & logger
├── DurabilityMultiplierFabric.java     # Fabric main entrypoint
├── DurabilityMultiplierFabricClient.java # Client entrypoint & network receiver
├── config/
│   ├── ClothConfigScreenHelper.java    # Optional Cloth Config GUI builder
│   ├── DurabilityConfig.java           # JSON configuration loader & save handler
│   └── ModMenuIntegration.java         # ModMenu entrypoint hook
├── mixin/
│   ├── GameRulesMixin.java             # Intercepts GameRules.set() for network sync
│   ├── ItemStackDurabilityMixin.java   # Intercepts hurtAndBreak() for damage reduction
│   └── ItemStackTooltipMixin.java      # Intercepts addDetailsToTooltip() for status lines
├── network/
│   ├── DurabilityClientState.java      # Client-side cache of 73 GameRules & dynamic mod rules
│   ├── DurabilityNetworking.java       # S2C packet registration & player sync logic
│   └── DurabilityPayload.java          # CustomPacketPayload record definition
└── registry/
    └── DurabilityRules.java            # GameRuleCategory & 73 GameRules definitions
```

---

## 💉 Полный разбор целей и точек внедрения Mixin

### 1. `ItemStackDurabilityMixin`
* **Целевой класс**: `net.minecraft.world.item.ItemStack`
* **Целевой метод**: `hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V`
* **Точка внедрения**: `@At("HEAD")`, `cancellable = true`
* **Метод-обработчик**: `dm$hurtAndBreak(int amount, ServerLevel level, @Nullable ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci)`
* **Архитектурный смысл**: Единая точка воронки для всего урона предметов в Minecraft. Защита повторного входа с `ThreadLocal<Boolean> dm$processing` предотвращает бесконечные циклы при повторном вызове `hurtAndBreak` с уменьшенным уроном.

### 2. `ItemStackTooltipMixin`
* **Целевой класс**: `net.minecraft.world.item.ItemStack`
* **Целевой метод**: `addDetailsToTooltip(Item.TooltipContext, TooltipDisplay, Player, TooltipFlag, Consumer<Component>)V`
* **Точка внедрения**: `@At("TAIL")`
* **Метод-обработчик**: `dm$addDurabilityTooltip(...)`
* **Архитектурный смысл**: Добавляет золотую жирную надпись `✦ UNBREAKABLE`, серую `⟨SINGLE-USE⟩` или серую `⟨Nx Прочность категории⟩` / `⟨P% Прочность категории⟩` к подсказкам предметов.

### 3. `GameRulesMixin`
* **Целевой класс**: `net.minecraft.world.level.gamerules.GameRules`
* **Целевой метод**: `set(GameRule<T>, T, MinecraftServer)V`
* **Точка внедрения**: `@At("TAIL")`
* **Метод-обработчик**: `onSet(GameRule<T> key, T value, @Nullable MinecraftServer server, CallbackInfo ci)`
* **Архитектурный смысл**: При изменении правила в категории `DURABILITY_MULTIPLIER` запускает `DurabilityNetworking.syncToAll(server)` для отправки обновленных значений всем клиентам в реальном времени.

---

## 🔄 Динамическое сканирование реестра предметов из модов

Динамическая регистрация предметов работает на основе `DynamicRegistryScanner` из **`DasikLibrary`**:
* **Метод перехвата**: `DynamicRegistryScanner.subscribe(BuiltInRegistries.ITEM, DurabilityRules::isItemDamageable, (id, item) -> { ... })`
* **Жизненный цикл**: Универсальный 3-уровневый сканер обнаружения (сканирование при запуске, живые коллбэки при загрузке модов и защитное сканирование при старте сервера).
* **Ноль пользовательских миксинов реестра**: Заменяет ручные миксины реестра стандартными безопасными для загрузчика классов коллбэками событий.
