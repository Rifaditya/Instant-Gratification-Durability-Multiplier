// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.durabilitymultiplier.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;

public class ClothConfigScreenHelper {
    public static ConfigScreenFactory<?> createFactory() {
        return ClothConfigScreenHelper::createScreen;
    }

    public static Screen createScreen(Screen parent) {
        DurabilityConfig config = DurabilityConfig.get();
        ConfigBuilder builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(Component.translatable("config.durability-multiplier.title"));

        builder.setSavingRunnable(DurabilityConfig::save);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        // --- PERCENTAGES CATEGORY ---
        ConfigCategory percentages = builder.getOrCreateCategory(Component.translatable("config.durability-multiplier.category.percentages"));
        percentages.addEntry(entryBuilder.startTextDescription(Component.translatable("config.durability-multiplier.warning")).build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_global"), config.percentGlobal)
            .setDefaultValue(200)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_global.description"))
            .setSaveConsumer(val -> config.percentGlobal = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_weapons"), config.percentWeapons)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_weapons.description"))
            .setSaveConsumer(val -> config.percentWeapons = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_swords"), config.percentSwords)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_swords.description"))
            .setSaveConsumer(val -> config.percentSwords = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_spears"), config.percentSpears)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_spears.description"))
            .setSaveConsumer(val -> config.percentSpears = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_tridents"), config.percentTridents)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_tridents.description"))
            .setSaveConsumer(val -> config.percentTridents = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_maces"), config.percentMaces)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_maces.description"))
            .setSaveConsumer(val -> config.percentMaces = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_bows"), config.percentBows)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_bows.description"))
            .setSaveConsumer(val -> config.percentBows = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_crossbows"), config.percentCrossbows)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_crossbows.description"))
            .setSaveConsumer(val -> config.percentCrossbows = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_tools"), config.percentTools)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_tools.description"))
            .setSaveConsumer(val -> config.percentTools = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_pickaxes"), config.percentPickaxes)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_pickaxes.description"))
            .setSaveConsumer(val -> config.percentPickaxes = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_axes"), config.percentAxes)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_axes.description"))
            .setSaveConsumer(val -> config.percentAxes = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_shovels"), config.percentShovels)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_shovels.description"))
            .setSaveConsumer(val -> config.percentShovels = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_hoes"), config.percentHoes)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_hoes.description"))
            .setSaveConsumer(val -> config.percentHoes = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_shears"), config.percentShears)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_shears.description"))
            .setSaveConsumer(val -> config.percentShears = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_fishing_rods"), config.percentFishingRods)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_fishing_rods.description"))
            .setSaveConsumer(val -> config.percentFishingRods = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_brushes"), config.percentBrushes)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_brushes.description"))
            .setSaveConsumer(val -> config.percentBrushes = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_flint_and_steel"), config.percentFlintAndSteel)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_flint_and_steel.description"))
            .setSaveConsumer(val -> config.percentFlintAndSteel = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_armor"), config.percentArmor)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_armor.description"))
            .setSaveConsumer(val -> config.percentArmor = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_helmets"), config.percentHelmets)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_helmets.description"))
            .setSaveConsumer(val -> config.percentHelmets = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_chestplates"), config.percentChestplates)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_chestplates.description"))
            .setSaveConsumer(val -> config.percentChestplates = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_leggings"), config.percentLeggings)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_leggings.description"))
            .setSaveConsumer(val -> config.percentLeggings = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_boots"), config.percentBoots)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_boots.description"))
            .setSaveConsumer(val -> config.percentBoots = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_elytra"), config.percentElytra)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_elytra.description"))
            .setSaveConsumer(val -> config.percentElytra = val)
            .build());

        percentages.addEntry(entryBuilder.startIntField(Component.translatable("gamerule.ig.dm_percent_shields"), config.percentShields)
            .setDefaultValue(0)
            .setMin(0)
            .setTooltip(Component.translatable("gamerule.ig.dm_percent_shields.description"))
            .setSaveConsumer(val -> config.percentShields = val)
            .build());


        // --- GOD MODE CATEGORY ---
        ConfigCategory godMode = builder.getOrCreateCategory(Component.translatable("config.durability-multiplier.category.godmode"));
        godMode.addEntry(entryBuilder.startTextDescription(Component.translatable("config.durability-multiplier.warning")).build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_global"), config.infinityGlobal)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_global.description"))
            .setSaveConsumer(val -> config.infinityGlobal = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_weapons"), config.infinityWeapons)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_weapons.description"))
            .setSaveConsumer(val -> config.infinityWeapons = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_swords"), config.infinitySwords)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_swords.description"))
            .setSaveConsumer(val -> config.infinitySwords = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_spears"), config.infinitySpears)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_spears.description"))
            .setSaveConsumer(val -> config.infinitySpears = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_tridents"), config.infinityTridents)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_tridents.description"))
            .setSaveConsumer(val -> config.infinityTridents = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_maces"), config.infinityMaces)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_maces.description"))
            .setSaveConsumer(val -> config.infinityMaces = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_bows"), config.infinityBows)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_bows.description"))
            .setSaveConsumer(val -> config.infinityBows = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_crossbows"), config.infinityCrossbows)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_crossbows.description"))
            .setSaveConsumer(val -> config.infinityCrossbows = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_tools"), config.infinityTools)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_tools.description"))
            .setSaveConsumer(val -> config.infinityTools = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_pickaxes"), config.infinityPickaxes)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_pickaxes.description"))
            .setSaveConsumer(val -> config.infinityPickaxes = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_axes"), config.infinityAxes)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_axes.description"))
            .setSaveConsumer(val -> config.infinityAxes = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_shovels"), config.infinityShovels)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_shovels.description"))
            .setSaveConsumer(val -> config.infinityShovels = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_hoes"), config.infinityHoes)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_hoes.description"))
            .setSaveConsumer(val -> config.infinityHoes = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_shears"), config.infinityShears)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_shears.description"))
            .setSaveConsumer(val -> config.infinityShears = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_fishing_rods"), config.infinityFishingRods)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_fishing_rods.description"))
            .setSaveConsumer(val -> config.infinityFishingRods = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_brushes"), config.infinityBrushes)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_brushes.description"))
            .setSaveConsumer(val -> config.infinityBrushes = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_flint_and_steel"), config.infinityFlintAndSteel)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_flint_and_steel.description"))
            .setSaveConsumer(val -> config.infinityFlintAndSteel = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_armor"), config.infinityArmor)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_armor.description"))
            .setSaveConsumer(val -> config.infinityArmor = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_helmets"), config.infinityHelmets)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_helmets.description"))
            .setSaveConsumer(val -> config.infinityHelmets = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_chestplates"), config.infinityChestplates)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_chestplates.description"))
            .setSaveConsumer(val -> config.infinityChestplates = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_leggings"), config.infinityLeggings)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_leggings.description"))
            .setSaveConsumer(val -> config.infinityLeggings = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_boots"), config.infinityBoots)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_boots.description"))
            .setSaveConsumer(val -> config.infinityBoots = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_elytra"), config.infinityElytra)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_elytra.description"))
            .setSaveConsumer(val -> config.infinityElytra = val)
            .build());

        godMode.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_infinity_shields"), config.infinityShields)
            .setDefaultValue(false)
            .setTooltip(Component.translatable("gamerule.ig.dm_infinity_shields.description"))
            .setSaveConsumer(val -> config.infinityShields = val)
            .build());


        // --- GENERAL CATEGORY ---
        ConfigCategory general = builder.getOrCreateCategory(Component.translatable("config.durability-multiplier.category.general"));
        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("gamerule.ig.dm_show_tooltip"), config.showTooltip)
            .setDefaultValue(true)
            .setTooltip(Component.translatable("gamerule.ig.dm_show_tooltip.description"))
            .setSaveConsumer(val -> config.showTooltip = val)
            .build());

        general.addEntry(entryBuilder.startEnumSelector(Component.translatable("config.durability-multiplier.tooltip_format"), DurabilityConfig.TooltipFormat.class, config.tooltipFormat)
            .setDefaultValue(DurabilityConfig.TooltipFormat.ADAPTIVE)
            .setTooltip(Component.translatable("config.durability-multiplier.tooltip_format.description"))
            .setSaveConsumer(val -> config.tooltipFormat = val)
            .build());

        // --- MODDED ITEMS CATEGORY ---
        ConfigCategory modded = builder.getOrCreateCategory(Component.translatable("config.durability-multiplier.category.modded"));
        modded.addEntry(entryBuilder.startTextDescription(Component.translatable("config.durability-multiplier.warning")).build());

        boolean hasModdedItems = !net.instantgratification.durabilitymultiplier.registry.DurabilityRules.DYNAMIC_ITEMS.isEmpty();
        if (hasModdedItems) {
            java.util.Map<String, java.util.List<Identifier>> byMod = new java.util.TreeMap<>();
            for (Identifier id : net.instantgratification.durabilitymultiplier.registry.DurabilityRules.DYNAMIC_ITEMS) {
                byMod.computeIfAbsent(id.getNamespace(), k -> new java.util.ArrayList<>()).add(id);
            }

            for (java.util.Map.Entry<String, java.util.List<Identifier>> entry : byMod.entrySet()) {
                String modNamespace = entry.getKey();
                String modTitle = formatModNamespace(modNamespace);
                var modCategory = entryBuilder.startSubCategory(
                        Component.literal("§6" + modTitle + "§r (" + modNamespace + ")"));

                for (Identifier id : entry.getValue()) {
                    Item item = BuiltInRegistries.ITEM.getValue(id);
                    if (item == null) continue;

                    String itemKey = id.toString();

                    // Add Percentage field
                    modCategory.add(entryBuilder.startIntField(
                        Component.translatable(item.getDescriptionId()).append(" Percent"),
                        config.dynamicPercentages.getOrDefault(itemKey, 0))
                            .setDefaultValue(0)
                            .setMin(0)
                            .setTooltip(Component.literal("Specific percentage for " + itemKey + ". Overrides category/global if > 0. Set to 0 to inherit. Default: 0."))
                            .setSaveConsumer(val -> config.dynamicPercentages.put(itemKey, val))
                            .build());

                    // Add Infinity toggle
                    modCategory.add(entryBuilder.startBooleanToggle(
                        Component.translatable(item.getDescriptionId()).append(" God Mode"),
                        config.dynamicInfinities.getOrDefault(itemKey, false))
                            .setDefaultValue(false)
                            .setTooltip(Component.literal("Enable unbreakable god mode for " + itemKey + ". Default: false."))
                            .setSaveConsumer(val -> config.dynamicInfinities.put(itemKey, val))
                            .build());
                }

                modded.addEntry(modCategory.build());
            }
        } else {
            modded.addEntry(entryBuilder.startTextDescription(Component.translatable("config.durability-multiplier.no_modded_items")).build());
        }

        return builder.build();
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
