import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;


public class Case {
	private final static int TAILLE=46;

	private char valeur;
	
	private Color couleurFond;
	private Color couleurTexte;
	private int posX;
	private int posY;
	public Case(char valeur){
		this.valeur = valeur;
		
		couleurFond=Color.darkGray;
		couleurTexte=Color.white;
		posX = -1;
		posY = -1;
	}
	public void draw(Graphics g){
		g.setColor(couleurFond);
		g.fillRect(posX, posY, TAILLE, TAILLE);
		g.setColor(couleurTexte);
		g.drawString(Character.toString(valeur), posX+TAILLE/2-g.getFont().getWidth(Character.toString(valeur))/2, TAILLE/2+posY-g.getFont().getHeight(Character.toString(valeur))/2);
	}
	public char getValeur(){
		return valeur;
	}
	public int getPosX(){
		return posX;
	}
	public int getPosY(){
		return posY;
	}
	public void setPosX(int posX){
		this.posX=posX;
	}
	public void setPosY(int posY){
		this.posY=posY;
	}

	public int getTaille(){
		return TAILLE;
	}
	public boolean onIt(Input input){
		if(input.getMouseX()>posX&&input.getMouseX()<posX+TAILLE&&input.getMouseY()>posY&&input.getMouseY()<posY+TAILLE)
			return true;
		else
			return false;
	}
	public void setColorBackground(Color color){
		couleurFond=color;
	}
	public Color getColorBackground(){
		return couleurFond;
	}
	public void setColorText(Color color){
		couleurTexte=color;
	}
	public void setValue(Character c){
		valeur = c;
	}
	
}
