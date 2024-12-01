package modelo;

import excepciones.CasillaYaDescubiertaException;

public class Tablero {
    private Casilla[][] casillas;
    private boolean juegoTerminado;  // Estado del juego
    private boolean victoria;        // Indica si el jugador ganó

    // Constructor por defecto: coloca minas aleatorias y calcula números
    public Tablero() {
        this.casillas = new Casilla[10][10];
        this.juegoTerminado = false;
        this.victoria = false;
        inicializarCasillas();
        colocarMinas(10);
        calcularNumeros();
    }

    // Constructor alternativo: no coloca minas ni calcula números
    public Tablero(boolean sinMinas) {
        this.casillas = new Casilla[10][10];
        this.juegoTerminado = false;
        this.victoria = false;
        inicializarCasillas();
    }

    // Método para inicializar casillas
    private void inicializarCasillas() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }

    // Método para colocar minas aleatorias
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

    // Método para calcular los números de minas adyacentes
    public void calcularNumeros() {
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

    // Método para validar coordenadas
    private boolean esValido(int fila, int columna) {
        return fila >= 0 && fila < 10 && columna >= 0 && columna < 10;
    }

    // Método para descubrir una casilla
    public void descubrir(int fila, int columna) throws CasillaYaDescubiertaException {
        if (juegoTerminado) {
            return;  // Si el juego ya terminó, no hacer nada
        }

        if (!esValido(fila, columna)) {
            return;
        }

        Casilla casilla = casillas[fila][columna];

        if (casilla.estaDescubierta()) {
            throw new CasillaYaDescubiertaException("La casilla ya ha sido descubierta.");
        }

        casilla.descubrir();

        if (casilla.tieneMina()) {
            // El jugador ha perdido
            juegoTerminado = true;
            System.out.println("¡Has perdido! Descubriste una mina.");
            mostrarTableroCompleto();
            return;
        }

        if (casilla.getNumero() == 0) {
            int[] dFila = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dColumna = {-1, 0, 1, -1, 1, -1, 0, 1};

            for (int d = 0; d < 8; d++) {
                int nuevaFila = fila + dFila[d];
                int nuevaColumna = columna + dColumna[d];
                if (esValido(nuevaFila, nuevaColumna) && !casillas[nuevaFila][nuevaColumna].estaDescubierta()) {
                    descubrir(nuevaFila, nuevaColumna);
                }
            }
        }

        // Verificar victoria y perdida
        if (verificarPerdida()) {
            juegoTerminado = true;
            System.out.println("¡Perdiste! Has descubierto una mina.");
            mostrarTableroCompleto();
            return;
        }

        if (verificarVictoria()) {
            victoria = true;
            juegoTerminado = true;
            System.out.println("¡Felicidades! ¡Has ganado!");
            mostrarTableroCompleto();
        }
    }

    // Método para verificar si el jugador ha perdido (descubrió una mina)
    public boolean verificarPerdida() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (casillas[i][j].tieneMina() && casillas[i][j].estaDescubierta()) {
                    return true;
                }
            }
        }
        return false;
    }

    // Método para verificar si el jugador ha ganado
    public boolean verificarVictoria() {
        return todosLosEspaciosDescubiertos() || todasLasMinasDescubiertas();
    }

    // Método para verificar si todas las minas están marcadas
    public boolean todasLasMinasDescubiertas() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Casilla casilla = casillas[i][j];
                if (casilla.tieneMina() && !casilla.estaMarcada()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Método para verificar si todos los espacios sin minas están descubiertos
    public boolean todosLosEspaciosDescubiertos() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Casilla casilla = casillas[i][j];
                if (!casilla.tieneMina() && !casilla.estaDescubierta()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Métodos auxiliares para mostrar el tablero
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
        return null;
    }

    // Método para gestionar la colocación de banderas
    public void gestionarBandera(int fila, int columna) {
        if (!esValido(fila, columna)) {
            System.out.println("Coordenadas inválidas.");
            return;
        }

        Casilla casilla = casillas[fila][columna];
        if (casilla.estaDescubierta()) {
            System.out.println("No se puede colocar una bandera en una casilla descubierta.");
        } else {
            casilla.marcar();
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

