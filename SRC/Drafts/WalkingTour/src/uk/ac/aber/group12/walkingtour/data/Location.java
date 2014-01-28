package uk.ac.aber.group12.walkingtour.data;

public class Location {
    private String name;
    private String image;
    private double latitude;
    private double longitude;
    private double time;

    public Location(String name, String image, double latitude, double longitude, double time) {
        this.name = name;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }
}
