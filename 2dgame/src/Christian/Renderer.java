package Christian;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import gfx.Font;
import gfx.Image;
import gfx.ImageRequest;
import gfx.ImageTile;
public class Renderer
{
	
	private Font font = Font.STANDARD;
	private ArrayList<ImageRequest> imageRequest=new ArrayList<ImageRequest>();
	
    private int pW, pH;
    private int[] p;
    private int[]zb; //zbuffer 
    
    private int zDepth=0;
    private boolean processing=false;
   
    public Renderer(Window window)
    {
        pW = window.getImage().getWidth();
        pH = window.getImage().getHeight();
        p = ((DataBufferInt)window.getImage().getRaster().getDataBuffer()).getData();
        zb=new int [p.length];
    }
    
    
    public void clear()
    {
    	for (int i=0; i<p.length; i++)
    	{
    		p[i]=0;
    		zb[i]=0;
    	}
    }
    
    public void process()
    {
    	processing=true;
    	
    	Collections.sort(imageRequest, new Comparator<ImageRequest>() {

			@Override
			public int compare(ImageRequest i0, ImageRequest i1) {
				if(i0.zDepth<i1.zDepth)
					return -1;
				if(i0.zDepth>i1.zDepth)
					return 1;
				return 0;
			}
    		
    	});
    	
    	for(int i=0; i<imageRequest.size(); i++)
    	{
    		ImageRequest ir=imageRequest.get(i);
    		setzDepth(ir.zDepth);
    		ir.image.setAlpha(false);
    		drawImage(ir.image, ir.offX, ir.offY);
    	}
    	
    	imageRequest.clear();
    	processing=false;
    }
    public void setPixel(int x, int y, int value)
    {
    	int alpha=((value>>24)&0xff);
    	
    	if((x<0||x>=pW||y<0||y>=pH)||((value>>24) & 0xff)==0 ||alpha==0) //0xffff00ff alpha color do not render
    	{
    		return;
    	}
    	
    	if(zb[x+y*pW]>zDepth)
    		return;
    	
    	int index=x+y*pW;
    	
    	zb[index]=zDepth;
    	
    	if(alpha==255)
    	{
    		p[index]=value;
    	}
    	else
    	{
    		
    		int pixelColor=p[index];
    		
    		int newRed=((pixelColor>>16) & 0xff) - (int)((((pixelColor>>16)& 0xff)-((value>>16) & 0xff))*(alpha/255f));
    		int newGreen=((pixelColor>>8) & 0xff) - (int)((((pixelColor>>8)& 0xff)-((value>>8) & 0xff))*(alpha/255f));
    		int newBlue=(pixelColor & 0xff) - (int)(((pixelColor& 0xff)-(value & 0xff))*(alpha/255f));
    		
    		p[index]=(255 << 24| newRed<<16| newGreen<<8|newBlue);
    	}
    	
    	
    }
    
    public void draw2DString(String text, int offX, int offY, int justified) {
		int unicode;
		int offset = 0;
		if(justified == Font.RIGHT) {
			offset -= font.getStringWidth(text);
		} else if(justified == Font.CENTER) {
			offset -= font.getStringWidth(text) / 2;
		}
		for (int i = 0; i < text.length(); i++) {
			unicode = text.codePointAt(i);
			drawImage(font.getChar(unicode), offX + offset, offY);
			offset += font.getChar(unicode).getWidth();
		}
	}
    
    public void drawText(String text, int offX, int offY, int color, int justified)
    {
    	int unicode;
		int offset = 0;
		if(justified == Font.RIGHT) {
			offset -= font.getStringWidth(text);
		} else if(justified == Font.CENTER) {
			offset -= font.getStringWidth(text) / 2;
		}
		for (int i = 0; i < text.length(); i++) {
			unicode = text.codePointAt(i);
			drawImage(font.getChar(unicode), offX + offset, offY);
			offset += font.getChar(unicode).getWidth();
		}
    	
    }
    
