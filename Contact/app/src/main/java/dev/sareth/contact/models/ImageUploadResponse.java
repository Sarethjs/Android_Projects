package dev.sareth.contact.models;

import com.google.gson.annotations.SerializedName;

public class ImageUploadResponse {
    @SerializedName("url")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }
}
