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
	        tablero = new Tablero();
	    }	    

	 
	    @Test
	    public void testColocarMinas() {
	        int minasContadas = 0;

	        for (int i = 0; i < 10; i++) {
	            for (int j = 0; j < 10; j++) {
	                if (tablero.getCasilla(i, j).tieneMina()) {
	                    minasContadas++;
	                }
	            }
	        }

	        assertEquals(10, minasContadas, "El tablero debe tener exactamente 10 minas.");
	    }

	    
	    @Test
        public void testCalcularNumeros() {
            // Crear un tablero limpio sin minas
            tablero = new Tablero(true);

            // Colocar manualmente una mina en (0, 0)
            tablero.colocarMinaEn(0, 0);

            // Recalcular los números
            tablero.calcularNumeros();

            // Verificar las casillas adyacentes a (0, 0)
            assertEquals(1, tablero.getCasilla(0, 1).getNumero(), "Debe calcular correctamente las minas adyacentes para (0, 1).");
            assertEquals(1, tablero.getCasilla(1, 0).getNumero(), "Debe calcular correctamente las minas adyacentes para (1, 0).");
            assertEquals(1, tablero.getCasilla(1, 1).getNumero(), "Debe calcular correctamente las minas adyacentes para (1, 1).");
	    }
	    
	    
	    @Test
	    public void testDescubrir() throws CasillaYaDescubiertaException {
	        tablero.descubrir(4, 4);
	        assertTrue(tablero.getCasilla(4, 4).estaDescubierta(), "La casilla (4, 4) debe estar descubierta después de llamar a descubrir().");
	    }

	    
	    @Test
	    public void testDescubrirCasillaYaDescubierta() {
	        try {
	            tablero.descubrir(5, 5);
	            tablero.descubrir(5, 5); // Segunda llamada debe lanzar excepción
	            fail("Se esperaba una CasillaYaDescubiertaException al descubrir una casilla ya descubierta.");
	        } catch (CasillaYaDescubiertaException e) {
	            assertEquals("La casilla ya ha sido descubierta.", e.getMessage(), "El mensaje de excepción debe ser correcto.");
	        }
	    }
	    
	    
	    @Test
	    public void testTodasLasMinasDescubiertas() {
	        for (int i = 0; i < 10; i++) {
	            for (int j = 0; j < 10; j++) {
	                Casilla casilla = tablero.getCasilla(i, j);
	                if (casilla.tieneMina()) {
	                    casilla.marcar();
	                }
	            }
	        }

	        assertTrue(tablero.todasLasMinasDescubiertas(), "Todas las minas deben estar marcadas.");
	    }
	    
	    
	    @Test
	    public void testTodosLosEspaciosDescubiertos() {
	        for (int i = 0; i < 10; i++) {
	            for (int j = 0; j < 10; j++) {
	                Casilla casilla = tablero.getCasilla(i, j);
	                if (!casilla.tieneMina()) {
	                    casilla.descubrir();
	                }
	            }
	        }

	        assertTrue(tablero.todosLosEspaciosDescubiertos(), "Todos los espacios sin minas deben estar descubiertos.");
	    }
	  
	    
	    @Test
	    public void testGestionarBandera() {
	        tablero.gestionarBandera(5, 5);

	        assertTrue(tablero.getCasilla(5, 5).estaMarcada(), "La casilla (5, 5) debe estar marcada después de gestionarBandera().");

	        tablero.gestionarBandera(5, 5); // Quitar bandera
	        assertFalse(tablero.getCasilla(5, 5).estaMarcada(), "La casilla (5, 5) no debe estar marcada después de llamar nuevamente a gestionarBandera().");
	    }
}
