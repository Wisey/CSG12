package uk.ac.aber.group12.walkingtour;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onStartTourCreator(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onStartViewTours(View view) {
       //Intent intent = new Intent(this, test.class);
       // startActivity(intent);
    }

    public void onStartSettings(View view) {
      //  Intent intent = new Intent(this,test.class);
      //  startActivity(intent);
    }

    public void onStartLogOut(View view) {
       // Intent intent = new Intent(this, test.class);
      //  startActivity(intent);
    }






}