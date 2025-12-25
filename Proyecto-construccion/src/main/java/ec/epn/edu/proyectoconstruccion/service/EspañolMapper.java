package ec.epn.edu.proyectoconstruccion.service;

import java.util.*;

/**
 * <p><b>BrailleTranscriptorService</b></p>
 *
 * <p>Servicio encargado de realizar la transcripción de texto
 * en Braille Unicode al idioma español, así como la conversión
 * del Braille a su representación en espejo para facilitar
 * la escritura manual.</p>
 *
 * <p>El servicio contempla:</p>
 * <ul>
 *     <li>Letras del alfabeto español.</li>
 *     <li>Vocales acentuadas.</li>
 *     <li>Letras especiales (ñ, ü).</li>
 *     <li>Números con signo Braille.</li>
 *     <li>Puntuación básica.</li>
 *     <li>Espacios y separadores.</li>
 * </ul>
 *
 * <p>La implementación se basa en la codificación oficial
 * del Braille español usando Unicode (U+2800).</p>
 *
 * <p><b>Auditoría:</b> Grupo 7</p>
 *
 * @author Grupo 7
 */
public class EspañolMapper {


    private final Map<Integer, String> reverseMap = new HashMap<>();

    // ⠼  signo de número
    private final int SIGNO_NUMERO = mask(3,4,5,6);

    // ⠠  signo de mayúscula
    private final int SIGNO_MAYUSCULA = mask(4,6);

    // ===========================
    //   MAPEO INVERSO
    // ===========================

    private void initReverseMap() {

        // ===========================
        // LETRAS MINÚSCULAS
        // ===========================
        put("a", mask(1));
        put("b", mask(1,2));
        put("c", mask(1,4));
        put("d", mask(1,4,5));
        put("e", mask(1,5));
        put("f", mask(1,2,4));
        put("g", mask(1,2,4,5));
        put("h", mask(1,2,5));
        put("i", mask(2,4));
        put("j", mask(2,4,5));

        put("k", mask(1,3));
        put("l", mask(1,2,3));
        put("m", mask(1,3,4));
        put("n", mask(1,3,4,5));
        put("ñ", mask(1,2,4,5,6));
        put("o", mask(1,3,5));
        put("p", mask(1,2,3,4));
        put("q", mask(1,2,3,4,5));
        put("r", mask(1,2,3,5));
        put("s", mask(2,3,4));
        put("t", mask(2,3,4,5));

        put("u", mask(1,3,6));
        put("v", mask(1,2,3,6));
        put("w", mask(2,4,5,6));
        put("x", mask(1,3,4,6));
        put("y", mask(1,3,4,5,6));
        put("z", mask(1,3,5,6));

        // ===========================
        // VOCALES ACENTUADAS
        // ===========================
        put("á", mask(1,2,3,5,6));
        put("é", mask(2,3,4,6));
        put("í", mask(3,4));
        put("ó", mask(3,4,6));
        put("ú", mask(2,3,4,5,6));
        put("ü", mask(1,2,5,6));

        // ===========================
        // SIGNOS DE PUNTUACIÓN
        // ===========================
        put(".", mask(3));
        put(",", mask(2));
        put(";", mask(2,3));
        put(":", mask(2,5));
        put("?", mask(2,6));
        put("!", mask(2,3,5));
        put("\"", mask(2,3,6));
        put("(", mask(1,2,6));
        put(")", mask(3,4,5));

        // ===========================
        // SIGNOS AUXILIARES
        // ===========================
        put("-", mask(3,6));
        put("*", mask(3,5));
        put("+", mask(2,3,5));
        put("=", mask(2,3,5,6));
        put("/", mask(2));
        put("&", mask(1,2,3,4,6));
        put("@", mask(5));
        put("%", mask(4,5,6));
        put("^", mask(4,5));

        // ===========================
    }
    public EspañolMapper() {
        initReverseMap();
    }

    public String brailleAEspanol(String textoBraille) {
        StringBuilder resultado = new StringBuilder();

        boolean modoNumero = false;
        boolean siguienteMayuscula = false;
        boolean mayusculaPalabra = false;

        for (int i = 0; i < textoBraille.length(); i++) {
            char c = textoBraille.charAt(i);

            if (c == ' ') {
                resultado.append(' ');
                modoNumero = false;
                siguienteMayuscula = false;
                mayusculaPalabra = false;
                continue;
            }

            int mask = brailleCharToMask(c);

            if (mask == SIGNO_NUMERO) {
                modoNumero = true;
                continue;
            }

            if (mask == SIGNO_MAYUSCULA) {
                if (i + 1 < textoBraille.length()
                        && brailleCharToMask(textoBraille.charAt(i + 1)) == SIGNO_MAYUSCULA) {
                    mayusculaPalabra = true;
                    siguienteMayuscula = false;
                    i++;
                } else {
                    siguienteMayuscula = true;
                }
                continue;
            }

            String valor = reverseMap.get(mask);

            if (valor == null) {
                resultado.append('?');
                modoNumero = false;
                siguienteMayuscula = false;
                mayusculaPalabra = false;
                continue;
            }

            if (modoNumero) {
                String numero = letraANumero(valor);
                if (numero != null) {
                    resultado.append(numero);
                    continue;
                } else {
                    modoNumero = false;
                }
            }

            if (mayusculaPalabra) {
                resultado.append(valor.toUpperCase());
            } else if (siguienteMayuscula) {
                resultado.append(valor.toUpperCase());
                siguienteMayuscula = false;
            } else {
                resultado.append(valor);
            }
        }

        return resultado.toString();
    }



    // ===========================
    //   BRAILLE → MÁSCARA
    // ===========================

    private int brailleCharToMask(char braille) {
        int value = braille - 0x2800;
        int mask = 0;

        for (int dot = 1; dot <= 6; dot++) {
            if ((value & (1 << (dot - 1))) != 0) {
                mask |= 1 << (dot - 1);
            }
        }
        return mask;
    }

    private static int mask(int... puntos) {
        int m = 0;
        for (int p : puntos) {
            m |= 1 << (p - 1);
        }
        return m;
    }

    private void put(String c, int mask) {
        reverseMap.put(mask, c);
    }

    private String letraANumero(String letra) {
        switch (letra) {
            case "a": return "1";
            case "b": return "2";
            case "c": return "3";
            case "d": return "4";
            case "e": return "5";
            case "f": return "6";
            case "g": return "7";
            case "h": return "8";
            case "i": return "9";
            case "j": return "0";
            default: return null;
        }
    }

}
