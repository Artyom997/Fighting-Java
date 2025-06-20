package my_base;

//import my_game.Pokimon;
import my_game.MyCharacter1;
import my_ui_elements.DirectionCombo;

import java.awt.event.KeyEvent;

import base.Game;
import base.KeyboardListener;

public class MyKeyboardListener extends KeyboardListener{

	private MyContent myContent;
	
	public MyKeyboardListener() {
		super();
		myContent = (MyContent) this.content;
	}
	@Override
	public void commandKeyPressed(Command command) {
		switch (command) {
		  //comand for character 1
			case PUNCH:
			  myContent.character(1).setCommandPolicy(MyCharacter1.MyCommand.PUNCH);
			  myContent.character(1).command(MyCharacter1.MyCommand.PUNCH);
			  break;
		  case KICK:
			  myContent.character(1).setCommandPolicy(MyCharacter1.MyCommand.KICK);
			  myContent.character(1).command(MyCharacter1.MyCommand.KICK);
			  break;
		  case BLOCK:
			  myContent.character(1).setCommandPolicy(MyCharacter1.MyCommand.BLOCK);
			  myContent.character(1).command(MyCharacter1.MyCommand.BLOCK);
			  break;
		  case WIN:
			  myContent.character(1).setCommandPolicy(MyCharacter1.MyCommand.WIN);
			  myContent.character(1).command(MyCharacter1.MyCommand.WIN);
			  break;
			
			//command for character 2
		  case PUNCH2:
			  myContent.character(2).setCommandPolicy(MyCharacter1.MyCommand.PUNCH);
			  myContent.character(2).command(MyCharacter1.MyCommand.PUNCH);
			  break;
		  case KICK2:
			  myContent.character(2).setCommandPolicy(MyCharacter1.MyCommand.KICK);
			  myContent.character(2).command(MyCharacter1.MyCommand.KICK);
			  break;
		  case BLOCK2:
			  myContent.character(2).setCommandPolicy(MyCharacter1.MyCommand.BLOCK);
			  myContent.character(2).command(MyCharacter1.MyCommand.BLOCK);
			  break;
		  case WIN2:
			  myContent.character(2).setCommandPolicy(MyCharacter1.MyCommand.WIN);
			  myContent.character(2).command(MyCharacter1.MyCommand.WIN);
			  break;
		  default:
			  myContent.character(1).setCommandPolicy(MyCharacter1.MyCommand.IDLE);
			  myContent.character(1).command(MyCharacter1.MyCommand.IDLE);
			  myContent.character(2).setCommandPolicy(MyCharacter1.MyCommand.IDLE);
			  myContent.character(2).command(MyCharacter1.MyCommand.IDLE);
			  break;
			
		}
	}
	@Override
	public void commandKeyReleased(Command command) {
		switch (command) {
		  case PUNCH:
			  myContent.character(1).setCommandPolicy(MyCharacter1.MyCommand.IDLE);
			  myContent.character(1).command(MyCharacter1.MyCommand.IDLE);
			  break;
		  case KICK:
			  myContent.character(1).setCommandPolicy(MyCharacter1.MyCommand.IDLE);
			  myContent.character(1).command(MyCharacter1.MyCommand.IDLE);
			  break;
		  case BLOCK:
			  myContent.character(1).setCommandPolicy(MyCharacter1.MyCommand.IDLE);
			  myContent.character(1).command(MyCharacter1.MyCommand.IDLE);
			  break;
		  case WIN:
			  myContent.character(1).setCommandPolicy(MyCharacter1.MyCommand.IDLE);
			  myContent.character(1).command(MyCharacter1.MyCommand.IDLE);
			  break;

		//command dor character 2
		  case PUNCH2:
			  myContent.character(2).setCommandPolicy(MyCharacter1.MyCommand.IDLE);
			  myContent.character(2).command(MyCharacter1.MyCommand.IDLE);
			  break;
		  case KICK2:
			  myContent.character(2).setCommandPolicy(MyCharacter1.MyCommand.IDLE);
			  myContent.character(2).command(MyCharacter1.MyCommand.IDLE);
			  break;
		  case BLOCK2:
			  myContent.character(2).setCommandPolicy(MyCharacter1.MyCommand.IDLE);
			  myContent.character(2).command(MyCharacter1.MyCommand.IDLE);
			  break;
		  case WIN2:
			  myContent.character(2).setCommandPolicy(MyCharacter1.MyCommand.IDLE);
			  myContent.character(2).command(MyCharacter1.MyCommand.IDLE);
			  break;
		  default:
		}
	}
	@Override
	public void directionalKeyPressed(Direction direction) {
		switch (direction) {
		  case RIGHT:
			  myContent.character(1).setDirectionPolicy(MyCharacter1.MyDirection.RIGHT);
			  myContent.character(1).move(1,MyCharacter1.MyDirection.RIGHT);
			  //((DirectionCombo) (Game.UI().dashboard().getUIElement("directionCombo"))).setDirection("Right");
			  break;
		  case LEFT:
			  myContent.character(1).setDirectionPolicy(MyCharacter1.MyDirection.LEFT);
			  myContent.character(1).move(1,MyCharacter1.MyDirection.LEFT);
			  //((DirectionCombo) (Game.UI().dashboard().getUIElement("directionCombo"))).setDirection("Left");
			  break;

			  //direction for character 2
		  case RIGHT2:
			  myContent.character(2).setDirectionPolicy(MyCharacter1.MyDirection.RIGHT);
			  myContent.character(2).move(2,MyCharacter1.MyDirection.RIGHT);
			  //((DirectionCombo) (Game.UI().dashboard().getUIElement("directionCombo"))).setDirection("Right");
			  break;
		  case LEFT2:
			  myContent.character(2).setDirectionPolicy(MyCharacter1.MyDirection.LEFT);
			  myContent.character(2).move(2,MyCharacter1.MyDirection.LEFT);
			  //((DirectionCombo) (Game.UI().dashboard().getUIElement("directionCombo"))).setDirection("Left");
			  break;
		}
	}
	@Override
	public void directionalKeyReleased(Direction direction) {
		switch (direction) {
		  case RIGHT:
		  case LEFT:
			  myContent.character(1).setDirectionPolicy(MyCharacter1.MyDirection.STOP);
			  myContent.character(1).move(1,MyCharacter1.MyDirection.STOP);
			  //((DirectionCombo) (Game.UI().dashboard().getUIElement("directionC ombo"))).setDirection("Right");
			 // break;
		  //case LEFT:
			  myContent.character(1).setDirectionPolicy(MyCharacter1.MyDirection.STOP);
			  myContent.character(1).move(1,MyCharacter1.MyDirection.STOP);
			  //((DirectionCombo) (Game.UI().dashboard().getUIElement("directionCombo"))).setDirection("Left");
			  break;

		//direction for character 2
		   case RIGHT2:
		   case LEFT2:
			  myContent.character(2).setDirectionPolicy(MyCharacter1.MyDirection.STOP);
			  myContent.character(2).move(2,MyCharacter1.MyDirection.STOP);
			  //((DirectionCombo) (Game.UI().dashboard().getUIElement("directionCombo"))).setDirection("Right");
			  //break;
		   //case LEFT2:
			  myContent.character(2).setDirectionPolicy(MyCharacter1.MyDirection.STOP);
			  myContent.character(2).move(2,MyCharacter1.MyDirection.STOP);
			  //((DirectionCombo) (Game.UI().dashboard().getUIElement("directionCombo"))).setDirection("Left");
			  break;
		}
	}

	@Override
	public void characterTyped(char c) {
		//System.out.println("key character = '" + c + "'" + " pressed.");
	}
	
	@Override
	public void enterKeyPressed() {
		//System.out.println("enter key pressed.");
	}
	
	@Override
	public void backSpaceKeyPressed() {
		//System.out.println("backSpace key pressed.");
	}
	
	@Override
	public void spaceKeyPressed() {
		//System.out.println("space key pressed.");
	}
	
	public void otherKeyPressed(KeyEvent e) {
		//System.out.println("other key pressed. type:" + e);
	}
}
