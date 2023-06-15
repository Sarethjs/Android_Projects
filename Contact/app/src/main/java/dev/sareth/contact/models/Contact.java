package dev.sareth.contact.models;

import java.io.Serializable;
import java.util.List;


public class Contact implements Serializable {

    private int id;
    private String name;
    private String phoneNumber;
    private String imageUrl;
    private ContactLocation contactLocation;

    private List<Comment> comments;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getId(){
        return  this.id;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setContactLocation(ContactLocation contactLocation) {
        this.contactLocation = contactLocation;
    }

    public ContactLocation getContactLocation() {
        return contactLocation;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
