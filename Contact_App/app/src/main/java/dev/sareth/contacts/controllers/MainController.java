package dev.sareth.contacts.controllers;

import android.widget.TextView;

import dev.sareth.contacts.MainActivity;
import dev.sareth.contacts.R;
import dev.sareth.contacts.models.Contact;

public class MainController {

    private MainActivity mainActivity;

    public MainController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.setOnClickEvents();
    }

    private void setOnClickEvents(){
        this.mainActivity.findViewById(R.id.btnSave).setOnClickListener(view -> saveClick());
    }

    private void saveClick(){
        TextView tvNames = this.mainActivity.findViewById(R.id.etNames);
        TextView tvPhone = this.mainActivity.findViewById(R.id.etPhoneNumber);

        this.mainActivity.addContact(new Contact(tvNames.getText().toString(), tvPhone.getText().toString()));
    }

}
