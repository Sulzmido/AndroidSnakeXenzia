package com.sulzinc.snakexenzia;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Sulaiman Ahmed on 12/16/2016.
 */

public class HighScoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        HighScoreView hsv=new HighScoreView(this);

        setContentView(hsv);
    }
}
