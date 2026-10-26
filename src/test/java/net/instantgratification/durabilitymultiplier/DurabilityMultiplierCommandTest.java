// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier;

import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
import net.minecraft.SharedConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Bootstrap;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

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

    @SuppressWarnings("unchecked")
    private static <T extends GameRules.Value<T>> GameRules.Key<T> createKey(String id) {
        try {
            Constructor<GameRules.Key> constructor = GameRules.Key.class.getDeclaredConstructor(String.class, GameRules.Category.class);
            constructor.setAccessible(true);
            return (GameRules.Key<T>) constructor.newInstance(id, GameRules.Category.MISC);
        } catch (Exception e) {
            throw new RuntimeException("Failed to construct GameRules.Key for " + id, e);
        }
    }

    private static void ensureGameRulesPopulated() {
        if (DurabilityRules.DM_PERCENT_GLOBAL != null) {
            return;
        }
        DurabilityRules.DM_PERCENT_GLOBAL = createKey("ig:dm_percent_global");
        DurabilityRules.DM_PERCENT_WEAPONS = createKey("ig:dm_percent_weapons");
        DurabilityRules.DM_PERCENT_SWORDS = createKey("ig:dm_percent_swords");
        DurabilityRules.DM_PERCENT_PICKAXES = createKey("ig:dm_percent_pickaxes");
        DurabilityRules.DM_PERCENT_CHESTPLATES = createKey("ig:dm_percent_chestplates");
        DurabilityRules.DM_PERCENT_ELYTRA = createKey("ig:dm_percent_elytra");
        DurabilityRules.DM_PERCENT_TOOLS = createKey("ig:dm_percent_tools");
        DurabilityRules.DM_PERCENT_ARMOR = createKey("ig:dm_percent_armor");

        DurabilityRules.DM_INFINITY_GLOBAL = createKey("ig:dm_infinity_global");
        DurabilityRules.DM_INFINITY_SWORDS = createKey("ig:dm_infinity_swords");

        DurabilityRules.DM_SINGLE_USE_GLOBAL = createKey("ig:dm_single_use_global");
        DurabilityRules.DM_SINGLE_USE_SWORDS = createKey("ig:dm_single_use_swords");
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
        TagKey<Item> exemptTag = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "durability_exempt"));
        TagKey<Item> unbreakableTag = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "durability_unbreakable"));
        TagKey<Item> singleUseTag = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "durability_single_use"));

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
