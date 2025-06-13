package my_game;

import base.Game;
import base.GameCanvas;
import base.PeriodicLoop;
import ui_elements.ScreenPoint;
import base.ShapeListener;
import base.IntersectionAlgorithm;
import my_base.GameControl;
import my_base.InVicinity;
import my_base.MyContent;
import DB.ExcelTable;
import shapes.Image;

//TODO
//Decide if you want to implemet the ShapeListener interface to handle drag and mouse events.
//If so, add it to the class definition and implement the methods you want.
public class MyCharacter1 implements ShapeListener {
	
	public enum MyDirection{
		RIGHT(10,0),
		LEFT(-10,0),
		//UP(0,-10),
		//DOWN(0,10),
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
		KICK("KICK"),
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
	private MyContent content;
	private GameControl gameControl = content.control();

	private MyDirection directionPolicy = MyDirection.STOP;
	private MyDirection direction = MyDirection.STOP;
	private MyCommand commandPolicy = MyCommand.IDLE;
	private MyCommand command = MyCommand.IDLE;
	public int speed = 2;//change this to change the speed of the character
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	private String[] images = {
		"resources/gifs/ryu-standing.gif",
		"resources/gifs/ryu-block.gif",
		"resources/gifs/ryu-mp.gif",
		"resources/gifs/ryu-mk.gif",
		"resources/gifs/ryu-hurricane-ts.gif",
		"resources/gifs/ryu-walkf.gif",
		"resources/gifs/ryu-walkb.gif"
	};

	private final int[] imageWidth = {78, 78, 127, 154, 159, 112, 112};//The following two arrays hold the widths and heights of the different images.
	private final int[] imageHeight = {111, 106, 105, 108, 140, 113, 113};//need to be changed according to each gif
	private int locationIndex = 0;
	private int imageIndex = 0;
	private String imageID = "Ryu";
	private boolean isMoving = true;
	
	private int rotation = 0;	// In degrees


	public MyCharacter1(int index) {
		this.imageID = "char" + index;
		ryuTable = Game.excelDB().createTableFromExcel("ryuMoves");
		ryuTable.deleteAllRows();
		if (index == 1) {setLocation(1, new ScreenPoint(200, 330));}
		else setLocation(2, new ScreenPoint(500, 330));
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
	/*
	public void moveLocation(int index, int dx, int dy) {
		if (index == 1) {
			this.location1.x += dx;
			this.location1.y += dy;
		} else if (index == 2) {
			this.location2.x += dx;
			this.location2.y += dy;
		}
	}
	*/

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

	public MyDirection getPolicy() {
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
			gameControl.meleeControl();
			ScreenPoint location = getLocation(index);
			ScreenPoint desired = new ScreenPoint(location.x + speed*direction.xVec(), location.y + speed*direction.yVec());
			location.x = desired.x;
			location.y = desired.y;
			switch (direction) {
				case LEFT: this.imageIndex = 6; break;
				case RIGHT:  this.imageIndex = 5; break;
				case STOP: this.imageIndex = 0; break;
			}
			Game.UI().canvas().moveShapeToLocation(imageID, location.x, location.y);
			Game.UI().canvas().changeImage(imageID, getImageName(), getImageWidth(), getImageHeight());
			
			//try {
			//	ryuTable.insertRow(new String[] {PeriodicLoop.elapsedTime() + "", location.x + "", location.y +"", direction.toString()});
			//	//Game.excelDB().commit();
			//} catch (Exception e) {
			//	e.printStackTrace();
			//	System.out.println("Error inserting new line to pokimon table");			
			//}
		}
	}
	public void command(MyCommand command) {
		this.command = command;
		switch (command) {
			case PUNCH: this.imageIndex = 2; break;
			case KICK:  this.imageIndex = 3; break;
			case BLOCK: this.imageIndex = 1; break;
			case WIN:   this.imageIndex = 4; break;
			case IDLE:  this.imageIndex = 0; break;
		}
		Game.UI().canvas().changeImage(imageID, getImageName(), getImageWidth(), getImageHeight());
	}

	
	//TODO
	//Add setters, getters and other methods that you need for your character
	public void shapeMoved (String shapeID, int dx, int dy){}
    public void shapeStartDrag(String shapeID){}
    public void shapeEndDrag(String shapeID){}
    public void shapeClicked(String shapeID, int x, int y){}
    public void shapeRightClicked(String shapeID, int x, int y){}
    public void mouseEnterShape(String shapeID, int x, int y){}
    public void mouseExitShape(String shapeID, int x, int y){}
}	

