# 設定とGUI統合 (26.2)

| システムパラメータ | 設定値 |
| :--- | :--- |
| **設定ファイルパス** | `config/durability-multiplier.json` |
| **設定バージョン** | `2` (v1から自動移行) |
| **GUIプロバイダ** | Cloth Config (`me.shedaniel.cloth:cloth-config-fabric`) & ModMenu |
| **設定クラス** | `net.instantgratification.durabilitymultiplier.config.DurabilityConfig` |
| **GUIヘルパー** | `ClothConfigScreenHelper` & `ModMenuIntegration` |
| **優先順位の法則** | 設定ファイルは**新規ワールドの初期値のみ**を定義し、既存ワールドはGameRulesを使用 |

---

## ⚙️ 設定ファイルの構造 (`config/durability-multiplier.json`)

設定ファイルは、新しく作成されるシングルプレイヤーワールドおよびマルチプレイヤーサーバーの初期値を定義します。耐久度倍率、ゴッドモード（無限）、使い捨て（ガラスモード）、ツールチップ書式、Modアイテムの上書きに対応しています。

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

## 🔄 自動補完システム

Durability Multiplierは、手動入力なしでModアイテムを自動カタログ化する自律型の**ユニバーサル3段階探索スキャナー**を備えています：

1. **起動時スキャン**: クライアント/サーバー起動時に`BuiltInRegistries.ITEM`を走査します。
2. **耐久度フィルター**: 外部Mod名前空間（`minecraft`および慣例タグ`c`を除く）のアイテムが`DataComponents.MAX_DAMAGE > 0`を持つか確認します。
3. **自動入力**: 発見された耐久度持ちアイテムは以下に自動追加されます：
   * `"forcedItems"`: Added to the active item registry list.
   * `"forcedPercentages"`: Added with default value `0` (indicating the item dynamically inherits its category or global multiplier).
4. **設定の永続化**: 更新されたリストは`config/durability-multiplier.json`に保存され、Cloth Config / ModMenu GUIおよびゲーム内ゲームルールですぐに編集可能になります。

---

## 🛠️ 手動アイテム設定ガイド

Modパック制作者、サーバー管理者、プレイヤーは`config/durability-multiplier.json`で特定アイテムのルールを宣言できます：

### 1. `forcedItems` (アイテム登録)
Modによって認識されるアイテム識別子のリストを宣言します。
```json
"forcedItems": [
  "techmod:plasma_cutter",
  "magicmod:staff_of_fire",
  "customweapons:obsidian_blade"
]
```

### 2. `forcedPercentages` (アイテムごとの耐久値パーセンテージ)
特定アイテムに明示的な耐久度パーセント倍率を割り当てます：
* `0`: 親カテゴリまたはグローバル倍率を継承。
* `100`: バニラ100%基準（耐久度1倍）。
* `200`: 耐久度200%（寿命2倍）。
* `50`: 耐久度50%（寿命半分 / 消耗速度2倍）。
* `-1`: 使い捨て（ガラスモード - 初撃で破損）。
```json
"forcedPercentages": {
  "techmod:plasma_cutter": 300,
  "customweapons:obsidian_blade": -1,
  "magicmod:training_wand": 50
}
```

### 3. `forcedInfinities` (アイテムごとのゴッドモード)
特定アイテムに永続的な破壊不可（壊れない）ステータスを付与します：
```json
"forcedInfinities": {
  "magicmod:creative_staff": true,
  "adminmod:ban_hammer": true
}
```

### 4. `forcedSingleUses` (アイテムごとのグラスモード)
特定アイテムが1回の耐久度減少で即座に粉砕するようにします：
```json
"forcedSingleUses": {
  "techmod:disposable_cutter": true,
  "survivalplus:glass_dagger": true
}
```

---

## ⚡ 上級者向け `-1` グラスモード番兵値

Durability Multiplierには耐久度パーセント値に**番兵値`-1`**が用意されています：
* パーセントルールまたは設定項目を`-1`（または負の整数）に設定すると、そのアイテムやカテゴリで**使い捨て（ガラスモード）**が自動発動します。
* 有効時、アイテムは最初のヒットで`maxDamage - damageValue`のダメージを受け、耐久度が0になって確実に1回で破壊されます。
* 管理者や制作者は、ブール値を切り替えることなくスライダーや`/gamerule`から直接1撃破壊メカニクスを設定できます。

---

## 🎨 ツールチップ表示のフォーマット

`tooltipFormat`オプションは、アイテムツールチップ上での耐久度ボーナスの表示方法を設定します：

| 書式設定 | 表示例 (200% / 2x) | 表示例 (150% / 1.5x) | 説明 |
| :--- | :--- | :--- | :--- |
| `"ADAPTIVE"` *(デフォルト)* | `⟨2x 剣の耐久度⟩` | `⟨150% 剣の耐久度⟩` | 100単位は整数倍数で、それ以外はパーセントで表示します。 |
| `"PERCENTAGE"` | `⟨200% 剣の耐久度⟩` | `⟨150% 剣の耐久度⟩` | 常に正確なパーセント値を表示します。 |
| `"MULTIPLIER"` | `⟨2x 剣の耐久度⟩` | `⟨1.5x 剣の耐久度⟩` | 常にフォーマットされた倍率文字列を表示します。 |

`"showTooltip": false`に設定すると、ツールチップの耐久度表示が完全に非表示になります。

---

## ⚠️ 設定の優先順位に関する重要警告

> ⚠️ **注意**: `durability-multiplier.json`やModMenu画面での変更は、**新しく作成されるワールドの初期値のみを定義**します。
> 
> 既存のワールドでは、各ワールドのデータ(`level.dat`)内に個別のゲームルール状態が保存されます。進行中のワールドの設定を変更するには、ゲーム内の`/gamerule`コマンドまたは設定GUIを使用してください。

