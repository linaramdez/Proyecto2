import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InterficieGrafica {

	public static void main(String[] args) {
		
		new VentanaPrincipal();
	}
}

// Creem la classe VentanaPrincipal on es trobarà la inferficie gràfica on es produeix la lluita

class VentanaPrincipal extends JFrame{
	
	private JPanel panel1, panel2, panel21, panel22, panel23, panel24, panel25, panel26, panel3, panel31, panel4;
	private JButton boto1, boto2, boto3, boto4, boto5;
	private JTextArea consola;
	private JScrollPane scroll;
	private JProgressBar progressBar1, progressBar11,  progressBar12,  progressBar13,  progressBar14, progressBar2, progressBar21, 
	progressBar22, progressBar23, progressBar24;
	private ImageIcon imatgeWarrior1, imatgeWarrior2, imatgeWeapon1, imatgeWeapon2;
	private JLabel power1, power2, agility1, agility2, speed1, speed2, defense1, defense2, imatge1, imatge2;
	
	private JFrame ventana2, ventana3;
	private ImageIcon imagenOriginal;
	private Image imagen;
	private Image imagenRedimensionada;
	private ImageIcon imagenFinal;
	private JLabel humans, elfs, nans;
	private JPanel panelHumans, panelElfs, panelNans;
	private ArrayList<String> textArmes;
	private ArrayList<JLabel> labelArmes;
	
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
		
		// Creem els objectes botons
		
		boto1 = new JButton("Choose Character");
		boto2 = new JButton("Choose Weapon");
		boto3 = new JButton("Ranking");
		boto4 = new JButton("Fight");
		boto5 = new JButton("Clear Console");
		
		// Creem l'objecte consola
		
		consola = new JTextArea(5, 10);
		consola.setEditable(false);
		
		// Creem l'objecte scroll
		
		scroll = new JScrollPane(consola);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		// Seleccionem un warrior i actualitzem la seva imatge
		
		
		imatgeWarrior1 = new ImageIcon();
		imatgeWeapon1 = new ImageIcon("imagenes/we1.jpg");
		imatgeWarrior2 = new ImageIcon();
		imatgeWeapon2 = new ImageIcon("imagenes/we1.jpg");
		
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
		
		panel25.add(new JLabel(imatgeWeapon1));
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
		
		panel26.add(new JLabel(imatgeWeapon2));
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
		
		// Creem els objectes finestra
		
		ventana2 = new JFrame("Selecció de guerrer");
		ventana3 = new JFrame("Selecció d'armes");
		
		ventana2.setLayout(new GridLayout(3, 5));
		ventana3.setLayout(new GridLayout(3, 3));
		
		// Creem els objectes text
		
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
            ventana2.add(new JLabel(imagenFinal));
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
            ventana2.add(new JLabel(imagenFinal));
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
            ventana2.add(new JLabel(imagenFinal));
        }
		
		for (int i = 1; i <= 9; i++) {
            String imagenPath = "imagenes/we" + i + ".jpg";
            ImageIcon imagenOriginal = new ImageIcon(imagenPath);
            imagen = imagenOriginal.getImage();
            int anchoNuevo = 80;
            int altoNuevo = 100;
            imagenRedimensionada = imagen.getScaledInstance(anchoNuevo, altoNuevo, Image.SCALE_SMOOTH);
            imagenFinal = new ImageIcon(imagenRedimensionada);
            ventana3.add(new JLabel(imagenOriginal));
        }
		
		ventana2.setSize(800, 650);
		ventana2.setVisible(true);	
		ventana3.setSize(800, 650);
		ventana3.setVisible(true);	

	}
	
	static JProgressBar crearProgressBar(int minimum, int maximum, int value, boolean stringPainted, Color foregroundColor) {
		
	    JProgressBar progressBar = new JProgressBar();
	    progressBar.setMinimum(minimum);
	    progressBar.setMaximum(maximum);
	    progressBar.setValue(value);
	    progressBar.setStringPainted(stringPainted);
	    progressBar.setForeground(foregroundColor);
	    
	    return progressBar;
	}
	
	static Warrior seleccionarRandomWarrior(ArrayList<Warrior> warriors) {
		
        Random random = new Random();
        int index = random.nextInt(warriors.size()); 
        Warrior randomWarrior = warriors.get(index);
        
        return randomWarrior;
        
    }
	
	public void inicialitzarImatges(String url1, String url2) {
		
		imatgeWarrior1 = new ImageIcon(url1);
		imatge1.setIcon(imatgeWarrior1);
		
		imatgeWarrior2 = new ImageIcon(url2);
		imatge2.setIcon(imatgeWarrior2);
		
	}
	
	public void obtenirWarrior(Warrior warrior1, Warrior warrior2) {
		
		progressBar11.setValue(warrior1.getForça() * 10);
		progressBar12.setValue(warrior1.getAgilitat() * 10);
		progressBar13.setValue(warrior1.getVelocitat() * 10);
		progressBar14.setValue(warrior1.getDefensa() * 10);
		
		progressBar21.setValue(warrior2.getForça() * 10);
		progressBar22.setValue(warrior2.getAgilitat() * 10);
		progressBar23.setValue(warrior2.getVelocitat() * 10);
		progressBar24.setValue(warrior2.getDefensa() * 10);
		
	}
	
}