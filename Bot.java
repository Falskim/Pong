import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Computer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bot extends Paddle
{
    Ball ball;
    
    public Bot(int speed, Ball ball){
        super(speed);
        this.ball = ball;
    }
    
    /**
     * Act - do whatever the PlayerOne wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        movement();
    }
    
    private void movement(){
       if(getY() < ball.getY()){
           if(getY() + size/2 < getWorld().getHeight() - 25) 
            this.setLocation(getX(), getY() + speed);
       }
       if(getY() > ball.getY()){
           if(getY() - size/2 > 25) 
            this.setLocation(getX(), getY() - speed);
       } 
    }    
}
