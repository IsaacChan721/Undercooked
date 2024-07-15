import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Allows the user to throw away any unnecessary food that the player
 * wishes to dispose of.
 * 
 * @author Isaac Chan
 * @version January 28, 2022
 */
public class TrashCan extends Counter
{
    public void act()
    {
        throwAway();
    }
    public void throwAway() //throws away the food
    {
        if(Greenfoot.isKeyDown("q") && touchingPlayer()){ //if the player presses q whle interacting with the object
            if(!qWasPressed && touchingFood()){ //if this is touching food
                if(isTouching(Plate.class)){ //removes the soup on the plate
                    removeTouching(Food.class);
                    getWorld().addObject(new Plate(), getX(), getY());
                } else { //removes the food
                    removeTouching(Food.class);
                }
                //checks that the previous button pressed was q
                qWasPressed = true;
            } else {
                //checks that the previous button pressed was not q
                qWasPressed = false;    
            }
        }
    }
}
