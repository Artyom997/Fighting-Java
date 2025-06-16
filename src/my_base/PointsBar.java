package my_base;
public class PointsBar {
    private int currentPoints;

    public PointsBar() {
        currentPoints = 0;
    }

    public void decreasePoints(int amount) {
        currentPoints -= amount;
            if (currentPoints < 0) {
                currentPoints = 0;
        }
    }
    public void increasePoints(int amount) {
        currentPoints += amount;
    }
    public int getCurrentPoints() {
        return currentPoints;
    }
}