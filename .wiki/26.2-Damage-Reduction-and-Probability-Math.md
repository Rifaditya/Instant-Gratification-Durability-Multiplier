# Damage Reduction & Probability Math (26.2)

| Mathematical Property | Value |
| :--- | :--- |
| **Total Damage Units** | $\text{amount} \times 100$ |
| **Base Integer Division** | $\lfloor (\text{amount} \times 100) / \text{percent} \rfloor$ |
| **Remainder** | $(\text{amount} \times 100) \pmod{\text{percent}}$ |
| **Random Evaluation** | `random.nextInt(percent) < remainder` |
| **Integer Bounds** | $\ge 0$ (Default global: 200, Overrides: 0 = inherit) |
| **Zero-Damage Floor** | Guaranteed $0$ on Infinity or successful probabilistic absorption roll |

---

## 🛡️ Why Damage Interception (100% World-Save Safety)?

Durability Multiplier is engineered to **never mutate item NBT or `DataComponents`** stored in world save files. 

### Why Not Directly Modify `DataComponents.MAX_DAMAGE`?
1. **Zero World Contamination**: Mutating item max durability permanently bakes modified values into player inventories, chests, and world save files. If the player uninstalls the mod or changes GameRules, items would remain permanently altered or broken.
2. **Instant Hot-Reloading**: Changing `/gamerule ig:dm_percent_global` immediately takes effect on every item in the world without requiring inventory scanning or item recreation.
3. **Mending & Anvil Repair Balance**: Vanilla anvil repair costs and XP Mending absorption calculate based on standard item durability without integer overflow or repair penalties.

Instead, the mod dynamically intercepts damage events at runtime via `ItemStackDurabilityMixin` and applies **Probabilistic Damage Scaling** (the exact same architecture used by Minecraft's native **Unbreaking** enchantment).

---

## 📐 The Exact Scaling Algorithm

When an item is used (dealing `originalAmount` damage, typically 1 for standard block breaks and tool swings):

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

## 🎲 Probability Distribution Matrix

### For a 1-Damage Event (`originalAmount = 1`)

| Percentage | Effective Multiplier | Base (`100 / P`) | Remainder (`100 % P`) | Chance of Damage per Hit | Expected Damage per Hit | Relative Durability |
| :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| **25%** | $0.25\times$ | `4` | `0` | $100\%$ (4 damage) | $4.00$ | $0.25\times$ (4x wear) |
| **50%** | $0.50\times$ | `2` | `0` | $100\%$ (2 damage) | $2.00$ | $0.50\times$ (2x wear) |
| **75%** | $0.75\times$ | `1` | `25` | $100\%$ (1) + $33.3\%$ (+1) | $1.33$ | $0.75\times$ (1.33x wear) |
| **100%** | $1.00\times$ (Vanilla) | `1` | `0` | $100\%$ (1 damage) | $1.00$ | $1.00\times$ (Standard) |
| **150%** | $1.50\times$ | `0` | `100` | $\frac{100}{150} \approx 66.67\%$ (1 damage) | $0.67$ | $1.50\times$ |
| **200%** | $2.00\times$ | `0` | `100` | $\frac{100}{200} = 50.00\%$ (1 damage) | $0.50$ | $2.00\times$ |
| **300%** | $3.00\times$ | `0` | `100` | $\frac{100}{300} \approx 33.33\%$ (1 damage) | $0.33$ | $3.00\times$ |
| **500%** | $5.00\times$ | `0` | `100` | $\frac{100}{500} = 20.00\%$ (1 damage) | $0.20$ | $5.00\times$ |
| **1000%** | $10.00\times$ | `0` | `100` | $\frac{100}{1000} = 10.00\%$ (1 damage) | $0.10$ | $10.00\times$ |

---

## 📈 Independent Random Rolls & Law of Large Numbers

Because damage absorption is evaluated independently on every hit (just like Vanilla Unbreaking):
* At **500% (5x)**, every hit rolls an independent $20\%$ chance of taking 1 damage and $80\%$ chance of taking 0.
* In short trials, a tool might take 1 damage after 2 hits or after 8 hits.
* Over the entire lifespan of the tool (e.g. 1,561 uses on a Diamond Axe), the total block breaks converge mathematically to **$\approx 7,805$ uses** (exactly $5\times$).

Let $N$ be the vanilla durability of an item, and $P$ be the active percentage ($P \ge 100$). The number of uses $U$ until the item breaks follows a negative binomial distribution with mean:

$$\mathbb{E}[U] = N \times \frac{P}{100}$$

Over thousands of uses, the law of large numbers guarantees that total durability converges to **exactly $\frac{P}{100}$ times vanilla durability**.
