package com.example.cs496_proj1.Gallery;

import android.net.Uri;

public class ImageUnit {
    long id;
    Uri imageUri;

    public ImageUnit(long id, Uri imageUri) {
        super();
        this.id = id;
        this.imageUri = imageUri;
    }
}
