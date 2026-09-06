# Fehlerbehebung & Häufig gestellte Fragen (26.2)

| Systemthema | Zusammenfassung |
| :--- | :--- |
| **Vorrangverhalten** | GameRules überschreiben die Konfiguration in aktiven Welten; Config setzt Standards für neue Welten |
| **Berechnungs-Engine** | Probabilistisches Abfangen (keine NBT-Veränderung, keine Desynchronisation) |
| **Randfall-Toleranz** | 100% absturzfrei beim Entfernen von Mods, Register-Freigaben und fehlenden Komponenten |

---

## ❓ Häufig gestellte Fragen (FAQ)

### Q1: Warum wirken sich Konfigurationsänderungen in ModMenu nicht auf meine aktive Einzelspielerwelt aus?
**Antwort**: Gemäß dem **Vorrang-Gesetz** definieren Änderungen in `durability-multiplier.json` oder ModMenu Standardwerte **NUR FÜR NEUE WELTEN**. Um Werte in deiner aktuellen Welt zu ändern, nutze den `/gamerule`-Befehl im Spiel (z. B. `/gamerule ig:dm_percent_tools 500`) oder das GameRules-Menü.

### Q2: Warum zeigt ein Gegenstands-Tooltip keinen Prozentsatz oder Multiplikator-Text an?
**Antwort**:
1. Prüfe, ob der Gegenstand eine Haltbarkeitsleiste besitzt (`DataComponents.MAX_DAMAGE > 0`).
2. Prüfe, ob `ig:dm_show_tooltip` auf `true` steht.
3. Steht der Wert auf `100` (100% Vanilla-Haltbarkeit), wird keine zusätzliche Zeile gerendert, um Tooltips sauber zu halten.

### Q3: Warum hat mein 500% (5x) Werkzeug bereits nach 2 Schlägen Haltbarkeit verloren?
**Antwort**: Durability Multiplier nutzt **probabilistisches Abfangen von Schaden** (dieselbe Mechanik wie die Vanilla-Verzauberung *Haltbarkeit*), um **100%ige Speichersicherheit** zu garantieren. Bei 500% (5x Haltbarkeit) hat jeder Schlag eine **20%-Chance (1 zu 5)** auf 1 Schaden und eine **80%-Chance**, den Schaden zu absorbieren. Da jeder Schlag unabhängig berechnet wird, kann ein Werkzeug nach 2 oder 8 Treffern Schaden nehmen, hält aber über die gesamte Lebensdauer exakt 5-mal so lange (~7.805 Blöcke für eine Diamantspitzhacke).

### Q4: Sollte ich Dezimalzahlen wie 0.5 oder 1.5 in die GameRules eingeben?
**Antwort**: **Nein**. Minecraft-GameRules akzeptieren nur ganze Zahlen (`int`). Gib immer ganze Prozentzahlen ein:
* `50` für 50% (halbe Haltbarkeit / 2x Abnutzung)
* `100` für 100% (1x Vanilla-Standard)
* `150` für 150% (1.5x Haltbarkeit)
* `200` für 200% (2x doppelte Haltbarkeit)
* `-1` für Einmaligen Gebrauch (Glas-Modus / bricht nach 1 Schlag)

### Q5: Funktioniert Durability Multiplier mit Haltbarkeits-Verzauberungen (Unbreaking)?
**Antwort**: Ja! Durability Multiplier skaliert den Schaden **vor** der Vanilla-Verzauberungsberechnung. Eine Spitzhacke mit Haltbarkeit III bei 200% (2x) hält ca. $4 \times 2 = 8\times$ länger als eine unverzauberte Vanilla-Spitzhacke.

### Q6: Wie aktiviere ich den 1-Schlag-Glas-Modus (Einmalgebrauch) für einen Gegenstand?
**Antwort**: Du hast zwei Möglichkeiten:
1. Setze die Einmal-GameRule auf true: `/gamerule ig:dm_single_use_swords true` (oder `/gamerule ig:single_use_<mod>_<item> true`).
2. Nutze den **Power-User-Signalwert `-1`**: Setze die Prozentregel auf `-1`, z. B. `/gamerule ig:dm_percent_swords -1` oder `/gamerule ig:percent_<mod>_<item> -1`.

---

## 🔍 Vertiefung: Randfälle & Lebenszyklus-Verhalten

