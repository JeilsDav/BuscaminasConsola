package principal;

import modelo.Tablero;
import excepciones.CasillaYaDescubiertaException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
			    tablero.mostrarTablero();
			    System.out.println("Introduce la fila (letra) y columna (número) para descubrir (ejemplo: A1):");
			    String input = scanner.nextLine().toUpperCase();

			    if (input.length() < 2 || input.length() > 3) {
			        System.out.println("Formato inválido. Usa letra y número (ejemplo: A1).");
			        continue;
			    }

			    char filaChar = input.charAt(0);
			    int columna;
			    try {
			        columna = Integer.parseInt(input.substring(1)) - 1;
			        int fila = filaChar - 'A';

			        tablero.descubrir(fila, columna);
			    } catch (NumberFormatException e) {
			        System.out.println("El número de columna no es válido.");
			    } catch (CasillaYaDescubiertaException e) {
			        System.out.println(e.getMessage());
			    }
			}
		}
    }
}