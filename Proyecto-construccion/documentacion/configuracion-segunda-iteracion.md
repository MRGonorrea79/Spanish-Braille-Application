# Resumen de Elementos de Configuración - Segunda Iteración
## Sistema de Transcripción Español ↔ Braille

---

## 📋 Información del Documento

**Proyecto**: Spanish-Braille-Application  
**Versión**: 2.0  
**Fecha**: 23 de enero de 2026  
**Equipo**: Grupo 7 - EPN  
**Iteración**: Segunda  

---

## 1. Introducción

Este documento consolida todos los cambios necesarios en los elementos de configuración del sistema para implementar las nuevas funcionalidades de la segunda iteración:

1. ✅ Transcripción bidireccional (Braille → Español)
2. ✅ Impresión en espejo para escritura manual

---

## 2. Cambios en Archivos de Configuración

### 2.1 `application.properties`

**Ubicación**: `src/main/resources/application.properties`

**Cambios requeridos**:

```properties
# ============================================
# CONFIGURACIÓN EXISTENTE (mantener)
# ============================================
spring.application.name=Proyecto-construccion

# ============================================
# NUEVA CONFIGURACIÓN - SEGUNDA ITERACIÓN
# ============================================

# --- Funcionalidades habilitadas ---
# Habilitar transcripción inversa (Braille → Español)
app.features.transcripcion-inversa=true

# Habilitar impresión en espejo
app.features.impresion-espejo=true

# --- Configuración de Braille ---
# Fuente predeterminada para visualización Braille
app.braille.font-family=DejaVu Sans, Braille, monospace

# Rango Unicode válido para entrada Braille
app.braille.unicode-range-start=0x2800
app.braille.unicode-range-end=0x28FF

# Tamaño de fuente para impresión (en puntos)
app.braille.print-font-size=24pt

# Espaciado entre caracteres Braille (en em)
app.braille.letter-spacing=0.5em

# Altura de línea para impresión
app.braille.line-height=2em

# --- Validación de entrada ---
# Longitud máxima de texto para transcripción
app.validation.max-text-length=10000

# Permitir caracteres no soportados (se reemplazan por espacio)
app.validation.allow-unsupported-chars=true

# --- Configuración de impresión en espejo ---
# Color de fondo para visualización de espejo
app.espejo.background-color=#f0f0f0

# Color de borde para área de espejo
app.espejo.border-color=#666666

# Mostrar instrucciones de uso en impresión
app.espejo.show-instructions=true
```

**Justificación de cada propiedad**:

| Propiedad | Propósito | Valor por defecto |
|-----------|-----------|-------------------|
| `app.features.transcripcion-inversa` | Feature toggle para funcionalidad inversa | `true` |
| `app.features.impresion-espejo` | Feature toggle para impresión espejo | `true` |
| `app.braille.font-family` | Asegurar correcta visualización Unicode | `DejaVu Sans, Braille, monospace` |
| `app.braille.unicode-range-*` | Validación de entrada | `0x2800` - `0x28FF` |
| `app.validation.max-text-length` | Prevenir sobrecarga del servidor | `10000` caracteres |

---

### 2.2 `pom.xml`

**Ubicación**: `Proyecto-construccion/pom.xml`

**Estado**: ✅ **No requiere cambios**

**Justificación**: 
- No se agregan nuevas dependencias externas
- Versiones de Spring Boot, Java y Maven se mantienen
- Tests utilizan JUnit existente

**Verificación recomendada**:
```xml
<!-- Verificar que estas dependencias existentes están presentes -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

---

## 3. Cambios en Archivos CSS

### 3.1 `styles.css`

**Ubicación**: `src/main/resources/static/styles.css`

**Nuevas secciones a agregar**:

```css
/* ============================================
   ESTILOS PARA TRANSCRIPCIÓN INVERSA
   ============================================ */

.formulario-inverso {
    background-color: #e8f4f8;
    border: 2px solid #0066cc;
    border-radius: 8px;
    padding: 20px;
    margin: 20px 0;
}

.formulario-inverso label {
    font-weight: bold;
    color: #0066cc;
}

.entrada-braille {
    font-family: 'DejaVu Sans', 'Braille', monospace;
    font-size: 18pt;
    line-height: 2;
    letter-spacing: 0.3em;
    min-height: 150px;
    width: 100%;
    padding: 15px;
    border: 2px solid #0066cc;
    border-radius: 4px;
    direction: ltr; /* Entrada normal LTR */
}

.resultado-espanol {
    background-color: #fff;
    border: 2px solid #28a745;
    border-radius: 8px;
    padding: 20px;
    font-size: 14pt;
    line-height: 1.6;
}

/* ============================================
   ESTILOS PARA IMPRESIÓN EN ESPEJO
   ============================================ */

