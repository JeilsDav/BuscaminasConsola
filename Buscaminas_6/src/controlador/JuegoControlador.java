package controlador;

import modelo.Tablero;
import vista.ConsolaVista;
import modelo.Jugador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        // Cargar los datos del jugador al principio
        jugador.cargarDatos();
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
                        jugador.incrementarCasillasDescubiertas(1); // Incrementamos las casillas descubiertas
                        break;
                    case "B":
                        tablero.gestionarBandera(fila, columna);
                        break;
                    default:
                        vista.mostrarMensaje("Acción no válida");
                        break;
                }

                // Comprobamos si el jugador ha ganado o perdido después de cada acción
                if (tablero.verificarVictoria()) {
                    vista.mostrarMensaje("¡Felicidades, has ganado!");
                    jugador.incrementarPartidasGanadas(1);
                    guardarDatos(true);  // Guardamos los datos y terminamos el juego
                    return;  // Aquí se termina el juego inmediatamente después de guardar
                }

                if (tablero.verificarPerdida()) {
                    vista.mostrarMensaje("¡Has perdido! Descubriste una mina.");
                    jugador.incrementarPartidasPerdidas(1);
                    guardarDatos(false);  // Guardamos los datos y terminamos el juego
                    return;  // Aquí se termina el juego inmediatamente después de guardar
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

    // Método para guardar los datos del jugador
    public void guardarDatos(boolean finDelJuego) {
        Path rutaArchivo = Paths.get("datosJugador.csv");

        try {
            // Verificar si el archivo existe
            if (!Files.exists(rutaArchivo)) {
                Files.createFile(rutaArchivo); // Crear archivo si no existe
            }

            // Escribir los datos en el archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo.toFile(), true))) {
                writer.write(jugador.getCasillasDescubiertas() + "," 
                            + jugador.getPartidasGanadas() + "," 
                            + jugador.getPartidasPerdidas() + "\n");
            }

            // Si el juego terminó (ya sea victoria o derrota), se termina el flujo del juego
            if (finDelJuego) {
               
            }
        } catch (IOException e) {
            System.err.println("Error al manejar el archivo: " + e.getMessage());
        }
    }
}
