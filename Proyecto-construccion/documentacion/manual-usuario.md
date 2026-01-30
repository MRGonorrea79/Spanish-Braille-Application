# Manual de usuario

## Descripción general
Spanish-Braille-Application es una aplicación web que convierte texto en español a Braille Unicode. Soporta el alfabeto español completo (incluyendo ñ, ü, vocales acentuadas), mayúsculas, números y puntuación básica. El sistema normaliza espacios y ofrece una interfaz web intuitiva y accesible.

---

## Acceso y requisitos
- **Navegador recomendado:** Chrome, Firefox, Edge, Safari
- **URL por defecto:** [http://localhost:8080](http://localhost:8080)
- **Requisitos previos:** Java 17+ (recomendado Java 25), Maven 3.8+ (o usar el Maven Wrapper incluido)

---

## Instalación y ejecución
1. **Clonar el repositorio:**
   ```powershell
   git clone https://github.com/tu-usuario/Spanish-Braille-Application.git
   cd Spanish-Braille-Application/Proyecto-construccion
   ```
2. **Compilar el proyecto:**
   ```powershell
   ./mvnw clean install
   # O en Windows: mvnw.cmd clean install
   ```
3. **Ejecutar la aplicación:**
   ```powershell
   ./mvnw spring-boot:run
   # O en Windows: mvnw.cmd spring-boot:run
   ```
4. **Abrir en el navegador:**
   [http://localhost:8080](http://localhost:8080)

---

## Uso básico de la aplicación
1. En la página principal, escriba o pegue el texto en español que desea transcribir.
2. Presione el botón **Transcribir**.
3. El sistema mostrará el texto original y su equivalente en Braille Unicode.
4. (Opcional) Use el botón **Imprimir** para generar una copia física.

### Ejemplo
- **Entrada:** `¡Hola, mundo! 123`
- **Salida esperada:** ⠨⠓⠕⠇⠁⠂⠀⠍⠥⠝⠙⠕⠖⠀⠼⠁⠃⠉

---

## Uso avanzado (línea de comandos)
- Enviar una petición POST desde PowerShell (curl integrado):
  ```powershell
  curl -X POST -F "texto=Hola mundo 123" http://localhost:8080/transcribir
  ```
- La aplicación responde con HTML en la vista `result.html`.

---

## Formato de entrada y limitaciones
- **Entrada:** texto plano en español. Soporta letras a–z, `ñ`, `ü`, vocales acentuadas, números y puntuación básica.
- **Mayúsculas:** se marcan con un prefijo de mayúscula en Braille.
- **Números:** se agrega un signo de número al inicio de una secuencia de dígitos.
- **Caracteres no soportados:** (ej.: emojis, kanjis, algunos acentos latinos extendidos) se reemplazan por un espacio Braille en la salida.
- **Normalización:** múltiples espacios se convierten en uno solo.

### Ejemplo avanzado
- **Entrada:** `Año 2025: Acción Útil.`
- **Salida (indicativa):** cadena de caracteres Braille Unicode donde las mayúsculas y números incluyen los prefijos correspondientes.

---

## Impresión y accesibilidad
- La vista de resultados incluye un botón **Imprimir** que invoca `window.print()` para impresión directa desde el navegador.
- La salida está en Braille Unicode (U+2800 en adelante); verifique la compatibilidad de su lector o dispositivo Braille.

---

## Resolución de problemas y preguntas frecuentes (FAQ)
- **La página no carga:** Verifique que la aplicación esté en ejecución y el puerto 8080 esté libre.
- **La transcripción no aparece:** Revise los logs en la terminal donde se ejecuta la app.
- **La aplicación no arranca:**
  - Ejecute `./mvnw -v` para comprobar el wrapper de Maven y el entorno de ejecución.
  - Revise los logs en la terminal para ver excepciones y errores.
- **¿Cómo ejecutar los tests?**
  ```powershell
  ./mvnw test
  ```
- **¿Qué cubren los tests?**
  - Transcripción de texto básico
  - Manejo de mayúsculas
  - Vocales acentuadas
  - Letra ñ
  - Normalización de espacios
  - Números con signo
  - Puntuación
  - Caracteres no válidos
  - Emojis y caracteres especiales

---

## Información técnica y contribución
- **Arquitectura:** Patrón MVC (Model-View-Controller)
  - **Model:** `BrailleMapper` (lógica de conversión)
  - **View:** Plantillas Thymeleaf (`index.html`, `result.html`)
  - **Controller:** `TranscriptionController` (maneja peticiones HTTP)
- **Tecnologías:**
  - Java 25, Spring Boot 3.5.7, Maven, Thymeleaf, HTML5, CSS3, JUnit 5, AssertJ
- **Documentación adicional:**
  - [Guía de Instalación](manual-de-instalacion.md)
- **Soporte:**
  - Revise la documentación incluida
  - Contacte al equipo de desarrollo para dudas o sugerencias

---

**Fecha de actualización:** 30/01/2026
