# Análisis de Cambio - Segunda Iteración
## Sistema de Transcripción Español ↔ Braille

---

## 📋 Información General

**Proyecto**: Spanish-Braille-Application  
**Versión**: 2.0  
**Fecha de análisis**: 23 de enero de 2026  
**Equipo**: Grupo 7 - EPN  
**Iteración**: Segunda  

---

## 1. Resumen

Este documento presenta el análisis detallado de los cambios requeridos para la segunda iteración del sistema de transcripción Español-Braille. La primera iteración implementó exitosamente la transcripción unidireccional de **Español → Braille Unicode**. Esta segunda iteración amplía las funcionalidades con dos nuevos requisitos:

1. **Transcripción bidireccional**: Braille → Español (transcripción inversa)
2. **Impresión en espejo**: Generación de texto Braille en formato espejo para escritura manual

---

## 2. Análisis de Requisitos de la Segunda Iteración

### 2.1 Requisito 1: Transcripción Braille → Español

#### 2.1.1 Descripción
Permitir a los usuarios transcribir texto en Braille Unicode a su representación en español legible. Esto es el proceso inverso al implementado en la primera iteración.

#### 2.1.2 Justificación
- Facilitar la lectura de textos Braille por personas que no dominan el sistema
- Herramienta educativa para aprendizaje del Braille
- Complementar la funcionalidad existente con bidireccionalidad completa
- Permitir validación de transcripciones realizadas

#### 2.1.3 Ejemplo de Uso
**Entrada**: `⠠⠓⠕⠇⠁ ⠍⠥⠝⠙⠕`  
**Salida**: `Hola mundo`

#### 2.1.4 Cambios Técnicos Requeridos

##### A. Modelo/Servicio (Service Layer)

**Componente principal: `BrailleMapper` (MODIFICADO - Integrado)**

```java
public class BrailleMapper {
    // Usa BrailleDictionary para todos los mapeos
    private final BrailleDictionary dictionary = new BrailleDictionary();
    private final Map<String, Integer> map = dictionary.getMap();
    private final Map<Integer, String> reverseMap = dictionary.getReverseMap();
    private final Map<String, Integer> pam = dictionary.getMapEspejo();
    
    /**
     * Convierte texto español a Braille Unicode
     */
    public String españolABraille(String texto) {
        // Implementación para Español → Braille
    }
    
    /**
     * Convierte Braille Unicode a texto español
     */
    public String brailleAEspañol(String textoBraille) {
        // Implementación para Braille → Español
    }
    
    /**
     * Genera versión en espejo para escritura manual
     */
    public String españolABrailleEspejo(String texto) {
        // Usa mapa pam y luego sb.reverse()
    }
}
```

**Nuevo componente: `BrailleDictionary`**

```java
public class BrailleDictionary {
    private final Map<String, Integer> map = new HashMap<>();          // Español → Braille normal
    private final Map<Integer, String> reverseMap = new HashMap<>();   // Braille → Español
    private final Map<String, Integer> pam = new HashMap<>();          // Español → Braille espejo
    
    public BrailleDictionary() {
        initLetters();
        initAccents();
        initPunctuation();
        initNumbers();
        initReverseMap();
    }
    
    public Map<String, Integer> getMap() {
        return Collections.unmodifiableMap(map);
    }
    
    public Map<Integer, String> getReverseMap() {
        return Collections.unmodifiableMap(reverseMap);
    }
    
    public Map<String, Integer> getMapEspejo() {
        return Collections.unmodifiableMap(pam);
    }
}
```

**Funcionalidades clave**:
- **BrailleMapper integrado**: Una sola clase maneja todas las operaciones
- **BrailleDictionary centralizado**: Gestiona 3 mapeos diferentes
- Mapeo directo: `String → Integer` (Español → Braille)
- Mapeo inverso: `Integer → String` (Braille → Español)
- Mapeo espejo: `String → Integer` (pre-calculado con reflexión)
- Detección de signos especiales (mayúscula ⠠, número ⠼)
- Reconocimiento de vocales acentuadas
- Manejo de mayúsculas en palabra completa (⠠⠠)
- Normalización de salida

