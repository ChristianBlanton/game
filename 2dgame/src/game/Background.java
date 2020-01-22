package game;


	import java.awt.event.KeyEvent;
	import java.awt.event.MouseEvent;

	import javax.sound.sampled.Clip;

	import Christian.GameContainer;
	import Christian.Input;
	import Christian.Renderer;
	import audio.SoundClip;
import gfx.Image;


	public class Background extends GameObject {

	private SoundClip clip;
	private Input input;
	private Image image;
	
		public Background(int posX, int posY, String text, Image image)
		{
			this.posX=posX;
			this.posY=posY;
			this.tag=text;
			this.image=image;
		}
		
		@Override
		public void update(GameContainer gc, float dt) {
			// TODO Auto-generated method stub
							
			}

		@Override
		public void render(GameContainer gc, Renderer r) {
			// TODO Auto-generated method stub
			
			r.drawImage(image, posX, posY);
			
		}

	}

