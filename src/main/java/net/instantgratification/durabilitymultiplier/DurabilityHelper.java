package net.instantgratification.durabilitymultiplier;

import net.instantgratification.durabilitymultiplier.network.DurabilityClientState;
import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * Core durability logic.
 *
 * <h3>Hierarchy</h3>
 * <ol>
 * <li>Tag-specific infinity → Global infinity</li>
 * <li>Tag-specific multiplier (if &gt; 0) → Global multiplier</li>
 * </ol>
 *
 * <p>Server-side methods take {@link ServerLevel}; client-side overloads
 * (suffixed {@code Client}) read from {@link DurabilityClientState}.</p>
 */
public final class DurabilityHelper {

    private DurabilityHelper() {
    }

    /** Item classification for tag-based rule lookup. */
    public enum ItemCategory {
        SWORD, TOOL, ARMOR, ELYTRA, OTHER
    }

    // ==================== Public API ====================

    /**
     * Resolve whether the item should take zero damage (God Mode).
     * Infinity priority: tag-specific (if true) → global fallback.
     */
    public static boolean isInfinite(ServerLevel level, ItemStack stack) {
        ItemCategory cat = classifyItem(stack);
        return switch (cat) {
            case SWORD -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_SWORDS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case TOOL -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_TOOLS)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case ARMOR -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_ARMOR)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case ELYTRA -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_ELYTRA)
                    || DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
            case OTHER -> DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL);
        };
    }

    /**
     * Resolve the effective multiplier.
     * Multiplier priority: tag-specific (if > 0) → global fallback.
     *
     * @return multiplier >= 1. Returns 1 if no multiplier is active.
     */
    public static int getEffectiveMultiplier(ServerLevel level, ItemStack stack) {
        ItemCategory cat = classifyItem(stack);
        int specific = switch (cat) {
            case SWORD -> DurabilityRules.getInt(level, DurabilityRules.DM_MULTIPLIER_SWORDS);
            case TOOL -> DurabilityRules.getInt(level, DurabilityRules.DM_MULTIPLIER_TOOLS);
            case ARMOR -> DurabilityRules.getInt(level, DurabilityRules.DM_MULTIPLIER_ARMOR);
            case ELYTRA -> DurabilityRules.getInt(level, DurabilityRules.DM_MULTIPLIER_ELYTRA);
            case OTHER -> 0;
        };
        if (specific > 0)
            return specific;

        int global = DurabilityRules.getInt(level, DurabilityRules.DM_MULTIPLIER_GLOBAL);
        return Math.max(global, 1);
    }

    /**
     * Reduce incoming damage amount based on the effective multiplier.
     * For a 2x multiplier, damage is halved. For 4x, quartered.
     * Uses probabilistic rounding to avoid always rounding down.
     *
     * @param originalAmount the original damage amount (pre-enchantment processing)
     * @param level          the server level
     * @param stack          the item being damaged
     * @return the reduced damage amount (0 if infinite)
     */
    public static int reduceDamage(int originalAmount, ServerLevel level, ItemStack stack) {
        if (isInfinite(level, stack))
            return 0;

        int multiplier = getEffectiveMultiplier(level, stack);
        if (multiplier <= 1)
            return originalAmount;

        // Integer division with probabilistic rounding:
        // e.g., 1 damage / 3x multiplier = 0.33 → 33% chance of 1, 67% chance of 0.
        // This ensures exact long-term durability extension.
        int base = originalAmount / multiplier;
        int remainder = originalAmount % multiplier;
        if (remainder > 0 && level.getRandom().nextInt(multiplier) < remainder) {
            base++;
        }
        return base;
    }

    /**
     * Check if the tooltip indicator GameRule is enabled.
     */
    public static boolean shouldShowTooltip(ServerLevel level) {
        return DurabilityRules.getBoolean(level, DurabilityRules.DM_SHOW_TOOLTIP);
    }

    /**
     * Get the label for the tooltip based on active multiplier/infinity.
     *
     * @return label string, or null if no modifier is active.
     */
    public static String getTooltipLabel(ServerLevel level, ItemStack stack) {
        if (isInfinite(level, stack))
            return "UNBREAKABLE";

        int multiplier = getEffectiveMultiplier(level, stack);
        if (multiplier > 1) {
            ItemCategory cat = classifyItem(stack);
            String catName = switch (cat) {
                case SWORD -> "Swords";
                case TOOL -> "Tools";
                case ARMOR -> "Armor";
                case ELYTRA -> "Elytra";
                case OTHER -> "Global";
            };
            return multiplier + "x " + catName;
        }
        return null;
    }

    // ==================== Client-Side API (synced cache) ====================

    /** Client-side infinity check using synced GameRule values. */
    public static boolean isInfiniteClient(ItemStack stack) {
        ItemCategory cat = classifyItem(stack);
        return switch (cat) {
            case SWORD -> DurabilityClientState.infinitySwords() || DurabilityClientState.infinityGlobal();
            case TOOL -> DurabilityClientState.infinityTools() || DurabilityClientState.infinityGlobal();
            case ARMOR -> DurabilityClientState.infinityArmor() || DurabilityClientState.infinityGlobal();
            case ELYTRA -> DurabilityClientState.infinityElytra() || DurabilityClientState.infinityGlobal();
            case OTHER -> DurabilityClientState.infinityGlobal();
        };
    }

    /** Client-side multiplier using synced GameRule values. */
    public static int getEffectiveMultiplierClient(ItemStack stack) {
        ItemCategory cat = classifyItem(stack);
        int specific = switch (cat) {
            case SWORD -> DurabilityClientState.multiplierSwords();
            case TOOL -> DurabilityClientState.multiplierTools();
            case ARMOR -> DurabilityClientState.multiplierArmor();
            case ELYTRA -> DurabilityClientState.multiplierElytra();
            case OTHER -> 0;
        };
        if (specific > 0)
            return specific;
        return Math.max(DurabilityClientState.multiplierGlobal(), 1);
    }

    /** Client-side tooltip visibility using synced GameRule values. */
    public static boolean shouldShowTooltipClient() {
        return DurabilityClientState.showTooltip();
    }

    /** Client-side tooltip label using synced GameRule values. */
    public static String getTooltipLabelClient(ItemStack stack) {
        if (isInfiniteClient(stack))
            return "UNBREAKABLE";
        int multiplier = getEffectiveMultiplierClient(stack);
        if (multiplier > 1) {
            ItemCategory cat = classifyItem(stack);
            String catName = switch (cat) {
                case SWORD -> "Swords";
                case TOOL -> "Tools";
                case ARMOR -> "Armor";
                case ELYTRA -> "Elytra";
                case OTHER -> "Global";
            };
            return multiplier + "x " + catName;
        }
        return null;
    }

    // ==================== Item Classification ====================

    public static ItemCategory classifyItem(ItemStack stack) {
        if (stack.is(ItemTags.SWORDS))
            return ItemCategory.SWORD;
        if (stack.is(ItemTags.AXES)
                || stack.is(ItemTags.PICKAXES)
                || stack.is(ItemTags.SHOVELS)
                || stack.is(ItemTags.HOES)
                || stack.is(ItemTags.SPEARS))
            return ItemCategory.TOOL;
        if (stack.is(ItemTags.HEAD_ARMOR)
                || stack.is(ItemTags.CHEST_ARMOR)
                || stack.is(ItemTags.LEG_ARMOR)
                || stack.is(ItemTags.FOOT_ARMOR))
            return ItemCategory.ARMOR;
        if (stack.is(Items.ELYTRA))
            return ItemCategory.ELYTRA;
        return ItemCategory.OTHER;
    }
}
