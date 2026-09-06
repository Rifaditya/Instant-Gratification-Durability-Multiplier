# Configuração e integração de GUI (26.2)

| Parâmetro do Sistema | Valor |
| :--- | :--- |
| **Caminho do Arquivo de Configuração** | `config/durability-multiplier.json` |
| **Versão da Configuração** | `2` (Migrado automaticamente da v1) |
| **Provedores de GUI** | Cloth Config (`me.shedaniel.cloth:cloth-config-fabric`) e ModMenu |
| **Classe de Configuração** | `net.instantgratification.durabilitymultiplier.config.DurabilityConfig` |
| **Auxiliar de GUI** | `ClothConfigScreenHelper` e `ModMenuIntegration` |
| **Lei da Precedência** | O arquivo define **APENAS PADRÕES PARA NOVOS MUNDOS**; mundos ativos usam GameRules |

---

## ⚙️ Estrutura do arquivo de configuração (`config/durability-multiplier.json`)

O arquivo de configuração define parâmetros base e padrões para todos os mundos e servidores criados. Ele suporta porcentagens de durabilidade, Modo Deus (Infinito), Uso Único (Modo Vidro), formatação personalizada de dicas e substituições para itens de mods.

```json
{
  "configVersion": 2,
  
  "percentGlobal": 200,
  "percentWeapons": 0,
  "percentSwords": 0,
  "percentSpears": 0,
  "percentTridents": 0,
  "percentMaces": 0,
  "percentBows": 0,
  "percentCrossbows": 0,
  "percentTools": 0,
  "percentPickaxes": 0,
  "percentAxes": 0,
  "percentShovels": 0,
  "percentHoes": 0,
  "percentShears": 0,
  "percentFishingRods": 0,
  "percentBrushes": 0,
  "percentFlintAndSteel": 0,
  "percentArmor": 0,
  "percentHelmets": 0,
  "percentChestplates": 0,
  "percentLeggings": 0,
  "percentBoots": 0,
  "percentElytra": 0,
  "percentShields": 0,
  
  "infinityGlobal": false,
  "infinityWeapons": false,
  "infinitySwords": false,
  "infinitySpears": false,
  "infinityTridents": false,
  "infinityMaces": false,
  "infinityBows": false,
  "infinityCrossbows": false,
  "infinityTools": false,
  "infinityPickaxes": false,
  "infinityAxes": false,
  "infinityShovels": false,
  "infinityHoes": false,
  "infinityShears": false,
  "infinityFishingRods": false,
  "infinityBrushes": false,
  "infinityFlintAndSteel": false,
  "infinityArmor": false,
  "infinityHelmets": false,
  "infinityChestplates": false,
  "infinityLeggings": false,
  "infinityBoots": false,
  "infinityElytra": false,
  "infinityShields": false,
  
  "singleUseGlobal": false,
  "singleUseWeapons": false,
  "singleUseSwords": false,
  "singleUseSpears": false,
  "singleUseTridents": false,
  "singleUseMaces": false,
  "singleUseBows": false,
  "singleUseCrossbows": false,
  "singleUseTools": false,
  "singleUsePickaxes": false,
  "singleUseAxes": false,
  "singleUseShovels": false,
  "singleUseHoes": false,
  "singleUseShears": false,
  "singleUseFishingRods": false,
  "singleUseBrushes": false,
  "singleUseFlintAndSteel": false,
  "singleUseArmor": false,
  "singleUseHelmets": false,
  "singleUseChestplates": false,
  "singleUseLeggings": false,
  "singleUseBoots": false,
  "singleUseElytra": false,
  "singleUseShields": false,
  
  "showTooltip": true,
  "tooltipFormat": "ADAPTIVE",
  
  "forcedItems": [],
  "forcedPercentages": {},
  "forcedInfinities": {},
  "forcedSingleUses": {}
}
```

---

## 🔄 O sistema de preenchimento automático

Durability Multiplier apresenta um **Scanner de Descoberta Universal de 3 Níveis** autônomo que cataloga automaticamente itens de mods sem exigir entrada manual de dados:

1. **Varredura Inicial**: Na inicialização do cliente/servidor, o motor escaneia `BuiltInRegistries.ITEM`.
2. **Filtro de Durabilidade**: Itens de mods externos (excluindo `minecraft` e tags de convenção `c`) são verificados para `DataComponents.MAX_DAMAGE > 0`.
3. **Autopreenchimento**: Itens com durabilidade descobertos são automaticamente adicionados a:
   * `"forcedItems"`: Added to the active item registry list.
   * `"forcedPercentages"`: Added with default value `0` (indicating the item dynamically inherits its category or global multiplier).