    public void drawImage(Image image, int offX, int offY)
    {
    	if(image.isAlpha()&& !processing)
    	{
    		imageRequest.add(new ImageRequest(image, zDepth, offX, offY));
    		return;
    	}
    	
    	//don't render
    	
    	if(offX<-image.getWidth())
    		return;
    	if(offY<-image.getHeight())
    		return;
    	if(offX>=pW)
    		return;
    	if(offY>=pH) return;
    	int newX, newY;
    	newX=newY=0;
    	int newWidth=image.getWidth();
    	int newHeight=image.getHeight();
    	
    	//clipping
    	if (offX<0)    	{newX-=offX;    	}
    	if (newY<0)    	{newY-=offY;    	}
    	if (newWidth+offX>=pW){newWidth -=(newWidth+offX-pW);}
    	if (newHeight+offY>=pH){newHeight -=(newHeight+offY-pH);
    	}
    	for (int y=newY; y<newHeight;y++)
    	{
    	 for (int x=newX; x<newWidth; x++)
    	 { 
    		 setPixel(x+offX, y+offY, image.getPixels()[x+y*image.getWidth()]);
    	 }
    	}
    }
    public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY)
    {
    	if(image.isAlpha() && !processing)
    	{
    		imageRequest.add(new ImageRequest(image.getTileImage(tileX, tileY), zDepth, offX, offY));
    		return;
    	}
    	
    	if(offX<-image.getTileW())
    		return;
    	if(offY<-image.getTileH())
    		return;
    	if(offX>=pW)
    		return;
    	if(offY>=pH) return;
    	int newX, newY;
    	newX=newY=0;
    	int newWidth=image.getTileW();
    	int newHeight=image.getTileH();
    	
    	//clipping
    	if (offX<0)    	{newX-=offX;    	}
    	if (newY<0)    	{newY-=offY;    	}
    	if (newWidth+offX>=pW){newWidth -=(newWidth+offX-pW);}
    	if (newHeight+offY>=pH){newHeight -=(newHeight+offY-pH);
    	}
    	for (int y=newY; y<newHeight;y++)
    	{
    	 for (int x=newX; x<newWidth; x++)
    	 { 
    		 setPixel(x+offX, y+offY, image.getPixels()[(x+tileX*image.getTileW())+(y+tileY*image.getTileH())*image.getWidth()]);
    	 }
    	}
    }
    
    public void drawRect(int offX, int offY, int width, int height, int color)
    {
    	for(int y=0; y<height; y++)
    	{
    		setPixel(offX, y+offY, color);
    		setPixel(offX+width, y+offY, color);
    	}
    	
    	for(int x=0; x<width; x++)
    	{
    		setPixel(x+offX, offY, color);
    		setPixel(offX+x, offY+height, color);
    	}
    }
    
    public void drawFillRect(int offX, int offY, int width, int height, int color)
    {
    	if(offX<-width)
    		return;
    	if(offY<-height)
    		return;
    	if(offX>=pW)
    		return;
    	if(offY>=pH) return;
    	int newX, newY;
    	newX=newY=0;
    	int newWidth=width;
    	int newHeight=height;
    	
    	//clipping
    	if (offX<0)    	{newX-=offX;    	}
    	if (newY<0)    	{newY-=offY;    	}
    	if (newWidth+offX>=pW){newWidth -=(newWidth+offX-pW);}
    	if (newHeight+offY>=pH){newHeight -=(newHeight+offY-pH);
    	}
    	for(int y=newY; y<newHeight; y++)
    	{
    		for(int x=newX; x<newWidth; x++)
    		setPixel(x+offX,y+offY, color);
    	}
    }
    
    public void drawTextInBox(int offX, int offY, int width, int height, int color, String text)
    {
    	if(offX<-width)
    		return;
    	if(offY<-height)
    		return;
    	if(offX>=pW)
    		return;
    	if(offY>=pH) return;
    	
    	drawFillRect(offX, offY, width, height, 0xffcc00ff);
    	
        int offset=0;
    	int unicode;
    	for (int i = 0; i < text.length(); i++) {
			unicode = text.codePointAt(i);
				if(offset+font.getChar(unicode).getWidth()>width)
				{
					offset=0;
					offY+=font.getHeight();				
				}
			drawImage(font.getChar(unicode), offX + offset, offY);
			offset += font.getChar(unicode).getWidth();
		}
    		
    		/*
    		 * for(int y=0; y<font.getHeight(); y++)
        	{
        		for(int x=0; x<font.getWidths()[unicode]; x++)
        		{
        			if(font.getPixels()[(x+font.getOffsets()[unicode])+y*font.getWidths()] == 0xffffffff)
        			{
        				if(offset+x>width)
        				{
        					offset=0;
        					offY+=font.getHeight();
		 					setPixel(x+offX+offset, y+offY, color);
        				}
        				
        				else
        				setPixel(x+offX+offset, y+offY, color);
        			}
        		}
        	}
    		 */
    		
    	
    	
    	
    }
    
    public int getzDepth() {
		return zDepth;
	}


	public void setzDepth(int zDepth) {
		this.zDepth = zDepth;
	}
}
