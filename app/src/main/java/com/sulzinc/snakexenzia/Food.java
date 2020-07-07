package com.sulzinc.snakexenzia;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

public class Food {

    private int fWIDTH;
    private int fHEIGHT;
    private Random rnd;

    private Paint redPaint;

    public Food(int screenW,int screenH,int snakeW){

        fWIDTH=screenW;
        fHEIGHT=screenH;

        foodSize=snakeW;

        rnd=new Random();

        pFood=new Point();

        pFood.x=rndMultiple(fWIDTH);
        pFood.y=rndMultiple(fHEIGHT);

        redPaint=new Paint();
        redPaint.setColor(Color.RED);
    }

    private int rndMultiple(int bound){
        int num;
        while(true){
            num=Math.abs(rnd.nextInt()%bound);
            if(num%foodSize==0) return num;
        }
    }

    public Point pFood;
    private int foodSize;

    public void draw(Canvas c) {

        //c.drawRect(pFood.x,pFood.y,pFood.x+foodSize,pFood.y+foodSize,redPaint);

        c.drawCircle(pFood.x+foodSize/2,pFood.y+foodSize/2,foodSize/2,redPaint);
    }

    public void respawnFood(){

        pFood.x=rndMultiple(fWIDTH);
        pFood.y=rndMultiple(fHEIGHT);
        for(int i=0;i<fSnake.snakeLength;i++){

            if(pFood.x==fSnake.box[i].x && pFood.y==fSnake.box[i].y){
                respawnFood();
            }
        }
    }

    private Snake fSnake;
    public void init(Snake snake) {
        fSnake=snake;
    }
}
