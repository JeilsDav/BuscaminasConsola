package modelo;

public class Casilla {
    private boolean mina;
    private boolean descubierta;
    private int numero;

    public Casilla() {
        this.mina = false;
        this.descubierta = false;
        this.numero = 0;
    }

    public boolean tieneMina() {
        return mina;
    }

    public void colocarMina() {
        this.mina = true;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }

    public void descubrir() {
        this.descubierta = true;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String mostrar() {
        if (!descubierta) {
            return "-";
        }
        return mina ? "X" : (numero == 0 ? "V" : String.valueOf(numero));
    }
}
