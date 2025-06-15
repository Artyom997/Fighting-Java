package my_base;

import base.Game;
import base.GameCanvas;
import base.GameDashboard;
import base.PeriodicLoop;
import my_game.GameControl;
import my_game.MyCharacter1;
import my_game.MyCharacter1.MyDirection;
import my_base.MyContent; 
import my_base.InVicinity;

public class MyPeriodicLoop extends PeriodicLoop {
	private MyContent content;

	public void setContent(MyContent content) {
		this.content = content;
	}
	
	@Override
	public void execute() {
		// Let the super class do its work first
		super.execute();

		if(content.control()!=null){
		content.control().gameStep();
		}
		//redrawCharacter();
	}
	private void redrawCharacter() {
		GameCanvas canvas = Game.UI().canvas();
		MyCharacter1 char1 = content.character(1);
		MyCharacter1 char2 = content.character(2);
		if (char1 == null || char2 == null) return;
		
	}
}