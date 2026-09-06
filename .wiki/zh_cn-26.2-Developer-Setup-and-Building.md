# 开发者环境搭建与构建 (26.2)

| 开发环境要求 | 规格规范 |
| :--- | :--- |
| **Java 开发工具包 (JDK)** | **JDK 25** (Oracle / Eclipse Temurin / Microsoft Build) |
| **Gradle 包装器** | **Gradle 8.x / 9.x** |
| **Fabric Loom** | `1.15.2` (`net.fabricmc.fabric-loom`) |
| **目标 Minecraft 版本** | `26.2` |
| **DasikLibrary 依赖** | `1.8.28` |

---

## 🛠️ 构建命令

在子项目根目录打开终端执行：

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

## 📄 依赖声明 (`build.gradle`)

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
