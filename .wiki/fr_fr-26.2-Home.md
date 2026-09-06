# Durability Multiplier — Centre de documentation Minecraft 26.2

Bienvenue dans la documentation dédiée de **Durability Multiplier** pour **Minecraft 26.2** (`1.2.14+26.2`).

> 📌 **Avertissement relatif au code source** : La documentation de ce Wiki reflète l'**état actuel du code dans le dépôt**, qui peut inclure des commits de développement non encore publiés sur CurseForge et Modrinth.

---

## 📋 Aperçu technique (26.2)

| Paramètre | Valeur | Description |
| :--- | :--- | :--- |
| **Identifiant du mod** | `durability-multiplier` | ID du mod dans Fabric Loader |
| **Version du mod** | `1.2.14+26.2` | Tag de version SemVer |
| **Minecraft cible** | `26.2` (`>=26.2-`) | Ancrage de version natif |
| **Version Java** | Java 25 | Compilé avec `release = 25` |
| **Fabric Loader** | `>=0.16.9` | Exigence minimale du loader |
| **Fabric API** | `0.150.1+26.2` | Exigence d'exécution de Fabric API |
| **DasikLibrary** | `1.8.28` | Cœur d'architecture partagé |
| **GameRules enregistrées** | **73 règles statiques** + règles dynamiques | 24 pourcentages, 24 infinis, 24 usages uniques, 1 infobulle |
| **Points d'injection Mixin** | 3 classes cibles | `ItemStack`, `GameRules` |
| **Auteur & Licence** | **Dasik (Rifaditya)** / GPL-3.0-or-later | Modification open source |

---

## 🧭 Matrice de navigation (26.2)

### 🎮 Guides pour joueurs et gameplay
* [[Multiplicateurs de durabilité et catégories|fr_fr-26.2-Durability-Multipliers]] — Système de pourcentage granulaire à 24 catégories et hiérarchie.
* [[Mode Dieu et Infini|fr_fr-26.2-God-Mode-and-Infinity]] — Invincibilité totale à 0 dégât dans 24 catégories.
* [[Mathématiques de réduction de dégâts et probabilités|fr_fr-26.2-Damage-Reduction-and-Probability-Math]] — Formules mathématiques et arrondi probabiliste.
* [[Classification des objets et compatibilité avec les mods|fr_fr-26.2-Item-Classification-and-Mod-Compatibility]] — Classification des objets vanilla et de mods.
* [[Enregistrement dynamique d'objets de mods|fr_fr-26.2-Dynamic-Modded-Item-Registration]] — Scanner universel de découverte et remplissage automatique.
* [[Indicateurs d'infobulles et HUD|fr_fr-26.2-Tooltip-Indicators-and-HUD]] — Rendu des infobulles côté client.
* [[Tableau de référence des GameRules|fr_fr-26.2-GameRules]] — Tableau exhaustif des 73 GameRules statiques.
* [[Commandes et administration en jeu|fr_fr-26.2-Commands-and-Administration]] — Gestion des paramètres en jeu via `/gamerule`.
* [[Progrès et succès|fr_fr-26.2-Advancements]] — Politique d'absence et intégration vanilla.
* [[Configuration et interface GUI|fr_fr-26.2-Configuration]] — Intégration de ModMenu et Cloth Config.
* [[Protection à l'exécution ModVersionGuard|fr_fr-26.2-ModVersionGuard-and-Safety]] — Protection de version sans dépendance.
* [[Dépannage et FAQ|fr_fr-26.2-Troubleshooting-and-FAQ]] — Diagnostics et réponses aux questions fréquentes.

### 💻 Référence technique pour développeurs
* [[Architecture et descripteurs de Mixin|fr_fr-26.2-Architecture-and-Mixins]] — Hiérarchie de paquets, crochets d'injection et sécurité.
* [[Synchronisation réseau et protocole de charge utile|fr_fr-26.2-Network-Sync-and-Payload-Protocol]] — Protocole S2C (`DurabilityPayload`).
* [[Configuration développeur et compilation|fr_fr-26.2-Developer-Setup-and-Building]] — Commandes Gradle, outils Loom et JDK.
* [[Intégration d'API et d'addons|fr_fr-26.2-API-and-Addon-Integration]] — Extension du mod, `DurabilityHelper` et règles personnalisées.
