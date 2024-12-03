package principal;

import controlador.JuegoControlador;
import modelo.Jugador;

public class Principal {
    public static void main(String[] args) {
        // Crear un jugador 
        Jugador jugador = new Jugador();

        // Crear el controlador del juego
        JuegoControlador juegoControlador = new JuegoControlador(jugador);

        // Iniciar el juego
        juegoControlador.jugar();
    }
}
