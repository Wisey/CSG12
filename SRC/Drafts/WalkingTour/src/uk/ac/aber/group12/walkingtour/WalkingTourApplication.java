package uk.ac.aber.group12.walkingtour;

import android.app.Application;

import java.util.ArrayList;

import uk.ac.aber.group12.walkingtour.data.Tour;

/**
 * Created by lot15 on 29/01/2014.
 */

public class WalkingTourApplication extends Application {
    private static Tour tour;
    public ArrayList<Integer> asyncDone = new ArrayList<Integer>();

    public Tour getCurrentTour() {
        return tour;
    }

    public void setCurrentTour(Tour tour) {
        WalkingTourApplication.tour = tour;
    }
}