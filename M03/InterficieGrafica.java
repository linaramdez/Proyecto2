package Proyect;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;



public class InterficieGrafica {

	public static void main(String[] args) {
		
		new VentanaPrincipal();
	}
}

// We create the VentanaPrincipal class where you will find the graphic inferficie where the fight takes place

class VentanaPrincipal extends JFrame{
	
	private JPanel panel1, panel2, panel21, panel22, panel23, panel24, panel25, panel26, panel3, panel31, panel4, panelHumans, panelElfs, panelNans;
	private JButton boto1, boto2, boto3, boto4, boto5;
	private JTextArea consola;
	private JScrollPane scroll;
	private JProgressBar progressBar1, progressBar11,  progressBar12,  progressBar13,  progressBar14, progressBar2, progressBar21, 
	progressBar22, progressBar23, progressBar24;
	private ImageIcon imatgeWarrior1, imatgeWarrior2, imatgeWeapon1, imatgeWeapon2, imagenOriginal, imagenFinal;
	private JLabel power1, power2, agility1, agility2, speed1, speed2, defense1, defense2, imatge1, imatge2, imatge3, imatge4, humans, elfs, nans, interaccio;
	private JFrame ventana2, ventana3,ventana4;
	private Image imagen, imagenRedimensionada;
	private ArrayList<String> textArmes, urlArmes;
	private ArrayList<JLabel> labelArmes;
    private WarriorContainer warriorContainer;
    private WeaponContainer weaponContainer;
    private int warriorSeleccionat,players, puntuacio=0, danyCausat=0, danyRebut=0,batallesGuanyades,playerId= -1;
    private Warrior warrioreal,warriorbot;
    private String nombre,nomUsuari= "",urlDatos = "jdbc:mysql://localhost/battleRace?serverTimezone=UTC",usuario = "root", pass = "1234";;
    private boolean aTriatWarrior = false, aTriatWeapon= false, esInici= false,posible=true;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
	
	VentanaPrincipal() {
		
		// We create the panel objects
		
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
		
		// We create the object consola
		
		consola = new JTextArea(5, 10);
		consola.setEditable(false);
		
		// We create the object scroll
		
		scroll = new JScrollPane(consola);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		// We create the objects warriorContainer and weaponContainer
		
		warriorContainer = new WarriorContainer();
		warriorContainer.carregarWarriorsFromDB();
		weaponContainer = new WeaponContainer();
		weaponContainer.carregarWeaponsFromDB();
		
		// We create the button objects
		
		boto1 = new JButton("Choose Character");
		
		// This will be done when the choose warrior button is clicked
		
		boto1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(posible) {
				//We create the ranks of humans, elves and dwarves.
				ventana2 = new JFrame("Warrior Selection");
				ventana2.setLayout(new GridLayout(3, 5));
				humans = new JLabel("Humans:");
				humans.setHorizontalAlignment(JLabel.CENTER);
		        humans.setVerticalAlignment(JLabel.CENTER);
				elfs = new JLabel("Elves:");
				elfs.setHorizontalAlignment(JLabel.CENTER);
		        elfs.setVerticalAlignment(JLabel.CENTER);
				nans = new JLabel("Dwarfs:");
				nans.setHorizontalAlignment(JLabel.CENTER);
		        nans.setVerticalAlignment(JLabel.CENTER);
				
				ventana2.add(humans);
				
				for (int i = 1; i <= 4; i++) {
					//We add first the humans
		            String imagenPath = "imagenes/wa" + i + ".jpg";
		            ImageIcon imagenOriginal = new ImageIcon(imagenPath);
		            imagen = imagenOriginal.getImage();
		            int anchoNuevo = 100;
		            int altoNuevo = 150;
		            imagenRedimensionada = imagen.getScaledInstance(anchoNuevo, altoNuevo, Image.SCALE_SMOOTH);
		            imagenFinal = new ImageIcon(imagenRedimensionada);
		            JLabel label = new JLabel(imagenFinal);
		            label.addMouseListener(new MouseAdapter() {
		            	//Let's add the action of choosing a character
		                @Override
		                public void mouseClicked(MouseEvent e) {
		                	for (Warrior u: warriorContainer.getArrayListWarrior()) {
				    			
				    			if (u.getUrlImatge().equals(imagenPath)) {
				    				
				    				 if(u.getUrlImatge().equals(warriorbot.getUrlImatge())) {
				    					 JOptionPane.showOptionDialog(null, "This warrior has been selected!!", "Warrior not available", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
				    				 }else {
				    					 aTriatWarrior = true;
						                    String nombreImagen = imagenPath;
						                    actualitzarImatgeWarriorTriat(nombreImagen);
						                    actualitzarValorsWarriorTriat(nombreImagen);
						                    ventana2.setVisible(false);
						                    ventana2.dispose();
				    				 }
				    			}}
		                }
		            });
		            ventana2.add(label);
		        }
				
				ventana2.add(nans);
				//To add dwarfs
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
		            	//Let's add the action of choosing a character
		                @Override
		                public void mouseClicked(MouseEvent e) {
							for (Warrior u: warriorContainer.getArrayListWarrior()) {
				    			if (u.getUrlImatge().equals(imagenPath)) {
				    				
				    				 if(u.getUrlImatge().equals(warriorbot.getUrlImatge())) {
				    					 JOptionPane.showOptionDialog(null, "This warrior has been selected!!", "Warrior not available", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
				    				 }else {
				    					 aTriatWarrior = true;
						                    String nombreImagen = imagenPath;
						                    actualitzarImatgeWarriorTriat(nombreImagen);
						                    actualitzarValorsWarriorTriat(nombreImagen);
						                    ventana2.setVisible(false);
						                    ventana2.dispose();
				    				 }
				    			}}
		                }
		            });
		            ventana2.add(label);
		        }
				
				ventana2.add(elfs);
				//Add the elves
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
		            	//Let's add the action of choosing a character
		                @Override
		                public void mouseClicked(MouseEvent e) {
		                		for (Warrior u: warriorContainer.getArrayListWarrior()) {
				    			
				    			if (u.getUrlImatge().equals(imagenPath)) {
				    				
				    				 if(u.getUrlImatge().equals(warriorbot.getUrlImatge())) {
				    					
				    					 JOptionPane.showOptionDialog(null, "This warrior has been selected!!", "Warrior not available", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
				    				 }else {
				    					 aTriatWarrior = true;
						                    String nombreImagen = imagenPath;
						                    actualitzarImatgeWarriorTriat(nombreImagen);
						                    actualitzarValorsWarriorTriat(nombreImagen);
						                    ventana2.setVisible(false);
						                    ventana2.dispose();
				    				 }
				    			}}
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
				//We locate the window in the center
				ventana2.setLocation((ancho/2) - (ventana2.getWidth()/2), (alto/2) - (ventana2.getHeight()/2));
				
			}
			else {
				 JOptionPane.showOptionDialog(null, "If you won you can't choose another warrior!!", "Warrior not available", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

			}
			}
			
		});
		//We create the button to choose a weapon
		boto2 = new JButton("Choose Weapon");
		
