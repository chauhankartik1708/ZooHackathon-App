package com.example.kartik_chauhan.zoohackathon.Seller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kartik_chauhan.zoohackathon.R;

import java.util.List;

public class AddItemAdapter extends RecyclerView.Adapter<AddItemAdapter.ViewHolder>{

    private List<RecyclerItems> listItems;
    private Context mContext;

    public AddItemAdapter(List<RecyclerItems> listItems, Context mContext) {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RecyclerItems item = listItems.get(position);
        holder.item.setText(item.getItem());
        holder.price.setText(item.getPrice());
        holder.desc.setText(item.getDesc());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView item;
        public TextView price;
        public TextView desc;



        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
            desc = itemView.findViewById(R.id.item_desc);
        }
    }
}
