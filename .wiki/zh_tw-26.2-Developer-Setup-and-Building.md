# 開發者環境建置與建置 (26.2)

| 開發環境要求 | 規格規范 |
| :--- | :--- |
| **Java 開發工具封包 (JDK)** | **JDK 25** (Oracle / Eclipse Temurin / Microsoft Build) |
| **Gradle 封包裝器** | **Gradle 8.x / 9.x** |
| **Fabric Loom** | `1.15.2` (`net.fabricmc.fabric-loom`) |
| **目標 Minecraft 版本** | `26.2` |
| **DasikLibrary 依赖** | `1.8.28` |

---

## 🛠️ 建置指令

在子項目根目錄打開终端執行：

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

## 📄 相依性宣告 (`build.gradle`)

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
