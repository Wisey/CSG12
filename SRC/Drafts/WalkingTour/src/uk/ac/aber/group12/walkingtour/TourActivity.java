
package uk.ac.aber.group12.walkingtour;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.util.TimeUtils;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import uk.ac.aber.group12.walkingtour.data.Post;
import uk.ac.aber.group12.walkingtour.data.Tour;

import static java.lang.System.*;

public class TourActivity extends Activity implements LocationListener{
    private static double TIME_BETWEEN_WAYPOINTS = 10000;
    private static boolean DEBUG = false;

    private LocationManager locationManager;
    private String provider;
    private double latitude = 0;
    private double longitude = 0;
    private double time = currentTimeMillis();

    // private TourCreatorActivity TCA;
    private Tour tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        tour = ((WalkingTourApplication) this.getApplication()).getCurrentTour();
        Toast.makeText(getApplicationContext(), tour.getName(), Toast.LENGTH_SHORT).show();
        ((TextView) findViewById(R.id.textView)).setText(tour.getName());

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            onLocationChanged(location);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        if (currentTimeMillis() - time > TIME_BETWEEN_WAYPOINTS) {
            tour.addWaypoint(latitude, longitude);

            String Text = "Latitude = " + latitude + " Longitude = " + longitude;
            Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_LONG).show();
            time = currentTimeMillis();
        }
        Log.e("location", String.valueOf(latitude));
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
        String url;
        if (DEBUG) {
            url = "http://nyaa.kragniz.eu";
        } else {
            url = "http://nyaa.kragniz.eu:443/~group/upload.php";
        }
        //Toast.makeText(getApplicationContext(), tour.toJSON().concat(" to ").concat(url), Toast.LENGTH_SHORT).show();
        Post post = new Post(url, tour.toJSON());
        post.sendAsync();
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
