
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
		System.out.println("contacte afegit!");
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
	public String mostraContactes() {
		List<Contacte> contactes = this.getContactes();
		String texto = "";

		for (Contacte c : contactes) {
			texto += "Nom: " + c.getNom() + "\n";
		}

		return texto;
	}

	// to do:
	// método que lista los contactos que contengan el substring pasado por
	// parametro
	// método que muestra todos las datos del contacto con este nombre
	// método que elimina el contacto que tenga el nombre pasado por parametro
	// método que elimina el numero del contacto pasado por parámetro
	// método que elimina el email del contacto pasado por parámetro
	// método que añade un email a un contacto existente o crea uno nuevo
	// método que cambia a primera posicion el contacto pasado por parametro

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
				System.out.println("el nombre ja existeix");
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
		}

	}

	public static void main(String[] args) throws Exception {

		GestorContactes entorn = new GestorContactes();
		Contacte c1 = new Contacte("Rafael Marín John", 933349231);
		Contacte c2 = new Contacte("Aina Suàrez Romagossa","aina1998@coldmail.com");
		
		entorn.afegirContacte(c1);
		entorn.afegirContacte(c2);
		List<Contacte> contactes = entorn.getContactes();
		entorn.writeTextFile("contactes.lst", contactes, false);
		ArrayList<String> linies = readTextFile("contactes.lst");
		mostraLinies(linies);
		
		/*

		Scanner entrada = new Scanner(System.in);
		System.out.println("Gestor de contactes, escriu 'ajuda' per obtenir ajuda");

		while (true) {
			String input = entrada.nextLine().toUpperCase();
			if (input.equals("SURT")) {
				System.out.println("Guardar canvis: G, Ignorar canvis: I, Cancelar: C");
				String orden = entrada.nextLine().toUpperCase();
				entorn.processaSortida(orden);
				break;
			} else if (input.indexOf("AJUDA") == 0 && input.contains("AJUDA")) {
				System.out.println(entorn.mostraAjuda());
			} else if (input.equals("LLISTA")) {
				System.out.println("listando...");
			} else if (input.startsWith("AFEGEIX NUM")) {
				System.out.println("afegint....");
				entorn.afegeixNum(input);
			} else if (input.indexOf("AFEGEIX EMAIL") == 0 && input.contains("AFEGEIX EMAIL")) {
				System.out.println("vols afegir un email!");
			} else {
				System.out.println("No t'entenc");
			}

		}
		System.out.println("has sortit correctament");
		
		*/

	}

	/* Donat el camí a un fitxer, llegeix els seus continguts i els retorna en
     * forma d'ArrayList */
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
		}else {
			for (Contacte c : contactes) {
				ArrayList<Integer> nums = c.getNums();
				ArrayList<String> emails = c.getEmail();
				if (!nums.isEmpty()) {
					for (Integer i : nums) {
						linia =  c.getNom() + " NUM " + i.intValue();
						bufferedWriter.write(linia);
					}
				}
				if (!emails.isEmpty()) {
					for (String s : emails) {
						linia = c.getNom() + " EMAIL: " + s;
						bufferedWriter.write(linia);
					}
				}
			}
			bufferedWriter.close();
		}
	}
	
	/* Donada una seqüència de línies, les mostra per sortida estàndard */
    private static void mostraLinies(ArrayList<String> linies) {
        for (String linia: linies) {
            System.out.println("Linia: " + linia);
        }
    }

}