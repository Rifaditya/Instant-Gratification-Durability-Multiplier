# Konfiguration & GUI-Integration (26.2)

| Systemparameter | Wert |
| :--- | :--- |
| **Konfigurationsdateipfad** | `config/durability-multiplier.json` |
| **Konfigurationsversion** | `2` (Automatische Migration von v1) |
| **GUI-Anbieter** | Cloth Config (`me.shedaniel.cloth:cloth-config-fabric`) & ModMenu |
| **Konfigurationsklasse** | `net.instantgratification.durabilitymultiplier.config.DurabilityConfig` |
| **GUI-Helfer** | `ClothConfigScreenHelper` & `ModMenuIntegration` |
| **Vorrang-Gesetz** | Konfigurationsdatei definiert **NUR STANDARDS FÜR NEUE WELTEN**; aktive Welten nutzen GameRules |

---

## ⚙️ Konfigurationsdateistruktur (`config/durability-multiplier.json`)

Die Konfigurationsdatei definiert grundlegende Einstellungen und Standardwerte für neu erstellte Welten und Server. Sie unterstützt Haltbarkeitsprozentsätze, Gott-Modus (Unendlich), Einmalige Verwendung (Glas-Modus), Tooltip-Formatierung und Mod-Überschreibungen.

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

## 🔄 Das System zur automatischen Befüllung

Durability Multiplier verfügt über einen autonomen **Universellen 3-Stufen-Erkennungsscanner**, der Mod-Gegenstände ohne manuelle Dateneingabe katalogisiert:

1. **Startprüfung**: Beim Start von Client/Server scannt die Engine `BuiltInRegistries.ITEM`.
2. **Haltbarkeitsfilter**: Gegenstände aus externen Mod-Namespaces (außer `minecraft` und Konventionstags `c`) werden auf `DataComponents.MAX_DAMAGE > 0` geprüft.
3. **Automatisches Auffüllen**: Erkannte Gegenstände mit Haltbarkeit werden automatisch hinzugefügt zu:
   * `"forcedItems"`: Added to the active item registry list.
   * `"forcedPercentages"`: Added with default value `0` (indicating the item dynamically inherits its category or global multiplier).
4. **Konfigurationspersistenz**: Die aktualisierten Listen werden in `config/durability-multiplier.json` gespeichert und sind sofort in der GUI und in GameRules verfügbar.

---

## 🛠️ Leitfaden zur manuellen Gegenstandskonfiguration

Modpack-Autoren, Server-Administratoren und Spieler können eigene Regeln für bestimmte Gegenstände direkt in `config/durability-multiplier.json` definieren:

### 1. `forcedItems` (Gegenstandsregistrierung)
Deklariert die Liste der von der Mod erkannten Gegenstands-IDs.
```json
"forcedItems": [
  "techmod:plasma_cutter",
  "magicmod:staff_of_fire",
  "customweapons:obsidian_blade"
]
```

### 2. `forcedPercentages` (Haltbarkeitsprozente pro Gegenstand)
Weist bestimmten Gegenständen explizite Haltbarkeits-Multiplikatoren zu:
* `0`: Erbt von übergeordneter Kategorie oder globalem Multiplikator.
* `100`: Vanilla 100% Basiswert (1x Haltbarkeit).
* `200`: 200% Haltbarkeit (doppelte Lebensdauer).
* `50`: 50% Haltbarkeit (halbe Lebensdauer / 2x Abnutzung).
* `-1`: Einmalige Verwendung (Glas-Modus - bricht beim ersten Schlag).
```json
"forcedPercentages": {
  "techmod:plasma_cutter": 300,
  "customweapons:obsidian_blade": -1,
  "magicmod:training_wand": 50
}
```

### 3. `forcedInfinities` (Gott-Modus pro Gegenstand)
Verleiht bestimmten Gegenständen permanenten unzerstörbaren Status:
```json
"forcedInfinities": {
  "magicmod:creative_staff": true,
  "adminmod:ban_hammer": true
}
```

### 4. `forcedSingleUses` (Glas-Modus pro Gegenstand)
Erzwingt das Zerbrechen bestimmter Gegenstände nach einem einzigen Haltbarkeitsverlust:
```json
"forcedSingleUses": {
  "techmod:disposable_cutter": true,
  "survivalplus:glass_dagger": true
}
```

---

## ⚡ Power-User `-1` Glas-Modus-Sentinel

Durability Multiplier enthält einen **Signalwert `-1`** für Haltbarkeitsprozentsätze:
* Das Setzen eines Prozentfelds auf `-1` (oder eine negative Ganzzahl) aktiviert automatisch **Einmalige Verwendung (Glas-Modus)** für diesen Gegenstand oder diese Kategorie.
* Wenn aktiv, erleidet der Gegenstand beim ersten Treffer `maxDamage - damageValue` Schaden, wodurch die Haltbarkeit auf 0 sinkt und er nach genau 1 Nutzung bricht.
* Dies ermöglicht Administratoren das Durchsetzen von 1-Treffer-Bruchmechaniken direkt über Schieberegler oder `/gamerule`-Befehle.

---

## 🎨 Formatierung der Tooltip-Anzeige

Die Option `tooltipFormat` legt fest, wie Haltbarkeitsboni auf Tooltips dargestellt werden:

| Formateinstellung | Beispielausgabe (200% / 2x) | Beispielausgabe (150% / 1.5x) | Beschreibung |
| :--- | :--- | :--- | :--- |
| `"ADAPTIVE"` *(Standard)* | `⟨2x Schwerter-Haltbarkeit⟩` | `⟨150% Schwerter-Haltbarkeit⟩` | Zeigt saubere ganzzahlige Multiplikatoren für glatte Hunderter, andernfalls Prozente. |
| `"PERCENTAGE"` | `⟨200% Schwerter-Haltbarkeit⟩` | `⟨150% Schwerter-Haltbarkeit⟩` | Zeigt immer den genauen Prozentwert an. |
| `"MULTIPLIER"` | `⟨2x Schwerter-Haltbarkeit⟩` | `⟨1.5x Schwerter-Haltbarkeit⟩` | Zeigt immer die formatierte Multiplikator-Zeichenkette an. |

Setze `"showTooltip": false`, um Haltbarkeitsanzeigen vollständig auszublenden.

---

## ⚠️ Wichtige Warnung zur Konfigurations-Rangfolge

> ⚠️ **Hinweis**: Änderungen in `durability-multiplier.json` oder im ModMenu-Menü **definieren nur Standardwerte für neu erstellte Welten**.
> 
> Für bestehende Welten behält jede Welt ihren eigenen GameRule-Status in `level.dat`. Um Einstellungen in einer aktiven Welt zu ändern, nutze den `/gamerule`-Befehl im Spiel oder das GameRules-Bearbeitungsmenü.

