package my_base;
public class LifeBar {
    private static int maxLife=5; 
    private int currentLife;

    public LifeBar(int maxLife) {
        this.maxLife = maxLife;
        this.currentLife = maxLife;
    }

    public void decreaseLife(int amount) {
        currentLife -= amount;
        if (currentLife < 0) {
            currentLife = 0;
        }
    }
    public void increaseLife(int amount) {
        currentLife += amount;
        if (currentLife > maxLife) {
            currentLife = maxLife;
        }
    }
    public int getCurrentLife() {
        return currentLife;
    }
    public static int getMaxLife() {
        return maxLife;
    }

}