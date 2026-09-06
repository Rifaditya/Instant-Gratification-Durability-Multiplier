# ゲームルール (GameRules) リファレンス (26.2)

すべてのDurability Multiplierゲームルールはカスタムカテゴリ**`durability-multiplier:durability_multiplier`**配下に登録されます。

---

## 📊 完全なゲームルールリファレンステーブル

### 1. 耐久値パーセンテージゲームルール
パーセントルールはアイテムの耐久度倍率を制御します。
* `200` = 200% (耐久度2倍)
* `100` = 100% (バニラ1倍の基準値)
* `50` = 50% (耐久度半分 / 消耗速度2倍)
* `0` = 親カテゴリまたは全体の初期値を継承
* `-1` = **使い捨て（ガラスモード）**の番兵値（1回の使用で破損）

| # | ゲームルール識別子 | 型 | 初期値 | 最小値 | 説明と挙動 |
| :-: | :--- | :---: | :---: | :---: | :--- |
| 1 | `ig:dm_percent_global` | `Integer` | `200` | `-1` | すべての耐久値を持つアイテムに対する基本グローバルパーセンテージ。 |
| 2 | `ig:dm_percent_weapons` | `Integer` | `0` | `-1` | すべての武器に対するグローバルオーバーライド（剣、槍、トライデント、メイス、弓、クロスボウ）。 |
| 3 | `ig:dm_percent_swords` | `Integer` | `0` | `-1` | 剣専用の耐久値パーセンテージ（`#minecraft:swords`, `#c:swords`）。 |
| 4 | `ig:dm_percent_spears` | `Integer` | `0` | `-1` | 槍専用の耐久値パーセンテージ（`#minecraft:spears`, `#c:spears`）。 |
| 5 | `ig:dm_percent_tridents` | `Integer` | `0` | `-1` | トライデント専用の耐久値パーセンテージ（`TridentItem`, `#c:tridents`）。 |
| 6 | `ig:dm_percent_maces` | `Integer` | `0` | `-1` | メイス専用の耐久値パーセンテージ（`MaceItem`, `#c:maces`）。 |
| 7 | `ig:dm_percent_bows` | `Integer` | `0` | `-1` | 弓専用の耐久値パーセンテージ（`BowItem`, `#c:bows`）。 |
| 8 | `ig:dm_percent_crossbows` | `Integer` | `0` | `-1` | クロスボウ専用の耐久値パーセンテージ（`CrossbowItem`, `#c:crossbows`）。 |
| 9 | `ig:dm_percent_shields` | `Integer` | `0` | `-1` | 盾専用の耐久値パーセンテージ（`ShieldItem`, `#c:shields`）。 |
| 10 | `ig:dm_percent_tools` | `Integer` | `0` | `-1` | すべてのツールに対する親カテゴリパーセンテージ。 |
| 11 | `ig:dm_percent_pickaxes` | `Integer` | `0` | `-1` | ツルハシ専用の耐久値パーセンテージ（`PickaxeItem`, `#c:pickaxes`）。 |
| 12 | `ig:dm_percent_axes` | `Integer` | `0` | `-1` | 斧専用の耐久値パーセンテージ（`AxeItem`, `#c:axes`）。 |
| 13 | `ig:dm_percent_shovels` | `Integer` | `0` | `-1` | シャベル専用の耐久値パーセンテージ（`ShovelItem`, `#c:shovels`）。 |
| 14 | `ig:dm_percent_hoes` | `Integer` | `0` | `-1` | クワ専用の耐久値パーセンテージ（`HoeItem`, `#c:hoes`）。 |
| 15 | `ig:dm_percent_shears` | `Integer` | `0` | `-1` | ハサミ専用の耐久値パーセンテージ（`ShearsItem`, `#c:shears`）。 |
| 16 | `ig:dm_percent_fishing_rods` | `Integer` | `0` | `-1` | 釣竿専用の耐久値パーセンテージ（`FishingRodItem`）。 |
| 17 | `ig:dm_percent_brushes` | `Integer` | `0` | `-1` | ブラシ専用の耐久値パーセンテージ（`BrushItem`）。 |
| 18 | `ig:dm_percent_flint_and_steel` | `Integer` | `0` | `-1` | 火打石と打ち金専用の耐久値パーセンテージ（`FlintAndSteelItem`）。 |
| 19 | `ig:dm_percent_armor` | `Integer` | `0` | `-1` | すべての防具部位に対する親カテゴリパーセンテージ。 |
| 20 | `ig:dm_percent_helmets` | `Integer` | `0` | `-1` | ヘルメット専用の耐久値パーセンテージ（`#c:helmets`, 頭スロット）。 |
| 21 | `ig:dm_percent_chestplates` | `Integer` | `0` | `-1` | チェストプレート専用の耐久値パーセンテージ（`#c:chestplates`, 胸スロット）。 |
| 22 | `ig:dm_percent_leggings` | `Integer` | `0` | `-1` | レギンス専用の耐久値パーセンテージ（`#c:leggings`, 脚スロット）。 |
| 23 | `ig:dm_percent_boots` | `Integer` | `0` | `-1` | ブーツ専用の耐久値パーセンテージ（`#c:boots`, 足スロット）。 |
| 24 | `ig:dm_percent_elytra` | `Integer` | `0` | `-1` | エリトラ専用の耐久値パーセンテージ（`Items.ELYTRA`, `GLIDER`）。 |

