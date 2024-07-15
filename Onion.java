import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A The base ingredient for onion soup which can either be raw or chopped
 * 
 * @author Isaac Chan
 * @version January 28, 2022
 */
public class Onion extends Food
{
    public void act()
    {
        chopped();
    }
    public void chopped() //checks if the onion is chopped or not
    {
        if(isCut){ //if it is chopped
            //change image to a chopped onion
            GreenfootImage choppedOnion = new GreenfootImage("ChoppedOnion.png");
            setImage(choppedOnion);
        }
    }
}
