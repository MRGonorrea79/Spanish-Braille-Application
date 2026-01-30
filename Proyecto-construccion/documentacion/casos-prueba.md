# Casos de Prueba

## Cómo ejecutar los tests
```cmd
mvn test
```

Los resultados se generan en `target/surefire-reports/`.

---

## Plantilla de caso de prueba

Cada caso de prueba debe especificar **explícitamente el resultado esperado**, no solo describirlo:

| ID | Descripción | Entrada | Resultado Esperado | Resultado Obtenido | Estado | Observaciones |
|----|-------------|---------|-------------------|------------------|--------|---------------|

### Estructura de cada caso:

**ID del caso:** TCxx (Ej: TC-01)
- **Descripción:** Breve descripción del objetivo de la prueba
- **Pasos:** Lista numerada de acciones a realizar
- **Entrada:** Dato(s) exacto(s) que se envían al sistema
- **Resultado Esperado:** Valor específico y verificable que debe retornar (NO solo una descripción)
- **Resultado Obtenido:** Lo que el sistema realmente retorna
- **Estado:** ✓ Pasado / ✗ Fallido / ⊘ Pendiente
- **Observaciones:** Notas adicionales si aplica

---

---

## CASOS DE PRUEBA ESPAÑOL → BRAILLE

### TC-01: Transcribir texto básico en español
- **Descripción:** Verificar que un texto simple se transcribe correctamente a Braille
- **Entrada:** `"hola mundo"`
- **Resultado Esperado:** `⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕`
- **Resultado Obtenido:** `⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕`
- **Estado:** ✓ Pasado
- **Observaciones:** El espacio en el medio se traduce a `⠀`

### TC-02: Transcribir mayúsculas
- **Descripción:** Verificar que las letras mayúsculas incluyen el signo de mayúscula antes de cada letra
- **Entrada:** `"HOLA"`
- **Resultado Esperado:** `⠨⠨⠓⠕⠇⠁`
- **Resultado Obtenido:** `⠨⠨⠓⠕⠇⠁`
- **Estado:** ✓ Pasado
- **Observaciones:** Signo de mayúscula `⠨` precede a cada letra mayúscula consecutiva

### TC-03: Transcribir vocales acentuadas
- **Descripción:** Verificar que las vocales con acento se convierten a sus equivalentes Braille
- **Entrada:** `"áéíóú"`
- **Resultado Esperado:** `⠷⠮⠌⠬⠾`
- **Resultado Obtenido:** `⠷⠮⠌⠬⠾`
- **Estado:** ✓ Pasado
- **Observaciones:** Cada vocal acentuada tiene su propio símbolo Braille específico

### TC-04: Transcribir la letra ñ
- **Descripción:** Verificar que la letra "ñ" se convierte correctamente a su símbolo Braille
- **Entrada:** `"año"`
- **Resultado Esperado:** `⠁⠻⠕`
- **Resultado Obtenido:** `⠁⠻⠕`
- **Estado:** ✓ Pasado
- **Observaciones:** La ñ se representa como `⠻`

### TC-05: Normalizar múltiples espacios
- **Descripción:** Verificar que múltiples espacios consecutivos se reduce a uno solo
- **Entrada:** `"hola     mundo"` (5 espacios entre palabras)
- **Resultado Esperado:** `⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕`
- **Resultado Obtenido:** `⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕`
- **Estado:** ✓ Pasado
- **Observaciones:** Se normaliza a un único espacio Braille `⠀`

### TC-06: Transcribir números
- **Descripción:** Verificar que los números se antepone el signo de número y se convierten cada dígito
- **Entrada:** `"123"`
- **Resultado Esperado:** `⠼⠁⠃⠉`
- **Resultado Obtenido:** `⠼⠁⠃⠉`
- **Estado:** ✓ Pasado
- **Observaciones:** `⠼` es el prefijo de número; 1=⠁, 2=⠃, 3=⠉

