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
| :--- | :--- | :--- | :---: |
| **God Mode Active** | `✦ UNBREAKABLE` | **✦ UNBREAKABLE** | Gold, Bold (`§6§l`) |
| **2x Multiplier on Sword** | `⟨2x Swords Durability⟩` | ⟨2x Swords Durability⟩ | Gray (`§7`) |
| **4x Multiplier on Pickaxe** | `⟨4x Tools Durability⟩` | ⟨4x Tools Durability⟩ | Gray (`§7`) |
| **5x Multiplier on Elytra** | `⟨5x Elytra Durability⟩` | ⟨5x Elytra Durability⟩ | Gray (`§7`) |
| **3x Multiplier on Mod Item** | `⟨3x Ruby Scythe Durability⟩` | ⟨3x Ruby Scythe Durability⟩ | Gray (`§7`) |
| **Vanilla Multiplier (1x)** | *(None)* | *(No extra tooltip line rendered)* | — |

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
