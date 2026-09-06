# 遊戲規則 GameRules 參考 (26.1.2)

所有 Durability Multiplier 游戏規則均注册在自定义分類 **`durability-multiplier:durability_multiplier`**（`"Durability Multiplier"`）下。

---

## 📊 完整遊戲規則參考表

### 1. 耐久度百分比遊戲規則
百分比規則用于控制物品耐久度的缩放比例。
* `200` = 200% (2x 耐久度)
* `100` = 100% (原版 1x 基线)
* `50` = 50% (半數耐久 / 2x 磨損速率)
* `0` = 繼承父分類或全局默认值
* `-1` = **單次使用（玻璃模式）**哨兵值（1 次命中即破碎）

| # | 游戏規則標識符 | 類型 | 默认值 | 下限 | 說明与行為 |
| :-: | :--- | :---: | :---: | :---: | :--- |
| 1 | `ig:dm_percent_global` | `Integer` | `200` | `-1` | 所有可損耗物品的全局基礎耐久百分比。 |
| 2 | `ig:dm_percent_weapons` | `Integer` | `0` | `-1` | 所有武器的全局覆蓋百分比（劍、長矛、三叉戟、重鎚、弓、弩）。 |
| 3 | `ig:dm_percent_swords` | `Integer` | `0` | `-1` | 劍類专属耐久百分比（`#minecraft:swords`, `#c:swords`）。 |
| 4 | `ig:dm_percent_spears` | `Integer` | `0` | `-1` | 長矛专属耐久百分比（`#minecraft:spears`, `#c:spears`）。 |
| 5 | `ig:dm_percent_tridents` | `Integer` | `0` | `-1` | 三叉戟专属耐久百分比（`TridentItem`, `#c:tridents`）。 |
| 6 | `ig:dm_percent_maces` | `Integer` | `0` | `-1` | 重鎚专属耐久百分比（`MaceItem`, `#c:maces`）。 |
| 7 | `ig:dm_percent_bows` | `Integer` | `0` | `-1` | 弓专属耐久百分比（`BowItem`, `#c:bows`）。 |
| 8 | `ig:dm_percent_crossbows` | `Integer` | `0` | `-1` | 弩专属耐久百分比（`CrossbowItem`, `#c:crossbows`）。 |
| 9 | `ig:dm_percent_shields` | `Integer` | `0` | `-1` | 盾牌专属耐久百分比（`ShieldItem`, `#c:shields`）。 |
| 10 | `ig:dm_percent_tools` | `Integer` | `0` | `-1` | 所有工具的父分類耐久百分比。 |
| 11 | `ig:dm_percent_pickaxes` | `Integer` | `0` | `-1` | 鎬類专属耐久百分比（`PickaxeItem`, `#c:pickaxes`）。 |
| 12 | `ig:dm_percent_axes` | `Integer` | `0` | `-1` | 斧類专属耐久百分比（`AxeItem`, `#c:axes`）。 |
| 13 | `ig:dm_percent_shovels` | `Integer` | `0` | `-1` | 鍬類专属耐久百分比（`ShovelItem`, `#c:shovels`）。 |
| 14 | `ig:dm_percent_hoes` | `Integer` | `0` | `-1` | 鋤類专属耐久百分比（`HoeItem`, `#c:hoes`）。 |
| 15 | `ig:dm_percent_shears` | `Integer` | `0` | `-1` | 剪刀专属耐久百分比（`ShearsItem`, `#c:shears`）。 |
| 16 | `ig:dm_percent_fishing_rods` | `Integer` | `0` | `-1` | 釣魚竿专属耐久百分比（`FishingRodItem`）。 |
| 17 | `ig:dm_percent_brushes` | `Integer` | `0` | `-1` | 刷子专属耐久百分比（`BrushItem`）。 |
| 18 | `ig:dm_percent_flint_and_steel` | `Integer` | `0` | `-1` | 打火石专属耐久百分比（`FlintAndSteelItem`）。 |
| 19 | `ig:dm_percent_armor` | `Integer` | `0` | `-1` | 所有盔甲部位的父分類耐久百分比。 |
| 20 | `ig:dm_percent_helmets` | `Integer` | `0` | `-1` | 頭盔专属耐久百分比（`#c:helmets`, 頭部槽位）。 |
| 21 | `ig:dm_percent_chestplates` | `Integer` | `0` | `-1` | 胸甲专属耐久百分比（`#c:chestplates`, 胸部槽位）。 |
| 22 | `ig:dm_percent_leggings` | `Integer` | `0` | `-1` | 護腿专属耐久百分比（`#c:leggings`, 腿部槽位）。 |
| 23 | `ig:dm_percent_boots` | `Integer` | `0` | `-1` | 靴子专属耐久百分比（`#c:boots`, 腳部槽位）。 |
| 24 | `ig:dm_percent_elytra` | `Integer` | `0` | `-1` | 鞘翅专属耐久百分比（`Items.ELYTRA`, `GLIDER`）。 |

