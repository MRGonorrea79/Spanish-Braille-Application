# Entorno de desarrollo

## Requisitos mínimos
- JDK: 21 (OpenJDK / Adoptium u otro proveedor)
- Maven: 3.8+ (si no quieres instalar Maven, usar el Maven Wrapper `mvnw` cuando exista)
- Git
- IDE recomendado: IntelliJ IDEA o Eclipse

## Variables de entorno
- `JAVA_HOME` apuntando al directorio del JDK.

## Comandos útiles
- Compilar y empaquetar:
  ```cmd
  mvn clean package
  ```
- Ejecutar tests:
  ```cmd
  mvn test
  ```
- Ejecutar la aplicación directamente:
  ```cmd
  java -jar target\Proyecto-construccion-0.0.1-SNAPSHOT.jar
  ```
- Ejecutar con Spring Boot (desarrollo):
  ```cmd
  mvn spring-boot:run
  ```

## Generar JavaDoc
```cmd
mvn javadoc:javadoc
```
Salida: `target/site/apidocs/`

## Flujo de trabajo Git (estrategia)
- `main`: rama estable y protegida. No se debe desarrollar directamente en `main`.
- `documentacion`: rama para todo el trabajo documental.
- `feature/<nombre>`: ramas para nuevas características de código.
- `fix/<nombre>` o `hotfix/<nombre>`: correcciones urgentes.

Reglas:
- No hacer push directo a `main`.
- Crear PRs hacia `main` y solicitar al menos 1 revisión.
- Ejecutar tests y comprobar JavaDoc antes de mergear.

## Importar en IDE
- Importar como proyecto Maven.
- Ejecutar la clase `ProyectoConstruccionApplication` como aplicación Java/Spring Boot.

---
# Entorno de desarrollo (instrucciones detalladas)

Este documento describe el entorno exacto y los pasos para poner en marcha, compilar, probar y ejecutar la aplicación en Windows (cmd.exe). Incluye configuración de JDK, Maven, uso del Maven Wrapper y flujo de trabajo Git recomendado.

## 1) Requisitos (versiones exactas)

- Java: OpenJDK 21 (probado con Adoptium Temurin 21).
- Maven: 3.9.x (si usas el wrapper no es obligatorio instalar Maven globalmente).
- Git: versión reciente (2.30+ recomendable).
- IDE: IntelliJ IDEA (recomendado) o Eclipse.

Comprueba versiones instaladas:

```cmd
java -version
mvn -v
git --version
```

Si `java -version` no muestra Java 21, instala JDK 21 y configura `JAVA_HOME`.

## 2) Instalar JDK en Windows y configurar `JAVA_HOME`

