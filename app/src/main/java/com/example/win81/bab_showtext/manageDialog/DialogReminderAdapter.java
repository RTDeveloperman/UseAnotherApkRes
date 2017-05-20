package com.example.win81.bab_showtext.manageDialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.win81.bab_showtext.R;
import com.example.win81.bab_showtext.ReminderModel;

import java.util.ArrayList;

/**
 * Created by WIN 8.1 on 8/10/2016.
 */
public class DialogReminderAdapter extends BaseAdapter {

    private Context mContext;
    private boolean[] selectedItemsArray;
    private ArrayList<ReminderModel> dataList;
    private ArrayList<ReminderModel> queueDataList = new ArrayList<ReminderModel>();


    public DialogReminderAdapter(Context pContext, ArrayList<ReminderModel> dataList) {
        mContext = pContext;
        this.dataList = dataList;
    }

    public void setSelectedItem(boolean[] selectedItemsArray) {
        this.selectedItemsArray = selectedItemsArray;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_dialog_reminder, viewGroup, false);
        }

        RelativeLayout rvRootLayout = (RelativeLayout) view.findViewById(R.id.item_dialog_reminder_rl_root_layout);
        TextView titleTextView = (TextView) view.findViewById(R.id.item_dialog_reminder_tv_group_name);
        final CheckBox cbAddToGroup = (CheckBox) view.findViewById(R.id.item_dialog_reminder_cb_add_to_group);

        try {
            cbAddToGroup.setChecked(selectedItemsArray[position]);
        } catch (Exception e) {
       //     Toast.makeText(mContext, e + "", Toast.LENGTH_SHORT).show();
        }

        titleTextView.setText(dataList.get(position).getGroupName());
        rvRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickView(cbAddToGroup, position);
            }
        });
        //   titleTextView.setTypeface(Constants.iranSansLight);

        return view;
    }
/**/
    private void onClickView(CheckBox checkBox, int position) {
        boolean isTag = false;
        int positionTag = -1;
        ReminderModel queueModel = new ReminderModel();
        int currIndex=dataList.get(position).getIndex();
        // search to queue for add or removeWithText

        for (int i = 0; i < queueDataList.size(); i++) {
            if (queueDataList.get(i).getIndex() == dataList.get(position).getIndex()) {
                positionTag = i;
                isTag = true;
                break;
            }
        }
        if (isTag == true) {
            queueDataList.remove(positionTag);
            checkBox.setChecked(false);
            //to do... adapter notify
        }else {
            queueModel.setGroupName(dataList.get(position).getGroupName());
            queueModel.setIndex(dataList.get(position).getIndex());
            queueDataList.add(queueModel);
            checkBox.setChecked(true);
            //to do... adapter notify
        }
        //to do... add to something
    }

    public ArrayList<ReminderModel> getQueueDataList() {
        return queueDataList;
    }
}
