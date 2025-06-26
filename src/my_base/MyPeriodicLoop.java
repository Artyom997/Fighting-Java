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
import my_base.LifeBar;
import my_base.PointsBar;
import my_base.TimerBar;

public class MyPeriodicLoop extends PeriodicLoop {
	private MyContent content;
	MyCharacter1 char1;
	MyCharacter1 char2;
	LifeBar char1HP;
	LifeBar char2HP;
	PointsBar char1P;
	PointsBar char2P;
	TimerBar timerBar;



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
		char1HP = updateLifeBar(1);
		char2HP = updateLifeBar(2);
		char1P = updatePointsBar(1);
		char2P = updatePointsBar(2);
		timerBar = updateTimerBar();
		content.control().gameStep(char1, char2,char1HP, char2HP, char1P, char2P,timerBar);
		//}
		//System.out.println("check2!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	private MyCharacter1 updateCharacter1() {
		GameCanvas canvas = Game.UI().canvas();
		char1 = content.character(1);
		return char1;
	}
	private MyCharacter1 updateCharacter2() {
		GameCanvas canvas = Game.UI().canvas();
		char2 = content.character(2);
		return char2;
	}
	private LifeBar updateLifeBar(int i) {
		GameCanvas canvas = Game.UI().canvas();
		if(i==1){
			char1HP = content.life(1);
			return char1HP;
		}
		else{
			char2HP = content.life(2);
			return char2HP;
		}
	}

	private PointsBar updatePointsBar(int i) {
		if(i==1){
			char1P = content.points(1);
			return char1P;
		}
		else{
			char2P = content.points(2);
			return char2P;
		}
	}
	private TimerBar updateTimerBar() {
		GameCanvas canvas = Game.UI().canvas();
		TimerBar timerBar = content.timerBar();
		return timerBar;
	}

}