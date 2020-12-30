package com.example.cs496_proj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private List<ImageModel> getAllImages() {
        List<ImageModel> imageModels = new ArrayList<>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        //TODO Cursor Null Check
        while(cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
            imageModels.add(new ImageModel(imageUri));
        }
        cursor.close();
        return imageModels;
    }

}

/*
public static class AlbumAdapter extends BaseAdapter {
    String[] proj = {
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME};
    }
    public static class PhotoData {
        int photoID;
        String photoPath;
    }

    Context mContext;
    ArrayList<PhotoData> photoList;

    public AlbumAdapter(Context context, ArrayList<PhotoData> list) {
        mContext = context;
        photoList = list;
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position) {

        ImageView view = (ImageView)convertView;
        if( view == null ) {
            view = new ImageView(mContext);
            view.setLayoutParams(new AbsListView.LayoutParams(cell_size, cell_size));
            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            view.setPadding(2, 2, 2, 2);
        }

        PhotoData photo = photoList.get(position);
        view.setImageBitmap(imageLoader.getImage(photo.photoID, photo.photoPath);
        return view;
    }
}
*/


/*
public View getView(int position, View convertView, ViewGroup parent) {
    ImageView imageView;
    if (convertView == null) {
        // if it's not recycled, initialize some attributes
        imageView = new ImageView(mContext);
    } else {
        imageView = (ImageView) convertView;
    }
    //bm = BitmapFactory.decodeFile(mBasePath + File.separator + mImgList[position]);
    Bitmap bm = BitmapFactory.decodeFile(MediaStore.Images);
    Bitmap mThumbnail = ThumbnailUtils.extractThumbnail(bm, 300, 300);
    imageView.setPadding(8, 8, 8, 8);
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT));
    imageView.setImageBitmap(mThumbnail);
    return imageView;
} */