### TC-07: Transcribir signos de puntuación
- **Descripción:** Verificar que comas, puntos y otros signos se convierten correctamente
- **Entrada:** `"hola, mundo."`
- **Resultado Esperado:** `⠓⠕⠇⠁⠂⠀⠍⠥⠝⠙⠕⠄`
- **Resultado Obtenido:** `⠓⠕⠇⠁⠂⠀⠍⠥⠝⠙⠕⠄`
- **Estado:** ✓ Pasado
- **Observaciones:** coma=⠂, punto=⠄

### TC-08: Caracteres no soportados se reemplazan por espacio
- **Descripción:** Verificar que caracteres como @ se convierten en espacio
- **Entrada:** `"hola @ mundo"`
- **Resultado Esperado:** `⠓⠕⠇⠁⠀ ⠀⠍⠥⠝⠙⠕`
- **Resultado Obtenido:** `⠓⠕⠇⠁⠀ ⠀⠍⠥⠝⠙⠕`
- **Estado:** ✓ Pasado
- **Observaciones:** El símbolo @ se reemplaza por un espacio regular

### TC-09: Emojis se reemplazan por espacio
- **Descripción:** Verificar que los emojis no soportados se convierten en espacio
- **Entrada:** `"hola 😀 mundo"`
- **Resultado Esperado:** `⠓⠕⠇⠁⠀⠨⠨  ⠀⠍⠥⠝⠙⠕`
- **Resultado Obtenido:** `⠓⠕⠇⠁⠀⠨⠨  ⠀⠍⠥⠝⠙⠕`
- **Estado:** ✓ Pasado
- **Observaciones:** El emoji se reemplaza, generando espacios en la salida

### TC-10: Kanjis (caracteres chinos) se reemplazan por espacio
- **Descripción:** Verificar que caracteres orientales como kanjis se convierten en espacio
- **Entrada:** `"hola 漢"`
- **Resultado Esperado:** `⠓⠕⠇⠁⠀ `
- **Resultado Obtenido:** `⠓⠕⠇⠁⠀ `
- **Estado:** ✓ Pasado
- **Observaciones:** El kanji se reemplaza por espacio

### TC-11: Texto largo completamente válido
- **Descripción:** Verificar que un texto extenso con múltiples características se transcribe correctamente
- **Entrada:** `"La rápida acción del zorro marrón sorprende al niño que veía televisión."`
- **Resultado Esperado:** `⠨⠇⠁⠀⠗⠷⠏⠊⠙⠁⠀⠁⠉⠉⠊⠬⠝⠀⠙⠑⠇⠀⠵⠕⠗⠗⠕⠀⠍⠁⠗⠗⠬⠝⠀⠎⠕⠗⠏⠗⠑⠝⠙⠑⠀⠁⠇⠀⠝⠊⠻⠕⠀⠟⠥⠑⠀⠧⠑⠌⠁⠀⠞⠑⠇⠑⠧⠊⠎⠊⠬⠝⠄`
- **Resultado Obtenido:** `⠨⠇⠁⠀⠗⠷⠏⠊⠙⠁⠀⠁⠉⠉⠊⠬⠝⠀⠙⠑⠇⠀⠵⠕⠗⠗⠕⠀⠍⠁⠗⠗⠬⠝⠀⠎⠕⠗⠏⠗⠑⠝⠙⠑⠀⠁⠇⠀⠝⠊⠻⠕⠀⠟⠥⠑⠀⠧⠑⠌⠁⠀⠞⠑⠇⠑⠧⠊⠎⠊⠬⠝⠄`
- **Estado:** ✓ Pasado
- **Observaciones:** Contiene mayúsculas, acentos, ñ y punto final

### TC-12: Solo espacios se normaliza a un espacio
- **Descripción:** Verificar que múltiples espacios sin texto se normalizan a un único espacio
- **Entrada:** `"      "` (seis espacios)
- **Resultado Esperado:** `⠀`
- **Resultado Obtenido:** `⠀`
- **Estado:** ✓ Pasado
- **Observaciones:** Se reduce a un único espacio Braille

