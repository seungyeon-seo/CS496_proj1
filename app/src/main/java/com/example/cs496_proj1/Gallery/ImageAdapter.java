package com.example.cs496_proj1.Gallery;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.cs496_proj1.R;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.Q)
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private final ArrayList<ImageUnit> ImgList;
    public RequestManager mRequestManager;
   // Context mcontext;


     // ViewHolder: store item view
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            image.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ViewImage.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    // Constructor
    public ImageAdapter(ArrayList<ImageUnit> ImgList, RequestManager mRequestManager) {
        this.ImgList = ImgList;
        this.mRequestManager = mRequestManager;
    }

    @NonNull
    @Override public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        ImageUnit photo = ImgList.get(position);

        mRequestManager
                .load(photo.imageUri)
                .into(holder.image);

        holder.image.setImageURI(photo.imageUri);

    }

    @Override public int getItemCount() {
        return ImgList.size();
    }



}