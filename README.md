## Gradle плагин для подсчета количества слассов и функций

### Описание

Gradle плагин на котлин для подсчета количества классов, функций и строк в файлах .kt и .java. Плагин написан на Kotlin с использованием ANTLR4.

### Инструкции по запуску

1. **Скачайте проект:**
    ```bash
   git clone https://github.com/AlexanderTaran73/ProjectDeclInfPlugin.git
   ```

2. **Перейдите в каталог проекта:**
    ```bash
    cd ProjectDeclInfPlugin
    ```

3. **Опубликовать плаган в локальный репозиторий:**
    ```bash
   ./gradlew.bat publishToMavenLocal
    ```

4. **Подключить плагин в желаемый проект:**
    В 'build.gradle'
    ```kotlin
    plugins {
      id("io.github.AlexanderTaran73") version "0.0.1"
    }
   
    repositories {
      mavenLocal()
    }
   
    dirinf{
    dir = "src" // Или другую дирикторию в которой необходомо сделать подсчет
    }
    ```
   В 'settings.gradle'
    ```kotlin
    pluginManagement {
      repositories {
        gradlePluginPortal()
        mavenLocal()
      }
    }
    ```
5. **Собрать проект**
    ```bash
   ./gradlew build
   ```

6. **Запустить плагин**
    ```bash
   ./gradlew.bat dirinf
    ```


В результате выполнения этих дествий в основной папке проекта появится 'ClassAndMethodsDecInf.json' с искомыми данными.