##### B. Controlador (Controller Layer)

**Modificaciones en `TranscriptionController`**:

```java
@Controller
public class TranscriptionController {
    private final BrailleMapper brailleMapper = new BrailleMapper();
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    /**
     * Endpoint para transcripción Español → Braille
     */
    @PostMapping("/transcribir-Español")
    public String transcribirEspañol(@RequestParam("texto") String texto, Model model) {
        model.addAttribute("textoOriginal", texto);
        model.addAttribute("resultadoBraille", brailleMapper.españolABraille(texto));
        return "result-español";
    }
    
    /**
     * Endpoint para transcripción Braille → Español
     */
    @PostMapping("/transcribir-Braille")
    public String transcribirBraille(@RequestParam("texto") String texto, Model model) {
        model.addAttribute("textoOriginal", texto);
        model.addAttribute("resultadoEspañol", brailleMapper.brailleAEspañol(texto));
        return "result-braille";
    }
    
    /**
     * Endpoint para generación de espejo
     */
    @PostMapping("/espejo")
    public String transcribirEspejo(@RequestParam("texto") String texto, Model model) {
        model.addAttribute("resultadoBraille", brailleMapper.españolABrailleEspejo(texto));
        return "result-espejo";
    }
}
```

##### C. Vista (View Layer)

**Nuevas vistas requeridas**:

1. **`index-inverso.html`** - Formulario para entrada de Braille
   - Campo de texto con fuente Braille
   - Validación de entrada (solo caracteres Unicode Braille U+2800-U+28FF)
   - Ejemplos predefinidos
   - Botón "Transcribir a Español"

2. **`result-inverso.html`** - Resultados de transcripción inversa
   - Muestra texto Braille original
   - Muestra resultado en español
   - Botones: "Nueva transcripción", "Imprimir"

**Modificación de navegación**:
- Actualizar `index.html` con enlace a transcripción inversa
- Crear menú de navegación entre ambas funcionalidades

##### D. Testing

**Extensión de clase de pruebas: `BrailleMapperTest`**

Se agregan nuevos métodos de prueba para los métodos:
- `brailleAEspañol(String)`: transcripción inversa
- `españolABrailleEspejo(String)`: generación de espejo

Casos de prueba requeridos:
```java
@Test
public void testTranscribirBrailleSimple()
@Test
public void testTranscribirConMayusculas()
@Test
public void testTranscribirConNumeros()
@Test
public void testTranscribirConAcentos()
@Test
public void testTranscribirConPuntuacion()
@Test
public void testTranscribirTextoComplejo()
@Test
public void testCaracteresInvalidos()
@Test
public void testEspaciosMultiples()
```

#### 2.1.5 Desafíos Técnicos

1. **Ambigüedad en signos**:
   - Algunos signos Braille pueden representar múltiples caracteres según contexto
   - Solución: Análisis de contexto y reglas gramaticales

2. **Signos compuestos**:
   - Vocales acentuadas = prefijo + vocal base
   - Mayúsculas = signo mayúscula + letra
   - Números = signo número + secuencia de "letras"

3. **Estado durante parsing**:
   - Necesario mantener estado: `inNumber`, `nextIsUppercase`, `expectingAccentedVowel`

#### 2.1.6 Impacto en Arquitectura

- **Complejidad**: Media
- **Cambios en capas existentes**: Controlador, Vista, Servicio (BrailleMapper extendido)
- **Nuevas clases**: `BrailleDictionary`
- **Patrón aplicado**: Repository Pattern (BrailleDictionary)
- **Dependencias**: Ninguna nueva
- **Compatibilidad**: Totalmente compatible con funcionalidad existente

---

### 2.2 Requisito 2: Impresión en Espejo para Escritura Manual

