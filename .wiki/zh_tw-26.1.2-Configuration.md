# 設定與 GUI 介面整合 (26.1.2)

| 系统參數 | 取值 |
| :--- | :--- |
| **配置文件路径** | `config/durability-multiplier.json` |
| **配置版本** | `2` (自 v1 自動迁移) |
| **GUI 界面提供庫** | Cloth Config (`me.shedaniel.cloth:cloth-config-fabric`) 与 ModMenu |
| **配置數據類** | `net.instantgratification.durabilitymultiplier.config.DurabilityConfig` |
| **GUI 辅助類** | `ClothConfigScreenHelper` 与 `ModMenuIntegration` |
| **优先級鐵律** | 配置文件**仅定义新建世界默认值**；活跃世界完全以游戏規則為准 |

---

## ⚙️ 設定檔結構 (`config/durability-multiplier.json`)

该配置文件定义了所有新建單人世界和多人服务器的基础基线默认值。它支持耐久百分比、上帝模式（無限耐久）、單次使用（玻璃模式）、自定义提示框格式以及動態模組物品覆蓋配置。

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

## 🔄 自動填入系統

Durability Multiplier 拥有自主的**通用 3 級發现扫描器**，可自動归檔模組物品，無需任何手動數據錄入：

1. **啟動全面扫描**：在客戶端/服务端啟動時，引擎自動扫描 `BuiltInRegistries.ITEM`。
2. **可損耗过滤**：检查外部模組命名空间中的物品（排除 `minecraft` 和通用约定標籤 `c`）是否具備 `DataComponents.MAX_DAMAGE > 0`。
3. **自動填充**：扫描發现的可損耗物品会自動追加至：
   * `"forcedItems"`: Added to the active item registry list.
   * `"forcedPercentages"`: Added with default value `0` (indicating the item dynamically inherits its category or global multiplier).
4. **配置持久化**：更新后的列表会回寫保存到 `config/durability-multiplier.json`，使所有模組物品立即在 Cloth Config / ModMenu 界面和游戏内規則中可見且可编辑。

---

## 🛠️ 手動物品設定指南

整合封包作者、服务器管理员和玩家可以直接在 `config/durability-multiplier.json` 中為特定物品聲明自定义規則：

### 1. `forcedItems`（物品顯式註冊）
聲明本模組識别管理的物品資源標識符 (Identifier) 列表。
```json
"forcedItems": [
  "techmod:plasma_cutter",
  "magicmod:staff_of_fire",
  "customweapons:obsidian_blade"
]
```

### 2. `forcedPercentages`（單品耐久百分比設定）
為特定物品指定明確的耐久度百分比倍率：
* `0`: 繼承父分類或全局倍率。
* `100`: 原版 100% 基准（1x 耐久）。
* `200`: 200% 耐久度（2x 寿命）。
* `50`: 50% 耐久度（半數寿命 / 2x 磨損）。
* `-1`: 單次使用（玻璃模式 - 首次命中即碎）。
```json
"forcedPercentages": {
  "techmod:plasma_cutter": 300,
  "customweapons:obsidian_blade": -1,
  "magicmod:training_wand": 50
}
```

### 3. `forcedInfinities`（單品上帝模式）
為特定物品赋予永久無法破壞的上帝模式状態：
```json
"forcedInfinities": {
  "magicmod:creative_staff": true,
  "adminmod:ban_hammer": true
}
```

### 4. `forcedSingleUses`（單品玻璃模式）
强制特定物品在單次耐久損耗事件后彻底破碎：
```json
"forcedSingleUses": {
  "techmod:disposable_cutter": true,
  "survivalplus:glass_dagger": true
}
```

---

## ⚡ 進階使用者 `-1` 玻璃模式哨兵值

Durability Multiplier 為耐久度百分比引入了 **`-1` 哨兵值**：
* 将任何百分比規則或配置字段設為 `-1`（或任何負整數）会自動為该物品或分類激活**單次使用（玻璃模式）**。
* 激活時，物品在首次命中時承受 `maxDamage - damageValue` 損耗，将其耐久度直接清零并在恰好 1 次使用后破碎。
* 這允许服主和整合封包作者直接通过百分比滑块或 `/gamerule` 指令强制執行 1 次破碎機制，無需切換獨立的布尔規則。

---

## 🎨 提示框顯示格式化

`tooltipFormat` 选項用于配置耐久度加成在物品提示框上的展示格式：

| 格式設置項 | 示例输出 (200% / 2x) | 示例输出 (150% / 1.5x) | 說明 |
| :--- | :--- | :--- | :--- |
| `"ADAPTIVE"` *(默认)* | `⟨2x 剑类耐久度⟩` | `⟨150% 剑类耐久度⟩` | 整百倍率顯示整洁整數；非整百顯示明確百分比。 |
| `"PERCENTAGE"` | `⟨200% 剑类耐久度⟩` | `⟨150% 剑类耐久度⟩` | 始终顯示精確百分比數值。 |
| `"MULTIPLIER"` | `⟨2x 剑类耐久度⟩` | `⟨1.5x 剑类耐久度⟩` | 始终顯示格式化的浮點倍率字符串。 |

設置 `"showTooltip": false` 可完全隱藏提示框中的耐久度指示文本行。

---

## ⚠️ 重要設定優先級警告

> ⚠️ **注意**：在 `durability-multiplier.json` 或 ModMenu 界面中做出的修改**仅定义新建世界的初始默认值**。
> 
> 对于现存的活跃世界，每个世界都在存檔數據 (`level.dat`) 中维護獨立的 GameRule 状態。若要修改现有世界設置，请使用游戏内 `/gamerule` 指令或原版游戏規則编辑界面。

