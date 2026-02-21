# Changelog

## [1.0.3+build.6] - 2026-02-21

### Fixed

- **Localization**: Added 13 missing translation entries for granular weapon categories (Weapons, Spears, Tridents, Maces, Bows, Crossbows) and the Granular Settings toggle.

## [1.0.3+build.5] - 2026-02-21

### Fixed

- **Compatibility**: Reverted Mixin compatibility level from `JAVA_25` to `JAVA_22` to resolve warning.
- **Cleanup**: Removed stale `refmap` entry from `durability-multiplier.mixins.json`.

## [1.0.3+build.4] - 2026-02-21

### Fixed

- **Build**: Updated Gradle wrapper from 7.3.1 to 9.3.0 (fixes "Unsupported class file major version 69" with Java 25).
- **Target**: Updated Minecraft target from `26.1-snapshot-4` to `26.1-snapshot-8`.

## [1.0.3+build.3] - 2026-02-21

### Changed

- **Documentation**: Replaced "Architect" with "Creator" in Platform Page Author roles.

## [1.0.3+build.1] - 2026-02-19

### Changed

- **DasikLibrary Integration**: Switched to standalone dependency (JiJ removed).
- **Versioning**: Adopted strict Build Number policy.

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

- Initial release.
- Global and Tool specific durability scaling.
- In-game GameRule support.
