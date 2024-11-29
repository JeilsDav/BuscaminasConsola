package vista;

import modelo.Tablero;

public class ConsolaVista {

    public void mostrarTablero(Tablero tablero) {
        // Aquí se debe imprimir el tablero de forma apropiada
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(tablero.getCasilla(i, j) + " ");
            }
            System.out.println();
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
