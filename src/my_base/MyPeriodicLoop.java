package my_base;

import base.Game;
import base.GameCanvas;
import base.GameDashboard;
import base.PeriodicLoop;
import my_game.MyCharacter1;
import my_game.MyCharacter1.MyDirection;
import my_base.MyContent; 
import my_base.InVicinity;

public class MyPeriodicLoop extends PeriodicLoop {
	private InVicinity melee = new InVicinity();
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
		MyCharacter1 char1 = content.character(1);
		MyCharacter1 char2 = content.character(2);
		if (char1 == null || char2 == null) return;

		// Check if characters are in melee range, test section
		/*
		if (melee.inMelee(char1.getLocation(1), char2.getLocation(2))&&
		(char1.getPolicy() == MyDirection.RIGHT||char2.getPolicy() == MyDirection.LEFT||char1.getPolicy() == MyDirection.STOP||char2.getPolicy() == MyDirection.STOP))
		 {
			System.out.println("In Melee Range!!!!!!!!!!!!!!!!!!!!!!!!!");
			char1.setSpeed(0);
			char2.setSpeed(0);
			if(char1.getPolicy() == MyDirection.LEFT || char2.getPolicy() == MyDirection.RIGHT) {
				char1.setSpeed(2);
				char2.setSpeed(2);
			}
		}
		else {
			char1.setSpeed(2);
			char2.setSpeed(2);
		}
		*/
		//System.out.println("In Melee Range!!!!!!!!!!!!!!!!!!!!!!!!!");
		//System.out.println("The state of Ermak is" + ermak.getPolicy());
	}
}