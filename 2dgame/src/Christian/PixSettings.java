package Christian;

public class PixSettings {

	private String title = "Old Town Road";
	private int width=1000;
	private int height=1000;
	private float scale=1.0f;
	private double updateCap=1.0/144.0; 
	private boolean debug=true; 
	private boolean lockFPS=false;

	/*
	 * public PixSettings(String title, int width, int height, float scale, double updateCap, boolean debug, boolean lockFPS)
	{
		this.title=title;
		this.width=width;
		this.height=height;
		this.scale=scale;
		this.updateCap=updateCap;
		this.debug=debug;
		this.lockFPS=lockFPS;
		
	}
	 */
	
	
	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getUpdateCap() {
		return updateCap;
	}

	public void setUpdateCap(double updateCap) {
		this.updateCap = updateCap;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public boolean isLockFPS() {
		return lockFPS;
	}

	public void setLockFPS(boolean lockFPS) {
		this.lockFPS = lockFPS;
	}
	
	
}