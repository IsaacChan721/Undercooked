import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The introduction world to the game
 * 
 * @author Isaac Chan 
 * @version January 28, 2022
 */
public class StartWorld extends World
{
    private static int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 640;
    
    public StartWorld() //constructs the start world
    {    
        super(SCREEN_WIDTH, SCREEN_HEIGHT, 1); 
    }
    public void act () { //when enter is pressed
        if (Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new InstructionWorld());
        }
    }
}
