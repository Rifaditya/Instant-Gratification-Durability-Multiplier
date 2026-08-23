// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier;

import net.instantgratification.durabilitymultiplier.config.DurabilityConfig;
import net.instantgratification.durabilitymultiplier.network.DurabilityClientState;
import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.gamerules.GameRule;
import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Core durability logic.
 *
 * <h3>Hierarchy</h3>
 * <ol>
 * <li>Tag-specific infinity → Global infinity</li>
 * <li>Tag-specific percentage (if &gt; 0) → Weapons global (for weapons) → Global percentage</li>
 * </ol>
 *
 * <p>
 * Server-side methods take {@link ServerLevel}; client-side overloads
 * (suffixed {@code Client}) read from {@link DurabilityClientState}.
 * </p>
 */
public final class DurabilityHelper {

    private DurabilityHelper() {
    }

    /** Item classification for tag-based rule lookup. */
    public enum ItemCategory {
        // Weapons
        SWORD, BOW, CROSSBOW, TRIDENT, SPEAR, MACE, SHIELD, WEAPON_GLOBAL,
        // Tools & Utility
        PICKAXE, AXE, SHOVEL, HOE, SHEARS, FISHING_ROD, BRUSH, FLINT_AND_STEEL, TOOL_GLOBAL,
        // Armor
        HELMET, CHESTPLATE, LEGGINGS, BOOTS, ARMOR_GLOBAL,
        // Other
        ELYTRA, OTHER
    }

    // ==================== Public API ====================

    /**
     * Resolve whether the item should take zero damage (God Mode).
     * Infinity priority: tag-specific (if true) → global fallback.
     */
    public static boolean isInfinite(ServerLevel level, ItemStack stack) {
        return isInfinite(level, stack, classifyItem(stack));
    }

    /**
     * Resolve whether the item should take zero damage (God Mode) with pre-resolved category.
     */
    public static boolean isInfinite(ServerLevel level, ItemStack stack, ItemCategory cat) {
        Identifier id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (id != null && !id.getNamespace().equals("minecraft") && !id.getNamespace().equals("c")) {
            String infinityRuleName = "ig:infinity_" + id.getNamespace() + "_" + id.getPath();
            @SuppressWarnings("unchecked")
            GameRule<Boolean> dynamicInfinityRule = (GameRule<Boolean>) DynamicGameRuleManager.getDynamicRules().get(infinityRuleName);
            if (dynamicInfinityRule == null) {
                boolean defInf = DurabilityConfig.get().getForcedInfinity(id.toString());
                dynamicInfinityRule = DynamicGameRuleManager.booleanRule(infinityRuleName, DurabilityRules.DURABILITY_MULTIPLIER, defInf).register();
                if (dynamicInfinityRule != null && !DurabilityRules.DYNAMIC_ITEMS.contains(id)) {
                    DurabilityRules.DYNAMIC_ITEMS.add(id);
                }
            }
            if (dynamicInfinityRule != null && DynamicGameRuleManager.getBoolean(level, dynamicInfinityRule)) {
                return true;
            }
        }

        return switch (cat) {
            case SWORD -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_SWORDS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_WEAPONS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case SPEAR -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_SPEARS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_WEAPONS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case TRIDENT -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_TRIDENTS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_WEAPONS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case MACE -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_MACES)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_WEAPONS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case BOW -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_BOWS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_WEAPONS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case CROSSBOW -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_CROSSBOWS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_WEAPONS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case SHIELD -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_SHIELDS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case WEAPON_GLOBAL -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_WEAPONS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);

