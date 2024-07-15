import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This actor takes an actor of the food class and determines whether the object
 * is cut or not. If not, then the cutting board allows this object to be cut.
 * Credit to Mr. Cohen for the stat bar actor
 * Credit to Hiroyuki Terada for the cutting sounds
 * 
 * @author Isaac Chan 
 * @version January 28, 2022
 */

public class CuttingBoard extends Counter
{
    public CuttingBoard() //constructs a cutting board
    {
        //adds a stat bar to the cutting board
        statBar = new SuperStatBar(maxVal, currVal, this, width, height, offset, filledColor, missingColor, hideAtMax);
    }
    public void addedToWorld(World w) //allows the stat bar to be added into the world directly
    {
        w.addObject(statBar, 0, 0);
    }
    public void act()
    {
        chopping();
    }
    public void chopping() //while the food is being chopped
    {
        //if the polayer presses q, is near the cutting board, is touching food, and that food isn't a plate
        if(Greenfoot.isKeyDown("q") && touchingPlayer() && touchingFood() && !isTouching(Plate.class)){
            if(!qWasPressed){
                findFoodObject = getOneIntersectingObject(Food.class);
                foundFoodObject = (Food)findFoodObject; //casts the actor into the food class
                
                //chops the food up
                foundFoodObject.chopFood();
                
                //checks that the previous button pressed was q
                qWasPressed = true;
            } else { // if q was previously pressed
                //checks that the previous button pressed was not q
                qWasPressed = false;    
            }
        }
    }
    public boolean isCuttingFood(boolean isCut) //cuts the food
    {
        if(!isCut){ //if the food object has not been cut yet
            //add the sound of cutting the food
            GreenfootSound sound = new GreenfootSound("CuttingSound.wav");
            sound.setVolume(90);
            if(!setZero){ //sets the stat bar to zero in order to reveal it
                currVal = 0;
                setZero = true;
            }
            
            //gradually increases the stat bar; cutting the food
            statBar.update(currVal);
            currVal++;
            
            if(currVal % 10 == 0){ //every 10 frames that pass by plays a sound
                sound.play();
            }
            if(currVal >= maxVal){ //if the stat bar is full
                sound.stop();
                statBar.update(currVal);
                setZero = false;
                return true;
            }
        } else if(isCut){ //if the object is already cut then nothing will happen
            return true;
        }
        return false;
    }
    public boolean hasFood() //checks whether the cutting boar is touching food
    {
        if(isTouching(Food.class)){
            return true;
        }
        return false;
    }
}
