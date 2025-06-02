package ui_elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

//import org.apache.poi.ss.usermodel.Color;

import base.Game;
//import my_base.MyGame;
import my_base.MyContent;

/*
 * The GameButton is a class that handles dashboard buttons.
 * To have a specific behavior to a button, you should create a class that extends GameButton
 * and overrides it buttonAction method.
 * You can see an example in the class ExampleButton in package buttons.
 */

public class GameImageButton extends UIElement {
	
	protected JButton button;
	protected MyContent content = (MyContent) Game.Content();
	
	public GameImageButton(String id, String name, int width, int height, int posX, int posY, String filename) {
		super(id, posX, posY, width, height, new JButton(new ImageIcon(((new ImageIcon(filename)).getImage()).getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH))));
		this.button = (JButton) this.getJComponent();

		setText(name);
		this.button.setBackground(Color.BLACK);
		this.button.setBorder(null);
		this.button.setFont(new Font("Ariel", Font.BOLD, 16));
		this.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				action();
			}
		});

	}
	
	public void setImage(String filename) {
		// Update the icon with a new image from the file
		ImageIcon newIcon = new ImageIcon(((new ImageIcon(filename)).getImage()).getScaledInstance(
			this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH));
		this.button.setIcon(newIcon);
	}
	
	public void setText(String name) {
		this.button.setText(name);
	}
	public String getText() {
		return this.button.getText();
	}

	//This function is a placeholder and should be overriden in derived specific buttons
	public void action() {
		System.out.println(id + " clicked");
		Game.UI().frame().requestFocus();
	}
	
}

