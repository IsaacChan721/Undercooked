import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This actor is the one that the player controls. It is able to pick up
 * objects, put them down and interact with the various stations placed 
 * around the world.
 * Credit to Nathan Jiang for the directionX() and directionY() methods
 * 
 * @author Isaac Chan 
 * @version January 28, 2022
 */
public class Player extends Actor
{
    //player variables
    int playerSpeed = 5, width = 64, height = 64, playerReach = 75;
    
    //variables that the player encounters
    private Food foodObject;
    private Actor grabFood, findCounter;
    private Inventory inventory;
    
    private boolean eWasPressed = false;
    private boolean isFacingCounterTop;
    private int oldX, oldY;
    
    public Player(Inventory inventory) 
    {
        this.inventory = inventory;
    }
    public void act()
    {
        playerMovement();
        collisionDetection();
        useInventory();
    }
    public void playerMovement() //allows the player to move around
    {
        oldX = getX();
        oldY = getY();
        if(Greenfoot.isKeyDown("d")){ //moves the player to the right
            setRotation(0);
            move(playerSpeed);
        } else if(Greenfoot.isKeyDown("s")){ //moves the player downwards
            setRotation(90);
            move(playerSpeed);
        } else if(Greenfoot.isKeyDown("a")){ //moves the player to the left
            setRotation(180);
            move(playerSpeed);
        } else if(Greenfoot.isKeyDown("w")){ //moves the plaer upwards
            setRotation(270);
            move(playerSpeed);
        }
    }
    public void useInventory()
    {
        grabFood = findFood();
        
        //used to find a counter to place a food object on since food can only be placed on counters
        findCounter = getOneObjectAtOffset((int)directionX() * playerReach, (int)directionY() * playerReach, Counter.class);
        
        if(Greenfoot.isKeyDown("e")){ //allows the player to use the inventory slot when e is pressed
            if(!eWasPressed){
                 inventory.inventorySlot(grabFood, findCounter, getX(), getY(), getRotation(), playerReach);
            }
            //checks that the previous button pressed was e
            eWasPressed = true;
        } else {
            //checks that the previous button pressed was not e
            eWasPressed = false;
        }
    }
    public void collisionDetection() //detects if the player collides with a counter
    {   
        if(getIntersectingObjects(Counter.class).size() > 0){
            //repositions the player to where they started
            setLocation(oldX, oldY);
        }
    } 
    public Food findFood()
    {
        for(int i = 0; i <= playerReach; i += playerReach / 3){ //finds food in front of the player
            grabFood = getOneObjectAtOffset((int)directionX() * i, (int)directionY() * i, Food.class);
            if(grabFood != null){
                i = 76; //ends the loop
            }
        }
        return (Food)grabFood;
    }
    public double directionX() //credit to Nathan Jiang, finds the direction the player is facing
    {
        return Math.cos(Math.toRadians(getRotation()));
    }
    public double directionY() //credit to Nathan Jiang, finds the direction the player is facing
    {
        return Math.sin(Math.toRadians(getRotation()));
    }
}