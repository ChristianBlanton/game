package gameObjects;

import java.awt.event.MouseEvent;

import gameFunctions.GameContainer;
import gameFunctions.PixSettings;
import gameFunctions.Renderer;
import gfx.Pixel;


public class DialogBox extends Button{
	private static PixSettings p = new PixSettings();
	private float speed;
	private int len;
	private float count;
	private String animText;
	public DialogBox(String text, float speed)
	{
		super(0,-(p.getHeight()/6-p.getHeight()), p.getWidth(), p.getHeight());
		this.speed=speed;
		len=text.length();
		this.text="";
		count=0;
		animText=text;
	}
	
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		speed=gc.getWindow().getSettings().getTxtSpd();
		clip.setVolume(gc.getWindow().getSettings().getSfxVol());
		hover = (gc.getInput().getMouseX() >= posX && gc.getInput().getMouseX() <= posX + width)
				&& (gc.getInput().getMouseY() >= posY && gc.getInput().getMouseY() <= posY + height);
		if ((hover && gc.getInput().isButtonUp(MouseEvent.BUTTON1))) {
			setClicked(true);
			clip.play();
		}

		if (hover) {
			this.color = Pixel.MAGENTA + 1;
		} else
			color = Pixel.GREEN;
		
		if(count<len)
		{
			if(count+speed>len)
			speed=1;
			count+=speed;
			text=""+animText.substring(0,(int)count);
		}
		
	
	
	}
	
	
	
	

}
