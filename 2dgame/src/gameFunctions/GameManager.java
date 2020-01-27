package gameFunctions;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import audio.SoundClip;
import gameObjects.Button;
import gameObjects.Dvd;
import gameObjects.GameObject;
import gfx.Image;
import gfx.ImageTile;

public class GameManager extends AbstractGame {

	public GameManager() {
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

	}

	public static enum GameState {
		MAINMENU, GAME, SETTINGS
	}

	public static GameState gameState = GameState.MAINMENU;

	public static GameState getGameState() {
		return gameState;
	}

	public static void setGameState(GameState gameState) {
		GameManager.gameState = gameState;
	}

	public static MainMenu mainMenu;
	public static Game game = new Game();
	public static SettingsMenu Settings = new SettingsMenu();
	private GameState lastGameState;

	public void update(GameContainer gc, float dt) {
		switch (gameState) {
		case MAINMENU:
			if (mainMenu == null)
				mainMenu = new MainMenu();
			mainMenu.update(gc, dt);
			if (mainMenu.isGoToPlay()) {
				setGameState(GameState.GAME);
				mainMenu.setGoToPlay(false);
			}

			else if (mainMenu.isGoToSettings()) {
				setGameState(GameState.SETTINGS);
				mainMenu.setGoToSettings(false);
			}
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
			break;
		}

		// System.out.println(gameState);

	}

	public void render(GameContainer gc, Renderer r) {
		switch (gameState) {
		case MAINMENU:
			mainMenu.render(gc, r);
			break;
		case GAME:
			game.render(gc, r);
			break;
		case SETTINGS:
			Settings.render(gc, r);
			break;
		}
		r.noiseFlicker();

		// r.drawImageTile(image, 0, 0, (int)temp, 0);
		/*
		 * r.drawImage(bg, 0, 0); r.drawFillRect(32, 32, 200, 200, 0xffffccff);
		 * r.drawImage(player, gc.getInput().getMouseX()-8,
		 * gc.getInput().getMouseY()-8); r.drawImageTile(image,
		 * gc.getInput().getMouseX()-8, gc.getInput().getMouseY()-8, (int)temp, 0);
		 */
	}
}