package my_base;

import java.awt.Color;
import java.awt.*;

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
import my_game.CharacterSelectFrame;
import my_game.GameOverFrame;
import my_game.GameControl;
import my_game.MyCharacter1;
import my_base.LifeBar;
import my_base.PointsBar;

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
		LifeBar char1HP = content.life(1);
		LifeBar char2HP = content.life(2);
		PointsBar char1P = content.points(1);
		PointsBar char2P = content.points(2);
		TimerBar timerBar = content.timerBar();
		timerBar.start();

		//Pokimon pokimon = content.pokimon();
		char1.addToCanvas(1);
		char2.addToCanvas(2);
		char1HP.addToCanvas(1);
		char2HP.addToCanvas(2);
		char1P.addToCanvas();
		char2P.addToCanvas();	
		timerBar.addToCanvas();
	}
	/* 
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
	*/
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
	public interface EndGameListener {
    	void onGameEnd(int endGameCondition);
	}
	private static EndGameListener endGameListener;

	public void setEndGameListener(EndGameListener listener) {
		this.endGameListener = listener;
	}

	public static void notifyGameEnd(int endGameCondition) {
		if (endGameListener != null) {
			endGameListener.onGameEnd(endGameCondition);
		}
	}
	public static void main(String[] args) {
		// Create a new game UI, named "My Game" with a size of 1080 x 720 pixels
		        javax.swing.SwingUtilities.invokeLater(() -> {
					MyGame game = new MyGame();
					CharacterSelectFrame selectFrame = new CharacterSelectFrame(characterNames -> {
						// After character is selected, set up the game
						MyContent content = new MyContent();
						content.setSelectedCharacter1(characterNames[0]); // Store the selection
						content.setSelectedCharacter2(characterNames[1]); // Store the selection
						game.setGameContent(content);
						MyPeriodicLoop periodicLoop = new MyPeriodicLoop();
						periodicLoop.setContent(game.getContent());
						game.setPeriodicLoop(periodicLoop);
						game.setMouseHandler(new MyMouseHandler());
						game.setKeyboardListener(new MyKeyboardListener());
						game.initGame();
						Game.gameUI.frame().setVisible(true); // Show the main game frame
            		});
            		selectFrame.setVisible(true);
					game.setEndGameListener(endGameCondition -> {
						selectFrame.setVisible(false);
						Game.UI().frame().setVisible(false);
					// Show the game over frame with the end game condition
						GameOverFrame gameOverFrame = new GameOverFrame(() -> {
							game.getContent().restartGame();
							Game.UI().frame().setVisible(false);
							// Handle new game selection
							selectFrame.setVisible(true);
						});
						gameOverFrame.setVisible(true);
        });
		});
		/* 
		MyGame game = new MyGame();	
		game.setGameContent(new MyContent());
		MyPeriodicLoop periodicLoop = new MyPeriodicLoop();
		periodicLoop.setContent(game.getContent());
		game.setPeriodicLoop(periodicLoop);
		game.setMouseHandler(new MyMouseHandler());
		game.setKeyboardListener(new MyKeyboardListener());
		game.initGame();
		*/
	}
}