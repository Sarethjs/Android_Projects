package dev.sareth.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import dev.sareth.contacts.controllers.MainController;
import dev.sareth.contacts.models.Contact;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contacts;
    private RecyclerView rv;

    private MyAdapter ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference to recycler view
        rv = findViewById(R.id.rvContact);
        contacts = new ArrayList<>();

        //for (int i = 0; i < 15; i++) contacts.add(new Contact("Jean Smith " + i, "" + i + 1, i));

        rv.setLayoutManager(new LinearLayoutManager(this));
        ma = new MyAdapter(getApplicationContext(), contacts);
        rv.setAdapter(ma);

        // Set controllers
        new MainController(this);

    }

    public void addContact(Contact c){
        this.contacts.add(c);
        ma.notifyDataSetChanged();
    }

}