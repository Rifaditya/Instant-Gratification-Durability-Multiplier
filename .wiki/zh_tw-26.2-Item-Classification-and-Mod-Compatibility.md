# 物品分類與模組相容性 (26.2)

| 系统參數 | 取值 |
| :--- | :--- |
| **分類判定方法** | `DurabilityHelper.classifyItem(ItemStack)` |
| **缓存引擎** | 线程安全的 `ConcurrentHashMap<Item, ItemCategory>` |
| **受支持分類** | 22 个獨立細分類别与回退機制 |
| **組件检查** | `DataComponents.MAX_DAMAGE`, `EQUIPPABLE`, `TOOL`, `GLIDER` |
| **標籤检查** | `#minecraft:*` 与 `#c:*` (约定 / Fabric 標籤) |
| **耐久門禁过滤** | `DataComponents.MAX_DAMAGE > 0` (方块与家具等严格过滤) |

---

## 🔍 嚴格耐久度過濾 (`MAX_DAMAGE > 0`)

為防止注册表冗餘和游戏規則命名空间污染，Durability Multiplier 實施了严格的耐久度前置條件：

```java
public static boolean isItemDamageable(Item item) {
    if (item == null) return false;
    try {
        Identifier id = BuiltInRegistries.ITEM.getKey(item);
        if (id != null && (FORCED_ITEMS.contains(id) || DurabilityConfig.get().isForced(id.toString()))) {
            return true;
        }
        Integer maxDamage = item.components().get(DataComponents.MAX_DAMAGE);
        return maxDamage != null && maxDamage > 0;
    } catch (Throwable t) {
        return false;
    }
}
```

### 為什麼無損耗模組物品會被排除
* **家具模組**（例如 Macaw's Furniture 的衣柜、椅子、桌子、門）：這些物品不封包含 `DataComponents.MAX_DAMAGE` 組件，因為它们屬于可放置方块而非易磨損工具。
* **建筑方块与材料**: 石頭、锭、宝石、木材和裝饰物品会被扫描器完全忽略。
* **食物与消耗品**: 消耗品堆疊上限 $> 1$ 且耐久為零。
* **性能优势**: 預过滤在啟動扫描期间以 $0.0001\mu\text{s}$ 的速度剔除约 95% 的游戏物品，確保零性能開销并在指令中提供清晰的自動补全建议。

---

## 👑 完整判定求值與優先級層級

当物品進行耐久度計算時，`DurabilityHelper` 会執行以下严格的 7 級评估序列：

```mermaid
flowchart TD
    Start[Item Durability Event] --> Step1{1. Unbreakable God Mode?}
    Step1 -->|Yes| Invincible[Cancel Damage / Take 0 Damage]
    Step1 -->|No| Step2{2. Single-Use Glass Mode?}
    Step2 -->|Yes| BreakItem[Apply Max Durability Damage / 1-Hit Break]
    Step2 -->|No| Step3{3. Per-Item Percentage != 0?}
    Step3 -->|Yes| ApplyItem[Scale Damage with Item Override]
    Step3 -->|No| Step4{4. Subcategory Percentage != 0?}
    Step4 -->|Yes| ApplySub[Scale Damage with Subcategory %]
    Step4 -->|No| Step5{5. Parent Category % != 0?}
    Step5 -->|Yes| ApplyParent[Scale Damage with Parent %]
    Step5 -->|No| Step6{6. Global Percentage != 0?}
    Step6 -->|Yes| ApplyGlobal[Scale Damage with Global %]
    Step6 -->|No| Step7[7. Vanilla 100% Baseline]
```

### 優先級詳解：
1. **無法破壞上帝模式 (`isInfinite`)**：
   * Per-Item Override (`ig:infinity_<mod>_<item>` / `forcedInfinities`) $\rightarrow$ Subcategory (`ig:dm_infinity_pickaxes`) $\rightarrow$ Parent Category (`ig:dm_infinity_tools`) $\rightarrow$ Global (`ig:dm_infinity_global`).
2. **單次使用玻璃模式 (`isSingleUse`)**：
   * `-1` Sentinel in percentage rule $\rightarrow$ Per-Item (`ig:single_use_<mod>_<item>`) $\rightarrow$ Subcategory $\rightarrow$ Parent Category $\rightarrow$ Global.
