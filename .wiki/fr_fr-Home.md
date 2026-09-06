# Wiki officiel de Durability Multiplier

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

Bienvenue dans la documentation technique et de jeu officielle de **Durability Multiplier** (Collection Instant Gratification), développée par **Dasik (Rifaditya)**.

> 📌 **Avertissement relatif au code source** : La documentation de ce Wiki reflète l'**état actuel du code dans le dépôt**, qui peut inclure des commits de développement non encore publiés sur CurseForge et Modrinth.

---

## 🧭 Portail de commutation multi-version

Durability Multiplier est conçu pour des versions cibles dédiées de Minecraft. Sélectionnez votre version ci-dessous pour accéder à son centre de documentation isolé :

| Version de Minecraft | Ère de sortie | Build supportée | Niveau Java | Chaîne d'outils Loom | Entrée Wiki |
| :--- | :--- | :--- | :---: | :---: | :--- |
| **Minecraft 26.2** | Ère Modern Sovereign | `1.2.14+26.2` | Java 25 | Loom 1.15.2 | [[👉 Entrer dans le Wiki MC 26.2\|fr_fr-26.2-Home]] |
| **Minecraft 26.1.2** | Ère Modern Sovereign | `1.1.21+26.1.2` | Java 25 | Loom 1.15.2 | [[👉 Entrer dans le Wiki MC 26.1.2\|fr_fr-26.1.2-Home]] |

---

## ⚡ Philosophie fondamentale & Architecture

Durability Multiplier fait partie de la gamme de conception **Instant Gratification (IG)**. Son unique objectif est d'éliminer la **friction d'entretien** dans la survie Minecraft :

* **Respect du temps du joueur** : Élimine les cycles fastidieux de réparation, les interruptions de minage et les bris accidentels.
* **Réduction de dégâts purement mathématique** : L'extension de durabilité est calculée par division entière et arrondi probabiliste, assurant une précision parfaite sans altérer les attributs vanilla.
* **Contrôle granulaire** : Configurez 24 catégories individuelles (épées, lances, tridents, masses, arcs, arbalètes, boucliers, outils, pioches, haches, pelles, houes, cisailles, cannes à pêche, pinceaux, briquets, armures, casques, plastrons, jambières, bottes, élytres, armes, global) via 73 GameRules statiques.
* **Mode Dieu (Infini)** : Rendez n'importe quelle catégorie 100% incassable avec une seule GameRule booléenne.
* **Détection automatique d'objets de mods** : Découvre les objets avec durabilité au gel du registre et expose des GameRules dédiées.
* **Zéro désynchronisation** : Les GameRules serveur sont synchronisées aux clients via le réseau Fabric (`durability-multiplier:sync_rules`) pour des infobulles en direct.

---

## 📚 Navigation globale et ressources

* [[Matrice de compatibilité des versions|fr_fr-Version-Compatibility]]
* [[Centre de documentation MC 26.2|fr_fr-26.2-Home]]
* [[Centre de documentation MC 26.1.2|fr_fr-26.1.2-Home]]
* [Page CurseForge](https://curseforge.com/minecraft/mc-mods/durability-multiplier)
* [Page Modrinth](https://modrinth.com/mod/durability-multiplier)
* [Dépôt source GitHub](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier)