---

### 2. 上帝模式（無限耐久）遊戲規則
啟用 (`true`) 時，该分類下的物品承受 $0$ 點損耗且永不破損。

| # | 游戏規則標識符 | 類型 | 默认值 | 說明 |
| :-: | :--- | :---: | :---: | :--- |
| 25 | `ig:dm_infinity_global` | `Boolean` | `false` | 遊戲中所有可損耗物品的全局上帝模式（無限耐久）。 |
| 26 | `ig:dm_infinity_weapons` | `Boolean` | `false` | 所有武器的上帝模式。 |
| 27 | `ig:dm_infinity_swords` | `Boolean` | `false` | 劍類的上帝模式。 |
| 28 | `ig:dm_infinity_spears` | `Boolean` | `false` | 長矛的上帝模式。 |
| 29 | `ig:dm_infinity_tridents` | `Boolean` | `false` | 三叉戟的上帝模式。 |
| 30 | `ig:dm_infinity_maces` | `Boolean` | `false` | 重鎚的上帝模式。 |
| 31 | `ig:dm_infinity_bows` | `Boolean` | `false` | 弓的上帝模式。 |
| 32 | `ig:dm_infinity_crossbows` | `Boolean` | `false` | 弩的上帝模式。 |
| 33 | `ig:dm_infinity_shields` | `Boolean` | `false` | 盾牌的上帝模式。 |
| 34 | `ig:dm_infinity_tools` | `Boolean` | `false` | 所有工具的上帝模式。 |
| 35 | `ig:dm_infinity_pickaxes` | `Boolean` | `false` | 鎬類的上帝模式。 |
| 36 | `ig:dm_infinity_axes` | `Boolean` | `false` | 斧類的上帝模式。 |
| 37 | `ig:dm_infinity_shovels` | `Boolean` | `false` | 鍬類的上帝模式。 |
| 38 | `ig:dm_infinity_hoes` | `Boolean` | `false` | 鋤類的上帝模式。 |
| 39 | `ig:dm_infinity_shears` | `Boolean` | `false` | 剪刀的上帝模式。 |
| 40 | `ig:dm_infinity_fishing_rods` | `Boolean` | `false` | 釣魚竿的上帝模式。 |
| 41 | `ig:dm_infinity_brushes` | `Boolean` | `false` | 刷子的上帝模式。 |
| 42 | `ig:dm_infinity_flint_and_steel` | `Boolean` | `false` | 打火石的上帝模式。 |
| 43 | `ig:dm_infinity_armor` | `Boolean` | `false` | 所有盔甲的上帝模式。 |
| 44 | `ig:dm_infinity_helmets` | `Boolean` | `false` | 頭盔的上帝模式。 |
| 45 | `ig:dm_infinity_chestplates` | `Boolean` | `false` | 胸甲的上帝模式。 |
| 46 | `ig:dm_infinity_leggings` | `Boolean` | `false` | 護腿的上帝模式。 |
| 47 | `ig:dm_infinity_boots` | `Boolean` | `false` | 靴子的上帝模式。 |
| 48 | `ig:dm_infinity_elytra` | `Boolean` | `false` | 鞘翅的上帝模式。 |

