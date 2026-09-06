# Indicateurs d'infobulle et HUD (26.2)

| Paramètre système | Valeur |
| :--- | :--- |
| **GameRule d'activation** | `ig:dm_show_tooltip` |
| **État par défaut** | `true` (Activé) |
| **Cible Mixin** | `ItemStack.addDetailsToTooltip` (`ItemStackTooltipMixin`) |
| **Point d'injection** | `@At("TAIL")` |
| **Style Mode Dieu** | `✦ UNBREAKABLE` (Or, Gras — `ChatFormatting.GOLD`, `ChatFormatting.BOLD`) |
| **Style Multiplicateur** | `⟨Nx Durabilité de catégorie⟩` (Gris — `ChatFormatting.GRAY`) |

---

## ⚡ Aperçu et présentation visuelle

Durability Multiplier fournit un retour visuel clair et immédiat sur les infobulles chaque fois que la durée de vie d'un objet est modifiée.

### Styles visuels des infobulles

| Statut | Texte affiché | Apparence visuelle | Code couleur |
| :--- | :--- | :--- | :--- |
| **Mode Dieu actif** | `✦ UNBREAKABLE` | **✦ UNBREAKABLE** | Or, Gras (`ChatFormatting.GOLD`, `BOLD`) |
| **Usage unique (Mode Verre)** | `⟨SINGLE-USE⟩` | ⟨SINGLE-USE⟩ | Gris (`ChatFormatting.GRAY`) |
| **Multiplicateur 200% / 2x** | `⟨2x Durabilité des épées⟩` | ⟨2x Durabilité des épées⟩ | Gris (`ChatFormatting.GRAY`) |
| **Durabilité 150%** | `⟨150% Durabilité des plastrons⟩` | ⟨150% Durabilité des plastrons⟩ | Gris (`ChatFormatting.GRAY`) |
| **50% (Moitié de durabilité)** | `⟨50% Durabilité des épées⟩` | ⟨50% Durabilité des épées⟩ | Gris (`ChatFormatting.GRAY`) |
| **Multiplicateur 500% / 5x** | `⟨5x Durabilité des pioches⟩` | ⟨5x Durabilité des pioches⟩ | Gris (`ChatFormatting.GRAY`) |
| **Remplacement objet de mod** | `⟨300% Durabilité de Plasma Cutter⟩` | ⟨300% Durabilité de Plasma Cutter⟩ | Gris (`ChatFormatting.GRAY`) |
| **Base vanilla (100%)** | *(Aucun)* | *(Aucune ligne supplémentaire affichée)* | — |

---

## 🎨 Modes de formatage d'infobulle (`tooltipFormat`)

Le mod prend en charge 3 formats d'affichage via `config/durability-multiplier.json` et ModMenu :
1. **`ADAPTIVE` (Défaut)** : Affiche automatiquement des multiplicateurs entiers (`2x`, `5x`) pour les centaines exactes, des pourcentages (`50%`, `150%`) sinon.
2. **`PERCENTAGE`** : Affiche toujours des pourcentages explicites (ex. `200% Durabilité des épées`, `50% Durabilité des pioches`).
3. **`MULTIPLIER`** : Affiche toujours des multiplicateurs décimaux (ex. `2x Durabilité des épées`, `0.5x Durabilité des épées`, `1.5x Durabilité des plastrons`).

---

## 🖥️ Exécution côté client et côté serveur

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

1. **Serveur intégré (Solo / Hôte LAN)** : Les infobulles interrogent directement les GameRules du `ServerLevel` en temps réel.
2. **Client dédié (Multijoueur)** : Les infobulles lisent depuis `DurabilityClientState`, mis à jour par les paquets `DurabilityPayload`.
