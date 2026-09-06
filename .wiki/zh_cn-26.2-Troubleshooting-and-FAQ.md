# 常见问题与故障排除 (26.2)

| 系统主题 | 概要 |
| :--- | :--- |
| **优先级行为** | 活跃世界中游戏规则覆盖配置；配置文件仅定义新世界默认值 |
| **计算引擎** | 概率损耗拦截（零 NBT 篡改，零存档脱节） |
| **边缘容错能力** | 模组移除、注册表解绑和组件缺失期间 100% 免于崩溃 |

---

## ❓ 常见问题解答 (FAQ)

### Q1: 为什么在 ModMenu 中更改的配置不会影响我正在游玩的单人世界？
**回答**: 根据**优先级铁律**的设计，在 `durability-multiplier.json` 或 ModMenu 界面中所做的修改仅定义**新世界的基准默认值**。若要更改当前世界中的设置，请使用游戏内 `/gamerule` 指令（例如 `/gamerule ig:dm_percent_tools 500`）或原版游戏规则编辑界面。

### Q2: 为什么物品提示框没有显示百分比或倍率文本？
**回答**:
1. 确认该物品是拥有耐久条的可损耗物品 (`DataComponents.MAX_DAMAGE > 0`)。
2. 检查 `ig:dm_show_tooltip` 是否已设置为 `true`。
3. 若当前生效设置为 `100`（100% 原版耐久），为保持提示框整洁不会渲染额外行。

### Q3: 为什么我的 500% (5x) 工具只用了 2 次就消耗了耐久度？
**回答**: Durability Multiplier 采用**概率损耗拦截机制**（与 Minecraft 原版*耐久*附魔完全相同的机制），以确保 **100% 存档安全**。在 500%（5x 耐久）下，每次破坏方块均有独立的 **20% 几率（5分之1）**造成 1 点损耗，以及 **80% 几率**吸收损耗。因为每次命中均独立判定，你可能偶尔会在 2 次或 8 次后承受损耗，但在工具的整个使用寿命中，它的总寿命恰好延长 5 倍（钻石镐约破坏 7,805 个方块）。

### Q4: 我应该在游戏规则中输入像 0.5 或 1.5 这样的小数吗？
**回答**: **否**。Minecraft 游戏规则仅接受整数 (`int`)。请始终输入完整的百分比整数：
* `50` 代表 50%（半数耐久 / 2x 磨损）
* `100` 代表 100%（1x 原版标准）
* `150` 代表 150%（1.5x 耐久提升）
* `200` 代表 200%（2x 双倍耐久）
* `-1` 代表单次使用（玻璃模式 / 1 次命中即碎）

### Q5: Durability Multiplier 可以与耐久附魔（Unbreaking）同时生效吗？
**回答**: 是的！Durability Multiplier 在原版附魔判定**之前**缩放传入损耗。一把附有耐久 III 且设置为 200%（2x）的镐子，其实际寿命约为无附魔原版镐的 $4 \times 2 = 8\times$ 倍。

### Q6: 我该如何为某件物品开启 1 次命中破碎的玻璃模式（单次使用）？
**回答**: 你可以采取以下任一方式：
1. 将单次使用游戏规则设为 true：`/gamerule ig:dm_single_use_swords true`（或 `/gamerule ig:single_use_<mod>_<item> true`）。
2. 使用**高级用户 `-1` 哨兵值**：将百分比规则设为 `-1`，例如 `/gamerule ig:dm_percent_swords -1` 或 `/gamerule ig:percent_<mod>_<item> -1`。

---

## 🔍 深度边缘情况与生命周期行为

