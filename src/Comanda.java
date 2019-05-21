
/*
 * Implementació d'una comanda
 *
 * Una comanda té:
 * - un nom
 * - una llista d'arguments possiblement buida
 */

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Comanda {
    private static final String[] comandesSenseArgs = { "ajuda", "llista", "canvis", "sortir"};
    private static final String[] comandesRegex = {
    	"^afegeix num +([\\p{L} ]+) +([\\w.+]+) *$",
        "^afegeix email +([\\p{L} ]+) +(.+@.+) *$",
        "^elimina contacte +(.+)$",
        "^elimina num +([\\p{L} ]+) +([\\w.+]+) *$",
        "^elimina email +([\\p{L} ]+) +(.+@.+) *$",
        "^llista +(.+)$",
        "^mostra +(.+)$",
        "^puja +(.+)$",
        "^flota +(.+)$",
        "^baixa +(.+)$",
        "^esfonsa +(.+)$",
        "^troba +([\\w.+]+) *$"
    };

    private final String nom;
    private final List<String> arguments;
    private final boolean comandaDesconeguda;

    public Comanda(String nom, String... arguments) {
        this.nom = nom;
        this.arguments = Arrays.asList(arguments);
        this.comandaDesconeguda = false;
    }

    public Comanda() {
        nom = null;
        arguments = null;
        comandaDesconeguda = true;
    }

    public String getNom() {
        return nom;
    }

    public int getNumArguments() {
        return arguments.size();
    }

    public String getArgument(int i) {
        return arguments.get(i);
    }

    public boolean esComandaDesconeguda() {
        return comandaDesconeguda;
    }

    /*
     * donada una línia de text que pot contenir una comanda, retorna la comanda
     * corresponent o bé null si no correspon a una comanda coneguda
     */
    public static Comanda processaComanda(String linia) {
        linia = linia.trim();

        Comanda comanda;

        // comanda amb paraula única
        if ((comanda = comprovaParaulaUnica(linia)) != null)
            return comanda;
        
        // afegeix num
        if ((comanda = comprovaDosArgs(linia, "afegeix num", comandesRegex[0], 1, 2)) != null)
            return comanda;
        
        // afegeix email
        if ((comanda = comprovaDosArgs(linia, "afegeix email", comandesRegex[1], 1, 2)) != null)
            return comanda;
        
        // elimina contacte
        if ((comanda = comprovaUnArg(linia, "elimina contacte", comandesRegex[2], 1)) != null)
            return comanda;
        
        // elimina num
        if ((comanda = comprovaDosArgs(linia, "elimina num", comandesRegex[3], 1, 2)) != null)
            return comanda;
        
        // elimina email
        if ((comanda = comprovaDosArgs(linia, "elimina email", comandesRegex[4], 1, 2)) != null)
            return comanda;
        
        // llista str
        if ((comanda = comprovaUnArg(linia, "llista str", comandesRegex[5], 1)) != null)
            return comanda;
       
        // mostra nom
        if ((comanda = comprovaUnArg(linia, "mostra nom", comandesRegex[6], 1)) != null)
            return comanda;
        
        // puja nom
        if ((comanda = comprovaUnArg(linia, "puja nom", comandesRegex[7], 1)) != null)
            return comanda;
        
        // flota nom
        if ((comanda = comprovaUnArg(linia, "flota nom", comandesRegex[8], 1)) != null)
            return comanda;
        
        // baixa nom
        if ((comanda = comprovaUnArg(linia, "baixa nom", comandesRegex[9], 1)) != null)
            return comanda;
        
        // esfonsa nom
        if ((comanda = comprovaUnArg(linia, "esfonsa nom", comandesRegex[10], 1)) != null)
            return comanda;

        // troba num
        if ((comanda = comprovaUnArg(linia, "troba num", comandesRegex[11], 1)) != null)
            return comanda;

        // retorna una comanda desconeguda
        return new Comanda();
    }

    private static Comanda comprovaParaulaUnica(String linia) {
        linia = linia.trim();
        for (String paraula: comandesSenseArgs) {
            if (paraula.equals(linia)) {
                return new Comanda(paraula);
            }
        }
        return null;
    }

    private static Comanda comprovaUnArg(String linia, String nomComanda, String regex, int group) {
        Matcher matcher = Pattern.compile(regex).matcher(linia);
        if (matcher.matches()) {
            return new Comanda(nomComanda, matcher.group(group));
        }
        return null;
    }

    private static Comanda comprovaDosArgs(String linia, String nomComanda, String regex, int group1, int group2) {
        Matcher matcher = Pattern.compile(regex).matcher(linia);
        if (matcher.matches()) {
            return new Comanda(nomComanda, matcher.group(group1), matcher.group(group2));
        }
        return null;
    }
}

