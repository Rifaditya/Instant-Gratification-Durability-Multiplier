// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.network;

import net.instantgratification.durabilitymultiplier.DurabilityHelper.ItemCategory;
import net.instantgratification.durabilitymultiplier.config.DurabilityConfig;

import java.util.Map;

/**
 * Client-side cache for synced GameRule values (Minecraft 1.20.1).
 * Populated by {@link DurabilityPayload} received from the server.
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

    private static Map<String, Integer> dynamicPercentages = Map.of();
    private static Map<String, Boolean> dynamicInfinities = Map.of();
    private static Map<String, Boolean> dynamicSingleUses = Map.of();

    private DurabilityClientState() {
    }

    /** Apply received payload from the server. */
    public static void apply(DurabilityPayload payload) {
        if (payload == null) return;
        applyPayload(payload);
    }

    /** Apply received payload from the server (alias). */
    public static void applyPayload(DurabilityPayload payload) {
        if (payload == null) return;
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

        dynamicPercentages = payload.dynamicPercentages() != null ? payload.dynamicPercentages() : Map.of();
        dynamicInfinities = payload.dynamicInfinities() != null ? payload.dynamicInfinities() : Map.of();
        dynamicSingleUses = payload.dynamicSingleUses() != null ? payload.dynamicSingleUses() : Map.of();
    }

    /**
     * Unified client-side durability percentage query for category and item id.
     */
    public static int getPercentage(ItemCategory cat, String itemId) {
        if (itemId != null) {
            int dynamicVal = getDynamicPercent(itemId);
            if (dynamicVal != 0) {
                return dynamicVal < 0 ? -1 : dynamicVal;
            }
            int forcedVal = DurabilityConfig.get().getForcedPercent(itemId);
            if (forcedVal != 0) {
                return forcedVal < 0 ? -1 : forcedVal;
            }
        }

        if (cat == null) {
            return percentGlobal != 0 ? (percentGlobal < 0 ? -1 : percentGlobal) : 100;
        }

        int specific = switch (cat) {
            case SWORD -> percentSwords;
            case SPEAR -> percentSpears;
            case TRIDENT -> percentTridents;
            case MACE -> percentMaces;
            case BOW -> percentBows;
            case CROSSBOW -> percentCrossbows;
            case SHIELD -> percentShields;
            case WEAPON_GLOBAL -> 0;

            case PICKAXE -> percentPickaxes;
            case AXE -> percentAxes;
            case SHOVEL -> percentShovels;
            case HOE -> percentHoes;
            case SHEARS -> percentShears;
            case FISHING_ROD -> percentFishingRods;
            case BRUSH -> percentBrushes;
            case FLINT_AND_STEEL -> percentFlintAndSteel;
            case TOOL_GLOBAL -> percentTools;

            case HELMET -> percentHelmets;
            case CHESTPLATE -> percentChestplates;
            case LEGGINGS -> percentLeggings;
            case BOOTS -> percentBoots;
            case ARMOR_GLOBAL -> percentArmor;

            case ELYTRA -> percentElytra;
            case OTHER -> 0;
        };
        if (specific != 0) {
            return specific < 0 ? -1 : specific;
        }

        // Tool parent fallback
        if (cat == ItemCategory.PICKAXE || cat == ItemCategory.AXE || cat == ItemCategory.SHOVEL ||
                cat == ItemCategory.HOE || cat == ItemCategory.SHEARS || cat == ItemCategory.FISHING_ROD ||
                cat == ItemCategory.BRUSH || cat == ItemCategory.FLINT_AND_STEEL || cat == ItemCategory.TOOL_GLOBAL) {
            if (percentTools != 0) {
                return percentTools < 0 ? -1 : percentTools;
            }
        }

        // Armor parent fallback
        if (cat == ItemCategory.HELMET || cat == ItemCategory.CHESTPLATE ||
                cat == ItemCategory.LEGGINGS || cat == ItemCategory.BOOTS || cat == ItemCategory.ARMOR_GLOBAL) {
            if (percentArmor != 0) {
                return percentArmor < 0 ? -1 : percentArmor;
            }
        }

        // Weapons parent fallback
        if (cat == ItemCategory.SWORD || cat == ItemCategory.SPEAR || cat == ItemCategory.TRIDENT ||
                cat == ItemCategory.MACE || cat == ItemCategory.BOW || cat == ItemCategory.CROSSBOW ||
                cat == ItemCategory.WEAPON_GLOBAL) {
            if (percentWeapons != 0) {
                return percentWeapons < 0 ? -1 : percentWeapons;
            }
        }

        return percentGlobal != 0 ? (percentGlobal < 0 ? -1 : percentGlobal) : 100;
    }

    /**
     * Unified client-side infinity (God Mode) query for category and item id.
     */
    public static boolean isInfinite(ItemCategory cat, String itemId) {
        if (itemId != null) {
            if (getDynamicInfinity(itemId) || DurabilityConfig.get().getForcedInfinity(itemId)) {
                return true;
            }
        }

        if (cat == null) {
            return infinityGlobal;
        }

        return switch (cat) {
            case SWORD -> infinitySwords || infinityWeapons || infinityGlobal;
            case SPEAR -> infinitySpears || infinityWeapons || infinityGlobal;
            case TRIDENT -> infinityTridents || infinityWeapons || infinityGlobal;
            case MACE -> infinityMaces || infinityWeapons || infinityGlobal;
            case BOW -> infinityBows || infinityWeapons || infinityGlobal;
            case CROSSBOW -> infinityCrossbows || infinityWeapons || infinityGlobal;
            case SHIELD -> infinityShields || infinityGlobal;
            case WEAPON_GLOBAL -> infinityWeapons || infinityGlobal;

            case PICKAXE -> infinityPickaxes || infinityTools || infinityGlobal;
            case AXE -> infinityAxes || infinityTools || infinityGlobal;
            case SHOVEL -> infinityShovels || infinityTools || infinityGlobal;
            case HOE -> infinityHoes || infinityTools || infinityGlobal;
            case SHEARS -> infinityShears || infinityTools || infinityGlobal;
            case FISHING_ROD -> infinityFishingRods || infinityTools || infinityGlobal;
            case BRUSH -> infinityBrushes || infinityTools || infinityGlobal;
            case FLINT_AND_STEEL -> infinityFlintAndSteel || infinityTools || infinityGlobal;
            case TOOL_GLOBAL -> infinityTools || infinityGlobal;

            case HELMET -> infinityHelmets || infinityArmor || infinityGlobal;
            case CHESTPLATE -> infinityChestplates || infinityArmor || infinityGlobal;
            case LEGGINGS -> infinityLeggings || infinityArmor || infinityGlobal;
            case BOOTS -> infinityBoots || infinityArmor || infinityGlobal;
            case ARMOR_GLOBAL -> infinityArmor || infinityGlobal;

            case ELYTRA -> infinityElytra || infinityGlobal;
            case OTHER -> infinityGlobal;
        };
    }

    /**
     * Unified client-side single-use (Glass Mode) query for category and item id.
     */
    public static boolean isSingleUse(ItemCategory cat, String itemId) {
        if (getPercentage(cat, itemId) <= -1) {
            return true;
        }

        if (itemId != null) {
            if (getDynamicSingleUse(itemId) || DurabilityConfig.get().getForcedSingleUse(itemId)) {
                return true;
            }
        }

        if (cat == null) {
            return singleUseGlobal;
        }

        return switch (cat) {
            case SWORD -> singleUseSwords || singleUseWeapons || singleUseGlobal;
            case SPEAR -> singleUseSpears || singleUseWeapons || singleUseGlobal;
            case TRIDENT -> singleUseTridents || singleUseWeapons || singleUseGlobal;
            case MACE -> singleUseMaces || singleUseWeapons || singleUseGlobal;
            case BOW -> singleUseBows || singleUseWeapons || singleUseGlobal;
            case CROSSBOW -> singleUseCrossbows || singleUseWeapons || singleUseGlobal;
            case SHIELD -> singleUseShields || singleUseGlobal;
            case WEAPON_GLOBAL -> singleUseWeapons || singleUseGlobal;

            case PICKAXE -> singleUsePickaxes || singleUseTools || singleUseGlobal;
            case AXE -> singleUseAxes || singleUseTools || singleUseGlobal;
            case SHOVEL -> singleUseShovels || singleUseTools || singleUseGlobal;
            case HOE -> singleUseHoes || singleUseTools || singleUseGlobal;
            case SHEARS -> singleUseShears || singleUseTools || singleUseGlobal;
            case FISHING_ROD -> singleUseFishingRods || singleUseTools || singleUseGlobal;
            case BRUSH -> singleUseBrushes || singleUseTools || singleUseGlobal;
            case FLINT_AND_STEEL -> singleUseFlintAndSteel || singleUseTools || singleUseGlobal;
            case TOOL_GLOBAL -> singleUseTools || singleUseGlobal;

            case HELMET -> singleUseHelmets || singleUseArmor || singleUseGlobal;
            case CHESTPLATE -> singleUseChestplates || singleUseArmor || singleUseGlobal;
            case LEGGINGS -> singleUseLeggings || singleUseArmor || singleUseGlobal;
            case BOOTS -> singleUseBoots || singleUseArmor || singleUseGlobal;
            case ARMOR_GLOBAL -> singleUseArmor || singleUseGlobal;

            case ELYTRA -> singleUseElytra || singleUseGlobal;
            case OTHER -> singleUseGlobal;
        };
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
