# Player Features

## Durability Multiplier

Make your tools, weapons, and armor last longer, tune custom wear penalties, or last forever.

### Feature: Durability Scaling & Multiplier
Scale the effective durability of any damageable item by percentages:
- **`200%` (2x)**: Your Diamond Pickaxe lasts **3,124 uses** instead of 1,562 (set GameRule to `200`).
- **`50%` (0.5x)**: Hardcore durability challenge where items wear down twice as fast (set GameRule to `50`).
- **`150%` (1.5x)**: Fine-tuned boost for extended exploration without excessive durability (set GameRule to `150`).

> [!NOTE]
> **Always Use Whole Percentages, Not Decimals**: Enter `50` for 50% half durability, `150` for 1.5x, and `200` for 2x double durability. Do **not** enter decimals like `0.5` or `1.5`.

### 🛡️ How It Works (Under the Hood)
1. **100% World-Save Safe**: Durability Multiplier intercepts incoming damage events at runtime rather than mutating `DataComponents` or NBT data on your items. Your items always store clean vanilla durability data in the world save. If you change GameRules or remove the mod at any time, your items in chests and inventories are never corrupted or permanently altered.
2. **Probabilistic Damage Absorption (Like Vanilla Unbreaking)**:
   - At **`200%` (2x)**, each hit has a **50% chance** of absorbing wear (dealing 0 damage) and a **50% chance** of taking 1 damage.
   - At **`500%` (5x)**, each hit has an **80% chance** of taking 0 damage and a **20% chance** of taking 1 damage. Because each hit rolls independently (just like Minecraft's Unbreaking enchantment), damage intervals vary naturally during short tests, while converging to exact 5x longevity over the item's lifespan.
   - At **`50%` (0.5x)**, each hit deals **2 damage** consistently (doubled wear).
3. **Flawless Anvil & Mending Compatibility**: Because vanilla item maximum durability is preserved, anvil repair costs and XP Mending absorption rates remain completely balanced and standard.

### Feature: God Mode (Infinity)
Toggle complete invincibility for item categories. With God Mode active, your Elytra will **never break** — fly as long as you want.

### Feature: Category Overrides
Set individual durability percentages per category:
- **Swords** (`ig:dm_percent_swords`)
- **Spears** (`ig:dm_percent_spears`)
- **Tridents** (`ig:dm_percent_tridents`)
- **Maces** (`ig:dm_percent_maces`)
- **Bows & Crossbows** (`ig:dm_percent_bows`, `ig:dm_percent_crossbows`)
- **Tools** (`ig:dm_percent_tools`)
- **Armor & Elytra** (`ig:dm_percent_armor`, `ig:dm_percent_elytra`)
- **Shields** (`ig:dm_percent_shields`)

Category settings override global defaults if `> 0`. Setting `0` falls back to Global.

### Feature: Tooltip Indicator & Display Formats
Hover over any item to see its durability status:
- **`⟨2x Swords Durability⟩`** or **`⟨50% Swords Durability⟩`** — customizable via the `tooltipFormat` client setting (`ADAPTIVE`, `PERCENTAGE`, `MULTIPLIER`).
- **`✦ UNBREAKABLE`** — gold bold text when God Mode is active.

### Feature: Mod Compatibility
Works automatically with modded items via tag, class, and component inspection. Dynamic modded items also receive their own dedicated gamerules and config entries.

