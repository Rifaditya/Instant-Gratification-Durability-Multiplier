# Network Sync & Payload Protocol (26.1.2)

| Protocol Parameter | Value |
| :--- | :--- |
| **Channel Identifier** | `durability-multiplier:sync_rules` |
| **Payload Class** | `net.instantgratification.durabilitymultiplier.network.DurabilityPayload` |
| **Network Manager** | `net.instantgratification.durabilitymultiplier.network.DurabilityNetworking` |
| **Client State Cache** | `net.instantgratification.durabilitymultiplier.network.DurabilityClientState` |
| **Codec Type** | `StreamCodec<FriendlyByteBuf, DurabilityPayload>` |
| **Sync Triggers** | Player Join (`ServerPlayConnectionEvents.JOIN`) & GameRule Change (`GameRulesMixin`) |

---

## ⚡ Protocol Architecture

Because tooltips are rendered on the physical client but GameRules exist on the logical server, Durability Multiplier uses Fabric Networking API to push live snapshots of all 25 GameRules to clients.

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
    Net->>Net: Snapshot 25 GameRules
    Net->>Client: Send DurabilityPayload (sync_rules)
    Client->>State: DurabilityClientState.apply(payload)

    Note over Server,Client: Event 2: Admin Changes /gamerule
    Server->>Net: GameRules.set() [GameRulesMixin TAIL]
    Net->>Client: Broadcast DurabilityPayload to all players
    Client->>State: DurabilityClientState.apply(payload)
    Client->>UI: Tooltips update immediately without relog
```

---

## 📦 Payload Structure (25 Fields)

`DurabilityPayload` serializes 25 fields efficiently using `FriendlyByteBuf` VarInts and Booleans:

```java
public record DurabilityPayload(
    int multiplierGlobal,
    int multiplierWeapons,
    int multiplierSwords,
    int multiplierSpears,
    int multiplierTridents,
    int multiplierMaces,
    int multiplierBows,
    int multiplierCrossbows,
    int multiplierShields,
    int multiplierTools,
    int multiplierArmor,
    int multiplierElytra,
    
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
    boolean infinityArmor,
    boolean infinityElytra,
    
    boolean showTooltip
) implements CustomPacketPayload
```
