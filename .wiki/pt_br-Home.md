# Wiki oficial do Durability Multiplier

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

Bem-vindo à documentação oficial técnica e de jogabilidade do **Durability Multiplier** (Coleção Instant Gratification), desenvolvido por **Dasik (Rifaditya)**.

> 📌 **Aviso sobre o Código Fonte**: A documentação nesta Wiki reflete o **estado atual do código no repositório**, podendo incluir commits recentes ainda não lançados publicamente no CurseForge ou Modrinth.

---

## 🧭 Portal de troca entre versões

Durability Multiplier foi projetado para versões âncoras dedicadas do Minecraft. Selecione a versão desejada abaixo para entrar em sua documentação isolada:

| Versão do Minecraft | Era de Lançamento | Build Suportada | Nível Java | Ferramentas Loom | Entrada na Wiki |
| :--- | :--- | :--- | :---: | :---: | :--- |
| **Minecraft 26.2** | Era Modern Sovereign | `1.2.14+26.2` | Java 25 | Loom 1.15.2 | [[👉 Entrar na Wiki do MC 26.2\|pt_br-26.2-Home]] |
| **Minecraft 26.1.2** | Era Modern Sovereign | `1.1.21+26.1.2` | Java 25 | Loom 1.15.2 | [[👉 Entrar na Wiki do MC 26.1.2\|pt_br-26.1.2-Home]] |

---

## ⚡ Filosofia central e arquitetura

Durability Multiplier pertence à linha de design **Instant Gratification (IG)**. Seu único objetivo é eliminar o **desgaste tedioso de manutenção** na sobrevivência do Minecraft:

* **Respeito ao Tempo do Jogador**: Elimine ciclos de reparo, paradas na mineração e quebras acidentais de equipamentos.
* **Redução de Dano Puramente Matemática**: A extensão de durabilidade é calculada por divisão inteira e arredondamento probabilístico do dano, garantindo precisão sem alterar atributos do vanilla.
* **Controle Granular**: Configure 24 categorias individuais de itens (espadas, lanças, tridentes, maças, arcos, bestas, escudos, ferramentas, picaretas, machados, pás, enxadas, tesouras, varas de pescar, pincéis, pederneiras, armadura, capacetes, peitorais, calças, botas, élitros, armas, global) independentemente em 73 GameRules estáticas.
* **Modo Deus (Infinito)**: Torne qualquer categoria 100% inquebrável com uma única GameRule booleana.
* **Detecção Automática de Itens de Mods**: Descobre itens com durabilidade ao congelar o registro e expõe GameRules dedicadas e controles na GUI.
* **Zero Dessincronização entre Lados**: As GameRules do servidor são sincronizadas com os clientes via rede Fabric (`durability-multiplier:sync_rules`) para dicas ao vivo.

---

## 📚 Navegação global e recursos

* [[Matriz de Compatibilidade de Versões|pt_br-Version-Compatibility]]
* [[Central de Documentação do MC 26.2|pt_br-26.2-Home]]
* [[Central de Documentação do MC 26.1.2|pt_br-26.1.2-Home]]
* [Página na Plataforma CurseForge](https://curseforge.com/minecraft/mc-mods/durability-multiplier)
* [Página na Plataforma Modrinth](https://modrinth.com/mod/durability-multiplier)
* [Repositório de Código no GitHub](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier)
