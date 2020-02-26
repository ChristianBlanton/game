package gameObjects;

import java.util.Random;

import gameFunctions.AbstractGame;
import gameFunctions.GameContainer;
import gameFunctions.GameManager;
import gameFunctions.PixSettings;
import gameFunctions.Renderer;
import gameFunctions.Window;
import gfx.Image;

public class Intro extends AbstractGame{
	
	private Image image;
	private Sprite sprite;
	private String text;
	private Background bg;
	private boolean finished;
	private float speed=1.5f;
	private float time;
	private float row;
	private Random rand=new Random();
	private int catchP;
	
	public Intro(String text)
	{
		this.text=text;
		setFinished(false);
		catchP=rand.nextInt(4);
	}
		
	
	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		if(time>1.5)
		{
			switch(catchP)
			{
			case 3: text="I hate you"; break;
			case 2: text="I love you"; break;
			case 1: text="go away"; break;
			default: break;
			}
			
		}
			
		time+=dt;
		if(time>2)
			finished=true;
		if(row+speed<gc.getWindow().getSettings().getHeight())
		row+=speed;
		else
		row=0;
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
	r.draw2DString(text, gc.getWindow().getSettings().getWidth()/2, gc.getWindow().getSettings().getHeight()/2, 2);
	r.sideNoise();
	r.noiseShad();
	r.sideNoiseScroll((int)row);
	r.sideNoiseScrollWhite((int)row+50);
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean nextScene) {
		this.finished = nextScene;
	}
}
