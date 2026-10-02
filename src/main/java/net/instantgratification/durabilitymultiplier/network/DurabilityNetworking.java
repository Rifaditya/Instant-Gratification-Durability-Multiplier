// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

/**
 * Handles registration of {@link DurabilityPayload} and server-side
 * network synchronization for Minecraft 1.21.1.
 */
public final class DurabilityNetworking {

    private DurabilityNetworking() {
    }

    /** Register payload type and server-side event hooks. */
    public static void registerServer() {
        PayloadTypeRegistry.playS2C().register(DurabilityPayload.TYPE, DurabilityPayload.STREAM_CODEC);

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->
                syncToPlayer(handler.getPlayer()));
    }

    /** Build the payload snapshot for a given ServerLevel. */
    public static DurabilityPayload createPayload(ServerLevel level) {
        return new DurabilityPayload(
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_GLOBAL),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_WEAPONS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_SWORDS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_SPEARS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_TRIDENTS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_MACES),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_BOWS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_CROSSBOWS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_SHIELDS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_TOOLS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_PICKAXES),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_AXES),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_SHOVELS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_HOES),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_SHEARS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_FISHING_RODS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_BRUSHES),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_FLINT_AND_STEEL),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_ARMOR),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_HELMETS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_CHESTPLATES),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_LEGGINGS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_BOOTS),
                DurabilityRules.getInt(level, DurabilityRules.DM_PERCENT_ELYTRA),

                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_GLOBAL),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_WEAPONS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_SWORDS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_SPEARS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_TRIDENTS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_MACES),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_BOWS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_CROSSBOWS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_SHIELDS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_TOOLS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_PICKAXES),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_AXES),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_SHOVELS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_HOES),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_SHEARS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_FISHING_RODS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_BRUSHES),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_FLINT_AND_STEEL),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_ARMOR),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_HELMETS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_CHESTPLATES),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_LEGGINGS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_BOOTS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_INFINITY_ELYTRA),

                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_GLOBAL),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_WEAPONS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_SWORDS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_SPEARS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_TRIDENTS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_MACES),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_BOWS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_CROSSBOWS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_SHIELDS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_TOOLS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_PICKAXES),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_AXES),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_SHOVELS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_HOES),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_SHEARS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_FISHING_RODS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_BRUSHES),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_FLINT_AND_STEEL),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_ARMOR),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_HELMETS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_CHESTPLATES),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_LEGGINGS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_BOOTS),
                DurabilityRules.getBoolean(level, DurabilityRules.DM_SINGLE_USE_ELYTRA),

                DurabilityRules.getBoolean(level, DurabilityRules.DM_SHOW_TOOLTIP),

                DurabilityRules.getActiveDynamicPercentages(level),
                DurabilityRules.getActiveDynamicInfinities(level),
                DurabilityRules.getActiveDynamicSingleUses(level)
        );
    }

    /** Build and send the current GameRule snapshot to a single player. */
    public static void syncToPlayer(ServerPlayer player) {
        if (player == null) return;
        ServerLevel level = player.serverLevel();
        if (level == null) return;
        DurabilityPayload payload = createPayload(level);
        ServerPlayNetworking.send(player, payload);
    }

    /** Broadcast the current GameRule snapshot to all connected players. */
    public static void syncToAll(MinecraftServer server) {
        if (server == null || server.getPlayerList() == null) return;
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            syncToPlayer(player);
        }
    }

    /** Broadcast the current GameRule snapshot to all connected players (legacy/helper). */
    public static void syncToAll(ServerLevel level) {
        if (level != null && level.getServer() != null) {
            syncToAll(level.getServer());
        }
    }
}
