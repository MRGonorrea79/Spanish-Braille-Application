# Manual de instalación

## Requisitos
- JDK 21
- Maven 3.8+ (opcional si no usas el wrapper)
- Git

## Clonar el repositorio
```cmd
git clone https://github.com/MRGonorrea79/Spanish-Braille-Application.git
cd Proyecto-construccion
```

## Construir y ejecutar
```cmd
mvn clean package
java -jar target\Proyecto-construccion-0.0.1-SNAPSHOT.jar
```

Si prefieres ejecutar en otro puerto:
```cmd
java -jar target\Proyecto-construccion-0.0.1-SNAPSHOT.jar --server.port=8081
```
