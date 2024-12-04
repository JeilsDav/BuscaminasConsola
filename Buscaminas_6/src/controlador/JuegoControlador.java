package controlador;

import modelo.Tablero;
import vista.ConsolaVista;
import modelo.Jugador;
import excepciones.CasillaYaDescubiertaException;
import excepciones.InputInvalidoException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        boolean juegoActivo = true;

        while (juegoActivo) {
            vista.mostrarTablero(tablero);  // Mostrar tablero actualizado

            // Pedir acción y coordenadas
            String accion = vista.pedirAccion();
            String coordenada = vista.pedirCoordenada();

            try {
                int[] coords = convertirCoordenada(coordenada);
                int fila = coords[0];
                int columna = coords[1];

                switch (accion) {
                    case "D":  // Acción de descubrir
                        if (tablero.getCasilla(fila, columna).estaMarcada()) {
                            vista.mostrarMensaje("No se puede descubrir una casilla marcada con bandera.");
                        } else {
                            tablero.descubrir(fila, columna);
                            jugador.incrementarCasillasDescubiertas(1);  // Incrementamos las casillas descubiertas
                        }
                        break;
                    case "B":  // Acción de marcar con bandera
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
                    juegoActivo = false;  // Finaliza el juego
                } else if (tablero.verificarPerdida()) {
                    vista.mostrarMensaje("¡Has perdido! Descubriste una mina.");
                    jugador.incrementarPartidasPerdidas(1);
                    guardarDatos(false);  // Guardamos los datos y terminamos el juego
                    juegoActivo = false;  // Finaliza el juego
                }

            } catch (CasillaYaDescubiertaException e) {
                vista.mostrarMensaje(e.getMessage());
            } catch (Exception e) {
                vista.mostrarMensaje("Error: " + e.getMessage());
            }
            
            if (!juegoActivo) { 
            	String respuesta = vista.pedirVolverAJugar(); 
            	while (!respuesta.equalsIgnoreCase("Si") && !respuesta.equalsIgnoreCase("No")) { 
            		vista.mostrarMensaje("Por favor, ingresa 'Si' o 'No'."); 
            		respuesta = vista.pedirVolverAJugar(); 
            	} 
            	if (respuesta.equalsIgnoreCase("Si")) { 
            		reiniciarJuego(); 
            		juegoActivo = true; 
            	} else { 
            		vista.mostrarMensaje("¡Gracias por jugar! ¡Hasta la próxima!"); 
            		}
            	}
        }
    }

    private int[] convertirCoordenada(String coordenada) throws InputInvalidoException {
        if (coordenada.length() < 2 || coordenada.length() > 3) {
            throw new InputInvalidoException("Coordenada inválida. Usa un formato como A1, B10.");
        }

        char letra = Character.toUpperCase(coordenada.charAt(0));  // Asegurar que la letra sea mayúscula
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

        } catch (IOException e) {
            System.err.println("Error al manejar el archivo: " + e.getMessage());
        }
    }
    
 // Método para reiniciar el juego 
    public void reiniciarJuego() { 
    	this.tablero = new Tablero(); 
    }
}