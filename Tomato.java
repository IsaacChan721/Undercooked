import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A The base ingredient for tomato soup which can either be raw or chopped
 * 
 * @author Isaac Chan
 * @version January 28, 2022
 */
public class Tomato extends Food
{
    public void act()
    {
        chopped();
    }
    public void chopped() //checks if the onion is chopped or not
    {
        if(isCut){ //if it is chopped
            //change image to a chopped onion
            GreenfootImage choppedTomato = new GreenfootImage("ChoppedTomato.png");
            setImage(choppedTomato);
        }
    }
}
