# 上帝模式与无限耐久 (26.2)

| 系统参数 | 取值 |
| :--- | :--- |
| **全局上帝模式规则** | `ig:dm_infinity_global` |
| **默认状态** | `false` (已禁用) |
| **损耗拦截机制** | 传入损耗在 `HEAD` 处被取消（造成 $0$ 点损耗） |
| **提示框渲染样式** | `✦ UNBREAKABLE` (金色, 粗体) |
| **计算优先级** | 绝对优先（在任何百分比倍率前计算） |

---

## ⚡ 概述与运行机制

**上帝模式（无限耐久）**为所选分类内的物品赋予完全无敌状态。当某物品激活上帝模式时，任何耐久损耗事件都在 `ItemStackDurabilityMixin` 处被彻底拦截并取消，防止物品遭受任何损坏或破碎。

### 与原版无法破坏组件的区别
* 原版 `Unbreakable` 组件必须通过指令单个赋予物品堆叠（`/give @p diamond_sword[unbreakable={}]`）。
* Durability Multiplier 的上帝模式是**全服与全类别生效**的：世界中的每件工具、武器或盔甲都会自动具备无法破坏特性，无需修改物品 NBT 或组件数据。

---

## 🛡️ 24 项上帝模式规则

| # | 游戏规则键名 | 分类名称 | 目标物品范围 | 默认值 |
| :-: | :--- | :--- | :--- | :-: |
| 1 | `ig:dm_infinity_global` | **Global God Mode** | All damageable items in the game | `false` |
| 2 | `ig:dm_infinity_weapons` | **Weapons God Mode** | All weapons (swords, spears, tridents, maces, bows, crossbows) | `false` |
| 3 | `ig:dm_infinity_swords` | **Swords God Mode** | `#minecraft:swords`, `#c:swords` | `false` |
| 4 | `ig:dm_infinity_spears` | **Spears God Mode** | `#minecraft:spears`, `#c:spears` | `false` |
| 5 | `ig:dm_infinity_tridents` | **Tridents God Mode** | `Items.TRIDENT`, `TridentItem`, `#c:tridents` | `false` |
| 6 | `ig:dm_infinity_maces` | **Maces God Mode** | `Items.MACE`, `MaceItem`, `#c:maces` | `false` |
| 7 | `ig:dm_infinity_bows` | **Bows God Mode** | `Items.BOW`, `BowItem`, `#c:bows` | `false` |
| 8 | `ig:dm_infinity_crossbows` | **Crossbows God Mode** | `Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows` | `false` |
| 9 | `ig:dm_infinity_shields` | **Shields God Mode** | `Items.SHIELD`, `ShieldItem`, `#c:shields` | `false` |
| 10 | `ig:dm_infinity_tools` | **Tools God Mode** | Parent category for all Tools | `false` |
| 11 | `ig:dm_infinity_pickaxes` | **Pickaxes God Mode** | `PickaxeItem`, `#c:pickaxes` | `false` |
| 12 | `ig:dm_infinity_axes` | **Axes God Mode** | `AxeItem`, `#c:axes` | `false` |
| 13 | `ig:dm_infinity_shovels` | **Shovels God Mode** | `ShovelItem`, `#c:shovels` | `false` |
| 14 | `ig:dm_infinity_hoes` | **Hoes God Mode** | `HoeItem`, `#c:hoes` | `false` |
| 15 | `ig:dm_infinity_shears` | **Shears God Mode** | `ShearsItem`, `#c:shears` | `false` |
| 16 | `ig:dm_infinity_fishing_rods` | **Fishing Rods God Mode** | `FishingRodItem` | `false` |
| 17 | `ig:dm_infinity_brushes` | **Brushes God Mode** | `BrushItem` | `false` |
| 18 | `ig:dm_infinity_flint_and_steel` | **Flint and Steel God Mode** | `FlintAndSteelItem` | `false` |
| 19 | `ig:dm_infinity_armor` | **Armor God Mode** | Parent category for all Armor pieces | `false` |
| 20 | `ig:dm_infinity_helmets` | **Helmets God Mode** | `#minecraft:head_armor`, `#c:helmets` | `false` |
| 21 | `ig:dm_infinity_chestplates` | **Chestplates God Mode** | `#minecraft:chest_armor`, `#c:chestplates` | `false` |
| 22 | `ig:dm_infinity_leggings` | **Leggings God Mode** | `#minecraft:leg_armor`, `#c:leggings` | `false` |
| 23 | `ig:dm_infinity_boots` | **Boots God Mode** | `#minecraft:foot_armor`, `#c:boots` | `false` |
| 24 | `ig:dm_infinity_elytra` | **Elytra God Mode** | `Items.ELYTRA`, `DataComponents.GLIDER` | `false` |

---

## 👑 上帝模式判定顺序

`DurabilityHelper.isInfinite(ServerLevel, ItemStack)` 检查：

```
[1. Per-Item Dynamic Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[2. Specific Category Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[3. Weapons/Tools/Armor Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[4. Global Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[Proceed to Single-Use / Multiplier Calculation]
```

上帝模式相较于任何百分比倍率或单次使用设置拥有**绝对优先权**。只要 `ig:dm_infinity_tools = true`，工具就绝不会受损，无论 `ig:dm_percent_tools` 是设为 `200`、`1000` 还是 `0`。

