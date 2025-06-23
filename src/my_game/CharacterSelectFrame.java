// CharacterSelectFrame.java
package my_game;

import javax.swing.*;
import java.awt.*;

public class CharacterSelectFrame extends JFrame {
    public interface CharacterSelectListener {
        void onCharacterSelected(String characterName);
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
                // Optionally, draw a background image here
                Image bg = Toolkit.getDefaultToolkit().getImage("resources/BackGround.jpg");
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        // Title label
        JLabel titleLabel = new JLabel("Welcome to our Fighting Game!\nPlease select your character:");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        mainPanel.add(titleLabel);

        // Character buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 20));
        //mainPanel.setBackground(new Color(30, 30, 60));
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        
        
        setLayout(new java.awt.FlowLayout());

        

        JButton ermakBtn = new JButton("Ken");
        JButton ryuBtn = new JButton("Ryu");

        ermakBtn.setFont(new Font("Arial", Font.BOLD, 24));
        ryuBtn.setFont(new Font("Arial", Font.BOLD, 24));

        ermakBtn.addActionListener(e -> {
            listener.onCharacterSelected("Ermak");
            dispose();
        });
        ryuBtn.addActionListener(e -> {
            listener.onCharacterSelected("Ryu");
            dispose();
        });

        buttonPanel.add(ermakBtn);
        buttonPanel.add(ryuBtn);

        mainPanel.add(buttonPanel);

        setContentPane(mainPanel);
        setLocationRelativeTo(null); // Center on screen
    }
}