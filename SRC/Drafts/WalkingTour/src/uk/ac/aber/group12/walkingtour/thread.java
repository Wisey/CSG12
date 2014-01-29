package uk.ac.aber.group12.walkingtour;


import android.app.Activity;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dpb1 on 28/01/2014.
 */


    public class thread extends Activity implements LocationListener {

        private LocationManager locationManager;
        private String provider;
        private double latitude = 0;
        private double longitude = 0;
        private Image image;
        private TextView textView;
        private static final int CAMERA_REQUEST = 1888;
        private ImageView imageView;
        private String test;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tour_creator);
            setupActionBar();

            // location stuff
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria, false);


            new Thread(new Runnable() {
                public void run() {

                    while (true) {
                        try {
                            Location location = locationManager.getLastKnownLocation(provider);
                            if (location != null) {
                                onLocationChanged(location);
                            }

                            Thread.sleep(20000);
                        } catch (InterruptedException e) {

                            Toast.makeText(getApplicationContext(), "The Thread was halted!", Toast.LENGTH_SHORT);
                        }




                                }

                                 }
                                       }).start();


        }


        public void onCoordinateClick(View view) {
            String Text = "Latitude = " + latitude + " Longitude = " + longitude;
            Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
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


