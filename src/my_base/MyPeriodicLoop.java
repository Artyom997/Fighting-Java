package my_base;

import base.Game;
import base.GameCanvas;
import base.GameDashboard;
import base.PeriodicLoop;
import my_game.MyCharacter1;
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

		// Move both characters according to their current direction policy
		//char1.move(1, char1.getPolicy());
		//char2.move(2, char2.getPolicy());

		// React to command policy (e.g., punch, kick, block, idle)
		//char1.command(char1.getCommandPolicy());
		//char2.command(char2.getCommandPolicy());
		if (melee.inMelee(char1.getLocation(1), char2.getLocation(2))) {
			System.out.println("In Melee Range!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
		//System.out.println("The state of Ermak is" + ermak.getPolicy());
	}
}