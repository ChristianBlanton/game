package gameObjects;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;

import audio.SoundClip;
import gameFunctions.GameContainer;
import gameFunctions.Input;
import gameFunctions.Renderer;


public class Choice extends GameObject{
private boolean hover;
private SoundClip clip;
private Input input;

	public Choice(int posX, int posY, int width, int height, String text)
	{
		this.posX=posX;
		this.posY=posY;
		this.width=width;
		this.height=height;
		this.tag="choice";
		this.text="Love me";
		hover=false;
		clip=new SoundClip("/click.wav");
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		if((gc.getInput().getMouseX()>=posX && gc.getInput().getMouseX()<=posX+width) && (gc.getInput().getMouseY()>=posY && gc.getInput().getMouseY()<=posY+height) 
				&& gc.getInput().isButton(MouseEvent.BUTTON1))
		{
			text="hate me";
			hover=true;
			clip.play();
		
			
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		r.draw2DString(text, posX+200, posY+200, 0);
		r.drawTextInBox(posX, posY, width, height, 0xffffffff, text);
		//r.noiseGen();
	}

}