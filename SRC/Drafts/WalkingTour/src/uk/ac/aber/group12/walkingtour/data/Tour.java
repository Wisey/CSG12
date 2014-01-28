package uk.ac.aber.group12.walkingtour.data;

import java.util.ArrayList;

public class Tour {
    private String name;
    private String shortDescription;
    private String longDescription;
    private ArrayList<Location> locations;

    public Tour(){

    }

    public Tour(String name, String shortDescription, String longDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }
}
