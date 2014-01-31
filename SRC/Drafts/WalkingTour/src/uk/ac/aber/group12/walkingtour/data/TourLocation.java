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
    private String image;
    private String base64Bitmap;
    private double latitude;
    private double longitude;
    private double time;

    /**
     *
     * This is the constructor for a location in a Tour.
     *
     *
     *
     * @param name Name of location.
     * @param description A description of the place. E.g. ("A very nice place to relax")
     * @param imagePath The filepath of the image associated with the tour.
     * @param base64Bitmap this is the converted image associated with the location.
     * @param latitude The latitude coordinate
     * @param longitude The longitude coordinate
     * @param time The time that the location was created.
     */
    public TourLocation(String name, String description, String imagePath, String base64Bitmap, double latitude, double longitude, double time) {
        this.name = name;
        this.description = description;
        this.image = imagePath;
        this.base64Bitmap = base64Bitmap;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    /**
     *
     * This method converts the location into a JSON string based on the Schema we are using.
     *
     *
     * @return a JSON string of a converted Tour.
     */
    public String toJSON() {
        return String.format(LOCATION_JSON, JSON.quote(name), JSON.quote(description), latitude, longitude, time, JSON.quote(base64Bitmap));
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
