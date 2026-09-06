# Commandes & Administration (26.1.2)

| Système administratif | Détail |
| :--- | :--- |
| **Moteur de commande** | Système de commandes Brigadier `/gamerule` de Minecraft Vanilla |
| **Espace de noms** | Préfixe `ig:` pour toutes les règles |
| **Niveau de permission** | Niveau 2 (OP / Triche activée en solo) |
| **Administration graphique** | Prise en charge via l'écran des GameRules et la configuration ModMenu |
| **Politique d'absence** | **Aucun sous-arbre de commande Brigadier personnalisé** par conception |

---

## ⚡ Flux de travail d'administration en jeu

Durability Multiplier repose entièrement sur les commandes standard `/gamerule`. Aucune commande personnalisée (comme `/durability set` ou `/durability reload`) n'est ajoutée, garantissant 100% de compatibilité native avec les blocs de commande, fonctions, permissions et datapacks.

### Tâches administratives courantes

#### 1. Configurer des améliorations de survie standard
```mcfunction
# Double all items globally (200% durability - Default)
/gamerule ig:dm_percent_global 200

# Give mining tools a 5x (500%) lifespan
/gamerule ig:dm_percent_tools 500

# Give weapons a 3x (300%) lifespan
/gamerule ig:dm_percent_weapons 300
```

#### 2. Configurer les paramètres de combat & serveur PvP
```mcfunction
# Keep armor at vanilla durability (100%) to prevent overly tanky players
/gamerule ig:dm_percent_armor 100

# Give weapons 2x (200%) durability
/gamerule ig:dm_percent_swords 200
/gamerule ig:dm_percent_bows 200
```

#### 3. Activer la survie style Créatif (élytres et outils incassables)
```mcfunction
# Unbreakable Elytra for limitless exploration
/gamerule ig:dm_infinity_elytra true

# Unbreakable Tools for building mega-projects
/gamerule ig:dm_infinity_tools true
```

#### 4. Configurer des objets de mods dynamiques
```mcfunction
# Give a custom modded drill 400% durability
/gamerule ig:percent_techmod_titanium_drill 400

# Make a modded staff unbreakable
/gamerule ig:infinity_magicmod_staff_of_fire true

# Set a modded dagger to Single-Use using the -1 sentinel
/gamerule ig:percent_customweapons_glass_dagger -1
```

#### 5. Masquer le texte de l'infobulle
```mcfunction
# Hide durability multiplier info from item tooltips
/gamerule ig:dm_show_tooltip false
```

