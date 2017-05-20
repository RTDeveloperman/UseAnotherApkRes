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
import android.widget.TextView;
import android.widget.Toast;

import com.example.win81.bab_showtext.manageDialog.BaseDialog;

/**
 * Created by WIN 8.1 on 8/14/2016.
 */
public class ManageHint extends BaseDialog implements View.OnClickListener {
    private PopupWindow pwindo;
    private Context mContext;
    private View parentView;
    private ImageView ivClose, ivHintType;
    private TextView tv_showText;
    private Typeface currFont;
    private ManageComponentHint componentHintListener;


    public ManageHint(Context context, View parentView, Typeface currFont, ManageComponentHint componentHintListener) {
        super(context, R.layout.reminder_dialog);
        this.mContext = context;
        this.parentView = parentView;
        this.currFont = currFont;
        this.componentHintListener = componentHintListener;

    }

    public void showHint(String textHint, int[] locationObject, int heightObject,int iconHintType) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutPopup = inflater.inflate(R.layout.hint, null);

        initVariable(layoutPopup);
        setTypeFace();
        setOnclick();
        setTextHint(textHint);
        setIconHintType(iconHintType);
        buildPopup(layoutPopup, locationObject, heightObject);


    }

    private void initVariable(View currentView) {
        ivClose = (ImageView) currentView.findViewById(R.id.hint_iv_close);
        ivHintType = (ImageView) currentView.findViewById(R.id.hint_iv_type);
        tv_showText = (TextView) currentView.findViewById(R.id.hint_tv_show_text);
    }

    private void setTextHint(String textHint) {
        tv_showText.setText(textHint);
    }

    private void setTypeFace() {
        tv_showText.setTypeface(currFont);
    }

    private void setOnclick() {
        ivClose.setOnClickListener(this);
        ivHintType.setOnClickListener(this);

    }

    private void buildPopup(View layoutPopup, int[] location, int heightView) {

        int dimenWidth = ((int) (mContext.getResources().getDimension(R.dimen.shoText_popup_width)));
        int widthPopup = (int) (getScreenSize()[0] - dimenWidth);
        Toast.makeText(mContext, widthPopup+"", Toast.LENGTH_SHORT).show();
        pwindo = new PopupWindow(layoutPopup, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        pwindo.setAnimationStyle(R.style.AnimationHint);
        pwindo.setWidth(widthPopup);
        pwindo.setOutsideTouchable(true);
        pwindo.setFocusable(true);


 /*       WindowManager.LayoutParams lp =  ((Activity) mContext).getWindow().getAttributes();
        lp.dimAmount = 0.110f;
        Window window =  ((Activity) mContext).getWindow();
        if (window != null) {

                window.setAttributes(lp);
                window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        }*/
    /*    WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.dimAmount = 0.7f;*/


        if (Build.VERSION.SDK_INT >= 21) {
            pwindo.setBackgroundDrawable(mContext.getDrawable(R.drawable.hint_bg));
        } else {
            pwindo.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.hint_bg));
        }
        int posWidth = ((int) (mContext.getResources().getDimension(R.dimen.shoText_popup_pos_width)));
        int i=location[0]-posWidth;
        Toast.makeText(mContext, posWidth+"", Toast.LENGTH_SHORT).show();
        pwindo.showAtLocation(parentView, Gravity.NO_GRAVITY, i, location[1] + heightView);

    }

    private void setIconHintType(int imageId){
        ivHintType.setImageResource(imageId);
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
        Toast.makeText(mContext, screenSize[0]+" "+screenSize[1], Toast.LENGTH_SHORT).show();
        return screenSize;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hint_iv_close:
                pwindo.dismiss();
                componentHintListener.onCloseHint();
                break;
            case R.id.hint_iv_type:
                componentHintListener.onTypeHint();
                break;
        }
    }

    public interface ManageComponentHint {
        void onCloseHint();

        void onTypeHint();
    }
}
