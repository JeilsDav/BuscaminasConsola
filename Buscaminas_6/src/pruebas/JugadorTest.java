package pruebas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

import modelo.Jugador;

class JugadorTest {

    private Jugador jugador;
    private final Path archivoPrueba = Paths.get("datosJugador.csv");

    @BeforeEach
    void setUp() {
        jugador = new Jugador();
    }

    @AfterEach
    void tearDown() throws Exception {
        // Limpia el archivo de prueba después de cada test
        if (Files.exists(archivoPrueba)) {
            Files.delete(archivoPrueba);
        }
    }

    @Test
    void testIncrementarPartidasGanadas() {
        jugador.incrementarPartidasGanadas(3);
        assertEquals(3, jugador.getPartidasGanadas(), "La cantidad de partidas ganadas no coincide.");
    }

    @Test
    void testIncrementarCasillasDescubiertas() {
        jugador.incrementarCasillasDescubiertas(10);
        assertEquals(10, jugador.getCasillasDescubiertas(), "La cantidad de casillas descubiertas no coincide.");
    }

    @Test
    void testIncrementarPartidasPerdidas() {
        jugador.incrementarPartidasPerdidas(2);
        assertEquals(2, jugador.getPartidasPerdidas(), "La cantidad de partidas perdidas no coincide.");
    }

    @Test
    void testGuardarDatos() {
        jugador.incrementarPartidasGanadas(5);
        jugador.incrementarCasillasDescubiertas(15);
        jugador.incrementarPartidasPerdidas(3);

        jugador.guardarDatos();

        assertTrue(Files.exists(archivoPrueba), "El archivo no fue creado correctamente.");
        try {
            String contenido = Files.readString(archivoPrueba);
            assertTrue(contenido.contains("15,5,3"), "Los datos guardados en el archivo no son correctos.");
        } catch (Exception e) {
            fail("No se pudo leer el archivo: " + e.getMessage());
        }
    }

    @Test
    void testCargarDatos() {
        // Simula un archivo con datos
        try {
            Files.writeString(archivoPrueba, "casillasDescubiertas,partidasGanadas,partidasPerdidas\n20,7,4\n");
        } catch (Exception e) {
            fail("No se pudo preparar el archivo de prueba: " + e.getMessage());
        }

        jugador.cargarDatos();

        assertEquals(20, jugador.getCasillasDescubiertas(), "Las casillas descubiertas no se cargaron correctamente.");
        assertEquals(7, jugador.getPartidasGanadas(), "Las partidas ganadas no se cargaron correctamente.");
        assertEquals(4, jugador.getPartidasPerdidas(), "Las partidas perdidas no se cargaron correctamente.");
    }
}