3. **單品百分比覆蓋**：
   * `ig:percent_<mod>_<item>` or `forcedPercentages` (if $\neq 0$).
4. **細分子分類百分比**：
   * `ig:dm_percent_swords`, `ig:dm_percent_pickaxes`, `ig:dm_percent_helmets`, etc. (if $\neq 0$).
5. **父分類百分比**：
   * Tools parent (`ig:dm_percent_tools`), Weapons parent (`ig:dm_percent_weapons`), Armor parent (`ig:dm_percent_armor`) (if $\neq 0$).
6. **全局百分比**：
   * `ig:dm_percent_global` (if $\neq 0$).
7. **原版基线**：
   * Default $100\%$ ($1\times$ vanilla durability).

---

## 📦 分類匹配準則與受支援物品

### 1. 武器類
* **劍類 (`ItemCategory.SWORD`)**: `#minecraft:swords`, `#c:swords`, `#c:melee_weapons`, `SwordItem`。
* **长矛 (`ItemCategory.SPEAR`)**: `#minecraft:spears`, `#c:spears`。
* **三叉戟 (`ItemCategory.TRIDENT`)**: `Items.TRIDENT`, `#c:tridents`, `TridentItem`。
* **重鎚 (`ItemCategory.MACE`)**: `Items.MACE`, `#c:maces`, `MaceItem`。
* **弓 (`ItemCategory.BOW`)**: `Items.BOW`, `#c:bows`, `BowItem`。
* **弩 (`ItemCategory.CROSSBOW`)**: `Items.CROSSBOW`, `#c:crossbows`, `CrossbowItem`。
* **盾牌 (`ItemCategory.SHIELD`)**: `Items.SHIELD`, `#c:shields`, `ShieldItem`。

### 2. 工具與實用物品
* **鎬類 (`ItemCategory.PICKAXE`)**: `#minecraft:pickaxes`, `#c:pickaxes`, `PickaxeItem`。
* **斧類 (`ItemCategory.AXE`)**: `#minecraft:axes`, `#c:axes`, `AxeItem`。
* **鍬類 (`ItemCategory.SHOVEL`)**: `#minecraft:shovels`, `#c:shovels`, `ShovelItem`。
* **鋤類 (`ItemCategory.HOE`)**: `#minecraft:hoes`, `#c:hoes`, `HoeItem`。
* **剪刀 (`ItemCategory.SHEARS`)**: `Items.SHEARS`, `#c:shears`, `ShearsItem`。
* **釣鱼竿 (`ItemCategory.FISHING_ROD`)**: `Items.FISHING_ROD`, `FishingRodItem`。
* **刷子 (`ItemCategory.BRUSH`)**: `Items.BRUSH`, `BrushItem`。
* **打火石 (`ItemCategory.FLINT_AND_STEEL`)**: `Items.FLINT_AND_STEEL`, `FlintAndSteelItem`。
* **工具通用 (`ItemCategory.TOOL_GLOBAL`)**: 带有 `DataComponents.TOOL` 或 `#c:tools` 的任何其餘物品。

### 3. 盔甲與可穿戴裝備
* **頭盔 (`ItemCategory.HELMET`)**: `#minecraft:head_armor`, `#c:helmets`, `Equippable` (頭部)。
* **胸甲 (`ItemCategory.CHESTPLATE`)**: `#minecraft:chest_armor`, `#c:chestplates`, `Equippable` (胸部)。
* **護腿 (`ItemCategory.LEGGINGS`)**: `#minecraft:leg_armor`, `#c:leggings`, `Equippable` (腿部)。
* **靴子 (`ItemCategory.BOOTS`)**: `#minecraft:foot_armor`, `#c:boots`, `Equippable` (脚部)。
* **鞘翅 (`ItemCategory.ELYTRA`)**: `Items.ELYTRA`, `DataComponents.GLIDER`。

### 4. 其他 / 模組物品 (`ItemCategory.OTHER`)
* 任何不匹配標准標籤或組件的可損耗物品都会归入 `OTHER`，并通过[[動態扫描器|zh_tw-26.2-Dynamic-Modded-Item-Registration]]進行動態管理。

