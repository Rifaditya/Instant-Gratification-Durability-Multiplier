// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.registry;

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.instantgratification.durabilitymultiplier.DurabilityHelper;
import net.instantgratification.durabilitymultiplier.DurabilityMultiplier;
import net.instantgratification.durabilitymultiplier.config.DurabilityConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import java.util.List;

public class DurabilityRules {

    public static final GameRuleCategory DURABILITY_MULTIPLIER = GameRuleCategory
            .register(Identifier.fromNamespaceAndPath("durability-multiplier", "durability_multiplier"));

    // ==================== Percentages ====================
    public static GameRule<Integer> DM_PERCENT_GLOBAL;
    public static GameRule<Integer> DM_PERCENT_WEAPONS;
    public static GameRule<Integer> DM_PERCENT_SWORDS;
    public static GameRule<Integer> DM_PERCENT_SPEARS;
    public static GameRule<Integer> DM_PERCENT_TRIDENTS;
    public static GameRule<Integer> DM_PERCENT_MACES;
    public static GameRule<Integer> DM_PERCENT_BOWS;
    public static GameRule<Integer> DM_PERCENT_CROSSBOWS;
    public static GameRule<Integer> DM_PERCENT_TOOLS;
    public static GameRule<Integer> DM_PERCENT_PICKAXES;
    public static GameRule<Integer> DM_PERCENT_AXES;
    public static GameRule<Integer> DM_PERCENT_SHOVELS;
    public static GameRule<Integer> DM_PERCENT_HOES;
    public static GameRule<Integer> DM_PERCENT_SHEARS;
    public static GameRule<Integer> DM_PERCENT_FISHING_RODS;
    public static GameRule<Integer> DM_PERCENT_BRUSHES;
    public static GameRule<Integer> DM_PERCENT_FLINT_AND_STEEL;
    public static GameRule<Integer> DM_PERCENT_ARMOR;
    public static GameRule<Integer> DM_PERCENT_HELMETS;
    public static GameRule<Integer> DM_PERCENT_CHESTPLATES;
    public static GameRule<Integer> DM_PERCENT_LEGGINGS;
    public static GameRule<Integer> DM_PERCENT_BOOTS;
    public static GameRule<Integer> DM_PERCENT_ELYTRA;
    public static GameRule<Integer> DM_PERCENT_SHIELDS;

    // ==================== Infinity (God Mode) ====================
    public static GameRule<Boolean> DM_INFINITY_GLOBAL;
    public static GameRule<Boolean> DM_INFINITY_WEAPONS;
    public static GameRule<Boolean> DM_INFINITY_SWORDS;
    public static GameRule<Boolean> DM_INFINITY_SPEARS;
    public static GameRule<Boolean> DM_INFINITY_TRIDENTS;
    public static GameRule<Boolean> DM_INFINITY_MACES;
    public static GameRule<Boolean> DM_INFINITY_BOWS;
    public static GameRule<Boolean> DM_INFINITY_CROSSBOWS;
    public static GameRule<Boolean> DM_INFINITY_TOOLS;
    public static GameRule<Boolean> DM_INFINITY_PICKAXES;
    public static GameRule<Boolean> DM_INFINITY_AXES;
    public static GameRule<Boolean> DM_INFINITY_SHOVELS;
    public static GameRule<Boolean> DM_INFINITY_HOES;
    public static GameRule<Boolean> DM_INFINITY_SHEARS;
    public static GameRule<Boolean> DM_INFINITY_FISHING_RODS;
    public static GameRule<Boolean> DM_INFINITY_BRUSHES;
    public static GameRule<Boolean> DM_INFINITY_FLINT_AND_STEEL;
    public static GameRule<Boolean> DM_INFINITY_ARMOR;
    public static GameRule<Boolean> DM_INFINITY_HELMETS;
    public static GameRule<Boolean> DM_INFINITY_CHESTPLATES;
    public static GameRule<Boolean> DM_INFINITY_LEGGINGS;
    public static GameRule<Boolean> DM_INFINITY_BOOTS;
    public static GameRule<Boolean> DM_INFINITY_ELYTRA;
    public static GameRule<Boolean> DM_INFINITY_SHIELDS;