### TC-13: Mezcla compleja (mayúsculas, números, acentos, ñ)
- **Descripción:** Verificar el manejo correcto de una mezcla de caracteres especiales
- **Entrada:** `"Año 2025: Acción Útil."`
- **Resultado Esperado:** `⠨⠁⠻⠕⠀⠼⠃⠚⠃⠑⠒⠀⠨⠁⠉⠉⠊⠬⠝⠀⠨⠾⠞⠊⠇⠄`
- **Resultado Obtenido:** `⠨⠁⠻⠕⠀⠼⠃⠚⠃⠑⠒⠀⠨⠁⠉⠉⠊⠬⠝⠀⠨⠾⠞⠊⠇⠄`
- **Estado:** ✓ Pasado
- **Observaciones:** Combina mayúsculas al inicio, ñ, números con prefijo, acentos y puntuación

### TC-14: Caracteres latinos no españoles (ê, â, ô)
- **Descripción:** Verificar que caracteres latinos no españoles se reemplazan por espacio
- **Entrada:** `"Galletas ângulo"`
- **Resultado Esperado:** `⠨⠛⠁⠇⠇⠑⠞⠁⠎⠀ ⠝⠛⠥⠇⠕`
- **Resultado Obtenido:** `⠨⠛⠁⠇⠇⠑⠞⠁⠎⠀ ⠝⠛⠥⠇⠕`
- **Estado:** ✓ Pasado
- **Observaciones:** La letra "â" no soportada se reemplaza por espacio

---

## CASOS DE PRUEBA BRAILLE → ESPAÑOL (Transcripción Inversa)

### TC-15: Transcribir Braille básico a español
- **Descripción:** Verificar que un texto Braille simple se convierte a español correctamente
- **Entrada:** `⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕`
- **Resultado Esperado:** `"hola mundo"`
- **Resultado Obtenido:** `"hola mundo"`
- **Estado:** ✓ Pasado
- **Observaciones:**

### TC-16: Convertir mayúsculas desde Braille
- **Descripción:** Verificar que el signo de mayúscula en Braille se convierte a letras mayúsculas
- **Entrada:** `⠨⠨⠓⠕⠇⠁`
- **Resultado Esperado:** `"HOLA"`
- **Resultado Obtenido:** `"HOLA"`
- **Estado:** ✓ Pasado
- **Observaciones:**

### TC-17: Convertir vocales acentuadas desde Braille
- **Descripción:** Verificar que los símbolos Braille de acentos se convierten correctamente
- **Entrada:** `⠷⠮⠌⠬⠾`
- **Resultado Esperado:** `"áéíóú"`
- **Resultado Obtenido:** `"áéíóú"`
- **Estado:** ✓ Pasado
- **Observaciones:**

### TC-18: Convertir ñ desde Braille
- **Descripción:** Verificar que el símbolo Braille de ñ se convierte correctamente
- **Entrada:** `⠁⠻⠕`
- **Resultado Esperado:** `"año"`
- **Resultado Obtenido:** `"año"`
- **Estado:** ✓ Pasado
- **Observaciones:**

### TC-19: Convertir espacios desde Braille
- **Descripción:** Verificar que los espacios Braille se convierten a espacios normales
- **Entrada:** `⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕`
- **Resultado Esperado:** `"hola mundo"`
- **Resultado Obtenido:** `"hola mundo"`
- **Estado:** ✓ Pasado
- **Observaciones:**

### TC-20: Convertir números desde Braille
- **Descripción:** Verificar que números en Braille con prefijo se convierten correctamente
- **Entrada:** `⠼⠁⠃⠉`
- **Resultado Esperado:** `"123"`
- **Resultado Obtenido:** `"123"`
- **Estado:** ✓ Pasado
- **Observaciones:**

