# Réduction des dégâts & Mathématiques probabilistes (26.1.2)

| Propriété mathématique | Valeur |
| :--- | :--- |
| **Unités de dégâts totales** | $\text{amount} \times 100$ |
| **Division entière de base** | $\lfloor (\text{amount} \times 100) / \text{percent} \rfloor$ |
| **Reste** | $(\text{amount} \times 100) \pmod{\text{percent}}$ |
| **Évaluation aléatoire** | `random.nextInt(percent) < remainder` |
| **Limites entières** | $\ge 0$ (Défaut global : 200, Remplacements : 0 = hériter) |
| **Plancher de dégâts zéro** | Garanti $0$ en Infini ou lors d'un tirage d'absorption probabiliste réussi |

---

## 🛡️ Pourquoi l'interception des dégâts (100 % de sécurité pour les sauvegardes) ?

Durability Multiplier is engineered to **never mutate item NBT or `DataComponents`** stored in world save files. 

### Pourquoi ne pas modifier directement `DataComponents.MAX_DAMAGE` ?
1. **Zéro contamination** : Modifier la durabilité maximale figerait des valeurs altérées dans les inventaires et sauvegardes. Si le mod est retiré, les objets resteraient altérés.
2. **Rechargement instantané** : Changer `/gamerule ig:dm_percent_global` s'applique immédiatement à chaque objet du monde sans scan d'inventaire.
3. **Équilibre Enclume & Raccommodage** : Les coûts d'enclume et l'absorption d'XP avec Raccommodage se calculent selon la durabilité vanilla standard, sans pénalité.

Au lieu de cela, le mod intercepte dynamiquement les événements de dégâts via `ItemStackDurabilityMixin` et applique un **Échelonnage probabiliste des dégâts** (la même architecture que l'enchantement **Solidité**).

---

## 📐 L'algorithme de mise à l'échelle exact

Lorsqu'un objet est utilisé (infligeant des dégâts `originalAmount`, typiquement 1 lors du minage ou d'un coup) :

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

## 🎲 Matrice de distribution des probabilités

### Pour un événement de 1 dégât (`originalAmount = 1`)

| Pourcentage | Multiplicateur effectif | Base (`100 / P`) | Reste (`100 % P`) | Chance de dégât par coup | Dégâts moyens par coup | Durabilité relative |
| :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| **25%** | $0.25\times$ | `4` | `0` | $100\%$ (4 dégâts) | $4.00$ | $0.25\times$ (usure 4x) |
| **50%** | $0.50\times$ | `2` | `0` | $100\%$ (2 dégâts) | $2.00$ | $0.50\times$ (usure 2x) |
| **75%** | $0.75\times$ | `1` | `25` | $100\%$ (1) + $33.3\%$ (+1) | $1.33$ | $0.75\times$ (usure 1.33x) |
| **100%** | $1.00\times$ (Vanilla) | `1` | `0` | $100\%$ (1 dégât) | $1.00$ | $1.00\times$ (Standard) |
| **150%** | $1.50\times$ | `0` | `100` | $\frac{100}{150} \approx 66.67\%$ (1 dégât) | $0.67$ | $1.50\times$ |
| **200%** | $2.00\times$ | `0` | `100` | $\frac{100}{200} = 50.00\%$ (1 dégât) | $0.50$ | $2.00\times$ |
| **300%** | $3.00\times$ | `0` | `100` | $\frac{100}{300} \approx 33.33\%$ (1 dégât) | $0.33$ | $3.00\times$ |
| **500%** | $5.00\times$ | `0` | `100` | $\frac{100}{500} = 20.00\%$ (1 dégât) | $0.20$ | $5.00\times$ |
| **1000%** | $10.00\times$ | `0` | `100` | $\frac{100}{1000} = 10.00\%$ (1 dégât) | $0.10$ | $10.00\times$ |

---

## 📈 Tirages aléatoires indépendants et loi des grands nombres

Comme l'absorption des dégâts est évaluée de façon indépendante à chaque coup (exactement comme Solidité en Vanilla) :
* À **500% (5x)**, chaque coup a une chance indépendante de $20\%$ de subir 1 dégât et de $80\%$ d'en subir 0.
* Sur de courts essais, un outil peut subir 1 dégât après 2 ou 8 coups.
* Sur toute la durée de vie (ex. 1 561 utilisations d'une hache en diamant), les blocs cassés convergent mathématiquement vers **$\approx 7 805$ utilisations** (exactement $5\times$).

Soit $N$ la durabilité vanilla et $P$ le pourcentage actif ($P \ge 100$). Le nombre d'utilisations $U$ jusqu'au bris suit une loi binomiale négative d'espérance :

$$\mathbb{E}[U] = N \times \frac{P}{100}$$

Sur des milliers d'utilisations, la loi des grands nombres garantit que la durabilité totale converge vers **exactement $\frac{P}{100}$ fois la durabilité vanilla**.
