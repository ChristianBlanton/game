package gfx;

import java.util.ArrayList;

public class Font 
{
	public static final Font STANDARD = new Font("/font2.png");
	private Image[] fontImage;
	private int[] offsets;
	private int[] widths;
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int CENTER = 2;
	private int height;
	
	public Font(String path)
	{
		this(new Image(path));
	}
	
	public Font(Image pixImage) {
		int start = 0;
		ArrayList<Image> tempPixImages = new ArrayList<>();
		for (int i = 0; i < pixImage.getWidth(); i++) {
			if (pixImage.getPixels()[i] == 0xff0000ff) {
				start = i;
			} else if (pixImage.getPixels()[i] == 0xffffff00) {
				int width = i - start;
				int[] p = new int[width * (pixImage.getHeight() - 1)];
				for (int y = 0; y < pixImage.getHeight() - 1; y++) {
					for (int x = start; x < start + width; x++) {
						p[(x - start) + y * width] = pixImage.getPixels()[x + (y + 1) * pixImage.getWidth()];
					}
				}
				tempPixImages.add(new Image(p, width, pixImage.getHeight() - 1));
			}
		}
		fontImage = new Image[tempPixImages.size()];
		tempPixImages.toArray(fontImage);
		height = getChar(0).getHeight();
	}

	public Image getChar(int unicode) {
		if (unicode > fontImage.length || unicode < 0) {
			return fontImage[0];
		} else {
			return fontImage[unicode];
		}
	}
	
	public int getStringWidth(String text) {
		int res = 0;
		for(int i = 0; i < text.length(); i++) {
			res += getChar(text.codePointAt(i)).getWidth();
		}
		return res;
	}
	

	public int[] getOffsets() {
		return offsets;
	}

	public void setOffsets(int[] offsets) {
		this.offsets = offsets;
	}

	public int[] getWidths() {
		return widths;
	}

	public void setWidths(int[] widths) {
		this.widths = widths;
	}
	
	public int getHeight()
	{
		return height;
	}
}
