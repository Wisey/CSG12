package uk.ac.aber.group12.walkingtour.data;

import java.util.ArrayList;

public class Tour {
    private String name;
    private String shortDescription;
    private String longDescription;
    private ArrayList<TourLocation> locations;

    public Tour(String name, String shortDescription, String longDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public void addLocation(TourLocation location) {
        locations.add(location);
    }
}
