import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Missil {

	private Image image;
	private int x, y, altura, largura;

	private boolean visivel;
	
	private static final int ALTURA_DA_TELA = 0;
	private static final int VELOCIDADE = 5;
	
	public Missil(int x, int y) {
		this.x = x;
		this.y = y;
		
		ImageIcon image_path = new ImageIcon("res\\missil.gif");
		this.image = image_path.getImage();
		this.altura = image.getHeight(null);
		this.largura = image.getWidth(null);
		
		visivel = true;
	}

	public void mexer() {
		this.y -= VELOCIDADE;
		if(this.y < ALTURA_DA_TELA) visivel = false;
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
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

}
