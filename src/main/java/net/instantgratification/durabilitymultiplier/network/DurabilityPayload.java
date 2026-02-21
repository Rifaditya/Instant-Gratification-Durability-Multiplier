package net.instantgratification.durabilitymultiplier.network;

import net.instantgratification.durabilitymultiplier.DurabilityMultiplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

/**
 * Server-to-client payload carrying all 11 GameRule values.
 * Sent on player join and whenever a durability GameRule changes.
 */
public record DurabilityPayload(
        int multiplierGlobal,
        int multiplierWeapons,
        int multiplierSwords,
        int multiplierSpears,
        int multiplierTridents,
        int multiplierMaces,
        int multiplierBows,
        int multiplierCrossbows,
        int multiplierTools,
        int multiplierArmor,
        int multiplierElytra,
        
        boolean infinityGlobal,
        boolean infinityWeapons,
        boolean infinitySwords,
        boolean infinitySpears,
        boolean infinityTridents,
        boolean infinityMaces,
        boolean infinityBows,
        boolean infinityCrossbows,
        boolean infinityTools,
        boolean infinityArmor,
        boolean infinityElytra,
        
        boolean showTooltip
) implements CustomPacketPayload {

    public static final Type<DurabilityPayload> TYPE = new Type<>(
            Identifier.parse(DurabilityMultiplier.MOD_ID + ":sync_rules"));

    public static final StreamCodec<FriendlyByteBuf, DurabilityPayload> CODEC =
            CustomPacketPayload.codec(DurabilityPayload::write, DurabilityPayload::read);

    private void write(FriendlyByteBuf buf) {
        buf.writeVarInt(multiplierGlobal);
        buf.writeVarInt(multiplierWeapons);
        buf.writeVarInt(multiplierSwords);
        buf.writeVarInt(multiplierSpears);
        buf.writeVarInt(multiplierTridents);
        buf.writeVarInt(multiplierMaces);
        buf.writeVarInt(multiplierBows);
        buf.writeVarInt(multiplierCrossbows);
        buf.writeVarInt(multiplierTools);
        buf.writeVarInt(multiplierArmor);
        buf.writeVarInt(multiplierElytra);
        
        buf.writeBoolean(infinityGlobal);
        buf.writeBoolean(infinityWeapons);
        buf.writeBoolean(infinitySwords);
        buf.writeBoolean(infinitySpears);
        buf.writeBoolean(infinityTridents);
        buf.writeBoolean(infinityMaces);
        buf.writeBoolean(infinityBows);
        buf.writeBoolean(infinityCrossbows);
        buf.writeBoolean(infinityTools);
        buf.writeBoolean(infinityArmor);
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
