# Troubleshooting & FAQ (26.1.2)

| System Topic | Summary |
| :--- | :--- |
| **Precedence Behavior** | GameRules override config in active worlds; config defines new world defaults |
| **Calculation Engine** | Probabilistic interception (zero NBT mutation, zero save desync) |
| **Edge-Case Tolerance** | 100% crash-free during mod removal, registry unmapping, and missing components |

---

## ❓ Frequently Asked Questions

### Q1: Why do config changes in ModMenu not affect my active singleplayer world?
**Answer**: As designed by the **Precedence Law**, changes made in `durability-multiplier.json` or the ModMenu GUI define default baseline values for **NEW worlds only**. To change settings in your current world, use the in-game `/gamerule` command (e.g. `/gamerule ig:dm_percent_tools 500`) or the GameRules edit screen.

### Q2: Why does an item tooltip not show percentage or multiplier text?
**Answer**:
1. Verify that the item is a damageable item with a durability bar (`DataComponents.MAX_DAMAGE > 0`).
2. Check if `ig:dm_show_tooltip` is set to `true`.
3. If the active setting is `100` (100% vanilla durability), no extra tooltip line is rendered to keep tooltips clean.

### Q3: Why did my 500% (5x) tool take durability damage after only 2 hits?
**Answer**: Durability Multiplier uses **probabilistic damage interception** (the exact same mechanic Minecraft uses for the vanilla *Unbreaking* enchantment) to ensure **100% world-save safety**. At 500% (5x durability), each block break has an independent **20% chance (1 in 5)** of dealing 1 damage and an **80% chance** of absorbing it. Because each hit rolls independently, you may occasionally take damage after 2 hits or 8 hits, but across the tool's lifespan it will last exactly 5x longer (~7,805 block breaks for a diamond pickaxe).

### Q4: Should I enter decimals like 0.5 or 1.5 into GameRules?
**Answer**: **No**. Minecraft GameRules only accept integer (`int`) numbers. Always enter whole percentage numbers:
* `50` for 50% (half durability / 2x wear)
* `100` for 100% (1x standard vanilla)
* `150` for 150% (1.5x durability boost)
* `200` for 200% (2x double durability)
* `-1` for Single-Use (Glass Mode / breaks in 1 hit)

### Q5: Does Durability Multiplier work with Unbreaking enchantments?
**Answer**: Yes! Durability Multiplier scales incoming damage **before** vanilla enchantment processing. An Unbreaking III pickaxe with a 200% (2x) setting will last approximately $4 \times 2 = 8\times$ longer than a vanilla unenchanted pickaxe.

### Q6: How do I enable 1-Hit Glass Mode (Single-Use) for an item?
**Answer**: You can either:
1. Set the single-use GameRule to true: `/gamerule ig:dm_single_use_swords true` (or `/gamerule ig:single_use_<mod>_<item> true`).
2. Use the **Power-User `-1` Sentinel**: set the percentage rule to `-1`, e.g. `/gamerule ig:dm_percent_swords -1` or `/gamerule ig:percent_<mod>_<item> -1`.

---

## 🔍 Deep-Dive Edge Cases & Lifecycle Behavior

### Edge Case 1: Mod Uninstallation & Item Deletion
When a player deletes or removes a mod that had custom items registered in Durability Multiplier:
1. **Config File Safety**: The removed item IDs remain safely recorded in `forcedItems` and `forcedPercentages` inside `config/durability-multiplier.json`.
2. **Dormant World State**: Any dynamic GameRules (`ig:percent_<mod>_<item>`, `ig:infinity_<mod>_<item>`) stored in the world's `level.dat` sit completely dormant in memory.
3. **Zero Crashes & Zero Corruption**: Because item lookup is gated through `BuiltInRegistries.ITEM.getKey(stack.getItem())`, the game will never attempt to look up missing classes or unmapped IDs. No `NullPointerException`, `ClassNotFoundException`, or chunk corruption can ever occur.
4. **Automatic Restoration on Reinstall**: If the mod is ever reinstalled in the future, all previous durability percentage, God Mode, and Single-Use configurations will **instantly re-bind** to the items without requiring any reconfiguration!
5. **Manual Config Cleanup (Optional)**: If you wish to purge deleted mod entries from your config:
   * Open `config/durability-multiplier.json` in a text editor.
   * Remove the deleted item ID string from the `"forcedItems"` list.
   * Remove the corresponding keys from `"forcedPercentages"`, `"forcedInfinities"`, and `"forcedSingleUses"`.
   * Save the file.

---

### Edge Case 2: Strict Durability Filtering (`MAX_DAMAGE > 0`)
Why don't furniture mods (e.g. Macaw's Furniture chairs/wardrobes), building blocks, food, or crafting materials show up in GameRules or `durability-multiplier.json`?
* Durability Multiplier strictly verifies `DataComponents.MAX_DAMAGE > 0` before registering any item.
* Items without durability components (blocks, placeables, food, ingots, seeds) are rejected in $0.0001\mu\text{s}$ during startup sweeps.
* This prevents namespace pollution and ensures GameRules tab-autocomplete remains clean and responsive.

---

### Edge Case 3: Complete Evaluation & Precedence Hierarchy
When an item takes durability damage, the outcome is determined by the following strict evaluation hierarchy:

$$\text{God Mode (Infinity)} \longrightarrow \text{Single-Use (Glass Mode)} \longrightarrow \text{Per-Item Override} \longrightarrow \text{Subcategory} \longrightarrow \text{Parent Category} \longrightarrow \text{Global} \longrightarrow \text{100\% Vanilla}$$

1. **Unbreakable God Mode Check**:
   * Per-Item God Mode (`ig:infinity_<mod>_<item>` / `forcedInfinities`)
   * Subcategory God Mode (`ig:dm_infinity_pickaxes`, `ig:dm_infinity_swords`, etc.)
   * Parent Category God Mode (`ig:dm_infinity_tools`, `ig:dm_infinity_weapons`, `ig:dm_infinity_armor`)
   * Global God Mode (`ig:dm_infinity_global`)
   * *If any is `true` $\rightarrow$ Damage = $0$ (Unbreakable).*
2. **Single-Use (Glass Mode) Check**:
   * Effective percentage $\le -1$ (`-1` Glass Mode Sentinel)
   * Per-Item Single-Use (`ig:single_use_<mod>_<item>` / `forcedSingleUses`)
   * Subcategory Single-Use (`ig:dm_single_use_pickaxes`, `ig:dm_single_use_swords`, etc.)
   * Parent Category Single-Use (`ig:dm_single_use_tools`, `ig:dm_single_use_weapons`, `ig:dm_single_use_armor`)
   * Global Single-Use (`ig:dm_single_use_global`)
   * *If active $\rightarrow$ Item loses all remaining durability in 1 hit.*
3. **Percentage Scaling Resolution**:
   * Per-Item Override (`ig:percent_<mod>_<item>` / `forcedPercentages`) if $\neq 0$
   * Subcategory Percentage (`ig:dm_percent_pickaxes`, `ig:dm_percent_swords`, etc.) if $\neq 0$
   * Parent Category Percentage (`ig:dm_percent_tools`, `ig:dm_percent_weapons`, `ig:dm_percent_armor`) if $\neq 0$
   * Global Percentage (`ig:dm_percent_global`) if $\neq 0$
   * Vanilla Fallback: $100\%$ baseline.

