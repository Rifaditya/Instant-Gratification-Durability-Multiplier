package net.instantgratification.durabilitymultiplier;

import net.fabricmc.loader.api.FabricLoader;
import net.instantgratification.durabilitymultiplier.config.DurabilityConfig;
import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DurabilityMultiplier {

    public static final String MOD_ID = "durability-multiplier";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        LOGGER.info("Durability Multiplier initializing...");
        DurabilityConfig.load(FabricLoader.getInstance().getConfigDir());
        DurabilityRules.register();
        LOGGER.info("Durability Multiplier initialized. {} GameRules registered.", 25);
    }
}
