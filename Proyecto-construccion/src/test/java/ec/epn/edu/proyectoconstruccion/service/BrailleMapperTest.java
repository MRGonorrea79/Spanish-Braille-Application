package ec.epn.edu.proyectoconstruccion.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BrailleMapperTest {

    private BrailleMapper mapper;
    private EspañolMapper espMapper;

    @BeforeEach
    void setUp() {
        mapper = new BrailleMapper();
        espMapper = new EspañolMapper();
    }

    @Test
    @DisplayName("1. Transcribir texto básico en español")
    void transcribirTextoBasico() {
        String result = mapper.transcribir("hola mundo");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕");
    }

    @Test
    @DisplayName("2. Transcribir mayúsculas con signo de mayúscula")
    void transcribirMayusculas() {
        String result = mapper.transcribir("HOLA");
        assertThat(result).isEqualTo("⠨⠨⠓⠕⠇⠁");
    }

    @Test
    @DisplayName("3. Transcribir vocales acentuadas")
    void transcribirAcentos() {
        String result = mapper.transcribir("áéíóú");
        assertThat(result).isEqualTo("⠷⠮⠌⠬⠾");
    }

    @Test
    @DisplayName("4. Transcribir letra ñ")
    void transcribirEnie() {
        String result = mapper.transcribir("año");
        assertThat(result).isEqualTo("⠁⠻⠕");
    }

    @Test
    @DisplayName("5. Normalizar múltiples espacios a uno solo")
    void normalizarEspacios() {
        String result = mapper.transcribir("hola     mundo");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕");
    }

    @Test
    @DisplayName("6. Transcribir números con signo de número")
    void transcribirNumeros() {
        String result = mapper.transcribir("123");
        assertThat(result).isEqualTo("⠼⠁⠃⠉");
    }

    @Test
    @DisplayName("7. Transcribir signos de puntuación válidos")
    void transcribirPuntuacion() {
        String result = mapper.transcribir("hola, mundo.");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠂⠀⠍⠥⠝⠙⠕⠄");
    }

    @Test
    @DisplayName("8. Caracteres NO españoles deben generar espacio en Braille")
    void caracteresInvalidosDevuelvenEspacio() {
        String result = mapper.transcribir("hola @ mundo");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠀ ⠀⠍⠥⠝⠙⠕");
    }

    @Test
    @DisplayName("9. No debe aceptar emojis: reemplazar por espacio")
    void emojiDebeReemplazarse() {
        String result = mapper.transcribir("hola 😀 mundo");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠀⠨⠨  ⠀⠍⠥⠝⠙⠕");
    }

    @Test
    @DisplayName("10. No debe aceptar kanjis: reemplazar por espacio")
    void kanjiDebeReemplazarse() {
        String result = mapper.transcribir("hola 漢");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠀ ");
    }

    @Test
    @DisplayName("11. Texto largo completamente válido")
    void transcribirTextoLargo() {
        String texto = "La rápida acción del zorro marrón sorprende al niño que veía televisión.";
        String result = mapper.transcribir(texto);

        assertThat(result).isEqualTo(
                "⠨⠇⠁⠀⠗⠷⠏⠊⠙⠁⠀⠁⠉⠉⠊⠬⠝⠀⠙⠑⠇⠀⠵⠕⠗⠗⠕⠀⠍⠁⠗⠗⠬⠝⠀⠎⠕⠗⠏⠗⠑⠝⠙⠑⠀⠁⠇⠀⠝⠊⠻⠕⠀⠟⠥⠑⠀⠧⠑⠌⠁⠀⠞⠑⠇⠑⠧⠊⠎⠊⠬⠝⠄"
        );
    }

    @Test
    @DisplayName("12. Solo espacios debe retornar un único espacio braille")
    void soloEspacios() {
        String result = mapper.transcribir("      ");
        assertThat(result).isEqualTo("⠀");
    }

    @Test
    @DisplayName("13. Mezcla de números, acentos, ñ y mayúsculas")
    void mezclaCompleja() {
        String result = mapper.transcribir("Año 2025: Acción Útil.");
        assertThat(result).isEqualTo(
                "⠨⠁⠻⠕⠀⠼⠃⠚⠃⠑⠒⠀⠨⠁⠉⠉⠊⠬⠝⠀⠨⠾⠞⠊⠇⠄"
        );
    }

    @Test
    @DisplayName("14. Caracteres latinos NO españoles (ê â ô) → reemplazo")
    void latinExtNoSoportado() {
        String result = mapper.transcribir("Galletas ângulo");
        assertThat(result).isEqualTo("⠨⠛⠁⠇⠇⠑⠞⠁⠎⠀ ⠝⠛⠥⠇⠕");
    }
}
