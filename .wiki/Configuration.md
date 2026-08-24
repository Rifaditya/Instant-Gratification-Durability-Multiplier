# Configuration & GUI Integration (26.2)

| System Parameter | Value |
| :--- | :--- |
| **Config File Path** | `config/durability-multiplier.json` |
| **Config Version** | `2` (Auto-migrated from v1) |
| **GUI Providers** | Cloth Config (`me.shedaniel.cloth:cloth-config-fabric`) & ModMenu |
| **Config Class** | `net.instantgratification.durabilitymultiplier.config.DurabilityConfig` |
| **GUI Helper** | `ClothConfigScreenHelper` & `ModMenuIntegration` |
| **Precedence Law** | Config file defines **NEW WORLD DEFAULTS ONLY**; active worlds use GameRules |

---

## ⚙️ Configuration File Structure (`config/durability-multiplier.json`)

The configuration file defines baseline settings and defaults for all newly created singleplayer worlds and multiplayer servers. It supports durability percentages, God Mode (Infinity), Single-Use (Glass Mode), custom tooltip formatting, and dynamic modded item overrides.

```json
{
  "configVersion": 2,
  
  "percentGlobal": 200,
  "percentWeapons": 0,
  "percentSwords": 0,
  "percentSpears": 0,
  "percentTridents": 0,
  "percentMaces": 0,
  "percentBows": 0,
  "percentCrossbows": 0,
  "percentTools": 0,
  "percentPickaxes": 0,
  "percentAxes": 0,
  "percentShovels": 0,
  "percentHoes": 0,
  "percentShears": 0,
  "percentFishingRods": 0,
  "percentBrushes": 0,
  "percentFlintAndSteel": 0,
  "percentArmor": 0,
  "percentHelmets": 0,
  "percentChestplates": 0,
  "percentLeggings": 0,
  "percentBoots": 0,
  "percentElytra": 0,
  "percentShields": 0,
  
  "infinityGlobal": false,
  "infinityWeapons": false,
  "infinitySwords": false,
  "infinitySpears": false,
  "infinityTridents": false,
  "infinityMaces": false,
  "infinityBows": false,
  "infinityCrossbows": false,
  "infinityTools": false,
  "infinityPickaxes": false,
  "infinityAxes": false,
  "infinityShovels": false,
  "infinityHoes": false,
  "infinityShears": false,
  "infinityFishingRods": false,
  "infinityBrushes": false,
  "infinityFlintAndSteel": false,
  "infinityArmor": false,
  "infinityHelmets": false,
  "infinityChestplates": false,
  "infinityLeggings": false,
  "infinityBoots": false,
  "infinityElytra": false,
  "infinityShields": false,
  
  "singleUseGlobal": false,
  "singleUseWeapons": false,
  "singleUseSwords": false,
  "singleUseSpears": false,
  "singleUseTridents": false,
  "singleUseMaces": false,
  "singleUseBows": false,
  "singleUseCrossbows": false,
  "singleUseTools": false,
  "singleUsePickaxes": false,
  "singleUseAxes": false,
  "singleUseShovels": false,
  "singleUseHoes": false,
  "singleUseShears": false,
  "singleUseFishingRods": false,
  "singleUseBrushes": false,
  "singleUseFlintAndSteel": false,
  "singleUseArmor": false,
  "singleUseHelmets": false,
  "singleUseChestplates": false,
  "singleUseLeggings": false,
  "singleUseBoots": false,
  "singleUseElytra": false,
  "singleUseShields": false,
  
  "showTooltip": true,
  "tooltipFormat": "ADAPTIVE",
  
  "forcedItems": [],
  "forcedPercentages": {},
  "forcedInfinities": {},
  "forcedSingleUses": {}
}
```

---

## 🔄 The Auto-Populate System

Durability Multiplier features an autonomous **Universal 3-Tier Discovery Scanner** that automatically catalogs modded items without requiring manual data entry:

