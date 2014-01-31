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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import uk.ac.aber.group12.walkingtour.data.Tour;
import uk.ac.aber.group12.walkingtour.data.TourLocation;

public class ViewWalksActivity extends Activity {



    public int editLoc;

    ListView listView;
    private Tour tour =new Tour(null,null,null);

    private ArrayList<TourLocation>locations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_walks);
        setupActionBar();
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        tour = ((WalkingTourApplication) this.getApplication()).getCurrentTour();
        locations = tour.getLocations();

       String[] values=new String[locations.size()];

       for (int i=0;i<locations.size();i++){
            values[i]=locations.get(i).getName();

        }

/*
        // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };*/


        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //public int editLoc;

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //startActivity(new Intent(this, EditLocationActivity.class).putExtra("editLocation",editLoc));


                // ListView Clicked item index
                int itemPosition     = position;

                 editLoc=position;

                Intent intent;
                intent = new Intent(ViewWalksActivity.this, EditLocationActivity.class);
                intent.putExtra("editLocation", editLoc);
                startActivity(intent);

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();


            }

        });
    }
/*
    public void onStartEditLocation (View view){
        Intent intent;
        intent = new Intent(this, EditLocationActivity.class);
        intent.putExtra("editLocation", editLoc);
        startActivity(intent);
    }*/

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
        getMenuInflater().inflate(R.menu.view_walks, menu);
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

}