### 边缘情况 1：模组卸载与物品被删除
当玩家删除或移除了曾注册在 Durability Multiplier 中的自定义物品模组时：
1. **配置文件安全**：已移除物品的 ID 仍安全保留在 `config/durability-multiplier.json` 的 `forcedItems` 和 `forcedPercentages` 中。
2. **静默世界状态**：存储在世界 `level.dat` 中的任何动态规则 (`ig:percent_<mod>_<item>`, `ig:infinity_<mod>_<item>`) 将在内存中完全静默休眠。
3. **零崩溃与零损坏**：由于物品查询通过 `BuiltInRegistries.ITEM.getKey(stack.getItem())` 进行门禁把关，游戏绝不会尝试查找缺失的类或未映射的 ID。绝不可能发生 `NullPointerException`、`ClassNotFoundException` 或区块损坏。
4. **重新安装自动恢复**：若未来重新安装该模组，之前的所有耐久百分比、上帝模式及单次使用配置都将**瞬间重新绑定**，无需重新配置！
5. **手动清理配置（可选）**：如果你希望从配置中清除已删除模组的词条：
   * Open `config/durability-multiplier.json` in a text editor.
   * Remove the deleted item ID string from the `"forcedItems"` list.
   * Remove the corresponding keys from `"forcedPercentages"`, `"forcedInfinities"`, and `"forcedSingleUses"`.
   * Save the file.

---

### 边缘情况 2：严格耐久度过滤 (`MAX_DAMAGE > 0`)
为什么家具模组（如 Macaw's Furniture 的椅子/衣柜）、建筑方块、食物或合成材料不会出现在 GameRules 或 `durability-multiplier.json` 中？
* Durability Multiplier 在注册任何物品前均严格验证 `DataComponents.MAX_DAMAGE > 0`。
* 不含耐久组件的物品（方块、放置物、食物、锭、种子）在启动扫描期间于 $0.0001\mu\text{s}$ 内被快速排除。
* 这可以防止命名空间污染，并确保游戏规则的 Tab 补全列表保持整洁高效。

---

### 边缘情况 3：完整评估判定与优先级层级
当物品承受耐久度损耗时，其结果由以下严格的评估判定层级决定：

$$\text{God Mode (Infinity)} \longrightarrow \text{Single-Use (Glass Mode)} \longrightarrow \text{Per-Item Override} \longrightarrow \text{Subcategory} \longrightarrow \text{Parent Category} \longrightarrow \text{Global} \longrightarrow \text{100\% Vanilla}$$

1. **无法破坏上帝模式判定**：
   * Per-Item God Mode (`ig:infinity_<mod>_<item>` / `forcedInfinities`)
   * Subcategory God Mode (`ig:dm_infinity_pickaxes`, `ig:dm_infinity_swords`, etc.)
   * Parent Category God Mode (`ig:dm_infinity_tools`, `ig:dm_infinity_weapons`, `ig:dm_infinity_armor`)
   * Global God Mode (`ig:dm_infinity_global`)
   * *If any is `true` $\rightarrow$ Damage = $0$ (Unbreakable).*
2. **单次使用（玻璃模式）判定**：
   * Effective percentage $\le -1$ (`-1` Glass Mode Sentinel)
   * Per-Item Single-Use (`ig:single_use_<mod>_<item>` / `forcedSingleUses`)
   * Subcategory Single-Use (`ig:dm_single_use_pickaxes`, `ig:dm_single_use_swords`, etc.)
   * Parent Category Single-Use (`ig:dm_single_use_tools`, `ig:dm_single_use_weapons`, `ig:dm_single_use_armor`)
   * Global Single-Use (`ig:dm_single_use_global`)
   * *If active $\rightarrow$ Item loses all remaining durability in 1 hit.*
3. **百分比缩放解析**：
   * Per-Item Override (`ig:percent_<mod>_<item>` / `forcedPercentages`) if $\neq 0$
   * Subcategory Percentage (`ig:dm_percent_pickaxes`, `ig:dm_percent_swords`, etc.) if $\neq 0$
   * Parent Category Percentage (`ig:dm_percent_tools`, `ig:dm_percent_weapons`, `ig:dm_percent_armor`) if $\neq 0$
   * Global Percentage (`ig:dm_percent_global`) if $\neq 0$
   * Vanilla Fallback: $100\%$ baseline.

