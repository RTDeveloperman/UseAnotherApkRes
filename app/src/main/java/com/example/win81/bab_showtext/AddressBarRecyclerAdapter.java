package com.example.win81.bab_showtext;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AddressBarRecyclerAdapter extends RecyclerView.Adapter<AddressBarRecyclerAdapter.ViewHolder> {

    private List<String> dataList;
    private OnItemAddressBarListener itemClickListener;

    public AddressBarRecyclerAdapter(List<String> dataList, OnItemAddressBarListener listener) {
        this.dataList = dataList;
        this.itemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fehrest_item_address_bar, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tv_text.setText(dataList.get(position));

        holder.tv_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClickAddressBar(position, dataList.get(position));
            }
        });

}


    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public void add(String text) {
        dataList.add(text);
    }

    public void removeWithText(String text) {
        int position = dataList.indexOf(text);
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    public void removePosition(int pos) {
        dataList.remove(pos);
        notifyItemRemoved(pos);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivArrow;
        public TextView tv_text;

        public ViewHolder(View itemView) {
            super(itemView);
            ivArrow = (ImageView) itemView.findViewById(R.id.fehrest__address_bar_item_iv_arrow);
            tv_text = (TextView) itemView.findViewById(R.id.fehrest__address_bar_item_tv_show_text);

        }

    }

    public interface OnItemAddressBarListener {
        void onItemClickAddressBar(int postion, String str);
    }

}

