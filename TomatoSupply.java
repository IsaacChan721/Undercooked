import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Supplies the tomatoes for the player to collect when interacting with 
 * this actor. Generates an infinite amount of tomatoes for the player's 
 * usage.
 * 
 * @author Isaac Chan 
 * @version January 28, 2022
 */
public class TomatoSupply extends Counter
{
    public void act()
    {
        spawnTomato();
    }
    public void spawnTomato() //spawns a tomato that the player can use
    {
        if(Greenfoot.isKeyDown("q") && touchingPlayer()){ //if q is pressed while interacting with the player
            if(!qWasPressed && !touchingFood()){ //create a tomato while food is not touching it
                Tomato rawTomato = new Tomato();
                getWorld().addObject(rawTomato, getX(), getY());
                
                //checks that the previous button pressed was q
                qWasPressed = true;
            } else {
                //checks that the previous button pressed was not q
                qWasPressed = false;    
            }
        }
    }
}
