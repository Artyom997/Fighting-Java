package my_ui_elements;

import base.Game;
import my_base.MyContent;
import ui_elements.GameButton;

public class RotatePolygonButton extends GameButton{
	
	public RotatePolygonButton(String id, String name, int posX, int posY) {
		super(id, name, 160, 40, posX, posY);
		this.setText("Rotate Polygon");
	}

	@Override
	public void action() {
		// The basic buttonAction prints the id of the button to the console.
		// Keep the call to super to preserve this behavior or remove it if you don't want the printing.
		super.action();
		
		MyContent content = (MyContent) Game.Content();
		//content.polygon().exitEditMode();
		//content.polygon().getVisualPolygon().rotate(45);
		//TODO
		//Change your character properties by calling the appropriate method of
		//MyContent
		
	}

}
