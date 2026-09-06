# 游戏规则 GameRules 参考 (26.1.2)

所有 Durability Multiplier 游戏规则均注册在自定义分类 **`durability-multiplier:durability_multiplier`**（`"Durability Multiplier"`）下。

---

## 📊 完整游戏规则参考表

### 1. 耐久度百分比游戏规则
百分比规则用于控制物品耐久度的缩放比例。
* `200` = 200% (2x 耐久度)
* `100` = 100% (原版 1x 基线)
* `50` = 50% (半数耐久 / 2x 磨损速率)
* `0` = 继承父分类或全局默认值
* `-1` = **单次使用（玻璃模式）**哨兵值（1 次命中即破碎）

| # | 游戏规则标识符 | 类型 | 默认值 | 下限 | 说明与行为 |
| :-: | :--- | :---: | :---: | :---: | :--- |
| 1 | `ig:dm_percent_global` | `Integer` | `200` | `-1` | 所有可损耗物品的全局基础耐久百分比。 |
| 2 | `ig:dm_percent_weapons` | `Integer` | `0` | `-1` | 所有武器的全局覆盖百分比（剑、长矛、三叉戟、重锤、弓、弩）。 |
| 3 | `ig:dm_percent_swords` | `Integer` | `0` | `-1` | 剑类专属耐久百分比（`#minecraft:swords`, `#c:swords`）。 |
| 4 | `ig:dm_percent_spears` | `Integer` | `0` | `-1` | 长矛专属耐久百分比（`#minecraft:spears`, `#c:spears`）。 |
| 5 | `ig:dm_percent_tridents` | `Integer` | `0` | `-1` | 三叉戟专属耐久百分比（`TridentItem`, `#c:tridents`）。 |
| 6 | `ig:dm_percent_maces` | `Integer` | `0` | `-1` | 重锤专属耐久百分比（`MaceItem`, `#c:maces`）。 |
| 7 | `ig:dm_percent_bows` | `Integer` | `0` | `-1` | 弓专属耐久百分比（`BowItem`, `#c:bows`）。 |
| 8 | `ig:dm_percent_crossbows` | `Integer` | `0` | `-1` | 弩专属耐久百分比（`CrossbowItem`, `#c:crossbows`）。 |
| 9 | `ig:dm_percent_shields` | `Integer` | `0` | `-1` | 盾牌专属耐久百分比（`ShieldItem`, `#c:shields`）。 |
| 10 | `ig:dm_percent_tools` | `Integer` | `0` | `-1` | 所有工具的父分类耐久百分比。 |
| 11 | `ig:dm_percent_pickaxes` | `Integer` | `0` | `-1` | 镐类专属耐久百分比（`PickaxeItem`, `#c:pickaxes`）。 |
| 12 | `ig:dm_percent_axes` | `Integer` | `0` | `-1` | 斧类专属耐久百分比（`AxeItem`, `#c:axes`）。 |
| 13 | `ig:dm_percent_shovels` | `Integer` | `0` | `-1` | 锹类专属耐久百分比（`ShovelItem`, `#c:shovels`）。 |
| 14 | `ig:dm_percent_hoes` | `Integer` | `0` | `-1` | 锄类专属耐久百分比（`HoeItem`, `#c:hoes`）。 |
| 15 | `ig:dm_percent_shears` | `Integer` | `0` | `-1` | 剪刀专属耐久百分比（`ShearsItem`, `#c:shears`）。 |
| 16 | `ig:dm_percent_fishing_rods` | `Integer` | `0` | `-1` | 钓鱼竿专属耐久百分比（`FishingRodItem`）。 |
| 17 | `ig:dm_percent_brushes` | `Integer` | `0` | `-1` | 刷子专属耐久百分比（`BrushItem`）。 |
| 18 | `ig:dm_percent_flint_and_steel` | `Integer` | `0` | `-1` | 打火石专属耐久百分比（`FlintAndSteelItem`）。 |
| 19 | `ig:dm_percent_armor` | `Integer` | `0` | `-1` | 所有盔甲部位的父分类耐久百分比。 |
| 20 | `ig:dm_percent_helmets` | `Integer` | `0` | `-1` | 头盔专属耐久百分比（`#c:helmets`, 头部槽位）。 |
| 21 | `ig:dm_percent_chestplates` | `Integer` | `0` | `-1` | 胸甲专属耐久百分比（`#c:chestplates`, 胸部槽位）。 |
| 22 | `ig:dm_percent_leggings` | `Integer` | `0` | `-1` | 护腿专属耐久百分比（`#c:leggings`, 腿部槽位）。 |
| 23 | `ig:dm_percent_boots` | `Integer` | `0` | `-1` | 靴子专属耐久百分比（`#c:boots`, 脚部槽位）。 |
| 24 | `ig:dm_percent_elytra` | `Integer` | `0` | `-1` | 鞘翅专属耐久百分比（`Items.ELYTRA`, `GLIDER`）。 |

