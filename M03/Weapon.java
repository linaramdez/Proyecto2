
public class Weapon {
	
	// Atributs

	private int id;
	private String name;
	private String urlImatge;
	private int plusVelocitat;
	private int plusForça;
	
	// Constructor
	
	public Weapon(int id, String name, String urlImatge, int plusVelocitat, int plusForça) {
		super();
		this.id = id;
		this.name = name;
		this.urlImatge = urlImatge;
		this.plusVelocitat = plusVelocitat;
		this.plusForça = plusForça;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the plusVelocitat
	 */
	public int getPlusVelocitat() {
		return plusVelocitat;
	}

	/**
	 * @param plusVelocitat the plusVelocitat to set
	 */
	public void setPlusVelocitat(int plusVelocitat) {
		this.plusVelocitat = plusVelocitat;
	}

	/**
	 * @return the plusForça
	 */
	public int getPlusForça() {
		return plusForça;
	}

	/**
	 * @param plusForça the plusForça to set
	 */
	public void setPlusForça(int plusForça) {
		this.plusForça = plusForça;
	}
	
	@Override
	public String toString() {
		return "Weapon [id=" + id + ", name=" + name + ", urlImatge=" + urlImatge + ", plusVelocitat=" + plusVelocitat
				+ ", plusForça=" + plusForça + "]";
	}
	
}
