package uk.ac.aber.group12.walkingtour;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends Activity implements LocationListener {
	private LocationManager locationManager;
	private String provider;
	private double latitude = 0;
	private double longitude = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			onLocationChanged(location);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public void onCoordinateClick(View view) {
		String Text = "Latitude = " + latitude + " Longitude = " + longitude;
		Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_SHORT)
				.show();
	}

	public void getCoordinates() {

	}

	@Override
	public void onLocationChanged(Location location) {
		location.getLatitude();
		location.getLongitude();
		System.out.println("location changed");
		latitude = location.getLatitude();
		longitude = location.getLongitude();
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
}
