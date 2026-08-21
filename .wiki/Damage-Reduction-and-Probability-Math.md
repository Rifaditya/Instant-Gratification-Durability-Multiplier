# Damage Reduction & Probability Math (26.2)

| Mathematical Property | Value |
| :--- | :--- |
| **Base Integer Division** | $\lfloor \text{amount} / \text{multiplier} \rfloor$ |
| **Remainder** | $\text{amount} \pmod{\text{multiplier}}$ |
| **Random Evaluation** | `level.getRandom().nextInt(multiplier) < remainder` |
| **Integer Overflow Cap** | `Integer.MAX_VALUE` ($2,147,483,647$) |
| **Zero-Damage Floor** | Guaranteed $0$ on Infinity or successful probabilistic roll |

---

## 📐 The Exact Damage Reduction Algorithm

In Minecraft, most tool uses deal **1 point of damage** to the item (e.g. mining a stone block with a pickaxe, hitting a mob with a sword).

Under standard integer division:
$$\frac{1 \text{ damage}}{2\times \text{ multiplier}} = 0.5 \xrightarrow{\text{integer truncation}} 0 \text{ damage}$$

If integer truncation were applied directly, any multiplier $\ge 2$ would truncate 1 damage to 0 damage, making **all tools accidentally unbreakable**!

To achieve mathematically exact durability extension, Durability Multiplier implements **Probabilistic Rounding** in `DurabilityHelper.reduceDamage(int, ServerLevel, ItemStack)`:

```java
int base = originalAmount / multiplier;
int remainder = originalAmount % multiplier;
if (remainder > 0 && level.getRandom().nextInt(multiplier) < remainder) {
    base++;
}
return base;
```

---

## 🎲 Probability Distribution Matrix

### For a 1-Damage Event (`originalAmount = 1`)

| Multiplier | Base (`1 / M`) | Remainder (`1 % M`) | Probability of Taking 1 Damage | Probability of Taking 0 Damage | Expected Uses per Point |
| :-: | :-: | :-: | :-: | :-: | :-: |
| **1x** | `1` | `0` | $100\%$ | $0\%$ | $1.00\times$ |
| **2x** | `0` | `1` | $\frac{1}{2} = 50.00\%$ | $\frac{1}{2} = 50.00\%$ | $2.00\times$ |
| **3x** | `0` | `1` | $\frac{1}{3} \approx 33.33\%$ | $\frac{2}{3} \approx 66.67\%$ | $3.00\times$ |
| **4x** | `0` | `1` | $\frac{1}{4} = 25.00\%$ | $\frac{3}{4} = 75.00\%$ | $4.00\times$ |
| **5x** | `0` | `1` | $\frac{1}{5} = 20.00\%$ | $\frac{4}{5} = 80.00\%$ | $5.00\times$ |
| **10x** | `0` | `1` | $\frac{1}{10} = 10.00\%$ | $\frac{9}{10} = 90.00\%$ | $10.00\times$ |
| **100x** | `0` | `1` | $\frac{1}{100} = 1.00\%$ | $\frac{99}{100} = 99.00\%$ | $100.00\times$ |

### For Multi-Damage Events (e.g. Shield block absorbing 5 damage under 3x multiplier)
* $\text{Base} = 5 / 3 = 1$
* $\text{Remainder} = 5 \% 3 = 2$
* $\text{Chance of taking 2 damage} = \frac{2}{3} \approx 66.67\%$
* $\text{Chance of taking 1 damage} = \frac{1}{3} \approx 33.33\%$
* **Expected damage**: $1 \times \frac{1}{3} + 2 \times \frac{2}{3} = \frac{5}{3} = 1.667$ damage (exactly $\frac{1}{3}$ of 5).

---

## 📈 Long-Term Durability Convergence

Let $N$ be the vanilla durability of an item, and $M$ be the active multiplier. The number of uses $U$ until the item breaks follows a negative binomial distribution with mean:

$$\mathbb{E}[U] = N \times M$$
$$\sigma^2(U) = N \times M \times (M - 1)$$

Over thousands of uses, the law of large numbers guarantees that total durability converges to **exactly $M$ times vanilla durability**.
