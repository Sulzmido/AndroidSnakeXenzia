package com.sulzinc.snakexenzia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //TitleView tView=new TitleView(this);
        //setContentView(tView);

        setContentView(R.layout.activity_main);

        Button newGame=(Button)findViewById(R.id.button1);
        newGame.setOnClickListener(this);

        Button highScores=(Button)findViewById(R.id.button2);
        highScores.setOnClickListener(this);

        Button quitGame=(Button)findViewById(R.id.button3);
        quitGame.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        int ID=v.getId();

        switch (ID){
            case R.id.button:
                Intent newGameIntent=new Intent(this,NewGameActivity.class);
                this.startActivity(newGameIntent);
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
        }


    }
}
