
package uk.ac.aber.group12.walkingtour;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import uk.ac.aber.group12.walkingtour.data.Post;
import uk.ac.aber.group12.walkingtour.data.Tour;

public class TourActivity extends Activity implements LocationListener {
    private static boolean DEBUG = true;

    // private TourCreatorActivity TCA;
    private int i = 1;
    private Location[] waypoints = new Location[i];
    private LocationManager locationManager;
    private String provider;
    private double latitude = 0;
    private double longitude = 0;
    private Tour tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        tour = ((WalkingTourApplication) this.getApplication()).getCurrentTour();
        Toast.makeText(getApplicationContext(), tour.getName(), Toast.LENGTH_SHORT).show();
        ((TextView) findViewById(R.id.textView)).setText(tour.getName());

        // location stuff
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Log.e("sometjong", provider.toString());

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Looper.prepare();
                while (true) {
                    /*Location location = getLocation();
                    Log.e("location", location.toString());
                    if (location != null) {
                        waypoints[i] = location;
                        setArrayLength();
                        System.out.println(i);
                        i++;
                    } else {
                        System.out.println("null");

                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    */
                }
            }
        });
        thread.start();
    }

    public Location getLocation() {
        Location location = null;
        boolean canGetLocation;
        try {
            locationManager = (LocationManager) this.getApplicationContext().getSystemService(LOCATION_SERVICE);

            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    1000, this);
            Log.d("GPS", "GPS Enabled");
            if (locationManager != null) {
                location = locationManager
                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("GPS", e.toString());
        }
        return location;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public void onStartLocationCreator(View view) {
        Intent intent = new Intent(this, LocationCreatorActivity.class);
        ((WalkingTourApplication) this.getApplication()).setCurrentTour(tour);
        startActivity(intent);
    }

    public void onStartDeleteTour(View view) {
        finish();
    }

    public void onStartEditLocations(View view) {
        Intent intent = new Intent(this, ViewWalksActivity.class);
        startActivity(intent);
    }

    public void onUpload(View view) {
        Tour tour = ((WalkingTourApplication) this.getApplication()).getCurrentTour();
        //Post post = new Post("http://nyaa.kragniz.eu:443/~group/upload.php", tour.toJSON());
        String url;
        if (DEBUG) {
            url = "http://nyaa.kragniz.eu";
        } else {
            url = "http://nyaa.kragniz.eu:443/~group/upload.php";
        }
        Toast.makeText(getApplicationContext(), tour.toJSON().concat(" to ").concat(url), Toast.LENGTH_SHORT).show();
        Post post = new Post(url, tour.toJSON());
        post.sendAsync();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e("LOCATION_CHANGED", "yes");
        Toast.makeText(getApplicationContext(),"Waypoints saved: "+waypoints.length,Toast.LENGTH_SHORT).show();
        location.getLatitude();
        location.getLongitude();
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        setArrayLength();
        waypoints[waypoints.length - 1] = location;
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

    private void setArrayLength() {

        Location[] temp = waypoints.clone();
        waypoints = new Location[temp.length + 1];

        for (int integ = 0; integ < temp.length; integ++) {
            waypoints[integ] = temp[integ];

        }
    }

    public void UIMethod1() {
        TextView t = ((TextView) findViewById(R.id.textView));
        t.setText("This is 1");
    }

    public void UIMethod2() {
        TextView t = ((TextView) findViewById(R.id.textView));
        t.setText("This is 2");
    }
}