---

### 2. ゴッドモード（無限耐久）ゲームルール
有効（`true`）時、そのカテゴリのアイテムは$0$ダメージとなり絶対に壊れません。

| # | ゲームルール識別子 | 型 | 初期値 | 説明 |
| :-: | :--- | :---: | :---: | :--- |
| 25 | `ig:dm_infinity_global` | `Boolean` | `false` | ゲーム内のすべての耐久値を持つアイテムに対するグローバルゴッドモード。 |
| 26 | `ig:dm_infinity_weapons` | `Boolean` | `false` | すべての武器のゴッドモード。 |
| 27 | `ig:dm_infinity_swords` | `Boolean` | `false` | 剣のゴッドモード。 |
| 28 | `ig:dm_infinity_spears` | `Boolean` | `false` | 槍のゴッドモード。 |
| 29 | `ig:dm_infinity_tridents` | `Boolean` | `false` | トライデントのゴッドモード。 |
| 30 | `ig:dm_infinity_maces` | `Boolean` | `false` | メイスのゴッドモード。 |
| 31 | `ig:dm_infinity_bows` | `Boolean` | `false` | 弓のゴッドモード。 |
| 32 | `ig:dm_infinity_crossbows` | `Boolean` | `false` | クロスボウのゴッドモード。 |
| 33 | `ig:dm_infinity_shields` | `Boolean` | `false` | 盾のゴッドモード。 |
| 34 | `ig:dm_infinity_tools` | `Boolean` | `false` | すべてのツールのゴッドモード。 |
| 35 | `ig:dm_infinity_pickaxes` | `Boolean` | `false` | ツルハシのゴッドモード。 |
| 36 | `ig:dm_infinity_axes` | `Boolean` | `false` | 斧のゴッドモード。 |
| 37 | `ig:dm_infinity_shovels` | `Boolean` | `false` | シャベルのゴッドモード。 |
| 38 | `ig:dm_infinity_hoes` | `Boolean` | `false` | クワのゴッドモード。 |
| 39 | `ig:dm_infinity_shears` | `Boolean` | `false` | ハサミのゴッドモード。 |
| 40 | `ig:dm_infinity_fishing_rods` | `Boolean` | `false` | 釣竿のゴッドモード。 |
| 41 | `ig:dm_infinity_brushes` | `Boolean` | `false` | ブラシのゴッドモード。 |
| 42 | `ig:dm_infinity_flint_and_steel` | `Boolean` | `false` | 火打石と打ち金のゴッドモード。 |
| 43 | `ig:dm_infinity_armor` | `Boolean` | `false` | すべての防具のゴッドモード。 |
| 44 | `ig:dm_infinity_helmets` | `Boolean` | `false` | ヘルメットのゴッドモード。 |
| 45 | `ig:dm_infinity_chestplates` | `Boolean` | `false` | チェストプレートのゴッドモード。 |
| 46 | `ig:dm_infinity_leggings` | `Boolean` | `false` | レギンスのゴッドモード。 |
| 47 | `ig:dm_infinity_boots` | `Boolean` | `false` | ブーツのゴッドモード。 |
| 48 | `ig:dm_infinity_elytra` | `Boolean` | `false` | エリトラのゴッドモード。 |

