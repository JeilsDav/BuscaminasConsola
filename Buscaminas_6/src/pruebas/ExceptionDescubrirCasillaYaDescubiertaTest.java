package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import excepciones.CasillaYaDescubiertaException;
import modelo.Tablero;

public class ExceptionDescubrirCasillaYaDescubiertaTest {

    @Test
    public void testDescubrirCasillaYaDescubierta() {
        Tablero tablero = new Tablero();

        // Descubrimos una casilla
        int fila = 3;
        int columna = 2;
        try {
            tablero.descubrir(fila, columna); // Descubre una casilla
        } catch (CasillaYaDescubiertaException e) {
            fail("No se esperaba una excepción en esta primera llamada.");
        }

        // Ahora intentamos descubrirla de nuevo y esperamos la excepción
        CasillaYaDescubiertaException exception = assertThrows(CasillaYaDescubiertaException.class, () -> {
            tablero.descubrir(fila, columna); // Debería lanzar la excepción
        });

        // Verificamos que el mensaje de la excepción sea el esperado
        assertEquals("La casilla ya ha sido descubierta.", exception.getMessage());
    }
}