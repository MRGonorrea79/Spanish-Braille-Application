package ec.epn.edu.proyectoconstruccion.service;

public class OtherBasesTransformationService {

    // Binario a Decimal
    public int binarioADecimal(String binario) {
        return Integer.parseInt(binario, 2);
    }

    // Octal a Decimal
    public int octalADecimal(String octal) {
        return Integer.parseInt(octal, 8);
    }

    // Hexadecimal a Decimal
    public int hexadecimalADecimal(String hexadecimal) {
        return Integer.parseInt(hexadecimal, 16);
    }

}