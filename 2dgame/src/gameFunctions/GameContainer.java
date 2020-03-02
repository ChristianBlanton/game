package gameFunctions;
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
    private GameManager gameManager;
    private PixSettings settings;
    private double passedTime;
    private static double globTime;
    
    private static volatile boolean running=false;

    public GameContainer(Window window)
    {
     this.window=window;
    }

    public void start()
    {
        renderer=new Renderer(window);
        input= new Input(window);
        settings=new PixSettings();
        gameManager=new GameManager(window);
        thread=new Thread(this);
        thread.run();
    }

    public static void stop()
    {
    running=false;
    }

    public void run()
    {
        running=true;
        boolean render=false;
        double firstTime=0;
        double lastTime=System.nanoTime()/1000000000.0;
        passedTime=0;
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

            while(unprocessedTime>=settings.getUpdateCap())
            {
                unprocessedTime-=settings.getUpdateCap();
                render=true;
                
                gameManager.update(this, (float)settings.getUpdateCap());
                input.update();
                
                if(frameTime>=1.0)
                {
                    frameTime=0;
                    fps=frames;
                    frames=0;
                  
                }

            }
            if(render)
            {
            	
            	globTime=firstTime%10;
            	renderer.clear();
                gameManager.render(this, renderer);
                renderer.process();
                renderer.drawText("FPS:"+fps, 0, 0, 0, 0xff00ffff);
                //renderer.drawText("Time:"+globTime, 0, 100, 0, 0xff00ffff);
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

    public static double getglobTime() {
		return globTime;
	}

	private void dispose()
    {
    	window.dispose();

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

	public GameManager getGameManager() {
		return gameManager;
	}

	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	  
}


    
    

