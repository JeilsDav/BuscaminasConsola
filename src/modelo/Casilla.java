package modelo;

public class Casilla {
    private boolean tieneMina;
    private boolean descubierta;
    private boolean marcada;
    private int numero;

    public Casilla() {
        this.tieneMina = false;
        this.descubierta = false;
        this.marcada = false;
        this.numero = 0;
    }

    public void colocarMina() {
        this.tieneMina = true;
    }

    public void descubrir() {
        this.descubierta = true;
    }

    public void marcar() {
        this.marcada = true;
    }

    public boolean tieneMina() {
        return this.tieneMina;
    }

    public boolean estaDescubierta() {
        return this.descubierta;
    }

    public boolean estaMarcada() {
        return this.marcada;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String mostrar() {
        if (this.marcada) {
            return "M";
        } else if (this.descubierta) {
            return this.tieneMina() ? "X" : String.valueOf(this.numero);
        } else {
            return "#";  // Casilla no descubierta
        }
    }
}
