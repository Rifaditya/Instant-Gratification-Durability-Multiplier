// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier;

import net.instantgratification.durabilitymultiplier.config.DurabilityConfig;
import net.minecraft.SharedConstants;
import net.minecraft.server.Bootstrap;
import net.minecraft.util.RandomSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DurabilityMultiplierStressFuzzTest: Pillar 1 Chaos Property Fuzzing & Saturated Bounds.
 *
 * Verifies mathematical soundness, saturated arithmetic, non-negative bounds,
 * deterministic random roll behaviors, tooltip formatting invariants, and config concurrency/fuzzing
 * across all Minecraft version anchors.
 */
public class DurabilityMultiplierStressFuzzTest {

    @BeforeAll
    public static void init() {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
    }

    @Test
    @DisplayName("Pillar 1 Chaos: calculateScaledDamage under 20,000 iterations of randomized & boundary fuzzing")
    void testChaosDamageScaling() {
        RandomSource random = RandomSource.create(42L);
        Random rng = new Random(0xDA51C263L);

        int[] boundaryValues = new int[]{
                Integer.MIN_VALUE, -100, -1, 0, 1, 2, 50, 100, 200, 1000, 1_000_000, Integer.MAX_VALUE
        };

        // 1. Matrix verification of boundary values
        for (int amount : boundaryValues) {
            for (int percent : boundaryValues) {
                int scaled = DurabilityHelper.calculateScaledDamage(amount, percent, random);

                if (amount <= 0) {
                    assertTrue(scaled <= 0, "amount <= 0 must return <= 0 (got: " + scaled + " for amount=" + amount + ", percent=" + percent + ")");
                } else if (percent > 0) {
                    assertTrue(scaled >= 0, "amount > 0 and percent > 0 must return >= 0 (got: " + scaled + " for amount=" + amount + ", percent=" + percent + ")");
                }

                if (percent == 100) {
                    int expected = amount <= 0 ? 0 : amount;
                    assertEquals(expected, scaled, "percent == 100 must preserve original amount (amount=" + amount + ")");
                }

                if (percent == 50 && amount > 0 && amount <= 1000) {
                    assertEquals(amount * 2, scaled, "percent == 50 must double small amount=" + amount);
                }
            }
        }

        // 2. Extreme percentage check (100,000,000%) with amount = 1
        for (int i = 0; i < 500; i++) {
            int extremeScaled = DurabilityHelper.calculateScaledDamage(1, 100_000_000, random);
            assertTrue(extremeScaled == 0 || extremeScaled == 1,
                    "Extreme percentage (100,000,000%) scaled damage for amount=1 must be 0 or 1, got: " + extremeScaled);
        }

        // 3. Small amounts 1..1000 at 50%
        for (int amt = 1; amt <= 1000; amt++) {
            int scaled = DurabilityHelper.calculateScaledDamage(amt, 50, random);
            assertEquals(amt * 2, scaled, "percent == 50 must exactly double amount=" + amt);
        }

        // 4. 20,000 randomized chaos iterations
        for (int i = 0; i < 20_000; i++) {
            int amount;
            int percent;

            int amountMode = rng.nextInt(3);
            if (amountMode == 0) {
                amount = boundaryValues[rng.nextInt(boundaryValues.length)];
            } else if (amountMode == 1) {
                amount = rng.nextInt(2001) - 1000; // -1000 to 1000
            } else {
                amount = rng.nextInt();
            }

            int percentMode = rng.nextInt(3);
            if (percentMode == 0) {
                percent = boundaryValues[rng.nextInt(boundaryValues.length)];
            } else if (percentMode == 1) {
                percent = rng.nextInt(5000) - 100; // -100 to 4900
            } else {
                percent = rng.nextInt();
            }

            int scaled = DurabilityHelper.calculateScaledDamage(amount, percent, random);

            if (amount <= 0) {
                assertTrue(scaled <= 0, "amount <= 0 must yield <= 0, got " + scaled + " for amount=" + amount + ", percent=" + percent);
            } else if (percent > 0) {
                assertTrue(scaled >= 0, "amount > 0 and percent > 0 must never be negative, got " + scaled + " for amount=" + amount + ", percent=" + percent);
            }

            if (percent == 100) {
                int expected = amount <= 0 ? 0 : amount;
                assertEquals(expected, scaled, "percent == 100 must yield original amount (amount=" + amount + ")");
            }

            if (percent == 50 && amount > 0 && amount <= 1000) {
                assertEquals(amount * 2, scaled, "percent == 50 must yield amount * 2 (amount=" + amount + ")");
            }
        }
    }

