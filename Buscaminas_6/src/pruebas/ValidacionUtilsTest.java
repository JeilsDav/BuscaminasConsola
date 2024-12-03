package pruebas;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import utilidades.ValidacionUtils;

public class ValidacionUtilsTest {

    @Test
    public void testValidarEntradaValida() {
        // Entradas válidas según el patrón "[A-J][1-9]|10"
        assertTrue(ValidacionUtils.validarEntrada("A1"));
        assertTrue(ValidacionUtils.validarEntrada("B9"));
        assertTrue(ValidacionUtils.validarEntrada("J10"));
    }

    @Test
    public void testValidarEntradaInvalida() {
        // Entradas inválidas
        assertFalse(ValidacionUtils.validarEntrada("K1")); // Letra fuera de rango
        assertFalse(ValidacionUtils.validarEntrada("A0")); // Número fuera de rango
        assertFalse(ValidacionUtils.validarEntrada("A11")); // Número fuera de rango
        assertFalse(ValidacionUtils.validarEntrada("Z3")); // Letra fuera de rango
    }

    @Test
    public void testValidarEntradaVacia() {
        // Entrada vacía
        assertFalse(ValidacionUtils.validarEntrada(""));
    }
}