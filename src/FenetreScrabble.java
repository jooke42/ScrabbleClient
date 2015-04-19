import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.w3c.dom.events.MouseEvent;




public class FenetreScrabble extends BasicGameState implements MusicListener{
	
	private final String texteMode1 ="Mode écrit des mots OH YEAHHH";
	private final String texteMode2 ="Mode change tes lettres negro !";
	private final int debutXPlateau =250;
	private int id;
	private boolean modeEchange;
	private GButton boutonEchanger,boutonFinTours;
	private Color couleurCaseAMot;
	private ArrayList<CaseMobile> lettreMain;
	private Plateau plateau;
	private Input input;
	private int lettreMouvement;
	
	//private ArrayList<Goutte> listGoutte;

	private boolean clique;


	public FenetreScrabble(int id) {
		this.lettreMain = new ArrayList<CaseMobile>();
		modeEchange = false;
		lettreMouvement = -1;
		couleurCaseAMot = new Color(33,81,133);
		boutonEchanger = new GButton(1075, 450, 100, 50,"Echanger", Color.darkGray, Color.white, Color.blue);
		boutonFinTours = new GButton(1075, 450, 100, 50,"Fin Tours", Color.darkGray, Color.white, Color.blue);
		ArrayList<Character> listLettre = new ArrayList<Character>();
		plateau = new Plateau( new Case[15][15]);
		for(int i =0 ; i <15 ;i++){
			for(int j = 0 ; j< 15 ;j++){
				plateau.getPlateau()[j][i]=new Case(' ');
				plateau.getPlateau()[j][i].setPosX(j*47+debutXPlateau);
				plateau.getPlateau()[j][i].setPosY(i*47+8);
			}
		}
		plateau.getPlateau()[7][7].setColorBackground(new Color(125,232,134));
	
		listLettre.add('A');
		listLettre.add('F');
		listLettre.add('D');
		listLettre.add('H');
		listLettre.add('I');
		listLettre.add('B');
		listLettre.add('Y');
		setMain(listLettre);
		this.id = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		// TODO Auto-generated method stub
		input = container.getInput();
		
		
			
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
	
		lettreMouvement = -1;
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, container.getWidth(),  container.getHeight());
		boutonEchanger.draw(g, input);
		g.fillRect(0, 0, container.getWidth(),  container.getHeight());
		boutonFinTours.draw(g, input);
		
