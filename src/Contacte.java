
import java.awt.List;
import java.util.ArrayList;

public class Contacte {

	/* propiedades */
	private String nom;
	private ArrayList<Integer> nums = new ArrayList<Integer>();
	private ArrayList<String> emails = new ArrayList<String>();

	/* constructores específicos */
	
	// Constructor por defecto
	public Contacte() {
	}
	
	/*Constructor con los datos enteros */
	public Contacte(String nom, ArrayList<Integer> nums, ArrayList<String> emails) throws InvalidParamException {
		if (nom.equals("") || nom == null)
			throw new InvalidParamException("Parametros inválidos");
		else {
			this.nom = nom;
		}
		
		if (nums.isEmpty()) {
			this.nums = new ArrayList<Integer>();
		} else {
			this.nums = nums;
		}
		
		if (emails.isEmpty()) {
			this.emails = new ArrayList<String>();
		} else {
			this.emails = emails;
		}
	}
	/* Constructor con el nombre y el numero de telefono del contacto*/
	public Contacte(String nom, int telefon) throws InvalidParamException {
		if (nom.equals("") || nom == null)
			throw new InvalidParamException("Parametros inválidos");
		else {
			this.nom = nom;
		}

		if (telefon == 0 || Integer.toString(telefon).length() != 9) {
			throw new InvalidParamException("numero de teléfono no válido");
		} else {
			this.nums.add(telefon);
		}

	}

	/* Constructor con el nombre y mail dirección de correo contacto */
	public Contacte(String nom, String email) throws InvalidParamException {
		if (!email.contains("@")) {
			throw new InvalidParamException("Formato de email no válido");
		} else if (!email.equals("")) {
			this.emails.add(email);
		}

		if (nom.equals("") || nom == null)
			throw new InvalidParamException("Introduce un nombre");
		else {
			this.nom = nom;
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
	
	/* retorna true si el contacto tiene numero(s) de telefono*/
	public boolean contacteTeNumero() {
		return this.nums.isEmpty();
	}
	
	/* retorna true si el contacte tienes email(s) */
	public boolean contacteTeCorreo() {
		return this.emails.isEmpty();
	}

	public ArrayList<Integer> getNums() {
		return nums;
	}

	public ArrayList<String> getEmail() {
		return emails;
	}
	
	public void addNumero(int num) {
		this.nums.add(num);
	}
	
	public void addEmail(String email) {
		this.emails.add(email);
	}
}
