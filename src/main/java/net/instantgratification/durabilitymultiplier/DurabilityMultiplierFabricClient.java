// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.instantgratification.durabilitymultiplier.network.DurabilityClientState;
import net.instantgratification.durabilitymultiplier.network.DurabilityPayload;

/**
 * Client entrypoint. Registers the payload receiver that populates
 * {@link DurabilityClientState} with GameRule values synced from the server.
 */
@Environment(EnvType.CLIENT)
public class DurabilityMultiplierFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(DurabilityPayload.TYPE,
                (payload, context) -> DurabilityClientState.apply(payload));
    }
}
