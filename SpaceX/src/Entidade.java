import java.awt.Image;

public class Entidade implements Visivel{

	private Image image;
	private int x;
	private int y;
	private int altura;
	private int largura;
	private boolean visibilidade;
	private int velocidade;
	
	@Override
	public boolean getVisibilidade() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setVisibilidade() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Image getImagem() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
