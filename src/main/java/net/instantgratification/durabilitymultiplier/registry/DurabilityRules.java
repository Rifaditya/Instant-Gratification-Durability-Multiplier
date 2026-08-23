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

    // ==================== Misc ====================
    public static GameRule<Boolean> DM_SHOW_TOOLTIP;

    // List of dynamic modded items registered during registry freeze
    public static final java.util.List<Identifier> DYNAMIC_ITEMS = new java.util.ArrayList<>();

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
        DM_PERCENT_GLOBAL = DynamicGameRuleManager.integerRule("ig:dm_percent_global", DURABILITY_MULTIPLIER, config.percentGlobal).min(0).register();
        DM_PERCENT_WEAPONS = DynamicGameRuleManager.integerRule("ig:dm_percent_weapons", DURABILITY_MULTIPLIER, config.percentWeapons).min(0).register();
        DM_PERCENT_SWORDS = DynamicGameRuleManager.integerRule("ig:dm_percent_swords", DURABILITY_MULTIPLIER, config.percentSwords).min(0).register();
        DM_PERCENT_SPEARS = DynamicGameRuleManager.integerRule("ig:dm_percent_spears", DURABILITY_MULTIPLIER, config.percentSpears).min(0).register();
        DM_PERCENT_TRIDENTS = DynamicGameRuleManager.integerRule("ig:dm_percent_tridents", DURABILITY_MULTIPLIER, config.percentTridents).min(0).register();
        DM_PERCENT_MACES = DynamicGameRuleManager.integerRule("ig:dm_percent_maces", DURABILITY_MULTIPLIER, config.percentMaces).min(0).register();
        DM_PERCENT_BOWS = DynamicGameRuleManager.integerRule("ig:dm_percent_bows", DURABILITY_MULTIPLIER, config.percentBows).min(0).register();
        DM_PERCENT_CROSSBOWS = DynamicGameRuleManager.integerRule("ig:dm_percent_crossbows", DURABILITY_MULTIPLIER, config.percentCrossbows).min(0).register();
        DM_PERCENT_TOOLS = DynamicGameRuleManager.integerRule("ig:dm_percent_tools", DURABILITY_MULTIPLIER, config.percentTools).min(0).register();
        DM_PERCENT_PICKAXES = DynamicGameRuleManager.integerRule("ig:dm_percent_pickaxes", DURABILITY_MULTIPLIER, config.percentPickaxes).min(0).register();
        DM_PERCENT_AXES = DynamicGameRuleManager.integerRule("ig:dm_percent_axes", DURABILITY_MULTIPLIER, config.percentAxes).min(0).register();
        DM_PERCENT_SHOVELS = DynamicGameRuleManager.integerRule("ig:dm_percent_shovels", DURABILITY_MULTIPLIER, config.percentShovels).min(0).register();
        DM_PERCENT_HOES = DynamicGameRuleManager.integerRule("ig:dm_percent_hoes", DURABILITY_MULTIPLIER, config.percentHoes).min(0).register();
        DM_PERCENT_SHEARS = DynamicGameRuleManager.integerRule("ig:dm_percent_shears", DURABILITY_MULTIPLIER, config.percentShears).min(0).register();
        DM_PERCENT_FISHING_RODS = DynamicGameRuleManager.integerRule("ig:dm_percent_fishing_rods", DURABILITY_MULTIPLIER, config.percentFishingRods).min(0).register();
        DM_PERCENT_BRUSHES = DynamicGameRuleManager.integerRule("ig:dm_percent_brushes", DURABILITY_MULTIPLIER, config.percentBrushes).min(0).register();
        DM_PERCENT_FLINT_AND_STEEL = DynamicGameRuleManager.integerRule("ig:dm_percent_flint_and_steel", DURABILITY_MULTIPLIER, config.percentFlintAndSteel).min(0).register();
        DM_PERCENT_ARMOR = DynamicGameRuleManager.integerRule("ig:dm_percent_armor", DURABILITY_MULTIPLIER, config.percentArmor).min(0).register();
        DM_PERCENT_HELMETS = DynamicGameRuleManager.integerRule("ig:dm_percent_helmets", DURABILITY_MULTIPLIER, config.percentHelmets).min(0).register();
        DM_PERCENT_CHESTPLATES = DynamicGameRuleManager.integerRule("ig:dm_percent_chestplates", DURABILITY_MULTIPLIER, config.percentChestplates).min(0).register();
        DM_PERCENT_LEGGINGS = DynamicGameRuleManager.integerRule("ig:dm_percent_leggings", DURABILITY_MULTIPLIER, config.percentLeggings).min(0).register();
        DM_PERCENT_BOOTS = DynamicGameRuleManager.integerRule("ig:dm_percent_boots", DURABILITY_MULTIPLIER, config.percentBoots).min(0).register();
        DM_PERCENT_ELYTRA = DynamicGameRuleManager.integerRule("ig:dm_percent_elytra", DURABILITY_MULTIPLIER, config.percentElytra).min(0).register();
        DM_PERCENT_SHIELDS = DynamicGameRuleManager.integerRule("ig:dm_percent_shields", DURABILITY_MULTIPLIER, config.percentShields).min(0).register();

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

        DM_SHOW_TOOLTIP = DynamicGameRuleManager.booleanRule("ig:dm_show_tooltip", DURABILITY_MULTIPLIER, config.showTooltip).register();
    }

    public static void registerDynamicRulesOnRegistryFreeze() {
        for (Item item : BuiltInRegistries.ITEM) {
            Identifier id = BuiltInRegistries.ITEM.getKey(item);
            if (id != null) {
                processItemRegistration(id, item);
            }
        }
    }

    private static void processItemRegistration(Identifier id, Item item) {
        if (id.getNamespace().equals("minecraft") || id.getNamespace().equals("c")) {
            return;
        }

        if (isItemDamageableAndUncategorized(id, item)) {
            registerDynamicRules(id);
            DYNAMIC_ITEMS.add(id);
        }
    }

    private static boolean isItemDamageableAndUncategorized(Identifier id, Item item) {
        if (item instanceof TridentItem ||
            item instanceof MaceItem ||
            item instanceof BowItem ||
            item instanceof CrossbowItem ||
            item instanceof ShieldItem ||
            item instanceof AxeItem ||
            item instanceof ShovelItem ||
            item instanceof HoeItem ||
            item instanceof ShearsItem ||
            item instanceof FishingRodItem ||
            item instanceof FlintAndSteelItem ||
            item instanceof BrushItem ||
            item instanceof FoodOnAStickItem) {
            return false;
        }

        try {
            java.lang.reflect.Field field = BuiltInRegistries.DATA_COMPONENT_INITIALIZERS.getClass().getDeclaredField("initializers");
            field.setAccessible(true);
            List<?> list = (List<?>) field.get(BuiltInRegistries.DATA_COMPONENT_INITIALIZERS);
            for (Object entry : list) {
                java.lang.reflect.Method keyMethod = entry.getClass().getMethod("key");
                keyMethod.setAccessible(true);
                ResourceKey<?> key = (ResourceKey<?>) keyMethod.invoke(entry);
                if (key.identifier().equals(id)) {
                    java.lang.reflect.Method initializerMethod = entry.getClass().getMethod("initializer");
                    initializerMethod.setAccessible(true);
                    Object initializer = initializerMethod.invoke(entry);
                    
                    DataComponentMap.Builder builder = DataComponentMap.builder();
                    try {
                        java.lang.reflect.Method runMethod = initializer.getClass().getMethod("run", DataComponentMap.Builder.class, net.minecraft.core.HolderLookup.Provider.class, ResourceKey.class);
                        runMethod.setAccessible(true);
                        runMethod.invoke(initializer, builder, null, key);
                    } catch (Throwable ignored) {
                    }
                    
                    DataComponentMap map = builder.build();
                    
                    if (map.has(DataComponents.TOOL) || map.has(DataComponents.EQUIPPABLE)) {
                        return false;
                    }
                    
                    if (map.has(DataComponents.MAX_DAMAGE)) {
                        Integer maxDamage = map.get(DataComponents.MAX_DAMAGE);
                        if (maxDamage != null && maxDamage > 0) {
                            return true;
                        }
                    }
                }
            }
        } catch (Throwable e) {
            DurabilityMultiplier.LOGGER.warn("Failed to check durability for item " + id + " using reflection initializers scan", e);
        }

        return false;
    }

    public static void registerDynamicRules(Identifier id) {
        DurabilityConfig config = DurabilityConfig.get();

        String infinityRuleName = "ig:infinity_" + id.getNamespace() + "_" + id.getPath();
        String percentRuleName = "ig:percent_" + id.getNamespace() + "_" + id.getPath();

        boolean defaultInfinity = config.dynamicInfinities.getOrDefault(id.toString(), false);
        int defaultPercent = config.dynamicPercentages.getOrDefault(id.toString(), 0);

        DynamicGameRuleManager.booleanRule(infinityRuleName, DURABILITY_MULTIPLIER, defaultInfinity)
            .name(DynamicGameRuleManager.generateReadableName(id.getPath()) + " Infinity")
            .description("Enable god mode for " + id)
            .register();

        DynamicGameRuleManager.integerRule(percentRuleName, DURABILITY_MULTIPLIER, defaultPercent)
            .name(DynamicGameRuleManager.generateReadableName(id.getPath()) + " Durability Percent")
            .description("Durability percentage for " + id + " (100 = vanilla, 200 = 2x, 50 = 0.5x)")
            .min(0)
            .register();
    }
}
