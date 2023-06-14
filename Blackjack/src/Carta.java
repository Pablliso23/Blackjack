public class Carta {
    private int numero;
    private String palo;

    public static final String[] PALOS = {"DIAMANTES", "TREBOLES", "CORAZONES", "PICAS"};
    public static final int LIM_CARTAS = 13;

    public Carta(int numero, String palo) {
        if (numero < 1 || numero > LIM_CARTAS) {
            throw new IllegalArgumentException("Número de carta inválido");
        }
        if (!validarPalo(palo)) {
            throw new IllegalArgumentException("Palo de carta inválido");
        }

        this.numero = numero;
        this.palo = palo;
    }

    public int getNumero() {
        return numero;
    }

    public String getPalo() {
        return palo;
    }

    public int getValor() {
        if (numero >= 10) {
            return 10;
        } else {
            return numero;
        }
    }

    @Override
    public String toString() {
        return "Número: " + obtenerNombreNumero() + ", Palo: " + palo;
    }

    private boolean validarPalo(String palo) {
        for (String p : PALOS) {
            if (p.equalsIgnoreCase(palo)) {
                return true;
            }
        }
        return false;
    }

    private String obtenerNombreNumero() {
        switch (numero) {
            case 1:
                return "AS";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            default:
                return String.valueOf(numero);
        }
    }
}

