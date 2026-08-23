// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.config;

import java.nio.file.Path;

public class DurabilityConfig {
    public enum TooltipFormat {
        ADAPTIVE,
        PERCENTAGE,
        MULTIPLIER
    }

    private static DurabilityConfig INSTANCE = new DurabilityConfig();
    private static Path CONFIG_PATH;

    public static final int VERSION = 2;
    public int configVersion = VERSION;

    // Durability Percentages (100 = 100% / 1x vanilla, 200 = 200% / 2x double, 50 = 50% / 0.5x half)
    public int percentGlobal = 200;
    public int percentWeapons = 0;
    public int percentSwords = 0;
    public int percentSpears = 0;
    public int percentTridents = 0;
    public int percentMaces = 0;
    public int percentBows = 0;
    public int percentCrossbows = 0;
    public int percentTools = 0;
    public int percentPickaxes = 0;
    public int percentAxes = 0;
    public int percentShovels = 0;
    public int percentHoes = 0;
    public int percentShears = 0;
    public int percentFishingRods = 0;
    public int percentBrushes = 0;
    public int percentFlintAndSteel = 0;
    public int percentArmor = 0;
    public int percentHelmets = 0;
    public int percentChestplates = 0;
    public int percentLeggings = 0;
    public int percentBoots = 0;
    public int percentElytra = 0;
    public int percentShields = 0;

    // Legacy fields for JSON migration compatibility
    public Integer multiplierGlobal;
    public Integer multiplierWeapons;
    public Integer multiplierSwords;
    public Integer multiplierSpears;
    public Integer multiplierTridents;
    public Integer multiplierMaces;
    public Integer multiplierBows;
    public Integer multiplierCrossbows;
    public Integer multiplierTools;
    public Integer multiplierArmor;
    public Integer multiplierElytra;
    public Integer multiplierShields;

    // Infinity (God Mode)
    public boolean infinityGlobal = false;
    public boolean infinityWeapons = false;
    public boolean infinitySwords = false;
    public boolean infinitySpears = false;
    public boolean infinityTridents = false;
    public boolean infinityMaces = false;
    public boolean infinityBows = false;
    public boolean infinityCrossbows = false;
    public boolean infinityTools = false;
    public boolean infinityPickaxes = false;
    public boolean infinityAxes = false;
    public boolean infinityShovels = false;
    public boolean infinityHoes = false;
    public boolean infinityShears = false;
    public boolean infinityFishingRods = false;
    public boolean infinityBrushes = false;
    public boolean infinityFlintAndSteel = false;
    public boolean infinityArmor = false;
    public boolean infinityHelmets = false;
    public boolean infinityChestplates = false;
    public boolean infinityLeggings = false;
    public boolean infinityBoots = false;
    public boolean infinityElytra = false;
    public boolean infinityShields = false;

    // Single-Use (Glass Mode)
    public boolean singleUseGlobal = false;
    public boolean singleUseWeapons = false;
    public boolean singleUseSwords = false;
    public boolean singleUseSpears = false;
    public boolean singleUseTridents = false;
    public boolean singleUseMaces = false;
    public boolean singleUseBows = false;
    public boolean singleUseCrossbows = false;
    public boolean singleUseTools = false;
    public boolean singleUsePickaxes = false;
    public boolean singleUseAxes = false;
    public boolean singleUseShovels = false;
    public boolean singleUseHoes = false;
    public boolean singleUseShears = false;
    public boolean singleUseFishingRods = false;
    public boolean singleUseBrushes = false;
    public boolean singleUseFlintAndSteel = false;
    public boolean singleUseArmor = false;
    public boolean singleUseHelmets = false;
    public boolean singleUseChestplates = false;
    public boolean singleUseLeggings = false;
    public boolean singleUseBoots = false;
    public boolean singleUseElytra = false;
    public boolean singleUseShields = false;

    // Tooltip
    public boolean showTooltip = true;
    public TooltipFormat tooltipFormat = TooltipFormat.ADAPTIVE;

    // Dynamic Modded Items
    public java.util.Map<String, Integer> dynamicPercentages = new java.util.HashMap<>();
    public java.util.Map<String, Boolean> dynamicInfinities = new java.util.HashMap<>();
    public java.util.Map<String, Boolean> dynamicSingleUses = new java.util.HashMap<>();

    public static synchronized void load(Path configDir) {
        CONFIG_PATH = configDir.resolve("durability-multiplier.json");
        INSTANCE = net.dasik.social.api.config.ConfigHelper.load(
            CONFIG_PATH, INSTANCE, DurabilityConfig.class, VERSION,
            config -> config.configVersion, (config, ver) -> {
                config.configVersion = ver;
                config.migrateFromV1();
            },
            null, org.slf4j.LoggerFactory.getLogger("DurabilityMultiplier")
        );
    }

    public void migrateFromV1() {
        if (multiplierGlobal != null && multiplierGlobal > 0) percentGlobal = multiplierGlobal * 100;
        if (multiplierWeapons != null && multiplierWeapons > 0) percentWeapons = multiplierWeapons * 100;
        if (multiplierSwords != null && multiplierSwords > 0) percentSwords = multiplierSwords * 100;
        if (multiplierSpears != null && multiplierSpears > 0) percentSpears = multiplierSpears * 100;
        if (multiplierTridents != null && multiplierTridents > 0) percentTridents = multiplierTridents * 100;
        if (multiplierMaces != null && multiplierMaces > 0) percentMaces = multiplierMaces * 100;
        if (multiplierBows != null && multiplierBows > 0) percentBows = multiplierBows * 100;
        if (multiplierCrossbows != null && multiplierCrossbows > 0) percentCrossbows = multiplierCrossbows * 100;
        if (multiplierTools != null && multiplierTools > 0) percentTools = multiplierTools * 100;
        if (multiplierArmor != null && multiplierArmor > 0) percentArmor = multiplierArmor * 100;
        if (multiplierElytra != null && multiplierElytra > 0) percentElytra = multiplierElytra * 100;
        if (multiplierShields != null && multiplierShields > 0) percentShields = multiplierShields * 100;
    }

    public static synchronized void save() {
        if (CONFIG_PATH == null) return;
        net.dasik.social.api.config.ConfigHelper.save(CONFIG_PATH, INSTANCE, org.slf4j.LoggerFactory.getLogger("DurabilityMultiplier"));
    }

    public static DurabilityConfig get() { return INSTANCE; }
}
