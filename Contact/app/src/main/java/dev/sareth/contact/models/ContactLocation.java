package dev.sareth.contact.models;

public class ContactLocation {

    private double latitude;
    private double longitude;

    public ContactLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "ContactLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
