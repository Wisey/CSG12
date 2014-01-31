
package uk.ac.aber.group12.walkingtour;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import uk.ac.aber.group12.walkingtour.data.Image;
import uk.ac.aber.group12.walkingtour.data.Tour;
import uk.ac.aber.group12.walkingtour.data.TourLocation;

public class LocationCreatorActivity extends Activity implements LocationListener {

    private LocationManager locationManager;
    private String provider;
    private double latitude = 0;
    private double longitude = 0;
    private String imageFilePath;
    private ImageView imageView;
    private Tour tour;

    private Bitmap savedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_creator);
        setupActionBar();

        tour = ((WalkingTourApplication) this.getApplication()).getCurrentTour();
        Toast.makeText(getApplicationContext(), tour.getName(), Toast.LENGTH_SHORT).show();

        // location stuff
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            onLocationChanged(location);
        }

        // photo stuff
        this.imageView = (ImageView) this.findViewById(R.id.imageView);
    }

    public void onPhotoClick(View view) {
        onSelectImage();
        //Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    private void onSelectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(LocationCreatorActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void onCoordinateClick(View view) {
        String Text = "Latitude = " + latitude + " Longitude = " + longitude;
        Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_LONG).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImage = data.getData();

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Cursor cursor = null;
            if (selectedImage != null) {
                cursor = getContentResolver().query(selectedImage, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            }
            cursor.moveToFirst();
            imageFilePath = cursor.getString(0);
            cursor.close();
        }
        if (requestCode == 2) {
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            imageFilePath = c.getString(columnIndex);
            c.close();
        }

        Toast.makeText(getApplicationContext(), imageFilePath, Toast.LENGTH_SHORT).show();
        Bitmap img = BitmapFactory.decodeFile(imageFilePath);
        int h = img.getHeight();
        int w = img.getWidth();
        double t = 512;
        double scaleH = t/h;
        double scaleW = t/w;
        double scale;
        if (scaleH > scaleW) {
            scale = scaleW;
        } else {
            scale = scaleH;
        }

        savedImage = Bitmap.createScaledBitmap(img, (int) (scale*w), (int) (scale*h), false);
        imageView.setImageBitmap(savedImage);
    }

    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        String Text = "LocationCreator Latitude = " + latitude + " Longitude = " + longitude;
        Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_LONG).show();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tour_creator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *
     *
     *
     * @param view
     */
    public void onStartAddLocation(View view) {
        String locName = ((EditText) findViewById(R.id.locName)).getText().toString();
        String locationDes = ((EditText) findViewById(R.id.locDes)).getText().toString();

        if ((locName.matches("")) || locationDes.matches("")||imageFilePath==null) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double time = System.currentTimeMillis() / 1000;
        TourLocation loc;

        loc = new TourLocation(locName, locationDes, imageFilePath, Image.base64(savedImage), latitude, longitude, time);

        tour.addLocation(loc);
        Toast.makeText(getApplicationContext(), String.valueOf(tour.getLocations().size()), Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onStartDeleteLocation(View view) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onProviderDisabled(String arg0) {
    }

    @Override
    public void onProviderEnabled(String arg0) {
    }

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
    }
}
