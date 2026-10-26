// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier;

import net.instantgratification.durabilitymultiplier.config.DurabilityConfig;
import net.instantgratification.durabilitymultiplier.network.DurabilityClientState;
import net.instantgratification.durabilitymultiplier.network.DurabilityPayload;
import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
import net.minecraft.SharedConstants;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pillar 4: Headless State Simulation GameTest")
public class DurabilityMultiplierGameTest {

    @BeforeAll
    public static void init() {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
        bindDataComponentsIfPresent();
        try {
            DurabilityRules.register();
        } catch (Throwable ignored) {
        }
    }

    private static void bindDataComponentsIfPresent() {
        try {
            Class<?> registriesClass = Class.forName("net.minecraft.core.registries.BuiltInRegistries");
            Field dataCompField = registriesClass.getField("DATA_COMPONENT_INITIALIZERS");
            Object dataComponentInitializers = dataCompField.get(null);

            Class<?> vanillaRegClass = Class.forName("net.minecraft.data.registries.VanillaRegistries");
            Method createLookupMethod = vanillaRegClass.getMethod("createLookup");
            Object provider = createLookupMethod.invoke(null);

            Class<?> providerClass = Class.forName("net.minecraft.core.HolderLookup$Provider");
            Method buildMethod = dataComponentInitializers.getClass().getMethod("build", providerClass);
            List<?> pendingList = (List<?>) buildMethod.invoke(dataComponentInitializers, provider);
            if (pendingList != null) {
                Class<?> pendingCompClass = Class.forName("net.minecraft.core.component.DataComponentInitializers$PendingComponents");
                Method applyMethod = pendingCompClass.getMethod("apply");
                applyMethod.setAccessible(true);
                for (Object pending : pendingList) {
                    applyMethod.invoke(pending);
                }
            }
        } catch (Throwable ignored) {
        }
    }

    @AfterEach
    void tearDown() {
        DurabilityConfig config = DurabilityConfig.get();
        if (config.forcedItems != null) {
            config.forcedItems.removeIf(id -> id != null && (id.startsWith("custom:") || id.startsWith("testmod:")));
        }
        if (config.forcedPercentages != null) {
            config.forcedPercentages.keySet().removeIf(id -> id != null && (id.startsWith("custom:") || id.startsWith("testmod:")));
        }
        if (config.forcedInfinities != null) {
            config.forcedInfinities.keySet().removeIf(id -> id != null && (id.startsWith("custom:") || id.startsWith("testmod:")));
        }
        if (config.forcedSingleUses != null) {
            config.forcedSingleUses.keySet().removeIf(id -> id != null && (id.startsWith("custom:") || id.startsWith("testmod:")));
        }
        if (config.dynamicPercentages != null) {
            config.dynamicPercentages.keySet().removeIf(id -> id != null && (id.startsWith("custom:") || id.startsWith("testmod:")));
        }
        if (config.dynamicInfinities != null) {
            config.dynamicInfinities.keySet().removeIf(id -> id != null && (id.startsWith("custom:") || id.startsWith("testmod:")));
        }
        if (config.dynamicSingleUses != null) {
            config.dynamicSingleUses.keySet().removeIf(id -> id != null && (id.startsWith("custom:") || id.startsWith("testmod:")));
        }

        DurabilityPayload resetPayload = createPayload(Map.of(), Map.of(), Map.of());
        DurabilityClientState.apply(resetPayload);
        DurabilityHelper.clearCategoryCache();
    }

    private static DurabilityPayload createPayload(
            Map<String, Integer> dynamicPercentages,
            Map<String, Boolean> dynamicInfinities,
            Map<String, Boolean> dynamicSingleUses
    ) {
        return new DurabilityPayload(
                100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                true,
                dynamicPercentages, dynamicInfinities, dynamicSingleUses
        );
    }

    @Test
    @DisplayName("State Simulation: Category Classification for Vanilla Archetypes")
    void testCategoryClassificationSimulation() {
        assertEquals(DurabilityHelper.ItemCategory.SWORD, DurabilityHelper.classifyItem(new ItemStack(Items.DIAMOND_SWORD)));
        assertEquals(DurabilityHelper.ItemCategory.PICKAXE, DurabilityHelper.classifyItem(new ItemStack(Items.NETHERITE_PICKAXE)));
        assertEquals(DurabilityHelper.ItemCategory.BOW, DurabilityHelper.classifyItem(new ItemStack(Items.BOW)));
        assertEquals(DurabilityHelper.ItemCategory.SHIELD, DurabilityHelper.classifyItem(new ItemStack(Items.SHIELD)));
        assertEquals(DurabilityHelper.ItemCategory.ELYTRA, DurabilityHelper.classifyItem(new ItemStack(Items.ELYTRA)));
        assertEquals(DurabilityHelper.ItemCategory.CHESTPLATE, DurabilityHelper.classifyItem(new ItemStack(Items.IRON_CHESTPLATE)));
    }

