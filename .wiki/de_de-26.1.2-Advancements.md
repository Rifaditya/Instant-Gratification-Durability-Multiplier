# Fortschritte & Erfolge (26.1.2)

| Systemparameter | Status |
| :--- | :--- |
| **Eigene Fortschritts-JSONs** | **Keine** (Absichtlich weggelassen) |
| **Umfang** | Reiner Gameplay-Modifikator / Sofortige Belohnung |
| **Vanilla-Fortschritte** | 100% kompatibel mit allen Vanilla-Fortschritten (Geschichte, Landwirtschaft usw.) |

---

## 📌 Erklärung zur Abwesenheitspolitik

In strikter Übereinstimmung mit der **Instant Gratification (IG)**-Philosophie und der Minimal-Footprint-Architektur:
* Durability Multiplier enthält **keine eigenen Fortschritts-JSON-Bäume**.
* Die Mod registriert **keine** benutzerdefinierten Auslöser, Kriterien oder Toast-Benachrichtigungen.

---

## 🌾 Kompatibilität mit Vanilla-Fortschritten

Alle Vanilla-Fortschritte werden weiterhin ganz natürlich ausgelöst:
* Der Abbau von Diamanterz löst **„Diamanten!“** aus (`minecraft:story/mine_diamond`).
* Das Gleiten mit Elytren löst **„Bis zum Himmel“** aus (`minecraft:end/elytra`).
* Das Abwehren von Schaden mit einem Schild löst **„Heute nicht, danke“** aus (`minecraft:story/deflect_arrow`).

Da die Verringerung der Haltbarkeit transparent innerhalb von `ItemStack.hurtAndBreak` erfolgt, werden Fortschritte, die Werkzeugnutzung, Kills oder Rüstungsschaden überwachen, ohne jede Störung ausgewertet.
