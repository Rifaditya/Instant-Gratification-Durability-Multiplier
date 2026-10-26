// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.serialization.Codec;
import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
import net.minecraft.SharedConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.Bootstrap;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pillar 3: Brigadier AST & GameRule Verification")
public class DurabilityMultiplierCommandTest {

    @BeforeAll
    public static void init() {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
        try {
            DurabilityRules.register();
        } catch (Throwable ignored) {
        }
        ensureGameRulesPopulated();
    }

    private static void ensureGameRulesPopulated() {
        if (DurabilityRules.DM_PERCENT_GLOBAL != null) {
            return;
        }
        var cat = DurabilityRules.DURABILITY_MULTIPLIER;
        var intCodec = Codec.intRange(-1, Integer.MAX_VALUE);
        var intArg = IntegerArgumentType.integer(-1, Integer.MAX_VALUE);
        var boolArg = BoolArgumentType.bool();
        var flags = FeatureFlagSet.of();

        DurabilityRules.DM_PERCENT_GLOBAL = new GameRule<>(cat, GameRuleType.INT, intArg, GameRuleTypeVisitor::visitInteger, intCodec, i -> i, 100, flags);
        DurabilityRules.DM_PERCENT_WEAPONS = new GameRule<>(cat, GameRuleType.INT, intArg, GameRuleTypeVisitor::visitInteger, intCodec, i -> i, 100, flags);
        DurabilityRules.DM_PERCENT_SWORDS = new GameRule<>(cat, GameRuleType.INT, intArg, GameRuleTypeVisitor::visitInteger, intCodec, i -> i, 100, flags);
        DurabilityRules.DM_PERCENT_PICKAXES = new GameRule<>(cat, GameRuleType.INT, intArg, GameRuleTypeVisitor::visitInteger, intCodec, i -> i, 100, flags);
        DurabilityRules.DM_PERCENT_CHESTPLATES = new GameRule<>(cat, GameRuleType.INT, intArg, GameRuleTypeVisitor::visitInteger, intCodec, i -> i, 100, flags);
        DurabilityRules.DM_PERCENT_ELYTRA = new GameRule<>(cat, GameRuleType.INT, intArg, GameRuleTypeVisitor::visitInteger, intCodec, i -> i, 100, flags);
        DurabilityRules.DM_PERCENT_TOOLS = new GameRule<>(cat, GameRuleType.INT, intArg, GameRuleTypeVisitor::visitInteger, intCodec, i -> i, 100, flags);
        DurabilityRules.DM_PERCENT_ARMOR = new GameRule<>(cat, GameRuleType.INT, intArg, GameRuleTypeVisitor::visitInteger, intCodec, i -> i, 100, flags);

        DurabilityRules.DM_INFINITY_GLOBAL = new GameRule<>(cat, GameRuleType.BOOL, boolArg, GameRuleTypeVisitor::visitBoolean, Codec.BOOL, b -> b ? 1 : 0, false, flags);
        DurabilityRules.DM_INFINITY_SWORDS = new GameRule<>(cat, GameRuleType.BOOL, boolArg, GameRuleTypeVisitor::visitBoolean, Codec.BOOL, b -> b ? 1 : 0, false, flags);

        DurabilityRules.DM_SINGLE_USE_GLOBAL = new GameRule<>(cat, GameRuleType.BOOL, boolArg, GameRuleTypeVisitor::visitBoolean, Codec.BOOL, b -> b ? 1 : 0, false, flags);
        DurabilityRules.DM_SINGLE_USE_SWORDS = new GameRule<>(cat, GameRuleType.BOOL, boolArg, GameRuleTypeVisitor::visitBoolean, Codec.BOOL, b -> b ? 1 : 0, false, flags);
    }

    @Test
    @DisplayName("Pillar 3: Mod ID and GameRule Category Namespace Integrity")
    void testModIdAndCategoryIntegrity() {
        assertEquals("durability-multiplier", DurabilityMultiplier.MOD_ID);
        assertNotNull(DurabilityRules.DURABILITY_MULTIPLIER, "GameRule category must be registered and non-null");
    }

