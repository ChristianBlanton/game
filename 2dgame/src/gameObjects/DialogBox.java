package gameObjects;

import java.awt.event.MouseEvent;

import gameFunctions.GameContainer;
import gameFunctions.PixSettings;
import gameFunctions.Renderer;
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
	private String endAnim="";
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
		
		this.text="";
		count=0;
		animText=text;
		tempTexts=animText.split("_");
		
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
		for(String str: tempTexts)
			//System.out.println(str);
		speed=gc.getWindow().getSettings().getTxtSpd();
		clip.setVolume(gc.getWindow().getSettings().getSfxVol());
		
		hover = (gc.getInput().getMouseX() >= posX && gc.getInput().getMouseX() <= posX + width)
				&& (gc.getInput().getMouseY() >= posY && gc.getInput().getMouseY() <= posY + height);
		
		if (hover) {
			this.color = Pixel.RED;
		} else
			color = Pixel.GREEN;
		
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
					 if((endAnim.length()<3))
					 {
						 endAnim+=" ";
						cdTime=50f;
						text=tempTexts[currText].substring(0,(int)count)+" "+endAnim+">";
					 }
					 else					
					endAnim="";
				}
		}
		else if(count<currLen)
		{
			if((count+speed)>currLen)
			speed=1;
			count+=speed;
			//System.out.println("Count " + count+"CurrLen" + currLen);
			text=""+tempTexts[currText].substring(0,(int)count);
			
		}
		
		
		
	
	
	}
	
	
	
	

}
