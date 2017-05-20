package com.example.win81.bab_showtext;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by WIN 8.1 on 8/8/2016.
 */
public class ManagePopup implements View.OnClickListener {

    private PopupWindow pwindo;
    private Context mContext;
    private View parentView;
    private ManageComponentPopup componentPopup;
    private ImageView ivZoomIn, ivZoomOut;
    private TextView tvTranslatedSubtitle, tvNightStatus, tvMore;
    private ToggleButton tglTranslatedSubtitle, tglNightStatus;
    private RelativeLayout rlTranslatedSubtitle, rlNightStatus;
    private Typeface currFont;

    public ManagePopup(Context context, View parentView, ManageComponentPopup componentPopup,  Typeface currFont) {

        this.mContext = context;
        this.parentView = (ViewGroup) parentView.getParent();
        this.componentPopup = componentPopup;
        this.currFont=currFont;
    }


    public void showPopup(int[] location, int heightView) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutPopup = inflater.inflate(R.layout.popup, null);

        initVariable(layoutPopup);
        setTypeFace();
        setOnclick();

        buildPopup(layoutPopup, location, heightView);


    }


    private void initVariable(View currentView) {
        ivZoomIn = (ImageView) currentView.findViewById(R.id.popup_iv_zoom_in);
        ivZoomOut = (ImageView) currentView.findViewById(R.id.popup_iv_zoom_out);
        tvNightStatus = (TextView) currentView.findViewById(R.id.popup_tv_nightStatus);
        tvTranslatedSubtitle = (TextView) currentView.findViewById(R.id.popup_tv_translatedSubtitles);
        tvMore = (TextView) currentView.findViewById(R.id.popup_tv_more);
        tglNightStatus = (ToggleButton) currentView.findViewById(R.id.popup_tgl_nightStatus);
        tglTranslatedSubtitle = (ToggleButton) currentView.findViewById(R.id.popup_tgl_translatedSubtitles);
        rlNightStatus = (RelativeLayout) currentView.findViewById(R.id.popup_rl_nightStatus);
        rlTranslatedSubtitle = (RelativeLayout) currentView.findViewById(R.id.popup_rl_translatedSubtitles);

    }

    private void setTypeFace() {
        tvNightStatus.setTypeface(currFont);
        tvTranslatedSubtitle.setTypeface(currFont);
    }

    private void setOnclick() {

        tvMore.setOnClickListener(this);
        ivZoomOut.setOnClickListener(this);
        ivZoomIn.setOnClickListener(this);
        rlTranslatedSubtitle.setOnClickListener(this);
        rlNightStatus.setOnClickListener(this);


    }

    private void buildPopup(View layoutPopup, int[] location, int heightView) {

        int dimenWidth = ((int) (mContext.getResources().getDimension(R.dimen.shoText_popup_width)));
        int widthPopup = (int) (getScreenSize()[0] - dimenWidth
        );

        pwindo = new PopupWindow(layoutPopup, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        pwindo.setAnimationStyle(R.style.AnimationShoeTextPopup);
        pwindo.setWidth(widthPopup);
        pwindo.setOutsideTouchable(true);
        pwindo.setFocusable(true);

        if (Build.VERSION.SDK_INT >= 21) {
            pwindo.setBackgroundDrawable(mContext.getDrawable(R.drawable.bg));
        } else {
            pwindo.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg));
        }

        pwindo.showAtLocation(parentView, Gravity.NO_GRAVITY, location[0], location[1] + heightView);

    }

    private int[] getScreenSize() {
        int[] screenSize = new int[2];
        Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();

        if (Build.VERSION.SDK_INT >= 13) {

            Point size = new Point();
            display.getSize(size);
            screenSize[0] = size.x;
            screenSize[1] = size.y;

        } else if (Build.VERSION.SDK_INT <= 12) {

            screenSize[0] = display.getWidth();  // deprecated
            screenSize[1] = display.getHeight();  // deprecated
        }

        return screenSize;
    }

    private void manageToggleButton(ToggleButton tglButton) {
        if (tglButton.isChecked() == false) {
            tglButton.setChecked(true);
        } else {
            tglButton.setChecked(false);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.popup_iv_zoom_in:

                componentPopup.onClickZoomIn();

                break;
            case R.id.popup_iv_zoom_out:

                componentPopup.onClickZoomOut();

                break;
            case R.id.popup_rl_nightStatus:

                manageToggleButton(tglNightStatus);
                componentPopup.onClickNightStatus();

                break;
            case R.id.popup_rl_translatedSubtitles:

                manageToggleButton(tglTranslatedSubtitle);
                componentPopup.onClickTranslatedSubtitles();

                break;
            case R.id.popup_tv_more:

                componentPopup.onClickMore();

                break;
        }
    }

    public interface ManageComponentPopup {
        void onClickZoomIn();

        void onClickZoomOut();

        void onClickNightStatus();

        void onClickTranslatedSubtitles();

        void onClickMore();
    }


}
