package gameFunctions;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import audio.SoundClip;
import gameObjects.Button;
import gameObjects.Dvd;
import gameObjects.GameObject;
import gameObjects.Intro;
import gfx.Image;
import gfx.ImageTile;

public class GameManager {
	private Window window;
	public static Intro intro = new Intro("VIBES");
	private SaveMenu Save;
	private SettingsMenu Settings;
	private GameState lastGameState;
	public Game game;
	private SoundClip clip;
public MainMenu mainMenu;

	public GameManager(GameContainer gc) {
		/*
		 * bg=new Image("/old.png"); //objects.add(new Choice(20,20,100,100 ,"hoss"));
		 * //objects.add(new Choice(400,400,200,200, "hoss2")); //objects.add(new
		 * Background(bg)); //image = new ImageTile("/noisesprite.png", 1000, 1000); dvd
		 * = new ImageTile("/dvdsprites.png",100,100); objects.add(new
		 * Choice(250,250,100,100, "welcome to hell" )); objects.add(new
		 * Dvd(200,200,dvd)); objects.add(new Dvd(200,200,dvd)); objects.add(new
		 * Dvd(200,200,dvd)); objects.add(new Dvd(100,100,dvd)); image = new
		 * ImageTile("/noisesprite.png", 1000, 1000);
		 */
		window = gc.getWindow();
		Save = new SaveMenu(window);
	}

	public GameManager(Window window) {

		this.window = window;
		Save = new SaveMenu(window);
		game = new Game(window);
		mainMenu=new MainMenu(window);
		Settings= new SettingsMenu(window);
		clip=new SoundClip("/static.wav");
		clip.setVolume(window.getSettings().getMusicVol());
	}

	public static enum GameState {
		MAINMENU, GAME, SETTINGS, INTRO, SAVE
	}

	public static GameState gameState = GameState.INTRO;

	public static GameState getGameState() {
		return gameState;
	}

	public static void setGameState(GameState gameState) {
		GameManager.gameState = gameState;
	}

	

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void update(GameContainer gc, float dt) {
		switch (gameState) {
		case MAINMENU:
			mainMenu.update(gc, dt);
			if (mainMenu.isGoToPlay()) {
				setGameState(GameState.GAME);
				mainMenu.setGoToPlay(false);
				clip.close();
			}
			else if (mainMenu.isGoToSettings()) {
				setGameState(GameState.SETTINGS);
				mainMenu.setGoToSettings(false);
			}
			clip.play();
			break;
		case GAME:
			game.update(gc, dt);
			if (game.isGoToSettings()) {
				setGameState(GameState.SETTINGS);
				game.setGoToSettings(false);
				
			}

			break;
		case SETTINGS:
			Settings.update(gc, dt);
			if (Settings.isGoBack()) {
				Settings.setGoBack(false);
				setGameState(GameState.MAINMENU);
			}
			if (Settings.isGoSave()) {
				Settings.setGoSave(false);
				setGameState(GameState.SAVE);
			}
			break;
		case INTRO:
			intro.update(gc, dt);
			if (intro.isFinished())
				gameState = GameState.MAINMENU;
			if(!clip.isRunning());
			clip.play();
			break;
		case SAVE: {
			Save.update(gc, dt);
			if (Save.isGoToSettings()) {
				Save.setGoToSettings(false);
				gameState = GameState.SETTINGS;
			}
			break;

		}

		}

		// System.out.println(gameState);

	}

	public void render(GameContainer gc, Renderer r) {
		// r.sideNoise();
		// r.noiseGen();
		switch (gameState) {
		case INTRO:
			intro.render(gc, r);
			break;
		case MAINMENU:
			mainMenu.render(gc, r);
			break;
		case GAME:
			game.render(gc, r);
			break;
		case SETTINGS:
			Settings.render(gc, r);
			break;
		case SAVE:
			Save.render(gc, r);
			break;
		}
		// r.noiseFlicker();

		// r.drawImageTile(image, 0, 0, (int)temp, 0);
		/*
		 * r.drawImage(bg, 0, 0); r.drawFillRect(32, 32, 200, 200, 0xffffccff);
		 * r.drawImage(player, gc.getInput().getMouseX()-8,
		 * gc.getInput().getMouseY()-8); r.drawImageTile(image,
		 * gc.getInput().getMouseX()-8, gc.getInput().getMouseY()-8, (int)temp, 0);
		 */
	}
}