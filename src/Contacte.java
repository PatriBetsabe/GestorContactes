
import java.util.ArrayList;

public class Contacte {

	/* propiedades */
	private String nom;
	private ArrayList<String> nums = new ArrayList<String>();
	private ArrayList<String> emails = new ArrayList<String>();
	private Canvi canvi = Canvi.SINCAMBIOS;

	// Constructor por defecto
	public Contacte() {
	}
	
	/*Constructor con los datos enteros */
	public Contacte(String nom, ArrayList<String> nums, ArrayList<String> emails) throws InvalidParamException {
		if (nom.equals("") || nom == null)
			throw new InvalidParamException("Parametros inválidos");
		else {
			this.nom = nom;
		}
		
		if (nums.isEmpty()) {
			this.nums = new ArrayList<String>();
		} else {
			this.nums = nums;
		}
		
		if (emails.isEmpty()) {
			this.emails = new ArrayList<String>();
		} else {
			this.emails = emails;
		}
	}
	
	
	/* Constructor con solo el nombre del contacto */
	public Contacte(String nom) throws InvalidParamException {
		if (nom.equals("") || nom == null)
			throw new InvalidParamException("Introduce un nombre");
		else {
			this.nom = nom;
		}
	}

	// métodos
	
	/* getters & setters */
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) throws InvalidParamException {
		if (nom == null || nom.equals(""))
			throw new InvalidParamException();
		this.nom = nom;
	}
	
	public Canvi getCanvi() {
		return canvi;
	}
	
	public void setCanvi(Canvi canvi) {
		this.canvi = canvi;
	}
	
	/* retorna true si el contacto tiene numero(s) de telefono*/
	public boolean contacteTeNumero() {
		return this.nums.isEmpty();
	}
	
	/* retorna true si el contacte tienes email(s) */
	public boolean contacteTeCorreo() {
		return this.emails.isEmpty();
	}

	public ArrayList<String> getNums() {
		return nums;
	}

	public ArrayList<String> getEmails() {
		return emails;
	}
	
	public void addNumero(String num) {
		this.nums.add(num);
	}
	
	public void addEmail(String email) {
		this.emails.add(email);
	}
	
	public void removeNumero(String numero) {
		this.nums.remove(numero);
	}
	
	public void removeEmail(String email) {
		this.emails.remove(email);
	}
	
	@Override
	public String toString() {
		String texto = "Nom: " + this.getNom() + "\n";
		if (!nums.isEmpty()) {
			for (String n : getNums()) {
				texto += "Numero: " + n +"\n";
			}
		}
		
		if (!emails.isEmpty()) {
			for (String e : getEmails()) {
				texto += "Email: " + e + "\n";
			}
		}
		return texto;
	}
}
