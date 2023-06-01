package dev.sareth.contacts.controllers;

import android.widget.Toast;

import dev.sareth.contacts.ContactActivity;
import dev.sareth.contacts.models.APIContact;
import dev.sareth.contacts.models.Contact;
import dev.sareth.contacts.services.ContactService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactController {

    private final ContactActivity contactActivity;
    public ContactController(ContactActivity contact) {
        this.contactActivity = contact;
        setClickListeners();
    }

    private void setClickListeners(){
        this.contactActivity.getBtnDelete().setOnClickListener(view -> deleteContact());
        this.contactActivity.getBtnUpdate().setOnClickListener(view -> updateContact());
    }

    private void updateContact(){

        ContactService contactService = APIContact.RETROFIT.create(ContactService.class);
        final  Contact c = this.contactActivity.getContact();

        // Get changes
        String names = this.contactActivity.getTvNames().getText().toString();
        String phone = this.contactActivity.getTvPhone().getText().toString();
        String imageUrl = this.contactActivity.getTvImage().getText().toString();

        c.setNames(names);
        c.setPhoneNumber(phone);
        c.setImage(imageUrl);

        Call<Contact> call = contactService.update(c.getId(), c);

        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Toast.makeText(contactActivity.getApplicationContext(), "Contact updated from API", Toast.LENGTH_SHORT).show();
                contactActivity.finish();
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                Toast.makeText(contactActivity, "Contact can't be updated", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteContact(){

        ContactService contactService = APIContact.RETROFIT.create(ContactService.class);
        final  Contact c = this.contactActivity.getContact();
        Call<Void> call = contactService.delete(c.getId());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(contactActivity.getApplicationContext(), "Contact removed from API", Toast.LENGTH_SHORT).show();
                contactActivity.finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(contactActivity, "Contact can't be removed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
