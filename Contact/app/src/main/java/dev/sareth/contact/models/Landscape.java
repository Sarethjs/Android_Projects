package dev.sareth.contact.models;

import androidx.annotation.NonNull;

import java.io.Serializable;


public class Landscape implements Serializable {

    private int id;
    private final String name;
    private String imageUrl;
    private BoxLocation boxLocation;


    public Landscape(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public int getId(){
        return  this.id;
    }

    public void setContactLocation(BoxLocation boxLocation) {
        this.boxLocation = boxLocation;
    }

    public BoxLocation getContactLocation() {
        return boxLocation;
    }

    @NonNull
    @Override
    public String toString() {
        return "Landscape{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
