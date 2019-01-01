import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Paddle
{
    //Controller
    String DOWN;
    String UP;
    public Player(int speed, int player){
        super(speed);
        switch(player){
            case 1:
                this.DOWN = "down";
                this.UP = "up";
                System.out.println("Case 1");
                break;
            case 2:
                this.DOWN = "s";
                this.UP = "w";
                System.out.println("Case 2");
        }
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
       if(Greenfoot.isKeyDown(DOWN)){
           if(getY() + size/2 < getWorld().getHeight() - 25) 
            this.setLocation(getX(), getY() + speed);
       }
       if(Greenfoot.isKeyDown(UP)){
           if(getY() - size/2 > 25) 
            this.setLocation(getX(), getY() - speed);
       } 
    }
}
