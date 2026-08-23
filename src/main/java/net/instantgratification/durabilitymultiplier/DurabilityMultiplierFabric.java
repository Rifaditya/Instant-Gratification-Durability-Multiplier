package net.instantgratification.durabilitymultiplier;

import net.fabricmc.api.ModInitializer;
import net.instantgratification.durabilitymultiplier.network.DurabilityNetworking;
import net.instantgratification.durabilitymultiplier.util.ModVersionGuard;

public class DurabilityMultiplierFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModVersionGuard.checkClass("Durability Multiplier", "net.minecraft.world.entity.EntityTypes");
        DurabilityMultiplier.init();
        DurabilityNetworking.registerServer();
    }
}
