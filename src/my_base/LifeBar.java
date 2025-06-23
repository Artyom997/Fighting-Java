package my_base;
import base.Game;
import base.GameCanvas;
import base.PeriodicLoop;
import ui_elements.ScreenPoint;
import base.ShapeListener;
//import my_game.GameControl;
import my_base.InVicinity;
import my_base.MyContent;
import shapes.Image;

public class LifeBar implements ShapeListener {
    //private static final int maxLife = 5; 
    private int currentLife;
	private final int[] imageWidth = {193};//The following two arrays hold the widths and heights of the different images.
	private final int[] imageHeight = {42};//need to be changed according to each gif
	private int imageIndex = 0;
    private String imageID ; // Default image ID, can be changed later
    private ScreenPoint location1;
	private ScreenPoint location2;
    private boolean imageChanging = false;

   

    public LifeBar(int index) {
        this.imageID = "Life" + index;
      //  this.maxLife = maxLife;
        this.currentLife =5;
        if (index == 1) {setLocation(1, new ScreenPoint(100, 50));}
		else setLocation(2, new ScreenPoint(700, 50));
        
        System.out.println("initialized"+ currentLife);
    }
    public void decreaseLife(int amount) {
        currentLife -= amount;
        if (currentLife < 0) {
            currentLife = 0;
        }
    }
    	private String[] images = {
		"resources/Resized/LIFE BAR 5.png",
		"resources/Resized/LIFE BAR 4.png",
		"resources/Resized/LIFE BAR 3.png",
		"resources/Resized/LIFE BAR 2.png",
		"resources/Resized/LIFE BAR 1.png",
		"resources/Resized/LIFE BAR 0.png"
	};
	
	public void setLocation(int index, ScreenPoint location) {
		if (index == 1) {
			this.location1 = location;
		} else 
			this.location2 = location;
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

    public int getCurrentLife() {
        System.out.println("get"+ currentLife);
        return currentLife;
    }

     public boolean isImageChanging() {
        return imageChanging;
    }

    public void setImageChanging(boolean imageChanging) {
        this.imageChanging = imageChanging;
    }

	public void setImageID(String id) {
		this.imageID = id;
	}
	public void initImage() {
		this.imageIndex = 0;
		Game.UI().canvas().changeImage(imageID, getImageName(), getImageWidth(), getImageHeight());
	}
    
	public void changeImage() {
        System.out.println("changeImage called, current index: " + this.imageIndex);
		this.imageIndex ++;
        System.out.println("changeImage called, current index: " + this.imageIndex);
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
		//return imageWidth[imageIndex];
		return 193;
	}
	
	private int getImageHeight() {
		//return imageHeight[imageIndex];
		return 42;
	}
    /*
    public static int getMaxLife() {
        return maxLife;
    }
    */
	public void shapeMoved (String shapeID, int dx, int dy){}
    public void shapeStartDrag(String shapeID){}
    public void shapeEndDrag(String shapeID){}
    public void shapeClicked(String shapeID, int x, int y){}
    public void shapeRightClicked(String shapeID, int x, int y){}
    public void mouseEnterShape(String shapeID, int x, int y){}
    public void mouseExitShape(String shapeID, int x, int y){}

}