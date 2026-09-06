# Progrès et réalisations (26.1.2)

| Paramètre système | Statut |
| :--- | :--- |
| **JSON de progrès personnalisés** | **Aucun** (Intentionnellement omis par conception) |
| **Portée** | Modificateur de gameplay pur / Utilitaire Instant Gratification |
| **Progrès Vanilla** | 100% compatible avec tous les progrès vanilla d'histoire et d'agriculture |

---

## 📌 Déclaration de la politique d'absence

En stricte conformité avec la philosophie **Instant Gratification (IG)** du projet et son empreinte minimale :
* Durability Multiplier ne contient **aucun arbre JSON de progrès personnalisé**.
* Le mod n'enregistre **aucun** déclencheur, critère ou notification toast personnalisé.

---

## 🌾 Compatibilité avec les progrès vanilla

Tous les progrès vanilla continuent de se déclencher naturellement :
* Miner du diamant déclenche **« Des diamants ! »** (`minecraft:story/mine_diamond`).
* Voler avec des élytres déclenche **« Tout droit vers le ciel »** (`minecraft:end/elytra`).
* Bloquer des dégâts avec un bouclier déclenche **« Pas aujourd'hui, merci »** (`minecraft:story/deflect_arrow`).

Comme la réduction de durabilité se produit de manière transparente dans `ItemStack.hurtAndBreak`, les progrès suivant l'utilisation des outils, les éliminations ou les dégâts d'armure s'évaluent sans aucune interférence.
