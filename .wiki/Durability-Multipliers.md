# Durability Multipliers (26.2)

| System Parameter | Value |
| :--- | :--- |
| **Default Global Multiplier** | `2` ($2\times$ item life) |
| **Global Rule Key** | `ig:dm_multiplier_global` |
| **Category Multiplier Default** | `0` (Inherit from Global) |
| **Minimum Value** | `1` (Global) / `0` (Categories) |
| **Maximum Value** | `2,147,483,647` (`Integer.MAX_VALUE`) |
| **Calculation Point** | `ItemStack.hurtAndBreak` (Server-side) |

---

## ⚡ Overview & Mechanics

Durability Multiplier allows server administrators and singleplayer players to scale the lifespan of equipment without modifying vanilla item attributes, NBT components, or max-damage limits.

### How Durability Extension Works
Rather than increasing the item's maximum durability (which creates client-side synchronization issues and tooltip desyncs), the mod intercepts incoming durability damage at the server level and **reduces the incoming damage amount proportionally**:

$$\text{Effective Lifespan} = \text{Vanilla Durability} \times \text{Multiplier}$$

* **2x Multiplier**: Damage is reduced by $50\%$. A Diamond Pickaxe (1,562 vanilla uses) lasts **3,124 uses**.
* **5x Multiplier**: Damage is reduced by $80\%$. A Netherite Sword (2,031 vanilla uses) lasts **10,155 uses**.
* **10x Multiplier**: Damage is reduced by $90\%$. An Elytra (432 vanilla flight seconds) lasts **4,320 seconds** (72 minutes).

---

## 📊 The 12 Multiplier Categories

The mod provides 12 distinct integer GameRules for granular control:

| # | GameRule Key | Category Name | Scope / Target Items | Default |
| :-: | :--- | :--- | :--- | :-: |
| 1 | `ig:dm_multiplier_global` | **Global Multiplier** | All damageable items (fallback) | `2` |
| 2 | `ig:dm_multiplier_weapons` | **Weapons Multiplier** | All weapon categories (fallback for weapons) | `0` |
| 3 | `ig:dm_multiplier_swords` | **Swords Multiplier** | `#minecraft:swords` | `0` |
| 4 | `ig:dm_multiplier_spears` | **Spears Multiplier** | `#minecraft:spears` | `0` |
| 5 | `ig:dm_multiplier_tridents` | **Tridents Multiplier** | `Items.TRIDENT`, `TridentItem` | `0` |
| 6 | `ig:dm_multiplier_maces` | **Maces Multiplier** | `Items.MACE`, `MaceItem` | `0` |
| 7 | `ig:dm_multiplier_bows` | **Bows Multiplier** | `Items.BOW`, `BowItem` | `0` |
| 8 | `ig:dm_multiplier_crossbows` | **Crossbows Multiplier** | `Items.CROSSBOW`, `CrossbowItem` | `0` |
| 9 | `ig:dm_multiplier_shields` | **Shields Multiplier** | `Items.SHIELD`, `ShieldItem` | `0` |
| 10 | `ig:dm_multiplier_tools` | **Tools Multiplier** | Pickaxes, Axes, Shovels, Hoes, Shears, etc. | `0` |
| 11 | `ig:dm_multiplier_armor` | **Armor Multiplier** | Head, Chest, Leg, and Foot armor | `0` |
| 12 | `ig:dm_multiplier_elytra` | **Elytra Multiplier** | `Items.ELYTRA`, `DataComponents.GLIDER` | `0` |

---

## 👑 Override Resolution Hierarchy

When an item takes damage, `DurabilityHelper.getEffectiveMultiplier(ServerLevel, ItemStack)` resolves the active multiplier following a strict hierarchy:

```
[1. Specific Category Multiplier] (e.g. Swords, Bows, Tools)
               │ (if == 0)
               ▼
[2. Weapons Multiplier] (Only for Swords, Spears, Tridents, Maces, Bows, Crossbows)
               │ (if == 0)
               ▼
[3. Global Multiplier] (ig:dm_multiplier_global, minimum 1)
```

### Hierarchy Examples
* **Example A**: `dm_multiplier_global = 2`, `dm_multiplier_swords = 5`.
  * Swords receive **5x** durability.
  * Pickaxes and Armor receive **2x** durability.
* **Example B**: `dm_multiplier_global = 2`, `dm_multiplier_weapons = 4`, `dm_multiplier_swords = 0`, `dm_multiplier_bows = 8`.
  * Bows receive **8x** (specific override).
  * Swords receive **4x** (inherited from Weapons).
  * Pickaxes receive **2x** (inherited from Global).
