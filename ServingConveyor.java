import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //imports array lists
/**
 * Takes different soups and completes orders based off of the order list
 * which then gives the player coins in return for their success. 
 * Credit to Mario for the coins sound effect
 * 
 * @author Isaac Chan 
 * @version January 28, 2022
 */
public class ServingConveyor extends Counter
{
    //finds the array list
    ArrayList<Order> orderList;
    
    //variable used to calculate the number of coins earned
    private static int currVal;
    
    public void act()
    {
        serveFood();
    }
    public void serveFood() //when soup is place on the serving conveyor
    {
        if(isTouching(Plate.class)){ //if this is touching a plate
            //finds the specific plate that this object interacts with
            Actor findPlate = getOneIntersectingObject(Plate.class);
            Plate foundPlate = (Plate)findPlate;
            
            //finds the plate station that this object interacts with
            Actor findPlateStation = getObjectsInRange(GameWorld.getGrid() * 2, PlateStation.class).get(0);
            PlateStation foundPlateStation = (PlateStation)findPlateStation;
            
            //grabs the order list from the game world
            orderList = ((GameWorld)getWorld()).getOrderList();
            
            if(foundPlate.getOnionSoup() && onionExists()){ //if onion soup matches the order
                //return one plate back to the plate station
                foundPlateStation.setPlates(1);
                removeTouching(Plate.class);
                //earn coins
                coinsEarned();
            } else if(foundPlate.getTomatoSoup() && tomatoExists()){ //if tomato soup matches the order
                //return one plate back to the plate station
                foundPlateStation.setPlates(1);
                removeTouching(Plate.class);
                //earn coins
                coinsEarned();
            }
        }
    }
    public boolean onionExists() //checks if the onion order exists
    {
        for(int i = 0; i < 3; i++){
            //searches for an order that has onino soup
            Order oldOrder = orderList.get(i);
            
            //prepares a new order to replace the old one
            Order newOrder = new Order();
            int orderX = orderList.get(i).getX();
            int orderY = orderList.get(i).getY();
            
            if(orderList.get(i).getOnionOrder()){ //if the order list contains onion soup
                //stores the current value of the stat bar
                currVal = oldOrder.getCurrVal();
                
                //replaces the order in the list
                orderList.remove(i);
                orderList.add(newOrder);
                ((GameWorld)getWorld()).removeObject(oldOrder);
                ((GameWorld)getWorld()).addObject(newOrder, orderX, orderY);
                return true;
            } 
        }
        return false;
    }
     public boolean tomatoExists() //checks if the tomato order exists
    {
        for(int i = 0; i < 3; i++){
            //searches for an order that has onino soup
            Order oldOrder = orderList.get(i);
            
            //prepares a new order to replace the old one
            Order newOrder = new Order();
            int orderX = orderList.get(i).getX();
            int orderY = orderList.get(i).getY();
            
            if(orderList.get(i).getTomatoOrder()){ //if the order list contains tomato soup
                //stores the current value of the stat bar
                currVal = oldOrder.getCurrVal();
                
                //replaces the order in the list
                orderList.remove(i);
                orderList.add(newOrder);
                ((GameWorld)getWorld()).removeObject(oldOrder);
                ((GameWorld)getWorld()).addObject(newOrder, orderX, orderY);
                return true;
            } 
        }
        return false;
    }
    public void coinsEarned() //determines the number of coins earned
    {
        //finds the coins object
        Coins coins = ((GameWorld)getWorld()).getObjects(Coins.class).get(0);
        
        //makes a sound when coins are earned
        GreenfootSound sound = new GreenfootSound("MarioCoinSound.wav");
        sound.setVolume(80);
        sound.play();
        
        //creates a multiplier for the points based on the current value of the stat bar
        int multiplier;
        if(currVal > 3250){
            multiplier = 2;
        } else {
            multiplier = 1;
        }
        
        //calculates the coins earned
        int coinsEarned = currVal * multiplier/ 100 + 150;
        
        //adds the coins earned to the coins object
        coins.earnCoins(coinsEarned);
    }
}
