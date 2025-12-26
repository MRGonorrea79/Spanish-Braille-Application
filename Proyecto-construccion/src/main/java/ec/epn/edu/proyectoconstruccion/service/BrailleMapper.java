package ec.epn.edu.proyectoconstruccion.service;

import java.util.*;

/**
 * Servicio bidireccional de traducción Braille ⇄ Español.
 *
 * - Español → Braille Unicode
 * - Braille Unicode → Español
 *
 * Basado en el estándar oficial del Braille español (Unicode U+2800).
 *
 * Auditoría: Grupo 7
 */
public class BrailleMapper {

    // =====================================================
    //  CONSTANTES BRAILLE
    // =====================================================

    private static final int SIGNO_NUMERO = mask(3,4,5,6);    // ⠼
    private static final int SIGNO_MAYUSCULA = mask(4,6);     // ⠠
    private static final int SIGNO_NUMERO_ESPEJO = mask(1,2,3,6);
    private static final int SIGNO_MAYUSCULA_ESPEJO = mask(1,3);
    // =====================================================
    //  MAPAS
    // =====================================================

    private final BrailleDictionary dictionary = new BrailleDictionary();

    private final Map<String, Integer> map = dictionary.getMap();
    private final Map<Integer, String> reverseMap = dictionary.getReverseMap();
    private final Map<String, Integer> pam = dictionary.getMapEspejo();


    // =====================================================
    //  CONSTRUCTOR
    // =====================================================

    public BrailleMapper() {}

    // =====================================================
    //  ESPAÑOL → BRAILLE
    // =====================================================

    public String españolABraille(String texto) {
        if (texto.trim().isEmpty()) {
            return maskToUnicode(0);
        }

        texto = texto.replaceAll("\\s+", " ");

        StringBuilder sb = new StringBuilder();
        String[] palabras = texto.split(" ");

        for (int i = 0; i < palabras.length; i++) {

            String palabra = palabras[i];

            boolean esNumeroExtendido = palabra.matches("(?=.*\\d)[^A-Za-z]+");
            boolean esNumero = esNumeroExtendido;
            boolean esMayusCompleta = !esNumero && isFullUppercaseWord(palabra);

            if (esMayusCompleta) {
                sb.append(maskToUnicode(SIGNO_MAYUSCULA));
                sb.append(maskToUnicode(SIGNO_MAYUSCULA));
            }

            boolean inNumber = false;

            for (char c : palabra.toCharArray()) {
                String ch = String.valueOf(c);

                if (esNumero) {

                    if (Character.isDigit(c)) {
                        if (!inNumber) {
                            sb.append(maskToUnicode(SIGNO_NUMERO));
                            inNumber = true;
                        }
                        sb.append(maskToUnicode(map.get(ch)));
                        continue;
                    }

                    if (map.containsKey(ch)) {
                        sb.append(maskToUnicode(map.get(ch)));
                        inNumber = false;
                        continue;
                    }

                    sb.append(" ");
                    inNumber = false;
                    continue;
                }

                if (!esMayusCompleta && Character.isUpperCase(c)) {
                    sb.append(maskToUnicode(SIGNO_MAYUSCULA));
                    ch = ch.toLowerCase();
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


    // =====================================================
    //  BRAILLE → ESPAÑOL
    // =====================================================

    public String brailleAEspañol(String textoBraille) {
        StringBuilder resultado = new StringBuilder();

        boolean modoNumero = false;
        boolean siguienteMayuscula = false;
        boolean mayusculaPalabra = false;

        for (int i = 0; i < textoBraille.length(); i++) {
            char c = textoBraille.charAt(i);

            if (c == ' ' || c == '\u2800') {
                resultado.append(' ');
                modoNumero = false;
                siguienteMayuscula = false;
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

    public String españolABrailleEspejo(String texto) {
        if (texto.trim().isEmpty()) {
            return maskToUnicode(0);
        }

        texto = texto.replaceAll("\\s+", " ");

        StringBuilder sb = new StringBuilder();
        String[] palabras = texto.split(" ");

        for (int i = 0; i < palabras.length; i++) {

            String palabra = palabras[i];

            boolean esNumeroExtendido = palabra.matches("(?=.*\\d)[^A-Za-z]+");
            boolean esNumero = esNumeroExtendido;
            boolean esMayusCompleta = !esNumero && isFullUppercaseWord(palabra);

            if (esMayusCompleta) {
                sb.append(maskToUnicode(SIGNO_MAYUSCULA_ESPEJO));
                sb.append(maskToUnicode(SIGNO_MAYUSCULA_ESPEJO));
            }

            boolean inNumber = false;

            for (char c : palabra.toCharArray()) {
                String ch = String.valueOf(c);

                if (esNumero) {

                    if (Character.isDigit(c)) {
                        if (!inNumber) {
                            sb.append(maskToUnicode(SIGNO_NUMERO_ESPEJO));
                            inNumber = true;
                        }
                        sb.append(maskToUnicode(pam.get(ch)));
                        continue;
                    }

                    if (pam.containsKey(ch)) {
                        sb.append(maskToUnicode(pam.get(ch)));
                        inNumber = false;
                        continue;
                    }

                    sb.append(" ");
                    inNumber = false;
                    continue;
                }

                if (!esMayusCompleta && Character.isUpperCase(c)) {
                    sb.append(maskToUnicode(SIGNO_MAYUSCULA_ESPEJO));
                    ch = ch.toLowerCase();
                }

                inNumber = false;

                if (pam.containsKey(ch.toLowerCase())) {
                    sb.append(maskToUnicode(pam.get(ch.toLowerCase())));
                } else {
                    sb.append(" ");
                }
            }

            if (i < palabras.length - 1) {
                sb.append(maskToUnicode(0));
            }
        }
        sb.reverse();
        return sb.toString();
    }



    // =====================================================
    //  UTILIDADES
    // =====================================================

    private static int mask(int... dots) {
        int m = 0;
        for (int d : dots) m |= (1 << (d - 1));
        return m;
    }

    private static int addDot(int base, int dot) {
        return base | (1 << (dot - 1));
    }

    private static String maskToUnicode(int mask) {
        return String.valueOf((char) (0x2800 + mask));
    }
    private boolean isFullUppercaseWord(String word) {
        return word.length() > 1 && word.equals(word.toUpperCase());
    }
    private static int brailleCharToMask(char braille) {
        return braille - 0x2800;
    }

    private static String letraANumero(String l) {
        switch (l) {
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
