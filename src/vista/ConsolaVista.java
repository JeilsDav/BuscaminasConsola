package vista;

import modelo.Tablero;

public class ConsolaVista {

	// Método para mostrar el tablero en la consola
    public void mostrarTablero(Tablero tablero) {
        System.out.println("Banderas restantes: " + tablero.getBanderasRestantes());
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char letraFila = 'A';
        for (int i = 0; i < 10; i++) {
            System.out.print(letraFila + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(tablero.getCasilla(i, j).mostrar() + " ");
            }
            System.out.println();
            letraFila++;
        }
    }
    

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String pedirAccion() {
        String accion = pedirEntradaAccion();
        while (!accion.equals("D") && !accion.equals("B")) {
            System.out.println("Acción inválida. Usa 'D' para descubrir o 'B' para poner una bandera.");
            accion = pedirEntradaAccion();
        }
        return accion;
    }

    private String pedirEntradaAccion() {
        System.out.println("¿Qué acción quieres realizar? (D: Descubrir, B: Agregar/Quitar Bandera): ");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        return scanner.nextLine().trim().toUpperCase();
    }


    public String pedirCoordenada() {
        System.out.println("Introduce la fila (letra) y columna (número) (ejemplo: A1): ");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String coordenada = scanner.nextLine().trim().toUpperCase();

        while (!coordenada.matches("[A-J](10|[1-9])")) {
            System.out.println("Entrada inválida. Usa un formato como A1, B10, etc.");
            coordenada = scanner.nextLine().trim().toUpperCase();
        }
        return coordenada;
    }
}