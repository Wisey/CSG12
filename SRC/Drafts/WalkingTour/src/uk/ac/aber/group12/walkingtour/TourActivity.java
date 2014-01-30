
package uk.ac.aber.group12.walkingtour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import uk.ac.aber.group12.walkingtour.data.Post;
import uk.ac.aber.group12.walkingtour.data.Tour;

public class TourActivity extends Activity {
    private static boolean DEBUG = true;

    // private TourCreatorActivity TCA;
    private Tour tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        tour = ((WalkingTourApplication) this.getApplication()).getCurrentTour();
        Toast.makeText(getApplicationContext(), tour.getName(), Toast.LENGTH_SHORT).show();
        ((TextView) findViewById(R.id.textView)).setText(tour.getName());
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

}
