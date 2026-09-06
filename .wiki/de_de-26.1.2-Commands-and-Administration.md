# Befehle & Administration (26.1.2)

| Verwaltungssystem | Detail |
| :--- | :--- |
| **Befehlssystem** | Vanilla Minecraft `/gamerule` Brigadier-Befehlssystem |
| **Namespace** | `ig:`-Präfix für alle Regeln |
| **Berechtigungsstufe** | Stufe 2 (OP / Cheats im Einzelspieler aktiviert) |
| **GUI-Verwaltung** | Unterstützt über das GameRules-Menü und ModMenu-Konfiguration |
| **Abwesenheitsrichtlinie** | **Keine eigenen Brigadier-Befehlsbäume** (nach Design) |

---

## ⚡ Administrations-Arbeitsablauf im Spiel

Durability Multiplier basiert vollständig auf Standard-`/gamerule`-Befehlen von Vanilla. Es werden keine eigenen Befehle (wie `/durability set` oder `/durability reload`) hinzugefügt, was eine 100%ige native Kompatibilität mit Befehlsblöcken, Funktionen, Berechtigungen und Datenpaketen garantiert.

### Häufige administrative Aufgaben

#### 1. Standard-Überlebens-Buffs konfigurieren
```mcfunction
# Double all items globally (200% durability - Default)
/gamerule ig:dm_percent_global 200

# Give mining tools a 5x (500%) lifespan
/gamerule ig:dm_percent_tools 500

# Give weapons a 3x (300%) lifespan
/gamerule ig:dm_percent_weapons 300
```

#### 2. Kampf- & PvP-Server-Einstellungen konfigurieren
```mcfunction
# Keep armor at vanilla durability (100%) to prevent overly tanky players
/gamerule ig:dm_percent_armor 100

# Give weapons 2x (200%) durability
/gamerule ig:dm_percent_swords 200
/gamerule ig:dm_percent_bows 200
```

#### 3. Kreativ-Überlebensmodus aktivieren (unzerstörbare Elytren & Werkzeuge)
```mcfunction
# Unbreakable Elytra for limitless exploration
/gamerule ig:dm_infinity_elytra true

# Unbreakable Tools for building mega-projects
/gamerule ig:dm_infinity_tools true
```

#### 4. Dynamische Mod-Gegenstände konfigurieren
```mcfunction
# Give a custom modded drill 400% durability
/gamerule ig:percent_techmod_titanium_drill 400

# Make a modded staff unbreakable
/gamerule ig:infinity_magicmod_staff_of_fire true

# Set a modded dagger to Single-Use using the -1 sentinel
/gamerule ig:percent_customweapons_glass_dagger -1
```

#### 5. Tooltip-Text ausblenden
```mcfunction
# Hide durability multiplier info from item tooltips
/gamerule ig:dm_show_tooltip false
```

