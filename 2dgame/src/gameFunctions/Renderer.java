package gameFunctions;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.TimeUnit;


import gfx.Font;
import gfx.Image;
import gfx.ImageRequest;
import gfx.ImageTile;
import gfx.Pixel;

public class Renderer {

	private Font font = Font.STANDARD;
	private ArrayList<ImageRequest> imageRequest = new ArrayList<ImageRequest>();
	private Random rand = new Random();

	private PixSettings settings = new PixSettings();
	private int pW, pH;
	private int[] pixels;
	private int[] zb; // zbuffer

	private float alphaMod = 1f;
	private int colorOverlay = Pixel.WHITE;
	private int clearColor = 0xff000000;

	private int zDepth = 0;
	private boolean processing = false;

	public Renderer(Window window) {
		pW = window.getImage().getWidth();
		pH = window.getImage().getHeight();
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

		for (int i = 0; i < settings.getHeight(); i++) {
			for (int j = 0; j < settings.getWidth(); j++) {
				occur = rand.nextInt(99);
				if (occur > 20) {
					randY = rand.nextInt(8);
					randX = rand.nextInt(8);
					pixelColor = pixels[i + j * pW];
					setPixel(i + randX, j + randY, pixelColor);
				}

			}
		}
	}

	public void noiseFlicker() {
		int occur = 0;
		int randX = 0;
		int randY = 0;
		occur = rand.nextInt(99);
		if (occur > 95) {
			for (int y = settings.getHeight(); y > settings.getHeight() - settings.getHeight() / 8; y--) {
				for (int x = 0; x < settings.getWidth(); x++) {
					{
						randY = rand.nextInt(8);
						randX = rand.nextInt(8);
						setPixel(x + randX, y + randY, Pixel.WHITE);
					}
				}

			}
		}
	}

	public void setPixel(int x, int y, int value) {
		if (x < 0 || x >= pW || y < 0 || y >= pH)
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

		drawFillRect(offX, offY, width, height, 0xffcc00ff);

		int offset = 0;
		int unicode;
		for (int i = 0; i < text.length(); i++) {
			unicode = text.codePointAt(i);
			if (offset + font.getChar(unicode).getWidth() > width) {
				offset = 0;
				offY += font.getHeight();
			}
			drawImage(font.getChar(unicode), offX + offset, offY);
			offset += font.getChar(unicode).getWidth();
		}

		/*
		 * for(int y=0; y<font.getHeight(); y++) { for(int x=0;
		 * x<font.getWidths()[unicode]; x++) {
		 * if(font.getPixels()[(x+font.getOffsets()[unicode])+y*font.getWidths()] ==
		 * 0xffffffff) { if(offset+x>width) { offset=0; offY+=font.getHeight();
		 * setPixel(x+offX+offset, y+offY, color); }
		 * 
		 * else setPixel(x+offX+offset, y+offY, color); } } }
		 */

	}

	public int getzDepth() {
		return zDepth;
	}

	public void setzDepth(int zDepth) {
		this.zDepth = zDepth;
	}
}
