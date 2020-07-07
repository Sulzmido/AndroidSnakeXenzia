package com.sulzinc.snakexenzia;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class TitleView extends View {

    private Context myContext;

    private int screenW,screenH;
    private Paint redPaint;
    private Paint blackPaint;

    private Bitmap titleGraphic;
    private Bitmap playUp;
    private Bitmap playDown;

    public TitleView(Context context) {

        super(context);
        myContext=context;
        redPaint=new Paint();
        blackPaint=new Paint();

        redPaint.setAntiAlias(false);

      //  redPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        redPaint.setColor(Color.RED);

        blackPaint.setColor(Color.BLACK);

        titleGraphic= BitmapFactory.decodeResource(getResources(),R.drawable.title_graphic);
        playUp=BitmapFactory.decodeResource(getResources(),R.drawable.play_button_up);
        playDown=BitmapFactory.decodeResource(getResources(),R.drawable.play_button_down);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenW=w;
        screenH=h;
    }

    @Override
    protected void onDraw(Canvas c) {

        c.drawRect(0,0,screenW,screenH,blackPaint);
        drawBorder(c,0,0,screenW,screenH,redPaint);
        c.drawBitmap(titleGraphic,0,0,null);




    }

    private void drawBorder(Canvas c,float x1,float y1,float x2,float y2,Paint p){

        c.drawLine(x1,x1,x2,y1,p);
        c.drawLine(x2,y1,x2,y2,p);
        c.drawLine(x2,y2,x1,y2,p);
        c.drawLine(x1,y2,x1,y1,p);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventaction=event.getAction();

        int X=(int)event.getX();
        int Y=(int)event.getY();

        switch (eventaction){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:

                Intent gameIntent=new Intent(myContext,GameActivity.class);
                myContext.startActivity(gameIntent);

                break;
        }
        invalidate();
        return true;
    }
}