            case PICKAXE -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_PICKAXES)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case AXE -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_AXES)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case SHOVEL -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_SHOVELS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case HOE -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_HOES)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case SHEARS -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_SHEARS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case FISHING_ROD -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_FISHING_RODS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case BRUSH -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_BRUSHES)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case FLINT_AND_STEEL -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_FLINT_AND_STEEL)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case TOOL_GLOBAL -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);

            case HELMET -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_HELMETS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_ARMOR)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case CHESTPLATE -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_CHESTPLATES)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_ARMOR)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case LEGGINGS -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_LEGGINGS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_ARMOR)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case BOOTS -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_BOOTS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_ARMOR)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case ARMOR_GLOBAL -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_ARMOR)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);

            case ELYTRA -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_ELYTRA)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case OTHER -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
        };
    }

    /**
     * Check if single-use (glass mode) is active for the given item stack.
     */
    public static boolean isSingleUse(ServerLevel level, ItemStack stack) {
        return isSingleUse(level, stack, classifyItem(stack));
    }

    /**
     * Check if single-use (glass mode) is active with pre-resolved category.
     */
    public static boolean isSingleUse(ServerLevel level, ItemStack stack, ItemCategory cat) {
        if (getEffectivePercent(level, stack, cat) <= -1) {
            return true;
        }

        Identifier id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (id != null && !id.getNamespace().equals("minecraft") && !id.getNamespace().equals("c")) {
            String ruleName = "ig:single_use_" + id.getNamespace() + "_" + id.getPath();
            @SuppressWarnings("unchecked")
            GameRule<Boolean> dynamicRule = (GameRule<Boolean>) DynamicGameRuleManager.getDynamicRules().get(ruleName);
            if (dynamicRule == null) {
                boolean defSingleUse = DurabilityConfig.get().getForcedSingleUse(id.toString());
                dynamicRule = DynamicGameRuleManager.booleanRule(ruleName, DurabilityRules.DURABILITY_MULTIPLIER, defSingleUse).register();
                if (dynamicRule != null && !DurabilityRules.DYNAMIC_ITEMS.contains(id)) {
                    DurabilityRules.DYNAMIC_ITEMS.add(id);
                }
            }
            if (dynamicRule != null && DynamicGameRuleManager.getBoolean(level, dynamicRule)) {
                return true;
            }
        }

        return switch (cat) {
            case SWORD -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_SWORDS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_WEAPONS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case SPEAR -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_SPEARS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_WEAPONS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case TRIDENT -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_TRIDENTS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_WEAPONS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case MACE -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_MACES)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_WEAPONS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case BOW -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_BOWS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_WEAPONS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case CROSSBOW -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_CROSSBOWS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_CROSSBOWS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case SHIELD -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_SHIELDS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case WEAPON_GLOBAL -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_WEAPONS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);

            case PICKAXE -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_PICKAXES)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case AXE -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_AXES)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case SHOVEL -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_SHOVELS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case HOE -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_HOES)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case SHEARS -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_SHEARS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case FISHING_ROD -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_FISHING_RODS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case BRUSH -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_BRUSHES)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case FLINT_AND_STEEL -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_FLINT_AND_STEEL)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case TOOL_GLOBAL -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);

            case HELMET -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_HELMETS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_ARMOR)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case CHESTPLATE -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_CHESTPLATES)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_ARMOR)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case LEGGINGS -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_LEGGINGS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_ARMOR)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case BOOTS -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_BOOTS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_ARMOR)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case ARMOR_GLOBAL -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_ARMOR)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);

            case ELYTRA -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_ELYTRA)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
            case OTHER -> DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL);
        };
    }

    /**
     * Resolve the effective durability percentage.
     * Priority: individual per-item override (if != 0) → tag-specific (if != 0) → parent category fallback → weapons fallback (if weapon) → global fallback → 100%.
     * Values <= -1 represent Single-Use (Glass Mode).
     *
     * @return durability percentage (e.g. 100 = 100% vanilla, 200 = 200% double durability, 50 = 50% half, -1 = single-use).
     */
    public static int getEffectivePercent(ServerLevel level, ItemStack stack) {
        return getEffectivePercent(level, stack, classifyItem(stack));
    }

    /**
     * Resolve the effective durability percentage with pre-resolved category.
     */
    public static int getEffectivePercent(ServerLevel level, ItemStack stack, ItemCategory cat) {
        Identifier id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (id != null && !id.getNamespace().equals("minecraft") && !id.getNamespace().equals("c")) {
            String ruleName = "ig:percent_" + id.getNamespace() + "_" + id.getPath();
            @SuppressWarnings("unchecked")
            GameRule<Integer> dynamicRule = (GameRule<Integer>) DynamicGameRuleManager.getDynamicRules().get(ruleName);
            if (dynamicRule == null) {
                int defPercent = DurabilityConfig.get().getForcedPercent(id.toString());
                dynamicRule = DynamicGameRuleManager.integerRule(ruleName,
                        DurabilityRules.DURABILITY_MULTIPLIER, defPercent).min(-1).register();
                if (dynamicRule != null && !DurabilityRules.DYNAMIC_ITEMS.contains(id)) {
                    DurabilityRules.DYNAMIC_ITEMS.add(id);
                }
            }
            if (dynamicRule != null) {
                int dynamicVal = DynamicGameRuleManager.getInt(level, dynamicRule);
                if (dynamicVal != 0) {
                    return dynamicVal < 0 ? -1 : dynamicVal;
                }
            }
        }

        int specific = switch (cat) {
            case SWORD -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_SWORDS);
            case SPEAR -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_SPEARS);
            case TRIDENT -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_TRIDENTS);
            case MACE -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_MACES);
            case BOW -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_BOWS);
            case CROSSBOW -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_CROSSBOWS);
            case SHIELD -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_SHIELDS);
            case WEAPON_GLOBAL -> 0;

            case PICKAXE -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_PICKAXES);
            case AXE -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_AXES);
            case SHOVEL -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_SHOVELS);
            case HOE -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_HOES);
            case SHEARS -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_SHEARS);
            case FISHING_ROD -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_FISHING_RODS);
            case BRUSH -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_BRUSHES);
            case FLINT_AND_STEEL -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_FLINT_AND_STEEL);
            case TOOL_GLOBAL -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_TOOLS);

            case HELMET -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_HELMETS);
            case CHESTPLATE -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_CHESTPLATES);
            case LEGGINGS -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_LEGGINGS);
            case BOOTS -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_BOOTS);
            case ARMOR_GLOBAL -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_ARMOR);

            case ELYTRA -> DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_ELYTRA);
            case OTHER -> 0;
        };
        if (specific != 0)
            return specific < 0 ? -1 : specific;

        // Tool parent fallback
        if (cat == ItemCategory.PICKAXE || cat == ItemCategory.AXE || cat == ItemCategory.SHOVEL ||
                cat == ItemCategory.HOE || cat == ItemCategory.SHEARS || cat == ItemCategory.FISHING_ROD ||
                cat == ItemCategory.BRUSH || cat == ItemCategory.FLINT_AND_STEEL || cat == ItemCategory.TOOL_GLOBAL) {
            int toolGlobal = DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_TOOLS);
            if (toolGlobal != 0)
                return toolGlobal < 0 ? -1 : toolGlobal;
        }

        // Armor parent fallback
        if (cat == ItemCategory.HELMET || cat == ItemCategory.CHESTPLATE ||
                cat == ItemCategory.LEGGINGS || cat == ItemCategory.BOOTS || cat == ItemCategory.ARMOR_GLOBAL) {
            int armorGlobal = DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_ARMOR);
            if (armorGlobal != 0)
                return armorGlobal < 0 ? -1 : armorGlobal;
        }

        // Weapons parent fallback
        if (cat == ItemCategory.SWORD || cat == ItemCategory.SPEAR || cat == ItemCategory.TRIDENT ||
                cat == ItemCategory.MACE || cat == ItemCategory.BOW || cat == ItemCategory.CROSSBOW ||
                cat == ItemCategory.WEAPON_GLOBAL) {
            int weaponGlobal = DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_WEAPONS);
            if (weaponGlobal != 0)
                return weaponGlobal < 0 ? -1 : weaponGlobal;
        }

        int global = DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_GLOBAL);
        return global != 0 ? (global < 0 ? -1 : global) : 100;
    }

    /**
     * Legacy helper method returning multiplier factor (percent / 100, clamped >= 1).
     */
    public static int getEffectiveMultiplier(ServerLevel level, ItemStack stack) {
        return Math.max(getEffectivePercent(level, stack) / 100, 1);
    }

    /**
     * Scale incoming durability damage amount based on effective durability percentage.
     * Supports both durability boosts (percent > 100) and durability reductions (percent < 100).
     */
    public static int calculateScaledDamage(int originalAmount, int percent, RandomSource random) {
        if (originalAmount <= 0)
            return 0;
        if (percent <= 0 || percent == 100)
            return originalAmount;

        int totalDamageUnits = originalAmount * 100;
        int baseDamage = totalDamageUnits / percent;
        int remainder = totalDamageUnits % percent;
        if (remainder > 0 && random.nextInt(percent) < remainder) {
            baseDamage++;
        }
        return baseDamage;
    }

    public static int reduceDamage(int originalAmount, ServerLevel level, ItemStack stack) {
        ItemCategory cat = classifyItem(stack);
        if (isInfinite(level, stack, cat))
            return 0;
        if (isSingleUse(level, stack, cat))
            return Math.max(1, stack.getMaxDamage() - stack.getDamageValue());

        int percent = getEffectivePercent(level, stack, cat);
        return calculateScaledDamage(originalAmount, percent, level.getRandom());
    }

    /**
     * Check if the tooltip indicator GameRule is enabled.
     */
    public static boolean shouldShowTooltip(ServerLevel level) {
        return DurabilityRules.getBoolean(level, DurabilityRules.DM_SHOW_TOOLTIP);
    }

    /**
     * Get the label for the tooltip based on active percentage/infinity/single-use.
     */
    public static String getTooltipLabel(ServerLevel level, ItemStack stack) {
        ItemCategory cat = classifyItem(stack);
        if (isInfinite(level, stack, cat))
            return "UNBREAKABLE";
        if (isSingleUse(level, stack, cat))
            return "SINGLE-USE";

        int percent = getEffectivePercent(level, stack, cat);
        return formatTooltip(percent, cat, stack.getHoverName().getString(), DurabilityConfig.get().tooltipFormat);
    }

    // ==================== Client-Side API (synced cache) ====================

    /** Client-side infinity check using synced GameRule values. */
    public static boolean isInfiniteClient(ItemStack stack) {
        Identifier id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (id != null) {
            if (DurabilityClientState.getDynamicInfinity(id.toString()) || DurabilityConfig.get().getForcedInfinity(id.toString())) {
                return true;
            }
        }

        ItemCategory cat = classifyItem(stack);
        return switch (cat) {
            case SWORD -> DurabilityClientState.infinitySwords() || DurabilityClientState.infinityWeapons() || DurabilityClientState.infinityGlobal();
            case SPEAR -> DurabilityClientState.infinitySpears() || DurabilityClientState.infinityWeapons() || DurabilityClientState.infinityGlobal();
            case TRIDENT -> DurabilityClientState.infinityTridents() || DurabilityClientState.infinityWeapons() || DurabilityClientState.infinityGlobal();
            case MACE -> DurabilityClientState.infinityMaces() || DurabilityClientState.infinityWeapons() || DurabilityClientState.infinityGlobal();
            case BOW -> DurabilityClientState.infinityBows() || DurabilityClientState.infinityWeapons() || DurabilityClientState.infinityGlobal();
            case CROSSBOW -> DurabilityClientState.infinityCrossbows() || DurabilityClientState.infinityWeapons() || DurabilityClientState.infinityGlobal();
            case SHIELD -> DurabilityClientState.infinityShields() || DurabilityClientState.infinityGlobal();
            case WEAPON_GLOBAL -> DurabilityClientState.infinityWeapons() || DurabilityClientState.infinityGlobal();

            case PICKAXE -> DurabilityClientState.infinityPickaxes() || DurabilityClientState.infinityTools() || DurabilityClientState.infinityGlobal();
            case AXE -> DurabilityClientState.infinityAxes() || DurabilityClientState.infinityTools() || DurabilityClientState.infinityGlobal();
            case SHOVEL -> DurabilityClientState.infinityShovels() || DurabilityClientState.infinityTools() || DurabilityClientState.infinityGlobal();
            case HOE -> DurabilityClientState.infinityHoes() || DurabilityClientState.infinityTools() || DurabilityClientState.infinityGlobal();
            case SHEARS -> DurabilityClientState.infinityShears() || DurabilityClientState.infinityTools() || DurabilityClientState.infinityGlobal();
            case FISHING_ROD -> DurabilityClientState.infinityFishingRods() || DurabilityClientState.infinityTools() || DurabilityClientState.infinityGlobal();
            case BRUSH -> DurabilityClientState.infinityBrushes() || DurabilityClientState.infinityTools() || DurabilityClientState.infinityGlobal();
            case FLINT_AND_STEEL -> DurabilityClientState.infinityFlintAndSteel() || DurabilityClientState.infinityTools() || DurabilityClientState.infinityGlobal();
            case TOOL_GLOBAL -> DurabilityClientState.infinityTools() || DurabilityClientState.infinityGlobal();

            case HELMET -> DurabilityClientState.infinityHelmets() || DurabilityClientState.infinityArmor() || DurabilityClientState.infinityGlobal();
            case CHESTPLATE -> DurabilityClientState.infinityChestplates() || DurabilityClientState.infinityArmor() || DurabilityClientState.infinityGlobal();
            case LEGGINGS -> DurabilityClientState.infinityLeggings() || DurabilityClientState.infinityArmor() || DurabilityClientState.infinityGlobal();
            case BOOTS -> DurabilityClientState.infinityBoots() || DurabilityClientState.infinityArmor() || DurabilityClientState.infinityGlobal();
            case ARMOR_GLOBAL -> DurabilityClientState.infinityArmor() || DurabilityClientState.infinityGlobal();

            case ELYTRA -> DurabilityClientState.infinityElytra() || DurabilityClientState.infinityGlobal();
            case OTHER -> DurabilityClientState.infinityGlobal();
        };
    }

    /** Client-side single-use check using synced GameRule values. */
    public static boolean isSingleUseClient(ItemStack stack) {
        if (getEffectivePercentClient(stack) <= -1) {
            return true;
        }

        Identifier id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (id != null) {
            if (DurabilityClientState.getDynamicSingleUse(id.toString()) || DurabilityConfig.get().getForcedSingleUse(id.toString())) {
                return true;
            }
        }

        ItemCategory cat = classifyItem(stack);
        return switch (cat) {
            case SWORD -> DurabilityClientState.singleUseSwords() || DurabilityClientState.singleUseWeapons() || DurabilityClientState.singleUseGlobal();
            case SPEAR -> DurabilityClientState.singleUseSpears() || DurabilityClientState.singleUseWeapons() || DurabilityClientState.singleUseGlobal();
            case TRIDENT -> DurabilityClientState.singleUseTridents() || DurabilityClientState.singleUseWeapons() || DurabilityClientState.singleUseGlobal();
            case MACE -> DurabilityClientState.singleUseMaces() || DurabilityClientState.singleUseWeapons() || DurabilityClientState.singleUseGlobal();
            case BOW -> DurabilityClientState.singleUseBows() || DurabilityClientState.singleUseWeapons() || DurabilityClientState.singleUseGlobal();
            case CROSSBOW -> DurabilityClientState.singleUseCrossbows() || DurabilityClientState.singleUseWeapons() || DurabilityClientState.singleUseGlobal();
            case SHIELD -> DurabilityClientState.singleUseShields() || DurabilityClientState.singleUseGlobal();
            case WEAPON_GLOBAL -> DurabilityClientState.singleUseWeapons() || DurabilityClientState.singleUseGlobal();

            case PICKAXE -> DurabilityClientState.singleUsePickaxes() || DurabilityClientState.singleUseTools() || DurabilityClientState.singleUseGlobal();
            case AXE -> DurabilityClientState.singleUseAxes() || DurabilityClientState.singleUseTools() || DurabilityClientState.singleUseGlobal();
            case SHOVEL -> DurabilityClientState.singleUseShovels() || DurabilityClientState.singleUseTools() || DurabilityClientState.singleUseGlobal();
            case HOE -> DurabilityClientState.singleUseHoes() || DurabilityClientState.singleUseTools() || DurabilityClientState.singleUseGlobal();
            case SHEARS -> DurabilityClientState.singleUseShears() || DurabilityClientState.singleUseTools() || DurabilityClientState.singleUseGlobal();
            case FISHING_ROD -> DurabilityClientState.singleUseFishingRods() || DurabilityClientState.singleUseTools() || DurabilityClientState.singleUseGlobal();
            case BRUSH -> DurabilityClientState.singleUseBrushes() || DurabilityClientState.singleUseTools() || DurabilityClientState.singleUseGlobal();
            case FLINT_AND_STEEL -> DurabilityClientState.singleUseFlintAndSteel() || DurabilityClientState.singleUseTools() || DurabilityClientState.singleUseGlobal();
            case TOOL_GLOBAL -> DurabilityClientState.singleUseTools() || DurabilityClientState.singleUseGlobal();

            case HELMET -> DurabilityClientState.singleUseHelmets() || DurabilityClientState.singleUseArmor() || DurabilityClientState.singleUseGlobal();
            case CHESTPLATE -> DurabilityClientState.singleUseChestplates() || DurabilityClientState.singleUseArmor() || DurabilityClientState.singleUseGlobal();
            case LEGGINGS -> DurabilityClientState.singleUseLeggings() || DurabilityClientState.singleUseArmor() || DurabilityClientState.singleUseGlobal();
            case BOOTS -> DurabilityClientState.singleUseBoots() || DurabilityClientState.singleUseArmor() || DurabilityClientState.singleUseGlobal();
            case ARMOR_GLOBAL -> DurabilityClientState.singleUseArmor() || DurabilityClientState.singleUseGlobal();

            case ELYTRA -> DurabilityClientState.singleUseElytra() || DurabilityClientState.singleUseGlobal();
            case OTHER -> DurabilityClientState.singleUseGlobal();
        };
    }

    /** Client-side percentage using synced GameRule values. */
    public static int getEffectivePercentClient(ItemStack stack) {
        Identifier id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (id != null) {
            int dynamicVal = DurabilityClientState.getDynamicPercent(id.toString());
            if (dynamicVal != 0) {
                return dynamicVal < 0 ? -1 : dynamicVal;
            }
            int forcedVal = DurabilityConfig.get().getForcedPercent(id.toString());
            if (forcedVal != 0) {
                return forcedVal < 0 ? -1 : forcedVal;
            }
        }

        ItemCategory cat = classifyItem(stack);
        int specific = switch (cat) {
            case SWORD -> DurabilityClientState.percentSwords();
            case SPEAR -> DurabilityClientState.percentSpears();
            case TRIDENT -> DurabilityClientState.percentTridents();
            case MACE -> DurabilityClientState.percentMaces();
            case BOW -> DurabilityClientState.percentBows();
            case CROSSBOW -> DurabilityClientState.percentCrossbows();
            case SHIELD -> DurabilityClientState.percentShields();
            case WEAPON_GLOBAL -> 0;

            case PICKAXE -> DurabilityClientState.percentPickaxes();
            case AXE -> DurabilityClientState.percentAxes();
            case SHOVEL -> DurabilityClientState.percentShovels();
            case HOE -> DurabilityClientState.percentHoes();
            case SHEARS -> DurabilityClientState.percentShears();
            case FISHING_ROD -> DurabilityClientState.percentFishingRods();
            case BRUSH -> DurabilityClientState.percentBrushes();
            case FLINT_AND_STEEL -> DurabilityClientState.percentFlintAndSteel();
            case TOOL_GLOBAL -> DurabilityClientState.percentTools();

            case HELMET -> DurabilityClientState.percentHelmets();
            case CHESTPLATE -> DurabilityClientState.percentChestplates();
            case LEGGINGS -> DurabilityClientState.percentLeggings();
            case BOOTS -> DurabilityClientState.percentBoots();
            case ARMOR_GLOBAL -> DurabilityClientState.percentArmor();

            case ELYTRA -> DurabilityClientState.percentElytra();
            default -> 0;
        };
        if (specific != 0)
            return specific < 0 ? -1 : specific;

        // Tool parent fallback
        if (cat == ItemCategory.PICKAXE || cat == ItemCategory.AXE || cat == ItemCategory.SHOVEL ||
                cat == ItemCategory.HOE || cat == ItemCategory.SHEARS || cat == ItemCategory.FISHING_ROD ||
                cat == ItemCategory.BRUSH || cat == ItemCategory.FLINT_AND_STEEL || cat == ItemCategory.TOOL_GLOBAL) {
            int toolGlobal = DurabilityClientState.percentTools();
            if (toolGlobal != 0)
                return toolGlobal < 0 ? -1 : toolGlobal;
        }

        // Armor parent fallback
        if (cat == ItemCategory.HELMET || cat == ItemCategory.CHESTPLATE ||
                cat == ItemCategory.LEGGINGS || cat == ItemCategory.BOOTS || cat == ItemCategory.ARMOR_GLOBAL) {
            int armorGlobal = DurabilityClientState.percentArmor();
            if (armorGlobal != 0)
                return armorGlobal < 0 ? -1 : armorGlobal;
        }

        // Weapons parent fallback
        if (cat == ItemCategory.SWORD || cat == ItemCategory.SPEAR || cat == ItemCategory.TRIDENT ||
                cat == ItemCategory.MACE || cat == ItemCategory.BOW || cat == ItemCategory.CROSSBOW ||
                cat == ItemCategory.WEAPON_GLOBAL) {
            int weaponGlobal = DurabilityClientState.percentWeapons();
            if (weaponGlobal != 0)
                return weaponGlobal < 0 ? -1 : weaponGlobal;
        }

        int global = DurabilityClientState.percentGlobal();
        return global != 0 ? (global < 0 ? -1 : global) : 100;
    }

    /** Client-side multiplier using synced GameRule values (legacy helper). */
    public static int getEffectiveMultiplierClient(ItemStack stack) {
        return Math.max(getEffectivePercentClient(stack) / 100, 1);
    }

    /** Client-side tooltip visibility using synced GameRule values. */
    public static boolean shouldShowTooltipClient() {
        return DurabilityClientState.showTooltip();
    }

    /** Client-side tooltip label using synced GameRule values. */
    public static String getTooltipLabelClient(ItemStack stack) {
        if (isInfiniteClient(stack))
            return "UNBREAKABLE";
        if (isSingleUseClient(stack))
            return "SINGLE-USE";
        ItemCategory cat = classifyItem(stack);
        int percent = getEffectivePercentClient(stack);
        return formatTooltip(percent, cat, stack.getHoverName().getString(), DurabilityConfig.get().tooltipFormat);
    }

    /**
     * Formats the tooltip text according to the selected {@link DurabilityConfig.TooltipFormat}.
     */
    public static String formatTooltip(int percent, ItemCategory cat, String itemName, DurabilityConfig.TooltipFormat format) {
        if (percent <= 0 || percent == 100)
            return null;

        String targetName = (cat == ItemCategory.OTHER) ? itemName : switch (cat) {
            case SWORD -> "Swords";
            case SPEAR -> "Spears";
            case TRIDENT -> "Tridents";
            case MACE -> "Maces";
            case BOW -> "Bows";
            case CROSSBOW -> "Crossbows";
            case SHIELD -> "Shields";
            case WEAPON_GLOBAL -> "Weapons";

            case PICKAXE -> "Pickaxes";
            case AXE -> "Axes";
            case SHOVEL -> "Shovels";
            case HOE -> "Hoes";
            case SHEARS -> "Shears";
            case FISHING_ROD -> "Fishing Rods";
            case BRUSH -> "Brushes";
            case FLINT_AND_STEEL -> "Flint and Steel";
            case TOOL_GLOBAL -> "Tools";

            case HELMET -> "Helmets";
            case CHESTPLATE -> "Chestplates";
            case LEGGINGS -> "Leggings";
            case BOOTS -> "Boots";
            case ARMOR_GLOBAL -> "Armor";

            case ELYTRA -> "Elytra";
            default -> "Items";
        };

        if (format == null) {
            format = DurabilityConfig.TooltipFormat.ADAPTIVE;
        }

        return switch (format) {
            case PERCENTAGE -> percent + "% " + targetName + " Durability";
            case MULTIPLIER -> formatMultiplierString(percent) + " " + targetName + " Durability";
            case ADAPTIVE -> {
                if (percent > 100 && percent % 100 == 0) {
                    yield (percent / 100) + "x " + targetName + " Durability";
                } else {
                    yield percent + "% " + targetName + " Durability";
                }
            }
        };
    }

    private static String formatMultiplierString(int percent) {
        if (percent % 100 == 0) {
            return (percent / 100) + "x";
        }
        return new java.math.BigDecimal(percent).divide(new java.math.BigDecimal(100)).stripTrailingZeros().toPlainString() + "x";
    }

    // ==================== Item Classification ====================

    // Conventional & Fabric Tags (#c:*)
    private static final TagKey<Item> C_SWORDS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "swords"));
    private static final TagKey<Item> C_MELEE_WEAPONS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "melee_weapons"));
    private static final TagKey<Item> C_WEAPONS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "weapons"));
    private static final TagKey<Item> C_SPEARS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "spears"));
    private static final TagKey<Item> C_TRIDENTS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "tridents"));
    private static final TagKey<Item> C_MACES = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "maces"));
    private static final TagKey<Item> C_BOWS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "bows"));
    private static final TagKey<Item> C_CROSSBOWS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "crossbows"));
    private static final TagKey<Item> C_SHIELDS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "shields"));
    private static final TagKey<Item> C_TOOLS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "tools"));
    private static final TagKey<Item> C_PICKAXES = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "pickaxes"));
    private static final TagKey<Item> C_AXES = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "axes"));
    private static final TagKey<Item> C_SHOVELS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "shovels"));
    private static final TagKey<Item> C_HOES = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "hoes"));
    private static final TagKey<Item> C_SHEARS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "shears"));
    private static final TagKey<Item> C_MINING_TOOL = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "mining_tool"));
    private static final TagKey<Item> C_ARMORS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "armors"));
    private static final TagKey<Item> C_HELMETS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "helmets"));
    private static final TagKey<Item> C_CHESTPLATES = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "chestplates"));
    private static final TagKey<Item> C_LEGGINGS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "leggings"));
    private static final TagKey<Item> C_BOOTS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "boots"));

    private static final Map<Item, ItemCategory> CATEGORY_CACHE = new ConcurrentHashMap<>();

    /**
     * Invalidate the classification cache (e.g. on datapack tag reload).
     */
    public static void clearCategoryCache() {
        CATEGORY_CACHE.clear();
    }

    public static ItemCategory classifyItem(ItemStack stack) {
        return CATEGORY_CACHE.computeIfAbsent(stack.getItem(), item -> classifyItemRaw(stack));
    }

    private static ItemCategory classifyItemRaw(ItemStack stack) {
        Identifier id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        String path = (id != null) ? id.getPath().toLowerCase(Locale.ROOT) : "";

        // 1. Crossbows
        if (stack.getItem() instanceof CrossbowItem || stack.is(Items.CROSSBOW)
                || stack.is(C_CROSSBOWS) || path.contains("crossbow")) {
            return ItemCategory.CROSSBOW;
        }

        // 2. Bows (excluding crossbows)
        if (stack.getItem() instanceof BowItem || stack.is(Items.BOW)
                || stack.is(C_BOWS)
                || (path.contains("bow") && !path.contains("crossbow") && !path.contains("bowl"))) {
            return ItemCategory.BOW;
        }

        // 3. Spears
        if (stack.is(ItemTags.SPEARS) || stack.is(C_SPEARS)
                || path.contains("spear") || path.contains("pike")
                || path.contains("halberd") || path.contains("lance")) {
            return ItemCategory.SPEAR;
        }

        // 4. Tridents
        if (stack.getItem() instanceof TridentItem || stack.is(Items.TRIDENT)
                || stack.is(C_TRIDENTS) || path.contains("trident")) {
            return ItemCategory.TRIDENT;
        }

        // 5. Maces
        if (stack.getItem() instanceof MaceItem || stack.is(Items.MACE)
                || stack.is(C_MACES) || path.contains("mace") || path.contains("warhammer")) {
            return ItemCategory.MACE;
        }

        // 6. Shields
        if (stack.getItem() instanceof ShieldItem || stack.is(Items.SHIELD)
                || stack.is(C_SHIELDS) || path.contains("shield")) {
            return ItemCategory.SHIELD;
        }

        // 7. Swords
        if (stack.is(ItemTags.SWORDS) || stack.is(C_SWORDS)
                || path.endsWith("_sword") || path.contains("sword")
                || path.contains("katana") || path.contains("saber") || path.contains("sabre")
                || path.contains("blade") || path.contains("dagger") || path.contains("rapier")
                || path.contains("claymore") || path.contains("cutlass") || path.contains("glaive")
                || path.contains("scythe")) {
            return ItemCategory.SWORD;
        }

        // 8. Elytra / Glider
        if (stack.is(Items.ELYTRA) || stack.has(DataComponents.GLIDER)
                || path.contains("elytra") || path.contains("glider") || path.contains("wings")) {
            return ItemCategory.ELYTRA;
        }

        // 9. Granular Armor
        Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        EquipmentSlot slot = (equippable != null) ? equippable.slot() : null;

        if (stack.is(ItemTags.HEAD_ARMOR) || stack.is(C_HELMETS) || slot == EquipmentSlot.HEAD
                || path.contains("helmet") || path.contains("crown") || path.contains("cap") || path.contains("hood") || path.contains("mask")) {
            return ItemCategory.HELMET;
        }
        if (stack.is(ItemTags.CHEST_ARMOR) || stack.is(C_CHESTPLATES) || slot == EquipmentSlot.CHEST
                || path.contains("chestplate") || path.contains("tunic") || path.contains("cuirass") || path.contains("robe")) {
            return ItemCategory.CHESTPLATE;
        }
        if (stack.is(ItemTags.LEG_ARMOR) || stack.is(C_LEGGINGS) || slot == EquipmentSlot.LEGS
                || path.contains("leggings") || path.contains("pants") || path.contains("greaves")) {
            return ItemCategory.LEGGINGS;
        }
        if (stack.is(ItemTags.FOOT_ARMOR) || stack.is(C_BOOTS) || slot == EquipmentSlot.FEET
                || path.contains("boots") || path.contains("shoes") || path.contains("sabatons")) {
            return ItemCategory.BOOTS;
        }
        if (stack.is(C_ARMORS) || (slot != null && (slot == EquipmentSlot.HEAD || slot == EquipmentSlot.CHEST || slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET))
                || path.contains("armor")) {
            return ItemCategory.ARMOR_GLOBAL;
        }

        // 10. Granular Tools
        if (stack.is(ItemTags.PICKAXES) || stack.is(C_PICKAXES)
                || path.contains("pickaxe") || path.contains("mattock") || path.contains("drill")) {
            return ItemCategory.PICKAXE;
        }
        if (stack.is(ItemTags.AXES) || stack.is(C_AXES) || stack.getItem() instanceof AxeItem
                || (path.contains("axe") && !path.contains("pickaxe")) || path.contains("hatchet") || path.contains("saw")) {
            return ItemCategory.AXE;
        }
        if (stack.is(ItemTags.SHOVELS) || stack.is(C_SHOVELS) || stack.getItem() instanceof ShovelItem
                || path.contains("shovel") || path.contains("spade")) {
            return ItemCategory.SHOVEL;
        }
        if (stack.is(ItemTags.HOES) || stack.is(C_HOES) || stack.getItem() instanceof HoeItem
                || path.contains("hoe") || path.contains("sickle")) {
            return ItemCategory.HOE;
        }
        if (stack.is(Items.SHEARS) || stack.is(C_SHEARS) || stack.getItem() instanceof ShearsItem
                || path.contains("shears")) {
            return ItemCategory.SHEARS;
        }
        if (stack.is(Items.FISHING_ROD) || stack.getItem() instanceof FishingRodItem
                || path.contains("fishing_rod")) {
            return ItemCategory.FISHING_ROD;
        }
        if (stack.is(Items.BRUSH) || stack.getItem() instanceof BrushItem
                || path.contains("brush")) {
            return ItemCategory.BRUSH;
        }
        if (stack.is(Items.FLINT_AND_STEEL) || stack.getItem() instanceof FlintAndSteelItem
                || path.contains("flint_and_steel")) {
            return ItemCategory.FLINT_AND_STEEL;
        }
        if (stack.is(C_TOOLS) || stack.is(C_MINING_TOOL)
                || stack.is(Items.CARROT_ON_A_STICK) || stack.is(Items.WARPED_FUNGUS_ON_A_STICK)
                || stack.getItem() instanceof FoodOnAStickItem || stack.has(DataComponents.TOOL)
                || path.contains("wrench") || path.contains("tool")) {
            return ItemCategory.TOOL_GLOBAL;
        }

        // 11. General Weapons Fallback
        if (stack.is(C_MELEE_WEAPONS) || stack.is(C_WEAPONS)
                || stack.has(DataComponents.WEAPON)
                || path.contains("weapon")) {
            return ItemCategory.WEAPON_GLOBAL;
        }

        return ItemCategory.OTHER;
    }
}
