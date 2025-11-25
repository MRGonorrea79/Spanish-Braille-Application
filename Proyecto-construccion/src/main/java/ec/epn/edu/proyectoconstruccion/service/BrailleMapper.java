package ec.epn.edu.proyectoconstruccion.service;

import java.util.*;

/**
 * <p><b>BrailleMapper</b></p>
 *
 * <p>Clase encargada de mapear caracteres del alfabeto español
 * a su representación en Braille Unicode. Implementa:</p>
 *
 * <ul>
 *     <li>Tres series oficiales del Braille español (a–j, k–t, u–z).</li>
 *     <li>Soporte para letras especiales: ñ, ü, vocales acentuadas.</li>
 *     <li>Signo de número y conversión de cifras.</li>
 *     <li>Signo de mayúsculas.</li>
 *     <li>Puntuación básica.</li>
 *     <li>Conversión Unicode (U+2800 en adelante).</li>
 *     <li>Normalización de espacios múltiples a uno solo.</li>
 * </ul>
 *
 * <p><b>Auditoría:</b> Grupo 7</p>
 * <p><b>Última actualización:</b> 15/11/2025</p>
 *
 * @author Grupo7
 */
public class BrailleMapper {

    private final Map<String, Integer> map = new HashMap<>();

    private final int SIGNO_NUMERO = mask(3,4,5,6);  // ⠼
    private final int SIGNO_MAYUSCULA = mask(6);     // ⠠

    /**
     * Constructor: inicializa todas las tablas Braille.
     */
    public BrailleMapper() {
        initLetters();
        initAccents();
        initPunctuation();
        initNumbers();
    }

    // ===========================
    //   LETRAS OFICIALES BRAILLE
    // ===========================

    /**
     * Carga el alfabeto base siguiendo las tres series oficiales.
     */
    private void initLetters() {

        map.put("a", mask(1));
        map.put("b", mask(1,2));
        map.put("c", mask(1,4));
        map.put("d", mask(1,4,5));
        map.put("e", mask(1,5));
        map.put("f", mask(1,2,4));
        map.put("g", mask(1,2,4,5));
        map.put("h", mask(1,2,5));
        map.put("i", mask(2,4));
        map.put("j", mask(2,4,5));
        map.put("k", addDot(map.get("a"), 3));
        map.put("l", addDot(map.get("b"), 3));
        map.put("m", addDot(map.get("c"), 3));
        map.put("n", addDot(map.get("d"), 3));
        map.put("o", addDot(map.get("e"), 3));
        map.put("p", addDot(map.get("f"), 3));
        map.put("q", addDot(map.get("g"), 3));
        map.put("r", addDot(map.get("h"), 3));
        map.put("s", addDot(map.get("i"), 3));
        map.put("t", addDot(map.get("j"), 3));
        map.put("u", addDot(map.get("k"), 6));
        map.put("v", addDot(map.get("l"), 6));
        map.put("x", addDot(map.get("m"), 6));
        map.put("y", addDot(map.get("n"), 6));
        map.put("z", addDot(map.get("o"), 6));

        map.put("ñ", mask(1,3,4,5,6)); // ⠻
        map.put("ü", mask(1,3,6));     // ⠳
    }

    // ===========================
    //   ACENTOS
    // ===========================

    /**
     * Carga vocales acentuadas con el prefijo oficial (4,6).
     */
    private void initAccents() {
        int prefijo = mask(4,6);

        map.put("á", prefijo);
        map.put("é", prefijo);
        map.put("í", prefijo);
        map.put("ó", prefijo);
        map.put("ú", prefijo);
    }

    // ===========================
    //   SIGNOS DE PUNTUACIÓN
    // ===========================

    /**
     * Carga puntuación estándar del Braille español.
     */
    private void initPunctuation() {
        map.put(",", mask(2));
        map.put(";", mask(2,3));
        map.put(":", mask(2,5));
        map.put(".", mask(2,5,6));
        map.put("?", mask(2,3,6));
        map.put("¿", mask(2,3,6));
        map.put("!", mask(2,3,5));
        map.put("¡", mask(2,3,5));
        map.put("-", mask(3,6));
        map.put("(", mask(5,6));
        map.put(")", mask(5,6));
        map.put(" ", 0);
    }

