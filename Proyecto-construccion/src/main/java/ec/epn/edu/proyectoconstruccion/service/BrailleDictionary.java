package ec.epn.edu.proyectoconstruccion.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BrailleDictionary {

    private static final int SIGNO_NUMERO = mask(3,4,5,6);
    private static final int SIGNO_NUMERO_ESPEJO = mask(1,2,3,6);

    private final Map<String, Integer> map = new HashMap<>();
    private final Map<Integer, String> reverseMap = new HashMap<>();
    private final Map<String, Integer> pam = new HashMap<>();

    public BrailleDictionary() {
        initLetters();
        initAccents();
        initPunctuation();
        initNumbers();
        initReverseMap();
    }

    // ===========================
    //  GETTERS (SOLO LECTURA)
    // ===========================

    public Map<String, Integer> getMap() {
        return Collections.unmodifiableMap(map);
    }
    public Map<String, Integer> getMapEspejo() {
        return Collections.unmodifiableMap(pam);
    }

    public Map<Integer, String> getReverseMap() {
        return Collections.unmodifiableMap(reverseMap);
    }

    // ===========================
    //  INICIALIZACIÓN
    // ===========================

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

        pam.put("a", mask(4));
        pam.put("b", mask(4,5));
        pam.put("c", mask(1,4));
        pam.put("d", mask(1,2,4));
        pam.put("e", mask(2,4));
        pam.put("f", mask(1,4,5));
        pam.put("g", mask(1,2,4,5));
        pam.put("h", mask(2,4,5));
        pam.put("i", mask(1,5));
        pam.put("j", mask(2,4,5));

        map.put("k", addDot(map.get("a"), 3));
        map.put("l", addDot(map.get("b"), 3));
        map.put("m", addDot(map.get("c"), 3));
        map.put("n", addDot(map.get("d"), 3));
        map.put("o", addDot(map.get("e"), 3));
        map.put("p", addDot(map.get("f"), 3));
        map.put("q", addDot(map.get("g"), 3));
        map.put("r", addDot(map.get("h"), 3));
        map.put("s", addDot(map.get("i"), 3));

        pam.put("k", addDot(pam.get("a"), 6));
        pam.put("l", addDot(pam.get("b"), 6));
        pam.put("m", addDot(pam.get("c"), 6));
        pam.put("n", addDot(pam.get("d"), 6));
        pam.put("o", addDot(pam.get("e"), 6));
        pam.put("p", addDot(pam.get("f"), 6));
        pam.put("q", addDot(pam.get("g"), 6));
        pam.put("r", addDot(pam.get("h"), 6));
        pam.put("s", addDot(pam.get("i"), 6));

        map.put("t", addDot(map.get("j"), 3));
        map.put("u", addDot(map.get("k"), 6));
        map.put("v", addDot(map.get("l"), 6));
        map.put("x", addDot(map.get("m"), 6));
        map.put("y", addDot(map.get("n"), 6));
        map.put("z", addDot(map.get("o"), 6));

        map.put("ñ", mask(1,2,4,5,6));
        map.put("ü", mask(1,2,5,6));

        pam.put("t", addDot(pam.get("j"), 6));
        pam.put("u", addDot(pam.get("k"), 3));
        pam.put("v", addDot(pam.get("l"), 3));
        pam.put("x", addDot(pam.get("m"), 3));
        pam.put("y", addDot(pam.get("n"), 3));
        pam.put("z", addDot(pam.get("o"), 3));

        pam.put("ñ", mask(1,2,4,5,3));
        pam.put("ü", mask(4,2,5,3));
    }

    private void initAccents() {
        map.put("á", mask(1,2,3,5,6));
        map.put("é", mask(2,3,4,6));
        map.put("í", mask(3,4));
        map.put("ó", mask(3,4,6));
        map.put("ú", mask(2,3,4,5,6));

        pam.put("á", mask(2,3,4,5,6));
        pam.put("é", mask(1,3,5,6));
        pam.put("í", mask(1,6));
        pam.put("ó", mask(3,1,6));
        pam.put("ú", mask(2,3,1,5,6));
    }

    private void initPunctuation() {
        map.put(",", mask(2));
        map.put(";", mask(2,3));
        map.put(":", mask(2,5));
        map.put(".", mask(3));
        map.put("?", mask(2,6));
        map.put("!", mask(2,3,5));
        map.put("-", mask(3,6));
        map.put("(", mask(1,2,6));
        map.put(")", mask(3,4,5));
        map.put("+", mask(2,3,5));
        map.put("*", mask(3,5));
        map.put("=", mask(2,3,5,6));
        map.put(" ", 0);

        pam.put(",", mask(5));
        pam.put(";", mask(5,6));
        pam.put(":", mask(2,5));
        pam.put(".", mask(6));
        pam.put("?", mask(5,3));
        pam.put("!", mask(2,5,6));
        pam.put("-", mask(3,6));
        pam.put("(", mask(3,4,5));
        pam.put(")", mask(1,2,6));
        pam.put("+", mask(2,5,6));
        pam.put("*", mask(2,3,6));
        pam.put("=", mask(2,3,5,6));
        pam.put(" ", 0);
    }

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

        pam.put("#", SIGNO_NUMERO_ESPEJO);
        pam.put("1", pam.get("a"));
        pam.put("2", pam.get("b"));
        pam.put("3", pam.get("c"));
        pam.put("4", pam.get("d"));
        pam.put("5", pam.get("e"));
        pam.put("6", pam.get("f"));
        pam.put("7", pam.get("g"));
        pam.put("8", pam.get("h"));
        pam.put("9", pam.get("i"));
        pam.put("0", pam.get("j"));
    }

    private void initReverseMap() {
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            reverseMap.put(e.getValue(), e.getKey());
        }
    }

    // ===========================
    //  UTILIDADES
    // ===========================

    private static int mask(int... dots) {
        int m = 0;
        for (int d : dots) {
            m |= (1 << (d - 1));
        }
        return m;
    }

    private static int addDot(int base, int dot) {
        return base | (1 << (dot - 1));
    }
}
