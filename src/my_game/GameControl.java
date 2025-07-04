package my_game;
import base.Game;
import my_base.LifeBar;
import my_base.MyContent;
import my_base.MyGame;
import my_base.PointsBar;
import my_base.TimerBar;
import my_game.MyCharacter1.MyDirection;
import ui_elements.ScreenPoint;

public class GameControl {
	static int leftBorder = 50;
	static int rightBorder = 850;
	long time = System.currentTimeMillis();
	private static long lastHitTimeChar1 = 0; // last time char1 hit char2
	private static long lastHitTimeChar2 = 0; // last time char2 hit char1
	private static final long HIT_COOLDOWN_MS = 700; // time period for a next hit to be registered, 0.5 seconds
	static int gameOverCondition;
	public int flag = 0; // flag to check if game is over, 1 means game is not over, 0 means game is over

	public GameControl() {
	}

	public void gameStep(MyCharacter1 char1, MyCharacter1 char2, LifeBar char1HP, LifeBar char2HP, PointsBar char1P, PointsBar char2P, TimerBar timerBar) {
		distanceControl(char1,char2);
		borderControl(char1, char2);
		moveUpdate(char1, char2);
		commandUpdate(char1, char2);
		hitRegistration(char1, char2, char1HP, char2HP, char1P, char2P);
		char1P.addToCanvas();
		char2P.addToCanvas();
		timerBar.addToCanvas();	
		checkGameOver(timerBar, char1HP, char2HP, char1, char2);
	}

	public void checkGameOver(TimerBar timerBar, LifeBar char1HP, LifeBar char2HP, MyCharacter1 char1, MyCharacter1 char2) {
		if (timerBar.getSeconds() == 0 || char1HP.getCurrentLife() == 0 || char2HP.getCurrentLife() == 0) {
			if (timerBar.getSeconds() == 0) {
				if(char1HP.getCurrentLife() > char2HP.getCurrentLife()){
					switch(char1.getCharName()) {
						case "Ryu":
							gameOverCondition = 1; // Player one as RYU wins by points
							break;
						case "Ken":
							gameOverCondition = 2; // Player one as KEN wins by points
							break;
					}
				}
				else if (char1HP.getCurrentLife() < char2HP.getCurrentLife()){
					switch(char2.getCharName()) {
						case "Ryu":
							gameOverCondition = 3; // Player two as RYU wins by points
							break;
						case "Ken":
							gameOverCondition = 4; // Player two as KEN wins by points
							break;
						}
				}
				else{gameOverCondition = 9;}  //A draw, no one wins
			}
			else if (char1HP.getCurrentLife() == 0&&timerBar.getSeconds()>0) {
				switch(char2.getCharName()) {
					case "Ryu":
						gameOverCondition = 5; // Player two as RYU wins by KO
						break;
					case "Ken":
						gameOverCondition = 6; // Player two as KEN wins by KO
						break;
					}
				}
			else if (char2HP.getCurrentLife() == 0&&timerBar.getSeconds()>0) {
				switch(char1.getCharName()) {
					case "Ryu":
						gameOverCondition = 7; // Player one as RYU wins by KO
						break;
					case "Ken":
						gameOverCondition = 8; // Player one as KEN wins by KO
						break;
				}
			}
			if(flag == 0){
				MyGame.notifyGameEnd(gameOverCondition);
				flag++;
			}
		}
	}	
	public static int getGameOverCondition() {
		return gameOverCondition;
	}
	public static void moveUpdate(MyCharacter1 char1, MyCharacter1 char2) {
		javax.swing.SwingUtilities.invokeLater(() -> {
            char1.move(1, char1.getDirectionPolicy());
			char2.move(2, char2.getDirectionPolicy());
		});
	}
	public static void commandUpdate(MyCharacter1 char1, MyCharacter1 char2) {
		javax.swing.SwingUtilities.invokeLater(() -> {
            char1.command(1, char1.getCommandPolicy());
			char2.command(2, char2.getCommandPolicy());
		});
	}


