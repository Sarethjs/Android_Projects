package dev.sareth.contact;

import androidx.annotation.NonNull;
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
import dev.sareth.contact.models.Landscape;
import dev.sareth.contact.models.BoxLocation;
import dev.sareth.contact.services.CommentService;
import dev.sareth.contact.services.iImageService;

public class CommentIndex extends AppCompatActivity implements CallbackListener.comments,
        CallbackListener.item
{

    private Landscape landscape;
    private CommetAdapter commetAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_index);

        this.commetAdapter = new CommetAdapter(new ArrayList<>(), this);

        RecyclerView rv = this.findViewById(R.id.rvComments);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(this.commetAdapter);

        // Get landscape
        Intent intent = this.getIntent();
        this.landscape = (Landscape) intent.getSerializableExtra("landscape");
        Log.d("comments", "onCreate: " + landscape);

        // Loading UI
        ImageView ivContact = this.findViewById(R.id.ivProfile);
        Picasso.get().load(iImageService.API_URL + landscape.getImageUrl())
                .into(ivContact);

        TextView tvNames = this.findViewById(R.id.tvNames);
        tvNames.setText(landscape.getName());

        TextView tvLocation = this.findViewById(R.id.tvLocation);
        tvLocation.setText(landscape.getContactLocation().toString());

        // Adding functionalities
        Button btnLocation = this.findViewById(R.id.btnLoadLocation);
        btnLocation.setOnClickListener(view -> this.loadLocation(landscape.getContactLocation()));



        Button btnComment = this.findViewById(R.id.btnComment);
        btnComment.setOnClickListener(view -> this.saveComment());
        this.fetchComments();
    }

    private void loadLocation(@NonNull BoxLocation boxLocation) {

        Intent intent = new Intent(this, MapLocation.class);
        intent.putExtra("location", this.landscape);
        this.startActivity(intent);
    }

    private void saveComment() {

        EditText etComment = this.findViewById(R.id.etContent);
        String content = etComment.getText().toString();

        Log.d("comments", "saveComment: saved for: " + landscape.getId());
        /*
        Comment comment = new Comment(landscape.getId(), content);
        CommentService.create(comment, this);
        this.fetchComments();
         */
    }

    private void fetchComments() {
        Log.d("comments", "fetchComments: fetching for: " + this.landscape.getId());
        CommentService.find(landscape, this);
    }

    @Override
    public void itemsReceived(List<Comment> items) {
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