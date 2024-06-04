## Gradle Plugin for Counting Classes and Functions

### Description

A Gradle plugin in Kotlin for counting the number of classes, functions, and lines in .kt and .java files. The plugin is written in Kotlin using ANTLR4.

### Instructions for Running

1. **Download the project:**
    ```bash
    git clone https://github.com/AlexanderTaran73/ProjectDeclInfPlugin.git
    ```

2. **Navigate to the project directory:**
    ```bash
    cd ProjectDeclInfPlugin
    ```

3. **Publish the plugin to the local repository:**
    ```bash
    ./gradlew.bat publishToMavenLocal
    ```

4. **Apply the plugin to the desired project:**
    In `build.gradle`:
    ```kotlin
    plugins {
        id("io.github.AlexanderTaran73") version "0.0.1"
    }
    
    repositories {
        mavenLocal()
    }
    
    dirinf {
        dir = "src" // Or another directory where the count is needed
    }
    ```
    In `settings.gradle`:
    ```kotlin
    pluginManagement {
        repositories {
            gradlePluginPortal()
            mavenLocal()
        }
    }
    ```
5. **Build the project:**
    ```bash
    ./gradlew build
    ```

6. **Run the plugin:**
    ```bash
    ./gradlew.bat dirinf
    ```

As a result of these actions, a `ClassAndMethodsDecInf.json` file with the required data will appear in the main project folder.
