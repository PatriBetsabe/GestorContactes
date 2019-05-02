import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FitxerContactes {
	private String path = "contactes.lst";
	private static List<Contacte> contactes = new ArrayList<>();
	
	// método que obtiene los contactos de la lista de contactos
		public static List<Contacte> getContactes() {
			return contactes;
		}
		
		// añade un nuevo contacto a la lista de contactos
		public void afegirContacte(Contacte c) throws Exception {
			checkContacteNull(c);
			this.contactes.add(c);
			// System.out.println("contacte afegit!");
		}
		
		// comprueba si el contacto indicado por parametro existe
		public void checkContacteNull(Contacte c) throws Exception {
			if (c == null) {
				throw new Exception("No es troba el contacte");
			}
		}

	
	public static void main(String[] args) throws Exception{
		
		ArrayList<String> linies = readTextFile("contactes.lst");
		for (String l: linies) {
			extraeDados(l);
		}
		
		//mostraLinies(linies);
		
		//extraeDados("   Patricia Lamadrid rgrgrgr NUM +34 697824853");
		//extraeDados("   Patricia Lamadrid EMAIL   patty@gmail.com");
	}
	public static void extraeDados(String text) throws InvalidParamException {
		String regex = "^(.+) (NUM|EMAIL) (.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		if (matcher.matches()) {
			System.out.println(matcher.group(1).trim());
			System.out.println(matcher.group(3).trim());
			
			String nombre = matcher.group(1).trim();
			String email = "";
			String numero = "";
			
			
			// si contacto existe
			Contacte c = new Contacte(nombre);
			if (esContacteAfegit(nombre))
			// si la lista esta vacia añade contacto y agrega datos
			
			// busca contacto por nombre
			// si existe contacto en la lista, agrega datos
			// si no existe contacto, crea contacto, y agrega datos
			
			
			if (matcher.group(2).trim().equals("NUM")){
				System.out.println(matcher.group(3).trim() + " es un numero!\n");
				numero = matcher.group(3).trim();
			}
			else if (matcher.group(2).trim().equals("EMAIL")){
				System.out.println(matcher.group(3).trim() + " es una direccion de correo!\n");
				email = matcher.group(2).trim();
			}
		}
	}
	
	// leer fichero "contactes.lst"
	
	// buscar contactos
	// añadir contactos a la lista de contactos
	private static boolean esContacteAfegit(String nom) {
		List<Contacte> contactes = getContactes();
		if (contactes.isEmpty()) {
		return false;
	
		}
		return false;}

	
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
				ArrayList<String> nums = c.getNums();
				ArrayList<String> emails = c.getEmails();
				if (!nums.isEmpty()) {
					for (String i : nums) {
						linia = c.getNom() + " NUM " + i + "\n";
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
	
	/*
	// actualitza les dades
		public void actualitzaFitxer() throws Exception {
			List<Contacte> contactes = this.getContactes();
			this.writeTextFile("contactes.lst", contactes, false);
			ArrayList<String> linies = readTextFile("contactes.lst");
			mostraLinies(linies);
		}
*/
}
