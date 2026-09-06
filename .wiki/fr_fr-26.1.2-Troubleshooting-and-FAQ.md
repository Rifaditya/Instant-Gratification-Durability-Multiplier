# Dépannage et Foire Aux Questions (26.1.2)

| Sujet système | Résumé |
| :--- | :--- |
| **Comportement de précédence** | Les GameRules priment dans les mondes actifs ; le config définit les bases des nouveaux mondes |
| **Moteur de calcul** | Interception probabiliste (zéro mutation NBT, zéro désynchronisation) |
| **Tolérance aux cas limites** | 100% sans crash lors du retrait de mods, démappage de registres et composants manquants |

---

## ❓ Foire Aux Questions (FAQ)

### Q1 : Pourquoi les modifications dans ModMenu n'affectent-elles pas mon monde solo actif ?
**Réponse** : Selon la **Loi de précédence**, les modifications dans `durability-multiplier.json` ou ModMenu définissent les valeurs par défaut pour les **NOUVEAUX mondes uniquement**. Pour modifier votre monde actuel, utilisez `/gamerule` en jeu (ex. `/gamerule ig:dm_percent_tools 500`) ou l'écran d'édition des GameRules.

### Q2 : Pourquoi l'infobulle d'un objet n'affiche-t-elle pas de texte de pourcentage ou de multiplicateur ?
**Réponse** :
1. Vérifiez que l'objet a une barre de durabilité (`DataComponents.MAX_DAMAGE > 0`).
2. Vérifiez si `ig:dm_show_tooltip` est sur `true`.
3. Si le réglage actif est `100` (100% durabilité vanilla), aucune ligne supplémentaire n'est rendue pour garder les infobulles propres.

### Q3 : Pourquoi mon outil à 500 % (5x) a-t-il subi des dégâts de durabilité après seulement 2 coups ?
**Réponse** : Durability Multiplier utilise l'**interception probabiliste des dégâts** (le même mécanisme que l'enchantement *Solidité* de Minecraft) pour garantir une **sécurité absolue des sauvegardes**. À 500% (durabilité 5x), chaque bloc brisé a une **chance indépendante de 20% (1 sur 5)** de subir 1 dégât et **80% de chance** de l'absorber. Comme chaque coup est calculé indépendamment, un outil peut subir des dégâts après 2 ou 8 coups, mais sur toute sa durée de vie, il durera exactement 5 fois plus longtemps (~7 805 blocs brisés pour une pioche en diamant).

### Q4 : Dois-je saisir des décimales comme 0,5 ou 1,5 dans les GameRules ?
**Réponse** : **Non**. Les GameRules n'acceptent que des entiers (`int`). Saisissez toujours des pourcentages entiers :
* `50` pour 50% (moitié de durabilité / usure 2x)
* `100` pour 100% (base vanilla 1x)
* `150` pour 150% (hausse de durabilité 1.5x)
* `200` pour 200% (durabilité doublée 2x)
* `-1` pour Usage unique (Mode Verre / se brise en 1 coup)

### Q5 : Durability Multiplier fonctionne-t-il avec l'enchantement Solidité (Unbreaking) ?
**Réponse** : Oui ! Durability Multiplier calcule les dégâts **avant** l'application des enchantements vanilla. Une pioche Solidité III avec un réglage de 200% (2x) durera environ $4 \times 2 = 8\times$ plus longtemps qu'une pioche non enchantée.

### Q6 : Comment activer le mode Verre (usage unique en 1 coup) pour un objet ?
**Réponse** : Vous pouvez :
1. Définir la GameRule d'usage unique sur true : `/gamerule ig:dm_single_use_swords true` (ou `/gamerule ig:single_use_<mod>_<item> true`).
2. Utiliser la **Sentinelle experte `-1`** : définissez la règle de pourcentage sur `-1`, ex. `/gamerule ig:dm_percent_swords -1` ou `/gamerule ig:percent_<mod>_<item> -1`.

---

## 🔍 Cas limites approfondis & Comportement du cycle de vie

