package ec.epn.edu.proyectoconstruccion.service;

public class DecimalTransformationService {

    // Decimal a Binario
    public String decimalABinario(int numero) {
        return Integer.toBinaryString(numero);
    }

    // Decimal a Octal
    public String decimalAOctal(int numero) {
        return Integer.toOctalString(numero);
    }

    // Decimal a Hexadecimal
    public String decimalAHexadecimal(int numero) {
        return Integer.toHexString(numero).toUpperCase();
    }

}