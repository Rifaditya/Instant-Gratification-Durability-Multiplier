# ModVersionGuard e segurança em tempo de execução (26.2)

| Parâmetro | Valor |
| :--- | :--- |
| **Classe de Proteção** | `net.instantgratification.durabilitymultiplier.util.ModVersionGuard` |
| **Invocação** | `DurabilityMultiplierFabric.onInitialize()` |
| **Classe Verificada** | `net.minecraft.world.entity.EntityTypes` (Indicador MC 26.2+) |
| **ClassLoader** | `Thread.currentThread().getContextClassLoader()` (Knot ClassLoader) |
| **Objetivo** | Evitar corrupção do mundo se carregado em ambiente incompatível |

---

## 🛡️ Como o ModVersionGuard protege seus mundos

As versões de Minecraft na era Modern Sovereign passam por rápidas evoluções de API. Se um mod compilado para o MC 26.2 for executado em um ambiente incompatível, erros de carregamento de classe podem corromper mundos.

`ModVersionGuard` executa uma pré-verificação sem dependências durante `onInitialize()` antes de inicializar GameRules, mixins ou configs:

```java
ModVersionGuard.checkClass("Durability Multiplier", "net.minecraft.world.entity.EntityTypes");
```

Se a classe exigida estiver ausente no Knot ClassLoader, o jogo para imediatamente com uma mensagem explicativa:

```
=====================================================================
 [PRE-RELEASE / VERSION GUARD WARNING] Durability Multiplier
---------------------------------------------------------------------
 CRITICAL: Incompatible Minecraft Game Runtime or Missing Class!
 Required Class : net.minecraft.world.entity.EntityTypes
 Status         : UNRESOLVED AT RUNTIME

 Safety Protection:
 Execution halted to prevent unreleased/incompatible build deployment
 or broken world state save corruption.

 Troubleshooting Steps:
 1. Verify target Minecraft version (26.2+ release drop).
 2. Ensure all required dependencies (Fabric API, DasikLibrary) are loaded.
 3. Build/Download a verified matching release JAR from Modrinth/CurseForge.
=====================================================================
```
