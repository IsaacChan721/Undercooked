import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This actor stores all the coins earned throughout the game and displays
 * the total coins earned using the label constructed into the world
 * 
 * @author Isaac Chan 
 * @version January 28, 2022
 */
public class Coins extends Actor
{
    //the total amount of coins that the player has
    private int totalCoins = 0;

    public void act()
    {
        //updates the coin label every act 
        Label label = (((GameWorld)getWorld()).getCoinLabel());
        label.setValue("Coins: " + totalCoins);
    }
    public void earnCoins(int coins)
    {
        //increases the total amount of coins when they are earned
        totalCoins += coins;
    }
    public int getCoins()
    {
        return totalCoins;
    }
}
