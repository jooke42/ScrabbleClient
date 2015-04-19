import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class Plateau {
	private Case[][] plateau;
	private LinkedHashMap<Mot, String> Mots;
	public Plateau(Case[][] plateau){
		this.plateau = plateau;
	}
	public void setPlateau(Case[][] plateau){
		this.plateau = plateau;
	}
	public Case[][] getPlateau(){
		return plateau;
	}
}
