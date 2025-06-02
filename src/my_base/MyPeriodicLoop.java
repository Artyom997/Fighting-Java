package my_base;

import base.Game;
import base.GameCanvas;
import base.PeriodicLoop;
import my_game.MyCharacter1;
import my_base.MyContent; 

public class MyPeriodicLoop extends PeriodicLoop {

	private MyContent content;

	public void setContent(MyContent content) {
		this.content = content;
	}
	
	@Override
	public void execute() {
		// Let the super class do its work first
		super.execute();
		redrawCharacter();
	}

	private void redrawCharacter() {
		GameCanvas canvas = Game.UI().canvas();
		MyCharacter1 character = content.character();
		//Since this function is called every interval, it will also be called
		//before the character is created. Therefore, we check if the character 
		//exists and if not, we return without doing anything.
		if (character == null)
			return;
	}


}
