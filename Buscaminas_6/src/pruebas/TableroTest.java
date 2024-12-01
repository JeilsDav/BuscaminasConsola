package pruebas;

import excepciones.CasillaYaDescubiertaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import modelo.Casilla;
import modelo.Tablero;

class TableroTest {

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
    }

    @Test
    void testInicializarCasillas() {
        // Comprobar que todas las casillas están inicializadas correctamente
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertNotNull(tablero.getCasilla(i, j), "La casilla debería estar inicializada");
                assertFalse(tablero.getCasilla(i, j).estaDescubierta(), "La casilla no debería estar descubierta inicialmente");
            }
        }
    }

    @Test
    void testColocarMinas() {
        int totalMinas = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero.getCasilla(i, j).tieneMina()) {
                    totalMinas++;
                }
            }
        }
        assertEquals(10, totalMinas, "El tablero debería tener exactamente 10 minas");
    }

    @Test
    void testDescubrirCasillaSinMina() {
        try {
            // Buscar una casilla sin mina y descubrirla
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (!tablero.getCasilla(i, j).tieneMina()) {
                        tablero.descubrir(i, j);
                        assertTrue(tablero.getCasilla(i, j).estaDescubierta(), "La casilla debería estar descubierta");
                        return;
                    }
                }
            }
        } catch (CasillaYaDescubiertaException e) {
            fail("No debería lanzarse la excepción al descubrir una casilla válida");
        }
    }

    @Test
    void testDescubrirCasillaConMina() {
        try {
            // Buscar una casilla con mina y descubrirla
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (tablero.getCasilla(i, j).tieneMina()) {
                        tablero.descubrir(i, j);
                        assertTrue(tablero.verificarPerdida(), "El juego debería estar perdido al descubrir una mina");
                        return;
                    }
                }
            }
        } catch (CasillaYaDescubiertaException e) {
            fail("No debería lanzarse la excepción al descubrir una casilla válida");
        }
    }

    @Test
    void testGestionarBandera() {
        int fila = 0, columna = 0;
        tablero.gestionarBandera(fila, columna);
        assertTrue(tablero.getCasilla(fila, columna).estaMarcada(), "La casilla debería estar marcada con una bandera");

        // Colocar y quitar bandera
        tablero.gestionarBandera(fila, columna);
        assertFalse(tablero.getCasilla(fila, columna).estaMarcada(), "La casilla debería estar desmarcada");
    }

    @Test
    void testVerificarVictoria() {
        // Simular el escenario donde todas las minas están marcadas
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Casilla casilla = tablero.getCasilla(i, j);
                if (casilla.tieneMina()) {
                    tablero.gestionarBandera(i, j);
                } else {
                    try {
                        tablero.descubrir(i, j);
                    } catch (CasillaYaDescubiertaException e) {
                        fail("No debería lanzarse la excepción al descubrir una casilla válida");
                    }
                }
            }
        }

        // Validar si las condiciones de victoria están configuradas correctamente
        assertTrue(tablero.todosLosEspaciosDescubiertos(), "No se han descubierto todos los espacios sin minas.");
        assertTrue(tablero.todasLasMinasDescubiertas(), "No se han marcado todas las minas correctamente.");

        // Comprobar la victoria global
        assertTrue(tablero.verificarVictoria(), "El jugador debería ganar al cumplir las condiciones de victoria.");
    }

}