		// This will be done when the choose weapon button is clicked
		
		// This will be done when the choose weapon button is clicked

		boto2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (aTriatWarrior) { 
					
					try {
						
						textArmes = new ArrayList<String>();
						labelArmes = new ArrayList<JLabel>();
						urlArmes = new ArrayList<String>();
						
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
			            
			            for (Weapon j : weaponContainer.getArrayListWeapons()) {
			                if (armesDisponiblesWarrior1.contains(j.getId())) {
			                    // Get weapon URL and weapon number
			                    String urlsArma = j.getUrlImatge();
			                    String nombreArma = j.getName();
			                    
			                    // Add the name of the weapon to the textArmes list and url to the urlArmes list
			                    textArmes.add(nombreArma);
			                    urlArmes.add(urlsArma);
			                    
			                    // Create a JLabel with the name of the weapon
			                    JLabel labelAr = new JLabel(nombreArma);
			                    labelAr.setHorizontalAlignment(JLabel.CENTER);
			                    labelAr.setVerticalAlignment(JLabel.CENTER);
			                    
			                    // Add the JLabel to the labelArmes list
			                    labelArmes.add(labelAr);
			                }
			            }
			            
			            ventana3 = new JFrame("Weapon Select");
			            
			            // Get the size of the GridLayout based on the number of weapons available
			            int cantidadArmas = labelArmes.size();
			            int filas = (int) Math.ceil(cantidadArmas / 3.0);  // Adjust the number of columns according to your needs

			            // Create the GridLayout with the calculated size
			            ventana3.setLayout(new GridLayout(filas, 3));
			            
			            for (int i = 0; i < labelArmes.size(); i++) {
						    String imagenPath = urlArmes.get(i);
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
						        	
						        	Weapon weaponTriat = null;
						        	
						        	for (Weapon u: weaponContainer.getArrayListWeapons()) {
						    			
						    			if (u.getUrlImatge().equals(imagenPath)) {
						    				
						    				weaponTriat = u;
						    				// Show weapon statistics
						    				String message = "Plus Power: " + u.getPlusForça() + "\nPlus Speed: " + u.getPlusVelocitat();
						    				int option = JOptionPane.showOptionDialog(null, message, "Weapon Stats", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
						    				
						    				// Check if the user has clicked the "OK" button
						    				if (option == JOptionPane.OK_OPTION) {
						    				    // Check if the user has clicked the "OK" button
						    					consola.setText("Correctly selected weapon!!");
									            aTriatWeapon = true;
						    					imatgeWeapon1 = new ImageIcon(imagenPath);
						    					imatge3.setIcon(imatgeWeapon1);
						    					actualitzarValorsWarriorTriat2(warriorSeleccionat, weaponTriat);
						    					ventana3.dispose();
						    				}
						    			}
						    		}
			 
						        }
						    });
						    ventana3.add(labelArmes.get(i));
						    ventana3.add(label);
						}

						
						ventana3.setSize(800, 650);
						ventana3.setVisible(true);

			            
			        } catch (SQLException u) {
			        	System.out.println("The connection could not be created");
			        }

				} else {
					
					consola.setText("You must first choose a warrior before choosing a weapon");	
				}	
			}
			
		});
		//We create the Ranking button
		boto3 = new JButton("Ranking");
		//It will do this when we select it
		boto3.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				//Create a new window

			ventana4 = new JFrame("Ranking");
			ventana4.setLayout(new GridLayout(11, 6));

			JLabel player, name,punts, won, inCaused, inSuffered;

			//Generate the header with the different data that will be there
			player = new JLabel("Player ID");
			player.setOpaque(true);
			Color color = player.getBackground();
			player.setBackground(new Color((color.getRed()+130)%256,(color.getGreen()+161)%256,(color.getBlue()+177)%256));
			
			name = new JLabel("Player name");
			name.setOpaque(true);
			name.setBackground(new Color((color.getRed()+130)%256,(color.getGreen()+161)%256,(color.getBlue()+177)%256));

			punts = new JLabel("Total score");
			punts.setOpaque(true);
			punts.setBackground(new Color((color.getRed()+130)%256,(color.getGreen()+161)%256,(color.getBlue()+177)%256));

			won = new JLabel("Battles won");
			won.setOpaque(true);
			won.setBackground(new Color((color.getRed()+130)%256,(color.getGreen()+161)%256,(color.getBlue()+177)%256));

			inCaused = new JLabel("Damage caused");

			inCaused.setOpaque(true);
			inCaused.setBackground(new Color((color.getRed()+130)%256,(color.getGreen()+161)%256,(color.getBlue()+177)%256));

			inSuffered = new JLabel("Damage taken");
			inSuffered.setOpaque(true);
			inSuffered.setBackground(new Color((color.getRed()+130)%256,(color.getGreen()+161)%256,(color.getBlue()+177)%256));


			
			ventana4.add(player);
			
			ventana4.add(name);

			ventana4.add(punts);

			ventana4.add(won);

			ventana4.add(inCaused);

			ventana4.add(inSuffered);
			

			//We enter the SQL data in the Ranking window

			try {


			// Create an SQL statement

			Statement stmt = conn.createStatement();

			// Run the query

			String query = "SELECT b.player_id,"
					+ "p.player_name,"
					+ "SUM(b.battle_points) AS total_score, "
					+ "COUNT(CASE WHEN b.battle_points > 0 THEN 1 ELSE NULL END) AS battles_won, "
					+ "SUM(b.injuries_caused) AS total_damage_caused, "
					+ "SUM(b.injuries_suffered) AS total_damage_taken "
					+ "FROM battle b\n"
					+ "inner join players p on p.player_id=b.player_id "
					+ "GROUP BY player_id "
					+ "ORDER BY total_score DESC "
					+ "LIMIT 10";

			ResultSet rs = stmt.executeQuery(query);

			// Loops through the results and assigns them to variables
			//Assign a variable n to record the number of players generated within the ranking
			int n=0;
			while (rs.next()) {
			n++;
			
			players = rs.getInt("player_id");
			
			nombre = rs.getString("player_name");

			puntuacio = rs.getInt("total_score");

			batallesGuanyades = rs.getInt("battles_won");

			danyCausat = rs.getInt("total_damage_caused");

			danyRebut = rs.getInt("total_damage_taken");


			JLabel playerIdLabel = new JLabel(Integer.toString(players));
			playerIdLabel.setOpaque(true);
			playerIdLabel.setBackground(new Color((color.getRed()+231)%256,(color.getGreen()+235)%256,(color.getBlue()+218)%256));
	
			JLabel playerNameLabel = new JLabel(nombre);
			playerNameLabel.setVerticalAlignment(SwingConstants.CENTER);
			playerNameLabel.setOpaque(true);
			playerNameLabel.setBackground(new Color((color.getRed()+231)%256,(color.getGreen()+235)%256,(color.getBlue()+218)%256));


			JLabel totalScoreLabel = new JLabel(Integer.toString(puntuacio));
			totalScoreLabel.setOpaque(true);
			totalScoreLabel.setBackground(new Color((color.getRed()+231)%256,(color.getGreen()+235)%256,(color.getBlue()+218)%256));


			JLabel battlesWonLabel = new JLabel(Integer.toString(batallesGuanyades));
			battlesWonLabel.setOpaque(true);
			battlesWonLabel.setBackground(new Color((color.getRed()+231)%256,(color.getGreen()+235)%256,(color.getBlue()+218)%256));


			JLabel totalDamageCausedLabel = new JLabel(Integer.toString(danyCausat));
			totalDamageCausedLabel.setOpaque(true);
			totalDamageCausedLabel.setBackground(new Color((color.getRed()+231)%256,(color.getGreen()+235)%256,(color.getBlue()+218)%256));


			JLabel totalDamageTakenLabel = new JLabel(Integer.toString(danyRebut));
			totalDamageTakenLabel.setOpaque(true);
			totalDamageTakenLabel.setBackground(new Color((color.getRed()+231)%256,(color.getGreen()+235)%256,(color.getBlue()+218)%256));


			ventana4.add(playerIdLabel);
			
			ventana4.add(playerNameLabel);

			ventana4.add(totalScoreLabel);

			ventana4.add(battlesWonLabel);

			ventana4.add(totalDamageCausedLabel);

			ventana4.add(totalDamageTakenLabel);


			}
			//If the minimum number of players to make the ranking (10 players) have not been assigned, empty JLabels will be generated until 10 are reached.
			while(n<10) {
				
				ventana4.add(new JLabel(""));
				
				ventana4.add(new JLabel(""));

				ventana4.add(new JLabel(""));

				ventana4.add(new JLabel(""));

				ventana4.add(new JLabel(""));

				ventana4.add(new JLabel(""));
				n++;
			}

			ventana4.setSize(800, 650);
			ventana4.setVisible(true);


			} catch (SQLException x) {

			x.printStackTrace();

			}


			}


			});
		
		boto4 = new JButton("Fight");
		
		// This will be done when the fight button is clicked
		
		boto4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (aTriatWarrior && aTriatWeapon) {
					Warrior luchadores[]= new Warrior[2];
					consola.setText("");
					consola.append("\n\nTHE FIGHT START\n"+" ".repeat(10)+String.format("%-60s", nomUsuari)+String.format("%-60s", "VS")+String.format("%-60s", "Boot"));
					luchadores[0]=warrioreal;
					luchadores[1]=warriorbot;
					//Call the fight function
					iniciarLluita(luchadores, warrioreal, warriorbot);
				} else {
					consola.setText("You have to choose a warrior and an available weapon");
				}
			}
			
		});
		
		boto5 = new JButton("Clear Console");
		
		// This will be done when the clean console button is clicked
		
		boto5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				consola.setText("");
			}
			
		});
		
		// We create the ImageIcon objects
		
		imatgeWarrior1 = new ImageIcon();
		imatgeWeapon1 = new ImageIcon();
		imatgeWarrior2 = new ImageIcon();
		imatgeWeapon2 = new ImageIcon();
		
		// We create the ProgressBar objects
		
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

		// We create the stats objects
		
		power1 = new JLabel("Power");
		agility1 = new JLabel("Agility");
		speed1 = new JLabel("Speed");
		defense1 = new JLabel("Defense");
		
		power2 = new JLabel("Power");
		agility2 = new JLabel("Agility");
		speed2 = new JLabel("Speed");
		defense2 = new JLabel("Defense");
		
		// We add the elements to the panels
		
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
		
		// We add the panels to the window
		
		this.add(panel1, BorderLayout.NORTH);
		this.add(panel2, BorderLayout.CENTER);
		this.add(panel4, BorderLayout.SOUTH);
		
		// We specify the characteristics of the window
		
		this.setTitle("BattleRaces");
		this.setSize(800,650);
		this.setResizable(false);
		Toolkit pantalla = Toolkit.getDefaultToolkit(); 
		Dimension grandaria = pantalla.getScreenSize(); 
		int ancho = grandaria.width; 
		int alto = grandaria.height;
		this.setLocation((ancho/2) - (this.getWidth()/2), (alto/2) - (this.getHeight()/2)); 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	

	}
	
	// This method identifies the user with a unique player_id and a name entered by the user
	
	public void identificacioUsuari() {

        nomUsuari = null;

        while (nomUsuari == null || nomUsuari.isBlank()) {
            nomUsuari = JOptionPane.showInputDialog(null, "Enter your username:");

            // Check if a valid username was entered
            if (nomUsuari != null && !nomUsuari.isBlank()) {
                // The username is assigned to a variable within the program
                insertPlayer(nomUsuari);
                consola.setText("Welcome " + nomUsuari + "!");
            } else {
                // User cancelled or did not enter a valid username
                consola.setText("Invalid username!!");
            }
        }
    }
	
	
	
	// This method creates the progressBar
	
	static JProgressBar crearProgressBar(int minimum, int maximum, int value, boolean stringPainted, Color foregroundColor) {
		
	    JProgressBar progressBar = new JProgressBar();
	    progressBar.setMinimum(minimum);
	    progressBar.setMaximum(maximum);
	    progressBar.setValue(value);
	    progressBar.setStringPainted(stringPainted);
	    progressBar.setForeground(foregroundColor);
	    
	    return progressBar;
	}
	
	// This method selects a random warriorbot
	
	
	static Warrior seleccionarRandomWarrior(ArrayList<Warrior> warriors) {
		
        Random random = new Random();
        int index = random.nextInt(warriors.size()); 
        Warrior randomWarrior = warriors.get(index);
        
        return randomWarrior;   
    }
	
	// This method initializes the images of the two warriors
	
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
	
	// This method initializes the warriorbot's weapon
	
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
        	System.out.println("The connection could not be created");
       }
		
	}
	
	// This method initializes the warriorbot values
	
	public void inicialitzarValorsWarrior2(Warrior warrior2, Weapon arma) {
		
		progressBar21.setValue(warrior2.getForça() * 10 + arma.getPlusForça() * 10 );
		progressBar22.setValue(warrior2.getAgilitat() * 10);
		progressBar23.setValue(warrior2.getVelocitat() * 10 +  arma.getPlusVelocitat() * 10);
		progressBar24.setValue(warrior2.getDefensa() * 10);
		
		warriorbot=warrior2;
		
	}
	
	// This method updates the image of the warrioreal
	
	public void actualitzarImatgeWarriorTriat(String url) {
		
		imatgeWarrior1 = new ImageIcon(url);
		imatge1.setIcon(imatgeWarrior1);

	}
	
	// This method updates the warrioreal values
	
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
	
	// This method updates the values ​​of the warrioreal when it already has its weapon
	
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
	
	// This method starts the fight
	//The StringWorker is used to be able to reflect what we want inside the JTextArea of ​​our main window
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
	            // The battle is over
	            
	        }
	    };

	    worker.execute();
	}

