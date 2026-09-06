# Synchronisation réseau & Protocole de données (26.1.2)

| Paramètre de protocole | Valeur |
| :--- | :--- |
| **Identifiant de canal** | `durability-multiplier:sync_rules` |
| **Classe de charge utile** | `net.instantgratification.durabilitymultiplier.network.DurabilityPayload` |
| **Gestionnaire de réseau** | `net.instantgratification.durabilitymultiplier.network.DurabilityNetworking` |
| **Cache d'état client** | `net.instantgratification.durabilitymultiplier.network.DurabilityClientState` |
| **Type de codec** | `StreamCodec<FriendlyByteBuf, DurabilityPayload>` |
| **Déclencheurs de synchronisation** | Connexion du joueur (`ServerPlayConnectionEvents.JOIN`) & modification de GameRule (`GameRulesMixin`) |

---

## ⚡ Architecture du protocole

Comme les infobulles sont affichées sur le client physique alors que les GameRules résident sur le serveur, Durability Multiplier utilise l'API réseau Fabric pour synchroniser les 73 règles statiques et les règles dynamiques.

```mermaid
sequenceDiagram
    autonumber
    participant Server as Logical Server
    participant Net as DurabilityNetworking
    participant Client as Remote Client
    participant State as DurabilityClientState
    participant UI as ItemStackTooltipMixin

    Note over Server,Client: Event 1: Player Joins World
    Server->>Net: ServerPlayConnectionEvents.JOIN
    Net->>Net: Snapshot 73 GameRules + Dynamic Maps
    Net->>Client: Send DurabilityPayload (sync_rules)
    Client->>State: DurabilityClientState.apply(payload)

    Note over Server,Client: Event 2: Admin Changes /gamerule
    Server->>Net: GameRules.set() [GameRulesMixin TAIL]
    Net->>Client: Broadcast DurabilityPayload to all players
    Client->>State: DurabilityClientState.apply(payload)
    Client->>UI: Tooltips update immediately without relog
```

---

## 📦 Structure du paquet (76 champs et cartes dynamiques)

`DurabilityPayload` sérialise efficacement les GameRules et tables dynamiques avec les VarInts, booléens et codecs de `FriendlyByteBuf` :

```java
public record DurabilityPayload(
    int percentGlobal,
    int percentWeapons,
    int percentSwords,
    int percentSpears,
    int percentTridents,
    int percentMaces,
    int percentBows,
    int percentCrossbows,
    int percentShields,
    int percentTools,
    int percentPickaxes,
    int percentAxes,
    int percentShovels,
    int percentHoes,
    int percentShears,
    int percentFishingRods,
    int percentBrushes,
    int percentFlintAndSteel,
    int percentArmor,
    int percentHelmets,
    int percentChestplates,
    int percentLeggings,
    int percentBoots,
    int percentElytra,
    
    boolean infinityGlobal,
    boolean infinityWeapons,
    boolean infinitySwords,
    boolean infinitySpears,
    boolean infinityTridents,
    boolean infinityMaces,
    boolean infinityBows,
    boolean infinityCrossbows,
    boolean infinityShields,
    boolean infinityTools,
    boolean infinityPickaxes,
    boolean infinityAxes,
    boolean infinityShovels,
    boolean infinityHoes,
    boolean infinityShears,
    boolean infinityFishingRods,
    boolean infinityBrushes,
    boolean infinityFlintAndSteel,
    boolean infinityArmor,
    boolean infinityHelmets,
    boolean infinityChestplates,
    boolean infinityLeggings,
    boolean infinityBoots,
    boolean infinityElytra,
    
    boolean singleUseGlobal,
    boolean singleUseWeapons,
    boolean singleUseSwords,
    boolean singleUseSpears,
    boolean singleUseTridents,
    boolean singleUseMaces,
    boolean singleUseBows,
    boolean singleUseCrossbows,
    boolean singleUseShields,
    boolean singleUseTools,
    boolean singleUsePickaxes,
    boolean singleUseAxes,
    boolean singleUseShovels,
    boolean singleUseHoes,
    boolean singleUseShears,
    boolean singleUseFishingRods,
    boolean singleUseBrushes,
    boolean singleUseFlintAndSteel,
    boolean singleUseArmor,
    boolean singleUseHelmets,
    boolean singleUseChestplates,
    boolean singleUseLeggings,
    boolean singleUseBoots,
    boolean singleUseElytra,

    boolean showTooltip,
    
    java.util.Map<String, Integer> dynamicPercentages,
    java.util.Map<String, Boolean> dynamicInfinities,
    java.util.Map<String, Boolean> dynamicSingleUses
) implements CustomPacketPayload
```
