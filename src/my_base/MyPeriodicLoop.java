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
		MyCharacter1 ryu = content.character(1);
		MyCharacter1 ermak = content.character(2);
		//Since this function is called every interval, it will also be called
		//before the character is created. Therefore, we check if the character 
		//exists and if not, we return without doing anything.
		if (ryu == null|| ermak == null) {
			return;
		}
		if (melee.inMelee(ryu.getLocation(1), ermak.getLocation(2))) {
			System.out.println("In Melee Range!!!!!!!!!!!!!!!!!!!!!!!!!");
		} 
	}
}