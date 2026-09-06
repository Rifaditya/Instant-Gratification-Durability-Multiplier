# アーキテクチャとMixin記述子 (26.2)

| アーキテクチャ特性 | 値 |
| :--- | :--- |
| **ルートパッケージ** | `net.instantgratification.durabilitymultiplier` |
| **互換性レベル** | `JAVA_25` |
| **Mixin設定** | `durability-multiplier.mixins.json` |
| **デフォルト注入必須数** | `1` |
| **再突入防止ガード** | `ThreadLocal<Boolean> dm$processing` |

---

## 🌳 ASCIIパッケージ階層

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

## 💉 Mixinターゲットの詳細解説

### 1. `ItemStackDurabilityMixin`
* **対象クラス**: `net.minecraft.world.item.ItemStack`
* **対象メソッド**: `hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V`
* **注入ポイント**: `@At("HEAD")`, `cancellable = true`
* **ハンドラメソッド**: `dm$hurtAndBreak(int amount, ServerLevel level, @Nullable ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci)`
* **設計意図**: 全アイテムダメージの集約ポイント。再計算後のダメージで`hurtAndBreak`を再呼び出しする際の無限ループを防ぐため、`ThreadLocal<Boolean> dm$processing`で再突入をガードしています。

### 2. `ItemStackTooltipMixin`
* **対象クラス**: `net.minecraft.world.item.ItemStack`
* **対象メソッド**: `addDetailsToTooltip(Item.TooltipContext, TooltipDisplay, Player, TooltipFlag, Consumer<Component>)V`
* **注入ポイント**: `@At("TAIL")`
* **ハンドラメソッド**: `dm$addDurabilityTooltip(...)`
* **設計意図**: ホバーツールチップに金字太字の`✦ UNBREAKABLE`、灰色の`⟨SINGLE-USE⟩`、または`⟨Nx カテゴリ耐久度⟩` / `⟨P% カテゴリ耐久度⟩`を追加します。

### 3. `GameRulesMixin`
* **対象クラス**: `net.minecraft.world.level.gamerules.GameRules`
* **対象メソッド**: `set(GameRule<T>, T, MinecraftServer)V`
* **注入ポイント**: `@At("TAIL")`
* **ハンドラメソッド**: `onSet(GameRule<T> key, T value, @Nullable MinecraftServer server, CallbackInfo ci)`
* **設計意図**: `DURABILITY_MULTIPLIER`カテゴリのルールが変更された際、`DurabilityNetworking.syncToAll(server)`を発火して最新の値をリアルタイムで同期します。

---

## 🔄 動的Modアイテムレジストリスキャン

動的なアイテム登録は**`DasikLibrary`**の`DynamicRegistryScanner`によって支えられています：
* **フックメソッド**: `DynamicRegistryScanner.subscribe(BuiltInRegistries.ITEM, DurabilityRules::isItemDamageable, (id, item) -> { ... })`
* **ライフサイクル**: ユニバーサル3段階探索スキャナー（起動時スキャン、外部Mod読み込み中のライブコールバック、サーバー起動時の安全スキャン）。
* **独自レジストリMixinゼロ**: 手動のレジストリMixinを、クラッシュゼロでクラスローダー安全な標準イベントコールバックに置き換えています。
