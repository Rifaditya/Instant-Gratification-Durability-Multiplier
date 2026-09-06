# Modo Deus e infinidade (26.1.2)

| Parâmetro do Sistema | Valor |
| :--- | :--- |
| **Regra Global de Infinito** | `ig:dm_infinity_global` |
| **Estado Padrão** | `false` (Desativado) |
| **Interceptação de Dano** | Dano cancelado em `HEAD` ($0$ de dano aplicado) |
| **Estilo da Dica** | `✦ UNBREAKABLE` (Dourado, Negrito) |
| **Prioridade** | Absoluta (Avaliada antes de qualquer multiplicador) |

---

## ⚡ Visão geral e mecânicas

**Modo Deus (Infinito)** concede invencibilidade completa a itens nas categorias selecionadas. Quando o Modo Deus está ativo, qualquer perda de durabilidade é totalmente interceptada e cancelada em `ItemStackDurabilityMixin`, impedindo o item de sofrer dano ou quebrar.

### Distinção do componente inquebrável vanilla
* O componente `Unbreakable` do vanilla deve ser aplicado a itens individuais por comandos (`/give @p diamond_sword[unbreakable={}]`).
* O Modo Deus do Durability Multiplier é **global para todo o mundo e categorias**: cada ferramenta, arma ou armadura no mundo comporta-se como inquebrável sem editar NBT ou componentes.

---

## 🛡️ As 24 regras do Modo Deus

| # | Chave de GameRule | Nome da Categoria | Itens Alvo | Padrão |
| :-: | :--- | :--- | :--- | :-: |
| 1 | `ig:dm_infinity_global` | **Global God Mode** | All damageable items in the game | `false` |
| 2 | `ig:dm_infinity_weapons` | **Weapons God Mode** | All weapons (swords, spears, tridents, maces, bows, crossbows) | `false` |
| 3 | `ig:dm_infinity_swords` | **Swords God Mode** | `#minecraft:swords`, `#c:swords` | `false` |
| 4 | `ig:dm_infinity_spears` | **Spears God Mode** | `#minecraft:spears`, `#c:spears` | `false` |
| 5 | `ig:dm_infinity_tridents` | **Tridents God Mode** | `Items.TRIDENT`, `TridentItem`, `#c:tridents` | `false` |
| 6 | `ig:dm_infinity_maces` | **Maces God Mode** | `Items.MACE`, `MaceItem`, `#c:maces` | `false` |
| 7 | `ig:dm_infinity_bows` | **Bows God Mode** | `Items.BOW`, `BowItem`, `#c:bows` | `false` |
| 8 | `ig:dm_infinity_crossbows` | **Crossbows God Mode** | `Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows` | `false` |
| 9 | `ig:dm_infinity_shields` | **Shields God Mode** | `Items.SHIELD`, `ShieldItem`, `#c:shields` | `false` |
| 10 | `ig:dm_infinity_tools` | **Tools God Mode** | Parent category for all Tools | `false` |
| 11 | `ig:dm_infinity_pickaxes` | **Pickaxes God Mode** | `PickaxeItem`, `#c:pickaxes` | `false` |
| 12 | `ig:dm_infinity_axes` | **Axes God Mode** | `AxeItem`, `#c:axes` | `false` |
| 13 | `ig:dm_infinity_shovels` | **Shovels God Mode** | `ShovelItem`, `#c:shovels` | `false` |
| 14 | `ig:dm_infinity_hoes` | **Hoes God Mode** | `HoeItem`, `#c:hoes` | `false` |
| 15 | `ig:dm_infinity_shears` | **Shears God Mode** | `ShearsItem`, `#c:shears` | `false` |
| 16 | `ig:dm_infinity_fishing_rods` | **Fishing Rods God Mode** | `FishingRodItem` | `false` |
| 17 | `ig:dm_infinity_brushes` | **Brushes God Mode** | `BrushItem` | `false` |
| 18 | `ig:dm_infinity_flint_and_steel` | **Flint and Steel God Mode** | `FlintAndSteelItem` | `false` |
| 19 | `ig:dm_infinity_armor` | **Armor God Mode** | Parent category for all Armor pieces | `false` |
| 20 | `ig:dm_infinity_helmets` | **Helmets God Mode** | `#minecraft:head_armor`, `#c:helmets` | `false` |
| 21 | `ig:dm_infinity_chestplates` | **Chestplates God Mode** | `#minecraft:chest_armor`, `#c:chestplates` | `false` |
| 22 | `ig:dm_infinity_leggings` | **Leggings God Mode** | `#minecraft:leg_armor`, `#c:leggings` | `false` |
| 23 | `ig:dm_infinity_boots` | **Boots God Mode** | `#minecraft:foot_armor`, `#c:boots` | `false` |
| 24 | `ig:dm_infinity_elytra` | **Elytra God Mode** | `Items.ELYTRA`, `DataComponents.GLIDER` | `false` |

---

## 👑 Ordem de resolução do Modo Deus

`DurabilityHelper.isInfinite(ServerLevel, ItemStack)` verifica:

```
[1. Per-Item Dynamic Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[2. Specific Category Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[3. Weapons/Tools/Armor Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[4. Global Infinity == true?] ──► YES ──► Unbreakable (Damage = 0)
                 │ NO
                 ▼
[Proceed to Single-Use / Multiplier Calculation]
```

O Modo Deus tem **prioridade absoluta** sobre qualquer configuração de multiplicador ou uso único. Se `ig:dm_infinity_tools = true`, as ferramentas nunca receberão dano, independentemente do percentual.

