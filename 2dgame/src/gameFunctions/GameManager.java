package gameFunctions;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import audio.SoundClip;
import gameObjects.Choice;
import gameObjects.Dvd;
import gameObjects.GameObject;
import gfx.Image;
import gfx.ImageTile;

public class GameManager extends AbstractGame{

	private ArrayList<GameObject> objects=new ArrayList<GameObject>();
	
	private ImageTile image;
	private SoundClip clip;
	private Image bg;
	private ImageTile dvd;
	
	public GameManager()
	{
		bg=new Image("/old.png");
	//objects.add(new Choice(20,20,100,100 ,"hoss"));
	//objects.add(new Choice(400,400,200,200, "hoss2"));
	//objects.add(new Background(bg));
	//image = new ImageTile("/noisesprite.png", 1000, 1000);
		dvd = new ImageTile("/dvdsprites.png",100,100);
		objects.add(new Dvd(200,200,dvd));
	image = new ImageTile("/noisesprite.png", 1000, 1000);

	}
	public void update(GameContainer gc, float dt) {
		for(int i=0; i<objects.size(); i++)
		{
			objects.get(i).update(gc, dt);
			if(objects.get(i).isDead())
			{
				objects.remove(i);
				i--;
			}
		}
		
		
		
		/*
		 * 	if(gc.getInput().isKeyDown(KeyEvent.VK_A))
		{
		
		clip.play();
		}*/
		temp+=dt*2;
		
		if(temp>3)
			temp=0;
		 
	
	}

	float temp=0;
	
	public void render(GameContainer gc, Renderer r) {
	
		for(GameObject obj: objects)
		{
			obj.render(gc, r);
		}
		
		
		//r.drawImageTile(image, 0, 0, (int)temp, 0);
		/*
		r.drawImage(bg, 0, 0);
		r.drawFillRect(32, 32, 200, 200, 0xffffccff);
	    r.drawImage(player, gc.getInput().getMouseX()-8, gc.getInput().getMouseY()-8);
		r.drawImageTile(image, gc.getInput().getMouseX()-8, gc.getInput().getMouseY()-8, (int)temp, 0);
	    r.drawRect(10, 10, 100, 100, 0xffffccff);
	 */
	
		
	}

}