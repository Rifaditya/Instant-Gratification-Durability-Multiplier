package net.instantgratification.durabilitymultiplier.network;

/**
 * Client-side cache for synced GameRule values.
 * Populated by {@link DurabilityPayload} received from the server.
 * Read by the tooltip mixin instead of accessing ServerLevel GameRules.
 */
public final class DurabilityClientState {

    private static int multiplierGlobal = 2;
    private static int multiplierSwords;
    private static int multiplierTools;
    private static int multiplierArmor;
    private static int multiplierElytra;
    private static boolean infinityGlobal;
    private static boolean infinitySwords;
    private static boolean infinityTools;
    private static boolean infinityArmor;
    private static boolean infinityElytra;
    private static boolean showTooltip = true;

    private DurabilityClientState() {
    }

    /** Apply received payload from the server. */
    public static void apply(DurabilityPayload payload) {
        multiplierGlobal = payload.multiplierGlobal();
        multiplierSwords = payload.multiplierSwords();
        multiplierTools = payload.multiplierTools();
        multiplierArmor = payload.multiplierArmor();
        multiplierElytra = payload.multiplierElytra();
        infinityGlobal = payload.infinityGlobal();
        infinitySwords = payload.infinitySwords();
        infinityTools = payload.infinityTools();
        infinityArmor = payload.infinityArmor();
        infinityElytra = payload.infinityElytra();
        showTooltip = payload.showTooltip();
    }

    // ==================== Accessors ====================

    public static int multiplierGlobal() { return multiplierGlobal; }
    public static int multiplierSwords() { return multiplierSwords; }
    public static int multiplierTools() { return multiplierTools; }
    public static int multiplierArmor() { return multiplierArmor; }
    public static int multiplierElytra() { return multiplierElytra; }

    public static boolean infinityGlobal() { return infinityGlobal; }
    public static boolean infinitySwords() { return infinitySwords; }
    public static boolean infinityTools() { return infinityTools; }
    public static boolean infinityArmor() { return infinityArmor; }
    public static boolean infinityElytra() { return infinityElytra; }

    public static boolean showTooltip() { return showTooltip; }
}
