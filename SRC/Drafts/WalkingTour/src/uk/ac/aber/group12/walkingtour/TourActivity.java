
package uk.ac.aber.group12.walkingtour;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import uk.ac.aber.group12.walkingtour.data.Tour;

public class TourActivity extends Activity {

   // private TourCreatorActivity TCA;
    private Tour tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        Intent i = getIntent();
        Tour tour=(Tour)i.getSerializableExtra("tour");
        ((TextView)findViewById(R.id.textView)).setText(tour.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public void onStartLocationCreator(View view) {
        Intent intent = new Intent(this, LocationCreatorActivity.class);
        intent.putExtra("tour",tour);
        startActivity(intent);
    }

    public void onStartDeleteTour(View view) {
        finish();
    }

    public void onUpload(View view) {
        Intent intent = new Intent(this, UploaderActivity.class);
        startActivity(intent);
    }

}
