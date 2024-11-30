package utilidades;

public class ValidacionUtils {
    public static boolean validarEntrada(String entrada) {
        return entrada.matches("[A-J](10|[1-9])");
    }
}
