package gameFunctions;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gameObjects.Background;
import gameObjects.Button;
import gameObjects.Dvd;
import gameObjects.GameObject;
import gameObjects.Scene;
import gameObjects.Sprite;
import gfx.Image;
import gfx.ImageTile;

//go from scene to scene
public class Game extends AbstractGame {
	private ArrayList<Scene> scenes = new ArrayList<Scene>();
	private boolean goToSettings = false;
	private int sceneNum = 0;

	/*
	 * image=new ImageTile("/dvdsprites.png",100,100); ImageTile ryu=new
	 * ImageTile("/ryusprite.png", 67,104); sprite=new Sprite(ryu); scenes.add(new
	 * Button(0,-(p.getHeight()/6-p.getHeight()),p.getWidth(), p.getHeight(),
	 * "I don't know Sheen, I feel as if there's no one in the world for me... Don't worry Carl, I believe you can do it, there is someone there for you."
	 * )); scenes.add(new Dvd(200,200,image)); scenes.add(new Dvd(200,200,image));
	 * 
	 */

	public Game() {
		// load all scenes add to scene list
		Image image = new Image("/eltonjohn.png");
		Image bball = new Image("/bball.jpg");
		Background bg = new Background(image);
		scenes.add(new Scene(
				"Welcome to adventure call! My name is Falcoonhoof, and I will be your guide on your quest. Greetings traveler, what is your name?",
				bg));
		scenes.add(new Scene(
				"There's antimony, arsenic, aluminum, selenium, and hydrogen and hydrogren and oxygen and nitrogen and reinium and nickel, neodynium, neptunium, uranium",
				bg));
		scenes.add(new Scene("CREEPER, AW MAN", new Background(bball)));
		scenes.add(new Scene(
				"Once upon a midnight dreary, while I pondered weak and weary, over many a quaint and curious volume of forgotten lore",
				bg));

	}

	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		if (sceneNum >= scenes.size() || sceneNum < 0) {

		} else {
			scenes.get(sceneNum).update(gc, dt);
		}
		
			if (scenes.get(sceneNum).isFinished()) {
				sceneNum++;
				scenes.get(sceneNum).setFinished(false);
			}

		

		/*
		 * if (scenes.get(i).isDead()) { scenes.remove(i); i--;
		 */
		if (gc.getInput().isKeyUp(KeyEvent.VK_ESCAPE))
			setGoToSettings(true);
		if (gc.getInput().isKeyUp(KeyEvent.VK_SPACE) || gc.getInput().isKeyUp(KeyEvent.VK_RIGHT))
			sceneNum++;
		if (gc.getInput().isKeyUp(KeyEvent.VK_LEFT))
			sceneNum--;
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		
		if (sceneNum >= scenes.size() || sceneNum < 0) {

		} else
			scenes.get(sceneNum).render(gc, r);
		r.sideNoise(990);
		r.noiseShad();
		r.noiseFlicker();
		

		/*
		 * for (Scene obj : scenes) { obj.render(gc, r);
		 */

	}

	public boolean isGoToSettings() {
		return goToSettings;
	}

	public void setGoToSettings(boolean goToSettings) {
		this.goToSettings = goToSettings;
	}
}
//test
