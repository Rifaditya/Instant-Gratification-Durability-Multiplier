// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.network;

/**
 * Client-side cache for synced GameRule values.
 * Populated by {@link DurabilityPayload} received from the server.
 * Read by the tooltip mixin instead of accessing ServerLevel GameRules.
 */
public final class DurabilityClientState {

    private static int percentGlobal = 200;
    private static int percentWeapons;
    private static int percentSwords;
    private static int percentSpears;
    private static int percentTridents;
    private static int percentMaces;
    private static int percentBows;
    private static int percentCrossbows;
    private static int percentShields;
    private static int percentTools;
    private static int percentPickaxes;
    private static int percentAxes;
    private static int percentShovels;
    private static int percentHoes;
    private static int percentShears;
    private static int percentFishingRods;
    private static int percentBrushes;
    private static int percentFlintAndSteel;
    private static int percentArmor;
    private static int percentHelmets;
    private static int percentChestplates;
    private static int percentLeggings;
    private static int percentBoots;
    private static int percentElytra;
    
    private static boolean infinityGlobal;
    private static boolean infinityWeapons;
    private static boolean infinitySwords;
    private static boolean infinitySpears;
    private static boolean infinityTridents;
    private static boolean infinityMaces;
    private static boolean infinityBows;
    private static boolean infinityCrossbows;
    private static boolean infinityShields;
    private static boolean infinityTools;
    private static boolean infinityPickaxes;
    private static boolean infinityAxes;
    private static boolean infinityShovels;
    private static boolean infinityHoes;
    private static boolean infinityShears;
    private static boolean infinityFishingRods;
    private static boolean infinityBrushes;
    private static boolean infinityFlintAndSteel;
    private static boolean infinityArmor;
    private static boolean infinityHelmets;
    private static boolean infinityChestplates;
    private static boolean infinityLeggings;
    private static boolean infinityBoots;
    private static boolean infinityElytra;
    
    private static boolean singleUseGlobal;
    private static boolean singleUseWeapons;
    private static boolean singleUseSwords;
    private static boolean singleUseSpears;
    private static boolean singleUseTridents;
    private static boolean singleUseMaces;
    private static boolean singleUseBows;
    private static boolean singleUseCrossbows;
    private static boolean singleUseShields;
    private static boolean singleUseTools;
    private static boolean singleUsePickaxes;
    private static boolean singleUseAxes;
    private static boolean singleUseShovels;
    private static boolean singleUseHoes;
    private static boolean singleUseShears;
    private static boolean singleUseFishingRods;
    private static boolean singleUseBrushes;
    private static boolean singleUseFlintAndSteel;
    private static boolean singleUseArmor;
    private static boolean singleUseHelmets;
    private static boolean singleUseChestplates;
    private static boolean singleUseLeggings;
    private static boolean singleUseBoots;
    private static boolean singleUseElytra;

    private static boolean showTooltip = true;

    private static java.util.Map<String, Integer> dynamicPercentages = java.util.Map.of();
    private static java.util.Map<String, Boolean> dynamicInfinities = java.util.Map.of();
    private static java.util.Map<String, Boolean> dynamicSingleUses = java.util.Map.of();

    private DurabilityClientState() {
    }

