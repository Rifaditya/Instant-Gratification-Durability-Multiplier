# God Mode & Infinity (26.2)

| System Parameter | Value |
| :--- | :--- |
| **Global Infinity Rule** | `ig:dm_infinity_global` |
| **Default State** | `false` (Disabled) |
| **Damage Interception** | Incoming damage cancelled at `HEAD` ($0$ damage applied) |
| **Tooltip Styling** | `✦ UNBREAKABLE` (Gold, Bold) |
| **Priority** | Absolute (Evaluated before any multiplier) |

---

## ⚡ Overview & Mechanics

**God Mode (Infinity)** grants complete invincibility to items within selected categories. When God Mode is active for an item, any durability loss event is completely intercepted and cancelled at `ItemStackDurabilityMixin`, preventing the item from ever taking damage or breaking.

### Distinction from Vanilla Unbreakable Component
* Vanilla `Unbreakable` component must be applied to individual item stacks via commands (`/give @p diamond_sword[unbreakable={}]`).
* Durability Multiplier's God Mode is **world-wide and category-wide**: every tool, weapon, or armor piece in the world automatically behaves as unbreakable without editing item NBT or components.

---

## 🛡️ The 12 God Mode Rules

| # | GameRule Key | Category Name | Target Items | Default |
| :-: | :--- | :--- | :--- | :-: |
| 1 | `ig:dm_infinity_global` | **Global God Mode** | All damageable items in the game | `false` |
| 2 | `ig:dm_infinity_weapons` | **Weapons God Mode** | All weapons (swords, bows, maces, tridents, etc.) | `false` |
| 3 | `ig:dm_infinity_swords` | **Swords God Mode** | `#minecraft:swords` | `false` |
| 4 | `ig:dm_infinity_spears` | **Spears God Mode** | `#minecraft:spears` | `false` |
| 5 | `ig:dm_infinity_tridents` | **Tridents God Mode** | `Items.TRIDENT`, `TridentItem` | `false` |
| 6 | `ig:dm_infinity_maces` | **Maces God Mode** | `Items.MACE`, `MaceItem` | `false` |
| 7 | `ig:dm_infinity_bows` | **Bows God Mode** | `Items.BOW`, `BowItem` | `false` |
| 8 | `ig:dm_infinity_crossbows` | **Crossbows God Mode** | `Items.CROSSBOW`, `CrossbowItem` | `false` |
| 9 | `ig:dm_infinity_shields` | **Shields God Mode** | `Items.SHIELD`, `ShieldItem` | `false` |
| 10 | `ig:dm_infinity_tools` | **Tools God Mode** | Pickaxes, Axes, Shovels, Hoes, Shears, Brushes, etc. | `false` |
| 11 | `ig:dm_infinity_armor` | **Armor God Mode** | Helmets, Chestplates, Leggings, Boots | `false` |
| 12 | `ig:dm_infinity_elytra` | **Elytra God Mode** | `Items.ELYTRA`, `DataComponents.GLIDER` | `false` |

---

## 👑 God Mode Resolution Order

`DurabilityHelper.isInfinite(ServerLevel, ItemStack)` checks:

```
[1. Specific Category Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[2. Weapons Infinity == true?] (Weapons only) ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[3. Global Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[Proceed to Multiplier Calculation]
```

God Mode takes **absolute precedence** over any multiplier setting. If `ig:dm_infinity_tools = true`, tools will never take damage, regardless of whether `ig:dm_multiplier_tools` is set to `2`, `10`, or `0`.
