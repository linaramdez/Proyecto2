
public class Warrior {

	// Atributs
	
	private int id;
	private String nom;
	private int puntsVida;
	private int força;
	private int velocitat;
	private int agilitat;
	private int defensa;
	private String urlImatge;
	private String type;
	private Weapon arma;
	private int raca;
	
	// Constructor
	
	public Warrior(int id, String nom, int puntsVida, int força, int velocitat, int agilitat, int defensa,
			String urlImatge, String type) {
		super();
		this.id = id;
		this.nom = nom;
		this.puntsVida = puntsVida;
		this.força = força;
		this.velocitat = velocitat;
		this.agilitat = agilitat;
		this.defensa = defensa;
		this.urlImatge = urlImatge;
		this.type = type;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the puntsVida
	 */
	public int getPuntsVida() {
		return puntsVida;
	}

	/**
	 * @param puntsVida the puntsVida to set
	 */
	public void setPuntsVida(int puntsVida) {
		this.puntsVida = puntsVida;
	}

	/**
	 * @return the força
	 */
	public int getForça() {
		return força;
	}

	/**
	 * @param força the força to set
	 */
	public void setForça(int força) {
		this.força = força;
	}

	/**
	 * @return the velocitat
	 */
	public int getVelocitat() {
		return velocitat;
	}

	/**
	 * @param velocitat the velocitat to set
	 */
	public void setVelocitat(int velocitat) {
		this.velocitat = velocitat;
	}

	/**
	 * @return the agilitat
	 */
	public int getAgilitat() {
		return agilitat;
	}

	/**
	 * @param agilitat the agilitat to set
	 */
	public void setAgilitat(int agilitat) {
		this.agilitat = agilitat;
	}

	/**
	 * @return the defensa
	 */
	public int getDefensa() {
		return defensa;
	}

	/**
	 * @param defensa the defensa to set
	 */
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	/**
	 * @return the urlImatge
	 */
	public String getUrlImatge() {
		return urlImatge;
	}

	/**
	 * @param urlImatge the urlImatge to set
	 */
	public void setUrlImatge(String urlImatge) {
		this.urlImatge = urlImatge;
	}

	/**
	 * @return the arma
	 */
	public Weapon getArma() {
		return this.arma;
	}

	/**
	 * @param arma the arma to set
	 */
	public void setArma(Weapon arma) {
		this.arma = arma;
	}

	/**
	 * @return the arma
	 */
	
	public String getType() {
		return type;
	}
	
	/**
	 * @param arma the arma to set
	 */

	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the raca
	 */
	public int getRaca() {
		return raca;
	}

	/**
	 * @param raca the raca to set
	 */
	public void setRaca(int raca) {
		this.raca = raca;
	}

	@Override
	public String toString() {
		return "Warrior [id=" + id + ", nom=" + nom + ", puntsVida=" + puntsVida + ", força=" + força + ", velocitat="
				+ velocitat + ", agilitat=" + agilitat + ", defensa=" + defensa + ", urlImatge=" + urlImatge + ", type="
				+ type + ", arma=" + arma + ", raça=" + raca + "]";
	}
	
	
}
