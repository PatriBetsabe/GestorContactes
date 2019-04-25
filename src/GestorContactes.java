
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorContactes {
	private List<Contacte> contactes = new ArrayList<>();
	

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

	// mostrar ayuda (todos los comandos con su descripción)
	public String mostraAjuda() {
		String mensaje = "ajuda: per obtenir ajuda\n"
				+ "llista:mostra la llista de noms de tots els contactes\n"
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
			texto += "Nom: " + c.getNom()+ "\n";
		}
	
		return texto;
	}
	// método que lista los contactos que contengan el substring pasado por parametro
	// método que muestra todos las datos del contacto con este nombre
	// método que elimina el contacto que tenga el nombre pasado por parametro
	// método que elimina el numero del contacto pasado por parámetro
	// método que elimina el email del contacto pasado por parámetro
	// método que añade un numero a un contacto existente o crea uno nuevo
	// método que añade un email a un contacto existente o crea uno nuevo
	// método que cambia a primera posicion el contacto pasado por parametro
	// 

	
	public static void main(String[] args) throws Exception {

		GestorContactes entorn = new GestorContactes();
		Contacte patri = new Contacte("Patricia Lamadrid");
		entorn.afegirContacte(patri);
		
		
		
		Scanner entrada = new Scanner(System.in);
		System.out.println("Gestor de contactes, escriu 'ajuda' per obtenir ajuda");
		String input = entrada.nextLine().toUpperCase();
		
		if(input.indexOf("AJUDA")!= -1) {		
			System.out.println(entorn.mostraAjuda()); 
			
		} else if (input.equals("LLISTA")) {
			System.out.println(entorn.mostraContactes()); 
		}
		
		
		
		
	}

}