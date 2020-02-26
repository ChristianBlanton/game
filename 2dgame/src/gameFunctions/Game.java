package gameFunctions;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gameObjects.Background;
import gameObjects.Button;
import gameObjects.Dvd;
import gameObjects.GameObject;
import gameObjects.Scene;
import gameObjects.Sprite;
import gameObjects.TextInputter;
import gfx.Image;
import gfx.ImageTile;

//go from scene to scene
public class Game extends AbstractGame {
	private ArrayList<Scene> scenes = new ArrayList<Scene>();
	private boolean goToSettings = false;
	private int sceneNum = 0;
	private float row = 0;
private String name;
private String []sceneText;
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
		Image bball = new Image("/city.gif");
		
		sceneText=TextLoader.load("/scenetxt.txt").split("///");
		
		Background bg = new Background(image);
		TextInputter tI = new TextInputter(400, 400, 200, 100);
		//todo refactor
		scenes.add(new Scene(sceneText[0],bg, tI));
		scenes.add(new Scene(sceneText[1],bg));
		scenes.add(new Scene(sceneText[2], new Background(image), new Sprite(new ImageTile("/newcityspr.png", 1280, 1020),2,0.01f)));
		scenes.add(new Scene(sceneText[2],bg));

	}

	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		
		name=scenes.get(0).gettI().getText();
		
		if (row + 1.5 < gc.getWindow().getSettings().getHeight())
			row += 1.5;
		else
			row = 0;
		if (sceneNum >= scenes.size() || sceneNum < 0) {

		} else {
			scenes.get(sceneNum).update(gc, dt);
			if(name!=null)
			scenes.get(sceneNum).getDialog().replaceName(name);
			
		}

		if (scenes.get(sceneNum).isFinished() && sceneNum + 1 < scenes.size()) {
			sceneNum++;
			scenes.get(sceneNum).setFinished(false);
		}

		/*
		 * if (scenes.get(i).isDead()) { scenes.remove(i); i--;
		 */
		if (gc.getInput().isKeyUp(KeyEvent.VK_ESCAPE))
			setGoToSettings(true);
		//gc.getInput().isKeyUp(KeyEvent.VK_SPACE)||
		
		if (gc.getInput().isKeyUp(KeyEvent.VK_RIGHT) && (sceneNum + 1) < scenes.size())
			sceneNum++;
		if (gc.getInput().isKeyUp(KeyEvent.VK_LEFT) && sceneNum != 0) {
			scenes.get(sceneNum - 1).setFinished(false);
			sceneNum--;
		}

	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub

		if (sceneNum >= scenes.size() || sceneNum < 0) {

		} else
			scenes.get(sceneNum).render(gc, r);
		// r.sideNoise(990);
		r.sideNoiseScroll((int) row);
		// r.sideNoiseScroll((int)row+1);
		// r.noiseShearScroll((int)row);
		// r.sideNoise();
		// r.noiseShad();
		// r.sideNoiseScrollWhite((int)row);
		// r.noiseFlicker();
		// r.noiseShear();

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
