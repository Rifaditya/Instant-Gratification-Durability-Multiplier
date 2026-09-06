// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.EnumDropdownControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import net.dasik.social.api.config.DasikSupportHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class YaclScreenHelper {
    public static ConfigScreenFactory<?> createScreen() {
        return YaclScreenHelper::buildScreen;
    }

    private static Screen buildScreen(Screen parent) {
        DurabilityConfig config = DurabilityConfig.get();

        // 1. --- PERCENTAGES CATEGORY ---
        var percentagesGroup = OptionGroup.createBuilder()
            .name(Component.translatable("config.durability-multiplier.category.percentages"));

        Option<?> supportButton = (Option<?>) DasikSupportHelper.createYaclButton();
        if (supportButton != null) {
            percentagesGroup.option(supportButton);
        }

        percentagesGroup
            .option(createIntOption("gamerule.ig.dm_percent_global", "gamerule.ig.dm_percent_global.description", 200, () -> config.percentGlobal, val -> config.percentGlobal = val))
            .option(createIntOption("gamerule.ig.dm_percent_weapons", "gamerule.ig.dm_percent_weapons.description", 0, () -> config.percentWeapons, val -> config.percentWeapons = val))
            .option(createIntOption("gamerule.ig.dm_percent_swords", "gamerule.ig.dm_percent_swords.description", 0, () -> config.percentSwords, val -> config.percentSwords = val))
            .option(createIntOption("gamerule.ig.dm_percent_spears", "gamerule.ig.dm_percent_spears.description", 0, () -> config.percentSpears, val -> config.percentSpears = val))
            .option(createIntOption("gamerule.ig.dm_percent_tridents", "gamerule.ig.dm_percent_tridents.description", 0, () -> config.percentTridents, val -> config.percentTridents = val))
            .option(createIntOption("gamerule.ig.dm_percent_maces", "gamerule.ig.dm_percent_maces.description", 0, () -> config.percentMaces, val -> config.percentMaces = val))
            .option(createIntOption("gamerule.ig.dm_percent_bows", "gamerule.ig.dm_percent_bows.description", 0, () -> config.percentBows, val -> config.percentBows = val))
            .option(createIntOption("gamerule.ig.dm_percent_crossbows", "gamerule.ig.dm_percent_crossbows.description", 0, () -> config.percentCrossbows, val -> config.percentCrossbows = val))
            .option(createIntOption("gamerule.ig.dm_percent_tools", "gamerule.ig.dm_percent_tools.description", 0, () -> config.percentTools, val -> config.percentTools = val))
            .option(createIntOption("gamerule.ig.dm_percent_pickaxes", "gamerule.ig.dm_percent_pickaxes.description", 0, () -> config.percentPickaxes, val -> config.percentPickaxes = val))
            .option(createIntOption("gamerule.ig.dm_percent_axes", "gamerule.ig.dm_percent_axes.description", 0, () -> config.percentAxes, val -> config.percentAxes = val))
            .option(createIntOption("gamerule.ig.dm_percent_shovels", "gamerule.ig.dm_percent_shovels.description", 0, () -> config.percentShovels, val -> config.percentShovels = val))
            .option(createIntOption("gamerule.ig.dm_percent_hoes", "gamerule.ig.dm_percent_hoes.description", 0, () -> config.percentHoes, val -> config.percentHoes = val))
            .option(createIntOption("gamerule.ig.dm_percent_shears", "gamerule.ig.dm_percent_shears.description", 0, () -> config.percentShears, val -> config.percentShears = val))
            .option(createIntOption("gamerule.ig.dm_percent_fishing_rods", "gamerule.ig.dm_percent_fishing_rods.description", 0, () -> config.percentFishingRods, val -> config.percentFishingRods = val))
            .option(createIntOption("gamerule.ig.dm_percent_brushes", "gamerule.ig.dm_percent_brushes.description", 0, () -> config.percentBrushes, val -> config.percentBrushes = val))
            .option(createIntOption("gamerule.ig.dm_percent_flint_and_steel", "gamerule.ig.dm_percent_flint_and_steel.description", 0, () -> config.percentFlintAndSteel, val -> config.percentFlintAndSteel = val))
            .option(createIntOption("gamerule.ig.dm_percent_armor", "gamerule.ig.dm_percent_armor.description", 0, () -> config.percentArmor, val -> config.percentArmor = val))
            .option(createIntOption("gamerule.ig.dm_percent_helmets", "gamerule.ig.dm_percent_helmets.description", 0, () -> config.percentHelmets, val -> config.percentHelmets = val))
            .option(createIntOption("gamerule.ig.dm_percent_chestplates", "gamerule.ig.dm_percent_chestplates.description", 0, () -> config.percentChestplates, val -> config.percentChestplates = val))
            .option(createIntOption("gamerule.ig.dm_percent_leggings", "gamerule.ig.dm_percent_leggings.description", 0, () -> config.percentLeggings, val -> config.percentLeggings = val))
            .option(createIntOption("gamerule.ig.dm_percent_boots", "gamerule.ig.dm_percent_boots.description", 0, () -> config.percentBoots, val -> config.percentBoots = val))
            .option(createIntOption("gamerule.ig.dm_percent_elytra", "gamerule.ig.dm_percent_elytra.description", 0, () -> config.percentElytra, val -> config.percentElytra = val))
            .option(createIntOption("gamerule.ig.dm_percent_shields", "gamerule.ig.dm_percent_shields.description", 0, () -> config.percentShields, val -> config.percentShields = val));

        var percentagesCategory = ConfigCategory.createBuilder()
            .name(Component.translatable("config.durability-multiplier.category.percentages"))
            .group(percentagesGroup.build())
            .build();

        // 2. --- GOD MODE CATEGORY ---
        var godModeGroup = OptionGroup.createBuilder()
            .name(Component.translatable("config.durability-multiplier.category.godmode"));

        Option<?> godModeSupportButton = (Option<?>) DasikSupportHelper.createYaclButton();
        if (godModeSupportButton != null) {
            godModeGroup.option(godModeSupportButton);
        }

        godModeGroup
            .option(createBoolOption("gamerule.ig.dm_infinity_global", "gamerule.ig.dm_infinity_global.description", false, () -> config.infinityGlobal, val -> config.infinityGlobal = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_weapons", "gamerule.ig.dm_infinity_weapons.description", false, () -> config.infinityWeapons, val -> config.infinityWeapons = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_swords", "gamerule.ig.dm_infinity_swords.description", false, () -> config.infinitySwords, val -> config.infinitySwords = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_spears", "gamerule.ig.dm_infinity_spears.description", false, () -> config.infinitySpears, val -> config.infinitySpears = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_tridents", "gamerule.ig.dm_infinity_tridents.description", false, () -> config.infinityTridents, val -> config.infinityTridents = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_maces", "gamerule.ig.dm_infinity_maces.description", false, () -> config.infinityMaces, val -> config.infinityMaces = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_bows", "gamerule.ig.dm_infinity_bows.description", false, () -> config.infinityBows, val -> config.infinityBows = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_crossbows", "gamerule.ig.dm_infinity_crossbows.description", false, () -> config.infinityCrossbows, val -> config.infinityCrossbows = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_tools", "gamerule.ig.dm_infinity_tools.description", false, () -> config.infinityTools, val -> config.infinityTools = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_pickaxes", "gamerule.ig.dm_infinity_pickaxes.description", false, () -> config.infinityPickaxes, val -> config.infinityPickaxes = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_axes", "gamerule.ig.dm_infinity_axes.description", false, () -> config.infinityAxes, val -> config.infinityAxes = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_shovels", "gamerule.ig.dm_infinity_shovels.description", false, () -> config.infinityShovels, val -> config.infinityShovels = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_hoes", "gamerule.ig.dm_infinity_hoes.description", false, () -> config.infinityHoes, val -> config.infinityHoes = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_shears", "gamerule.ig.dm_infinity_shears.description", false, () -> config.infinityShears, val -> config.infinityShears = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_fishing_rods", "gamerule.ig.dm_infinity_fishing_rods.description", false, () -> config.infinityFishingRods, val -> config.infinityFishingRods = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_brushes", "gamerule.ig.dm_infinity_brushes.description", false, () -> config.infinityBrushes, val -> config.infinityBrushes = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_flint_and_steel", "gamerule.ig.dm_infinity_flint_and_steel.description", false, () -> config.infinityFlintAndSteel, val -> config.infinityFlintAndSteel = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_armor", "gamerule.ig.dm_infinity_armor.description", false, () -> config.infinityArmor, val -> config.infinityArmor = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_helmets", "gamerule.ig.dm_infinity_helmets.description", false, () -> config.infinityHelmets, val -> config.infinityHelmets = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_chestplates", "gamerule.ig.dm_infinity_chestplates.description", false, () -> config.infinityChestplates, val -> config.infinityChestplates = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_leggings", "gamerule.ig.dm_infinity_leggings.description", false, () -> config.infinityLeggings, val -> config.infinityLeggings = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_boots", "gamerule.ig.dm_infinity_boots.description", false, () -> config.infinityBoots, val -> config.infinityBoots = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_elytra", "gamerule.ig.dm_infinity_elytra.description", false, () -> config.infinityElytra, val -> config.infinityElytra = val))
            .option(createBoolOption("gamerule.ig.dm_infinity_shields", "gamerule.ig.dm_infinity_shields.description", false, () -> config.infinityShields, val -> config.infinityShields = val));

        var godModeCategory = ConfigCategory.createBuilder()
            .name(Component.translatable("config.durability-multiplier.category.godmode"))
            .group(godModeGroup.build())
            .build();

        // 3. --- SINGLE-USE CATEGORY ---
        var singleUseGroup = OptionGroup.createBuilder()
            .name(Component.translatable("config.durability-multiplier.category.singleuse"));

        Option<?> singleUseSupportButton = (Option<?>) DasikSupportHelper.createYaclButton();
        if (singleUseSupportButton != null) {
            singleUseGroup.option(singleUseSupportButton);
        }

        singleUseGroup
            .option(createBoolOption("gamerule.ig.dm_single_use_global", "gamerule.ig.dm_single_use_global.description", false, () -> config.singleUseGlobal, val -> config.singleUseGlobal = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_weapons", "gamerule.ig.dm_single_use_weapons.description", false, () -> config.singleUseWeapons, val -> config.singleUseWeapons = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_swords", "gamerule.ig.dm_single_use_swords.description", false, () -> config.singleUseSwords, val -> config.singleUseSwords = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_spears", "gamerule.ig.dm_single_use_spears.description", false, () -> config.singleUseSpears, val -> config.singleUseSpears = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_tridents", "gamerule.ig.dm_single_use_tridents.description", false, () -> config.singleUseTridents, val -> config.singleUseTridents = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_maces", "gamerule.ig.dm_single_use_maces.description", false, () -> config.singleUseMaces, val -> config.singleUseMaces = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_bows", "gamerule.ig.dm_single_use_bows.description", false, () -> config.singleUseBows, val -> config.singleUseBows = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_crossbows", "gamerule.ig.dm_single_use_crossbows.description", false, () -> config.singleUseCrossbows, val -> config.singleUseCrossbows = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_tools", "gamerule.ig.dm_single_use_tools.description", false, () -> config.singleUseTools, val -> config.singleUseTools = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_pickaxes", "gamerule.ig.dm_single_use_pickaxes.description", false, () -> config.singleUsePickaxes, val -> config.singleUsePickaxes = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_axes", "gamerule.ig.dm_single_use_axes.description", false, () -> config.singleUseAxes, val -> config.singleUseAxes = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_shovels", "gamerule.ig.dm_single_use_shovels.description", false, () -> config.singleUseShovels, val -> config.singleUseShovels = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_hoes", "gamerule.ig.dm_single_use_hoes.description", false, () -> config.singleUseHoes, val -> config.singleUseHoes = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_shears", "gamerule.ig.dm_single_use_shears.description", false, () -> config.singleUseShears, val -> config.singleUseShears = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_fishing_rods", "gamerule.ig.dm_single_use_fishing_rods.description", false, () -> config.singleUseFishingRods, val -> config.singleUseFishingRods = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_brushes", "gamerule.ig.dm_single_use_brushes.description", false, () -> config.singleUseBrushes, val -> config.singleUseBrushes = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_flint_and_steel", "gamerule.ig.dm_single_use_flint_and_steel.description", false, () -> config.singleUseFlintAndSteel, val -> config.singleUseFlintAndSteel = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_armor", "gamerule.ig.dm_single_use_armor.description", false, () -> config.singleUseArmor, val -> config.singleUseArmor = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_helmets", "gamerule.ig.dm_single_use_helmets.description", false, () -> config.singleUseHelmets, val -> config.singleUseHelmets = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_chestplates", "gamerule.ig.dm_single_use_chestplates.description", false, () -> config.singleUseChestplates, val -> config.singleUseChestplates = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_leggings", "gamerule.ig.dm_single_use_leggings.description", false, () -> config.singleUseLeggings, val -> config.singleUseLeggings = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_boots", "gamerule.ig.dm_single_use_boots.description", false, () -> config.singleUseBoots, val -> config.singleUseBoots = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_elytra", "gamerule.ig.dm_single_use_elytra.description", false, () -> config.singleUseElytra, val -> config.singleUseElytra = val))
            .option(createBoolOption("gamerule.ig.dm_single_use_shields", "gamerule.ig.dm_single_use_shields.description", false, () -> config.singleUseShields, val -> config.singleUseShields = val));

        var singleUseCategory = ConfigCategory.createBuilder()
            .name(Component.translatable("config.durability-multiplier.category.singleuse"))
            .group(singleUseGroup.build())
            .build();

        // 4. --- GENERAL CATEGORY ---
        var generalGroup = OptionGroup.createBuilder()
            .name(Component.translatable("config.durability-multiplier.category.general"));

        Option<?> generalSupportButton = (Option<?>) DasikSupportHelper.createYaclButton();
        if (generalSupportButton != null) {
            generalGroup.option(generalSupportButton);
        }

        generalGroup
            .option(Option.<Boolean>createBuilder()
                .name(Component.translatable("gamerule.ig.dm_show_tooltip"))
                .description(OptionDescription.of(Component.translatable("gamerule.ig.dm_show_tooltip.description")))
                .binding(true, () -> config.showTooltip, val -> config.showTooltip = val)
                .controller(BooleanControllerBuilder::create)
                .build())
            .option(Option.<DurabilityConfig.TooltipFormat>createBuilder()
                .name(Component.translatable("config.durability-multiplier.tooltip_format"))
                .description(OptionDescription.of(Component.translatable("config.durability-multiplier.tooltip_format.description")))
                .binding(DurabilityConfig.TooltipFormat.ADAPTIVE, () -> config.tooltipFormat, val -> config.tooltipFormat = val)
                .controller(EnumDropdownControllerBuilder::create)
                .build());

        var generalCategory = ConfigCategory.createBuilder()
            .name(Component.translatable("config.durability-multiplier.category.general"))
            .group(generalGroup.build())
            .build();

        // 5. --- MODDED ITEMS CATEGORY ---
        var moddedCategoryBuilder = ConfigCategory.createBuilder()
            .name(Component.translatable("config.durability-multiplier.category.modded"));

        var moddedItemsGroup = OptionGroup.createBuilder()
            .name(Component.translatable("config.durability-multiplier.category.modded"));

        Option<?> moddedSupportButton = (Option<?>) DasikSupportHelper.createYaclButton();
        if (moddedSupportButton != null) {
            moddedItemsGroup.option(moddedSupportButton);
        }
        moddedCategoryBuilder.group(moddedItemsGroup.build());

        boolean hasModdedItems = !DurabilityRules.DYNAMIC_ITEMS.isEmpty();
        if (hasModdedItems) {
            Map<String, List<Identifier>> byMod = new TreeMap<>();
            for (Identifier id : DurabilityRules.DYNAMIC_ITEMS) {
                byMod.computeIfAbsent(id.getNamespace(), k -> new ArrayList<>()).add(id);
            }

            for (Map.Entry<String, List<Identifier>> entry : byMod.entrySet()) {
                String modNamespace = entry.getKey();
                String modTitle = formatModNamespace(modNamespace);
                var modGroup = OptionGroup.createBuilder()
                    .name(Component.literal("§6" + modTitle + "§r (" + modNamespace + ")"))
                    .collapsed(true);

                for (Identifier id : entry.getValue()) {
                    Item item = BuiltInRegistries.ITEM.getValue(id);
                    if (item == null) continue;

                    String itemKey = id.toString();

                    modGroup.option(Option.<Integer>createBuilder()
                        .name(Component.translatable(item.getDescriptionId()).append(" Percent"))
                        .description(OptionDescription.of(Component.literal("Specific percentage for " + itemKey + " (-1 = Single-Use, 0 = Inherit, >0 = Percentage). Default: 0.")))
                        .binding(0, () -> config.getForcedPercent(itemKey), val -> config.setForcedPercent(itemKey, val))
                        .controller(opt -> IntegerFieldControllerBuilder.create(opt).min(-1).max(Integer.MAX_VALUE))
                        .build());

                    modGroup.option(Option.<Boolean>createBuilder()
                        .name(Component.translatable(item.getDescriptionId()).append(" God Mode"))
                        .description(OptionDescription.of(Component.literal("Enable unbreakable god mode for " + itemKey + ". Default: false.")))
                        .binding(false, () -> config.getForcedInfinity(itemKey), val -> config.setForcedInfinity(itemKey, val))
                        .controller(BooleanControllerBuilder::create)
                        .build());

                    modGroup.option(Option.<Boolean>createBuilder()
                        .name(Component.translatable(item.getDescriptionId()).append(" Single-Use"))
                        .description(OptionDescription.of(Component.literal("Enable 1-hit break mode for " + itemKey + ". Default: false.")))
                        .binding(false, () -> config.getForcedSingleUse(itemKey), val -> config.setForcedSingleUse(itemKey, val))
                        .controller(BooleanControllerBuilder::create)
                        .build());
                }

                moddedCategoryBuilder.group(modGroup.build());
            }
        } else {
            var emptyGroup = OptionGroup.createBuilder()
                .name(Component.translatable("config.durability-multiplier.no_modded_items"));
            moddedCategoryBuilder.group(emptyGroup.build());
        }

        return YetAnotherConfigLib.createBuilder()
            .title(Component.translatable("config.durability-multiplier.title"))
            .category(percentagesCategory)
            .category(godModeCategory)
            .category(singleUseCategory)
            .category(generalCategory)
            .category(moddedCategoryBuilder.build())
            .save(DurabilityConfig::save)
            .build()
            .generateScreen(parent);
    }

    private static Option<Integer> createIntOption(String nameKey, String descKey, int def, Supplier<Integer> getter, Consumer<Integer> setter) {
        return Option.<Integer>createBuilder()
            .name(Component.translatable(nameKey))
            .description(OptionDescription.of(Component.translatable(descKey)))
            .binding(def, getter, setter)
            .controller(opt -> IntegerFieldControllerBuilder.create(opt).min(-1).max(Integer.MAX_VALUE))
            .build();
    }

    private static Option<Boolean> createBoolOption(String nameKey, String descKey, boolean def, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        return Option.<Boolean>createBuilder()
            .name(Component.translatable(nameKey))
            .description(OptionDescription.of(Component.translatable(descKey)))
            .binding(def, getter, setter)
            .controller(BooleanControllerBuilder::create)
            .build();
    }

    private static String formatModNamespace(String namespace) {
        if (namespace == null || namespace.isEmpty()) return "Unknown Mod";
        String[] parts = namespace.split("[_-]");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (part.isEmpty()) continue;
            sb.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
            if (i < parts.length - 1) sb.append(" ");
        }
        return sb.toString();
    }
}
