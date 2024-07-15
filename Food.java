import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A general class that shares a few methods and variables that all food
 * possess.
 * 
 * @author Isaac Chan
 * @version January 28, 2022
 */
public class Food extends Actor
{
    //food objects start uncut
    boolean isCut = false;
    
    public void act()
    {
        chopFood();
    }
    public void chopFood() //sets the food as either cut or not
    {
        if(isTouching(CuttingBoard.class)){
            //gets a cutting board thatis touching the food object
            CuttingBoard cuttingBoard = getIntersectingObjects(CuttingBoard.class).get(0);
            
            //determines whether the food is cut or not
            isCut = cuttingBoard.isCuttingFood(isCut);
        }
    }
    public boolean choppingOnCuttingBoard(Counter counter) //checks whether the food is being chopped or not
    {
        if(isTouching(CuttingBoard.class) && counter.getCurrVal() < 120){ //of the food object is still being cut
            return true;
        }
        return false;
    }
    public boolean hasBeenCut() //returns whether the object is cut or not
    {
        return isCut;
    }
}
