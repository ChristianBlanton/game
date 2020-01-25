package gameFunctions;


<<<<<<< HEAD
=======
import sun.misc.GC;
>>>>>>> branch 'master' of https://github.com/ChristianBlanton/game.git

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gameFunctions.PixSettings;

public class Window implements WindowListener
{

		private PixSettings settings;
		private JFrame frame;
		private BufferedImage image;
		private BufferStrategy bufferStrategy;
		private Graphics graphics;
		private Insets insets;
		private Graphics graphics2;

		public Window(PixSettings settings) {
			this.settings = settings;
			image = new BufferedImage(settings.getWidth(), settings.getHeight(), BufferedImage.TYPE_INT_RGB);
			frame = new JFrame(settings.getTitle());
			insets = frame.getInsets();
			frame.setSize(0,0);
			frame.setResizable(false);
			//frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setState(Frame.NORMAL);
			//frame.setExtendedState(Frame.MAXIMIZED_BOTH);
			
			
			GraphicsEnvironment graphics2 =
			GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice device = graphics2.getDefaultScreenDevice();
			//device.setFullScreenWindow(frame);

			frame.createBufferStrategy(2);
			bufferStrategy = frame.getBufferStrategy();
			graphics = bufferStrategy.getDrawGraphics();
			frame.addWindowListener(this);
			frame.requestFocus();
		}
			public void update() {
				insets = frame.getInsets();
				if(frame.getWidth() != insets.left + insets.right + (int)(image.getWidth() * getSettings().getScale())) {
					frame.setSize(insets.left + insets.right + (int)(image.getWidth() * getSettings().getScale()), frame.getHeight());
					frame.setLocationRelativeTo(null);
				}
				if(frame.getHeight() != insets.top + insets.bottom + (int)(image.getHeight() * getSettings().getScale())) {
					frame.setSize(frame.getWidth(), insets.top + insets.bottom + (int) (image.getHeight() * getSettings().getScale()));
					frame.setLocationRelativeTo(null);
				}
				do {
					do {
						bufferStrategy = frame.getBufferStrategy();
						if(bufferStrategy == null) {
							frame.createBufferStrategy(2);
							continue;
						}
						graphics = bufferStrategy.getDrawGraphics();
						graphics.drawImage(image, insets.left,
												  insets.top,
								                  (int)(getSettings().getWidth() * getSettings().getScale()),
								                  (int)(image.getHeight() * getSettings().getScale()),
								     null);
						graphics.dispose();
					} while (bufferStrategy.contentsRestored());
					bufferStrategy.show();
				} while (bufferStrategy.contentsLost());
		}

		public void dispose() {
			frame.setVisible(false);
			frame.dispose();
		}

		public BufferedImage getImage() {
			return image;
		}
		

		public void setImage(BufferedImage image) {
			this.image = image;
		}

		@Override
		public void windowOpened(WindowEvent windowEvent) {

		}

		@Override
		public void windowClosing(WindowEvent windowEvent) {
			
			GameContainer.stop();
		}

		@Override
		public void windowClosed(WindowEvent windowEvent) {

		}

		@Override
		public void windowIconified(WindowEvent windowEvent) {

		}

		@Override
		public void windowDeiconified(WindowEvent windowEvent) {

		}

		@Override
		public void windowActivated(WindowEvent windowEvent) {

		}

		@Override
		public void windowDeactivated(WindowEvent windowEvent) {

		}

		public Insets getInsets() {
			return insets;
		}

		public Frame getFrame() {
			return frame;
		}
		public PixSettings getSettings() {
			return settings;
		}
		
		
}

