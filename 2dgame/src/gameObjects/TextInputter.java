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
	private int maxLength;
	
	public TextInputter(int posX, int posY, int width, int height)
	{
		super(posX, posY, width, height);
	}
	
	public TextInputter(int posX, int posY, int width, int height, int maxLength)
	{
		super(posX, posY, width, height);
		this.maxLength=maxLength;
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
			else if(text.length()<=maxLength)
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
		else
			endAnim="";
		
		
		
			

		if (hover) {
			this.color1 = Pixel.RED;
		} else
			color1 = Pixel.GREEN;
	}

public void render(GameContainer gc, Renderer r) {
	// TODO Auto-generated method stub
	//r.draw2DString(text, posX+200, posY+200, 0);
	r.drawTextInBox(posX, posY, width, height, color1, text+endAnim);
	if(focused)
		r.drawRect(posX-1, posY-1, width+1, height+1, Pixel.WHITE);
	//r.noiseGen();
}
}
