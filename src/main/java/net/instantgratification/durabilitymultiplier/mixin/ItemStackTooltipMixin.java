package net.instantgratification.durabilitymultiplier.mixin;

import net.instantgratification.durabilitymultiplier.DurabilityHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.jspecify.annotations.Nullable;
import java.util.function.Consumer;

/**
 * Injects durability status into the item tooltip.
 * Displays "✦ UNBREAKABLE" (gold/bold) or "⟨Nx Category⟩" (gray).
 */
@Mixin(ItemStack.class)
public abstract class ItemStackTooltipMixin {

    @Inject(method = "addDetailsToTooltip", at = @At("TAIL"))
    private void dm$addDurabilityTooltip(Item.TooltipContext context, TooltipDisplay display,
            @Nullable Player player, TooltipFlag tooltipFlag,
            Consumer<Component> builder, CallbackInfo ci) {
        if (player == null)
            return;
        if (!(player.level() instanceof ServerLevel serverLevel))
            return;

        ItemStack self = (ItemStack) (Object) this;
        if (!self.isDamageableItem())
            return;

        if (!DurabilityHelper.shouldShowTooltip(serverLevel))
            return;

        String label = DurabilityHelper.getTooltipLabel(serverLevel, self);
        if (label == null)
            return;

        if (label.equals("UNBREAKABLE")) {
            builder.accept(Component.literal("✦ UNBREAKABLE")
                    .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));
        } else {
            builder.accept(Component.literal("⟨" + label + "⟩")
                    .withStyle(ChatFormatting.GRAY));
        }
    }
}
