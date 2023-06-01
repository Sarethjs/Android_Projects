package dev.sareth.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import dev.sareth.contacts.controllers.AddContactController;

public class AddContact extends AppCompatActivity {

    private EditText etNames, etPhone, etImage;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        // Initiate components
        this.etNames = this.findViewById(R.id.etNames);
        this.etPhone = this.findViewById(R.id.etPhone);
        this.etImage = this.findViewById(R.id.etImageUrl);
        this.btnSave = this.findViewById(R.id.btnSave);

        // Set controller
        new AddContactController(this);
    }

    public EditText getEtNames() {
        return etNames;
    }

    public EditText getEtPhone() {
        return etPhone;
    }

    public EditText getEtImage() {
        return etImage;
    }

    public Button getBtnSave() {
        return btnSave;
    }
}