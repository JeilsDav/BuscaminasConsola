package modelo;

import java.util.Scanner;
import excepciones.CasillaYaDescubiertaException;

public class Tablero {
    private Casilla[][] casillas;

    public Tablero() {
        this.casillas = new Casilla[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                casillas[i][j] = new Casilla();
            }
        }
        colocarMinas(10);
        calcularNumeros();
    }

    private void colocarMinas(int cantidadMinas) {
        int colocadas = 0;
        while (colocadas < cantidadMinas) {
            int fila = (int) (Math.random() * 10);
            int columna = (int) (Math.random() * 10);
            if (!casillas[fila][columna].tieneMina()) {
                casillas[fila][columna].colocarMina();
                colocadas++;
            }
        }
    }

    private void calcularNumeros() {
        int[] dFila = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dColumna = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!casillas[i][j].tieneMina()) {
                    int minasAdyacentes = 0;
                    for (int d = 0; d < 8; d++) {
                        int nuevaFila = i + dFila[d];
                        int nuevaColumna = j + dColumna[d];
                        if (esValido(nuevaFila, nuevaColumna) && casillas[nuevaFila][nuevaColumna].tieneMina()) {
                            minasAdyacentes++;
                        }
                    }
                    casillas[i][j].setNumero(minasAdyacentes);
                }
            }
        }
    }

    private boolean esValido(int fila, int columna) {
        return fila >= 0 && fila < 10 && columna >= 0 && columna < 10;
    }

    public void descubrir(int fila, int columna) throws CasillaYaDescubiertaException {
        if (!esValido(fila, columna)) {
            return;
        }
        if (casillas[fila][columna].estaDescubierta()) {
            throw new CasillaYaDescubiertaException("La casilla ya ha sido descubierta.");
        }

        casillas[fila][columna].descubrir();

        if (casillas[fila][columna].tieneMina()) {
            System.out.println("¡Has perdido! Descubriste una mina.");
            mostrarTableroCompleto();
            System.exit(0);
        } else if (casillas[fila][columna].getNumero() == 0) {
            int[] dFila = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dColumna = {-1, 0, 1, -1, 1, -1, 0, 1};
            for (int d = 0; d < 8; d++) {
                descubrir(fila + dFila[d], columna + dColumna[d]);
            }
        }
        
    }

    public void mostrarTablero() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char letraFila = 'A';
        for (int i = 0; i < 10; i++) {
            System.out.print(letraFila + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(casillas[i][j].mostrar() + " ");
            }
            System.out.println();
            letraFila++;
        }
    }
    public Casilla getCasilla(int fila, int columna) {
        if (esValido(fila, columna)) {
            return casillas[fila][columna];
        }
        return null;  // O lanzar una excepción si prefieres
    }

    private void mostrarTableroCompleto() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char letraFila = 'A';
        for (int i = 0; i < 10; i++) {
            System.out.print(letraFila + " ");
            for (int j = 0; j < 10; j++) {
                if (casillas[i][j].tieneMina()) {
                    System.out.print("X ");
                } else {
                    System.out.print(casillas[i][j].getNumero() + " ");
                }
            }
            System.out.println();
            letraFila++;
        }
    }

    // Método para iniciar el juego
    public static void iniciarJuego() {
        try (Scanner scanner = new Scanner(System.in)) {
			Tablero tablero = new Tablero();

			while (true) {
			    tablero.mostrarTablero();
			    System.out.println("Introduce la fila (letra) y columna (número) para descubrir (ejemplo: A1):");

			    String entrada = scanner.nextLine().toUpperCase(); // Tomamos la entrada y la convertimos a mayúsculas

			    // Verificamos que la entrada sea válida (debe ser una letra y un número)
			    if (entrada.length() == 2) {
			        char letra = entrada.charAt(0);
			        int columna;

			        try {
			            columna = Integer.parseInt(entrada.substring(1)); // Intentamos obtener la columna
			        } catch (NumberFormatException e) {
			            System.out.println("Formato inválido. Usa letra y número (ejemplo: A1).");
			            continue;
			        }

			        // Validamos que la letra esté entre 'A' y 'J' y la columna entre 1 y 10
			        if (letra >= 'A' && letra <= 'J' && columna >= 1 && columna <= 10) {
			            int fila = letra - 'A';  // Convertimos la letra en un índice (A = 0, B = 1, ...)
			            
			            try {
			                // Llamada al método descubrir, ahora manejamos la excepción CasillaYaDescubiertaException
			                tablero.descubrir(fila, columna - 1);  // Restamos 1 porque las columnas son 0-based
			            } catch (CasillaYaDescubiertaException e) {
			                // Si la casilla ya ha sido descubierta, mostramos un mensaje
			                System.out.println(e.getMessage());
			            }
			        } else {
			            System.out.println("Entrada fuera de rango. Usa una fila de la A a la J y columna de 1 a 10.");
			        }
			    } else {
			        System.out.println("Formato inválido. Usa letra y número (ejemplo: A1).");
			    }
			}
		}
    }
    
}


