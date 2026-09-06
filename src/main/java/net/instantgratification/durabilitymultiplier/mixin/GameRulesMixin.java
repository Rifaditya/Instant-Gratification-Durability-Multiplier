// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.mixin;

import net.instantgratification.durabilitymultiplier.network.DurabilityNetworking;
import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRules;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRules.class)
public class GameRulesMixin {

    @Inject(method = "set", at = @At("TAIL"))
    private <T> void onSet(GameRule<T> key, T value, @Nullable MinecraftServer server, CallbackInfo ci) {
        if (server != null && key.category() == DurabilityRules.DURABILITY_MULTIPLIER) {
            DurabilityNetworking.syncToAll(server);
        }
    }
}