### TC-21: Convertir puntuación desde Braille
- **Descripción:** Verificar que comas y puntos en Braille se convierten a español
- **Entrada:** `⠓⠕⠇⠁⠂⠀⠍⠥⠝⠙⠕⠄`
- **Resultado Esperado:** `"hola, mundo."`
- **Resultado Obtenido:** `"hola, mundo."`
- **Estado:** ✓ Pasado
- **Observaciones:**

### TC-22: Espacios por caracteres inválidos en Braille
- **Descripción:** Verificar que espacios en Braille se convierten a espacios normales
- **Entrada:** `⠓⠕⠇⠁⠀ ⠍⠥⠝⠙⠕`
- **Resultado Esperado:** `"hola  mundo"`
- **Resultado Obtenido:** `"hola  mundo"`
- **Estado:** ✓ Pasado
- **Observaciones:**

### TC-23: Texto largo en Braille
- **Descripción:** Verificar que texto extenso en Braille se convierte correctamente
- **Entrada:** `⠨⠇⠁⠀⠗⠷⠏⠊⠙⠁⠀⠁⠉⠉⠊⠬⠝⠀⠙⠑⠇⠀⠵⠕⠗⠗⠕⠀⠍⠁⠗⠗⠬⠝⠀⠎⠕⠗⠏⠗⠑⠝⠙⠑⠀⠁⠇⠀⠝⠊⠻⠕⠀⠟⠥⠑⠀⠧⠑⠌⠁⠀⠞⠑⠇⠑⠧⠊⠎⠊⠬⠝⠄`
- **Resultado Esperado:** `"La rápida acción del zorro marrón sorprende al niño que veía televisión."`
- **Resultado Obtenido:** `"La rápida acción del zorro marrón sorprende al niño que veía televisión."`
- **Estado:** ✓ Pasado
- **Observaciones:**

### TC-24: Solo espacio Braille
- **Descripción:** Verificar que un único espacio Braille se convierte a espacio español
- **Entrada:** `⠀`
- **Resultado Esperado:** `" "`
- **Resultado Obtenido:** `" "`
- **Estado:** ✓ Pasado
- **Observaciones:**

### TC-25: Mezcla compleja inversa
- **Descripción:** Verificar la conversión correcta de Braille complejo a español
- **Entrada:** `⠨⠁⠻⠕⠀⠼⠃⠚⠃⠑⠒⠀⠨⠁⠉⠉⠊⠬⠝⠀⠨⠾⠞⠊⠇⠄`
- **Resultado Esperado:** `"Año 2025: Acción Útil."`
- **Resultado Obtenido:** `"Año 2025: Acción Útil."`
- **Estado:** ✓ Pasado
- **Observaciones:**

### TC-26: Caracteres latinos no españoles en Braille
- **Descripción:** Verificar espacios en Braille se convierten correctamente
- **Entrada:** `⠨⠛⠁⠇⠇⠑⠞⠁⠎⠀ ⠝⠛⠥⠇⠕`
- **Resultado Esperado:** `"Galletas  ngulo"`
- **Resultado Obtenido:** `"Galletas  ngulo"`
- **Estado:** ✓ Pasado
- **Observaciones:**

## Ejecución de pruebas unitarias

Para ejecutar todos los tests:
```cmd
mvn test
```

Los resultados se generan en `target/surefire-reports/`.

### Resultados de la última ejecución (29/01/2026 - Rama ITERACION-2)

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running ec.epn.edu.proyectoconstruccion.ProyectoConstruccionApplicationTests
[INFO] Running ec.epn.edu.proyectoconstruccion.service.BrailleMapperTest
[INFO] 
[INFO] Results:
[INFO]
[INFO] Tests run: 27, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  21.754 s
[INFO] Finished at: 2026-01-29T20:55:43-05:00
```

**Resumen:**
- ✓ **26 tests de BrailleMapperTest**: TODOS PASADOS
- ✓ **1 test de ProyectoConstruccionApplicationTests**: PASADO
- **Total: 27/27 tests exitosos**
- **Tasa de éxito: 100%**
