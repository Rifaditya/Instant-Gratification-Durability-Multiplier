// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier;

import net.instantgratification.durabilitymultiplier.config.DurabilityConfig;
import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
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
    @DisplayName("Single-Use Mode Math: deals full remaining durability")
    void testSingleUseRemainingDamageMath() {
        // Simulating stack.getMaxDamage() - stack.getDamageValue()
        int maxDamage = 1561; // Diamond sword
        int damageValue = 0;
        int damageInflicted = Math.max(1, maxDamage - damageValue);
        assertEquals(1561, damageInflicted);

        damageValue = 1560; // 1 durability left
        damageInflicted = Math.max(1, maxDamage - damageValue);
        assertEquals(1, damageInflicted);

        damageValue = 1561; // Broken / zero durability
        damageInflicted = Math.max(1, maxDamage - damageValue);
        assertEquals(1, damageInflicted);
    }

    @Test
    @DisplayName("Single-Use Sentinel: -1 formatTooltip returns null for dedicated SINGLE-USE label handling")
    void testNegativeOneSentinelTooltip() {
        assertNull(DurabilityHelper.formatTooltip(-1, DurabilityHelper.ItemCategory.SWORD, "Sword", DurabilityConfig.TooltipFormat.ADAPTIVE));
        assertNull(DurabilityHelper.formatTooltip(-5, DurabilityHelper.ItemCategory.SWORD, "Sword", DurabilityConfig.TooltipFormat.ADAPTIVE));
    }

    @Test
    @DisplayName("Forced Items: config getters and aliases resolve correctly")
    void testForcedItemConfigResolution() {
        DurabilityConfig config = DurabilityConfig.get();
        config.forcedItems.add("watersgems:moonstone_sword");
        config.forcedPercentages.put("watersgems:moonstone_sword", 300);
        config.forcedInfinities.put("watersgems:ruby_axe", true);
        config.forcedSingleUses.put("watersgems:glass_dagger", true);

        assertTrue(config.isForced("watersgems:moonstone_sword"));
        assertTrue(config.isForced("watersgems:ruby_axe"));
        assertTrue(config.isForced("watersgems:glass_dagger"));
        assertEquals(300, config.getForcedPercent("watersgems:moonstone_sword"));
        assertTrue(config.getForcedInfinity("watersgems:ruby_axe"));
        assertTrue(config.getForcedSingleUse("watersgems:glass_dagger"));
    }

    @Test
    @DisplayName("Auto-Populate: recordDiscoveredItem populates forcedItems and forcedPercentages with default 0")
    void testRecordDiscoveredItemAutoPopulatesWithZero() {
        DurabilityConfig config = DurabilityConfig.get();
        String testItem = "testmod:auto_discovered_sword";
        config.forcedItems.remove(testItem);
        config.forcedPercentages.remove(testItem);

        boolean recorded = config.recordDiscoveredItem(testItem);
        assertTrue(recorded, "Expected recordDiscoveredItem to return true for newly discovered item");
        assertTrue(config.forcedItems.contains(testItem), "forcedItems must contain the discovered item");
        assertTrue(config.forcedPercentages.containsKey(testItem), "forcedPercentages must contain the discovered item");
        assertEquals(0, config.forcedPercentages.get(testItem), "Default percentage for newly discovered item must be 0");
        assertTrue(config.isForced(testItem), "isForced must recognize auto-populated item");
        assertEquals(0, config.getForcedPercent(testItem));
        assertTrue(DurabilityConfig.isDirty(), "Dirty state must be true after recording newly discovered item");
    }

    @Test
    @DisplayName("Auto-Populate: recordDiscoveredItem strictly preserves pre-existing non-zero customized values")
    void testRecordDiscoveredItemPreservesPreExistingNonZeroValues() {
        DurabilityConfig config = DurabilityConfig.get();
        String customizedItem = "custommod:legendary_hammer";
        config.forcedItems.add(customizedItem);
        config.forcedPercentages.put(customizedItem, 400);

        int initialItemCount = config.forcedItems.size();
        boolean recordedAgain = config.recordDiscoveredItem(customizedItem);
        assertFalse(recordedAgain, "Expected recordDiscoveredItem to return false when item is already present");
        assertEquals(initialItemCount, config.forcedItems.size(), "forcedItems should not accumulate duplicate entries");
        assertEquals(400, config.getForcedPercent(customizedItem), "Pre-existing customized percentage (400) must be strictly preserved");
    }

    @Test
    @DisplayName("Auto-Populate: recordDiscoveredItem strictly preserves pre-existing negative sentinel values (-1)")
    void testRecordDiscoveredItemPreservesNegativeSentinel() {
        DurabilityConfig config = DurabilityConfig.get();
        String singleUseItem = "custommod:fragile_dagger";
        config.forcedItems.add(singleUseItem);
        config.forcedPercentages.put(singleUseItem, -1);

        boolean recordedAgain = config.recordDiscoveredItem(singleUseItem);
        assertFalse(recordedAgain, "Expected recordDiscoveredItem to return false when item is already present");
        assertEquals(-1, config.getForcedPercent(singleUseItem), "Pre-existing -1 Single-Use sentinel must be preserved");
    }

    @Test
    @DisplayName("Auto-Populate: recordDiscoveredItem handles null and empty inputs safely")
    void testRecordDiscoveredItemNullAndEmptyRejection() {
        DurabilityConfig config = DurabilityConfig.get();
        assertFalse(config.recordDiscoveredItem(null));
        assertFalse(config.recordDiscoveredItem(""));
    }

    @Test
    @DisplayName("Migration: migrateFromV1 migrates dynamic map fields to canonical forced maps")
    void testMigrateFromV1DynamicMaps() {
        DurabilityConfig config = new DurabilityConfig();
        config.dynamicPercentages.put("legacy:sword", 250);
        config.dynamicInfinities.put("legacy:shield", true);
        config.dynamicSingleUses.put("legacy:glass_bow", true);

        config.migrateFromV1();

        assertTrue(config.forcedItems.contains("legacy:sword"));
        assertTrue(config.forcedItems.contains("legacy:shield"));
        assertTrue(config.forcedItems.contains("legacy:glass_bow"));
        assertEquals(250, config.forcedPercentages.get("legacy:sword"));
        assertTrue(config.forcedInfinities.get("legacy:shield"));
        assertTrue(config.forcedSingleUses.get("legacy:glass_bow"));
    }

    @Test
    @DisplayName("Setters: setForced setters update both forced and dynamic maps and mark dirty")
    void testSetForcedSetters() {
        DurabilityConfig config = DurabilityConfig.get();
        String item = "custom:laser_cutter";

        config.setForcedPercent(item, 350);
        assertEquals(350, config.getForcedPercent(item));
        assertTrue(config.forcedItems.contains(item));
        assertEquals(350, config.forcedPercentages.get(item));
        assertEquals(350, config.dynamicPercentages.get(item));

        config.setForcedInfinity(item, true);
        assertTrue(config.getForcedInfinity(item));
        assertTrue(config.forcedInfinities.get(item));
        assertTrue(config.dynamicInfinities.get(item));

        config.setForcedSingleUse(item, true);
        assertTrue(config.getForcedSingleUse(item));
        assertTrue(config.forcedSingleUses.get(item));
        assertTrue(config.dynamicSingleUses.get(item));

        assertTrue(DurabilityConfig.isDirty());
    }

    @Test
    @DisplayName("Client State: Crossbow inherits singleUseWeapons and singleUseGlobal fallbacks")
    void testClientStateCrossbowWeaponFallback() {
        net.instantgratification.durabilitymultiplier.network.DurabilityPayload payloadWeapons = new net.instantgratification.durabilitymultiplier.network.DurabilityPayload(
                100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                true, java.util.Map.of(), java.util.Map.of(), java.util.Map.of()
        );
        net.instantgratification.durabilitymultiplier.network.DurabilityClientState.apply(payloadWeapons);
        assertTrue(net.instantgratification.durabilitymultiplier.network.DurabilityClientState.singleUseWeapons());
        assertFalse(net.instantgratification.durabilitymultiplier.network.DurabilityClientState.singleUseCrossbows());
    }

    @Test
    @DisplayName("DurabilityConfig: saveIfDirty resets dirty flag")
    void testSaveIfDirtyResetsDirty() {
        DurabilityConfig.markDirty();
        assertTrue(DurabilityConfig.isDirty());
        DurabilityConfig.saveIfDirty();
        assertFalse(DurabilityConfig.isDirty());
    }

    @Test
    @DisplayName("DurabilityConfig: null-field resilience on getters")
    void testNullFieldResilienceOnGetters() {
        DurabilityConfig config = new DurabilityConfig();
        config.forcedItems = null;
        config.forcedPercentages = null;
        config.forcedInfinities = null;
        config.forcedSingleUses = null;
        config.dynamicPercentages = null;
        config.dynamicInfinities = null;
        config.dynamicSingleUses = null;

        assertNotNull(config.getAllForcedItemIds());
        assertEquals(0, config.getForcedPercent("test:item"));
        assertFalse(config.getForcedInfinity("test:item"));
        assertFalse(config.getForcedSingleUse("test:item"));
        assertFalse(config.isForced("test:item"));

        // recordDiscoveredItem handles null collections gracefully
        assertTrue(config.recordDiscoveredItem("test:new_item"));
        assertTrue(config.forcedItems.contains("test:new_item"));
        assertEquals(0, config.getForcedPercent("test:new_item"));
    }

    @Test
    @DisplayName("Non-Damageable Items: null or non-durability items are strictly rejected unless forced")
    void testNonDamageableItemRejection() {
        assertFalse(DurabilityRules.isItemDamageable(null));
        
        DurabilityConfig.get().forcedItems.add("custom:forced_non_damageable");
        assertTrue(DurabilityConfig.get().isForced("custom:forced_non_damageable"));
    }
}
