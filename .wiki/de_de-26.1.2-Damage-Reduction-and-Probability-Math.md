# Schadensreduzierung & Wahrscheinlichkeitsmathematik (26.1.2)

| Mathematische Eigenschaft | Wert |
| :--- | :--- |
| **Gesamtschadenseinheiten** | $\text{amount} \times 100$ |
| **Ganzzahlige Basis-Division** | $\lfloor (\text{amount} \times 100) / \text{percent} \rfloor$ |
| **Restwert** | $(\text{amount} \times 100) \pmod{\text{percent}}$ |
| **Zufallsauswertung** | `random.nextInt(percent) < remainder` |
| **Ganzzahlgrenzen** | $\ge 0$ (Globaler Standard: 200, Überschreibungen: 0 = erben) |
| **Null-Schadens-Grenze** | Garantiert $0$ bei Unendlich oder erfolgreicher Wahrscheinlichkeitsabsorption |

---

## 🛡️ Warum Schadensabfang (100% Speichersicherheit)?

Durability Multiplier is engineered to **never mutate item NBT or `DataComponents`** stored in world save files. 

### Warum nicht direkt `DataComponents.MAX_DAMAGE` modifizieren?
1. **Keine Welt-Kontamination**: Eine Veränderung der maximalen Haltbarkeit würde Werte fest in Spielerinventare und Speicherdateien brennen. Bei Deinstallation oder Regeländerungen blieben Gegenstände dauerhaft verändert.
2. **Sofortige Echtzeit-Aktualisierung**: Das Ändern von `/gamerule ig:dm_percent_global` wirkt sich sofort auf jeden Gegenstand in der Welt aus, ohne Inventare scannen oder Gegenstände neu erstellen zu müssen.
3. **Balance bei Reparatur & Amboss**: Ambosskosten und EP-Reparatur richten sich nach der normalen Vanilla-Haltbarkeit, ohne Überläufe oder Strafen.

Stattdessen fängt die Mod Schadensereignisse zur Laufzeit über `ItemStackDurabilityMixin` ab und wendet **Probabilistische Schadensskalierung** an (dieselbe Architektur wie die Vanilla-Verzauberung **Haltbarkeit**).

---

## 📐 Der exakte Skalierungsalgorithmus

Wenn ein Gegenstand benutzt wird (verursacht `originalAmount` Schaden, typischerweise 1 beim Blockabbau):

```java
public static int calculateScaledDamage(int originalAmount, int percent, RandomSource random) {
    if (originalAmount <= 0)
        return 0;
    if (percent <= 0 || percent == 100)
        return originalAmount;

    int totalDamageUnits = originalAmount * 100;
    int baseDamage = totalDamageUnits / percent;
    int remainder = totalDamageUnits % percent;
    if (remainder > 0 && random.nextInt(percent) < remainder) {
        baseDamage++;
    }
    return baseDamage;
}
```

---

## 🎲 Wahrscheinlichkeitsverteilungsmatrix

### Für ein 1-Schaden-Ereignis (`originalAmount = 1`)

| Prozentsatz | Effektiver Multiplikator | Basis (`100 / P`) | Rest (`100 % P`) | Schadenschance pro Schlag | Erwarteter Schaden pro Schlag | Relative Haltbarkeit |
| :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| **25%** | $0.25\times$ | `4` | `0` | $100\%$ (4 Schaden) | $4.00$ | $0.25\times$ (4x Abnutzung) |
| **50%** | $0.50\times$ | `2` | `0` | $100\%$ (2 Schaden) | $2.00$ | $0.50\times$ (2x Abnutzung) |
| **75%** | $0.75\times$ | `1` | `25` | $100\%$ (1) + $33.3\%$ (+1) | $1.33$ | $0.75\times$ (1.33x Abnutzung) |
| **100%** | $1.00\times$ (Vanilla) | `1` | `0` | $100\%$ (1 Schaden) | $1.00$ | $1.00\times$ (Standard) |
| **150%** | $1.50\times$ | `0` | `100` | $\frac{100}{150} \approx 66.67\%$ (1 Schaden) | $0.67$ | $1.50\times$ |
| **200%** | $2.00\times$ | `0` | `100` | $\frac{100}{200} = 50.00\%$ (1 Schaden) | $0.50$ | $2.00\times$ |
| **300%** | $3.00\times$ | `0` | `100` | $\frac{100}{300} \approx 33.33\%$ (1 Schaden) | $0.33$ | $3.00\times$ |
| **500%** | $5.00\times$ | `0` | `100` | $\frac{100}{500} = 20.00\%$ (1 Schaden) | $0.20$ | $5.00\times$ |
| **1000%** | $10.00\times$ | `0` | `100` | $\frac{100}{1000} = 10.00\%$ (1 Schaden) | $0.10$ | $10.00\times$ |

---

## 📈 Unabhängige Zufallsprüfungen & Gesetz der großen Zahlen

Da die Schadensabsorption bei jedem Schlag unabhängig ausgewertet wird (wie bei Vanilla-Haltbarkeit):
* Bei **500% (5x)** hat jeder Schlag unabhängig eine $20\%$-Chance auf 1 Schaden und $80\%$ auf 0.
* In kurzen Tests kann ein Werkzeug nach 2 oder erst nach 8 Schlägen 1 Schaden nehmen.
* Über die gesamte Lebensdauer (z. B. 1.561 Nutzungen bei einer Diamantaxt) konvergieren die Gesamtabbrüche mathematisch gegen **$\approx 7.805$ Nutzungen** (exakt $5\times$).

Sei $N$ die Vanilla-Haltbarkeit und $P$ der aktive Prozentsatz ($P \ge 100$). Die Anzahl der Nutzungen $U$ bis zum Bruch folgt einer negativen Binomialverteilung mit dem Erwartungswert:

$$\mathbb{E}[U] = N \times \frac{P}{100}$$

Über Tausende von Nutzungen hinweg garantiert das Gesetz der großen Zahlen, dass die Gesamthaltbarkeit gegen **genau $\frac{P}{100}$ mal die Vanilla-Haltbarkeit** konvergiert.
