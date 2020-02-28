package gameObjects;

import gameFunctions.AbstractGame;
import gameFunctions.GameContainer;
import gameFunctions.Renderer;
import gfx.Image;
import gfx.ImageTile;

public class Scene extends AbstractGame{
	private Image image;
	protected Sprite sprite;
	protected DialogBox dialog;
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public DialogBox getDialog() {
		return dialog;
	}

	public void setDialog(DialogBox dialog) {
		this.dialog = dialog;
	}

	public Background getBg() {
		return bg;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

	protected Background bg;
	protected TextInputter tI;
	public TextInputter gettI() {
		return tI;
	}

	public void settI(TextInputter tI) {
		this.tI = tI;
	}

	protected boolean finished;

	public Scene(String text, Background bg)
	{
		this.dialog=new DialogBox(text,0.15f);
		this.bg =bg;
		setFinished(false);
	}
	
	public Scene(String text, Background bg, TextInputter tI)
	{
		this.dialog=new DialogBox(text,0.15f);
		this.bg =bg;
		setFinished(false);
		this.tI=tI;
	}
	
	public Scene(String text, Background bg, Sprite sprite)
	{
		this.dialog=new DialogBox(text,0.25f);
		this.bg =bg;
		this.sprite=sprite;
		setFinished(false);
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		bg.update(gc, dt);
		dialog.update(gc, dt);
		if(sprite!=null)
			sprite.update(gc, dt);
		if(tI!=null)
			tI.update(gc, dt);
		if(dialog.isFinished())
			setFinished(true);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		if(sprite!=null)
		sprite.render(gc, r);
		bg.render(gc, r);
		dialog.render(gc, r);
		if(tI!=null)
		tI.render(gc, r);
	
		
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean nextScene) {
		this.finished = nextScene;
	}

}
