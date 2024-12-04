package pruebas;


import excepciones.CasillaYaDescubiertaException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import modelo.Casilla;
import modelo.Tablero;

public class TableroTest {

    @Test
    public void testInicializarTablero() {
        Tablero tablero = new Tablero();
        assertEquals(10, tablero.getBanderasRestantes(), "Las banderas iniciales deben ser 10.");
    }

    @Test
    public void testColocarMinas() {
        Tablero tablero = new Tablero(true); // Inicializa sin minas
        tablero.calcularNumeros();
        int totalMinas = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero.getCasilla(i, j).tieneMina()) {
                    totalMinas++;
                }
            }
        }

        assertEquals(0, totalMinas, "El tablero no debe tener minas en este caso.");
    }

    @Test
    public void testDescubrirCasillaSinMina() throws CasillaYaDescubiertaException {
        Tablero tablero = new Tablero(true);
        Casilla casilla = tablero.getCasilla(0, 0);
        assertFalse(casilla.estaDescubierta(), "La casilla debe estar sin descubrir inicialmente.");

        tablero.descubrir(0, 0);
        assertTrue(casilla.estaDescubierta(), "La casilla debe estar descubierta después de llamar a descubrir.");
    }

    @Test
    public void testGestionarBandera() {
        Tablero tablero = new Tablero(true);
        Casilla casilla = tablero.getCasilla(0, 0);

        tablero.gestionarBandera(0, 0);
        assertTrue(casilla.estaMarcada(), "La casilla debe estar marcada después de colocar la bandera.");
        assertEquals(9, tablero.getBanderasRestantes(), "El número de banderas restantes debe disminuir.");

        tablero.gestionarBandera(0, 0);
        assertFalse(casilla.estaMarcada(), "La casilla no debe estar marcada después de quitar la bandera.");
        assertEquals(10, tablero.getBanderasRestantes(), "El número de banderas restantes debe aumentar.");
    }

    @Test
    public void testVerificarVictoria() throws CasillaYaDescubiertaException {
        Tablero tablero = new Tablero(true);
        // Descubrir todas las casillas que no tienen mina
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Casilla casilla = tablero.getCasilla(i, j);
                if (!casilla.tieneMina()) {
                    tablero.descubrir(i, j);
                }
            }
        }

        assertTrue(tablero.verificarVictoria(), "El jugador debe ganar cuando todas las casillas sin minas están descubiertas.");
    }

    @Test
    public void testVerificarPerdida() throws CasillaYaDescubiertaException {
        Tablero tablero = new Tablero(true);
        Casilla casillaConMina = tablero.getCasilla(0, 0);
        casillaConMina.colocarMina();

        tablero.descubrir(0, 0);
        assertTrue(tablero.verificarPerdida(), "El jugador debe perder al descubrir una mina.");
    }
}