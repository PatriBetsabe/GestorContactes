
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
		afegirEmailAContacte(c, email);
		afegirNumeroAcontacte(c, numero);
	}

	public void afegirNumeroAcontacte(Contacte c, String numero) {
		if (!numero.equals("")) {
			if (!esNumeroExistent(c, numero)) {
				c.addNumero(numero);
				System.out.println("número afegit");
			} else {
				System.out.println("aquest contacte ja té aquest número");
			}
		}
	}

	public void afegirEmailAContacte(Contacte c, String email) {
		if (!email.equals("")) {
			if (!esEmailExistent(c, email)) {
				c.addEmail(email);
				System.out.println("email afegit");
			} else {
				System.out.println("aquest contacte ja té aquest email");
			}
		}
	}

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
			throw new Exception();
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
	public boolean esNumeroExistent(Contacte c, String texto) {
		boolean resposta = false;
		ArrayList<String> nums = c.getNums();
		for (String i : nums) {
			if (texto.equals(i)) {
				resposta = true;
				break;
			}
		}
		return resposta;

	}

	// comprueba si el email del contacto existe
	public boolean esEmailExistent(Contacte c, String email) {
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
		String respuesta = "";
		String str = "";
		if (matcher.matches()) {
			str = matcher.group(2).trim();
		}

		for (Contacte c : getContactes()) {
			if (c.getNom().indexOf(str) != -1) {
				respuesta += c.toString();
			}
			for (String e : c.getEmails()) {
				if (e.indexOf(str) != -1) {
					respuesta += c.toString();
				}
			}
			for (String n : c.getNums()) {
				if (n.indexOf(str) != -1) {
					respuesta += c.toString();
				}
			}
		}

		if (respuesta.equals("")) {
			respuesta = "no hi ha cap contacte que contingui ' " + str + " '";
		}

		return respuesta;
	}

	// método que lista el nombre de los contactos contenidos en la lista
	public void llistaNomContactes() throws Exception {
		List<Contacte> contactes = this.getContactes();
		if (contactes.isEmpty()) {
			System.out.println("De moment no hi ha contactes");
		} else {
			for (Contacte c : contactes) {
				System.out.println(c.getNom());
			}
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

	// elimina el contacto
	public void esborrarContacte(String entrada) throws Exception {
		Scanner input = new Scanner(System.in);
		String regex = "^(elimina contacte) (.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(entrada);
		if (matcher.matches()) {
			String nom = matcher.group(2).trim();
			if (esNomExistent(nom)) {
				Contacte c = obtenirContacteExistent(nom);
				System.out.println("Segur que vols eliminar aquest contacte? SI /NO\n" + c.toString());
				String rpta = input.next();
				switch (rpta.toUpperCase()) {
				case "SI":
					eliminaContacte(c);
					System.out.println("Contacte esborrat!");
					break;
				case "NO":
					System.out.println("Has cancel·lat l'eliminació");
					break;
				default:
					System.out.println("No t'entenc");
				}

			} else {
				System.out.println("no es troba el contacte");
			}
		}

	}

	// método que elimina el telefono del contacto pasado por parámetro
	public void eliminaEmail(String entrada) throws InvalidParamException {
		Scanner input = new Scanner(System.in);
		String regex = "^(elimina email) (.+) (.+@.+) *";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(entrada);
		if (matcher.matches()) {
			String nom = matcher.group(2).trim();
			String email = matcher.group(3).trim();
			boolean troba = false;
			if (esNomExistent(nom)) {
				Contacte c = obtenirContacteExistent(nom);
				for (String e : c.getEmails()) {
					if (e.equals(email)) {
						troba = true;
						if (c.getEmails().size() == 1) {
							System.out.println("aquest contacte té un sol correu electrònic, segur que ho vols eliminar? SI / NO\n" + e );
							String rpta = input.next();
							switch (rpta.toUpperCase()) {
							case "SI":
								c.removeEmail(email);
								System.out.println("correu eliminat");
								break;
							case "NO":
								System.out.println("Has cancel·lat l'eliminació");
								break;
							default:
								System.out.println("No t'entenc");
							}
						} else {
							c.removeEmail(email);
						}
					}
				}
				if (!troba) {
					System.out.println("correu no disponible");
				}
			} else {
				System.out.println("no es troba el contacte");
			}
		} else {
			System.out.println("format de correu incorrecte");
		}

	}

	// método que elimina el email del contacto pasado por parámetro
	public void eliminaTelefon(String entrada) throws InvalidParamException {
		Scanner input = new Scanner(System.in);
		String regex = "^(elimina num) (.+) (.+\\d+) *";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(entrada);
		if (matcher.matches()) {
			String nom = matcher.group(2).trim();
			String numero = matcher.group(3).trim();
			boolean troba = false;
			if (esNomExistent(nom)) {
				Contacte c = obtenirContacteExistent(nom);
				for (String n : c.getNums()) {
					if (n.equals(numero)) {
						troba = true;
						if (c.getNums().size() == 1) {
							System.out.println("aquest contacte té només un telèfon, segur que ho vols eliminar? SI/NO\n"+ n);
							String rpta = input.next();
							switch (rpta.toUpperCase()) {
							case "SI":
								c.removeNumero(numero);
								System.out.println("telèfon eliminat");
								break;
							case "NO":
								System.out.println("Has cancel·lat l'eliminació");
								break;
							default:
								System.out.println("No t'entenc");
							}
						} else {
							c.removeNumero(numero);
						}
					}
				}
				if (!troba) {
					System.out.println("telèfon no disponible");
				}
			} else {
				System.out.println("no es troba el contacte");
			}
		} else {
			System.out.println("format de telèfon incorrecte");
		}

	}

	// puja el contacte una posició en l’ordre.
	public void pujaContacte(String entrada) {
		String regex = "^(puja) (.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(entrada);
		if (matcher.matches()) {
			String nom = matcher.group(2).trim();
			if (esNomExistent(nom)) {
				List<Contacte> contactes = getContactes();
				Contacte c = obtenirContacteExistent(nom);
				int posicio = contactes.indexOf(c);
				System.out.println("las posicio del contacte es: " + posicio);
				if (posicio != 0) {
					Contacte tmp = contactes.get(posicio - 1);
					System.out.println(posicio);
					// puja el contacte
					contactes.set(posicio - 1, c);
					contactes.set(posicio, tmp);
					System.out.println("fet, la nova posicio de " + c.getNom() + " es: " + contactes.indexOf(c));
				}
			} else {
				System.out.println("no es troba el contacte");
			}
		}
		// mirar metodo insert
	}

	// puja el contacte com a primer element del llistat.
	public void flotaContacte(String entrada) {
		String regex = "^(flota) (.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(entrada);
		if (matcher.matches()) {
			String nom = matcher.group(2).trim();
			if (esNomExistent(nom)) {
				List<Contacte> contactes = getContactes();
				Contacte c = obtenirContacteExistent(nom);
				int posicio = contactes.indexOf(c);
				if (posicio != 0) {
					contactes.add(0, c);
					contactes.remove(posicio + 1);
					System.out.println("fet");
				}
			} else {
				System.out.println("no es troba el contacte");
			}
		}
	}

	// baixa el contacte una posició en l’ordre
	public void baixaContacte(String entrada) {
		String regex = "^(baixa) (.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(entrada);
		if (matcher.matches()) {
			String nom = matcher.group(2).trim();
			if (esNomExistent(nom)) {
				List<Contacte> contactes = getContactes();
				Contacte c = obtenirContacteExistent(nom);
				int posicio = contactes.indexOf(c);
				if (posicio != contactes.size() - 1) {
					Contacte tmp = contactes.get(posicio + 1);
					contactes.set(posicio + 1, c);
					contactes.set(posicio, tmp);
					System.out.println("fet");
				}
			} else {
				System.out.println("no es troba el contacte");
			}
		}
	}

	// baixa el contacte a la darrera posició del llistat
	public void esfonsaContacte(String entrada) {
		String regex = "^(esfonsa) (.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(entrada);
		if (matcher.matches()) {
			String nom = matcher.group(2).trim();
			if (esNomExistent(nom)) {
				List<Contacte> contactes = getContactes();
				Contacte c = obtenirContacteExistent(nom);
				int posicio = contactes.indexOf(c);
				if (posicio != contactes.size() - 1) {
					contactes.add(contactes.size(), c);
					contactes.remove(posicio);
					System.out.println("fet");
				}
			} else {
				System.out.println("no es troba el contacte");
			}
		}
	}
	
	//troba tots els contactes que comparteixen aquest número de telèfon
	public void trobaNum(String entrada) {
		String regex = "^(troba) (.+\\d+) *";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(entrada);
		boolean trobat = false;
		if (matcher.matches()) {
			String numero = matcher.group(2).trim();
			for (Contacte c : getContactes()) {
				if (esNumeroExistent(c, numero)) {
					trobat = true;
					System.out.println(c.toString());
				}
			}
			if (!trobat) {
				System.out.println("no es troba el contacte");
			}
		}
	}
	
	// mostra els contactes que han estat canviats respecte el que hi ha guardat.
	public void mostraCanvis() {
		
	}

	// método que añade un email a un contacto existente o crea uno nuevo public
	public void afegeixEmail(String entrada) throws Exception {
		String regex = "^(afegeix email) (.+) (.+@.+) *";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(entrada);
		if (matcher.matches()) {
			String nom = matcher.group(2).trim();
			String email = matcher.group(3).trim();

			Contacte c = new Contacte(nom);
			if (esNomExistent(nom)) {
				afegirContacte(c);
			} else {
				c = obtenirContacteExistent(nom);
			}
			afegirNumeroAcontacte(c, email);
		} else {
			System.out.println("el format del email és incorrecte");
		}
	}

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
			afegirNumeroAcontacte(c, numero);
		} else {
			System.out.println("el format del nombre és incorrecte");
		}
	}

	// método que gestiona los cambios hechos en la lista
	public void processaSortida(String entrada) {
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
			System.out.println("no t'entenc ");
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
			System.out.print(">> ");
			String input = entrada.nextLine();
			if (input.equals("sortir")) {
				System.out.println("Guardar canvis: G, Ignorar canvis: I, Cancelar: C");
				System.out.print(">> ");
				String orden = entrada.nextLine();
				entorn.processaSortida(orden);
				break;
			} else if (input.equals("ajuda")) {
				System.out.println(entorn.mostraAjuda());
			} else if (input.equals("llista")) {
				entorn.llistaNomContactes();
			} else if (input.startsWith("llista")) {
				System.out.println(entorn.llistaContactesPerString(input));
			} else if (input.startsWith("mostra")) {
				System.out.println(entorn.mostraNom(input));
			} else if (input.startsWith("afegeix num")) {
				entorn.afegeixNum(input);
			} else if (input.startsWith("afegeix email")) {
				entorn.afegeixEmail(input);
			} else if (input.startsWith("elimina contacte")) {
				entorn.esborrarContacte(input);
			} else if (input.startsWith("elimina email")) {
				entorn.eliminaEmail(input);
			} else if (input.startsWith("elimina num")) {
				entorn.eliminaTelefon(input);
			} else if (input.startsWith("puja")) {
				entorn.pujaContacte(input);
			} else if (input.startsWith("flota")) {
				entorn.flotaContacte(input);
			} else if (input.startsWith("baixa")) {
				entorn.baixaContacte(input);
			} else if (input.startsWith("esfonsa")) {
				entorn.esfonsaContacte(input);
			} else if (input.startsWith("troba")) {
				entorn.trobaNum(input);
			} else if (input.equals("canvis")) {
				entorn.mostraCanvis();
			} else {
				System.out.println("No t'entenc");
			}
		}
		System.out.println("has sortit correctament");

	}

}