package my_base;

import base.Game;
import base.GameCanvas;
import ui_elements.ScreenPoint;
import shapes.Shape;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TimerBar {
    private int seconds;
    private final ScreenPoint location = new ScreenPoint(405, 20); // Fixed location
    private Shape timerShape;
    private static final int RECT_WIDTH = 195;
    private static final int RECT_HEIGHT = 35;
    private static final int RECT_OFFSET_X = 0;
    private static final int RECT_OFFSET_Y = -25;
    private static final String FONT_NAME = "Serif";
    private static final int FONT_SIZE = 20;

    public TimerBar() {
        this.seconds = 120;
        this.timerShape = null;
    }

    public void start() {
        new Thread(() -> {
            while (true) {
                addToCanvas();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
                seconds--;
            }
        }).start();
    }
public int getSeconds() {
        return seconds;
    }
    public void addToCanvas() {
        GameCanvas canvas = Game.UI().canvas();
        if (canvas == null) {
            throw new IllegalStateException("GameCanvas is not available");
        }

        String text = "Time left: " + seconds    + "[sec]";

        if (timerShape != null) {
            canvas.hideShape("TimerBarShape");
        }

        timerShape = new Shape("TimerBarShape") {
            @Override
            public void draw(Graphics2D g) {
                g.setColor(Color.WHITE);
                g.fillRect(location.getX() + RECT_OFFSET_X, location.getY() + RECT_OFFSET_Y, RECT_WIDTH, RECT_HEIGHT);
                g.setColor(Color.BLACK);
                g.drawRect(location.getX() + RECT_OFFSET_X, location.getY() + RECT_OFFSET_Y, RECT_WIDTH, RECT_HEIGHT);
                g.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
                if (seconds <= 10) {
                  g.setColor(Color.RED);
                } 
                else {
                g.setColor(Color.BLUE);
                }
                g.drawString(text, location.getX() +18, location.getY());
       
      
            }

            @Override
            public void moveToLocation(int x, int y) {}
            @Override
            public boolean isInArea(int x, int y) { return false; }
            @Override
            public void move(int dx, int dy) {}
        };

        canvas.addShape(timerShape);
    }
}