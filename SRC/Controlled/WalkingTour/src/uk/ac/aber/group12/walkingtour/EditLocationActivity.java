package uk.ac.aber.group12.walkingtour;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import uk.ac.aber.group12.walkingtour.data.Tour;
import uk.ac.aber.group12.walkingtour.data.TourLocation;

/**
 * Created by srp11 on 30/01/2014.
 */
public class EditLocationActivity extends Activity{


    private ImageView imageView;
    private Tour tour =new Tour(null,null,null);
    private String imageFilePath;
    private double latitude = 0;
    private double longitude = 0;
    private ArrayList<TourLocation> locations;
    private int locationNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_location);


        Intent intent = getIntent();
        int editLoc = intent.getIntExtra("editLocation", 0);
        locationNumber=editLoc;

        tour = ((WalkingTourApplication) this.getApplication()).getCurrentTour();
        locations = tour.getLocations();



        this.imageView = (ImageView) this.findViewById(R.id.imageView);
        String Path=locations.get(editLoc).getImage();
        imageFilePath=Path;
        if(Path !=null){
            Bitmap img= BitmapFactory.decodeFile(Path);
            this.imageView.setImageBitmap(Bitmap.createScaledBitmap(img, 512, 512, false));
        }
        ((TextView) findViewById(R.id.locName)).setText(locations.get(editLoc).getName());
        ((TextView) findViewById(R.id.locDes)).setText(locations.get(editLoc).getDescription());

    }

    public void onCoordinateClick(View view) {
        String Text = "Latitude = " + latitude + " Longitude = " + longitude;
        Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_LONG).show();
    }

    public void onPhotoClickEdit(View view) {
        onChangeImage();

    }

    private void onChangeImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(EditLocationActivity.this);
        builder.setTitle("Change Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);

                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
        Uri selectedImage = data.getData();


        if (requestCode == 1 ) {
            Cursor cursor = getContentResolver().query(selectedImage, new String[] { android.provider.MediaStore.Images.ImageColumns.DATA }, null, null, null);
            cursor.moveToFirst();
            imageFilePath = cursor.getString(0);
            cursor.close();
        }
        if(requestCode == 2 ){
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            imageFilePath = c.getString(columnIndex);
            c.close();
        }

        // String encodedImage = image.convertimagebase64(photo);
        Toast.makeText(getApplicationContext(), imageFilePath, Toast.LENGTH_SHORT).show();
        Bitmap img=BitmapFactory.decodeFile(imageFilePath);
        imageView.setImageBitmap(Bitmap.createScaledBitmap(img, 512, 512, false));
        //imageView.setImageBitmap(img);
        }
    }

    public void onStartEditLocation(View view){
        String locName = ((EditText) findViewById(R.id.locName)).getText().toString();
       String locationDes = ((EditText) findViewById(R.id.locDes)).getText().toString();

       if ((locName.matches("")) || locationDes.matches("")) {
           Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
           return;
        }

        locations.get(locationNumber).setName(locName);
        locations.get(locationNumber).setDescription(locationDes);
        locations.get(locationNumber).setImage(imageFilePath);


        finish();
    }
}
