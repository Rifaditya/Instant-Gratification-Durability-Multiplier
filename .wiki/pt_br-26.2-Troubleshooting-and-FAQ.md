# Solução de problemas e perguntas frequentes (26.2)

| Tópico do Sistema | Resumo |
| :--- | :--- |
| **Comportamento de Precedência** | GameRules têm precedência em mundos ativos; config define padrões para novos mundos |
| **Motor de Cálculo** | Interceptação probabilística (zero mutação de NBT, zero dessincronização) |
| **Tolerância a Casos Limite** | 100% livre de falhas na remoção de mods, desvinculação de registros e componentes ausentes |

---

## ❓ Perguntas frequentes (FAQ)

### Q1: Por que as alterações de configuração no ModMenu não afetam meu mundo solo ativo?
**Resposta**: Conforme a **Lei da Precedência**, mudanças feitas em `durability-multiplier.json` ou na GUI do ModMenu definem valores padrão **APENAS PARA NOVOS MUNDOS**. Para alterar em seu mundo atual, use o comando `/gamerule` no jogo (ex. `/gamerule ig:dm_percent_tools 500`) ou a tela de edição de GameRules.

### Q2: Por que a dica de um item não mostra o texto de porcentagem ou multiplicador?
**Resposta**:
1. Verifique se o item possui barra de durabilidade (`DataComponents.MAX_DAMAGE > 0`).
2. Verifique se `ig:dm_show_tooltip` está definido como `true`.
3. Se o valor ativo for `100` (100% da durabilidade vanilla), nenhuma linha extra é renderizada para manter as dicas limpas.

### Q3: Por que minha ferramenta de 500% (5x) sofreu dano de durabilidade após apenas 2 usos?
**Resposta**: Durability Multiplier usa **interceptação probabilística de dano** (o mesmo mecanismo usado pelo encantamento *Inquebrável* do vanilla) para garantir **100% de segurança do mundo**. A 500% (5x durabilidade), cada bloco quebrado tem **20% de chance (1 em 5)** de causar 1 dano e **80% de chance** de absorvê-lo. Como cada golpe calcula de forma independente, você pode receber dano após 2 ou 8 golpes, mas ao longo da vida útil durará 5x mais (~7.805 blocos para picareta de diamante).

### Q4: Devo inserir números decimais como 0.5 ou 1.5 nas GameRules?
**Resposta**: **Não**. As GameRules aceitam apenas números inteiros (`int`). Sempre insira números inteiros de porcentagem:
* `50` para 50% (metade da durabilidade / desgaste 2x)
* `100` para 100% (padrão vanilla 1x)
* `150` para 150% (aumento de 1.5x de durabilidade)
* `200` para 200% (durabilidade dobrada 2x)
* `-1` para Uso Único (Modo Vidro / quebra em 1 golpe)

### Q5: O Durability Multiplier funciona com encantamentos de Inquebrável (Unbreaking)?
**Resposta**: Sim! Durability Multiplier calcula o dano **antes** do processamento de encantamentos do vanilla. Uma picareta com Inquebrável III configurada para 200% (2x) durará cerca de $4 \times 2 = 8\times$ mais que uma picareta vanilla sem encantamentos.

### Q6: Como ativo o Modo Vidro de 1 uso (uso único) para um item?
**Resposta**: Você pode:
1. Definir a GameRule de uso único como true: `/gamerule ig:dm_single_use_swords true` (ou `/gamerule ig:single_use_<mod>_<item> true`).
2. Usar o **Sentinela `-1`**: defina a regra de porcentagem para `-1`, ex. `/gamerule ig:dm_percent_swords -1` ou `/gamerule ig:percent_<mod>_<item> -1`.

---

## 🔍 Casos extremos detalhados e comportamento do ciclo de vida

