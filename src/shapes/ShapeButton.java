package shapes;

import java.awt.Color;
import base.Game;
import base.GameCanvas;
import base.ShapeListener;
import ui_elements.ScreenPoint;

public abstract class ShapeButton implements ShapeListener {
    protected String id;
    protected ScreenPoint position;
    
    protected int height, width;
    
    protected Image image;
    protected Text text;
    protected Rectangle highlightCircle; // hovering effect
    protected Rectangle disabledOverlay; // when button disabled
    protected boolean isDisabled = false;

    protected String normalImageSrc;   // image for enabled state
    protected String disabledImageSrc; // image for disabled state

    /*
    This abstract class hold all common logic for shape buttons (rectangles)
    An image and a text linked to the button
    ShapeListener logic
    Highlight rectangle when hovering over the button
    */
    public ShapeButton(String id, int width, int height, int posX, int posY, String normalImageSrc, String disabledImageSrc, String buttonText) {
        this.id = id;
        this.position = new ScreenPoint(posX, posY);
        this.height = height;
        this.width = width;
        this.normalImageSrc = normalImageSrc;
        this.disabledImageSrc = disabledImageSrc;

        // Button image
        updateImage(id, normalImageSrc, width, height, posX, posY);
        
        // Text label
        text = new Text(id + "_text", buttonText, posX - 1, posY + height + 15);
        text.setFontSize(13);
        text.setColor(Color.WHITE);
        text.setzOrder(10);
    }

    private void updateImage (String id, String imageSrc, int width, int height, int posX, int posY) {
        image = new Image(id, imageSrc, width, height, posX, posY);
        image.setzOrder(10);
        image.setShapeListener(this);
        image.setDraggable(false);
    }

    public void addToCanvas() {
        GameCanvas canvas = Game.UI().canvas();
        canvas.addShape(image);
        canvas.addShape(text);
        canvas.revalidate();
        canvas.repaint();
    }

    public void removeFromCanvas() {
        GameCanvas canvas = Game.UI().canvas();
        canvas.deleteShape(image.getId());
        canvas.deleteShape(text.getId());
        canvas.revalidate();
        canvas.repaint();
    }

    protected void showHighlight() {
        GameCanvas canvas = Game.UI().canvas();
        highlightCircle = new Rectangle(getId() + "_buildHighlight", getPosition().x - 2, getPosition().y - 2, width + 4, height + 4);
        highlightCircle.setIsFilled(true);
        highlightCircle.setFillColor(new java.awt.Color(240, 240, 160, 80));
        highlightCircle.setColor(new java.awt.Color(240, 240, 160, 80));
        highlightCircle.setWeight(0);
        highlightCircle.setzOrder(2);
        canvas.addShape(highlightCircle);
        canvas.revalidate();
        canvas.repaint();
    }

    protected void hideHighlight() {
        GameCanvas canvas = Game.UI().canvas();
        if (highlightCircle != null) {
            canvas.deleteShape(highlightCircle.getId());
            highlightCircle = null;
            canvas.revalidate();
            canvas.repaint();
        }
    }

    public String getId() {
        return id;
    }
    
    public ScreenPoint getPosition() {
        return position;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    protected abstract void onClick(); 

    @Override
    public void shapeClicked(String shapeID, int x, int y) {
        // Prevent when disabled
        if (isDisabled) {
            return;
        }
        hideHighlight();
        onClick();
        //disableButton();
    }
    
    @Override public void shapeMoved(String shapeID, int dx, int dy) { }
    @Override public void shapeStartDrag(String shapeID) { }
    @Override public void shapeEndDrag(String shapeID) { }
    @Override public void shapeRightClicked(String shapeID, int x, int y) { }
    
    @Override
    public void mouseEnterShape(String shapeID, int x, int y) {
        // Prevent when disabled
        if (isDisabled) {
            return;
        }
        showHighlight();
    }
    
    @Override
    public void mouseExitShape(String shapeID, int x, int y) {
        // Prevent when disabled
        if (isDisabled) {
            return;
        }
        hideHighlight();
    }

    // Show disabled button (black and white)
    public void disableButton() {
        updateImage(id, disabledImageSrc, width, height, position.x, position.y);
        isDisabled = true;

        GameCanvas canvas = Game.UI().canvas();
        canvas.deleteShape(id);
        canvas.addShape(image);
        canvas.revalidate();
        canvas.repaint();
    }

    // Show enabled button
    public void enableButton() {
        updateImage(id, normalImageSrc, width, height, position.x, position.y);
        isDisabled = false;
        
        GameCanvas canvas = Game.UI().canvas();
        canvas.deleteShape(id);
        canvas.addShape(image);
    }

}
