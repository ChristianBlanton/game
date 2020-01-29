package gameFunctions;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gameObjects.Button;
import gameObjects.Dvd;
import gameObjects.GameObject;
import gameObjects.Sprite;
import gfx.ImageTile;

public class Game extends AbstractGame {
	private ImageTile image;
	private Sprite sprite;
	private Button play;
	private PixSettings p=new PixSettings();
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private boolean goToSettings=false;

	
	public Game()
	{
		image=new ImageTile("/dvdsprites.png",100,100);
		ImageTile ryu=new ImageTile("/ryusprite.png", 67,104);
		sprite=new Sprite(ryu);
		objects.add(new Button(0,-(p.getHeight()/6-p.getHeight()),p.getWidth(), p.getHeight(), "I don't know Sheen, I feel as if there's no one in the world for me... Don't worry Carl, I believe you can do it, there is someone there for you."));
		objects.add(new Dvd(200,200,image));
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
//test