    public static boolean InVicinity(ScreenPoint  p1, ScreenPoint p2) {
        int meleeRadius = 90; // Default collision radius
        double dx = p1.x - p2.x;
        double distance = Math.sqrt(dx * dx);
        return distance <= meleeRadius;
	}

    public static void distanceControl(MyCharacter1 char1, MyCharacter1 char2) {
		if (InVicinity(char1.getLocation(1), char2.getLocation(2))&&
		(char1.getDirectionPolicy() == MyDirection.RIGHT||
		char2.getDirectionPolicy() == MyDirection.LEFT||
		char1.getDirectionPolicy() == MyDirection.STOP||
		char2.getDirectionPolicy() == MyDirection.STOP))
		{
			char1.setSpeed(0);
			char2.setSpeed(0);
			if(char1.getDirectionPolicy() == MyDirection.LEFT){
				char1.setSpeed(MyCharacter1.getDefSpeed());}
			else if (char2.getDirectionPolicy() == MyDirection.RIGHT){
				char2.setSpeed(MyCharacter1.getDefSpeed());}
			else{char1.setSpeed(0);
			char2.setSpeed(0);}
    	}
		else{char1.setSpeed(MyCharacter1.getDefSpeed());
			char2.setSpeed(MyCharacter1.getDefSpeed());}
	}
	
	public static void borderControl(MyCharacter1 char1, MyCharacter1 char2) {
		if (char1.getLocation(1).getX()<=leftBorder)
		{
			char1.setSpeed(0);
			if(char1.getDirectionPolicy() == MyDirection.RIGHT) {
				char1.setSpeed(MyCharacter1.getDefSpeed());
			}
		}
		if (char2.getLocation(2).getX()>=rightBorder)
		{
			char2.setSpeed(0);
			if(char2.getDirectionPolicy() == MyDirection.LEFT) {
				char2.setSpeed(MyCharacter1.getDefSpeed());
			}
		
		}
	}
	public static void hitRegistration(MyCharacter1 char1, MyCharacter1 char2,LifeBar char1HP, LifeBar char2HP, PointsBar char1P, PointsBar char2P) {
		long now = System.currentTimeMillis();
		javax.swing.SwingUtilities.invokeLater(() -> {
		if (InVicinity(char1.getLocation(1), char2.getLocation(2))) {
			if (char1.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char2.getCommandPolicy() == MyCharacter1.MyCommand.BLOCK) {
				if (now - lastHitTimeChar1 >= HIT_COOLDOWN_MS) {
				System.out.println("Char1: Punch blocked!");
				char2P.addPoints(70);
				lastHitTimeChar1 = now;
				}
			} 

			else if (char1.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char2.getCommandPolicy() != MyCharacter1.MyCommand.BLOCK) {
				if (now - lastHitTimeChar1 >= HIT_COOLDOWN_MS) {
				System.out.println("Char1: Punch hit!");
				char1P.addPoints(100);
				char2HP.decreaseLife(1);
				char2HP.changeImage();
				lastHitTimeChar1 = now;
				}
			}

			if (char2.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char1.getCommandPolicy() == MyCharacter1.MyCommand.BLOCK) {
				if (now - lastHitTimeChar2 >= HIT_COOLDOWN_MS) {
				System.out.println("Char2: Punch blocked!");
				char1P.addPoints(70);
				lastHitTimeChar2 = now;
				}
			}
			
			else if (char2.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char1.getCommandPolicy() != MyCharacter1.MyCommand.BLOCK) {
			if (now - lastHitTimeChar2 >= HIT_COOLDOWN_MS) {
				System.out.println("Char2: Punch hit!");
				char2P.addPoints(100);
				char1HP.decreaseLife(1);
				char1HP.changeImage();
				lastHitTimeChar2 = now;
				}
			}
		}
	});
	}
}