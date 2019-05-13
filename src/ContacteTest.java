
import static org.junit.Assert.*;

import org.junit.Test;
public class ContacteTest {
	
    @Test
    public void testContacteConstructorNoAceptaNull() {
        try {
        	new Contacte(null);
        	fail("No debería de aceptar null como nombre");
        } catch (InvalidParamException e){ }
    }
    
    @Test
    public void testContacteConstructorNoAceptaBuit() {
        try {
        	new Contacte("");
        	fail("No debería de aceptar vacío como nombre");
        } catch (InvalidParamException e){ }
    }

    @Test
    public void testContacteConstructorNoAceptaBlanc() {
        try {
        	new Contacte("   ");
        	fail("No debería de aceptar nombre si solo consiste en espacios");
        } catch (InvalidParamException e){ }
    }

    
    
}
