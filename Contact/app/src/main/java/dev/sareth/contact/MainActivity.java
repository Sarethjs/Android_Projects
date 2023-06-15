package dev.sareth.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dev.sareth.contact.adpters.holders.LandscapeAdapter;
import dev.sareth.contact.listeners.CallbackListener;
import dev.sareth.contact.models.Landscape;
import dev.sareth.contact.services.LandscapeService;

public class MainActivity extends AppCompatActivity implements CallbackListener.items{

    private LandscapeAdapter landscapeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViewById(R.id.btnCreate).setOnClickListener(view-> {
            this.startActivity(new Intent(this, CreateLandscape.class));
        });

        this.landscapeAdapter = new LandscapeAdapter(new ArrayList<>(), this);

        RecyclerView rc = this.findViewById(R.id.rvContacts);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(this.landscapeAdapter);

        // Fetching contacts
        this.fetchContacts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchContacts();
    }

    private void fetchContacts(){
        LandscapeService.findAll(this);
    }



    @Override
    public void itemsReceived(List<Landscape> items) {
        Log.d("fetch_contacts", "itemsReceived: " + items);
        Toast.makeText(this, "Items loaded: " + items.size(), Toast.LENGTH_SHORT).show();
        this.landscapeAdapter.setItems(items);
    }

    @Override
    public void itemsNotReceived(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}