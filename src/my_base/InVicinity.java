package my_base;

import ui_elements.ScreenPoint;

/**
 * This class is responsible for checking the intersection between shape
 */
public class InVicinity {

    /**
     * Checks if two ScreenPoints are within a certain distance (collision radius).
     * 
     * @param p1 - first point
     * @param p2 - second point
     * @param meleeRadius - the maximum distance for intersection
     * @return true if the points are within the threshold distance, false otherwise
     */
    
    public static int meleeRadius = 90; // Default collision radius

    
    public static boolean inMelee(ScreenPoint p1, ScreenPoint p2) {
        double dx = p1.x - p2.x;
        double distance = Math.sqrt(dx * dx);
        return distance <= meleeRadius;
    }
}
