package dev.sareth.contacts.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Contact implements Serializable {

    private int id;
    private String names;
    private String phoneNumber;
    private String image;

    public Contact(int id, String names, String phoneNumber, String image) {
        this.id = id;
        this.names = names;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public Contact(String names, String phoneNumber, String image) {
        this.names = names;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public Contact(String names, String phoneNumber) {
        this.names = names;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
