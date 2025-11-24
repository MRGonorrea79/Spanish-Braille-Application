# Manual de usuario

## Acceder a la aplicación
Abre en tu navegador: `http://localhost:8080`

## Usar la funcionalidad de transcripción
1. En la página principal, introduce el texto que deseas transcribir.
2. Haz clic en el botón de transcribir.
3. El resultado en Braille aparecerá en la página de resultados.

### Ejemplo
- Entrada: "Ejemplo: ¡Hola, mundo! 123"
- Salida esperada: ⠨⠑⠚⠑⠍⠏⠇⠕⠒⠀ ⠨⠓⠕⠇⠁⠂⠀⠍⠥⠝⠙⠕⠖⠀⠼⠁⠃⠉

## Errores comunes y soluciones
- Si la página no carga: comprobar que la aplicación esté en ejecución y el puerto.
- Si la transcripción no aparece: revisar logs en la terminal donde se ejecuta la app.

## Instrucciones de ejecución (desarrollador/usuario)

1. Compilar y ejecutar con Maven (Windows PowerShell - recomendado):

```powershell
./mvnw clean package
./mvnw spring-boot:run
```

2. Alternativa: ejecutar el JAR empaquetado:

```powershell
java -jar target/Proyecto-construccion-0.0.1-SNAPSHOT.jar
```

3. Abrir en el navegador: `http://localhost:8080`.

## Uso avanzado (línea de comandos)

- Enviar una petición POST desde PowerShell (curl integrado):

```powershell
curl -X POST -F "texto=Hola mundo 123" http://localhost:8080/transcribir
```

- Nota: la aplicación responde con HTML en la vista `result.html`.

## Formato de entrada y limitaciones

- Entrada: texto plano en español. Soporta letras a–z, `ñ`, `ü`, vocales acentuadas, números y puntuación básica.
- Mayúsculas: se marca con un prefijo de mayúscula en Braille.
- Números: se agrega un signo de número al inicio de una secuencia de dígitos.
- Caracteres no soportados (ej.: emojis, kanjis, algunos acentos latinos extendidos) se reemplazan por un espacio Braille en la salida.

## Ejemplos

- Entrada: `Año 2025: Acción Útil.`
- Salida (indicativa): cadena de caracteres Braille Unicode donde las mayúsculas y números incluyen los prefijos correspondientes.

## Impresión y accesibilidad

- La vista de resultados incluye un botón `Imprimir` que invoca `window.print()` para impresión directa desde el navegador.
- Para usuarios con necesidad de accesibilidad, la salida está en Braille Unicode; verificar compatibilidad del lector o dispositivo Braille con caracteres U+2800 en adelante.

## Resolución de problemas

- Si la aplicación no arranca:
  - Ejecuta `./mvnw -v` para comprobar el wrapper de Maven y el entorno de ejecución.

- Revisar logs en la terminal donde se ejecuta `./mvnw spring-boot:run` para ver excepciones y errores.

- Ejecutar tests locales para validar el mapeo Braille:

```powershell
./mvnw test
```

---
Fecha de actualización: 24/11/2025
