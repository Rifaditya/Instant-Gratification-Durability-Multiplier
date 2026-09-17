# Architecture & Symbol Index: Durability Multiplier

## 1. Mod Metadata & Entrypoint
- **Mod ID**: `durability-multiplier`
- **Main Entrypoint**: `net.instantgratification.durabilitymultiplier.DurabilityMultiplierFabric` (`net.fabricmc.api.ModInitializer`)
- **Client Entrypoint**: `net.instantgratification.durabilitymultiplier.DurabilityMultiplierFabricClient`

## 2. Bytecode Mixin Target Registry
| Target Vanilla Class | Mixin Class | Purpose |
| :--- | :--- | :--- |
| `Vanilla Class` | `net.instantgratification.durabilitymultiplier.mixin.ItemStackDurabilityMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.durabilitymultiplier.mixin.ItemStackTooltipMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.durabilitymultiplier.mixin.GameRulesMixin` | Core mixin hook |

## 3. Core Mechanics & Subsystems
- **Source Root**: `src/main/java/`
- **Resource Root**: `src/main/resources/`

## 4. Dynamic GameRules & Commands
- **GameRules / Commands**: Configured dynamically via namespaced keys (`durability-multiplier:*`).

## 5. Configuration & Sidedness Isolation
- **Sidedness**: Server-safe logic in main, client isolated in `src/client/java` or client entrypoint.
