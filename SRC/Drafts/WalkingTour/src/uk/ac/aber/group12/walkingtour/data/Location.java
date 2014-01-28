package uk.ac.aber.group12.walkingtour.data;

public class Location {
    private String name;
    private String locationDes;
    private Image image;
    private double latitude;
    private double longitude;
    private double time;

    public Location(String name,String locationDes ,Image image, double latitude, double longitude, double time) {
        this.name = name;
        this.locationDes=locationDes;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getLocationDes() {
        return locationDes;
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
