package proyecto2;

import java.util.ArrayList;


public class Batalla {
	
	private WarriorContainer warriorContainer;
	private WeaponContainer weaponContainer;
	private ArrayList<Warrior> warriors;
	private InterficieGrafica finestra;
	private VentanaPrincipal ventanaPrincipal;
	private Warrior warrior2;
	private boolean esInici;
	
	// We load the warriors and weapons that are in the bdd and insert them into the arrayList
	
	public void CarregarWarriorsIWepaons() {
	
		warriorContainer = new WarriorContainer();
		warriorContainer.carregarWarriorsFromDB();
		weaponContainer = new WeaponContainer();
		weaponContainer.carregarWeaponsFromDB();		
		warriors = warriorContainer.getArrayListWarrior();

	}
	
	// We create the graphical interface and select warriors and weapons
	
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
		
	//We create the battle object
		
	Batalla batalla = new Batalla();
		
	// We load the two arrayList with data
	
	batalla.CarregarWarriorsIWepaons();
	
	// We create the GUI and show it
	
	batalla.creemInterficieGrafica();
	
	}

}