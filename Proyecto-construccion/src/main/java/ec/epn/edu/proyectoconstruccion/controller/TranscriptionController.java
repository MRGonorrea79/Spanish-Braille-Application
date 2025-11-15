package ec.epn.edu.proyectoconstruccion.controller;

import ec.epn.edu.proyectoconstruccion.service.BrailleMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p><b>TranscriptionController</b></p>
 *
 * <p>Controlador encargado de gestionar las solicitudes relacionadas con la
 * transcripción de texto plano a Braille Unicode dentro de la aplicación web.</p>
 *
 * <p>Funciones principales:</p>
 * <ul>
 *     <li>Mostrar la página principal del transcriptor.</li>
 *     <li>Recibir texto enviado desde un formulario.</li>
 *     <li>Enviar el texto procesado en Braille hacia la vista de resultados.</li>
 * </ul>
 *
 * <p>Este controlador utiliza {@link BrailleMapper} para realizar la lógica de
 * conversión entre caracteres del alfabeto español y su equivalente en Braille.</p>
 *
 * <p>Última actualización: <b>15/11/2025</b></p>
 *
 * @author Grupo 7
 */
@Controller
public class TranscriptionController {

    /** Servicio encargado de mapear texto plano a Braille Unicode. */
    private final BrailleMapper brailleMapper = new BrailleMapper();

    /**
     * Muestra la página principal del transcriptor.
     *
     * <p>Responde a solicitudes GET en la raíz del sistema y retorna la vista
     * base donde el usuario puede ingresar texto para transcribir.</p>
     *
     * @return nombre de la plantilla Thymeleaf correspondiente a la página inicial
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Procesa el texto ingresado por el usuario y lo convierte en Braille Unicode.
     *
     * <p>El texto recibido desde el formulario se envía al servicio
     * {@link BrailleMapper#transcribir(String)}, y el resultado se envía al modelo
     * para ser presentado en la vista correspondiente.</p>
     *
     * @param texto texto plano ingresado por el usuario
     * @param model objeto utilizado para enviar atributos a la vista Thymeleaf
     * @return nombre de la plantilla Thymeleaf donde se muestra el resultado
     */
    @PostMapping("/transcribir")
    public String transcribir(@RequestParam("texto") String texto, Model model) {

        String braille = brailleMapper.transcribir(texto);

        model.addAttribute("textoOriginal", texto);
        model.addAttribute("resultadoBraille", braille);

        return "result";
    }
}

