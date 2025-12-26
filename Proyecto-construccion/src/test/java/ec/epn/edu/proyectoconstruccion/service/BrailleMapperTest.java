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
        String result = mapper.españolABraille("hola mundo");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕");
    }

    @Test
    @DisplayName("2. Transcribir mayúsculas con signo de mayúscula")
    void transcribirMayusculas() {
        String result = mapper.españolABraille("HOLA");
        assertThat(result).isEqualTo("⠨⠨⠓⠕⠇⠁");
    }

    @Test
    @DisplayName("3. Transcribir vocales acentuadas")
    void transcribirAcentos() {
        String result = mapper.españolABraille("áéíóú");
        assertThat(result).isEqualTo("⠷⠮⠌⠬⠾");
    }

    @Test
    @DisplayName("4. Transcribir letra ñ")
    void transcribirEnie() {
        String result = mapper.españolABraille("año");
        assertThat(result).isEqualTo("⠁⠻⠕");
    }

    @Test
    @DisplayName("5. Normalizar múltiples espacios a uno solo")
    void normalizarEspacios() {
        String result = mapper.españolABraille("hola     mundo");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕");
    }

    @Test
    @DisplayName("6. Transcribir números con signo de número")
    void transcribirNumeros() {
        String result = mapper.españolABraille("123");
        assertThat(result).isEqualTo("⠼⠁⠃⠉");
    }

    @Test
    @DisplayName("7. Transcribir signos de puntuación válidos")
    void transcribirPuntuacion() {
        String result = mapper.españolABraille("hola, mundo.");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠂⠀⠍⠥⠝⠙⠕⠄");
    }

    @Test
    @DisplayName("8. Caracteres NO españoles deben generar espacio en Braille")
    void caracteresInvalidosDevuelvenEspacio() {
        String result = mapper.españolABraille("hola @ mundo");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠀ ⠀⠍⠥⠝⠙⠕");
    }

    @Test
    @DisplayName("9. No debe aceptar emojis: reemplazar por espacio")
    void emojiDebeReemplazarse() {
        String result = mapper.españolABraille("hola 😀 mundo");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠀⠨⠨  ⠀⠍⠥⠝⠙⠕");
    }

    @Test
    @DisplayName("10. No debe aceptar kanjis: reemplazar por espacio")
    void kanjiDebeReemplazarse() {
        String result = mapper.españolABraille("hola 漢");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠀ ");
    }

    @Test
    @DisplayName("11. Texto largo completamente válido")
    void transcribirTextoLargo() {
        String texto = "La rápida acción del zorro marrón sorprende al niño que veía televisión.";
        String result = mapper.españolABraille(texto);

        assertThat(result).isEqualTo(
                "⠨⠇⠁⠀⠗⠷⠏⠊⠙⠁⠀⠁⠉⠉⠊⠬⠝⠀⠙⠑⠇⠀⠵⠕⠗⠗⠕⠀⠍⠁⠗⠗⠬⠝⠀⠎⠕⠗⠏⠗⠑⠝⠙⠑⠀⠁⠇⠀⠝⠊⠻⠕⠀⠟⠥⠑⠀⠧⠑⠌⠁⠀⠞⠑⠇⠑⠧⠊⠎⠊⠬⠝⠄"
        );
    }

    @Test
    @DisplayName("12. Solo espacios debe retornar un único espacio braille")
    void soloEspacios() {
        String result = mapper.españolABraille("      ");
        assertThat(result).isEqualTo("⠀");
    }

    @Test
    @DisplayName("13. Mezcla de números, acentos, ñ y mayúsculas")
    void mezclaCompleja() {
        String result = mapper.españolABraille("Año 2025: Acción Útil.");
        assertThat(result).isEqualTo(
                "⠨⠁⠻⠕⠀⠼⠃⠚⠃⠑⠒⠀⠨⠁⠉⠉⠊⠬⠝⠀⠨⠾⠞⠊⠇⠄"
        );
    }

    @Test
    @DisplayName("14. Caracteres latinos NO españoles (ê â ô) → reemplazo")
    void latinExtNoSoportado() {
        String result = mapper.españolABraille("Galletas ângulo");
        assertThat(result).isEqualTo("⠨⠛⠁⠇⠇⠑⠞⠁⠎⠀ ⠝⠛⠥⠇⠕");
    }

    @Test
    @DisplayName("1. Transcribir braille básico a español")
    void brailleTextoBasico() {
        String result = mapper.brailleAEspañol("⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕");
        assertThat(result).isEqualTo("hola mundo");
    }

    @Test
    @DisplayName("2. Transcribir mayúsculas con signo de mayúscula")
    void brailleMayusculas() {
        String result = mapper.brailleAEspañol("⠨⠨⠓⠕⠇⠁");
        assertThat(result).isEqualTo("HOLA");
    }

    @Test
    @DisplayName("3. Transcribir vocales acentuadas")
    void brailleAcentos() {
        String result = mapper.brailleAEspañol("⠷⠮⠌⠬⠾");
        assertThat(result).isEqualTo("áéíóú");
    }

    @Test
    @DisplayName("4. Transcribir letra ñ")
    void brailleEnie() {
        String result = mapper.brailleAEspañol("⠁⠻⠕");
        assertThat(result).isEqualTo("año");
    }

    @Test
    @DisplayName("5. Normalizar múltiples espacios")
    void brailleEspacios() {
        String result = mapper.brailleAEspañol("⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕");
        assertThat(result).isEqualTo("hola mundo");
    }

    @Test
    @DisplayName("6. Transcribir números con signo de número")
    void brailleNumeros() {
        String result = mapper.brailleAEspañol("⠼⠁⠃⠉");
        assertThat(result).isEqualTo("123");
    }

    @Test
    @DisplayName("7. Transcribir signos de puntuación")
    void braillePuntuacion() {
        String result = mapper.brailleAEspañol("⠓⠕⠇⠁⠂⠀⠍⠥⠝⠙⠕⠄");
        assertThat(result).isEqualTo("hola, mundo.");
    }

    @Test
    @DisplayName("8. Espacios por caracteres no españoles")
    void brailleCaracterInvalido() {
        String result = mapper.brailleAEspañol("⠓⠕⠇⠁⠀ ⠍⠥⠝⠙⠕");
        assertThat(result).isEqualTo("hola  mundo");
    }



    @Test
    @DisplayName("9. Texto largo completamente válido")
    void brailleTextoLargo() {
        String result = mapper.brailleAEspañol(
                "⠨⠇⠁⠀⠗⠷⠏⠊⠙⠁⠀⠁⠉⠉⠊⠬⠝⠀⠙⠑⠇⠀⠵⠕⠗⠗⠕⠀⠍⠁⠗⠗⠬⠝⠀" +
                        "⠎⠕⠗⠏⠗⠑⠝⠙⠑⠀⠁⠇⠀⠝⠊⠻⠕⠀⠟⠥⠑⠀⠧⠑⠌⠁⠀⠞⠑⠇⠑⠧⠊⠎⠊⠬⠝⠄"
        );

        assertThat(result).isEqualTo(
                "La rápida acción del zorro marrón sorprende al niño que veía televisión."
        );
    }

    @Test
    @DisplayName("10. Solo espacio braille")
    void brailleSoloEspacio() {
        String result = mapper.brailleAEspañol("⠀");
        assertThat(result).isEqualTo(" ");
    }

    @Test
    @DisplayName("11. Mezcla de números, acentos, ñ y mayúsculas")
    void brailleMezclaCompleja() {
        String result = mapper.brailleAEspañol(
                "⠨⠁⠻⠕⠀⠼⠃⠚⠃⠑⠒⠀⠨⠁⠉⠉⠊⠬⠝⠀⠨⠾⠞⠊⠇⠄"
        );

        assertThat(result).isEqualTo("Año 2025: Acción Útil.");
    }

    @Test
    @DisplayName("12. Caracteres latinos no españoles → espacio")
    void brailleLatinNoSoportado() {
        String result = mapper.brailleAEspañol("⠨⠛⠁⠇⠇⠑⠞⠁⠎⠀ ⠝⠛⠥⠇⠕");
        assertThat(result).isEqualTo("Galletas  ngulo");
    }
}
