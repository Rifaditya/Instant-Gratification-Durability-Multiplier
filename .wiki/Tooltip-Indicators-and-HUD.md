# Tooltip Indicators & HUD (26.1.2)

| System Parameter | Value |
| :--- | :--- |
| **Toggle GameRule** | `ig:dm_show_tooltip` |
| **Default State** | `true` (Enabled) |
| **Mixin Target** | `ItemStack.addDetailsToTooltip` (`ItemStackTooltipMixin`) |
| **Injection Point** | `@At("TAIL")` |
| **God Mode Style** | `✦ UNBREAKABLE` (Gold, Bold — `ChatFormatting.GOLD`, `ChatFormatting.BOLD`) |
| **Multiplier Style** | `⟨Nx Category Durability⟩` (Gray — `ChatFormatting.GRAY`) |

---

## ⚡ Overview & Visual Presentation

Durability Multiplier provides immediate, unambiguous feedback directly on item tooltips whenever an item's lifespan is modified.

### Tooltip Visual Styles

| Status | Rendered Text | Visual Appearance | Color Code |
| :--- | :--- | :--- | :--- |
| **God Mode Active** | `✦ UNBREAKABLE` | **✦ UNBREAKABLE** | Gold, Bold (`ChatFormatting.GOLD`, `BOLD`) |
| **Single-Use (Glass Mode)** | `⟨SINGLE-USE⟩` | ⟨SINGLE-USE⟩ | Gray (`ChatFormatting.GRAY`) |
| **200% / 2x Multiplier** | `⟨2x Swords Durability⟩` | ⟨2x Swords Durability⟩ | Gray (`ChatFormatting.GRAY`) |
| **150% Durability** | `⟨150% Chestplates Durability⟩` | ⟨150% Chestplates Durability⟩ | Gray (`ChatFormatting.GRAY`) |
| **50% (Half Durability)** | `⟨50% Swords Durability⟩` | ⟨50% Swords Durability⟩ | Gray (`ChatFormatting.GRAY`) |
| **500% / 5x Multiplier** | `⟨5x Pickaxes Durability⟩` | ⟨5x Pickaxes Durability⟩ | Gray (`ChatFormatting.GRAY`) |
| **Modded Item Override** | `⟨300% Plasma Cutter Durability⟩` | ⟨300% Plasma Cutter Durability⟩ | Gray (`ChatFormatting.GRAY`) |
| **Vanilla Baseline (100%)** | *(None)* | *(No extra tooltip line rendered)* | — |

---

## 🎨 Tooltip Formatting Modes (`tooltipFormat`)

The mod supports 3 configurable display formats via `config/durability-multiplier.json` and ModMenu GUI:
1. **`ADAPTIVE` (Default)**: Automatically displays clean integer multipliers (`2x`, `5x`) for even hundreds, and percentages (`50%`, `150%`) otherwise.
2. **`PERCENTAGE`**: Always displays explicit percentages (e.g. `200% Swords Durability`, `50% Pickaxes Durability`).
3. **`MULTIPLIER`**: Always displays decimal multipliers (e.g. `2x Swords Durability`, `0.5x Swords Durability`, `1.5x Chestplates Durability`).

---

## 🖥️ Client-Server Sided Execution

```
                       [Item Tooltip Render]
                                 │
                                 ▼
                     [Is Player on Integrated Server?]
                     ├── YES ──► Read GameRules from ServerLevel
                     │           (DurabilityHelper.getTooltipLabel)
                     │
                     └── NO (Remote Server) ──► Read Synced Client Cache
                                                (DurabilityClientState)
```

1. **Integrated Server (Singleplayer / LAN Host)**: Tooltips query active `ServerLevel` GameRules directly in real time.
2. **Dedicated Client (Multiplayer Connected)**: Tooltips read from `DurabilityClientState`, which is populated via `DurabilityPayload` network packets whenever server GameRules change.
