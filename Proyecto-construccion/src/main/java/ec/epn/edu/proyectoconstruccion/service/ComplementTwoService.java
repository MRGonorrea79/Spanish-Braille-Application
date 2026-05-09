package ec.epn.edu.proyectoconstruccion.service;

/**
 * <p><b>ComplementTwoService</b></p>
 *
 * <p>Servicio encargado de realizar operaciones relacionadas con la
 * representación de números binarios utilizando el método de
 * Complemento a Dos (Ca2).</p>
 *
 * <p>Las funcionalidades implementadas incluyen:</p>
 * <ul>
 *     <li>Conversión de números decimales a Complemento a Dos.</li>
 *     <li>Obtención del Complemento a Uno (C1).</li>
 *     <li>Reconversión de un número en Ca2 a decimal.</li>
 *     <li>Validación de overflow según la cantidad de bits.</li>
 * </ul>
 *
 * <p>La implementación cumple con el proceso académico solicitado:</p>
 * <ul>
 *     <li>Representación binaria de números positivos y negativos.</li>
 *     <li>Uso de Complemento a Uno y suma de 1 para negativos.</li>
 *     <li>Verificación de rango representable en N bits.</li>
 *     <li>Conversión inversa desde Ca2 hacia decimal.</li>
 * </ul>
 *
 * @author Grupo 7
 * @version 1.0
 */
public class ComplementTwoService {

    /**
     * Convierte un número decimal entero a su representación
     * binaria utilizando Complemento a Dos (Ca2).
     *
     * <p><b>Proceso implementado:</b></p>
     *
     * <ul>
     *     <li>Se valida si el número puede representarse en la
     *     cantidad de bits especificada.</li>
     *
     *     <li>Si el número es positivo:
     *          <ul>
     *              <li>Se convierte directamente a binario.</li>
     *              <li>Se completa con ceros a la izquierda
     *              hasta alcanzar N bits.</li>
     *          </ul>
     *     </li>
     *
     *     <li>Si el número es negativo:
     *          <ul>
     *              <li>Se calcula el valor equivalente en Ca2
     *              utilizando la suma:
     *              <code>(2^N + número)</code>.</li>
     *
     *              <li>Internamente este proceso equivale a:
     *                  <ol>
     *                      <li>Convertir el valor absoluto a binario.</li>
     *                      <li>Invertir los bits (Complemento a Uno).</li>
     *                      <li>Sumar 1 para obtener el Ca2.</li>
     *                  </ol>
     *              </li>
     *          </ul>
     *     </li>
     * </ul>
     *
     * <p>Si el valor no puede representarse en N bits,
     * se retorna un mensaje de overflow.</p>
     *
     * @param numero número decimal entero a convertir
     * @param bits cantidad de bits para la representación
     * @return representación binaria en Complemento a Dos
     *         o mensaje de error por overflow
     */
    public String decimalAComplementoDos(int numero, int bits) {
        int min = -(1 << (bits - 1));
        int max = (1 << (bits - 1)) - 1;

        if (numero < min || numero > max)
            return "ERROR: Overflow";

        String binario;
        if (numero >= 0) {

            binario = Integer.toBinaryString(numero);

            while (binario.length() < bits) {
                binario = "0" + binario;
            }

        }else{

            /*
             * Caso: número negativo
             *
             * Se obtiene el equivalente en Complemento a Dos:
             *
             * Ca2 = 2^N + número
             *
             * Esto representa automáticamente:
             * 1. Valor absoluto
             * 2. Complemento a Uno
             * 3. Suma de 1
             */
            int complemento = (1 << bits) + numero;

            binario = Integer.toBinaryString(complemento);

            if (binario.length() > bits)
                binario = binario.substring(binario.length() - bits);

        }

        return binario;
    }

    /**
     * Obtiene el Complemento a Uno (C1) de un número binario.
     *
     * <p>El proceso consiste en invertir todos los bits:</p>
     * <ul>
     *     <li>0 → 1</li>
     *     <li>1 → 0</li>
     * </ul>
     *
     * <p>Este método es utilizado como paso intermedio para
     * construir el Complemento a Dos.</p>
     *
     * @param binario cadena binaria original
     * @return cadena binaria invertida (Complemento a Uno)
     */
    public String complementoUno(String binario) {

        StringBuilder resultado = new StringBuilder();

        for (char bit : binario.toCharArray()) {
            if (bit == '0')
                resultado.append('1');
            else
                resultado.append('0');

        }
        return resultado.toString();
    }

    /**
     * Convierte un número representado en Complemento a Dos
     * nuevamente a su equivalente decimal.
     *
     * <p><b>Proceso de verificación:</b></p>
     *
     * <ul>
     *     <li>Si el bit más significativo es 0,
     *     el número es positivo.</li>
     *
     *     <li>Si el bit más significativo es 1,
     *     el número es negativo y se aplica:
     *     <code>valor - 2^N</code>.</li>
     * </ul>
     *
     * <p>Este método permite demostrar cómo revertir el
     * proceso del Complemento a Dos y recuperar el número
     * decimal original.</p>
     *
     * @param binario número binario en Complemento a Dos
     * @return valor decimal equivalente
     */
    public int complementoDosADecimal(String binario) {

        int bits = binario.length();

        if (binario.charAt(0) == '0') {
            return Integer.parseInt(binario, 2);
        }
        int valor = Integer.parseInt(binario, 2);

        /*
         * Conversión inversa:
         *
         * Decimal = valorBinario - 2^N
         */
        return valor - (1 << bits);
    }

}