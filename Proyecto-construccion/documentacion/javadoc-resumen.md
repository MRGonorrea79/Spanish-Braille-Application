# JavaDoc - Resumen

Generar JavaDoc:
```cmd
mvn javadoc:javadoc
```

Salida: `target/site/apidocs/`.

Descripción general
- Proyecto: transcriptor Español → Braille implementado como una aplicación Spring Boot.
- Paquete principal: `ec.epn.edu.proyectoconstruccion`.

Clases y componentes clave

- `ProyectoConstruccionApplication` (clase principal)
	- Paquete: `ec.epn.edu.proyectoconstruccion`
	- Rol: punto de entrada de la aplicación Spring Boot. Contiene `main` que arranca el contexto con `SpringApplication.run(...)`.

- `BrailleMapper` (servicio)
	- Paquete: `ec.epn.edu.proyectoconstruccion.service`
	- Rol: lógica de negocio que mapea caracteres del alfabeto español a Braille Unicode.
	- Funcionalidades principales:
		- Inicializa tablas de mapeo para letras (series a–j, k–t, u–z), signos (`ñ`, `ü`) y vocales acentuadas.
		- Soporta puntuación básica (`, ; : . ? ! - ( )`) y espacio.
		- Gestión de números: usa un signo de número (mask) seguido de las celdas correspondientes (1→a, 2→b, ...).
		- Signo de mayúscula: antepone una celda de mayúscula cuando el carácter es uppercase.
		- Normaliza múltiples espacios (`\s+`) a un único espacio antes de transcribir.
	- Métodos relevantes (documentados en JavaDoc):
		- `public BrailleMapper()` — constructor que inicializa las tablas.
		- `public String transcribir(String texto)` — devuelve la representación en Braille Unicode.
		- `public String maskToUnicode(int mask)` — convierte una máscara de puntos Braille a su carácter Unicode (U+2800 + mask).
		- utilidades privadas: `mask(int... dots)`, `addDot(int base, int dot)`, e inicializadores `initLetters()`, `initAccents()`, `initPunctuation()`, `initNumbers()`.
	- Comportamiento observado en tests:
		- Mayúsculas generan el signo de mayúscula en la transcripción.
		- Números se preceden por el signo de número una sola vez mientras la secuencia de dígitos continúa.
		- Caracteres no soportados (ej. emojis, kanjis, algunos acentos latinos extendidos) se reemplazan por un espacio Braille.

- `TranscriptionController` (controlador web)
	- Paquete: `ec.epn.edu.proyectoconstruccion.controller`
	- Rol: expone la interfaz web para recibir texto y devolver la transcripción.
	- Endpoints:
		- `GET /` → plantilla `index` (formulario para ingresar texto).
		- `POST /transcribir` → procesa el parámetro `texto` y devuelve la plantilla `result` con atributos `textoOriginal` y `resultadoBraille`.
	- Uso interno: delega la conversión a `BrailleMapper#transcribir(String)`.

Vistas y recursos
- Plantillas Thymeleaf en `src/main/resources/templates`:
	- `index.html`: formulario con `textarea name="texto"` y ejemplo prellenado `Ejemplo: ¡Hola, mundo! 123`.
	- `result.html`: muestra `textoOriginal` y el bloque con la representación Braille (`th:utext="${resultadoBraille}"`).
- Estilos en `src/main/resources/static/styles.css`.

Propiedades
- `src/main/resources/application.properties`:
	- `spring.application.name=Proyecto-construccion`

Pruebas unitarias
- Ubicación: `src/test/java/ec/epn/edu/proyectoconstruccion/service/BrailleMapperTest.java`.
- Cobertura de casos: mayúsculas, acentos, ñ, normalización de espacios, números, puntuación, manejo de caracteres inválidos (emojis/kanjis), texto largo y combinaciones complejas.
- Ejecutar tests:
```cmd
mvn test
```

Ejemplos de uso

- Uso programático (desde otra clase Java):
```java
BrailleMapper mapper = new BrailleMapper();
String braille = mapper.transcribir("Hola Mundo 123");
System.out.println(braille); // imprime caracteres Braille Unicode
```

- Uso via web (navegador):
	- Abrir `http://localhost:8080/`, pegar texto y enviar el formulario.

- Ejemplo con curl (form-data):
```powershell
curl -X POST -F "texto=Hola mundo 123" http://localhost:8080/transcribir
```
Nota: la app no incluye seguridad (CSRF) por defecto en esta entrega, por lo que la ruta de formulario se acepta tal cual en ejecución local.

Consideraciones de diseño y limitaciones
- El mapeo de vocales acentuadas usa un prefijo (mask con puntos 4 y 6) seguido del carácter; según la implementación actual, las vocales acentuadas están representadas por el prefijo (esto puede requerir refinamiento si se desea una celda compuesta por prefijo+vocal en una única celda).
- Caracteres no mapeados por la tabla devuelven un espacio Braille (comportamiento intencional según tests).
- Algunos caracteres latinos extendidos (ê, â, ô, etc.) y símbolos no latinos se consideran inválidos y se reemplazan por espacio.

Generación de JavaDoc y verificación
- Comando:
```cmd
mvn javadoc:javadoc
```
- Salida esperada: `target/site/apidocs/` con la JavaDoc HTML generada para los paquetes y clases del proyecto.

Puntos siguientes / recomendaciones
- Verificar en la JavaDoc generada que los tags `@author`, `@version` y descripciones se muestren correctamente.
- Si se desea documentar ejemplos más extensos o diagramas, incluir secciones adicionales en la carpeta `documentacion/`.
- Considerar exponer una API REST JSON si se necesita consumo por clientes sin HTML (por ejemplo, `POST /api/transcribir` que retorne JSON con `resultadoBraille`).

Resumen de archivos referenciados (ubicaciones)
- `src/main/java/ec/epn/edu/proyectoconstruccion/ProyectoConstruccionApplication.java`
- `src/main/java/ec/epn/edu/proyectoconstruccion/controller/TranscriptionController.java`
- `src/main/java/ec/epn/edu/proyectoconstruccion/service/BrailleMapper.java`
- `src/main/resources/templates/index.html`
- `src/main/resources/templates/result.html`
- `src/test/java/ec/epn/edu/proyectoconstruccion/service/BrailleMapperTest.java`

