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
*Guía básica para poner en marcha el entorno de desarrollo.*
