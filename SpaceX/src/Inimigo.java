import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo {

	private Image image;
	private int x, y, altura, largura;


	private boolean visivel;
	
	private static final int POSICAO_INICIAL = -30;
	private static int VELOCIDADE = 0;
	

	public Inimigo(int x, int y) {
		this.x = x;
		this.y = y;
		
		ImageIcon image_path = new ImageIcon("res\\inimigo.png");
		this.image = image_path.getImage();
		
		this.altura  = image.getHeight(null);
		this.largura = image.getWidth(null);
		
		visivel = true;
	}

	public void mexer() {
		if (this.y > 780) {
			this.y = POSICAO_INICIAL;
		} else {
			this.y += VELOCIDADE;
		}
	}
	
	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public Image getImage() {
		return image;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getAltura() {
		return altura;
	}

	public int getLargura() {
		return largura;
	}
	
	public Rectangle getBounds() {
		Rectangle hitBox = new Rectangle(x, y, largura, altura);
		return hitBox;
	}
	
	public static void setVELOCIDADE(int velocidade) {
		VELOCIDADE = velocidade;
	}
	
	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}
	
	public static void alterarVELOCIDADE(int velocidade) {
		VELOCIDADE += velocidade;
	}


}
