import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FitxerContactes {
	// leer fichero "contactes.lst"
	
	// buscar contactos
	// añadir contactos a la lista de contactos
	
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
				ArrayList<String> emails = c.getEmail();
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
	
	// actualitza les dades
		public void actualitzaFitxer() throws Exception {
			List<Contacte> contactes = this.getContactes();
			this.writeTextFile("contactes.lst", contactes, false);
			ArrayList<String> linies = readTextFile("contactes.lst");
			mostraLinies(linies);
		}

}
