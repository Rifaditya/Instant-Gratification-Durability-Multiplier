# Configuration & GUI Integration (26.2)

| System Parameter | Value |
| :--- | :--- |
| **Config File Path** | `config/durability-multiplier.json` |
| **GUI Providers** | Cloth Config (`me.shedaniel.cloth:cloth-config-fabric`) & ModMenu |
| **Config Class** | `net.instantgratification.durabilitymultiplier.config.DurabilityConfig` |
| **GUI Helper** | `ClothConfigScreenHelper` & `ModMenuIntegration` |
| **Precedence Law** | Config file defines **NEW WORLD DEFAULTS ONLY**; active worlds use GameRules |

---

## ⚙️ Configuration File Structure (`config/durability-multiplier.json`)

```json
{
  "configVersion": 1,
  "multiplierGlobal": 2,
  "multiplierWeapons": 0,
  "multiplierSwords": 0,
  "multiplierSpears": 0,
  "multiplierTridents": 0,
  "multiplierMaces": 0,
  "multiplierBows": 0,
  "multiplierCrossbows": 0,
  "multiplierTools": 0,
  "multiplierArmor": 0,
  "multiplierElytra": 0,
  "multiplierShields": 0,
  
  "infinityGlobal": false,
  "infinityWeapons": false,
  "infinitySwords": false,
  "infinitySpears": false,
  "infinityTridents": false,
  "infinityMaces": false,
  "infinityBows": false,
  "infinityCrossbows": false,
  "infinityTools": false,
  "infinityArmor": false,
  "infinityElytra": false,
  "infinityShields": false,
  
  "showTooltip": true,
  "dynamicMultipliers": {},
  "dynamicInfinities": {}
}
```

---

## ⚠️ Important Config Precedence Warning

> ⚠️ **Notice**: Changes made in `durability-multiplier.json` or the ModMenu GUI screen **only define default baseline values for newly created worlds**.
> 
> For existing, active worlds, each world maintains its own independent GameRule state saved in world data. To change settings in an existing world, use the in-game `/gamerule` command or the native GameRules edit GUI screen.
