package dev.sareth.contacts.controllers;

import android.widget.Toast;
import dev.sareth.contacts.AddContact;
import dev.sareth.contacts.models.APIContact;
import dev.sareth.contacts.models.Contact;
import dev.sareth.contacts.services.ContactService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddContactController {

    private final AddContact addContact;

    public AddContactController(AddContact addContact) {
        this.addContact = addContact;
        setListenerClicks();
    }

    private void setListenerClicks(){
        this.addContact.getBtnSave().setOnClickListener(view -> this.saveContact());
    }

    private void saveContact() {

        String names, phoneNumber, imageUrl;
        names = this.addContact.getEtNames().getText().toString();
        phoneNumber = this.addContact.getEtPhone().getText().toString();
        imageUrl = this.addContact.getEtImage().getText().toString();

        final Contact contact = new Contact(names, phoneNumber, imageUrl);

        ContactService contactService = APIContact.RETROFIT.create(ContactService.class);
        Call<Contact> call = contactService.create(contact);

        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Toast.makeText(addContact.getApplicationContext(), "Contact added to API", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent();
                addContact.setResult(addContact.RESULT_OK);
                addContact.finish();
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                Toast.makeText(addContact.getApplicationContext(), "Contact can't be added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
