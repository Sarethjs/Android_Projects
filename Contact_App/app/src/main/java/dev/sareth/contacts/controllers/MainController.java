package dev.sareth.contacts.controllers;

import android.content.Intent;
import dev.sareth.contacts.AddContact;
import dev.sareth.contacts.MainActivity;


public class MainController{

    private final MainActivity mainActivity;

    public MainController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.setOnClickEvents();
    }

    private void setOnClickEvents(){
        this.mainActivity.getBtnAdd().setOnClickListener(view -> addContact());
        // this.mainActivity.getBtnRefresh().setOnClickListener(view -> loadContacts());
    }

    private void addContact() {
        Intent intent = new Intent(this.mainActivity, AddContact.class);
        this.mainActivity.startActivity(intent);
    }
}
