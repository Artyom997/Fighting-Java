package my_ui_elements;

import shapes.ShapeButton;

public class SlowDownButton extends ShapeButton {
    
    private final static String IMAGE_SRC = "resources/slowDown.png";
    private final static String IMAGE_SRC_DIS = "resources/slowDownDisabled.png";
    private final static int WIDTH = 70;
    private final static int HEIGHT = 85;


    public SlowDownButton(String id, int posX, int posY) {
        super(id, WIDTH, HEIGHT, posX, posY, IMAGE_SRC, IMAGE_SRC_DIS, "Slow Down");
    }

    @Override
    protected void onClick() {
        // Signal GameControl that this button was clicked
        System.out.println("This button is placed on canvas and can be used for anything ...");
    }
}
