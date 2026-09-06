# Multiplicadores de durabilidade e porcentagens (26.2)

Durability Multiplier substitui a mecânica de desgaste fixo do vanilla por um **Motor Dinâmico de Escala de Porcentagem** que suporta aumentos de durabilidade (ex. 200% = 2x, 500% = 5x) e penalidades de desgaste (ex. 50% = 0.5x, 25% = 0.25x).

---

## ⚙️ Principais GameRules de porcentagem

| # | Identificador de GameRule | Padrão | Categoria Alvo / Descrição |
| :-: | :--- | :---: | :--- |
| 1 | `ig:dm_percent_global` | `200` | Porcentagem global aplicada a todos os itens com durabilidade. |
| 2 | `ig:dm_percent_weapons` | `0` | Substituição pai para todas as armas (espadas, lanças, tridentes, maças, arcos, bestas). |
| 3 | `ig:dm_percent_swords` | `0` | Porcentagem específica para espadas (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `0` | Porcentagem específica para lanças (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `0` | Porcentagem específica para tridentes (`Items.TRIDENT`, `TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `0` | Porcentagem específica para maças (`Items.MACE`, `MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `0` | Porcentagem específica para arcos (`Items.BOW`, `BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `0` | Porcentagem específica para bestas (`Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `0` | Porcentagem específica para escudos (`Items.SHIELD`, `ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `0` | Porcentagem da categoria pai para todas as ferramentas. |
| 11 | `ig:dm_percent_pickaxes` | `0` | Porcentagem específica para picaretas (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `0` | Porcentagem específica para machados (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `0` | Porcentagem específica para pás (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `0` | Porcentagem específica para enxadas (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `0` | Porcentagem específica para tesouras (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `0` | Porcentagem específica para varas de pesca (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `0` | Porcentagem específica para pincéis (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `0` | Porcentagem específica para isqueiros (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `0` | Porcentagem da categoria pai para todas as peças de armadura. |
| 20 | `ig:dm_percent_helmets` | `0` | Porcentagem específica para capacetes (`#minecraft:head_armor`, `#c:helmets`). |
| 21 | `ig:dm_percent_chestplates` | `0` | Porcentagem específica para peitorais (`#minecraft:chest_armor`, `#c:chestplates`). |
| 22 | `ig:dm_percent_leggings` | `0` | Porcentagem específica para calças (`#minecraft:leg_armor`, `#c:leggings`). |
| 23 | `ig:dm_percent_boots` | `0` | Porcentagem específica para botas (`#minecraft:foot_armor`, `#c:boots`). |
| 24 | `ig:dm_percent_elytra` | `0` | Porcentagem específica para élitros (`Items.ELYTRA`, `DataComponents.GLIDER`). |

> [!NOTE]
> Regras de anulação definidas como `0` revertem automaticamente para a categoria pai ou para o padrão Global. Definir `-1` ativa **Uso Único (Modo Vidro)**.

---

## 🔒 100% de segurança para os salvamentos de mundo
Durability Multiplier **não** altera o NBT do item ou `DataComponents.MAX_DAMAGE` no salvamento do mundo. Toda escala é calculada dinamicamente ao computar o dano, garantindo zero corrupção se o mod for removido.