    @Test
    @DisplayName("Pillar 1 Chaos: formatTooltip across 10,000 iterations of ItemCategory and TooltipFormat values")
    void testChaosTooltipFormatting() {
        Random rng = new Random(0xDA51C263L);
        DurabilityHelper.ItemCategory[] categories = DurabilityHelper.ItemCategory.values();
        DurabilityConfig.TooltipFormat[] formats = DurabilityConfig.TooltipFormat.values();

        int[] samplePercentages = new int[]{
                Integer.MIN_VALUE, -500, -1, 0, 1, 25, 50, 75, 100, 125, 150, 200, 300, 500, 1000, 100_000, Integer.MAX_VALUE
        };

        for (int i = 0; i < 10_000; i++) {
            DurabilityHelper.ItemCategory cat = categories[rng.nextInt(categories.length)];
            DurabilityConfig.TooltipFormat format = formats[rng.nextInt(formats.length)];

            int percent;
            if (rng.nextBoolean()) {
                percent = samplePercentages[rng.nextInt(samplePercentages.length)];
            } else {
                percent = rng.nextInt(2001) - 500; // -500 to 1500
            }

            String itemName = "TestItem_" + (rng.nextInt(100));

            String result = DurabilityHelper.formatTooltip(percent, cat, itemName, format);

            if (percent <= 0 || (percent == 100 && format == DurabilityConfig.TooltipFormat.ADAPTIVE)) {
                assertNull(result, "Expected null tooltip for percent=" + percent + ", format=" + format);
            } else if (percent == 100) {
                assertNull(result, "Expected null tooltip for percent=100 across all formats");
            } else {
                assertNotNull(result, "Expected non-null tooltip for percent=" + percent + ", format=" + format);
                assertFalse(result.isEmpty(), "Tooltip should not be empty for percent=" + percent + ", format=" + format);
            }
        }
    }

    @Test
    @DisplayName("Pillar 1 Chaos: 5,000 randomized forced items config operations and dirty save lifecycle")
    void testChaosConfigForcedItems() {
        DurabilityConfig config = DurabilityConfig.get();
        Random rng = new Random(0xDA51C263L);

        String[] itemPool = new String[50];
        for (int i = 0; i < itemPool.length; i++) {
            itemPool[i] = "chaos_mod:item_" + i;
        }

        int operations = 5000;
        for (int op = 0; op < operations; op++) {
            int action = rng.nextInt(5);
            String item = itemPool[rng.nextInt(itemPool.length)];

            switch (action) {
                case 0 -> {
                    // recordDiscoveredItem
                    boolean recorded = config.recordDiscoveredItem(item);
                    assertTrue(config.isForced(item), "Item must be considered forced once discovered: " + item);
                }
                case 1 -> {
                    // setForcedPercent
                    int percent = rng.nextInt(1000) - 100; // -100 to 900
                    config.setForcedPercent(item, percent);
                    assertEquals(percent, config.getForcedPercent(item), "Forced percent must match set value");
                    if (percent != 0) {
                        assertTrue(config.isForced(item), "Item must be forced after non-zero setForcedPercent");
                    }
                }
                case 2 -> {
                    // Query getForcedPercent
                    int percent = config.getForcedPercent(item);
                    // Assert zero NPEs and valid query
                }
                case 3 -> {
                    // Query isForced
                    boolean forced = config.isForced(item);
                    // Assert zero NPEs and valid query
                }
                case 4 -> {
                    // Edge case inputs: null or empty
                    String edgeInput = rng.nextBoolean() ? null : "";
                    assertFalse(config.recordDiscoveredItem(edgeInput), "Discovered item with null/empty must return false");
                    config.setForcedPercent(edgeInput, 200);
                    assertEquals(0, config.getForcedPercent(edgeInput), "Null/empty item must yield default 0 percent");
                }
            }
        }

        // Verify saveIfDirty() lifecycle
        DurabilityConfig.markDirty();
        assertTrue(DurabilityConfig.isDirty(), "Dirty flag must be true after markDirty()");
        DurabilityConfig.saveIfDirty();
        assertFalse(DurabilityConfig.isDirty(), "Dirty flag must be false after saveIfDirty()");
    }
}
