import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An object that holds the soup and allows it to be served
 * 
 * @author Isaac Chan
 * @version January 28, 2022
 */
public class Plate extends Food
{
    //variables to determine what type of soup it is
    private boolean isOnionSoup = false;
    private boolean isTomatoSoup = false;
    
    public void act()
    {
        takeSoup();
    }
    public void takeSoup() //when grabbing soup from the pot
    {
        //finds a cooking pot object that this is interacting with
        Actor findCookingPot = getOneIntersectingObject(CookingPot.class);
        CookingPot foundCookingPot = (CookingPot)findCookingPot;
        
        if(isTouching(CookingPot.class)){ //if this is touching a cooking pot
            if(foundCookingPot.getSoupInPot().equals("onion")){ //if the pot has onion soup
                //set the image as an onion soup
                GreenfootImage onionSoup = new GreenfootImage ("OnionPlate.png");
                setImage(onionSoup);
                isOnionSoup = true;
            } else if(foundCookingPot.getSoupInPot().equals("tomato")){ //if the pot has tomato soup
                //set the image as a tomato soup
                GreenfootImage tomatoSoup = new GreenfootImage ("TomatoPlate.png");
                setImage(tomatoSoup);
                isTomatoSoup = true;
            }
        }
    }
    public boolean getOnionSoup() //checks if the plate is an onion soup
    {
        return isOnionSoup;
    }
    public boolean getTomatoSoup() //checks if the plate is a tomato soup
    {
        return isTomatoSoup;
    }
}
