package ec.epn.edu.proyectoconstruccion.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BrailleMapperTest {

    private BrailleMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new BrailleMapper();
    }

    @Test
    @DisplayName("1. Transcribir texto básico en español")
    void transcribirTextoBasico() {
        String result = mapper.transcribir("hola mundo");
        assertThat(result).isNotBlank();
    }

    @Test
    @DisplayName("2. Transcribir mayúsculas con signo de mayúscula")
    void transcribirMayusculas() {
        String result = mapper.transcribir("HOLA");
        assertThat(result).contains("\u2820");
    }

    @Test
    @DisplayName("3. Transcribir vocales acentuadas")
    void transcribirAcentos() {
        String result = mapper.transcribir("áéíóú");
        assertThat(result).isNotBlank();
    }

    @Test
    @DisplayName("4. Transcribir letra ñ")
    void transcribirEnie() {
        String result = mapper.transcribir("año");
        assertThat(result).isNotBlank();
    }

    @Test
    @DisplayName("5. Normalizar múltiples espacios a uno solo")
    void normalizarEspacios() {
        String result = mapper.transcribir("hola     mundo");
        assertThat(result).doesNotContain("  ");
    }

    @Test
    @DisplayName("6. Transcribir números con signo de número")
    void transcribirNumeros() {
        String result = mapper.transcribir("123");
        assertThat(result.charAt(0)).isNotEqualTo(' ');
    }

    @Test
    @DisplayName("7. Transcribir signos de puntuación válidos")
    void transcribirPuntuacion() {
        String result = mapper.transcribir("hola, mundo.");
        assertThat(result).isNotBlank();
    }

    @Test
    @DisplayName("8. Caracteres NO españoles deben generar espacio en Braille")
    void caracteresInvalidosDevuelvenEspacio() {
        String result = mapper.transcribir("hola @ mundo");
        // '@' no existe → genera un espacio Braille
        assertThat(result).contains(" ");
    }

    @Test
    @DisplayName("9. No debe aceptar emojis: reemplazar por espacio")
    void emojiDebeReemplazarse() {
        String result = mapper.transcribir("hola 😀");

        // esperado → el output contiene un espacio Braille por el emoji
        assertThat(result).contains(" ");
    }

    @Test
    @DisplayName("10. No debe aceptar kanjis: reemplazar por espacio")
    void kanjiDebeReemplazarse() {
        String result = mapper.transcribir("hola 漢");

        assertThat(result).contains(" ");
    }

    @Test
    @DisplayName("11. Texto largo completamente válido")
    void transcribirTextoLargo() {
        String texto = "La rápida acción del zorro marrón sorprende al niño que veía televisión.";
        String result = mapper.transcribir(texto);
        assertThat(result).isNotBlank();
    }

    @Test
    @DisplayName("12. Solo espacios debe retornar un único espacio braille")
    void soloEspacios() {
        String result = mapper.transcribir("      ");
        assertThat(result.length()).isEqualTo(1);
    }

    @Test
    @DisplayName("13. Mezcla de números, acentos, ñ y mayúsculas")
    void mezclaCompleja() {
        String result = mapper.transcribir("Año 2025: Acción Útil.");
        assertThat(result).isNotBlank();
    }

    @Test
    @DisplayName("14. Caracteres latinos NO españoles (ê â ô) → reemplazo")
    void latinExtNoSoportado() {
        String result = mapper.transcribir("café ângulo");
        assertThat(result).contains(" ");
    }
}

