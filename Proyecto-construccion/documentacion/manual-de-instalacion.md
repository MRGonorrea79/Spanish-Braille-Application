# 📦 Guía de Instalación - Sistema de Transcripción Español → Braille

## Índice
1. [Requisitos del Sistema](#requisitos-del-sistema)
2. [Instalación de Prerrequisitos](#instalación-de-prerrequisitos)
3. [Instalación del Proyecto](#instalación-del-proyecto)
4. [Configuración](#configuración)
5. [Verificación de la Instalación](#verificación-de-la-instalación)
6. [Problemas Comunes](#problemas-comunes)
7. [Instalación en Diferentes Sistemas Operativos](#instalación-en-diferentes-sistemas-operativos)
8. [Instalación para Desarrollo](#instalación-para-desarrollo)
9. [Instalación para Producción](#instalación-para-producción)

---

## Requisitos del Sistema

### Requisitos Mínimos

| Componente | Requisito Mínimo |
|------------|------------------|
| **Procesador** | Intel Core i3 / AMD equivalente |
| **RAM** | 2 GB disponible |
| **Disco Duro** | 500 MB disponible |
| **Sistema Operativo** | Windows 10+, macOS 10.14+, Linux (kernel 4.x+) |
| **Java** | JDK 17 o superior |
| **Maven** | 3.8.0 o superior (opcional, incluido en el proyecto) |

### Requisitos Recomendados

| Componente | Requisito Recomendado |
|------------|----------------------|
| **Procesador** | Intel Core i5 / AMD Ryzen 5 o superior |
| **RAM** | 4 GB disponible |
| **Disco Duro** | 1 GB disponible |
| **Sistema Operativo** | Windows 11, macOS 12+, Ubuntu 20.04+ |
| **Java** | JDK 21 o 25 |
| **Maven** | 3.9.0+ |

### Software Necesario

1. **Java Development Kit (JDK)** - Version 17 o superior
   - ⚠️ **Importante**: Se requiere JDK, no solo JRE
   - Versión del proyecto: Java 25

2. **Maven** (Opcional)
   - El proyecto incluye Maven Wrapper (`mvnw`)
   - Útil si desea usar Maven globalmente

3. **Git** (Opcional)
   - Necesario solo si clonará desde repositorio
   - Puede descargar el proyecto como ZIP

4. **IDE** (Opcional, recomendado para desarrollo)
   - IntelliJ IDEA (Community o Ultimate)
   - Eclipse IDE
   - Visual Studio Code con extensiones Java

---

## Instalación de Prerrequisitos

### Windows

#### 1. Instalar Java JDK

**Opción A: Oracle JDK**

1. Visitar: https://www.oracle.com/java/technologies/downloads/
2. Descargar JDK 21 o 25 para Windows (x64 Installer)
3. Ejecutar el instalador `.exe`
4. Seguir el asistente de instalación
5. Aceptar la ruta predeterminada: `C:\Program Files\Java\jdk-25`

**Opción B: OpenJDK (Temurin)**

1. Visitar: https://adoptium.net/
2. Descargar Eclipse Temurin JDK 21 LTS
3. Ejecutar el instalador `.msi`
4. ✅ Marcar "Set JAVA_HOME variable"
5. ✅ Marcar "Add to PATH"
6. Completar instalación

**Verificar instalación:**
```powershell
java -version
javac -version
```

Salida esperada:
```
java version "25" 2025-03-18
Java(TM) SE Runtime Environment (build 25+...)
Java HotSpot(TM) 64-Bit Server VM (build 25+...)
```

**Configurar JAVA_HOME (si no se configuró automáticamente):**

1. Abrir "Variables de entorno del sistema"
2. Click en "Variables de entorno"
3. En "Variables del sistema", click "Nueva"
4. Nombre: `JAVA_HOME`
5. Valor: `C:\Program Files\Java\jdk-25` (ajustar a su ruta)
6. Aceptar
7. Editar variable `Path`, agregar: `%JAVA_HOME%\bin`
8. Reiniciar terminal

#### 2. Instalar Git (Opcional)

1. Visitar: https://git-scm.com/download/win
2. Descargar instalador de 64-bit
3. Ejecutar instalador
4. Usar configuraciones predeterminadas
5. Verificar:
```powershell
git --version
```

#### 3. Instalar Maven (Opcional)

El proyecto incluye Maven Wrapper, pero puede instalar Maven globalmente:

1. Visitar: https://maven.apache.org/download.cgi
2. Descargar `apache-maven-3.9.x-bin.zip`
3. Extraer a: `C:\Program Files\Apache\Maven`
4. Agregar a PATH: `C:\Program Files\Apache\Maven\bin`
5. Verificar:
```powershell
mvn -version
```

---

### macOS

#### 1. Instalar Homebrew (si no está instalado)

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

#### 2. Instalar Java JDK

**Opción A: OpenJDK via Homebrew**
```bash
# Instalar OpenJDK 21
brew install openjdk@21

# Crear symlink
sudo ln -sfn /opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk \
  /Library/Java/JavaVirtualMachines/openjdk-21.jdk
```

**Opción B: Oracle JDK**

1. Descargar desde: https://www.oracle.com/java/technologies/downloads/
2. Instalar el paquete `.dmg`

**Verificar:**
```bash
java -version
javac -version
```

**Configurar JAVA_HOME:**

Agregar a `~/.zshrc` o `~/.bash_profile`:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
export PATH=$JAVA_HOME/bin:$PATH
```

Recargar:
```bash
source ~/.zshrc
```

#### 3. Instalar Git

```bash
brew install git
```

#### 4. Instalar Maven (Opcional)

```bash
brew install maven
mvn -version
```

---

### Linux (Ubuntu/Debian)

#### 1. Actualizar sistema

```bash
sudo apt update
sudo apt upgrade -y
```

#### 2. Instalar Java JDK

**Opción A: OpenJDK 21**
```bash
sudo apt install openjdk-21-jdk -y
```

**Opción B: Oracle JDK**
```bash
# Descargar desde Oracle
wget https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.tar.gz

# Extraer
sudo tar -xzf jdk-21_linux-x64_bin.tar.gz -C /opt/

# Configurar alternativas
sudo update-alternatives --install /usr/bin/java java /opt/jdk-21/bin/java 1
sudo update-alternatives --install /usr/bin/javac javac /opt/jdk-21/bin/javac 1
```

**Verificar:**
```bash
java -version
javac -version
```

**Configurar JAVA_HOME:**

Agregar a `~/.bashrc`:
```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
```

Recargar:
```bash
source ~/.bashrc
```

#### 3. Instalar Git

```bash
sudo apt install git -y
git --version
```

#### 4. Instalar Maven (Opcional)

```bash
sudo apt install maven -y
mvn -version
```

---

## Instalación del Proyecto

### Método 1: Clonar desde Git (Recomendado)

```bash
# Navegar a la carpeta deseada
cd ~/Projects

# Clonar repositorio
git clone https://github.com/tu-usuario/Spanish-Braille-Application.git

# Entrar al proyecto
cd Spanish-Braille-Application/Proyecto-construccion

# Verificar estructura
ls -la
```

### Método 2: Descargar como ZIP

1. Ir al repositorio en GitHub
2. Click en "Code" → "Download ZIP"
3. Extraer el archivo ZIP
4. Navegar a la carpeta `Proyecto-construccion`

### Método 3: Importar en IDE

#### IntelliJ IDEA

1. Abrir IntelliJ IDEA
2. File → Open
3. Seleccionar carpeta `Proyecto-construccion`
4. Esperar que IntelliJ importe el proyecto Maven
5. Configurar SDK:
   - File → Project Structure → Project
   - Project SDK: Seleccionar Java 21 o 25
   - Apply

#### Eclipse

1. Abrir Eclipse
2. File → Import → Maven → Existing Maven Projects
3. Root Directory: Seleccionar `Proyecto-construccion`
4. Finish
5. Click derecho en proyecto → Properties → Java Build Path
6. Verificar JRE System Library

#### Visual Studio Code

1. Abrir VS Code
2. Instalar extensión "Extension Pack for Java"
3. File → Open Folder
4. Seleccionar `Proyecto-construccion`
5. VS Code detectará automáticamente el proyecto Maven

---

## Configuración

### Verificar Estructura del Proyecto

```
Proyecto-construccion/
├── mvnw              ← Maven Wrapper (Linux/Mac)
├── mvnw.cmd          ← Maven Wrapper (Windows)
├── pom.xml           ← Configuración Maven
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
│       └── java/
└── target/           (se crea después de compilar)
```

### Permisos de Ejecución (Linux/Mac)

```bash
chmod +x mvnw
```

### Compilar el Proyecto

**Windows:**
```powershell
.\mvnw.cmd clean compile
```

**Linux/Mac:**
```bash
./mvnw clean compile
```

**Salida esperada:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: 15 s
```

### Ejecutar Tests

**Windows:**
```powershell
.\mvnw.cmd test
```

**Linux/Mac:**
```bash
./mvnw test
```

**Salida esperada:**
```
[INFO] Tests run: 12, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### Empaquetar la Aplicación

**Windows:**
```powershell
.\mvnw.cmd clean package
```

**Linux/Mac:**
```bash
./mvnw clean package
```

Genera: `target/Proyecto-construccion-0.0.1-SNAPSHOT.jar`

---

## Verificación de la Instalación

### Paso 1: Ejecutar la Aplicación

**Windows:**
```powershell
.\mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

**O ejecutar el JAR directamente:**
```bash
java -jar target/Proyecto-construccion-0.0.1-SNAPSHOT.jar
```

### Paso 2: Verificar en el Navegador

1. Abrir navegador web
2. Navegar a: `http://localhost:8080`
3. Debe ver la página principal del transcriptor

### Paso 3: Probar Funcionalidad

1. Ingresar texto: `Hola mundo 123`
2. Click en "Transcribir"
3. Verificar resultado en Braille

✅ **Si ve el resultado, la instalación fue exitosa**

### Paso 4: Detener la Aplicación

- Presionar `Ctrl + C` en la terminal

---

## Problemas Comunes

### Error: "JAVA_HOME is not set"

**Solución:**
```bash
# Windows (PowerShell como admin)
[System.Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Program Files\Java\jdk-25", "Machine")

# Linux/Mac
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
```

### Error: "mvnw: Permission denied" (Linux/Mac)

**Solución:**
```bash
chmod +x mvnw
```

### Error: "Port 8080 already in use"

**Solución 1:** Cambiar puerto

Crear `src/main/resources/application.properties`:
```properties
server.port=8081
```

**Solución 2:** Liberar puerto

Windows:
```powershell
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

Linux/Mac:
```bash
lsof -ti:8080 | xargs kill -9
```

### Error: "No compiler is provided in this environment"

**Causa:** JRE instalado en lugar de JDK

**Solución:**
1. Desinstalar JRE
2. Instalar JDK completo
3. Verificar: `javac -version` (debe funcionar)

### Error: Dependencias no se descargan

**Solución:**
```bash
# Forzar actualización de dependencias
./mvnw clean install -U

# Limpiar repositorio local Maven
rm -rf ~/.m2/repository
./mvnw clean install
```

### Error: OutOfMemoryError

**Solución:** Aumentar memoria Maven

Crear archivo `.mvn/jvm.config`:
```
-Xmx1024m
-Xms512m
```

O ejecutar con parámetros:
```bash
MAVEN_OPTS="-Xmx1024m" ./mvnw spring-boot:run
```

---

## Instalación en Diferentes Sistemas Operativos

### Windows Server

1. Instalar Java JDK
2. Clonar/copiar proyecto
3. Compilar: `mvnw.cmd package`
4. Crear servicio Windows (ver Manual Técnico)
5. Configurar firewall para puerto 8080

### Ubuntu Server

```bash
# 1. Actualizar sistema
sudo apt update && sudo apt upgrade -y

# 2. Instalar Java
sudo apt install openjdk-21-jdk -y

# 3. Crear usuario para la aplicación
sudo useradd -r -s /bin/false braille

# 4. Copiar proyecto
sudo mkdir -p /opt/braille
sudo cp -r Proyecto-construccion /opt/braille/
cd /opt/braille/Proyecto-construccion

# 5. Compilar
./mvnw clean package

# 6. Crear servicio systemd
sudo nano /etc/systemd/system/braille.service
```

Contenido del servicio:
```ini
[Unit]
Description=Spanish Braille Application
After=network.target

[Service]
Type=simple
User=braille
WorkingDirectory=/opt/braille/Proyecto-construccion
ExecStart=/usr/bin/java -jar target/Proyecto-construccion-0.0.1-SNAPSHOT.jar
Restart=on-failure

[Install]
WantedBy=multi-user.target
```

```bash
# 7. Habilitar y arrancar
sudo systemctl daemon-reload
sudo systemctl enable braille
sudo systemctl start braille
sudo systemctl status braille

# 8. Configurar firewall
sudo ufw allow 8080
```

### Docker

**Dockerfile:**
```dockerfile
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiar archivos Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Descargar dependencias
RUN ./mvnw dependency:go-offline

# Copiar código fuente
COPY src src

# Compilar aplicación
RUN ./mvnw package -DskipTests

# Exponer puerto
EXPOSE 8080

# Ejecutar aplicación
CMD ["java", "-jar", "target/Proyecto-construccion-0.0.1-SNAPSHOT.jar"]
```

**Comandos:**
```bash
# Construir imagen
docker build -t braille-app:1.0 .

# Ejecutar contenedor
docker run -d -p 8080:8080 --name braille-app braille-app:1.0

# Ver logs
docker logs -f braille-app

# Detener
docker stop braille-app

# Eliminar
docker rm braille-app
```

**Docker Compose:**

`docker-compose.yml`:

```yaml
version: '3.8'
services:
  braille-app:
    build: ../..
    ports:
      - "8080:8080"
    restart: unless-stopped
    environment:
      - SPRING_PROFILES_ACTIVE=production
```

Ejecutar:
```bash
docker-compose up -d
```

---

## Instalación para Desarrollo

### Configuración de IDE

#### IntelliJ IDEA

1. **Importar proyecto:**
   - File → Open → Seleccionar `Proyecto-construccion`

2. **Configurar SDK:**
   - File → Project Structure → Project
   - Project SDK: Java 21 o 25
   - Project language level: 21

3. **Configurar Maven:**
   - File → Settings → Build, Execution, Deployment → Build Tools → Maven
   - Maven home directory: Bundled (Maven 3)
   - User settings file: Default

4. **Ejecutar desde IDE:**
   - Abrir `ProyectoConstruccionApplication.java`
   - Click derecho → Run 'ProyectoConstruccionApplication'
   - O usar botón verde de play

5. **Hot Reload (opcional):**
   - Agregar dependencia `spring-boot-devtools` en `pom.xml`
   - Habilitar "Build project automatically"

#### Visual Studio Code

1. **Instalar extensiones:**
   - Extension Pack for Java
   - Spring Boot Extension Pack
   - Lombok Annotations Support (si se usa)

2. **Abrir proyecto:**
   - File → Open Folder → `Proyecto-construccion`

3. **Ejecutar:**
   - Presionar F5
   - O usar "Java: Run Spring Boot App" en paleta de comandos

### Configuración de Git

```bash
# Configurar usuario
git config --global user.name "Tu Nombre"
git config --global user.email "tu@email.com"

# Verificar estado
git status

# Crear rama de desarrollo
git checkout -b desarrollo
```

### .gitignore Recomendado

Ya incluido en el proyecto, pero verificar:
```gitignore
target/
.idea/
*.iml
.vscode/
.DS_Store
*.log
```

---

## Instalación para Producción

### Servidor Linux (Ubuntu)

**Script completo de instalación:**

```bash
#!/bin/bash
# install-braille-prod.sh

set -e

echo "=== Instalación Spanish-Braille-Application ==="

# 1. Actualizar sistema
echo "Actualizando sistema..."
sudo apt update
sudo apt upgrade -y

# 2. Instalar Java
echo "Instalando Java 21..."
sudo apt install openjdk-21-jdk -y

# 3. Crear usuario
echo "Creando usuario braille..."
sudo useradd -r -m -s /bin/bash braille

# 4. Crear directorios
echo "Creando estructura de directorios..."
sudo mkdir -p /opt/braille/app
sudo mkdir -p /opt/braille/logs
sudo chown -R braille:braille /opt/braille

# 5. Copiar aplicación
echo "Copiando aplicación..."
sudo cp target/Proyecto-construccion-0.0.1-SNAPSHOT.jar /opt/braille/app/braille.jar
sudo chown braille:braille /opt/braille/app/braille.jar

# 6. Crear archivo de configuración
echo "Creando configuración..."
sudo tee /opt/braille/app/application.properties > /dev/null <<EOF
server.port=8080
logging.file.name=/opt/braille/logs/application.log
logging.level.root=INFO
EOF

# 7. Crear servicio systemd
echo "Creando servicio systemd..."
sudo tee /etc/systemd/system/braille.service > /dev/null <<EOF
[Unit]
Description=Spanish Braille Application
After=network.target

[Service]
Type=simple
User=braille
Group=braille
WorkingDirectory=/opt/braille/app
ExecStart=/usr/bin/java -Xmx512m -jar braille.jar
SuccessExitStatus=143
Restart=on-failure
RestartSec=10

[Install]
WantedBy=multi-user.target
EOF

# 8. Configurar firewall
echo "Configurando firewall..."
sudo ufw allow 8080/tcp

# 9. Habilitar y arrancar servicio
echo "Arrancando servicio..."
sudo systemctl daemon-reload
sudo systemctl enable braille
sudo systemctl start braille

# 10. Verificar estado
sleep 5
sudo systemctl status braille

echo "=== Instalación completada ==="
echo "Aplicación disponible en: http://localhost:8080"
```

**Ejecutar:**
```bash
chmod +x install-braille-prod.sh
sudo ./install-braille-prod.sh
```

### Configuración de Nginx (Proxy Reverso)

```nginx
# /etc/nginx/sites-available/braille

server {
    listen 80;
    server_name braille.example.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

**Habilitar:**
```bash
sudo ln -s /etc/nginx/sites-available/braille /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl reload nginx
```

### SSL con Let's Encrypt

```bash
sudo apt install certbot python3-certbot-nginx -y
sudo certbot --nginx -d braille.example.com
```

---

## Verificación Final

### Checklist de Instalación

- [ ] Java JDK instalado (versión 17+)
- [ ] JAVA_HOME configurado
- [ ] Git instalado (si se usa)
- [ ] Proyecto descargado/clonado
- [ ] Dependencias descargadas (`./mvnw clean compile`)
- [ ] Tests ejecutados exitosamente (`./mvnw test`)
- [ ] Aplicación compila (`./mvnw package`)
- [ ] Aplicación arranca (`./mvnw spring-boot:run`)
- [ ] Página accesible en navegador (`http://localhost:8080`)
- [ ] Transcripción funciona correctamente
- [ ] Impresión funciona (opcional)

### Comandos de Verificación

```bash
# Verificar Java
java -version

# Verificar compilación
./mvnw --version

# Verificar tests
./mvnw test

# Verificar aplicación corriendo
curl http://localhost:8080

# Verificar logs
tail -f logs/spring.log  # si está configurado
```

---

## Soporte

Si encuentra problemas durante la instalación:

1. **Revisar logs**: Ver salida de consola o archivos de log
2. **Verificar prerrequisitos**: Java, Maven, permisos
3. **Consultar documentación**: README.md
4. **Contactar equipo**: Grupo 7 - EPN

---

**Versión**: 1.0  
**Última actualización**: 24/11/2025  
**Autores**: Grupo 7 - Escuela Politécnica Nacional

*Esta guía es parte de la documentación del proyecto de Construcción de Software.*

