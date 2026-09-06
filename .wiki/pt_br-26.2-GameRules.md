# Referência de GameRules (26.2)

Todas as GameRules do Durability Multiplier estão registradas na categoria personalizada **`durability-multiplier:durability_multiplier`** (`"Durability Multiplier"`).

---

## 📊 Tabelas completas de referência de GameRules

### 1. GameRules de porcentagem de durabilidade
Regras de porcentagem controlam o escalonamento de durabilidade dos itens.
* `200` = 200% (durabilidade 2x)
* `100` = 100% (Padrão vanilla 1x)
* `50` = 50% (Metade da durabilidade / desgaste 2x)
* `0` = Herda da categoria pai ou padrão global
* `-1` = Sentinela de **Uso Único (Modo Vidro)** (quebra em 1 golpe)

| # | Identificador de GameRule | Tipo | Padrão | Mín | Descrição e Comportamento |
| :-: | :--- | :---: | :---: | :---: | :--- |
| 1 | `ig:dm_percent_global` | `Integer` | `200` | `-1` | Porcentagem base global para todos os itens com durabilidade. |
| 2 | `ig:dm_percent_weapons` | `Integer` | `0` | `-1` | Substituição global para todas as armas (espadas, lanças, tridentes, maças, arcos, bestas). |
| 3 | `ig:dm_percent_swords` | `Integer` | `0` | `-1` | Porcentagem específica para espadas (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `Integer` | `0` | `-1` | Porcentagem específica para lanças (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `Integer` | `0` | `-1` | Porcentagem específica para tridentes (`TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `Integer` | `0` | `-1` | Porcentagem específica para maças (`MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `Integer` | `0` | `-1` | Porcentagem específica para arcos (`BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `Integer` | `0` | `-1` | Porcentagem específica para bestas (`CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `Integer` | `0` | `-1` | Porcentagem específica para escudos (`ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `Integer` | `0` | `-1` | Porcentagem da categoria pai para todas as ferramentas. |
| 11 | `ig:dm_percent_pickaxes` | `Integer` | `0` | `-1` | Porcentagem específica para picaretas (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `Integer` | `0` | `-1` | Porcentagem específica para machados (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `Integer` | `0` | `-1` | Porcentagem específica para pás (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `Integer` | `0` | `-1` | Porcentagem específica para enxadas (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `Integer` | `0` | `-1` | Porcentagem específica para tesouras (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `Integer` | `0` | `-1` | Porcentagem específica para varas de pesca (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `Integer` | `0` | `-1` | Porcentagem específica para pincéis (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `Integer` | `0` | `-1` | Porcentagem específica para isqueiros (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `Integer` | `0` | `-1` | Porcentagem da categoria pai para todas as peças de armadura. |
| 20 | `ig:dm_percent_helmets` | `Integer` | `0` | `-1` | Porcentagem específica para capacetes (`#c:helmets`, slot de cabeça). |
| 21 | `ig:dm_percent_chestplates` | `Integer` | `0` | `-1` | Porcentagem específica para peitorais (`#c:chestplates`, slot de peito). |
| 22 | `ig:dm_percent_leggings` | `Integer` | `0` | `-1` | Porcentagem específica para calças (`#c:leggings`, slot de pernas). |
| 23 | `ig:dm_percent_boots` | `Integer` | `0` | `-1` | Porcentagem específica para botas (`#c:boots`, slot de pés). |
| 24 | `ig:dm_percent_elytra` | `Integer` | `0` | `-1` | Porcentagem específica para élitros (`Items.ELYTRA`, `GLIDER`). |

---

### 2. GameRules do Modo Deus (Infinidade)
Quando ativado (`true`), itens dessa categoria recebem $0$ de dano e nunca quebram.

| # | Identificador de GameRule | Tipo | Padrão | Descrição |
| :-: | :--- | :---: | :---: | :--- |
| 25 | `ig:dm_infinity_global` | `Boolean` | `false` | Modo Deus global para todos os itens com durabilidade no jogo. |
| 26 | `ig:dm_infinity_weapons` | `Boolean` | `false` | Modo Deus para todas as armas. |
| 27 | `ig:dm_infinity_swords` | `Boolean` | `false` | Modo Deus para espadas. |
| 28 | `ig:dm_infinity_spears` | `Boolean` | `false` | Modo Deus para lanças. |
| 29 | `ig:dm_infinity_tridents` | `Boolean` | `false` | Modo Deus para tridentes. |
| 30 | `ig:dm_infinity_maces` | `Boolean` | `false` | Modo Deus para maças. |
| 31 | `ig:dm_infinity_bows` | `Boolean` | `false` | Modo Deus para arcos. |
| 32 | `ig:dm_infinity_crossbows` | `Boolean` | `false` | Modo Deus para bestas. |
| 33 | `ig:dm_infinity_shields` | `Boolean` | `false` | Modo Deus para escudos. |
| 34 | `ig:dm_infinity_tools` | `Boolean` | `false` | Modo Deus para todas as ferramentas. |
| 35 | `ig:dm_infinity_pickaxes` | `Boolean` | `false` | Modo Deus para picaretas. |
| 36 | `ig:dm_infinity_axes` | `Boolean` | `false` | Modo Deus para machados. |
| 37 | `ig:dm_infinity_shovels` | `Boolean` | `false` | Modo Deus para pás. |
| 38 | `ig:dm_infinity_hoes` | `Boolean` | `false` | Modo Deus para enxadas. |
| 39 | `ig:dm_infinity_shears` | `Boolean` | `false` | Modo Deus para tesouras. |
| 40 | `ig:dm_infinity_fishing_rods` | `Boolean` | `false` | Modo Deus para varas de pesca. |
| 41 | `ig:dm_infinity_brushes` | `Boolean` | `false` | Modo Deus para pincéis. |
| 42 | `ig:dm_infinity_flint_and_steel` | `Boolean` | `false` | Modo Deus para isqueiros. |
| 43 | `ig:dm_infinity_armor` | `Boolean` | `false` | Modo Deus para todas as armaduras. |
| 44 | `ig:dm_infinity_helmets` | `Boolean` | `false` | Modo Deus para capacetes. |
| 45 | `ig:dm_infinity_chestplates` | `Boolean` | `false` | Modo Deus para peitorais. |
| 46 | `ig:dm_infinity_leggings` | `Boolean` | `false` | Modo Deus para calças. |
| 47 | `ig:dm_infinity_boots` | `Boolean` | `false` | Modo Deus para botas. |
| 48 | `ig:dm_infinity_elytra` | `Boolean` | `false` | Modo Deus para élitros. |

---

### 3. GameRules de uso único (Modo Vidro)
Quando ativado (`true`), itens dessa categoria quebram após um único golpe.

| # | Identificador de GameRule | Tipo | Padrão | Descrição |
| :-: | :--- | :---: | :---: | :--- |
| 49 | `ig:dm_single_use_global` | `Boolean` | `false` | Modo Vidro global (uso único) para todos os itens. |
| 50 | `ig:dm_single_use_weapons` | `Boolean` | `false` | Uso único para todas as armas. |
| 51 | `ig:dm_single_use_swords` | `Boolean` | `false` | Uso único para espadas. |
| 52 | `ig:dm_single_use_spears` | `Boolean` | `false` | Uso único para lanças. |
| 53 | `ig:dm_single_use_tridents` | `Boolean` | `false` | Uso único para tridentes. |
| 54 | `ig:dm_single_use_maces` | `Boolean` | `false` | Uso único para maças. |
| 55 | `ig:dm_single_use_bows` | `Boolean` | `false` | Uso único para arcos. |
| 56 | `ig:dm_single_use_crossbows` | `Boolean` | `false` | Uso único para bestas. |
| 57 | `ig:dm_single_use_shields` | `Boolean` | `false` | Uso único para escudos. |
| 58 | `ig:dm_single_use_tools` | `Boolean` | `false` | Uso único para todas as ferramentas. |
| 59 | `ig:dm_single_use_pickaxes` | `Boolean` | `false` | Uso único para picaretas. |
| 60 | `ig:dm_single_use_axes` | `Boolean` | `false` | Uso único para machados. |
| 61 | `ig:dm_single_use_shovels` | `Boolean` | `false` | Uso único para pás. |
| 62 | `ig:dm_single_use_hoes` | `Boolean` | `false` | Uso único para enxadas. |
| 63 | `ig:dm_single_use_shears` | `Boolean` | `false` | Uso único para tesouras. |
| 64 | `ig:dm_single_use_fishing_rods` | `Boolean` | `false` | Uso único para varas de pesca. |
| 65 | `ig:dm_single_use_brushes` | `Boolean` | `false` | Uso único para pincéis. |
| 66 | `ig:dm_single_use_flint_and_steel` | `Boolean` | `false` | Uso único para isqueiros. |
| 67 | `ig:dm_single_use_armor` | `Boolean` | `false` | Uso único para todas as armaduras. |
| 68 | `ig:dm_single_use_helmets` | `Boolean` | `false` | Uso único para capacetes. |
| 69 | `ig:dm_single_use_chestplates` | `Boolean` | `false` | Uso único para peitorais. |
| 70 | `ig:dm_single_use_leggings` | `Boolean` | `false` | Uso único para calças. |
| 71 | `ig:dm_single_use_boots` | `Boolean` | `false` | Uso único para botas. |
| 72 | `ig:dm_single_use_elytra` | `Boolean` | `false` | Uso único para élitros. |

---

### 4. GameRules de exibição e mods dinâmicos

| Identificador de GameRule | Tipo | Padrão | Descrição |
| :--- | :---: | :---: | :--- |
| `ig:dm_show_tooltip` | `Boolean` | `true` | Exibe a linha de bônus de durabilidade nas dicas de itens. |
| `ig:percent_<mod>_<item>` | `Integer` | `0` | Substituição dinâmica de porcentagem para item de mod (Mín `-1`). |
| `ig:infinity_<mod>_<item>` | `Boolean` | `false` | Substituição dinâmica de Modo Deus para item de mod. |
| `ig:single_use_<mod>_<item>` | `Boolean` | `false` | Substituição dinâmica de Uso Único para item de mod. |

---

## ⚡ Comandos de ajuste no jogo

```mcfunction
# Query current global percentage
/gamerule ig:dm_percent_global

# Set diamond/netherite pickaxes to 500% (5x) durability
/gamerule ig:dm_percent_pickaxes 500

# Make Elytra wings unbreakable
/gamerule ig:dm_infinity_elytra true

# Set a modded weapon to Single-Use using the -1 sentinel
/gamerule ig:percent_techmod_plasma_cutter -1

# Disable all multipliers (vanilla 100% baseline)
/gamerule ig:dm_percent_global 100
```

