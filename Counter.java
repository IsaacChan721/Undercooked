import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This Actor is a general actor to store all properties that Counters
 * hold such as stat bar variables or food actors which have been found.
 * 
 * @author Isaac Chan 
 * @version January 28, 2022
 */
public class Counter extends Actor
{
    //StatBar variables
    int maxVal = 80, currVal = 80;
    int width = 80, height = 10, offset = -GameWorld.getGrid()/2;
    Color filledColor = new Color (0, 255, 0); 
    Color missingColor = new Color (255, 0, 0);
    boolean hideAtMax = true;
    SuperStatBar statBar;
    
    //variables for general usage
    Actor findFoodObject;
    Food foundFoodObject;
    boolean qWasPressed = false, setZero = false;
    
    public boolean touchingPlayer() //method for when the player is interacting with a counter
    {
        return getObjectsInRange(GameWorld.getGrid(), Player.class).size() > 0;
    }
    public boolean touchingFood() //method for finding whetherthe counter is touching a food object
    {
        return isTouching(Food.class);
    }
    public int getCurrVal() //used for returning the current value of the stat bar
    {
        return currVal;
    }
}
