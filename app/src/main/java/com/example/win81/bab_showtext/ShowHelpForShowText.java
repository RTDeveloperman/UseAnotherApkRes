package com.example.win81.bab_showtext;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

/**
 * Created by WIN 8.1 on 8/22/2016.
 */
public class ShowHelpForShowText {

    private static final byte HELP_MENU = 0;
    private static final byte HELP_SHOW_TEXT_SETTING = 0;
    private static final byte HELP_FULL_SCREEN = 1;
    private static final byte HELP_AUTO_SCROOL = 2;
    private static final byte HELP_ADD_RECENT = 3;
    private static final byte HELP_SOUND_SEEK_BAR = 4;
    private static final byte HELP_SOUND_REPEAT = 5;
    private static final byte HELP_SOUND_SETTING = 6;
    private static final byte HELP_SOUND_READER = 7;
    private static final byte HELP_PAGE_START = 8;
    private static final byte HELP_RUN_ANIMATION = 9;
    private static final byte HELP_DESTROY_ANIMATION = 10;

    // This const should equal with HELP_PAGE_START Const
    private static final byte HELP_PAGE_END = 9;

    private Activity mActivity;
    private Context mContext;
    private ShowHelpHandler showHelpListener;
    private View currView;
    private Typeface currFont;
    private ShowcaseView showcaseView;
    private byte counter = 1;
    private RelativeLayout.LayoutParams params;


    public ShowHelpForShowText(Activity mActivity, View currView, Typeface currFont, ShowHelpHandler showHelpListener) {
        this.mActivity = mActivity;
        this.currView = currView;
        this.currFont = currFont;
        this.showHelpListener = showHelpListener;
        this.mContext = mActivity.getApplicationContext();
    }

    View.OnClickListener helpOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (counter) {
           /*     case HELP_SHOW_TEXT_SETTING:
                    showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.action_display_setting)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_SHOW_TEXT_SETTING));
                    showcaseView.setContentText(getContentText(HELP_SHOW_TEXT_SETTING));
                    break;*/

                case HELP_FULL_SCREEN:
                    showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.action_full_screen)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_FULL_SCREEN));
                    showcaseView.setContentText(getContentText(HELP_FULL_SCREEN));
                    break;
                case HELP_AUTO_SCROOL:
                    showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.action_automatic_scroll)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_AUTO_SCROOL));
                    showcaseView.setContentText(getContentText(HELP_AUTO_SCROOL));
                    break;
                case HELP_ADD_RECENT:
                    showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.action_last_review)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_ADD_RECENT));
                    showcaseView.setContentText(getContentText(HELP_ADD_RECENT));
                    break;
                case HELP_SOUND_SEEK_BAR:

                    showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.help_seekbar)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_SOUND_SEEK_BAR));
                    showcaseView.setContentText(getContentText(HELP_SOUND_SEEK_BAR));

                    break;
                case HELP_SOUND_REPEAT:
                    showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.action_audio_repeat_layout)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_SOUND_REPEAT));
                    showcaseView.setContentText(getContentText(HELP_SOUND_REPEAT));
                    break;
                case HELP_SOUND_SETTING:
                    showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.action_audio_setting)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_SOUND_SETTING));
                    showcaseView.setContentText(getContentText(HELP_SOUND_SETTING));
                    break;
                case HELP_SOUND_READER:
                    showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.action_audio_reader)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_SOUND_READER));
                    showcaseView.setContentText(getContentText(HELP_SOUND_READER));
                    break;
                case HELP_PAGE_START:
                    showcaseView.setTarget(Target.NONE);
                 //   showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.main)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_PAGE_START));
                    showcaseView.setContentText(getContentText(HELP_PAGE_START));
                    break;
                case HELP_RUN_ANIMATION:
                    showcaseView.hide();
                    showHelpListener.runHideAnimatioForHelp();
                    break;
                case HELP_DESTROY_ANIMATION:
                    showcaseView.hide();
                    showcaseView.destroyDrawingCache();
                    showHelpListener.runShowAnimationForHelp();
                    break;
            }
            counter++;
        }
    };

    public void showHelp() {
        if (Build.VERSION.SDK_INT > 8) {

            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            params.setMargins(60, 60, 60, 200);

            TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
            textPaint.setTypeface(currFont);

            showcaseView = new ShowcaseView.Builder(mActivity)
                    .withMaterialShowcase()
                    .setTarget(new ViewTarget(currView.findViewById(R.id.action_display_setting)))
                    .setContentTitlePaint(textPaint)
                    .setContentTitle(getContentTitle(HELP_SHOW_TEXT_SETTING))
                    .setContentTextPaint(textPaint)
                    .setContentText(getContentText(HELP_SHOW_TEXT_SETTING))
                    .setStyle(R.style.style_for_help)
                    .setOnClickListener(helpOnClick)
                    .blockAllTouches()
                    .build();
            showcaseView.setButtonPosition(params);


        }
    }

    public void showHelpAfterAnimation() {
        if (Build.VERSION.SDK_INT > 8) {
            showcaseView.show();
            showcaseView.setContentTitle(getContentTitle(HELP_PAGE_END));
            showcaseView.setContentText(getContentText(HELP_PAGE_END));
            showcaseView.setTarget(Target.NONE);
          //  showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.main)), true);
            showcaseView.setButtonText(mContext.getResources().getString(R.string.close));
        }
    }

    private String getContentTitle(int index) {
        String title = mContext.getResources().getStringArray(R.array.show_text_content_title)[index];
        return title;

    }

    private String getContentText(int index) {
        String text = mContext.getResources().getStringArray(R.array.show_text_content_text)[index];
        return text;
    }

    //----------------------------------------help part-------------------------------------------------
    public interface ShowHelpHandler {
        void runHideAnimatioForHelp();

        void runShowAnimationForHelp();
    }
}
