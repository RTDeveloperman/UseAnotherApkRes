package com.example.win81.managethem;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ApplayChangeThem {
    private Context mContext;
    private View currView;
    private String packNameThem;
    private StructThem dataList;
    private ReflectResource reflectResource;
    private PackageManager packageManager;
    private Resources resources;

    public ApplayChangeThem(Context mContext, View currView, String packNameThem, String jsonName) {
        this.mContext = mContext;
        this.currView = currView;
        this.packNameThem = packNameThem;
        initResource();
        getJsonLocal getjson = new getJsonLocal(mContext, reflectResource, jsonName);
        dataList = getjson.getMyParsedJson();
        ListView();
        Image_View();
        RelativeLayout();
        LinearLayout();
        Button();
        TextView();
        /*EditText();
        CheckBox();
        RadioButton();
        Spinner();
        ProgressBar();
        SeekBar();
        ToggleButton();*/

    }

    private void initResource() {
        packageManager = mContext.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packNameThem, PackageManager.GET_ACTIVITIES);
            resources = packageManager.getResourcesForApplication(packageInfo.applicationInfo);
            reflectResource = new ReflectResource(resources, packageInfo.packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void ListView() {
        ListView listView;

        for (int i = 0; i < dataList.listView.size(); i++) {
            int resID = mContext.getResources().getIdentifier(dataList.listView.get(i).id, "id", mContext.getPackageName());
            listView = (ListView) currView.findViewById(resID);

            if (!dataList.listView.get(i).bg.equals("")) {
                listView.setBackgroundColor(reflectResource.getResApkColor(dataList.listView.get(i).bg));
            }
        }
    }

    private void Image_View() {
        ImageView imageView;

        for (int i = 0; i < dataList.ImageView.size(); i++) {
            int resID = mContext.getResources().getIdentifier(dataList.ImageView.get(i).id, "id",
                    mContext.getPackageName());

            imageView = (ImageView) currView.findViewById(resID);
            if (!dataList.ImageView.get(i).anim.equals("")) {
                imageView.setAnimation(reflectResource.getResApkAnim(mContext, dataList.ImageView.get(i).anim));
            }
        }
    }

    private void RelativeLayout() {
        RelativeLayout relativeLayout;

        for (int i = 0; i < dataList.RelativeLayout.size(); i++) {
            int resID = mContext.getResources().getIdentifier(dataList.RelativeLayout.get(i).id, "id",
                    mContext.getPackageName());

            relativeLayout = (RelativeLayout) currView.findViewById(resID);

            if (!dataList.RelativeLayout.get(i).bg.equals("")) {
                setBackground(relativeLayout, dataList.RelativeLayout.get(i).bg);
            }

        }
    }

    private void LinearLayout() {
        LinearLayout linearLayout;

        for (int i = 0; i < dataList.LinearLayout.size(); i++) {
            int resID = mContext.getResources().getIdentifier(dataList.LinearLayout.get(i).id, "id",
                    mContext.getPackageName());
            linearLayout = (LinearLayout) currView.findViewById(resID);
            if (!dataList.LinearLayout.get(i).bg.equals("")) {
                setBackground(linearLayout, dataList.LinearLayout.get(i).bg);
            }
        }
    }

    private void Button() {
        Button button;

        for (int i = 0; i < dataList.Button.size(); i++) {
            int resID = mContext.getResources().getIdentifier(dataList.Button.get(i).id, "id", mContext.getPackageName());
            button = (Button) currView.findViewById(resID);

            if (!dataList.Button.get(i).bg.equals("")) {
                setBackground(button, dataList.Button.get(i).bg);
            }
            if (!dataList.Button.get(i).txt.equals("")) {
                button.setText(reflectResource.getResApkString(dataList.Button.get(i).txt));
            }
            if (!dataList.Button.get(i).txt_color.equals("")) {
                button.setTextColor(reflectResource.getResApkColor(dataList.Button.get(i).txt_color));
            }
        }
    }

    private void TextView() {
        TextView textView;

        for (int i = 0; i < dataList.TextView.size(); i++) {
            int resID = mContext.getResources().getIdentifier(dataList.TextView.get(i).id, "id",
                    mContext.getPackageName());
            textView = (TextView) currView.findViewById(resID);

            if (!dataList.TextView.get(i).bg.equals("")) {
                setBackground(textView, dataList.TextView.get(i).bg);
            }
            if (!dataList.TextView.get(i).txt.equals(""))
                textView.setText(reflectResource.getResApkString(dataList.TextView.get(i).txt));

            if (!dataList.TextView.get(i).txt_color.equals(""))
                textView.setTextColor(reflectResource.getResApkColor(dataList.TextView.get(i).txt_color));
        }

    }

    private void EditText() {
        EditText editText;

        for (int i = 0; i < dataList.EditText.size(); i++) {
            int resID = mContext.getResources().getIdentifier(dataList.EditText.get(i).id, "id",
                    mContext.getPackageName());
            editText = (EditText) currView.findViewById(resID);

            if (!dataList.EditText.get(i).bg.equals("")) {
                setBackground(editText, dataList.EditText.get(i).bg);
            }
            if (!dataList.EditText.get(i).txt.equals(""))
                editText.setText(reflectResource.getResApkString(dataList.EditText.get(i).txt));

            if (!dataList.EditText.get(i).txt_color.equals(""))
                editText.setTextColor(reflectResource.getResApkColor(dataList.EditText.get(i).txt_color));

            if (!dataList.EditText.get(i).txt_hint_color.equals(""))
                editText.setHintTextColor(reflectResource.getResApkColor(dataList.EditText.get(i).
                        txt_hint_color));
        }

    }

    private void CheckBox() {
        CheckBox checkBox;

        for (int i = 0; i < dataList.CheckBox.size(); i++) {
            int resID = mContext.getResources().getIdentifier(dataList.CheckBox.get(i).id, "id",
                    mContext.getPackageName());
            checkBox = (CheckBox) currView.findViewById(resID);
            if (!dataList.CheckBox.get(i).bg.equals("")) {
                setBackground(checkBox, dataList.CheckBox.get(i).bg);
            }
        }
    }

    private void RadioButton() {
        RadioButton radioButton;

        for (int i = 0; i < dataList.RadioButton.size(); i++) {
            int resID = mContext.getResources().getIdentifier(dataList.RadioButton.get(i).id, "id",
                    mContext.getPackageName());
            radioButton = (RadioButton) currView.findViewById(resID);
            if (!dataList.RadioButton.get(i).bg.equals("")) {
                setBackground(radioButton, dataList.RadioButton.get(i).bg);
            }
        }
    }

    private void Spinner() {
        Spinner spinner;

        for (int i = 0; i < dataList.Spinner.size(); i++) {
            int resID = mContext.getResources().getIdentifier(dataList.Spinner.get(i).id, "id",
                    mContext.getPackageName());
            spinner = (Spinner) currView.findViewById(resID);
            if (!dataList.Spinner.get(i).bg.equals("")) {
                setBackground(spinner, dataList.Spinner.get(i).bg);
            }
        }
    }

    private void ProgressBar() {
        ProgressBar progressBar;

        for (int i = 0; i < dataList.ProgressBar.size(); i++) {
            int resID = mContext.getResources().getIdentifier(dataList.ProgressBar.get(i).id, "id",
                    mContext.getPackageName());
            progressBar = (ProgressBar) currView.findViewById(resID);
            if (!dataList.ProgressBar.get(i).bg.equals("")) {
                setBackground(progressBar, dataList.ProgressBar.get(i).bg);
            }
        }
    }

    private void SeekBar() {
        SeekBar seekBar;

        for (int i = 0; i < dataList.SeekBar.size(); i++) {
            int resID = mContext.getResources().getIdentifier(dataList.SeekBar.get(i).id, "id",
                    mContext.getPackageName());
            seekBar = (SeekBar) currView.findViewById(resID);
            if (!dataList.SeekBar.get(i).bg.equals("")) {
                setBackground(seekBar, dataList.SeekBar.get(i).bg);
            }
        }
    }

    private void ToggleButton() {
        ToggleButton toggleButton;

        for (int i = 0; i < dataList.ToggleButton.size(); i++) {
            int resID = mContext.getResources().getIdentifier(dataList.ToggleButton.get(i).id, "id",
                    mContext.getPackageName());
            toggleButton = (ToggleButton) currView.findViewById(resID);
            if (!dataList.ToggleButton.get(i).bg.equals("")) {
                setBackground(toggleButton, dataList.ToggleButton.get(i).bg);
            }
        }
    }

    private void setBackground(View view, String bg) {

        if (Build.VERSION.SDK_INT > 7 && Build.VERSION.SDK_INT < 16)
            view.setBackgroundDrawable(reflectResource.getResApkDrawable(bg));

        if (Build.VERSION.SDK_INT > 15)
            view.setBackground(reflectResource.getResApkDrawable(bg));
    }
}
