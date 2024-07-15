import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cooks the food and turns it into soup that can later be served in order
 * to complete an order. The cooking pot can also count how many objects
 * are currently stored in the pot.
 * Credit to Mr. Cohen for the stat bar actor
 * Credit to Relaxing White Noise for the boiling sound
 * 
 * @author Isaac Chan
 * @version January 28, 2022
 */
public class CookingPot extends Counter
{
    //variables for the dimensions of this actor
    private final int length = 75;
    
    //variables for drawing the indicator that shows how much food is in the pot
    //as well as what type of food it is
    private final Color onionIndicator = new Color (102, 0, 204);
    private final Color tomatoIndicator = new Color (255, 70, 70);
    private final int drawIndicatorLength = 10;
    
    //variables used to check for boiling parameters
    int totalFoodInPot = 0;
    boolean isBoiling = false;
    boolean onionInPot = false, tomatoInPot = false;
    boolean hasOnionSoup = false, hasTomatoSoup = false;
    boolean soupInPot = false;
    GreenfootSound sound = new GreenfootSound("BoilingSound.wav");
    
    //StatBar variables are different here for a longer duration
    int maxVal = 400, currVal = 400;
    
    public CookingPot()
    {
        //attaches the stat bar to the cooking pot when it is constructed
        statBar = new SuperStatBar(maxVal, currVal, this, width, height, offset, filledColor, missingColor, hideAtMax);
    }
    public void addedToWorld(World w) //allows the stat bar to be added into the world directly
    {
        w.addObject(statBar, 0, 0);
    }
    public void act()
    {
        boiling();
        noSoup();
    }
    public boolean foodInPot() //checks if the player is putting food into the pot
    {
        findFoodObject = getOneIntersectingObject(Food.class);
        foundFoodObject = (Food)findFoodObject;
        
        if(foundFoodObject != null){ 
            if(foundFoodObject.hasBeenCut() && !soupInPot){ //when adding chopped food into the cooking pot
                if(foundFoodObject instanceof Onion && tomatoInPot == false){ //if the food object is an onion
                    totalFoodInPot++;
                    onionInPot = true; //classifies it into onion soup
                    foodIndicator(); //displays the number of onions in the pot
                    removeTouching(Onion.class);
                } else if(foundFoodObject instanceof Tomato && onionInPot == false){ //if the food object is a tomato
                    totalFoodInPot++;
                    tomatoInPot = true; //classifies it into tomato soup
                    foodIndicator(); //displays the number of tomatoes in the pot
                    removeTouching(Tomato.class);
                }
                return true;
            }
        }
        return false;
    }
    public void boiling() //boils the food
    {
        if(touchingPlayer() && foodInPot()){ //if the player is near the pot with food
            if(!isBoiling && totalFoodInPot == 3){
                isBoiling = true; //starts boiling the food
                if(!setZero){
                    sound.setVolume(100);
                    sound.play();
                    currVal = 0; //reveals the stat bar starting at 0
                    setZero = true;
                }
            } 
        } else if(isBoiling){ //while the food is boiling
            currVal++;
            statBar.update(currVal);
            if(currVal >= maxVal){
                statBar.update(currVal);
                sound.stop();
                soupType(); //determines what kind of soup has been made
                
                //resets the stats to prepare for the next soup
                totalFoodInPot = 0;
                onionInPot = false;
                tomatoInPot = false;
                soupInPot = true;
                setZero = false;
                isBoiling = false;
            }
        }
    }
    public void soupType() //determines what kind of soup has been made
    {
        if(onionInPot){ //makes onion soup
            GreenfootImage onionSoup = new GreenfootImage("OnionSoup.png");
            setImage(onionSoup);
            hasOnionSoup = true;
        } else if (tomatoInPot){ //makes tomato soup
            GreenfootImage tomatoSoup = new GreenfootImage("TomatoSoup.png");
            setImage(tomatoSoup);
            hasTomatoSoup = true;
        }
    }
    public GreenfootImage drawIndicator() //makes the image of the indicator
    {
        GreenfootImage image = new GreenfootImage (drawIndicatorLength, drawIndicatorLength);
        if(onionInPot){
            image.setColor(onionIndicator); //purple
        } else if(tomatoInPot){
            image.setColor(tomatoIndicator); //red
        }
        image.fillRect(0, 0, drawIndicatorLength, drawIndicatorLength);
        return image;
    }
    public void foodIndicator() //indicates how much onion or tomato is in the pot
    {
        GreenfootImage image = getImage();
        GreenfootImage indicator = drawIndicator();
        if(totalFoodInPot != 0){ //draws the indicator on top of the original image
            image.drawImage(indicator, length / 4 * totalFoodInPot - drawIndicatorLength/2, length - drawIndicatorLength);
            setImage(image);
        }
    }
    public void putOnPlate() //puts the soup onto the plate
    {
        if(isTouching(Plate.class) && soupInPot){
            //resets the image to normal again
            GreenfootImage cookingPot = new GreenfootImage("CookingPot.png");
            setImage(cookingPot);
        }
    }
    public String getSoupInPot() //returns what kind of soup is in the pot
    {
        if(hasOnionSoup){ //if it is onion soup
            putOnPlate();
            soupInPot = false;
            return "onion";
        } else if(hasTomatoSoup){ //if it is tomato soup
            putOnPlate();
            soupInPot = false;
            return "tomato";
        }    
        return "";
    }
    public void noSoup() //checks for soup stats during every act
    {
        if(!soupInPot){
            hasOnionSoup = false;
            hasTomatoSoup = false;
        }
    }
}