    /** Apply received payload from the server. */
    public static void apply(DurabilityPayload payload) {
        percentGlobal = payload.percentGlobal();
        percentWeapons = payload.percentWeapons();
        percentSwords = payload.percentSwords();
        percentSpears = payload.percentSpears();
        percentTridents = payload.percentTridents();
        percentMaces = payload.percentMaces();
        percentBows = payload.percentBows();
        percentCrossbows = payload.percentCrossbows();
        percentShields = payload.percentShields();
        percentTools = payload.percentTools();
        percentPickaxes = payload.percentPickaxes();
        percentAxes = payload.percentAxes();
        percentShovels = payload.percentShovels();
        percentHoes = payload.percentHoes();
        percentShears = payload.percentShears();
        percentFishingRods = payload.percentFishingRods();
        percentBrushes = payload.percentBrushes();
        percentFlintAndSteel = payload.percentFlintAndSteel();
        percentArmor = payload.percentArmor();
        percentHelmets = payload.percentHelmets();
        percentChestplates = payload.percentChestplates();
        percentLeggings = payload.percentLeggings();
        percentBoots = payload.percentBoots();
        percentElytra = payload.percentElytra();
        
        infinityGlobal = payload.infinityGlobal();
        infinityWeapons = payload.infinityWeapons();
        infinitySwords = payload.infinitySwords();
        infinitySpears = payload.infinitySpears();
        infinityTridents = payload.infinityTridents();
        infinityMaces = payload.infinityMaces();
        infinityBows = payload.infinityBows();
        infinityCrossbows = payload.infinityCrossbows();
        infinityShields = payload.infinityShields();
        infinityTools = payload.infinityTools();
        infinityPickaxes = payload.infinityPickaxes();
        infinityAxes = payload.infinityAxes();
        infinityShovels = payload.infinityShovels();
        infinityHoes = payload.infinityHoes();
        infinityShears = payload.infinityShears();
        infinityFishingRods = payload.infinityFishingRods();
        infinityBrushes = payload.infinityBrushes();
        infinityFlintAndSteel = payload.infinityFlintAndSteel();
        infinityArmor = payload.infinityArmor();
        infinityHelmets = payload.infinityHelmets();
        infinityChestplates = payload.infinityChestplates();
        infinityLeggings = payload.infinityLeggings();
        infinityBoots = payload.infinityBoots();
        infinityElytra = payload.infinityElytra();

        singleUseGlobal = payload.singleUseGlobal();
        singleUseWeapons = payload.singleUseWeapons();
        singleUseSwords = payload.singleUseSwords();
        singleUseSpears = payload.singleUseSpears();
        singleUseTridents = payload.singleUseTridents();
        singleUseMaces = payload.singleUseMaces();
        singleUseBows = payload.singleUseBows();
        singleUseCrossbows = payload.singleUseCrossbows();
        singleUseShields = payload.singleUseShields();
        singleUseTools = payload.singleUseTools();
        singleUsePickaxes = payload.singleUsePickaxes();
        singleUseAxes = payload.singleUseAxes();
        singleUseShovels = payload.singleUseShovels();
        singleUseHoes = payload.singleUseHoes();
        singleUseShears = payload.singleUseShears();
        singleUseFishingRods = payload.singleUseFishingRods();
        singleUseBrushes = payload.singleUseBrushes();
        singleUseFlintAndSteel = payload.singleUseFlintAndSteel();
        singleUseArmor = payload.singleUseArmor();
        singleUseHelmets = payload.singleUseHelmets();
        singleUseChestplates = payload.singleUseChestplates();
        singleUseLeggings = payload.singleUseLeggings();
        singleUseBoots = payload.singleUseBoots();
        singleUseElytra = payload.singleUseElytra();
        
        showTooltip = payload.showTooltip();

        dynamicPercentages = payload.dynamicPercentages() != null ? payload.dynamicPercentages() : java.util.Map.of();
        dynamicInfinities = payload.dynamicInfinities() != null ? payload.dynamicInfinities() : java.util.Map.of();
        dynamicSingleUses = payload.dynamicSingleUses() != null ? payload.dynamicSingleUses() : java.util.Map.of();
    }

    // ==================== Accessors ====================

    public static int percentGlobal() { return percentGlobal; }
    public static int percentWeapons() { return percentWeapons; }
    public static int percentSwords() { return percentSwords; }
    public static int percentSpears() { return percentSpears; }
    public static int percentTridents() { return percentTridents; }
    public static int percentMaces() { return percentMaces; }
    public static int percentBows() { return percentBows; }
    public static int percentCrossbows() { return percentCrossbows; }
    public static int percentShields() { return percentShields; }
    public static int percentTools() { return percentTools; }
    public static int percentPickaxes() { return percentPickaxes; }
    public static int percentAxes() { return percentAxes; }
    public static int percentShovels() { return percentShovels; }
    public static int percentHoes() { return percentHoes; }
    public static int percentShears() { return percentShears; }
    public static int percentFishingRods() { return percentFishingRods; }
    public static int percentBrushes() { return percentBrushes; }
    public static int percentFlintAndSteel() { return percentFlintAndSteel; }
    public static int percentArmor() { return percentArmor; }
    public static int percentHelmets() { return percentHelmets; }
    public static int percentChestplates() { return percentChestplates; }
    public static int percentLeggings() { return percentLeggings; }
    public static int percentBoots() { return percentBoots; }
    public static int percentElytra() { return percentElytra; }

