package ec.epn.edu.proyectoconstruccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p><b>ProyectoConstruccionApplication</b></p>
 *
 * <p>Clase principal del proyecto Spring Boot para el sistema de
 * conversión de bases numéricas, aritmética binaria y complemento a dos.
 * Esta clase actúa como punto de entrada de la aplicación y se encarga
 * de inicializar el contenedor Spring, cargar los componentes,
 * configurar el contexto de la aplicación y arrancar el servidor embebido.</p>
 *
 * <p>La aplicación permite realizar operaciones relacionadas con:</p>
 * <ul>
 *     <li>Conversión entre sistemas numéricos (binario, octal, decimal y hexadecimal).</li>
 *     <li>Representación de números utilizando complemento a dos.</li>
 *     <li>Operaciones de aritmética binaria.</li>
 *     <li>Procesos de verificación y representación numérica.</li>
 * </ul>
 *
 * <p>La anotación {@link SpringBootApplication} habilita:</p>
 * <ul>
 *     <li>Escaneo automático de componentes (<i>component scan</i>).</li>
 *     <li>Configuración automática (<i>auto-configuration</i>).</li>
 *     <li>Registro y gestión del contexto de la aplicación Spring.</li>
 * </ul>
 *
 * <p>Última actualización: <b>08/05/2026</b></p>
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
     * donde operará el sistema de conversión y representación numérica.</p>
     *
     * @param args argumentos de línea de comandos (opcionalmente vacíos)
     */
    public static void main(String[] args) {
        SpringApplication.run(ProyectoConstruccionApplication.class, args);
    }

}
