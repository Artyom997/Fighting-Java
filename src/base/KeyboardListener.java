
package base;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import my_game.MyCharacter1.Command;

public class KeyboardListener {
	public enum Direction {
		RIGHT, LEFT, UP, DOWN, STOP;
	}

	public KeyListener keyListener = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			characterTyped(e.getKeyChar());
		}
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				directionalKeyPressed(Direction.RIGHT);
				break;
			case KeyEvent.VK_LEFT:
				directionalKeyPressed(Direction.LEFT);
				break;
			case KeyEvent.VK_UP:
				directionalKeyPressed(Direction.UP);
				break;
			case KeyEvent.VK_DOWN:
				directionalKeyPressed(Direction.DOWN);
				break;
			case KeyEvent.VK_ENTER:
				enterKeyPressed();
				break;
			case KeyEvent.VK_BACK_SPACE:
				backSpaceKeyPressed();
				break;
			case KeyEvent.VK_SPACE:
				spaceKeyPressed();
				break;
			case KeyEvent.VK_NUMPAD1:
				commandKeyPressed(Command.PUNCH);
				break;
			case KeyEvent.VK_NUMPAD2:
				commandKeyPressed(Command.KICK);
				break;
			case KeyEvent.VK_NUMPAD3:
				commandKeyPressed(Command.BLOCK);
				break;
			case KeyEvent.VK_NUMPAD0:
				commandKeyPressed(Command.WIN);
				break;
			case KeyEvent.VK_NUMPAD4:
				commandKeyPressed(Command.IDLE);
				break;
			default:
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				directionalKeyReleased(Direction.RIGHT);
				break;
			case KeyEvent.VK_LEFT:
				directionalKeyReleased(Direction.LEFT);
				break;
			case KeyEvent.VK_UP:
				directionalKeyReleased(Direction.UP);
				break;
			case KeyEvent.VK_DOWN:
				directionalKeyReleased(Direction.DOWN);
				break;
				case KeyEvent.VK_NUMPAD1:
				commandKeyReleased(Command.PUNCH);
				break;
			case KeyEvent.VK_NUMPAD2:
				commandKeyReleased(Command.KICK);
				break;
			case KeyEvent.VK_NUMPAD3:
				commandKeyReleased(Command.BLOCK);
				break;
			case KeyEvent.VK_NUMPAD0:
				commandKeyReleased(Command.WIN);
				break;
			case KeyEvent.VK_NUMPAD4:
				commandKeyReleased(Command.IDLE);
				break;
			default:
			}
		}
	};

	protected GameContent content;

	public KeyboardListener() {
		this.content = Game.Content();
	}
	// This function is a placeholder and should be overridden in derived specific
	// buttons
	public void directionalKeyPressed(Direction direction) {
	}
	// This function is a placeholder and should be overridden in derived specific
	// buttons
	public void directionalKeyReleased(Direction direction) {
	}
	// This function is a placeholder and should be overridden in derived specific
	// buttons
	public void commandKeyPressed(Command command) {
	}
	// This function is a placeholder and should be overridden in derived specific
	// buttons
	public void commandKeyReleased(Command command) {
	}
	// This function is a placeholder and should be overridden in derived specific
	// buttons
	public void characterTyped(char c) {
	}
	
	// This function is a placeholder and should be overridden in derived specific
	// buttons
	public void enterKeyPressed() {
	}
	
	// This function is a placeholder and should be overridden in derived specific
	// buttons
	public void backSpaceKeyPressed() {
	}
	
	// This function is a placeholder and should be overridden in derived specific
	// buttons
	public void spaceKeyPressed() {
	}
	
	// This function is a placeholder and should be overridden in derived specific
		// buttons
	public void otherKeyPressed(KeyEvent e) {
	}
}
