// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.instantgratification.durabilitymultiplier.config.DurabilityConfig;
import net.instantgratification.durabilitymultiplier.network.DurabilityNetworking;
import net.instantgratification.durabilitymultiplier.util.ModVersionGuard;

public class DurabilityMultiplierFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModVersionGuard.checkClass("Durability Multiplier", "net.minecraft.world.entity.EntityType");
        DurabilityMultiplier.init();
        DurabilityNetworking.registerServer();
        ServerLifecycleEvents.SERVER_STARTING.register(server -> DurabilityConfig.saveIfDirty());
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> DurabilityConfig.saveIfDirty());
    }
}
