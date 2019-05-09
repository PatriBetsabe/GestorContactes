import static org.junit.Assert.*;

import org.junit.Test;

public class ComandaTest {

    @Test
    public void testProcessaComandaPerLlista() {
        String linia = "llista";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "llista";
        int numArgumentsEsperats = 0;
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertFalse(comanda.esComandaDesconeguda());
    }
    
    @Test
    public void testProcessaComandaPerAjuda() {
        String linia = "ajuda";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "ajuda";
        int numArgumentsEsperats = 0;
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertFalse(comanda.esComandaDesconeguda());
    }
    
    @Test
    public void testProcessaComandaPerSortir() {
        String linia = "sortir";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "sortir";
        int numArgumentsEsperats = 0;
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertFalse(comanda.esComandaDesconeguda());
    }

    @Test
    public void testProcessaComandaPerCanvis() {
        String linia = "canvis";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "canvis";
        int numArgumentsEsperats = 0;
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertFalse(comanda.esComandaDesconeguda());
    }
    
    @Test
    public void testProcessaComandaPerAfegeixNum() {
        String linia = "afegeix num Natasha Riba Gorça 111111111";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "afegeix num";
        int numArgumentsEsperats = 2;
        String arg1Esperat = "Natasha Riba Gorça";
        String arg2Esperat = "111111111";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(arg1Esperat, comanda.getArgument(0));
        assertEquals(arg2Esperat, comanda.getArgument(1));
        assertFalse(comanda.esComandaDesconeguda());
    }

    @Test
    public void testProcessaComandaPerAfegeixNum2() {
        String linia = "afegeix num Ramir Ruel 111122222";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "afegeix num";
        int numArgumentsEsperats = 2;
        String arg1Esperat = "Ramir Ruel";
        String arg2Esperat = "111122222";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(arg1Esperat, comanda.getArgument(0));
        assertEquals(arg2Esperat, comanda.getArgument(1));
        assertFalse(comanda.esComandaDesconeguda());
    }
    
    @Test
    public void testProcessaComandaPerAfegeixEmail() {
        String linia = "afegeix email Natasha natasha@gmail.com";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "afegeix email";
        int numArgumentsEsperats = 2;
        String arg1Esperat = "Natasha";
        String arg2Esperat = "natasha@gmail.com";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(arg1Esperat, comanda.getArgument(0));
        assertEquals(arg2Esperat, comanda.getArgument(1));
        assertFalse(comanda.esComandaDesconeguda());
    }

    @Test
    public void testProcessaComandaPerAfegeixEmail2() {
        String linia = "afegeix email Ramir Ruel email@lala.com";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "afegeix email";
        int numArgumentsEsperats = 2;
        String arg1Esperat = "Ramir Ruel";
        String arg2Esperat = "email@lala.com";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(arg1Esperat, comanda.getArgument(0));
        assertEquals(arg2Esperat, comanda.getArgument(1));
        assertFalse(comanda.esComandaDesconeguda());
    }


    @Test
    public void testProcessaComandaPerEliminaEmail() {
        String linia = "elimina email Natasha Riba Gorça natasha@gmail.com";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "elimina email";
        int numArgumentsEsperats = 2;
        String arg1Esperat = "Natasha Riba Gorça";
        String arg2Esperat = "natasha@gmail.com";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(arg1Esperat, comanda.getArgument(0));
        assertEquals(arg2Esperat, comanda.getArgument(1));
        assertFalse(comanda.esComandaDesconeguda());
    }
    
    @Test
    public void testProcessaComandaPerEliminaNum() {
        String linia = "elimina num Natasha Riba Gorça 111111111";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "elimina num";
        int numArgumentsEsperats = 2;
        String arg1Esperat = "Natasha Riba Gorça";
        String arg2Esperat = "111111111";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(arg1Esperat, comanda.getArgument(0));
        assertEquals(arg2Esperat, comanda.getArgument(1));
        assertFalse(comanda.esComandaDesconeguda());
    }

    @Test
    public void testProcessaComandaPerLlistarStr() {
        String linia = "llista natasha";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "llista str";
        int numArgumentsEsperats = 1;
        String argEsperat = "natasha";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(argEsperat, comanda.getArgument(0));
        assertFalse(comanda.esComandaDesconeguda());
    }

    @Test
    public void testProcessaComandaPerLlistarStrTrim() {
        String linia = "llista  natasha ozores ";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "llista str";
        int numArgumentsEsperats = 1;
        String argEsperat = "natasha ozores";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(argEsperat, comanda.getArgument(0));
        assertFalse(comanda.esComandaDesconeguda());
    }

    @Test
    public void testProcessaComandaPerMostraNom() {
        String linia = "mostra Natasha Riba";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "mostra nom";
        int numArgumentsEsperats = 1;
        String argEsperat = "Natasha Riba";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(argEsperat, comanda.getArgument(0));
        assertFalse(comanda.esComandaDesconeguda());
    }
    
    @Test
    public void testProcessaComandaPerEliminaContacte() {
        String linia = "elimina contacte Natasha Riba";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "elimina contacte";
        int numArgumentsEsperats = 1;
        String argEsperat = "Natasha Riba";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(argEsperat, comanda.getArgument(0));
        assertFalse(comanda.esComandaDesconeguda());
    }
    
    @Test
    public void testProcessaComandaPerPuja() {
        String linia = "puja Natasha Riba";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "puja nom";
        int numArgumentsEsperats = 1;
        String argEsperat = "Natasha Riba";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(argEsperat, comanda.getArgument(0));
        assertFalse(comanda.esComandaDesconeguda());
    }

    @Test
    public void testProcessaComandaPerFlota() {
        String linia = "flota Natasha Riba";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "flota nom";
        int numArgumentsEsperats = 1;
        String argEsperat = "Natasha Riba";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(argEsperat, comanda.getArgument(0));
        assertFalse(comanda.esComandaDesconeguda());
    }
    
    @Test
    public void testProcessaComandaPerBaixa() {
        String linia = "baixa Natasha Riba";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "baixa nom";
        int numArgumentsEsperats = 1;
        String argEsperat = "Natasha Riba";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(argEsperat, comanda.getArgument(0));
        assertFalse(comanda.esComandaDesconeguda());
    }
    
    @Test
    public void testProcessaComandaPerEsfonsa() {
        String linia = "esfonsa Natasha Riba";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "esfonsa nom";
        int numArgumentsEsperats = 1;
        String argEsperat = "Natasha Riba";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(argEsperat, comanda.getArgument(0));
        assertFalse(comanda.esComandaDesconeguda());
    }
    
    @Test
    public void testProcessaComandaPerTroba() {
        String linia = "troba 111111222";
        Comanda comanda = Comanda.processaComanda(linia);
        String nomComandaEsperat = "troba num";
        int numArgumentsEsperats = 1;
        String argEsperat = "111111222";
        assertEquals(nomComandaEsperat, comanda.getNom());
        assertEquals(numArgumentsEsperats, comanda.getNumArguments());
        assertEquals(argEsperat, comanda.getArgument(0));
        assertFalse(comanda.esComandaDesconeguda());
    }
    @Test
    public void testProcessaComandaPerComandaDesconeguda() {
        String linia = "comanda desconeguda";
        Comanda comanda = Comanda.processaComanda(linia);
        assertTrue(comanda.esComandaDesconeguda());
    }
}
