# Durability Multipliers & Percentages (26.2)

Durability Multiplier replaces vanilla fixed wear mechanics with a dynamic **Percentage Scaling Engine** that supports both durability boosts (e.g. 200% = 2x, 500% = 5x) and wear penalties (e.g. 50% = 0.5x, 25% = 0.25x).

---

## ⚙️ Core Percentage GameRules

| # | GameRule Identifier | Default | Target Category / Description |
| :-: | :--- | :---: | :--- |
| 1 | `ig:dm_percent_global` | `200` | Global percentage applied to all damageable items. |
| 2 | `ig:dm_percent_weapons` | `0` | Parent override for all weapons (Swords, Spears, Tridents, Maces, Bows, Crossbows). |
| 3 | `ig:dm_percent_swords` | `0` | Specific percentage for Swords (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `0` | Specific percentage for Spears (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `0` | Specific percentage for Tridents (`Items.TRIDENT`, `TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `0` | Specific percentage for Maces (`Items.MACE`, `MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `0` | Specific percentage for Bows (`Items.BOW`, `BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `0` | Specific percentage for Crossbows (`Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `0` | Specific percentage for Shields (`Items.SHIELD`, `ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `0` | Parent category percentage for all Tools. |
| 11 | `ig:dm_percent_pickaxes` | `0` | Specific percentage for Pickaxes (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `0` | Specific percentage for Axes (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `0` | Specific percentage for Shovels (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `0` | Specific percentage for Hoes (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `0` | Specific percentage for Shears (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `0` | Specific percentage for Fishing Rods (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `0` | Specific percentage for Brushes (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `0` | Specific percentage for Flint and Steel (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `0` | Parent category percentage for all Armor pieces. |
| 20 | `ig:dm_percent_helmets` | `0` | Specific percentage for Helmets (`#minecraft:head_armor`, `#c:helmets`). |
| 21 | `ig:dm_percent_chestplates` | `0` | Specific percentage for Chestplates (`#minecraft:chest_armor`, `#c:chestplates`). |
| 22 | `ig:dm_percent_leggings` | `0` | Specific percentage for Leggings (`#minecraft:leg_armor`, `#c:leggings`). |
| 23 | `ig:dm_percent_boots` | `0` | Specific percentage for Boots (`#minecraft:foot_armor`, `#c:boots`). |
| 24 | `ig:dm_percent_elytra` | `0` | Specific percentage for Elytra (`Items.ELYTRA`, `DataComponents.GLIDER`). |

> [!NOTE]
> Override rules set to `0` automatically fall back to their parent category or the Global default. Setting `-1` activates **Single-Use (Glass Mode)**.

---

## 🔒 100% World-Save Safety
Durability Multiplier does **not** alter item NBT or `DataComponents.MAX_DAMAGE` in your world save. All durability scaling is performed dynamically during damage calculation, guaranteeing zero world corruption or leftover modified data if the mod is removed.
