package gameFunctions;

import java.util.ArrayList;

import gameObjects.Button;
import gameObjects.GameObject;

public class SettingsMenu extends AbstractGame {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private Button back;
	private Button settings;
	private Button volume;
	private boolean goBack = false;

	public void setGoBack(boolean goBack) {
		this.goBack = goBack;
	}

	public SettingsMenu() {
		back = new Button(500, 500, 40, 40, "<");
		objects.add(back);
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
			if (back.isClicked()) {
				goBack = true;
				back.setClicked(false);
				//destroy();
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

	public void destroy() {
		for (int i = 0; i < objects.size(); i++) {
			objects.remove(i);
			i--;
		}

	}

	public boolean isGoBack() {
		return goBack;
	}

}
