
package uk.ac.aber.group12.walkingtour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import uk.ac.aber.group12.walkingtour.data.Image;
import uk.ac.aber.group12.walkingtour.data.Post;
import uk.ac.aber.group12.walkingtour.data.Tour;
import uk.ac.aber.group12.walkingtour.data.TourLocation;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onStartTourCreator(View view) {
        Intent intent = new Intent(this, TourCreatorActivity.class);
        startActivity(intent);
    }

    public void onStartUpload(View view) {
        Tour tour = new Tour("Nice", "A nice tour", "This is a very nice tour. Something something long description");
        tour.addLocation(new TourLocation("road", "first test location", "", 0.3, 23.1, 312312313.122));
        tour.addLocation(new TourLocation("house", "second location", "", 0.3, 23.1, 312312313.122));

        Toast.makeText(getApplicationContext(), String.valueOf(tour.getLocations().get(5).toJSON()), Toast.LENGTH_SHORT).show();
        Post post = new Post("http://nyaa.kragniz.eu:443/~group/upload.php", tour.toJSON());
        post.sendAsync();
    }


    public void onStartQuit(View view) {
        finish();
        // Intent intent = new Intent(this, test.class);
        //  startActivity(intent);
    }
}
