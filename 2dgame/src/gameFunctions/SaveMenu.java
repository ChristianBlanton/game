package gameFunctions;

import java.awt.event.KeyEvent;
import java.io.File;

import gameObjects.ButtonGroup;
import gameObjects.SaveButtonGroup;

public class SaveMenu {
private SaveButtonGroup saveSlots;
private SaveButtonGroup loadSlots;
private boolean goToSettings;
private Save saveF;
private Window window;

public boolean isGoToSettings() {
	return goToSettings;
}
public void setGoToSettings(boolean goToSettings) {
	this.goToSettings = goToSettings;
}
SaveMenu(Window window)
{
	this.window=window;
	saveSlots=new SaveButtonGroup("Save 1", "Save 2", "Save 3");
	saveF= new Save(3);
	loadSlots=new SaveButtonGroup("Load 1", "Load 2", "Load 3", window.getSettings().getWidth()/2, window.getSettings().getWidth()/2, window.getSettings().getWidth()/2);
	saveF.load();
}
public void update(GameContainer gc, float dt) {
	// TODO Auto-generated method stub
	saveSlots.update(gc, dt);
	loadSlots.update(gc, dt);
	if(saveSlots.isClicked())
	{
	//System.out.println(saveSlots.getChoice());
	saveF.save(saveSlots.getChoice(), gc.getGameManager().game.getSceneNum());
	saveSlots.setClicked(false);
	}
	if(loadSlots.isClicked())
	{
		//load(loadSlots.getChoice()
		gc.getGameManager().game.setSceneNum(saveF.getSceneOfSlot(loadSlots.getChoice()));
		//System.out.println(GameManager.game.getSceneNum());
		//System.out.println(saveF.getSceneOfSlot(loadSlots.getChoice()));
		loadSlots.setClicked(false);
	}
	if (gc.getInput().isKeyUp(KeyEvent.VK_ESCAPE))
		setGoToSettings(true);
	}
public void render(GameContainer gc, Renderer r) {
	// TODO Auto-generated method stub
	saveSlots.render(gc, r);
	loadSlots.render(gc, r);
	//r.noiseShad();
	//r.noiseShear();
}
}