.braille-espejo {
    background-color: #f0f0f0;
    border: 2px dashed #666;
    border-radius: 8px;
    padding: 20px;
    margin: 20px 0;
    direction: rtl; /* Right-to-Left para espejo */
    unicode-bidi: bidi-override;
}

.texto-braille-espejo {
    font-family: 'DejaVu Sans', 'Braille', monospace;
    font-size: 24pt;
    letter-spacing: 0.5em;
    line-height: 2em;
    color: #000;
    text-align: right; /* Alineación derecha para RTL */
}

.etiqueta-espejo {
    font-weight: bold;
    color: #d9534f;
    margin-bottom: 10px;
    direction: ltr; /* Etiqueta en dirección normal */
}

.instrucciones-espejo {
    background-color: #fff3cd;
    border-left: 4px solid #ffc107;
    padding: 15px;
    margin-top: 20px;
    direction: ltr; /* Instrucciones en dirección normal */
    font-size: 12pt;
    line-height: 1.5;
}

.instrucciones-espejo::before {
    content: "⚠️ ";
    font-size: 16pt;
}

/* ============================================
   COMPARACIÓN LADO A LADO
   ============================================ */

.comparacion-braille {
    display: flex;
    gap: 20px;
    margin: 20px 0;
}

.comparacion-braille > div {
    flex: 1;
    padding: 15px;
    border-radius: 8px;
}

.braille-normal {
    background-color: #d4edda;
    border: 2px solid #28a745;
}

.braille-normal .texto-braille {
    direction: ltr;
    text-align: left;
}

/* ============================================
   ESTILOS DE IMPRESIÓN
   ============================================ */

@media print {
    /* Ocultar elementos no necesarios en impresión */
    .navbar, .btn, .footer, .instrucciones-espejo {
        display: none !important;
    }
    
    /* Mantener dirección RTL en impresión */
    .braille-espejo {
        direction: rtl;
        background-color: white;
        border: 2px solid #000;
        page-break-inside: avoid;
    }
    
    /* Optimizar tamaño de fuente para impresión */
    .texto-braille-espejo {
        font-size: 20pt;
        letter-spacing: 0.4em;
        color: #000;
    }
    
    /* Asegurar que braille normal también imprima correctamente */
    .texto-braille {
        font-size: 18pt;
        color: #000;
    }
    
    /* Márgenes de página */
    @page {
        margin: 2cm;
    }
}

/* ============================================
   ESTILOS PARA NAVEGACIÓN ENTRE FUNCIONALIDADES
   ============================================ */

.menu-funcionalidades {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin: 30px 0;
    flex-wrap: wrap;
}

.opcion-funcionalidad {
    background-color: #fff;
    border: 2px solid #0066cc;
    border-radius: 8px;
    padding: 20px;
    text-align: center;
    min-width: 250px;
    cursor: pointer;
    transition: all 0.3s ease;
}

.opcion-funcionalidad:hover {
    background-color: #0066cc;
    color: white;
    transform: translateY(-5px);
    box-shadow: 0 4px 8px rgba(0,0,0,0.2);
}

.opcion-funcionalidad h3 {
    margin: 0 0 10px 0;
    font-size: 18pt;
}

.opcion-funcionalidad p {
    margin: 0;
    font-size: 12pt;
}

/* ============================================
   ESTILOS PARA VALIDACIÓN DE ENTRADA
   ============================================ */

.entrada-invalida {
    border-color: #dc3545 !important;
    background-color: #f8d7da;
}

.mensaje-error {
    color: #dc3545;
    font-size: 12pt;
    margin-top: 10px;
    padding: 10px;
    background-color: #f8d7da;
    border-radius: 4px;
    border-left: 4px solid #dc3545;
}

.mensaje-exito {
    color: #28a745;
    font-size: 12pt;
    margin-top: 10px;
    padding: 10px;
    background-color: #d4edda;
    border-radius: 4px;
    border-left: 4px solid #28a745;
}

/* ============================================
   RESPONSIVE DESIGN
   ============================================ */

@media (max-width: 768px) {
    .comparacion-braille {
        flex-direction: column;
    }
    
    .menu-funcionalidades {
        flex-direction: column;
        align-items: stretch;
    }
    
    .opcion-funcionalidad {
        min-width: 100%;
    }
    
    .texto-braille-espejo {
        font-size: 18pt;
        letter-spacing: 0.3em;
    }
}

/* ============================================
   MODO OSCURO (OPCIONAL)
   ============================================ */

