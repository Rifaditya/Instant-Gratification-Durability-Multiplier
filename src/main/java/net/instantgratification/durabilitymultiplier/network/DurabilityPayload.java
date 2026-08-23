// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.network;

import net.instantgratification.durabilitymultiplier.DurabilityMultiplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

/**
 * Server-to-client payload carrying all GameRule values.
 * Sent on player join and whenever a durability GameRule changes.
 */
public record DurabilityPayload(
        int percentGlobal,
        int percentWeapons,
        int percentSwords,
        int percentSpears,
        int percentTridents,
        int percentMaces,
        int percentBows,
        int percentCrossbows,
        int percentShields,
        int percentTools,
        int percentPickaxes,
        int percentAxes,
        int percentShovels,
        int percentHoes,
        int percentShears,
        int percentFishingRods,
        int percentBrushes,
        int percentFlintAndSteel,
        int percentArmor,
        int percentHelmets,
        int percentChestplates,
        int percentLeggings,
        int percentBoots,
        int percentElytra,
        
        boolean infinityGlobal,
        boolean infinityWeapons,
        boolean infinitySwords,
        boolean infinitySpears,
        boolean infinityTridents,
        boolean infinityMaces,
        boolean infinityBows,
        boolean infinityCrossbows,
        boolean infinityShields,
        boolean infinityTools,
        boolean infinityPickaxes,
        boolean infinityAxes,
        boolean infinityShovels,
        boolean infinityHoes,
        boolean infinityShears,
        boolean infinityFishingRods,
        boolean infinityBrushes,
        boolean infinityFlintAndSteel,
        boolean infinityArmor,
        boolean infinityHelmets,
        boolean infinityChestplates,
        boolean infinityLeggings,
        boolean infinityBoots,
        boolean infinityElytra,
        
        boolean showTooltip
) implements CustomPacketPayload {

    public static final Type<DurabilityPayload> TYPE = new Type<>(
            Identifier.fromNamespaceAndPath(DurabilityMultiplier.MOD_ID, "sync_rules"));

    public static final StreamCodec<FriendlyByteBuf, DurabilityPayload> CODEC =
            CustomPacketPayload.codec(DurabilityPayload::write, DurabilityPayload::read);

    private void write(FriendlyByteBuf buf) {
        buf.writeVarInt(percentGlobal);
        buf.writeVarInt(percentWeapons);
        buf.writeVarInt(percentSwords);
        buf.writeVarInt(percentSpears);
        buf.writeVarInt(percentTridents);
        buf.writeVarInt(percentMaces);
        buf.writeVarInt(percentBows);
        buf.writeVarInt(percentCrossbows);
        buf.writeVarInt(percentShields);
        buf.writeVarInt(percentTools);
        buf.writeVarInt(percentPickaxes);
        buf.writeVarInt(percentAxes);
        buf.writeVarInt(percentShovels);
        buf.writeVarInt(percentHoes);
        buf.writeVarInt(percentShears);
        buf.writeVarInt(percentFishingRods);
        buf.writeVarInt(percentBrushes);
        buf.writeVarInt(percentFlintAndSteel);
        buf.writeVarInt(percentArmor);
        buf.writeVarInt(percentHelmets);
        buf.writeVarInt(percentChestplates);
        buf.writeVarInt(percentLeggings);
        buf.writeVarInt(percentBoots);
        buf.writeVarInt(percentElytra);
        
        buf.writeBoolean(infinityGlobal);
        buf.writeBoolean(infinityWeapons);
        buf.writeBoolean(infinitySwords);
        buf.writeBoolean(infinitySpears);
        buf.writeBoolean(infinityTridents);
        buf.writeBoolean(infinityMaces);
        buf.writeBoolean(infinityBows);
        buf.writeBoolean(infinityCrossbows);
        buf.writeBoolean(infinityShields);
        buf.writeBoolean(infinityTools);
        buf.writeBoolean(infinityPickaxes);
        buf.writeBoolean(infinityAxes);
        buf.writeBoolean(infinityShovels);
        buf.writeBoolean(infinityHoes);
        buf.writeBoolean(infinityShears);
        buf.writeBoolean(infinityFishingRods);
        buf.writeBoolean(infinityBrushes);
        buf.writeBoolean(infinityFlintAndSteel);
        buf.writeBoolean(infinityArmor);
        buf.writeBoolean(infinityHelmets);
        buf.writeBoolean(infinityChestplates);
        buf.writeBoolean(infinityLeggings);
        buf.writeBoolean(infinityBoots);
        buf.writeBoolean(infinityElytra);
        
        buf.writeBoolean(showTooltip);
    }

    private static DurabilityPayload read(FriendlyByteBuf buf) {
        return new DurabilityPayload(
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                buf.readVarInt(),
                
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                
                buf.readBoolean());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
