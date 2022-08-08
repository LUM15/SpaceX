import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Janelas extends JFrame{

	ImageIcon logo_path = new ImageIcon("res\\logo.png");
	Image logo = logo_path.getImage();
	
	public Janelas() {

		setIconImage(logo);
		MenuJogo mr = new MenuJogo();
		setJMenuBar(mr.getJmenubar());
		add(new Mapa());
		setVisible(true);
		setTitle("SpaceX");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 750);
		setResizable(false);
		setLocationRelativeTo(null);	
	}

}
