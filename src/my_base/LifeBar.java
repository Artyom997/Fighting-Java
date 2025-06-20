package my_base;
public class LifeBar {
    //private static final int maxLife = 5; 
    private int currentLife;

    public LifeBar() {
      //  this.maxLife = maxLife;
        this.currentLife =5;
        System.out.println("initialized"+ currentLife);
    }
    public void decreaseLife(int amount) {
        currentLife -= amount;
        if (currentLife < 0) {
            currentLife = 0;
        }
    }
    /* 
    public void increaseLife(int amount) {
        currentLife += amount;
        if (currentLife > maxLife) {
            currentLife = maxLife;
        }
    }
    */
    public int getCurrentLife() {
        System.out.println("get"+ currentLife);
        return currentLife;
    }
    /*
    public static int getMaxLife() {
        return maxLife;
    }
    */

}