import java.util.ArrayList;

public class Batalla {
	
	private WarriorContainer warriorContainer;
	private WeaponContainer weaponContainer;
	private ArrayList<Warrior> warriors;
	private ArrayList<Weapon> weapons;
	private InterficieGrafica finestra;
	private VentanaPrincipal ventanaPrincipal;
	private Warrior warrior1;
	private Warrior warrior2;
	
	// Carreguem els guerrers i armes que hi han a la bdd i els insertem a l'arrayList
	
	public void CarregarWarriorsIWepaons() {
		
		warriorContainer = new WarriorContainer();
		warriorContainer.carregarWarriorsFromDB();
		
		weaponContainer = new WeaponContainer();
		weaponContainer.carregarWeaponsFromDB();
		
		// Mostrem als guerrers i les armes que tenim a la BBDD

		for (Warrior e: warriorContainer.getArrayListWarrior()) {
			
			System.out.println(e);
		}
		
		System.out.println("");
		
		for (Weapon e: weaponContainer.getArrayListWeapons()) {
			
			System.out.println(e);
		}
		
		warriors = warriorContainer.getArrayListWarrior();
		weapons = weaponContainer.getArrayListWeapons();
	}
	
	// Creem la interficie gràfica i seleccionem guerrers i armes
	
	public void creemInterficieGrafica() {

		finestra = new InterficieGrafica();
		ventanaPrincipal = new VentanaPrincipal();
		
		warrior1 = VentanaPrincipal.seleccionarRandomWarrior(warriors);
		warrior2 = VentanaPrincipal.seleccionarRandomWarrior(warriors);
		
		System.out.println(warrior1.getNom());
		System.out.println(warrior2.getNom());
		
		ventanaPrincipal.inicialitzarImatges(warrior1.getUrlImatge(), warrior2.getUrlImatge());
		ventanaPrincipal.obtenirWarrior(warrior1, warrior2);
		
		ventanaPrincipal.setVisible(true);
		
	}

	public static void main(String[] args) {
		
	// Creem l'objecte batalla
		
	Batalla batalla = new Batalla();
		
	// Carreguem de dades les dues arrayList
	
	batalla.CarregarWarriorsIWepaons();
	
	// Creem la interficie gràfica i la mostrem
	
	batalla.creemInterficieGrafica();
	
	}

}
