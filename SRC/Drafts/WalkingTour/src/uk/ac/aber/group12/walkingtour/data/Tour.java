package uk.ac.aber.group12.walkingtour.data;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Tour implements Serializable{
    private static String TOUR_JSON = "{\n"
        + "  \"name\": \"%s\",\n"
        + "  \"short-description\": %s,\n"
        + "  \"long-description\": %s,\n"
        + "  \"locations\": [\n"
        + "    %s"
        + "  ]\n"
        + "}";

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