//These methods are responsible for updating the bar while the fight is running
	public void actualizarbarra(JProgressBar barra,int valor){
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            barra.setValue(valor);
	        }
	    });
	}
	
	// This method executes the fighting mechanics
	
	public void lluita(Warrior[] luchadores, Warrior warrioreal, Warrior warriorbot) {
		posible=true;
		//Two variables are saved that represent each fighter's starting life
		int vidaInicialWarriorReal = warrioreal.getPuntsVida();
		int vidaInicialWarriorBot = warriorbot.getPuntsVida();
		delay(2000);
		consola.setText("");
		//An ArrayList is created with the names of the players
		ArrayList<String> nom = new ArrayList<String>();
		nom.add(nomUsuari);
		nom.add("Boot");
		//An ArrayList of each player's starting life is created
		ArrayList<Integer> vidas = new ArrayList<Integer>();
	    vidas.add(luchadores[0].getPuntsVida());
	    vidas.add(luchadores[1].getPuntsVida());
	    //An Array is created to store the two progress bars that represent each fighter's percentage of life
		JProgressBar barras[]= new JProgressBar[2];
		barras[0]=progressBar1;
		barras[1]=progressBar2;
		
		//In case the first fighter has greater speed than the second.
		if (luchadores[0].getVelocitat()>luchadores[1].getVelocitat()) {
			//Iteration that ends when one of the two fighters runs out of life points
			while(luchadores[0].getPuntsVida()>0 && luchadores[1].getPuntsVida()>0) {
				consola.append("\n" + "Turn of "+nom.get(0));
				//The success variable is calculated to determine whether the attack succeeds or not
				int exit= (int) (Math.random() * 99) + 1;
				if(luchadores[0].getAgilitat()*10>exit) {
					//The attack succeeds and the dodge variable will be generated which will determine if the defender will be able to dodge the attack
					consola.append("\n"+"The attack was successful!!");
					int esquivar= (int) (Math.random() * 49) + 1;
					if(luchadores[1].getAgilitat()>esquivar) {
						//The defender dodges the attack
						consola.append("\n" + nom.get(1)+" dodged the attack!!");
					}else {
						//The attack is carried out and the defender cannot dodge it, it calculates the damage that the defender will take.
						int dany = (luchadores[0].getForça()+luchadores[0].getArma().getPlusForça())-luchadores[1].getDefensa();
						consola.append("\n" + nom.get(1)+" has received "+dany+" damage points!!");
						//The defender's life is updated and so is his life progress bar.
						luchadores[1].setPuntsVida(luchadores[1].getPuntsVida()-dany);
						actualizarbarra(barras[1],(int) (((double) luchadores[1].getPuntsVida() / vidas.get(1)) * 100));
						
						
					}
				}else {
					//The attack was not successful
					consola.append("\n" + "The attack could not be carried out, it was not successful!!");}
				
				//Once the attack is over, it is decided whether the attacker will continue to attack or become a defender
				if(luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat() <= luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()) {
					//In this case the attacker becomes defender. To make the change, all the variables put in the Arrays are swapped
					Collections.swap(Arrays.asList(luchadores),0,1);
					Collections.swap(vidas, 0, 1);
					Collections.swap(nom, 0,1);
					Collections.swap(Arrays.asList(barras),0,1);
				}else {
					int num= (int) (Math.random() * 99) + 1;
					if(((luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat())-(luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()))*10>num){
						//In this case the roles are maintained, no exchanges are made in the Arrays
						consola.append("\n" + nom.get(0)+" attacks again!!");
					}else {
						//In this case the attacker becomes defender. To make the change, all the variables put in the Arrays are swapped
						Collections.swap(Arrays.asList(luchadores),0,1);
						Collections.swap(vidas, 0, 1);
						Collections.swap(Arrays.asList(barras),0,1);
						Collections.swap(nom, 0,1);
					}
				}
				//There is a one second pause and the next attack is made
				delay(1000);
				
			}
			
		}
		//In case the second fighter has more speed than the first
		else if(luchadores[0].getVelocitat()<luchadores[1].getVelocitat()) {
			//The values ​​of the Arrays are exchanged so that the second becomes the attacker
			Collections.swap(Arrays.asList(luchadores),0,1);
			Collections.swap(vidas, 0, 1);
			Collections.swap(Arrays.asList(barras),0,1);
			while(luchadores[0].getPuntsVida()>0 && luchadores[1].getPuntsVida()>0) {
				consola.append("\n"+"Turn of "+nom.get(0));
				
				int exit= (int) (Math.random() * 99) + 1;
				if(luchadores[0].getAgilitat()*10>exit) {
					consola.append("\n" + "The attack was successful!!");
					int esquivar= (int) (Math.random() * 49) + 1;
					if(luchadores[1].getAgilitat()>esquivar) {
						consola.append("\n" + nom.get(1)+" dodged the attack!!");
					}else {
						int dany = (luchadores[0].getForça()+luchadores[0].getArma().getPlusForça())-luchadores[1].getDefensa();
						consola.append("\n" + nom.get(1)+" has received "+dany+" damage points!!");
						luchadores[1].setPuntsVida(luchadores[1].getPuntsVida()-dany);
						actualizarbarra(barras[1],(int) (((double) luchadores[1].getPuntsVida() / vidas.get(1)) * 100));
						
						
					}
				}else {consola.append("\n" + "The attack could not be carried out, it was not successful!!");}
				if(luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat() <= luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()) {
					Collections.swap(Arrays.asList(luchadores),0,1);
					Collections.swap(vidas, 0, 1);
					Collections.swap(Arrays.asList(barras),0,1);
					Collections.swap(nom, 0,1);
				}else {
					int num= (int) (Math.random() * 99) + 1;
					if(((luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat())-(luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()))*10>num){
						consola.append("\n" + nom.get(0)+" attacks again!!");
					}else {
						Collections.swap(Arrays.asList(luchadores),0,1);
						Collections.swap(vidas, 0, 1);
						Collections.swap(Arrays.asList(barras),0,1);
						Collections.swap(nom, 0,1);
					}
				}
				delay(1000);
			}
		}
		else {
			//In case the speed of the first is equal to that of the second but the agility of the first is greater
			if(luchadores[0].getAgilitat()>luchadores[1].getAgilitat()) {
				while(luchadores[0].getPuntsVida()>0 && luchadores[1].getPuntsVida()>0) {
					consola.append("\n" + "Turn of "+nom.get(0));
					int exit= (int) (Math.random() * 99) + 1;
					if(luchadores[0].getAgilitat()*10>exit) {
						consola.append("\n"+"The attack was successful!!");
						int esquivar= (int) (Math.random() * 49) + 1;
						if(luchadores[1].getAgilitat()>esquivar) {
							consola.append("\n" + nom.get(1)+" dodged the attack!!");
						}else {
							int dany = (luchadores[0].getForça()+luchadores[0].getArma().getPlusForça())-luchadores[1].getDefensa();
							consola.append("\n" + nom.get(1)+" has received "+dany+" damage points!!");
							luchadores[1].setPuntsVida(luchadores[1].getPuntsVida()-dany);
							actualizarbarra(barras[1],(int) (((double) luchadores[1].getPuntsVida() / vidas.get(1)) * 100));
							
							
						}
					}else {consola.append("\n" + "The attack could not be carried out, it was not successful!!");}
					if(luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat() <= luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()) {
						Collections.swap(Arrays.asList(luchadores),0,1);
						Collections.swap(vidas, 0, 1);
						Collections.swap(Arrays.asList(barras),0,1);
						Collections.swap(nom, 0,1);
					}else {
						int num= (int) (Math.random() * 99) + 1;
						if(((luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat())-(luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()))*10>num){
							consola.append("\n" + nom.get(0)+" attacks again!!");
						}else {
							Collections.swap(Arrays.asList(luchadores),0,1);
							Collections.swap(vidas, 0, 1);
							Collections.swap(Arrays.asList(barras),0,1);
							Collections.swap(nom, 0,1);
						}
					}

					delay(1000);
				}
				
			}
			//In case the speed of the first is equal to that of the second but the agility of the second is greater
			else if(luchadores[0].getAgilitat()<luchadores[1].getAgilitat()) {
				Collections.swap(Arrays.asList(luchadores),0,1);
				Collections.swap(vidas, 0, 1);
				Collections.swap(Arrays.asList(barras),0,1);
				while(luchadores[0].getPuntsVida()>0 && luchadores[1].getPuntsVida()>0) {
					consola.append("\n" + "Turn of "+nom.get(0));
					int exit= (int) (Math.random() * 99) + 1;
					if(luchadores[0].getAgilitat()*10>exit) {
						consola.append("\n" + "The attack was successful!!");
						int esquivar= (int) (Math.random() * 49) + 1;
						if(luchadores[1].getAgilitat()>esquivar) {
							consola.append("\n" + nom.get(1)+" dodged the attack!!");
						}else {
							int dany = (luchadores[0].getForça()+luchadores[0].getArma().getPlusForça())-luchadores[1].getDefensa();
							consola.append("\n" + nom.get(1)+" has received "+dany+" damage points!!");
							luchadores[1].setPuntsVida(luchadores[1].getPuntsVida()-dany);
							actualizarbarra(barras[1],(int) (((double) luchadores[1].getPuntsVida() / vidas.get(1)) * 100));
							
						}
					}else {consola.append("\n" + "The attack could not be carried out, it was not successful!!");}
					if(luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat() <= luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()) {
						Collections.swap(Arrays.asList(luchadores),0,1);
						Collections.swap(vidas, 0, 1);
						Collections.swap(Arrays.asList(barras),0,1);
						Collections.swap(nom, 0,1);
					}else {
						int num= (int) (Math.random() * 99) + 1;
						if(((luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat())-(luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()))*10>num){
							consola.append("\n" + nom.get(0)+" attacks again!!");
						}else {
							Collections.swap(Arrays.asList(luchadores),0,1);
							Collections.swap(vidas, 0, 1);
							Collections.swap(Arrays.asList(barras),0,1);
							Collections.swap(nom, 0,1);
						}
					}
					delay(1000);
				}
				
			}
			else {
				//If the agility and speed of the first and second fighters are the same, the attacker will be chosen randomly
				int atac= (int)Math.round(Math.random());
				if(atac!=0) {
				Collections.swap(Arrays.asList(luchadores),0,1);
				Collections.swap(vidas, 0, 1);
				Collections.swap(Arrays.asList(barras),0,1);
				Collections.swap(nom, 0,1);
				}
				while(luchadores[0].getPuntsVida()>0 && luchadores[1].getPuntsVida()>0) {
					consola.append("\n" + "Turn of "+nom.get(0));
					int exit= (int) (Math.random() * 99) + 1;
					if(luchadores[0].getAgilitat()*10>exit) {
						
						consola.append("\n" + "The attack was successful!!");
						int esquivar= (int) (Math.random() * 49) + 1;
						if(luchadores[1].getAgilitat()>esquivar) {
							consola.append("\n" + nom.get(1)+" dodged the attack!!");
						}else {
							int dany = (luchadores[0].getForça()+luchadores[0].getArma().getPlusForça())-luchadores[1].getDefensa();
							consola.append("\n" + nom.get(1)+" has received "+dany+" damage points!!");
							luchadores[1].setPuntsVida(luchadores[1].getPuntsVida()-dany);
							actualizarbarra(barras[1],(int) (((double) luchadores[1].getPuntsVida() / vidas.get(1)) * 100));
							
						}
					}else {consola.append("\n" + "The attack could not be carried out, it was not successful!!");}
					if(luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat() <= luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()) {
						Collections.swap(Arrays.asList(luchadores),0,1);
						Collections.swap(vidas, 0, 1);
						Collections.swap(Arrays.asList(barras),0,1);
						Collections.swap(nom, 0,1);
					}else {
						int num= (int) (Math.random() * 99) + 1;
						if(((luchadores[0].getVelocitat()+luchadores[0].getArma().getPlusVelocitat())-(luchadores[1].getVelocitat()+luchadores[1].getArma().getPlusVelocitat()))*10>num){
							consola.append("\n" + nom.get(0)+" attacks again!!");
						}else {
							Collections.swap(Arrays.asList(luchadores),0,1);
							Collections.swap(vidas, 0, 1);
							Collections.swap(Arrays.asList(barras),0,1);
							Collections.swap(nom, 0,1);
						}
					}
					delay(1000);
				}
		
			}
		}
		//When one of the two fighters runs out of life points, it will show the winning player
		if(warrioreal.getPuntsVida()>0) {
			puntuacio=puntuacio+warriorbot.getRaca()+warriorbot.getArma().getPunts();
			consola.append("\n" + "=".repeat(20));
			consola.append("\n" + nomUsuari+" is the winner!!");
			consola.append("\n" + "=".repeat(20));
		}else {
			consola.append("\n" + "=".repeat(20));
			consola.append("\n" + "Boot"+" is the winner!!");
			consola.append("\n" + "=".repeat(20));
		}
	
		danyCausat=danyCausat+(vidaInicialWarriorBot-warriorbot.getPuntsVida());
		danyRebut=danyRebut+(vidaInicialWarriorReal-warrioreal.getPuntsVida());
		delay(3000);
		
		// A message is displayed asking whether you want to continue playing or not
		
		String message = "Do you want to keep fighting?";
		int option = JOptionPane.showOptionDialog(null, message, "Keep Fighting", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Yes", "No"}, "Yes");
		
		//If the option is "YES", that is, continue fighting, this will be done
		
		if (option == JOptionPane.YES_OPTION) {
			
			// If he won
			if (warrioreal.getPuntsVida() > 0) {
				
				// Resets the life of the character and the opponent
			    warrioreal.setPuntsVida(vidaInicialWarriorReal);
				warriorbot.setPuntsVida(vidaInicialWarriorBot);
				
			    // Select a new random opponent with a random weapon
				do {
					warriorbot = seleccionarRandomWarrior(warriorContainer.getArrayListWarrior());
					}while(warriorbot.getUrlImatge().equals(warrioreal.getUrlImatge()));
				inicialitzarImatges(warrioreal.getUrlImatge(), warriorbot.getUrlImatge(), esInici);
				progressBar1.setValue(100);
				progressBar2.setValue(100);
				consola.setText("");
				posible=false;
			    // Accumulate the score
			    
			// If he lost
			
			} else {
				// Save the score in the database
			    insertBattleBD(playerId, warrioreal.getId(), warrioreal.getArma().getId(), warriorbot.getId(), warriorbot.getArma().getId(), danyCausat, danyRebut, puntuacio);

			    // Resets character and opponent stats
			    warrioreal.setPuntsVida(vidaInicialWarriorReal);
				warriorbot.setPuntsVida(vidaInicialWarriorBot);
				progressBar1.setValue(100);
				progressBar2.setValue(100);
				consola.setText("");

				// Removes the selected weapon
			    warrioreal.setArma(null);
			    warriorbot.setArma(null);
			    aTriatWarrior=false;
			    aTriatWeapon=false;
				
			    // Shows an image indicating that you have not selected a warrior
			    do {
					warriorbot = seleccionarRandomWarrior(warriorContainer.getArrayListWarrior());
					}while(warriorbot.getUrlImatge().equals(warrioreal.getUrlImatge()));
			    esInici = true;
				inicialitzarImatges("imagenes/anonim.jpg", warriorbot.getUrlImatge(), esInici);	
			}
			
		// If the option is "NO", that is, he does not want to continue fighting, this will be done
		  
		} else {
			// Save the score in the database
			insertBattleBD(playerId, warrioreal.getId(), warrioreal.getArma().getId(), warriorbot.getId(), warriorbot.getArma().getId(), danyCausat, danyRebut, puntuacio);
			
			// Terminate the application
		    System.exit(0);
		}
	}
	
	// This method stops time	
	public void delay(long milis){
		
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// This method inserts the data into the battle table
	
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
	
	//This method creates the player
	public void insertPlayer(String playerName) {
		
        String query = "INSERT INTO players (player_name) VALUES (?)";

        try {
            stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, playerName);

            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                playerId = generatedKeys.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
}