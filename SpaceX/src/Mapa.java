import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Mapa extends JPanel implements ActionListener{

	private Image fundo;
	private Image gameover;
	private Image gif;
	private Image intro;
	private Nave nave;
	private Timer t;
	
	private int emJogo = 2;
	
	private List<Inimigo> inimigos;
	private int[][] posicoes = {
		//Lista de coordenadas dos inimigos
		{	26	, 	-276}, 			{	30	,	-528},			{	338	,	-506},			{	416	,	-431}, 
		{	99	,	-95},	 		{	84	,	-663}, 			{	13	,	-179},			{	123	,	-686},	
		{	207	,	-94},			{	286	,	-411},			{	237	,	-284},		 	{	81	,	-672},	
		{	368	,	-564},			{	191	,	-203},			{	102	,	-424},			{	339	,	-555},	
		{	202	,	-581},			{	259	,	-36},			{	189	,	-687},			{	178	,	-634},	
		{	93	,	-451},			{	285	,	-384},			{	131	,	-637},			{	32	,	-671},	
		{	26	,	-263},			{	234	,	-154},			{	292	,	-424},			{	299	,	-356},			{	464	,	-121},	
		{	437	,	-73},			{	114	,	-30},			{	191	,	-150},			{	283	,	-287},	
		{	113	,	-679},			{	100	,	-290},			{	232	,	-410},			{	416	,	-608},	
		{	426	,	-443},			{	368	,	-33},			{	74	,	-34},			{	16	,	-322},			{	358	,	-261},	 
		{	87	,	-656},			{	268	,	-416},			{	247	,	-66},			{	378	,	-336},			{	10	,	-30},	
		{	52	,	-654},			{	400	,	-376}, 			{	22	,	-46},
	};

	public Mapa() {
		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());
		inicializaInimigos();
		ImageIcon image_path = new ImageIcon("res\\fundo.png");
		fundo = image_path.getImage();
		
		nave = new Nave();
		
		//Inicia o timer que a cada 5ms chama o ActionListener
		t = new Timer(1, this);
		t.start();
	}

	public void inicializaInimigos() {
		inimigos = new ArrayList<Inimigo>();
		for (int i = 0; i < posicoes.length; i++) {
			inimigos.add(new Inimigo(posicoes[i][0], posicoes[i][1]));
		}
	}
	
	String placar = "Inimigos: ";
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);

		//Valida se o jogo não acabou
		if (emJogo == 0) {
			graficos.drawImage(nave.getImage(), nave.getX(), nave.getY(), this);
			graficos.setColor(Color.WHITE);
			graficos.drawString(placar + inimigos.size(), 20, 20);
			//Inicializa misseis
			List<Missil> misseis = nave.getMisseis();
			for (int i = 0; i < misseis.size(); i++) {
				Missil m = (Missil) misseis.get(i);
				graficos.drawImage(m.getImage(), m.getX(), m.getY(), null);
			}
			
			//Inicializa inimigos
			for (int i = 0; i < inimigos.size(); i++) {
				Inimigo in = (Inimigo) inimigos.get(i);
				graficos.drawImage(in.getImage(), in.getX(), in.getY(), null);
			}

		} else if (emJogo == 1) {
			ImageIcon image_path = new ImageIcon("res\\sefudeu.png");
			gameover = image_path.getImage();
			graficos.drawImage(gameover, 0, 0, null);
			
			ImageIcon image_cat = new ImageIcon("res\\loser.gif");
			gif = image_cat.getImage();
			graficos.drawImage(gif, 130, 300, null);

		} else if (emJogo == 2) {
			ImageIcon image_path = new ImageIcon("res\\intro.png");
			intro = image_path.getImage();
			graficos.drawImage(intro, 0, 0, null);


		} else {
			ImageIcon image_path = new ImageIcon("res\\vitoria.png");
			gameover = image_path.getImage();
			graficos.drawImage(gameover, 0, 0, null);
		}
		
		//Limpa tela para redesenhar tudo novamente
		graficos.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		List<Missil> misseis = nave.getMisseis();
		
		for (int i = 0; i < misseis.size(); i++) {
			if (misseis.get(i).isVisivel()) {
				misseis.get(i).mexer();
			} else {
				misseis.remove(i);
			}
		}
		
		for (int i = 0; i < inimigos.size(); i++) {
			if (inimigos.get(i).isVisivel()) {
				inimigos.get(i).mexer();
			} else {
				inimigos.remove(i);
			}
		}
		
		if (inimigos.size() == 0) emJogo = 3;
		
		nave.mexer();
		checarColisoes();
		repaint();	
	}
	
	public void checarColisoes() {
		Rectangle hitBoxNave = nave.getBounds();
		Rectangle hitBoxInimigo;
		Rectangle hitBoxMissil;
		
		List <Missil> misseis = nave.getMisseis();
		for (int i = 0; i < misseis.size(); i++) {
			
			hitBoxMissil = misseis.get(i).getBounds();
			
			for(int j = 0; j < inimigos.size(); j++) {
				
				hitBoxInimigo = inimigos.get(j).getBounds();
				
				if (hitBoxMissil.intersects(hitBoxInimigo)) {
					misseis.get(i).setVisivel(false);
					inimigos.get(j).setVisivel(false);
				}
			}	
		}
		
		for (int i = 0; i < inimigos.size(); i++) {
			
			hitBoxInimigo = inimigos.get(i).getBounds();
			
			if (hitBoxNave.intersects(hitBoxInimigo)) {
				nave.setVisivel(false);
				inimigos.get(i).setVisivel(false);
				emJogo = 1;
			}
		}
	}
	
	
	private class TecladoAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == e.VK_ADD) Inimigo.alterarVELOCIDADE(2);
			if(e.getKeyCode() == e.VK_SUBTRACT) {
				if (Inimigo.getVELOCIDADE() > 2) Inimigo.alterarVELOCIDADE(-2);
			}
			
			if(e.getKeyCode() == e.VK_ENTER) {
				emJogo = 0;
				nave = new Nave();
				inicializaInimigos();
				Inimigo.setVELOCIDADE(2);
			}
			nave.keyPressed(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}
		
	}
	
	
}