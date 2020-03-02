package gameObjects;

import java.awt.event.MouseEvent;

import gameFunctions.GameContainer;
import gameFunctions.Renderer;
import gfx.Pixel;

public class ButtonGroup {
	Button but1, but2, but3;
	protected boolean clicked;
	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	private int choice;

	public ButtonGroup(Button b1, Button b2, Button b3) {
		but1 = b1;
		but2 = b2;
		but3 = b3;
	}

	public ButtonGroup(String b1, String b2, String b3) {
		// todo change values to window values
		but1 = new Button(440, 10, 200, 50, b1);
		but2 = new Button(440, 160, 200, 50, b2);
		but3 = new Button(440, 310, 200, 50, b3);
	}
	
	public ButtonGroup(String b1, String b2, String b3, int posX1, int posX2, int posX3) {
		// todo change values to window values
		but1 = new Button(posX1, 10, 200, 50, b1);
		but2 = new Button(posX2, 160, 200, 50, b2);
		but3 = new Button(posX3, 310, 200, 50, b3);
	}

	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		but1.update(gc, dt);
		but2.update(gc, dt);
		but3.update(gc, dt);
		if (but1.isClicked())
			setChoice(1);
		else if (but2.isClicked())
			setChoice(2);
		else if (but3.isClicked())
			setChoice(3);
	}

	public void render(GameContainer gc, Renderer r) {
		if (!(but1.isClicked() || but2.isClicked() || but3.isClicked())) {
			but1.render(gc, r);
			but2.render(gc, r);
			but3.render(gc, r);
		}
		else
			clicked=true;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

}
