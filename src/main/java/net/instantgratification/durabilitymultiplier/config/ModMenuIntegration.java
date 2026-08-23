/*
 * Standard Core v2.1
 * Verified against: ModMenuApi.java (ModMenu 18+)
 */
package net.instantgratification.durabilitymultiplier.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.dasik.social.api.config.GuiHelper;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return GuiHelper.getOptionalFactory(
            "durability-multiplier",
            "net.instantgratification.durabilitymultiplier.config.ClothConfigScreenHelper",
            "createFactory"
        );
    }
}
