import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class InterficieGrafica {

	public static void main(String[] args) {
		
		new VentanaPrincipal();
	}
}

// Creem la classe VentanaPrincipal on es trobarà la inferficie gràfica on es produeix la lluita

class VentanaPrincipal extends JFrame{
	
	private JPanel panel1, panel2, panel21, panel22, panel23, panel24, panel25, panel26, panel3, panel31, panel4, panelHumans, panelElfs, panelNans;
	private JButton boto1, boto2, boto3, boto4, boto5;
	private JTextArea consola;
	private JScrollPane scroll;
	private JProgressBar progressBar1, progressBar11,  progressBar12,  progressBar13,  progressBar14, progressBar2, progressBar21, 
	progressBar22, progressBar23, progressBar24;
	private ImageIcon imatgeWarrior1, imatgeWarrior2, imatgeWeapon1, imatgeWeapon2, imagenOriginal, imagenFinal;
	private JLabel power1, power2, agility1, agility2, speed1, speed2, defense1, defense2, imatge1, imatge2, imatge3, imatge4, humans, elfs, nans, interaccio;
	private JFrame ventana2, ventana3, ventana4;
	private Image imagen, imagenRedimensionada;
	private ArrayList<String> textArmes;
	private ArrayList<JLabel> labelArmes;
    private WarriorContainer warriorContainer;
    private WeaponContainer weaponContainer;
    private int warriorSeleccionat;
    private Warrior warrioreal;
    private Warrior warriorbot;
    private boolean aTriatWarrior = false;
    private boolean aTriatWeapon = false;
    private boolean esInici = false;
    private int playerId = -1;
    private int puntuacio = 0;
	private int danyCausat = 0;
	private int danyRebut = 0;
	private int batallesGuanyades = 0;
    private String nomUsuari = "";
	private String urlDatos = "jdbc:mysql://localhost/battlerace?serverTimezone=UTC";
	private String usuario = "root";
	private String pass = "1234";
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private Statement statement = null;
	
