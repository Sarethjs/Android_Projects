package dev.sareth.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dev.sareth.contact.adpters.holders.CommetAdapter;
import dev.sareth.contact.listeners.CallbackListener;
import dev.sareth.contact.models.Comment;
import dev.sareth.contact.models.Contact;
import dev.sareth.contact.models.ContactLocation;
import dev.sareth.contact.services.CommentService;
import dev.sareth.contact.services.iImageService;

public class CommentIndex extends AppCompatActivity implements CallbackListener.comments,
        CallbackListener.item
{

    private Contact contact;
    private CommetAdapter commetAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_index);

        this.commetAdapter = new CommetAdapter(new ArrayList<>(), this);

        RecyclerView rv = this.findViewById(R.id.rvComments);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(this.commetAdapter);

        // Get contact
        Intent intent = this.getIntent();
        this.contact = (Contact) intent.getSerializableExtra("contact");
        Log.d("comments", "onCreate: " + contact);

        // Loading UI
        ImageView ivContact = this.findViewById(R.id.ivProfile);
        Picasso.get().load(iImageService.API_URL + contact.getImageUrl())
                .into(ivContact);

        TextView tvNames = this.findViewById(R.id.tvNames);
        tvNames.setText(contact.getName());

        TextView tvLocation = this.findViewById(R.id.tvLocation);
        tvLocation.setText(contact.getContactLocation().toString());

        // Adding functionalities
        Button btnLocation = this.findViewById(R.id.btnLoadLocation);
        btnLocation.setOnClickListener(view -> this.loadLocation(contact.getContactLocation()));


        /*
        Button btnComment = this.findViewById(R.id.btnComment);
        Log.d("comments", "button created: ");
        btnComment.setOnClickListener(view -> this.saveComment());
        Log.d("comments", "button click event added");
        //this.fetchComments();
         */

    }

    private void loadLocation(ContactLocation contactLocation) {

        Intent intent = new Intent(this, MapLocation.class);
        intent.putExtra("location", contact.getContactLocation());
        this.startActivity(intent);


    }

    private void saveComment() {

        EditText etComment = this.findViewById(R.id.etContent);
        String content = etComment.getText().toString();

        Comment comment = new Comment(contact.getId(), content);
        CommentService.create(comment, this);

    }

    private void fetchComments() {

        CommentService.find(contact, this);
    }

    @Override
    public void itemsReceived(List<Comment> items) {
        Toast.makeText(this, "Comments loaded: " + items.size(),
                Toast.LENGTH_SHORT).show();

        if (items.size() > 0) {
            this.commetAdapter.setItems(items);
        }

    }

    @Override
    public void itemsNotReceived(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void itemReceived(Object object) {
        fetchComments();
    }

    @Override
    public void itemNotReceived(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}