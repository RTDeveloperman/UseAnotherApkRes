package com.example.win81.bab_showtext;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;

public class FehrestActivity extends Activity {

    public static final int COUNT_DOWN_TIMER = 2000;
    public static final int ANIMATION_DURATION = 1000;

    private View currentView;
    private Typeface typeface;
    private ShowHelpForFehrest showHelpForFehrest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) FehrestActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        currentView = inflater.inflate(R.layout.activity_fehrest, null);
        setContentView(currentView);

        typeface = Typeface.createFromAsset(getAssets(), "IRANSansMobile.ttf");
        showHelpForFehrest = new ShowHelpForFehrest(FehrestActivity.this, currentView, typeface);

        new CountDownTimer(COUNT_DOWN_TIMER, ANIMATION_DURATION) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                showHelpForFehrest.showHelp();
            }
        }.start();


    }
}