    // ==================== Single-Use (Glass Mode) ====================
    public static GameRule<Boolean> DM_SINGLE_USE_GLOBAL;
    public static GameRule<Boolean> DM_SINGLE_USE_WEAPONS;
    public static GameRule<Boolean> DM_SINGLE_USE_SWORDS;
    public static GameRule<Boolean> DM_SINGLE_USE_SPEARS;
    public static GameRule<Boolean> DM_SINGLE_USE_TRIDENTS;
    public static GameRule<Boolean> DM_SINGLE_USE_MACES;
    public static GameRule<Boolean> DM_SINGLE_USE_BOWS;
    public static GameRule<Boolean> DM_SINGLE_USE_CROSSBOWS;
    public static GameRule<Boolean> DM_SINGLE_USE_TOOLS;
    public static GameRule<Boolean> DM_SINGLE_USE_PICKAXES;
    public static GameRule<Boolean> DM_SINGLE_USE_AXES;
    public static GameRule<Boolean> DM_SINGLE_USE_SHOVELS;
    public static GameRule<Boolean> DM_SINGLE_USE_HOES;
    public static GameRule<Boolean> DM_SINGLE_USE_SHEARS;
    public static GameRule<Boolean> DM_SINGLE_USE_FISHING_RODS;
    public static GameRule<Boolean> DM_SINGLE_USE_BRUSHES;
    public static GameRule<Boolean> DM_SINGLE_USE_FLINT_AND_STEEL;
    public static GameRule<Boolean> DM_SINGLE_USE_ARMOR;
    public static GameRule<Boolean> DM_SINGLE_USE_HELMETS;
    public static GameRule<Boolean> DM_SINGLE_USE_CHESTPLATES;
    public static GameRule<Boolean> DM_SINGLE_USE_LEGGINGS;
    public static GameRule<Boolean> DM_SINGLE_USE_BOOTS;
    public static GameRule<Boolean> DM_SINGLE_USE_ELYTRA;
    public static GameRule<Boolean> DM_SINGLE_USE_SHIELDS;

    // ==================== Misc ====================
    public static GameRule<Boolean> DM_SHOW_TOOLTIP;

    // List of dynamic modded items registered during registry freeze
    public static final java.util.List<Identifier> DYNAMIC_ITEMS = new java.util.ArrayList<>();
    public static final java.util.Set<Identifier> FORCED_ITEMS = java.util.concurrent.ConcurrentHashMap.newKeySet();

    // ==================== Accessors ====================

    public static int getInt(Level level, GameRule<Integer> rule) {
        if (level == null || level.isClientSide())
            return 0;
        return DynamicGameRuleManager.getInt((ServerLevel) level, rule);
    }

    public static boolean getBoolean(Level level, GameRule<Boolean> rule) {
        if (level == null || level.isClientSide())
            return false;
        return DynamicGameRuleManager.getBoolean((ServerLevel) level, rule);
    }

    // ==================== Registration ====================

