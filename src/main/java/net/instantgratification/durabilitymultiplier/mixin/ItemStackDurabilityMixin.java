// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.mixin;

import net.instantgratification.durabilitymultiplier.DurabilityHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

/**
 * Intercepts durability damage processing to apply multiplier, single-use, or god mode logic.
 *
 * <p>Funnel point: {@link ItemStack#hurtAndBreak(int, ServerLevel, ServerPlayer, Consumer)}
 * handles all durability loss in Minecraft 1.21.1.</p>
 */
@Mixin(ItemStack.class)
public abstract class ItemStackDurabilityMixin {

    /**
     * Re-entry guard: prevents infinite recursion when re-invoking hurtAndBreak with reduced damage.
     */
    private static final ThreadLocal<Boolean> dm$processing = ThreadLocal.withInitial(() -> Boolean.FALSE);

    /**
     * Intercepts item damage at HEAD of {@code hurtAndBreak} to scale, cancel, or max out durability damage.
     *
     * @param amount the incoming raw durability damage
     * @param level the server world context
     * @param player the player causing damage, or null if caused by another entity/environment
     * @param onBreak consumer callback when the item breaks
     * @param ci mixin callback info
     */
    @Inject(
            method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void dm$hurtAndBreak(
            int amount,
            ServerLevel level,
            @Nullable ServerPlayer player,
            Consumer<Item> onBreak,
            CallbackInfo ci
    ) {
        // Skip if we are already processing a scaled re-call on this thread.
        if (dm$processing.get()) {
            return;
        }

        ItemStack self = (ItemStack) (Object) this;

        // Resolve reduced / scaled damage via DurabilityHelper (handles God Mode 0, Single-Use, and % scaling)
        int reduced = DurabilityHelper.reduceDamage(amount, level, self);

        // God Mode / Unbreakable: cancel all durability loss
        if (reduced == 0) {
            ci.cancel();
            return;
        }

        // Scaled / Single-Use: cancel original damage and re-invoke with the modified amount
        if (reduced != amount) {
            ci.cancel();
            dm$processing.set(Boolean.TRUE);
            try {
                self.hurtAndBreak(reduced, level, player, onBreak);
            } finally {
                dm$processing.set(Boolean.FALSE);
            }
        }
        // If reduced == amount (100% vanilla parity), allow vanilla hurtAndBreak to proceed naturally.
    }
}
