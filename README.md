# Sistema de Transcripción Español → Braille

## 📋 Descripción

**Spanish-Braille-Application** es una aplicación web desarrollada en Spring Boot que convierte texto en español a Braille Unicode. Soporta el alfabeto español completo (incluyendo ñ, ü, vocales acentuadas), mayúsculas, números y puntuación básica. El sistema normaliza espacios y ofrece una interfaz web intuitiva y accesible.

---

## 🌟 Características Principales

- ✅ **Alfabeto español completo**: a-z, incluyendo ñ y ü
- ✅ **Vocales acentuadas**: á, é, í, ó, ú
- ✅ **Mayúsculas**: Conversión automática con signo de mayúscula
- ✅ **Números**: Con signo de número automático (⠼)
- ✅ **Puntuación**: Coma, punto, signos de interrogación, exclamación, paréntesis, guiones
- ✅ **Normalización de espacios**: Múltiples espacios se convierten en uno solo
- ✅ **Interfaz web intuitiva**: Diseño limpio y responsivo
- ✅ **Impresión**: Opción para imprimir el resultado
- ✅ **Código Unicode Braille**: Representación estándar (U+2800+)

---

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 25**: Lenguaje de programación principal
- **Spring Boot 3.5.7**: Framework para el desarrollo de la aplicación
- **Spring Web**: Para los controladores REST y web
- **Maven**: Gestión de dependencias y construcción del proyecto

### Frontend
- **Thymeleaf**: Motor de plantillas para las vistas HTML
- **HTML5**: Estructura de las páginas
- **CSS3**: Estilos personalizados con diseño moderno

### Testing
- **JUnit 5**: Framework de pruebas unitarias
- **AssertJ**: Assertions fluidas para testing

---

## 📁 Estructura del Proyecto

```
Spanish-Braille-Application/
├── README.md
├── MANUAL_USUARIO.md
├── MANUAL_TECNICO.md
├── GUIA_INSTALACION.md
├── ARQUITECTURA.md
└── Proyecto-construccion/
    ├── pom.xml
    ├── mvnw, mvnw.cmd (Maven Wrapper)
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
    │           ├── ProyectoConstruccionApplicationTests.java
    │           └── service/
    │               └── BrailleMapperTest.java
    └── target/ (archivos compilados)
```

---

## 🚀 Inicio Rápido

### Prerrequisitos
- Java 17 o superior (recomendado Java 25)
- Maven 3.8+ (o usar el Maven Wrapper incluido)

### Instalación y Ejecución

1. **Clonar el repositorio**
   ```powershell
   git clone https://github.com/tu-usuario/Spanish-Braille-Application.git
   cd Spanish-Braille-Application/Proyecto-construccion
   ```
2. **Compilar el proyecto**
   ```powershell
   ./mvnw clean install
   # En Windows: mvnw.cmd clean install
   ```
3. **Ejecutar la aplicación**
   ```powershell
   ./mvnw spring-boot:run
   # En Windows: mvnw.cmd spring-boot:run
   ```
4. **Acceder a la aplicación**
   - Abrir el navegador en: `http://localhost:8080`

---

## 📖 Uso

1. **Ingresar texto**: En la página principal, escriba o pegue el texto en español que desea transcribir.
2. **Transcribir**: Presione el botón "Transcribir".
3. **Ver resultado**: El sistema mostrará el texto original y su equivalente en Braille Unicode.
4. **Imprimir** (opcional): Use el botón "Imprimir" para generar una copia física.

### Ejemplo

- **Entrada:** `¡Hola, mundo! 123`
- **Salida esperada:** ⠨⠓⠕⠇⠁⠂⠀⠍⠥⠝⠙⠕⠖⠀⠼⠁⠃⠉

---

## 🧪 Pruebas

El proyecto incluye una suite completa de pruebas unitarias:

```powershell
./mvnw test
```

### Cobertura de pruebas:
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

## 📚 Documentación Adicional

- **[Manual de Usuario](Proyecto-construccion/documentacion/manual-usuario.md)**: Guía completa para usuarios finales
- **[Manual Técnico](Proyecto-construccion/documentacion/entorno-desarrollo.md)**: Documentación para desarrolladores
- **[Guía de Instalación](Proyecto-construccion/documentacion/manual-de-instalacion.md)**: Instrucciones detalladas de instalación
- **[Diseño Arquitectónico](Proyecto-construccion/documentacion/diseno-arquitectonico.md)**: Diseño y patrones del sistema

---

## 🏗️ Arquitectura

El proyecto sigue el patrón **MVC (Model-View-Controller)**:

- **Model**: `BrailleMapper` - Lógica de negocio para la conversión
- **View**: Plantillas Thymeleaf (`index.html`, `result.html`)
- **Controller**: `TranscriptionController` - Maneja las peticiones HTTP

### Componentes principales:

1. **BrailleMapper**: Servicio que implementa el algoritmo de conversión
   - Mapeo de caracteres a máscaras Braille
   - Conversión a Unicode (U+2800+)
   - Manejo de números, mayúsculas y acentos

2. **TranscriptionController**: Controlador web
   - Endpoint GET `/` - Página principal
   - Endpoint POST `/transcribir` - Procesa la transcripción

3. **Templates HTML**: Vistas con Thymeleaf
   - `index.html` - Formulario de entrada
   - `result.html` - Resultado de la transcripción

---

## 👥 Autores

**Grupo 7**
- Proyecto de Construcción de Software
- Escuela Politécnica Nacional (EPN)
- Fecha: Noviembre 2025

---

## 📄 Licencia

Este proyecto fue desarrollado con fines académicos para la materia de Construcción de Software.

---

## 🤝 Contribuciones

Este es un proyecto académico. Para sugerencias o mejoras, por favor contacte a los autores.

---

## 📞 Soporte

Para preguntas o problemas:
1. Revisar la documentación en este repositorio
2. Contactar al equipo de desarrollo

---

## 🔄 Historial de Versiones

### v0.0.1-SNAPSHOT (Noviembre 2025)
- ✅ Implementación inicial
- ✅ Soporte completo para español
- ✅ Interfaz web funcional
- ✅ Suite de pruebas unitarias
- ✅ Documentación completa

---

## 🎓 Aprendizajes del Proyecto

Este proyecto permitió aplicar los siguientes conceptos de Construcción de Software:

- **Arquitectura de software**: Patrón MVC
- **Desarrollo web**: Spring Boot + Thymeleaf
- **Testing**: JUnit y TDD
- **Gestión de dependencias**: Maven
- **Versionado**: Git
- **Documentación**: JavaDoc y Markdown
- **Buenas prácticas**: Clean Code, SOLID

---

**¡Gracias por usar Spanish-Braille-Application!** 🎉

**Fecha de actualización:** 30/01/2026
