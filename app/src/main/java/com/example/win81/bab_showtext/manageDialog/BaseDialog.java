package com.example.win81.bab_showtext.manageDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.win81.bab_showtext.R;


public class BaseDialog {
    protected Context mContext;
    private Dialog dialog = null;
    private int layoutID;
    protected View currView;
    private boolean isFullPage;
    private int gravity = Gravity.CENTER;
    private int screenW, screenH;
    //    private String[] aray;
    public static final int SIMPLE_ITEMS = 0;
    public static final int RB_ITEMS = 1;
    public static final int CB_ITEMS = 2;
    public static final int RB_IC_ITEMS = 3;
    public static final int LAYOUT_CUSTOM = 5;
    public static final int IC_TX_ITEMS = 4;

    public BaseDialog(Context context, int layoutID) {
        mContext = context;
        this.layoutID = layoutID;
        isFullPage = false;
    }

    public BaseDialog() {
        isFullPage = false;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }

    protected void setFullPafe(boolean showFull) {
        isFullPage = showFull;
    }

    protected void onCreateDialog() {
        setScreenSize();
        if (dialog == null) {
            dialog = new Dialog(mContext, android.R.style.Theme_Translucent_NoTitleBar);
        }
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog1, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    KeyBackPressed();
                    return true;
                }

                if (dialog != null)
                    return dialog.onKeyDown(keyCode, event);
                else
                    return false;
            }
        });

        LayoutInflater inflater;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        currView = inflater.inflate(layoutID, null);


        int with = (screenW > screenH) ? screenH : screenW;
        with = with - ((int) (mContext.getResources().getDimension(R.dimen.MarginLeftRightDialog)));

        FrameLayout.LayoutParams params;
        if (isFullPage) {
            params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
        } else {
            params = new FrameLayout.LayoutParams(with, FrameLayout.LayoutParams.WRAP_CONTENT);
        }
        params.gravity = gravity;
        params.topMargin = (int) mContext.getResources().getDimension(
                R.dimen.MarginTopBottomDialog);
        params.bottomMargin = (int) mContext.getResources().getDimension(
                R.dimen.MarginTopBottomDialog);
        dialog.setContentView(currView, params);

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.7f;
        Window window = dialog.getWindow();
        if (window != null) {
            try {
                window.setAttributes(lp);
                window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            } catch (Exception e) {
            }
        }

        if (!((Activity) mContext).isFinishing())
            dialog.show();
    }

    public void destroy() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (final Exception e) {
        } finally {
            dialog = null;
        }
    }

    public boolean isShow() {
        return dialog.isShowing();
    }

    protected void KeyBackPressed() {
    }

    private void setScreenSize() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        screenW = display.getWidth();
        screenH = display.getHeight();
    }


}