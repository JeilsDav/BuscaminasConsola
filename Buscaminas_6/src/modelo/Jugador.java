package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Jugador {
    private int casillasDescubiertas;
    private int partidasGanadas;
    private int partidasPerdidas;

    public Jugador() {
        this.casillasDescubiertas = 0;
        this.partidasGanadas = 0;
        this.partidasPerdidas = 0;
    }

    public int getCasillasDescubiertas() {
        return casillasDescubiertas;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void incrementarPartidasGanadas(int cantidad) {
        partidasGanadas += cantidad;
    }

    public void incrementarCasillasDescubiertas(int cantidad) {
        casillasDescubiertas += cantidad;
    }

    public void incrementarPartidasPerdidas(int cantidad) {
        partidasPerdidas += cantidad;
    }

 // Método para guardar los datos del jugador en un archivo CSV
    public void guardarDatos() {
        System.out.println("Guardando datos del jugador...");

        Path rutaArchivo = Paths.get("datosJugador.csv");

        try {
            // Verificar si el archivo existe. Si no existe, se crea y agrega encabezado
            if (!Files.exists(rutaArchivo)) {
                Files.createFile(rutaArchivo);  // Crear el archivo si no existe
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo.toFile(), false))) {
                    // Escribir los encabezados solo si el archivo es nuevo
                    writer.write("casillasDescubiertas,partidasGanadas,partidasPerdidas\n");
                    System.out.println("Archivo creado con encabezado.");
                }
            }

            // Escribir los datos como una nueva línea
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo.toFile(), true))) { // 'true' asegura que se agreguen nuevas líneas
                writer.write(casillasDescubiertas + "," 
                            + partidasGanadas + "," 
                            + partidasPerdidas + "\n");
                System.out.println("Datos guardados correctamente.");
            }
        } catch (IOException e) {
            System.err.println("Error al manejar el archivo: " + e.getMessage());
        }
    }


    // Método para cargar los datos del jugador desde un archivo CSV
    public void cargarDatos() {
        System.out.println("Cargando datos del jugador...");

        Path rutaArchivo = Paths.get("datosJugador.csv");

        if (!Files.exists(rutaArchivo)) {
            System.out.println("El archivo no existe. Se creará uno nuevo al guardar.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo.toFile()))) {
            String linea;
            String ultimaLinea = null;
            while ((linea = reader.readLine()) != null) {
                ultimaLinea = linea; // Guardamos la última línea
            }

            if (ultimaLinea != null) {
                String[] datos = ultimaLinea.split(",");
                if (datos.length == 3) {
                    casillasDescubiertas = Integer.parseInt(datos[0]);
                    partidasGanadas = Integer.parseInt(datos[1]);
                    partidasPerdidas = Integer.parseInt(datos[2]);
                    System.out.println("Datos cargados: CasillasDescubiertas=" + casillasDescubiertas + ", PartidasGanadas=" + partidasGanadas + ", PartidasPerdidas=" + partidasPerdidas);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "casillasDescubiertas=" + casillasDescubiertas +
                ", partidasGanadas=" + partidasGanadas +
                ", partidasPerdidas=" + partidasPerdidas +
                '}';
    }
}
