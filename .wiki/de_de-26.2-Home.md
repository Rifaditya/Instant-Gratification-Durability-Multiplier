# Durability Multiplier — Minecraft 26.2 Dokumentations-Hub

Willkommen in der dedizierten Dokumentation für **Durability Multiplier** auf **Minecraft 26.2** (`1.2.14+26.2`).

> 📌 **Quellcode-Hinweis**: Die Dokumentation in diesem Wiki gibt den **aktuellen Quellcode-Stand im Repository** wieder, welcher neuere, noch unveröffentlichte Entwicklungsstände enthalten kann.

---

## 📋 Technische Zusammenfassung (26.2)

| Parameter | Wert | Beschreibung |
| :--- | :--- | :--- |
| **Mod-Identifikator** | `durability-multiplier` | Mod-ID im Fabric Loader |
| **Mod-Version** | `1.2.14+26.2` | SemVer-Versions-Tag |
| **Ziel-Minecraft** | `26.2` (`>=26.2-`) | Nativer Versionsanker |
| **Java-Version** | Java 25 | Kompiliert mit `release = 25` |
| **Fabric Loader** | `>=0.16.9` | Mindestanforderung an Loader |
| **Fabric API** | `0.150.1+26.2` | Laufzeitanforderung für Fabric API |
| **DasikLibrary** | `1.8.28` | Gemeinsamer Architekturkern |
| **Registrierte GameRules** | **73 statische Regeln** + dynamische Mod-Regeln | 24 Prozente, 24 Unendlich, 24 Einmal-Gebrauch, 1 Tooltip |
| **Mixin-Injektionspunkte** | 3 Zielklassen | `ItemStack`, `GameRules` |
| **Autor & Lizenz** | **Dasik (Rifaditya)** / GPL-3.0-or-later | Open-Source-Modifikation |

---

## 🧭 Navigationsmatrix (26.2)

### 🎮 Spieler- & Gameplay-Leitfäden
* [[Haltbarkeitsmultiplikatoren & Kategorien|de_de-26.2-Durability-Multipliers]] — Granulares 24-Kategorien-Prozentsystem und Hierarchie.
* [[Gott-Modus & Unendlichkeit|de_de-26.2-God-Mode-and-Infinity]] — Unverwundbarkeit mit 0 Schaden in 24 Kategorien.
* [[Schadensreduktions-Mathematik & Wahrscheinlichkeiten|de_de-26.2-Damage-Reduction-and-Probability-Math]] — Mathematische Formeln und Rundung.
* [[Gegenstandsklassifizierung & Mod-Kompatibilität|de_de-26.2-Item-Classification-and-Mod-Compatibility]] — Klassifizierung von Vanilla- und Mod-Gegenständen.
* [[Dynamische Mod-Gegenstandsregistrierung|de_de-26.2-Dynamic-Modded-Item-Registration]] — Universeller 3-Stufen-Erkennungsscanner.
* [[Tooltip-Indikatoren & HUD|de_de-26.2-Tooltip-Indicators-and-HUD]] — Clientseitige Tooltip-Darstellung.
* [[GameRules-Referenztabelle|de_de-26.2-GameRules]] — Vollständige Übersicht aller 73 statischen GameRules.
* [[Befehle & Verwaltung im Spiel|de_de-26.2-Commands-and-Administration]] — Einstellungen per `/gamerule` verwalten.
* [[Fortschritte & Erfolge|de_de-26.2-Advancements]] — Richtlinie zur Abwesenheit und Vanilla-Integration.
* [[Konfigurations-GUI & Weltstandards|de_de-26.2-Configuration]] — ModMenu- und Cloth Config-Integration.
* [[ModVersionGuard-Laufzeitschutz|de_de-26.2-ModVersionGuard-and-Safety]] — Abhängigkeitsfreier Versionsschutz.
* [[Fehlerbehebung & FAQ|de_de-26.2-Troubleshooting-and-FAQ]] — Diagnose und häufig gestellte Fragen.

### 💻 Entwickler- & technische Referenz
* [[Architektur & Mixin-Deskriptoren|de_de-26.2-Architecture-and-Mixins]] — Pakethierarchie, Injektions-Hooks und Sicherheit.
* [[Netzwerksynchronisierung & Payload-Protokoll|de_de-26.2-Network-Sync-and-Payload-Protocol]] — S2C-Synchronisationsprotokoll (`DurabilityPayload`).
* [[Entwickler-Setup & Kompilierung|de_de-26.2-Developer-Setup-and-Building]] — Gradle-Befehle, Loom-Toolchain und JDK.
* [[API & Addon-Integration|de_de-26.2-API-and-Addon-Integration]] — Erweiterung der Mod, `DurabilityHelper` und Regeln.
