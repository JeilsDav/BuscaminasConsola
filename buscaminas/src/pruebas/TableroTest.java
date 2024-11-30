package pruebas;

import org.junit.jupiter.api.Test;
import excepciones.CasillaYaDescubiertaException;
import modelo.Casilla;
import modelo.Tablero;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;


public class TableroTest {

    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(); // Inicializa un nuevo tablero antes de cada prueba
    }

    @Test
    public void testColocarMinas() {
        int minasContadas = 0;

        // Recorre todo el tablero para contar cuántas minas están colocadas
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero.getCasilla(i, j).tieneMina()) {
                    minasContadas++;
                }
            }
        }

        // Verifica que el número de minas sea exactamente 10
        assertEquals(10, minasContadas, "El tablero debe tener exactamente 10 minas.");
    }

    @Test
    public void testCalcularNumeros() {
        // Inicializa el tablero
        tablero = new Tablero();

        // Coloca una mina en una casilla específica
        tablero.getCasilla(0, 0).colocarMina();
        
        // Llama a calcularNumeros después de colocar la mina
        tablero.calcularNumeros();

        // Verifica que las casillas adyacentes a la mina tengan el número correcto
        assertEquals(1, tablero.getCasilla(0, 1).getNumero(), "Debe calcular correctamente las minas adyacentes.");
        assertEquals(1, tablero.getCasilla(1, 0).getNumero(), "Debe calcular correctamente las minas adyacentes.");
    }


    @Test
    public void testDescubrir() throws CasillaYaDescubiertaException {
        tablero.descubrir(4, 4);  // Descubre una casilla
        // Verifica que la casilla esté descubierta
        assertTrue(tablero.getCasilla(4, 4).estaDescubierta(), "La casilla (4, 4) debe estar descubierta después de llamar a descubrir().");
    }

    @Test
    public void testDescubrirCasillaYaDescubierta() {
        try {
            tablero.descubrir(5, 5);  // Descubre una casilla
            tablero.descubrir(5, 5);  // Intentar descubrirla de nuevo debe lanzar una excepción
            fail("Se esperaba una CasillaYaDescubiertaException al descubrir una casilla ya descubierta.");
        } catch (CasillaYaDescubiertaException e) {
            // Verifica que la excepción tenga el mensaje correcto
            assertEquals("La casilla ya ha sido descubierta.", e.getMessage(), "El mensaje de excepción debe ser correcto.");
        }
    }

    @Test
    public void testTodasLasMinasDescubiertas() {
        // Marca todas las minas
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Casilla casilla = tablero.getCasilla(i, j);
                if (casilla.tieneMina()) {
                    casilla.marcar();
                }
            }
        }

        // Verifica que todas las minas estén marcadas
        assertTrue(tablero.todasLasMinasDescubiertas(), "Todas las minas deben estar marcadas.");
    }

    @Test
    public void testTodosLosEspaciosDescubiertos() {
        // Descubre todos los espacios que no tienen minas
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Casilla casilla = tablero.getCasilla(i, j);
                if (!casilla.tieneMina()) {
                    casilla.descubrir();
                }
            }
        }

        // Verifica que todos los espacios sin minas estén descubiertos
        assertTrue(tablero.todosLosEspaciosDescubiertos(), "Todos los espacios sin minas deben estar descubiertos.");
    }

    @Test
    public void testGestionarBandera() {
        // Marca una casilla
        tablero.gestionarBandera(5, 5);
        assertTrue(tablero.getCasilla(5, 5).estaMarcada(), "La casilla (5, 5) debe estar marcada después de gestionarBandera().");

        // Quita la bandera
        tablero.gestionarBandera(5, 5);
        assertFalse(tablero.getCasilla(5, 5).estaMarcada(), "La casilla (5, 5) no debe estar marcada después de llamar nuevamente a gestionarBandera().");
    }
}
