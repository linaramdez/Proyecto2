import java.util.ArrayList;

public class Batalla {
	
	private WarriorContainer warriorContainer;
	private WeaponContainer weaponContainer;
	private ArrayList<Warrior> warriors;
	private InterficieGrafica finestra;
	private VentanaPrincipal ventanaPrincipal;
	private Warrior warrior2;
	private boolean esInici;
	
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

	}
	
	// Creem la interficie gràfica i seleccionem guerrers i armes
	
	public void creemInterficieGrafica() {

		finestra = new InterficieGrafica();
		ventanaPrincipal = new VentanaPrincipal();
		
		esInici = true;

		warrior2 = VentanaPrincipal.seleccionarRandomWarrior(warriors);
		ventanaPrincipal.inicialitzarImatges("imagenes/anonim.jpg", warrior2.getUrlImatge(), esInici);
		ventanaPrincipal.setVisible(true);
		ventanaPrincipal.identificacioUsuari();
		
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
