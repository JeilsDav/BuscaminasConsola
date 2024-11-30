package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import excepciones.InputInvalidoException;

public class InputInvalidoExceptionTest {

	   @Test
	    public void testInputInvalidoExceptionMessage() {
	        String mensajeEsperado = "Coordenada inválida. Usa un formato como A1, B10.";
	        
	        InputInvalidoException exception = new InputInvalidoException(mensajeEsperado);
	        
	        assertEquals(mensajeEsperado, exception.getMessage());
	    }
	
}
