package dev.sareth.contacts.models;

public class Contact {

    private String names;
    private String phoneNumber;
    private int image;

    public Contact(String names, String phoneNumber, int image) {
        this.names = names;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public Contact(String names, String phoneNumber) {
        this.names = names;
        this.phoneNumber = phoneNumber;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
