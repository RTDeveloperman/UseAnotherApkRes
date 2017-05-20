package com.example.win81.bab_showtext.manageDialog;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.win81.bab_showtext.MainActivity;
import com.example.win81.bab_showtext.R;
import com.example.win81.bab_showtext.ReminderModel;
import com.example.win81.managethem.ApplayChangeThem;

import java.util.ArrayList;
import java.util.Random;


public class DialogReminder extends BaseDialog implements View.OnClickListener, DialogReminderAddGroup.DialogReminderAddGroupEventHandler {

    private TextView tvHeader, tvAddGroup;
    private Button btnConfirm, btnCancel;
    private LinearLayout llAddGroup;
    private ListView listViewShowGroups;
    private String titleDialog;
    private DialogReminderAdapter adapter;
    private ArrayList<ReminderModel> dataList;
    private DialogReminderEventHandler handlerListener;
    private Typeface currFont;


    public DialogReminder(Context pContext, DialogReminderEventHandler handlerListener, Typeface currFont) {
        super(pContext, R.layout.reminder_dialog);
        this.handlerListener = handlerListener;
        this.currFont = currFont;

    }

    public void setData(String titleDialog, ArrayList<ReminderModel> dataList) {
        this.titleDialog = titleDialog;
        this.dataList = dataList;
    }

    @Override
    public void onCreateDialog() {
        super.onCreateDialog();
        initVariable();
        setOnClick();
        setTypeFace();
        tvHeader.setText(titleDialog);
        initList();

       new ApplayChangeThem(mContext, currView, MainActivity.PACK_NAME_THEM, "reminder");
    }

    private void initVariable() {
        tvAddGroup = (TextView) currView.findViewById(R.id.remainder_dialog_tv_add_group);
        tvHeader = (TextView) currView.findViewById(R.id.reminder_dialog_header_tv);
        btnCancel = (Button) currView.findViewById(R.id.cancel_btn);
        btnConfirm = (Button) currView.findViewById(R.id.confirm_btn);
        llAddGroup = (LinearLayout) currView.findViewById(R.id.reminder_dialog_ll_add_group);
        listViewShowGroups = (ListView) currView.findViewById(R.id.reminder_dialog_lv_show_groups);
    }

    private void setOnClick() {
        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        llAddGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm_btn:
                onConfirm();

                break;
            case R.id.cancel_btn:
                onCancel();

                break;
            case R.id.reminder_dialog_ll_add_group:
                onAddGroup();

                break;
        }

    }

    private void setTypeFace() {

        tvHeader.setTypeface(currFont);
        tvAddGroup.setTypeface(currFont);

    }

    private void initList() {
        adapter = new DialogReminderAdapter(mContext, dataList);
        listViewShowGroups.setAdapter(adapter);
    }

    private void onConfirm() {
        destroy();
        handlerListener.selectOptionConfirmPressed(adapter.getQueueDataList());//get queueData form Adapter class

    }

    private void onCancel() {
        destroy();
        handlerListener.selectOptionBackPressed();
    }

    private void onAddGroup() {
        DialogReminderAddGroup addGroup = new DialogReminderAddGroup(mContext, this);
        addGroup.onCreateDialog();
        destroy();
    }

    @Override
    public void selectOptionConfirmPressed(String newGroupName) {
        final Random rand = new Random();
        int diceRoll = rand.nextInt();
        ReminderModel reminderModel = new ReminderModel(newGroupName, diceRoll);
        dataList.add(reminderModel);
        onCreateDialog();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void selectOptionBackPressed() {
        onCreateDialog();
    }

    public interface DialogReminderEventHandler {

        void selectOptionConfirmPressed(ArrayList<ReminderModel> queueDataList);

        void selectOptionBackPressed();

    }

    @Override
    protected void KeyBackPressed() {
        super.KeyBackPressed();
        //  onCancel();
    }


}
