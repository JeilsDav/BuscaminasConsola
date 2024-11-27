package vista;

import modelo.Tablero;
import modelo.Jugador;

public class ConsolaVista {
    public void mostrarTablero(Tablero tablero) {
        tablero.mostrarTablero();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarJugador(Jugador jugador) {
        System.out.println("Jugador: " + jugador.getNombre() + ", Minas Descubiertas: " + jugador.getMinasDescubiertas());
    }
}
