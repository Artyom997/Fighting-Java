package my_base;


import my_game.Pokimon;
import ui_elements.ScreenPoint;
import base.Game;
import base.GameCanvas;
import base.GameContent;
import base.GameUI;
import my_game.MyCharacter1;
import my_game.MyPolygon;

public class MyContent extends GameContent{
	private Pokimon pokimon;
	private MyPolygon myPolygon;
	private MyCharacter1 ryu;
	
	
	//TODO
	//Declare your own character
	

	@Override
	public void initContent() {
		pokimon = new Pokimon();
		//Create an instance of your character and set its properties with
		//initial values
		
		ryu = new MyCharacter1();
		//ryu.initImage();
		ScreenPoint[] points = {
			new ScreenPoint(100, 100),
			new ScreenPoint(130, 50),
			new ScreenPoint(170, 50),
			new ScreenPoint(200, 100),
			new ScreenPoint(220, 170),
			new ScreenPoint(170, 150),
			new ScreenPoint(130, 150)
		};

		myPolygon = new MyPolygon(points);
	}	
	
	public Pokimon pokimon() {
		return pokimon;
	}
	public MyCharacter1 character() { //AVI + DAVID
		return ryu;
	}
		//TODO
		//create a method with the name myCharacter which returns
		//your character for others to use.
	public MyPolygon polygon() {
		return myPolygon;
	}

	public void addCharacter() { //AVI + DAVID
		ryu.addToCanvas();
		//TODO
		
		
		//TODO
		//Add your character visual representation to the canvas using its addToCanvas() method.

	}
	public void changeCharacter() { //David+Artyom
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
	//TODO
	//create a changeCharacter method and change inside all the properties you like.
}
