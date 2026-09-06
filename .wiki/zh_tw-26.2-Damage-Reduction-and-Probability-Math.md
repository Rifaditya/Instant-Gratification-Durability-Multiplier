# 傷害減免與機率數學 (26.2)

| 數学屬性 | 取值 |
| :--- | :--- |
| **總損耗單元** | $\text{amount} \times 100$ |
| **基础整數除法** | $\lfloor (\text{amount} \times 100) / \text{percent} \rfloor$ |
| **餘數計算** | $(\text{amount} \times 100) \pmod{\text{percent}}$ |
| **隨機判定** | `random.nextInt(percent) < remainder` |
| **整數取值界限** | $\ge 0$ (全局默认: 200, 覆蓋規則: 0 = 繼承) |
| **零伤害保底** | 上帝模式或成功概率吸收判定時確保造成 $0$ 點損耗 |

---

## 🛡️ 為什麼採用傷害攔截機制（100% 存檔安全）？

Durability Multiplier is engineered to **never mutate item NBT or `DataComponents`** stored in world save files. 

### 為什麼不直接修改 `DataComponents.MAX_DAMAGE`？
1. **零世界污染**：篡改物品最大耐久值会将修改后的數據永久固化在玩家背封包、箱子和世界存檔中。一旦玩家卸載模組或更改規則，物品可能会永久异常損壞。
2. **即時热重載**：修改 `/gamerule ig:dm_percent_global` 会立即作用于世界中的所有物品，無需遍历背封包或重新创建物品實體。
3. **经驗修补与鐵砧平衡**：原版鐵砧修复消耗与经驗修补吸收依然基于標准耐久度計算，無整數溢出或修复惩罚。

相反，本模組在运行時通过 `ItemStackDurabilityMixin` 動態拦截損耗事件，并应用**概率損伤缩放算法**（与原版**耐久 (Unbreaking)** 附魔完全相同的架構實现）。

---

## 📐 精確縮放演算法

当物品被使用時（承受 `originalAmount` 損耗，常規破壞方块或挥動工具通常為 1）：

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

## 🎲 機率分佈矩陣

### 針對 1 點損耗事件 (`originalAmount = 1`)

| 耐久百分比 | 等效倍率 | 基础損耗 (`100 / P`) | 餘數 (`100 % P`) | 每次命中承受損耗概率 | 每次命中期望損耗 | 相对耐久度 |
| :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| **25%** | $0.25\times$ | `4` | `0` | $100\%$ (4 點損耗) | $4.00$ | $0.25\times$ (4 倍磨損) |
| **50%** | $0.50\times$ | `2` | `0` | $100\%$ (2 點損耗) | $2.00$ | $0.50\times$ (2 倍磨損) |
| **75%** | $0.75\times$ | `1` | `25` | $100\%$ (1) + $33.3\%$ (+1) | $1.33$ | $0.75\times$ (1.33 倍磨損) |
| **100%** | $1.00\times$ (原版) | `1` | `0` | $100\%$ (1 點損耗) | $1.00$ | $1.00\times$ (標准) |
| **150%** | $1.50\times$ | `0` | `100` | $\frac{100}{150} \approx 66.67\%$ (1 點損耗) | $0.67$ | $1.50\times$ |
| **200%** | $2.00\times$ | `0` | `100` | $\frac{100}{200} = 50.00\%$ (1 點損耗) | $0.50$ | $2.00\times$ |
| **300%** | $3.00\times$ | `0` | `100` | $\frac{100}{300} \approx 33.33\%$ (1 點損耗) | $0.33$ | $3.00\times$ |
| **500%** | $5.00\times$ | `0` | `100` | $\frac{100}{500} = 20.00\%$ (1 點損耗) | $0.20$ | $5.00\times$ |
| **1000%** | $10.00\times$ | `0` | `100` | $\frac{100}{1000} = 10.00\%$ (1 點損耗) | $0.10$ | $10.00\times$ |

---

## 📈 獨立隨機判定與大數法則

由于每次命中時均獨立判定損耗吸收（与原版耐久附魔機制完全一致）：
* 在 **500% (5x)** 下，每次命中獨立進行隨機判定，有 $20\%$ 几率承受 1 點損耗，有 $80\%$ 几率承受 0 點損耗。
* 在短期測試中，工具可能会在 2 次或 8 次使用后才消耗 1 點耐久。
* 在工具的整个使用寿命中（例如钻石斧原版 1,561 次使用），總破壞方块數在數学上收斂于 **$\approx 7,805$ 次使用**（恰好 $5\times$）。

設 $N$ 為物品原版耐久度，$P$ 為当前生效百分比 ($P \ge 100$)。直到物品破損的使用次數 $U$ 服從負二項分布，其期望均值為：

$$\mathbb{E}[U] = N \times \frac{P}{100}$$

在成千上万次使用中，大數定律確保總有效耐久度收斂于**恰好為原版耐久度的 $\frac{P}{100}$ 倍**。
