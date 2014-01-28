
package uk.ac.aber.group12.walkingtour;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Text;

import uk.ac.aber.group12.walkingtour.data.Post;

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
        Toast.makeText(getApplicationContext(), "Starting post", Toast.LENGTH_SHORT).show();
        Post.postJSON("http://nyaa.kragniz.eu", "\"test\": 12");
    }


    public void onStartQuit(View view) {
        finish();
        // Intent intent = new Intent(this, test.class);
        //  startActivity(intent);
    }






}