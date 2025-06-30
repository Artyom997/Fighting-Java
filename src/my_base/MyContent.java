package my_base;


import my_game.Pokimon;
import ui_elements.ScreenPoint;
//import ui_elements.ScreenPoint;
import base.Game;
import base.GameCanvas;
import base.GameContent;
import base.GameUI;
import my_game.MyCharacter1;
import my_game.MyPolygon;
import my_game.GameControl;
import my_base.InVicinity;
import my_base.LifeBar;
import my_base.PointsBar;
import my_base .TimerBar;

public class MyContent extends GameContent{
	private Pokimon pokimon;
	private MyPolygon myPolygon;
	private GameControl control;
	private MyCharacter1 char1;
	private MyCharacter1 char2;
	private LifeBar char1HP;
	private LifeBar char2HP;
	private PointsBar char1P;
	private PointsBar char2P;
	private String selectedCharacter1;
	private String selectedCharacter2;
	private TimerBar timerBar;
	private ScreenPoint timeScreenPoint;
	
	
	//TODO
	//Declare your own character
	

	@Override
	public void initContent() {
		pokimon = new Pokimon();
		//Create an instance of your character and set its properties with
		//initial values

		char1 = new MyCharacter1(1);
		char1.setVisuals(selectedCharacter1);
		char2 = new MyCharacter1(2);
		char2.setVisuals(selectedCharacter2);
		//char1HP = new LifeBar(LifeBar.getMaxLife());
		//char2HP = new LifeBar(LifeBar.getMaxLife());
		char1HP = new LifeBar(1);
		char2HP = new LifeBar(2);
		char1P = new PointsBar(1);
		char2P = new PointsBar(2);
		timeScreenPoint = new ScreenPoint(400, 30);
		timerBar = new TimerBar();
		control = new GameControl();
		//check if the life bars are initialized correctly
		/*	
		System.out.println("life 1char1hp"+ life(1).getCurrentLife());
		System.out.println("char2hp"+ char2HP.getCurrentLife());
		System.out.println("life 1char1hp"+ life(1).getCurrentLife());
		System.out.println("life 2char1hp"+ life(2).getCurrentLife());
 */

		//ryu.initImage();
	}	
	
	public Pokimon pokimon() {
		return pokimon;
	}
	public GameControl control() {
		return control;
	}
	public MyCharacter1 character(int index) {
		if (index == 1) {
			return char1;
		} 
		else
			return char2;		
	}

	public LifeBar life(int index) {
	if (index == 1) {
	return char1HP;
	} 
		else		
	return char2HP;
	}
 	public TimerBar timerBar() {
		return timerBar;
	}
	public PointsBar points(int index) {
		if (index == 1) {
			return char1P;
		} 
		else
			return char2P;		
	}
/* 
	public void addCharacter(int index) { 
		ryu.addToCanvas(index);
	}
	public void changeCharacter() {
		//TODO
		ryu.changeImage();
		//Create an instance of your character and set its properties with
		//new values.
	}
		
	public void changeLocation() { //David+Artyom
		//TODO
		//ryu.changeLocation();
		//Create an instance of your character and set its properties with
		//new values.
	}
*/
	
    public void setSelectedCharacter1(String name) {
		this.selectedCharacter1 = name;
    }
	public void setSelectedCharacter2(String name) {
        this.selectedCharacter2 = name;
    }

    public String getSelectedCharacter1() {
        return selectedCharacter1;
    }
	public String getSelectedCharacter2() {
        return selectedCharacter1;
    }
	public void restartGame() {
		// Reinitialize the content
		initContent();
		GameUI gameUI = Game.UI();
		gameUI.frame().dispose(); // Clear the canvas for a fresh start
	}
	//TODO
	//create a changeCharacter method and change inside all the properties you like.
}