1. **Startup Sweep**: On client/server launch, the engine scans `BuiltInRegistries.ITEM`.
2. **Damageable Filter**: Items from external mod namespaces (excluding `minecraft` and common convention tags `c`) are checked for `DataComponents.MAX_DAMAGE > 0`.
3. **Auto-Populate**: Discovered damageable items are automatically appended to:
   * `"forcedItems"`: Added to the active item registry list.
   * `"forcedPercentages"`: Added with default value `0` (indicating the item dynamically inherits its category or global multiplier).
4. **Config Persistence**: The updated lists are saved back to `config/durability-multiplier.json`, making all modded items instantly visible and editable in the Cloth Config / ModMenu GUI and in-game GameRules.

---

## 🛠️ Manual Item Configuration Guide

Pack authors, server administrators, and players can manually declare custom rules for specific items directly in `config/durability-multiplier.json`:

### 1. `forcedItems` (Item Registration)
Declares the list of item resource identifiers recognized by the mod.
```json
"forcedItems": [
  "techmod:plasma_cutter",
  "magicmod:staff_of_fire",
  "customweapons:obsidian_blade"
]
```

### 2. `forcedPercentages` (Per-Item Durability Percentages)
Assigns explicit durability percentage multipliers to specific items:
* `0`: Inherit from parent category or global multiplier.
* `100`: Vanilla 100% baseline (1x durability).
* `200`: 200% durability (2x lifespan).
* `50`: 50% durability (half lifespan / 2x wear).
* `-1`: Single-Use (Glass Mode - breaks on first hit).
```json
"forcedPercentages": {
  "techmod:plasma_cutter": 300,
  "customweapons:obsidian_blade": -1,
  "magicmod:training_wand": 50
}
```

### 3. `forcedInfinities` (Per-Item God Mode)
Grants permanent unbreakable status to specific items:
```json
"forcedInfinities": {
  "magicmod:creative_staff": true,
  "adminmod:ban_hammer": true
}
```

### 4. `forcedSingleUses` (Per-Item Glass Mode)
Forces specific items to shatter after a single durability loss event:
```json
"forcedSingleUses": {
  "techmod:disposable_cutter": true,
  "survivalplus:glass_dagger": true
}
```

---

## ⚡ Power-User `-1` Glass Mode Sentinel

Durability Multiplier includes a **`-1` Sentinel Value** for durability percentages:
* Setting any percentage rule or config field to `-1` (or any negative integer) automatically triggers **Single-Use (Glass Mode)** for that item or category.
* When active, the item takes `maxDamage - damageValue` on its first hit, reducing its durability to 0 and breaking it in exactly 1 use.
* This allows server admins and pack authors to enforce 1-hit break mechanics directly through percentage sliders or `/gamerule` commands without needing to toggle separate boolean rules.

---

## 🎨 Tooltip Display Formatting

The `tooltipFormat` option configures how durability bonuses are displayed on item tooltips:

| Format Setting | Example Output (200% / 2x) | Example Output (150% / 1.5x) | Description |
| :--- | :--- | :--- | :--- |
| `"ADAPTIVE"` *(Default)* | `⟨2x Swords Durability⟩` | `⟨150% Swords Durability⟩` | Displays clean integer multipliers for even hundreds; percentages otherwise. |
| `"PERCENTAGE"` | `⟨200% Swords Durability⟩` | `⟨150% Swords Durability⟩` | Always displays the exact percentage value. |
| `"MULTIPLIER"` | `⟨2x Swords Durability⟩` | `⟨1.5x Swords Durability⟩` | Always displays formatted multiplier factor string. |

Set `"showTooltip": false` to hide durability indicators entirely.

---

## ⚠️ Important Config Precedence Warning

> ⚠️ **Notice**: Changes made in `durability-multiplier.json` or the ModMenu GUI screen **only define default baseline values for newly created worlds**.
> 
> For existing, active worlds, each world maintains its own independent GameRule state saved inside world data (`level.dat`). To change settings in an active world, use the in-game `/gamerule` command or the native GameRules edit GUI screen.

