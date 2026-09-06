# 伤害减免与概率数学 (26.2)

| 数学属性 | 取值 |
| :--- | :--- |
| **总损耗单元** | $\text{amount} \times 100$ |
| **基础整数除法** | $\lfloor (\text{amount} \times 100) / \text{percent} \rfloor$ |
| **余数计算** | $(\text{amount} \times 100) \pmod{\text{percent}}$ |
| **随机判定** | `random.nextInt(percent) < remainder` |
| **整数取值界限** | $\ge 0$ (全局默认: 200, 覆盖规则: 0 = 继承) |
| **零伤害保底** | 上帝模式或成功概率吸收判定时确保造成 $0$ 点损耗 |

---

## 🛡️ 为什么采用伤害拦截机制（100% 存档安全）？

Durability Multiplier is engineered to **never mutate item NBT or `DataComponents`** stored in world save files. 

### 为什么不直接修改 `DataComponents.MAX_DAMAGE`？
1. **零世界污染**：篡改物品最大耐久值会将修改后的数据永久固化在玩家背包、箱子和世界存档中。一旦玩家卸载模组或更改规则，物品可能会永久异常损坏。
2. **即时热重载**：修改 `/gamerule ig:dm_percent_global` 会立即作用于世界中的所有物品，无需遍历背包或重新创建物品实体。
3. **经验修补与铁砧平衡**：原版铁砧修复消耗与经验修补吸收依然基于标准耐久度计算，无整数溢出或修复惩罚。

相反，本模组在运行时通过 `ItemStackDurabilityMixin` 动态拦截损耗事件，并应用**概率损伤缩放算法**（与原版**耐久 (Unbreaking)** 附魔完全相同的架构实现）。

---

## 📐 精确缩放算法

当物品被使用时（承受 `originalAmount` 损耗，常规破坏方块或挥动工具通常为 1）：

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

## 🎲 概率分布矩阵

### 针对 1 点损耗事件 (`originalAmount = 1`)

| 耐久百分比 | 等效倍率 | 基础损耗 (`100 / P`) | 余数 (`100 % P`) | 每次命中承受损耗概率 | 每次命中期望损耗 | 相对耐久度 |
| :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| **25%** | $0.25\times$ | `4` | `0` | $100\%$ (4 点损耗) | $4.00$ | $0.25\times$ (4 倍磨损) |
| **50%** | $0.50\times$ | `2` | `0` | $100\%$ (2 点损耗) | $2.00$ | $0.50\times$ (2 倍磨损) |
| **75%** | $0.75\times$ | `1` | `25` | $100\%$ (1) + $33.3\%$ (+1) | $1.33$ | $0.75\times$ (1.33 倍磨损) |
| **100%** | $1.00\times$ (原版) | `1` | `0` | $100\%$ (1 点损耗) | $1.00$ | $1.00\times$ (标准) |
| **150%** | $1.50\times$ | `0` | `100` | $\frac{100}{150} \approx 66.67\%$ (1 点损耗) | $0.67$ | $1.50\times$ |
| **200%** | $2.00\times$ | `0` | `100` | $\frac{100}{200} = 50.00\%$ (1 点损耗) | $0.50$ | $2.00\times$ |
| **300%** | $3.00\times$ | `0` | `100` | $\frac{100}{300} \approx 33.33\%$ (1 点损耗) | $0.33$ | $3.00\times$ |
| **500%** | $5.00\times$ | `0` | `100` | $\frac{100}{500} = 20.00\%$ (1 点损耗) | $0.20$ | $5.00\times$ |
| **1000%** | $10.00\times$ | `0` | `100` | $\frac{100}{1000} = 10.00\%$ (1 点损耗) | $0.10$ | $10.00\times$ |

---

## 📈 独立随机判定与大数定律

由于每次命中时均独立判定损耗吸收（与原版耐久附魔机制完全一致）：
* 在 **500% (5x)** 下，每次命中独立进行随机判定，有 $20\%$ 几率承受 1 点损耗，有 $80\%$ 几率承受 0 点损耗。
* 在短期测试中，工具可能会在 2 次或 8 次使用后才消耗 1 点耐久。
* 在工具的整个使用寿命中（例如钻石斧原版 1,561 次使用），总破坏方块数在数学上收敛于 **$\approx 7,805$ 次使用**（恰好 $5\times$）。

设 $N$ 为物品原版耐久度，$P$ 为当前生效百分比 ($P \ge 100$)。直到物品破损的使用次数 $U$ 服从负二项分布，其期望均值为：

$$\mathbb{E}[U] = N \times \frac{P}{100}$$

在成千上万次使用中，大数定律确保总有效耐久度收敛于**恰好为原版耐久度的 $\frac{P}{100}$ 倍**。