4. **Persistência de Configuração**: As listas atualizadas são salvas em `config/durability-multiplier.json`, tornando todos os itens de mods imediatamente visíveis e editáveis na GUI e GameRules.

---

## 🛠️ Guia de configuração manual de itens

Autores de modpacks, administradores de servidores e jogadores podem declarar manualmente regras para itens específicos em `config/durability-multiplier.json`:

### 1. `forcedItems` (Registro de itens)
Declara a lista de identificadores de recursos de itens reconhecidos pelo mod.
```json
"forcedItems": [
  "techmod:plasma_cutter",
  "magicmod:staff_of_fire",
  "customweapons:obsidian_blade"
]
```

### 2. `forcedPercentages` (Porcentagens de durabilidade por item)
Atribui multiplicadores explícitos de porcentagem de durabilidade a itens específicos:
* `0`: Herda da categoria pai ou multiplicador global.
* `100`: Padrão vanilla 100% (durabilidade 1x).
* `200`: Durabilidade 200% (2x vida útil).
* `50`: Durabilidade 50% (metade da vida / 2x desgaste).
* `-1`: Uso Único (Modo Vidro - quebra no primeiro golpe).
```json
"forcedPercentages": {
  "techmod:plasma_cutter": 300,
  "customweapons:obsidian_blade": -1,
  "magicmod:training_wand": 50
}
```

### 3. `forcedInfinities` (Modo Deus por item)
Concede status inquebrável permanente a itens específicos:
```json
"forcedInfinities": {
  "magicmod:creative_staff": true,
  "adminmod:ban_hammer": true
}
```

### 4. `forcedSingleUses` (Modo Vidro por item)
Força itens específicos a quebrarem após perderem durabilidade uma única vez:
```json
"forcedSingleUses": {
  "techmod:disposable_cutter": true,
  "survivalplus:glass_dagger": true
}
```

---

## ⚡ Sentinela de modo Vidro `-1` para usuários avançados

Durability Multiplier inclui um **Valor Sentinela `-1`** para porcentagens de durabilidade:
* Definir qualquer regra de porcentagem ou campo de configuração para `-1` (ou qualquer inteiro negativo) ativa automaticamente **Uso Único (Modo Vidro)** para aquele item ou categoria.
* Quando ativo, o item recebe `maxDamage - damageValue` no primeiro golpe, reduzindo sua durabilidade a 0 e quebrando em exatamente 1 uso.
* Isso permite que administradores de servidores e autores de pacotes imponham a mecânica de quebra em 1 golpe diretamente por barras de porcentagem ou comandos `/gamerule`.

---

## 🎨 Formatação de exibição de dicas

A opção `tooltipFormat` configura como os bônus de durabilidade são exibidos nas dicas de itens:

| Configuração de Formato | Exemplo de Saída (200% / 2x) | Exemplo de Saída (150% / 1.5x) | Descrição |
| :--- | :--- | :--- | :--- |
| `"ADAPTIVE"` *(Padrão)* | `⟨2x Durabilidade de Espadas⟩` | `⟨150% Durabilidade de Espadas⟩` | Exibe multiplicadores inteiros limpos para centenas exatas; porcentagens caso contrário. |
| `"PERCENTAGE"` | `⟨200% Durabilidade de Espadas⟩` | `⟨150% Durabilidade de Espadas⟩` | Sempre exibe o valor exato de porcentagem. |
| `"MULTIPLIER"` | `⟨2x Durabilidade de Espadas⟩` | `⟨1.5x Durabilidade de Espadas⟩` | Sempre exibe o texto formatado do multiplicador. |

Defina `"showTooltip": false` para ocultar totalmente os indicadores de durabilidade.

---

## ⚠️ Aviso importante sobre precedência de configuração

> ⚠️ **Aviso**: Mudanças feitas em `durability-multiplier.json` ou na tela do ModMenu **definem apenas valores base padrão para mundos recém-criados**.
> 
> Para mundos existentes e ativos, cada mundo mantém seu próprio estado independente de GameRule salvo nos dados do mundo (`level.dat`). Para alterar as configurações em um mundo ativo, use o comando `/gamerule` no jogo ou a tela nativa de edição de GameRules.

