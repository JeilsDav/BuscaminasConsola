package pruebas;

import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;
import utilidades.ArchivoUtils;

public class ArchivoUtilsTest {

	
	@Test
    public void testGuardarPartida() {
        String contenido = "Jugador1: 100 puntos";
        String nombreArchivo = "test_partida.txt";
        
        // Guardamos el archivo
        ArchivoUtils.guardarPartida(nombreArchivo, contenido);
        
        // Verificamos si el archivo fue creado
        File archivo = new File(nombreArchivo);
        assertTrue(archivo.exists(), "El archivo no se creó.");

        // Leemos el archivo y verificamos su contenido
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String line = reader.readLine();
            assertEquals(contenido, line, "El contenido del archivo no coincide.");
        } catch (IOException e) {
            fail("Error al leer el archivo.");
        }
        
        // Limpiamos eliminando el archivo después de la prueba
        archivo.delete();
    }
    
    @Test
    public void testGuardarPartidaConError() {
        // Intentamos guardar en un archivo con una ruta no válida
        String contenido = "Jugador1: 100 puntos";
        String nombreArchivo = "/ruta/no/valida/test_partida.txt";
        
        // No debería lanzar ninguna excepción, pero sí debe manejar el error internamente
        assertDoesNotThrow(() -> ArchivoUtils.guardarPartida(nombreArchivo, contenido));
    }

}
