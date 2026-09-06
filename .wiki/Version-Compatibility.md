# Version Compatibility & Lifecycle Matrix

| Specification | Minecraft 26.2 Anchor | Minecraft 26.1.2 Anchor |
| :--- | :--- | :--- |
| **Target Minecraft Version** | `26.2` (`>=26.2-`) | `26.1.2` (`*`) |
| **Mod Version (SemVer)** | `1.2.14+26.2` | `1.1.21+26.1.2` |
| **Java Toolchain** | Java 25 (`release = 25`) | Java 25 (`release = 25`) |
| **Fabric Loader** | `>=0.16.9` (Built on `0.19.1`) | `>=0.16.9` (Built on `0.19.1`) |
| **Fabric API** | `0.150.1+26.2` | `0.145.4+26.1.2` |
| **Fabric Loom** | `1.15.2` | `1.15.2` |
| **DasikLibrary Dependency** | `1.8.28` | `1.8.28` |
| **Cloth Config Dependency** | `26.1.154` (Optional) | `26.1.154` (Optional) |
| **ModMenu Dependency** | `18.0.0-beta.1` (Optional) | `18.0.0-beta.1` (Optional) |
| **ModVersionGuard Protection** | ✅ Active (`EntityTypes` Class Check) | Standard Runtime |
| **Dedicated Wiki Hub** | [[👉 Open MC 26.2 Hub\|26.2-Home]] | [[👉 Open MC 26.1.2 Hub\|26.1.2-Home]] |

---

## 🏛️ Era Architecture & "1 Jar 1 Version" Law

Durability Multiplier follows the **1 Jar 1 Version** design mandate:
1. Each Minecraft major drop has its own dedicated subproject directory (`Durability Multiplier v26.1/`, `Durability Multiplier v26.2/`).
2. Release artifacts are compiled independently into tagged JARs (`durability-multiplier-1.1.21+26.1.2.jar`, `durability-multiplier-1.2.14+26.2.jar`) and centrally cataloged in `Archive Jar of all versions/`.
3. MC 26.2 incorporates zero-dependency `ModVersionGuard` runtime verification in `onInitialize()` to safely halt execution if loaded into incompatible future or past environments, protecting player world save data against corruption.

---

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.