### Caso extremo 1: Desinstalação de mod e exclusão de itens
Quando um jogador exclui ou remove um mod que tinha itens registrados no Durability Multiplier:
1. **Segurança do Arquivo de Configuração**: Os IDs dos itens removidos permanecem registrados com segurança em `forcedItems` e `forcedPercentages` em `config/durability-multiplier.json`.
2. **Estado Inativo no Mundo**: Qualquer GameRule dinâmica (`ig:percent_<mod>_<item>`, `ig:infinity_<mod>_<item>`) no `level.dat` permanece inativa na memória.
3. **Zero Falhas e Zero Corrupção**: Como a consulta é feita por `BuiltInRegistries.ITEM.getKey(stack.getItem())`, o jogo nunca tenta consultar classes ausentes. Falhas como `NullPointerException` ou corrupção de chunks são impossíveis.
4. **Restauração Automática**: Se o mod for reinstalado, todas as configurações de porcentagem, Modo Deus e Uso Único serão **revinculadas instantaneamente**!
5. **Limpeza Manual da Configuração (Opcional)**: Se desejar limpar entradas de mods excluídos:
   * Open `config/durability-multiplier.json` in a text editor.
   * Remove the deleted item ID string from the `"forcedItems"` list.
   * Remove the corresponding keys from `"forcedPercentages"`, `"forcedInfinities"`, and `"forcedSingleUses"`.
   * Save the file.

---

### Caso extremo 2: Filtragem estrita de durabilidade (`MAX_DAMAGE > 0`)
Por que móveis de mods (ex. armários/cadeiras do Macaw's Furniture), blocos, comida ou materiais não aparecem nas GameRules nem no `durability-multiplier.json`?
* Durability Multiplier verifica estritamente `DataComponents.MAX_DAMAGE > 0` antes de registrar qualquer item.
* Itens sem durabilidade (blocos, comida, lingotes, sementes) são descartados em $0.0001\mu\text{s}$ na inicialização.
* Isso evita poluição de namespace e mantém o autocompletar de GameRules limpo e ágil.

---

### Caso extremo 3: Hierarquia completa de avaliação e precedência
Quando um item recebe dano de durabilidade, o resultado é determinado pela seguinte hierarquia estrita:

$$\text{God Mode (Infinity)} \longrightarrow \text{Single-Use (Glass Mode)} \longrightarrow \text{Per-Item Override} \longrightarrow \text{Subcategory} \longrightarrow \text{Parent Category} \longrightarrow \text{Global} \longrightarrow \text{100\% Vanilla}$$

1. **Verificação de Modo Deus Inquebrável**:
   * Per-Item God Mode (`ig:infinity_<mod>_<item>` / `forcedInfinities`)
   * Subcategory God Mode (`ig:dm_infinity_pickaxes`, `ig:dm_infinity_swords`, etc.)
   * Parent Category God Mode (`ig:dm_infinity_tools`, `ig:dm_infinity_weapons`, `ig:dm_infinity_armor`)
   * Global God Mode (`ig:dm_infinity_global`)
   * *If any is `true` $\rightarrow$ Damage = $0$ (Unbreakable).*
2. **Verificação de Uso Único (Modo Vidro)**:
   * Effective percentage $\le -1$ (`-1` Glass Mode Sentinel)
   * Per-Item Single-Use (`ig:single_use_<mod>_<item>` / `forcedSingleUses`)
   * Subcategory Single-Use (`ig:dm_single_use_pickaxes`, `ig:dm_single_use_swords`, etc.)
   * Parent Category Single-Use (`ig:dm_single_use_tools`, `ig:dm_single_use_weapons`, `ig:dm_single_use_armor`)
   * Global Single-Use (`ig:dm_single_use_global`)
   * *If active $\rightarrow$ Item loses all remaining durability in 1 hit.*
3. **Resolução de Escala de Porcentagem**:
   * Per-Item Override (`ig:percent_<mod>_<item>` / `forcedPercentages`) if $\neq 0$
   * Subcategory Percentage (`ig:dm_percent_pickaxes`, `ig:dm_percent_swords`, etc.) if $\neq 0$
   * Parent Category Percentage (`ig:dm_percent_tools`, `ig:dm_percent_weapons`, `ig:dm_percent_armor`) if $\neq 0$
   * Global Percentage (`ig:dm_percent_global`) if $\neq 0$
   * Vanilla Fallback: $100\%$ baseline.

