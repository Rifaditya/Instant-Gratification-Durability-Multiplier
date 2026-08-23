# Changelog

## [1.1.14+26.1.2] - 2026-08-23

### Added
- **Single-Use (Glass Mode) Durability Engine**: Implemented 1-hit break mode across all categories and individual modded items (`ig:dm_single_use_*` GameRules, config fields, network sync, and Cloth Config category).
- **Remaining Durability Shatter Mechanic**: When Single-Use mode is active, any durability damage inflicts full remaining item durability (`stack.getMaxDamage() - stack.getDamageValue()`), triggering standard vanilla tool-shatter sounds, break particles, and statistics in a single strike.
- **Strict God Mode Precedence**: Preserved absolute invulnerability hierarchy—if both God Mode (Infinity) and Single-Use mode are simultaneously active on an item, God Mode strictly overrides Single-Use mode, keeping the item unbreakable.
- **Red "SINGLE-USE" Tooltip Indicator**: Added clean `§cSINGLE-USE§r` tooltip label on client side for any item affected by Single-Use mode when tooltips are enabled.

## [1.1.13+26.1.2] - 2026-08-23

### Added
- **Universal Modded Item Dynamic Discovery**: Upgraded registry freeze item scanning to detect all damageable modded items (`DataComponents.MAX_DAMAGE > 0` with non-minecraft namespaces) directly without reflection, registering individual dynamic GameRules (`ig:percent_<mod>_<item>` and `ig:infinity_<mod>_<item>`).
- **Individual Per-Item Override Cascade**: Unlocked dedicated per-item override hierarchy taking highest precedence (`Per-Item Override > Sub-tag > Parent Category > Weapons Global > Global > 100%`) when set $> 0$, allowing players to set specific multipliers on any modded item.
- **Delta Map Network Synchronization**: Implemented efficient delta streaming in `DurabilityPayload` that synchronizes only active, non-zero per-item overrides and infinities to multiplayer clients with zero network overhead for default items.
- **Cloth Config Mod Namespace Grouping**: Reorganized the "Modded Items" config tab into clean collapsible subcategories grouped by mod namespace with formatted titles.

## [1.1.12+26.1.2] - 2026-08-23

### Added
- **Granular Tag GameRules & Subcategory Expansion**: Introduced distinct, dedicated GameRules and config options for individual tool subcategories (`pickaxes`, `axes`, `shovels`, `hoes`, `shears`, `fishing_rods`, `brushes`, `flint_and_steel`) and armor subcategories (`helmets`, `chestplates`, `leggings`, `boots`).
- **Multi-Tier Cascade Hierarchy**: Implemented clean fallback logic (`Sub-tag > 0` overrides `Parent Category > 0` overrides `Global > 0` overrides `100%`) for all percentage modifiers.
- **Additive Infinity Union**: Enabled additive boolean logic across subcategories, parent categories, and global God Mode rules.
- **Extended Networking & GUI Support**: Expanded server-to-client network synchronization payload (`DurabilityPayload`) and Cloth Config GUI screens with categorized sections for tool and armor breakdowns.

## [1.1.11+26.1.2] - 2026-08-23

### Fixed
- **Universal Item Classification Engine**: Upgraded item categorization in `DurabilityHelper` with Fabric Conventional Tags (`#c:swords`, `#c:tools`, `#c:pickaxes`, `#c:axes`, `#c:shovels`, `#c:hoes`, `#c:mining_tool`, `#c:armors`, `#c:shields`, `#c:bows`, `#c:crossbows`, `#c:spears`).
- **Sword-to-Tool Trapping Bug**: Reordered classification checks so all sword tags, classes, and path heuristics execute before generic `DataComponents.TOOL` inspection. Prevents modded swords with cobweb-mining rules from being misclassified into the `TOOL` category.
- **Identifier Path Heuristics**: Added prioritized name matching (`_sword`, `_katana`, `_pickaxe`, `_axe`, `_helmet`, etc.) as a robust fallback for custom mod items that omit tags.
- **Classification Cache Invalidation**: Added `clearCategoryCache()` method to clear cached item categories upon datapack tag reloads.

