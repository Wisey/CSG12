package uk.ac.aber.group12.walkingtour.data;


public class TourLocation {
    private static boolean UPLOAD_IMAGES = true;

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
     * @param name The name of a location. (E.g. "The Pug's tail")
     * @param description The Descripion of a location. (E.g. "The local watering whole for the crimnal puppy masterminds.")
     * @param imagePath The image filepath of a picture taken at the location. (E.g. " C:\Users\Benson\My Pictures\woof.bark")
     * @param latitude The latitude coordinate of a location.
     * @param longitude The longitude coordinate of a location.
     * @param time The date/Time of when a location was recorded into the phone.
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
     *Attempts to convert a location into a Json string, so that it may be sent to the database via HTTP Post.
     *
     * @return a Json String that holds the information of a single location.
     */
    public String toJSON() {
        String image;
        if (UPLOAD_IMAGES) {
            image = JSON.quote(base64Bitmap);
        } else {
            image = "\"placeholder image\"";
        }
        return String.format(LOCATION_JSON, JSON.quote(name), JSON.quote(description), latitude, longitude, time, image);
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