    public static boolean infinityGlobal() { return infinityGlobal; }
    public static boolean infinityWeapons() { return infinityWeapons; }
    public static boolean infinitySwords() { return infinitySwords; }
    public static boolean infinitySpears() { return infinitySpears; }
    public static boolean infinityTridents() { return infinityTridents; }
    public static boolean infinityMaces() { return infinityMaces; }
    public static boolean infinityBows() { return infinityBows; }
    public static boolean infinityCrossbows() { return infinityCrossbows; }
    public static boolean infinityShields() { return infinityShields; }
    public static boolean infinityTools() { return infinityTools; }
    public static boolean infinityPickaxes() { return infinityPickaxes; }
    public static boolean infinityAxes() { return infinityAxes; }
    public static boolean infinityShovels() { return infinityShovels; }
    public static boolean infinityHoes() { return infinityHoes; }
    public static boolean infinityShears() { return infinityShears; }
    public static boolean infinityFishingRods() { return infinityFishingRods; }
    public static boolean infinityBrushes() { return infinityBrushes; }
    public static boolean infinityFlintAndSteel() { return infinityFlintAndSteel; }
    public static boolean infinityArmor() { return infinityArmor; }
    public static boolean infinityHelmets() { return infinityHelmets; }
    public static boolean infinityChestplates() { return infinityChestplates; }
    public static boolean infinityLeggings() { return infinityLeggings; }
    public static boolean infinityBoots() { return infinityBoots; }
    public static boolean infinityElytra() { return infinityElytra; }

    public static boolean singleUseGlobal() { return singleUseGlobal; }
    public static boolean singleUseWeapons() { return singleUseWeapons; }
    public static boolean singleUseSwords() { return singleUseSwords; }
    public static boolean singleUseSpears() { return singleUseSpears; }
    public static boolean singleUseTridents() { return singleUseTridents; }
    public static boolean singleUseMaces() { return singleUseMaces; }
    public static boolean singleUseBows() { return singleUseBows; }
    public static boolean singleUseCrossbows() { return singleUseCrossbows; }
    public static boolean singleUseShields() { return singleUseShields; }
    public static boolean singleUseTools() { return singleUseTools; }
    public static boolean singleUsePickaxes() { return singleUsePickaxes; }
    public static boolean singleUseAxes() { return singleUseAxes; }
    public static boolean singleUseShovels() { return singleUseShovels; }
    public static boolean singleUseHoes() { return singleUseHoes; }
    public static boolean singleUseShears() { return singleUseShears; }
    public static boolean singleUseFishingRods() { return singleUseFishingRods; }
    public static boolean singleUseBrushes() { return singleUseBrushes; }
    public static boolean singleUseFlintAndSteel() { return singleUseFlintAndSteel; }
    public static boolean singleUseArmor() { return singleUseArmor; }
    public static boolean singleUseHelmets() { return singleUseHelmets; }
    public static boolean singleUseChestplates() { return singleUseChestplates; }
    public static boolean singleUseLeggings() { return singleUseLeggings; }
    public static boolean singleUseBoots() { return singleUseBoots; }
    public static boolean singleUseElytra() { return singleUseElytra; }

    public static boolean showTooltip() { return showTooltip; }

    public static int getDynamicPercent(String itemKey) {
        return dynamicPercentages.getOrDefault(itemKey, 0);
    }

    public static boolean getDynamicInfinity(String itemKey) {
        return dynamicInfinities.getOrDefault(itemKey, false);
    }

    public static boolean getDynamicSingleUse(String itemKey) {
        return dynamicSingleUses.getOrDefault(itemKey, false);
    }
}
