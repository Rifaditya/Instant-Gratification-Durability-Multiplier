# Conquistas e avanços (26.2)

| Parâmetro do Sistema | Status |
| :--- | :--- |
| **JSONs de Conquistas Personalizadas** | **Nenhum** (Intencionalmente Omitido por Design) |
| **Escopo** | Modificador Puro de Jogabilidade / Utilitário de Gratificação Instantânea |
| **Conquistas do Vanilla** | 100% compatível com todas as conquistas vanilla de História e Agricultura |

---

## 📌 Declaração da política de ausência

Em estrita conformidade com a filosofia **Instant Gratification (IG)** do projeto e arquitetura de impacto mínimo:
* Durability Multiplier contém **zero árvores JSON de conquistas personalizadas**.
* O mod **não** registra gatilhos personalizados, critérios ou notificações flutuantes.

---

## 🌾 Compatibilidade com conquistas vanilla

Todas as conquistas do vanilla continuam a ser ativadas naturalmente:
* Minerar minério de diamante ativa **"Diamantes!"** (`minecraft:story/mine_diamond`).
* Voar com élitros ativa **"O Céu é o Limite"** (`minecraft:end/elytra`).
* Bloquear dano com um escudo ativa **"Hoje Não, Obrigado"** (`minecraft:story/deflect_arrow`).

Como a redução de durabilidade do item ocorre de forma transparente dentro de `ItemStack.hurtAndBreak`, as conquistas que rastreiam o uso de ferramentas, abates com armas ou danos de armadura são avaliadas sem interferência.
