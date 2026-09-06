# 耐久度倍率与百分比 (26.1.2)

Durability Multiplier 用动态的**百分比缩放引擎**取代了原版固定的磨损机制，既支持耐久提升（如 200% = 2x, 500% = 5x），也支持磨损加重（如 50% = 0.5x, 25% = 0.25x）。

---

## ⚙️ 核心百分比游戏规则

| # | 游戏规则标识符 | 默认值 | 目标分类 / 说明 |
| :-: | :--- | :---: | :--- |
| 1 | `ig:dm_percent_global` | `200` | 应用于所有可损耗物品的全局百分比。 |
| 2 | `ig:dm_percent_weapons` | `0` | 所有武器的父分类覆盖百分比（剑、长矛、三叉戟、重锤、弓、弩）。 |
| 3 | `ig:dm_percent_swords` | `0` | 剑类专属耐久百分比（`#minecraft:swords`, `#c:swords`）。 |
| 4 | `ig:dm_percent_spears` | `0` | 长矛专属耐久百分比（`#minecraft:spears`, `#c:spears`）。 |
| 5 | `ig:dm_percent_tridents` | `0` | 三叉戟专属耐久百分比（`Items.TRIDENT`, `TridentItem`, `#c:tridents`）。 |
| 6 | `ig:dm_percent_maces` | `0` | 重锤专属耐久百分比（`Items.MACE`, `MaceItem`, `#c:maces`）。 |
| 7 | `ig:dm_percent_bows` | `0` | 弓专属耐久百分比（`Items.BOW`, `BowItem`, `#c:bows`）。 |
| 8 | `ig:dm_percent_crossbows` | `0` | 弩专属耐久百分比（`Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows`）。 |
| 9 | `ig:dm_percent_shields` | `0` | 盾牌专属耐久百分比（`Items.SHIELD`, `ShieldItem`, `#c:shields`）。 |
| 10 | `ig:dm_percent_tools` | `0` | 所有工具的父分类耐久百分比。 |
| 11 | `ig:dm_percent_pickaxes` | `0` | 镐类专属耐久百分比（`PickaxeItem`, `#c:pickaxes`）。 |
| 12 | `ig:dm_percent_axes` | `0` | 斧类专属耐久百分比（`AxeItem`, `#c:axes`）。 |
| 13 | `ig:dm_percent_shovels` | `0` | 锹类专属耐久百分比（`ShovelItem`, `#c:shovels`）。 |
| 14 | `ig:dm_percent_hoes` | `0` | 锄类专属耐久百分比（`HoeItem`, `#c:hoes`）。 |
| 15 | `ig:dm_percent_shears` | `0` | 剪刀专属耐久百分比（`ShearsItem`, `#c:shears`）。 |
| 16 | `ig:dm_percent_fishing_rods` | `0` | 钓鱼竿专属耐久百分比（`FishingRodItem`）。 |
| 17 | `ig:dm_percent_brushes` | `0` | 刷子专属耐久百分比（`BrushItem`）。 |
| 18 | `ig:dm_percent_flint_and_steel` | `0` | 打火石专属耐久百分比（`FlintAndSteelItem`）。 |
| 19 | `ig:dm_percent_armor` | `0` | 所有盔甲部位的父分类耐久百分比。 |
| 20 | `ig:dm_percent_helmets` | `0` | 头盔专属耐久百分比（`#minecraft:head_armor`, `#c:helmets`）。 |
| 21 | `ig:dm_percent_chestplates` | `0` | 胸甲专属耐久百分比（`#minecraft:chest_armor`, `#c:chestplates`）。 |
| 22 | `ig:dm_percent_leggings` | `0` | 护腿专属耐久百分比（`#minecraft:leg_armor`, `#c:leggings`）。 |
| 23 | `ig:dm_percent_boots` | `0` | 靴子专属耐久百分比（`#minecraft:foot_armor`, `#c:boots`）。 |
| 24 | `ig:dm_percent_elytra` | `0` | 鞘翅专属耐久百分比（`Items.ELYTRA`, `DataComponents.GLIDER`）。 |

> [!NOTE]
> 覆盖规则设置为 `0` 会自动回退至其父分类或全局默认值。设置为 `-1` 则激活**单次使用（玻璃模式）**。

---

## 🔒 100% 存档安全保证
Durability Multiplier **绝不**改动世界存档中的物品 NBT 或 `DataComponents.MAX_DAMAGE`。所有耐久缩放均在计算损耗时动态完成，确保即便移除模组也绝不会产生世界损坏或残留异常数据。
