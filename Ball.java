import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends Actor
{
    
    int BASE_SPEED;
    int vectorX;
    int vectorY;
    float vectorResult; //Resultan vektor x dan y
    int score;
    int speed;
    int[] win; //0 = kiri, 1 = kanan
    
    Ball(){
    }
    Ball(int speed){
        BASE_SPEED = speed;
        this.speed = speed;
        vectorX = speed;
        vectorY = speed;
        randomBallMovement();
        vectorResult = (float)Math.sqrt((vectorX*vectorX)+(vectorY*vectorY));
        win = new int[2];
        System.out.println("Vector X : " + vectorX + " Y : " + vectorY);
        score = 0;
    }
    
    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        moveBall();
        wallCollision();
        paddleCollision();
        outBound();
    }
    
    private void moveBall(){
        setLocation(getX() + vectorX, getY() + vectorY);
    }
    
    private void randomBallMovement(){
        if(new Random().nextInt(2) == 1)
            vectorX = -vectorX;
        if(new Random().nextInt(2) == 1)
            vectorY = -vectorY;
    }
    private void wallCollision(){
        if(isTouching(Wall.class)){
            vectorY = -vectorY;
        }
    }
    
    private void paddleCollision(){
        int paddleSegment = 10; //Pembagian hitbox paddle (1/2 atas, 1/2 bawah)
        if(isTouching(Paddle.class)){
            vectorX = -vectorX;
            //getWorld().addObject(new Explosion(), getX(), getY());
            reflectVector(paddleSegment);
            score++;
        }
        gameDetail();
    }
    
    private void outBound(){
        if(getX() < 10){
            win[1]++;
        }else if(getX() > getWorld().getWidth() - 10){
            win[0]++;
        }else{
            return;
        }
        resetRound();
    }
    
    private void resetRound(){
        speed = BASE_SPEED;
        vectorX = BASE_SPEED;
        vectorY = BASE_SPEED;
        setLocation(getWorld().getWidth()/2 , getWorld().getHeight()/2);
        vectorResult = (float)Math.sqrt((vectorX*vectorX)+(vectorY*vectorY));
        randomBallMovement();
    }
    
    private void reflectVector(int segment){
        Paddle pad = (Paddle)getOneIntersectingObject(Paddle.class);
        int hitPos = this.getY();
        if(pad != null){
            //System.out.println( pad + " at Position x : " + pad.getX() + " y : " + pad.getY() 
            //                    + " size : " + pad.getSize());
            int mid = pad.getY();
            int size = pad.getSize();
            int degMult = 12;   //Derajat sudut per segment 
            float segSize = size/segment; //Masing-masing ukuran segment pada paddle
            int interval = mid - hitPos;; //Selisih titik tengah paddle dengan posisi Y ball
            boolean isInverse = vectorY<0;
            if(interval <= 0)
                interval = -interval;
            
            vectorY = (int)(interval/segSize); //Lokasi ball terkena segment ke-
            if(isInverse)
                vectorY =- vectorY; 
            if(hitPos < mid){
                System.out.println("Hit Top !");
            }
            //Kena bagian bawah paddle
            if(hitPos > mid){ 
                System.out.println("Hit Bottom !");
            }
            adjustVector();
        }
    }
    
    private void adjustVector(){
        int result = (int)Math.sqrt((vectorResult*vectorResult) - (vectorY*vectorY));
        if(score % 10 == 0) speed++; 
        result += Math.abs(vectorY/2) + (speed) - 1;
        System.out.println("Score : "+ score + " " +
                           "X : " + vectorX + " Y : " + vectorY + " " +
                           "Vresult : "+ Math.sqrt((vectorX*vectorX)+(vectorY*vectorY))
                          );                
        if(vectorX < 0)
            vectorX = -result;
        else
            vectorX = result;
    }
    
    private void gameDetail(){
        getWorld().showText("Score : " + score + " " +
                            "Speed : " + speed, getWorld().getWidth()/2, 50);
        getWorld().showText(""+win[0], 10, 50);
        getWorld().showText(""+win[1], getWorld().getWidth() - 10, 50);
        
    }
    
    private void roundEnd(){
        //getWorld().resetRound();
        System.out.println(getWorld());
    }
    /*
    private int getPaddlePos(){
        Paddle act =(Paddle)getOneIntersectingObject(Paddle.class);
        if(act != null){
            System.out.println(act + " at Position x : " + act.getX() + " y : " + act.getY());
            return act.getY();
        }
        return -1;
    }
    private int getPaddleSize(){
        Paddle act =(Paddle)getOneIntersectingObject(Paddle.class);
        if(act != null){
            System.out.println(act + " size : " + act.getSize());
            return act.getSize();
        }
        return -1;
    }
    private float getReflectDegree(int segment){
        Paddle pad =(Paddle)getOneIntersectingObject(Paddle.class);
        int hitPos = this.getY();
        if(pad != null){
            System.out.println( pad + " at Position x : " + pad.getX() + " y : " + pad.getY() 
                                + " size : " + pad.getSize());
            int mid = pad.getY();
            int size = pad.getSize();
            int degMult = 12;   //Derajat sudut per segment 
            float segSize = size/segment; //Masing-masing ukuran segment pada paddle
            int interval = mid - hitPos;; //Selisih titik tengah paddle dengan posisi Y ball
            if(interval < 0)
                interval = -interval;
            
            float segLoc = interval/segSize; //Lokasi ball terkena segment ke-
            //Kena bagian atas paddle
            if(hitPos < mid){
                System.out.println("Hit Top !");
            }
            //Kena bagian bawah paddle
            if(hitPos > mid){ 
                System.out.println("Hit Bottom !");
            }
            float sinValue = segLoc * degMult; //Semakin jauh bola kena titik tengah paddle, semakin besar
            System.out.println("Interval : " + interval + " Seg : " + segLoc + " Sin : " + sinValue ); 
            System.out.println("Vector Y :" + vectorY + " X : " + vectorX);
            return (float)Math.sin(Math.toRadians(sinValue));
        }
        return -1;
    }
    
    private float getReflectSpeed(float degree, int segment){
        float divider = (float)2/segment;
        int result = (int)(degree/divider); //Lmao xd
        boolean isInverse = vectorY<0;
        switch(result){
            case 0:
                vectorY = 0;
                break;
            case 1:
                vectorY = 1;
                break;
            case 2:
                vectorY = 2;
                break;
            case 3:
                vectorY = 3;
                break;
            case 4:
                vectorY = 4;
        }
        if(isInverse)
            vectorY =- vectorY;
        return -1;
    }
    */
}