#### 2.2.1 Descripción
Generar una representación en espejo del texto Braille para permitir la escritura manual con punzón. En la escritura Braille manual, el texto se escribe de derecha a izquierda en el reverso del papel, por lo que se requiere una imagen especular.

#### 2.2.2 Justificación
- Permitir la creación de material Braille físico
- Facilitar el aprendizaje de escritura Braille manual
- Generar guías para perforación con punzón
- Cumplir con estándar de escritura Braille manual (derecha a izquierda en reverso)

#### 2.2.3 Ejemplo Visual

**Texto normal**: `⠓⠕⠇⠁`  
**Texto en espejo**: `⠁⠇⠕⠓` (orden de caracteres invertido)

Adicionalmente, cada carácter Braille individual debe reflejarse horizontalmente:
- ⠓ (puntos 1,2,5) → espejo → puntos reflejados horizontalmente

#### 2.2.4 Cambios Técnicos Requeridos

##### A. Modelo/Servicio (Service Layer)

**Extensión de `BrailleMapper`** (Método real implementado):

```java
public class BrailleMapper {
    private final BrailleDictionary dictionary = new BrailleDictionary();
    private final Map<String, Integer> pam = dictionary.getMapEspejo(); // Mapa espejo pre-calculado
    
    /**
     * Genera versión en espejo para escritura manual
     * @param texto Texto en español
     * @return Texto Braille en espejo (RTL con caracteres reflejados)
     */
    public String españolABrailleEspejo(String texto) {
        texto = texto.toLowerCase();
        StringBuilder sb = new StringBuilder();
        
        // 1. Convertir cada carácter usando el mapa espejo (pam)
        for (int i = 0; i < texto.length(); i++) {
            String key = String.valueOf(texto.charAt(i));
            
            if (pam.containsKey(key)) {
                int valor = pam.get(key);
                sb.append((char) (0x2800 + valor));
            } else if (key.equals(" ")) {
                sb.append(" ");
            }
        }
        
        // 2. Invertir el orden completo del string (RTL)
        return sb.reverse().toString();
    }
}
```

**Cómo funciona**:
1. **Mapa pam pre-calculado**: `BrailleDictionary` inicializa el mapa `pam` con caracteres ya reflejados horizontalmente
2. **Conversión directa**: Cada carácter español se convierte directamente a su equivalente espejo usando `pam`
3. **Inversión final**: Se usa `sb.reverse()` para invertir el orden de todo el string (derecha a izquierda)
4. **Resultado**: Texto Braille listo para escritura manual con punzón
```

##### B. Controlador (Controller Layer)

**Nuevo endpoint implementado**:

```java
@Controller
public class TranscriptionController {
    private final BrailleMapper brailleMapper = new BrailleMapper();
    
    /**
     * Endpoint para transcripción en espejo
     */
    @PostMapping("/espejo")
    public String transcribirEspejo(@RequestParam("texto") String texto, Model model) {
        model.addAttribute("resultadoBraille", brailleMapper.españolABrailleEspejo(texto));
        return "result-espejo";
    }
}
```

##### C. Vista (View Layer)

**Vistas implementadas**:

1. **`index.html`** (MODIFICADA): Formulario unificado con opciones para 3 tipos de transcripción
2. **`result-español.html`**: Muestra resultado de Español → Braille
3. **`result-braille.html`**: Muestra resultado de Braille → Español
4. **`result-espejo.html`**: Muestra resultado en espejo con:
   - Fuente Braille especial
   - Dirección RTL (right-to-left) aplicada con CSS
  - Indicadores visuales de orientación
  - Guías de perforación opcionales

```html
<div class="braille-espejo" style="direction: rtl; font-family: 'Braille';">
    <p class="etiqueta">Para escritura manual (espejo):</p>
    <p th:utext="${brailleEspejo}" class="texto-braille-espejo"></p>
    <p class="instrucciones">
        ⚠️ Este texto está en espejo. Escribe de derecha a izquierda 
        en el reverso del papel.
    </p>
