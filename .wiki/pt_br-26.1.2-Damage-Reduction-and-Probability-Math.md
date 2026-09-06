# Redução de dano e matemática de probabilidade (26.1.2)

| Propriedade Matemática | Valor |
| :--- | :--- |
| **Unidades Totais de Dano** | $\text{amount} \times 100$ |
| **Divisão Inteira Base** | $\lfloor (\text{amount} \times 100) / \text{percent} \rfloor$ |
| **Resto** | $(\text{amount} \times 100) \pmod{\text{percent}}$ |
| **Avaliação Aleatória** | `random.nextInt(percent) < remainder` |
| **Limites de Inteiros** | $\ge 0$ (Padrão global: 200, Substituições: 0 = herdar) |
| **Piso de Dano Zero** | $0$ garantido no Infinito ou rolagem bem-sucedida de absorção probabilística |

---

## 🛡️ Por que interceptação de dano (100% de segurança do mundo)?

Durability Multiplier is engineered to **never mutate item NBT or `DataComponents`** stored in world save files. 

### Por que não modificar diretamente `DataComponents.MAX_DAMAGE`?
1. **Zero Contaminação do Mundo**: Modificar a durabilidade máxima grava valores alterados nos inventários, baús e arquivos de salvamento. Se o mod for desinstalado ou as regras forem alteradas, os itens ficariam permanentemente danificados.
2. **Recarga Imediata sem Reiniciar**: Alterar `/gamerule ig:dm_percent_global` tem efeito imediato em todos os itens do mundo, sem escanear inventários ou recriar itens.
3. **Equilíbrio de Reparação e Bigorna**: Custos na bigorna e absorção de XP pelo Remendo calculam com base na durabilidade padrão, sem transbordamento de inteiros ou penalidades.

Em vez disso, o mod intercepta dinamicamente eventos de dano em tempo de execução via `ItemStackDurabilityMixin` e aplica **Escala de Dano Probabilística** (a mesma arquitetura usada pelo encantamento nativo **Inquebrável** do Minecraft).

---

## 📐 O algoritmo exato de escalonamento

Quando um item é usado (causando dano `originalAmount`, normalmente 1 ao quebrar blocos ou golpear):

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

## 🎲 Matriz de distribuição de probabilidade

### Para um evento de 1 dano (`originalAmount = 1`)

| Porcentagem | Multiplicador Efetivo | Base (`100 / P`) | Resto (`100 % P`) | Chance de Dano por Golpe | Dano Esperado por Golpe | Durabilidade Relativa |
| :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| **25%** | $0.25\times$ | `4` | `0` | $100\%$ (4 de dano) | $4.00$ | $0.25\times$ (desgaste 4x) |
| **50%** | $0.50\times$ | `2` | `0` | $100\%$ (2 de dano) | $2.00$ | $0.50\times$ (desgaste 2x) |
| **75%** | $0.75\times$ | `1` | `25` | $100\%$ (1) + $33.3\%$ (+1) | $1.33$ | $0.75\times$ (desgaste 1.33x) |
| **100%** | $1.00\times$ (Vanilla) | `1` | `0` | $100\%$ (1 de dano) | $1.00$ | $1.00\times$ (Padrão) |
| **150%** | $1.50\times$ | `0` | `100` | $\frac{100}{150} \approx 66.67\%$ (1 de dano) | $0.67$ | $1.50\times$ |
| **200%** | $2.00\times$ | `0` | `100` | $\frac{100}{200} = 50.00\%$ (1 de dano) | $0.50$ | $2.00\times$ |
| **300%** | $3.00\times$ | `0` | `100` | $\frac{100}{300} \approx 33.33\%$ (1 de dano) | $0.33$ | $3.00\times$ |
| **500%** | $5.00\times$ | `0` | `100` | $\frac{100}{500} = 20.00\%$ (1 de dano) | $0.20$ | $5.00\times$ |
| **1000%** | $10.00\times$ | `0` | `100` | $\frac{100}{1000} = 10.00\%$ (1 de dano) | $0.10$ | $10.00\times$ |

---

## 📈 Rolagens aleatórias independentes e lei dos grandes números

Como a absorção de dano é avaliada independentemente a cada golpe (assim como no Inquebrável do Vanilla):
* A **500% (5x)**, cada golpe calcula uma chance independente de $20\%$ de receber 1 dano e $80\%$ de receber 0.
* Em testes curtos, uma ferramenta pode receber 1 de dano após 2 golpes ou após 8 golpes.
* Ao longo de toda a vida útil da ferramenta (ex. 1.561 usos em um Machado de Diamante), o total de blocos quebrados converge matematicamente para **$\approx 7.805$ usos** (exatamente $5\times$).

Seja $N$ a durabilidade vanilla de um item e $P$ a porcentagem ativa ($P \ge 100$). O número de usos $U$ até quebrar segue uma distribuição binomial negativa com média:

$$\mathbb{E}[U] = N \times \frac{P}{100}$$

Ao longo de milhares de usos, a lei dos grandes números garante que a durabilidade total convirja para **exatamente $\frac{P}{100}$ vezes a durabilidade do vanilla**.
