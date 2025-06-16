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
	MyCharacter1 char1;
	MyCharacter1 char2;

	public void setContent(MyContent content) {
		this.content = content;
	}
	
	@Override
	public void execute() {
		// Let the super class do its work first
		super.execute();

		//if(content.control()!=null){
		char1 = updateCharacter1();
		char2 = updateCharacter2();
		content.control().gameStep(char1, char2);
		//}
		//System.out.println("check2!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	private MyCharacter1 updateCharacter1() {
		GameCanvas canvas = Game.UI().canvas();
		char1 = content.character(1);
		//char2 = content.character(2);
		return char1;
		//if (char1 == null || char2 == null) return;
	}
	private MyCharacter1 updateCharacter2() {
		GameCanvas canvas = Game.UI().canvas();
		char2 = content.character(2);
		return char2;
		//if (char1 == null || char2 == null) return;
	}
}