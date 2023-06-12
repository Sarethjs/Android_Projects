package dev.sareth.contact.services;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dev.sareth.contact.models.Contact;
import dev.sareth.contact.listeners.CallbackListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactService{

    private static final iContacService service =
            iContacService.RETROFIT.create(iContacService.class);

    public static void create(Contact contact, CallbackListener.item listener){

        service.create(contact).enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(@NonNull Call<Contact> call,
                                   @NonNull Response<Contact> response) {
                if (response.isSuccessful()){
                    listener.itemNotReceived("Contact saved");
                } else{
                    listener.itemNotReceived("Contact not saved");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Contact> call,
                                  @NonNull Throwable t){
                listener.itemNotReceived("Contact not saved: Unknown error");
            }
        });
    }

    public static void findAll(CallbackListener.items listener){
        service.getAllContacts().enqueue(new Callback<List<Contact>>() {
            List<Contact> contacts = new ArrayList<>();
            @Override
            public void onResponse(@NonNull Call<List<Contact>> call,
                                   @NonNull Response<List<Contact>> response) {
                if (response.isSuccessful()) {
                    contacts = response.body();
                    listener.itemsReceived(contacts);
                } else listener.itemsNotReceived("Items not loaded");
            }

            @Override
            public void onFailure(@NonNull Call<List<Contact>> call,
                                  @NonNull Throwable t) {
                listener.itemsNotReceived("Items not loaded: " + t);
            }
        });
    }

}