		if(modeEchange==true)
			g.drawString(texteMode2, 975+280/2-g.getFont().getWidth(texteMode2)/2, 20/2+30-g.getFont().getHeight(texteMode2)/2);
		else
			g.drawString(texteMode1, 975+280/2-g.getFont().getWidth(texteMode1)/2, 20/2+30-g.getFont().getHeight(texteMode1)/2);
		boolean lettrePose = false;
		for(int i = 0 ; i < lettreMain.size();i++){
			if(lettreMain.get(i).getEtat()==2)
				lettrePose = true;
			if(lettreMain.get(i).getEtat()==1)
				lettreMouvement=i;
		}
		for(int i =0 ; i <15 ;i++){
			for(int j = 0 ; j< 15 ;j++){
				if(lettreMouvement==-1){
					plateau.getPlateau()[j][i].setColorBackground(Color.darkGray);
				}
				else{
					if(lettrePose ==false){
						if(plateau.getPlateau()[7][7].getValeur()=='*')
							plateau.getPlateau()[7][7].setColorBackground(new Color(129,224,134));
						else if((j<14&&plateau.getPlateau()[j+1][i].getValeur()!=' '||j>0&&plateau.getPlateau()[j-1][i].getValeur()!=' '||i<14&&plateau.getPlateau()[j][i+1].getValeur()!=' '||i>0&&plateau.getPlateau()[j][i-1].getValeur()!=' ')&&plateau.getPlateau()[j][i].getValeur()==' ')
							plateau.getPlateau()[j][i].setColorBackground(new Color(129,224,134));

					}
					else{
						
						
						
						
						Integer[][] placement = new Integer[15][15];
						ArrayList<LigneColonne> ligneColonne = new ArrayList<LigneColonne>();
						int nbLettrePlace=0;
						//Integer[][] ligneColonne = new Integer[3][20];// [0] => 1 = ligne 2 = colonne [1] => x de la piece [2] => y de la piece
						for(int k = 0 ; k < lettreMain.size();k++){
							
							if(lettreMain.get(k).getEtat()==2){
								placement[lettreMain.get(k).getCoordonneSurPlateauX()][lettreMain.get(k).getCoordonneSurPlateauY()]=k;
								nbLettrePlace++;
								for(int l = lettreMain.get(k).getCoordonneSurPlateauX()+1;l<14;l++){
									if(plateau.getPlateau()[lettreMain.get(k).getCoordonneSurPlateauX()-1][lettreMain.get(k).getCoordonneSurPlateauY()].getValeur()!=' '||plateau.getPlateau()[lettreMain.get(k).getCoordonneSurPlateauX()+1][lettreMain.get(k).getCoordonneSurPlateauY()].getValeur()!=' '){
										ligneColonne.add(new LigneColonne(true,lettreMain.get(k).getCoordonneSurPlateauX(),lettreMain.get(k).getCoordonneSurPlateauY()));
										
									}
								
								//plateau[lettreMain.get(k).getCoordonneSurPlateauX()+1][lettreMain.get(k).getCoordonneSurPlateauY()].setColorBackground(new Color(129,224,134));
									if(plateau.getPlateau()[lettreMain.get(k).getCoordonneSurPlateauX()][lettreMain.get(k).getCoordonneSurPlateauY()-1].getValeur()!=' '||plateau.getPlateau()[lettreMain.get(k).getCoordonneSurPlateauX()][lettreMain.get(k).getCoordonneSurPlateauY()+1].getValeur()!=' '){
										ligneColonne.add(new LigneColonne(false,lettreMain.get(k).getCoordonneSurPlateauX(),lettreMain.get(k).getCoordonneSurPlateauY()));
										
									}
								}


							}

						}
						if(nbLettrePlace==1&&plateau.getPlateau()[7][7].getValeur()=='*'){
							ligneColonne.add(new LigneColonne(true,7,7));
							ligneColonne.add(new LigneColonne(false,7,7));
							
						}
						for(int k = 0 ; k < ligneColonne.size();k++){
							boolean continuer =true;
							if(ligneColonne.get(k).getLigne()){
								for(int l = ligneColonne.get(k).getCoordonneX()+1;l <14&&continuer;l++){
									if(plateau.getPlateau()[l][ligneColonne.get(k).getCoordonneY()].getValeur()==' '&&placement[l][ligneColonne.get(k).getCoordonneY()]==null){
										plateau.getPlateau()[l][ligneColonne.get(k).getCoordonneY()].setColorBackground(new Color(129,224,134));
										continuer=false;
									}
									else{
										plateau.getPlateau()[l][ligneColonne.get(k).getCoordonneY()].setColorBackground(couleurCaseAMot);
									}
								}
								continuer=true;
								for(int l = ligneColonne.get(k).getCoordonneX()-1;l >=0&&continuer;l--){
									if(plateau.getPlateau()[l][ligneColonne.get(k).getCoordonneY()].getValeur()==' '&&placement[l][ligneColonne.get(k).getCoordonneY()]==null){
										plateau.getPlateau()[l][ligneColonne.get(k).getCoordonneY()].setColorBackground(new Color(129,224,134));
										continuer=false;
									}
									else{
										plateau.getPlateau()[l][ligneColonne.get(k).getCoordonneY()].setColorBackground(couleurCaseAMot);
									}
								}
							}
							else{
								for(int l = ligneColonne.get(k).getCoordonneY()+1;l <14&&continuer;l++){
									if(plateau.getPlateau()[ligneColonne.get(k).getCoordonneX()][l].getValeur()==' '&&placement[ligneColonne.get(k).getCoordonneX()][l]==null){
										plateau.getPlateau()[ligneColonne.get(k).getCoordonneX()][l].setColorBackground(new Color(129,224,134));
										continuer=false;
									}
									else{
										plateau.getPlateau()[ligneColonne.get(k).getCoordonneX()][l].setColorBackground(couleurCaseAMot);
									}
								}
								continuer=true;
								for(int l = ligneColonne.get(k).getCoordonneY()-1;l >=0&&continuer;l--){
									if(plateau.getPlateau()[ligneColonne.get(k).getCoordonneX()][l].getValeur()==' '&&placement[ligneColonne.get(k).getCoordonneX()][l]==null){
										plateau.getPlateau()[ligneColonne.get(k).getCoordonneX()][l].setColorBackground(new Color(129,224,134));
										continuer=false;
									}
									else{
										plateau.getPlateau()[ligneColonne.get(k).getCoordonneX()][l].setColorBackground(couleurCaseAMot);
									}
								}								
							}
						}
					}
				}
				plateau.getPlateau()[j][i].draw(g);

			}
		}
	//batiment.draw(10,container.getHeight()-batiment.getHeight()-100);
		for(int i = 0 ; i < lettreMain.size();i++){
			//Etat initial dans la main 
			if(lettreMain.get(i).getEtat()==0 ||lettreMain.get(i).getEtat()==3){
				lettreMain.get(i).setPosX(i%2*50+1075);
				lettreMain.get(i).setPosY(i/2*50+110);
				if(i==6){
					lettreMain.get(i).setPosX(1100);
					lettreMain.get(i).setPosY(260);
				
				}
				lettreMain.get(i).draw(g);
			}
			else if(lettreMain.get(i).getEtat()==1){
				lettreMouvement=i;
				lettreMain.get(i).setPosX(input.getMouseX());
				lettreMain.get(i).setPosY(input.getMouseY());
				
			}
			else if(lettreMain.get(i).getEtat()==2){
				System.out.println(lettreMain.get(i).getCoordonneSurPlateauX()*47+debutXPlateau);
				lettreMain.get(i).setPosX(lettreMain.get(i).getCoordonneSurPlateauX()*47+debutXPlateau);
				lettreMain.get(i).setPosY(lettreMain.get(i).getCoordonneSurPlateauY()*47+8);
				lettreMain.get(i).draw(g);
			}
			if(lettreMain.get(i).getEtat()==3){
				g.setColor(Color.red);
				g.drawRect(lettreMain.get(i).getPosX(), lettreMain.get(i).getPosY(), lettreMain.get(i).getTaille(), lettreMain.get(i).getTaille());
			}
		}
		if( lettreMouvement != -1)
			lettreMain.get(lettreMouvement).draw(g);





	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if(input.isKeyDown(Keyboard.KEY_S))
			System.exit(0);
		
	}
	@Override
	public void mousePressed(int button, int x, int y)
	{
		/*if(bouton1.onIt(input)){
			listFouet.get((int)(Math.random()*4)).play();
			continuerPluie=!continuerPluie;
		}*/
		if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
			if(boutonEchanger.onIt(input)){
				if(modeEchange==true){
					modeEchange=false;
					for(int i = 0 ; i < lettreMain.size();i++){
					
						lettreMain.get(i).setEtat(0);

					
					}
				}
				else{
					for(int i = 0 ; i < lettreMain.size();i++){
						
							lettreMain.get(i).setEtat(0);

					
					}
					modeEchange=true;
				}
			}
			if(lettreMouvement != -1){
				for(int i =0 ; i <15 ;i++){
					for(int j = 0 ; j< 15 ;j++){
						if(plateau.getPlateau()[j][i].onIt(input)&&plateau.getPlateau()[j][i].getColorBackground().equals(new Color(129,224,134))){
							lettreMain.get(lettreMouvement).setEtat(2);
							lettreMain.get(lettreMouvement).setCoordonneSurPlateauX(j);
							lettreMain.get(lettreMouvement).setCoordonneSurPlateauY(i);
						}
							

					}
				}
			}
			if(modeEchange==true){
				for(int i = 0 ; i < lettreMain.size();i++){
					if(lettreMain.get(i).onIt(input)){
						if(lettreMain.get(i).getEtat()==3)
							lettreMain.get(i).setEtat(0);
						else
							lettreMain.get(i).setEtat(3);
					}
				}
			}
			else{
				for(int i = 0 ; i < lettreMain.size();i++){
					if(lettreMain.get(i).onIt(input)){
						for(int j = 0 ; j < lettreMain.size();j++){
							
							if(lettreMain.get(j).getEtat() ==1)
								lettreMain.get(j).setEtat(0);
							
						}
						lettreMain.get(i).setEtat(1);

					}
				}
			}
		}
		else if(input.isMousePressed(input.MOUSE_RIGHT_BUTTON)){
			if(modeEchange==false){
				for(int i = 0 ; i < lettreMain.size();i++){
					if(lettreMain.get(i).getEtat()==1)
						lettreMain.get(i).setEtat(0);

				
				}
			}
		}
				//clique=!clique;

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void musicEnded(Music arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void musicSwapped(Music arg0, Music arg1) {
		// TODO Auto-generated method stub
		
	}
	public void setMain(ArrayList <Character> main){
		
		lettreMain.clear();
		addmain(main);
	}
	public void addmain(ArrayList <Character> main){
		for(int i = 0 ; i<main.size();i++)
			lettreMain.add(new CaseMobile(main.get(i)));
	}

}
