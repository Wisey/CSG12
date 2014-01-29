package uk.ac.aber.group12.walkingtour.data;


public class TourLocation {
    private static String LOCATION_JSON = "    {\n"
            + "      \"name\": \"%s\",\n"
            + "      \"description\": \"%s\",\n"
            + "      \"latitude\": %f,\n"
            + "      \"longitude\": %f,\n"
            + "      \"time\": %f,\n"
            + "      \"image\": \"%s\"\n"
            + "    }";

    private String name;
    private String description;
    private String imageFilePath;
    private double latitude;
    private double longitude;
    private double time;

    public TourLocation(String name, String description, String imageFilePath, double latitude, double longitude, double time) {
        this.name = name;
        this.description = description;
        this.imageFilePath = imageFilePath;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public String toJSON() {
        return String.format(LOCATION_JSON, name, description, latitude, longitude, time, image.toString());
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
}
