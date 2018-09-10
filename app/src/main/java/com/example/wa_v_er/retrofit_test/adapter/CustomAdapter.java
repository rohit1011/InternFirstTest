package com.example.wa_v_er.retrofit_test.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wa_v_er.retrofit_test.R;
import com.example.wa_v_er.retrofit_test.object.RetroPhoto;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<RetroPhoto> dataList;
    private Context context;

    public CustomAdapter(Context context,List<RetroPhoto> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView item_title,item_producer,item_price;
        ImageView item_image;

        public final View mView;


        TextView txtTitle;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            item_title = itemView.findViewById(R.id.item_title);
            item_image = itemView.findViewById(R.id.item_image);
            item_producer = itemView.findViewById(R.id.producer);
            item_price = itemView.findViewById(R.id.price);
            //coverImage = mView.findViewById(R.id.coverImage);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
      /*  holder.txtTitle.setText(dataList.get(position).getTitle());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);*/



        holder.item_title.setText(dataList.get(position).getLanguage());
        holder.item_producer.setText(dataList.get(position).getProducer());
        holder.item_price.setText(dataList.get(position).getPrice());
        Glide
                .with(context)
                .load(Uri.parse(dataList.get(position).getUrl()))
                .into(holder.item_image);
        holder.item_image.setImageURI(Uri.parse(dataList.get(position).getUrl()));
        //holder.address.setText(current.getAddress());
    }






    @Override
    public int getItemCount() {
        return dataList.size();
    }
}