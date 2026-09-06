# Compatibilidade de versões e matriz de ciclo de vida

| Especificação | Âncora Minecraft 26.2 | Âncora Minecraft 26.1.2 |
| :--- | :--- | :--- |
| **Versão Alvo do Minecraft** | `26.2` (`>=26.2-`) | `26.1.2` (`*`) |
| **Versão do Mod (SemVer)** | `1.2.14+26.2` | `1.1.21+26.1.2` |
| **Ferramentas Java** | Java 25 (`release = 25`) | Java 25 (`release = 25`) |
| **Fabric Loader** | `>=0.16.9` (Compilado em `0.19.1`) | `>=0.16.9` (Compilado em `0.19.1`) |
| **Fabric API** | `0.150.1+26.2` | `0.145.4+26.1.2` |
| **Fabric Loom** | `1.15.2` | `1.15.2` |
| **Dependência DasikLibrary** | `1.8.28` | `1.8.28` |
| **Dependência Cloth Config** | `26.1.154` (Opcional) | `26.1.154` (Opcional) |
| **Dependência ModMenu** | `18.0.0-beta.1` (Opcional) | `18.0.0-beta.1` (Opcional) |
| **Proteção ModVersionGuard** | ✅ Ativa (Verificação de classe `EntityTypes`) | Ambiente Padrão |
| **Central da Wiki Dedicada** | [[👉 Abrir Central MC 26.2\|pt_br-26.2-Home]] | [[👉 Abrir Central MC 26.1.2\|pt_br-26.1.2-Home]] |

---

## 🏛️ Arquitetura de eras e lei "1 Jar 1 Version"

Durability Multiplier segue o princípio de design **1 Jar 1 Version**:
1. Cada versão principal do Minecraft possui seu próprio diretório de subprojeto dedicado (`Durability Multiplier v26.1/`, `Durability Multiplier v26.2/`).
2. Os artefatos de lançamento são compilados independentemente em JARs etiquetados (`durability-multiplier-1.1.21+26.1.2.jar`, `durability-multiplier-1.2.14+26.2.jar`) e arquivados centralmente.
3. O MC 26.2 incorpora verificação em tempo de execução sem dependências com `ModVersionGuard` em `onInitialize()` para interromper a execução com segurança se carregado em ambiente incompatível, protegendo os mundos.

---

> 📌 **Aviso sobre o Código Fonte**: A documentação nesta Wiki reflete o **estado atual do código no repositório**, podendo incluir commits recentes ainda não lançados publicamente no CurseForge ou Modrinth.
