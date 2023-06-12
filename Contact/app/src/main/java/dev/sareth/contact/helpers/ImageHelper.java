package dev.sareth.contact.helpers;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import android.util.Base64;

public abstract class ImageHelper {

    public static String BitmapToString(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

}
