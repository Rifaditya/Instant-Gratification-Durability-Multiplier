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
        
        boolean singleUseGlobal,
        boolean singleUseWeapons,
        boolean singleUseSwords,
        boolean singleUseSpears,
        boolean singleUseTridents,
        boolean singleUseMaces,
        boolean singleUseBows,
        boolean singleUseCrossbows,
        boolean singleUseShields,
        boolean singleUseTools,
        boolean singleUsePickaxes,
        boolean singleUseAxes,
        boolean singleUseShovels,
        boolean singleUseHoes,
        boolean singleUseShears,
        boolean singleUseFishingRods,
        boolean singleUseBrushes,
        boolean singleUseFlintAndSteel,
        boolean singleUseArmor,
        boolean singleUseHelmets,
        boolean singleUseChestplates,
        boolean singleUseLeggings,
        boolean singleUseBoots,
        boolean singleUseElytra,

        boolean showTooltip,
        
        java.util.Map<String, Integer> dynamicPercentages,
        java.util.Map<String, Boolean> dynamicInfinities,
        java.util.Map<String, Boolean> dynamicSingleUses
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

        buf.writeBoolean(singleUseGlobal);
        buf.writeBoolean(singleUseWeapons);
        buf.writeBoolean(singleUseSwords);
        buf.writeBoolean(singleUseSpears);
        buf.writeBoolean(singleUseTridents);
        buf.writeBoolean(singleUseMaces);
        buf.writeBoolean(singleUseBows);
        buf.writeBoolean(singleUseCrossbows);
        buf.writeBoolean(singleUseShields);
        buf.writeBoolean(singleUseTools);
        buf.writeBoolean(singleUsePickaxes);
        buf.writeBoolean(singleUseAxes);
        buf.writeBoolean(singleUseShovels);
        buf.writeBoolean(singleUseHoes);
        buf.writeBoolean(singleUseShears);
        buf.writeBoolean(singleUseFishingRods);
        buf.writeBoolean(singleUseBrushes);
        buf.writeBoolean(singleUseFlintAndSteel);
        buf.writeBoolean(singleUseArmor);
        buf.writeBoolean(singleUseHelmets);
        buf.writeBoolean(singleUseChestplates);
        buf.writeBoolean(singleUseLeggings);
        buf.writeBoolean(singleUseBoots);
        buf.writeBoolean(singleUseElytra);
        
        buf.writeBoolean(showTooltip);
        
        buf.writeMap(dynamicPercentages != null ? dynamicPercentages : java.util.Map.of(), FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeVarInt);
        buf.writeMap(dynamicInfinities != null ? dynamicInfinities : java.util.Map.of(), FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeBoolean);
        buf.writeMap(dynamicSingleUses != null ? dynamicSingleUses : java.util.Map.of(), FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeBoolean);
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
                
                buf.readBoolean(),
                
                buf.readMap(FriendlyByteBuf::readUtf, FriendlyByteBuf::readVarInt),
                buf.readMap(FriendlyByteBuf::readUtf, FriendlyByteBuf::readBoolean),
                buf.readMap(FriendlyByteBuf::readUtf, FriendlyByteBuf::readBoolean));
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
