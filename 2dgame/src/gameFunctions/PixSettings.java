package gameFunctions;

public class PixSettings {

	private String title = "Old Town Road";
	private int width=1280;
	private int height=720;
	private float scale=1.0f;
	private double updateCap=1.0/144.0; 
	private boolean debug=true; 
	private boolean lockFPS=false;
	private float musicVol=0;
	private float sfxVol=0;
	private float txtSpd=0.25f;

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
	public void addMusicVol()
	{
		if(musicVol+5>=6)
		{}
		else
		musicVol+=5;
	}
	
	public void subMusicVol()
	{
		if(musicVol-5<-80)
		{}
		else
		musicVol-=5;
	}
	
	public void addSfxVol()
	{
		if(sfxVol+5>=6)
		{}
		else
		sfxVol+=5;
	}
	
	public void subSfxVol()
	{
		if(sfxVol-5<-80)
		{}
		else
		sfxVol-=5;
	}
	
	public void setMusicVol(float vol)
	{
		musicVol=vol;
	}
	
	public float getMusicVol()
	{
		return musicVol;
	}
	
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

	public float getSfxVol() {
		return sfxVol;
	}

	public void setSfxVol(float sfVol) {
		this.sfxVol = sfVol;
	}

	public float getTxtSpd() {
		return txtSpd;
	}

	public void setTxtSpd(float txtSpd) {
		this.txtSpd = txtSpd;
	}
	
	public void addTxtSpd()
	{
		if(txtSpd+0.5<=3)
		txtSpd+=0.5;
	}
	
	public void subTxtSpd()
	{
		if(txtSpd-0.1>=0)
		txtSpd-=0.1;
	}
	
	
}