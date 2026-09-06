# Tooltip-Indikatoren & HUD (26.1.2)

| Systemparameter | Wert |
| :--- | :--- |
| **Umschalt-GameRule** | `ig:dm_show_tooltip` |
| **Standardstatus** | `true` (Aktiviert) |
| **Mixin-Ziel** | `ItemStack.addDetailsToTooltip` (`ItemStackTooltipMixin`) |
| **Injektionspunkt** | `@At("TAIL")` |
| **Gott-Modus-Stil** | `✦ UNBREAKABLE` (Gold, Fett — `ChatFormatting.GOLD`, `ChatFormatting.BOLD`) |
| **Multiplikator-Stil** | `⟨Nx Kategorie-Haltbarkeit⟩` (Grau — `ChatFormatting.GRAY`) |

---

## ⚡ Übersicht & Visuelle Darstellung

Durability Multiplier bietet sofortiges, klares Feedback direkt auf Gegenstands-Tooltips, wenn die Lebensdauer eines Gegenstands modifiziert ist.

### Visuelle Stile für Tooltips

| Status | Gerenderter Text | Visuelles Erscheinungsbild | Farbcode |
| :--- | :--- | :--- | :--- |
| **Gott-Modus aktiv** | `✦ UNBREAKABLE` | **✦ UNBREAKABLE** | Gold, Fett (`ChatFormatting.GOLD`, `BOLD`) |
| **Einmaliger Gebrauch (Glas-Modus)** | `⟨SINGLE-USE⟩` | ⟨SINGLE-USE⟩ | Grau (`ChatFormatting.GRAY`) |
| **200% / 2x Multiplikator** | `⟨2x Schwerter-Haltbarkeit⟩` | ⟨2x Schwerter-Haltbarkeit⟩ | Grau (`ChatFormatting.GRAY`) |
| **150% Haltbarkeit** | `⟨150% Brustpanzer-Haltbarkeit⟩` | ⟨150% Brustpanzer-Haltbarkeit⟩ | Grau (`ChatFormatting.GRAY`) |
| **50% (Halbe Haltbarkeit)** | `⟨50% Schwerter-Haltbarkeit⟩` | ⟨50% Schwerter-Haltbarkeit⟩ | Grau (`ChatFormatting.GRAY`) |
| **500% / 5x Multiplikator** | `⟨5x Spitzhacken-Haltbarkeit⟩` | ⟨5x Spitzhacken-Haltbarkeit⟩ | Grau (`ChatFormatting.GRAY`) |
| **Mod-Gegenstandsüberschreibung** | `⟨300% Plasma Cutter Haltbarkeit⟩` | ⟨300% Plasma Cutter Haltbarkeit⟩ | Grau (`ChatFormatting.GRAY`) |
| **Vanilla-Basis (100%)** | *(Keine)* | *(Keine zusätzliche Tooltip-Zeile)* | — |

---

## 🎨 Tooltip-Formatierungsmodi (`tooltipFormat`)

Die Mod unterstützt 3 Anzeigeformate über `config/durability-multiplier.json` und die ModMenu-GUI:
1. **`ADAPTIVE` (Standard)**: Zeigt ganzzahlige Multiplikatoren (`2x`, `5x`) für glatte Hunderter, andernfalls Prozente (`50%`, `150%`).
2. **`PERCENTAGE`**: Zeigt immer explizite Prozentsätze (z. B. `200% Schwerter-Haltbarkeit`, `50% Spitzhacken-Haltbarkeit`).
3. **`MULTIPLIER`**: Zeigt immer Dezimalmultiplikatoren (z. B. `2x Schwerter-Haltbarkeit`, `0.5x Schwerter-Haltbarkeit`, `1.5x Brustpanzer-Haltbarkeit`).

---

## 🖥️ Client- & serverseitige Ausführung

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

1. **Integrierter Server (Einzelspieler / LAN)**: Tooltips lesen aktive GameRules der `ServerLevel` direkt in Echtzeit ab.
2. **Dedizierter Client (Mehrspieler)**: Tooltips lesen aus `DurabilityClientState`, der über `DurabilityPayload`-Pakete aktualisiert wird.
