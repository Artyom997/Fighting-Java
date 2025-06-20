package my_game;
import my_base.InVicinity;
import my_base.LifeBar;
import my_base.MyContent;
import my_base.PointsBar;
import my_game.MyCharacter1.MyDirection;
//import my_game.MyCharacter1;
//import my_game.MyCharacter1.MyDirection;
//import my_game.MyContent;
//import my_game.InVicinity;
import ui_elements.ScreenPoint;

public class GameControl {
	MyContent content = new MyContent();
	//private static InVicinity vicinity = new InVicinity();	
    MyCharacter1 char1 = content.character(1);
    MyCharacter1 char2 = content.character(2);
	LifeBar char1HP = content.life(1);
    LifeBar char2HP = content.life(2);
	PointsBar char1P = content.points(1);
    PointsBar char2P = content.points(2);
	static int leftBorder = 50;
	static int rightBorder = 850;


	public GameControl() {
	}

	public void gameStep(MyCharacter1 char1, MyCharacter1 char2, LifeBar char1HP, LifeBar char2HP, PointsBar char1P, PointsBar char2P) {
		//System.out.println("game step!!!!!!!!!!!!!!!!!!!!!!!!!");
	
		distanceControl(char1,char2);
		borderControl(char1, char2);
		hitRegistration(char1, char2, char1HP, char2HP, char1P, char2P);

	if (char1HP == null) {
System.out.println("char1HP is null");
}
if (char2HP == null) {
System.out.println("char2HP is null");
}
	System.out.println( " char2hp: " + char2HP.getCurrentLife());

		//hit registration
		//update life bar
		//update score	
		checkGameOver();
	}

	public void checkGameOver() {
		//write your game over logic here
	}
    public static boolean InVicinity(ScreenPoint  p1, ScreenPoint p2) {
        int meleeRadius = 90; // Default collision radius
        double dx = p1.x - p2.x;
        double distance = Math.sqrt(dx * dx);
        return distance <= meleeRadius;
	
	}
    public static void distanceControl(MyCharacter1 char1, MyCharacter1 char2) {
		if (InVicinity(char1.getLocation(1), char2.getLocation(2))&&
		(char1.getPolicy() == MyDirection.RIGHT||
		char2.getPolicy() == MyDirection.LEFT||
		char1.getPolicy() == MyDirection.STOP||char2.getPolicy() == MyDirection.STOP))
		{
			char1.setSpeed(0);
			char2.setSpeed(0);
			if(char1.getPolicy() == MyDirection.LEFT || char2.getPolicy() == MyDirection.RIGHT) {
				char1.setSpeed(2);
				char2.setSpeed(2);
			}
			else 
			{char1.setSpeed(0);
			char2.setSpeed(0);}
    	}
	}
	public static void borderControl(MyCharacter1 char1, MyCharacter1 char2) {
		if (char1.getLocation(1).getX()<=leftBorder)
		{
			char1.setSpeed(0);
			if(char1.getPolicy() == MyDirection.RIGHT) {
				char1.setSpeed(2);
			}
		}
		if (char2.getLocation(2).getX()>=rightBorder)
		{
			char2.setSpeed(0);
			if(char2.getPolicy() == MyDirection.LEFT) {
				char2.setSpeed(2);
			}
		
		}
	}
	public static void hitRegistration(MyCharacter1 char1, MyCharacter1 char2,LifeBar char1HP, LifeBar char2HP, PointsBar char1P, PointsBar char2P) {
		if (InVicinity(char1.getLocation(1), char2.getLocation(2))) {
			if (char1.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char2.getCommandPolicy() == MyCharacter1.MyCommand.BLOCK) {
				System.out.println("Char1: Punch blocked!");
				char2P.increasePoints(50);	
			} 

			else if (char1.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char2.getCommandPolicy() != MyCharacter1.MyCommand.BLOCK) {
				System.out.println("Char1: Punch hit!");
				char1P.increasePoints(100);
				char2HP.decreaseLife(1);
			}

			if (char2.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char1.getCommandPolicy() == MyCharacter1.MyCommand.BLOCK) {
				System.out.println("Char2: Punch blocked!");
				char1P.increasePoints(50);
			}
			
			else if (char2.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char1.getCommandPolicy() != MyCharacter1.MyCommand.BLOCK) {
				System.out.println("Char2: Punch hit!");
				char2P.increasePoints(100);
				char1HP.decreaseLife(1);
			}
		}
		
	}
}