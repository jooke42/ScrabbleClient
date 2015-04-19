import org.newdawn.slick.Color;


public class CaseMobile extends Case{
	private int etat; // 0 = dans la main 1 = en deplacement 2 = posé sur le plateau 3 = selectionne pour echange
	private int coordonneSurPlateauX;
	private int coordonneSurPlateauY;
	public CaseMobile(char valeur) {
		
		super(valeur);
		etat = 0;
		coordonneSurPlateauX=-1;
		coordonneSurPlateauY=-1;
		super.setColorBackground(new Color(150,150,150));
		// TODO Auto-generated constructor stub
	}
	public void setEtat(int etat){
		this.etat = etat;
	}
	public int getEtat(){
		return etat;
	}
	public int getCoordonneSurPlateauX(){
		return coordonneSurPlateauX;
	}	
	public int getCoordonneSurPlateauY(){
		return coordonneSurPlateauY;
	}
	public void setCoordonneSurPlateauX(int coordonneSurPlateauX){
		this.coordonneSurPlateauX=coordonneSurPlateauX;
	}
	public void setCoordonneSurPlateauY(int coordonneSurPlateauY){
		this.coordonneSurPlateauY=coordonneSurPlateauY;
	}

}
