// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.mixin;

import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MappedRegistry.class)
public abstract class MappedRegistryMixin<T> {

    @Shadow
    public abstract ResourceKey<? extends Registry<T>> key();

    @Inject(method = "freeze", at = @At("TAIL"))
    private void onFreeze(CallbackInfoReturnable<Registry<T>> cir) {
        if (this.key().equals(Registries.ITEM)) {
            // All modded items have been registered and their components have been bound.
            // Temporarily unfreeze BuiltInRegistries.GAME_RULE if it was already frozen, register dynamic rules, then refreeze.
            if (BuiltInRegistries.GAME_RULE instanceof MappedRegistry<?> mappedGameRule) {
                MappedRegistryAccessor accessor = (MappedRegistryAccessor) mappedGameRule;
                boolean wasGameRuleFrozen = accessor.isFrozen();
                if (wasGameRuleFrozen) {
                    accessor.setFrozen(false);
                }
                try {
                    DurabilityRules.registerDynamicRulesOnRegistryFreeze();
                } finally {
                    if (wasGameRuleFrozen) {
                        accessor.setFrozen(true);
                    }
                }
            } else {
                DurabilityRules.registerDynamicRulesOnRegistryFreeze();
            }
        }
    }
}
