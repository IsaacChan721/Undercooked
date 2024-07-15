import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Supplies the onions for the player to collect when interacting with 
 * this actor. Generates an infinite amount of onions for the player's 
 * usage.
 * 
 * @author Isaac Chan
 * @version January 28, 20222
 */
public class OnionSupply extends Counter
{
    public void act()
    {
        spawnOnion();
    }
    public void spawnOnion() //spawns an onion that the player can use
    {
        if(Greenfoot.isKeyDown("q") && touchingPlayer()){ //if q is pressed while interacting with the player
            if(!qWasPressed && !touchingFood()){ //create an onion while food is not touching it
                Onion rawOnion = new Onion();
                getWorld().addObject(rawOnion, getX(), getY());
                
                //checks that the previous button pressed was q
                qWasPressed = true;
            } else {
                //checks that the previous button pressed was not q
                qWasPressed = false;    
            }
        }
    }
}
