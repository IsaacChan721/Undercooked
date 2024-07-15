import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //imports the array list
/**
 * This world is where most of this game takes place. Simaler to Overcooked!
 * This world requires the player to complete a set of tasks in order to 
 * earn the highest number of coins possible.
 * Credit to Mr. Cohen for the Label actor
 * Credit to Overcooked for the background music
 * 
 * @author Isaac Chan
 * @version January 28, 2022
 */

public class GameWorld extends World
{
    private static int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 640;   
    private static int GRID_LENGTH = 80; //length of each square that makes up the world layout
    private int timeLeft = 10800; //3 minutes
    
    private ArrayList<Order> orderList; //the array list used to organize orders
    
    //labels that are constructed into the world
    Coins coins = new Coins();
    Label coinLabel = new Label ("Coins: 0", 40);
    Label timeLabel = new Label ("Timer: 180s", 40);
    
    //background music
    GreenfootSound sound = new GreenfootSound("OvercookedMusic.mp3");
    
    //a 2D array of the world layout
    int[][] worldLayout = {
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,1,1,1,5,5,1,1,1,0},
        {0,1,0,0,0,0,0,0,6,0},
        {0,3,0,0,0,0,0,0,7,0},
        {0,4,0,0,0,2,0,0,1,0},
        {0,1,1,1,1,1,1,8,1,0},
        {0,0,0,0,0,0,0,0,0,0}
    };
    public void act()
    {
        //counts down the timer per act
        worldTimer();
    }
    public GameWorld()
    {    
        //constructs the world size
        super(SCREEN_WIDTH, SCREEN_HEIGHT, 1); 
        
        //prepares the background music when the world has been constructed
        sound.playLoop();
        
        //constructs the inventory along with the player
        Inventory inventory = new Inventory();
        addObject (inventory, inventory.getWidth() / 2, SCREEN_HEIGHT - inventory.getHeight() / 2);
        addObject (new Player (inventory), SCREEN_WIDTH * 2 / 3, SCREEN_HEIGHT * 2 / 3);
        
        //adds the labels and coin object into the world
        //Credit to Mr. Cohen for the labels
        addObject (coinLabel, 240, 600);
        addObject (timeLabel, 560, 600);
        addObject (coins, 0, 0); //constructed to access coins at all times
        
        //method to construct the world based off of the 2D array
        worldBuild();
        
        //adds orders to the world
        addOrders();
        
        //sets the order of what is displayed first
        setPaintOrder(Player.class, Food.class);
    }
    public void worldBuild() //builds the world based off of the 2D array
    {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 10; j++){
                if(worldLayout[i][j] == 1){ //creates a counter top
                    addObject (new CounterTop (), j * GRID_LENGTH + GRID_LENGTH/2, i * GRID_LENGTH + GRID_LENGTH/2);
                } else if(worldLayout[i][j] == 2){ //creates a cutting board
                    addObject (new CuttingBoard (), j * GRID_LENGTH + GRID_LENGTH/2, i * GRID_LENGTH + GRID_LENGTH/2);
                } else if(worldLayout[i][j] == 3){ //creates an onion generator
                    addObject (new OnionSupply (), j * GRID_LENGTH + GRID_LENGTH/2, i * GRID_LENGTH + GRID_LENGTH/2);
                } else if(worldLayout[i][j] == 4){ //creates a tomato generator
                    addObject (new TomatoSupply (), j * GRID_LENGTH + GRID_LENGTH/2, i * GRID_LENGTH + GRID_LENGTH/2);
                } else if(worldLayout[i][j] == 5){ //creates a cooking pot
                    addObject (new CookingPot (), j * GRID_LENGTH + GRID_LENGTH/2, i * GRID_LENGTH + GRID_LENGTH/2);
                } else if(worldLayout[i][j] == 6){ //creates serving conveyor
                    addObject (new ServingConveyor (), j * GRID_LENGTH + GRID_LENGTH/2, i * GRID_LENGTH + GRID_LENGTH/2);
                } else if(worldLayout[i][j] == 7){ //creates a plate station
                    addObject (new PlateStation (), j * GRID_LENGTH + GRID_LENGTH/2, i * GRID_LENGTH + GRID_LENGTH/2);
                } else if(worldLayout[i][j] == 8){ //creates a trash can
                    addObject (new TrashCan (), j * GRID_LENGTH + GRID_LENGTH/2, i * GRID_LENGTH + GRID_LENGTH/2);
                } 
            }
        }
    }
    public void addOrders() //adds orders to the world
    {
        //initializes the array list
        orderList = new ArrayList<Order>();
        
        //contructs 3 different orders
        for(int i = 1; i < 4; i++){
            Order order = new Order();
            addObject (order, SCREEN_WIDTH * i / 4, 80);
            orderList.add(order);
        }
    }
    public void worldTimer() //the timer that starts when the world is constructed and ends when the timer runs out 
    {
        timeLeft--;
        if(timeLeft % 60 == 0){ //each second is 60 acts
            timeLabel.setValue("Timer: " + timeLeft / 60 + "s");
        }
        if(timeLeft <= 0){ //when the timer hits 0
            sound.stop();
            Greenfoot.setWorld(new GameOverWorld(coins.getCoins()));
        }
    }
    public ArrayList<Order> getOrderList() //getter method to return the order list
    {
        return orderList;
    }
    public Label getCoinLabel() //getter method to return the coin label
    {
        return coinLabel;
    }
    public static int getGrid() //getter method to return the grid's length
    {
        return GRID_LENGTH;
    }
}
