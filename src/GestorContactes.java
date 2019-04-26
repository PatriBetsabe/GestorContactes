
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorContactes {
	private List<Contacte> contactes = new ArrayList<>();
	private String path = "contactes.lst";

	// añade un nuevo contacto a la lista de contactos
	public void afegirContacte(Contacte c) throws Exception {
		checkContacteNull(c);
		this.contactes.add(c);
		// System.out.println("contacte afegit!");
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
	public boolean existeixNom(String nom) {
		List<Contacte> contactes = this.getContactes();
		boolean resposta = false;
		for (Contacte c : contactes) {
			if (nom.equals(c.getNom())) {
				resposta = true;
				break;
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
	public boolean existeixNumero(int num) {
		List<Contacte> contactes = this.getContactes();
		boolean resposta = false;
		for (Contacte c : contactes) {
			ArrayList<Integer> nums = c.getNums();
			for (Integer i : nums) {
				if (num == i.intValue()) {
					resposta = true;
					break;
				}
			}
		}
		return resposta;

	}

	// comprueba si el email del contacto existe
	public boolean existeixEmail(String email) {
		List<Contacte> contactes = this.getContactes();
		boolean resposta = false;
		for (Contacte c : contactes) {
			ArrayList<String> emails = c.getEmail();
			for (String e : emails) {
				if (email.equals(e)) {
					resposta = true;
					break;
				}
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

	// método que lista con contactos contenidos en la lista
	public String llistaContactes() throws Exception {
		/*
		 * Contacte c1 = new Contacte("Rafael Marín John", 933349231); Contacte c2 = new
		 * Contacte("Aina Suàrez Romagossa",643321243);
		 * 
		 * this.afegirContacte(c1); this.afegirContacte(c2); c2.addNumero(926345123);
		 * c2.addEmail("aina1998@coldmail.com");
		 */

		List<Contacte> contactes = this.getContactes();
		String texto = "";

		for (Contacte c : contactes) {
			texto += c.getNom() + "\n";
		}

		return texto;
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

		if (this.existeixNom(nom)) {
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

	// método que elimina el numero del contacto pasado por parámetro
	public void eliminaNum(String entrada) {
		int index = this.indiceNumero(entrada);
		String nom = entrada.substring(12, index - 1);
		int numero = Integer.parseInt(entrada.substring(index));
		
		if (existeixNom(nom)) {
			if (existeixNumero(numero)) {
				Contacte contacte = this.contactes.get(indexContacte(nom));
				contacte.removeNumero(numero);
			} else {
				System.out.println("telefon no disponible");
			}
		}else {
			System.out.println("no es troba el contacte");
		}
	}
	// método que elimina el email del contacto pasado por parámetro
	public void eliminaEmail(String entrada) throws InvalidParamException {
		int index = this.indiceNumero(entrada);
		String nom = entrada.substring(12, index - 1);
		
		int posicio = this.contactes.indexOf(new Contacte(nom));
		System.out.println();
		
	}
	// método que añade un email a un contacto existente o crea uno nuevo
	// método que cambia a primera posicion el contacto pasado por parametro

	// método que añade un email a un contacto existente o crea uno nuevo
	public void afegeixEmail(String entrada) throws Exception {
		// encontrar posicion email
		String[] partes = entrada.split(" ");
		String email = partes[partes.length - 1];
		System.out.println(email);

		String nom = entrada.substring(14, entrada.indexOf(email));
		System.out.println(nom);

		if (!existeixNom(nom)) {
			Contacte nouContacte = new Contacte(nom, email);
			this.afegirContacte(nouContacte);
		} else {
			if (!existeixEmail(email)) {
				Contacte contacte = this.contactes.get(indexContacte(nom));
				contacte.addEmail(email);
			} else {
				System.out.println("l'email ja existeix");
			}
		}
	}

	// método que añade un numero a un contacto existente o crea uno nuevo
	public void afegeixNum(String entrada) throws Exception {
		int index = -1;
		// encontrar posicion numero
		for (int i = 0; i < entrada.length(); i++) {
			char act = entrada.charAt(i);
			if (Character.isDigit(act)) {
				index = i;
				break;
			}
		}
		// extreure dades
		String nom = entrada.substring(12, index - 1);
		System.out.println("nom del contacte: " + nom);
		int numero = Integer.parseInt(entrada.substring(index));
		System.out.println("i telefon: " + numero);

		if (!existeixNom(nom)) {
			Contacte nouContacte = new Contacte(nom, numero);
			this.afegirContacte(nouContacte);
		} else {
			if (!existeixNumero(numero)) {
				Contacte contacte = this.contactes.get(indexContacte(nom));
				contacte.addNumero(numero);
			} else {
				System.out.println("el numero ja existeix");
			}
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

	// actualitza les dades
	public void actualitzaFitxer() throws Exception {
		List<Contacte> contactes = this.getContactes();
		this.writeTextFile("contactes.lst", contactes, false);
		ArrayList<String> linies = readTextFile("contactes.lst");
		mostraLinies(linies);
	}

	public static void main(String[] args) throws Exception {

		GestorContactes entorn = new GestorContactes();
		/*
		 * Contacte c1 = new Contacte("Rafael Marín John", 933349231); Contacte c2 = new
		 * Contacte("Aina Suàrez Romagossa",643321243);
		 * 
		 * entorn.afegirContacte(c1); entorn.afegirContacte(c2);
		 * c2.addNumero(926345123); c2.addEmail("aina1998@coldmail.com"); List<Contacte>
		 * contactes = entorn.getContactes(); entorn.writeTextFile("contactes.lst",
		 * contactes, false); ArrayList<String> linies = readTextFile("contactes.lst");
		 * mostraLinies(linies);
		 */

		Scanner entrada = new Scanner(System.in);
		System.out.println("Gestor de contactes, escriu 'ajuda' per obtenir ajuda");

		while (true) {
			String input = entrada.nextLine().toUpperCase();
			if (input.equals("SORTIR")) {
				System.out.println("Guardar canvis: G, Ignorar canvis: I, Cancelar: C");
				String orden = entrada.nextLine().toUpperCase();
				entorn.processaSortida(orden);
				break;
			} else if (input.indexOf("AJUDA") == 0 && input.contains("AJUDA")) {
				System.out.println(entorn.mostraAjuda());
			} else if (input.equals("LLISTA")) {
				System.out.println(entorn.llistaContactes());
			} else if (input.startsWith("AFEGEIX NUM")) {
				System.out.println("afegint num....");
				entorn.afegeixNum(input);
				entorn.actualitzaFitxer();
			} else if (input.indexOf("AFEGEIX EMAIL") == 0 && input.contains("AFEGEIX EMAIL")) {
				System.out.println("afegint email....");
				entorn.afegeixEmail(input);
				entorn.actualitzaFitxer();
			} else if (input.startsWith("ELIMINA CONTACTE")) {
				System.out.println("esborrant contacte....");
				entorn.esborrarContacte(input);
			} else if (input.startsWith("ELIMINA EMAIL") && input.contains("ELIMINA EMAIL")) {
				System.out.println("esborrant email....");
				entorn.esborrarContacte(input);
			} else if (input.startsWith("ELIMINA NUM") && input.contains("ELIMINA NUM")) {
				System.out.println("esborrant num....");
				entorn.eliminaNum(input);
			} else {
				System.out.println("No t'entenc");
			}

		}
		System.out.println("has sortit correctament");

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

	// Escriu les dades dels contactes al fitxer 'contactes.lst'
	private void writeTextFile(String path, List<Contacte> contactes, boolean amplia) throws Exception {
		FileWriter fileWriter = new FileWriter(path, amplia);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		String linia = "";
		if (contactes.isEmpty()) {
			System.out.println("no hi ha cap contacte");
		} else {
			for (Contacte c : contactes) {
				ArrayList<Integer> nums = c.getNums();
				ArrayList<String> emails = c.getEmail();
				if (!nums.isEmpty()) {
					for (Integer i : nums) {
						linia = c.getNom() + " NUM " + i.intValue() + "\n";
						bufferedWriter.write(linia);
					}
				}
				if (!emails.isEmpty()) {
					for (String s : emails) {
						linia = c.getNom() + " EMAIL " + s + "\n";
						bufferedWriter.write(linia);
					}
				}
			}
			bufferedWriter.close();
		}
	}

	/* Donada una seqüència de línies, les mostra per sortida estàndard */
	private static void mostraLinies(ArrayList<String> linies) {
		for (String linia : linies) {
			System.out.println(linia);
		}
	}

}