---

### 2. 上帝模式（无限耐久）游戏规则
启用 (`true`) 时，该分类下的物品承受 $0$ 点损耗且永不破损。

| # | 游戏规则标识符 | 类型 | 默认值 | 说明 |
| :-: | :--- | :---: | :---: | :--- |
| 25 | `ig:dm_infinity_global` | `Boolean` | `false` | 游戏中所有可损耗物品的全局上帝模式（无限耐久）。 |
| 26 | `ig:dm_infinity_weapons` | `Boolean` | `false` | 所有武器的上帝模式。 |
| 27 | `ig:dm_infinity_swords` | `Boolean` | `false` | 剑类的上帝模式。 |
| 28 | `ig:dm_infinity_spears` | `Boolean` | `false` | 长矛的上帝模式。 |
| 29 | `ig:dm_infinity_tridents` | `Boolean` | `false` | 三叉戟的上帝模式。 |
| 30 | `ig:dm_infinity_maces` | `Boolean` | `false` | 重锤的上帝模式。 |
| 31 | `ig:dm_infinity_bows` | `Boolean` | `false` | 弓的上帝模式。 |
| 32 | `ig:dm_infinity_crossbows` | `Boolean` | `false` | 弩的上帝模式。 |
| 33 | `ig:dm_infinity_shields` | `Boolean` | `false` | 盾牌的上帝模式。 |
| 34 | `ig:dm_infinity_tools` | `Boolean` | `false` | 所有工具的上帝模式。 |
| 35 | `ig:dm_infinity_pickaxes` | `Boolean` | `false` | 镐类的上帝模式。 |
| 36 | `ig:dm_infinity_axes` | `Boolean` | `false` | 斧类的上帝模式。 |
| 37 | `ig:dm_infinity_shovels` | `Boolean` | `false` | 锹类的上帝模式。 |
| 38 | `ig:dm_infinity_hoes` | `Boolean` | `false` | 锄类的上帝模式。 |
| 39 | `ig:dm_infinity_shears` | `Boolean` | `false` | 剪刀的上帝模式。 |
| 40 | `ig:dm_infinity_fishing_rods` | `Boolean` | `false` | 钓鱼竿的上帝模式。 |
| 41 | `ig:dm_infinity_brushes` | `Boolean` | `false` | 刷子的上帝模式。 |
| 42 | `ig:dm_infinity_flint_and_steel` | `Boolean` | `false` | 打火石的上帝模式。 |
| 43 | `ig:dm_infinity_armor` | `Boolean` | `false` | 所有盔甲的上帝模式。 |
| 44 | `ig:dm_infinity_helmets` | `Boolean` | `false` | 头盔的上帝模式。 |
| 45 | `ig:dm_infinity_chestplates` | `Boolean` | `false` | 胸甲的上帝模式。 |
| 46 | `ig:dm_infinity_leggings` | `Boolean` | `false` | 护腿的上帝模式。 |
| 47 | `ig:dm_infinity_boots` | `Boolean` | `false` | 靴子的上帝模式。 |
| 48 | `ig:dm_infinity_elytra` | `Boolean` | `false` | 鞘翅的上帝模式。 |

