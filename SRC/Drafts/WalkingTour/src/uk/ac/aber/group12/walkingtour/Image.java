package uk.ac.aber.group12.walkingtour;

import android.graphics.*;
import android.util.*;
import java.io.*;
/**
 * Created by srp11 on 27/01/2014.
 */
public class Image {



    Bitmap image;
    String encodedImage;

    public Image(){

    }

    public Image(Bitmap image){
        this.image=image;
        this.encodedImage=null;
    }

    public void convertimagebase64(Bitmap image){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        this.encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
    }
}
