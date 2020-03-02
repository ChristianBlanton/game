package gameFunctions;

import java.util.ArrayList;

import audio.SoundClip;
import gameFunctions.GameManager.GameState;
import gameObjects.Background;
import gameObjects.Button;
import gameObjects.GameObject;
import gfx.ImageTile;
import gfx.Pixel;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
public class MainMenu extends AbstractGame {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private ImageTile image;
	private SoundClip clip;
	private Background bg;
	private ImageTile dvd;
	private Button play;
	private Button settings;
	private float row=0;
	private float row2=0;
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

	public MainMenu(Window window) {
		play = new Button(window.getSettings().getWidth() / 4, window.getSettings().getHeight() / 4, 200, 100, "PLAY", 0xFF5D6D7E, 0xFF080611);
		objects.add(play);
		settings = new Button(window.getSettings().getWidth() / 4, window.getSettings().getHeight() / 2, 200, 100,"SETTINGS", 0xFF5D6D7E, 0x805D6D7E);
		objects.add(settings);
		clip = new SoundClip("/song.wav");
		clip.setVolume(-50);
		// clip.loop();
		goToPlay = false;
		goToSettings = false;
	}

	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		if (row + 1.5 < gc.getWindow().getSettings().getHeight())
			row += 1.5;
		else
			row = 0;
		if (row2 + 0.5 < gc.getWindow().getSettings().getHeight())
			row2 += 0.5;
		else
			row2 = 0;
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
			} else {
				//goToPlay = false;
			}

			if (settings.isClicked())
			{
				goToSettings = true;
				settings.setClicked(false);
			}
			else {
				//goToSettings = false;
			}

		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		
		r.drawFillRect(gc.getWindow().getSettings().getWidth()/8, 0, (int)(gc.getWindow().getSettings().getWidth()*0.75), gc.getWindow().getSettings().getHeight(), Pixel.BLUE);
		for (GameObject obj : objects) {
			obj.render(gc, r);
		}
		r.sideNoise(800, (int)row);
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");  
		   LocalDateTime now = LocalDateTime.now();  
		   r.draw2DString(dtf.format(now), (int)(gc.getWindow().getSettings().getWidth()*0.60),(int)(gc.getWindow().getSettings().getHeight()/4), 2);  
		   r.noiseShad();
		   r.sideNoiseScroll((int)row2, 5);
		   r.sideNoiseScrollWhite(gc.getWindow().getSettings().getHeight()-1);
		  
		// r.noiseShear();
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
