package Christian;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
	private Window window;
	
	private final int NUM_KEYS=256;
	private boolean[] keys=new boolean[NUM_KEYS];
	private boolean[] keysLast=new boolean[NUM_KEYS];
	
	private final int NUM_BUTTONS=5;
	private boolean[] buttons=new boolean[NUM_BUTTONS];
	private boolean[] buttonsLast=new boolean[NUM_BUTTONS];
	
	private int mouseX, mouseY;
	private int scroll;
	
	public Input(Window window)
	{
		this.window = window;
		window.getFrame().addKeyListener(this);
		window.getFrame().addMouseListener(this);
		window.getFrame().addMouseMotionListener(this);
		window.getFrame().addMouseWheelListener(this);
	}
	
	public void update()
	{
		for(int i=0; i<NUM_KEYS; i++)
		{
			keysLast[i]=keys[i];
		}
		for(int i=0; i<NUM_BUTTONS; i++)
		{
			buttonsLast[i]=buttons[i];
		}
		
	}
	
	public boolean isKey(int keyCode)
	{
		return keys[keyCode];
	}
	public boolean isKeyUp(int keyCode)
	{
		return !keys[keyCode]&&keysLast[keyCode];
	}
	public boolean isKeyDown(int keyCode)
	{
		return keys[keyCode]&&!keysLast[keyCode];
	}
	
	public boolean isButton(int button)
	{
		return buttons[button];
	}
	public boolean isButtonUp(int button)
	{
		return !buttons[button]&&buttonsLast[button];
	}
	public boolean isButtonDown(int button)
	{
		return buttons[button]&&!buttonsLast[button];
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll=e.getWheelRotation();
		
	}

	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
		
	}

	public void mouseMoved(MouseEvent mouseEvent) {
		int x = mouseEvent.getX();
		mouseX = (int) (((x - window.getInsets().left) /
				 (float)(window.getFrame().getWidth() - window.getInsets().left - window.getInsets().right)) *
					    (window.getImage().getWidth()));
		int y = mouseEvent.getY();
		mouseY = (int) (((y - window.getInsets().top) /
				 (float)(window.getFrame().getHeight() - window.getInsets().top - window.getInsets().bottom)) *
					    (window.getImage().getHeight()));
	}

	public void mouseClicked(MouseEvent e) {
		
		
	}

	public void mouseEntered(MouseEvent e) {
		
		
	}

	public void mouseExited(MouseEvent e) {
		
		
	}

	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()]= true;
		
	}

	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()]= false;
		
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
		
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
		
	}

	public void keyTyped(KeyEvent e) {
		
		
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public int getScroll() {
		return scroll;
	}

}
