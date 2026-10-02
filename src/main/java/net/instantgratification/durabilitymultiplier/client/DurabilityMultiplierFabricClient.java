// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.instantgratification.durabilitymultiplier.network.DurabilityClientState;
import net.instantgratification.durabilitymultiplier.network.DurabilityNetworking;
import net.instantgratification.durabilitymultiplier.network.DurabilityPayload;

/**
 * Client entrypoint for Durability Multiplier (Minecraft 1.20.1).
 * Registers the network receiver that populates {@link DurabilityClientState}
 * with GameRule values synced from the server.
 */
@Environment(EnvType.CLIENT)
public class DurabilityMultiplierFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(DurabilityNetworking.CHANNEL_ID,
                (client, handler, buf, responseSender) -> {
                    DurabilityPayload payload = DurabilityPayload.read(buf);
                    client.execute(() -> DurabilityClientState.applyPayload(payload));
                });
    }
}
