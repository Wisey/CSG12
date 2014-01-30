package uk.ac.aber.group12.walkingtour.data;

import android.graphics.*;
import android.util.*;
import java.io.*;
/**
 * Created by lot15 on 27/01/2014.
 */
public class Image {
    public static String base64(Bitmap image){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, output); //bm is the bitmap object
        byte[] b = output.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }
}
