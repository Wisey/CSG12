/*package uk.ac.aber.group12.walkingtour;

import android.app.Service;
import android.content.Intent;
import android.location.*;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;
import android.os.HandlerThread;

public class GPSService extends Service implements LocationListener  {

    private LocationManager locationManager;
    private String provider;
    private double latitude = 0;
    private double longitude = 0;
    private Location[] waypoints;

    public IBinder onBind(Intent intent) {



        // location stuff
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        if (location != null) {
            onLocationChanged(location);
        }


        return null;
    }


    public void onProviderDisabled(String arg0) {
    }


    public void onProviderEnabled(String arg0) {
    }


    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
    }

    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();
        System.out.println("location changed");
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    public void onCoordinateClick(View view) {
        String Text = "Latitude = " + latitude + " Longitude = " + longitude;
        Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
    }
}*/