</div>
```

**CSS adicional**:
```css
.braille-espejo {
    background-color: #f0f0f0;
    border: 2px dashed #666;
    padding: 20px;
    direction: rtl;
    unicode-bidi: bidi-override;
}

.texto-braille-espejo {
    font-size: 24pt;
    letter-spacing: 0.5em;
    line-height: 2em;
}
```

**Modificación en `index.html`**:
- Agregar checkbox "Generar versión en espejo para escritura manual"
- Rutear a `/transcribir-espejo` si está marcado

##### D. Testing

**Extensión de `BrailleMapperTest`**:

```java
@Test
public void testTranscribirEnEspejoSimple() {
    String resultado = mapper.transcribirEnEspejo("abc");
    // Verificar inversión de orden y reflexión de puntos
}

@Test
public void testReflexionMascaraHorizontal() {
    // Verificar que puntos 1↔4, 2↔5, 3↔6 se intercambian
}

@Test
public void testEspejoConMayusculas() {
    // El signo de mayúscula también debe reflejarse
}

@Test
public void testEspejoConNumeros() {
    // El signo de número también debe reflejarse
}

@Test
public void testEspejoTextoCompleto() {
    String normal = mapper.transcribir("Hola 123");
    String espejo = mapper.transcribirEnEspejo("Hola 123");
    assertNotEquals(normal, espejo);
    // Verificar que aplicar espejo dos veces devuelve el original
}
```

#### 2.2.5 Desafíos Técnicos

1. **Reflexión de puntos Braille**:
   - Cada celda Braille (6 puntos) debe reflejarse horizontalmente
   - Intercambio de columnas: (1,2,3) ↔ (4,5,6)

2. **Orden RTL (Right-to-Left)**:
   - CSS: `direction: rtl`
   - Inversión de string: `StringBuilder.reverse()`

3. **Visualización en navegador**:
   - Asegurar que fuente Braille soporte orientación RTL
   - Compatibilidad entre navegadores

4. **Impresión**:
   - CSS de impresión debe mantener formato espejo
   - Márgenes y orientación de página

#### 2.2.6 Impacto en Arquitectura

- **Complejidad**: Baja-Media
- **Cambios en capas existentes**: Servicio (extensión), Controlador, Vista
- **Nuevas clases**: Ninguna (extensión de `BrailleMapper`)
- **Patrón aplicado**: Template Method (variante del método `transcribir`)
- **Dependencias**: Ninguna nueva
- **Compatibilidad**: Totalmente compatible

---

## 3. Elementos de Configuración a Actualizar

### 3.1 `pom.xml`
**Estado actual**: No requiere cambios  
**Justificación**: No se agregan nuevas dependencias externas

### 3.2 `application.properties`
**Cambios requeridos**:
```properties
# Configuración existente
spring.application.name=Proyecto-construccion

# NUEVO: Configuración para segunda iteración
# Habilitar transcripción bidireccional
app.features.transcripcion-inversa=true

# Habilitar impresión en espejo
app.features.impresion-espejo=true

# Fuente Braille para impresión
app.braille.font-family=DejaVu Sans, Braille

# Validación de entrada Braille (Unicode range)
app.braille.unicode-range-start=0x2800
app.braille.unicode-range-end=0x28FF
```

### 3.3 `static/styles.css`
**Nuevas clases CSS**:
```css
/* Estilos para transcripción inversa */
.formulario-inverso {
    /* ... */
}

/* Estilos para visualización en espejo */
.braille-espejo {
    direction: rtl;
    /* ... */
}

/* Estilos de impresión */
@media print {
    .braille-espejo {
        /* Mantener RTL en impresión */
    }
}
```

### 3.4 Estructura de Directorios
**Nuevos archivos/directorios**:
```
src/
├── main/
│   ├── java/.../
│   │   ├── service/
│   │   │   ├── BrailleMapper.java (MODIFICADO)
│   │   │   └── BrailleDictionary.java (NUEVO)
│   │   └── controller/
│   │       └── TranscriptionController.java (MODIFICADO)
│   └── resources/
│       ├── static/
│       │   └── styles.css (MODIFICADO)
│       └── templates/
│           ├── index.html (MODIFICADO)
│           ├── index-inverso.html (NUEVO)
│           ├── result-inverso.html (NUEVO)
│           └── result-espejo.html (NUEVO)
└── test/
    └── java/.../service/
        ├── BrailleMapperTest.java (MODIFICADO)
        └── BrailleMapperTest.java (MODIFICADO con nuevos tests)