## [1.1.10+26.1.2] - 2026-08-21

### Added
- **Fractional Durability & Percentage Scaling**: Upgraded durability math and GameRules from whole integer multipliers to granular percentages (`ig:dm_percent_*`), fully supporting durability reductions below 1x (e.g. 50 = 50% half durability / 2x wear, 25 = 25% quarter durability) as well as boosts (e.g. 200 = 200% double durability, 150 = 150% 1.5x durability).
- **Tooltip Display Format Setting**: Added `tooltipFormat` option in config and Cloth Config GUI with three selectable modes: `ADAPTIVE` (shows `2x` for round multiples, `50%` / `150%` for fractions/reductions), `PERCENTAGE` (always `%`), and `MULTIPLIER` (always `x`).
- **Automated Durability Test Suite**: Added comprehensive JUnit 5 test suite (`DurabilityHelperTest`) asserting probabilistic damage distribution, boundary cases, and tooltip formatting across all modes.

### Changed
- **Config Version 2 Migration**: Bumped `DurabilityConfig` to version 2 with automated backward-compatible migration (`multiplier * 100`) from existing v1 configuration files.
- **Rule Renaming**: Migrated gamerules to `ig:dm_percent_global`, `ig:dm_percent_weapons`, `ig:dm_percent_swords`, etc. Default global is 200% (2x), with specific category overrides defaulting to 0 (inherit).

## [1.1.9+26.1.2] - 2026-07-11

### Removed
- Removed live GameRule sync on config save. Changing config values now only defines default settings for new worlds, allowing each world to maintain independent GameRule settings.
- Appended `§6Notice:§r` warning notice to all gamerule descriptions to warn players about the config-only behavior when altering settings in ModMenu config screen.


## [1.1.8+A-26.1.2] - 2026-06-12

### Changed

- **Performance Optimization**: Implemented a thread-safe `ConcurrentHashMap` item classification cache in `DurabilityHelper` to cache resolved item categories, preventing redundant component scans and tag evaluations on every frame (tooltips) and every damage event.
- **Compliance Sanitisation**: Aligned mod comments with the Anonymity Mandate by removing references to the forbidden name from source file header comments in `ClothConfigScreenHelper` and `DurabilityConfig`.

## [1.1.7+A-26.1.2] - 2026-06-11

### Changed

- **Performance Optimization**: Refactored the durability damage reduction calculations in `DurabilityHelper` and `ItemStackDurabilityMixin` to perform item classification only once per damage event. This avoids redundant GameRule checks and tag/component inspections in the hot execution path.

## [1.1.6+R-26.1.2] - 2026-06-08

### Changed

- **Release**: Promoted Durability Multiplier to stable release stage.

## [1.1.5+A-26.1.2] - 2026-06-08

### Changed

- **Modded Items Config Explanation**: Updated the "No modded items found" explanation string on the configuration screen to clarify that standard modded weapons, tools, and armor are already automatically covered under the main categories (Multipliers and God Mode), and that only custom/uncategorized modded items are listed in the dynamic section.

## [1.1.4+A-26.1.2] - 2026-06-08

### Fixed

- **Mod Menu Configuration GUI (NPE Crash)**: Fixed a crash where clicking the configuration gear in Mod Menu threw `java.lang.NullPointerException: Components not bound yet`. This occurred because `ClothConfigScreenHelper` scanned `BuiltInRegistries.ITEM` and queried `item.getDefaultInstance().getMaxDamage()` before item components were bound to their holder references. Fixed by caching discovered dynamic items during the registry freeze phase in a static list, and using `item.getDescriptionId()` (translatable component) instead of calling `item.getDefaultInstance()`.
- **Registry Scan (Reflection Access Exception)**: Fixed startup warning log spam `Failed to check durability... java.lang.IllegalAccessException` during item scanning. This was caused by the nested helper record `DataComponentInitializers$InitializerEntry` being package-private, preventing direct reflection invocation of its public methods. Resolved by calling `setAccessible(true)` on `key()` and `initializer()` method objects.

