package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Casilla;

class CasillaTest {

    private Casilla casilla;

    @BeforeEach
    void setUp() {
        casilla = new Casilla();
    }

    @Test
    void testInicializacionCasilla() {
        assertFalse(casilla.tieneMina());
        assertFalse(casilla.estaDescubierta());
        assertFalse(casilla.estaMarcada());
        assertEquals(0, casilla.getNumero());
        assertEquals("■", casilla.mostrar());
    }

    @Test
    void testColocarMina() {
        casilla.colocarMina();
        assertTrue(casilla.tieneMina());
        assertEquals("■", casilla.mostrar()); // La mina no debe ser visible si no está descubierta
    }

    @Test
    void testDescubrir() {
        casilla.descubrir();
        assertTrue(casilla.estaDescubierta());
        assertEquals("V", casilla.mostrar()); // Casilla vacía al descubrir
    }

    @Test
    void testDescubrirConMina() {
        casilla.colocarMina();
        casilla.descubrir();
        assertTrue(casilla.estaDescubierta());
        assertEquals("X", casilla.mostrar()); // Debe mostrar X si tiene mina
    }

    @Test
    void testMarcarCasilla() {
        casilla.marcar();
        assertTrue(casilla.estaMarcada());
        assertEquals("M", casilla.mostrar()); // Debe mostrar M si está marcada

        casilla.marcar(); // Desmarcar
        assertFalse(casilla.estaMarcada());
        assertEquals("■", casilla.mostrar());
    }

    @Test
    void testNoMarcarCasillaDescubierta() {
        casilla.descubrir();
        casilla.marcar();
        assertFalse(casilla.estaMarcada()); // No debería marcarse
        assertEquals("V", casilla.mostrar());
    }

    @Test
    void testSetNumero() {
        casilla.setNumero(3);
        assertEquals(3, casilla.getNumero());
        casilla.descubrir();
        assertEquals("3", casilla.mostrar()); // Debe mostrar el número al descubrir
    }
}