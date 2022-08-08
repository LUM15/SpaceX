import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Nave {
	private Image image;
	private int x , y, dx, dy;
	private final int velocidade = 3;
	private int altura, largura;
	private boolean visivel;

	private List<Missil> misseis;
	
	public Nave() {
		ImageIcon jet_path = new ImageIcon("res\\player.gif"); 
		image = jet_path.getImage();
		altura = image.getHeight(null);
		largura = image.getWidth(null);
		
		misseis = new ArrayList<Missil>();
		
		this.x = 235;
		this.y = 600;
	}
	
	public List<Missil> getMisseis() {
		return misseis;
	}

	public void mexer() {	
		this.x += dx;
		if (x < 0) {
			x = 0;
		}
		if (x > 455){
			x = 455;
		}
		
		this.y += dy;
		if (y < 0) {
			y = 0;
		}
		if (y > 680){
			y = 680;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void atirar() {
		this.misseis.add(new Missil(this.x + 8, this.y - 22));
	}
	
	public void keyPressed(KeyEvent ke) {
		int codigo = ke.getKeyCode();
		
		if (codigo == ke.VK_Z) {
			this.atirar();
		}
		
		if (codigo == ke.VK_UP) {
			this.dy = -velocidade;
		}
		
		if (codigo == ke.VK_DOWN) {
			this.dy = velocidade;
		}
		
		if (codigo == ke.VK_LEFT) {
			this.dx = -velocidade;
		}
		
		if (codigo == ke.VK_RIGHT) {
				this.dx = velocidade;
		}
	}
	
	public void keyReleased(KeyEvent ke) {
		
		int codigo = ke.getKeyCode();

		if (codigo == ke.VK_UP) {
			dy = 0;
		}
		
		if (codigo == ke.VK_DOWN) {
			dy = 0;
		}
		
		if (codigo == ke.VK_LEFT) {
			dx = 0;
		}
		
		if (codigo == ke.VK_RIGHT) {
			dx = 0;
		}	
	}
	
	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	
}
