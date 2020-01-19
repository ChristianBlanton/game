package Christian;
import java.awt.event.KeyEvent;

import audio.SoundClip;



/**
 * Write a description of class GameContainer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameContainer implements Runnable
{
    private Thread thread;
    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;
    
    private boolean running=false;
    private final double UPDATE_CAP=1.0/144.0;
    private int width=1000, height=1000;
    private float scale=1f;
  

	private String title="Christian Engine v1.0";
    


    public GameContainer(AbstractGame game)
    {
     this.game=game;
    }

    public void start()
    {
        window=new Window(this);
        renderer=new Renderer(this);
        input= new Input(this);
        
        thread=new Thread(this);
        thread.run();
    }

    public void stop()
    {

    }

    public void run()
    {
        running=true;
        boolean render=false;
        double firstTime=0;
        double lastTime=System.nanoTime()/1000000000.0;
        double passedTime=0;
        double unprocessedTime=0;
        double frameTime=0;
        int frames=0;
        int fps=0;

        while(running)
        {
            render=false;
            firstTime=System.nanoTime()/1000000000.0;
            passedTime=firstTime-lastTime;
            lastTime=firstTime;

            unprocessedTime +=passedTime;
            frameTime+=passedTime;

            while(unprocessedTime>=UPDATE_CAP)
            {
                unprocessedTime-=UPDATE_CAP;
                render=true;
                
                game.update(this, (float)UPDATE_CAP);
                if(input.isKey(KeyEvent.VK_A))
                {
                	System.out.println("A");
                
                
                }
                input.update();
                
                if(frameTime>=1.0)
                {
                    frameTime=0;
                    fps=frames;
                    frames=0;
                   // System.out.println("FPS: " + fps);
                }

            }
            if(render)
            {
            	renderer.clear();
                game.render(this, renderer);
                renderer.process();
                renderer.drawText("FPS:"+fps, 0, 0, 0xff00ffff);
                window.update();
                frames++;
            }
            else
            {
                try
                {
                    Thread.sleep(1);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

        }
        dispose();
    }

    private void dispose()
    {

    }
    
    
    
    public int getHeight()
    {
        return height;
    }
    
    public float getScale()
    {
        return scale;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public void setHeight(int input)
    {
        height=input;
    }
    
    public void setScale(int input)
    {
        scale=input;
    }
    
    public void setTitle(String input)
    {
        title=input;
    }
    
    public void setWidth(int input)
    {
        width=input;
    }
    
    public Window getWindow()
    {
        return window;
    }

	public Input getInput() {
		return input;
	}
	
	  public Renderer getRenderer() {
			return renderer;
		}
    }
    

