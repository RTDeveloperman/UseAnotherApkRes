package com.example.win81.bab_showtext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by WIN 8.1 on 8/9/2016.
 */
public class ManageCardActs implements View.OnClickListener {
    private final int EVENT_MOURNING = 0;
    private final int EVENT_BIRTH = 1;
    private final int EVENT_FEASTS = 2;
    private final int EVENT_SPECIAL = 3;
    private final int EVENT_PUBLIC = 4;
    private int CURR_STYLE = EVENT_PUBLIC;


    private View currView;
    private Context mContext;
    private ImageView ivMinMiz, ivShare, ivOghat, ivZekr, ivMaxMiz;
    private TextView tvDate, tvShowEvent, tvFirstAct, tvSecondAct, tvShowAlert,tvZekr;
    private CardActsOnClick actsOnClick;
    private RelativeLayout rlBgHeaderCard;
    private LinearLayout llBgCard, llRootLayoutCard;
    private int indexFirstAct, indexSecondAct;
    private Button btnMore;
    private Typeface currFont;
    private Boolean badSabaIsAvailable;

    private final String STATUS_CARD = "statusCard";
    private Boolean boolStatusCard = false;

    private int[] arrayPublicZekrId = {R.drawable.main_zekr_public_sat, R.drawable.main_zekr_public_sun,
            R.drawable.main_zekr_public_mon, R.drawable.main_zekr_public_tue, R.drawable.main_zekr_public_wed,
            R.drawable.main_zekr_public_thu, R.drawable.main_zekr_public_fri};

    private int[] arrayFeastsZekrId = {R.drawable.main_zekr_feasts_sat, R.drawable.main_zekr_feasts_sun,
            R.drawable.main_zekr_feasts_mon, R.drawable.main_zekr_feasts_tue, R.drawable.main_zekr_feasts_wed,
            R.drawable.main_zekr_feasts_thu, R.drawable.main_zekr_feasts_fri};

    private int[] arrayMourningZekrId = {R.drawable.main_zekr_mourning_sat, R.drawable.main_zekr_mourning_sun,
            R.drawable.main_zekr_mourning_mon, R.drawable.main_zekr_mourning_tue, R.drawable.main_zekr_mourning_wed,
            R.drawable.main_zekr_mourning_thu, R.drawable.main_zekr_mourning_fri};


    public ManageCardActs(Context mContext, View currView, CardActsOnClick actsOnClick, Typeface currFont) {
        this.mContext = mContext;
        this.currView = currView;
        this.actsOnClick = actsOnClick;
        this.currFont = currFont;
    }

    private void intiVariable() {
        ivOghat = (ImageView) currView.findViewById(R.id.main_card_acts_iv_oghat);
        ivMinMiz = (ImageView) currView.findViewById(R.id.main_card_acts_iv_min_miz);
        ivShare = (ImageView) currView.findViewById(R.id.main_card_acts_iv_share);
        ivZekr = (ImageView) currView.findViewById(R.id.main_card_acts_iv_zekr);
        ivMaxMiz = (ImageView) currView.findViewById(R.id.main_card_acts_iv_max_mize);
        tvDate = (TextView) currView.findViewById(R.id.main_card_acts_tv_date);
        tvShowEvent = (TextView) currView.findViewById(R.id.main_card_acts_tv_show_event);
        tvFirstAct = (TextView) currView.findViewById(R.id.main_crad_amal_tv_first_act);
        tvSecondAct = (TextView) currView.findViewById(R.id.main_crad_amal_tv_second_act);
        tvZekr = (TextView) currView.findViewById(R.id.main_card_acts_tv_zekr);
        rlBgHeaderCard = (RelativeLayout) currView.findViewById(R.id.main_card_acts_rl_bg_header);
        llBgCard = (LinearLayout) currView.findViewById(R.id.main_card_acts_ll_bg);
        llRootLayoutCard = (LinearLayout) currView.findViewById(R.id.main_card_acts_ll_root);
        btnMore = (Button) currView.findViewById(R.id.main_crad_amal_btn_more);
        tvShowAlert = (TextView) currView.findViewById(R.id.main_card_acts_tv_show_alert);
    }


    public void showCard(Boolean badSabaIsAvailable, int month, int day, int day_of_week) {
        this.badSabaIsAvailable=badSabaIsAvailable;
        intiVariable();
        setTypeface(currFont);
        checkStatusCard();//for check preference
        setOnClick();
        if (badSabaIsAvailable) {
            setDate(month, day_of_week, day);
            setEvent(month, day);
            setCurrentZakrAndIcon(day_of_week);

        } else {
            manageAlert();
        }


    }

    private void manageAlert() {
        setBackgroundForPublic();
        ivZekr.setVisibility(View.GONE);
        ivShare.setVisibility(View.GONE);
        ivOghat.setVisibility(View.GONE);
        tvZekr.setVisibility(View.GONE);
        tvShowEvent.setVisibility(View.INVISIBLE);
        tvShowAlert.setVisibility(View.VISIBLE);
        btnMore.setVisibility(View.VISIBLE);

        tvShowAlert.setTypeface(currFont);
        tvDate.setText(R.string.alert);
        tvDate.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
        tvDate.setTypeface(currFont);
        btnMore.setText(R.string.download_badesaba);
        ivMinMiz.setImageResource(R.drawable.main_card_acts_ic_public_mini_mize);


    }