	VentanaPrincipal() {
		
		// Creem els objectes panel
		
		panel1 = new JPanel();
		panel2 = new JPanel(new GridLayout(1, 2, 30, 0));
		panel21 = new JPanel(new BorderLayout());
		panel22 = new JPanel(new BorderLayout());
		panel23 = new JPanel(new GridLayout(4, 2));
		panel24 = new JPanel(new GridLayout(4, 2));
		panel25 = new JPanel();
		panel26 = new JPanel();
		panel3 = new JPanel();
		panel31 = new JPanel(new BorderLayout());
		panel4 = new JPanel(new GridLayout(2, 1));
		
		// Creem l'objecte consola
		
		consola = new JTextArea(5, 10);
		consola.setEditable(false);
		
		// Creem l'objecte scroll
		
		scroll = new JScrollPane(consola);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		// Creem l'objecte warriorContainer i weaponContainer
		
		warriorContainer = new WarriorContainer();
		warriorContainer.carregarWarriorsFromDB();
		weaponContainer = new WeaponContainer();
		weaponContainer.carregarWeaponsFromDB();
		
		// Creem els objectes botons
		
		boto1 = new JButton("Choose Character");
		
		// Es farà això quan es dona clic al botó de triar warrior
		
		boto1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ventana2 = new JFrame("Selecció de guerrer");
				ventana2.setLayout(new GridLayout(3, 5));
				humans = new JLabel("Humans:");
				humans.setHorizontalAlignment(JLabel.CENTER);
		        humans.setVerticalAlignment(JLabel.CENTER);
				elfs = new JLabel("Elfs:");
				elfs.setHorizontalAlignment(JLabel.CENTER);
		        elfs.setVerticalAlignment(JLabel.CENTER);
				nans = new JLabel("Nans:");
				nans.setHorizontalAlignment(JLabel.CENTER);
		        nans.setVerticalAlignment(JLabel.CENTER);
				
				ventana2.add(humans);
				
				for (int i = 1; i <= 4; i++) {
		            String imagenPath = "imagenes/wa" + i + ".jpg";
		            ImageIcon imagenOriginal = new ImageIcon(imagenPath);
		            imagen = imagenOriginal.getImage();
		            int anchoNuevo = 100;
		            int altoNuevo = 150;
		            imagenRedimensionada = imagen.getScaledInstance(anchoNuevo, altoNuevo, Image.SCALE_SMOOTH);
		            imagenFinal = new ImageIcon(imagenRedimensionada);
		            JLabel label = new JLabel(imagenFinal);
		            label.addMouseListener(new MouseAdapter() {
		                @Override
		                public void mouseClicked(MouseEvent e) {
		                	aTriatWarrior = true;
		                    String nombreImagen = imagenPath;
		                    actualitzarImatgeWarriorTriat(nombreImagen);
		                    actualitzarValorsWarriorTriat(nombreImagen);
		                    
		                }
		            });
		            ventana2.add(label);
		        }
				
				ventana2.add(nans);
				
				for (int i = 5; i <= 8; i++) {
		            String imagenPath = "imagenes/wa" + i + ".jpg";
		            ImageIcon imagenOriginal = new ImageIcon(imagenPath);
		            imagen = imagenOriginal.getImage();
		            int anchoNuevo = 100;
		            int altoNuevo = 150;
		            imagenRedimensionada = imagen.getScaledInstance(anchoNuevo, altoNuevo, Image.SCALE_SMOOTH);
		            imagenFinal = new ImageIcon(imagenRedimensionada);
		            JLabel label = new JLabel(imagenFinal);
		            label.addMouseListener(new MouseAdapter() {
		                @Override
		                public void mouseClicked(MouseEvent e) {
		                	aTriatWarrior = true;
		                    String nombreImagen = imagenPath;
		                    consola.setText("Se hizo clic en la imagen: " + nombreImagen);
		                    actualitzarImatgeWarriorTriat(nombreImagen);
		                    actualitzarValorsWarriorTriat(nombreImagen);
		                    
		                }
		            });
		            ventana2.add(label);
		        }
				
				ventana2.add(elfs);
				
				for (int i = 9; i <= 12; i++) {
		            String imagenPath = "imagenes/wa" + i + ".jpg";
		            ImageIcon imagenOriginal = new ImageIcon(imagenPath);
		            imagen = imagenOriginal.getImage();
		            int anchoNuevo = 100;
		            int altoNuevo = 150;
		            imagenRedimensionada = imagen.getScaledInstance(anchoNuevo, altoNuevo, Image.SCALE_SMOOTH);
		            imagenFinal = new ImageIcon(imagenRedimensionada);
		            JLabel label = new JLabel(imagenFinal);
		            label.addMouseListener(new MouseAdapter() {
		                @Override
		                public void mouseClicked(MouseEvent e) {
		                	aTriatWarrior = true;
		                    String nombreImagen = imagenPath;
		                    consola.setText("Se hizo clic en la imagen guerrer: " + nombreImagen);
		                    actualitzarImatgeWarriorTriat(nombreImagen);
		                    actualitzarValorsWarriorTriat(nombreImagen);
		                }
		            });
		            ventana2.add(label);
		        }

				ventana2.setVisible(true);
				ventana2.setSize(800, 650);	
				ventana2.setResizable(false);
				Toolkit pantalla = Toolkit.getDefaultToolkit(); 
				Dimension grandaria = pantalla.getScreenSize(); 
				int ancho = grandaria.width; 
				int alto = grandaria.height;
				ventana2.setLocation((ancho/2) - (ventana2.getWidth()/2), (alto/2) - (ventana2.getHeight()/2));
				
			}
			
		});
		
		boto2 = new JButton("Choose Weapon");
		
		// Es farà això quan es dona clic al botó de triar weapon
		
		boto2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					if (aTriatWarrior) { 
						ventana3 = new JFrame("Selecció d'armes");

						ventana3.setLayout(new GridLayout(3, 6));
						
						textArmes = new ArrayList<String>();
						labelArmes = new ArrayList<JLabel>();
						
						textArmes.add("Daga");
				        textArmes.add("Espasa");
				        textArmes.add("Destral");
				        textArmes.add("Espases dobles");
				        textArmes.add("Simitarra");
				        textArmes.add("Arc");
				        textArmes.add("Katana");
				        textArmes.add("Punyal");
				        textArmes.add("Destral de dues mans");
				        
				        for (String text : textArmes) {
				            JLabel labelAr = new JLabel(text);
				            labelAr.setHorizontalAlignment(JLabel.CENTER);
				            labelAr.setVerticalAlignment(JLabel.CENTER);
				            labelArmes.add(labelAr);
				        }
						
						for (int i = 1; i <= 9; i++) {
				            String imagenPath = "imagenes/we" + i + ".jpg";
				            ImageIcon imagenOriginal = new ImageIcon(imagenPath);
				            imagen = imagenOriginal.getImage();
				            int anchoNuevo = 80;
				            int altoNuevo = 100;
				            imagenRedimensionada = imagen.getScaledInstance(anchoNuevo, altoNuevo, Image.SCALE_SMOOTH);
				            imagenFinal = new ImageIcon(imagenRedimensionada);
				            JLabel label = new JLabel(imagenFinal);
				            label.addMouseListener(new MouseAdapter() {
				                @Override
				                public void mouseClicked(MouseEvent e) {
				                    String nombreImagen = imagenPath;
				                    consola.setText("Se hizo clic en la imagen arma: " + nombreImagen);
				                    armesDisponibles(nombreImagen);
				                }
				            });
				            ventana3.add(labelArmes.get(i - 1));
				            ventana3.add(label);
				        }
						
						ventana3.setSize(800, 650);
						ventana3.setVisible(true);
						
					} else {
						
						consola.setText("Primer has de triar un guerrer abans de triar l'arma");	
					}	
				}
				
			});

		boto3 = new JButton("Ranking");
		
		boto3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ventana4 = new JFrame("Ranking");
				ventana4.setLayout(new GridLayout(9, 5));
				JLabel player, punts, won, inCaused, inSuffered;
				
				player = new JLabel("Player ID");
				punts = new JLabel("Puntuació global");
				won = new JLabel("Enemics derrotats");
				inCaused = new JLabel("Dany realitzat");
				inSuffered = new JLabel("Dany rebut");
				
				ventana4.add(player);
	            ventana4.add(punts);
	            ventana4.add(won);
	            ventana4.add(inCaused);
	            ventana4.add(inSuffered);
				
				try {
					
		            // Crear una declaración SQL
		            Statement stmt = conn.createStatement();

		            // Ejecutar la consulta
		            String query = "SELECT player_id, " +
		                    "SUM(battle_points) AS total_score, " +
		                    "COUNT(CASE WHEN battle_points > 0 THEN 1 ELSE NULL END) AS battles_won, " +
		                    "SUM(injuries_caused) AS total_damage_caused, " +
		                    "SUM(injuries_suffered) AS total_damage_taken " +
		                    "FROM battle " +
		                    "GROUP BY player_id " +
		                    "ORDER BY total_score DESC " +
		                    "LIMIT 8";

		            ResultSet rs = stmt.executeQuery(query);

		            // Recorrer los resultados y asignarlos a las variables
		            while (rs.next()) {
		            	
		                playerId = rs.getInt("player_id");
		                puntuacio = rs.getInt("total_score");
		                batallesGuanyades = rs.getInt("battles_won");
		                danyCausat = rs.getInt("total_damage_caused");
		                danyRebut = rs.getInt("total_damage_taken");
		                
		                // Imprimir los valores obtenidos como ejemplo
		                System.out.println("Player ID: " + playerId);
		                System.out.println("Total Score: " + puntuacio);
		                System.out.println("Battles Won: " + batallesGuanyades);
		                System.out.println("Total Damage Caused: " + danyCausat);
		                System.out.println("Total Damage Taken: " + danyRebut);
		                System.out.println("--------------------------");
		                
		                JLabel playerIdLabel = new JLabel(Integer.toString(playerId));
		                JLabel totalScoreLabel = new JLabel(Integer.toString(puntuacio));
		                JLabel battlesWonLabel = new JLabel(Integer.toString(batallesGuanyades));
		                JLabel totalDamageCausedLabel = new JLabel(Integer.toString(danyCausat));
		                JLabel totalDamageTakenLabel = new JLabel(Integer.toString(danyRebut));

		                ventana4.add(playerIdLabel);
		                ventana4.add(totalScoreLabel);
		                ventana4.add(battlesWonLabel);
		                ventana4.add(totalDamageCausedLabel);
		                ventana4.add(totalDamageTakenLabel);
		               
		            }
		            
		            
		            ventana4.setSize(800, 650);
					ventana4.setVisible(true);  
		            

		        } catch (SQLException x) {
		            x.printStackTrace();
		        }
				
			}
			
		});
		
		boto4 = new JButton("Fight");
		
		// Es farà això quan es dona clic al botó de lluitar
		
		boto4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (aTriatWarrior && aTriatWeapon) {
					Warrior luchadores[]= new Warrior[2];
					consola.append("COMENÇA LA LLUITA");
					luchadores[0]=warrioreal;
					luchadores[1]=warriorbot;
					iniciarLluita(luchadores, warrioreal, warriorbot);
				} else {
					consola.setText("Has de triar un guerrer i un arma disponible");
				}
			}
			
		});
		
		boto5 = new JButton("Clear Console");
		
		// Es farà això quan es dona clic al botó de netejar la consola
		
		boto5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				consola.setText("");
			}
			
		});
		
		// Creem els objectes ImageIcon
		
		imatgeWarrior1 = new ImageIcon();
		imatgeWeapon1 = new ImageIcon();
		imatgeWarrior2 = new ImageIcon();
		imatgeWeapon2 = new ImageIcon();
		
		// Creem els objectes progressBar
		
		progressBar1 = crearProgressBar(0, 100, 100, true, Color.GRAY);
		progressBar11 = crearProgressBar(0, 100, 50, false, Color.RED);
		progressBar12 = crearProgressBar(0, 100, 50, false, Color.PINK);
		progressBar13 = crearProgressBar(0, 100, 50, false, Color.YELLOW);
		progressBar14 = crearProgressBar(0, 100, 50, false, Color.BLUE);
		
		progressBar2 = crearProgressBar(0, 100, 100, true, Color.GRAY);
		progressBar21 = crearProgressBar(0, 100, 50, false, Color.RED);
		progressBar22 = crearProgressBar(0, 100, 50, false, Color.PINK);
		progressBar23 = crearProgressBar(0, 100, 50, false, Color.YELLOW);
		progressBar24 = crearProgressBar(0, 100, 50, false, Color.BLUE);

		// Creem els objectes stats
		
		power1 = new JLabel("Power");
		agility1 = new JLabel("Agility");
		speed1 = new JLabel("Speed");
		defense1 = new JLabel("Defense");
		
		power2 = new JLabel("Power");
		agility2 = new JLabel("Agility");
		speed2 = new JLabel("Speed");
		defense2 = new JLabel("Defense");
		
		// Afegim els elements als panels
		
		panel1.add(boto1);
		panel1.add(boto2);
		panel1.add(boto3);
		
		panel21.add(progressBar1, BorderLayout.NORTH);
		panel21.add(imatge1 = new JLabel(imatgeWarrior1), BorderLayout.CENTER);
		
		panel22.add(progressBar2, BorderLayout.NORTH);
		panel22.add(imatge2 = new JLabel(imatgeWarrior2), BorderLayout.CENTER);
		
		panel23.add(power1);
		panel23.add(progressBar11);
		panel23.add(agility1);
		panel23.add(progressBar12);
		panel23.add(speed1);
		panel23.add(progressBar13);
		panel23.add(defense1);
		panel23.add(progressBar14);
		
		panel25.add(imatge3 = new JLabel(imatgeWeapon1));
		panel25.add(panel23);
		
		panel21.add(panel25, BorderLayout.SOUTH);
		
		panel24.add(power2);
		panel24.add(progressBar21);
		panel24.add(agility2);
		panel24.add(progressBar22);
		panel24.add(speed2);
		panel24.add(progressBar23);
		panel24.add(defense2);
		panel24.add(progressBar24);
		
		panel26.add(imatge4 = new JLabel(imatgeWeapon2));
		panel26.add(panel24);
		
		panel22.add(panel26, BorderLayout.SOUTH);
		
		panel3.add(boto4);
		panel3.add(boto5);
		panel31.add(panel3, BorderLayout.SOUTH);
		
		panel4.add(panel31);
		panel4.add(scroll);
		
		panel2.add(panel21);
		panel2.add(panel22);
		
		// Afegim els panels a la finestra
		
		this.add(panel1, BorderLayout.NORTH);
		this.add(panel2, BorderLayout.CENTER);
		this.add(panel4, BorderLayout.SOUTH);
		
		// Especifiquem les característiques de la finestra 
		
		this.setTitle("Batalla de races");
		this.setSize(800,650);
		this.setResizable(false);
		Toolkit pantalla = Toolkit.getDefaultToolkit(); 
		Dimension grandaria = pantalla.getScreenSize(); 
		int ancho = grandaria.width; 
		int alto = grandaria.height;
		this.setLocation((ancho/2) - (this.getWidth()/2), (alto/2) - (this.getHeight()/2)); 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	

	}
	
	// Aquest mètode identifica a l'usuari amb un player_id únic i un nom que introdueix l'usuari
	
	public void identificacioUsuari() {
		
	    String nomUsuari = null;

	    while (nomUsuari == null || nomUsuari.isBlank()) {
	        nomUsuari = JOptionPane.showInputDialog(null, "Introdueix el teu nom d'usuari:");

	        // Comprobar si se ingresó un nombre de usuario válido
	        if (nomUsuari != null && !nomUsuari.isBlank()) {
	            // El nombre de usuario se asigna a una variable dentro del programa
	            insertPlayer(nomUsuari);
	            consola.setText("Benvingut/da " + nomUsuari + "!");
	        } else {
	            // El usuario canceló o no ingresó un nombre de usuario válido
	            consola.setText("No s'ha introduit un nom d'usuari vàlid.");
	        }
	    }
	}
	
	
	// Aquest mètode crea els progressBar
	
	static JProgressBar crearProgressBar(int minimum, int maximum, int value, boolean stringPainted, Color foregroundColor) {
		
	    JProgressBar progressBar = new JProgressBar();
	    progressBar.setMinimum(minimum);
	    progressBar.setMaximum(maximum);
	    progressBar.setValue(value);
	    progressBar.setStringPainted(stringPainted);
	    progressBar.setForeground(foregroundColor);
	    
	    return progressBar;
	}
	
	// Aquest mètode selecciona un warriorbot aleatori
	
	
	static Warrior seleccionarRandomWarrior(ArrayList<Warrior> warriors) {
		
        Random random = new Random();
        int index = random.nextInt(warriors.size()); 
        Warrior randomWarrior = warriors.get(index);
        
        return randomWarrior;   
    }
	
	// Aquest mètode inicialitza les imatges dels dos warriors
	
	public void inicialitzarImatges(String url1, String url2, boolean esInici) {

		imatgeWarrior1 = new ImageIcon(url1);
		imatge1.setIcon(imatgeWarrior1);
		
		if (esInici) {
			imatgeWeapon1 = new ImageIcon("imagenes/x.jpg");
			imatge3.setIcon(imatgeWeapon1);
		}
		
		imatgeWarrior2 = new ImageIcon(url2);
		imatge2.setIcon(imatgeWarrior2);
		
		for (Warrior e: warriorContainer.getArrayListWarrior()) {
			
			if (e.getUrlImatge().equals(url2)) {
				
				inicialitzarArmaWarrior(e);
			}
		}
	}
	
	// Aquest mètode inicialitza l'arma del warriorbot
	
	public void inicialitzarArmaWarrior(Warrior warrior2) {
        
        try {
            conn = DriverManager.getConnection(urlDatos, usuario, pass);
            String query = "SELECT weapon_id FROM weapons_available WHERE warrior_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, warrior2.getId());
            rs = stmt.executeQuery();
            
            ArrayList<Integer> armesDisponiblesWarrior2 = new ArrayList<>();
            while (rs.next()) {
                int weaponId = rs.getInt("weapon_id");
                armesDisponiblesWarrior2.add(weaponId);
            }
            
            Random aleatorio = new Random();
            int arma_id = armesDisponiblesWarrior2.get(aleatorio.nextInt(armesDisponiblesWarrior2.size()));
            System.out.println(arma_id);
            
            for (Weapon e: weaponContainer.getArrayListWeapons()) {
    			
    			if (e.getId() == arma_id) {
    				warrior2.setArma(e);
    				String url2 = e.getUrlImatge();
    				imatgeWeapon2 = new ImageIcon(url2);
					imatge4.setIcon(imatgeWeapon2);
					inicialitzarValorsWarrior2(warrior2, e);
    			}
    		}
            
       } catch (SQLException e) {
        	System.out.println("No s'ha pogut crear la connexió");
       }
		
	}
	
	// Aquest mètode inicialitza els valors del warriorbot
	
	public void inicialitzarValorsWarrior2(Warrior warrior2, Weapon arma) {
		
		progressBar21.setValue(warrior2.getForça() * 10 + arma.getPlusForça() * 10 );
		progressBar22.setValue(warrior2.getAgilitat() * 10);
		progressBar23.setValue(warrior2.getVelocitat() * 10 +  arma.getPlusVelocitat() * 10);
		progressBar24.setValue(warrior2.getDefensa() * 10);
		
		warriorbot=warrior2;
		
	}
	
	// Aquest mètode actualitza la imatge del warrioreal
	
	public void actualitzarImatgeWarriorTriat(String url) {
		
		imatgeWarrior1 = new ImageIcon(url);
		imatge1.setIcon(imatgeWarrior1);

	}
	
	// Aquest mètode actualitza els valors del warrioreal
	
	public void actualitzarValorsWarriorTriat(String url) {

		for (Warrior e: warriorContainer.getArrayListWarrior()) {
			
			if (e.getUrlImatge().equals(url)) {
				
				progressBar11.setValue(e.getForça() * 10);
				progressBar12.setValue(e.getAgilitat() * 10);
				progressBar13.setValue(e.getVelocitat() * 10);
				progressBar14.setValue(e.getDefensa() * 10);
				
				warriorSeleccionat = e.getId();
			}
		}
	}
	
	// Aquest mètode actualitza els valors del warrioreal quan ja té la seva arma
	
	public void actualitzarValorsWarriorTriat2(Integer warrior_id, Weapon arma) {;
		
		for (Warrior e: warriorContainer.getArrayListWarrior()) {
			
			if (e.getId() == warrior_id) {
				
				warrioreal= e;
				warrioreal.setArma(arma);
				
				progressBar11.setValue(e.getForça() * 10 + arma.getPlusForça() * 10);
				progressBar12.setValue(e.getAgilitat() * 10);
				progressBar13.setValue(e.getVelocitat() * 10 + arma.getPlusVelocitat() * 10);
				progressBar14.setValue(e.getDefensa() * 10);
			
			}
		}
	}
	
	// Aquest mètode ompla un array amb les armes que té disponible el warrior i verifica si la que ha triat està disponible o no
	
	public void armesDisponibles(String url) {

        try {
            conn = DriverManager.getConnection(urlDatos, usuario, pass);
            String query = "SELECT weapon_id FROM weapons_available WHERE warrior_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, warriorSeleccionat);
            rs = stmt.executeQuery();
            
            ArrayList<Integer> armesDisponiblesWarrior1 = new ArrayList<>();
            while (rs.next()) {
                int weaponId = rs.getInt("weapon_id");
                armesDisponiblesWarrior1.add(weaponId);
            }
            
            for (Weapon e: weaponContainer.getArrayListWeapons()) {
    			
    			if (e.getUrlImatge().equals(url)) {
    				
    				if (armesDisponiblesWarrior1.contains(e.getId())) {
    					aTriatWeapon = true;
    					imatgeWeapon1 = new ImageIcon(url);
    					imatge3.setIcon(imatgeWeapon1);
    					actualitzarValorsWarriorTriat2(warriorSeleccionat, e);
    				} else {
    					consola.setText("Aquesta arma no está disponible per aquest guerrer. Tria un altre.");
    				}
    			}
    		}
            
        } catch (SQLException e) {
        	System.out.println("No s'ha pogut crear la connexió");
        }
       
	}
	
	// Aquest mètode inicia la lluita
	
	public void iniciarLluita(Warrior[] luchadores, Warrior warriorreal, Warrior warriorbot) {
	    SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
	        @Override
	        protected Void doInBackground() throws Exception {
	            lluita(luchadores, warriorreal, warriorbot);
	            return null;
	        }

	        @Override
	        protected void process(java.util.List<String> chunks) {
	            SwingUtilities.invokeLater(() -> {
	                for (Object chunk : chunks) {
	                    consola.append(chunk.toString() + "\n");
	                }
	            });
	        }

	        @Override
	        protected void done() {
	            // La batalla ha finalizado
	            consola.append("Batalla finalitzada");
	        }
	    };

	    worker.execute();
	}

	// Aquest mètode executa la mecànica de la lluita
	
	public void lluita(Warrior[] luchadores, Warrior warrioreal, Warrior warriorbot) {
		
		int vidaInicialWarriorReal = warrioreal.getPuntsVida();
		int vidaInicialWarriorBot = warriorbot.getPuntsVida();

		
		if (luchadores[0].getVelocitat()>luchadores[1].getVelocitat()) {
			while(luchadores[0].getPuntsVida()>0 && luchadores[1].getPuntsVida()>0) {
				consola.append("\n" + "Torn de "+luchadores[0].getNom());
				int exit= (int) (Math.random() * 99) + 1;
				if(luchadores[0].getAgilitat()*10>exit) {
					consola.append("L'atac ha tingut exit!!");
					int esquivar= (int) (Math.random() * 49) + 1;
					if(luchadores[0].getAgilitat()>esquivar) {
						consola.append("\n" + "El defensor ha esquivat l'atac!!");
					}else {
						int dany = (luchadores[0].getForça()+luchadores[0].getArma().getPlusForça())-luchadores[1].getDefensa();
						consola.append("\n" + "El defensor ha rebut "+dany+" punts de dany!!");
						luchadores[1].setPuntsVida(luchadores[1].getPuntsVida()-dany);
					}
				}else {
					consola.append("\n" + "L'atac no s'ha pogut efectuar, no ha tingut exit!!");}
				if(luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat() <= luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()) {
					Collections.swap(Arrays.asList(luchadores),0,1);
				}else {
					int num= (int) (Math.random() * 99) + 1;
					if(((luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat())-(luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()))*10>num){
						consola.append("\n" + luchadores[0].getNom()+" torna a atacar!!");
					}else {
						Collections.swap(Arrays.asList(luchadores),0,1);
					}
				}delay(1000);
			}
		}
		else if(luchadores[0].getVelocitat()<luchadores[1].getVelocitat()) {
			Collections.swap(Arrays.asList(luchadores),0,1);
			while(luchadores[0].getPuntsVida()>0 && luchadores[1].getPuntsVida()>0) {
				consola.append("Torn de "+luchadores[0].getNom());
				int exit= (int) (Math.random() * 99) + 1;
				if(luchadores[0].getAgilitat()*10>exit) {
					consola.append("\n" + "L'atac ha tingut exit!!");
					int esquivar= (int) (Math.random() * 49) + 1;
					if(luchadores[0].getAgilitat()>esquivar) {
						consola.append("\n" + "El defensor ha esquivat l'atac!!");
					}else {
						int dany = (luchadores[0].getForça()+luchadores[0].getArma().getPlusForça())-luchadores[1].getDefensa();
						consola.append("\n" + "El defensor ha rebut "+dany+" punts de dany!!");
						luchadores[1].setPuntsVida(luchadores[1].getPuntsVida()-dany);
					}
				}else {consola.append("\n" + "L'atac no s'ha pogut efectuar, no ha tingut exit!!");}
				if(luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat() <= luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()) {
					Collections.swap(Arrays.asList(luchadores),0,1);
				}else {
					int num= (int) (Math.random() * 99) + 1;
					if(((luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat())-(luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()))*10>num){
						consola.append("\n" + luchadores[0].getNom()+" torna a atacar!!");
					}else {
						Collections.swap(Arrays.asList(luchadores),0,1);
					}
				}delay(1000);
			}
		}
		else {
			if(luchadores[0].getAgilitat()>luchadores[1].getAgilitat()) {
				while(luchadores[0].getPuntsVida()>0 && luchadores[1].getPuntsVida()>0) {
					consola.append("\n" + "Torn de "+luchadores[0].getNom());
					int exit= (int) (Math.random() * 99) + 1;
					if(luchadores[0].getAgilitat()*10>exit) {
						consola.append("L'atac ha tingut exit!!");
						int esquivar= (int) (Math.random() * 49) + 1;
						if(luchadores[0].getAgilitat()>esquivar) {
							consola.append("\n" + "El defensor ha esquivat l'atac!!");
						}else {
							int dany = (luchadores[0].getForça()+luchadores[0].getArma().getPlusForça())-luchadores[1].getDefensa();
							consola.append("\n" + "El defensor ha rebut "+dany+" punts de dany!!");
							luchadores[1].setPuntsVida(luchadores[1].getPuntsVida()-dany);
							
							
						}
					}else {consola.append("\n" + "L'atac no s'ha pogut efectuar, no ha tingut exit!!");}
					if(luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat() <= luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()) {
						Collections.swap(Arrays.asList(luchadores),0,1);
					}else {
						int num= (int) (Math.random() * 99) + 1;
						if(((luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat())-(luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()))*10>num){
							consola.append("\n" + luchadores[0].getNom()+" torna a atacar!!");
						}else {
							Collections.swap(Arrays.asList(luchadores),0,1);
						}
					}delay(1000);
				}
			}
			else if(luchadores[0].getAgilitat()<luchadores[1].getAgilitat()) {
				Collections.swap(Arrays.asList(luchadores),0,1);
				while(luchadores[0].getPuntsVida()>0 && luchadores[1].getPuntsVida()>0) {
					consola.append("\n" + "Torn de "+luchadores[0].getNom());
					int exit= (int) (Math.random() * 99) + 1;
					if(luchadores[0].getAgilitat()*10>exit) {
						consola.append("\n" + "L'atac ha tingut exit!!");
						int esquivar= (int) (Math.random() * 49) + 1;
						if(luchadores[0].getAgilitat()>esquivar) {
							consola.append("\n" + "El defensor ha esquivat l'atac!!");
						}else {
							int dany = (luchadores[0].getForça()+luchadores[0].getArma().getPlusForça())-luchadores[1].getDefensa();
							consola.append("\n" + "El defensor ha rebut "+dany+" punts de dany!!");
							luchadores[1].setPuntsVida(luchadores[1].getPuntsVida()-dany);
						}
					}else {consola.append("\n" + "L'atac no s'ha pogut efectuar, no ha tingut exit!!");}
					if(luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat() <= luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()) {
						Collections.swap(Arrays.asList(luchadores),0,1);
					}else {
						int num= (int) (Math.random() * 99) + 1;
						if(((luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat())-(luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()))*10>num){
							consola.append("\n" + luchadores[0].getNom()+" torna a atacar!!");
						}else {
							Collections.swap(Arrays.asList(luchadores),0,1);
						}
					}delay(1000);
				}
				
			}
			else {
				int atac= (int)Math.round(Math.random());
				if(atac!=0) {
				Collections.swap(Arrays.asList(luchadores),0,1);
				}
				while(luchadores[0].getPuntsVida()>0 && luchadores[1].getPuntsVida()>0) {
					consola.append("\n" + "Torn de "+luchadores[0].getNom());
					int exit= (int) (Math.random() * 99) + 1;
					if(luchadores[0].getAgilitat()*10>exit) {
						consola.append("\n" + "L'atac ha tingut exit!!");
						int esquivar= (int) (Math.random() * 49) + 1;
						if(luchadores[0].getAgilitat()>esquivar) {
							consola.append("\n" + "El defensor ha esquivat l'atac!!");
						}else {
							int dany = (luchadores[0].getForça()+luchadores[0].getArma().getPlusForça())-luchadores[1].getDefensa();
							consola.append("\n" + "El defensor ha rebut "+dany+" punts de dany!!");
							luchadores[1].setPuntsVida(luchadores[1].getPuntsVida()-dany);	
						}
					}else {consola.append("\n" + "L'atac no s'ha pogut efectuar, no ha tingut exit!!");}
					if(luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat() <= luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()) {
						Collections.swap(Arrays.asList(luchadores),0,1);
					}else {
						int num= (int) (Math.random() * 99) + 1;
						if(((luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat())-(luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()))*10>num){
							consola.append("\n" + luchadores[0].getNom()+" torna a atacar!!");
						}else {
							Collections.swap(Arrays.asList(luchadores),0,1);
						}
					}delay(1000);
				}
		
			}
		}
		
		if(warrioreal.getPuntsVida() > 0) {
			consola.append("\n" + "=".repeat(20));
			consola.append("\n" + "El guerrer " + warrioreal.getNom()+ " de l'usuari " + nomUsuari + "és el guanyador!!");
			consola.append("\n" + "=".repeat(20));
		}else {
			consola.append("\n" + "=".repeat(20));
			consola.append("\n" + "El guerrer " + warriorbot.getNom()+ " de l'usuari bot és el guanyador!!");
			consola.append("\n" + "=".repeat(20));
		}
		
		delay(3000);
		
		// Es mostra un missatge on es pregunta si vol continuar jugant o no
		
		String message = "Do you want to keep fighting?";
		int option = JOptionPane.showOptionDialog(null, message, "Keep Fighting", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Yes", "No"}, "Yes");
		
		// Si l'opció es "YES", és a dir, continuar lluitant es farà això 
		
		if (option == JOptionPane.YES_OPTION) {
			
			// Si ha guanyat
			
			if (warrioreal.getPuntsVida() > 0) {
				
				// Reseteja la vida del personatge i del contrincant
			    warrioreal.setPuntsVida(vidaInicialWarriorReal);
				warriorbot.setPuntsVida(vidaInicialWarriorBot);
				
			    // Selecciona un nuevo contrincante aleatorio con un arma aleatoria
				warriorbot = seleccionarRandomWarrior(warriorContainer.getArrayListWarrior());
				inicialitzarImatges(warrioreal.getUrlImatge(), warriorbot.getUrlImatge(), esInici);

			    // Acumula la puntuació
				//puntuacio += puntuacio;
			    
			// Si ha perdut	
			
			} else {
				// Guarda la puntuació en la base de datos
			    insertBattleBD(playerId, warrioreal.getId(), warrioreal.getArma().getId(), warriorbot.getId(), warriorbot.getArma().getId(), danyCausat, danyRebut, puntuacio);

			    // Reseteja els stats del personatge i del contrincant
			    warrioreal.setPuntsVida(vidaInicialWarriorReal);
				warriorbot.setPuntsVida(vidaInicialWarriorBot);

				// Elimina l'arma seleccionada
			    warrioreal.setArma(null);
			    warriorbot.setArma(null);
				
			    // Muestra una imatge indicant que no has triat cap warrior
			    warriorbot = seleccionarRandomWarrior(warriorContainer.getArrayListWarrior());
			    esInici = true;
				inicialitzarImatges("imagenes/anonim.jpg", warriorbot.getUrlImatge(), esInici);	
			}
			
		// Si l'opció és "NO", és a dir, no vol continuar lluitant es farà això
		  
		} else {
			// Guarda la puntuació en la base de dades
			insertBattleBD(playerId, warrioreal.getId(), warrioreal.getArma().getId(), warriorbot.getId(), warriorbot.getArma().getId(), danyCausat, danyRebut, puntuacio);
			
			// Finalitza l'aplicació
		    System.exit(0);
		}
	}
	
	// Aquest mètode fa l'aturada del temps
	
	public void delay(long milis){
		
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Aquest mètode inserta les dades a la taula battle
	
	public void insertBattleBD(int playerId, int warriorId, int warriorWeaponId, int opponentId, int opponentWeaponId, int injuriesCaused, int injuriesSuffered, int battlePoints) {
        
		String query = "INSERT INTO battle (player_id, warrior_id, warrior_weapon_id, opponent_id, "
                + "opponent_weapon_id, injuries_caused, injuries_suffered, battle_points) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
        	stmt = conn.prepareStatement(query);
            
        	stmt.setInt(1, playerId);
        	stmt.setInt(2, warriorId);
        	stmt.setInt(3, warriorWeaponId);
        	stmt.setInt(4, opponentId);
        	stmt.setInt(5, opponentWeaponId);
        	stmt.setInt(6, injuriesCaused);
        	stmt.setInt(7, injuriesSuffered);
        	stmt.setInt(8, battlePoints);

        	stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	// Aquest mètode crea els progressBar
	
	public void insertPlayer(String playerName) {
		
        String query = "INSERT INTO players (player_name) VALUES (?)";

        try {
            stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, playerName);

            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                playerId = generatedKeys.getInt(1);
                System.out.println(playerId);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
}