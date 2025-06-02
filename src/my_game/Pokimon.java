package my_game;

import java.util.Arrays;

import DB.ExcelTable;
import base.Game;
import base.GameCanvas;
import base.Intersectable;
import base.IntersectionAlgorithm;
import base.PeriodicLoop;
import base.ShapeListener;
import my_base.MyContent;
import shapes.Image;
import shapes.Polyline;
import ui_elements.ScreenPoint;

public class Pokimon implements ShapeListener, Intersectable {
	
	public enum Direction{
		RIGHT (10,0),
		LEFT(-10,0),
		UP (0,-10),
		DOWN(0,10);
		
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

	private ExcelTable pokimonTable; 
	
	private ScreenPoint location;
	private Direction directionPolicy = Direction.RIGHT;
	private Direction direction = Direction.RIGHT;
	
	private final String[] images = {"resources/Poki.jpg", "resources/Poki2.jpg"};

	/**
	 * The following two arrays hold the widths and heights of the different images.
	 */
	private final int[] imageWidth = {220,260};
	private final int[] imageHeight = {200,195};

	private int imageIndex = 0;
	private final String imageID = "pokimon";
	private boolean isMoving = true;
	private int rotation = 0;	// In degrees
	
	
	public Pokimon() {
		
		pokimonTable = Game.excelDB().createTableFromExcel("pokimonMoves");
		pokimonTable.deleteAllRows();
		setLocation(new ScreenPoint(300, 300));
	}	

	public void addToCanvas() {
		GameCanvas canvas = Game.UI().canvas();
		Image image = new Image(getImageID(), getImageName(), getImageWidth(),getImageHeight(), location.x, location.y);
		image.setShapeListener(this);
		image.setzOrder(3);
		canvas.addShape(image);
	}

	
	public ScreenPoint getLocation() {
		return this.location;
	}
	
	public void setLocation(ScreenPoint location) {
		this.location = location;
	}

	public void moveLocation(int dx, int dy) {
		this.location.x += dx;
		this.location.y += dy;
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
	
	public String getImageName() {
		return images[imageIndex];
	}

	private int getImageWidth() {
		return imageWidth[imageIndex];
	}
	
	private int getImageHeight() {
		return imageHeight[imageIndex];
	}

	public String getImageID() {
		return this.imageID;
	}
	
	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
		Image i = (Image) (Game.UI().canvas().getShape(imageID));
		i.setRotation(rotation);
	}

	public void switchImage() {
		setImage(1 - imageIndex);
	}

	public void setImage(int index) {
		this.imageIndex = index;
		Game.UI().canvas().changeImage(imageID, getImageName(), getImageWidth(), getImageHeight());
	}

	public void stopMoving() {
		isMoving = false;
	}

	public void resumeMoving() {
		isMoving = true;
	}
	
	private void turn180 () {
		switch (directionPolicy) {
			case LEFT:
				directionPolicy = Direction.RIGHT;
				break;
			case RIGHT:
				directionPolicy = Direction.LEFT;
				break;
			case UP:
				directionPolicy = Direction.DOWN;					
				break;
			case DOWN:
				directionPolicy = Direction.UP;
				break;
		}
	}

	public String[][] getLastTenMoves() {
		pokimonTable.sortByKey();
		String[][] moves = pokimonTable.getTableAsMatrix();
        int numRows = moves.length;
        // If less than or equal to 10 rows, return the entire array.
        if (numRows <= 10) {
            return moves;
        }
        // Otherwise, copy and return the last 10 rows.
        return Arrays.copyOfRange(moves, numRows - 10, numRows);
	}

	public void move() {
		//MyPolygon polygon = ((MyContent) Game.Content()).polygon();
		//if (IntersectionAlgorithm.areIntersecting(this, polygon)) {
		//	turn180();
		//}
		if (isMoving) {
			// Move according to policy
			ScreenPoint desired = new ScreenPoint(location.x + directionPolicy.xVec(), location.y + directionPolicy.yVec());
			// if move is possible, i.e., maze does not block
			direction = directionPolicy;
			location.x = desired.x;
			location.y = desired.y;
			// After changing the pokimon self location, move also its image in the canvas accordingly.
			Game.UI().canvas().moveShapeToLocation(imageID, location.x, location.y);
			try {
				pokimonTable.insertRow(new String[] {PeriodicLoop.elapsedTime() + "", location.x + "", location.y +"", direction.toString()});
				//Game.excelDB().commit();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error inserting new line to pokimon table");			
			}
		}
	}

	public void showLastMoves() {
		String[][] moves = getLastTenMoves();
		pokimonTable.showTable("Last Moves", moves, 300, 250);
	}

	@Override
	public void shapeMoved(String shapeID, int dx, int dy) {
		moveLocation(dx, dy);
	}

	@Override
	public void shapeStartDrag(String shapeID) {
		// Auto-generated method stub

	}

	@Override
	public void shapeEndDrag(String shapeID) {
		// Auto-generated method stub

	}

	@Override
	public void shapeClicked(String shapeID, int x, int y) {
		stopMoving();

	}

	@Override
	public void shapeRightClicked(String shapeID, int x, int y) {
		resumeMoving();
	}

	@Override
	public void mouseEnterShape(String shapeID, int x, int y) {
		switchImage();

	}

	@Override
	public void mouseExitShape(String shapeID, int x, int y) {
		switchImage();

	}
	
    @Override
    public ScreenPoint[] getIntersectionVertices() {
        int intersectionWidth = getImageWidth();
        int intersectionHeight = getImageHeight();

        int leftX = this.location.x;
        int topY = this.location.y;

        // ScreenPoint[] vertices = {
        //         new ScreenPoint(centerX - intersectionWidth / 2, centerY - intersectionHeight / 2),
        //         new ScreenPoint(centerX + intersectionWidth / 2, centerY - intersectionHeight / 2),
        //         new ScreenPoint(centerX + intersectionWidth / 2, centerY + intersectionHeight / 2),
        //         new ScreenPoint(centerX - intersectionWidth / 2, centerY + intersectionHeight / 2)
        // };
        ScreenPoint[] vertices = {
			new ScreenPoint(leftX, topY),
			new ScreenPoint(leftX + intersectionWidth, topY),
			new ScreenPoint(leftX + intersectionWidth, topY + intersectionHeight),
			new ScreenPoint(leftX, topY + intersectionHeight)
	};
        return vertices;
    }
}