    public static void register() {
        DurabilityConfig config = DurabilityConfig.get();
        DM_PERCENT_GLOBAL = DynamicGameRuleManager.integerRule("ig:dm_percent_global", DURABILITY_MULTIPLIER, config.percentGlobal).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_WEAPONS = DynamicGameRuleManager.integerRule("ig:dm_percent_weapons", DURABILITY_MULTIPLIER, config.percentWeapons).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_SWORDS = DynamicGameRuleManager.integerRule("ig:dm_percent_swords", DURABILITY_MULTIPLIER, config.percentSwords).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_SPEARS = DynamicGameRuleManager.integerRule("ig:dm_percent_spears", DURABILITY_MULTIPLIER, config.percentSpears).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_TRIDENTS = DynamicGameRuleManager.integerRule("ig:dm_percent_tridents", DURABILITY_MULTIPLIER, config.percentTridents).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_MACES = DynamicGameRuleManager.integerRule("ig:dm_percent_maces", DURABILITY_MULTIPLIER, config.percentMaces).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_BOWS = DynamicGameRuleManager.integerRule("ig:dm_percent_bows", DURABILITY_MULTIPLIER, config.percentBows).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_CROSSBOWS = DynamicGameRuleManager.integerRule("ig:dm_percent_crossbows", DURABILITY_MULTIPLIER, config.percentCrossbows).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_TOOLS = DynamicGameRuleManager.integerRule("ig:dm_percent_tools", DURABILITY_MULTIPLIER, config.percentTools).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_PICKAXES = DynamicGameRuleManager.integerRule("ig:dm_percent_pickaxes", DURABILITY_MULTIPLIER, config.percentPickaxes).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_AXES = DynamicGameRuleManager.integerRule("ig:dm_percent_axes", DURABILITY_MULTIPLIER, config.percentAxes).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_SHOVELS = DynamicGameRuleManager.integerRule("ig:dm_percent_shovels", DURABILITY_MULTIPLIER, config.percentShovels).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_HOES = DynamicGameRuleManager.integerRule("ig:dm_percent_hoes", DURABILITY_MULTIPLIER, config.percentHoes).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_SHEARS = DynamicGameRuleManager.integerRule("ig:dm_percent_shears", DURABILITY_MULTIPLIER, config.percentShears).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_FISHING_RODS = DynamicGameRuleManager.integerRule("ig:dm_percent_fishing_rods", DURABILITY_MULTIPLIER, config.percentFishingRods).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_BRUSHES = DynamicGameRuleManager.integerRule("ig:dm_percent_brushes", DURABILITY_MULTIPLIER, config.percentBrushes).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_FLINT_AND_STEEL = DynamicGameRuleManager.integerRule("ig:dm_percent_flint_and_steel", DURABILITY_MULTIPLIER, config.percentFlintAndSteel).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_ARMOR = DynamicGameRuleManager.integerRule("ig:dm_percent_armor", DURABILITY_MULTIPLIER, config.percentArmor).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_HELMETS = DynamicGameRuleManager.integerRule("ig:dm_percent_helmets", DURABILITY_MULTIPLIER, config.percentHelmets).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_CHESTPLATES = DynamicGameRuleManager.integerRule("ig:dm_percent_chestplates", DURABILITY_MULTIPLIER, config.percentChestplates).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_LEGGINGS = DynamicGameRuleManager.integerRule("ig:dm_percent_leggings", DURABILITY_MULTIPLIER, config.percentLeggings).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_BOOTS = DynamicGameRuleManager.integerRule("ig:dm_percent_boots", DURABILITY_MULTIPLIER, config.percentBoots).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_ELYTRA = DynamicGameRuleManager.integerRule("ig:dm_percent_elytra", DURABILITY_MULTIPLIER, config.percentElytra).range(-1, Integer.MAX_VALUE).register();
        DM_PERCENT_SHIELDS = DynamicGameRuleManager.integerRule("ig:dm_percent_shields", DURABILITY_MULTIPLIER, config.percentShields).range(-1, Integer.MAX_VALUE).register();

        DM_INFINITY_GLOBAL = DynamicGameRuleManager.booleanRule("ig:dm_infinity_global", DURABILITY_MULTIPLIER, config.infinityGlobal).register();
        DM_INFINITY_WEAPONS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_weapons", DURABILITY_MULTIPLIER, config.infinityWeapons).register();
        DM_INFINITY_SWORDS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_swords", DURABILITY_MULTIPLIER, config.infinitySwords).register();
        DM_INFINITY_SPEARS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_spears", DURABILITY_MULTIPLIER, config.infinitySpears).register();
        DM_INFINITY_TRIDENTS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_tridents", DURABILITY_MULTIPLIER, config.infinityTridents).register();
        DM_INFINITY_MACES = DynamicGameRuleManager.booleanRule("ig:dm_infinity_maces", DURABILITY_MULTIPLIER, config.infinityMaces).register();
        DM_INFINITY_BOWS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_bows", DURABILITY_MULTIPLIER, config.infinityBows).register();
        DM_INFINITY_CROSSBOWS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_crossbows", DURABILITY_MULTIPLIER, config.infinityCrossbows).register();
        DM_INFINITY_TOOLS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_tools", DURABILITY_MULTIPLIER, config.infinityTools).register();
        DM_INFINITY_PICKAXES = DynamicGameRuleManager.booleanRule("ig:dm_infinity_pickaxes", DURABILITY_MULTIPLIER, config.infinityPickaxes).register();
        DM_INFINITY_AXES = DynamicGameRuleManager.booleanRule("ig:dm_infinity_axes", DURABILITY_MULTIPLIER, config.infinityAxes).register();
        DM_INFINITY_SHOVELS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_shovels", DURABILITY_MULTIPLIER, config.infinityShovels).register();
        DM_INFINITY_HOES = DynamicGameRuleManager.booleanRule("ig:dm_infinity_hoes", DURABILITY_MULTIPLIER, config.infinityHoes).register();
        DM_INFINITY_SHEARS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_shears", DURABILITY_MULTIPLIER, config.infinityShears).register();
        DM_INFINITY_FISHING_RODS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_fishing_rods", DURABILITY_MULTIPLIER, config.infinityFishingRods).register();
        DM_INFINITY_BRUSHES = DynamicGameRuleManager.booleanRule("ig:dm_infinity_brushes", DURABILITY_MULTIPLIER, config.infinityBrushes).register();
        DM_INFINITY_FLINT_AND_STEEL = DynamicGameRuleManager.booleanRule("ig:dm_infinity_flint_and_steel", DURABILITY_MULTIPLIER, config.infinityFlintAndSteel).register();
        DM_INFINITY_ARMOR = DynamicGameRuleManager.booleanRule("ig:dm_infinity_armor", DURABILITY_MULTIPLIER, config.infinityArmor).register();
        DM_INFINITY_HELMETS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_helmets", DURABILITY_MULTIPLIER, config.infinityHelmets).register();
        DM_INFINITY_CHESTPLATES = DynamicGameRuleManager.booleanRule("ig:dm_infinity_chestplates", DURABILITY_MULTIPLIER, config.infinityChestplates).register();
        DM_INFINITY_LEGGINGS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_leggings", DURABILITY_MULTIPLIER, config.infinityLeggings).register();
        DM_INFINITY_BOOTS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_boots", DURABILITY_MULTIPLIER, config.infinityBoots).register();
        DM_INFINITY_ELYTRA = DynamicGameRuleManager.booleanRule("ig:dm_infinity_elytra", DURABILITY_MULTIPLIER, config.infinityElytra).register();
        DM_INFINITY_SHIELDS = DynamicGameRuleManager.booleanRule("ig:dm_infinity_shields", DURABILITY_MULTIPLIER, config.infinityShields).register();

        DM_SINGLE_USE_GLOBAL = DynamicGameRuleManager.booleanRule("ig:dm_single_use_global", DURABILITY_MULTIPLIER, config.singleUseGlobal).register();
        DM_SINGLE_USE_WEAPONS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_weapons", DURABILITY_MULTIPLIER, config.singleUseWeapons).register();
        DM_SINGLE_USE_SWORDS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_swords", DURABILITY_MULTIPLIER, config.singleUseSwords).register();
        DM_SINGLE_USE_SPEARS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_spears", DURABILITY_MULTIPLIER, config.singleUseSpears).register();
        DM_SINGLE_USE_TRIDENTS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_tridents", DURABILITY_MULTIPLIER, config.singleUseTridents).register();
        DM_SINGLE_USE_MACES = DynamicGameRuleManager.booleanRule("ig:dm_single_use_maces", DURABILITY_MULTIPLIER, config.singleUseMaces).register();
        DM_SINGLE_USE_BOWS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_bows", DURABILITY_MULTIPLIER, config.singleUseBows).register();
        DM_SINGLE_USE_CROSSBOWS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_crossbows", DURABILITY_MULTIPLIER, config.singleUseCrossbows).register();
        DM_SINGLE_USE_TOOLS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_tools", DURABILITY_MULTIPLIER, config.singleUseTools).register();
        DM_SINGLE_USE_PICKAXES = DynamicGameRuleManager.booleanRule("ig:dm_single_use_pickaxes", DURABILITY_MULTIPLIER, config.singleUsePickaxes).register();
        DM_SINGLE_USE_AXES = DynamicGameRuleManager.booleanRule("ig:dm_single_use_axes", DURABILITY_MULTIPLIER, config.singleUseAxes).register();
        DM_SINGLE_USE_SHOVELS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_shovels", DURABILITY_MULTIPLIER, config.singleUseShovels).register();
        DM_SINGLE_USE_HOES = DynamicGameRuleManager.booleanRule("ig:dm_single_use_hoes", DURABILITY_MULTIPLIER, config.singleUseHoes).register();
        DM_SINGLE_USE_SHEARS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_shears", DURABILITY_MULTIPLIER, config.singleUseShears).register();
        DM_SINGLE_USE_FISHING_RODS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_fishing_rods", DURABILITY_MULTIPLIER, config.singleUseFishingRods).register();
        DM_SINGLE_USE_BRUSHES = DynamicGameRuleManager.booleanRule("ig:dm_single_use_brushes", DURABILITY_MULTIPLIER, config.singleUseBrushes).register();
        DM_SINGLE_USE_FLINT_AND_STEEL = DynamicGameRuleManager.booleanRule("ig:dm_single_use_flint_and_steel", DURABILITY_MULTIPLIER, config.singleUseFlintAndSteel).register();
        DM_SINGLE_USE_ARMOR = DynamicGameRuleManager.booleanRule("ig:dm_single_use_armor", DURABILITY_MULTIPLIER, config.singleUseArmor).register();
        DM_SINGLE_USE_HELMETS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_helmets", DURABILITY_MULTIPLIER, config.singleUseHelmets).register();
        DM_SINGLE_USE_CHESTPLATES = DynamicGameRuleManager.booleanRule("ig:dm_single_use_chestplates", DURABILITY_MULTIPLIER, config.singleUseChestplates).register();
        DM_SINGLE_USE_LEGGINGS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_leggings", DURABILITY_MULTIPLIER, config.singleUseLeggings).register();
        DM_SINGLE_USE_BOOTS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_boots", DURABILITY_MULTIPLIER, config.singleUseBoots).register();
        DM_SINGLE_USE_ELYTRA = DynamicGameRuleManager.booleanRule("ig:dm_single_use_elytra", DURABILITY_MULTIPLIER, config.singleUseElytra).register();
        DM_SINGLE_USE_SHIELDS = DynamicGameRuleManager.booleanRule("ig:dm_single_use_shields", DURABILITY_MULTIPLIER, config.singleUseShields).register();

        DM_SHOW_TOOLTIP = DynamicGameRuleManager.booleanRule("ig:dm_show_tooltip", DURABILITY_MULTIPLIER, config.showTooltip).register();

        // 1. Immediately register all explicitly forced items from config at startup
        for (String idStr : config.getAllForcedItemIds()) {
            Identifier id = Identifier.tryParse(idStr);
            if (id != null) {
                registerDynamicRules(id);
                if (!DYNAMIC_ITEMS.contains(id)) {
                    DYNAMIC_ITEMS.add(id);
                }
                FORCED_ITEMS.add(id);
            }
        }

        // 2. Universal 3-tier discovery scanner (Startup sweep + Live entry callback + Server start safety sweep)
        net.dasik.social.api.registry.DynamicRegistryScanner.subscribe(
            BuiltInRegistries.ITEM,
            DurabilityRules::isItemDamageable,
            (id, item) -> {
                if (!id.getNamespace().equals("minecraft") && !id.getNamespace().equals("c")) {
                    config.recordDiscoveredItem(id.toString());
                    FORCED_ITEMS.add(id);
                    registerDynamicRules(id);
                    if (!DYNAMIC_ITEMS.contains(id)) {
                        DYNAMIC_ITEMS.add(id);
                    }
                }
            }
        );
    }

