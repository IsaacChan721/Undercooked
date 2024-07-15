import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This World informs the user about the instructions fot the game
 * 
 * @author Isaac Chan
 * @version January 28, 2022
 */
public class InstructionWorld extends World
{
    private static int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 640;
    
    public InstructionWorld() //constructs the world
    {    
        super(SCREEN_WIDTH, SCREEN_HEIGHT, 1); 
    }
    public void act () { //when space is pressed the game will start
        if (Greenfoot.isKeyDown("space")){
            Greenfoot.setWorld(new GameWorld());
        }
    }
}
