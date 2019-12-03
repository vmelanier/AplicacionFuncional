
package aima;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import java.net.MalformedURLException;
import java.nio.file.NoSuchFileException;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class aima_app {


    private static final Logger logger = LogManager.getLogger(aima_app.class);

    public static void main(String[] args) throws FacebookException, MalformedURLException {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                logger.info("Inicializando app");
                aima_login facebookImp = new aima_login();
                // Inicio Menu
                System.out.format("Simple Facebook client \n\n");
                System.out.println("Opciones: ");
                System.out.println("(0) Publicar POST");
                System.out.println("(1) Publicar POST con URL");
                System.out.println("(2) Traer Home");
                System.out.println("(3) Traer Feed");
                System.out.println("(4) Salir");
                System.out.println("\nPor favor ingrese una opcion: ");
                // Fin de Menu
                int seleccion;
                try {
                    seleccion = scanner.nextInt();
                    scanner.nextLine();

                    switch (seleccion) {
                        case 0:
                            logger.info("publicando desde la app");
                            System.out.println("\nPor favor ingrese un Mensaje: ");
                            String Message = scanner.nextLine();
                            facebookImp.Publish(facebookImp.facebook, Message);
                            break;
                        case 1:
                            logger.info("publicando url desde la app");
                            facebookImp.PublishURL(facebookImp.facebook);
                            break;
                        case 2:
                            logger.info("Obteniendo Home desde la app");
                            facebookImp.getHome(facebookImp.facebook);
                            break;
                        case 3:
                            logger.info("Obteniendo feed desde la app");
                            System.out.println("\nPor favor ingrese el id de un Feed: ");
                            String Feed = scanner.nextLine();
                            facebookImp.getFeed(facebookImp.facebook, Feed);
                            break;
                        case 4:
                            logger.info("Finalizando app");
                            System.exit(0);
                            break;

                        default:
                            logger.error("Opcion invalida");
                            break;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Ocurrio un errror, favor de revisar log.");
                    logger.error("Opcion invalida. %s. \n", ex.getClass());
                    scanner.next();
                } catch (Exception ex) {
                    System.out.println("Ocurrio un errror, favor de revisar log.");
                    logger.error(ex);
                    scanner.next();
                }
            }
        } catch (Exception ex) {
            logger.error(ex);
        }

    }

}