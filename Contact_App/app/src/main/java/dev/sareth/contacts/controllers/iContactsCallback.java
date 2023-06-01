package dev.sareth.contacts.controllers;

import java.util.List;

import dev.sareth.contacts.models.Contact;

public interface iContactsCallback {
    void onContactsLoaded(List<Contact> contacts);
    void onContactsLoadFailed();
}
