// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier;

import net.instantgratification.durabilitymultiplier.config.DurabilityConfig;
import net.minecraft.util.RandomSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DurabilityHelperTest {

    private final RandomSource random = RandomSource.create(42L);

    @Test
    @DisplayName("Durability Reduction: 50% percent deals 2x damage")
    void testFiftyPercentDealsDoubleDamage() {
        int scaled = DurabilityHelper.calculateScaledDamage(1, 50, random);
        assertEquals(2, scaled);

        int scaledThree = DurabilityHelper.calculateScaledDamage(3, 50, random);
        assertEquals(6, scaledThree);
    }

    @Test
    @DisplayName("Durability Reduction: 25% percent deals 4x damage")
    void testTwentyFivePercentDealsQuadrupleDamage() {
        int scaled = DurabilityHelper.calculateScaledDamage(1, 25, random);
        assertEquals(4, scaled);
    }

    @Test
    @DisplayName("Durability Reduction: 10% percent deals 10x damage")
    void testTenPercentDealsTenTimesDamage() {
        int scaled = DurabilityHelper.calculateScaledDamage(1, 10, random);
        assertEquals(10, scaled);
    }

    @Test
    @DisplayName("Durability Reduction: 1% percent deals 100x damage")
    void testOnePercentDealsHundredTimesDamage() {
        int scaled = DurabilityHelper.calculateScaledDamage(1, 1, random);
        assertEquals(100, scaled);
    }

    @Test
    @DisplayName("Durability Identity: 100% deals exact 1x damage")
    void testHundredPercentIdentity() {
        assertEquals(1, DurabilityHelper.calculateScaledDamage(1, 100, random));
        assertEquals(5, DurabilityHelper.calculateScaledDamage(5, 100, random));
    }

    @Test
    @DisplayName("Durability Boost: 200% percent deals ~0.5x damage on average")
    void testTwoHundredPercentProbabilisticAverage() {
        int totalDamage = 0;
        int trials = 10000;
        for (int i = 0; i < trials; i++) {
            totalDamage += DurabilityHelper.calculateScaledDamage(1, 200, random);
        }
        double avg = (double) totalDamage / trials;
        assertTrue(avg >= 0.47 && avg <= 0.53, "Expected average damage near 0.5, got: " + avg);
    }

    @Test
    @DisplayName("Durability Boost: 1000% percent deals ~0.1x damage on average")
    void testThousandPercentProbabilisticAverage() {
        int totalDamage = 0;
        int trials = 10000;
        for (int i = 0; i < trials; i++) {
            totalDamage += DurabilityHelper.calculateScaledDamage(1, 1000, random);
        }
        double avg = (double) totalDamage / trials;
        assertTrue(avg >= 0.08 && avg <= 0.12, "Expected average damage near 0.1, got: " + avg);
    }

    @Test
    @DisplayName("Boundary and edge cases")
    void testEdgeCases() {
        assertEquals(0, DurabilityHelper.calculateScaledDamage(0, 200, random));
        assertEquals(0, DurabilityHelper.calculateScaledDamage(0, 50, random));
        assertEquals(5, DurabilityHelper.calculateScaledDamage(5, 0, random));
        assertEquals(5, DurabilityHelper.calculateScaledDamage(5, -10, random));
    }

    @Test
    @DisplayName("Tooltip Formatting: Adaptive mode")
    void testAdaptiveTooltipFormat() {
        assertEquals("2x Swords Durability", DurabilityHelper.formatTooltip(200, DurabilityHelper.ItemCategory.SWORD, "Sword", DurabilityConfig.TooltipFormat.ADAPTIVE));
        assertEquals("5x Pickaxes Durability", DurabilityHelper.formatTooltip(500, DurabilityHelper.ItemCategory.PICKAXE, "Pickaxe", DurabilityConfig.TooltipFormat.ADAPTIVE));
        assertEquals("5x Tools Durability", DurabilityHelper.formatTooltip(500, DurabilityHelper.ItemCategory.TOOL_GLOBAL, "Tool", DurabilityConfig.TooltipFormat.ADAPTIVE));
        assertEquals("50% Swords Durability", DurabilityHelper.formatTooltip(50, DurabilityHelper.ItemCategory.SWORD, "Sword", DurabilityConfig.TooltipFormat.ADAPTIVE));
        assertEquals("150% Chestplates Durability", DurabilityHelper.formatTooltip(150, DurabilityHelper.ItemCategory.CHESTPLATE, "Chestplate", DurabilityConfig.TooltipFormat.ADAPTIVE));
        assertEquals("150% Armor Durability", DurabilityHelper.formatTooltip(150, DurabilityHelper.ItemCategory.ARMOR_GLOBAL, "Armor", DurabilityConfig.TooltipFormat.ADAPTIVE));
        assertNull(DurabilityHelper.formatTooltip(100, DurabilityHelper.ItemCategory.SWORD, "Sword", DurabilityConfig.TooltipFormat.ADAPTIVE));
        assertNull(DurabilityHelper.formatTooltip(0, DurabilityHelper.ItemCategory.SWORD, "Sword", DurabilityConfig.TooltipFormat.ADAPTIVE));
    }

    @Test
    @DisplayName("Tooltip Formatting: Percentage mode")
    void testPercentageTooltipFormat() {
        assertEquals("200% Swords Durability", DurabilityHelper.formatTooltip(200, DurabilityHelper.ItemCategory.SWORD, "Sword", DurabilityConfig.TooltipFormat.PERCENTAGE));
        assertEquals("50% Pickaxes Durability", DurabilityHelper.formatTooltip(50, DurabilityHelper.ItemCategory.PICKAXE, "Pickaxe", DurabilityConfig.TooltipFormat.PERCENTAGE));
        assertEquals("50% Tools Durability", DurabilityHelper.formatTooltip(50, DurabilityHelper.ItemCategory.TOOL_GLOBAL, "Tool", DurabilityConfig.TooltipFormat.PERCENTAGE));
    }

    @Test
    @DisplayName("Tooltip Formatting: Multiplier mode")
    void testMultiplierTooltipFormat() {
        assertEquals("2x Swords Durability", DurabilityHelper.formatTooltip(200, DurabilityHelper.ItemCategory.SWORD, "Sword", DurabilityConfig.TooltipFormat.MULTIPLIER));
        assertEquals("0.5x Swords Durability", DurabilityHelper.formatTooltip(50, DurabilityHelper.ItemCategory.SWORD, "Sword", DurabilityConfig.TooltipFormat.MULTIPLIER));
        assertEquals("1.5x Chestplates Durability", DurabilityHelper.formatTooltip(150, DurabilityHelper.ItemCategory.CHESTPLATE, "Chestplate", DurabilityConfig.TooltipFormat.MULTIPLIER));
    }

    @Test
    @DisplayName("Tooltip Formatting: OTHER category with custom item name")
    void testOtherCategoryTooltipFormat() {
        assertEquals("3x Amethyst Dagger Durability", DurabilityHelper.formatTooltip(300, DurabilityHelper.ItemCategory.OTHER, "Amethyst Dagger", DurabilityConfig.TooltipFormat.ADAPTIVE));
        assertEquals("50% Ruby Greatsword Durability", DurabilityHelper.formatTooltip(50, DurabilityHelper.ItemCategory.OTHER, "Ruby Greatsword", DurabilityConfig.TooltipFormat.ADAPTIVE));
    }

    @Test
    @DisplayName("Cache Invalidation: clearCategoryCache executes cleanly")
    void testClearCategoryCache() {
        assertDoesNotThrow(DurabilityHelper::clearCategoryCache);
    }
}
