import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Paddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Paddle extends Actor
{
    
    int speed;
    int size; //Paddle size in pixel
    int score;
    
    Paddle(){
    }
    Paddle(int speed){
        this.speed = speed + 2;
        this.score = 0;
        this.size = new GreenfootImage("explosion.png").getHeight();
    }
    
    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        
    }
    
    private boolean isValidMove(){
        return !isTouching(Wall.class);
    }
    
    public int getSize(){
        return this.size;
    }
}
