// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.mixin;

import net.instantgratification.durabilitymultiplier.DurabilityHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.jspecify.annotations.Nullable;
import java.util.function.Consumer;

/**
 * Intercepts durability damage processing to apply multiplier/infinity logic.
 * Damage reduction approach: a 2x multiplier halves incoming damage.
 * Infinity: cancels all damage.
 */
@Mixin(ItemStack.class)
public abstract class ItemStackDurabilityMixin {

    /**
     * Re-entry guard: prevents infinite recursion on re-call with reduced damage.
     */
    private static final ThreadLocal<Boolean> dm$processing = ThreadLocal.withInitial(() -> Boolean.FALSE);

    /**
     * Inject at HEAD of hurtAndBreak to reduce or cancel durability damage.
     * This is the single funnel point for all durability loss in the game.
     *
     * Target: {@code hurtAndBreak(int, ServerLevel, ServerPlayer, Consumer<Item>)}
     */
    @Inject(method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V", at = @At("HEAD"), cancellable = true)
    private void dm$hurtAndBreak(int amount, ServerLevel level, @Nullable ServerPlayer player,
            Consumer<Item> onBreak, CallbackInfo ci) {
        // Skip if we're already processing a reduced re-call.
        if (dm$processing.get())
            return;

        ItemStack self = (ItemStack) (Object) this;

        int reduced = DurabilityHelper.reduceDamage(amount, level, self);
        if (reduced == 0) {
            ci.cancel();
            return;
        }

        // If damage was reduced, cancel original and re-call with reduced amount.
        if (reduced != amount) {
            ci.cancel();
            dm$processing.set(Boolean.TRUE);
            try {
                self.hurtAndBreak(reduced, level, player, onBreak);
            } finally {
                dm$processing.set(Boolean.FALSE);
            }
        }
        // If reduced == amount (multiplier is 1), let vanilla proceed.
    }
}
