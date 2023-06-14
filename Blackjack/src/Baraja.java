import java.util.Random;

public class Baraja {
    private Carta[] cartas;
    private int posSigCarta;

    public static final int NUM_TOTAL_CARTAS = 52;

    public Baraja() {
        cartas = new Carta[NUM_TOTAL_CARTAS];
        posSigCarta = 0;
        crearBaraja();
        barajar();
    }

    private void crearBaraja() {
        String[] palos = Carta.PALOS;
        int limite = Carta.LIM_CARTAS;

        for (int i = 0; i < palos.length; i++) {
            for (int j = 0; j < limite; j++) {
                cartas[j + limite * i] = new Carta(j + 1, palos[i]);
            }
        }
    }

    private void barajar() {
        Random random = new Random();
        for (int i = 0; i < NUM_TOTAL_CARTAS; i++) {
            int j = random.nextInt(NUM_TOTAL_CARTAS);
            Carta temp = cartas[i];
            cartas[i] = cartas[j];
            cartas[j] = temp;
        }
    }

    public Carta sacarCarta() {
        if (posSigCarta >= NUM_TOTAL_CARTAS) {
            throw new IllegalStateException("No quedan cartas en la baraja");
        }
        return cartas[posSigCarta++];
    }
}
