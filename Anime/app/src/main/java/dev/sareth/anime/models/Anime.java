package dev.sareth.anime.models;

public class Anime {

    private String image;
    private String name;
    private  String description;
    private boolean isFavorite;

    public Anime(String image, String name, String description, boolean isFavorite) {
        this.image = image;
        this.name = name;
        this.description = description;
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
}
