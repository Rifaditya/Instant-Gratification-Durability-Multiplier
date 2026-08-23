# Full Changelog History

## [1.1.18+26.1.2] - 2026-08-23

### Fixed
- **Dynamic GameRule World Save Persistence & Instant /gamerule Recognition**: Upgraded to `DasikLibrary` `1.8.28` (`GameRuleMapMixin` & `CommandRegistrationCallback.EVENT`). All dynamic item GameRules (`ig:percent_<mod>_<item>`, `ig:infinity_<mod>_<item>`, `ig:single_use_<mod>_<item>`) are now recognized and autocompleted by Brigadier on world load without requiring `/reload`, and modified values are natively saved into world data (`level.dat` / `game_rules.dat`) across game restarts.
- **Live Tooltip Network Synchronization**: Synchronized dynamic item GameRule updates directly with `DurabilityPayload` broadcasts, ensuring player tooltips immediately reflect changes in real time.

## [1.1.17+26.1.2] - 2026-08-23

### Fixed
- **Universal 3-Tier Dynamic Item Discovery Engine**: Upgraded to `DasikLibrary` `1.8.27` (`DynamicRegistryScanner`), implementing a 3-tier discovery pipeline (startup scan, real-time `RegistryEntryAddedCallback` listener, and `ServerLifecycleEvents.SERVER_STARTING` safety sweep) guaranteeing 100% modded damageable item discovery (such as Water's Gems) regardless of mod loading order.
- **Centralized GameRule Registry Unfreeze**: Integrated `DynamicGameRuleManager`'s native on-demand registry unlocking, removing redundant local mixins and accessors while enabling full in-game `/gamerule` execution and autocomplete for all modded items.

## [1.1.16+26.1.2] - 2026-08-23

### Fixed
- **Dynamic GameRule Registry Freeze Lockout**: Resolved a registry freeze timing conflict where modern Minecraft froze `BuiltInRegistries.GAME_RULE` before dynamic modded items finished registering during `BuiltInRegistries.ITEM` freeze. Introduced `MappedRegistryAccessor` to temporarily unfreeze `BuiltInRegistries.GAME_RULE` during dynamic rule registration, ensuring all dynamic item GameRules (`ig:percent_<mod>_<item>`, `ig:infinity_<mod>_<item>`, `ig:single_use_<mod>_<item>`) are properly registered and recognized by `/gamerule` commands with full in-game tab completion.
- **Dynamic Mod Item Discovery Diagnostics**: Added startup log output detailing the exact count of discovered dynamic modded items and registered GameRules.

## [1.1.15+26.1.2] - 2026-08-23

### Added
- **Power-User `-1` Single-Use Sentinel for Percentage Rules & Configs**: Enabled setting `-1` (or any negative integer) directly in any percentage GameRule (`ig:dm_percent_*` and dynamic modded item percentage rules) or Cloth Config field to immediately activate Single-Use (Glass Mode) for that category or item without toggling a separate boolean rule.
- **Hierarchical `-1` Cascade Overrides**: Standard cascade priority ensures specific sub-categories or per-item percentages $> 0$ cleanly override parent/global `-1` sentinels (e.g. setting Swords to `200%` retains double durability even if Global/Weapons is set to `-1`), while setting a specific item or category to `-1` forces Single-Use even if parent is $> 0$.
- **Unified Single-Use Resolution**: Server and client Single-Use checks evaluate additively—Single-Use triggers if either the boolean single-use rule is `true` OR the resolved effective percentage is $\le -1$. Unbreakable God Mode strictly retains ultimate priority over both.
- **Cloth Config Negative Integer Range**: Updated all percentage input fields in Cloth Config to allow minimum values down to `-1` with updated descriptive tooltips (`-1 = Single-Use (Glass Mode), 0 = Inherit, >0 = Percentage`).

## [1.1.14+26.1.2] - 2026-08-23

### Single-Use (Glass Mode) Durability Mechanics
* **What**: Implemented complete Single-Use (Glass Mode) durability system across global, category, subcategory, and individual modded item rules (`ig:dm_single_use_*`), dealing full remaining item durability on hit with vanilla break particles and sounds, strict God Mode precedence, and client tooltip indication.
* **Why**: To provide high-challenge "glass weapon / glass armor" gameplay modes requested by players and modpack creators.
* **How**: Added single-use boolean fields to `DurabilityConfig`, registered dynamic GameRules, updated `DurabilityPayload` network codec, implemented additive single-use resolution in `DurabilityHelper`, inflicted remaining damage `Math.max(1, stack.getMaxDamage() - stack.getDamageValue())` during item wear, added dedicated Cloth Config tab and item toggles, and added `§cSINGLE-USE§r` client tooltip rendering.

## [1.1.13+26.1.2] - 2026-08-23

### Universal Modded Item Dynamic Discovery & Individual Overrides
* **What**: Rebuilt dynamic registry freeze scanning to scan all non-minecraft damageable items via direct component access (`DataComponents.MAX_DAMAGE > 0`), unlocked per-item override precedence (`ig:percent_<mod>_<item>` and `ig:infinity_<mod>_<item>`), enabled delta map network synchronization, and grouped modded items by mod namespace in Cloth Config GUI.
* **Why**: To fix Issue #2 where external mod items without categories or with custom overrides could not be individually customized, and eliminate fragile reflection hacks on package-private Minecraft classes.
* **How**: Replaced reflection on `DATA_COMPONENT_INITIALIZERS` with zero-reflection component evaluation, updated `DurabilityHelper` to evaluate per-item overrides before sub-tag/parent/global rules, added delta map streaming to `DurabilityPayload`, and organized Cloth Config "Modded Items" tab into collapsible mod namespace sections.

## [1.1.12+26.1.2] - 2026-08-23

### Granular Tag GameRules & Subcategory Expansion
* **What**: Expanded GameRules and config options from broad categories to individual tool tags (`pickaxes`, `axes`, `shovels`, `hoes`, `shears`, `fishing_rods`, `brushes`, `flint_and_steel`) and armor tags (`helmets`, `chestplates`, `leggings`, `boots`).
* **Why**: To grant players and modpack creators precise control over individual tool and armor durability and God Mode states without having to lump all tools or all armor together.
* **How**: Expanded `ItemCategory` enum with granular variants, implemented multi-tier fallback cascade (`Sub-tag > Parent Category > Global > 100%`), additive infinity boolean evaluation, extended `DurabilityPayload` network synchronization, and structured grouped sections in Cloth Config GUI.

## [1.1.11+26.1.2] - 2026-08-23

### Universal Item Classification & Conventional Tags
* **What**: Upgraded item categorization in `DurabilityHelper` with Fabric Conventional Tags (`#c:*`), fixed the modern sword-to-tool trapping bug, added prioritized identifier path heuristics (`_sword`, `_pickaxe`, `_helmet`), and added classification cache invalidation (`clearCategoryCache()`).
* **Why**: To fix external community mod items (such as *Water's Gems* and *Simply Swords*) failing to be identified or getting misclassified into the `TOOL` category due to attached `DataComponents.TOOL` (cobweb-destruction rules).
* **How**: Added static `TagKey<Item>` references for `#c:*` conventional tags, placed sword tag and path evaluations ahead of generic tool component checks, and implemented substring path pattern matching as a zero-failure fallback.

## [1.1.10+26.1.2] - 2026-08-21

### Fractional Durability Scaling & Display Customization
* **What**: Upgraded durability scaling and GameRules from integer multipliers to percentage-based values (`ig:dm_percent_*`), allowing durability reductions below 1x (e.g., 50% = half durability / 2x wear, 25% = quarter durability) as well as boosts (e.g. 200% = 2x double durability). Added a client-side `tooltipFormat` option (`ADAPTIVE`, `PERCENTAGE`, `MULTIPLIER`) in Cloth Config.
* **Why**: To address community feature request (Issue #1) for finer-grained durability adjustments and custom wear penalties without violating Minecraft's integer GameRule parser constraints.
* **How**: Formulated unified probabilistic integer damage division math (`(originalAmount * 100) / percent` with remainder check). Migrated configuration from v1 to v2 with automated multiplier-to-percentage migration. Registered unit test suite verifying damage scaling and formatting.

### Remove Live Config Sync & Add Standardized Warning Notice
* **What**: Removed live GameRule sync block from the Cloth configuration save handler. Appended `§6Notice:§r` warning text into all GameRule descriptions.
* **Why**: To keep GameRule settings isolated on a per-world basis so each world has different settings, and clearly warn the user to use `/gamerule` for existing worlds.
* **How**: Updated `ClothConfigScreenHelper.java` save block to only save config files and cleaned up unused imports. Updated `en_us.json` to append the gold notice to all description values.

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
