# Configuration & Intégration GUI (26.1.2)

| Paramètre système | Valeur |
| :--- | :--- |
| **Chemin du fichier de configuration** | `config/durability-multiplier.json` |
| **Version de configuration** | `2` (Migration automatique depuis v1) |
| **Fournisseurs d'interface** | Cloth Config (`me.shedaniel.cloth:cloth-config-fabric`) & ModMenu |
| **Classe de configuration** | `net.instantgratification.durabilitymultiplier.config.DurabilityConfig` |
| **Assistant GUI** | `ClothConfigScreenHelper` & `ModMenuIntegration` |
| **Loi de précédence** | Le fichier définit **UNIQUEMENT LES VALEURS PAR DÉFAUT DES NOUVEAUX MONDES** ; les mondes actifs utilisent les GameRules |

---

## ⚙️ Structure du fichier de configuration (`config/durability-multiplier.json`)

Le fichier de configuration définit les paramètres de base pour tous les mondes et serveurs nouvellement créés. Il prend en charge les pourcentages de durabilité, le Mode Dieu (Infini), l'Usage unique (Mode Verre), le formatage d'infobulle et les remplacements pour mods.

```json
{
  "configVersion": 2,
  
  "percentGlobal": 200,
  "percentWeapons": 0,
  "percentSwords": 0,
  "percentSpears": 0,
  "percentTridents": 0,
  "percentMaces": 0,
  "percentBows": 0,
  "percentCrossbows": 0,
  "percentTools": 0,
  "percentPickaxes": 0,
  "percentAxes": 0,
  "percentShovels": 0,
  "percentHoes": 0,
  "percentShears": 0,
  "percentFishingRods": 0,
  "percentBrushes": 0,
  "percentFlintAndSteel": 0,
  "percentArmor": 0,
  "percentHelmets": 0,
  "percentChestplates": 0,
  "percentLeggings": 0,
  "percentBoots": 0,
  "percentElytra": 0,
  "percentShields": 0,
  
  "infinityGlobal": false,
  "infinityWeapons": false,
  "infinitySwords": false,
  "infinitySpears": false,
  "infinityTridents": false,
  "infinityMaces": false,
  "infinityBows": false,
  "infinityCrossbows": false,
  "infinityTools": false,
  "infinityPickaxes": false,
  "infinityAxes": false,
  "infinityShovels": false,
  "infinityHoes": false,
  "infinityShears": false,
  "infinityFishingRods": false,
  "infinityBrushes": false,
  "infinityFlintAndSteel": false,
  "infinityArmor": false,
  "infinityHelmets": false,
  "infinityChestplates": false,
  "infinityLeggings": false,
  "infinityBoots": false,
  "infinityElytra": false,
  "infinityShields": false,
  
  "singleUseGlobal": false,
  "singleUseWeapons": false,
  "singleUseSwords": false,
  "singleUseSpears": false,
  "singleUseTridents": false,
  "singleUseMaces": false,
  "singleUseBows": false,
  "singleUseCrossbows": false,
  "singleUseTools": false,
  "singleUsePickaxes": false,
  "singleUseAxes": false,
  "singleUseShovels": false,
  "singleUseHoes": false,
  "singleUseShears": false,
  "singleUseFishingRods": false,
  "singleUseBrushes": false,
  "singleUseFlintAndSteel": false,
  "singleUseArmor": false,
  "singleUseHelmets": false,
  "singleUseChestplates": false,
  "singleUseLeggings": false,
  "singleUseBoots": false,
  "singleUseElytra": false,
  "singleUseShields": false,
  
  "showTooltip": true,
  "tooltipFormat": "ADAPTIVE",
  
  "forcedItems": [],
  "forcedPercentages": {},
  "forcedInfinities": {},
  "forcedSingleUses": {}
}
```

---

## 🔄 Le système de remplissage automatique

Durability Multiplier intègre un **Scanner de découverte universel à 3 niveaux** autonome qui catalogue automatiquement les objets de mods sans saisie manuelle :

