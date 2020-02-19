package gameObjects;

import gameFunctions.AbstractGame;
import gameFunctions.GameContainer;
import gameFunctions.Renderer;
import gfx.Image;
import gfx.ImageTile;

public class Scene extends AbstractGame{
	private Image image;
	private Sprite sprite;
	private DialogBox text;
	private Background bg;
	private boolean finished;

	public Scene(String text, Background bg)
	{
		this.text=new DialogBox(text,0.15f);
		this.bg =bg;
		setFinished(false);
	}
	
	public Scene(String text, Background bg, Sprite sprite)
	{
		this.text=new DialogBox(text,0.25f);
		this.bg =bg;
		this.sprite=sprite;
		setFinished(false);
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		/*
		 * for (int i = 0; i < scenes.size(); i++) {
			scenes.get(i).update(gc, dt);
			
			 * if (scenes.get(i).isDead()) {
			 * scenes.remove(i);
				i--;
		 */
		bg.update(gc, dt);
		text.update(gc, dt);
		if(sprite!=null)
			sprite.update(gc, dt);
		if(text.isClicked())
			setFinished(true);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		if(sprite!=null)
		sprite.render(gc, r);
		bg.render(gc, r);
		text.render(gc, r);
	
		
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean nextScene) {
		this.finished = nextScene;
	}

}
