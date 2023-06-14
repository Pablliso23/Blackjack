import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Carta> cartas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.cartas = new ArrayList<>();
    }

    public void recibirCarta(Carta carta) {
        cartas.add(carta);
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public int getPuntaje() {
        int puntaje = 0;
        int numAses = 0;

        for (Carta carta : cartas) {
            if (carta.getNumero() >= 10) {
                puntaje += 10;
            } else if (carta.getNumero() == 1) {
                puntaje += 11;
                numAses++;
            } else {
                puntaje += carta.getNumero();
            }
        }

        while (puntaje > 21 && numAses > 0) {
            puntaje -= 10;
            numAses--;
        }

        return puntaje;
    }

    public String getNombre() {
        return nombre;
    }
}
