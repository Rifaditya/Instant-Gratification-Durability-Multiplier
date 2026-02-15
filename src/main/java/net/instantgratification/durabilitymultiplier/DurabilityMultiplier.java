package net.instantgratification.durabilitymultiplier;

import net.instantgratification.durabilitymultiplier.registry.DurabilityRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DurabilityMultiplier {

    public static final String MOD_ID = "durability-multiplier";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        LOGGER.info("Durability Multiplier initializing...");
        DurabilityRules.register();
        LOGGER.info("Durability Multiplier initialized. {} GameRules registered.", 11);
    }
}
