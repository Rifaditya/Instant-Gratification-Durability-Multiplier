# 耐久度倍率とパーセンテージ (26.2)

Durability Multiplierは、バニラの固定消耗メカニクスを、耐久度強化（例：200% = 2倍、500% = 5倍）と消耗ペナルティ（例：50% = 0.5倍、25% = 0.25倍）の両方に対応した動的**パーセントスケーリングエンジン**に置き換えます。

---

## ⚙️ 主要なパーセンテージゲームルール

| # | ゲームルール識別子 | 初期値 | 対象カテゴリ / 説明 |
| :-: | :--- | :---: | :--- |
| 1 | `ig:dm_percent_global` | `200` | すべての耐久値を持つアイテムに適用されるグローバルパーセンテージ。 |
| 2 | `ig:dm_percent_weapons` | `0` | すべての武器に対する親オーバーライド（剣、槍、トライデント、メイス、弓、クロスボウ）。 |
| 3 | `ig:dm_percent_swords` | `0` | 剣専用の耐久値パーセンテージ（`#minecraft:swords`, `#c:swords`）。 |
| 4 | `ig:dm_percent_spears` | `0` | 槍専用の耐久値パーセンテージ（`#minecraft:spears`, `#c:spears`）。 |
| 5 | `ig:dm_percent_tridents` | `0` | トライデント専用の耐久値パーセンテージ（`Items.TRIDENT`, `TridentItem`, `#c:tridents`）。 |
| 6 | `ig:dm_percent_maces` | `0` | メイス専用の耐久値パーセンテージ（`Items.MACE`, `MaceItem`, `#c:maces`）。 |
| 7 | `ig:dm_percent_bows` | `0` | 弓専用の耐久値パーセンテージ（`Items.BOW`, `BowItem`, `#c:bows`）。 |
| 8 | `ig:dm_percent_crossbows` | `0` | クロスボウ専用の耐久値パーセンテージ（`Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows`）。 |
| 9 | `ig:dm_percent_shields` | `0` | 盾専用の耐久値パーセンテージ（`Items.SHIELD`, `ShieldItem`, `#c:shields`）。 |
| 10 | `ig:dm_percent_tools` | `0` | すべてのツールに対する親カテゴリパーセンテージ。 |
| 11 | `ig:dm_percent_pickaxes` | `0` | ツルハシ専用の耐久値パーセンテージ（`PickaxeItem`, `#c:pickaxes`）。 |
| 12 | `ig:dm_percent_axes` | `0` | 斧専用の耐久値パーセンテージ（`AxeItem`, `#c:axes`）。 |
| 13 | `ig:dm_percent_shovels` | `0` | シャベル専用の耐久値パーセンテージ（`ShovelItem`, `#c:shovels`）。 |
| 14 | `ig:dm_percent_hoes` | `0` | クワ専用の耐久値パーセンテージ（`HoeItem`, `#c:hoes`）。 |
| 15 | `ig:dm_percent_shears` | `0` | ハサミ専用の耐久値パーセンテージ（`ShearsItem`, `#c:shears`）。 |
| 16 | `ig:dm_percent_fishing_rods` | `0` | 釣竿専用の耐久値パーセンテージ（`FishingRodItem`）。 |
| 17 | `ig:dm_percent_brushes` | `0` | ブラシ専用の耐久値パーセンテージ（`BrushItem`）。 |
| 18 | `ig:dm_percent_flint_and_steel` | `0` | 火打石と打ち金専用の耐久値パーセンテージ（`FlintAndSteelItem`）。 |
| 19 | `ig:dm_percent_armor` | `0` | すべての防具部位に対する親カテゴリパーセンテージ。 |
| 20 | `ig:dm_percent_helmets` | `0` | ヘルメット専用の耐久値パーセンテージ（`#minecraft:head_armor`, `#c:helmets`）。 |
| 21 | `ig:dm_percent_chestplates` | `0` | チェストプレート専用の耐久値パーセンテージ（`#minecraft:chest_armor`, `#c:chestplates`）。 |
| 22 | `ig:dm_percent_leggings` | `0` | レギンス専用の耐久値パーセンテージ（`#minecraft:leg_armor`, `#c:leggings`）。 |
| 23 | `ig:dm_percent_boots` | `0` | ブーツ専用の耐久値パーセンテージ（`#minecraft:foot_armor`, `#c:boots`）。 |
| 24 | `ig:dm_percent_elytra` | `0` | エリトラ専用の耐久値パーセンテージ（`Items.ELYTRA`, `DataComponents.GLIDER`）。 |

> [!NOTE]
> `0`に設定された上書きルールは、自動的に親カテゴリまたは全体の初期値にフォールバックします。`-1`を設定すると**使い捨て（ガラスモード）**が発動します。

---

## 🔒 100%ワールドセーブの安全性
Durability Multiplierはセーブデータ内のNBTや`DataComponents.MAX_DAMAGE`を**一切変更しません**。すべてのスケーリングはダメージ計算時に動的に行われるため、Modを削除してもセーブデータの破損や不正な改変は一切残りません。
