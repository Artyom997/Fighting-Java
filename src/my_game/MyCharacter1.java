package my_game;

import base.Game;
import base.GameCanvas;
//import base.PeriodicLoop;
import ui_elements.ScreenPoint;
import base.ShapeListener;
//import base.IntersectionAlgorithm;
//import my_game.GameControl;
//import my_base.InVicinity;
//import my_base.MyContent;
import DB.ExcelTable;
import shapes.Image;

//TODO
//Decide if you want to implemet the ShapeListener interface to handle drag and mouse events.
//If so, add it to the class definition and implement the methods you want.
public class MyCharacter1 implements ShapeListener {
	
	public enum MyDirection{
		RIGHT(10,0),
		LEFT(-10,0),
		STOP(0,0);
		
		private final int xVec, yVec;
		private MyDirection(int xVec, int yVec) {
			this.xVec = xVec;
			this.yVec = yVec;
		}
		public int xVec() {
			return xVec;
		}
		public int yVec() {
			return yVec;
		}
	}
	public enum MyCommand{
		PUNCH("PUNCH"),
		//KICK("KICK"),
		BLOCK("BLOCK"),
		WIN("WIN"),
		IDLE("IDLE");
		
		private final String command;
		private MyCommand(String command) {
			this.command = command;
		}
		public String getCommand() {
			return command;
		}
	}
	private ExcelTable ryuTable;
	private ScreenPoint location1;
	private ScreenPoint location2;
	//private MyContent content;
	//private GameControl gameControl = content.control();

	private MyDirection directionPolicy = MyDirection.STOP;
	private MyDirection direction = MyDirection.STOP;
	private MyCommand commandPolicy = MyCommand.IDLE;
	private MyCommand command = MyCommand.IDLE;
	public static int defSpeed = 1; //default speed of the character
	public int speed = defSpeed;//change this to change the speed of the character
	
	public static int getDefSpeed() {
		return defSpeed;
	}
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	private String[] images;
	private String[] ryuImages = {
		//left character images
		"resources/gifs/Ryu/ryu-standing.gif", //img index 0
		"resources/gifs/Ryu/ryu-block.gif",//img index 1
		"resources/gifs/Ryu/ryu-mp.gif",//img index 2
		"resources/gifs/Ryu/ryu-timeout.gif",//img index 3
		"resources/gifs/Ryu/ryu-walkf.gif",//img index 4
		"resources/gifs/Ryu/ryu-walkb.gif",//img index 5
		//right character images
		"resources/gifs/Ryu/Rotate/ryu-standing-rotate.gif",//img index 6
		"resources/gifs/Ryu/Rotate/ryu-block-rotate.gif",//img index 7
		"resources/gifs/Ryu/Rotate/ryu-mp-rotate.gif",//img index 8
		"resources/gifs/Ryu/Rotate/ryu-timeout-rotate.gif",//img index 9
		"resources/gifs/Ryu/Rotate/ryu-walkf-rotate.gif",//img index 10
		"resources/gifs/Ryu/Rotate/ryu-walkb-rotate.gif"//img index 11
	};
	private String[] kenImages = {
		//left character images
		"resources/gifs/Ken/ken-mp.gif", //img index 0
		"resources/gifs/Ken/ken-block2.gif",//img index 1
		"resources/gifs/Ken/ken-hado-ts.gif",//img index 2
		"resources/gifs/Ken/ken-taunts2.gif",//img index 3
		"resources/gifs/Ken/ken-walkf.gif",//img index 4
		"resources/gifs/Ken/ken-walkb.gif",//img index 5
		//right character images
		"resources/gifs/Ken/Rotate/ken-mp-rotate.gif",//img index 6
		"resources/gifs/Ken/Rotate/ken-block2-rotate.gif",//img index 7
		"resources/gifs/Ken/Rotate/ken-hado-ts-rotate.gif",//img index 8
		"resources/gifs/Ken/Rotate/ken-taunts2-rotate.gif",//img index 9
		"resources/gifs/Ken/Rotate/ken-walkf-rotate.gif",//img index 10
		"resources/gifs/Ken/Rotate/ken-walkb-rotate.gif"//img index 11
	};

	private int[] imageWidth;
	private int[] imageHeight;
	
	
	private final int[] ryuWidth = {78, 78, 127, 75, 112, 112, 78, 78, 127, 75, 112, 112};//The following two arrays hold the widths and heights of the different images.
	private final int[] ryuHeight = {111, 106, 105, 109, 113, 113, 111, 106, 105, 109, 113, 113};//need to be changed according to each gif
	private final int[] kenWidth = {78, 78, 136, 115, 112, 111, 78, 78, 136, 115, 112, 111};//The following two arrays hold the widths and heights of the different images.
	private final int[] kenHeight = {111, 105, 104, 112, 113, 113, 111, 105, 104, 112, 113, 113};//need to be changed according to each gif
	{images = ryuImages; //default images
	imageWidth = ryuWidth; //default image width
	imageHeight = ryuHeight;} //default image height	
	private int imageIndex = 0;
	private String imageID = "Ryu";
	private String charName; //character name
	private boolean isMoving = true;

