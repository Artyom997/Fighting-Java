package my_base;

import java.awt.Color;

import base.Game;
import base.GameCanvas;
import base.GameContent;
import base.GameDashboard;
import my_game.Pokimon;
import my_ui_elements.AddButton;
import my_ui_elements.ChangeButton;
import my_ui_elements.DirectionCombo;
import my_ui_elements.EndButton;
import my_ui_elements.MusicButton;
import my_ui_elements.NewGameButton;
import my_game.GameControl;
import my_game.MyCharacter1;
import my_base.InVicinity;

public class MyGame extends Game {
	
	private MyContent content;
	private GameControl control;

	@Override
	protected void initCanvas() {
		
		GameCanvas canvas = gameUI.canvas();
		canvas.setMouseHandler(Game.MouseHandler());
		canvas.setBackground(Color.WHITE);
		canvas.setBackgroundImage("resources/map.png");

		//Initialize the character and add it to the canvas
		//the character is initialized inside MyContent
		MyCharacter1 char1 = content.character(1);
		MyCharacter1 char2 = content.character(2);
		//Pokimon pokimon = content.pokimon();
		char1.addToCanvas(1);
		char2.addToCanvas(2);
	}
	
	@Override
	protected void initDashboard() {
		super.initDashboard();
		GameDashboard dashboard = gameUI.dashboard();
		
		dashboard.setBackground(Color.BLACK);
		
		// Add a the New Game button
		dashboard.addUIElement(new NewGameButton("newGameButton", "New Game", 60, 40));
		dashboard.addUIElement(new EndButton("btnEND", "End Game", 150, 40, 60, 90));
		//dashboard.addUIElement(new RotatePolygonButton("rotateButton", "Rotate", 60, 100));

		// Add a the Circle drag checkbox
		//dashboard.addUIElement(new DragCircleCB("dragCB", "Drag Circle", 280, 80, 200, 40, false));

		// Add a the direction list combo
		dashboard.addUIElement(new DirectionCombo(280, 40));

		// Add a the AddButton button
		dashboard.addUIElement(new AddButton("addButton", "Add", 540, 100));
		
		// Add the ChangeButton button to the dashboard
		dashboard.addUIElement(new ChangeButton("changeButton", "Change", 540, 40));
		dashboard.addUIElement(new MusicButton("musicButton", "Play", 700, 40));

		//dashboard.addUIElement(new GetNameButton("btnName", "Get Name", 130, 40, 800, 100));
	}
	
	@Override
	public void setGameContent(GameContent content) {
		// Call the Game superclass to set its content 
		super.setGameContent(content);
		// point to the content with a variable of type MyContent so we have access to all
		// our game specific data

		this.content = (MyContent) content;
		control = new GameControl();
	}
	
	public MyContent getContent() {
		return this.content;
	}
	
	public static void main(String[] args) {
		MyGame game = new MyGame();
		game.setGameContent(new MyContent());
		MyPeriodicLoop periodicLoop = new MyPeriodicLoop();
		periodicLoop.setContent(game.getContent());
		game.setPeriodicLoop(periodicLoop);
		game.setMouseHandler(new MyMouseHandler());
		game.setKeyboardListener(new MyKeyboardListener());
		game.initGame();
	}
}