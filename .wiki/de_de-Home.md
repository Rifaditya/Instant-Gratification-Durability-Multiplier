# Durability Multiplier Wiki

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

Willkommen in der offiziellen technischen und spielerischen Dokumentation für **Durability Multiplier** (Instant Gratification Collection), entwickelt von **Dasik (Rifaditya)**.

> 📌 **Quellcode-Hinweis**: Die Dokumentation in diesem Wiki gibt den **aktuellen Quellcode-Stand im Repository** wieder, welcher neuere, noch unveröffentlichte Entwicklungsstände enthalten kann.

---

## 🧭 Multi-Versions-Schaltportal

Durability Multiplier ist für dedizierte Minecraft-Versionen optimiert. Wähle unten deine Minecraft-Version aus, um zum jeweiligen Dokumentationsbereich zu gelangen:

| Minecraft-Version | Veröffentlichungs-Ära | Unterstützter Build | Java-Stufe | Loom-Toolchain | Wiki-Eintrag |
| :--- | :--- | :--- | :---: | :---: | :--- |
| **Minecraft 26.2** | Modern Sovereign-Ära | `1.2.14+26.2` | Java 25 | Loom 1.15.2 | [[👉 Zur MC 26.2 Wiki\|de_de-26.2-Home]] |
| **Minecraft 26.1.2** | Modern Sovereign-Ära | `1.1.21+26.1.2` | Java 25 | Loom 1.15.2 | [[👉 Zur MC 26.1.2 Wiki\|de_de-26.1.2-Home]] |

---

## ⚡ Kernphilosophie & Architektur

Durability Multiplier gehört zur **Instant Gratification (IG)**-Designlinie. Ihr einziges Ziel ist es, **nervige Wartungsarbeiten** im Minecraft-Überlebensmodus zu eliminieren:

* **Respektiert die Zeit des Spielers**: Eliminiert zeitraubende Reparaturschleifen, Unterbrechungen beim Abbau und unbeabsichtigten Ausrüstungsbruch.
* **Rein mathematische Schadensreduktion**: Haltbarkeitsverlängerung wird über ganzzahlige Division und Rundung berechnet, was mathematische Präzision garantiert, ohne Vanilla-Attribute zu überschreiben.
* **Granulare Kontrolle**: Konfiguriere 24 einzelne Gegenstandskategorien (Schwerter, Speere, Dreizacke, Streitkolben, Bögen, Armbrüste, Schilde, Werkzeuge, Spitzhacken, Äxte, Schaufeln, Hacken, Scheren, Angelruten, Pinsel, Feuerzeuge, Rüstung, Helme, Brustpanzer, Hosen, Stiefel, Elytren, Waffen, Global) unabhängig über 73 statische GameRules.
* **Gott-Modus (Unendlich)**: Mache beliebige oder alle Kategorien mit einer einzigen booleschen GameRule zu 100% unzerstörbar.
* **Automatische Mod-Gegenstandserkennung**: Erkennt Mod-Gegenstände mit Haltbarkeit beim Einfrieren der Registrierung und stellt GameRules sowie GUI-Optionen bereit.
* **Keine Desynchronisation**: Server-GameRules werden über Fabric-Netzwerk (`durability-multiplier:sync_rules`) synchronisiert für sofortige Tooltips.

---

## 📚 Globale Navigation & Ressourcen

* [[Versions-Kompatibilitätsmatrix|de_de-Version-Compatibility]]
* [[MC 26.2 Dokumentations-Hub|de_de-26.2-Home]]
* [[MC 26.1.2 Dokumentations-Hub|de_de-26.1.2-Home]]
* [CurseForge-Projektseite](https://curseforge.com/minecraft/mc-mods/durability-multiplier)
* [Modrinth-Projektseite](https://modrinth.com/mod/durability-multiplier)
* [GitHub-Quellcode-Repository](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier)
