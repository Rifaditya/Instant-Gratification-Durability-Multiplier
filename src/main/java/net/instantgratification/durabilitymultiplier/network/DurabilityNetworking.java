package net.instantgratification.durabilitymultiplier.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

/**
 * Handles registration of the {@link DurabilityPayload} and server-side
 * sending logic. Called from the main Fabric entrypoint.
 */
public final class DurabilityNetworking {

    private DurabilityNetworking() {
    }

    /** Register payload type and server-side event hooks. */
    public static void registerServer() {
        PayloadTypeRegistry.clientboundPlay().register(DurabilityPayload.TYPE, DurabilityPayload.CODEC);

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->
                syncToPlayer(handler.getPlayer()));
    }

    /** Build and send the current GameRule snapshot to a single player. */
    public static void syncToPlayer(ServerPlayer player) {
        ServerLevel level = player.level();
        DurabilityPayload payload = new DurabilityPayload(
                level.getGameRules().get(DurabilityRules.DM_MULTIPLIER_GLOBAL),
                level.getGameRules().get(DurabilityRules.DM_MULTIPLIER_WEAPONS),
                level.getGameRules().get(DurabilityRules.DM_MULTIPLIER_SWORDS),
                level.getGameRules().get(DurabilityRules.DM_MULTIPLIER_SPEARS),
                level.getGameRules().get(DurabilityRules.DM_MULTIPLIER_TRIDENTS),
                level.getGameRules().get(DurabilityRules.DM_MULTIPLIER_MACES),
                level.getGameRules().get(DurabilityRules.DM_MULTIPLIER_BOWS),
                level.getGameRules().get(DurabilityRules.DM_MULTIPLIER_CROSSBOWS),
                level.getGameRules().get(DurabilityRules.DM_MULTIPLIER_TOOLS),
                level.getGameRules().get(DurabilityRules.DM_MULTIPLIER_ARMOR),
                level.getGameRules().get(DurabilityRules.DM_MULTIPLIER_ELYTRA),

                level.getGameRules().get(DurabilityRules.DM_INFINITY_GLOBAL),
                level.getGameRules().get(DurabilityRules.DM_INFINITY_WEAPONS),
                level.getGameRules().get(DurabilityRules.DM_INFINITY_SWORDS),
                level.getGameRules().get(DurabilityRules.DM_INFINITY_SPEARS),
                level.getGameRules().get(DurabilityRules.DM_INFINITY_TRIDENTS),
                level.getGameRules().get(DurabilityRules.DM_INFINITY_MACES),
                level.getGameRules().get(DurabilityRules.DM_INFINITY_BOWS),
                level.getGameRules().get(DurabilityRules.DM_INFINITY_CROSSBOWS),
                level.getGameRules().get(DurabilityRules.DM_INFINITY_TOOLS),
                level.getGameRules().get(DurabilityRules.DM_INFINITY_ARMOR),
                level.getGameRules().get(DurabilityRules.DM_INFINITY_ELYTRA),

                level.getGameRules().get(DurabilityRules.DM_SHOW_TOOLTIP));
        ServerPlayNetworking.send(player, payload);
    }

    /** Broadcast the current GameRule snapshot to all connected players. */
    public static void syncToAll(net.minecraft.server.MinecraftServer server) {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            syncToPlayer(player);
        }
    }

    /** Broadcast the current GameRule snapshot to all connected players (legacy/helper). */
    public static void syncToAll(ServerLevel level) {
         syncToAll(level.getServer());
    }
}
