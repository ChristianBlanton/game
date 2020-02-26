package gameFunctions;

import java.util.ArrayList;

import audio.SoundClip;
import gameFunctions.GameManager.GameState;
import gameObjects.Background;
import gameObjects.Button;
import gameObjects.GameObject;
import gfx.ImageTile;

public class MainMenu extends AbstractGame {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private ImageTile image;
	private SoundClip clip;
	private Background bg;
	private ImageTile dvd;
	private Button play;
	private Button settings;
	private boolean goToPlay = false;
	public void setGoToPlay(boolean goToPlay) {
		this.goToPlay = goToPlay;
	}

	private boolean goToSettings = false;

	public void setGoToSettings(boolean goToSettings) {
		this.goToSettings = goToSettings;
	}

	public boolean isGoToSettings() {
		return goToSettings;
	}

	public MainMenu() {
		play = new Button(200, 200, 200, 200, "PLAY");
		objects.add(play);
		settings = new Button(200, 500, 200, 200, "SETTINGS");
		objects.add(settings);
		clip = new SoundClip("/song.wav");
		clip.setVolume(-50);
		//clip.loop();
		goToPlay=false;
		goToSettings=false;
	}

	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		clip.setVolume(gc.getWindow().getSettings().getMusicVol());
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).update(gc, dt);
			if (objects.get(i).isDead()) {
				objects.remove(i);
				i--;
			}

			if (play.isClicked()) {
				goToPlay = true;
				play.setClicked(false);
				//destroy();
			} else if (settings.isClicked()) {
				goToSettings = true;
				settings.setClicked(false);
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
		r.sideNoise(990);
		r.noiseShad();
	}

	public void destroy() {
		clip.close();
		for (int i = 0; i < objects.size(); i++) {
			objects.remove(i);
			i--;
		}

	}

	public boolean isGoToPlay() {
		return goToPlay;
	}

}
