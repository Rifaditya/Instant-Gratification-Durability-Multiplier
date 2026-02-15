package net.instantgratification.durabilitymultiplier;

import net.fabricmc.api.ModInitializer;
import net.instantgratification.durabilitymultiplier.network.DurabilityNetworking;

public class DurabilityMultiplierFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        DurabilityMultiplier.init();
        DurabilityNetworking.registerServer();
    }
}
