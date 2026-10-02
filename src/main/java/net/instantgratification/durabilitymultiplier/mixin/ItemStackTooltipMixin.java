// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.mixin;

import net.instantgratification.durabilitymultiplier.DurabilityHelper;
import net.instantgratification.durabilitymultiplier.network.DurabilityClientState;
import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Injects durability status into item tooltips (Minecraft 1.20.1).
 * Displays "✦ UNBREAKABLE" (gold/bold), "✦ SINGLE-USE" (red/bold), or "⟨...⟩" (gray).
 *
 * <p>Client-side: reads from synced {@link DurabilityClientState}.
 * Server-side (integrated server): reads GameRules directly.</p>
 */
@Mixin(ItemStack.class)
public abstract class ItemStackTooltipMixin {

    @Inject(method = "getTooltipLines", at = @At("RETURN"), cancellable = true)
    private void dm$addDurabilityTooltip(@Nullable Player player, TooltipFlag tooltipFlag,
                                         CallbackInfoReturnable<List<Component>> cir) {
        if (player == null) {
            return;
        }

        ItemStack self = (ItemStack) (Object) this;
        if (self.isEmpty()) {
            return;
        }

        // Guard: check if item is damageable or forced in config/tags
        if (!self.isDamageableItem() && !DurabilityRules.isForcedItem(self)) {
            return;
        }

        String label;
        if (player.level() instanceof ServerLevel serverLevel) {
            // Server-side (integrated server) — check GameRules directly
            if (!DurabilityHelper.shouldShowTooltip(serverLevel)) {
                return;
            }
            label = DurabilityHelper.getTooltipLabel(serverLevel, self);
        } else {
            // Client-side — check DurabilityClientState
            if (!DurabilityClientState.showTooltip()) {
                return;
            }
            label = DurabilityHelper.getTooltipLabelClient(self);
        }

        if (label == null || label.isEmpty()) {
            return;
        }

        Component tooltipComponent;
        if ("UNBREAKABLE".equals(label)) {
            tooltipComponent = Component.literal("✦ UNBREAKABLE")
                    .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD);
        } else if ("SINGLE-USE".equals(label)) {
            tooltipComponent = Component.literal("✦ SINGLE-USE")
                    .withStyle(ChatFormatting.RED, ChatFormatting.BOLD);
        } else {
            tooltipComponent = Component.literal("⟨" + label + "⟩")
                    .withStyle(ChatFormatting.GRAY);
        }

        List<Component> lines = cir.getReturnValue();
        if (lines == null) {
            return;
        }

        try {
            lines.add(tooltipComponent);
        } catch (UnsupportedOperationException e) {
            List<Component> mutableLines = new ArrayList<>(lines);
            mutableLines.add(tooltipComponent);
            cir.setReturnValue(mutableLines);
        }
    }
}