### Cas limite 1 : Désinstallation du mod & Suppression d'objets
Lorsqu'un joueur supprime un mod ayant des objets enregistrés dans Durability Multiplier :
1. **Sécurité du fichier de configuration** : Les identifiants des objets retirés restent conservés dans `forcedItems` et `forcedPercentages`.
2. **État dormant en mémoire** : Les GameRules dynamiques stockées dans `level.dat` restent totalement inactives.
3. **Zéro crash et zéro corruption** : Comme la recherche passe par `BuiltInRegistries.ITEM.getKey(stack.getItem())`, le jeu ne cherche jamais de classes absentes. Des erreurs comme `NullPointerException` sont impossibles.
4. **Restauration automatique** : Si le mod est réinstallé plus tard, toutes les configurations antérieures se **reconnectent instantanément** sans reconfiguration !
5. **Nettoyage manuel (Facultatif)** : Si vous souhaitez purger les entrées de mods supprimés de la configuration :
   * Open `config/durability-multiplier.json` in a text editor.
   * Remove the deleted item ID string from the `"forcedItems"` list.
   * Remove the corresponding keys from `"forcedPercentages"`, `"forcedInfinities"`, and `"forcedSingleUses"`.
   * Save the file.

---

### Cas limite 2 : Filtrage strict de la durabilité (`MAX_DAMAGE > 0`)
Pourquoi les meubles de mods (ex. chaises/armoires de Macaw's Furniture), blocs, nourriture ou matériaux n'apparaissent-ils pas dans les GameRules ou `durability-multiplier.json` ?
* Durability Multiplier vérifie strictement `DataComponents.MAX_DAMAGE > 0` avant d'enregistrer tout objet.
* Les objets sans durabilité (blocs, nourriture, lingots, graines) sont rejetés en $0.0001\mu\text{s}$ au démarrage.
* Cela évite la pollution de l'autocomplétion et la maintient fluide et propre.

---

### Cas limite 3 : Hiérarchie complète d'évaluation et de priorité
Lorsqu'un objet subit des dégâts de durabilité, le résultat est déterminé selon la hiérarchie stricte suivante :

$$\text{God Mode (Infinity)} \longrightarrow \text{Single-Use (Glass Mode)} \longrightarrow \text{Per-Item Override} \longrightarrow \text{Subcategory} \longrightarrow \text{Parent Category} \longrightarrow \text{Global} \longrightarrow \text{100\% Vanilla}$$

1. **Vérification du Mode Dieu Incassable** :
   * Per-Item God Mode (`ig:infinity_<mod>_<item>` / `forcedInfinities`)
   * Subcategory God Mode (`ig:dm_infinity_pickaxes`, `ig:dm_infinity_swords`, etc.)
   * Parent Category God Mode (`ig:dm_infinity_tools`, `ig:dm_infinity_weapons`, `ig:dm_infinity_armor`)
   * Global God Mode (`ig:dm_infinity_global`)
   * *If any is `true` $\rightarrow$ Damage = $0$ (Unbreakable).*
2. **Vérification Usage unique (Mode Verre)** :
   * Effective percentage $\le -1$ (`-1` Glass Mode Sentinel)
   * Per-Item Single-Use (`ig:single_use_<mod>_<item>` / `forcedSingleUses`)
   * Subcategory Single-Use (`ig:dm_single_use_pickaxes`, `ig:dm_single_use_swords`, etc.)
   * Parent Category Single-Use (`ig:dm_single_use_tools`, `ig:dm_single_use_weapons`, `ig:dm_single_use_armor`)
   * Global Single-Use (`ig:dm_single_use_global`)
   * *If active $\rightarrow$ Item loses all remaining durability in 1 hit.*
3. **Résolution de l'échelonnage de pourcentage** :
   * Per-Item Override (`ig:percent_<mod>_<item>` / `forcedPercentages`) if $\neq 0$
   * Subcategory Percentage (`ig:dm_percent_pickaxes`, `ig:dm_percent_swords`, etc.) if $\neq 0$
   * Parent Category Percentage (`ig:dm_percent_tools`, `ig:dm_percent_weapons`, `ig:dm_percent_armor`) if $\neq 0$
   * Global Percentage (`ig:dm_percent_global`) if $\neq 0$
   * Vanilla Fallback: $100\%$ baseline.

