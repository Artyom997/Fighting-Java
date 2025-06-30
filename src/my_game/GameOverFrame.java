// CharacterSelectFrame.java
package my_game;

import javax.swing.*;
import java.awt.*;

public class GameOverFrame extends JFrame {
    //private static int gameOverCondition = my_game.GameControl.gameOverCondition;
    private String bgImagePath;
    
    public interface NewGameListener {
        void onNewGameSelected();
            
    }

    public GameOverFrame(NewGameListener listener) {
        setTitle("GameOver Screen");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int gameOverCondition = my_game.GameControl.gameOverCondition;
        switch (gameOverCondition) {
            case 1:
                //Player one as RYU wins by points!
                bgImagePath = "resources/Player1_RYU_Won.png";
                break;
            case 2:
                //Player one as KEN wins by points!
                bgImagePath = "resources/Player1_KEN_Won.png";
                break;
            case 3:
                //Player two as RYU wins by points!
                bgImagePath = "resources/Player2_RYU_Won.png";
                break;
            case 4:
                //Player two as KEN wins by points!
                bgImagePath = "resources/Player2_KEN_Won.png";
                break;
            case 5:
                //Player two as RYU wins by KO!
                bgImagePath = "resources/Player2_RYU_Won_KO.png";
                break;
            case 6:
                //Player two as KEN wins by KO!
                bgImagePath = "resources/Player2_KEN_Won_KO.png";
                break;
            case 7:
                //Player one as RYU wins by points!
                bgImagePath = "resources/Player1_RYU_Won_KO.png";
                break;
            case 8:
                //Player one as KEN wins by points!
                bgImagePath = "resources/Player1_KEN_Won_KO.png";
                break;
            case 9:
                //IT'S A DRAW!
                bgImagePath = "resources/DRAW_PICTURE.png";
                break;
        }
        // Set background color
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Optionally, draw a background image here
                Image bg = Toolkit.getDefaultToolkit().getImage(bgImagePath);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1080, 720)); // Adjust size as needed

        JButton newGameBtn = new JButton("Let's Try Again!");
        newGameBtn.setFont(new Font("Arial", Font.BOLD, 24));
        newGameBtn.setBounds(400, 580, 290, 60);

        layeredPane.add(newGameBtn);
        mainPanel.add(layeredPane);
        
        newGameBtn.addActionListener(e -> {
            listener.onNewGameSelected();
            bgImagePath = null;
            my_game.GameControl.gameOverCondition = 0; // Reset game over condition
            dispose();
        });

        setContentPane(mainPanel);
        setLocationRelativeTo(null); // Center on screen
    }
    
}