---

### 3. 1回使い切り（グラスモード）ゲームルール
有効（`true`）時、そのカテゴリのアイテムは1回の使用で破損します。

| # | ゲームルール識別子 | 型 | 初期値 | 説明 |
| :-: | :--- | :---: | :---: | :--- |
| 49 | `ig:dm_single_use_global` | `Boolean` | `false` | 全アイテムに対するグローバルグラスモード（1回使い切り）。 |
| 50 | `ig:dm_single_use_weapons` | `Boolean` | `false` | すべての武器の1回使い切りモード。 |
| 51 | `ig:dm_single_use_swords` | `Boolean` | `false` | 剣の1回使い切りモード。 |
| 52 | `ig:dm_single_use_spears` | `Boolean` | `false` | 槍の1回使い切りモード。 |
| 53 | `ig:dm_single_use_tridents` | `Boolean` | `false` | トライデントの1回使い切りモード。 |
| 54 | `ig:dm_single_use_maces` | `Boolean` | `false` | メイスの1回使い切りモード。 |
| 55 | `ig:dm_single_use_bows` | `Boolean` | `false` | 弓の1回使い切りモード。 |
| 56 | `ig:dm_single_use_crossbows` | `Boolean` | `false` | クロスボウの1回使い切りモード。 |
| 57 | `ig:dm_single_use_shields` | `Boolean` | `false` | 盾の1回使い切りモード。 |
| 58 | `ig:dm_single_use_tools` | `Boolean` | `false` | すべてのツールの1回使い切りモード。 |
| 59 | `ig:dm_single_use_pickaxes` | `Boolean` | `false` | ツルハシの1回使い切りモード。 |
| 60 | `ig:dm_single_use_axes` | `Boolean` | `false` | 斧の1回使い切りモード。 |
| 61 | `ig:dm_single_use_shovels` | `Boolean` | `false` | シャベルの1回使い切りモード。 |
| 62 | `ig:dm_single_use_hoes` | `Boolean` | `false` | クワの1回使い切りモード。 |
| 63 | `ig:dm_single_use_shears` | `Boolean` | `false` | ハサミの1回使い切りモード。 |
| 64 | `ig:dm_single_use_fishing_rods` | `Boolean` | `false` | 釣竿の1回使い切りモード。 |
| 65 | `ig:dm_single_use_brushes` | `Boolean` | `false` | ブラシの1回使い切りモード。 |
| 66 | `ig:dm_single_use_flint_and_steel` | `Boolean` | `false` | 火打石と打ち金の1回使い切りモード。 |
| 67 | `ig:dm_single_use_armor` | `Boolean` | `false` | すべての防具の1回使い切りモード。 |
| 68 | `ig:dm_single_use_helmets` | `Boolean` | `false` | ヘルメットの1回使い切りモード。 |
| 69 | `ig:dm_single_use_chestplates` | `Boolean` | `false` | チェストプレートの1回使い切りモード。 |
| 70 | `ig:dm_single_use_leggings` | `Boolean` | `false` | レギンスの1回使い切りモード。 |
| 71 | `ig:dm_single_use_boots` | `Boolean` | `false` | ブーツの1回使い切りモード。 |
| 72 | `ig:dm_single_use_elytra` | `Boolean` | `false` | エリトラの1回使い切りモード。 |

---

### 4. 表示および動的Modゲームルール

| ゲームルール識別子 | 型 | 初期値 | 説明 |
| :--- | :---: | :---: | :--- |
| `ig:dm_show_tooltip` | `Boolean` | `true` | アイテムのツールチップに耐久度倍率表示を描画します。 |
| `ig:percent_<mod>_<item>` | `Integer` | `0` | 特定Modアイテムの動的パーセント上書き（最小 `-1`）。 |
| `ig:infinity_<mod>_<item>` | `Boolean` | `false` | 特定Modアイテムの動的ゴッドモード上書き。 |
| `ig:single_use_<mod>_<item>` | `Boolean` | `false` | 特定Modアイテムの動的使い捨て上書き。 |

---

## ⚡ ゲーム内調整コマンド

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

