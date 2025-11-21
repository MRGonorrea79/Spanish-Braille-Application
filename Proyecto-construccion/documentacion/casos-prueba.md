# Casos de prueba y resultados

## Cómo ejecutar los tests
```cmd
mvn test
```

Los resultados se generan en `target/surefire-reports/`.

## Plantilla de caso de prueba
| ID | Descripción | Pasos | Entrada | Salida esperada | Resultado | Observaciones |
|----|-------------|-------|---------|----------------|----------:|---------------|
| TC-01 | Transcribir texto simple | 1) Abrir app 2) Ingresar texto 3) Enviar | "hola" | Representación Braille esperada | | |

Incluye aquí los resultados de `target/surefire-reports/` en texto o adjunta los archivos relevantes.
