import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //imports the array list
/**
 * Creates new orders for the player to complete in order to earn coins.
 * Credit to Mr. Cohen for the stat bar actor
 * 
 * @author Isaac Chan
 * @version January 28, 2022
 */
public class Order extends Actor
{
    //detemines if the order is onion or tomato soup
    boolean isOnionSoup = false;
    boolean isTomatoSoup = false;
    
    //StatBar variables
    int maxVal = 5000, currVal = 5000;
    int width = 125, height = 10, offset = -GameWorld.getGrid()/2;
    Color filledColor = new Color (0, 255, 0); 
    Color missingColor = new Color (0, 0, 255);
    boolean hideAtMax = true;
    SuperStatBar statBar;
    
    //finds the array list
    ArrayList<Order> orderList;
    
    //chooses a random soup order
    int rand = Greenfoot.getRandomNumber(2);
    
    public Order()
    {
        //adds a stat bar to the order
        statBar = new SuperStatBar(maxVal, currVal, this, width, height, offset, filledColor, missingColor, hideAtMax);
        randomSoup();
    }
    public void addedToWorld(World w) //allows the stat bar to be added into the world directly
    {
        w.addObject(statBar, 0, 0);
    }
    public void act() 
    {
        orderTimer();
    }
    public void orderTimer() //how long the order will last
    {
        //gradually decreases the time it has left
        currVal--;
        statBar.update(currVal);
        if(currVal <= 0){ //when the stat bar reaches 0
            //finds the order list
            orderList = ((GameWorld)getWorld()).getOrderList();
            
            //prepares a new order to replace the old one
            Order newOrder = new Order();
            int orderX = getX();
            int orderY = getY();

            //replaces the old order with a new one
            orderList.remove(0);
            orderList.add(newOrder);
            ((GameWorld)getWorld()).addObject(newOrder, orderX, orderY);
            ((GameWorld)getWorld()).removeObject(this);
        }
    }
    public GreenfootImage randomSoup() //gets a random soup
    {
        if(rand == 0){ //creates an onion order
            GreenfootImage onionSoup = new GreenfootImage ("OnionOrder.png");
            setImage(onionSoup);
            isOnionSoup = true;
            return onionSoup;
        } else if(rand == 1) { //creates a tomato order
            GreenfootImage tomatoSoup = new GreenfootImage ("TomatoOrder.png");
            setImage(tomatoSoup);
            isTomatoSoup = true;
            return tomatoSoup;
        }
        return null;
    }
    public boolean getOnionOrder() //returns if it is an onion order
    {
        return isOnionSoup;
    }
    public boolean getTomatoOrder() //returns if it is a tomato order
    {
        return isTomatoSoup;
    }
    public int getCurrVal() //returns the current value of the stat bar
    {
        return currVal;
    }
}
