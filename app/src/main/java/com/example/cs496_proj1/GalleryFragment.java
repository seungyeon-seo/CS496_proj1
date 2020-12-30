package com.example.cs496_proj1;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GalleryFragment extends Fragment {
    private GridView gridview;

    public GalleryFragment() {
    }

    public static GalleryFragment newInstance() {
        GalleryFragment fragment = new GalleryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // request the permission
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

    public static class ImageUnit{
        Long id;
        Uri imageUri;

        public ImageUnit(Long id, Uri imageUri) {
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    public List<ImageUnit> LoadImages(){
        List<ImageUnit> FileList = new ArrayList<>();
        String[] projection = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN
        };

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = Objects.requireNonNull(getActivity()).getContentResolver().query(uri, projection, null, null, null);

        while(cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
            FileList.add(new ImageUnit(id, imageUri));
        }
        cursor.close();
        return FileList;
    }

    static class GalleryAdapter extends BaseAdapter {
        private final List<ImageUnit> FileList;
        private final Context context;
        LayoutInflater inf;

        public GalleryAdapter(List<ImageUnit> FileList, Context context){
            this.FileList = FileList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return FileList.size();
        }

        @Override
        public Object getItem(int position) {
            return FileList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return FileList.get(position).id;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) convertView = inf.inflate((XmlPullParser) context, null);
            ImageView iv = (ImageView) convertView.findViewById(R.id.tabs);      // tabs가 맞는지 모름
            iv.setImageResource(position);// ?
            Glide.with(context)
                    .load(FileList.get(position).imageUri)
                    .centerCrop()
                    .into(iv);


            return convertView;
        }
    }


}