    private void setDate(int month, int week, int day) {
        String currNameDay = null;
        String currNameMonth = null;
/*        this.dateMonth = month;
        this.dateWeek = week;
        this.dateDay = day;*/


        if (week >= 1 && week <= 7) {
            currNameDay = mContext.getResources().getStringArray(R.array.all_day_week)[week - 1];
        }
        if (month >= 1 && month <= 12) {
            currNameMonth = mContext.getResources().getStringArray(R.array.all_month)[month - 1];
        }

        tvDate.setText(currNameDay + " " + day + " " + currNameMonth);

    }

    private void setEvent(int month, int day) {

        EventModel currEvent = getEventFromDataBase(month, day);


        if (currEvent.getEvent().equals(null) || currEvent.getEvent().equals("")) {
            tvShowEvent.setVisibility(View.INVISIBLE);

            // set Default BG
            CURR_STYLE = EVENT_PUBLIC;
            setBackgroundForPublic();


        } else {
            tvShowEvent.setVisibility(View.VISIBLE);
            tvShowEvent.setText(currEvent.getEvent());
            manageBgCard(currEvent);
        }
    }

    private EventModel getEventFromDataBase(int month, int day) {
        EventModel eventModel = new EventModel(EVENT_BIRTH, "");

        //Do something....

        return eventModel;
    }

