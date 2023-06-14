import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InicioSesion {
    private static final String ARCHIVO_FICHAS = "fichas.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al juego de Blackjack!");

        // Iniciar sesión
        String nombreUsuario = iniciarSesion();
        if (nombreUsuario == null) {
            // Error al iniciar sesión
            return;
        }


        System.out.println("Hola, " + nombreUsuario);

        // Continuar con el juego...
    }

    private static String iniciarSesion() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa tu nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();

        // Verificar si existe un archivo de fichas para el nombre de usuario
        File archivo = new File(ARCHIVO_FICHAS);
        if (archivo.exists()) {
            try {
                // Leer el archivo y obtener las fichas guardadas para el nombre de usuario
                Scanner archivoScanner = new Scanner(archivo);
                while (archivoScanner.hasNextLine()) {
                    String linea = archivoScanner.nextLine();
                    String[] datos = linea.split(",");
                    if (datos.length == 2 && datos[0].equals(nombreUsuario)) {
                        archivoScanner.close();
                        return nombreUsuario;
                    }
                }
                archivoScanner.close();
            } catch (IOException e) {
                System.out.println("Error al leer el archivo de fichas.");
                return null;
            }
        }

        // El nombre de usuario no existe o el archivo de fichas no existe
        System.out.println("¡Nuevo usuario creado!");
        return crearUsuario(nombreUsuario);
    }

    private static String crearUsuario(String nombreUsuario) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Es tu primera vez jugando. Ingresa la cantidad de fichas iniciales: ");
        int fichas = scanner.nextInt();
        scanner.nextLine();

        try {
            // Guardar el nombre de usuario y las fichas en el archivo
            FileWriter archivoWriter = new FileWriter(ARCHIVO_FICHAS, true);
            archivoWriter.write(nombreUsuario + "," + fichas + "\n");
            archivoWriter.close();
            return nombreUsuario;
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de fichas.");
            return null;
        }
    }

    private static int obtenerFichas(String nombreUsuario) {
        try {
            // Leer el archivo y obtener las fichas para el nombre de usuario
            File archivo = new File(ARCHIVO_FICHAS);
            if (archivo.exists()) {
                Scanner archivoScanner = new Scanner(archivo);
                while (archivoScanner.hasNextLine()) {
                    String linea = archivoScanner.nextLine();
                    String[] datos = linea.split(",");
                    if (datos.length == 2 && datos[0].equals(nombreUsuario)) {
                        archivoScanner.close();
                        return Integer.parseInt(datos[1]);
                    }
                }
                archivoScanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de fichas.");
        }

        System.out.println("Error al obtener las fichas para el usuario: " + nombreUsuario);
        return -1;
    }
}
