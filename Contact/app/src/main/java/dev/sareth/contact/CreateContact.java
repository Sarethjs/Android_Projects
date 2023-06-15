package dev.sareth.contact;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.Manifest;
import android.Manifest.permission;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import dev.sareth.contact.helpers.ImageHelper;
import dev.sareth.contact.listeners.CallbackListener;
import dev.sareth.contact.models.Contact;
import dev.sareth.contact.models.ContactLocation;
import dev.sareth.contact.models.SarethImage;
import dev.sareth.contact.models.SarethLocation;
import dev.sareth.contact.services.ContactService;

public class CreateContact extends AppCompatActivity implements CallbackListener.item,
        CallbackListener.location{

    private static final int OPEN_GALLERY_REQUEST = 101;
    private static final int OPEN_CAMERA_REQUEST = 102;
    private static final int REQUEST_CAMERA_PERMISSION = 1;
    private static final int REQUEST_GALLERY_PERMISSION = 2;
    private static final int REQUEST_LOCATION_PERMISSION = 3;

    // Other variables
    private ContactLocation contactLocation;

    private ImageView ivProfile;
    private String urlImage = null;
    private Bitmap imageBackUp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        this.ivProfile = this.findViewById(R.id.ivProfile);

        Button btnCamera = this.findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(view -> this.handleCamera());

        Button btnGallery = this.findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(view -> this.handleGallery());
        
        Button btnSave = this.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(view -> this.saveContact());

        Button btnSetLocation = this.findViewById(R.id.btnLocation);
        btnSetLocation.setOnClickListener(view -> this.setLocation());

        EditText etPhone = this.findViewById(R.id.etPhone);
        etPhone.setVisibility(View.GONE);
    }

    private void setLocation() {
        if(
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED ||
                        checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            String[] permissions = new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };
            requestPermissions(permissions, REQUEST_LOCATION_PERMISSION);
            return;
        }

        SarethLocation sarethLocation = new SarethLocation(CreateContact.this, this);
        sarethLocation.startLocationUpdates();
    }

    @Override
    public void locationReceived(ContactLocation contactLocation) {
        this.contactLocation = contactLocation;
        Log.d("sareth_location", "setLocation: " + this.contactLocation);
        Toast.makeText(this, "Location saved", Toast.LENGTH_SHORT).show();
    }

    private void saveContact() {

        EditText etNames = this.findViewById(R.id.etNames);
        EditText etPhone = this.findViewById(R.id.etPhone);

        String names = etNames.getText().toString();
        //String phone = etPhone.getText().toString();

        Contact contact = new Contact(names, "");

        if (urlImage != null){
            if (this.contactLocation != null){
                contact.setImageUrl(urlImage);
                contact.setContactLocation(this.contactLocation);
                ContactService.create(contact, this);
            } else {
                Toast.makeText(this, "Getting location, wait", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Uploading image, wait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == OPEN_CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = data != null ? (Bitmap) data.getExtras().get("data") : null;

            try {
                this.ivProfile.setImageBitmap(photo);
                this.uploadImage(photo);
            } catch (NullPointerException e){
                Toast.makeText(this, "Error taking picture",
                        Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode == OPEN_GALLERY_REQUEST && resultCode == RESULT_OK) {

            Uri selectedImageUri = data != null ? data.getData() : null;

            try {
                Bitmap photo = MediaStore.Images.Media.getBitmap(getContentResolver(),
                        selectedImageUri);
                this.ivProfile.setImageBitmap(photo);
                this.uploadImage(photo);
            } catch (IOException | NullPointerException e) {
                Toast.makeText(this, "Error selecting an image from gallery",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadImage(Bitmap photo) {
        Toast.makeText(this, "Uploading image to API", Toast.LENGTH_SHORT).show();
        String image = ImageHelper.BitmapToString(photo);
        this.imageBackUp = photo;
        // Logic to upload photo
        SarethImage service = new SarethImage();
        service.uploadImageToApi(image, this);
    }

    private void handleGallery() {

        if(ContextCompat.checkSelfPermission(this, permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            this.openGalley();
        } else{
            String[] permissions = new String[] {permission.READ_EXTERNAL_STORAGE};
            this.requestPermissions(permissions, REQUEST_GALLERY_PERMISSION);
        }
    }

    private void handleCamera() {

        if(checkSelfPermission(Manifest.permission.CAMERA)  == PackageManager.PERMISSION_GRANTED)
        {
            this.openCamera();
        } else {
            String[] permissions = new String[] {Manifest.permission.CAMERA};
            this.requestPermissions(permissions, REQUEST_CAMERA_PERMISSION);
        }
    }

    private void openCamera(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, OPEN_CAMERA_REQUEST);
        } else {
            // Handle if the device doesn't have a camera app installed
            Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show();
        }
    }

    private void openGalley(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, OPEN_GALLERY_REQUEST);
    }

    @Override
    public void itemReceived(Object object) {
        String response = (String) object;

        if (!response.contains("Contact saved")){
            this.urlImage = response;
        }

        Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void itemNotReceived(String error) {
        Log.d("api_res", "itemReceived: " + error);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}