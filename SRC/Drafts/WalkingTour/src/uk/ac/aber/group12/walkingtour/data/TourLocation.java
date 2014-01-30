package uk.ac.aber.group12.walkingtour.data;


public class TourLocation {
    private static String LOCATION_JSON = "    {\n"
            + "      \"name\": %s,\n"
            + "      \"description\": %s,\n"
            + "      \"latitude\": %f,\n"
            + "      \"longitude\": %f,\n"
            + "      \"time\": %f,\n"
            + "      \"image\": %s\n"
            + "    }";

    private String name;
    private String description;
    private String imageFilePath;
    private double latitude;
    private double longitude;
    private double time;

    /**
     *
     * This is the constructor for a location in a Tour.
     * Waypoints are considered locations, except that the unnecessary parameters are null.
     *
     *
     * @param name
     * @param description
     * @param imageFilePath
     * @param latitude
     * @param longitude
     * @param time
     */

    public TourLocation(String name, String description, String imageFilePath, double latitude, double longitude, double time) {
        this.name = name;
        this.description = description;
        this.imageFilePath = imageFilePath;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public String toJSON() {
        return String.format(LOCATION_JSON, JSON.quote(name), JSON.quote(description), latitude, longitude, time, JSON.quote(imageFilePath));
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }
}