    // ===========================
    //   NÚMEROS
    // ===========================

    /**
     * Carga números siguiendo: signo número + serie a–j.
     */
    private void initNumbers() {
        map.put("#", SIGNO_NUMERO);

        map.put("1", map.get("a"));
        map.put("2", map.get("b"));
        map.put("3", map.get("c"));
        map.put("4", map.get("d"));
        map.put("5", map.get("e"));
        map.put("6", map.get("f"));
        map.put("7", map.get("g"));
        map.put("8", map.get("h"));
        map.put("9", map.get("i"));
        map.put("0", map.get("j"));
    }

    // ===========================
    //   UTILIDADES
    // ===========================

    /**
     * Crea una máscara Braille activando los puntos especificados.
     *
     * @param dots puntos a activar (1–6)
     * @return entero que representa la celda Braille Unicode
     */
    private int mask(int... dots) {
        int m = 0;
        for (int d : dots) {
            m |= (1 << (d - 1));
        }
        return m;
    }

    /**
     * Añade un punto a una celda Braille existente.
     *
     * @param base máscara original
     * @param dot punto a añadir
     * @return máscara nueva con el punto agregado
     */
    private int addDot(int base, int dot) {
        return base | (1 << (dot - 1));
    }

    /**
     * Convierte una máscara Braille a su símbolo Unicode.
     *
     * @param mask máscara Braille
     * @return carácter Unicode Braille
     */
    public String maskToUnicode(int mask) {
        return String.valueOf((char) (0x2800 + mask));
    }

    // ================================================================
    //  NUEVO: DETECCIÓN DE PALABRAS COMPLETAS EN MAYÚSCULAS (SIGLAS)
    // ================================================================
    private boolean isFullUppercaseWord(String word) {
        return word.length() > 1 && word.equals(word.toUpperCase());
    }

    /**
     * Transcribe un texto español a Braille Unicode.
     * El proceso incluye:
     * <ul>
     *     <li>Conversión de mayúsculas.</li>
     *     <li>Números con signo inicial.</li>
     *     <li>Puntuación.</li>
     *     <li>Normalización de espacios múltiples.</li>
     * </ul>
     *
     * @param texto texto plano de entrada
     * @return cadena en Braille Unicode
     */
    public String transcribir(String texto) {
        if (texto.trim().isEmpty()) {
            return maskToUnicode(0);
        }

        texto = texto.replaceAll("\\s+", " ");

        StringBuilder sb = new StringBuilder();
        String[] palabras = texto.split(" ");

        for (int i = 0; i < palabras.length; i++) {

            String palabra = palabras[i];

            boolean esNumero = palabra.matches("\\d+");
            boolean esMayusCompleta = !esNumero && isFullUppercaseWord(palabra);

            if (esMayusCompleta) {
                sb.append(maskToUnicode(SIGNO_MAYUSCULA));
                sb.append(maskToUnicode(SIGNO_MAYUSCULA));
            }

            boolean inNumber = false;

            for (char c : palabra.toCharArray()) {
                String ch = String.valueOf(c);

                if (!esNumero && !esMayusCompleta && Character.isUpperCase(c)) {
                    sb.append(maskToUnicode(SIGNO_MAYUSCULA));
                    ch = ch.toLowerCase();
                }
                if (ch.matches("\\d")) {
                    if (!inNumber) {
                        sb.append(maskToUnicode(SIGNO_NUMERO));
                        inNumber = true;
                    }
                    sb.append(maskToUnicode(map.get(ch)));
                    continue;
                }

                inNumber = false;

                if (map.containsKey(ch.toLowerCase())) {
                    sb.append(maskToUnicode(map.get(ch.toLowerCase())));
                } else {
                    sb.append(" ");
                }
            }
            if (i < palabras.length - 1) {
                sb.append(maskToUnicode(0));
            }
        }

        return sb.toString();
    }
}
