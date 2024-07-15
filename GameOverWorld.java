import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world that shows up at the end and also shows the score that the player has by the end
 * 
 * @author Isaac Chan
 * @version January 28, 2022
 */
public class GameOverWorld extends World
{
    private static int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 640;
    int coins;
    
    public GameOverWorld(int totalCoins) //constructs the world
    {    
        super(SCREEN_WIDTH, SCREEN_HEIGHT, 1); 
        determineStars(totalCoins);
        
        //Credit to Mr. Cohen for the label
        Label finalScore = new Label("Coins: " + totalCoins, 60);
        addObject(finalScore, 400, 200);
    }
    public void determineStars (int coins) { 
        if (coins > 300){
            addObject(new Star (), SCREEN_WIDTH / 4, SCREEN_HEIGHT / 2);
        }
        if (coins > 600){
            addObject(new Star (), SCREEN_WIDTH * 2 / 4, SCREEN_HEIGHT / 2);
        }
        if (coins > 900){
            addObject(new Star (), SCREEN_WIDTH * 3 / 4, SCREEN_HEIGHT / 2);
        }
    }
}
