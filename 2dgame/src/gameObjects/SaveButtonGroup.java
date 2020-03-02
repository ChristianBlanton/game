package gameObjects;

import gameFunctions.GameContainer;
import gameFunctions.Renderer;

public class SaveButtonGroup extends ButtonGroup{
	public SaveButtonGroup(String b1, String b2, String b3, int i, int j, int k) {
		super(b1, b2, b3, i ,j,k );
		// TODO Auto-generated constructor stub
	}
	
	public SaveButtonGroup(String b1, String b2, String b3) {
		super(b1, b2, b3);
		// TODO Auto-generated constructor stub
	}
	
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		but1.update(gc, dt);
		but2.update(gc, dt);
		but3.update(gc, dt);
		if (but1.isClicked())
		{
			setChoice(0);
			clicked=true;
			but1.setClicked(false);
		}
		else if (but2.isClicked())
		{
			clicked=true;
			but2.setClicked(false);
			setChoice(1);
		}
		else if (but3.isClicked())
		{
			clicked=true;
			setChoice(2);
			but3.setClicked(false);
		}
			
	}

	
	public void render(GameContainer gc, Renderer r) {
			but1.render(gc, r);
			but2.render(gc, r);
			but3.render(gc, r);
			
	}

}
