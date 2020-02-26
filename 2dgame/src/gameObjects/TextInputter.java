package gameObjects;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


import gameFunctions.GameContainer;
import gameFunctions.Renderer;
import gfx.Pixel;

public class TextInputter extends Button {
	private boolean focused=true;
	private int cdTime=50;
	private String endAnim="";
	
	public TextInputter(int posX, int posY, int width, int height)
	{
		super(posX, posY, width, height);
	}
	
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		if(cdTime>0)
			cdTime--;
		clip.setVolume(gc.getWindow().getSettings().getSfxVol());
		hover = (gc.getInput().getMouseX() >= posX && gc.getInput().getMouseX() <= posX + width)
				&& (gc.getInput().getMouseY() >= posY && gc.getInput().getMouseY() <= posY + height);
		if ((hover && gc.getInput().isButtonUp(MouseEvent.BUTTON1))) {
			setClicked(true);
			clip.play();
			if (!focused)
			{
				focused=true;
			}
		}
		
		if(gc.getInput().isButtonUp(MouseEvent.BUTTON1)&&!hover)
		focused=false;
			
		if(focused&&gc.getInput().isKeyDown(gc.getInput().getLastCharCodePressed()))
		{
			if(gc.getInput().getLastCharCodePressed()==KeyEvent.VK_ENTER)
				focused=false;
			if((gc.getInput().getLastCharCodePressed()==KeyEvent.VK_BACK_SPACE||gc.getInput().getLastCharCodePressed()==KeyEvent.VK_DELETE)&&text.length()>0)
				text=text.substring(0, text.length()-1);
			else
			text+=gc.getInput().getLastCharPressed();
			
		}
		
		if(focused)
		{
			if(cdTime<=0)
			{
				cdTime=50;
				if(endAnim.length()>0)
					endAnim="";
				else
				endAnim="|";
			}
			
		}
		
		
		
		
			

		if (hover) {
			this.color = Pixel.RED;
		} else
			color = Pixel.GREEN;
	}

public void render(GameContainer gc, Renderer r) {
	// TODO Auto-generated method stub
	//r.draw2DString(text, posX+200, posY+200, 0);
	r.drawTextInBox(posX, posY, width, height, color, text+endAnim);
	//r.noiseGen();
}
}
