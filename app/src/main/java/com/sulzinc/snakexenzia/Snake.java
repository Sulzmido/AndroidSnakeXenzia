package com.sulzinc.snakexenzia;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Snake {

    public Point[] box;

    private Vector2D displacement;
    private Food food;
    private GameView gameView;

    public int snakeLength;
    public int foodEaten;

    private int gameWidth;
    private int gameHeight;

    private Paint framePaint;

    public Snake(GameView g,Food f,int screenW,int screenH,int sH,int hC,int bC){

        gameView=g;
        food=f;

        gameWidth=screenW;
        gameHeight=screenH;

        bLength=sH;

        box=new Point[arraySize];
        displacement=new Vector2D(0,0);
        initArray();

        snakeLength=3;
        foodEaten=0;

        headColor=new Paint();
        bodyColor=new Paint();
        chooseHead(hC);
        chooseBody(bC);

        framePaint = new Paint();
        framePaint.setARGB(0xff, 0x00, 0x00, 0x00);
        framePaint.setStyle(Paint.Style.STROKE);
    }

    private Paint bodyColor;
    private Paint headColor;


    //headColor
    //0 white
    //1 red
    //2 yellow
    //3 green
    //4 blue

    private void chooseHead(int choice){

        switch (choice){
            case 0: headColor.setColor(Color.WHITE);break;
            case 1: headColor.setColor(Color.RED);break;
            case 2: headColor.setColor(Color.YELLOW);break;
            case 3: headColor.setColor(Color.GREEN);break;
            case 4: headColor.setColor(Color.BLUE);break;

        }

    }

    //bodyColor
    //0 yellow
    //1 red
    //2 blue
    //3 green
    //4 white

    private void chooseBody(int choice){

        switch (choice){
            case 0: bodyColor.setColor(Color.YELLOW);break;
            case 1: bodyColor.setColor(Color.RED);break;
            case 2: bodyColor.setColor(Color.BLUE);break;
            case 3: bodyColor.setColor(Color.GREEN);break;
            case 4: bodyColor.setColor(Color.WHITE);break;

        }

    }

    public void draw(Canvas c) {

        for(int i=0;i<snakeLength;i++){

            if(i==0){

                //c.drawRect(box[i].x,box[i].y,box[i].x+bLength,box[i].y+bLength,headColor);
                //c.drawRect(box[i].x,box[i].y,box[i].x+bLength,box[i].y+bLength,framePaint);

                c.drawCircle(box[i].x+bLength/2,box[i].y+bLength/2,bLength/2,headColor);
                c.drawCircle(box[i].x+bLength/2,box[i].y+bLength/2,bLength/2,framePaint);

                continue;
            }

            //c.drawRect(box[i].x,box[i].y,box[i].x+bLength,box[i].y+bLength,bodyColor);
            //c.drawRect(box[i].x,box[i].y,box[i].x+bLength,box[i].y+bLength,framePaint);

            c.drawCircle(box[i].x+bLength/2,box[i].y+bLength/2,bLength/2,bodyColor);
            c.drawCircle(box[i].x+bLength/2,box[i].y+bLength/2,bLength/2,framePaint);


        }
    }


    public void update() {

        if(keyPressed){

            for(int i=(snakeLength-1);i>0;i--){

                box[i].set(box[i-1].x,box[i-1].y);

            }
        }

        box[0].x+=displacement.getX();
        box[0].y+=displacement.getY();

        checkHead();
    }


    private int bLength;
    private final int arraySize=100;

    private void initArray() {

        for(int i=0;i<arraySize;i++){

            if(i==0){
                box[i]=new Point(0,0);
                System.out.println(bLength);
                continue;
            }
            box[i]=new Point(gameWidth,gameHeight);

        }
    }

    private void foodEaten(int x,int y){

        if(x==food.pFood.x && y==food.pFood.y){
            food.respawnFood();
            if(snakeLength<arraySize) snakeLength++;
            foodEaten++;
        }
    }

    private void checkHead() {

        foodEaten(box[0].x,box[0].y);
        checkHeadInScreen();
        checkHeadOnBody();
    }

    private boolean keyPressed=false;

    public void move(int keycode){

        switch(keycode){

            case 1:
                if(displacement.getX()==0 && displacement.getY()==bLength) break;
                displacement.setX(0);
                displacement.setY(-bLength);
                break;
            case 2:
                if(displacement.getX()==0 && displacement.getY()==-bLength) break;
                displacement.setX(0);
                displacement.setY(bLength);
                break;
            case 3:
                if(displacement.getX()==bLength && displacement.getY()==0) break;
                displacement.setX(-bLength);
                displacement.setY(0);
                break;
            case 4:
                if(displacement.getX()==-bLength && displacement.getY()==0) break;
                displacement.setX(bLength);
                displacement.setY(0);
                break;
        }
        update();
        keyPressed=true;
    }

    private void checkHeadInScreen() {

        if(box[0].y<0){
            box[0].y=(gameHeight-bLength);
        }
        if(box[0].x<0){
            box[0].x=(gameWidth-bLength);
        }
        if(box[0].x>=gameWidth){
            box[0].x=0;
        }
        if(box[0].y>=gameHeight){
            box[0].y=0;
        }
    }

    private void checkHeadOnBody() {

        for(int i=1;i<snakeLength;i++){
            if((box[i].y==box[0].y) && (box[i].x==box[0].x)){
                gameView.gameOver=true;
                break;
            }
        }
    }

}
