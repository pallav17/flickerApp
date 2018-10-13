package com.example.pshah1.flickerapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class sampleAdapter  extends RecyclerView.Adapter<sampleAdapter.sampleViewHolder> {

    private Context context;
    private String[] titleArray;
    private String[] imageUrlArray;
    public sampleAdapter(Context context, String[] titleArray, String[] imageUrlArray ){
        this.context = context;
        this.titleArray = titleArray;
        this.imageUrlArray = imageUrlArray;
    }

    @NonNull
    @Override
    public sampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        for(String name: imageUrlArray){
            Log.w("Class", "Name :::::: ---------- "+name);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false);
        return new sampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sampleViewHolder holder, int position) {
        holder.title.setText(titleArray[position]);
        Picasso.get().load(imageUrlArray[position]).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return titleArray.length;
    }

    public class sampleViewHolder extends RecyclerView.ViewHolder{
        TextView title ;
        ImageView image;
        public sampleViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);

        }
    }
}
