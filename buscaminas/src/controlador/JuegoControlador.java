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

    public void jugar() {
        while (true) {
            vista.mostrarTablero(tablero);

            // Pedir acción y coordenadas
            String accion = vista.pedirAccion(); 
            String coordenada = vista.pedirCoordenada(); 

            try {
                int[] coords = convertirCoordenada(coordenada);
                int fila = coords[0];
                int columna = coords[1];

                switch (accion) {
                    case "D":
                        tablero.descubrir(fila, columna);
                        break;
                    case "B":
                        tablero.gestionarBandera(fila, columna); 
                        break;
                    default:
                        vista.mostrarMensaje("Acción no válida");
                        break;
                }

                // Verificar si el jugador ha ganado
                if (tablero.verificarVictoria()) {
                    vista.mostrarMensaje("¡Felicidades, has ganado!");
                    break; // Termina el juego
                }

            } catch (CasillaYaDescubiertaException e) {
                vista.mostrarMensaje(e.getMessage());
            } catch (Exception e) {
                vista.mostrarMensaje("Error: " + e.getMessage());
            }
        }
    }

    private int[] convertirCoordenada(String coordenada) throws InputInvalidoException {
        if (coordenada.length() < 2 || coordenada.length() > 3) {
            throw new InputInvalidoException("Coordenada inválida. Usa un formato como A1, B10.");
        }

        char letra = coordenada.charAt(0);
        int columna;

        try {
            columna = Integer.parseInt(coordenada.substring(1));
        } catch (NumberFormatException e) {
            throw new InputInvalidoException("Número de columna inválido.");
        }

        if (letra < 'A' || letra > 'J' || columna < 1 || columna > 10) {
            throw new InputInvalidoException("Coordenada fuera de rango.");
        }

        int fila = letra - 'A';
        return new int[] { fila, columna - 1 };  // Convertir a índice 0-based para las filas y columnas
    }

}