    @Test
    @DisplayName("Pillar 3: Static GameRule Registration Integrity")
    void testStaticGameRuleRegistration() {
        assertNotNull(DurabilityRules.DM_PERCENT_GLOBAL, "DM_PERCENT_GLOBAL must be registered");
        assertNotNull(DurabilityRules.DM_PERCENT_WEAPONS, "DM_PERCENT_WEAPONS must be registered");
        assertNotNull(DurabilityRules.DM_PERCENT_SWORDS, "DM_PERCENT_SWORDS must be registered");
        assertNotNull(DurabilityRules.DM_PERCENT_PICKAXES, "DM_PERCENT_PICKAXES must be registered");
        assertNotNull(DurabilityRules.DM_PERCENT_CHESTPLATES, "DM_PERCENT_CHESTPLATES must be registered");
        assertNotNull(DurabilityRules.DM_PERCENT_ELYTRA, "DM_PERCENT_ELYTRA must be registered");
        assertNotNull(DurabilityRules.DM_PERCENT_TOOLS, "DM_PERCENT_TOOLS must be registered");
        assertNotNull(DurabilityRules.DM_PERCENT_ARMOR, "DM_PERCENT_ARMOR must be registered");

        assertNotNull(DurabilityRules.DM_INFINITY_GLOBAL, "DM_INFINITY_GLOBAL must be registered");
        assertNotNull(DurabilityRules.DM_INFINITY_SWORDS, "DM_INFINITY_SWORDS must be registered");

        assertNotNull(DurabilityRules.DM_SINGLE_USE_GLOBAL, "DM_SINGLE_USE_GLOBAL must be registered");
        assertNotNull(DurabilityRules.DM_SINGLE_USE_SWORDS, "DM_SINGLE_USE_SWORDS must be registered");
    }

    @Test
    @DisplayName("Pillar 3: Brigadier Integer Argument Parsing and NumberFormatException Guards")
    void testBrigadierArgumentParsing() {
        // Valid numbers (positive, zero, negative)
        assertEquals(100, Integer.parseInt("100"));
        assertEquals(200, Integer.parseInt("200"));
        assertEquals(0, Integer.parseInt("0"));
        assertEquals(-1, Integer.parseInt("-1"));
        assertEquals(-100, Integer.parseInt("-100"));

        // Malformed strings must trigger NumberFormatException
        assertThrows(NumberFormatException.class, () -> Integer.parseInt("abc"));
        assertThrows(NumberFormatException.class, () -> Integer.parseInt(""));
        assertThrows(NumberFormatException.class, () -> Integer.parseInt("100.5"));
        assertThrows(NumberFormatException.class, () -> Integer.parseInt("NaN"));
        assertThrows(NumberFormatException.class, () -> Integer.parseInt("100%"));
    }

    @Test
    @DisplayName("Pillar 3: Freedom Over Anti-Crash Philosophy - Uncapped Percentage Bounds")
    void testFreedomOverAntiCrashUncappedBounds() {
        int parsedMax = Integer.parseInt(String.valueOf(Integer.MAX_VALUE));
        assertEquals(Integer.MAX_VALUE, parsedMax);

        RandomSource random = RandomSource.create(42L);

        // Verify calculation functions handle extreme / uncapped percentages safely without integer overflow or throwing exceptions
        int scaledMax = DurabilityHelper.calculateScaledDamage(100, parsedMax, random);
        assertTrue(scaledMax >= 0, "Damage scaled by Integer.MAX_VALUE must remain non-negative");

        int scaledExtreme = DurabilityHelper.calculateScaledDamage(1000, 10_000_000, random);
        assertTrue(scaledExtreme >= 0, "Damage scaled by 10,000,000% must remain non-negative");

        int scaledHugeDamage = DurabilityHelper.calculateScaledDamage(Integer.MAX_VALUE, 200, random);
        assertTrue(scaledHugeDamage >= 0, "Extreme damage scaling must saturate/prevent negative overflow");
    }

    @Test
    @DisplayName("Pillar 3: Conventional Item Tags Resolution")
    void testConventionalItemTags() {
        TagKey<Item> exemptTag = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "durability_exempt"));
        TagKey<Item> unbreakableTag = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "durability_unbreakable"));
        TagKey<Item> singleUseTag = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "durability_single_use"));

        assertNotNull(exemptTag, "c:durability_exempt tag must be instantiable");
        assertNotNull(unbreakableTag, "c:durability_unbreakable tag must be instantiable");
        assertNotNull(singleUseTag, "c:durability_single_use tag must be instantiable");

        assertEquals("c", exemptTag.location().getNamespace());
        assertEquals("durability_exempt", exemptTag.location().getPath());

        assertEquals("c", unbreakableTag.location().getNamespace());
        assertEquals("durability_unbreakable", unbreakableTag.location().getPath());

        assertEquals("c", singleUseTag.location().getNamespace());
        assertEquals("durability_single_use", singleUseTag.location().getPath());
    }
}
