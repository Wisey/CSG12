
package uk.ac.aber.group12.walkingtour;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import uk.ac.aber.group12.walkingtour.data.Tour;
import uk.ac.aber.group12.walkingtour.data.TourLocation;
import android.text.format.Time;

import java.util.Date;

import uk.ac.aber.group12.walkingtour.data.Image;
import uk.ac.aber.group12.walkingtour.data.TourLocation;

public class LocationCreatorActivity extends Activity implements LocationListener {

    private static final int CAMERA_REQUEST = 1888;
    private LocationManager locationManager;
    private String provider;
    private double latitude = 0;
    private double longitude = 0;
    private Image image;
    private TourLocation loca;
    private TextView textView;
    private ImageView imageView;
    private Tour tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_creator);
        setupActionBar();

        Intent i = getIntent();
        tour=(Tour)i.getSerializableExtra("tour");

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
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    public void onCoordinateClick(View view) {
        String Text = "Latitude = " + latitude + " Longitude = " + longitude;
        Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_LONG).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            image = new Image(photo);
            String encodedImage = image.convertimagebase64(photo);
            //Toast.makeText(getApplicationContext(), encodedImage, Toast.LENGTH_SHORT).show();

            imageView.setImageBitmap(photo);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();
        System.out.println("location changed");
        latitude = location.getLatitude();
        longitude = location.getLongitude();
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

    public void onStartAddLocation(View view) {
        //Code to save the information into the database
<<<<<<< HEAD
        String locName= ((EditText)findViewById(R.id.locName)).getText().toString();
        String locationDes= ((EditText)findViewById(R.id.locDes)).getText().toString();

        if ((locName.matches(""))||locationDes.matches("")) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double time = System.currentTimeMillis()/1000;
        loca=new TourLocation(locName,locationDes,image,latitude,longitude,time);
        tour.addLocation(loca);
=======
        String locName = ((EditText) findViewById(R.id.locName)).getText().toString();
        String locationDes = ((EditText) findViewById(R.id.locDes)).getText().toString();
        double time = System.currentTimeMillis() / 1000;
        loca = new TourLocation(locName, locationDes, image, latitude, longitude, time);
>>>>>>> 246cd1b2eb75a248351441b40ae72a64a8c112d1
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
