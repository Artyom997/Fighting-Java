// CharacterSelectFrame.java
package my_game;

import javax.swing.*;
import java.awt.*;

public class CharacterSelectFrame extends JFrame {
    private static String char1; 
    private static String char2;

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
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //Starting screen title
        JLabel titleLabel = new JLabel("Welcome to our Fighting Game!");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        mainPanel.add(titleLabel);
        
        // Title for first character label
        JLabel title1Label = new JLabel("Player one, please select your character:");
        title1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        title1Label.setFont(new Font("Arial", Font.BOLD, 28));
        title1Label.setForeground(Color.BLACK);
        title1Label.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));
        mainPanel.add(title1Label);
        
        // Character1 buttons panel
        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setOpaque(false);
        buttonPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
        //mainPanel.setBackground(new Color(30, 30, 60));
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setLayout(new java.awt.FlowLayout());
        JButton kenBtn1 = new JButton("Ken");
        JButton ryuBtn1 = new JButton("Ryu");
        kenBtn1.setFont(new Font("Arial", Font.BOLD, 24));
        ryuBtn1.setFont(new Font("Arial", Font.BOLD, 24));
        buttonPanel1.add(kenBtn1);
        buttonPanel1.add(ryuBtn1);
        mainPanel.add(buttonPanel1);

        // Title for second character label
        JLabel title2Label = new JLabel("Player two, please select your character:");
        title2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        title2Label.setFont(new Font("Arial", Font.BOLD, 28));
        title2Label.setForeground(Color.BLACK);
        title2Label.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        mainPanel.add(title2Label);
        // Character2 buttons panel
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setOpaque(false);
        buttonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
        //mainPanel.setBackground(new Color(30, 30, 60));
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setLayout(new java.awt.FlowLayout());
        JButton kenBtn2 = new JButton("Ken");
        JButton ryuBtn2 = new JButton("Ryu");
        kenBtn2.setFont(new Font("Arial", Font.BOLD, 24));
        ryuBtn2.setFont(new Font("Arial", Font.BOLD, 24));
        buttonPanel2.add(kenBtn2);
        buttonPanel2.add(ryuBtn2);
        mainPanel.add(buttonPanel2);


        kenBtn1.addActionListener(e -> {
            listener.onCharacterSelected("Ken");
            dispose();
        });
        ryuBtn1.addActionListener(e -> {
            listener.onCharacterSelected("Ryu");
            dispose();
        });
        kenBtn2.addActionListener(e -> {
            listener.onCharacterSelected("Ken");
            dispose();
        });
        ryuBtn2.addActionListener(e -> {
            listener.onCharacterSelected("Ryu");
            dispose();
        });




        setContentPane(mainPanel);
        setLocationRelativeTo(null); // Center on screen
    }
}