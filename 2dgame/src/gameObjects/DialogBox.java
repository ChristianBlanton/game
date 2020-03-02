package gameObjects;

import java.awt.event.MouseEvent;

import gameFunctions.GameContainer;
import gameFunctions.PixSettings;
import gameFunctions.Renderer;
import gameFunctions.TextLoader;
import gfx.Pixel;


public class DialogBox extends Button{
	private static PixSettings p = new PixSettings();
	private float speed;
	private int currLen;
	private float count;
	private String animText;
	private String[] tempTexts;
	private int numTexts;
	private int currText;
	private boolean finished; //if entire dialog box is done
	private String endAnim=" ";
	private float cdTime=1.0f;
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public DialogBox(String text, float speed)
	{
		super(0,-(p.getHeight()/6-p.getHeight()), p.getWidth(), p.getHeight());
		this.speed=speed;
		String temp="";
		boolean hide = false;
		//allows for comments in text file using start with # and end with #
		/*
		 * 	for(int i=0; i<text.length(); i++)
		{
			if (!hide&&text.charAt(i)!='#')
				temp+=text.charAt(i);
			if(text.charAt(i)=='#' && !hide)
				hide=true;
			else if(TextLoader.load("/scenetxt.txt").charAt(i)=='#' && hide)
			{
				hide=false; 
			}
		}
		text=temp;
		 */
		//increases load time significantly
	
		//this.text=text;
		count=0;
		tempTexts=text.split("_");
		numTexts=tempTexts.length-1;
		currText=0;
	}
	
	public String[] getTempTexts() {
		return tempTexts;
	}
	
	public void replaceName(String replace)
	{
		for(int i=0; i<tempTexts.length; i++)
			tempTexts[i]=tempTexts[i].replaceAll("/name", replace);
	}

	public void setTempTexts(String[] tempTexts) {
		this.tempTexts = tempTexts;
	}

	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		if(cdTime>0)
		cdTime-=1;
		speed=gc.getWindow().getSettings().getTxtSpd();
		clip.setVolume(gc.getWindow().getSettings().getSfxVol());
		
		hover = (gc.getInput().getMouseX() >= posX && gc.getInput().getMouseX() <= posX + width)
				&& (gc.getInput().getMouseY() >= posY && gc.getInput().getMouseY() <= posY + height);
		
		if (hover) {
			this.color1 = Pixel.RED;
		} else
			color1 = Pixel.GREEN;
		
		currLen=tempTexts[currText].length();
		
		if ((hover && gc.getInput().isButtonUp(MouseEvent.BUTTON1)&&currText==numTexts && count==tempTexts[currText].length()))
			finished=true;
		else if ((hover && gc.getInput().isButtonUp(MouseEvent.BUTTON1))) {
			setClicked(true);
			clip.play();
			if(count<currLen)
			{
				speed=currLen-count; //finish text anim if clicked
				count+=speed;
			}
			else
			{
				currText++;
				count=0;
			}
			
		}

		if(count>currLen)
			count=currLen;
		//animate arrows if done counting
		if(count>=currLen)
		{	
				 if((cdTime)<=0) //if cdTime has passed, animate the arrow at next spot
				{
					 
					 if((endAnim.length()<4))
						 endAnim+=">";
					 else
							 endAnim=" >";
					 cdTime=50f;
				}
		}
		else if(count<currLen)
		{
			if((count+speed)>currLen)
			speed=1;
			count+=speed;
			text=""+tempTexts[currText].substring(0,(int)count);
			
		}
	}
	
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		//r.draw2DString(text, posX+200, posY+200, 0);
		//System.out.println(endAnim);
		r.drawTextInBox(posX, posY, width, height, color1, (int)count+endAnim.length(), tempTexts[currText]+endAnim);
		//r.noiseGen();
	}
	
	

}
