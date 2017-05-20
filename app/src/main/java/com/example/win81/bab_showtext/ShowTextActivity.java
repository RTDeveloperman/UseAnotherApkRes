package com.example.win81.bab_showtext;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ShowTextActivity extends AppCompatActivity implements View.OnClickListener, ShowHelpForShowText.ShowHelpHandler {

    public static final int COUNT_DOWN_TIMER = 2000;
    public static final int ANIMATION_DURATION = 1000;

    RelativeLayout toolbar, matn;
    RelativeLayout info;
    LinearLayout sout;
    private Animation mMoveDown, mMoveUp;
    private boolean mIsShowingPanel = true;
    private boolean boolStatusHelp = false;
    boolean isHelp = false;
    private Typeface typeface;
    private ShowHelpForShowText showHelp;
    private View currentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) ShowTextActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        currentView = inflater.inflate(R.layout.page, null);

        setContentView(currentView);
        (findViewById(R.id.action_audio_play_pause)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShowTextActivity.this, "clickForTestByVatanDoost", Toast.LENGTH_SHORT).show();
            }
        });


        initViews();
        typeface = Typeface.createFromAsset(getAssets(), "IRANSansMobile.ttf");
        //***------------------initShowHelp---------------------------------------------------------
        showHelp = new ShowHelpForShowText(ShowTextActivity.this, currentView, typeface, this);

        //------------------------------------------------------------------------------------------
        new CountDownTimer(COUNT_DOWN_TIMER, COUNT_DOWN_TIMER) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (!boolStatusHelp) {
         //--------------------start show help -----------------------------------------------------
                    showHelp.showHelp();

                } else {
                    runHideAnimation();
                }
            }
        }.start();

    }


    private void runHideAnimation() {
        mIsShowingPanel = false;
        initHideAnimation();
        toolbar.startAnimation(mMoveUp);
    }

    private void initHideAnimation() {
        mMoveUp = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
        mMoveUp.setDuration(ANIMATION_DURATION);
        mMoveUp.setFillBefore(true);
        mMoveUp.setFillEnabled(true);
        mMoveUp.setInterpolator(new AccelerateInterpolator());

        mMoveDown = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.3f);
        mMoveDown.setDuration(ANIMATION_DURATION);
        mMoveDown.setFillBefore(true);
        mMoveDown.setFillEnabled(true);
        mMoveDown.setInterpolator(new AccelerateInterpolator());

        mMoveUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                matn.setOnClickListener(null);
                sout.startAnimation(mMoveDown);
                //info.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                toolbar.clearAnimation();
                toolbar.setVisibility(View.GONE);
                //-------------------------------show help again after aniamtion--------------------
                showHelp.showHelpAfterAnimation();


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mMoveDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                sout.clearAnimation();
                sout.setVisibility(View.GONE);
//                matn.setOnClickListener(ShowTextActivity.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initViews() {
        toolbar = (RelativeLayout) findViewById(R.id.toolbar);
        info = (RelativeLayout) findViewById(R.id.info_panel);
        //       matn = (TextView) findViewById(R.id.matn_quran);
        sout = (LinearLayout) findViewById(R.id.play_panel);
    }

    private void runShowAnimation() {
        mIsShowingPanel = true;
        initShowAnimation();

        toolbar.startAnimation(mMoveDown);
    }

    private void initShowAnimation() {

        mMoveUp = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.3f, Animation.RELATIVE_TO_SELF, 0f);
        mMoveUp.setDuration(ANIMATION_DURATION);
        mMoveUp.setFillBefore(true);
        mMoveUp.setFillEnabled(true);
        mMoveUp.setInterpolator(new AccelerateInterpolator());

        mMoveDown = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mMoveDown.setDuration(ANIMATION_DURATION);
        mMoveDown.setFillBefore(true);
        mMoveDown.setFillEnabled(true);
        mMoveDown.setInterpolator(new AccelerateInterpolator());

        mMoveUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                sout.clearAnimation();
                sout.setVisibility(View.VISIBLE);
///                matn.setOnClickListener(ShowTextActivity.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mMoveDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                matn.setOnClickListener(null);
                sout.startAnimation(mMoveUp);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                toolbar.clearAnimation();
                toolbar.setVisibility(View.VISIBLE);
                //        info.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        if (mIsShowingPanel) {
            runHideAnimation();
        } else {
            runShowAnimation();
        }

    }

    @Override
    public void runHideAnimatioForHelp() {
        runHideAnimation();
    }

    @Override
    public void runShowAnimationForHelp() {
        runShowAnimation();
    }
}
