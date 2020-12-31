package com.example.cs496_proj1.Gallery;

import android.net.Uri;

public class ImageUnit {
    int id;
    Uri imageUri;

    public ImageUnit(int id, Uri imageUri) {
        super();
        this.id = id;
        this.imageUri = imageUri;
    }
}
