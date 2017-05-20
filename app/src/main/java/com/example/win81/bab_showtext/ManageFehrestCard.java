package com.example.win81.bab_showtext;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by WIN 8.1 on 8/14/2016.
 */
public class ManageFehrestCard implements View.OnClickListener {
    Context mContext;
    View currView;
    private ImageView ivFavorite, ivShare, ivSond, ivTypeText;

    private TextView tvPrimaryText, tvDetailText;
    private Typeface currFont;

    public ManageFehrestCard(Context context, View view, Typeface typeface) {
        this.mContext = context;
        this.currView = view;
        this.currFont = typeface;
        initVariable();
        setTypFace();
        setOnclick();

    }

    private void initVariable() {
        ivFavorite = (ImageView) currView.findViewById(R.id.fehrest_iv_favorite);
        ivShare = (ImageView) currView.findViewById(R.id.fehrest_iv_share);
        ivSond = (ImageView) currView.findViewById(R.id.fehrest_iv_sound);
        ivTypeText = (ImageView) currView.findViewById(R.id.fehrest_iv_type_text);
        tvPrimaryText = (TextView) currView.findViewById(R.id.fehrest_tv_primary_text);
        tvDetailText = (TextView) currView.findViewById(R.id.fehrest_tv_detail_text);

    }

    private void setTypFace() {
        tvPrimaryText.setTypeface(currFont);
        tvDetailText.setTypeface(currFont);
    }

    private void setOnclick() {
        ivFavorite.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        ivSond.setOnClickListener(this);
        ivTypeText.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fehrest_iv_favorite:
                manageFavorite();
                break;
            case R.id.fehrest_iv_share:
                manageShare();
                break;
            case R.id.fehrest_iv_sound:
                manageSound();
                break;

        }
    }
    private void manageFavorite(){
        Toast.makeText(mContext, "manageFavorite", Toast.LENGTH_SHORT).show();
    }
    private void manageShare(){
        Toast.makeText(mContext, "manageShare", Toast.LENGTH_SHORT).show();

    }
    private void manageSound(){
        Toast.makeText(mContext, "manageSound", Toast.LENGTH_SHORT).show();

    }

}
