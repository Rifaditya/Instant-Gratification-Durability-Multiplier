# Haltbarkeits-Multiplikatoren & Prozentsätze (26.2)

Durability Multiplier ersetzt die feste Vanilla-Abnutzung durch eine dynamische **Prozent-Skalierungs-Engine**, die sowohl Haltbarkeits-Boni (z. B. 200% = 2x, 500% = 5x) als auch Abnutzungs-Strafen (z. B. 50% = 0.5x, 25% = 0.25x) unterstützt.

---

## ⚙️ Kern-Prozent-GameRules

| # | GameRule-Bezeichner | Standard | Zielkategorie / Beschreibung |
| :-: | :--- | :---: | :--- |
| 1 | `ig:dm_percent_global` | `200` | Globaler Prozentsatz für alle beschädigbaren Gegenstände. |
| 2 | `ig:dm_percent_weapons` | `0` | Übergeordnete Überschreibung für alle Waffen (Schwerter, Speere, Dreizacke, Streitkolben, Bögen, Armbrüste). |
| 3 | `ig:dm_percent_swords` | `0` | Spezifischer Prozentsatz für Schwerter (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `0` | Spezifischer Prozentsatz für Speere (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `0` | Spezifischer Prozentsatz für Dreizacke (`Items.TRIDENT`, `TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `0` | Spezifischer Prozentsatz für Streitkolben (`Items.MACE`, `MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `0` | Spezifischer Prozentsatz für Bögen (`Items.BOW`, `BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `0` | Spezifischer Prozentsatz für Armbrüste (`Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `0` | Spezifischer Prozentsatz für Schilde (`Items.SHIELD`, `ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `0` | Prozentsatz der übergeordneten Kategorie für alle Werkzeuge. |
| 11 | `ig:dm_percent_pickaxes` | `0` | Spezifischer Prozentsatz für Spitzhacken (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `0` | Spezifischer Prozentsatz für Äxte (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `0` | Spezifischer Prozentsatz für Schaufeln (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `0` | Spezifischer Prozentsatz für Hacken (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `0` | Spezifischer Prozentsatz für Scheren (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `0` | Spezifischer Prozentsatz für Angeln (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `0` | Spezifischer Prozentsatz für Pinsel (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `0` | Spezifischer Prozentsatz für Feuerzeuge (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `0` | Prozentsatz der übergeordneten Kategorie für alle Rüstungsteile. |
| 20 | `ig:dm_percent_helmets` | `0` | Spezifischer Prozentsatz für Helme (`#minecraft:head_armor`, `#c:helmets`). |
| 21 | `ig:dm_percent_chestplates` | `0` | Spezifischer Prozentsatz für Brustpanzer (`#minecraft:chest_armor`, `#c:chestplates`). |
| 22 | `ig:dm_percent_leggings` | `0` | Spezifischer Prozentsatz für Beinschützer (`#minecraft:leg_armor`, `#c:leggings`). |
| 23 | `ig:dm_percent_boots` | `0` | Spezifischer Prozentsatz für Stiefel (`#minecraft:foot_armor`, `#c:boots`). |
| 24 | `ig:dm_percent_elytra` | `0` | Spezifischer Prozentsatz für Elytren (`Items.ELYTRA`, `DataComponents.GLIDER`). |

> [!NOTE]
> Auf `0` gesetzte Überschreibungsregeln fallen automatisch auf die übergeordnete Kategorie oder den globalen Standard zurück. Der Wert `-1` aktiviert den **Einmaligen Gebrauch (Glas-Modus)**.

---

## 🔒 100% Speichersicherheit für Welten
Durability Multiplier verändert **kein** Gegenstands-NBT oder `DataComponents.MAX_DAMAGE` in Speicherdateien. Jegliche Skalierung erfolgt dynamisch bei der Schadensberechnung, was absolute Speicherdaten-Sicherheit garantiert.
