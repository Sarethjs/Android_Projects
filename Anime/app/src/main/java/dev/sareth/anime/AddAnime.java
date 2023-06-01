package dev.sareth.anime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import dev.sareth.anime.controllers.AnimeController;
import dev.sareth.anime.models.Anime;

public class AddAnime extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_anime);

        // Save button
        Button btnSave = this.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(view -> this.saveAnime());
    }

    private void saveAnime(){

        EditText etName = this.findViewById(R.id.etName);
        EditText etDescription = this.findViewById(R.id.etDescription);
        EditText etUrl = this.findViewById(R.id.etImageUrl);
        CheckBox cbFavorite = this.findViewById(R.id.cbFavorite);

        String names = etName.getText().toString();
        String description = etDescription.getText().toString();
        String url = etUrl.getText().toString();
        boolean isFavorite = cbFavorite.isChecked();

        Anime anime = new Anime(names, description, url, isFavorite);
        AnimeController animeController = new AnimeController(this);

        animeController.create(anime);
    }
}