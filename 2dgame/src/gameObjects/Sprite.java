package gameObjects;

import java.awt.event.MouseEvent;
import java.util.Random;

import gameFunctions.GameContainer;
import gameFunctions.Renderer;
import gfx.ImageTile;

public class Sprite extends GameObject{
	Random rand= new Random();
	ImageTile image;
	private float temp=0;
	private float speed;
	int frames;
	public Sprite(ImageTile image, int frames, float speed)
	{
		this.speed=speed;
		this.frames=frames;
		this.image=image;
	}
	
	
	public void update(GameContainer gc, float dt) {
				temp+=speed;
}
	
	public void render(GameContainer gc, Renderer r) {
		if(temp>frames)
		{
			temp=0;
			//System.out.println(temp);
		}
			
		r.drawImageTile(image, posX, posY, (int)temp, 0);
		//r.drawRect(posX, posY, width, height, Pixel.randColor());
		//r.noiseGen();
		//r.noiseFlicker();
	}
}
