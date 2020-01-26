package gameObjects;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;

import audio.SoundClip;
import gameFunctions.GameContainer;
import gameFunctions.Input;
import gameFunctions.Renderer;
import gfx.Pixel;


public class Choice extends GameObject{
private boolean hover;
private SoundClip clip;
private Input input;
private int color;

	public Choice(int posX, int posY, int width, int height, String text)
	{
		this.posX=posX;
		this.posY=posY;
		this.width=width;
		this.height=height;
		this.tag="choice";
		this.text="Love me";
		hover=false;
		color=Pixel.RED;
		clip=new SoundClip("/blip.wav");
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		hover=(gc.getInput().getMouseX()>=posX && gc.getInput().getMouseX()<=posX+width) && (gc.getInput().getMouseY()>=posY && gc.getInput().getMouseY()<=posY+height);
		if((hover && gc.getInput().isButtonDown(MouseEvent.BUTTON1)))
		{
			text="hate me";
			hover=true;
			clip.play();
		}
		if(hover)
			this.color=Pixel.GREEN;
		else
			color=Pixel.RED;
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		r.draw2DString(text, posX+200, posY+200, 0);
		r.drawTextInBox(posX, posY, width, height, color, text);
		//r.noiseGen();
	}

}