---

### 3. 单次使用（玻璃模式）游戏规则
启用 (`true`) 时，该分类下的物品在单次命中后立即破碎。

| # | 游戏规则标识符 | 类型 | 默认值 | 说明 |
| :-: | :--- | :---: | :---: | :--- |
| 49 | `ig:dm_single_use_global` | `Boolean` | `false` | 所有物品的全局玻璃模式（单次使用）。 |
| 50 | `ig:dm_single_use_weapons` | `Boolean` | `false` | 所有武器的单次使用模式。 |
| 51 | `ig:dm_single_use_swords` | `Boolean` | `false` | 剑类的单次使用模式。 |
| 52 | `ig:dm_single_use_spears` | `Boolean` | `false` | 长矛的单次使用模式。 |
| 53 | `ig:dm_single_use_tridents` | `Boolean` | `false` | 三叉戟的单次使用模式。 |
| 54 | `ig:dm_single_use_maces` | `Boolean` | `false` | 重锤的单次使用模式。 |
| 55 | `ig:dm_single_use_bows` | `Boolean` | `false` | 弓的单次使用模式。 |
| 56 | `ig:dm_single_use_crossbows` | `Boolean` | `false` | 弩的单次使用模式。 |
| 57 | `ig:dm_single_use_shields` | `Boolean` | `false` | 盾牌的单次使用模式。 |
| 58 | `ig:dm_single_use_tools` | `Boolean` | `false` | 所有工具的单次使用模式。 |
| 59 | `ig:dm_single_use_pickaxes` | `Boolean` | `false` | 镐类的单次使用模式。 |
| 60 | `ig:dm_single_use_axes` | `Boolean` | `false` | 斧类的单次使用模式。 |
| 61 | `ig:dm_single_use_shovels` | `Boolean` | `false` | 锹类的单次使用模式。 |
| 62 | `ig:dm_single_use_hoes` | `Boolean` | `false` | 锄类的单次使用模式。 |
| 63 | `ig:dm_single_use_shears` | `Boolean` | `false` | 剪刀的单次使用模式。 |
| 64 | `ig:dm_single_use_fishing_rods` | `Boolean` | `false` | 钓鱼竿的单次使用模式。 |
| 65 | `ig:dm_single_use_brushes` | `Boolean` | `false` | 刷子的单次使用模式。 |
| 66 | `ig:dm_single_use_flint_and_steel` | `Boolean` | `false` | 打火石的单次使用模式。 |
| 67 | `ig:dm_single_use_armor` | `Boolean` | `false` | 所有盔甲的单次使用模式。 |
| 68 | `ig:dm_single_use_helmets` | `Boolean` | `false` | 头盔的单次使用模式。 |
| 69 | `ig:dm_single_use_chestplates` | `Boolean` | `false` | 胸甲的单次使用模式。 |
| 70 | `ig:dm_single_use_leggings` | `Boolean` | `false` | 护腿的单次使用模式。 |
| 71 | `ig:dm_single_use_boots` | `Boolean` | `false` | 靴子的单次使用模式。 |
| 72 | `ig:dm_single_use_elytra` | `Boolean` | `false` | 鞘翅的单次使用模式。 |

---

### 4. 显示与动态模组游戏规则

| 游戏规则标识符 | 类型 | 默认值 | 说明 |
| :--- | :---: | :---: | :--- |
| `ig:dm_show_tooltip` | `Boolean` | `true` | 在物品提示框中渲染耐久度加成信息行。 |
| `ig:percent_<mod>_<item>` | `Integer` | `0` | 特定模组物品的动态百分比覆盖（下限 `-1`）。 |
| `ig:infinity_<mod>_<item>` | `Boolean` | `false` | 特定模组物品的动态上帝模式覆盖。 |
| `ig:single_use_<mod>_<item>` | `Boolean` | `false` | 特定模组物品的动态单次使用覆盖。 |

---

## ⚡ 游戏内调整指令

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

