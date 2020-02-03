package gameObjects;

import java.util.Random;

import gameFunctions.GameContainer;
import gameFunctions.Renderer;
import gfx.ImageTile;

public class Sprite extends GameObject{
	Random rand= new Random();
	ImageTile image;
	private int temp=1;
	private int dy=1; 
	private int dx=1;
	public Sprite(ImageTile image)
	{
		this.image=image;
	}
	
	
	public void update(GameContainer gc, float dt) {
		temp++;
		
}
	
	public void render(GameContainer gc, Renderer r) {
		if(temp>4)
			temp=0;
		r.drawImageTile(image, posX, posY, temp, 0);
		//r.drawRect(posX, posY, width, height, Pixel.randColor());
		r.noiseGen();
		//r.noiseFlicker();
	}
}
