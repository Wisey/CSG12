package uk.ac.aber.group12.walkingtour.data;


public class TourLocation {
    private static String LOCATION_JSON = "{\"name\": \"%s\",\n"
            + "\"latitude\": %d,\n"
            + "\"longitude\": %d,\n"
            + "\"time\": %d\n,"
            + "\"image\": \"%s\"\n}";

    private String name;
    private String locationDescription;
    private Image image;
    private double latitude;
    private double longitude;
    private double time;

    public TourLocation(String name, String locationDescription, Image image, double latitude, double longitude, double time) {
        this.name = name;
        this.locationDescription = locationDescription;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public String toJSON() {
        return null;
    }

    public String getName() {
        return name;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public Image getImage() {
        return image;
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
}