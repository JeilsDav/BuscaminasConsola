package controlador;

import modelo.Tablero;
import vista.ConsolaVista;
import modelo.Jugador;
import excepciones.CasillaYaDescubiertaException;

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
}