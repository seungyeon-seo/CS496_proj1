package com.example.cs496_proj1.Gallery;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.cs496_proj1.MainActivity;
import com.example.cs496_proj1.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


@RequiresApi(api = Build.VERSION_CODES.Q)
public class GalleryFragment extends Fragment {
    public ArrayList<ImageUnit> FileList;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ImageAdapter adapter;
    //Context context = this.getContext();
    int REQUEST_IMAGE_CAPTURE = 10;
    ImageView imageview;

    public GalleryFragment() {
    }

    public static GalleryFragment newInstance() {
        GalleryFragment fragment = new GalleryFragment();
        return fragment;
    }

    public RequestManager mGlideRequestManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlideRequestManager = Glide.with(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // mRecyclerView Initialization
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        imageview = (ImageView) view.findViewById(R.id.image_view);

        // Camera Button
        ImageButton camera = (ImageButton) view.findViewById(R.id.camera);
        camera.setOnClickListener(v ->{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //Fragment frag = this;
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        });

        // Set LayoutManager
        layoutManager = new GridLayoutManager(requireContext(), 3);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.scrollToPosition(0);

        // Init contact list
        FileList = LoadImages();

        // Set Adapter
        adapter = new ImageAdapter(FileList, mGlideRequestManager);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)

    public ArrayList<ImageUnit> LoadImages(){
        ArrayList<ImageUnit> FileList = new ArrayList<>();
        String[] projection = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN
        };

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = requireActivity().getContentResolver().query(uri, projection, null, null, null);

        while(cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
            FileList.add(new ImageUnit(id, imageUri));
        }
        cursor.close();

        return FileList;
    }

    @Override
    public void onActivityResult(int requestcode, int resultcode, Intent data){
        super.onActivityResult(requestcode, resultcode, data);
        if (resultcode == Activity.RESULT_OK){
            if (requestcode == REQUEST_IMAGE_CAPTURE){
                Bundle extras = data.getExtras();
                Bitmap imagebitmap = (Bitmap) extras.get(String.valueOf(data));
                imageview.setImageBitmap(imagebitmap);
            }
        }
    }


}
