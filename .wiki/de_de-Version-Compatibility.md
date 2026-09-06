# Versionskompatibilität & Lebenszyklus-Matrix

| Spezifikation | Minecraft 26.2 Anker | Minecraft 26.1.2 Anker |
| :--- | :--- | :--- |
| **Ziel-Minecraft-Version** | `26.2` (`>=26.2-`) | `26.1.2` (`*`) |
| **Mod-Version (SemVer)** | `1.2.14+26.2` | `1.1.21+26.1.2` |
| **Java-Toolchain** | Java 25 (`release = 25`) | Java 25 (`release = 25`) |
| **Fabric Loader** | `>=0.16.9` (Erstellt auf `0.19.1`) | `>=0.16.9` (Erstellt auf `0.19.1`) |
| **Fabric API** | `0.150.1+26.2` | `0.145.4+26.1.2` |
| **Fabric Loom** | `1.15.2` | `1.15.2` |
| **DasikLibrary-Abhängigkeit** | `1.8.28` | `1.8.28` |
| **Cloth Config-Abhängigkeit** | `26.1.154` (Optional) | `26.1.154` (Optional) |
| **ModMenu-Abhängigkeit** | `18.0.0-beta.1` (Optional) | `18.0.0-beta.1` (Optional) |
| **ModVersionGuard-Schutz** | ✅ Aktiv (`EntityTypes` Klassenprüfung) | Standard-Laufzeit |
| **Dedizierter Wiki-Hub** | [[👉 MC 26.2 Hub öffnen\|de_de-26.2-Home]] | [[👉 MC 26.1.2 Hub öffnen\|de_de-26.1.2-Home]] |

---

## 🏛️ Ära-Architektur & "1 Jar 1 Version"-Gesetz

Durability Multiplier folgt dem **1 Jar 1 Version**-Design-Mandat:
1. Jede Hauptversion von Minecraft besitzt ein eigenes Unterprojektverzeichnis (`Durability Multiplier v26.1/`, `Durability Multiplier v26.2/`).
2. Release-Artefakte werden unabhängig kompiliert (`durability-multiplier-1.1.21+26.1.2.jar`, `durability-multiplier-1.2.14+26.2.jar`) und zentral archiviert.
3. MC 26.2 enthält den abhängigkeitsfreien `ModVersionGuard` in `onInitialize()`, um die Ausführung bei inkompatiblen Versionen sicher zu stoppen und Welten vor Beschädigung zu schützen.

---

> 📌 **Quellcode-Hinweis**: Die Dokumentation in diesem Wiki gibt den **aktuellen Quellcode-Stand im Repository** wieder, welcher neuere, noch unveröffentlichte Entwicklungsstände enthalten kann.
