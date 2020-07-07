package com.sulzinc.snakexenzia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent=getIntent();

        int[] args=intent.getIntArrayExtra(NewGameActivity.MSSG);
        System.out.println(args[0]+" "+args[1]+" "+args[2]);
        GameView gView=new GameView(this,args[0],args[1],args[2]);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(gView);

    }
}
