package gameFunctions;

//import game.GameManager;

public abstract class GameState {
	//protected GameManager gm;
	
	public abstract void start(Renderer r);
	public abstract void update();
	
	
}
