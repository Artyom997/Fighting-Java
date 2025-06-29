// CharacterSelectFrame.java
package my_game;

import javax.swing.*;
import java.awt.*;

public class GameOverFrame extends JFrame {
    //private static String char1; 
    //private static String char2;
    private static String endText;
    private static int gameOverCondition = my_game.GameControl.gameOverCondition;
    public interface NewGameListener {
        void onNewGameSelected();
            
    }

    public GameOverFrame(NewGameListener listener) {
        setTitle("GameOver Screen");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set background color
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Optionally, draw a background image here
                Image bg = Toolkit.getDefaultToolkit().getImage("resources/ryu-street-fighter.jpg");
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1080, 720)); // Adjust size as needed

        switch (gameOverCondition) {
            case 1:
                endText = "Player one wins by points!";
                break;
            case 2:
                endText = "Player two wins by points!";
                break;
            case 3:
                endText = "Player two wins by KO!";
                break;
            case 4:
                endText = "Player one wins by KO!";
                break;
            case 5:
                endText = "We don't know how you did it, but you broke the game!";
        }
        JLabel win1Label = new JLabel(endText);
        win1Label.setFont(new Font("Arial", Font.BOLD, 28));
        win1Label.setForeground(Color.BLACK);
        win1Label.setBackground(new Color(255, 255, 255, 128));
        win1Label.setOpaque(true);
        win1Label.setHorizontalAlignment(SwingConstants.CENTER);
        win1Label.setBounds(330, 100, 450, 60);
        
        layeredPane.add(win1Label);

        JButton newGameBtn = new JButton("Let's Try Again!");
        newGameBtn.setFont(new Font("Arial", Font.BOLD, 24));
        newGameBtn.setBounds(400, 580, 290, 60);

        layeredPane.add(newGameBtn);
        mainPanel.add(layeredPane);
        

        newGameBtn.addActionListener(e -> {
            listener.onNewGameSelected();
            dispose();
        });


        setContentPane(mainPanel);
        setLocationRelativeTo(null); // Center on screen
    }
    
}