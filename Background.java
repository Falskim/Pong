import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Background here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background extends World
{

    final static int HEIGHT = 400;
    final static int WIDTH = 600;
    final static int SPEED = 2;
    
    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1);
       
        setGame();
    }
    public void setGame(){
        Ball ball = new Ball(SPEED);
        addObject(ball, WIDTH/2, HEIGHT/2);
        addObject(new Player(SPEED, 1), 10, HEIGHT/2);
        //addObject(new Player(SPEED, 2), WIDTH - 10, HEIGHT/2);
        addObject(new Bot(SPEED, ball), WIDTH - 10, HEIGHT/2);
        addObject(new Wall(), WIDTH/2, 0);
        addObject(new Wall(), WIDTH/2, HEIGHT);
        
        System.out.println("=== NEW GAME ===");
        //addObject(new Hud(), WIDTH/2, HEIGHT/2);
    }
    
    public void resetRound(){
        System.out.println("Reset Round !!");
        List<Actor> actList = getObjects(null);
        this.removeObjects(actList);
        setGame();
    }
}
