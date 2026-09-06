# Indicadores de dica de ferramenta e HUD (26.1.2)

| Parâmetro do Sistema | Valor |
| :--- | :--- |
| **GameRule de Alternância** | `ig:dm_show_tooltip` |
| **Estado Padrão** | `true` (Ativado) |
| **Alvo do Mixin** | `ItemStack.addDetailsToTooltip` (`ItemStackTooltipMixin`) |
| **Ponto de Injeção** | `@At("TAIL")` |
| **Estilo do Modo Deus** | `✦ UNBREAKABLE` (Dourado, Negrito — `ChatFormatting.GOLD`, `ChatFormatting.BOLD`) |
| **Estilo do Multiplicador** | `⟨Nx Durabilidade da Categoria⟩` (Cinza — `ChatFormatting.GRAY`) |

---

## ⚡ Visão geral e apresentação visual

Durability Multiplier fornece feedback imediato e claro diretamente nas dicas de itens sempre que a vida útil de um item é modificada.

### Estilos visuais de dicas

| Status | Texto Renderizado | Aparência Visual | Código de Cor |
| :--- | :--- | :--- | :--- |
| **Modo Deus Ativo** | `✦ UNBREAKABLE` | **✦ UNBREAKABLE** | Dourado, Negrito (`ChatFormatting.GOLD`, `BOLD`) |
| **Uso Único (Modo Vidro)** | `⟨SINGLE-USE⟩` | ⟨SINGLE-USE⟩ | Cinza (`ChatFormatting.GRAY`) |
| **Multiplicador 200% / 2x** | `⟨2x Durabilidade de Espadas⟩` | ⟨2x Durabilidade de Espadas⟩ | Cinza (`ChatFormatting.GRAY`) |
| **Durabilidade de 150%** | `⟨150% Durabilidade de Peitorais⟩` | ⟨150% Durabilidade de Peitorais⟩ | Cinza (`ChatFormatting.GRAY`) |
| **50% (Metade da Durabilidade)** | `⟨50% Durabilidade de Espadas⟩` | ⟨50% Durabilidade de Espadas⟩ | Cinza (`ChatFormatting.GRAY`) |
| **Multiplicador 500% / 5x** | `⟨5x Durabilidade de Picaretas⟩` | ⟨5x Durabilidade de Picaretas⟩ | Cinza (`ChatFormatting.GRAY`) |
| **Substituição de Item de Mod** | `⟨300% Durabilidade de Plasma Cutter⟩` | ⟨300% Durabilidade de Plasma Cutter⟩ | Cinza (`ChatFormatting.GRAY`) |
| **Base Padrão do Vanilla (100%)** | *(Nenhum)* | *(Nenhuma linha extra exibida)* | — |

---

## 🎨 Modos de formatação de dica (`tooltipFormat`)

O mod suporta 3 formatos configuráveis via `config/durability-multiplier.json` e tela do ModMenu:
1. **`ADAPTIVE` (Padrão)**: Exibe automaticamente multiplicadores inteiros limpos (`2x`, `5x`) para centenas exatas e porcentagens (`50%`, `150%`) nos demais casos.
2. **`PERCENTAGE`**: Sempre exibe porcentagens explícitas (ex. `200% Durabilidade de Espadas`, `50% Durabilidade de Picaretas`).
3. **`MULTIPLIER`**: Sempre exibe multiplicadores decimais (ex. `2x Durabilidade de Espadas`, `0.5x Durabilidade de Espadas`, `1.5x Durabilidade de Peitorais`).

---

## 🖥️ Execução nos lados cliente e servidor

```
                       [Item Tooltip Render]
                                 │
                                 ▼
                     [Is Player on Integrated Server?]
                     ├── YES ──► Read GameRules from ServerLevel
                     │           (DurabilityHelper.getTooltipLabel)
                     │
                     └── NO (Remote Server) ──► Read Synced Client Cache
                                                (DurabilityClientState)
```

1. **Servidor Integrado (Um Jogador / LAN)**: As dicas consultam as GameRules ativas do `ServerLevel` diretamente em tempo real.
2. **Cliente Dedicado (Multijogador)**: As dicas leem de `DurabilityClientState`, que é atualizado por pacotes `DurabilityPayload` quando as regras mudam no servidor.
