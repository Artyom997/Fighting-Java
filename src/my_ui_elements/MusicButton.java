package my_ui_elements;

import base.Game;
import base.AudioPlayer.MusicStatus;
import ui_elements.GameImageButton;

public class MusicButton extends GameImageButton{
	
	public MusicButton(String id, String name, int posX, int posY) {
		super(id, name, 70, 60, posX, posY, "resources/play.jpg");
	}

	@Override
	public void action() {
		// The basic buttonAction prints the id of the button to the console.
		// Keep the call to super to preserve this behavior or remove it if you don't want the printing.
		super.action();
		if (Game.audioPlayer().getStatus() == MusicStatus.STOPPED) {
			Game.audioPlayer().play("resources/audio/audio_sample.wav", 0);
			this.setImage("resources/stop.jpg");
			//this.setText("Stop");
			Game.UI().canvas().setBackgroundImage("resources/background2.jpg");
		}
		else {
			Game.audioPlayer().stop();
			this.setImage("resources/play.jpg");
			//this.setText("Play");
			Game.UI().canvas().setBackgroundImage("resources/background1.jpg");
		}
	}

}
