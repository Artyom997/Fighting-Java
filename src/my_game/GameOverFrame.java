// CharacterSelectFrame.java
package my_game;

import javax.swing.*;
import java.awt.*;

public class GameOverFrame extends JFrame {
    private static String char1; 
    private static String char2;
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
                Image bg = Toolkit.getDefaultToolkit().getImage("resources/BackGround.jpg");
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        

        JLabel titleLabel = new JLabel("Player one wins!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(new Color(255, 255, 255, 128));
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(320, 10, 450, 60);
        mainPanel.add(titleLabel);
        
        JLabel char1Label = new JLabel("Player two wins!");
        char1Label.setFont(new Font("Arial", Font.BOLD, 28));
        char1Label.setForeground(Color.BLACK);
        char1Label.setBackground(new Color(255, 255, 255, 128));
        char1Label.setOpaque(true);
        char1Label.setHorizontalAlignment(SwingConstants.CENTER);
        char1Label.setBounds(250, 150, 590, 60);
        mainPanel.add(char1Label);


        JButton newGameBtn = new JButton("Let's Try Again!");
        newGameBtn.setFont(new Font("Arial", Font.BOLD, 24));
        newGameBtn.setBounds(400, 580, 290, 60);


        mainPanel.add(newGameBtn);

        
        // Add the layeredPane to your button panel instead of the button directly

        newGameBtn.addActionListener(e -> {
            listener.onNewGameSelected();
            //overlayLabel2.setVisible(true);
            dispose();
        });


        setContentPane(mainPanel);
        setLocationRelativeTo(null); // Center on screen
    }
    
}