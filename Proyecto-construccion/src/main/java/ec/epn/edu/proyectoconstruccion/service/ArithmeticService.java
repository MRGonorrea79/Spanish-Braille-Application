package ec.epn.edu.proyectoconstruccion.service;

/**
 * <p><b>ArithmeticService</b></p>
 *
 * <p>Servicio encargado de realizar operaciones de aritmética
 * binaria entre dos números representados en base 2.</p>
 *
 * <p>Las operaciones implementadas incluyen:</p>
 * <ul>
 *     <li>Suma binaria.</li>
 *     <li>Resta binaria.</li>
 * </ul>
 *
 * <p>La clase utiliza conversiones entre cadenas binarias y valores
 * enteros para efectuar las operaciones matemáticas y posteriormente
 * retornar el resultado nuevamente en formato binario.</p>
 *
 * @version 1.0
 */
public class ArithmeticService {

    /**
     * Realiza la suma de dos números binarios.
     *
     * <p>El procedimiento consiste en:</p>
     * <ol>
     *     <li>Convertir ambas cadenas binarias a enteros decimales.</li>
     *     <li>Ejecutar la operación de suma.</li>
     *     <li>Convertir el resultado nuevamente a binario.</li>
     * </ol>
     *
     * @param bin1 primer número binario
     * @param bin2 segundo número binario
     * @return resultado de la suma en formato binario
     */
    public String sumaBinaria(String bin1, String bin2) {

        int num1 = Integer.parseInt(bin1, 2);
        int num2 = Integer.parseInt(bin2, 2);

        int suma = num1 + num2;

        return Integer.toBinaryString(suma);
    }

    /**
     * Realiza la resta de dos números binarios.
     *
     * <p>El procedimiento consiste en:</p>
     * <ol>
     *     <li>Convertir ambas cadenas binarias a enteros decimales.</li>
     *     <li>Ejecutar la operación de resta.</li>
     *     <li>Convertir el resultado nuevamente a binario.</li>
     * </ol>
     *
     * <p>El resultado puede representar valores negativos
     * dependiendo de los operandos ingresados.</p>
     *
     * @param bin1 primer número binario
     * @param bin2 segundo número binario
     * @return resultado de la resta en formato binario
     */
    public String restaBinaria(String bin1, String bin2) {

        int num1 = Integer.parseInt(bin1, 2);
        int num2 = Integer.parseInt(bin2, 2);

        int resta = num1 - num2;

        return Integer.toBinaryString(resta);
    }

}
