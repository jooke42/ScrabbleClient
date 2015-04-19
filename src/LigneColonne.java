
public class LigneColonne {
	public boolean ligne;
	public int coordonneX;
	public int coordonneY;
	public LigneColonne(boolean ligne,int coordonneX,int coordonneY){
		this.ligne =ligne;
		this.coordonneX = coordonneX;
		this.coordonneY = coordonneY;
	}
	public boolean getLigne(){
		return ligne;
	}
	public int getCoordonneX(){
		return coordonneX;
	}
	public int getCoordonneY(){
		return coordonneY;
	}
}
