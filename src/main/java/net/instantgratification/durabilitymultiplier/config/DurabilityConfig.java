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

    // Forced & Dynamic Modded Items
    public java.util.List<String> forcedItems = new java.util.ArrayList<>();
    public java.util.Map<String, Integer> forcedPercentages = new java.util.HashMap<>();
    public java.util.Map<String, Boolean> forcedInfinities = new java.util.HashMap<>();
    public java.util.Map<String, Boolean> forcedSingleUses = new java.util.HashMap<>();

    // Dynamic Modded Items (backward compatibility aliases)
    public java.util.Map<String, Integer> dynamicPercentages = new java.util.HashMap<>();
    public java.util.Map<String, Boolean> dynamicInfinities = new java.util.HashMap<>();
    public java.util.Map<String, Boolean> dynamicSingleUses = new java.util.HashMap<>();

    public java.util.Set<String> getAllForcedItemIds() {
        java.util.Set<String> ids = new java.util.LinkedHashSet<>();
        if (forcedItems != null) ids.addAll(forcedItems);
        if (forcedPercentages != null) ids.addAll(forcedPercentages.keySet());
        if (forcedInfinities != null) ids.addAll(forcedInfinities.keySet());
        if (forcedSingleUses != null) ids.addAll(forcedSingleUses.keySet());
        if (dynamicPercentages != null) ids.addAll(dynamicPercentages.keySet());
        if (dynamicInfinities != null) ids.addAll(dynamicInfinities.keySet());
        if (dynamicSingleUses != null) ids.addAll(dynamicSingleUses.keySet());
        return ids;
    }

    public int getForcedPercent(String itemId) {
        if (forcedPercentages != null && forcedPercentages.containsKey(itemId)) {
            return forcedPercentages.get(itemId);
        }
        if (dynamicPercentages != null && dynamicPercentages.containsKey(itemId)) {
            return dynamicPercentages.get(itemId);
        }
        return 0;
    }

    public boolean getForcedInfinity(String itemId) {
        if (forcedInfinities != null && forcedInfinities.containsKey(itemId)) {
            return Boolean.TRUE.equals(forcedInfinities.get(itemId));
        }
        if (dynamicInfinities != null && dynamicInfinities.containsKey(itemId)) {
            return Boolean.TRUE.equals(dynamicInfinities.get(itemId));
        }
        return false;
    }

    public boolean getForcedSingleUse(String itemId) {
        if (forcedSingleUses != null && forcedSingleUses.containsKey(itemId)) {
            return Boolean.TRUE.equals(forcedSingleUses.get(itemId));
        }
        if (dynamicSingleUses != null && dynamicSingleUses.containsKey(itemId)) {
            return Boolean.TRUE.equals(dynamicSingleUses.get(itemId));
        }
        return false;
    }

    public boolean isForced(String itemId) {
        return getAllForcedItemIds().contains(itemId);
    }

    private static volatile boolean dirty = false;

    public static void markDirty() {
        dirty = true;
    }

    public static boolean isDirty() {
        return dirty;
    }

    public synchronized boolean recordDiscoveredItem(String itemId) {
        if (itemId == null || itemId.isEmpty()) return false;
        boolean modified = false;
        if (forcedItems == null) {
            forcedItems = new java.util.ArrayList<>();
            modified = true;
        }
        if (!forcedItems.contains(itemId)) {
            forcedItems.add(itemId);
            modified = true;
        }
        if (forcedPercentages == null) {
            forcedPercentages = new java.util.HashMap<>();
            modified = true;
        }
        if (!forcedPercentages.containsKey(itemId)) {
            if (dynamicPercentages != null && dynamicPercentages.containsKey(itemId)) {
                forcedPercentages.put(itemId, dynamicPercentages.get(itemId));
            } else {
                forcedPercentages.put(itemId, 0);
            }
            modified = true;
        }
        if (dynamicPercentages != null && dynamicPercentages.containsKey(itemId) && !forcedPercentages.containsKey(itemId)) {
            forcedPercentages.put(itemId, dynamicPercentages.get(itemId));
            modified = true;
        }
        if (dynamicInfinities != null && dynamicInfinities.containsKey(itemId)) {
            if (forcedInfinities == null) forcedInfinities = new java.util.HashMap<>();
            if (!forcedInfinities.containsKey(itemId)) {
                forcedInfinities.put(itemId, dynamicInfinities.get(itemId));
                modified = true;
            }
        }
        if (dynamicSingleUses != null && dynamicSingleUses.containsKey(itemId)) {
            if (forcedSingleUses == null) forcedSingleUses = new java.util.HashMap<>();
            if (!forcedSingleUses.containsKey(itemId)) {
                forcedSingleUses.put(itemId, dynamicSingleUses.get(itemId));
                modified = true;
            }
        }
        if (modified) {
            dirty = true;
        }
        return modified;
    }

    public synchronized void setForcedPercent(String itemId, int percent) {
        if (itemId == null || itemId.isEmpty()) return;
        if (forcedPercentages == null) forcedPercentages = new java.util.HashMap<>();
        if (dynamicPercentages == null) dynamicPercentages = new java.util.HashMap<>();
        if (forcedItems == null) forcedItems = new java.util.ArrayList<>();
        forcedPercentages.put(itemId, percent);
        dynamicPercentages.put(itemId, percent);
        if (!forcedItems.contains(itemId)) forcedItems.add(itemId);
        dirty = true;
    }

    public synchronized void setForcedInfinity(String itemId, boolean infinity) {
        if (itemId == null || itemId.isEmpty()) return;
        if (forcedInfinities == null) forcedInfinities = new java.util.HashMap<>();
        if (dynamicInfinities == null) dynamicInfinities = new java.util.HashMap<>();
        if (forcedItems == null) forcedItems = new java.util.ArrayList<>();
        forcedInfinities.put(itemId, infinity);
        dynamicInfinities.put(itemId, infinity);
        if (!forcedItems.contains(itemId)) forcedItems.add(itemId);
        dirty = true;
    }

    public synchronized void setForcedSingleUse(String itemId, boolean singleUse) {
        if (itemId == null || itemId.isEmpty()) return;
        if (forcedSingleUses == null) forcedSingleUses = new java.util.HashMap<>();
        if (dynamicSingleUses == null) dynamicSingleUses = new java.util.HashMap<>();
        if (forcedItems == null) forcedItems = new java.util.ArrayList<>();
        forcedSingleUses.put(itemId, singleUse);
        dynamicSingleUses.put(itemId, singleUse);
        if (!forcedItems.contains(itemId)) forcedItems.add(itemId);
        dirty = true;
    }

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
        INSTANCE.validate();
    }

    public void validate() {
        percentGlobal = Math.max(percentGlobal, -1);
        percentWeapons = Math.max(percentWeapons, -1);
        percentSwords = Math.max(percentSwords, -1);
        percentSpears = Math.max(percentSpears, -1);
        percentTridents = Math.max(percentTridents, -1);
        percentMaces = Math.max(percentMaces, -1);
        percentBows = Math.max(percentBows, -1);
        percentCrossbows = Math.max(percentCrossbows, -1);
        percentTools = Math.max(percentTools, -1);
        percentPickaxes = Math.max(percentPickaxes, -1);
        percentAxes = Math.max(percentAxes, -1);
        percentShovels = Math.max(percentShovels, -1);
        percentHoes = Math.max(percentHoes, -1);
        percentShears = Math.max(percentShears, -1);
        percentFishingRods = Math.max(percentFishingRods, -1);
        percentBrushes = Math.max(percentBrushes, -1);
        percentFlintAndSteel = Math.max(percentFlintAndSteel, -1);
        percentArmor = Math.max(percentArmor, -1);
        percentHelmets = Math.max(percentHelmets, -1);
        percentChestplates = Math.max(percentChestplates, -1);
        percentLeggings = Math.max(percentLeggings, -1);
        percentBoots = Math.max(percentBoots, -1);
        percentElytra = Math.max(percentElytra, -1);
        percentShields = Math.max(percentShields, -1);

        if (forcedPercentages != null) {
            for (java.util.Map.Entry<String, Integer> e : forcedPercentages.entrySet()) {
                if (e.getValue() != null) {
                    e.setValue(Math.max(e.getValue(), -1));
                }
            }
        }
        if (dynamicPercentages != null) {
            for (java.util.Map.Entry<String, Integer> e : dynamicPercentages.entrySet()) {
                if (e.getValue() != null) {
                    e.setValue(Math.max(e.getValue(), -1));
                }
            }
        }
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

        if (dynamicPercentages != null) {
            if (forcedPercentages == null) forcedPercentages = new java.util.HashMap<>();
            if (forcedItems == null) forcedItems = new java.util.ArrayList<>();
            for (java.util.Map.Entry<String, Integer> e : dynamicPercentages.entrySet()) {
                forcedPercentages.putIfAbsent(e.getKey(), e.getValue());
                if (!forcedItems.contains(e.getKey())) forcedItems.add(e.getKey());
            }
        }
        if (dynamicInfinities != null) {
            if (forcedInfinities == null) forcedInfinities = new java.util.HashMap<>();
            if (forcedItems == null) forcedItems = new java.util.ArrayList<>();
            for (java.util.Map.Entry<String, Boolean> e : dynamicInfinities.entrySet()) {
                forcedInfinities.putIfAbsent(e.getKey(), e.getValue());
                if (!forcedItems.contains(e.getKey())) forcedItems.add(e.getKey());
            }
        }
        if (dynamicSingleUses != null) {
            if (forcedSingleUses == null) forcedSingleUses = new java.util.HashMap<>();
            if (forcedItems == null) forcedItems = new java.util.ArrayList<>();
            for (java.util.Map.Entry<String, Boolean> e : dynamicSingleUses.entrySet()) {
                forcedSingleUses.putIfAbsent(e.getKey(), e.getValue());
                if (!forcedItems.contains(e.getKey())) forcedItems.add(e.getKey());
            }
        }
    }

    public static synchronized void save() {
        if (CONFIG_PATH == null) return;
        net.dasik.social.api.config.ConfigHelper.save(CONFIG_PATH, INSTANCE, org.slf4j.LoggerFactory.getLogger("DurabilityMultiplier"));
    }

    public static synchronized void saveIfDirty() {
        if (dirty) {
            save();
            dirty = false;
        }
    }

    public static DurabilityConfig get() { return INSTANCE; }
}
