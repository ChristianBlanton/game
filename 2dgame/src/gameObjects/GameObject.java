package gameObjects;

import gameFunctions.GameContainer;
import gameFunctions.Renderer;

public abstract class GameObject {
	protected int posX, posY;
	protected int width, height;
	protected String text;
	protected String tag;
	protected boolean dead=false;
	

public abstract void update(GameContainer gc, float dt);
public abstract void render(GameContainer gc, Renderer r);

public int getPosX() {
	return posX;
}
public void setPosX(int posX) {
	this.posX = posX;
}
public int getPosY() {
	return posY;
}
public void setPosY(int posY) {
	this.posY = posY;
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
public String getTag() {
	return tag;
}
public void setTag(String tag) {
	this.tag = tag;
}	

public boolean isDead() {
	return dead;
}
public void setDead(boolean dead) {
	this.dead = dead;
}
public void update(GameContainer gc, float dt, Renderer r) {
	// TODO Auto-generated method stub
	
}
}
