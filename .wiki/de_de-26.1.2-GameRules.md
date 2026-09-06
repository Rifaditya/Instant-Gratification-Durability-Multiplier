# GameRules-Referenz (26.1.2)

Alle GameRules von Durability Multiplier sind in der Kategorie **`durability-multiplier:durability_multiplier`** registriert.

---

## 📊 Vollständige GameRules-Referenztabellen

### 1. Haltbarkeits-Prozentsatz GameRules
Prozentregeln steuern die Skalierung der Gegenstandshaltbarkeit.
* `200` = 200% (2x Haltbarkeit)
* `100` = 100% (Vanilla 1x Basiswert)
* `50` = 50% (Halbe Haltbarkeit / 2x Abnutzung)
* `0` = Erbt von übergeordneter Kategorie oder globalem Standard
* `-1` = Signalwert für **Einmalige Verwendung (Glas-Modus)** (bricht nach 1 Schlag)

| # | GameRule-Bezeichner | Typ | Standard | Min | Beschreibung & Verhalten |
| :-: | :--- | :---: | :---: | :---: | :--- |
| 1 | `ig:dm_percent_global` | `Integer` | `200` | `-1` | Globaler Basis-Prozentsatz für alle beschädigbaren Gegenstände. |
| 2 | `ig:dm_percent_weapons` | `Integer` | `0` | `-1` | Globale Überschreibung für alle Waffen (Schwerter, Speere, Dreizacke, Streitkolben, Bögen, Armbrüste). |
| 3 | `ig:dm_percent_swords` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Schwerter (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Speere (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Dreizacke (`TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Streitkolben (`MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Bögen (`BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Armbrüste (`CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Schilde (`ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `Integer` | `0` | `-1` | Prozentsatz der übergeordneten Kategorie für alle Werkzeuge. |
| 11 | `ig:dm_percent_pickaxes` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Spitzhacken (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Äxte (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Schaufeln (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Hacken (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Scheren (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Angeln (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Pinsel (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Feuerzeuge (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `Integer` | `0` | `-1` | Prozentsatz der übergeordneten Kategorie für alle Rüstungsteile. |
| 20 | `ig:dm_percent_helmets` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Helme (`#c:helmets`, Kopf-Slot). |
| 21 | `ig:dm_percent_chestplates` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Brustpanzer (`#c:chestplates`, Brust-Slot). |
| 22 | `ig:dm_percent_leggings` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Beinschützer (`#c:leggings`, Bein-Slot). |
| 23 | `ig:dm_percent_boots` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Stiefel (`#c:boots`, Fuß-Slot). |
| 24 | `ig:dm_percent_elytra` | `Integer` | `0` | `-1` | Spezifischer Prozentsatz für Elytren (`Items.ELYTRA`, `GLIDER`). |

---

### 2. Gott-Modus (Unendlichkeit) GameRules
Wenn aktiviert (`true`), nehmen Gegenstände dieser Kategorie $0$ Schaden und brechen nie.

| # | GameRule-Bezeichner | Typ | Standard | Beschreibung |
| :-: | :--- | :---: | :---: | :--- |
| 25 | `ig:dm_infinity_global` | `Boolean` | `false` | Globaler Gott-Modus für alle beschädigbaren Gegenstände im Spiel. |
| 26 | `ig:dm_infinity_weapons` | `Boolean` | `false` | Gott-Modus für alle Waffen. |
| 27 | `ig:dm_infinity_swords` | `Boolean` | `false` | Gott-Modus für Schwerter. |
| 28 | `ig:dm_infinity_spears` | `Boolean` | `false` | Gott-Modus für Speere. |
| 29 | `ig:dm_infinity_tridents` | `Boolean` | `false` | Gott-Modus für Dreizacke. |
| 30 | `ig:dm_infinity_maces` | `Boolean` | `false` | Gott-Modus für Streitkolben. |
| 31 | `ig:dm_infinity_bows` | `Boolean` | `false` | Gott-Modus für Bögen. |
| 32 | `ig:dm_infinity_crossbows` | `Boolean` | `false` | Gott-Modus für Armbrüste. |
| 33 | `ig:dm_infinity_shields` | `Boolean` | `false` | Gott-Modus für Schilde. |
| 34 | `ig:dm_infinity_tools` | `Boolean` | `false` | Gott-Modus für alle Werkzeuge. |
| 35 | `ig:dm_infinity_pickaxes` | `Boolean` | `false` | Gott-Modus für Spitzhacken. |
| 36 | `ig:dm_infinity_axes` | `Boolean` | `false` | Gott-Modus für Äxte. |
| 37 | `ig:dm_infinity_shovels` | `Boolean` | `false` | Gott-Modus für Schaufeln. |
| 38 | `ig:dm_infinity_hoes` | `Boolean` | `false` | Gott-Modus für Hacken. |
| 39 | `ig:dm_infinity_shears` | `Boolean` | `false` | Gott-Modus für Scheren. |
| 40 | `ig:dm_infinity_fishing_rods` | `Boolean` | `false` | Gott-Modus für Angeln. |
| 41 | `ig:dm_infinity_brushes` | `Boolean` | `false` | Gott-Modus für Pinsel. |
| 42 | `ig:dm_infinity_flint_and_steel` | `Boolean` | `false` | Gott-Modus für Feuerzeuge. |
| 43 | `ig:dm_infinity_armor` | `Boolean` | `false` | Gott-Modus für alle Rüstungen. |
| 44 | `ig:dm_infinity_helmets` | `Boolean` | `false` | Gott-Modus für Helme. |
| 45 | `ig:dm_infinity_chestplates` | `Boolean` | `false` | Gott-Modus für Brustpanzer. |
| 46 | `ig:dm_infinity_leggings` | `Boolean` | `false` | Gott-Modus für Beinschützer. |
| 47 | `ig:dm_infinity_boots` | `Boolean` | `false` | Gott-Modus für Stiefel. |
| 48 | `ig:dm_infinity_elytra` | `Boolean` | `false` | Gott-Modus für Elytren. |

---

### 3. Einmalgebrauch (Glas-Modus) GameRules
Wenn aktiviert (`true`), zerbrechen Gegenstände dieser Kategorie nach einem einzigen Schlag.

| # | GameRule-Bezeichner | Typ | Standard | Beschreibung |
| :-: | :--- | :---: | :---: | :--- |
| 49 | `ig:dm_single_use_global` | `Boolean` | `false` | Globaler Glas-Modus (Einmalgebrauch) für alle Gegenstände. |
| 50 | `ig:dm_single_use_weapons` | `Boolean` | `false` | Einmalgebrauch für alle Waffen. |
| 51 | `ig:dm_single_use_swords` | `Boolean` | `false` | Einmalgebrauch für Schwerter. |
| 52 | `ig:dm_single_use_spears` | `Boolean` | `false` | Einmalgebrauch für Speere. |
| 53 | `ig:dm_single_use_tridents` | `Boolean` | `false` | Einmalgebrauch für Dreizacke. |
| 54 | `ig:dm_single_use_maces` | `Boolean` | `false` | Einmalgebrauch für Streitkolben. |
| 55 | `ig:dm_single_use_bows` | `Boolean` | `false` | Einmalgebrauch für Bögen. |
| 56 | `ig:dm_single_use_crossbows` | `Boolean` | `false` | Einmalgebrauch für Armbrüste. |
| 57 | `ig:dm_single_use_shields` | `Boolean` | `false` | Einmalgebrauch für Schilde. |
| 58 | `ig:dm_single_use_tools` | `Boolean` | `false` | Einmalgebrauch für alle Werkzeuge. |
| 59 | `ig:dm_single_use_pickaxes` | `Boolean` | `false` | Einmalgebrauch für Spitzhacken. |
| 60 | `ig:dm_single_use_axes` | `Boolean` | `false` | Einmalgebrauch für Äxte. |
| 61 | `ig:dm_single_use_shovels` | `Boolean` | `false` | Einmalgebrauch für Schaufeln. |
| 62 | `ig:dm_single_use_hoes` | `Boolean` | `false` | Einmalgebrauch für Hacken. |
| 63 | `ig:dm_single_use_shears` | `Boolean` | `false` | Einmalgebrauch für Scheren. |
| 64 | `ig:dm_single_use_fishing_rods` | `Boolean` | `false` | Einmalgebrauch für Angeln. |
| 65 | `ig:dm_single_use_brushes` | `Boolean` | `false` | Einmalgebrauch für Pinsel. |
| 66 | `ig:dm_single_use_flint_and_steel` | `Boolean` | `false` | Einmalgebrauch für Feuerzeuge. |
| 67 | `ig:dm_single_use_armor` | `Boolean` | `false` | Einmalgebrauch für alle Rüstungen. |
| 68 | `ig:dm_single_use_helmets` | `Boolean` | `false` | Einmalgebrauch für Helme. |
| 69 | `ig:dm_single_use_chestplates` | `Boolean` | `false` | Einmalgebrauch für Brustpanzer. |
| 70 | `ig:dm_single_use_leggings` | `Boolean` | `false` | Einmalgebrauch für Beinschützer. |
| 71 | `ig:dm_single_use_boots` | `Boolean` | `false` | Einmalgebrauch für Stiefel. |
| 72 | `ig:dm_single_use_elytra` | `Boolean` | `false` | Einmalgebrauch für Elytren. |

---

### 4. Anzeige & dynamische Mod-GameRules

| GameRule-Bezeichner | Typ | Standard | Beschreibung |
| :--- | :---: | :---: | :--- |
| `ig:dm_show_tooltip` | `Boolean` | `true` | Zeigt Haltbarkeitsbonus in Gegenstands-Tooltips an. |
| `ig:percent_<mod>_<item>` | `Integer` | `0` | Dynamische Prozent-Überschreibung für Mod-Gegenstand (Min `-1`). |
| `ig:infinity_<mod>_<item>` | `Boolean` | `false` | Dynamische Gott-Modus-Überschreibung für Mod-Gegenstand. |
| `ig:single_use_<mod>_<item>` | `Boolean` | `false` | Dynamische Einmal-Gebrauch-Überschreibung für Mod-Gegenstand. |

---

## ⚡ Befehle zur Anpassung im Spiel

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

