# Architektur & Mixin-Deskriptoren (26.2)

| Architektureigenschaft | Wert |
| :--- | :--- |
| **Wurzelpaket** | `net.instantgratification.durabilitymultiplier` |
| **Kompatibilitätsstufe** | `JAVA_25` |
| **Mixin-Konfiguration** | `durability-multiplier.mixins.json` |
| **Standard-Injektor-Anforderung** | `1` |
| **Wiedereintrittsschutz** | `ThreadLocal<Boolean> dm$processing` |

---

## 🌳 ASCII-Pakethierarchie

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

## 💉 Vollständige Aufschlüsselung der Mixin-Ziele

### 1. `ItemStackDurabilityMixin`
* **Zielklasse**: `net.minecraft.world.item.ItemStack`
* **Zielmethode**: `hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V`
* **Injektionspunkt**: `@At("HEAD")`, `cancellable = true`
* **Handler-Methode**: `dm$hurtAndBreak(int amount, ServerLevel level, @Nullable ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci)`
* **Design-Begründung**: Zentraler Trichter für jeglichen Gegenstandsschaden in Minecraft. Abgesichert mit `ThreadLocal<Boolean> dm$processing`, um Endlosschleifen bei erneutem Aufruf von `hurtAndBreak` mit reduziertem Schaden zu verhindern.

### 2. `ItemStackTooltipMixin`
* **Zielklasse**: `net.minecraft.world.item.ItemStack`
* **Zielmethode**: `addDetailsToTooltip(Item.TooltipContext, TooltipDisplay, Player, TooltipFlag, Consumer<Component>)V`
* **Injektionspunkt**: `@At("TAIL")`
* **Handler-Methode**: `dm$addDurabilityTooltip(...)`
* **Design-Begründung**: Ergänzt Gegenstands-Tooltips um goldenes fettes `✦ UNBREAKABLE`, graues `⟨SINGLE-USE⟩` oder graues `⟨Nx Kategorie-Haltbarkeit⟩` / `⟨P% Kategorie-Haltbarkeit⟩`.

### 3. `GameRulesMixin`
* **Zielklasse**: `net.minecraft.world.level.gamerules.GameRules`
* **Zielmethode**: `set(GameRule<T>, T, MinecraftServer)V`
* **Injektionspunkt**: `@At("TAIL")`
* **Handler-Methode**: `onSet(GameRule<T> key, T value, @Nullable MinecraftServer server, CallbackInfo ci)`
* **Design-Begründung**: Bei Änderung einer Regel in der Kategorie `DURABILITY_MULTIPLIER` wird `DurabilityNetworking.syncToAll(server)` ausgelöst, um aktualisierte Werte in Echtzeit an alle Clients zu senden.

---

## 🔄 Dynamisches Scannen der Mod-Gegenstandsregistrierung

Die dynamische Gegenstandsregistrierung wird durch den `DynamicRegistryScanner` der **`DasikLibrary`** bereitgestellt:
* **Hook-Methode**: `DynamicRegistryScanner.subscribe(BuiltInRegistries.ITEM, DurabilityRules::isItemDamageable, (id, item) -> { ... })`
* **Lebenszyklus**: Universeller 3-Stufen-Erkennungsscanner (Startprüfung, Live-Rückrufe während des Ladens externer Mods und Sicherheitsprüfung beim Serverstart).
* **Keine eigenen Registry-Mixins**: Ersetzt manuelle Registry-Mixins durch standardmäßige, absturzsichere und classloader-sichere Event-Callbacks.
