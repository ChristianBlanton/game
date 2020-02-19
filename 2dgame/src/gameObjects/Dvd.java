package gameObjects;

import java.util.Random;

import audio.SoundClip;
import gameFunctions.GameContainer;
import gameFunctions.Renderer;
import gfx.ImageTile;
import gfx.Pixel;

public class Dvd extends GameObject{
	private SoundClip clip;
	private Random rand=new Random();
	private ImageTile image;
	private int dy=1, dx=1;
	private int temp=0;
	
	public Dvd(int posX, int posY, ImageTile image)
	{
		this.posX=posX;
		this.posY=posY;
		this.image=image;
		
		this.width=image.getTileW();
		this.height=image.getHeight();
		clip=new SoundClip("/blip.wav");
		
	}
	public void update(GameContainer gc, float dt) {
	clip.setVolume(gc.getWindow().getSettings().getMusicVol());
		if(temp>5)
			temp=0;
	if(posY+height>gc.getWindow().getSettings().getHeight())
	{
		dy=-rand.nextInt(3);
		clip.play();
		temp+=1;
	}	
	else if(posY<=0)
	{
		dy=rand.nextInt(3);
		clip.play();
		temp+=1;
	}
	if(posX+width>gc.getWindow().getSettings().getWidth())
	{
		dx=-rand.nextInt(3);
		clip.play();
		temp+=1;
	}
	else if(posX<=0)
	{
		dx=rand.nextInt(3);
		clip.play();
		temp+=1;
	}
	if(posY+dy>gc.getWindow().getSettings().getHeight())
		dy=1;
	else if(posY+dy<0)
		dy=-1;
	if(posX+dx>gc.getWindow().getSettings().getWidth())
		dx=1;
	else if(posX+dx<0)
		dx=-1;
		posY+=dy;
		posX+=dx;
}
	
	public void render(GameContainer gc, Renderer r) {
		if(temp>6)
			temp=6;
		r.drawImageTile(image, posX, posY, temp, 0);
		//r.drawRect(posX, posY, width, height, Pixel.randColor());
		//r.noiseGen();
		//r.noiseFlicker();
	}
	

}
