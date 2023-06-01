package dev.sareth.contacts;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.sareth.contacts.controllers.MainController;
import dev.sareth.contacts.controllers.iContactsCallback;
import dev.sareth.contacts.models.APIContact;
import dev.sareth.contacts.models.Contact;
import dev.sareth.contacts.services.ContactService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements iContactsCallback {

    private ContactAdapter ma;
    private Button btnAdd;
    private Button btnRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting contacts from API
        fetchContacts();

        // Reference to recycler view
        RecyclerView rv = findViewById(R.id.rvContact);
        rv.setLayoutManager(new LinearLayoutManager(this));
        ma = new ContactAdapter(this, new ArrayList<>());
        rv.setAdapter(ma);

        // Initiate components
        this.btnAdd = this.findViewById(R.id.btnAdd);
        this.btnRefresh = this.findViewById(R.id.btnRefresh);
        this.btnRefresh.setOnClickListener(view -> fetchContacts());


        new MainController(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        fetchContacts();
    }

    private void fetchContacts(){
        loadContacts(this);
    }

    public void loadContacts(iContactsCallback callback){

        ContactService contactService = APIContact.RETROFIT.create(ContactService.class);
        Call<List<Contact>> call = contactService.getAllContact();

        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if (response.isSuccessful()) {
                    List<Contact> contacts = response.body();
                    callback.onContactsLoaded(contacts);
                } else {
                    Toast.makeText(getApplicationContext(), "Contacts can't be loaded", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                callback.onContactsLoadFailed();
            }
        });
    }

    @Override
    public void onContactsLoaded(List<Contact> contacts) {
        this.ma.setItems(contacts);
        this.ma.notifyDataSetChanged();
    }

    @Override
    public void onContactsLoadFailed() {
        this.ma.setItems(new ArrayList<>());
        Toast.makeText(getApplicationContext(), "Contacts can't be loaded", Toast.LENGTH_SHORT).show();
    }


    public Button getBtnAdd() {
        return btnAdd;
    }
}