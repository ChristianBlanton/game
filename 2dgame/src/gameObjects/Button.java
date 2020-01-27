package gameObjects;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.sound.sampled.Clip;

import audio.SoundClip;
import gameFunctions.GameContainer;
import gameFunctions.Input;
import gameFunctions.Renderer;
import gfx.Pixel;


public class Button extends GameObject{
private boolean hover;
private SoundClip clip;
private int color;
private boolean clicked;


	public Button(int posX, int posY, int width, int height, String text)
	{
		this.posX=posX;
		this.posY=posY;
		this.width=width;
		this.height=height;
		this.tag="choice";
		this.text=text;
		hover=false;
		setClicked(false);
		color=Pixel.MAGENTA;
		clip=new SoundClip("/blip.wav");
		clip.setVolume(-50);
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		hover=(gc.getInput().getMouseX()>=posX && gc.getInput().getMouseX()<=posX+width) && (gc.getInput().getMouseY()>=posY && gc.getInput().getMouseY()<=posY+height);
		if((hover && gc.getInput().isButtonUp(MouseEvent.BUTTON1)))
		{
			setClicked(true);
			clip.play();
		}
		
		if(hover)
		{
			this.color=Pixel.MAGENTA+1;
		}
		else
			color=Pixel.GREEN;
			}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		//r.draw2DString(text, posX+200, posY+200, 0);
		r.drawTextInBox(posX, posY, width, height, color, text);
		//r.noiseGen();
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

}
