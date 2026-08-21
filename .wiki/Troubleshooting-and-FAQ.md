# Troubleshooting & FAQ (26.2)

### Q1: Why do config changes in ModMenu not affect my active singleplayer world?
**Answer**: As designed by the **Precedence Law**, changes made in `durability-multiplier.json` or the ModMenu GUI define default baseline values for **NEW worlds only**. To change settings in your current world, use the in-game `/gamerule` command (e.g. `/gamerule ig:dm_multiplier_tools 5`) or the GameRules edit screen.

### Q2: Why does an item tooltip not show multiplier text?
**Answer**:
1. Verify that the item is a damageable item with a durability bar.
2. Check if `ig:dm_show_tooltip` is set to `true`.
3. If the active multiplier is `1` (vanilla durability), no extra tooltip line is rendered to keep tooltips clean.

### Q3: Does Durability Multiplier work with Unbreaking enchantments?
**Answer**: Yes! Durability Multiplier reduces incoming damage **before** vanilla enchantment processing. An Unbreaking III pickaxe with a 2x multiplier will last approximately $4\times 2 = 8\times$ longer than a vanilla unenchanted pickaxe.

### Q4: Does Durability Multiplier work with modded items?
**Answer**: Yes! Standard modded weapons, tools, and armor (such as Mythic Metals or Simply Swords) that use vanilla tags (`#minecraft:swords`, `#minecraft:axes`) are automatically classified. Uncategorized modded items are detected on startup by the [[Dynamic Scanner|Dynamic-Modded-Item-Registration]].
