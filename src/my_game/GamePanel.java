package my_game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Image background;

    public GamePanel() {
        // Load the image from the resources folder
        background = new ImageIcon(getClass().getResource("/resources/Game_Screen_Beginning.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image, scaled to fit the panel
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
