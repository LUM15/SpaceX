import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class MenuJogo extends JFrame{

	JMenuBar jmenubar;

	public JMenuBar getJmenubar() {
		return jmenubar;
	}

	public MenuJogo() {
		jmenubar = new JMenuBar();
		
		JMenu menu = new JMenu("Menu");
		
		JMenuItem sobre = new JMenuItem("Sobre");
		sobre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Jogo desenvolvido por Luís Miguel", 
						"Informações", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JMenuItem fechar = new JMenuItem("Fechar");
		fechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					System.exit(0);
			}
		});
		
		JMenuItem comandos = new JMenuItem("Comandos");
		comandos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Velocidade dos inimigos:  +/-\n"
						+ "Mover:  SETAS        Atirar:  Z", 
						"Comandos", JOptionPane.INFORMATION_MESSAGE);
				
				
			}
		});
		
		menu.add(sobre);
		menu.add(comandos);
		menu.add(fechar);

		
		jmenubar.add(menu);
	}

}
