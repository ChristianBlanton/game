package audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundClip {

	private Clip clip = null;
	private FloatControl gainControl;

	public SoundClip(String path) {
		try {
			InputStream audioSrc = SoundClip.class.getResourceAsStream(path);
			InputStream bufferedIn = new BufferedInputStream(audioSrc);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
					baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);

			setClip(AudioSystem.getClip());
			getClip().open(dais);

			gainControl = (FloatControl) getClip().getControl(FloatControl.Type.MASTER_GAIN);

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void play() {
		if(clip.isRunning())
			return;
		if (clip == null)
			return;
		stop();
		clip.setFramePosition(0);
		clip.start();

	}

	public void stop() {
		if (clip.isRunning()) {
			clip.stop();
		}
		//System.out.println("here");

	}

	public void close() {
		clip.stop();
		clip.drain();
		clip.close();
	}

	public void loop() {
		getClip().loop(getClip().LOOP_CONTINUOUSLY);
		play();
	}

	public void setVolume(float value) {
		gainControl.setValue(value);
	}

	public boolean isRunning() {
		return getClip().isRunning();
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}
}
