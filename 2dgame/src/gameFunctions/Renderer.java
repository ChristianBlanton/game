package gameFunctions;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import gameObjects.Scene;
import gfx.Font;
import gfx.Image;
import gfx.ImageRequest;
import gfx.ImageTile;
import gfx.Pixel;

public class Renderer {

	private Font font = Font.STANDARD;
	private ArrayList<ImageRequest> imageRequest = new ArrayList<ImageRequest>();
	private Random rand = new Random();
	private Thread thread;

	private PixSettings settings;
	private int pW, pH;
	private int[] pixels;
	private int[] zb; // zbuffer

	private float alphaMod = 1f;
	private int colorOverlay = Pixel.WHITE;
	private int clearColor = 0xff000000;

	private int zDepth = 0;
	private boolean processing = false;

	public Renderer(Window window) {
		thread=new Thread();
		pW = window.getImage().getWidth();
		pH = window.getImage().getHeight();
		settings = window.getSettings();
		pixels = ((DataBufferInt) window.getImage().getRaster().getDataBuffer()).getData();
		zb = new int[pixels.length];
		// settings=window.settings
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
			zb[i] = 0;
		}
	}

	public void process() {
		processing = true;

		Collections.sort(imageRequest, new Comparator<ImageRequest>() {

			@Override
			public int compare(ImageRequest i0, ImageRequest i1) {
				if (i0.zDepth < i1.zDepth)
					return -1;
				if (i0.zDepth > i1.zDepth)
					return 1;
				return 0;
			}

		});

		for (int i = 0; i < imageRequest.size(); i++) {
			ImageRequest ir = imageRequest.get(i);
			setzDepth(ir.zDepth);
			ir.image.setAlpha(false);
			drawImage(ir.image, ir.offX, ir.offY);
		}

		imageRequest.clear();
		processing = false;
	}

	public void noiseGen() {
		int occur = 0;
		int randX = 0;
		int randY = 0;
		int pixelColor;

		for (int i = 0; i < pW; i++) {
			for (int j = 0; j < pH; j++) {
				occur = rand.nextInt(99);
				if (occur > 20) {
					randY = rand.nextInt(8) - rand.nextInt(8);
					randX = rand.nextInt(8) - rand.nextInt(8);
					pixelColor = pixels[i + j * pW];
					setPixel(i + randX, j + randY, pixelColor);
				}

			}
		}
	}

	public void sideNoise() {
		
		int occur = 0;
		int randX = 0;
		int randY = 0;
		int pixelColor;
		
		occur = rand.nextInt(99);
		if (occur > 90) {
			for (int i = 0; i < pW; i++) {
				
				for (int j = 0; j < pH; j++) {
					
						randY = rand.nextInt(1) - rand.nextInt(1);
						randX = rand.nextInt(5);
						
						pixelColor = pixels[i + j * pW];
						setPixel(i + randX, j + randY, Pixel.mul(pixelColor, 2));
				}

			}
		}
	}
	
public void sideNoise(int chance) {
		
		int occur = 0;
		int randX = 0;
		int randY = 0;
		int pixelColor;
		
		occur = rand.nextInt(1000);
		if (occur > chance) {
			for (int i = 0; i < pW; i++) {
				
				for (int j = 0; j < pH; j++) {
					
						randY = rand.nextInt(1) - rand.nextInt(1);
						randX = rand.nextInt(5);
						
						pixelColor = pixels[i + j * pW];
						setPixel(i + randX, j + randY, Pixel.mul(pixelColor, 2));
				}

			}
		}
	}

public void sideNoiseScroll() {
	
	int occur = 0;
	int randX = 0;
	int randY = 0;
	int pixelColor;
	
		for (int i = 0; i < pW; i++) {
			
			int j = pH-(int)GameContainer.getglobTime()*2-1; {				
					randY = rand.nextInt(1) - rand.nextInt(1);
					randX = rand.nextInt(5);
					
					pixelColor = pixels[i + j * pW];
					setPixel(i + randX, j + randY, Pixel.WHITE);
			}

		}
}
	
