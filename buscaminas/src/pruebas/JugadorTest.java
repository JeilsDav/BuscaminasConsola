package pruebas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import modelo.Jugador;

class JugadorTest {

    @Test
    void testIncrementarMinasDescubiertas() {
        Jugador jugador = new Jugador("Juan");
        assertEquals(0, jugador.getMinasDescubiertas());  // Verifica que el contador de minas descubiertas es 0
        jugador.incrementarMinasDescubiertas();
        assertEquals(1, jugador.getMinasDescubiertas());  // Verifica que el contador aumentó a 1
    }
}
