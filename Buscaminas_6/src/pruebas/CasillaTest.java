package pruebas;

import org.junit.jupiter.api.Test;
import modelo.Casilla;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasillaTest {

	@Test
    public void testColocarMina() {
        Casilla casilla = new Casilla();
        casilla.colocarMina();
        assertTrue(casilla.tieneMina(), "La casilla debería tener una mina.");
    }

    @Test
    public void testDescubrir() {
        Casilla casilla = new Casilla();
        casilla.descubrir();
        assertTrue(casilla.estaDescubierta(), "La casilla debería estar descubierta.");
    }

    @Test
    public void testMarcar() {
        Casilla casilla = new Casilla();
        casilla.marcar();
        assertTrue(casilla.estaMarcada(), "La casilla debería estar marcada.");
    }

    @Test
    public void testTieneMina() {
        Casilla casilla = new Casilla();
        casilla.colocarMina();
        assertTrue(casilla.tieneMina(), "Después de colocar una mina, debe retornar true.");
    }

    @Test
    public void testEstaDescubierta() {
        Casilla casilla = new Casilla();
        casilla.descubrir();
        assertTrue(casilla.estaDescubierta(), "Después de descubrir, debe retornar true.");
    }

    @Test
    public void testEstaMarcada() {
        Casilla casilla = new Casilla();
        casilla.marcar();
        assertTrue(casilla.estaMarcada(), "Después de marcar, debe retornar true.");
    }

    @Test
    public void testGetNumero() {
        Casilla casilla = new Casilla();
        assertEquals(0, casilla.getNumero(), "El número inicial debería ser 0.");

        casilla.setNumero(3);
        assertEquals(3, casilla.getNumero(), "Después de asignar un número, debe retornarlo correctamente.");
    }
    
    
    @Test
    public void testMostrar() {
        Casilla casilla = new Casilla();

        // Caso inicial: No descubierta ni marcada
        assertEquals("#", casilla.mostrar(), "La casilla inicial debería mostrar #.");

        // Caso marcada
        casilla.marcar();
        assertEquals("M", casilla.mostrar(), "La casilla marcada debería mostrar M.");


        // Caso descubierta con mina
        casilla = new Casilla(); // Reiniciar casilla
        casilla.colocarMina();
        casilla.descubrir();
        assertEquals("X", casilla.mostrar(), "La casilla descubierta con mina debería mostrar X.");
    }
    
	
}