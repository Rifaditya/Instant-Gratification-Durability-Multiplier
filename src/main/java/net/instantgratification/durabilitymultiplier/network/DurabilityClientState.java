package net.instantgratification.durabilitymultiplier.network;

/**
 * Client-side cache for synced GameRule values.
 * Populated by {@link DurabilityPayload} received from the server.
 * Read by the tooltip mixin instead of accessing ServerLevel GameRules.
 */
public final class DurabilityClientState {

    private static int multiplierGlobal = 2;
    private static int multiplierWeapons;
    private static int multiplierSwords;
    private static int multiplierSpears;
    private static int multiplierTridents;
    private static int multiplierMaces;
    private static int multiplierBows;
    private static int multiplierCrossbows;
    private static int multiplierTools;
    private static int multiplierArmor;
    private static int multiplierElytra;
    
    private static boolean infinityGlobal;
    private static boolean infinityWeapons;
    private static boolean infinitySwords;
    private static boolean infinitySpears;
    private static boolean infinityTridents;
    private static boolean infinityMaces;
    private static boolean infinityBows;
    private static boolean infinityCrossbows;
    private static boolean infinityTools;
    private static boolean infinityArmor;
    private static boolean infinityElytra;
    
    private static boolean showTooltip = true;

    private DurabilityClientState() {
    }

    /** Apply received payload from the server. */
    public static void apply(DurabilityPayload payload) {
        multiplierGlobal = payload.multiplierGlobal();
        multiplierWeapons = payload.multiplierWeapons();
        multiplierSwords = payload.multiplierSwords();
        multiplierSpears = payload.multiplierSpears();
        multiplierTridents = payload.multiplierTridents();
        multiplierMaces = payload.multiplierMaces();
        multiplierBows = payload.multiplierBows();
        multiplierCrossbows = payload.multiplierCrossbows();
        multiplierTools = payload.multiplierTools();
        multiplierArmor = payload.multiplierArmor();
        multiplierElytra = payload.multiplierElytra();
        
        infinityGlobal = payload.infinityGlobal();
        infinityWeapons = payload.infinityWeapons();
        infinitySwords = payload.infinitySwords();
        infinitySpears = payload.infinitySpears();
        infinityTridents = payload.infinityTridents();
        infinityMaces = payload.infinityMaces();
        infinityBows = payload.infinityBows();
        infinityCrossbows = payload.infinityCrossbows();
        infinityTools = payload.infinityTools();
        infinityArmor = payload.infinityArmor();
        infinityElytra = payload.infinityElytra();
        
        showTooltip = payload.showTooltip();
    }

    // ==================== Accessors ====================

    public static int multiplierGlobal() { return multiplierGlobal; }
    public static int multiplierWeapons() { return multiplierWeapons; }
    public static int multiplierSwords() { return multiplierSwords; }
    public static int multiplierSpears() { return multiplierSpears; }
    public static int multiplierTridents() { return multiplierTridents; }
    public static int multiplierMaces() { return multiplierMaces; }
    public static int multiplierBows() { return multiplierBows; }
    public static int multiplierCrossbows() { return multiplierCrossbows; }
    public static int multiplierTools() { return multiplierTools; }
    public static int multiplierArmor() { return multiplierArmor; }
    public static int multiplierElytra() { return multiplierElytra; }

    public static boolean infinityGlobal() { return infinityGlobal; }
    public static boolean infinityWeapons() { return infinityWeapons; }
    public static boolean infinitySwords() { return infinitySwords; }
    public static boolean infinitySpears() { return infinitySpears; }
    public static boolean infinityTridents() { return infinityTridents; }
    public static boolean infinityMaces() { return infinityMaces; }
    public static boolean infinityBows() { return infinityBows; }
    public static boolean infinityCrossbows() { return infinityCrossbows; }
    public static boolean infinityTools() { return infinityTools; }
    public static boolean infinityArmor() { return infinityArmor; }
    public static boolean infinityElytra() { return infinityElytra; }

    public static boolean showTooltip() { return showTooltip; }
}
