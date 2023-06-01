package dev.sareth.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import dev.sareth.contacts.controllers.ContactController;
import dev.sareth.contacts.models.Contact;

public class ContactActivity extends AppCompatActivity{

    private EditText tvNames, tvPhone, tvImage;
    private ImageView contactProfile;
    private Button btnDelete, btnUpdate;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        this.tvNames = this.findViewById(R.id.tvNames);
        this.tvPhone = this.findViewById(R.id.tvPhone);
        this.tvImage = this.findViewById(R.id.tvImageUrl);
        this.btnDelete = this.findViewById(R.id.btnDelete);
        this.btnUpdate = this.findViewById(R.id.btnUpdate);
        this.contactProfile = this.findViewById(R.id.imageProfile);

        Intent intent = this.getIntent();
        contact = (Contact) intent.getSerializableExtra("contact");
        new ContactController(this);

        // Set data
        this.tvNames.setText(contact.getNames());
        this.tvPhone.setText(contact.getPhoneNumber());
        this.tvImage.setText(contact.getImage());
        Picasso.get().load(contact.getImage()).into(this.contactProfile);
    }

    public TextView getTvNames() {
        return tvNames;
    }

    public void setTvNames(EditText tvNames) {
        this.tvNames = tvNames;
    }

    public TextView getTvPhone() {
        return tvPhone;
    }

    public void setTvPhone(EditText tvPhone) {
        this.tvPhone = tvPhone;
    }

    public TextView getTvImage() {
        return tvImage;
    }

    public void setTvImage(EditText tvImage) {
        this.tvImage = tvImage;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    public Button getBtnUpdate() {
        return btnUpdate;
    }

    public void setBtnUpdate(Button btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}