### Randfall 1: Mod-Deinstallation & Gegenstandslöschung
Wenn ein Spieler eine Mod entfernt, die Gegenstände in Durability Multiplier registriert hatte:
1. **Sicherheit der Konfigurationsdatei**: Die entfernten IDs bleiben sicher in `forcedItems` und `forcedPercentages` gespeichert.
2. **Inaktiver Weltstatus**: Dynamische GameRules in `level.dat` verbleiben vollkommen inaktiv im Speicher.
3. **Keine Abstürze oder Beschädigungen**: Da die Suche über `BuiltInRegistries.ITEM.getKey(stack.getItem())` läuft, wird niemals nach fehlenden Klassen gesucht. Abstürze wie `NullPointerException` sind unmöglich.
4. **Automatische Wiederherstellung**: Wird die Mod erneut installiert, werden alle früheren Einstellungen **sofort wieder verknüpft**, ohne Neukonfiguration!
5. **Manuelle Konfigurationsbereinigung (Optional)**: Wenn du gelöschte Mod-Einträge entfernen möchtest:
   * Open `config/durability-multiplier.json` in a text editor.
   * Remove the deleted item ID string from the `"forcedItems"` list.
   * Remove the corresponding keys from `"forcedPercentages"`, `"forcedInfinities"`, and `"forcedSingleUses"`.
   * Save the file.

---

### Randfall 2: Strikte Haltbarkeits-Filterung (`MAX_DAMAGE > 0`)
Warum erscheinen Möbel-Mods (z. B. Stühle von Macaw's Furniture), Blöcke, Essen oder Materialien nicht in GameRules oder `durability-multiplier.json`?
* Durability Multiplier prüft strikt `DataComponents.MAX_DAMAGE > 0` vor jeder Gegenstandsregistrierung.
* Gegenstände ohne Haltbarkeit (Blöcke, Nahrung, Barren, Samen) werden beim Start in $0.0001\mu\text{s}$ verworfen.
* Dies verhindert Namespace-Überfüllung und hält die Tab-Vervollständigung schnell und sauber.

---

### Randfall 3: Vollständige Auswertungs- & Rangfolgehierarchie
Erleidet ein Gegenstand Haltbarkeitsschaden, wird das Ergebnis anhand der folgenden strikten Hierarchie bestimmt:

$$\text{God Mode (Infinity)} \longrightarrow \text{Single-Use (Glass Mode)} \longrightarrow \text{Per-Item Override} \longrightarrow \text{Subcategory} \longrightarrow \text{Parent Category} \longrightarrow \text{Global} \longrightarrow \text{100\% Vanilla}$$

1. **Prüfung auf unzerstörbaren Gott-Modus**:
   * Per-Item God Mode (`ig:infinity_<mod>_<item>` / `forcedInfinities`)
   * Subcategory God Mode (`ig:dm_infinity_pickaxes`, `ig:dm_infinity_swords`, etc.)
   * Parent Category God Mode (`ig:dm_infinity_tools`, `ig:dm_infinity_weapons`, `ig:dm_infinity_armor`)
   * Global God Mode (`ig:dm_infinity_global`)
   * *If any is `true` $\rightarrow$ Damage = $0$ (Unbreakable).*
2. **Prüfung auf einmaligen Gebrauch (Glas-Modus)**:
   * Effective percentage $\le -1$ (`-1` Glass Mode Sentinel)
   * Per-Item Single-Use (`ig:single_use_<mod>_<item>` / `forcedSingleUses`)
   * Subcategory Single-Use (`ig:dm_single_use_pickaxes`, `ig:dm_single_use_swords`, etc.)
   * Parent Category Single-Use (`ig:dm_single_use_tools`, `ig:dm_single_use_weapons`, `ig:dm_single_use_armor`)
   * Global Single-Use (`ig:dm_single_use_global`)
   * *If active $\rightarrow$ Item loses all remaining durability in 1 hit.*
3. **Prozentuale Skalierungsauflösung**:
   * Per-Item Override (`ig:percent_<mod>_<item>` / `forcedPercentages`) if $\neq 0$
   * Subcategory Percentage (`ig:dm_percent_pickaxes`, `ig:dm_percent_swords`, etc.) if $\neq 0$
   * Parent Category Percentage (`ig:dm_percent_tools`, `ig:dm_percent_weapons`, `ig:dm_percent_armor`) if $\neq 0$
   * Global Percentage (`ig:dm_percent_global`) if $\neq 0$
   * Vanilla Fallback: $100\%$ baseline.

