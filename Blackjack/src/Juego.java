import java.util.Scanner;

public class Juego {
    private Jugador jugador;
    private Crupier crupier;
    private Baraja baraja;

    public Juego(String nombreJugador, String nombreCrupier) {
        jugador = new Jugador(nombreJugador);
        crupier = new Crupier(nombreCrupier);
        baraja = new Baraja();
    }
    
    public String getGanador() {
        int puntajeJugador = jugador.getPuntaje();
        int puntajeCrupier = crupier.getPuntaje();

        if (puntajeJugador > 21 || (puntajeCrupier <= 21 && puntajeCrupier > puntajeJugador)) {
            return crupier.getNombre();
        } else if (puntajeCrupier > 21 || (puntajeJugador <= 21 && puntajeJugador > puntajeCrupier)) {
            return jugador.getNombre();
        } else {
            return "Empate";
        }
    }


    public void jugar() {
        // Repartir cartas iniciales
        jugador.recibirCarta(baraja.sacarCarta());
        crupier.recibirCarta(baraja.sacarCarta());
        jugador.recibirCarta(baraja.sacarCarta());
        crupier.recibirCarta(baraja.sacarCarta());

        // Mostrar las cartas del jugador y la carta visible del crupier
        System.out.println("Cartas del jugador: " + jugador.getCartas());
        System.out.println("Puntuación total del jugador: " + jugador.getPuntaje());
        System.out.println("Carta visible del crupier: " + crupier.getCartaVisible());
        System.out.println("Puntuacion total: "+ crupier.getPuntaje());

        // Pedir decisiones al jugador
        Scanner scanner = new Scanner(System.in);
        String decision;
        while (true) {
            System.out.print("¿Deseas tomar otra carta? (s/n): ");
            decision = scanner.nextLine().trim().toLowerCase();

            if (decision.equals("s")) {
                jugador.recibirCarta(baraja.sacarCarta());
                System.out.println("Cartas del jugador: " + jugador.getCartas());
                System.out.println("Puntuacion total: "+ jugador.getPuntaje());

                if (jugador.getPuntaje() > 21) {
                    System.out.println("El jugador ha perdido. Puntaje: " + jugador.getPuntaje());
                    return;
                }
            } else if (decision.equals("n")) {
                break;
            }
        }

        // Jugar la mano del crupier
        while (crupier.getPuntaje() < 17) {
            crupier.recibirCarta(baraja.sacarCarta());
        }

        // Mostrar las cartas del crupier
        System.out.println("Cartas del crupier: " + crupier.getCartaVisible());

        // Determinar el ganador
        int puntajeJugador = jugador.getPuntaje();
        int puntajeCrupier = crupier.getPuntaje();

        System.out.println("Puntaje del jugador: " + puntajeJugador);
        System.out.println("Puntaje del crupier: " + puntajeCrupier);

        if (puntajeJugador > puntajeCrupier || puntajeCrupier > 21) {
            System.out.println("El jugador gana!");
        } else if (puntajeJugador < puntajeCrupier) {
            System.out.println("El crupier gana!");
        } else {
            System.out.println("Es un empate!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del jugador: ");
        String nombreJugador = scanner.nextLine();
        System.out.print("Ingrese el nombre del crupier: ");
        String nombreCrupier = scanner.nextLine();

        Juego juego = new Juego(nombreJugador, nombreCrupier);
        juego.jugar();
    }
}
