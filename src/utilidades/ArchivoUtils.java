package utilidades;

import java.io.*;

public class ArchivoUtils {
    public static void guardarPartida(String nombreArchivo, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(contenido);
        } catch (IOException e) {
            System.out.println("Error al guardar la partida.");
        }
    }
}
