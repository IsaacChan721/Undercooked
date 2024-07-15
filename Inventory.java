import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Stores whatever food object the player holds into the inventory in order
 * to be placed somewhere else into the world.
 * 
 * @author Isaac Chan 
 * @version January 28, 2022
 */
public class Inventory extends Actor
{
    //variables for creating the inventory's appearance
    private Color borderColour = new Color (150, 75, 0);
    private Color inventoryColour = new Color (210, 180, 140);
    private static int WIDTH = 80;
    private static int HEIGHT = 80;
    
    //variables that interact with the inventory
    private Actor counterTop;
    CuttingBoard cuttingBoard;
    Food foodObject;
    
    //determines whether the inventory has enough space
    private boolean inventoryFull = false;
    
    public Inventory () 
    {
        //draws the inventory
        setImage(drawInventory());
    }
    private GreenfootImage drawInventory() //creates the drawing of the inventory
    {
        GreenfootImage image = new GreenfootImage (WIDTH, HEIGHT);
        
        //drawing the border first to fill so the center overlaps it
        image.setColor (borderColour);
        image.fillRect(0, 0, WIDTH, HEIGHT);
        
        //drawing the inside of the inventory
        image.setColor (inventoryColour);
        image.fillRect (WIDTH * 1/8, HEIGHT * 1/8, WIDTH * 3/4, HEIGHT * 3/4);
        
        return image;
    }
    public void inventorySlot(Actor item, Actor counterTop, int playerX, int playerY, int rotation, int reach) //adds or removes the object in the inventory slot
    {
        if(!inventoryFull && item != null){ //if there is nothing in the player's inventory
            foodObject = (Food)item;
            Counter counter = (Counter)counterTop;

            if(!foodObject.choppingOnCuttingBoard(counter) || foodObject.hasBeenCut()){ //if the foodObject is not being chopped on the cuttingBoard
                foodObject.setLocation(getX(), getY()); //move into inventory
                inventoryFull = true;
            }
        } else if(inventoryFull && item == null){ //if there is something in the player's inventory
            if(counterTop != null){ //allows the player to only place items on counter tops
                foodObject.setLocation(counterTop.getX(), counterTop.getY());
                inventoryFull = false;
            }
        }
    }
    public int getWidth() //helps with getting the width of the inventory
    {
        return WIDTH;
    }
    public int getHeight() //helps with getting the height of the inventory
    {
        return HEIGHT;
    }
    public boolean isInventoryFull() //checks if the inventory is full
    {
        return inventoryFull;
    }
}
