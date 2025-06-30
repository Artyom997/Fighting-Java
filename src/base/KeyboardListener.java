
package base;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener {
	public enum Direction {
		RIGHT, LEFT,
		 //STOP, UP, DOWN,
		RIGHT2, LEFT2; 
		//STOP2, UP2, DOWN2;
	}
	public enum Command {
		PUNCH, KICK, BLOCK, WIN, IDLE,
		PUNCH2, KICK2, BLOCK2, WIN2, IDLE2;
	}

	public KeyListener keyListener = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			characterTyped(e.getKeyChar());
		}
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_D:
				directionalKeyPressed(Direction.RIGHT);
				break;
			case KeyEvent.VK_A:
				directionalKeyPressed(Direction.LEFT);
				break;
			//case KeyEvent.VK_UP:
			//	directionalKeyPressed(Direction.UP);
			//	break;
			//case KeyEvent.VK_DOWN:
			//	directionalKeyPressed(Direction.DOWN);
			//	break;
			case KeyEvent.VK_ENTER:
				enterKeyPressed();
				break;
			case KeyEvent.VK_BACK_SPACE:
				backSpaceKeyPressed();
				break;
			case KeyEvent.VK_SPACE:
				spaceKeyPressed();
				break;
			case KeyEvent.VK_G:
				commandKeyPressed(Command.PUNCH);
				break;
			//case KeyEvent.VK_NUMPAD2:
			//	commandKeyPressed(Command.KICK);
			//	break;
			case KeyEvent.VK_H:
				commandKeyPressed(Command.BLOCK);
				break;
			case KeyEvent.VK_J:
				commandKeyPressed(Command.WIN);
				break;
			case KeyEvent.VK_5:
				commandKeyPressed(Command.IDLE);
				break;
				//character 2 moves and commands
			case KeyEvent.VK_RIGHT:
				directionalKeyPressed(Direction.RIGHT2);
				break;
			case KeyEvent.VK_LEFT:
				directionalKeyPressed(Direction.LEFT2);
				break;
			//case KeyEvent.VK_W:
			//	directionalKeyPressed(Direction.UP2);
			//	break;
			//case KeyEvent.VK_X:
			//	directionalKeyPressed(Direction.DOWN2);
			//	break;
			case KeyEvent.VK_NUMPAD1:
				commandKeyPressed(Command.PUNCH2);
				break;
			//case KeyEvent.VK_2:
			//	commandKeyPressed(Command.KICK2);
			//	break;
			case KeyEvent.VK_NUMPAD3:
				commandKeyPressed(Command.BLOCK2);
				break;
			case KeyEvent.VK_NUMPAD0:
				commandKeyPressed(Command.WIN2);
				break;	
			default:
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_D:
				directionalKeyReleased(Direction.RIGHT);
				break;
			case KeyEvent.VK_A:
				directionalKeyReleased(Direction.LEFT);
				break;
			//case KeyEvent.VK_UP:
			//	directionalKeyReleased(Direction.UP);
			//	break;
			//case KeyEvent.VK_DOWN:
			//	directionalKeyReleased(Direction.DOWN);
			//	break;
			case KeyEvent.VK_G:
				commandKeyReleased(Command.PUNCH);
				break;
			//case KeyEvent.VK_NUMPAD2:
			//	commandKeyReleased(Command.KICK);
			//	break;
			case KeyEvent.VK_H:
				commandKeyReleased(Command.BLOCK);
				break;
			case KeyEvent.VK_J:
				commandKeyReleased(Command.WIN);
				break;
			case KeyEvent.VK_5:
				commandKeyReleased(Command.IDLE);
				break;
			case KeyEvent.VK_RIGHT:
				directionalKeyReleased(Direction.RIGHT2);
				break;
			case KeyEvent.VK_LEFT:
				directionalKeyReleased(Direction.LEFT2);
				break;
			case KeyEvent.VK_NUMPAD1:
				commandKeyReleased(Command.PUNCH2);
				break;
			//case KeyEvent.VK_2:
			//	commandKeyReleased(Command.KICK2);
			//	break;
			case KeyEvent.VK_NUMPAD3:
				commandKeyReleased(Command.BLOCK2);
				break;
			case KeyEvent.VK_NUMPAD0:
				commandKeyReleased(Command.WIN2);
				break;
			case KeyEvent.VK_NUMPAD5:
				commandKeyReleased(Command.IDLE2);
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