@media (prefers-color-scheme: dark) {
    .formulario-inverso {
        background-color: #1e3a5f;
        border-color: #4a90e2;
    }
    
    .entrada-braille {
        background-color: #2c3e50;
        color: #ecf0f1;
        border-color: #4a90e2;
    }
    
    .braille-espejo {
        background-color: #2c3e50;
        border-color: #95a5a6;
    }
    
    .texto-braille-espejo {
        color: #ecf0f1;
    }
}
```

---

## 4. Cambios en Plantillas HTML

### 4.1 Modificación de `index.html`

**Ubicación**: `src/main/resources/templates/index.html`

**Estado**: ✅ **Modificado en iteración 2**

**Cambios implementados**: 
- Formulario unificado con 3 opciones de transcripción
- Radio buttons para seleccionar tipo de operación
- Integración de todas las funcionalidades en una sola vista

### 4.2 Plantilla: `result-español.html`

**Ubicación**: `src/main/resources/templates/result-español.html`

**Estado**: ✅ **Implementado**

**Propósito**: Muestra resultado de transcripción Español → Braille

### 4.3 Plantilla: `result-braille.html`

**Ubicación**: `src/main/resources/templates/result-braille.html`

**Estado**: ✅ **Implementado**

**Propósito**: Muestra resultado de transcripción Braille → Español

### 4.4 Plantilla: `result-espejo.html`

**Ubicación**: `src/main/resources/templates/result-espejo.html`

**Estado**: ✅ **Implementado**

**Propósito**: Muestra texto Braille en espejo con dirección RTL para escritura manual

### 4.4 Nueva plantilla: `result-espejo.html`

**Ubicación**: `src/main/resources/templates/result-espejo.html`

**Contenido completo**: [Ver documento de análisis de cambio - Sección 2.2.4.C]

---

## 5. Estructura de Directorios Actualizada

### 5.1 Antes de la Segunda Iteración

```
Proyecto-construccion/
├── src/
│   ├── main/
│   │   ├── java/ec/epn/edu/proyectoconstruccion/
│   │   │   ├── ProyectoConstruccionApplication.java
│   │   │   ├── controller/
│   │   │   │   └── TranscriptionController.java
│   │   │   └── service/
│   │   │       └── BrailleMapper.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   └── styles.css
│   │       └── templates/
│   │           ├── index.html
│   │           └── result.html
│   └── test/
│       └── java/ec/epn/edu/proyectoconstruccion/
│           └── service/
│               └── BrailleMapperTest.java
└── documentacion/
    ├── manual-usuario.md
    ├── diseno-arquitectonico.md
    └── ...
```

### 5.2 Después de la Segunda Iteración (ESTRUCTURA REAL IMPLEMENTADA)

```
Proyecto-construccion/
├── src/
│   ├── main/
│   │   ├── java/ec/epn/edu/proyectoconstruccion/
│   │   │   ├── ProyectoConstruccionApplication.java
│   │   │   ├── controller/
│   │   │   │   └── TranscriptionController.java ⬅️ MODIFICADO
│   │   │   └── service/
│   │   │       ├── BrailleMapper.java ⬅️ MODIFICADO (Integrado)
│   │   │       ├── BrailleDictionary.java ⬅️ NUEVO
│   │   │       └── EspañolMapper.java (legacy, no usado)
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   └── styles.css ⬅️ MODIFICADO
│   │       └── templates/
│   │           ├── index.html ⬅️ MODIFICADO
│   │           ├── result.html
│   │           ├── result-español.html ⬅️ NUEVO
│   │           ├── result-braille.html ⬅️ NUEVO
│   │           └── result-espejo.html ⬅️ NUEVO
│   └── test/
│       └── java/ec/epn/edu/proyectoconstruccion/
│           └── service/
│               ├── BrailleMapperTest.java ⬅️ MODIFICADO
│               └── EspañolMapperTest.java
└── documentacion/
    ├── manual-usuario.md ⬅️ ACTUALIZAR
    ├── diseno-arquitectonico.md ⬅️ ACTUALIZAR
    ├── analisis-cambio-segunda-iteracion.md ⬅️ NUEVO
    ├── configuracion-segunda-iteracion.md ⬅️ NUEVO (este archivo)
    └── ...