public void noiseShad() {
		
		int occur = 0;
		int randX = 0;
		int randY = 0;
		int pixelColor;
		
		occur = rand.nextInt(99);
		if (occur > -1) {
			for (int i = 0; i < pW; i++) {
				for (int j = 0; j < pH; j++) {
					pixelColor = pixels[i + j * pW];
					if(pixelColor!=0x00000000)
					{
						randY = rand.nextInt(1) - rand.nextInt(1);
						randX = rand.nextInt(5);
						
						
						//setPixel(i+5, j+5, (pixelColor & 0x8000000));
						setPixel(i+2, j+1, Pixel.overlayColor(pixelColor, 0x800000FF));
						setPixel(i-2, j-1, Pixel.overlayColor(pixelColor, 0x80FF0000));
					}
					
						
				}

			}
		}
	}
	
	public void vertNoise()
	{
		int occur = 0;
		int randX = 0;
		int randY = 0;
		int pixelColor;
		int pixelColor2;
		int delay=0;
		randY = rand.nextInt(1) - rand.nextInt(1);
		randX =1+ rand.nextInt(5);
		occur = rand.nextInt(99);
		//if (occur > 80) {
			for (int i = 0; i < pW; i++) {
				
				for (int j = 0; j < pH; j++) {
					
						pixelColor = pixels[i + j * pW];
						pixelColor2=pixels[rand.nextInt(200)];
				}

			}
		}
	//}
	
	public void noiseFlicker() {
		int occur = 0;
		int randX = 0;
		int randY = 0;
		occur = rand.nextInt(99);
		if (occur > 95) {

			for (int y = settings.getHeight(); y > settings.getHeight() - settings.getHeight() / 100; y--) {
				for (int x = 0; x < settings.getWidth(); x++) {
					{
						randY = rand.nextInt(8) - rand.nextInt(8);
						randX = rand.nextInt(8) - rand.nextInt(8);
						setPixel(x + randX, y + randY, Pixel.randColor());
					}
				}

			}
		}
	}
	
	public void noiseFlicker(int chance) {
		int occur = 0;
		int randX = 0;
		int randY = 0;
		occur = rand.nextInt(99);
		if (occur > chance) {

			for (int y = settings.getHeight(); y > settings.getHeight() - settings.getHeight() / 100; y--) {
				for (int x = 0; x < settings.getWidth(); x++) {
					{
						randY = rand.nextInt(8) - rand.nextInt(8);
						randX = rand.nextInt(8) - rand.nextInt(8);
						setPixel(x + randX, y + randY, Pixel.randColor());
					}
				}

			}
		}
	}
	
	public void noiseShear() {
		int pixelColor;

		for (int i = 0; i < pW; i++) {
			for (int j = 0; j < pH; j++) {
					pixelColor = pixels[i + j * pW];
					setPixel(i+3*j,j, pixelColor);
					//setPixel(i-j, j, pixelColor);
			}

		}
	}
	

	
	

	public void setPixel(int x, int y, int value) {
		if (x < 0 || x >= pW || y < 0 || y >= pH)
			return;
		if(pixels[x + y * pW] == value)
			return;

		float alpha = Pixel.getAlpha(value) - (1f - alphaMod);
		if (colorOverlay != Pixel.WHITE) {
			value = Pixel.overlayColor(value, colorOverlay);
		}

		if (alpha == 1) {
			pixels[x + y * pW] = value;
		} else if (alpha != 0) {
			pixels[x + y * pW] = Pixel.alphaBlend(pixels[x + y * pW], value, alpha);
		}
	}

	public void draw2DString(String text, int offX, int offY, int justified) {
		int unicode;
		int offset = 0;
		if (justified == Font.RIGHT) {
			offset -= font.getStringWidth(text);
		} else if (justified == Font.CENTER) {
			offset -= font.getStringWidth(text) / 2;
		}
		for (int i = 0; i < text.length(); i++) {
			unicode = text.codePointAt(i);
			drawImage(font.getChar(unicode), offX + offset, offY);
			offset += font.getChar(unicode).getWidth();
		}
	}

	public void drawText(String text, int offX, int offY, int color, int justified) {
		int unicode;
		int offset = 0;
		if (justified == Font.RIGHT) {
			offset -= font.getStringWidth(text);
		} else if (justified == Font.CENTER) {
			offset -= font.getStringWidth(text) / 2;
		}
		for (int i = 0; i < text.length(); i++) {
			unicode = text.codePointAt(i);
			drawImage(font.getChar(unicode), offX + offset, offY);
			offset += font.getChar(unicode).getWidth();
		}

	}
	
	public void drawText(String text, int offX, int offY, int color, int justified, int speed) {
		int unicode;
		int offset = 0;
		if (justified == Font.RIGHT) {
			offset -= font.getStringWidth(text);
		} else if (justified == Font.CENTER) {
			offset -= font.getStringWidth(text) / 2;
		}
		for (int i = 0; i < text.length(); i++) {
			unicode = text.codePointAt(i);
			drawImage(font.getChar(unicode), offX + offset, offY);
			offset += font.getChar(unicode).getWidth();
		}

	}

	public void drawImage(Image image, int offX, int offY) {
		if (image.isAlpha() && !processing) {
			imageRequest.add(new ImageRequest(image, zDepth, offX, offY));
			return;
		}
		// don't render
		if (offX < -image.getWidth())
			return;
		if (offY < -image.getHeight())
			return;
		if (offX >= pW)
			return;
		if (offY >= pH)
			return;
		int newX, newY;
		newX = newY = 0;
		int newWidth = image.getWidth();
		int newHeight = image.getHeight();

		// clipping
		if (offX < 0) {
			newX -= offX;
		}
		if (newY < 0) {
			newY -= offY;
		}
		if (newWidth + offX >= pW) {
			newWidth -= (newWidth + offX - pW);
		}
		if (newHeight + offY >= pH) {
			newHeight -= (newHeight + offY - pH);
		}
		for (int y = newY; y < newHeight; y++) {
			for (int x = newX; x < newWidth; x++) {
				setPixel(x + offX, y + offY, image.getPixels()[x + y * image.getWidth()]);
			}
		}
	}

	public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY) {
		if (image.isAlpha() && !processing) {
			imageRequest.add(new ImageRequest(image.getTileImage(tileX, tileY), zDepth, offX, offY));
			return;
		}

		if (offX < -image.getTileW())
			return;
		if (offY < -image.getTileH())
			return;
		if (offX >= pW)
			return;
		if (offY >= pH)
			return;
		int newX, newY;
		newX = newY = 0;
		int newWidth = image.getTileW();
		int newHeight = image.getTileH();

		// clipping
		if (offX < 0) {
			newX -= offX;
		}
		if (newY < 0) {
			newY -= offY;
		}
		if (newWidth + offX >= pW) {
			newWidth -= (newWidth + offX - pW);
		}
		if (newHeight + offY >= pH) {
			newHeight -= (newHeight + offY - pH);
		}
		for (int y = newY; y < newHeight; y++) {
			for (int x = newX; x < newWidth; x++) {
				setPixel(x + offX, y + offY, image.getPixels()[(x + tileX * image.getTileW())
						+ (y + tileY * image.getTileH()) * image.getWidth()]);
			}
		}
	}

	public void drawRect(int offX, int offY, int width, int height, int color) {
		for (int y = 0; y < height; y++) {
			setPixel(offX, y + offY, color);
			setPixel(offX + width, y + offY, color);
		}

		for (int x = 0; x < width; x++) {
			setPixel(x + offX, offY, color);
			setPixel(offX + x, offY + height, color);
		}
	}

	public void drawFillRect(int offX, int offY, int width, int height, int color) {
		if (offX < -width)
			return;
		if (offY < -height)
			return;
		if (offX >= pW)
			return;
		if (offY >= pH)
			return;
		int newX, newY;
		newX = newY = 0;
		int newWidth = width;
		int newHeight = height;

		// clipping
		if (offX < 0) {
			newX -= offX;
		}
		if (newY < 0) {
			newY -= offY;
		}
		if (newWidth + offX >= pW) {
			newWidth -= (newWidth + offX - pW);
		}
		if (newHeight + offY >= pH) {
			newHeight -= (newHeight + offY - pH);
		}
		for (int y = newY; y < newHeight; y++) {
			for (int x = newX; x < newWidth; x++)
				setPixel(x + offX, y + offY, color);
		}
	}

	public void drawTextInBox(int offX, int offY, int width, int height, int color, String text) {
		if (offX < -width)
			return;
		if (offY < -height)
			return;
		if (offX >= pW)
			return;
		if (offY >= pH)
			return;

		drawFillRect(offX, offY, width, height, color);

		int offset = 0;
		int offset2 = 0;
		int unicode;
		int unicode2;
		int spacePos1 = 0;
		int spacePos2 = 0;
		boolean first = true;
		int offY2 = offY;

		int[] spots = new int[1000];
		int numReturns = 0;
		int findex = 0;
		int sindex = 0;
		
		for (int p = 0; p < text.length(); p++) {
			unicode2 = text.codePointAt(p);

			if (unicode2 == 32) {
				if (first) {
					first = false;
					spacePos1 = offset2;
					findex = p;
				} else {
					spacePos2 = offset2;
					first = true;
					sindex = p;
				}
			}
			if ((spacePos1 > width && spacePos2 < width)) {
				spots[numReturns] = spacePos2;
				numReturns++;
				spacePos1 = 0;
				spacePos2 = 0;
				offset2 = 0;
				offY += font.getHeight();
				p = sindex;
			} else if ((spacePos2 > width && spacePos1 < width)) {
				spots[numReturns] = spacePos1;
				numReturns++;
				spacePos1 = 0;
				spacePos2 = 0;
				offset2 = 0;
				offY += font.getHeight();
				p = findex;
			}
			offset2 += font.getChar(unicode2).getWidth();
		}
		int numReturns2 = 0;
		for (int i = 0; i < text.length(); i++) {
			unicode = text.codePointAt(i);
			if (offset != 0 && offset == spots[numReturns2]) {
				offset = 0;
				offY2 += font.getHeight();
				numReturns2++;
			}
			drawImage(font.getChar(unicode), offX + offset, offY2);
			offset += font.getChar(unicode).getWidth();
		}
	}
	

	public int getzDepth() {
		return zDepth;
	}

	public void setzDepth(int zDepth) {
		this.zDepth = zDepth;
	}
}
