package gameObjects;

import gameFunctions.GameContainer;
import gameFunctions.Renderer;

public class ChoiceScene extends Scene{
ButtonGroup buttG;
private int choiceOneScene,choiceThreeScene,choiceTwoScene;
private int chosenScene;
private boolean choiceMade;
	public int getChoiceOneScene() {
	return choiceOneScene;
}

public void setChoiceOneScene(int choiceOneScene) {
	this.choiceOneScene = choiceOneScene;
}

public int getChoiceThreeScene() {
	return choiceThreeScene;
}

public void setChoiceThreeScene(int choiceThreeScene) {
	this.choiceThreeScene = choiceThreeScene;
}

public int getChoiceTwoScene() {
	return choiceTwoScene;
}

public void setChoiceTwoScene(int choiceTwoScene) {
	this.choiceTwoScene = choiceTwoScene;
}

	public ChoiceScene(String text, Background bg, ButtonGroup buttG) {
		super(text, bg);
		this.buttG=buttG;
		// TODO Auto-generated constructor stub
	}
	
	public ChoiceScene(String text, Background bg, ButtonGroup buttG, int c1, int c2, int c3) {
		super(text, bg);
		this.buttG=buttG;
		choiceOneScene=c1;
		choiceTwoScene=c2;
		choiceThreeScene=c3;
		// TODO Auto-generated constructor stub
	}


	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		super.update(gc, dt);
		buttG.update(gc, dt);
		if(buttG.isClicked())
		{
			choiceMade=true;
			finished=true;
			int sc=buttG.getChoice();
			switch(sc) {
			case 1: setChosenScene(choiceOneScene); break;
			case 2: setChosenScene(choiceTwoScene); break;
			case 3: setChosenScene(choiceThreeScene); break;
			}
		}
			
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		super.render(gc, r);
	buttG.render(gc, r);
		
	}

	public boolean isChoiceMade() {
		return choiceMade;
	}

	public void setChoiceMade(boolean choiceMade) {
		this.choiceMade = choiceMade;
	}

	public int getChosenScene() {
		return chosenScene;
	}

	public void setChosenScene(int chosenScene) {
		this.chosenScene = chosenScene;
	}
}
