package com.serbi.sampledetailedrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DataClass> dataList;

    public void setSearchList(ArrayList<DataClass> list) {
        this.dataList = list;
        notifyDataSetChanged();
    }

    public DataAdapter(Context context, ArrayList<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recycler_card_title.setText(dataList.get(position).getDataTitle());
        holder.recycler_card_description.setText(dataList.get(position).getDataDescription());
        holder.recycler_card_language.setText(dataList.get(position).getDataLanguage());
        holder.recycler_card_image.setImageResource(dataList.get(position).getDataImage());

        holder.recycler_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("title", dataList.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("description", dataList.get(holder.getAdapterPosition()).getDataDescription());
                intent.putExtra("language", dataList.get(holder.getAdapterPosition()).getDataLanguage());
                intent.putExtra("image", dataList.get(holder.getAdapterPosition()).getDataImage());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView recycler_card_image;
        TextView recycler_card_title, recycler_card_description, recycler_card_language;
        CardView recycler_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recycler_card_image = itemView.findViewById(R.id.recycler_card_image);
            recycler_card_title = itemView.findViewById(R.id.recycler_card_title);
            recycler_card_description = itemView.findViewById(R.id.recycler_card_description);
            recycler_card_language = itemView.findViewById(R.id.recycler_card_language);
            recycler_card = itemView.findViewById(R.id.recycler_card);
        }
    }

}