    private void setOnClick() {
        ivShare.setOnClickListener(this);
        ivMinMiz.setOnClickListener(this);
        ivOghat.setOnClickListener(this);
        ivMaxMiz.setOnClickListener(this);
        tvFirstAct.setOnClickListener(this);
        tvSecondAct.setOnClickListener(this);
        btnMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_card_acts_iv_share:

                shareData();
                break;
            case R.id.main_card_acts_iv_min_miz:

                boolStatusCard = false;
                saveBoolean(STATUS_CARD, boolStatusCard);
                setMinMiz();
                break;
            case R.id.main_card_acts_iv_oghat:

                showOghat();
                break;
            case R.id.main_crad_amal_tv_first_act:

                /// Do something with indexFirstAct
                actsOnClick.onClickFirstAct(indexFirstAct);
                break;
            case R.id.main_crad_amal_tv_second_act:

                /// Do something with indexSecondAct
                actsOnClick.onClickSecondAct(indexSecondAct);
                break;
            case R.id.main_crad_amal_btn_more:

                actsOnClick.onClickMoreAct(badSabaIsAvailable);
                break;
            case R.id.main_card_acts_iv_max_mize:

                boolStatusCard = true;
                saveBoolean(STATUS_CARD, boolStatusCard);
                setMaxMiz();
                break;

        }
    }

    private void setMinMiz() {

        llRootLayoutCard.setVisibility(View.INVISIBLE);
        ivMaxMiz.setVisibility(View.VISIBLE);


    }

    private void setMaxMiz() {
        ivMaxMiz.setVisibility(View.GONE);
        llRootLayoutCard.setVisibility(View.VISIBLE);
    }

    private void shareData() {
        Toast.makeText(mContext, "shareData", Toast.LENGTH_SHORT).show();

    }

    private void showOghat() {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(mContext.getResources().getString(R.string.uri_show_oghat)));
        intent.setPackage("com.mobiliha.badesaba");
        mContext.startActivity(intent);
    }

    private void setCurrentZakrAndIcon(int day_of_week) {
        ivZekr.setVisibility(View.VISIBLE);
        ivShare.setVisibility(View.VISIBLE);
        ivOghat.setVisibility(View.VISIBLE);
        tvShowAlert.setVisibility(View.GONE);
        tvZekr.setVisibility(View.VISIBLE);

        switch (CURR_STYLE) {
            case EVENT_FEASTS:
                setEventForFeasts(day_of_week);

                break;
            case EVENT_BIRTH:
                setEventForFeasts(day_of_week);

                break;
            case EVENT_MOURNING:
                setEventForMourning(day_of_week);

                break;
            case EVENT_PUBLIC:
                setEventForPublic(day_of_week);

                break;
            case EVENT_SPECIAL:
                setEventForPublic(day_of_week);

                break;
        }


    }


    private void setEventForFeasts(int day_of_week) {
        ivZekr.setImageResource(arrayFeastsZekrId[day_of_week - 1]);

        ivMinMiz.setImageResource(R.drawable.main_card_acts_ic_public_mini_mize);
        ivOghat.setImageResource(R.drawable.main_card_acts_ic_public_prayer_times);
        ivShare.setImageResource(R.drawable.main_card_acts_ic_public_share);
        tvZekr.setTextColor(mContext.getResources().getColor(R.color.main_card_acts_tv_feasts_zekr));

        if (Build.VERSION.SDK_INT < 23) {
            tvDate.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
        } else {
            tvDate.setTextColor(mContext.getColor(R.color.colorBlack));
        }
    }

    private void setEventForMourning(int day_of_week) {
        ivZekr.setImageResource(arrayMourningZekrId[day_of_week - 1]);

        ivMinMiz.setImageResource(R.drawable.main_card_acts_ic_mourning_min_mize);
        ivOghat.setImageResource(R.drawable.main_card_acts_ic_mourning_prayer_times);
        ivShare.setImageResource(R.drawable.main_card_acts_ic_mourning_share);
        tvZekr.setTextColor(mContext.getResources().getColor(R.color.main_card_acts_tv_mourning_zekr));


        if (Build.VERSION.SDK_INT < 23) {
            tvDate.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
        } else {
            tvDate.setTextColor(mContext.getColor(R.color.colorWhite));
        }


    }

    private void setEventForPublic(int day_of_week) {
        ivZekr.setImageResource(arrayPublicZekrId[day_of_week - 1]);

        ivMinMiz.setImageResource(R.drawable.main_card_acts_ic_public_mini_mize);
        ivOghat.setImageResource(R.drawable.main_card_acts_ic_public_prayer_times);
        ivShare.setImageResource(R.drawable.main_card_acts_ic_public_share);
        tvZekr.setTextColor(mContext.getResources().getColor(R.color.main_card_acts_tv_public_zekr));

        if (Build.VERSION.SDK_INT < 23) {
            tvDate.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
        } else {
            tvDate.setTextColor(mContext.getColor(R.color.colorBlack));
        }
    }


    private void manageBgCard(EventModel eventModel) {

        switch (eventModel.getType()) {
            case EVENT_MOURNING:

                CURR_STYLE = EVENT_MOURNING;
                setBackgroundForMourning();

                break;
            case EVENT_FEASTS:

                CURR_STYLE = EVENT_FEASTS;
                setBackgroundForFeasts();
                break;

            case EVENT_BIRTH:

                CURR_STYLE = EVENT_BIRTH;
                setBackgroundForFeasts();

                break;
            case EVENT_SPECIAL:
                CURR_STYLE = EVENT_SPECIAL;

                setBackgroundForPublic();
                break;
            default:

                CURR_STYLE = EVENT_PUBLIC;
                setBackgroundForPublic();
                break;

        }

    }

    private void setBackgroundForFeasts() {
        rlBgHeaderCard.setBackgroundResource(R.drawable.main_bg_feasts_header);
        llBgCard.setBackgroundResource(R.drawable.main_bg_feasts_card);
    }

    private void setBackgroundForMourning() {
        rlBgHeaderCard.setBackgroundResource(R.drawable.main_bg_mourning_header);
        llBgCard.setBackgroundResource(R.drawable.main_bg_mourning_card);
    }

    private void setBackgroundForPublic() {
        rlBgHeaderCard.setBackgroundResource(R.drawable.main_bg_public_header);
        llBgCard.setBackgroundResource(R.drawable.main_bg_public_card);
    }

    public void setActs(String strFirstAct, int indexFirst, String strSecondAct, int indexSecond) {
        if(badSabaIsAvailable) {
            this.indexFirstAct = indexFirst;
            this.indexSecondAct = indexSecond;
            btnMore.setText(mContext.getResources().getString(R.string.more));

            if (strFirstAct.equals(null) || strFirstAct.equals("")) {
                tvFirstAct.setVisibility(View.GONE);


            } else {
                tvFirstAct.setVisibility(View.VISIBLE);
                tvFirstAct.setText(strFirstAct);
            }
            if (strSecondAct.equals(null) || strSecondAct.equals("")) {
                tvSecondAct.setVisibility(View.GONE);

            } else {

                tvSecondAct.setVisibility(View.VISIBLE);
                tvSecondAct.setText(strSecondAct);
            }
            // in ghesmat nyaz be aslah darad
            if (tvFirstAct.getVisibility() == View.GONE && tvSecondAct.getVisibility() == View.GONE ) {
                btnMore.setVisibility(View.GONE);
            }
        }

    }

    private void setTypeface(Typeface typeface) {
        tvSecondAct.setTypeface(typeface);
        tvFirstAct.setTypeface(typeface);
        tvDate.setTypeface(typeface);
        tvShowEvent.setTypeface(typeface);
        tvZekr.setTypeface(typeface);
    }

    private void checkStatusCard() {
        if (getBoolean(STATUS_CARD)) {
            setMaxMiz();
        } else {
            setMinMiz();
        }
    }

    public interface CardActsOnClick {

        void onClickFirstAct(int indexFirstAct);

        void onClickSecondAct(int indexSecondAct);

        void onClickMoreAct(Boolean badSabaIsAvailable);

    }

    //---------------------additional method------------------------------------------------------
    public boolean getBoolean(String key) {
        SharedPreferences preferences = mContext.getSharedPreferences(" SHARED_PREFERENCES_NAME ", android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }


    public void saveBoolean(String key, boolean value) {
        SharedPreferences preferences = mContext.getSharedPreferences(" SHARED_PREFERENCES_NAME ", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
}