---

### 3. 單次使用（玻璃模式）遊戲規則
啟用 (`true`) 時，该分類下的物品在單次命中后立即破碎。

| # | 游戏規則標識符 | 類型 | 默认值 | 說明 |
| :-: | :--- | :---: | :---: | :--- |
| 49 | `ig:dm_single_use_global` | `Boolean` | `false` | 所有物品的全局玻璃模式（單次使用）。 |
| 50 | `ig:dm_single_use_weapons` | `Boolean` | `false` | 所有武器的單次使用模式。 |
| 51 | `ig:dm_single_use_swords` | `Boolean` | `false` | 劍類的單次使用模式。 |
| 52 | `ig:dm_single_use_spears` | `Boolean` | `false` | 長矛的單次使用模式。 |
| 53 | `ig:dm_single_use_tridents` | `Boolean` | `false` | 三叉戟的單次使用模式。 |
| 54 | `ig:dm_single_use_maces` | `Boolean` | `false` | 重鎚的單次使用模式。 |
| 55 | `ig:dm_single_use_bows` | `Boolean` | `false` | 弓的單次使用模式。 |
| 56 | `ig:dm_single_use_crossbows` | `Boolean` | `false` | 弩的單次使用模式。 |
| 57 | `ig:dm_single_use_shields` | `Boolean` | `false` | 盾牌的單次使用模式。 |
| 58 | `ig:dm_single_use_tools` | `Boolean` | `false` | 所有工具的單次使用模式。 |
| 59 | `ig:dm_single_use_pickaxes` | `Boolean` | `false` | 鎬類的單次使用模式。 |
| 60 | `ig:dm_single_use_axes` | `Boolean` | `false` | 斧類的單次使用模式。 |
| 61 | `ig:dm_single_use_shovels` | `Boolean` | `false` | 鍬類的單次使用模式。 |
| 62 | `ig:dm_single_use_hoes` | `Boolean` | `false` | 鋤類的單次使用模式。 |
| 63 | `ig:dm_single_use_shears` | `Boolean` | `false` | 剪刀的單次使用模式。 |
| 64 | `ig:dm_single_use_fishing_rods` | `Boolean` | `false` | 釣魚竿的單次使用模式。 |
| 65 | `ig:dm_single_use_brushes` | `Boolean` | `false` | 刷子的單次使用模式。 |
| 66 | `ig:dm_single_use_flint_and_steel` | `Boolean` | `false` | 打火石的單次使用模式。 |
| 67 | `ig:dm_single_use_armor` | `Boolean` | `false` | 所有盔甲的單次使用模式。 |
| 68 | `ig:dm_single_use_helmets` | `Boolean` | `false` | 頭盔的單次使用模式。 |
| 69 | `ig:dm_single_use_chestplates` | `Boolean` | `false` | 胸甲的單次使用模式。 |
| 70 | `ig:dm_single_use_leggings` | `Boolean` | `false` | 護腿的單次使用模式。 |
| 71 | `ig:dm_single_use_boots` | `Boolean` | `false` | 靴子的單次使用模式。 |
| 72 | `ig:dm_single_use_elytra` | `Boolean` | `false` | 鞘翅的單次使用模式。 |

---

### 4. 顯示與動態模組遊戲規則

| 游戏規則標識符 | 類型 | 默认值 | 說明 |
| :--- | :---: | :---: | :--- |
| `ig:dm_show_tooltip` | `Boolean` | `true` | 在物品提示框中渲染耐久度加成信息行。 |
| `ig:percent_<mod>_<item>` | `Integer` | `0` | 特定模組物品的動態百分比覆蓋（下限 `-1`）。 |
| `ig:infinity_<mod>_<item>` | `Boolean` | `false` | 特定模組物品的動態上帝模式覆蓋。 |
| `ig:single_use_<mod>_<item>` | `Boolean` | `false` | 特定模組物品的動態單次使用覆蓋。 |

---

## ⚡ 遊戲內調整指令

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

