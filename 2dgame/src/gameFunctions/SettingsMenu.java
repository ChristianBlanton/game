package gameFunctions;

import java.util.ArrayList;

import gameObjects.Background;
import gameObjects.Button;
import gameObjects.Dvd;
import gameObjects.GameObject;
import gameObjects.TextInputter;
import gfx.Image;
import gfx.ImageTile;

public class SettingsMenu extends AbstractGame {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private Button back;
	private Button addMusVol;
	private Button subMusVol;
	private Button addSfxVol;
	private Button subSfxVol;
	private Button addTxtSpd;
	private Button subTxtSpd;
	private Button saveMenu;
	private TextInputter tI;
	private boolean goBack = false;
	private boolean goSave=false;
	public boolean isGoSave() {
		return goSave;
	}

	public void setGoSave(boolean goSave) {
		this.goSave = goSave;
	}

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
		addSfxVol=new Button(400, 100, 200, 100, "SFX VOL+");
		objects.add(addSfxVol);
		subSfxVol=new Button(400, 400, 200, 100, "SFX VOL-");
		objects.add(subSfxVol);
		ImageTile image=new ImageTile("/dvdsprites.png",100,100);
		
		addTxtSpd=new Button(800, 100, 200, 100, "Text Speed+");
		subTxtSpd=new Button(800, 400, 200, 100, "Text Speed-");
		objects.add(addTxtSpd);
		objects.add(subTxtSpd);
		tI=new TextInputter(500, 500, 100, 100);
		//objects.add(tI);
		objects.add(new Dvd(100, 100, image));
		saveMenu=new Button(600,600, 100,100, "Save");
		objects.add(saveMenu);
	}
	
	public SettingsMenu(Window window) {
		back = new Button(10, 10, 40, 40, "<");
		objects.add(back);
		addMusVol=new Button(window.getSettings().getWidth()/6-200, window.getSettings().getHeight()/10, 200, 100, "MUSIC VOL+");
		objects.add(addMusVol);
		subMusVol=new Button(window.getSettings().getWidth()/6-200, window.getSettings().getHeight()/10+300, 200, 100, "MUSIC VOL-");
		objects.add(subMusVol);
		addSfxVol=new Button(window.getSettings().getWidth()/4,window.getSettings().getHeight()/10, 200, 100, "SFX VOL+");
		objects.add(addSfxVol);
		subSfxVol=new Button(window.getSettings().getWidth()/4, window.getSettings().getHeight()/10+300, 200, 100, "SFX VOL-");
		objects.add(subSfxVol);
		ImageTile image=new ImageTile("/dvdsprites.png",100,100);
		
		addTxtSpd=new Button(window.getSettings().getWidth()/2, window.getSettings().getHeight()/10, 200, 100, "Text Speed+");
		subTxtSpd=new Button(window.getSettings().getWidth()/2, window.getSettings().getHeight()/10+300, 200, 100, "Text Speed-");
		objects.add(addTxtSpd);
		objects.add(subTxtSpd);
		tI=new TextInputter(500, 500, 100, 100);
		//objects.add(tI);
		objects.add(new Dvd(100, 100, image));
		saveMenu=new Button(600,600, 100,100, "Save");
		objects.add(saveMenu);
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
				//System.out.println(gc.getWindow().getSettings().getSfxVol());
				addSfxVol.setText(addSfxVol.getText()+"+");
			}
			if(subSfxVol.isClicked())
			{
				gc.getWindow().getSettings().subSfxVol();
				subSfxVol.setClicked(false);
//System.out.println(gc.getWindow().getSettings().getSfxVol());
				subSfxVol.setText(subSfxVol.getText()+"-");
			}
			
			if(addTxtSpd.isClicked())
			{
				gc.getWindow().getSettings().addTxtSpd();
				addTxtSpd.setClicked(false);
				addTxtSpd.setText(addTxtSpd.getText()+"+");
			}
			
			if(subTxtSpd.isClicked())
			{
				gc.getWindow().getSettings().subTxtSpd();
				subTxtSpd.setClicked(false);
				subTxtSpd.setText(subTxtSpd.getText()+"-");
			}
				
			if (back.isClicked()) {
				goBack = true;
				back.setClicked(false);
				//destroy();
			}
			if(saveMenu.isClicked())
			{
				goSave=true;
				saveMenu.setClicked(false);
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
		//r.noiseShad();
		//r.noiseShear();
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
