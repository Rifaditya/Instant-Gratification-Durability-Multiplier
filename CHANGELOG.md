# Changelog - Durability Multiplier (Minecraft 1.21.11)

## [1.0.0+1.21.11] - 2026-10-02

### Added
- **Native Minecraft 1.21.11 Anchor Port**: Established dedicated anchor subproject for Minecraft 1.21.11 with multi-era parity and zero cross-version reflection.
- **Granular Percentage Scaling & Multipliers**: Configure durability scaling via GameRules (`ig:dm_percent_*`) and sparse JSON config, supporting fractional durability reductions (< 100%) and massive durability boosts (> 100%).
- **Unbreakable God Mode**: Complete damage negation and unbreakable durability toggle per category and per item (`ig:dm_infinity_*`).
- **Single-Use (Glass Mode) Engine**: High-risk 1-hit break mode inflicting full item durability on damage events, with `-1` sentinel percentage support and red warning tooltips.
- **Dynamic Modded Item Discovery**: Automatic registry scanning detects damageable items added by other mods, automatically generating GameRules and config options without manual setup.
- **Hierarchical Priority Cascade**: Smart resolution cascade ensuring specific overrides cleanly take precedence (`Per-Item > Sub-category > Category > Global`).
- **Live Tooltip Networking**: Seamless Fabric networking synchronization broadcasting active durability scaling to client tooltips in real time.
- **YACL v3 Configuration Screen**: In-game configuration interface powered by YetAnotherConfigLib (YACL v3) and ModMenu integration, featuring categorized tabs and a Ko-fi creator support button.
- **DasikLibrary 1.1.0 Engine**: Built on DasikLibrary 1.1.0+1.21.11 for robust dynamic GameRule management and cross-mod synchronization.
