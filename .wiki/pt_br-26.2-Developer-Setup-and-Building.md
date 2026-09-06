# Configuração de desenvolvedor e compilação (26.2)

| Requisito de Ambiente | Especificação |
| :--- | :--- |
| **Java Development Kit (JDK)** | **JDK 25** (Oracle / Eclipse Temurin / Microsoft Build) |
| **Gradle Wrapper** | **Gradle 8.x / 9.x** |
| **Fabric Loom** | `1.15.2` (`net.fabricmc.fabric-loom`) |
| **Minecraft Alvo** | `26.2` |
| **Dependência DasikLibrary** | `1.8.28` |

---

## 🛠️ Comandos de compilação

Execute no terminal a partir do diretório do subprojeto:

```bash
# Build the production release JAR
./gradlew build --no-daemon

# Run automated tests and GameTests
./gradlew test --no-daemon

# Launch Fabric Client development environment
./gradlew runClient --no-daemon

# Launch Fabric Server development environment
./gradlew runServer --no-daemon
```

---

## 📄 Declarações de dependências (`build.gradle`)

```groovy
dependencies {
    minecraft "com.mojang:minecraft:${project.minecraft_version}"

    implementation "net.fabricmc:fabric-loader:${project.fabric_loader_version}"
    implementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_version}"

    implementation "net.dasik.social:dasik-library:${project.dasik_library_version}"

    compileOnly "me.shedaniel.cloth:cloth-config-fabric:26.1.154"
    runtimeOnly "me.shedaniel.cloth:cloth-config-fabric:26.1.154"
    compileOnly "com.terraformersmc:modmenu:18.0.0-beta.1"
    runtimeOnly "com.terraformersmc:modmenu:18.0.0-beta.1"
}
```
