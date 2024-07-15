import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The station that the player uses in order to gather plates which hold
 * the soup that the player needs to serve. It can only generate a total 
 * of two plates in the world.
 * 
 * @author Isaac Chan
 * @version January 28, 2022
 */

public class PlateStation extends Counter
{
    //total number of plates that the world can have
    private static int totalPlates = 2;
    
    public void act()
    {
        spawnPlate();
    }
    public void spawnPlate() //summons a plate for the player
    {
        if(Greenfoot.isKeyDown("q") && touchingPlayer()){ //if the player interacts with this actor
            if(!qWasPressed && !touchingFood() && totalPlates > 0){ //if there are plates available while nothing it on the actor
                //creates a new plate
                Plate plate = new Plate();
                getWorld().addObject(plate, getX(), getY());
                
                //reduces the plates left for the world to have
                totalPlates--;
                
                //checks that the previous button pressed was q
                qWasPressed = true;
            } else {
                //checks that the previous button pressed was not q
                qWasPressed = false;    
            }
        }
    }
    public void setPlates(int platesReturned) //when plates have been returned from serving orders
    {
        //increase the plates that can be generated rom this actor
        totalPlates += platesReturned;        
    }
}
