# Référence des GameRules (26.1.2)

Toutes les GameRules de Durability Multiplier sont enregistrées sous la catégorie personnalisée **`durability-multiplier:durability_multiplier`**.

---

## 📊 Tables de référence complètes des GameRules

### 1. GameRules de pourcentage de durabilité
Les règles de pourcentage contrôlent l'échelonnage de durabilité des objets.
* `200` = 200% (durabilité 2x)
* `100` = 100% (Base vanilla 1x)
* `50` = 50% (Moitié de durabilité / usure 2x)
* `0` = Hérite de la catégorie parente ou de la valeur globale
* `-1` = Sentinelle **Usage unique (Mode Verre)** (se brise en 1 coup)

| # | Identifiant de GameRule | Type | Défaut | Min | Description et comportement |
| :-: | :--- | :---: | :---: | :---: | :--- |
| 1 | `ig:dm_percent_global` | `Integer` | `200` | `-1` | Pourcentage de base global pour tous les objets endommageables. |
| 2 | `ig:dm_percent_weapons` | `Integer` | `0` | `-1` | Remplacement global pour toutes les armes (épées, lances, tridents, masses, arcs, arbalètes). |
| 3 | `ig:dm_percent_swords` | `Integer` | `0` | `-1` | Pourcentage spécifique pour épées (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `Integer` | `0` | `-1` | Pourcentage spécifique pour lances (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `Integer` | `0` | `-1` | Pourcentage spécifique pour tridents (`TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `Integer` | `0` | `-1` | Pourcentage spécifique pour masses (`MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `Integer` | `0` | `-1` | Pourcentage spécifique pour arcs (`BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `Integer` | `0` | `-1` | Pourcentage spécifique pour arbalètes (`CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `Integer` | `0` | `-1` | Pourcentage spécifique pour boucliers (`ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `Integer` | `0` | `-1` | Pourcentage de catégorie parente pour tous les outils. |
| 11 | `ig:dm_percent_pickaxes` | `Integer` | `0` | `-1` | Pourcentage spécifique pour pioches (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `Integer` | `0` | `-1` | Pourcentage spécifique pour haches (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `Integer` | `0` | `-1` | Pourcentage spécifique pour pelles (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `Integer` | `0` | `-1` | Pourcentage spécifique pour houes (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `Integer` | `0` | `-1` | Pourcentage spécifique pour cisailles (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `Integer` | `0` | `-1` | Pourcentage spécifique pour cannes à pêche (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `Integer` | `0` | `-1` | Pourcentage spécifique pour pinceaux (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `Integer` | `0` | `-1` | Pourcentage spécifique pour briquets (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `Integer` | `0` | `-1` | Pourcentage de catégorie parente pour toutes les pièces d'armure. |
| 20 | `ig:dm_percent_helmets` | `Integer` | `0` | `-1` | Pourcentage spécifique pour casques (`#c:helmets`, emplacement tête). |
| 21 | `ig:dm_percent_chestplates` | `Integer` | `0` | `-1` | Pourcentage spécifique pour plastrons (`#c:chestplates`, emplacement buste). |
| 22 | `ig:dm_percent_leggings` | `Integer` | `0` | `-1` | Pourcentage spécifique pour jambières (`#c:leggings`, emplacement jambes). |
| 23 | `ig:dm_percent_boots` | `Integer` | `0` | `-1` | Pourcentage spécifique pour bottes (`#c:boots`, emplacement pieds). |
| 24 | `ig:dm_percent_elytra` | `Integer` | `0` | `-1` | Pourcentage spécifique pour élytres (`Items.ELYTRA`, `GLIDER`). |

---

### 2. GameRules du Mode Dieu (Infini)
Lorsqu'elle est activée (`true`), les objets de cette catégorie subissent $0$ dégât et ne cassent jamais.

| # | Identifiant de GameRule | Type | Défaut | Description |
| :-: | :--- | :---: | :---: | :--- |
| 25 | `ig:dm_infinity_global` | `Boolean` | `false` | Mode Dieu global pour tous les objets endommageables du jeu. |
| 26 | `ig:dm_infinity_weapons` | `Boolean` | `false` | Mode Dieu pour toutes les armes. |
| 27 | `ig:dm_infinity_swords` | `Boolean` | `false` | Mode Dieu pour épées. |
| 28 | `ig:dm_infinity_spears` | `Boolean` | `false` | Mode Dieu pour lances. |
| 29 | `ig:dm_infinity_tridents` | `Boolean` | `false` | Mode Dieu pour tridents. |
| 30 | `ig:dm_infinity_maces` | `Boolean` | `false` | Mode Dieu pour masses. |
| 31 | `ig:dm_infinity_bows` | `Boolean` | `false` | Mode Dieu pour arcs. |
| 32 | `ig:dm_infinity_crossbows` | `Boolean` | `false` | Mode Dieu pour arbalètes. |
| 33 | `ig:dm_infinity_shields` | `Boolean` | `false` | Mode Dieu pour boucliers. |
| 34 | `ig:dm_infinity_tools` | `Boolean` | `false` | Mode Dieu pour tous les outils. |
| 35 | `ig:dm_infinity_pickaxes` | `Boolean` | `false` | Mode Dieu pour pioches. |
| 36 | `ig:dm_infinity_axes` | `Boolean` | `false` | Mode Dieu pour haches. |
| 37 | `ig:dm_infinity_shovels` | `Boolean` | `false` | Mode Dieu pour pelles. |
| 38 | `ig:dm_infinity_hoes` | `Boolean` | `false` | Mode Dieu pour houes. |
| 39 | `ig:dm_infinity_shears` | `Boolean` | `false` | Mode Dieu pour cisailles. |
| 40 | `ig:dm_infinity_fishing_rods` | `Boolean` | `false` | Mode Dieu pour cannes à pêche. |
| 41 | `ig:dm_infinity_brushes` | `Boolean` | `false` | Mode Dieu pour pinceaux. |
| 42 | `ig:dm_infinity_flint_and_steel` | `Boolean` | `false` | Mode Dieu pour briquets. |
| 43 | `ig:dm_infinity_armor` | `Boolean` | `false` | Mode Dieu pour toutes les armures. |
| 44 | `ig:dm_infinity_helmets` | `Boolean` | `false` | Mode Dieu pour casques. |
| 45 | `ig:dm_infinity_chestplates` | `Boolean` | `false` | Mode Dieu pour plastrons. |
| 46 | `ig:dm_infinity_leggings` | `Boolean` | `false` | Mode Dieu pour jambières. |
| 47 | `ig:dm_infinity_boots` | `Boolean` | `false` | Mode Dieu pour bottes. |
| 48 | `ig:dm_infinity_elytra` | `Boolean` | `false` | Mode Dieu pour élytres. |

---

### 3. GameRules d'usage unique (Mode Verre)
Lorsqu'elle est activée (`true`), les objets de cette catégorie se brisent après un seul coup.

| # | Identifiant de GameRule | Type | Défaut | Description |
| :-: | :--- | :---: | :---: | :--- |
| 49 | `ig:dm_single_use_global` | `Boolean` | `false` | Mode Verre global (usage unique) pour tous les objets. |
| 50 | `ig:dm_single_use_weapons` | `Boolean` | `false` | Usage unique pour toutes les armes. |
| 51 | `ig:dm_single_use_swords` | `Boolean` | `false` | Usage unique pour épées. |
| 52 | `ig:dm_single_use_spears` | `Boolean` | `false` | Usage unique pour lances. |
| 53 | `ig:dm_single_use_tridents` | `Boolean` | `false` | Usage unique pour tridents. |
| 54 | `ig:dm_single_use_maces` | `Boolean` | `false` | Usage unique pour masses. |
| 55 | `ig:dm_single_use_bows` | `Boolean` | `false` | Usage unique pour arcs. |
| 56 | `ig:dm_single_use_crossbows` | `Boolean` | `false` | Usage unique pour arbalètes. |
| 57 | `ig:dm_single_use_shields` | `Boolean` | `false` | Usage unique pour boucliers. |
| 58 | `ig:dm_single_use_tools` | `Boolean` | `false` | Usage unique pour tous les outils. |
| 59 | `ig:dm_single_use_pickaxes` | `Boolean` | `false` | Usage unique pour pioches. |
| 60 | `ig:dm_single_use_axes` | `Boolean` | `false` | Usage unique pour haches. |
| 61 | `ig:dm_single_use_shovels` | `Boolean` | `false` | Usage unique pour pelles. |
| 62 | `ig:dm_single_use_hoes` | `Boolean` | `false` | Usage unique pour houes. |
| 63 | `ig:dm_single_use_shears` | `Boolean` | `false` | Usage unique pour cisailles. |
| 64 | `ig:dm_single_use_fishing_rods` | `Boolean` | `false` | Usage unique pour cannes à pêche. |
| 65 | `ig:dm_single_use_brushes` | `Boolean` | `false` | Usage unique pour pinceaux. |
| 66 | `ig:dm_single_use_flint_and_steel` | `Boolean` | `false` | Usage unique pour briquets. |
| 67 | `ig:dm_single_use_armor` | `Boolean` | `false` | Usage unique pour toutes les armures. |
| 68 | `ig:dm_single_use_helmets` | `Boolean` | `false` | Usage unique pour casques. |
| 69 | `ig:dm_single_use_chestplates` | `Boolean` | `false` | Usage unique pour plastrons. |
| 70 | `ig:dm_single_use_leggings` | `Boolean` | `false` | Usage unique pour jambières. |
| 71 | `ig:dm_single_use_boots` | `Boolean` | `false` | Usage unique pour bottes. |
| 72 | `ig:dm_single_use_elytra` | `Boolean` | `false` | Usage unique pour élytres. |

---

### 4. GameRules d'affichage et de mods dynamiques

| Identifiant de GameRule | Type | Défaut | Description |
| :--- | :---: | :---: | :--- |
| `ig:dm_show_tooltip` | `Boolean` | `true` | Affiche la ligne de bonus de durabilité sur les infobulles. |
| `ig:percent_<mod>_<item>` | `Integer` | `0` | Remplacement de pourcentage dynamique pour objet de mod (Min `-1`). |
| `ig:infinity_<mod>_<item>` | `Boolean` | `false` | Remplacement Mode Dieu dynamique pour objet de mod. |
| `ig:single_use_<mod>_<item>` | `Boolean` | `false` | Remplacement Usage unique dynamique pour objet de mod. |

---

## ⚡ Commandes d'ajustement en jeu

```mcfunction
# Query current global percentage
/gamerule ig:dm_percent_global

# Set diamond/netherite pickaxes to 500% (5x) durability
/gamerule ig:dm_percent_pickaxes 500

# Make Elytra wings unbreakable
/gamerule ig:dm_infinity_elytra true

# Set a modded weapon to Single-Use using the -1 sentinel
/gamerule ig:percent_techmod_plasma_cutter -1

# Disable all multipliers (vanilla 100% baseline)
/gamerule ig:dm_percent_global 100
```

