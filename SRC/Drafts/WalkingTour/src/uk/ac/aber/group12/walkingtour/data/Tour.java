package uk.ac.aber.group12.walkingtour.data;

import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Tour implements Serializable {
    private static String TOUR_JSON = "{\n"
        + "  \"name\": %s,\n"
        + "  \"short-description\": %s,\n"
        + "  \"long-description\": %s,\n"
        + "  \"locations\": [\n"
        + "%s"
        + "  ]\n"
        + "}";

    private String name;
    private String shortDescription;
    private String longDescription;
    private ArrayList<TourLocation> locations;

    /**
     *
     * Constructor for a single Tour.
     *
     * @param name The Name of the Tour
     * @param shortDescription a short description, describing the route.
     * @param longDescription a more detailed description of the Tour and the route.
     */
    public Tour(String name, String shortDescription, String longDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        locations = new ArrayList<TourLocation>();
    }

    /**
     *
     * This method adds a location to the Tour.
     *
     * @param location the location to be added.
     */
    public void addLocation(TourLocation location) {
        locations.add(location);
    }

    /**
     *
     * This method converts the Tour into a JSON string based on the Schema we are using.
     *
     *
     * @return a JSON string of a converted Tour.
     */
    public String toJSON() {
        StringBuffer buf = new StringBuffer();

        for(int i=0; i < locations.size(); i++) {
            buf.append(locations.get(i).toJSON());
            if (i < locations.size()-1) {
                buf.append(",\n");
            } else {
                buf.append("\n");
            }
        }

        return String.format(TOUR_JSON, JSON.quote(name), JSON.quote(shortDescription), JSON.quote(longDescription), buf.toString());
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

    public ArrayList<TourLocation> getLocations() {
        return locations;
    }
}