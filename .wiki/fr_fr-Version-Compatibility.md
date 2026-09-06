# Compatibilité des versions et matrice de cycle de vie

| Spécification | Ancrage Minecraft 26.2 | Ancrage Minecraft 26.1.2 |
| :--- | :--- | :--- |
| **Version cible de Minecraft** | `26.2` (`>=26.2-`) | `26.1.2` (`*`) |
| **Version du mod (SemVer)** | `1.2.14+26.2` | `1.1.21+26.1.2` |
| **Chaîne d'outils Java** | Java 25 (`release = 25`) | Java 25 (`release = 25`) |
| **Fabric Loader** | `>=0.16.9` (Compilé sur `0.19.1`) | `>=0.16.9` (Compilé sur `0.19.1`) |
| **Fabric API** | `0.150.1+26.2` | `0.145.4+26.1.2` |
| **Fabric Loom** | `1.15.2` | `1.15.2` |
| **Dépendance DasikLibrary** | `1.8.28` | `1.8.28` |
| **Dépendance Cloth Config** | `26.1.154` (Optionnel) | `26.1.154` (Optionnel) |
| **Dépendance ModMenu** | `18.0.0-beta.1` (Optionnel) | `18.0.0-beta.1` (Optionnel) |
| **Protection ModVersionGuard** | ✅ Active (Vérification de la classe `EntityTypes`) | Environnement standard |
| **Hub Wiki dédié** | [[👉 Ouvrir le Hub MC 26.2\|fr_fr-26.2-Home]] | [[👉 Ouvrir le Hub MC 26.1.2\|fr_fr-26.1.2-Home]] |

---

## 🏛️ Architecture des ères & Loi « 1 Jar 1 Version »

Durability Multiplier applique le principe de conception **1 Jar 1 Version** :
1. Chaque version majeure de Minecraft possède son propre sous-projet (`Durability Multiplier v26.1/`, `Durability Multiplier v26.2/`).
2. Les fichiers JAR sont compilés indépendamment (`durability-multiplier-1.1.21+26.1.2.jar`, `durability-multiplier-1.2.14+26.2.jar`) et archivés de manière centralisée.
3. MC 26.2 intègre `ModVersionGuard` en `onInitialize()` pour interrompre l'exécution de manière sécurisée en cas d'incompatibilité, protégeant ainsi les mondes.

---

> 📌 **Avertissement relatif au code source** : La documentation de ce Wiki reflète l'**état actuel du code dans le dépôt**, qui peut inclure des commits de développement non encore publiés sur CurseForge et Modrinth.
