# Concept: Durability Multiplier (Ironbound)

> Original source: [`concept_durability_multiplier.yaml`](file:///e:/Minecraft%20Project/Instant%20Gratification%20Collection/Plans%20and%20Ideas/concept_durability_multiplier.yaml)

## Philosophy Fit

**Collection**: Instant Gratification
**Reasoning**: Attacks "Maintenance Friction" in survival gameplay. Reduces crafting/repair loops so players focus on building and combat. *"Respect the Player's Time, Not the Game's Rules."*

## Core Mechanics

### 1. Hierarchy-Based Logic

Resolution order for each item:

| Priority | Infinity Check | Multiplier Check |
| :--- | :--- | :--- |
| 1st | Tag-Specific Infinity (if `true`) | Tag-Specific Multiplier (if `> 0`) |
| 2nd | Global Infinity (fallback) | Global Multiplier (fallback) |

Infinity takes absolute priority over multipliers.

### 2. Integer Overflow Safety

```java
int newMax = (int) Math.min((long) original * multiplier, Integer.MAX_VALUE);
```

Prevents wrapping to negative values when `original * multiplier > 2,147,483,647`.

### 3. Tag Categories

| Category | Tags |
| :--- | :--- |
| Swords | `#minecraft:swords` |
| Tools | `#minecraft:axes`, `#minecraft:pickaxes`, `#minecraft:shovels`, `#minecraft:hoes`, `#minecraft:spears` |
| Armor | `#minecraft:head_armor`, `#minecraft:chest_armor`, `#minecraft:leg_armor`, `#minecraft:foot_armor` |
| Elytra | `minecraft:elytra` (item check) |

### 4. Native Mod Compatibility

Items from other mods (Better Nether, Mythic Metals, etc.) work **automatically** if they use standard vanilla tags. No config required.

### 5. Exclusion List (Blacklist) — Deferred v1.1.0

Comma-separated item ID list. Deferred because vanilla GameRule API has no string type. Will be implemented as JSON config file.

## Configuration

11 GameRules under custom "Durability Multiplier" category:

- 5 multiplier rules (`dm_multiplier_global`, `_swords`, `_tools`, `_armor`, `_elytra`)
- 5 infinity toggles (`dm_infinity_global`, `_swords`, `_tools`, `_armor`, `_elytra`)
- 1 tooltip toggle (`dm_show_tooltip`)

## To Do

- [ ] **Mace Category**: Create specific category for Maces.
  - [ ] **Verify Tags**: Check for `#minecraft:maces` or `#c:maces`. If not present, use explicit `minecraft:mace` content.
  - [ ] **Enchantments**: Note that Maces have unique enchantments (`density`, `breach`, `wind_burst`) which might be relevant for future logic.