```

---

## 4. Documentación a Actualizar

### 4.1 README.md
**Cambios requeridos**:
- Actualizar características principales (bidireccional + espejo)
- Agregar ejemplos de uso de nuevas funcionalidades
- Actualizar capturas de pantalla
- Incrementar versión a 2.0

### 4.2 Manuales de Usuario
**Archivos afectados**:
- `manual-usuario.md`
- `manual-de-instalacion.md`

**Secciones nuevas**:
1. Cómo usar transcripción Braille → Español
2. Cómo generar impresión en espejo
3. Guía de escritura manual con punzón
4. Interpretación de resultados en espejo

### 4.3 Documentación Técnica
**Archivos afectados**:
- `diseno-arquitectonico.md`
- `diagramas-complementarios.md`
- `javadoc-resumen.md`

**Actualizaciones**:
- Diagramas de clases (nueva clase `BrailleDictionary`, métodos extendidos en `BrailleMapper`)
- Diagramas de secuencia (nuevos flujos)
- Diagramas de componentes actualizados
- JavaDoc de métodos nuevos

### 4.4 Diagramas PlantUML
**Archivos a actualizar**:
1. `01-componentes-dependencias.puml` - Agregar `BrailleDictionary`, actualizar `BrailleMapper`
2. `02-flujo-datos-braillemapper.puml` - Agregar flujo inverso y espejo
3. `06-clase-detallado.puml` - Nueva clase y relaciones
4. **NUEVO**: `07-flujo-transcripcion-inversa.puml`
5. **NUEVO**: `08-flujo-generacion-espejo.puml`

### 4.5 Casos de Prueba
**Archivo**: `casos-prueba.md`

**Nuevas secciones**:
- TC-10 a TC-15: Casos de transcripción inversa
- TC-16 a TC-20: Casos de generación espejo
- Matriz de trazabilidad requisitos-pruebas

---

## 5. Análisis de Impacto

### 5.1 Impacto en Código Existente

| Componente | Tipo de Cambio | Complejidad | Riesgo |
|-----------|----------------|-------------|--------|
| `BrailleMapper` | Extensión (nuevos métodos) | Baja | Bajo |
| `TranscriptionController` | Extensión (nuevos endpoints) | Baja | Bajo |
| Templates HTML | Nuevos archivos + modificaciones | Media | Bajo |
| CSS | Nuevas clases + modificaciones | Baja | Bajo |
| Tests | Nuevos archivos de test | Media | Bajo |

**Conclusión**: Los cambios son principalmente **aditivos**, con mínima modificación de código existente.

### 5.2 Compatibilidad con Versión Anterior

✅ **Totalmente compatible**
- Funcionalidad existente (Español → Braille) no se modifica
- Nuevas funcionalidades son opcionales
- Sin cambios en API existente
- Sin cambios en dependencias

### 5.3 Impacto en Rendimiento

**Transcripción inversa**:
- Complejidad: O(n) con n = longitud del texto
- Overhead: Mínimo (lookup en HashMap)
- Sin impacto significativo

**Generación espejo**:
- Complejidad: O(n) adicional
- Operaciones bit a bit (muy rápidas)
- Sin impacto significativo

### 5.4 Impacto en Testing

**Nuevas pruebas requeridas**: ~15-20 nuevos casos de test
**Cobertura objetivo**: >85%
**Tiempo estimado de testing**: 4-6 horas

---

## 6. Plan de Implementación Recomendado

### Fase 1: Transcripción Inversa (Prioridad Alta)
1. Crear `BrailleDictionary` con 3 mapeos (map, reverseMap, pam)
2. Agregar endpoint `/transcribir-inverso` en controlador
3. Crear vistas `index-inverso.html` y `result-inverso.html`
4. Implementar casos de prueba
5. Actualizar documentación

**Estimación**: 8-12 horas de desarrollo

### Fase 2: Impresión en Espejo (Prioridad Alta)
1. Implementar métodos de reflexión en `BrailleMapper`
2. Agregar endpoint `/transcribir-espejo`
3. Crear vista `result-espejo.html` con CSS RTL
4. Implementar casos de prueba
5. Validar impresión física

**Estimación**: 6-8 horas de desarrollo

### Fase 3: Integración y Pulido (Prioridad Media)
1. Unificar navegación entre funcionalidades
2. Mejorar UI/UX
3. Agregar validaciones
4. Optimizar rendimiento si necesario

**Estimación**: 4-6 horas

### Fase 4: Documentación (Prioridad Alta)
1. Actualizar todos los manuales
2. Generar nuevos diagramas
3. Actualizar README
4. Generar JavaDoc completo

**Estimación**: 6-8 horas

**Estimación total**: 24-34 horas de trabajo

---

## 7. Riesgos Identificados

| Riesgo | Probabilidad | Impacto | Mitigación |
|--------|-------------|---------|-----------|
| Ambigüedad en transcripción inversa | Media | Alto | Implementar análisis de contexto y reglas claras |
| Problemas de visualización RTL en navegadores | Baja | Medio | Pruebas en múltiples navegadores, CSS fallback |
| Reflexión incorrecta de puntos Braille | Baja | Alto | Testing exhaustivo con casos conocidos |
| Rendimiento con textos largos | Baja | Bajo | Optimización si es necesario, usar StringBuilder |

---

## 8. Criterios de Aceptación

### 8.1 Transcripción Inversa
- ✅ Transcribe correctamente letras simples (a-z, ñ, ü)
- ✅ Reconoce y procesa mayúsculas con signo ⠠
- ✅ Reconoce y procesa números con signo ⠼
- ✅ Maneja vocales acentuadas correctamente
- ✅ Procesa puntuación básica
- ✅ Maneja textos largos sin errores
- ✅ Valida entrada (solo Unicode Braille válido)
- ✅ Pasa todos los casos de prueba

### 8.2 Impresión en Espejo
- ✅ Invierte correctamente el orden de caracteres (RTL)
- ✅ Refleja horizontalmente cada celda Braille
- ✅ Visualiza correctamente en navegador
- ✅ Imprime correctamente en papel
- ✅ Incluye instrucciones claras de uso
- ✅ Mantiene formato en diferentes tamaños de papel
- ✅ Pasa todos los casos de prueba

---

## 9. Conclusiones

La segunda iteración del proyecto agrega funcionalidades valiosas que complementan perfectamente la implementación inicial:

1. **Transcripción bidireccional completa**: El sistema ahora puede traducir en ambas direcciones (Español ↔ Braille), convirtiéndolo en una herramienta más versátil y educativa.

2. **Soporte para escritura manual**: La funcionalidad de impresión en espejo permite crear material Braille físico, ampliando el alcance del sistema más allá de lo digital.

3. **Arquitectura extensible mantenida**: Los cambios se integran naturalmente en la arquitectura MVC existente sin comprometer la calidad del código.

4. **Impacto mínimo en sistema existente**: Las nuevas funcionalidades son aditivas, manteniendo completa compatibilidad con la versión anterior.

---

## 10. Referencias

- Sistema Braille Español (Especificación oficial)
- Unicode Braille Patterns: U+2800 - U+28FF
- Documentación Spring Boot 3.x
- Estándares de escritura Braille manual

---

**Documento elaborado por**: Grupo 7 - EPN  
**Revisado por**: [Pendiente]  
**Aprobado por**: [Pendiente]  
**Última actualización**: 28 de enero de 2026  
**Versión del documento**: 1.0
