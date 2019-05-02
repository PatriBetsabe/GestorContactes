
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestorContactes {
	private List<Contacte> contactes = new ArrayList<>();

	public void extraeDados(String text) throws Exception {
		String regex = "^(.+) (NUM|EMAIL) (.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);

		if (matcher.matches()) {
			String nombre = matcher.group(1).trim();
			String email = "";
			String numero = "";

			if (matcher.group(2).trim().equals("NUM")) {
				numero = matcher.group(3).trim();
			} else if (matcher.group(2).trim().equals("EMAIL")) {
				email = matcher.group(3).trim();
			}

			Contacte c = new Contacte(nombre);
			if (!esNomExistent(nombre)) {
				afegirContacte(c);
			} else {
				c = obtenirContacteExistent(nombre);
			}
			afegirDadesAcontacte(c, email, numero);
		}
	}

	// retorna el contacto buscando por el nombre
	public Contacte obtenirContacteExistent(String nom) {
		Contacte contacte = contactes.get(indexContacte(nom));
		return contacte;
	}

	public void afegirDadesAcontacte(Contacte c, String email, String numero) {
		if (email != "") {
			if (!esEmailExistent(c, email)) {
				c.addEmail(email);
				System.out.println("email " + email + " afegit a " + c.getNom());
			}
		} else if (numero != "") {
			if (!esNumeroExistent(c,numero)) {
				c.addNumero(numero);
				System.out.println("numero " + numero + " afegit a " + c.getNom());
			}
		}
	}
	
	public void afegirNumeroAcontacte(Contacte c, String numero) {
		if (numero != "") {
			if (!esNumeroExistent(c,numero)) {
				c.addNumero(numero);
				System.out.println("numero " + numero + " afegit a " + c.getNom());
			}
		}
	}
	// leer fichero "contactes.lst"

	// buscar contactos
	// añadir contactos a la lista de contactos

	// añade un nuevo contacto a la lista de contactos
	public void afegirContacte(Contacte c) throws Exception {
		checkContacteNull(c);
		this.contactes.add(c);
		System.out.println("contacte afegit! " + c.getNom());
	}

	// elimina el contacto indicado por parametro
	public void eliminaContacte(Contacte c) throws Exception {
		checkContacteNull(c);
		contactes.remove(c);
	}

	// comprueba si el contacto indicado por parametro existe
	public void checkContacteNull(Contacte c) throws Exception {
		if (c == null) {
			throw new Exception("No es troba el contacte");
		}
	}

	// comprueba si el contacto existe por nombre
	public boolean esNomExistent(String nom) {
		List<Contacte> contactes = this.getContactes();
		boolean resposta = false;
		if (contactes.isEmpty()) {
			return false;
		} else {
			for (Contacte c : contactes) {
				if (nom.equals(c.getNom())) {
					resposta = true;
					break;
				}
			}
		}

		return resposta;

	}

	// retorna la posicion del contacto buscando por el nombre
	public int indexContacte(String nom) {
		int index = -1;
		List<Contacte> contactes = this.getContactes();
		for (int i = 0; i < contactes.size(); i++) {
			Contacte c = contactes.get(i);
			if (nom.equals(c.getNom())) {
				index = i;
			}
		}
		return index;
	}

	// comprueba si el numero del contacto existe
	public boolean esNumeroExistent(Contacte c,String texto) {
		boolean resposta = false;
		ArrayList<String> nums = c.getNums();
		for (String i : nums) {
			if (texto == i) {
				resposta = true;
				break;
			}
		}
		return resposta;

	}

	// comprueba si el email del contacto existe
	public boolean esEmailExistent(Contacte c,String email) {
		boolean resposta = false;
		ArrayList<String> emails = c.getEmails();
		for (String e : emails) {
			if (email.equals(e)) {
				resposta = true;
				break;
			}
		}
		return resposta;

	}

	// mostrar ayuda (todos los comandos con su descripción)
	public String mostraAjuda() {
		String mensaje = "ajuda: per obtenir ajuda\n" + "llista:mostra la llista de noms de tots els contactes\n"
				+ "llista «str»: mostra la llista de tots els contactes que contenen el substring «str»\n"
				+ "mostra «nom»: mostra totes les dades del contacte amb aquest nom\n"
				+ "elimina contacte «nom»: elimina el contacte amb aquest nom\n"
				+ "elimina num «nom» «num»: elimina el telèfon del contacte\n"
				+ "elimina email «nom» «email»:elimina  l’adreça de correu\n"
				+ "afegeix num «nom» «num»:afegeix el número de telèfon al contacte amb aquest nom\n"
				+ "afegeix email «nom» «email»:afegeix l’adreça de correu al contacte amb aquest nom\n"
				+ "puja «nom»: puja el contacte una posició en l’ordre\n"
				+ "flota «nom»: puja el contacte com a primer element del llistat\n"
				+ "baixa «nom»: baixa el contacte una posició en l’ordre\n"
				+ "enfonsa «nom»: baixa el contacte a la darrera posició del llistat\n"
				+ "troba «num»: troba tots els contactes que comparteixen aquest número de telèfon\n"
				+ "canvis: mostra els contactes que han estat canviats respecte el que hi ha guardat\n"
				+ "sortir: surt";
		return mensaje;

	}

	// método que obtiene los contactos de la lista de contactos
	public List<Contacte> getContactes() {
		return contactes;
	}

	// método que lista los contactos que contienen el substring
	public String llistaContactesPerString(String entrada) {
		String regex = "^(llista) (.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(entrada);
		String s="";
		System.out.println("hola");
		if (matcher.matches()) {
			s = matcher.group(1).trim();
			System.out.println(matcher.group(2).trim());
		}
		/*
		// comprobar si es nombre, email o correo.
		if (esNomExistent(s)) {

		} else if (esNumeroExistent(s)) {

		} else if (esEmailExistent(s)) {

		}
		*/
		return s;
	}

	
	
	// método que lista el nombre de los contactos contenidos en la lista
	public String llistaNomContactes() throws Exception {
		List<Contacte> contactes = this.getContactes();
		if (contactes.isEmpty()) {
			return "De moment no hi ha contactes afegits";
		} else {
			String texto = "";

			for (Contacte c : contactes) {
				texto += c.getNom() + "\n";
			}
			return texto;
		}
	}

	// metodo que muestra todos los datos del contacto
	public String mostraNom(String entrada) {
		String regex = "^(mostra) (.+)$";
		String respuesta = "no es troba el contacte";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(entrada);

		if (matcher.matches()) {
			String nom = matcher.group(2).trim();

			if (esNomExistent(nom)) {
				Contacte c = obtenirContacteExistent(nom);
				respuesta = c.toString();
			} 
		}
		
		return respuesta;

	}

	// to do:
	// método que lista los contactos que contengan el substring pasado por
	// parametro
	// método que muestra todos las datos del contacto con este nombre
	// método que elimina el contacto que tenga el nombre pasado por parametro
	public void esborrarContacte(String entrada) throws Exception {

		// verificar que contacto exista
		String nom = entrada.substring(17);
		System.out.println(nom);

		if (this.esNomExistent(nom)) {
			Contacte contacte = this.contactes.get(indexContacte(nom));
			this.eliminaContacte(contacte);
			System.out.println("s'ha esborrat el contacte");
		} else {
			System.out.println("No es troba el contacte");
		}
		// eliminar contacto
	}

	// retorna el indice del primer digito que encuentra en la cadena de texto
	private int indiceNumero(String entrada) {
		// verifica que telefono exista
		int index = -1;
		// encontrar posicion numero
		for (int i = 0; i < entrada.length(); i++) {
			char act = entrada.charAt(i);
			if (Character.isDigit(act)) {
				index = i;
				break;
			}
		}
		return index;
	}
/*
	// método que elimina el numero del contacto pasado por parámetro
	public void eliminaNum(String entrada) {
		int index = this.indiceNumero(entrada);
		String nom = entrada.substring(12, index - 1);
		String numero = entrada.substring(index);

		if (esNomExistent(nom)) {
			if (esNumeroExistent(c,numero)) {
				Contacte contacte = this.contactes.get(indexContacte(nom));
				contacte.removeNumero(numero);
			} else {
				System.out.println("telefon no disponible");
			}
		} else {
			System.out.println("no es troba el contacte");
		}
	}*/

	// método que elimina el email del contacto pasado por parámetro
	public void eliminaEmail(String entrada) throws InvalidParamException {
		int index = this.indiceNumero(entrada);
		String nom = entrada.substring(12, index - 1);

		int posicio = this.contactes.indexOf(new Contacte(nom));
		System.out.println();

	}

	// método que devuelve el nombre de la entrada
	// método que recibe el nombre y devuelve la posicion del contacto

	// método que cambia a primera posicion el contacto pasado por parametro
	public void pujaContacte(String nom) {

		// guarda la posicion
		// Contacte tmp = contactes.set(index, element);
		// cambia la posicion por el anterior
		// contactes.set(index, element);
		// cambia la posicion
		// mirar metodo insert
	}
/*
	// método que añade un email a un contacto existente o crea uno nuevo
	public void afegeixEmail(String entrada) throws Exception {
		// encontrar posicion email
		String[] partes = entrada.split(" ");
		String email = partes[partes.length - 1];
		String nom = entrada.substring(14, entrada.indexOf(email)).trim();

		if (!esNomExistent(nom)) {
			Contacte nouContacte = new Contacte(nom);
			nouContacte.addEmail(email);
			this.afegirContacte(nouContacte);
		} else {
			if (!esEmailExistent(email)) {
				Contacte contacte = this.contactes.get(indexContacte(nom));
				contacte.addEmail(email);
			} else {
				System.out.println("l'email ja existeix");
			}
		}
	} */

	// método que añade un numero a un contacto existente o crea uno nuevo
	public void afegeixNum(String entrada) throws Exception {
		String regex = "^(afegeix num) (.+) (.+\\d+) *";
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(entrada);
        if (matcher.matches()) {
        	String nom = matcher.group(2).trim();
            String numero = matcher.group(3).trim();
            
            Contacte c = new Contacte(nom);
			if (!esNomExistent(nom)) {
				afegirContacte(c);
			} else {
				c = obtenirContacteExistent(nom);
			}
			afegirNumeroAcontacte(c,numero);
        }		
	}

	// método que gestiona los cambios hechos en la lista
	public void processaSortida(String entrada) {
		System.out.println("Guardar canvis: G, Ignorar canvis: I, Cancelar: C");
		switch (entrada) {
		case "G":
			System.out.println("Canvis guardats");
			break;
		case "I":
			System.out.println("Canvis ignorats");
			break;
		case "C":
			System.out.println("Sortida cancel·lada");
			break;
		default:
			System.out.println("Guardar canvis: G, Ignorar canvis: I, Cancelar: C");
		}

	}

	/*
	 * Donat el camí a un fitxer, llegeix els seus continguts i els retorna en forma
	 * d'ArrayList
	 */
	private static ArrayList<String> readTextFile(String path) throws Exception {
		ArrayList<String> linies = new ArrayList<String>();
		FileReader fileReader = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String linia = "";
		while (linia != null) {
			linia = bufferedReader.readLine();
			if (linia != null) {
				linies.add(linia);
			}
		}
		bufferedReader.close();
		return linies;
	}

	public static void main(String[] args) throws Exception {

		GestorContactes entorn = new GestorContactes();

		ArrayList<String> linies = readTextFile("contactes.lst");
		for (String l : linies) {
			entorn.extraeDados(l);
		}

		Scanner entrada = new Scanner(System.in);
		System.out.println("Gestor de contactes, escriu 'ajuda' per obtenir ajuda");

		while (true) {
			String input = entrada.nextLine();
			if (input.equals("SORTIR")) {
				System.out.println("Guardar canvis: G, Ignorar canvis: I, Cancelar: C");
				String orden = entrada.nextLine();
				entorn.processaSortida(orden);
				break;
			} else if (input.equals("ajuda")) {
				System.out.println(entorn.mostraAjuda());
			} else if (input.equals("llista")) {
				System.out.println(entorn.llistaNomContactes());
			} else if (input.startsWith("llista")) {
				System.out.println(entorn.llistaContactesPerString(input));
			} else if (input.startsWith("mostra")){
				System.out.println(entorn.mostraNom(input));
			} else if (input.startsWith("afegeix num")) {
				entorn.afegeixNum(input);
			} else if (input.toUpperCase().startsWith("AFEGEIX EMAIL")) {
				//entorn.afegeixEmail(input);
			} else if (input.startsWith("ELIMINA CONTACTE")) {
				System.out.println("esborrant contacte....");
				entorn.esborrarContacte(input);
			} else if (input.startsWith("ELIMINA EMAIL") && input.contains("ELIMINA EMAIL")) {
				System.out.println("esborrant email....");
				entorn.esborrarContacte(input);
			} else if (input.startsWith("ELIMINA NUM") && input.contains("ELIMINA NUM")) {
				System.out.println("esborrant num....");
				//entorn.eliminaNum(input);
			} else {
				System.out.println("No t'entenc");
			}
		}
		System.out.println("has sortit correctament");

	}

}