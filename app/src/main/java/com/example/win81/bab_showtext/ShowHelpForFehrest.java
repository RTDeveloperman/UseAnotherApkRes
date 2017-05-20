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
import com.github.amlcurran.showcaseview.targets.ViewTarget;

/**
 * Created by WIN 8.1 on 8/23/2016.
 */
public class ShowHelpForFehrest {

    private static final byte HELP_MENU = 0;
    private static final byte HELP_FEHREST = 1;
    private static final byte HELP_REMINDER = 2;
    private static final byte HELP_SEARCH = 3;
    private static final byte HELP_ALL_TABS = 4;
    private static final byte HELP_End = 5;

    private Activity mActivity;
    private Context mContext;
    private View currView;
    private Typeface currFont;
    private ShowcaseView showcaseView;
    private byte counter = 1;
    private RelativeLayout.LayoutParams params;

    public ShowHelpForFehrest(Activity mActivity, View currView, Typeface currFont) {
        this.mActivity = mActivity;
        this.currView = currView;
        this.currFont = currFont;
        this.mContext = mActivity.getApplicationContext();
    }
    View.OnClickListener helpOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (counter) {
                case HELP_MENU:
                    showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.action_drawer_menu)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_MENU));
                    showcaseView.setContentText(getContentText(HELP_MENU));
                    break;

                case HELP_FEHREST:
                    showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.action_bar_title_text)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_FEHREST));
                    showcaseView.setContentText(getContentText(HELP_FEHREST));
                    break;

                case HELP_REMINDER:
                    showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.action_remind)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_REMINDER));
                    showcaseView.setContentText(getContentText(HELP_REMINDER));
                    break;

                case HELP_SEARCH:
                    showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.action_search)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_SEARCH));
                    showcaseView.setContentText(getContentText(HELP_SEARCH));
                    break;

                case HELP_ALL_TABS:
            //        showcaseView.setShowcase(new ViewTarget(currView.findViewById(R.id.tab_layout_sliding_tabs)), true);
                    showcaseView.setContentTitle(getContentTitle(HELP_ALL_TABS));
                    showcaseView.setContentText(getContentText(HELP_ALL_TABS));
                    showcaseView.setButtonText(mContext.getString(R.string.close));

                   break;
                case HELP_End:
                    showcaseView.hide();
                    showcaseView.destroyDrawingCache();
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
                    .setTarget(new ViewTarget(currView.findViewById(R.id.action_drawer_menu)))
                    .setContentTitlePaint(textPaint)
                    .setContentTitle(getContentTitle(HELP_MENU))
                    .setContentTextPaint(textPaint)
                    .setContentText(getContentText(HELP_MENU))
                    .setStyle(R.style.style_for_help)
                    .setOnClickListener(helpOnClick)
                    .blockAllTouches()

                    .build();
            showcaseView.setButtonPosition(params);
        }
    }

    private String getContentTitle(int index) {
        String title = mContext.getResources().getStringArray(R.array.fehrest_content_text)[index];
        return title;

    }

    private String getContentText(int index) {
        String text = mContext.getResources().getStringArray(R.array.fehrest_content_title)[index];
        return text;
    }

}
