package com.example.win81.bab_showtext.manageDialog;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win81.bab_showtext.R;

/**
 * Created by WIN 8.1 on 8/11/2016.
 */
public class DialogReminderAddGroup extends BaseDialog implements View.OnClickListener {
    private TextView tvHeader, tvTitleGroupName;
    private EditText etInputName;
    private Button btnConfirm, btnCancel;
    private DialogReminderAddGroupEventHandler addGroupEventHandler;


    public DialogReminderAddGroup(Context context, DialogReminderAddGroupEventHandler addGroupEventHandler) {
        super(context, R.layout.reminder_add_group_dialog);
        this.addGroupEventHandler=addGroupEventHandler;
    }

    @Override
    public void onCreateDialog() {
        super.onCreateDialog();
        initVariable();
        setTypeFace();
        tvHeader.setText(R.string.create_new_group);
        setOnclick();


    }

    private void initVariable() {
        tvHeader = (TextView) currView.findViewById(R.id.reminder_dialog_header_tv);
        tvTitleGroupName = (TextView) currView.findViewById(R.id.reminder_add_group_tv_group_name);
        etInputName = (EditText) currView.findViewById(R.id.reminder_add_group_et_group_name);
        btnCancel = (Button) currView.findViewById(R.id.cancel_btn);
        btnConfirm = (Button) currView.findViewById(R.id.confirm_btn);
    }

    private void setTypeFace() {
        tvHeader.setTypeface(null);
        tvTitleGroupName.setTypeface(null);
        etInputName.setTypeface(null);
    }

    private void setOnclick() {
        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
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
        }
    }

    private void onConfirm() {
        String value = etInputName.getText().toString();
        if (value.equals(null) || value.equals("")) {
            if (Build.VERSION.SDK_INT >= 24) {
                Toast.makeText(mContext,mContext.getResources().getString(R.string.invalidData), Toast.LENGTH_SHORT).show();
            } else {
                etInputName.setError(Html.fromHtml("<font color='white'>"+mContext.getResources().getString(R.string.invalidData)+" </font>"));
            }
        } else {

            addGroupEventHandler.selectOptionConfirmPressed(value);
             destroy();
        }
}

    private void onCancel() {
        addGroupEventHandler.selectOptionBackPressed();
        destroy();
    }

    @Override
    protected void KeyBackPressed() {
        super.KeyBackPressed();
        onCancel();
    }

    public interface DialogReminderAddGroupEventHandler{
        void selectOptionConfirmPressed(String newGroupName);
        void selectOptionBackPressed();
    }
}