- Descargar JDK 21 desde Adoptium (https://adoptium.net) u otro proveedor.
- Instalar normalmente (por ejemplo `C:\Program Files\Eclipse Adoptium\jdk-21.x.x`).
- Configurar `JAVA_HOME` y añadir al `PATH` (cmd.exe):

```cmd
setx JAVA_HOME "C:\Program Files\Eclipse Adoptium\jdk-21.x.x"
setx PATH "%JAVA_HOME%\bin;%PATH%"
```

Después abre una nueva ventana `cmd` y verifica:

```cmd
echo %JAVA_HOME%
java -version
```

## 3) Maven y Maven Wrapper

- Si no quieres instalar Maven globalmente, el proyecto debería incluir el Maven Wrapper (`mvnw`, `mvnw.cmd` y `.mvn/wrapper/*`). Si el wrapper no está en el repo, puedes generar uno (requiere Maven instalado):

```cmd
mvn -N io.takari:maven:wrapper
```

- Una vez generado, deberías ver `mvnw`, `mvnw.cmd` y `.mvn/wrapper/maven-wrapper.properties`.

- Para usar el wrapper (recomendado cuando esté en el repo):

```cmd
.\mvnw.cmd clean package
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

## 4) Ignorar artefactos de build

Asegúrate de que `target/` está en `.gitignore` para no subir artefactos de compilación:

```
/target/
*.log
```

## 5) Comandos básicos (cmd.exe)

- Compilar y empaquetar JAR:

```cmd
mvn clean package
```

o usando wrapper:

```cmd
.\mvnw.cmd clean package
```

- Ejecutar tests (todos):

```cmd
mvn test
```

Ejecutar un test concreto (por clase):

```cmd
mvn -Dtest=BrailleMapperTest test
```

- Generar Javadoc:

```cmd
mvn javadoc:javadoc
```

Salida: `target/site/apidocs/`.

- Ejecutar la aplicación (JAR):

```cmd
java -jar target\Proyecto-construccion-0.0.1-SNAPSHOT.jar
```

Ejecutar en otro puerto:

```cmd
java -jar target\Proyecto-construccion-0.0.1-SNAPSHOT.jar --server.port=8081
```

- Ejecutar en segundo plano (Windows):

```cmd
start "Proyecto-Braille" cmd /c "java -jar target\Proyecto-construccion-0.0.1-SNAPSHOT.jar"
```

## 6) Debug / ejecución desde IDE (IntelliJ)

1. Importa el proyecto como Maven: `File -> Open` y selecciona el `pom.xml` del subproyecto.
2. Marca `ProyectoConstruccionApplication` (ubicada en `ec.epn.edu.proyectoconstruccion`) como clase principal.
3. Ejecuta con configuración de tipo `Application` o `Spring Boot`.
4. Para depurar, añade breakpoints y usa `Run -> Debug`.

VM options útiles al ejecutar en IDE:

```
-Dspring.profiles.active=dev
-Xmx512m
```

## 7) Notas sobre `pom.xml` y la propiedad `java.version`

- Si `pom.xml` declara una versión de Java mayor a la instalada (ej.: `25`), la compilación fallará. Para compilar localmente sin cambiar `pom.xml` puedes pasar la propiedad al mvn:

```cmd
mvn clean package -Djava.version=21
```

Sin embargo, lo recomendable es alinear `pom.xml` con la versión real (editar la propiedad `<java.version>`), o instalar la versión de JDK requerida.

## 8) Flujo de trabajo Git (recordatorio ejecutable)

- Ramas:
  - `main`: estable (protegida). No hacer commits directos.
  - `documentacion`: para documentación.
  - `feature/<nombre>`: desarrollo de nuevas funcionalidades.
  - `fix/<nombre>` o `hotfix/<nombre>`: correcciones urgentes.

- Pasos para crear y subir una rama de trabajo:

```cmd
git checkout main
git pull origin main
git checkout -b feature/mi-cambio
# trabajar, git add, git commit
git push -u origin feature/mi-cambio
```

- Para trabajar documentación:

```cmd
git checkout documentacion
# editar archivos en documentacion/
git add documentacion/
git commit -m "docs: <mensaje>"
git push origin documentacion
```

## 9) Revisión y Pull Request

- Antes de abrir PR:
  - Ejecutar `mvn test` y verificar que todos pasan.
  - Generar JavaDoc si la PR incluye cambios en API pública.
  - Asegurarse de no incluir `target/` ni archivos binarios.

- Plantilla mínima para PR de documentación:
  - Título: `docs: <breve descripción>`
  - Descripción: qué archivos se agregan/modifican y cómo validar.
  - Checklist: tests ejecutados, JavaDoc (sí/no), imágenes adjuntadas.

## 10) Troubleshooting rápido

- Error "release version X not supported": la propiedad `java.version` en `pom.xml` es mayor que la versión del JDK. Soluciones: instalar JDK requerido o compilar con `-Djava.version=<tu-version>`.
- `mvnw.cmd` falla por falta de `.mvn/wrapper`: o bien instalar Maven global o generar el wrapper con `mvn -N io.takari:maven:wrapper`.

---
*Este archivo puede actualizarse con pasos para macOS/Linux si es necesario; por ahora contiene comandos para Windows (cmd.exe).* 
