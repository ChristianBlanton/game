package gameFunctions;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gameObjects.Button;
import gameObjects.Dvd;
import gameObjects.GameObject;
import gfx.ImageTile;

public class Game extends AbstractGame {
	private ImageTile image;
	private Button play;
	private PixSettings p=new PixSettings();
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private boolean goToSettings=false;

	
	public Game()
	{
		image=new ImageTile("/dvdsprites.png",100,100);
		
		objects.add(new Button(0,-(p.getHeight()/7-p.getHeight()),p.getWidth(), p.getHeight(), "You're watching Adventure Call. My name is Falconhoof, and I will be your guide on your quest. Greetings, traveler, what is your name?"));
		objects.add(new Dvd(200,200,image));
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).update(gc, dt);
			if (objects.get(i).isDead()) {
				objects.remove(i);
				i--;
			}
			if(gc.getInput().isKeyUp(KeyEvent.VK_ESCAPE))
			{
				setGoToSettings(true);
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		for (GameObject obj : objects) {
			obj.render(gc, r);
	}

}

	public boolean isGoToSettings() {
		return goToSettings;
	}

	public void setGoToSettings(boolean goToSettings) {
		this.goToSettings = goToSettings;
	}}
