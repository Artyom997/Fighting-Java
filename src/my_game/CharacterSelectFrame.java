// CharacterSelectFrame.java
package my_game;

import javax.swing.*;

public class CharacterSelectFrame extends JFrame {
    public interface CharacterSelectListener {
        void onCharacterSelected(String characterName);
    }

    public CharacterSelectFrame(CharacterSelectListener listener) {
        setTitle("Welcome to our Fighting game, please select your character");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        

        JButton ermakBtn = new JButton("Ermak");
        JButton ryuBtn = new JButton("Ryu");

        ermakBtn.addActionListener(e -> {
            listener.onCharacterSelected("Ermak");
            dispose();
        });
        ryuBtn.addActionListener(e -> {
            listener.onCharacterSelected("Ryu");
            dispose();
        });

        add(ermakBtn);
        add(ryuBtn);
    }
}