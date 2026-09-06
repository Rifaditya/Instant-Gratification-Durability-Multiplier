# 耐久度倍率與百分比 (26.2)

Durability Multiplier 用動態的**百分比缩放引擎**取代了原版固定的磨損機制，既支持耐久提升（如 200% = 2x, 500% = 5x），也支持磨損加重（如 50% = 0.5x, 25% = 0.25x）。

---

## ⚙️ 核心百分比遊戲規則

| # | 游戏規則標識符 | 默认值 | 目標分類 / 說明 |
| :-: | :--- | :---: | :--- |
| 1 | `ig:dm_percent_global` | `200` | 套用於所有可損耗物品的全局百分比。 |
| 2 | `ig:dm_percent_weapons` | `0` | 所有武器的父分類覆蓋百分比（劍、長矛、三叉戟、重鎚、弓、弩）。 |
| 3 | `ig:dm_percent_swords` | `0` | 劍類专属耐久百分比（`#minecraft:swords`, `#c:swords`）。 |
| 4 | `ig:dm_percent_spears` | `0` | 長矛专属耐久百分比（`#minecraft:spears`, `#c:spears`）。 |
| 5 | `ig:dm_percent_tridents` | `0` | 三叉戟专属耐久百分比（`Items.TRIDENT`, `TridentItem`, `#c:tridents`）。 |
| 6 | `ig:dm_percent_maces` | `0` | 重鎚专属耐久百分比（`Items.MACE`, `MaceItem`, `#c:maces`）。 |
| 7 | `ig:dm_percent_bows` | `0` | 弓专属耐久百分比（`Items.BOW`, `BowItem`, `#c:bows`）。 |
| 8 | `ig:dm_percent_crossbows` | `0` | 弩专属耐久百分比（`Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows`）。 |
| 9 | `ig:dm_percent_shields` | `0` | 盾牌专属耐久百分比（`Items.SHIELD`, `ShieldItem`, `#c:shields`）。 |
| 10 | `ig:dm_percent_tools` | `0` | 所有工具的父分類耐久百分比。 |
| 11 | `ig:dm_percent_pickaxes` | `0` | 鎬類专属耐久百分比（`PickaxeItem`, `#c:pickaxes`）。 |
| 12 | `ig:dm_percent_axes` | `0` | 斧類专属耐久百分比（`AxeItem`, `#c:axes`）。 |
| 13 | `ig:dm_percent_shovels` | `0` | 鍬類专属耐久百分比（`ShovelItem`, `#c:shovels`）。 |
| 14 | `ig:dm_percent_hoes` | `0` | 鋤類专属耐久百分比（`HoeItem`, `#c:hoes`）。 |
| 15 | `ig:dm_percent_shears` | `0` | 剪刀专属耐久百分比（`ShearsItem`, `#c:shears`）。 |
| 16 | `ig:dm_percent_fishing_rods` | `0` | 釣魚竿专属耐久百分比（`FishingRodItem`）。 |
| 17 | `ig:dm_percent_brushes` | `0` | 刷子专属耐久百分比（`BrushItem`）。 |
| 18 | `ig:dm_percent_flint_and_steel` | `0` | 打火石专属耐久百分比（`FlintAndSteelItem`）。 |
| 19 | `ig:dm_percent_armor` | `0` | 所有盔甲部位的父分類耐久百分比。 |
| 20 | `ig:dm_percent_helmets` | `0` | 頭盔专属耐久百分比（`#minecraft:head_armor`, `#c:helmets`）。 |
| 21 | `ig:dm_percent_chestplates` | `0` | 胸甲专属耐久百分比（`#minecraft:chest_armor`, `#c:chestplates`）。 |
| 22 | `ig:dm_percent_leggings` | `0` | 護腿专属耐久百分比（`#minecraft:leg_armor`, `#c:leggings`）。 |
| 23 | `ig:dm_percent_boots` | `0` | 靴子专属耐久百分比（`#minecraft:foot_armor`, `#c:boots`）。 |
| 24 | `ig:dm_percent_elytra` | `0` | 鞘翅专属耐久百分比（`Items.ELYTRA`, `DataComponents.GLIDER`）。 |

> [!NOTE]
> 覆蓋規則設置為 `0` 会自動回退至其父分類或全局默认值。設置為 `-1` 則激活**單次使用（玻璃模式）**。

---

## 🔒 100% 存檔安全保證
Durability Multiplier **绝不**改動世界存檔中的物品 NBT 或 `DataComponents.MAX_DAMAGE`。所有耐久缩放均在計算損耗時動態完成，確保即便移除模組也绝不会产生世界損壞或残留异常數據。
