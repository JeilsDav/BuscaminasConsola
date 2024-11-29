package excepciones;

public class InputInvalidoException extends Exception {
    private static final long serialVersionUID = 1L;

	public InputInvalidoException(String mensaje) {
        super(mensaje);
    }
}
