import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarriorContainer {
	
	// Atribut: un arrayList que conté els guerrers de la BBDD
	
    private ArrayList<Warrior> warriors;

    // Constructor: crea l'arrayList de guerrers
    
    public WarriorContainer() {
        this.warriors = new ArrayList<>();
    }
    
    // Getter: retorna l'arrayList de guerrers

    public ArrayList<Warrior> getArrayListWarrior() {
        return this.warriors;
    }
    
    // Setter: assigna el valor inicial a l'arrayList de guerrers

    public void setArrayListWarriors(ArrayList<Warrior> warriors) {
        this.warriors = warriors;
    }

    // Mètode: es conecta amb la BBDD, a la taula de guerrers i omple l'arrayList amb aquests
    
    public void carregarWarriorsFromDB() {
    	
    	String urlDatos = "jdbc:mysql://localhost/battlerace?serverTimezone=UTC";
		String usuario = "root";
		String pass = "1234";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(urlDatos, usuario, pass);
            stmt = conn.prepareStatement("SELECT * FROM warriors");
            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	int id = rs.getInt(1);
                String name = rs.getString(2);
                int puntsVida = rs.getInt(3);
                int força = rs.getInt(4);
                int velocitat = rs.getInt(5);
                int agilitat = rs.getInt(6);
                int defensa = rs.getInt(7);
                String urlImatge = rs.getString(8);
                String type = rs.getString(9);
                int raca = rs.getInt(10);
                
                switch (type) {
                
                    case "huma":
                    	Warrior huma = new Warrior(id, name, puntsVida, força, velocitat, agilitat, defensa, urlImatge, type, raca);
                        warriors.add(huma);
                        break;
                        
                    case "nan":
                    	Warrior nan = new Warrior(id, name, puntsVida, força, velocitat, agilitat, defensa, urlImatge, type, raca);
                        warriors.add(nan);
                        break;
                        
                    case "elf":
                    	Warrior elf = new Warrior(id, name, puntsVida, força, velocitat, agilitat, defensa, urlImatge, type, raca);
                        warriors.add(elf);
                        break;
                        
                    default:
                        System.out.println("Guerrer desconegut: " + name);
                        break;
                }
            }
          
            
        } catch (SQLException e) {
        	
            System.out.println("No s'ha pogut crear la connexió");
            
        } 
        
    }
}
