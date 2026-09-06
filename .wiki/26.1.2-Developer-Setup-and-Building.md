# Developer Setup & Building (26.1.2)

| Environment Requirement | Specification |
| :--- | :--- |
| **Java Development Kit (JDK)** | **JDK 25** (Oracle / Eclipse Temurin / Microsoft Build) |
| **Gradle Wrapper** | **Gradle 8.x / 9.x** |
| **Fabric Loom** | `1.15.2` (`net.fabricmc.fabric-loom`) |
| **Target Minecraft** | `26.1.2` |
| **DasikLibrary Dependency** | `1.8.28` |

---

## 🛠️ Build Commands

Execute in terminal from the subproject directory:

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

## 📄 Dependency Declarations (`build.gradle`)

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
