// CharacterSelectFrame.java
package my_game;

import javax.swing.*;
import java.awt.*;

public class CharacterSelectFrame extends JFrame {
    private static String char1; 
    private static String char2;

    public interface CharacterSelectListener {
        void onCharacterSelected(String[] characterNames);
            
    }

    public CharacterSelectFrame(CharacterSelectListener listener) {
        setTitle("Start Screen");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set background color
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image bg = Toolkit.getDefaultToolkit().getImage("resources/BackGround.jpg");
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1080, 720)); // Adjust size as needed

        JLabel titleLabel = new JLabel("Welcome to our Fighting Game!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(new Color(255, 255, 255, 128));
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(320, 10, 450, 60);
        layeredPane.add(titleLabel);
        
        JLabel char1Label = new JLabel("Player one, please select your character:");
        char1Label.setFont(new Font("Arial", Font.BOLD, 28));
        char1Label.setForeground(Color.BLACK);
        char1Label.setBackground(new Color(255, 255, 255, 128));
        char1Label.setOpaque(true);
        char1Label.setHorizontalAlignment(SwingConstants.CENTER);
        char1Label.setBounds(250, 150, 590, 60);
        layeredPane.add(char1Label);

        JButton kenBtn1 = new JButton("Ken");
        kenBtn1.setFont(new Font("Arial", Font.BOLD, 24));
        kenBtn1.setBounds(330, 260, 160, 60);

        JButton ryuBtn1 = new JButton("Ryu");
        ryuBtn1.setFont(new Font("Arial", Font.BOLD, 24));
        ryuBtn1.setBounds(590, 260, 160, 60);

        JLabel char2Label = new JLabel("Player two, please select your character:");
        char2Label.setFont(new Font("Arial", Font.BOLD, 28));
        char2Label.setForeground(Color.BLACK);
        char2Label.setBackground(new Color(255, 255, 255, 128));
        char2Label.setOpaque(true);
        char2Label.setHorizontalAlignment(SwingConstants.CENTER);
        char2Label.setBounds(250, 350, 590, 60);
        layeredPane.add(char2Label);

        JButton kenBtn2 = new JButton("Ken");
        kenBtn2.setFont(new Font("Arial", Font.BOLD, 24));
        kenBtn2.setBounds(330, 460, 160, 60);

        JButton ryuBtn2 = new JButton("Ryu");
        ryuBtn2.setFont(new Font("Arial", Font.BOLD, 24));
        ryuBtn2.setBounds(590, 460, 160, 60);

        JLabel overlayLabel1 = new JLabel("Character 1 has been selected!");
        overlayLabel1.setFont(new Font("Arial", Font.BOLD, 28));
        overlayLabel1.setForeground(Color.BLACK);
        overlayLabel1.setOpaque(true);
        overlayLabel1.setBackground(Color.WHITE);
        overlayLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        overlayLabel1.setVerticalAlignment(SwingConstants.CENTER);
        overlayLabel1.setBounds(330, 260, 420, 60);
        overlayLabel1.setVisible(false); // Hide initially

        JLabel overlayLabel2 = new JLabel("Character 2 has been selected!");
        overlayLabel2.setFont(new Font("Arial", Font.BOLD, 28));
        overlayLabel2.setForeground(Color.BLACK);
        overlayLabel2.setOpaque(true);
        overlayLabel2.setBackground(Color.WHITE);
        overlayLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        overlayLabel2.setVerticalAlignment(SwingConstants.CENTER);
        overlayLabel2.setBounds(330, 460, 420, 60);
        overlayLabel2.setVisible(false); // Hide initially

        JButton startGameBtn = new JButton("Let's Start the Fight!");
        startGameBtn.setFont(new Font("Arial", Font.BOLD, 24));
        startGameBtn.setBounds(400, 580, 290, 60);

        layeredPane.add(kenBtn1, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(ryuBtn1, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(kenBtn2, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(ryuBtn2, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(startGameBtn, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(overlayLabel1, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(overlayLabel2, JLayeredPane.PALETTE_LAYER);
        mainPanel.add(layeredPane);
        // Add the layeredPane to your button panel instead of the button directly

        kenBtn1.addActionListener(e -> {
            char1 = "Ken";
            overlayLabel1.setVisible(true);
            kenBtn1.setEnabled(false);
        }); 
        ryuBtn1.addActionListener(e -> {
            char1 = "Ryu";
            overlayLabel1.setVisible(true);
            ryuBtn1.setEnabled(false);
        });
        kenBtn2.addActionListener(e -> {
            char2 = "Ken";
            overlayLabel2.setVisible(true);
            kenBtn2.setEnabled(false);
        });
        ryuBtn2.addActionListener(e -> {
            char2 = "Ryu";
            overlayLabel2.setVisible(true);
            ryuBtn2.setEnabled(false);
        });
        startGameBtn.addActionListener(e -> {
            String[] characterNames = {char1, char2};
            listener.onCharacterSelected(characterNames);
            overlayLabel1.setVisible(false);
            overlayLabel2.setVisible(false);
            ryuBtn1.setEnabled(true);
            kenBtn1.setEnabled(true);
            ryuBtn2.setEnabled(true);
            kenBtn2.setEnabled(true);
            dispose();
        });


        setContentPane(mainPanel);
        setLocationRelativeTo(null); // Center on screen
    }
    
}