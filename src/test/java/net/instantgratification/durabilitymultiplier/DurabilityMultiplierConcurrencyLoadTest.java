// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier;

import net.instantgratification.durabilitymultiplier.config.DurabilityConfig;
import net.instantgratification.durabilitymultiplier.network.DurabilityClientState;
import net.instantgratification.durabilitymultiplier.network.DurabilityPayload;
import net.minecraft.SharedConstants;
import net.minecraft.server.Bootstrap;
import net.minecraft.util.RandomSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DurabilityMultiplierConcurrencyLoadTest: Pillar 2 Virtual Multi-Thread Concurrency Load Harness.
 *
 * Verifies high-load concurrent calculations, thread-safe config discovery/mutation,
 * client network state payload distribution, zero deadlocks, and zero ConcurrentModificationExceptions
 * across all Minecraft version anchors.
 */
public class DurabilityMultiplierConcurrencyLoadTest {

    @BeforeAll
    public static void init() {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
    }

    @AfterEach
    void tearDown() {
        DurabilityConfig config = DurabilityConfig.get();
        synchronized (config) {
            if (config.forcedItems != null) {
                config.forcedItems.removeIf(id -> id != null && id.startsWith("mod_"));
            }
            if (config.forcedPercentages != null) {
                config.forcedPercentages.keySet().removeIf(id -> id != null && id.startsWith("mod_"));
            }
            if (config.forcedInfinities != null) {
                config.forcedInfinities.keySet().removeIf(id -> id != null && id.startsWith("mod_"));
            }
            if (config.forcedSingleUses != null) {
                config.forcedSingleUses.keySet().removeIf(id -> id != null && id.startsWith("mod_"));
            }
            if (config.dynamicPercentages != null) {
                config.dynamicPercentages.keySet().removeIf(id -> id != null && id.startsWith("mod_"));
            }
            if (config.dynamicInfinities != null) {
                config.dynamicInfinities.keySet().removeIf(id -> id != null && id.startsWith("mod_"));
            }
            if (config.dynamicSingleUses != null) {
                config.dynamicSingleUses.keySet().removeIf(id -> id != null && id.startsWith("mod_"));
            }
        }

        DurabilityPayload resetPayload = new DurabilityPayload(
                100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                true,
                Map.of(), Map.of(), Map.of()
        );
        DurabilityClientState.apply(resetPayload);
    }

    @Test
    @DisplayName("Pillar 2 Concurrency: 50 threads x 400 iterations of calculations, config mutations, and client sync")
    void testConcurrentCalculationsAndState() throws Exception {
        int threadCount = 50;
        int operationsPerThread = 400;

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch readyLatch = new CountDownLatch(threadCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        List<Future<?>> futures = new ArrayList<>();

        for (int t = 0; t < threadCount; t++) {
            final int threadId = t;
            futures.add(executor.submit(() -> {
                readyLatch.countDown();
                try {
                    startLatch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread interrupted while waiting for start", e);
                }

                for (int i = 0; i < operationsPerThread; i++) {
                    // 1. Concurrently perform DurabilityHelper.calculateScaledDamage and assert result >= 0
                    RandomSource random = RandomSource.create(threadId * 1000L + i);
                    int scaled = DurabilityHelper.calculateScaledDamage(1, 200, random);
                    assertTrue(scaled >= 0, "Scaled damage must be non-negative: " + scaled);

                    // 2. Alternating reads and mutations on DurabilityConfig
                    String itemKey = "mod_" + (threadId % 10) + ":item_" + (i % 20);
                    DurabilityConfig.get().recordDiscoveredItem(itemKey);
                    DurabilityConfig.get().setForcedPercent(itemKey, (threadId + i) % 500);
                    int forcedPercent = DurabilityConfig.get().getForcedPercent(itemKey);
                    assertTrue(forcedPercent >= 0, "Forced percent must be non-negative: " + forcedPercent);

                    // 3. Concurrently test client state payload application and queries
                    DurabilityPayload payload = new DurabilityPayload(
                            100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                            false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                            false, (threadId % 2 == 0), false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                            true,
                            Map.of(itemKey, (threadId + i) % 500),
                            Map.of(itemKey, (i % 2 == 0)),
                            Map.of(itemKey, (i % 3 == 0))
                    );
                    DurabilityClientState.apply(payload);
                    DurabilityClientState.isItemInfiniteClient(itemKey);
                    DurabilityClientState.singleUseWeapons();
                }
            }));
        }

        assertTrue(readyLatch.await(10, TimeUnit.SECONDS), "All 50 threads must report ready within 10 seconds");
        startLatch.countDown();

        for (Future<?> f : futures) {
            f.get(15, TimeUnit.SECONDS);
        }

        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS), "Executor must shut down cleanly within 5 seconds");
    }
}
