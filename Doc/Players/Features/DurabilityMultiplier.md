# Player Features

## Durability Multiplier

Make your tools, weapons, and armor last longer — or last forever.

### Feature: Durability Multiplier

Multiply the effective durability of any damageable item. A 2x multiplier means your Diamond Pickaxe lasts **3,124 uses** instead of 1,562.

**How it works**: Items take reduced damage. A 4x multiplier means every hit only deals 25% of normal wear to the item.

### Feature: God Mode (Infinity)

Toggle complete invincibility for item categories. With God Mode active, your Elytra will **never break** — fly as long as you want.

### Feature: Category Overrides

Don't want your tools and armor on the same settings? Set per-category multipliers:

| Category | Items Affected |
| :--- | :--- |
| **Swords** | All swords (vanilla + modded via `#minecraft:swords`) |
| **Tools** | Pickaxes, Axes, Shovels, Hoes, Spears |
| **Armor** | Helmets, Chestplates, Leggings, Boots |
| **Elytra** | Elytra wings |

Category settings **override** the global setting. Everything else with durability uses the global multiplier.

### Feature: Tooltip Indicator

Hover over any item to see its durability status:

- **`⟨4x Durability⟩`** — gray text showing active multiplier
- **`✦ UNBREAKABLE`** — gold bold text when God Mode is active

Can be disabled via `dm_show_tooltip`.

### Feature: Mod Compatibility

Works automatically with modded items. If a modded weapon uses `#minecraft:swords`, it inherits your sword multiplier. No config needed.
