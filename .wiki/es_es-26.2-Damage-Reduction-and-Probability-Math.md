# Reducción de daño y matemáticas de probabilidad (26.2)

| Propiedad matemática | Valor |
| :--- | :--- |
| **Unidades de daño totales** | $\text{amount} \times 100$ |
| **División entera base** | $\lfloor (\text{amount} \times 100) / \text{percent} \rfloor$ |
| **Residuo** | $(\text{amount} \times 100) \pmod{\text{percent}}$ |
| **Evaluación aleatoria** | `random.nextInt(percent) < remainder` |
| **Límites enteros** | $\ge 0$ (Global predeterminado: 200, Anulaciones: 0 = heredar) |
| **Suelo de daño cero** | $0$ garantizado en Infinito o tirada exitosa de absorción probabilística |

---

## 🛡️ ¿Por qué intercepción de daño (100% seguridad para partidas)?

Durability Multiplier is engineered to **never mutate item NBT or `DataComponents`** stored in world save files. 

### ¿Por qué no modificar directamente `DataComponents.MAX_DAMAGE`?
1. **Cero contaminación del mundo**: Modificar la durabilidad máxima fija valores alterados en inventarios, cofres y partidas guardadas. Si el jugador desinstala el mod o cambia las GameRules, los objetos quedarían permanentemente alterados.
2. **Recarga en caliente instantánea**: Cambiar `/gamerule ig:dm_percent_global` surte efecto de inmediato en cada objeto del mundo sin necesidad de escanear inventarios ni recrear objetos.
3. **Equilibrio con Reparación y Yunques**: Los costos de yunque y la absorción de experiencia con Reparación se calculan según la durabilidad estándar sin desbordamientos de enteros ni penalizaciones.

En su lugar, el mod intercepta dinámicamente los eventos de daño durante la ejecución mediante `ItemStackDurabilityMixin` y aplica un **Escalado de daño probabilístico** (la misma arquitectura utilizada por el encantamiento **Irrompibilidad** de Minecraft).

---

## 📐 El algoritmo de escalado exacto

Cuando se usa un objeto (infligiendo daño `originalAmount`, normalmente 1 al romper bloques o golpear):

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

## 🎲 Matriz de distribución de probabilidad

### Para un evento de 1 daño (`originalAmount = 1`)

| Porcentaje | Multiplicador efectivo | Base (`100 / P`) | Residuo (`100 % P`) | Probabilidad de daño por uso | Daño esperado por uso | Durabilidad relativa |
| :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| **25%** | $0.25\times$ | `4` | `0` | $100\%$ (4 de daño) | $4.00$ | $0.25\times$ (desgaste 4x) |
| **50%** | $0.50\times$ | `2` | `0` | $100\%$ (2 de daño) | $2.00$ | $0.50\times$ (desgaste 2x) |
| **75%** | $0.75\times$ | `1` | `25` | $100\%$ (1) + $33.3\%$ (+1) | $1.33$ | $0.75\times$ (desgaste 1.33x) |
| **100%** | $1.00\times$ (Vanilla) | `1` | `0` | $100\%$ (1 de daño) | $1.00$ | $1.00\times$ (Estándar) |
| **150%** | $1.50\times$ | `0` | `100` | $\frac{100}{150} \approx 66.67\%$ (1 de daño) | $0.67$ | $1.50\times$ |
| **200%** | $2.00\times$ | `0` | `100` | $\frac{100}{200} = 50.00\%$ (1 de daño) | $0.50$ | $2.00\times$ |
| **300%** | $3.00\times$ | `0` | `100` | $\frac{100}{300} \approx 33.33\%$ (1 de daño) | $0.33$ | $3.00\times$ |
| **500%** | $5.00\times$ | `0` | `100` | $\frac{100}{500} = 20.00\%$ (1 de daño) | $0.20$ | $5.00\times$ |
| **1000%** | $10.00\times$ | `0` | `100` | $\frac{100}{1000} = 10.00\%$ (1 de daño) | $0.10$ | $10.00\times$ |

---

## 📈 Tiradas aleatorias independientes y ley de los grandes números

Debido a que la absorción de daño se evalúa de manera independiente en cada golpe (igual que la Irrompibilidad de Vanilla):
* Al **500% (5x)**, cada golpe calcula de forma independiente un $20\%$ de probabilidad de recibir 1 daño y un $80\%$ de recibir 0.
* En pruebas cortas, una herramienta podría recibir 1 de daño tras 2 golpes o tras 8 golpes.
* A lo largo de la vida completa de la herramienta (ej. 1,561 usos en un hacha de diamante), la cantidad total de bloques rotos converge matemáticamente a **$\approx 7,805$ usos** (exactamente $5\times$).

Sea $N$ la durabilidad vanilla de un objeto y $P$ el porcentaje activo ($P \ge 100$). La cantidad de usos $U$ hasta que el objeto se rompe sigue una distribución binomial negativa con media:

$$\mathbb{E}[U] = N \times \frac{P}{100}$$

A lo largo de miles de usos, la ley de los grandes números garantiza que la durabilidad total converja a **exactamente $\frac{P}{100}$ veces la durabilidad vanilla**.
