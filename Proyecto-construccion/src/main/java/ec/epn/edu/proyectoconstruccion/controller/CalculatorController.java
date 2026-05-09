package ec.epn.edu.proyectoconstruccion.controller;

import ec.epn.edu.proyectoconstruccion.service.ArithmeticService;
import ec.epn.edu.proyectoconstruccion.service.ComplementTwoService;
import ec.epn.edu.proyectoconstruccion.service.DecimalTransformationService;
import ec.epn.edu.proyectoconstruccion.service.OtherBasesTransformationService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p><b>ConversorController</b></p>
 *
 * <p>
 * Controlador encargado de gestionar:
 * </p>
 *
 * <ul>
 *     <li>Conversión decimal.</li>
 *     <li>Conversión de otras bases.</li>
 *     <li>Complemento a dos.</li>
 *     <li>Aritmética binaria.</li>
 * </ul>
 *
 * @author Grupo 7
 */

@Controller
public class CalculatorController {

    /**
     * Servicio de transformaciones decimales.
     */
    private final DecimalTransformationService decimalService = new DecimalTransformationService();

    /**
     * Servicio de otras bases.
     */
    private final OtherBasesTransformationService otherBasesService = new OtherBasesTransformationService();

    /**
     * Servicio de complemento a dos.
     */
    private final ComplementTwoService complementService = new ComplementTwoService();

    /**
     * Servicio de aritmética binaria.
     */
    private final ArithmeticService arithmeticService = new ArithmeticService();

    /**
     * Dashboard principal.
     *
     * @return vista index
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Vista fase 1.
     *
     * @return vista decimal-transformation
     */
    @GetMapping("/decimal-transformation")
    public String decimalTransformation() {
        return "decimal-transformation";
    }

    /**
     * Conversión decimal.
     *
     * @param numero número decimal
     * @param model modelo Thymeleaf
     * @return vista decimal-transformation
     */
    @PostMapping("/decimal/convertir")
    public String convertirDecimal(@RequestParam("numero") int numero, Model model) {

        model.addAttribute("binario", decimalService.decimalABinario(numero));
        model.addAttribute("octal", decimalService.decimalAOctal(numero));
        model.addAttribute("hexadecimal", decimalService.decimalAHexadecimal(numero));

        return "decimal-transformation";
    }

    /**
     * Vista fase 2.
     *
     * @return vista other-transformation
     */
    @GetMapping("/other-transformation")
    public String otherTransformation() {
        return "other-transformation";
    }

    /**
     * Conversión de otras bases a decimal.
     *
     * @param numero número ingresado
     * @param base base seleccionada
     * @param model modelo Thymeleaf
     * @return vista other-transformation
     */
    @PostMapping("/otrasBases/convertir")
    public String convertirOtrasBases(@RequestParam("numero") String numero, @RequestParam("base") int base, Model model) {

        int resultado = 0;
        if (base == 2)
            resultado = otherBasesService.binarioADecimal(numero);
        if (base == 8)
            resultado = otherBasesService.octalADecimal(numero);
        if (base == 16)
            resultado = otherBasesService.hexadecimalADecimal(numero);

        model.addAttribute("decimal", resultado);

        return "other-transformation";
    }

    /**
     * Vista complemento a dos.
     *
     * @return vista Complement-2
     */
    @GetMapping("/Complement-2")
    public String complementTwo() {
        return "Complement-2";
    }

    /**
     * Conversión a complemento a dos.
     *
     * @param numero número decimal
     * @param bits cantidad de bits
     * @param model modelo Thymeleaf
     * @return vista Complement-2
     */
    @PostMapping("/ca2/convertir")
    public String convertirComplementoDos(@RequestParam("numero") int numero, @RequestParam("bits") int bits, Model model) {

        String ca2 = complementService.decimalAComplementoDos(numero, bits);
        model.addAttribute("ca2", ca2);

        if (!ca2.contains("ERROR")) {
            model.addAttribute("decimal", complementService.complementoDosADecimal(ca2));
        }
        return "Complement-2";
    }

    /**
     * Vista aritmética binaria.
     *
     * @return vista arithmetic-operations
     */
    @GetMapping("/arithmetic-operations")
    public String arithmeticOperations() {
        return "arithmetic-operations";
    }

    /**
     * Operaciones binarias.
     *
     * @param bin1 primer binario
     * @param bin2 segundo binario
     * @param operacion operación seleccionada
     * @param model modelo Thymeleaf
     * @return vista arithmetic-operations
     */
    @PostMapping("/aritmetica/operar")
    public String operarBinarios(@RequestParam("bin1") String bin1, @RequestParam("bin2") String bin2, @RequestParam("operacion") String operacion, Model model) {

        String resultado = "";
        if (operacion.equals("suma"))
            resultado = arithmeticService.sumaBinaria(bin1, bin2);

        if (operacion.equals("resta"))
            resultado = arithmeticService.restaBinaria(bin1, bin2);

        model.addAttribute("resultado", resultado);

        return "arithmetic-operations";
    }

}