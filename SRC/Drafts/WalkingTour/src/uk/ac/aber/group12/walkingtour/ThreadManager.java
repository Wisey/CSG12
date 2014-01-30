package uk.ac.aber.group12.walkingtour;



import android.location.Location;
import android.os.Looper;
import android.widget.Toast;
import android.app.Activity;
import android.widget.TextView;

import java.lang.Thread;


/**
 * Created by dpb1 on 28/01/2014.
 */


public class ThreadManager{



    ThreadManager(Activity t, Location l) {
        final TourActivity ta = (TourActivity) t;
        final Location loc = l;

        Thread thr = new Thread(new Runnable(){

            @Override
            public void run() {
                Looper.prepare();
                TextView t = ((TextView) ta.findViewById(R.id.textView));
                while (true) {

                    try {
                       ta.onLocationChanged(loc);
                       // System.out.println("This is one");
                        Thread.sleep(8000);
                    //   System.out.println("This is two");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thr.start();
    }




    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */






}