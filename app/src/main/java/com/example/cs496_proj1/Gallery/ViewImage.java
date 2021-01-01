package com.example.cs496_proj1.Gallery;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cs496_proj1.R;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class ViewImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        ImageView image = (ImageView) findViewById(R.id.imageView1);
        Bundle extras = getIntent().getExtras();
        String s = extras.getString("uri");
        Uri myuri = Uri.parse(s);
        image.setImageURI(myuri);

    }
}