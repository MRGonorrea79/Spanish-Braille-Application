package ec.epn.edu.proyectoconstruccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p><b>ProyectoConstruccionApplication</b></p>
 *
 * <p>Clase principal del proyecto Spring Boot para el sistema de transcripción
 * Español → Braille. Esta clase actúa como punto de entrada de la aplicación y
 * se encarga de inicializar el contenedor Spring, cargar los componentes,
 * configurar el contexto de aplicación y arrancar el servidor embebido.</p>
 *
 * <p>La anotación {@link SpringBootApplication} habilita:</p>
 * <ul>
 *     <li>Escaneo automático de componentes (<i>component scan</i>).</li>
 *     <li>Configuración automática (<i>auto-configuration</i>).</li>
 *     <li>Registro y gestión del contexto de la aplicación Spring.</li>
 * </ul>
 *
 * <p>Última actualización: <b>15/11/2025</b></p>
 *
 * @author Grupo 7
 */
@SpringBootApplication
public class ProyectoConstruccionApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     *
     * <p>Este método invoca a {@link SpringApplication#run(Class, String...)}
     * para inicializar todo el ecosistema de Spring y desplegar el servidor web
     * donde operará el transcriptor Braille.</p>
     *
     * @param args argumentos de línea de comandos (opcionalmente vacíos)
     */
    public static void main(String[] args) {
        SpringApplication.run(ProyectoConstruccionApplication.class, args);
    }

}