1. **Analyse de démarrage** : Au lancement du client/serveur, le moteur analyse `BuiltInRegistries.ITEM`.
2. **Filtre de durabilité** : Les objets des espaces de noms externes (hors `minecraft` et tags de convention `c`) sont vérifiés pour `DataComponents.MAX_DAMAGE > 0`.
3. **Remplissage automatique** : Les objets avec durabilité découverts sont ajoutés automatiquement à :
   * `"forcedItems"`: Added to the active item registry list.
   * `"forcedPercentages"`: Added with default value `0` (indicating the item dynamically inherits its category or global multiplier).
4. **Persistance de configuration** : Les listes sont enregistrées dans `config/durability-multiplier.json`, rendant les objets éditables dans l'interface et les GameRules.

---

## 🛠️ Guide de configuration manuelle des objets

Les auteurs de modpacks, administrateurs et joueurs peuvent déclarer manuellement des règles spécifiques dans `config/durability-multiplier.json` :

### 1. `forcedItems` (Enregistrement d'objets)
Déclare la liste des identifiants d'objets reconnus par le mod.
```json
"forcedItems": [
  "techmod:plasma_cutter",
  "magicmod:staff_of_fire",
  "customweapons:obsidian_blade"
]
```

### 2. `forcedPercentages` (Pourcentages de durabilité par objet)
Assigne des multiplicateurs de pourcentage explicites à des objets spécifiques :
* `0` : Hérite de la catégorie parente ou du multiplicateur global.
* `100` : Base vanilla 100% (durabilité 1x).
* `200` : Durabilité 200% (durée de vie 2x).
* `50` : Durabilité 50% (moitié de vie / usure 2x).
* `-1` : Usage unique (Mode Verre - se brise au premier coup).
```json
"forcedPercentages": {
  "techmod:plasma_cutter": 300,
  "customweapons:obsidian_blade": -1,
  "magicmod:training_wand": 50
}
```

### 3. `forcedInfinities` (Mode Dieu par objet)
Accorde le statut incassable permanent à des objets spécifiques :
```json
"forcedInfinities": {
  "magicmod:creative_staff": true,
  "adminmod:ban_hammer": true
}
```

### 4. `forcedSingleUses` (Mode Verre par objet)
Force des objets spécifiques à se briser après une seule perte de durabilité :
```json
"forcedSingleUses": {
  "techmod:disposable_cutter": true,
  "survivalplus:glass_dagger": true
}
```

---

## ⚡ Sentinelle `-1` du mode Verre pour utilisateurs avancés

Durability Multiplier inclut une **Valeur sentinelle `-1`** pour les pourcentages :
* Définir une règle de pourcentage sur `-1` (ou tout entier négatif) active automatiquement le **Mode Verre (Usage unique)** pour cet objet ou cette catégorie.
* Lorsqu'il est actif, l'objet subit `maxDamage - damageValue` au premier coup, ramenant sa durabilité à 0 et le brisant en exactement 1 usage.
* Permet aux administrateurs d'appliquer la mécanique de bris en 1 coup directement par curseur de pourcentage ou commande `/gamerule`.

---

## 🎨 Formatage de l'affichage des infobulles

L'option `tooltipFormat` configure l'affichage des bonus de durabilité sur les infobulles :

| Paramètre de format | Exemple (200% / 2x) | Exemple (150% / 1.5x) | Description |
| :--- | :--- | :--- | :--- |
| `"ADAPTIVE"` *(Défaut)* | `⟨2x Durabilité des épées⟩` | `⟨150% Durabilité des épées⟩` | Affiche des multiplicateurs entiers pour les centaines exactes, des pourcentages sinon. |
| `"PERCENTAGE"` | `⟨200% Durabilité des épées⟩` | `⟨150% Durabilité des épées⟩` | Affiche toujours le pourcentage exact. |
| `"MULTIPLIER"` | `⟨2x Durabilité des épées⟩` | `⟨1.5x Durabilité des épées⟩` | Affiche toujours le facteur multiplicateur formaté. |

Définissez `"showTooltip": false` pour masquer complètement les indicateurs de durabilité.

---

## ⚠️ Avertissement important sur la priorité de configuration

> ⚠️ **Remarque** : Les modifications dans `durability-multiplier.json` ou l'interface ModMenu **définissent uniquement les valeurs par défaut des nouveaux mondes**.
> 
> Pour les mondes existants, chaque monde conserve son propre état GameRule dans `level.dat`. Pour modifier les réglages dans un monde actif, utilisez la commande `/gamerule` en jeu ou l'écran d'édition des GameRules.

