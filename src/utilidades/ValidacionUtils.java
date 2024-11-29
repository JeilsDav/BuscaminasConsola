package utilidades;

public class ValidacionUtils {
    public static boolean validarEntrada(String entrada) {
        return entrada.matches("[A-J][1-9]|10");
    }
}
