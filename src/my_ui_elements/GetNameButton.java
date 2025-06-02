package my_ui_elements;

import ui_elements.GameButton;
import ui_elements.InputDialog;

public class GetNameButton extends GameButton{
	
	public GetNameButton(String id, String name, int width, int height, int posX, int posY) {
		super(id, name, width, height, posX, posY);
	}

	@Override
	public void action() {
		// The basic buttonAction prints the id of the button to the console.
		// Keep the call to super to preserve this behavior or remove it if you don't want the printing.
		super.action();
		//Game.excelDB().getTable("pokimonMoves").sortByKey();
	    String input = InputDialog.showInputDialog("Enter your name:", "Submit");
        if (input != null) {
            System.out.println("User input: " + input);
        } else {
            System.out.println("No input provided.");
        }
	}
}
