package my_game;

import base.Game;
import base.GameCanvas;
import base.IntersectionAlgorithm;
import base.PeriodicLoop;
import ui_elements.ScreenPoint;
import base.ShapeListener;
import my_base.MyContent;
import DB.ExcelTable;
import shapes.Image;

//TODO
//Decide if you want to implemet the ShapeListener interface to handle drag and mouse events.
//If so, add it to the class definition and implement the methods you want.
public class MyCharacter1 implements ShapeListener {
	
	public enum Direction{
		RIGHT (10,0),
		LEFT(-10,0),
		UP (0,-10),
		DOWN(0,10),
		STOP(0,0);
		
		private final int xVec, yVec;
		private Direction(int xVec, int yVec) {
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
	private ExcelTable ryuTable; 
	private ScreenPoint location;
	
	private Direction directionPolicy = Direction.STOP;
	private Direction direction = Direction.STOP;
	public int speed = 5;//change this to change the speed of the character

	private String[] images = {
		"resources/ryu - haduken - right.png",
	 	"resources/ryu - standing - right.png",
		"resources/ryu - kick - right.png"
	};

	private final int[] imageWidth = {420,460, 450};//The following two arrays hold the widths and heights of the different images.
	private final int[] imageHeight = {400,470, 450};//
	private int locationIndex = 0;
	private int imageIndex = 0;
	private String imageID = "Ryu";
	private boolean isMoving = true;
	private int rotation = 0;	// In degrees


	public MyCharacter1() {
		ryuTable = Game.excelDB().createTableFromExcel("ryuMoves");
		ryuTable.deleteAllRows();
		setLocation(new ScreenPoint(200, 250));
	}

	public void addToCanvas() {
		GameCanvas canvas = Game.UI().canvas();
		Image image = new Image(getImageID(), getImageName(), getImageWidth(),getImageHeight(), location.x, location.y);
		image.setShapeListener(this);
		image.setzOrder(3);
		canvas.addShape(image);
	}

	public void moveLocation(int dx, int dy) {
		this.location.x += dx;
		this.location.y += dy;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public void setDirectionPolicy(Direction direction) {
		directionPolicy = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public Direction getPolicy() {
		return directionPolicy;
	}
	
	public ScreenPoint getLocation() {
		return this.location;
	}
	
	public void setLocation(ScreenPoint location) {
		this.location = location;
	}
	
	public void setImageID(String id) {
		this.imageID = id;
	}
	public void initImage() {
		this.imageIndex = 0;
		Game.UI().canvas().changeImage(imageID, getImageName(), getImageWidth(), getImageHeight());
	}
	public void changeImage() {//David+Artyom
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
	public void move() {
		MyPolygon polygon = ((MyContent) Game.Content()).polygon();
		if (isMoving) {
			// Move according to policy
			ScreenPoint desired = new ScreenPoint(location.x + speed*directionPolicy.xVec(), location.y + speed*directionPolicy.yVec());
			// if move is possible, i.e., maze does not block
			direction = directionPolicy;
			location.x = desired.x;
			location.y = desired.y;
			// After changing the pokimon self location, move also its image in the canvas accordingly.
			Game.UI().canvas().moveShapeToLocation(imageID, location.x, location.y);
			try {
				ryuTable.insertRow(new String[] {PeriodicLoop.elapsedTime() + "", location.x + "", location.y +"", direction.toString()});
				//Game.excelDB().commit();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error inserting new line to pokimon table");			
			}
		}
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