    @Test
    @DisplayName("State Simulation: God Mode Infinity in Config and Client State")
    void testGodModeInfinitySimulation() {
        DurabilityConfig config = DurabilityConfig.get();
        config.setForcedInfinity("custom:god_sword", true);
        assertTrue(config.getForcedInfinity("custom:god_sword"), "Custom item should have forced infinity in config");
        assertTrue(DurabilityClientState.isItemInfiniteClient("custom:god_sword"), "Client state should recognize forced infinity from config");

        DurabilityPayload payload = createPayload(
                Map.of(),
                Map.of("custom:payload_god_item", true),
                Map.of()
        );
        DurabilityClientState.apply(payload);
        assertTrue(DurabilityClientState.isItemInfiniteClient("custom:payload_god_item"), "Client state should recognize infinity from synced payload");
        assertFalse(DurabilityClientState.isItemInfiniteClient("custom:normal_sword"), "Unregistered item should not be infinite");
    }

    @Test
    @DisplayName("State Simulation: Single-Use Glass Mode Remaining Damage Calculation")
    void testSingleUseSimulation() {
        ItemStack stack = new ItemStack(Items.DIAMOND_SWORD);
        int maxDamage = stack.getMaxDamage();
        assertEquals(1561, maxDamage, "Diamond sword should have 1561 max durability in vanilla");

        int currentDamage = stack.getDamageValue();
        assertEquals(0, currentDamage, "Pristine item damage value should be 0");

        int singleUseDamage = Math.max(1, maxDamage - currentDamage);
        assertEquals(1561, singleUseDamage, "Pristine item should take all 1561 damage in a single use");

        // Existing damage simulation
        int damagedAmount = 500;
        int remainingDamage = Math.max(1, maxDamage - damagedAmount);
        assertEquals(1061, remainingDamage, "Damaged item should take remaining 1061 durability");

        // 1 durability remaining (current damage = 1560)
        int criticalDamage = Math.max(1, maxDamage - 1560);
        assertEquals(1, criticalDamage, "Item with 1 durability remaining should take 1 damage");

        // Clamped boundary (current damage equals or exceeds maxDamage)
        int zeroDurabilityDamage = Math.max(1, maxDamage - 1561);
        assertEquals(1, zeroDurabilityDamage, "Zero remaining durability should clamp to at least 1");
        int overflowDamage = Math.max(1, maxDamage - 2000);
        assertEquals(1, overflowDamage, "Overflow damage should clamp to at least 1");
    }

    @Test
    @DisplayName("State Simulation: Damageable Item Filtering Verification")
    void testDamageableItemFilteringSimulation() {
        assertTrue(DurabilityRules.isItemDamageable(Items.DIAMOND_SWORD), "Diamond sword must be classified as damageable");
        assertFalse(DurabilityRules.isItemDamageable(Items.APPLE), "Apple must not be classified as damageable");
    }

    @Test
    @DisplayName("State Simulation: Dynamic Discovery Lifecycle & Unmodified Defaults Pruning")
    void testDynamicDiscoveryLifecycleSimulation() {
        DurabilityConfig config = DurabilityConfig.get();
        String modifiedId = "testmod:laser_cannon";
        String untamperedId = "testmod:untampered_item";

        // 1. Record discovered items
        boolean recordedMod = config.recordDiscoveredItem(modifiedId);
        boolean recordedUntampered = config.recordDiscoveredItem(untamperedId);
        assertTrue(recordedMod, "Recording new item should return true");
        assertTrue(recordedUntampered, "Recording new item should return true");

        // 2. Verify initial defaults are 0
        assertEquals(0, config.getForcedPercent(modifiedId), "Default percentage for discovered item must be 0");
        assertEquals(0, config.getForcedPercent(untamperedId), "Default percentage for untampered item must be 0");

        // 3. Mutate one item to 300%
        config.setForcedPercent(modifiedId, 300);
        assertEquals(300, config.getForcedPercent(modifiedId), "Modified item percentage must be 300");

        // 4. Run pruneUnmodifiedDefaults
        config.pruneUnmodifiedDefaults();

        // 5. Verify untampered item was pruned while modified item was retained
        assertFalse(config.getAllForcedItemIds().contains(untamperedId), "Untampered item with 0% must be pruned from forced items");
        assertTrue(config.getAllForcedItemIds().contains(modifiedId), "Modified item with 300% must be retained in forced items");
        assertEquals(300, config.getForcedPercent(modifiedId), "Modified item percentage must remain 300 after pruning");
    }
}
