package com.sulzinc.snakexenzia;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class GameView extends View {

    private Context myContext;

    //Screen Dimensions
    private int screenW,screenH;

    // Play Screen dimensions
    private int gameW,gameH;

    //Snake Size.
    private int snakeW,snakeH;

    private Paint blackPaint;
    private Paint redPaint;
    private Paint yellowPaint;
    private Paint greenPaint;
    private Paint bluePaint;
    private Paint whitePaint;
    private Paint textPaint;
    private Paint framePaint;


    private int hC;
    private int bC;
    private int difficulty;



    // diff

    // 0 easy
    // 1 medium
    // 2 hard
    public GameView(Context context,int diff,int hC,int bC) {

        super(context);
        myContext=context;

        this.bC=bC;
        this.hC=hC;

        difficulty=5-diff;

        blackPaint=new Paint();
        redPaint=new Paint();
        yellowPaint=new Paint();
        greenPaint=new Paint();
        bluePaint=new Paint();
        whitePaint=new Paint();

        textPaint=new Paint();
        textPaint.setARGB(0xff, 0xff, 0xff, 0xff);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(20);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setShadowLayer(1, 2, 2, 0xff000000);

        framePaint = new Paint();
        framePaint.setARGB(0xff, 0xff, 0xff, 0xff);
        framePaint.setStyle(Paint.Style.STROKE);

        redPaint.setColor(Color.RED);
        whitePaint.setColor(Color.WHITE);
        blackPaint.setColor(Color.BLACK);
        yellowPaint.setColor(Color.YELLOW);
        greenPaint.setColor(Color.GREEN);
        bluePaint.setColor(Color.BLUE);



        String filename = "myfile";
        String string = "Hello world!";
        FileInputStream outputStream;

        //File file = new File(context.getFilesDir(), filename);

        try {
            //outputStream = context.openFileInput(filename);
            //System.out.println((char) outputStream.read());
            //outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);

        screenH=h;
        screenW=w;

        gameW=screenW;
        gameH=gameW;

        snakeW=screenW/40;
        snakeH=snakeW;

        food=new Food(gameW,gameH,snakeW);
        snake=new Snake(this,food,gameW,gameH,snakeW,hC,bC);
        food.init(snake);

        div=screenW/3;

        running=true;
    }

    @Override
    protected void onDraw(Canvas c) {

        count++;
        if(count%difficulty==0) {
            if(running) gameUpdate();
        }


        //draw black background
        c.drawRect(0,0,gameW,gameH,blackPaint);
        c.drawRect(0,0,gameW,gameH,framePaint);


        //draw Colors that imitate movement
        c.drawRect(0,screenW,div,screenH,redPaint);
        c.drawRect(div,screenW,2*div,screenW+(screenH/2-screenW/2),yellowPaint);
        c.drawRect(div,screenW+(screenH/2-screenW/2),2*div,screenH,bluePaint);
        c.drawRect(2*div,screenW,screenW,screenH,greenPaint);

        c.drawRect(0,screenW,div,screenH,framePaint);
        c.drawRect(div,screenW,2*div,screenW+(screenH/2-screenW/2),framePaint);
        c.drawRect(div,screenW+(screenH/2-screenW/2),2*div,screenH,framePaint);
        c.drawRect(2*div,screenW,screenW,screenH,framePaint);

        // give snake and food canvas to draw/
        snake.draw(c);
        food.draw(c);

        if(isPaused){
            c.drawText("Paused!",gameW/2,gameH/2,textPaint);
        }

        // ay said sth about not liking having a score on the play screen.
        // thats why i commented it out.

       // if(!gameOver){
        //    c.drawText("Score:"+snake.foodEaten*5,screenW/2,screenH/25,textPaint);
        //}

        if(gameOver){

            c.drawText("Game Over! Score: "+(snake.foodEaten*5/difficulty),gameW/2,gameH/2,textPaint);
        }

        invalidate();

    }

    private Snake snake;
    private Food food;

    private volatile boolean running=false;

    public void stop() {
        running=false;
    }

    volatile boolean gameOver=false;

    private void gameUpdate() {

        if(!isPaused && !gameOver){
            snake.update();
        }
    }

    private volatile boolean isPaused=false;

    public void pause() {
        isPaused=true;
    }

    public void resume() {
        isPaused=false;
    }

    private int div;

    private int count=0;

    public final int UP=1;
    public final int DOWN=2;
    public final int LEFT=3;
    public final int RIGHT=4;
    public final int SCREEN=0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int eventaction=event.getAction();

        int X=(int)event.getX();
        int Y=(int)event.getY();

        int keycode=getKeyCode(X,Y);

        switch (eventaction){

            case MotionEvent.ACTION_DOWN:

                // adding break; in the pausing methods stopped the 'Premature gameOver Bug'
                if(keycode==SCREEN && !isPaused) {
                    //System.out.println("Pause!");
                    pause();
                    break;
                }
                if(keycode==SCREEN && isPaused){
                    //System.out.println("Play!");
                    resume();
                    break;
                }

                // adding &&!gameOver stopped the 'Playing after gameOver Bug'.
                if(!isPaused && !gameOver){
                    snake.move(keycode);
                }

                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    private int getKeyCode(int x,int y){

        if((x>0 && x < div)&&(y>screenW && y< screenH)) {
            //System.out.println(LEFT);
            return LEFT;
        }

        if((x>2*div && x < screenW)&&(y>screenW && y< screenH)) {
            //System.out.println(RIGHT);
            return RIGHT;
        }

        if((x>0 && x < screenW)&&(y>0 && y< screenW)) {
            //System.out.println(SCREEN);
            return SCREEN;
        }

        if((x>div && x < 2*div)&&(y>screenW && y< screenH-(screenH/2-screenW/2))) {
            //System.out.println(UP);
            return UP;
        }

        if((x>div && x < 2*div)&&(y>screenW+(screenH/2-screenW/2) && y< screenH)) {
            //System.out.println(DOWN);
            return DOWN;
        }

         return 0;
    }

}