## [1.1.3+A-26.1.2] - 2026-06-08 [CRASHING / BROKEN - DO NOT PUBLISH]

### Fixed (Incomplete)

- **Registry Freeze Startup Crash (Data Components Check)**: Rewrote dynamic scanning to use reflection on `DATA_COMPONENT_INITIALIZERS` to bypass early `ItemStack` instantiation.
- **Broken / Crashing in Game**:
  - **Reflection Exception**: Threw `IllegalAccessException` for every scanned item because reflected methods lacked `setAccessible(true)`, flooding logs with warning stack traces and failing to detect dynamic items.
  - **Config Screen Crash**: Clicking the config button in Mod Menu crashed the client with `NullPointerException: Components not bound yet` due to `item.getDefaultInstance()` call in `ClothConfigScreenHelper`.

## [1.1.2+A-26.1.2] - 2026-06-08 [CRASHING - DO NOT PUBLISH]

### Fixed (Broken)

- **Registry Freeze Startup Crash**: Attempted to fix startup NPE by moving registry scanning to MappedRegistry freeze mixin.
- **Crashing in Game**: Crashed on startup with `NullPointerException: Components not bound yet` for mods that register items early before components are bound.

## [1.1.1+A-26.1.2] - 2026-06-08

### Added

- **Dynamic Modded Item Support**: Added automatic scanning of `BuiltInRegistries.ITEM` on startup to dynamically register GameRules for modded items (which do not fit into standard tool/weapon categories) and listen to late modded item registrations.
- **Dynamic Config GUI**: Added a dynamically populated "Modded Items" category in the Cloth Config configuration screen, rendering translatable item names, allowing multiplier/infinity adjustments, and syncing settings to active server GameRules in real time.

### Changed

- **Tooltip Name Resolution**: Upgraded tooltips for uncategorized modded items to dynamically render the item's specific localized hover name (e.g., `4x Blood Katana Durability`) instead of a generic label.

## [1.1.0+A-26.1.2] - 2026-06-08

### Changed

- **Item Classification**: Upgraded the item classification system to support modded items using class-based (e.g. `instanceof ShieldItem`, `instanceof BowItem`) and component-based (e.g. `DataComponents.GLIDER`, `DataComponents.EQUIPPABLE`, `DataComponents.TOOL`) checks instead of hardcoded vanilla item references.

## [1.0.8+R-26.1.2] - 2026-06-08

### Changed

- **Release**: Promoted Durability Multiplier to stable release stage.

## [1.0.7+A-26.1.2] - 2026-06-08

### Added

- **Config**: Added an optional configuration GUI using Cloth Config and ModMenu with safe dynamic class loading.
- **Support**: Added a "Support the Project" section to the README with Modrinth and CurseForge links.

## [1.0.6+A-26.1.2] - 2026-06-04

### Changed

- **API Compliance**: Updated custom rule category and network payload registration to use the non-legacy `Identifier.fromNamespaceAndPath` API.
- **Code Quality**: Refactored dynamic GameRules registration to utilize the non-deprecated builder APIs (`integerRule` and `booleanRule` chains).
- **Localization**: Added explicit default values (`Default: [value]`) to all 25 game rule description strings in the localization file.
- **Repository Boundary**: Added `Doc/Marketing/` directory to `.gitignore` under the Hype & Marketing Exclusion Policy.

## [1.0.5+A-26.1.2] - 2026-06-04

### Removed

- **GameRules**: Removed the unused `ig:dm_granular_settings` GameRule registry and related localization keys.

### Changed

- **Build**: Aligned build toolchain and dependencies (Fabric Loom 1.15.2, Fabric API 0.145.4+26.1.2, Fabric Loader 0.19.1, DasikLibrary 1.7.4, and Java toolchain 25) with Minecraft 26.1.2 standards to resolve compile-time symbol resolution issues.

## [1.0.4+build.1] - 2026-03-08

### Fixed

- **Screen**: Fixed untranslated gamerule keys on the Durability Multiplier screen by correcting the translation namespace from `minecraft` to `ig`.

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
