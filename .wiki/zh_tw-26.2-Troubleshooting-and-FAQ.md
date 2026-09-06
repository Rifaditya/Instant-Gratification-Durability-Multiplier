# 常見問題與疑難排解 (26.2)

| 系统主題 | 概要 |
| :--- | :--- |
| **优先級行為** | 活跃世界中游戏規則覆蓋配置；配置文件仅定义新世界默认值 |
| **計算引擎** | 概率損耗拦截（零 NBT 篡改，零存檔脱节） |
| **邊緣容错能力** | 模組移除、注册表解绑和組件缺失期间 100% 免于崩溃 |

---

## ❓ 常見問題解答 (FAQ)

### Q1: 為什麼在 ModMenu 中變更的設定不會影響我正在遊玩的單人世界？
**回答**: 根據**优先級鐵律**的設計，在 `durability-multiplier.json` 或 ModMenu 界面中所做的修改仅定义**新世界的基准默认值**。若要更改当前世界中的設置，请使用游戏内 `/gamerule` 指令（例如 `/gamerule ig:dm_percent_tools 500`）或原版游戏規則编辑界面。

### Q2: 為什麼物品提示框沒有顯示百分比或倍率文字？
**回答**:
1. 確认该物品是拥有耐久條的可損耗物品 (`DataComponents.MAX_DAMAGE > 0`)。
2. 检查 `ig:dm_show_tooltip` 是否已設置為 `true`。
3. 若当前生效設置為 `100`（100% 原版耐久），為保持提示框整洁不会渲染额外行。

### Q3: 為什麼我的 500% (5x) 工具只用了 2 次就消耗了耐久度？
**回答**: Durability Multiplier 采用**概率損耗拦截機制**（与 Minecraft 原版*耐久*附魔完全相同的機制），以確保 **100% 存檔安全**。在 500%（5x 耐久）下，每次破壞方块均有獨立的 **20% 几率（5分之1）**造成 1 點損耗，以及 **80% 几率**吸收損耗。因為每次命中均獨立判定，你可能偶尔会在 2 次或 8 次后承受損耗，但在工具的整个使用寿命中，它的總寿命恰好延长 5 倍（钻石鎬约破壞 7,805 个方块）。

### Q4: 我應該在遊戲規則中輸入像 0.5 或 1.5 這樣的小數嗎？
**回答**: **否**。Minecraft 游戏規則仅接受整數 (`int`)。请始终输入完整的百分比整數：
* `50` 代表 50%（半數耐久 / 2x 磨損）
* `100` 代表 100%（1x 原版標准）
* `150` 代表 150%（1.5x 耐久提升）
* `200` 代表 200%（2x 双倍耐久）
* `-1` 代表單次使用（玻璃模式 / 1 次命中即碎）

### Q5: Durability Multiplier 可以與耐久附魔（Unbreaking）同時生效嗎？
**回答**: 是的！Durability Multiplier 在原版附魔判定**之前**缩放传入損耗。一把附有耐久 III 且設置為 200%（2x）的鎬子，其實际寿命约為無附魔原版鎬的 $4 \times 2 = 8\times$ 倍。

### Q6: 我該如何為某件物品開啟 1 次命中破碎的玻璃模式（單次使用）？
**回答**: 你可以采取以下任一方式：
1. 将單次使用游戏規則設為 true：`/gamerule ig:dm_single_use_swords true`（或 `/gamerule ig:single_use_<mod>_<item> true`）。
2. 使用**高級用戶 `-1` 哨兵值**：将百分比規則設為 `-1`，例如 `/gamerule ig:dm_percent_swords -1` 或 `/gamerule ig:percent_<mod>_<item> -1`。

---

## 🔍 深度邊緣案例與生命週期行為

### 邊緣案例 1：模組解除安裝與物品被刪除
当玩家删除或移除了曾注册在 Durability Multiplier 中的自定义物品模組時：
1. **配置文件安全**：已移除物品的 ID 仍安全保留在 `config/durability-multiplier.json` 的 `forcedItems` 和 `forcedPercentages` 中。
2. **靜默世界状態**：存儲在世界 `level.dat` 中的任何動態規則 (`ig:percent_<mod>_<item>`, `ig:infinity_<mod>_<item>`) 将在内存中完全靜默休眠。
3. **零崩溃与零損壞**：由于物品查詢通过 `BuiltInRegistries.ITEM.getKey(stack.getItem())` 進行門禁把關，游戏绝不会尝試查找缺失的類或未映射的 ID。绝不可能發生 `NullPointerException`、`ClassNotFoundException` 或区块損壞。
4. **重新安裝自動恢复**：若未来重新安裝该模組，之前的所有耐久百分比、上帝模式及單次使用配置都将**瞬间重新绑定**，無需重新配置！
5. **手動清理配置（可选）**：如果你希望從配置中清除已删除模組的词條：
   * Open `config/durability-multiplier.json` in a text editor.
   * Remove the deleted item ID string from the `"forcedItems"` list.
   * Remove the corresponding keys from `"forcedPercentages"`, `"forcedInfinities"`, and `"forcedSingleUses"`.
   * Save the file.

---

### 邊緣案例 2：嚴格耐久度過濾 (`MAX_DAMAGE > 0`)
為什麼家具模組（如 Macaw's Furniture 的椅子/衣柜）、建筑方块、食物或合成材料不会出现在 GameRules 或 `durability-multiplier.json` 中？
* Durability Multiplier 在注册任何物品前均严格驗證 `DataComponents.MAX_DAMAGE > 0`。
* 不含耐久組件的物品（方块、放置物、食物、锭、种子）在啟動扫描期间于 $0.0001\mu\text{s}$ 内被快速排除。
* 這可以防止命名空间污染，并確保游戏規則的 Tab 补全列表保持整洁高效。

---

### 邊緣案例 3：完整評估判定與優先級層級
当物品承受耐久度損耗時，其結果由以下严格的评估判定层級决定：

$$\text{God Mode (Infinity)} \longrightarrow \text{Single-Use (Glass Mode)} \longrightarrow \text{Per-Item Override} \longrightarrow \text{Subcategory} \longrightarrow \text{Parent Category} \longrightarrow \text{Global} \longrightarrow \text{100\% Vanilla}$$

1. **無法破壞上帝模式判定**：
   * Per-Item God Mode (`ig:infinity_<mod>_<item>` / `forcedInfinities`)
   * Subcategory God Mode (`ig:dm_infinity_pickaxes`, `ig:dm_infinity_swords`, etc.)
   * Parent Category God Mode (`ig:dm_infinity_tools`, `ig:dm_infinity_weapons`, `ig:dm_infinity_armor`)
   * Global God Mode (`ig:dm_infinity_global`)
   * *If any is `true` $\rightarrow$ Damage = $0$ (Unbreakable).*
2. **單次使用（玻璃模式）判定**：
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

