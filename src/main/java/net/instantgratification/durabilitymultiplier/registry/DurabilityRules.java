package net.instantgratification.durabilitymultiplier.registry;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;

public class DurabilityRules {

    public static final GameRuleCategory DURABILITY_MULTIPLIER = GameRuleCategory
            .register(Identifier.parse("durability-multiplier:durability_multiplier"));

    // ==================== Multipliers ====================
    public static GameRule<Integer> DM_MULTIPLIER_GLOBAL;
    public static GameRule<Integer> DM_MULTIPLIER_SWORDS;
    public static GameRule<Integer> DM_MULTIPLIER_TOOLS;
    public static GameRule<Integer> DM_MULTIPLIER_ARMOR;
    public static GameRule<Integer> DM_MULTIPLIER_ELYTRA;

    // ==================== Infinity (God Mode) ====================
    public static GameRule<Boolean> DM_INFINITY_GLOBAL;
    public static GameRule<Boolean> DM_INFINITY_SWORDS;
    public static GameRule<Boolean> DM_INFINITY_TOOLS;
    public static GameRule<Boolean> DM_INFINITY_ARMOR;
    public static GameRule<Boolean> DM_INFINITY_ELYTRA;

    // ==================== Misc ====================
    public static GameRule<Boolean> DM_SHOW_TOOLTIP;

    // ==================== Accessors ====================

    public static int getInt(Level level, GameRule<Integer> rule) {
        if (level.isClientSide())
            return 0;
        return ((ServerLevel) level).getGameRules().get(rule);
    }

    public static boolean getBoolean(Level level, GameRule<Boolean> rule) {
        if (level.isClientSide())
            return false;
        return ((ServerLevel) level).getGameRules().get(rule);
    }

    // ==================== Registration ====================

    public static void register() {
        DM_MULTIPLIER_GLOBAL = registerInteger("dm_multiplier_global", DURABILITY_MULTIPLIER, 2);
        DM_MULTIPLIER_SWORDS = registerInteger("dm_multiplier_swords", DURABILITY_MULTIPLIER, 0);
        DM_MULTIPLIER_TOOLS = registerInteger("dm_multiplier_tools", DURABILITY_MULTIPLIER, 0);
        DM_MULTIPLIER_ARMOR = registerInteger("dm_multiplier_armor", DURABILITY_MULTIPLIER, 0);
        DM_MULTIPLIER_ELYTRA = registerInteger("dm_multiplier_elytra", DURABILITY_MULTIPLIER, 0);

        DM_INFINITY_GLOBAL = registerBoolean("dm_infinity_global", DURABILITY_MULTIPLIER, false);
        DM_INFINITY_SWORDS = registerBoolean("dm_infinity_swords", DURABILITY_MULTIPLIER, false);
        DM_INFINITY_TOOLS = registerBoolean("dm_infinity_tools", DURABILITY_MULTIPLIER, false);
        DM_INFINITY_ARMOR = registerBoolean("dm_infinity_armor", DURABILITY_MULTIPLIER, false);
        DM_INFINITY_ELYTRA = registerBoolean("dm_infinity_elytra", DURABILITY_MULTIPLIER, false);

        DM_SHOW_TOOLTIP = registerBoolean("dm_show_tooltip", DURABILITY_MULTIPLIER, true);
    }

    // ==================== Helpers ====================

    private static GameRule<Boolean> registerBoolean(String id, GameRuleCategory category, boolean defaultValue) {
        return Registry.register(BuiltInRegistries.GAME_RULE, id, new GameRule<>(
                category,
                GameRuleType.BOOL,
                BoolArgumentType.bool(),
                GameRuleTypeVisitor::visitBoolean,
                Codec.BOOL,
                b -> b ? 1 : 0,
                defaultValue,
                FeatureFlagSet.of()));
    }

    private static GameRule<Integer> registerInteger(String id, GameRuleCategory category, int defaultValue) {
        return Registry.register(BuiltInRegistries.GAME_RULE, id, new GameRule<>(
                category,
                GameRuleType.INT,
                IntegerArgumentType.integer(0),
                GameRuleTypeVisitor::visitInteger,
                Codec.INT,
                i -> i,
                defaultValue,
                FeatureFlagSet.of()));
    }
}
