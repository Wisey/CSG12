
package uk.ac.aber.group12.walkingtour;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import uk.ac.aber.group12.walkingtour.data.Post;
import uk.ac.aber.group12.walkingtour.data.Tour;

public class TourActivity extends Activity  implements LocationListener {

    // private TourCreatorActivity TCA;
    private Location[] waypoints;
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


        new java.lang.Thread(new Runnable() {

            public void run() {
                Looper.prepare();
                Location location = locationManager.getLastKnownLocation(provider);
                while (true) {
                    Toast.makeText(getApplicationContext(), "Things are happening", Toast.LENGTH_SHORT).show();
                    try
                    {
                        if (location != null) {
                            onLocationChanged(location);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Things are struggling", Toast.LENGTH_SHORT).show();
                        }
                        java.lang.Thread.sleep(2000);
                    }
                    catch (Exception e)
                    {

                    }
                }
            }
        }).start();
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

    public void onUpload(View view) {
        Tour tour = ((WalkingTourApplication) this.getApplication()).getCurrentTour();
        Toast.makeText(getApplicationContext(), String.valueOf(tour.getLocations().get(0).toJSON()), Toast.LENGTH_SHORT).show();
        Post post = new Post("http://nyaa.kragniz.eu:443/~group/upload.php", tour.toJSON());
        post.sendAsync();
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getApplicationContext(),"Waypoints saved: "+waypoints.length,Toast.LENGTH_SHORT).show();
        location.getLatitude();
        location.getLongitude();
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        setArrayLength();
        waypoints[waypoints.length-1] = location;
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

    private void setArrayLength()
    {

        Location[] temp = waypoints.clone();
        waypoints = new Location[temp.length+1];

        for(int integ =0; integ < temp.length; integ++)
        {
            waypoints[integ] = temp[integ];

        }
    }

}
