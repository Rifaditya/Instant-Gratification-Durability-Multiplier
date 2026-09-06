# 配置与 GUI 界面集成 (26.2)

| 系统参数 | 取值 |
| :--- | :--- |
| **配置文件路径** | `config/durability-multiplier.json` |
| **配置版本** | `2` (自 v1 自动迁移) |
| **GUI 界面提供库** | Cloth Config (`me.shedaniel.cloth:cloth-config-fabric`) 与 ModMenu |
| **配置数据类** | `net.instantgratification.durabilitymultiplier.config.DurabilityConfig` |
| **GUI 辅助类** | `ClothConfigScreenHelper` 与 `ModMenuIntegration` |
| **优先级铁律** | 配置文件**仅定义新建世界默认值**；活跃世界完全以游戏规则为准 |

---

## ⚙️ 配置文件结构 (`config/durability-multiplier.json`)

该配置文件定义了所有新建单人世界和多人服务器的基础基线默认值。它支持耐久百分比、上帝模式（无限耐久）、单次使用（玻璃模式）、自定义提示框格式以及动态模组物品覆盖配置。

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

## 🔄 自动填充系统

Durability Multiplier 拥有自主的**通用 3 级发现扫描器**，可自动归档模组物品，无需任何手动数据录入：

1. **启动全面扫描**：在客户端/服务端启动时，引擎自动扫描 `BuiltInRegistries.ITEM`。
2. **可损耗过滤**：检查外部模组命名空间中的物品（排除 `minecraft` 和通用约定标签 `c`）是否具备 `DataComponents.MAX_DAMAGE > 0`。
3. **自动填充**：扫描发现的可损耗物品会自动追加至：
   * `"forcedItems"`: Added to the active item registry list.
   * `"forcedPercentages"`: Added with default value `0` (indicating the item dynamically inherits its category or global multiplier).
4. **配置持久化**：更新后的列表会回写保存到 `config/durability-multiplier.json`，使所有模组物品立即在 Cloth Config / ModMenu 界面和游戏内规则中可见且可编辑。

---

## 🛠️ 手动物品配置指南

整合包作者、服务器管理员和玩家可以直接在 `config/durability-multiplier.json` 中为特定物品声明自定义规则：

### 1. `forcedItems`（物品显式注册）
声明本模组识别管理的物品资源标识符 (Identifier) 列表。
```json
"forcedItems": [
  "techmod:plasma_cutter",
  "magicmod:staff_of_fire",
  "customweapons:obsidian_blade"
]
```

### 2. `forcedPercentages`（单品耐久百分比配置）
为特定物品指定明确的耐久度百分比倍率：
* `0`: 继承父分类或全局倍率。
* `100`: 原版 100% 基准（1x 耐久）。
* `200`: 200% 耐久度（2x 寿命）。
* `50`: 50% 耐久度（半数寿命 / 2x 磨损）。
* `-1`: 单次使用（玻璃模式 - 首次命中即碎）。
```json
"forcedPercentages": {
  "techmod:plasma_cutter": 300,
  "customweapons:obsidian_blade": -1,
  "magicmod:training_wand": 50
}
```

### 3. `forcedInfinities`（单品上帝模式）
为特定物品赋予永久无法破坏的上帝模式状态：
```json
"forcedInfinities": {
  "magicmod:creative_staff": true,
  "adminmod:ban_hammer": true
}
```

### 4. `forcedSingleUses`（单品玻璃模式）
强制特定物品在单次耐久损耗事件后彻底破碎：
```json
"forcedSingleUses": {
  "techmod:disposable_cutter": true,
  "survivalplus:glass_dagger": true
}
```

---

## ⚡ 高级用户 `-1` 玻璃模式哨兵值

Durability Multiplier 为耐久度百分比引入了 **`-1` 哨兵值**：
* 将任何百分比规则或配置字段设为 `-1`（或任何负整数）会自动为该物品或分类激活**单次使用（玻璃模式）**。
* 激活时，物品在首次命中时承受 `maxDamage - damageValue` 损耗，将其耐久度直接清零并在恰好 1 次使用后破碎。
* 这允许服主和整合包作者直接通过百分比滑块或 `/gamerule` 指令强制执行 1 次破碎机制，无需切换独立的布尔规则。

---

## 🎨 提示框显示格式化

`tooltipFormat` 选项用于配置耐久度加成在物品提示框上的展示格式：

| 格式设置项 | 示例输出 (200% / 2x) | 示例输出 (150% / 1.5x) | 说明 |
| :--- | :--- | :--- | :--- |
| `"ADAPTIVE"` *(默认)* | `⟨2x 剑类耐久度⟩` | `⟨150% 剑类耐久度⟩` | 整百倍率显示整洁整数；非整百显示明确百分比。 |
| `"PERCENTAGE"` | `⟨200% 剑类耐久度⟩` | `⟨150% 剑类耐久度⟩` | 始终显示精确百分比数值。 |
| `"MULTIPLIER"` | `⟨2x 剑类耐久度⟩` | `⟨1.5x 剑类耐久度⟩` | 始终显示格式化的浮点倍率字符串。 |

设置 `"showTooltip": false` 可完全隐藏提示框中的耐久度指示文本行。

---

## ⚠️ 重要配置优先级警告

> ⚠️ **注意**：在 `durability-multiplier.json` 或 ModMenu 界面中做出的修改**仅定义新建世界的初始默认值**。
> 
> 对于现存的活跃世界，每个世界都在存档数据 (`level.dat`) 中维护独立的 GameRule 状态。若要修改现有世界设置，请使用游戏内 `/gamerule` 指令或原版游戏规则编辑界面。

