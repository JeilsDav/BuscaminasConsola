package controlador;

import modelo.Tablero;
import vista.ConsolaVista;
import modelo.Jugador;
import excepciones.CasillaYaDescubiertaException;
import excepciones.InputInvalidoException;

public class JuegoControlador {
    private Tablero tablero;
    private ConsolaVista vista;
    private Jugador jugador;

    public JuegoControlador(Jugador jugador) {
        this.tablero = new Tablero();
        this.vista = new ConsolaVista();
        this.jugador = jugador;
    }

    public void jugar(int fila, int columna) {
        try {
            tablero.descubrir(fila, columna);
            jugador.incrementarMinasDescubiertas();
            vista.mostrarTablero(tablero);
        } catch (CasillaYaDescubiertaException e) {
            vista.mostrarMensaje(e.getMessage());
        }
    }

    public int[] convertirCoordenada(String coordenada) throws InputInvalidoException {
        // Verificar que la coordenada tenga al menos 2 caracteres y máximo 3 (A1, B10)
        if (coordenada.length() < 2 || coordenada.length() > 3) {
            throw new InputInvalidoException("Formato inválido. Usa letra y número (ejemplo: A1, B10).");
        }

        // Obtener la letra (que representa la fila)
        char letra = coordenada.charAt(0);
        // Obtener el número (que representa la columna)
        String numero = coordenada.substring(1);

        // Validar que la letra esté entre A y J (A=0, B=1... J=9)
        if (letra < 'A' || letra > 'J') {
            throw new InputInvalidoException("Fila inválida. Debe ser una letra entre A y J.");
        }

        // Validar que el número sea un número válido entre 1 y 10
        if (!numero.matches("[1-9]|10")) {
            throw new InputInvalidoException("Columna inválida. Debe ser un número entre 1 y 10.");
        }

        // Convertir la letra a un índice de fila (A=0, B=1... J=9)
        int fila = letra - 'A';

        // Convertir el número (1-10) a un índice de columna (0-9)
        int columna = Integer.parseInt(numero) - 1;

        return new int[]{fila, columna};
    }
}

