package modelo;

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
}
