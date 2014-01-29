package uk.ac.aber.group12.walkingtour;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
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

import uk.ac.aber.group12.walkingtour.data.Image;
import uk.ac.aber.group12.walkingtour.data.Tour;

public class TourCreatorActivity extends Activity {


    private Image image;
    private TextView textView;
    private ImageView imageView;
    private HomeActivity homeactivity;
    //private Tour tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_creator);
        setupActionBar();


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

    public void onStartTour(View view) {
        String name = ((EditText) findViewById(R.id.tourName)).getText().toString();
        String shortDes = ((EditText) findViewById(R.id.shortDes)).getText().toString();
        String longDes = ((EditText) findViewById(R.id.longDes)).getText().toString();


        if ((name.matches("")) || shortDes.matches("") || longDes.matches("")) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        Tour tour = new Tour(name, shortDes, longDes);
        ((WalkingTourApplication) this.getApplication()).setCurrentTour(tour);
        Intent intent = new Intent(this, TourActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


}
