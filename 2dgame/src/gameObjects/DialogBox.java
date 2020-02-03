package gameObjects;

import gameFunctions.GameContainer;
import gameFunctions.PixSettings;
import gameFunctions.Renderer;
import gfx.Pixel;


public class DialogBox extends Button{
	private static PixSettings p = new PixSettings();
	public DialogBox(String text)
	{
		super(0,-(p.getHeight()/6-p.getHeight()), p.getWidth(), p.getHeight(),text);
	}
	
	
	
	

}
