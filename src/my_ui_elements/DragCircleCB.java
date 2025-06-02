package my_ui_elements;

import base.Game;
import shapes.Shape;
import ui_elements.GameCheckbox;

public class DragCircleCB extends GameCheckbox {
    public DragCircleCB(String id, String name, int posX, int posY, int width, int height, boolean isSelected) {
        super(id, name, posX, posY, width, height, isSelected);
    }

    @Override
    public void action() {
        super.action();
        Shape c = Game.UI().canvas().getShape("circle");
		c.setDraggable(this.isSelected());
    }
    
}
