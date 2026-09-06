# Multiplicateurs de durabilité et pourcentages (26.2)

Durability Multiplier remplace l'usure fixe vanilla par un **Moteur d'échelonnage en pourcentage** dynamique qui prend en charge les bonus de durabilité (ex. 200% = 2x, 500% = 5x) et les pénalités d'usure (ex. 50% = 0.5x, 25% = 0.25x).

---

## ⚙️ Règles de jeu (GameRules) de pourcentage principales

| # | Identifiant de GameRule | Défaut | Catégorie cible / Description |
| :-: | :--- | :---: | :--- |
| 1 | `ig:dm_percent_global` | `200` | Pourcentage global appliqué à tous les objets endommageables. |
| 2 | `ig:dm_percent_weapons` | `0` | Remplacement parent pour toutes les armes (épées, lances, tridents, masses, arcs, arbalètes). |
| 3 | `ig:dm_percent_swords` | `0` | Pourcentage spécifique pour épées (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `0` | Pourcentage spécifique pour lances (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `0` | Pourcentage spécifique pour tridents (`Items.TRIDENT`, `TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `0` | Pourcentage spécifique pour masses (`Items.MACE`, `MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `0` | Pourcentage spécifique pour arcs (`Items.BOW`, `BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `0` | Pourcentage spécifique pour arbalètes (`Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `0` | Pourcentage spécifique pour boucliers (`Items.SHIELD`, `ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `0` | Pourcentage de catégorie parente pour tous les outils. |
| 11 | `ig:dm_percent_pickaxes` | `0` | Pourcentage spécifique pour pioches (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `0` | Pourcentage spécifique pour haches (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `0` | Pourcentage spécifique pour pelles (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `0` | Pourcentage spécifique pour houes (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `0` | Pourcentage spécifique pour cisailles (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `0` | Pourcentage spécifique pour cannes à pêche (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `0` | Pourcentage spécifique pour pinceaux (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `0` | Pourcentage spécifique pour briquets (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `0` | Pourcentage de catégorie parente pour toutes les pièces d'armure. |
| 20 | `ig:dm_percent_helmets` | `0` | Pourcentage spécifique pour casques (`#minecraft:head_armor`, `#c:helmets`). |
| 21 | `ig:dm_percent_chestplates` | `0` | Pourcentage spécifique pour plastrons (`#minecraft:chest_armor`, `#c:chestplates`). |
| 22 | `ig:dm_percent_leggings` | `0` | Pourcentage spécifique pour jambières (`#minecraft:leg_armor`, `#c:leggings`). |
| 23 | `ig:dm_percent_boots` | `0` | Pourcentage spécifique pour bottes (`#minecraft:foot_armor`, `#c:boots`). |
| 24 | `ig:dm_percent_elytra` | `0` | Pourcentage spécifique pour élytres (`Items.ELYTRA`, `DataComponents.GLIDER`). |

> [!NOTE]
> Les règles de remplacement définies sur `0` héritent automatiquement de leur catégorie parente ou de la valeur globale. Définir `-1` active l'**Usage unique (Mode Verre)**.

---

## 🔒 Sécurité à 100 % pour les sauvegardes de monde
Durability Multiplier ne modifie **pas** le NBT ni `DataComponents.MAX_DAMAGE` dans les sauvegardes. Tout l'échelonnage est calculé dynamiquement, garantissant zéro corruption si le mod est retiré.
