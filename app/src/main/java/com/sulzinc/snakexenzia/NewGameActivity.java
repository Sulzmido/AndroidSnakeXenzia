package com.sulzinc.snakexenzia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class NewGameActivity extends Activity implements View.OnClickListener,AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        Button playButton=(Button)findViewById(R.id.button);
        playButton.setOnClickListener(this);

        Spinner difficulty=(Spinner)findViewById(R.id.spinner);
        difficulty.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.difficulty,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter);

        Spinner headColor=(Spinner)findViewById(R.id.spinner2);
        headColor.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapte= ArrayAdapter.createFromResource(this,R.array.headcolors,android.R.layout.simple_spinner_item);
        adapte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        headColor.setAdapter(adapte);

        Spinner bodyColor=(Spinner)findViewById(R.id.spinner3);
        bodyColor.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapt= ArrayAdapter.createFromResource(this,R.array.bodycolors,android.R.layout.simple_spinner_item);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bodyColor.setAdapter(adapt);
    }

    public final static String MSSG="com.sulzinc.snakexenzia.MSSG";

    @Override
    public void onClick(View v) {

        Intent gameIntent=new Intent(this,GameActivity.class);
        //System.out.println(diff+" "+hC+" "+bC);
        int []array={diff,hC,bC};
        gameIntent.putExtra(MSSG,array);
        this.startActivity(gameIntent);
    }

    private int diff=0;
    private int hC=0;
    private int bC=0;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int i= parent.getId();
        switch(i){
            case R.id.spinner:
                //System.out.println("diff "+position);
                diff=position;
                break;
            case R.id.spinner2:
                //System.out.println("headColor "+position);
                hC=position;
                break;
            case R.id.spinner3:
                //System.out.println("bodyColor "+position);
                bC=position;
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
