import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;





public class App extends StateBasedGame{

	private AppGameContainer container; // le conteneur du jeu
	public static final int MENU = 0;
	public static final int JEUX =1;
	
	public App() {
		super("Scrabble");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		if (container instanceof AppGameContainer) {
			this.container = (AppGameContainer) container; // on stocke le
															// conteneur du jeu
															// ! }
			this.addState(new FenetreScrabble(MENU));
			//jeu = new Menu(MENU); // le jeu en lui-même !
			container.setShowFPS(true); // on ne veut pas voir le FPS ?? mettre
										// alors "false" ! addState(jeu); //on
										// ajoute le GameState au conteneur !
		}
	}
	public static void main(String[] args) {
		
		try {
			AppGameContainer container = new AppGameContainer(new App());
			container.setDisplayMode(1280, 720, false); // fenêtre de 1280*768
														// fullscreen =false !!
			container.setTargetFrameRate(60); // on règle le FrameRate
			container.start(); // on démarre le container
		} catch (SlickException e) {
			e.printStackTrace();
		}

}}
