package gameObjects;

import java.awt.event.MouseEvent;
import java.util.Random;

import gameFunctions.GameContainer;
import gameFunctions.Renderer;
import gfx.ImageTile;

public class Sprite extends GameObject{
	Random rand= new Random();
	ImageTile image;
<<<<<<< HEAD
	private int temp=1;
	private int dy=1; 
	private int dx=1;
	public Sprite(ImageTile image)
=======
	private float temp=0;
	private float speed;
	int frames;
	public Sprite(ImageTile image, int frames, float speed)
>>>>>>> branch 'master' of https://github.com/ChristianBlanton/game.git
	{
		this.speed=speed;
		this.frames=frames;
		this.image=image;
	}
	
	
	public void update(GameContainer gc, float dt) {
<<<<<<< HEAD
		temp++;
		
=======
				temp+=speed;
>>>>>>> branch 'master' of https://github.com/ChristianBlanton/game.git
}
	
	public void render(GameContainer gc, Renderer r) {
		if(temp>frames)
		{
			temp=0;
			//System.out.println(temp);
		}
			
		r.drawImageTile(image, posX, posY, (int)temp, 0);
		//r.drawRect(posX, posY, width, height, Pixel.randColor());
		r.noiseGen();
		//r.noiseFlicker();
	}
}
