# Getting Started

## Requirements

| Dependency | Version |
| :--- | :--- |
| Minecraft | 26.1 Snapshot 4+ |
| Fabric Loader | ≥ 0.16.10 |
| Fabric API | 0.142.1+26.1 |
| Java | 25 |

## Building from Source

```bash
git clone <repo-url>
cd durability-multiplier
./gradlew build
```

The JAR will be in `build/libs/durability-multiplier-<version>.jar`.

## Project Structure

```
src/main/java/net/instantgratification/durabilitymultiplier/
├── DurabilityMultiplierFabric.java   # Fabric entrypoint (ModInitializer)
├── DurabilityMultiplier.java         # Core init (registers rules, logging)
├── DurabilityHelper.java             # Stateless logic (hierarchy, overflow, rounding)
├── registry/
│   └── DurabilityRules.java          # 11 GameRules + custom category
└── mixin/
    ├── ItemStackDurabilityMixin.java  # hurtAndBreak damage reduction
    └── ItemStackTooltipMixin.java     # Tooltip status injection
```

## Configuration

All settings are GameRules — no config files. Access via:

- **Edit Game Rules** screen when creating/editing a world
- `/gamerule <rule> <value>` command in-game

## Dependencies

This is a **standalone mod** — no DasikLibrary dependency. Only requires Fabric API.
