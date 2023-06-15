package dev.sareth.contact.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ContactLocation implements Serializable {

    private final double latitude;
    private final double longitude;

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

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
