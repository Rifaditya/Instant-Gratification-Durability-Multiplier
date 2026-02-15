package net.instantgratification.durabilitymultiplier;

import net.fabricmc.api.ModInitializer;

public class DurabilityMultiplierFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        DurabilityMultiplier.init();
    }
}
