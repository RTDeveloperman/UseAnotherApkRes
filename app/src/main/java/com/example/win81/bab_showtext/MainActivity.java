package com.example.win81.bab_showtext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.win81.bab_showtext.manageDialog.DialogReminder;
import com.example.win81.managethem.ApplayChangeThem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ManagePopup.ManageComponentPopup,
        ManageCardActs.CardActsOnClick, DialogReminder.DialogReminderEventHandler, ManageHint.ManageComponentHint {

    private ImageView btnShowPopup;
    private Button btnShowDialog;
    private ImageView btnShowHint;
    private View currentView;
    private ManagePopup popupShowText;
    private Typeface currFont;
    public static String PACK_NAME_THEM = "com.mobiliha.mth.theme_apk_3";
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) MainActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        currentView = inflater.inflate(R.layout.activity_main, null);
        setContentView(currentView);
        btnShowPopup = (ImageView) findViewById(R.id.btn$showPopUp);
        btnShowDialog = (Button) findViewById(R.id.btn$showdialog);
        btnShowHint = (ImageView) findViewById(R.id.btn_showHint);

        currFont = Typeface.createFromAsset(getAssets(), "IRANSansMobile.ttf");

//---------------------------initPopup--------------------------------------------------------------
        popupShowText = new ManagePopup(this, currentView, this, currFont);
        btnShowPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int[] loc = new int[2];
                btnShowPopup.getLocationOnScreen(loc);
                popupShowText.showPopup(loc, btnShowPopup.getHeight());
            }

        });

//--------------------------initCardAmal------------------------------------------------------------

        ManageCardActs cardAmal = new ManageCardActs(this, currentView, this, currFont);
        cardAmal.showCard(true, 8, 24, 7);
        cardAmal.setActs("بلدبلدبل", 3, "", 51);

//------------------------------initDialogReminder--------------------------------------------------

        final DialogReminder dialogReminder = new DialogReminder(this, this, currFont);
        ArrayList<ReminderModel> dataList = new ArrayList<ReminderModel>();
        dialogReminder.setData(getString(R.string.create_reminder), dataList);

        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogReminder.onCreateDialog();
            }
        });

//------------------------------------initItemCard--------------------------------------------------

        ManageFehrestCard manageFehrestCard = new ManageFehrestCard(this, currentView, currFont);

//-------------------------------------init Hint----------------------------------------------------
        final ManageHint manageHint = new ManageHint(this, currentView, currFont, this);

        btnShowHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] loc1 = new int[2];
                btnShowHint.getLocationOnScreen(loc1);
                String textHint = getResources().getString(R.string.alert_text);
                manageHint.showHint(textHint, loc1, btnShowHint.getHeight(), R.drawable.ic_audio_play);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //------------------------------------------initVariable for change Them----------------------------
        manageChangThem();
    //    get_allpackagename();
    }

    @Override
    public void onClickZoomIn() {
        startActivity(new Intent(MainActivity.this, ExpectAvtivity.class));
        quickToast("ZoomIN");
    }

    @Override
    public void onClickZoomOut() {
        startActivity(new Intent(MainActivity.this, ShowTextActivity.class));
        quickToast("ZoomOut");
    }

    @Override
    public void onClickNightStatus() {
        quickToast("NightStatus");
    }

    @Override
    public void onClickTranslatedSubtitles() {
        quickToast("TranslatedSubtitles");
    }

    @Override
    public void onClickMore() {
        quickToast("More");
    }

    public void quickToast(String str) {
        Toast.makeText(MainActivity.this, str + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickFirstAct(int indexFirstAct) {
        quickToast("" + indexFirstAct);
    }

    @Override
    public void onClickSecondAct(int indexSecondAct) {
        quickToast("" + indexSecondAct);
    }

    @Override
    public void onClickMoreAct(Boolean badSabaIsAvailable) {
        quickToast(badSabaIsAvailable + "  onClickMoreAct");
    }
    //--------------------relate to dialog--------------------------------------------------------

    @Override
    public void selectOptionConfirmPressed(ArrayList<ReminderModel> queueDataList) {
        //Do something with queueDataList for example add to dataBase:)
    }

    @Override
    public void selectOptionBackPressed() {
        quickToast("selectOptionBackPressed");
    }

    @Override
    public void onCloseHint() {
        quickToast("onCloseHint");
    }

    @Override
    public void onTypeHint() {
        quickToast("onTypeHint");
    }
    //---------------------additional method------------------------------------------------------

    public int getSaveString(String key) {
        SharedPreferences preferences = getSharedPreferences(" SHARED_PREFERENCES_NAME ",
                android.content.Context.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

    public void saveString(String key, int value) {
        SharedPreferences preferences = getSharedPreferences(" SHARED_PREFERENCES_NAME ",
                android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private void manageChangThem(){

        flag = getSaveString("Vlaue");
        if (flag == 1) {
            PACK_NAME_THEM = "com.mobiliha.mth.theme_apk_3";
            new ApplayChangeThem(this, currentView, PACK_NAME_THEM, getLocalClassName().toLowerCase());
        }else{
            PACK_NAME_THEM = "com.mobiliha.mth.theme_apk_4";
            new ApplayChangeThem(this, currentView, PACK_NAME_THEM,getLocalClassName().toLowerCase() );
        }

        Button btnOkay = (Button) findViewById(R.id.confirm_btn);
        Button btnCancel = (Button) findViewById(R.id.cancel_btn);

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                saveString("Vlaue", flag);
             onStart();

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                saveString("Vlaue", flag);
                onStart();
            }
        });
    }

    private void get_allpackagename(){

        final PackageManager pm = getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            Log.i("barchasbekhasi hast", "Installed package :" + packageInfo.packageName);
            Log.i("barchasbekhasi hast", "Source dir : " + packageInfo.sourceDir);
            Log.i("barchasbekhasi hast", "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
            if (packageInfo.packageName.contains("com.mobiliha.mth.theme_apk")){
                Toast.makeText(this, " O.k", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
