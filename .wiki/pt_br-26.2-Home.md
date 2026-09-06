# Durability Multiplier — Central de documentação do Minecraft 26.2

Bem-vindo à documentação dedicada do **Durability Multiplier** para **Minecraft 26.2** (`1.2.14+26.2`).

> 📌 **Aviso sobre o Código Fonte**: A documentação nesta Wiki reflete o **estado atual do código no repositório**, podendo incluir commits recentes ainda não lançados publicamente no CurseForge ou Modrinth.

---

## 📋 Panorama técnico (26.2)

| Parâmetro | Valor | Descrição |
| :--- | :--- | :--- |
| **Identificador do Mod** | `durability-multiplier` | ID do mod no Fabric Loader |
| **Versão do Mod** | `1.2.14+26.2` | Tag de versão SemVer |
| **Minecraft Alvo** | `26.2` (`>=26.2-`) | Âncora nativa de versão |
| **Lançamento do Java** | Java 25 | Compilado com `release = 25` |
| **Fabric Loader** | `>=0.16.9` | Requisito mínimo do loader |
| **Fabric API** | `0.150.1+26.2` | Requisito de execução do Fabric API |
| **DasikLibrary** | `1.8.28` | Núcleo de arquitetura compartilhada |
| **GameRules Registradas** | **73 Regras Estáticas** + Regras Dinâmicas | 24 Porcentagens, 24 Infinitos, 24 Uso Único, 1 Dica |
| **Pontos de Injeção de Mixin** | 3 Classes Alvo | `ItemStack`, `GameRules` |
| **Autor e Licença** | **Dasik (Rifaditya)** / GPL-3.0-or-later | Modificação de código aberto |

---

## 🧭 Matriz de navegação (26.2)

### 🎮 Guias de jogabilidade para jogadores
* [[Multiplicadores de Durabilidade e Categorias|pt_br-26.2-Durability-Multipliers]] — Sistema granular de porcentagens de 24 categorias e hierarquia de substituição.
* [[Modo Deus e Infinito|pt_br-26.2-God-Mode-and-Infinity]] — Alternâncias de invencibilidade com zero dano em 24 categorias.
* [[Matemática de Redução de Dano e Probabilidades|pt_br-26.2-Damage-Reduction-and-Probability-Math]] — Fórmulas matemáticas e arredondamento probabilístico.
* [[Classificação de Itens e Compatibilidade com Mods|pt_br-26.2-Item-Classification-and-Mod-Compatibility]] — Como itens do vanilla e de mods são classificados.
* [[Registro Dinâmico de Itens de Mods|pt_br-26.2-Dynamic-Modded-Item-Registration]] — Scanner universal de descoberta de 3 níveis e autopreenchimento.
* [[Indicadores de Dicas e HUD|pt_br-26.2-Tooltip-Indicators-and-HUD]] — Renderização de dicas de itens no lado do cliente.
* [[Tabela de Referência de GameRules|pt_br-26.2-GameRules]] — Tabela de referência completa para as 73 GameRules estáticas.
* [[Comandos e Administração no Jogo|pt_br-26.2-Commands-and-Administration]] — Gerenciando configurações no jogo via `/gamerule`.
* [[Conquistas e Progressos|pt_br-26.2-Advancements]] — Política de ausência e integração com o vanilla.
* [[Configuração e Interface GUI|pt_br-26.2-Configuration]] — Integração com ModMenu e Cloth Config.
* [[Proteção em Tempo de Execução ModVersionGuard|pt_br-26.2-ModVersionGuard-and-Safety]] — Proteção de versão sem dependências externas.
* [[Solução de Problemas e Perguntas Frequentes|pt_br-26.2-Troubleshooting-and-FAQ]] — Diagnóstico e respostas para dúvidas frequentes.

### 💻 Referência técnica e de desenvolvedor
* [[Arquitetura e Descritores de Mixin|pt_br-26.2-Architecture-and-Mixins]] — Hierarquia de pacotes, ganchos de injeção e segurança de reentrada.
* [[Sincronização de Rede e Protocolo de Carga|pt_br-26.2-Network-Sync-and-Payload-Protocol]] — Protocolo de sincronização S2C (`DurabilityPayload`).
* [[Configuração de Desenvolvedor e Compilação|pt_br-26.2-Developer-Setup-and-Building]] — Comandos Gradle, ferramentas Loom e configuração do JDK.
* [[Integração de API e Addons|pt_br-26.2-API-and-Addon-Integration]] — Estendendo o mod, `DurabilityHelper` e regras personalizadas.
