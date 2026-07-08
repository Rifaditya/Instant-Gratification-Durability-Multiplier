# Developer Hub

Welcome to the **Durability Multiplier** development documentation.

## Sections

- **[Getting Started](Getting-Started/index.md)**: Requirements, Building, and Project Structure.
- **[Architecture](Architecture/Architecture.md)**: System flow, module responsibilities, design decisions.
- **[GameRules Reference](gamerules_reference.md)**: Complete reference for all 11 GameRules.
- **[Changelogs](Changelogs/History.md)**: Full release history.

## Architecture

| Component | Purpose |
| :--- | :--- |
| [DurabilityRules](../../src/main/java/net/instantgratification/durabilitymultiplier/registry/DurabilityRules.java) | Registers 11 GameRules under custom category |
| [DurabilityHelper](../../src/main/java/net/instantgratification/durabilitymultiplier/DurabilityHelper.java) | Stateless logic: hierarchy resolution, overflow safety, damage reduction |
| [ItemStackDurabilityMixin](../../src/main/java/net/instantgratification/durabilitymultiplier/mixin/ItemStackDurabilityMixin.java) | Intercepts `hurtAndBreak()` to reduce/cancel damage |
| [ItemStackTooltipMixin](../../src/main/java/net/instantgratification/durabilitymultiplier/mixin/ItemStackTooltipMixin.java) | Injects durability status into tooltips |

## Key Design Decisions

1. **Damage Reduction, Not Max Override**: `getMaxDamage()` lacks Level context (called client-side). Reducing incoming damage in `hurtAndBreak()` is server-side only and mathematically equivalent.
2. **Probabilistic Rounding**: For non-divisible damage (e.g., 1 damage / 3x multiplier), uses random rounding to achieve exact long-term durability extension.
3. **ThreadLocal Re-entry Guard**: The durability Mixin re-calls `hurtAndBreak()` with reduced damage. A `ThreadLocal<Boolean>` prevents infinite recursion.

## Tag Classification

Items are classified using vanilla `ItemTags`:

- **SWORD**: `#minecraft:swords`
- **TOOL**: `#minecraft:axes`, `#minecraft:pickaxes`, `#minecraft:shovels`, `#minecraft:hoes`, `#minecraft:spears`
- **ARMOR**: `#minecraft:head_armor`, `#minecraft:chest_armor`, `#minecraft:leg_armor`, `#minecraft:foot_armor`
- **ELYTRA**: `minecraft:elytra` (specific item, no tag)
- **OTHER**: Everything else with durability
