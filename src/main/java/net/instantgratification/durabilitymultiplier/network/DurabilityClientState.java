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
    
    private static boolean showTooltip = true;

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
        
        showTooltip = payload.showTooltip();
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

    public static boolean showTooltip() { return showTooltip; }
}
