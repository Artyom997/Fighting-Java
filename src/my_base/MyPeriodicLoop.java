package my_base;




import base.Game;
import base.GameCanvas;
import base.PeriodicLoop;
import my_game.MyCharacter1;
import my_base.MyContent;

public class MyPeriodicLoop extends PeriodicLoop {

	private MyContent content;

	public void setContent(MyContent content) {
		this.content = content;
	}
	
	@Override
	public void execute() {
		// Let the super class do its work first
		super.execute();

		redrawCharacter();

	}
	
	//private void redrawPokimon() {
	//	content.pokimon().move();
	//}

	private void redrawCharacter() {
		
		GameCanvas canvas = Game.UI().canvas();
		
		//TODO
		//Remove the comment from the next line so you can easily 
		//access your character

		MyCharacter1 character = content.character();
		
		//Since this function is called every interval, it will also be called
		//before the character is created. Therefore, we check if the character 
		//exists and if not, we return without doing anything.
		
		//TODO: Remove comments from next 2 lines
		if (character == null)
			return;
		
		//TODO
		//Call the canvas to change the shape properties according to
		//its current property values
		content.character().move();

		//Remove the comment from the next line so the character will change its appearance each time step
		//content.character().changeImage();

		//Remove the comment from the next line so the character will change its location each time step
		//content.character().changeLocation();

	}


}