```

**Leyenda**:
- ⬅️ NUEVO: Archivo completamente nuevo
- ⬅️ MODIFICADO: Archivo existente con cambios
- Sin flecha: Archivo sin cambios

**Diferencias clave con diseño propuesto**:
- ❌ NO existe `InverseBrailleMapper.java` como clase separada
- ✅ `BrailleMapper.java` contiene TODAS las funcionalidades integradas
- ✅ `BrailleDictionary.java` es el nuevo componente centralizado para mapeos
- ✅ Templates: `result-español.html`, `result-braille.html`, `result-espejo.html` (no `index-inverso.html` ni `result-inverso.html`)

---

## 6. Validaciones de Configuración

### 6.1 Checklist de Verificación Pre-Despliegue

Antes de desplegar la segunda iteración, verificar:

- [ ] `application.properties` contiene todas las nuevas propiedades
- [ ] `styles.css` incluye todos los nuevos estilos
- [ ] Todas las plantillas HTML nuevas están creadas
- [ ] `index.html` tiene el menú de navegación actualizado
- [ ] Directorios `templates/` y `static/` tienen permisos correctos
- [ ] No hay conflictos de nombres en los endpoints
- [ ] Tests de configuración pasan exitosamente

### 6.2 Comandos de Verificación

```bash
# Verificar que la aplicación compila
./mvnw clean compile

# Verificar que los tests pasan
./mvnw test

# Verificar que los recursos estáticos son accesibles
ls -la src/main/resources/static/
ls -la src/main/resources/templates/

# Verificar application.properties
cat src/main/resources/application.properties | grep "app.features"

# Ejecutar aplicación y verificar logs
./mvnw spring-boot:run
```

### 6.3 URLs de Prueba

Después de iniciar la aplicación, verificar que estas URLs responden:

```
✅ http://localhost:8080/                     → Página principal unificada
✅ http://localhost:8080/transcribir-Español  → Endpoint POST Español → Braille
✅ http://localhost:8080/transcribir-Braille   → Endpoint POST Braille → Español
✅ http://localhost:8080/espejo                → Endpoint POST para espejo
```

---

## 7. Rollback Plan

En caso de que surjan problemas con la nueva configuración:

### 7.1 Rollback Parcial (Desactivar Funcionalidades)

Editar `application.properties`:

```properties
# Desactivar transcripción inversa
app.features.transcripcion-inversa=false

# Desactivar impresión espejo
app.features.impresion-espejo=false
```

**Resultado**: Las nuevas funcionalidades se desactivan, pero el código permanece.

### 7.2 Rollback Total (Restaurar Versión Anterior)

```bash
# Restaurar desde control de versiones
git checkout main
git pull origin main

# Recompilar
./mvnw clean package

# Ejecutar versión estable
./mvnw spring-boot:run
```

---

## 8. Monitoreo Post-Despliegue

### 8.1 Métricas a Monitorear

| Métrica | Valor Esperado | Acción si Falla |
|---------|----------------|-----------------|
| Tiempo de respuesta `/transcribir-Braille` | < 500ms | Optimizar algoritmo reverseMap |
| Tiempo de respuesta `/espejo` | < 300ms | Revisar mapa pam y reverse() |
| Tasa de errores 4xx | < 5% | Mejorar validación |
| Tasa de errores 5xx | 0% | Revisar logs, debugear |
| Uso de memoria | < 512MB | Optimizar carga de BrailleDictionary |

### 8.2 Logs a Revisar

```bash
# Ver logs de Spring Boot
tail -f logs/spring.log

# Filtrar errores
grep ERROR logs/spring.log

# Filtrar advertencias de transcripción
grep "transcripcion-inversa" logs/spring.log
```

---

## 9. Mantenimiento Futuro

### 9.1 Actualizaciones Periódicas

**Trimestral**:
- Revisar configuración de fuentes Braille
- Actualizar rango Unicode si se agregan nuevos caracteres
- Verificar compatibilidad con nuevos navegadores

**Anual**:
- Revisar y optimizar valores de configuración basados en uso real
- Actualizar dependencias en `pom.xml`
- Revisar y mejorar estilos CSS

### 9.2 Configuraciones Experimentales

Para pruebas en entorno de desarrollo, crear `application-dev.properties`:

```properties
# Perfil de desarrollo
spring.profiles.active=dev

# Habilitar debug de transcripción
app.debug.log-transcriptions=true

# Reducir validaciones para testing
app.validation.max-text-length=100000
```

Ejecutar con: `./mvnw spring-boot:run -Dspring.profiles.active=dev`

---

## 10. Conclusión

Este documento ha identificado y documentado todos los cambios necesarios en los elementos de configuración para la segunda iteración:

✅ **Configuración de aplicación** (`application.properties`)  
✅ **Estilos CSS** (`styles.css`)  
✅ **Plantillas HTML** (nuevas y modificadas)  
✅ **Estructura de directorios** actualizada  
✅ **Plan de validación** y verificación  
✅ **Estrategia de rollback** en caso de problemas  

Con estos cambios implementados, el sistema estará listo para soportar las nuevas funcionalidades de transcripción bidireccional e impresión en espejo.

---

**Documento elaborado por**: Grupo 7 - EPN  
**Última actualización**: 28 de enero de 2026  
**Versión del documento**: 1.0  
**Estado**: ✅ Listo para implementación
