package principal;

import controlador.JuegoControlador;
import modelo.Jugador;

public class Principal {
    public static void main(String[] args) {
        // Crear un jugador con su nombre
        Jugador jugador = new Jugador("Jugador1");

        // Crear el controlador del juego
        JuegoControlador juegoControlador = new JuegoControlador(jugador);

        // Iniciar el juego
        juegoControlador.jugar();
    }
}
