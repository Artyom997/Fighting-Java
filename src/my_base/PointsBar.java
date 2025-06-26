package my_base;

import base.Game;
import base.GameCanvas;
import ui_elements.ScreenPoint;
import shapes.Shape;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class PointsBar {
    private int currentPoints;
    private final ScreenPoint location;
    private Shape pointsShape; // Store shape to allow updates
    private int playerIndex;
    private static final int RECT_WIDTH = 250;
    private static final int RECT_HEIGHT = 40;
    private static final int RECT_OFFSET_X = -30;
    private static final int RECT_OFFSET_Y = -30;
    private static final String FONT_NAME = "Serif"; // Use a more readable font
    private static final int FONT_SIZE = 26;

    public PointsBar(int index) {
        this.currentPoints = 0;
        this.playerIndex = index;
        // Set location based on player index
        if (index == 1) {
            this.location = new ScreenPoint(102, 30);
        } else if (index == 2) {
            this.location = new ScreenPoint(702, 30);
        } else {
            throw new IllegalArgumentException("index must be 1 or 2");
        }
        this.pointsShape = null;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int points) {
        if (points >= 0) {
            this.currentPoints = points;
            updateShapeText();
        }
    }

    public void addPoints(int points) {
        setCurrentPoints(this.currentPoints + points);
    }

    public void addToCanvas() {
        GameCanvas canvas = Game.UI().canvas();
        if (canvas == null) {
            throw new IllegalStateException("GameCanvas is not available");
        }

        String text = "Player " + playerIndex + " Points: " + currentPoints;

        // Remove existing shape to avoid duplicates
        if (pointsShape != null) {
            canvas.hideShape("PointsBarShape" + playerIndex);
        }

        pointsShape = new Shape("PointsBarShape" + playerIndex) {
            @Override
            public void draw(Graphics2D g) {
                g.setColor(Color.WHITE);
                g.fillRect(location.getX() + RECT_OFFSET_X, location.getY() + RECT_OFFSET_Y, RECT_WIDTH, RECT_HEIGHT);
                g.setColor(Color.BLACK);
                g.drawRect(location.getX() + RECT_OFFSET_X, location.getY() + RECT_OFFSET_Y, RECT_WIDTH, RECT_HEIGHT);
                g.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
                g.setColor(Color.BLACK);
                g.drawString(text, location.getX()-20, location.getY());
            }

            @Override
            public void moveToLocation(int x, int y) {}
            @Override
            public boolean isInArea(int x, int y) { return false; }
            @Override
            public void move(int dx, int dy) {}
        };

        canvas.addShape(pointsShape);
    }

    private void updateShapeText() {
        if (pointsShape != null) {
            addToCanvas();
        }
    }

    public int getPlayerIndex() {
        return playerIndex;
    }
}