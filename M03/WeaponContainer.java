import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WeaponContainer {
	
	// Atribut: un arrayList que conté les armes de la BBDD
	
    private ArrayList<Weapon> weapons;

    // Constructor: crea l'arrayList d'armes
    
    public WeaponContainer() {
        this.weapons = new ArrayList<>();
    }
    
    // Getter: retorna l'arrayList d'armes

    public ArrayList<Weapon> getArrayListWeapons() {
        return this.weapons;
    }
    
    // Setter: assigna el valor inicial a l'arrayList d'armes

    public void setArrayListWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    // Mètode: es conecta amb la BBDD, a la taula d'armes i omple l'arrayList amb aquestes
    
    public void carregarWeaponsFromDB() {
    	
    	String urlDatos = "jdbc:mysql://localhost/battlerace?serverTimezone=UTC";
		String usuario = "root";
		String pass = "1234";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(urlDatos, usuario, pass);
            stmt = conn.prepareStatement("SELECT * FROM weapons");
            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	int id = rs.getInt(1);
                String name = rs.getString(2);
                String urlImatge = rs.getString(3);
                int plusVelocitat = rs.getInt(4);
                int plusForça = rs.getInt(5);
                
                switch (name) {
                
                    case "Daga":
                    	Weapon daga = new Weapon(id, name, urlImatge, plusVelocitat, plusForça);
                        weapons.add(daga);
                        break;
                        
                    case "Espasa":
                    	Weapon espasa = new Weapon(id, name, urlImatge, plusVelocitat, plusForça);
                        weapons.add(espasa);
                        break;
                        
                    case "Destral":
                    	Weapon destral = new Weapon(id, name, urlImatge, plusVelocitat, plusForça);
                        weapons.add(destral);
                        break;
                        
                    case "Espases dobles":
                    	Weapon espasesDobles = new Weapon(id, name, urlImatge, plusVelocitat, plusForça);
                        weapons.add(espasesDobles);
                        break;   
                       
                    case "Simitarra":
                    	Weapon simitarra = new Weapon(id, name, urlImatge, plusVelocitat, plusForça);
                        weapons.add(simitarra);
                        break;
                        
                    case "Arc":
                    	Weapon arc = new Weapon(id, name, urlImatge, plusVelocitat, plusForça);
                        weapons.add(arc);
                        break;
                        
                    case "Katana":
                    	Weapon katana = new Weapon(id, name, urlImatge, plusVelocitat, plusForça);
                        weapons.add(katana);
                        break;
                        
                    case "Punyal":
                    	Weapon punyal = new Weapon(id, name, urlImatge, plusVelocitat, plusForça);
                        weapons.add(punyal);
                        break;
                        
                    case "Destral de dues mans":
                    	Weapon destralDeDuesMans = new Weapon(id, name, urlImatge, plusVelocitat, plusForça);
                        weapons.add(destralDeDuesMans);
                        break;
                        
                    default:
                        System.out.println("Arma desconeguda: " + name);
                        break;
                }
            }
            
            
        } catch (SQLException e) {
        	
        	System.out.println("No s'ha pogut crear la connexió");
            
        } 
    }
}