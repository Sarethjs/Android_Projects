package dev.sareth.anime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import dev.sareth.anime.controllers.AnimeController;
import dev.sareth.anime.models.Anime;

public class AnimeView extends AppCompatActivity {

    private EditText etName, etDescription, etUrl;
    private CheckBox cbFavorite;
    private Anime anime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_view);

        // Initiate components
        ImageView ivIcon = this.findViewById(R.id.ivIcon);
        this.etName = this.findViewById(R.id.etName);
        this.etDescription = this.findViewById(R.id.etDescription);
        this.etUrl = this.findViewById(R.id.etImageUrl);
        this.cbFavorite = this.findViewById(R.id.cbFavorite);
        Button btnDelete = this.findViewById(R.id.btnDelete);
        Button btnUpdate = this.findViewById(R.id.btnUpdate);

        Intent intent = this.getIntent();
        this.anime = (Anime) intent.getSerializableExtra("anime");

        // Set default data
        Picasso.get().load(this.anime.getImage()).into(ivIcon);
        btnDelete.setOnClickListener(view -> this.deleteAnime());
        btnUpdate.setOnClickListener(view -> this.updateAnime());
        this.etName.setText(this.anime.getName());
        this.etDescription.setText(this.anime.getDescription());
        this.etUrl.setText(this.anime.getImage());
        this.cbFavorite.setChecked(this.anime.isFavorite());
    }

    private void deleteAnime(){
        AnimeController animeController = new AnimeController(this);
        animeController.delete(anime.getId());
    }

    private void updateAnime(){
        String name = this.etName.getText().toString();
        String description = this.etDescription.getText().toString();
        String url = this.etUrl.getText().toString();
        boolean isFavorite = this.cbFavorite.isChecked();

        this.anime.setName(name);
        this.anime.setDescription(description);
        this.anime.setImage(url);
        this.anime.isFavorite(isFavorite);

        AnimeController animeController = new AnimeController(this);
        animeController.update(this.anime.getId(), this.anime);
    }
}