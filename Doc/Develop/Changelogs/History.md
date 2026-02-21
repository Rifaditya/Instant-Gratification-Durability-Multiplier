# Changelog History

## [1.0.3+build.4] - 2026-02-21

### Fixed

- **Build**: Updated Gradle wrapper from 7.3.1 to 9.3.0 (fixes "Unsupported class file major version 69" with Java 25).
- **Target**: Updated Minecraft target from `26.1-snapshot-4` to `26.1-snapshot-8`.

## [1.0.3] - 2026-02-15

### Fixed

- Tooltip changes now apply immediately without requiring a world/game reload (synced on gamerule change).

## [1.0.2] - 2026-02-16

### Changed

- Updated tooltip format to be more explicit: "4x Tools" -> "4x Tools Durability".

## [1.0.1] - 2026-02-16

### Fixed

- Tooltips not displaying: client-side rendering could not access server-side GameRules
- Added Fabric Networking sync: GameRule values are now sent to clients on join
- Tooltip mixin is now side-aware (client reads synced cache, integrated server reads directly)

## [1.0.0] - 2026-02-15

### Added

- Initial release
- 11 GameRules under custom "Durability Multiplier" category
  - 5 multiplier rules (Global, Swords, Tools, Armor, Elytra)
  - 5 infinity/God Mode toggles (Global, Swords, Tools, Armor, Elytra)
  - 1 tooltip toggle
- Hierarchy-based resolution: tag-specific overrides global
- Integer overflow safety (long math + clamp)
- Probabilistic damage reduction for exact long-term multiplier effect
- Tooltip visualization
- Full en_us.json localization with verbose descriptions
- Native mod compatibility via Vanilla Tag System
