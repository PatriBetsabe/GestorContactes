
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
			c.setCanvi(Canvi.SENSECANVIS);
			c.setGuardatEnFitxer(true);

		}
	}
	
	public List<Contacte> obtenirContactesFitxer(String numero) throws Exception{
		List<Contacte> llistaContactes = new ArrayList<>();
		ArrayList<String> linies = readTextFile("contactes.lst");
		return llistaContactes;
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
		if (!numero.isEmpty()) {
			if (!esNumeroExistent(c, numero)) {
				c.addNumero(numero);
			} else {
				System.out.println("aquest contacte ja té aquest número");
			}
		}
	}

	public void afegirEmailAContacte(Contacte c, String email) {
		if (!email.isEmpty()) {
			if (!esEmailExistent(c, email)) {
				c.addEmail(email);
			} else {
				System.out.println("aquest contacte ja té aquest email");
			}
		}
	}

	// añade un nuevo contacto a la lista de contactos
	public void afegirContacte(Contacte c) throws Exception {
		checkContacteNull(c);
		this.contactes.add(c);
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
		String mensaje = "ajuda: mostra comandes\n"
				+ "llista: mostra els noms dels contactes\n"
				+ "llista «str»: llista els contactes que contenen «str»\n"
				+ "mostra «nom»: mostra les dades del contacte amb aquest nom\n"
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
	public String llistaContactesPerString(String str) {
		String respuesta = "";
		boolean llistat = false;
		for (Contacte c : getContactes()) {
			if (!c.teMarcaEliminat()) {
				if (c.getNom().indexOf(str) != -1 && !llistat) {
					llistat = true;
					respuesta += c.toString();
				}
				for (String n : c.getNums()) {
					if (n.indexOf(str) != -1 && !llistat) {
						llistat = true;
						respuesta += c.toString();
					}
				}
				for (String e : c.getEmails()) {
					if (e.indexOf(str) != -1 && !llistat) {
						llistat = true;
						respuesta += c.toString() +"\n";
					}
				}				
			}
			llistat = false;
		}

		if (respuesta.equals("")) {
			respuesta = "no hi ha cap contacte que contingui '" + str + "'";
		}
		return respuesta;
	}

	// método que lista el nombre de los contactos contenidos en la lista
	public void llistaNomContactes() throws Exception {
		List<Contacte> contactes = this.getContactes();
		List<Contacte> contactesToLlistar = new ArrayList<>();
		for (Contacte c : contactes) {
			if (!c.teMarcaEliminat()) {
				contactesToLlistar.add(c);
			}
		}		
		if (contactesToLlistar.isEmpty()) {
			System.out.println("De moment no hi ha contactes");
		} else {
			for (Contacte c : contactesToLlistar) {
					System.out.println(c.getNom());	
			}
		}
	}

	// metodo que muestra todos los datos del contacto
	public String mostraNom(String nom) {
		String respuesta = "no es troba el contacte";
		if (esNomExistent(nom)) {
			Contacte c = obtenirContacteExistent(nom);
			if (!c.teMarcaEliminat()) {
				respuesta = c.toString();
			}	
		}
		return respuesta;
	}

	// elimina el contacto
	public void eliminarContacte(String nom) throws Exception {
		Scanner input = new Scanner(System.in);
		if (esNomExistent(nom)) {
			Contacte c = obtenirContacteExistent(nom);
			System.out.println("Segur que vols eliminar aquest contacte? SI/NO\n" + c.toString());
			System.out.print(">> ");
			String rpta = input.next();
			switch (rpta.toUpperCase()) {
			case "SI":
				if (c.estaGuardatEnFitxer()) {
					marcaContacteEliminat(c);
				}else {
					eliminaContacte(c);
				}
				System.out.println("Contacte eliminat!");
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

	// método que elimina el telefono del contacto pasado por parámetro
	public void eliminaEmail(String nom, String email) throws InvalidParamException {
		Scanner input = new Scanner(System.in);
		boolean troba = false;
		if (esNomExistent(nom)) {
			Contacte c = obtenirContacteExistent(nom);
			for (int i=0; i<c.getEmails().size();i++) {
				if (c.getEmails().get(i).equals(email)) {
					troba = true;
					if (c.getEmails().size() == 1) {
						System.out.println("aquest contacte té un sol correu electrònic, segur que ho vols eliminar? SI / NO");
						System.out.print(">> ");
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
						marcaContacteModificat(c);
					}
				}
			}
			if (!troba) {
				System.out.println("correu no disponible");
			}
		} else {
			System.out.println("no es troba el contacte");
		}
	}

	// método que elimina el email del contacto pasado por parámetro
	public void eliminaTelefon(String nom, String numero) throws InvalidParamException {
		Scanner input = new Scanner(System.in);
		boolean troba = false;
		if (esNomExistent(nom)) {
			Contacte c = obtenirContacteExistent(nom);
			for (int i=0; i<c.getNums().size();i++) {
				if (c.getNums().get(i).equals(numero)) {
					troba = true;
					if (c.getNums().size() == 1) {
						System.out.println("aquest contacte té només un telèfon, segur que ho vols eliminar? SI/NO");
						System.out.print(">> ");
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
						marcaContacteModificat(c);
					}
				}
			}
			if (!troba) {
				System.out.println("telèfon no disponible");
			}
		} else {
			System.out.println("no es troba el contacte");
		}

	}

	// puja el contacte una posició en l’ordre.
	public void pujaContacte(String nom) {
		if (esNomExistent(nom)) {
			List<Contacte> contactes = getContactes();
			Contacte c = obtenirContacteExistent(nom);
			if (!c.teMarcaEliminat()) {
				int posicio = contactes.indexOf(c);
				if (posicio != 0) {
					Contacte tmp = contactes.get(posicio - 1);
					// puja el contacte
					contactes.set(posicio - 1, c);
					contactes.set(posicio, tmp);
					System.out.println("fet");
					marcaContacteModificat(c);
				}
			}else {
				System.out.println("no es troba el contacte");
			}
		}else {
			System.out.println("no es troba el contacte");
		}
	}

	// puja el contacte com a primer element del llistat.
	public void flotaContacte(String nom) {
		if (esNomExistent(nom)) {
			List<Contacte> contactes = getContactes();
			Contacte c = obtenirContacteExistent(nom);
			if (!c.teMarcaEliminat()) {
				int posicio = contactes.indexOf(c);
				if (posicio != 0) {
					contactes.add(0, c);
					contactes.remove(posicio + 1);
					System.out.println("fet");
					marcaContacteModificat(c);
				}
			}else {
				System.out.println("no es troba el contacte");
			}
		}else {
			System.out.println("no es troba el contacte");
		}
	}

	// baixa el contacte una posició en l’ordre
	public void baixaContacte(String nom) {
		if (esNomExistent(nom)) {
			List<Contacte> contactes = getContactes();
			Contacte c = obtenirContacteExistent(nom);
			if (!c.teMarcaEliminat()) {
				int posicio = contactes.indexOf(c);
				if (posicio != contactes.size() - 1) {
					Contacte tmp = contactes.get(posicio + 1);
					contactes.set(posicio + 1, c);
					contactes.set(posicio, tmp);
					System.out.println("fet");
					marcaContacteModificat(c);
				}
			}else {
				System.out.println("no es troba el contacte");
			}
			
		}else {
			System.out.println("no es troba el contacte");
		}
	}

	// baixa el contacte a la darrera posició del llistat
	public void esfonsaContacte(String nom) {
		if (esNomExistent(nom)) {
			List<Contacte> contactes = getContactes();
			Contacte c = obtenirContacteExistent(nom);
			if (!c.teMarcaEliminat()) {
				int posicio = contactes.indexOf(c);
				if (posicio != contactes.size() - 1) {
					contactes.add(contactes.size(), c);
					contactes.remove(posicio);
					System.out.println("fet");
					marcaContacteModificat(c);
				}
			}else {
				System.out.println("no es troba el contacte");
			}
		}else {
			System.out.println("no es troba el contacte");
		}
	}
	
	//troba tots els contactes que comparteixen aquest número de telèfon
	public void trobaNum(String numero) {
		boolean trobat = false;
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
	
	// mostra els contactes que han estat canviats respecte el que hi ha guardat.
	public void mostraCanvis() {
		// mostrar los cambios de los contactos (añadidos, modificados, borrados)
		for (Contacte c : getContactes()) {
			System.out.println(c.getNom() + " " + c.getCanvi().name() );
		}
	}

	// método que añade un email a un contacto existente o crea uno nuevo public
	public void afegeixEmail(String nom, String email) throws Exception {
		Contacte c = new Contacte(nom);
		if (esNomExistent(nom)) {
			c = obtenirContacteExistent(nom);
			if (c.teMarcaEliminat()) {
				Contacte nouC = new Contacte(c.getNom());
				afegirContacte(nouC);
				afegirEmailAContacte(nouC, email);
			}else {
				afegirEmailAContacte(c, email);
				marcaContacteModificat(c);
			}
		}else {
			afegirContacte(c);
			afegirEmailAContacte(c, email);
		}		
	}

	// método que añade un numero a un contacto existente o crea uno nuevo
	public void afegeixNum(String nom, String numero) throws Exception {
		Contacte c = new Contacte(nom);
			if (esNomExistent(nom)) {
				c = obtenirContacteExistent(nom);
				if (c.teMarcaEliminat()) {
					Contacte nouC = new Contacte(c.getNom());
					afegirContacte(nouC);
					afegirNumeroAcontacte(nouC, numero);
				}else {
					afegirNumeroAcontacte(c, numero);
					marcaContacteModificat(c);
				}
			}else {
				afegirContacte(c);
				afegirNumeroAcontacte(c, numero);
			}		
		}
	
	// método que pone como contacto modificado
	public void marcaContacteModificat(Contacte c) {
		if (c.estaGuardatEnFitxer()) {
			c.setCanvi(Canvi.MODIFICAT);
		}
	}
	
	// método que pone como contacto eliminado
	public void marcaContacteEliminat(Contacte c) {
		if (c.estaGuardatEnFitxer()) {
			c.setCanvi(Canvi.ELIMINAT);
		}
	}

	// método que gestiona los cambios hechos en la lista
	public boolean processaSortida(String entrada) throws Exception {
		switch (entrada.toUpperCase()) {
		case "G":
			guardarCanvis();
			System.out.println("Canvis guardats");
			return true;
		case "I":
			System.out.println("Canvis ignorats");
			return true;
		case "C":
			System.out.println("Sortida cancel·lada");
			return false;
		default:
			System.out.println("no t'entenc ");
			return false;
		}

	}
	
	public void guardarCanvis() throws Exception {
		ArrayList<String> linies = new ArrayList<>();
		List<Contacte> contactes = getContactes();
		if (!contactes.isEmpty()) {
			for (int i=0; i<contactes.size();i++) {
				if (contactes.get(i).teMarcaEliminat()) {
					contactes.remove(contactes.get(i));
				}
			}
		}	
		if (!contactes.isEmpty()) {
			for (int i=0; i<contactes.size();i++) {
				contactes.get(i).setCanvi(Canvi.SENSECANVIS);
				linies.addAll(contactes.get(i).contacteDetallat());
			}
		}
		writeTextFile("contactes.lst", linies, false);

	}

	/* Donat el camí a un fitxer i una seqüència de línies de text, escriu les
     * línies de text al fitxer indicat.
     * El booleà amplia permet indicar:
     *  true: afegir les línies després dels continguts existents al fitxer
     *  false: reemplaçar els continguts anteriors al fitxer pels nous */
    private static void writeTextFile(String path,ArrayList<String> linies,boolean amplia) throws Exception {
        FileWriter fileWriter = new FileWriter(path, amplia);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (String linia: linies) {
            bufferedWriter.write(linia);
        }
        bufferedWriter.close();
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
			Comanda comanda = Comanda.processaComanda(input);
			if (!comanda.esComandaDesconeguda()) {
				if (comanda.getNom().equals("sortir")) {
					System.out.println("Guardar canvis: G, Ignorar canvis: I, Cancelar: C");
					System.out.print(">> ");
					String orden = entrada.nextLine();
					boolean demanaSortir = entorn.processaSortida(orden);
					if (demanaSortir) {
						break;
					}
				}else if (comanda.getNom().equals("ajuda")) {
					System.out.println(entorn.mostraAjuda());
				} else if (comanda.getNom().equals("llista")) {
					entorn.llistaNomContactes();
				} else if (comanda.getNom().equals("llista str")) {
					System.out.println(entorn.llistaContactesPerString(comanda.getArgument(0)));
				} else if (comanda.getNom().equals("mostra nom")) {
					System.out.println(entorn.mostraNom(comanda.getArgument(0)));
				} else if (comanda.getNom().equals("afegeix num")) {
					entorn.afegeixNum(comanda.getArgument(0),comanda.getArgument(1));
				} else if (comanda.getNom().equals("afegeix email")) {
					entorn.afegeixEmail(comanda.getArgument(0),comanda.getArgument(1));
				} else if (comanda.getNom().equals("elimina contacte")) {
					entorn.eliminarContacte(comanda.getArgument(0));
				} else if (comanda.getNom().equals("elimina email")) {
					entorn.eliminaEmail(comanda.getArgument(0),comanda.getArgument(1));
				} else if (comanda.getNom().equals("elimina num")) {
					entorn.eliminaTelefon(comanda.getArgument(0),comanda.getArgument(1));
				} else if (comanda.getNom().equals("puja nom")) {
					entorn.pujaContacte(comanda.getArgument(0));
				} else if (comanda.getNom().equals("flota nom")) {
					entorn.flotaContacte(comanda.getArgument(0));
				} else if (comanda.getNom().equals("baixa nom")) {
					entorn.baixaContacte(comanda.getArgument(0));
				} else if (comanda.getNom().equals("esfonsa nom")) {
					entorn.esfonsaContacte(comanda.getArgument(0));
				} else if (comanda.getNom().equals("troba num")) {
					entorn.trobaNum(comanda.getArgument(0));
				} else if (comanda.getNom().equals("canvis")) {
					entorn.mostraCanvis();
				}
			} else {
				System.out.println("No t'entenc");
			}
		}
		System.out.println("has sortit correctament");
			

	}

}