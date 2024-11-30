package modelo;

public class Jugador {
    private String nombre;
    private int minasDescubiertas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.minasDescubiertas = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMinasDescubiertas() {
        return minasDescubiertas;
    }

    public void incrementarMinasDescubiertas() {
        minasDescubiertas++;
    }
}