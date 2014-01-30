package uk.ac.aber.group12.walkingtour.data;

import android.graphics.*;
import android.util.*;
import java.io.*;
/**
 * Created by srp11 on 27/01/2014.
 */
public class Image {



    Bitmap image;
    String imageFilePath;

    public Image(){

    }

    /**
     *
     * This is the constructor for an image. It takes in an Image Filepath so that the location can
     * be refered to at a later time.
     *
     * @param imageFilePath This is the String that holds the filepath of the location.
     */
    public Image(String imageFilePath){
        this.imageFilePath=imageFilePath;
       // this.encodedImage=null;
    }
/*
    public String convertimagebase64(Bitmap image){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        this.encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return this.encodedImage;
    }*/

    /**
     *
     * This is the method that returns the image's file path.
     *
     * @return This is the location of the image that has been stored on the device.
     */
    public String getImageFilePath() {
        return imageFilePath;
    }


}
