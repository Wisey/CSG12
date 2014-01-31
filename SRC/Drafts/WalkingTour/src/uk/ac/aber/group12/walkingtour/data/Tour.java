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
        + "  ],\n"
        + "  \"waypoint_long\": [\n"
        + "%s"
        + "  ],\n"
        + "  \"waypoint_lat\": [\n"
        + "%s"
        + "  ]\n"
        + "}";

    private String name;
    private String shortDescription;
    private String longDescription;
    private ArrayList<TourLocation> locations;
    private ArrayList<Double> latitudes;
    private ArrayList<Double> longitudes;


    /**
     * The constructor for a Tour.
     * It has a name, a short Description and a Long Description.
     *
     * @param name The name of the Tour.
     * @param shortDescription A short description that describes the route taken.
     * @param longDescription A description that can be used to describe the route taken in more detail.
     */
    public Tour(String name, String shortDescription, String longDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        locations = new ArrayList<TourLocation>();
        latitudes = new ArrayList<Double>();
        longitudes = new ArrayList<Double>();
    }
    
    public void addLocation(TourLocation location) {
        locations.add(location);
    }

    public void addWaypoint(double lat, double _long) {
        latitudes.add(lat);
        longitudes.add(_long);
    }

    /**
     *Attempts to convert a Tour into a Json string, so that it may be sent to the database via HTTP Post.
     *
     * @return a Json String that holds the information of a single tour.
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

        StringBuffer latBuf = new StringBuffer();

        for(int i=0; i < latitudes.size(); i++) {
            latBuf.append("    ");
            latBuf.append(latitudes.get(i).toString());
            if (i < latitudes.size()-1) {
                latBuf.append(",\n");
            } else {
                latBuf.append("\n");
            }
        }

        StringBuffer longBuf = new StringBuffer();

        for(int i=0; i < longitudes.size(); i++) {
            longBuf.append("    ");
            longBuf.append(longitudes.get(i).toString());
            if (i < longitudes.size()-1) {
                longBuf.append(",\n");
            } else {
                longBuf.append("\n");
            }
        }

        return String.format(TOUR_JSON, JSON.quote(name), JSON.quote(shortDescription), JSON.quote(longDescription), buf.toString(), latBuf.toString(), longBuf.toString());
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
