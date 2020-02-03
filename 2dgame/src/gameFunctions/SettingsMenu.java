package gameFunctions;

import java.util.ArrayList;

import gameObjects.Button;
import gameObjects.Dvd;
import gameObjects.GameObject;
import gfx.Image;
import gfx.ImageTile;

public class SettingsMenu extends AbstractGame {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private Button back;
	private Button addMusVol;
	private Button subMusVol;
	private Button addSfxVol;
	private Button subSfxVol;
	private boolean goBack = false;
	private boolean musChange=false;

	public boolean isMusChange() {
		return musChange;
	}

	public void setMusChange(boolean musChange) {
		this.musChange = musChange;
	}

	public void setGoBack(boolean goBack) {
		this.goBack = goBack;
	}

	public SettingsMenu() {
		back = new Button(10, 10, 40, 40, "<");
		objects.add(back);
		addMusVol=new Button(100, 100, 200, 100, "MUSIC VOL+");
		objects.add(addMusVol);
		subMusVol=new Button(100, 400, 200, 100, "MUSIC VOL-");
		objects.add(subMusVol);
		addSfxVol=new Button(400, 100, 200, 100, "");
		objects.add(addSfxVol);
		subSfxVol=new Button(400, 400, 200, 100, "SFX VOL-");
		objects.add(subSfxVol);
		ImageTile image=new ImageTile("/dvdsprites.png",100,100);
		objects.add(new Dvd(100, 100, image));
		
	}

	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).update(gc, dt);
			if (objects.get(i).isDead()) {
				objects.remove(i);
				i--;
			}
			if(addMusVol.isClicked())
			{
				gc.getWindow().getSettings().addMusicVol();
				addMusVol.setClicked(false);
				addMusVol.setText(addMusVol.getText()+"+");
				musChange=true;
				
			}
			if(subMusVol.isClicked())
			{
				gc.getWindow().getSettings().subMusicVol();
				subMusVol.setClicked(false);
				subMusVol.setText(subMusVol.getText()+"-");
				musChange=true;
			}
			
			if(addSfxVol.isClicked())
			{
				gc.getWindow().getSettings().addSfxVol();
				addSfxVol.setClicked(false);
				System.out.println(gc.getWindow().getSettings().getSfxVol());
				addSfxVol.setText(addSfxVol.getText()+"JOE ");
			}
			if(subSfxVol.isClicked())
			{
				gc.getWindow().getSettings().subSfxVol();
				subSfxVol.setClicked(false);
System.out.println(gc.getWindow().getSettings().getSfxVol());
				subSfxVol.setText(subSfxVol.getText()+"-");
			}
			
				
			if (back.isClicked()) {
				goBack = true;
				back.setClicked(false);
				//destroy();
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		for (GameObject obj : objects) {
			obj.render(gc, r);
		}
		r.sideNoise();
		r.noiseShad();
	}

	public void destroy() {
		for (int i = 0; i < objects.size(); i++) {
			objects.remove(i);
			i--;
		}

	}

	public boolean isGoBack() {
		return goBack;
	}

}
