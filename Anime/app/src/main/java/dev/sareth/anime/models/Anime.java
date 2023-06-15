package dev.sareth.anime.models;

import java.io.Serializable;

public class Anime implements Serializable {

    private int id;
    private String name;
    private  String description;
    private String image;
    private boolean isFavorite;

    public Anime(String name, String description, String image, boolean isFavorite) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.isFavorite = isFavorite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void isFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
