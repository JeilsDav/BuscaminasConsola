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

    // Método para marcar la casilla (poner bandera)
    public void marcar() {
        if (!this.descubierta) { // No puedes marcar una casilla ya descubierta
            this.marcada = !this.marcada; // Cambia el estado de marcada
        }
    }

    // Método para saber si está marcada
    public boolean estaMarcada() {
        return this.marcada;
    }

    public boolean tieneMina() {
        return this.tieneMina;
    }

    public boolean estaDescubierta() {
        return this.descubierta;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Mostrar la casilla según su estado
    public String mostrar() {
        if (this.marcada) {
            return "M"; // La casilla está marcada con bandera
        } else if (this.descubierta) {
            return this.tieneMina() ? "X" : String.valueOf(this.numero); // Casilla descubierta
        } else {
            return "#";  // Casilla no descubierta
        }
    }
}