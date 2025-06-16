package my_game;
import my_base.InVicinity;
import my_base.MyContent;
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
	static int leftBorder = 50;
	static int rightBorder = 850;
    // Returns the distance between two characters

	public GameControl() {
	}

	public void gameStep(MyCharacter1 char1, MyCharacter1 char2) {
		//System.out.println("game step!!!!!!!!!!!!!!!!!!!!!!!!!");
		distanceControl(char1,char2);
		borderControl(char1, char2);
		hitRegistration(char1, char2);
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
	public static void hitRegistration(MyCharacter1 char1, MyCharacter1 char2) {
		if (InVicinity(char1.getLocation(1), char2.getLocation(2))) {
			//Left Character attacks
			//Successful Punch
			if (char1.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char2.getCommandPolicy() == MyCharacter1.MyCommand.BLOCK) {
				System.out.println("Char1: Punch blocked!");
			} else if (char1.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char2.getCommandPolicy() != MyCharacter1.MyCommand.BLOCK) {
				System.out.println("Char1: Punch hit!");
				// Update life bar or score here
			}
			//Left Character attacks
			//Successful Punch
			if (char2.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char1.getCommandPolicy() == MyCharacter1.MyCommand.BLOCK) {
				System.out.println("Char2: Punch blocked!");
			} else if (char2.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char1.getCommandPolicy() != MyCharacter1.MyCommand.BLOCK) {
				System.out.println("Char2: Punch hit!");
				// Update life bar or score here
			}
			// Add more hit registration logic as needed
		}
	}
}