	public MyCharacter1(int index) {
		this.imageID = "char" + index;
		ryuTable = Game.excelDB().createTableFromExcel("ryuMoves");
		ryuTable.deleteAllRows();
		if (index == 1) {
			setLocation(1, new ScreenPoint(200, 330));
			this.imageIndex = 0;
		}
		else{
		setLocation(2, new ScreenPoint(800, 330));
		this.imageIndex = 7;}
	}
	public void setVisuals(String choice) {
		if (choice.equals("Ryu")) {
			this.images = ryuImages;
			this.imageWidth = ryuWidth;
			this.imageHeight = ryuHeight;
			this.charName = "RYU";
		} else if (choice.equals("Ken")) {
			this.images = kenImages;
			this.imageWidth = kenWidth;
			this.imageHeight = kenHeight;
			this.charName = "KEN";
		}
	}
	public String getCharName() {
		return charName;
	}
	public void addToCanvas(int index) {
		GameCanvas canvas = Game.UI().canvas();
		Image image;
		if (index == 1) {
			image = new Image(getImageID(), getImageName(), getImageWidth(),getImageHeight(), location1.x, location1.y);
		} else
			image = new Image(getImageID(), getImageName(), getImageWidth(),getImageHeight(), location2.x, location2.y);
		
		image.setShapeListener(this);
		image.setzOrder(3);
		canvas.addShape(image);
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public void setDirectionPolicy(MyDirection direction) {
		directionPolicy = direction;
	}
	public void setCommandPolicy(MyCommand command) {
		commandPolicy = command;
	}
	public MyCommand getCommandPolicy() {
		return commandPolicy;
	}
	public MyDirection getDirection() {
		return direction;
	}

	public MyDirection getDirectionPolicy() {
		return directionPolicy;
	}
	
	public ScreenPoint getLocation(int index) {
		if (index == 1) {
			return this.location1;
		} else
			return this.location2;
	}
	
	public void setLocation(int index, ScreenPoint location) {
		if (index == 1) {
			this.location1 = location;
		} else 
			this.location2 = location;
	}
	
	public void setImageID(String id) {
		this.imageID = id;
	}
	public void initImage() {
		this.imageIndex = 0;
		Game.UI().canvas().changeImage(imageID, getImageName(), getImageWidth(), getImageHeight());
	}
	public void changeImage() {
		this.imageIndex ++;
		if (this.imageIndex >= this.images.length) {
			this.imageIndex = 0;
		}
		Game.UI().canvas().changeImage(imageID, getImageName(), getImageWidth(), getImageHeight());
	}
	
	public String getImageID() {
		return this.imageID;
	}
	public String getImageName() {
		return images[imageIndex];
	}

	private int getImageWidth() {
		return imageWidth[imageIndex];
	}
	
	private int getImageHeight() {
		return imageHeight[imageIndex];
	}
	public void move(int index, MyDirection direction) {
		if (isMoving) {
			this.direction = direction;
			ScreenPoint location = getLocation(index);
			ScreenPoint desired = new ScreenPoint(location.x + speed*direction.xVec(), location.y + speed*direction.yVec());
			location.x = desired.x;
			location.y = desired.y;
			if(index == 1) {
				switch (direction) {
					case LEFT: imageIndex = 5; break;
					case RIGHT:  imageIndex = 4; break;
					case STOP: imageIndex = 0; break;
				}
			}
			else if(index == 2) {
				switch (direction) {
					case LEFT: imageIndex = 10; break;
					case RIGHT:  imageIndex = 11; break;
					case STOP: imageIndex = 6; break;
				}
			}
		Game.UI().canvas().changeImage(imageID, getImageName(), getImageWidth(), getImageHeight());
		Game.UI().canvas().moveShapeToLocation(imageID, location.x, location.y);
		}	
	}
	public void command(int index, MyCommand command) {
		this.command = command;
		if(index == 1) {
			switch (command) {
				case PUNCH: this.imageIndex = 2; break;
				case BLOCK: this.imageIndex = 1; break;
				case WIN:   this.imageIndex = 3; break;//taunt animation
				case IDLE:  this.imageIndex = 0; break;
			}
		}
		else if(index == 2) {
			switch (command) {
				case PUNCH: this.imageIndex = 8; break;
				case BLOCK: this.imageIndex = 7; break;
				case WIN:   this.imageIndex = 9; break;//taunt animation
				case IDLE:  this.imageIndex = 6; break;
			}
		}
		Game.UI().canvas().changeImage(imageID, getImageName(), getImageWidth(), getImageHeight());
	}

	//TODO
	//Irrelevant methods but still needed for the game to run properly
	//Add setters, getters and other methods that you need for your character
	public void shapeMoved (String shapeID, int dx, int dy){}
    public void shapeStartDrag(String shapeID){}
    public void shapeEndDrag(String shapeID){}
    public void shapeClicked(String shapeID, int x, int y){}
    public void shapeRightClicked(String shapeID, int x, int y){}
    public void mouseEnterShape(String shapeID, int x, int y){}
    public void mouseExitShape(String shapeID, int x, int y){}
}	

