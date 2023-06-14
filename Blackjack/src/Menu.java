import java.util.Scanner;

public class Menu {
    private static final int FICHAS_INICIALES = 500;
    private static final int APUESTA_MINIMA = 25;

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al juego de Blackjack!");

        while (true) {
            System.out.println("\nSeleccione un modo de juego:");
            System.out.println("1. Modo de entrenamiento");
            System.out.println("2. Modo competitivo");
            System.out.println("3. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    jugarModoEntrenamiento();
                    break;
                case 2:
                    jugarModoCompetitivo();
                    break;
                case 3:
                    System.out.println("Gracias por jugar. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione nuevamente.");
                    break;
            }
        }
    }

    private void jugarModoEntrenamiento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nModo de entrenamiento");
        System.out.print("Ingrese el nombre del jugador: ");
        String nombreJugador = scanner.nextLine();
        while (true) {

            Juego juego = new Juego(nombreJugador, "Crupier");
            juego.jugar();

            System.out.print("¿Desea jugar nuevamente en modo de entrenamiento? (s/n): ");
            String decision = scanner.nextLine().trim().toLowerCase();
            if (!decision.equals("s")) {
                break;
            }
        }
    }

    private void jugarModoCompetitivo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nModo competitivo");
        int fichas = FICHAS_INICIALES;

        while (fichas > 0) {
            System.out.println("Fichas disponibles: " + fichas);
            System.out.print("Ingrese el nombre del jugador: ");
            String nombreJugador = scanner.nextLine();

            if (fichas < APUESTA_MINIMA) {
                System.out.println("No tienes suficientes fichas para apostar. El juego ha terminado.");
                break;
            }

            System.out.print("Ingrese la cantidad de fichas a apostar (mínimo " + APUESTA_MINIMA + "): ");
            int apuesta = scanner.nextInt();
            scanner.nextLine();

            if (apuesta < APUESTA_MINIMA || apuesta > fichas) {
                System.out.println("Cantidad de apuesta inválida. El juego ha terminado.");
                break;
            }

            Juego juego = new Juego(nombreJugador, "Crupier");
            juego.jugar();

            if (juego.getGanador().equals(nombreJugador)) {
                fichas += apuesta;
            } else {
                fichas -= apuesta;
            }

            System.out.println("Fichas restantes: " + fichas);
            System.out.print("¿Desea jugar nuevamente en modo competitivo? (s/n): ");
            String decision = scanner.nextLine().trim().toLowerCase();
            if (!decision.equals("s")) {
                break;
            }
        }

        System.out.println("Has quedado sin fichas. El juego ha terminado.");
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.iniciar();
    }
}
