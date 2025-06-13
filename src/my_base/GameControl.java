package my_base;

import my_game.MyCharacter1;
import my_game.MyCharacter1.MyDirection;
//import my_game.MyContent;
//import my_game.InVicinity;
import ui_elements.ScreenPoint;

public class GameControl {
	static private MyContent content;
	private static InVicinity melee;
    static MyCharacter1 char1 = content.character(1);
    static MyCharacter1 char2 = content.character(2);
    // Returns the distance between two characters

	

    public static void meleeControl() {
        melee = new InVicinity();
		// Move according to policy
		if (melee.inMelee(char1.getLocation(1), char2.getLocation(2))&&
		(char1.getPolicy() == MyDirection.RIGHT||
		char2.getPolicy() == MyDirection.LEFT||
		char1.getPolicy() == MyDirection.STOP||char2.getPolicy() == MyDirection.STOP))
		{
			System.out.println("In Melee Range!!!!!!!!!!!!!!!!!!!!!!!!!");
			char1.setSpeed(0);
			char2.setSpeed(0);
			if(char1.getPolicy() == MyDirection.LEFT || char2.getPolicy() == MyDirection.RIGHT) {
				char1.setSpeed(2);
				char2.setSpeed(2);
			}
			else 
			{char1.setSpeed(2);
			char2.setSpeed(2);}
    	}
	}
}