    public static boolean isItemDamageable(Item item) {
        if (item == null) return false;
        try {
            Identifier id = BuiltInRegistries.ITEM.getKey(item);
            if (id != null && (FORCED_ITEMS.contains(id) || DurabilityConfig.get().isForced(id.toString()))) {
                return true;
            }
            Integer maxDamage = item.components().get(DataComponents.MAX_DAMAGE);
            return maxDamage != null && maxDamage > 0;
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean isForcedItem(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return false;
        Identifier id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (id == null) return false;
        return FORCED_ITEMS.contains(id) || DurabilityConfig.get().isForced(id.toString());
    }

    public static boolean isDamageableOrForced(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return false;
        return stack.isDamageableItem() || isForcedItem(stack);
    }

    public static void registerDynamicRules(Identifier id) {
        DurabilityConfig config = DurabilityConfig.get();

        String infinityRuleName = "ig:infinity_" + id.getNamespace() + "_" + id.getPath();
        String singleUseRuleName = "ig:single_use_" + id.getNamespace() + "_" + id.getPath();
        String percentRuleName = "ig:percent_" + id.getNamespace() + "_" + id.getPath();

        boolean defaultInfinity = config.getForcedInfinity(id.toString());
        boolean defaultSingleUse = config.getForcedSingleUse(id.toString());
        int defaultPercent = config.getForcedPercent(id.toString());

        DynamicGameRuleManager.booleanRule(infinityRuleName, DURABILITY_MULTIPLIER, defaultInfinity)
            .name(DynamicGameRuleManager.generateReadableName(id.getPath()) + " Infinity")
            .description("Enable god mode for " + id)
            .register();

        DynamicGameRuleManager.booleanRule(singleUseRuleName, DURABILITY_MULTIPLIER, defaultSingleUse)
            .name(DynamicGameRuleManager.generateReadableName(id.getPath()) + " Single-Use")
            .description("Enable 1-hit break mode for " + id)
            .register();

        DynamicGameRuleManager.integerRule(percentRuleName, DURABILITY_MULTIPLIER, defaultPercent)
            .name(DynamicGameRuleManager.generateReadableName(id.getPath()) + " Durability Percent")
            .description("Durability percentage for " + id + " (100 = vanilla, 200 = 2x, 50 = 0.5x, -1 = single-use)")
            .range(-1, Integer.MAX_VALUE)
            .register();
    }

    @SuppressWarnings("unchecked")
    public static java.util.Map<String, Integer> getActiveDynamicPercentages(ServerLevel level) {
        java.util.Map<String, Integer> active = new java.util.HashMap<>();
        for (Identifier id : DYNAMIC_ITEMS) {
            String percentRuleName = "ig:percent_" + id.getNamespace() + "_" + id.getPath();
            GameRule<Integer> rule = (GameRule<Integer>) DynamicGameRuleManager.getDynamicRules().get(percentRuleName);
            if (rule != null) {
                int val = DynamicGameRuleManager.getInt(level, rule);
                if (val != 0) {
                    active.put(id.toString(), val);
                }
            }
        }
        return active;
    }

    @SuppressWarnings("unchecked")
    public static java.util.Map<String, Boolean> getActiveDynamicInfinities(ServerLevel level) {
        java.util.Map<String, Boolean> active = new java.util.HashMap<>();
        for (Identifier id : DYNAMIC_ITEMS) {
            String infinityRuleName = "ig:infinity_" + id.getNamespace() + "_" + id.getPath();
            GameRule<Boolean> rule = (GameRule<Boolean>) DynamicGameRuleManager.getDynamicRules().get(infinityRuleName);
            if (rule != null) {
                boolean val = DynamicGameRuleManager.getBoolean(level, rule);
                if (val) {
                    active.put(id.toString(), true);
                }
            }
        }
        return active;
    }

    @SuppressWarnings("unchecked")
    public static java.util.Map<String, Boolean> getActiveDynamicSingleUses(ServerLevel level) {
        java.util.Map<String, Boolean> active = new java.util.HashMap<>();
        for (Identifier id : DYNAMIC_ITEMS) {
            String singleUseRuleName = "ig:single_use_" + id.getNamespace() + "_" + id.getPath();
            GameRule<Boolean> rule = (GameRule<Boolean>) DynamicGameRuleManager.getDynamicRules().get(singleUseRuleName);
            if (rule != null) {
                boolean val = DynamicGameRuleManager.getBoolean(level, rule);
                if (val) {
                    active.put(id.toString(), true);
                }
            }
        }
        return active;
    }
}
