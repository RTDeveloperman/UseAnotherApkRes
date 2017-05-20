package com.example.win81.bab_showtext;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WIN 8.1 on 8/15/2016.
 */
public class ManageAddressBar implements AddressBarRecyclerAdapter.OnItemAddressBarListener {
    private Context mContext;
    private View currView;
    private AddressBarRecyclerAdapter adapter;
    private int recyclerViewId;
    private ManageAddressBarEventHandler addressBarEventHandler;


    public ManageAddressBar(Context context, View currView, int recyclerViewId, ManageAddressBarEventHandler handler) {
        this.mContext = context;
        this.currView = currView;
        this.recyclerViewId = recyclerViewId;
        initList();
        this.addressBarEventHandler = handler;
    }

    private void initList() {
        List<String> dataList = new ArrayList<String>();
        RecyclerView recyclerView = (RecyclerView) currView.findViewById(recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter = new AddressBarRecyclerAdapter(dataList, this));

        LinearLayoutManager layoutManagerHorizontal = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        layoutManagerHorizontal.setReverseLayout(true);

        recyclerView.setLayoutManager(layoutManagerHorizontal);


    }

    @Override
    public void onItemClickAddressBar(int postion, String str) {
     addressBarEventHandler.onItemClickAddressBar(postion,str);
    }

    public void addItem(String str) {
        adapter.add(str);
        adapter.notifyDataSetChanged();
    }

    public void removeItemWithPosition(int position) {
        adapter.removePosition(position);
    }

    public void removeItemWithText(String text) {
        adapter.removeWithText(text);
    }

    public interface ManageAddressBarEventHandler {
        void onItemClickAddressBar(int position, String str);
    }
}
